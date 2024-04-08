FROM adoptopenjdk/openjdk17:alpine
WORKDIR /app
EXPOSE 8080
COPY target/MS_Microservice_Gestion_Comment-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
