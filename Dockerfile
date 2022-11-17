FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /app
COPY . /app
RUN mvn package -DskipTests

FROM openjdk:17
COPY --from=builder /app/target/*.jar /app.jar
EXPOSE 8082/tcp
ENTRYPOINT ["java", "-XX:InitialRAMPercentage=10.0" , "-XX:MaxRAMPercentage=80.0", "-jar", "-Dspring.profiles.active=deployment", "/app.jar"]
