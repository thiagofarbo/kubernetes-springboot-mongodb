package br.com.thec.cartao.event;

import java.net.URI;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class CartaoEventListener<K,T> {
	
	@EventListener
	public void adicionarHeaderLocation(RecursoCriadoEvent<K, T> event) {
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
		.buildAndExpand(event.getId()).toUri();
		
		event.getResponse().setHeader("Location", uri.toASCIIString());
	}
}
