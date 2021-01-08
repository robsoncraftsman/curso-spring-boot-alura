package com.robsoncraftsman.alura.forum.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.robsoncraftsman.alura.forum.model.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${app.jwt.expiration}")
	private Long expiration;

	@Value("${app.jwt.secret}")
	private String secret;

	public String gerarToken(final Authentication authentication) {
		final var usuario = (Usuario) authentication.getPrincipal();

		final var agora = new Date();
		final var expirationDate = new Date(agora.getTime() + this.expiration);

		return Jwts.builder().setIssuer("API Curso Spring Boot")
				.setSubject(usuario.getId().toString())
				.setIssuedAt(agora)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, this.secret)
				.compact();
	}

	public boolean isTokenValido(final String token) {
		try {
			final var claimsJws = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			System.out.println(claimsJws);
			return true;
		} catch (final Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	public Long getIdUsuario(final String token) {
		final var claimsJws = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
		final var body = claimsJws.getBody();
		return Long.parseLong(body.getSubject());
	}

}
