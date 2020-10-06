package br.com.thec.cartao.domain.exception;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import br.com.thec.cartao.exception.ExceptionCartoes;

public class NotFoundCustom extends ExceptionCartoes {
	
	private static final long serialVersionUID = -7331739769883331451L;

	public NotFoundCustom (final String message) {
		super(NOT_FOUND, message);
		
	}	
	
}
