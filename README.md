![GitHub Workflow Status](https://img.shields.io/github/workflow/status/thiagofarbo/kubernetes-springboot-mongodb/maven)
[![card](https://github-readme-stats.vercel.app/api?thiagofarbo=iuricode&theme=default)](https://github.com/iuricode/)

#Sobre esse projeto.

Este aplicação foi desenvolvida utilizando Spring Boot com Java 8. A aplicação não precisa de nenhum servidor para realizar o deploy.
Por quê? Esse aplicativo é implantado em um Embedded Tomcat.

#Base de dados.
Para acessar a base de dados, basta acessar a URL http://localhost:9595/h2/

#Imagem docker.
Para criar a imagem docker, basta acessar a raiz do projeto e executar o comamndo abaixo.

**docker build -f Dockerfile -t api-cartoes .**


#Rodar a aplicação no docker
Para rodar a imagem no docker, basta executar o comando a baixo.
**docker run -p 8085:8085 api-cartoes**

#URL's REST API

** @POST Para salvar um cartão, penas realize uma request para a url abaixo. http://localhost:9595/api/cartoes 

** @GET Para consultar um cartão, apenas realize uma request para a url a abaixo. passando o id do cartão: http://localhost:9595/api/cartoes/1

** @GET Para listar os cartões, apenas realize uma request para a url abaixo. http://localhost:9595/api/cartoes

** @PUT Para atualizar um cartão, apenas realize uma request para a url abaixo passando o body abaixo.
http://localhost:9595/api/cartoes 

{
	"nome": "Joh Conner",
    "valor": 300.00,
    "tipoCartao": "DEBITO",
    "dataRecarga": "16-10-2019",
    "dataExpiracao": "21-09-2020"
}

** @PATCH Para atualizar o status de um cartão, apenas realize uma request para a url http://localhost:9595/api/cartoes passando o body abaixo.

{
	"status": "PERDA"
}

** @DELETE Para deletar um cartão, apenas realize uma request para a url a seguir passando o id do cartão: http://localhost:9595/api/cartoes/1
