package com.robsoncraftsman.alura.forum.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.robsoncraftsman.alura.forum.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final var usuarioOptional = this.usuarioRepository.findByEmail(username);
		if (usuarioOptional.isPresent()) {
			return usuarioOptional.get();
		} else {
			throw new UsernameNotFoundException("Invalid username");
		}
	}

}
