FROM openjdk:8
MAINTAINER Carlos Prado

WORKDIR /app

EXPOSE 8088

ADD target/movies-0.0.1.jar .

ENTRYPOINT ["java","-jar", "movies-0.0.1.jar"]