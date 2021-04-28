FROM openjdk:8

MAINTAINER Thiago Emidio

COPY /target/*.jar /tmp/target/*.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "api-cartoes.jar"]