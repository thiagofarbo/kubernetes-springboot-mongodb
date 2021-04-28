FROM openjdk:8

MAINTAINER Thiago Emidio

RUN mvn clean package

ARG JAR_FILE=target/

COPY ${JAR_FILE} api-cartoes.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "api-cartoes.jar"]
