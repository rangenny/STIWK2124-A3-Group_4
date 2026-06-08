# Stage 1: Build compilation footprint
FROM maven:3.9.6-eclipse-temurin-17 AS backend-builder
WORKDIR /app

# Copy dependency mappings first to cache layers efficiently
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy all source folders 
COPY src ./src

# Compile and package the production jar without hardcoding local database constraints
RUN mvn clean package -DskipTests

# Stage 2: Clean production execution environment
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy production compiled JAR from Stage 1
COPY --from=backend-builder /app/target/assignment1-group4-*.jar app.jar

# Expose target application port
EXPOSE 8080

# Run with environment variables matching the service definition dynamically
ENTRYPOINT ["java", "-jar", "app.jar"]