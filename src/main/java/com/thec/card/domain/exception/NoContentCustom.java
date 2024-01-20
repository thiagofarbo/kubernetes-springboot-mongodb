package com.thec.card.domain.exception;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.thec.card.exception.ExceptionCard;

public class NoContentCustom extends ExceptionCard {

	private static final long serialVersionUID = 6344045555428479067L;

	public NoContentCustom(final String message) {
		super(NO_CONTENT, message);

	}

}
