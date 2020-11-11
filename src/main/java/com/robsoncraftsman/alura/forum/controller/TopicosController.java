package com.robsoncraftsman.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robsoncraftsman.alura.forum.controller.dto.TopicoDto;
import com.robsoncraftsman.alura.forum.modelo.Topico;
import com.robsoncraftsman.alura.forum.repository.TopicoRepository;

@RestController
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;

	@RequestMapping("/topicos")
	public List<TopicoDto> listar(final String nomeCurso) {
		List<Topico> topicos;
		if (nomeCurso == null) {
			topicos = this.topicoRepository.findAll();
		} else {
			topicos = this.topicoRepository.findByCursoNome(nomeCurso);
		}

		return TopicoDto.convert(topicos);
	}

}
