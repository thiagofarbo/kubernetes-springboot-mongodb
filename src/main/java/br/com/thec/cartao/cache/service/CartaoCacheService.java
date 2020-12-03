package br.com.thec.cartao.cache.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.thec.cartao.domain.Cartao;
import br.com.thec.cartao.mapper.Mapper;
import br.com.thec.cartao.redis.repository.RedisRepository;
import br.com.thec.cartao.response.CartaoResponse;

@Service
public class CartaoCacheService {
	
	@Autowired
	private Mapper mapper;
	
	@Value("${hash.key}")
	private String hashKey;
	
	@Autowired
	private RedisRepository redisRepository;
	
	public CartaoResponse getCartaoFromCache(final String id) {
		
		final Cartao cartaoCache = redisRepository.findCartao(hashKey, id);
		
		if(Objects.isNull(cartaoCache)) {
			System.out.println(CartaoResponse.builder().nome("Indisponivel").build());
			return CartaoResponse.builder().nome("Indisponivel").build();
		}
		
		return mapper.mapToModelResponse(cartaoCache);
	}
}