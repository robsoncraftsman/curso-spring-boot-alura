package com.robsoncraftsman.alura.forum.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@Cacheable(value = "listaDeTopicos")
	public Page<TopicoResumidoDto> listar(
			@RequestParam(required = false) final String nomeCurso,
			@PageableDefault(sort = "id", direction = Sort.Direction.ASC) final Pageable paginacao) {

		Page<Topico> topicosPage;
		if (nomeCurso == null) {
			topicosPage = this.topicoRepository.findAll(paginacao);
		} else {
			topicosPage = this.topicoRepository.findByCursoNome(nomeCurso, paginacao);
		}

		return TopicoResumidoDto.convert(topicosPage);
	}

	@GetMapping("/{id}")
	public TopicoDetalhadoDto detalhar(@PathVariable final Long id) {
		final var topico = this.topicoRepository.getOne(id);
		return new TopicoDetalhadoDto(topico);
	}

	@PostMapping
	@Transactional
	@ResponseStatus(code = HttpStatus.CREATED)
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public TopicoResumidoDto cadastrar(@RequestBody @Valid final CadastrarTopicoForm cadastrarTopicoForm,
			final UriComponentsBuilder uriBuilder) {
		final var topico = cadastrarTopicoForm.converter(this.cursoRepository);
		this.topicoRepository.save(topico);

		return new TopicoResumidoDto(topico);
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public ResponseEntity<TopicoResumidoDto> atualizar(@PathVariable final Long id,
			@RequestBody @Valid final AtualizarTopicoForm atualizarTopicoForm) {
		final var topico = atualizarTopicoForm.atualizar(id, this.topicoRepository);

		return ResponseEntity.ok(new TopicoResumidoDto(topico));
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public ResponseEntity<Object> remover(@PathVariable final Long id) {
		this.topicoRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
