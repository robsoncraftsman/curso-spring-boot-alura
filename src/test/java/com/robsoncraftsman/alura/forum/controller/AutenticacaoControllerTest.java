package com.robsoncraftsman.alura.forum.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.robsoncraftsman.alura.forum.model.Usuario;
import com.robsoncraftsman.alura.forum.repository.UsuarioRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AutenticacaoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Test
	void naoDeveAutenticarComUsuarioInvalido() throws Exception {
		final var uri = new URI("/auth");
		final var json = """
				{"email":"invalid@email.com.br",
				"senha": "12345678"}
				""";

		this.mockMvc.perform(
			post(uri)
			.content(json)
			.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	void deveAutenticarComUsuarioValido() throws Exception {

		final Usuario usuario = new Usuario();
		usuario.setEmail("aluno@email.com.br");
		usuario.setSenha("$2a$10$llhfzOf6R01gvGuSRzPJsuIm7p8QHKGi50QPotM5ZU32iDw/PE7c6");
		this.usuarioRepository.save(usuario);

		final var uri = new URI("/auth");
		final var json = """
				{"email":"aluno@email.com.br",
				"senha": "12345678"}
				""";

		this.mockMvc.perform(
			post(uri)
			.content(json)
			.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
