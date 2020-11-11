package com.robsoncraftsman.alura.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robsoncraftsman.alura.forum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

}
