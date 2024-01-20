![GitHub Workflow Status](https://img.shields.io/github/workflow/status/thiagofarbo/kubernetes-springboot-mongodb/maven)

# About this project
This application was developed using Spring Boot with Java 8. The application does not require any server for deployment.
Why? Because this application is deployed on an Embedded Tomcat.


## Prerequisites
* Docker installed and configured on your machine
* Maven
  * Java 11

# Database.
We are using a NSQL database MongoDB running on port 27017

# Docker image.
To create the Docker image, simply navigate to the project root and execute the command below

```
    docker build -f Dockerfile -t api-cartoes .
```
# To run the Docker image, simply execute the command below.
 ```   
    docker run -p 8085:8085 api-cartoes
 ```

# URL's REST API

#### @POST To save a card, simply make a request to the following URL: http://localhost:9595/api/cartoes

```
{
	"nome": "Joh Conner",
    "valor": 300.00,
    "tipoCartao": "DEBITO",
    "dataRecarga": "16-10-2019",
    "dataExpiracao": "21-09-2020"
}
```

#### @GET To query a card, just make a request to the following URL, passing the card id

```
     http://localhost:9595/api/cartoes/1
```


#### @GET To list the cards, just make a request to the following 

```
http://localhost:9595/api/cartoes
```

#### @PUT To update a card, simply make a request to the following URL, passing the body below.
``` 
http://localhost:9595/api/cartoes

{
	"nome": "Joh Conner",
    "valor": 300.00,
    "tipoCartao": "DEBITO",
    "dataRecarga": "16-10-2019",
    "dataExpiracao": "21-09-2020"
}

``` 

#### @PATCH To update the status of a card, simply make a request to the following URL.

``` 
http://localhost:9595/api/cartoes

{
        "status": "LOSS"
}

```

#### @DELETE To delete a card, just make a request to the following URL, passing the card ID.

``` 
 http://localhost:9595/api/cartoes/1
``` 