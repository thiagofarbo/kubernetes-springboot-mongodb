package br.com.thec.cartao.event;

import javax.servlet.http.HttpServletResponse;

import br.com.thec.cartao.request.CartaoRequest;

public class CartaoCriadoEvent extends RecursoCriadoEvent<String, CartaoRequest>{
	
	public CartaoCriadoEvent(CartaoRequest cartao, HttpServletResponse response, String queue) {
		super(cartao.getId(), cartao, response, queue);
	}
}
