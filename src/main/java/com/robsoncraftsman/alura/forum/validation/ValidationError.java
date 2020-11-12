package com.robsoncraftsman.alura.forum.validation;

public class ValidationError {

	private final String field;
	private final String message;

	public ValidationError(final String field, final String message) {
		super();
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return this.field;
	}

	public String getMessage() {
		return this.message;
	}

}
