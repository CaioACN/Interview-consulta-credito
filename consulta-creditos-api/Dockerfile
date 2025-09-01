# ===== BUILD =====
FROM gradle:8.8-jdk17-alpine AS build
WORKDIR /app

# cache de dependências
COPY gradle gradle
COPY gradlew .
COPY build.gradle settings.gradle ./
RUN ./gradlew --no-daemon dependencies

# código-fonte e build
COPY src ./src
RUN ./gradlew --no-daemon clean vaadinBuildFrontend bootJar -Pvaadin.productionMode

# ===== RUN =====
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# copie o JAR gerado; se falhar, ajuste para o nome exato do jar
COPY --from=build /app/build/libs/*.jar /app/app.jar

EXPOSE 8081
ENV JAVA_OPTS=""
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
