package com.thec.card.event;

import javax.servlet.http.HttpServletResponse;

import com.thec.card.request.CardRequest;

public class CardCreatedEvent extends ResourceCreatedEvent<String, CardRequest> {
	
	public CardCreatedEvent(CardRequest card, HttpServletResponse response, String queue) {
		super(card.getId(), card, response, queue);
	}
}
