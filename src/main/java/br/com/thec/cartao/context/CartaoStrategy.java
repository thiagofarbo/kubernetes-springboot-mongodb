package br.com.thec.cartao.context;

import org.springframework.stereotype.Component;

import br.com.thec.cartao.request.CartaoRequest;
import br.com.thec.cartao.response.CartaoResponse;

@Component
public interface CartaoStrategy {
	
	public CartaoResponse criarCartao(CartaoRequest cartaoRequest);

}
