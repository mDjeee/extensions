# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:23-jdk-slim
WORKDIR /app
COPY --from=build /app/target/extensions-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["mvn", "spring-boot:run", "-Dspring-boot.run.profiles=local"]