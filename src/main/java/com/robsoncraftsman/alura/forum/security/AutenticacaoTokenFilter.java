package com.robsoncraftsman.alura.forum.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.robsoncraftsman.alura.forum.repository.UsuarioRepository;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {

	private static final String BEARER = "Bearer";

	private final TokenService tokenService;
	private final UsuarioRepository usuarioRepository;

	public AutenticacaoTokenFilter(final TokenService tokenService, final UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain filterChain)
					throws ServletException, IOException {

		final var authorization = request.getHeader("Authorization");
		autenticate(authorization);

		filterChain.doFilter(request, response);
	}

	private void autenticate(final String authorization) {
		final var token = recuperarToken(authorization);
		if (token != null) {
			final var isTokenValido = this.tokenService.isTokenValido(token);

			if (isTokenValido) {
				autenticarCliente(token);
			}
		}
	}

	private String recuperarToken(final String authorization) {
		if ((authorization != null) && !authorization.isBlank() && authorization.startsWith(BEARER)) {
			return authorization.substring(BEARER.length() + 1, authorization.length());
		}

		return null;
	}

	private void autenticarCliente(final String token) {
		final var idUsuario = this.tokenService.getIdUsuario(token);

		final var usuarioOptional = this.usuarioRepository.findById(idUsuario);
		if (usuarioOptional.isPresent()) {
			final var usuario = usuarioOptional.get();
			final var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}

}
