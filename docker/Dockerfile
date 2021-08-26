FROM maven:3.8.1-jdk-11 as build

WORKDIR /workspace

COPY pom.xml pom.xml

COPY src/ /workspace/src/

RUN mvn clean package

FROM amazoncorretto:11-alpine as prod

EXPOSE 9292/tcp

VOLUME /opt/logs

COPY --from=build /workspace/target/spring-data-1.0-SNAPSHOT.jar /opt/app.jar

COPY src/main/resources/* /opt/

WORKDIR /opt

CMD ["-Xmx1000m", "-Xss512m", "-XX:+HeapDumpOnOutOfMemoryError", "-XX:HeapDumpPath=/opt/logs"]

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=beta", "/opt/app.jar"]