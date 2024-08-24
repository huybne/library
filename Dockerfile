
FROM maven:3.8.4-openjdk-17 AS build
COPY library-management-system .
RUN mvn clean package -DskipTests


EXPOSE 8082
COPY target/library-management-system-0.0.1-SNAPSHOT.jar application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]

