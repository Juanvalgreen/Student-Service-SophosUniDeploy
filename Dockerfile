FROM openjdk:17-alpine

LABEL author="Juan Valverde"

WORKDIR /app

COPY target/SophosUniversity-0.0.1-SNAPSHOT.jar sophos-uni-student-ms.jar

EXPOSE 9005

ENTRYPOINT ["java", "-jar", "sophos-uni-student-ms.jar"]
