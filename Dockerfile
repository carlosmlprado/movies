FROM openjdk:8
MAINTAINER Carlos Prado

WORKDIR /app

EXPOSE 8088
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} movies-0.0.1.jar
ARG PATH_FILE=/target/movies-0.0.1.jar
ADD ${PATH_FILE} movies-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar", "movies-0.0.1.jar"]