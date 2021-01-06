# Projeto com os exercícios dos cursos de Spring Boot da plataforma Alura

## Curso: Spring Boot API REST: Construa uma API

Instrutor: Rodrigo Ferreira

Link para o curso: https://www.alura.com.br/curso-online-spring-boot-api-rest

## Curso: Spring Boot API Rest: Segurança da API, Cache e Monitoramento

Instrutor: Rodrigo Ferreira

Link para o curso: https://www.alura.com.br/curso-online-spring-boot-seguranca-cache-monitoramento

## Curso: Spring Boot e Teste: Profiles, Testes e Deploy

Link para o curso: https://www.alura.com.br/curso-online-spring-boot-profiles-testes-deploy

## Projetos utilizados de terceiros:

https://github.com/codecentric/spring-boot-admin

https://springfox.github.io/springfox/

## Para rodar o projeto no eclipse:

- Setar o profile em "Run Configurations...":

```
-Dspring.profiles.active=dev
```


## Build do projeto e rodar pelo JAR com profile 'prod' 

```
mvn clean package
```

```
java -Dspring.profiles.active=prod -DFORUM_PORT=8888 -DFORUM_DATABASE_URL='jdbc:h2:mem:alura-forum' -DFORUM_DATABASE_USERNAME=sa -DFORUM_JWT_SECRET='A5nEP5uheU3TmfoOHqz7fmXeN0qFWlqmU+uMZO3yFHM=' -jar forum.jar
```
