FROM amazoncorretto:21-alpine

WORKDIR /app

COPY rest/target/rest-0.0.1-SNAPSHOT.jar /app/comments.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/comments.jar"]