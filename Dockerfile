FROM maven:3.9.3-eclipse-temurin-24 AS build

WORKDIR /app

# Copiamos el código fuente
COPY Raje-Backend/ .

# Compilamos el proyecto sin tests
RUN mvn clean package -DskipTests

# Renombramos el jar generado a app.jar para simplificar
RUN mv target/*.jar target/app.jar

# Segunda etapa: imagen ligera para correr la app
FROM eclipse-temurin:24-jdk-alpine

WORKDIR /app

# Copiamos solo el jar renombrado desde la etapa build
COPY --from=build /app/target/app.jar app.jar

# Puerto en el que corre la app (ajusta si usas otro)
EXPOSE 8080

# Ejecutamos el jar renombrado
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
