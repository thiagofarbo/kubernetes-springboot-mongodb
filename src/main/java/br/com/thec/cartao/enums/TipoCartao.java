package br.com.thec.cartao.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.thec.cartao.context.CartaoStrategy;
import br.com.thec.cartao.interfaces.CartaoCredito;
import br.com.thec.cartao.interfaces.CartaoDebito;
import lombok.Getter;

@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public enum TipoCartao {
	
	DEBITO("Débito") {
		@Override
		public CartaoStrategy criarCartao() {
			return new CartaoDebito();
		}
	}, CREDITO("Crédito") {
		@Override
		public CartaoStrategy criarCartao() {
			return new CartaoCredito();
		}
	};
	
	private String descricao;
	
	TipoCartao(String descricao) {
		this.descricao = descricao;
	}
	
	public abstract CartaoStrategy criarCartao();
	
}
