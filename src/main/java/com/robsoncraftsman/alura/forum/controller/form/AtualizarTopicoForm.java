package com.robsoncraftsman.alura.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.robsoncraftsman.alura.forum.modelo.Topico;
import com.robsoncraftsman.alura.forum.repository.TopicoRepository;

public class AtualizarTopicoForm {

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String titulo;
	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String mensagem;

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return this.mensagem;
	}

	public void setMensagem(final String mensagem) {
		this.mensagem = mensagem;
	}

	public Topico atualizar(final Long id, final TopicoRepository topicoRepository) {
		// Ao atualizar os atributos, o JPA faz a automaticamente a persistência
		// dos dados no final da transação/requisição
		final var topico = topicoRepository.getOne(id);
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.getMensagem());
		return topico;
	}

}
