package com.thec.card.mapper;

import com.thec.card.domain.Card;
import com.thec.card.request.CardRequest;
import com.thec.card.request.CardRequestUpdate;
import com.thec.card.response.CardResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
	
	public CardResponse toCardResponse(final CardRequest cartao) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(cartao, CardResponse.class);
	}
	
	public Card ToCard(final CardRequest cartao) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(cartao, Card.class);
	}
	
	public Card ToCard(final CardResponse cartaoResponse) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(cartaoResponse, Card.class);
	}
	
	
	public CardResponse toCardResponse(final Card cartao) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(cartao, CardResponse.class);
	}
	
	
	public Card toCardUpdate(final CardRequestUpdate cartao) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(cartao, Card.class);
	}
	
//	public static <T> Page<T> toPageable(final List<T> list) {
//		
//		final Page<T> pageble = new PageImpl<>(list);	
//		
//		return pageble;
//	}

}
