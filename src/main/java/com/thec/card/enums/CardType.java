package com.thec.card.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.thec.card.context.CardStrategy;
import com.thec.card.interfaces.CreditCard;
import com.thec.card.interfaces.DebitCard;
import lombok.Getter;

@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public enum CardType {
	
	DEBIT("Debit") {
		@Override
		public CardStrategy criarCartao() {
			return new DebitCard();
		}
	}, CREDIT("Credit") {
		@Override
		public CardStrategy criarCartao() {
			return new CreditCard();
		}
	};
	
	private String descricao;
	
	CardType(String descricao) {
		this.descricao = descricao;
	}
	
	public abstract CardStrategy criarCartao();
	
}
