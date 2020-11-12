package com.robsoncraftsman.alura.forum.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.robsoncraftsman.alura.forum.controller.dto.TopicoDetalhadoDto;
import com.robsoncraftsman.alura.forum.controller.dto.TopicoDto;
import com.robsoncraftsman.alura.forum.controller.form.AtualizarTopicoForm;
import com.robsoncraftsman.alura.forum.controller.form.TopicoForm;
import com.robsoncraftsman.alura.forum.modelo.Topico;
import com.robsoncraftsman.alura.forum.repository.CursoRepository;
import com.robsoncraftsman.alura.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;
	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	public List<TopicoDto> listar(final String nomeCurso) {
		List<Topico> topicos;
		if (nomeCurso == null) {
			topicos = this.topicoRepository.findAll();
		} else {
			topicos = this.topicoRepository.findByCursoNome(nomeCurso);
		}

		return TopicoDto.convert(topicos);
	}

	@PostMapping
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid final TopicoForm topicoForm,
			final UriComponentsBuilder uriBuilder) {
		final var topico = topicoForm.converter(this.cursoRepository);
		this.topicoRepository.save(topico);

		final var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}

	@GetMapping("/{id}")
	public TopicoDetalhadoDto detalhar(@PathVariable final Long id) {
		final var topico = this.topicoRepository.getOne(id);
		return new TopicoDetalhadoDto(topico);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDto> atualizar(@PathVariable final Long id,
			@RequestBody @Valid final AtualizarTopicoForm atualizarTopicoForm) {
		final var topico = atualizarTopicoForm.atualizar(id, this.topicoRepository);

		return ResponseEntity.ok(new TopicoDto(topico));
	}

}
