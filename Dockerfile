FROM openjdk:8

MAINTAINER Thiago Emidio

WORKDIR /target/
COPY ${WORKDIR} api-cartoes.jar

RUN mvn clean package

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "api-cartoes.jar"]
