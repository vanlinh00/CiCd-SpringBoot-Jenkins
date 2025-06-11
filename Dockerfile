FROM openjdk:17-jdk-slim
COPY target/SpringBootTestCiCd-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]


#docker build -t springboot-cicd .

#docker run -d --name spring-app -p 8081:8081 springboot-cicd
#docker ps -a
#docker rm spring-app