
package com.thec.card.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.thec.card.domain.exception.BadRequestCustom;
import com.thec.card.domain.exception.NoContentCustom;
import com.thec.card.domain.exception.NotFoundCustom;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum ExceptionsMessagesCartoesEnum {

	GLOBAL_INTERNAL_SERVER_ERROR(INTERNAL_SERVER_ERROR.value(), "Internal Server Error", ExceptionCard.class),
	GLOBAL_BAD_REQUEST(BAD_REQUEST.value(), "Internal system error.", BadRequestCustom.class),
	ERROR_TO_UPDATE_STATUS_CARD(BAD_REQUEST.value(), "Error updating card status.", BadRequestCustom.class),
	GLOBAL_NO_CONTENT(NO_CONTENT.value(), "No content for this request.", NoContentCustom.class),
	GLOBAL_RESOURCE_NOT_FOUND(NOT_FOUND.value(), "Item does not exist", NotFoundCustom.class),
	ERROR_TO_UPDATE_CARD(BAD_REQUEST.value(), "Error updating card", BadRequestCustom.class);


	private Integer code;

	@Setter
	private String message;

	ExceptionsMessagesCartoesEnum(int code, String message, Class<? extends ExceptionCard> klass) {
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
			throw new ExceptionCard(INTERNAL_SERVER_ERROR, this.message);
		}
	}
}
