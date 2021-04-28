FROM openjdk:8

MAINTAINER Thiago Emidio

ARG artifactory=/target/*.jar

COPY ${artifactory} api-cartoes.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "api-cartoes.jar"]
