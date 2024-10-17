FROM openjdk:17-jdk-slim
ARG JAR_FILE = straget/casadelacultura-0.0.1.JAR_FILE
COPY ${JAR_FILE} app_casadelacultura.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar", "app_casadelacultura.jar"]
