package com.robsoncraftsman.alura.forum.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.robsoncraftsman.alura.forum.model.Curso;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CursoRepositoryTest {

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private TestEntityManager em;

	@Test
	void deveRetornarNomeDoCurso() {
		final var novoCurso = new Curso();
		novoCurso.setNome("Java");
		novoCurso.setCategoria("Programação");
		this.em.persist(novoCurso);

		final var nomeCurso = novoCurso.getNome();
		final var cursoRetornado = this.cursoRepository.findByNome(nomeCurso);
		assertNotNull(cursoRetornado);
		assertEquals(nomeCurso, cursoRetornado.getNome());
	}

	@Test
	void naoDeveRetornarCursoNaoCadastrado() {
		final var nomeCurso = "HTML 5";
		final var curso = this.cursoRepository.findByNome(nomeCurso);
		assertNull(curso);
	}

}
