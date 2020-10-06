package br.com.thec.cartao.domain.exception;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import br.com.thec.cartao.exception.ExceptionCartoes;

public class NoContentCustom extends ExceptionCartoes {

	private static final long serialVersionUID = 6344045555428479067L;

	public NoContentCustom(final String message) {
		super(NO_CONTENT, message);

	}

}
