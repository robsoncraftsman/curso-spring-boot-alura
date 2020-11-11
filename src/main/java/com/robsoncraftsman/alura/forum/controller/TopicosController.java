package com.robsoncraftsman.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robsoncraftsman.alura.forum.controller.dto.TopicoDto;
import com.robsoncraftsman.alura.forum.repository.TopicoRepository;

@RestController
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;

	@RequestMapping("/topicos")
	public List<TopicoDto> listar() {
		final var topicos = this.topicoRepository.findAll();

		return TopicoDto.convert(topicos);
	}

}
