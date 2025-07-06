FROM eclipse-temurin:21-jdk
VOLUME /tmp
COPY target/actuator.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
