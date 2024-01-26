FROM openjdk:17
WORKDIR /app
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/api-0.0.1-SNAPSHOT.jar"]
