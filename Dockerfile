# Build stage
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/extensions-*.jar extensions.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "extensions.jar"]
CMD ["mvn", "spring-boot:run", "-Dspring-boot.run.profiles="]