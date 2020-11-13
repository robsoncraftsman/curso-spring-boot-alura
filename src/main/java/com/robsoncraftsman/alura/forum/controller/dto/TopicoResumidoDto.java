package com.robsoncraftsman.alura.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

	public static List<TopicoResumidoDto> convert(final List<Topico> topicos) {
		return topicos.stream().map(TopicoResumidoDto::new).collect(Collectors.toList());
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
