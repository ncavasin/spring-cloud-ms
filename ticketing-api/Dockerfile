# Pull base image.
FROM openjdk:20-ea-jdk

ADD ./target/ticketing-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD java -jar app.jar