package com.thec.card.exception;

import static java.util.Collections.singletonList;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.thec.card.domain.exception.BadRequestCustom;
import com.thec.card.domain.exception.NoContentCustom;
import com.thec.card.domain.exception.NotFoundCustom;

@ControllerAdvice
public class GlobalExceptionHandler {

	public static final String GLOBAL_MESSAGE_204 = "No content.";
	public static final String GLOBAL_MESSAGE_400 = "Invalid request.";
	public static final String GLOBAL_MESSAGE_404 = "Resource not found.";
	public static final String GLOBAL_MESSAGE_500 = "Internal system error.";

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Card does not exist.")
	@ExceptionHandler(NotFoundCustom.class)
	public @ResponseBody ErrorInfo handleCardDoesNotExistException(HttpServletRequest request, NotFoundCustom exception) {
		return builderErrorInfo(request, exception, singletonList(exception.getMessage()));
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Internal system error.")
	@ExceptionHandler({ BadRequestCustom.class })
	public @ResponseBody ErrorInfo handleCardUpdateErrorException(HttpServletRequest request, BadRequestCustom exception) {
		return builderErrorInfo(request, exception, singletonList(exception.getMessage()));
	}

	@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "No card for this request.")
	@ExceptionHandler({ NoContentCustom.class })
	public @ResponseBody ErrorInfo handleCardNotFoundException(HttpServletRequest request, NoContentCustom exception) {
		return builderErrorInfo(request, exception, singletonList(exception.getMessage()));
	}

	private ErrorInfo builderErrorInfo(HttpServletRequest request, Exception exception, List<String> messages) {
		return ErrorInfo.builder().
				timestamp(LocalDateTime.now())
				.messages(messages)
				.exception(exception.getClass().getSimpleName())
				.path(request.getRequestURI())
				.build();
	}
}