FROM openjdk:8

MAINTAINER Thiago Emidio

WORKDIR /app

COPY /target/*.jar api-cartoes.jar

RUN mvn package

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "api-cartoes.jar"]
