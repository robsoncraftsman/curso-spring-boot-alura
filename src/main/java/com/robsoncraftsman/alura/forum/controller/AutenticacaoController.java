package com.robsoncraftsman.alura.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robsoncraftsman.alura.forum.controller.dto.TokenDto;
import com.robsoncraftsman.alura.forum.controller.form.LoginForm;
import com.robsoncraftsman.alura.forum.security.TokenService;

@RestController
@RequestMapping("auth")
@Profile(value = { "prod", "test" })
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid final LoginForm loginForm) {
		try {
			final var authentication = this.authenticationManager.authenticate(loginForm.convert());
			final var token = this.tokenService.gerarToken(authentication);

			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (final AuthenticationException ex) {
			return ResponseEntity.badRequest().build();
		}
	}

}
