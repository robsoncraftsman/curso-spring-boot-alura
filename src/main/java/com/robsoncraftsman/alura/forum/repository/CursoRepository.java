package com.robsoncraftsman.alura.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robsoncraftsman.alura.forum.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);

}
