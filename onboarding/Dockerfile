# ------------ Stage 1: Build the JAR using Maven ------------
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Set working directory inside the container
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Clean and build the project without tests
RUN mvn clean package -DskipTests

# ------------ Stage 2: Run the JAR on a lightweight JDK image ------------
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=builder /app/target/onboarding-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
