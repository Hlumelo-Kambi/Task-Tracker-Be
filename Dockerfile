# Build stage (Java 21 + Maven Wrapper)
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# 1. Copy only the necessary files for Maven Wrapper first
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# 2. Download dependencies (cache them in Docker layer)
RUN ./mvnw dependency:go-offline -B

# 3. Copy source code and build
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Runtime stage (Lightweight Java 21 JRE)
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]