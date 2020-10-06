package br.com.thec.cartao.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionCartoes extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String message;
	private final HttpStatus status;

	public ExceptionCartoes(HttpStatus status, String message) {
		super(message);
		this.message = message;
		this.status = status;
	}

	public static void checkThrow(final boolean expression, final ExceptionsMessagesCartoesEnum exceptionsMessagesPoppuloEnum) {

		if (expression) {
			exceptionsMessagesPoppuloEnum.raise();
		}
	}
}
