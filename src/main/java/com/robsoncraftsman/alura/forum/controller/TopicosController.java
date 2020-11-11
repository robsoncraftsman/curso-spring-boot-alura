package com.robsoncraftsman.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robsoncraftsman.alura.forum.modelo.Curso;
import com.robsoncraftsman.alura.forum.modelo.Topico;

@Controller
public class TopicosController {

	@RequestMapping("/topicos")
	@ResponseBody
	public List<Topico> listar() {
		final var topico = new Topico("Dúvida", "Dúvida com Spring", new Curso("Spring Boot", "Programação"));

		return Arrays.asList(topico, topico, topico);
	}
}
