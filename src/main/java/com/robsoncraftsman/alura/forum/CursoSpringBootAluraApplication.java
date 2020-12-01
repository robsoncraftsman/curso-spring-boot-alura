package com.robsoncraftsman.alura.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class CursoSpringBootAluraApplication {

	public static void main(final String[] args) {
		SpringApplication.run(CursoSpringBootAluraApplication.class, args);
	}

}
