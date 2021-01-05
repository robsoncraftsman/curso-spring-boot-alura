package com.robsoncraftsman.alura.forum.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.robsoncraftsman.alura.forum.model.Usuario;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ParameterType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.robsoncraftsman.alura.forum"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.pathMapping("/")
				.ignoredParameterTypes(Usuario.class)
				.globalRequestParameters(
					Arrays.asList(
						new RequestParameterBuilder()
						.name("Authorization")
						.description("Token JWT")
						.in(ParameterType.HEADER)
						.required(false)
						.build()));
	}

}
