package com.robsoncraftsman.alura.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String email;
	@NotNull
	@NotEmpty
	@Length(min = 8)
	private String senha;

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(final String senha) {
		this.senha = senha;
	}

	public UsernamePasswordAuthenticationToken convert() {
		return new UsernamePasswordAuthenticationToken(this.email, this.senha);
	}

}
