# Graphqldemo
- [Objetivo](#objective)
- [Status do projeto](#status)
- [Instalação](#setup)
___
## <a name="objective"></a> Objetivo

Este é um serviço que simula uma blog e utiliza Java 21 e Spring Boot 3.2. 
Foi criado para a finalidade de estudo e utiliza tecnologias como graphql, Docker, 
Postgres e o novo RestClient do spring.

## <a name="status"></a> Status do projeto

O projeto se encontra finalizado no momento porém pode ser alterado a qualquer momento 
para implementar/testar novas funcionalidades.

## <a name="setup"></a> Instalação

Para rodar o projeto é necessário ter o Java 21 instalado em conjunto com uma IDE de preferência 
e ter o docker rodando.

Ao rodar o projeto o spring detecta a presença do arquivo `compose.yaml` e roda a imagem mais recente 
do postgres. Este arquivo também contém os dados de conexão necessários caso queira acessar essa base
através de um SGDB.

A API conta com os seguintes recursos:  
Query: `findAllPosts`, `findAllPosts`  
Mutation: `createPost`, `deletePost`

Para fazer interagir com o projeto basta acessar o `localhost:8080/graphiql` e testar as queries,
como ilustrado abaixo.

``` graphql
query {
  findAllPosts {
    id
    title
    datePublished
    summary
    comments {
      id
      name
    }
  }
}
```

