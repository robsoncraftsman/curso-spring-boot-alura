package com.robsoncraftsman.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robsoncraftsman.alura.forum.controller.dto.TopicoDto;
import com.robsoncraftsman.alura.forum.modelo.Curso;
import com.robsoncraftsman.alura.forum.modelo.Topico;

@RestController
public class TopicosController {

	@RequestMapping("/topicos")
	public List<TopicoDto> listar() {
		final var topico = new Topico("Dúvida", "Dúvida com Spring Boot", new Curso("Spring Boot", "Programação"));

		return TopicoDto.convert(Arrays.asList(topico, topico, topico));
	}

}
