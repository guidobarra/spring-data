FROM maven:3.9.6-amazoncorretto-17 AS build

WORKDIR /workspace

COPY pom.xml pom.xml

COPY src/ /workspace/src/

RUN mvn clean package

FROM amazoncorretto:17-alpine-jdk AS prod

EXPOSE 9292/tcp

VOLUME /opt/logs

COPY --from=build /workspace/target/*.jar /opt/app.jar

COPY src/main/resources/* /opt/

WORKDIR /opt

CMD ["-Xmx1000m", "-Xss512m", "-XX:+HeapDumpOnOutOfMemoryError", "-XX:HeapDumpPath=/opt/logs"]

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=beta", "/opt/app.jar"]