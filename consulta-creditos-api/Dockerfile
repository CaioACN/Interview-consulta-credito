# ===== BUILD =====
FROM gradle:8.8-jdk17-alpine AS build
WORKDIR /app

# cache de dependências
COPY gradle gradle
COPY gradlew .
COPY build.gradle settings.gradle ./
RUN chmod +x gradlew
RUN ./gradlew --no-daemon dependencies

# código-fonte e build
COPY src ./src
RUN ./gradlew --no-daemon clean bootJar

# ===== RUN =====
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# copie o JAR gerado
COPY --from=build /app/build/libs/*.jar /app/app.jar

EXPOSE 8080
# força subir na 8080 (consistente com docker-compose e front)
ENV JAVA_OPTS="-Dserver.port=8080"
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
