FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8088
COPY target/*.jar policies.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/policies.jar"]