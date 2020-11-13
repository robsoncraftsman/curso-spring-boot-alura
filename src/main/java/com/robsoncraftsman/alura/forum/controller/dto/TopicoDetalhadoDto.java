package com.robsoncraftsman.alura.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.robsoncraftsman.alura.forum.model.StatusTopico;
import com.robsoncraftsman.alura.forum.model.Topico;

public class TopicoDetalhadoDto {

	private final Long id;
	private final String titulo;
	private final String mensagem;
	private final LocalDateTime dataCriacao;
	private final String nomeAutor;
	private final StatusTopico status;
	private final List<RespostaDto> respostas;

	public TopicoDetalhadoDto(final Topico topico) {
		super();
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.nomeAutor = topico.getAutor().getNome();
		this.status = topico.getStatus();
		this.respostas = RespostaDto.convert(topico.getRespostas());
	}

	public static List<TopicoDetalhadoDto> convert(final List<Topico> topicos) {
		return topicos.stream().map(TopicoDetalhadoDto::new).collect(Collectors.toList());
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

	public String getNomeAutor() {
		return this.nomeAutor;
	}

	public StatusTopico getStatus() {
		return this.status;
	}

	public List<RespostaDto> getRespostas() {
		return this.respostas;
	}

}
