package com.robsoncraftsman.alura.forum.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.robsoncraftsman.alura.forum.model.Topico;

public class TopicoResumidoDto {

	private final Long id;
	private final String titulo;
	private final String mensagem;
	private final LocalDateTime dataCriacao;

	public TopicoResumidoDto(final Topico topico) {
		super();
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}

	public static Page<TopicoResumidoDto> convert(final Page<Topico> topicos) {
		return topicos.map(TopicoResumidoDto::new);
	}

	public Long getId() {
		return this.id;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public String getMensagem() {
		return this.mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return this.dataCriacao;
	}

}
