package com.thec.card.interfaces;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.thec.card.context.CardStrategy;
import com.thec.card.enums.BrandEnum;
import com.thec.card.enums.CardStatusEnum;
import com.thec.card.request.CardRequest;
import com.thec.card.response.CardResponse;

public class DebitCard implements CardStrategy {
	
	@Override
	public CardResponse createCard(CardRequest cartao) {
		
		return CardResponse.builder()
				.name(cartao.getName())
				.cardBrand(BrandEnum.MASTERCARD)
				.expirationDate(LocalDate.now().plusDays(365))
				.rechargeDate(LocalDate.now().plusDays(25))
				.status(CardStatusEnum.INACTIVE)
				.cardLimit(BigDecimal.valueOf(500.00))
				.cardType(cartao.getCardType())
				.cardNumber(cartao.getCardNumber())
				.build();
		
	}
}
