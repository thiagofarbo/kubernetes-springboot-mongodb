
package br.com.thec.cartao.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import br.com.thec.cartao.domain.exception.BadRequestCustom;
import br.com.thec.cartao.domain.exception.NoContentCustom;
import br.com.thec.cartao.domain.exception.NotFoundCustom;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum ExceptionsMessagesCartoesEnum {

	GLOBAL_INTERNAL_SERVER_ERROR(INTERNAL_SERVER_ERROR.value(), "Internal Server Error", ExceptionCartoes.class),
	GLOBAL_BAD_REQUEST(BAD_REQUEST.value(), "Erro interno no sistema.", BadRequestCustom.class),
	ERROR_TO_UPDATE_STATUS_CARD(BAD_REQUEST.value(), "Erro ao atualizar o status do cartao.", BadRequestCustom.class),
	GLOBAL_NO_CONTENT(NO_CONTENT.value(), "Não existe conteudo para essa requisição.", NoContentCustom.class),
	GLOBAL_RESOURCE_NOT_FOUND(NOT_FOUND.value(), "Item não existe", NotFoundCustom.class),
	ERROR_TO_UPDATE_CARD(BAD_REQUEST.value(), "Erro ao atualizar o cartao", BadRequestCustom.class);

	private Integer code;

	@Setter
	private String message;

	ExceptionsMessagesCartoesEnum(int code, String message, Class<? extends ExceptionCartoes> klass) {
		this.message = message;
		this.code = code;
	}

	public void raise() {

		if (BAD_REQUEST.value() == this.code) {

			throw new BadRequestCustom(this.message);

		} else if (NOT_FOUND.value() == this.code) {

			throw new NotFoundCustom(this.message);

		} else if (NO_CONTENT.value() == this.code) {
			
			throw new NoContentCustom(this.message);
		
		}else {
			throw new ExceptionCartoes(INTERNAL_SERVER_ERROR, this.message);
		}
	}
}
