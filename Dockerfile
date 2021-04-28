FROM openjdk:8

MAINTAINER Thiago Emidio

WORKDIR /app

COPY target/*.jar /app/api-cartoes.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "api-cartoes.jar"]
