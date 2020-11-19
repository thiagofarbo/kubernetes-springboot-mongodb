package br.com.thec.cartao.interfaces;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.thec.cartao.context.CartaoStrategy;
import br.com.thec.cartao.enums.BandeiraEnum;
import br.com.thec.cartao.enums.StatusCartaoEnum;
import br.com.thec.cartao.request.CartaoRequest;
import br.com.thec.cartao.response.CartaoResponse;

public class CartaoDebito implements CartaoStrategy{
	
	@Override
	public CartaoResponse criarCartao(CartaoRequest cartao) {
		
		return CartaoResponse.builder()
				.nome(cartao.getNome())
				.bandeira(BandeiraEnum.MASTERCARD)
				.dataExpiracao(LocalDate.now().plusDays(365))
				.dataRecarga(LocalDate.now().plusDays(25))
				.status(StatusCartaoEnum.INATIVO)
				.limiteCartao(BigDecimal.valueOf(500.00))
				.tipoCartao(cartao.getTipoCartao())
				.numeroCartao(cartao.getNumeroCartao())
				.build();
		
	}
}
