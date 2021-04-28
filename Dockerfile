FROM openjdk:8

MAINTAINER Thiago Emidio

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ARG artifactory=/target/*.jar

COPY ${artifactory} api-cartoes.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "api-cartoes.jar"]
