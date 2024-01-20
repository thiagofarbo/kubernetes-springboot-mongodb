package com.thec.card.context;

import com.thec.card.response.CardResponse;
import org.springframework.stereotype.Component;

import com.thec.card.request.CardRequest;

@Component
public interface CardStrategy {
	
	public CardResponse createCard(CardRequest cartaoRequest);

}
