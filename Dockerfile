FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} user-ms-api-1.0.0-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "user-ms-api-1.0.0-SNAPSHOT.jar"]