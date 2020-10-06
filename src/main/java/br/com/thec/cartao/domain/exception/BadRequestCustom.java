package br.com.thec.cartao.domain.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import br.com.thec.cartao.exception.ExceptionCartoes;

public class BadRequestCustom extends ExceptionCartoes {

	private static final long serialVersionUID = 4711372295703897008L;

	public BadRequestCustom(final String message) {
		super(BAD_REQUEST, message);
	}

}
