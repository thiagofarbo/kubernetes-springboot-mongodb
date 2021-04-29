FROM openjdk:8

MAINTAINER Thiago Emidio

VOLUME /tmp

ADD target/*.jar api-cartoes.jar

ENTRYPOINT ["/bin/bash", "-c", "java", "-jar","/api-cartoes.jar"]

EXPOSE 8090
