package com.robsoncraftsman.alura.forum.controller.form;

import com.robsoncraftsman.alura.forum.modelo.Topico;
import com.robsoncraftsman.alura.forum.repository.CursoRepository;

public class TopicoForm {

	private String titulo;
	private String mensagem;
	private String nomeCurso;

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

	public String getNomeCurso() {
		return this.nomeCurso;
	}

	public void setNomeCurso(final String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Topico converter(final CursoRepository cursoRepository) {
		final var curso = cursoRepository.findByNome(this.getNomeCurso());
		return new Topico(this.getTitulo(), this.getMensagem(), curso);
	}

}
