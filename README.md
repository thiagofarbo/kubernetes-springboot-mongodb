![Endpoint Badge](https://img.shields.io/endpoint?url=https%3A%2F%2Flocalhost%3A9595%2Fapi%2Fcards)

# About this project
This application aims to show how to build an application using some technologies such as Java, Springboot Docker, Kubernetes, SQS , Grafana and MongoDB.


## Prerequisites
* Docker installed and configured on your machine
* Maven
* Java 1.8

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
