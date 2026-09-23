FROM eclipse-temurin:25-jdk AS build
LABEL authors="AmanKumarJangid"

WORKDIR /app

COPY pom.xml .
COPY src ./src

# Compile the application and skip tests for faster deployment
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# Runtime stage using a clean Temurin JDK 25 image
FROM eclipse-temurin:25-jdk
WORKDIR /app

# Copy the compiled JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Spring Boot 4 web service port
EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]