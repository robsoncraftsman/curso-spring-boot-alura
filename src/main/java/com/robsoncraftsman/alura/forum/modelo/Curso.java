package com.robsoncraftsman.alura.forum.modelo;

import java.util.Objects;

public class Curso {

	private Long id;
	private String nome;
	private String categoria;

	public Curso(final String nome, final String categoria) {
		this.nome = nome;
		this.categoria = categoria;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(final String categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		final var prime = 31;
		var result = 1;
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final var other = (Curso) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

}
