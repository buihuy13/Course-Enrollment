# Base Stage
FROM eclipse-temurin:21-jdk-jammy AS base
WORKDIR /app
COPY gradlew ./
COPY gradle ./gradle
RUN chmod +x ./gradlew
RUN ./gradlew --version

##Install mysql client
RUN apt-get update && apt-get install -y default-mysql-client
    
# Build Stage
FROM base AS build
WORKDIR /app
COPY build.gradle settings.gradle.kts ./
COPY src ./src
RUN ./gradlew clean build --no-daemon -x test
    
# Publish Stage
FROM build AS publish
WORKDIR /app
RUN ./gradlew bootJar --no-daemon
    
# Final Stage
FROM eclipse-temurin:21-jre-jammy AS final
WORKDIR /app
COPY --from=publish /app/build/libs/*.jar /app/application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/application.jar"]