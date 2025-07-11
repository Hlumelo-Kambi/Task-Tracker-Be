FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# 1. Copy Maven Wrapper files
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# 2. Make mvnw executable (THIS FIXES THE ERROR)
RUN chmod +x mvnw

# 3. Download dependencies (now with proper permissions)
RUN ./mvnw dependency:go-offline -B

# 4. Copy source code and build
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]