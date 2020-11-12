package com.robsoncraftsman.alura.forum.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ValidationError> handle(final MethodArgumentNotValidException exception) {
		final List<ValidationError> validationErrors = new ArrayList<>();
		final var fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.stream().forEach(fieldError -> {
			final var field = fieldError.getField();
			final var message = this.messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			validationErrors.add(new ValidationError(field, message));
		});
		return validationErrors;
	}

}
