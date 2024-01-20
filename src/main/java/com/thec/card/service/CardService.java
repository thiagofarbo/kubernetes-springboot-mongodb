package com.thec.card.service;

import java.util.Objects;
import java.util.Optional;

import com.thec.card.exception.ExceptionCard;
import com.thec.card.exception.ExceptionsMessagesCartoesEnum;
import com.thec.card.repository.CardRepository;
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

import com.thec.card.context.CardStrategy;
import com.thec.card.domain.Card;
import com.thec.card.domain.generator.Generator;
import com.thec.card.enums.CardStatusEnum;
import com.thec.card.event.CardCreatedEvent;
import com.thec.card.mapper.Mapper;
import com.thec.card.redis.repository.RedisRepository;
import com.thec.card.request.CardRequest;
import com.thec.card.request.CardRequestUpdate;
import com.thec.card.response.CardResponse;
import com.thec.sqs.service.QueueService;

@Service
public class CardService {
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
	private Generator generator;
	
	@Autowired
	private RedisRepository redisRepository;
	
	@Autowired
	private CardRepository cartaoRepository;
	
	@Value("${hash.key}")
	private String hashKey;
	

	@Transactional
	public CardResponse createCard(final CardRequest cartaoRequest) {
		
		CardStrategy cartaoStrategy = cartaoRequest.getCardType().criarCartao();
		
		final String numeroCartao = generator.cardGenerator();
		cartaoRequest.setCardNumber(numeroCartao);
		
		final CardResponse cartaoResponse = cartaoStrategy.createCard(cartaoRequest);
		
		final Card cartao = this.cartaoRepository.save(mapper.ToCard(cartaoResponse));
		
		this.redisRepository.save(cartao, hashKey);
		
		return mapper.toCardResponse(cartao);
		
	}
	
	public CardResponse findById(final String id) {
		
	    Optional<Card> cartao  = Optional.empty();
		
		Card cartaoCache = redisRepository.findCard(hashKey, id);
		
		if(Objects.isNull(cartaoCache)) {
			
			cartao = this.cartaoRepository.findById(id);
			
			ExceptionCard.checkThrow(!cartao.isPresent(), ExceptionsMessagesCartoesEnum.GLOBAL_RESOURCE_NOT_FOUND);
			
			return mapper.toCardResponse(cartao.get());
		}
		
		return mapper.toCardResponse(cartaoCache);
	}
	
	@Cacheable(cacheNames = "Card", key="#root.method.name")
	public Page<Card> listCards(final int page, final int size) {
		
		final Page<Card> card = this.cartaoRepository.findAll(PageRequest.of(page, size));

		ExceptionCard.checkThrow(CollectionUtils.isEmpty(card.getContent()), ExceptionsMessagesCartoesEnum.GLOBAL_NO_CONTENT);
		
		return card;
	}

	@Transactional
	public CardResponse updateCards(final CardRequestUpdate cartaoRequest, final String id) {
		
		final Optional<Card> cartaoOpt = this.cartaoRepository.findById(id);
		
		ExceptionCard.checkThrow(!cartaoOpt.isPresent(), ExceptionsMessagesCartoesEnum.GLOBAL_RESOURCE_NOT_FOUND);
		
		final Card cartao = this.cartaoRepository.save(mapper.toCardUpdate(cartaoRequest));
		
		ExceptionCard.checkThrow(Objects.isNull(cartao), ExceptionsMessagesCartoesEnum.ERROR_TO_UPDATE_CARD);

		return mapper.toCardResponse(cartao);

		
	}

	@Transactional
	public CardResponse deleteCard(final String id) {
		
		final Optional<Card> cartao = this.cartaoRepository.findById(id);
		
		ExceptionCard.checkThrow(!cartao.isPresent(), ExceptionsMessagesCartoesEnum.GLOBAL_RESOURCE_NOT_FOUND);
		
		this.cartaoRepository.deleteById(id);
		
		return CardResponse.builder().id(id).build();
	}
	
	@Transactional
	public CardRequestUpdate updateStatusCard(CardRequestUpdate cartaoRequest, String id) {
		
		final Optional<Card> cartao = this.cartaoRepository.findById(id);
		
		ExceptionCard.checkThrow(Objects.isNull(cartaoRequest.getStatus()), ExceptionsMessagesCartoesEnum.GLOBAL_BAD_REQUEST);
		
		final CardStatusEnum statusCartao = mapStatusCartao(cartaoRequest.getStatus());
		
		ExceptionCard.checkThrow(Objects.isNull(statusCartao), ExceptionsMessagesCartoesEnum.GLOBAL_BAD_REQUEST);
		
		cartao.get().setStatus(statusCartao);
		
		Card cart = this.cartaoRepository.save(cartao.get());

		return CardRequestUpdate.builder().id(cart.getId()).status(cart.getStatus()).build();
				
	}
	
	private static CardStatusEnum mapStatusCartao(CardStatusEnum statusRequest) {
	    for (CardStatusEnum status : CardStatusEnum.values()) {
	        if (status.name().equals(statusRequest.name())) {
	        	return status;
	        }
	    }
	    return null;
	}
	@Async
	@EventListener
	public void sendToQueue(final CardCreatedEvent cartaoCriadoEvent) {
		QueueService.send(cartaoCriadoEvent);
	}
}