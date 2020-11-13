package com.robsoncraftsman.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robsoncraftsman.alura.forum.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

	List<Topico> findByCursoNome(String nomeCurso);

}
