FROM maven:3.9.3-eclipse-temurin-20 AS build

WORKDIR /app

COPY Raje-Backend/ .

RUN mvn clean package -DskipTests
RUN mv target/*.jar target/app.jar

FROM eclipse-temurin:20-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/app.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
