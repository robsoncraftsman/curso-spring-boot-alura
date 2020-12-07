package com.robsoncraftsman.alura.forum.controller.dto;

public class TokenDto {

	private final String token;
	private final String tipo;

	public TokenDto(final String token, final String tipo) {
		this.token = token;
		this.tipo = tipo;
	}

	public String getToken() {
		return this.token;
	}

	public String getTipo() {
		return this.tipo;
	}

}
