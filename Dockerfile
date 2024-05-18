FROM maven:3-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:22-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/register-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "register-0.0.1-SNAPSHOT.jar"]
