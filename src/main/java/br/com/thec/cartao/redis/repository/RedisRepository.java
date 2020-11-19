package br.com.thec.cartao.redis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import br.com.thec.cartao.cache.CacheConfig;
import br.com.thec.cartao.domain.Cartao;

@Repository
public class RedisRepository {
	
	@Autowired
	private CacheConfig config;
	
	public Cartao findCartao(final String hash, final String id) {
		
		Object object = config.redisTemplate().opsForHash().get(hash, id);
		
		Cartao cartao = (Cartao) object;
		
		return cartao;
	}
	
	public void save(final Cartao cartao, final String hash) {
		
		HashOperations<String, Object, Object> hashOperations = config.redisTemplate().opsForHash();
		hashOperations.put(hash, cartao.getId(), cartao);
	}
	
}
