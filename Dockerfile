# Stage 1: Build the application
FROM maven:3.9-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Package the application (skip tests to speed up build)
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
# Copy the JAR from the build stage
COPY --from=build /app/target/middleware-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
