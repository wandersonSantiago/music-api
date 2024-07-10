# MusicApp

## Descrição

MusicApp é uma aplicação web para gerenciamento de músicas, álbuns e artistas. A aplicação permite criar, listar, atualizar e deletar artistas, álbuns e músicas, com persistência de dados utilizando bancos de dados relacionais.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Web
- Spring Validation
- MapStruct
- Hibernate Validator
- H2 Database (desenvolvimento)
- PostgreSQL (produção)
- Springdoc OpenAPI
- JUnit 5
- Mockito
- Maven

## Estrutura do Projeto

```plaintext
src
├── main
│   ├── java
│   │   └── br
│   │       └── com
│   │           └── musica
│   │               ├── api
│   │               │   ├── dto
│   │               │   │   ├── input
│   │               │   │   ├── output
│   │               │   │   └── mappers
│   │               │   └── resources
│   │               │       └── v1
│   │               ├── core
│   │               │   ├── config
│   │               │   └── validators
│   │               └── domain
│   │                   ├── models
│   │                   ├── repositories
│   │                   └── service
│   └── resources
│       ├── application.yml
│       ├── application-dev.yml
│       └── application-prod.yml
└── test
    └── java
        └── br
            └── com
                └── musica
                    └── service
```

## Configuração

### Configuração de Desenvolvimento

A aplicação usa o banco de dados em memória H2 para o ambiente de desenvolvimento. A configuração está em `src/main/resources/application-dev.yml`.

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password:
  h2:
    console:
      enabled: true
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
```

### Configuração de Produção

Para o ambiente de produção, a aplicação usa PostgreSQL. A configuração está em `src/main/resources/application-prod.yml`.

```yaml
spring:
  datasource:
    url: jdbc:postgresql://<HOST>:<PORT>/<DATABASE_NAME>
    driver-class-name: org.postgresql.Driver
    username: <USERNAME>
    password: <PASSWORD>
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
```

### Executando a Aplicação

#### Ambiente de Desenvolvimento

Para iniciar a aplicação em desenvolvimento, use o perfil `dev`. Isso pode ser feito configurando a variável de ambiente `SPRING_PROFILES_ACTIVE` ou diretamente no comando Maven.

```sh
export SPRING_PROFILES_ACTIVE=dev
mvn spring-boot:run
```

#### Ambiente de Produção

Para iniciar a aplicação em produção, use o perfil `prod`.

```sh
export SPRING_PROFILES_ACTIVE=prod
mvn spring-boot:run
```

## Testes

Os testes unitários estão localizados na pasta `src/test/java/br/com/musica/service`. Para executar os testes, use o seguinte comando Maven:

```sh
mvn test
```

## Documentação da API

A documentação da API está disponível via Swagger/OpenAPI. Após iniciar a aplicação, você pode acessar a documentação em:

```
http://localhost:8080/swagger-ui.html
```

## Estrutura de Pacotes

### Pacote `br.com.musica.api.dto.input`
Contém as classes DTO para entrada de dados na API.

### Pacote `br.com.musica.api.dto.output`
Contém as classes DTO para saída de dados da API.

### Pacote `br.com.musica.api.dto.mappers`
Contém as interfaces do MapStruct para mapeamento entre entidades e DTOs.

### Pacote `br.com.musica.api.resources.v1`
Contém os controladores REST da versão 1 da API.

### Pacote `br.com.musica.core.config`
Contém as classes de configuração do Spring.

### Pacote `br.com.musica.core.validators`
Contém as classes de validação personalizada.

### Pacote `br.com.musica.domain.models`
Contém as entidades JPA que representam o domínio.

### Pacote `br.com.musica.domain.repositories`
Contém as interfaces de repositório do Spring Data JPA.

### Pacote `br.com.musica.domain.service`
Contém as classes de serviço que implementam a lógica de negócios.

