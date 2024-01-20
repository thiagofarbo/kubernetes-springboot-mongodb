package com.thec.card.cache.service;

import java.util.Objects;

import com.thec.card.domain.Card;
import com.thec.card.mapper.Mapper;
import com.thec.card.redis.repository.RedisRepository;
import com.thec.card.response.CardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CartaoCacheService {
	
	@Autowired
	private Mapper mapper;
	
	@Value("${hash.key}")
	private String hashKey;
	
	@Autowired
	private RedisRepository redisRepository;
	
	public CardResponse getCardFromCache(final String id) {
		
		final Card cardCache = redisRepository.findCard(hashKey, id);
		
		if(Objects.isNull(cardCache)) {
			System.out.println(CardResponse.builder().name("Unavailable").build());
			return CardResponse.builder().name("Unavailable").build();
		}
		
		return mapper.toCardResponse(cardCache);
	}
}