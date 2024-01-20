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
    docker build -f Dockerfile -t api-cards .
```
# To run the Docker image, simply execute the command below.
 ```   
    docker run -p 8085:8085 api-cards
 ```

# URL's REST API

#### @POST To save a card, simply make a request to the following URL:

```
http://localhost:9595/api/cards
{
	"name": "Thiago",
	"amount": 200.00,
    "status": "INACTIVE",  
    "cardType": "CREDIT",
	"productType":"MEAL"
}
```

#### @GET To query a card, just make a request to the following URL, passing the card id

```
     http://localhost:9595/api/cartoes/1
```


#### @GET To list the cards, just make a request to the following 

```
http://localhost:9595/api/cards
```

#### @PUT To update a card, simply make a request to the following URL, passing the body below.
``` 
http://localhost:9595/api/cards

{
	"name": "Thiago",
	"amount": 200.00,
    "status": "INACTIVE",  
    "cardType": "CREDIT",
	"productType":"MEAL"
}

``` 

#### @PATCH To update the status of a card, simply make a request to the following URL.

``` 
http://localhost:9595/api/cards

{
        "status": "LOSS"
}

```

#### @DELETE To delete a card, just make a request to the following URL, passing the card ID.

``` 
 http://localhost:9595/api/cards/1
``` 