package com.thec.card.domain.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.thec.card.exception.ExceptionCard;

public class BadRequestCustom extends ExceptionCard {

	private static final long serialVersionUID = 4711372295703897008L;

	public BadRequestCustom(final String message) {
		super(BAD_REQUEST, message);
	}

}
