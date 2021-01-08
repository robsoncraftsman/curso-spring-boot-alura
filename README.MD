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

- Fazer o build:

```
mvn clean package
```

- Rodar via linha de comando:

```
java -Dspring.profiles.active=prod -Dserver.port=8888 -DFORUM_DATABASE_URL='jdbc:h2:mem:alura-forum' -DFORUM_DATABASE_USERNAME=sa -DFORUM_JWT_SECRET='A5nEP5uheU3TmfoOHqz7fmXeN0qFWlqmU+uMZO3yFHM=' -jar forum.jar
```

## Rodar aplicação no Docker

- Criar a imagem:

```
docker image build -t robsoncraftsman/curso-spring-boot-alura:1.0.0 .
```

- Rodar o container:

```
docker container run -d --name curso-spring-boot-alura -p 8080:8080 -e spring.profiles.active='prod' -e FORUM_DATABASE_URL='jdbc:h2:mem:alura-forum' -e FORUM_DATABASE_USERNAME='sa' -e FORUM_JWT_SECRET='A5nEP5uheU3TmfoOHqz7fmXeN0qFWlqmU+uMZO3yFHM=' robsoncraftsman/curso-spring-boot-alura:1.0.0
```

