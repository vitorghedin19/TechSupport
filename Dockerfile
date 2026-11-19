FROM maven:3.9.8-eclipse-temurin-21-jammy AS builder

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

WORKDIR /app

COPY --from=builder /app/target/TechSupport-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar","TechSupport-0.0.1-SNAPSHOT.jar"]