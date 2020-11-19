package br.com.thec.cartao.service;

import static br.com.thec.cartao.exception.ExceptionCartoes.checkThrow;
import static br.com.thec.cartao.exception.ExceptionsMessagesCartoesEnum.ERROR_TO_UPDATE_CARD;
import static br.com.thec.cartao.exception.ExceptionsMessagesCartoesEnum.GLOBAL_BAD_REQUEST;
import static br.com.thec.cartao.exception.ExceptionsMessagesCartoesEnum.GLOBAL_NO_CONTENT;
import static br.com.thec.cartao.exception.ExceptionsMessagesCartoesEnum.GLOBAL_RESOURCE_NOT_FOUND;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.com.thec.cartao.context.CartaoStrategy;
import br.com.thec.cartao.domain.Cartao;
import br.com.thec.cartao.domain.generator.Generator;
import br.com.thec.cartao.enums.StatusCartaoEnum;
import br.com.thec.cartao.event.CartaoCriadoEvent;
import br.com.thec.cartao.mapper.Mapper;
import br.com.thec.cartao.redis.repository.RedisRepository;
import br.com.thec.cartao.repository.CartaoRepository;
import br.com.thec.cartao.request.CartaoRequest;
import br.com.thec.cartao.request.CartaoRequestUpdate;
import br.com.thec.cartao.response.CartaoResponse;
import br.com.thec.sqs.service.QueueService;

@Service
public class CartaoService {
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
	private Generator generator;
	
	@Autowired
	private RedisRepository redisRepository;
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Value("${hash.key}")
	private String hashKey;
	

	@Transactional
	public CartaoResponse criarCartao(final CartaoRequest cartaoRequest) {
		
		CartaoStrategy cartaoStrategy = cartaoRequest.getTipoCartao().criarCartao();
		
		final String numeroCartao = generator.cardGenerator();
		cartaoRequest.setNumeroCartao(numeroCartao);
		
		final CartaoResponse cartaoResponse = cartaoStrategy.criarCartao(cartaoRequest);
		
		final Cartao cartao = this.cartaoRepository.save(mapper.mapToCartao(cartaoResponse));
		
		this.redisRepository.save(cartao, hashKey);
		
		return mapper.mapToModelResponse(cartao);
		
	}
	
	public CartaoResponse consultarCartao(final String id) {
		
	    Optional<Cartao> cartao  = Optional.empty();
		
		Cartao cartaoCache = redisRepository.findCartao(hashKey, id);
		
		if(Objects.isNull(cartaoCache)) {
			
			cartao = this.cartaoRepository.findById(id);
			
			checkThrow(!cartao.isPresent(), GLOBAL_RESOURCE_NOT_FOUND);
			
			return mapper.mapToModelResponse(cartao.get()); 
		}
		
		return mapper.mapToModelResponse(cartaoCache);
	}
	
	@Cacheable(cacheNames = "Cartao", key="#root.method.name")
	public Page<Cartao> listarCartoes(final int page, final int size) {
		
		final Page<Cartao> cartoes = this.cartaoRepository.findAll(PageRequest.of(page, size));

		checkThrow(CollectionUtils.isEmpty(cartoes.getContent()), GLOBAL_NO_CONTENT);
		
		return cartoes;
	}

	@Transactional
	public CartaoResponse atualizarCartao(final CartaoRequestUpdate cartaoRequest, final String id) {
		
		final Optional<Cartao> cartaoOpt = this.cartaoRepository.findById(id);
		
		checkThrow(!cartaoOpt.isPresent(), GLOBAL_RESOURCE_NOT_FOUND);
		
		final Cartao cartao = this.cartaoRepository.save(mapper.mapToModelUpdate(cartaoRequest));
		
		checkThrow(Objects.isNull(cartao), ERROR_TO_UPDATE_CARD);

		return CartaoResponse.builder()
				.nome(cartao.getNome())
				.dataRecarga(cartao.getDataRecarga())
				.dataExpiracao(cartao.getDataExpiracao())
				.tipoProduto(cartao.getTipoProduto()).build();
		
	}

	@Transactional
	public CartaoResponse excluirCartao(final String id) {
		
		final Optional<Cartao> cartao = this.cartaoRepository.findById(id);
		
		checkThrow(!cartao.isPresent(), GLOBAL_RESOURCE_NOT_FOUND);
		
		this.cartaoRepository.deleteById(id);
		
		return CartaoResponse.builder().id(id).build();	
	}
	
	@Transactional
	public CartaoRequestUpdate atualizarStatusCartao(CartaoRequestUpdate cartaoRequest, String id) {
		
		final Optional<Cartao> cartao = this.cartaoRepository.findById(id);
		
		checkThrow(Objects.isNull(cartaoRequest.getStatus()), GLOBAL_BAD_REQUEST);
		
		final StatusCartaoEnum statusCartao = mapStatusCartao(cartaoRequest.getStatus());
		
		checkThrow(Objects.isNull(statusCartao), GLOBAL_BAD_REQUEST);
		
		cartao.get().setStatus(statusCartao);
		
		Cartao cart = this.cartaoRepository.save(cartao.get());
		
		return CartaoRequestUpdate.builder().id(cart.getId()).status(cart.getStatus()).build();
				
	}
	
	private static StatusCartaoEnum mapStatusCartao(StatusCartaoEnum statusRequest) {
	    for (StatusCartaoEnum status : StatusCartaoEnum.values()) {
	        if (status.name().equals(statusRequest.name())) {
	        	return status;
	        }
	    }
	    return null;
	}
	@Async
	@EventListener
	public void sendToQueue(final CartaoCriadoEvent cartaoCriadoEvent) {
		QueueService.send(cartaoCriadoEvent);
	}
}