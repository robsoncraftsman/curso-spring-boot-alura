package com.robsoncraftsman.alura.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.robsoncraftsman.alura.forum.modelo.Resposta;

public class RespostaDto {

	private final Long id;
	private final String mensagem;
	private final LocalDateTime dataCriacao;
	private final String nomeAutor;

	public RespostaDto(final Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.dataCriacao = resposta.getDataCriacao();
		this.nomeAutor = resposta.getAutor().getNome();
	}

	public static List<RespostaDto> convert(final List<Resposta> respostas) {
		return respostas.stream().map(RespostaDto::new).collect(Collectors.toList());
	}

	public Long getId() {
		return this.id;
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

}
