FROM openjdk:17-jdk-slim

LABEL authors="Saurav"

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn

COPY pom.xml .

RUN ./mvnw dependency:go-offline -B

COPY src ./src

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/shopforhomes-0.0.1-SNAPSHOT.jar"]