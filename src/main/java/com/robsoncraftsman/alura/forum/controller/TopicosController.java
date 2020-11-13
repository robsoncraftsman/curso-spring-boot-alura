package com.robsoncraftsman.alura.forum.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.robsoncraftsman.alura.forum.controller.dto.TopicoDetalhadoDto;
import com.robsoncraftsman.alura.forum.controller.dto.TopicoResumidoDto;
import com.robsoncraftsman.alura.forum.controller.form.AtualizarTopicoForm;
import com.robsoncraftsman.alura.forum.controller.form.CadastrarTopicoForm;
import com.robsoncraftsman.alura.forum.model.Topico;
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
	public List<TopicoResumidoDto> listar(final String nomeCurso) {
		List<Topico> topicos;
		if (nomeCurso == null) {
			topicos = this.topicoRepository.findAll();
		} else {
			topicos = this.topicoRepository.findByCursoNome(nomeCurso);
		}

		return TopicoResumidoDto.convert(topicos);
	}

	@GetMapping("/{id}")
	public TopicoDetalhadoDto detalhar(@PathVariable final Long id) {
		final var topico = this.topicoRepository.getOne(id);
		return new TopicoDetalhadoDto(topico);
	}

	@PostMapping
	@Transactional
	@ResponseStatus(code = HttpStatus.CREATED)
	public TopicoResumidoDto cadastrar(@RequestBody @Valid final CadastrarTopicoForm cadastrarTopicoForm,
			final UriComponentsBuilder uriBuilder) {
		final var topico = cadastrarTopicoForm.converter(this.cursoRepository);
		this.topicoRepository.save(topico);

		return new TopicoResumidoDto(topico);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoResumidoDto> atualizar(@PathVariable final Long id,
			@RequestBody @Valid final AtualizarTopicoForm atualizarTopicoForm) {
		final var topico = atualizarTopicoForm.atualizar(id, this.topicoRepository);

		return ResponseEntity.ok(new TopicoResumidoDto(topico));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable final Long id) {
		this.topicoRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
