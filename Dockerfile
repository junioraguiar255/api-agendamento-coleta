# Use uma imagem base do OpenJDK 17
FROM openjdk:17-jre-slim
VOLUME /tmp
COPY target/api-agendamento-coleta-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
