FROM openjdk:8

MAINTAINER Thiago Emidio

RUN mvn clean package

ADD /target  /target/lib/api-cartoes.jar

ARG JAR_FILE
ADD target/${JAR_FILE} /target/lib/api-cartoes.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "api-cartoes.jar"]
