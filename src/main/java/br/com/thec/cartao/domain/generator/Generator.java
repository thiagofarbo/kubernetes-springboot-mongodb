package br.com.thec.cartao.domain.generator;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class Generator {
	
	public String cardGenerator() {
		
		final UUID fromString = UUID.randomUUID();
		
		return fromString.toString();
	}
}
