FROM adoptopenjdk/openjdk11:jdk-11.0.3_7

RUN mkdir /opt/app

COPY target/ui-1.0-SNAPSHOT.jar /opt/app

EXPOSE 8080

CMD ["java", "-jar", "ui-1.0-SNAPSHOT.jar"]