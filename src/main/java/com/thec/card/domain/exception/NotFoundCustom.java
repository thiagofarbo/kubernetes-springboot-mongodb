package com.thec.card.domain.exception;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.thec.card.exception.ExceptionCard;

public class NotFoundCustom extends ExceptionCard {
	
	private static final long serialVersionUID = -7331739769883331451L;

	public NotFoundCustom (final String message) {
		super(NOT_FOUND, message);
		
	}	
	
}
