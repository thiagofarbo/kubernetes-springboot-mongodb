package com.thec.card.redis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import com.thec.card.cache.CacheConfig;
import com.thec.card.domain.Card;

@Repository
public class RedisRepository {
	
	@Autowired
	private CacheConfig config;
	
	public Card findCard(final String hash, final String id) {
		
		Object object = config.redisTemplate().opsForHash().get(hash, id);
		
		Card cartao = (Card) object;
		
		return cartao;
	}
	
	public void save(final Card card, final String hash) {
		HashOperations<String, Object, Object> hashOperations = config.redisTemplate().opsForHash();
		hashOperations.put(hash, card.getId(), card);
	}
	
}
