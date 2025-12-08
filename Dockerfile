# -------------------------
# STAGE 1 — Build Jar
# -------------------------
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy Maven files first (for caching)
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn -q -e -DskipTests=true clean package

# -------------------------
# STAGE 2 — Run Jar
# -------------------------
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy built jar
COPY --from=build /app/target/*.jar app.jar

# Expose your Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
