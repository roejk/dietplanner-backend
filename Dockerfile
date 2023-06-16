FROM openjdk:17-jdk-alpine
COPY target/dietplanner-0.0.1-SNAPSHOT.jar dietplanner-backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/dietplanner-backend.jar"]