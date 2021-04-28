FROM openjdk:8

MAINTAINER Thiago Emidio

RUN mvn clean package

ARG JAR_FILE

COPY target/${JAR_FILE} api-cartoes.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "api-cartoes.jar"]
