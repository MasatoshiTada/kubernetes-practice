FROM adoptopenjdk/openjdk11:jdk-11.0.4_11-slim

RUN mkdir /opt/app

COPY target/ui-0.0.1.jar /opt/app

EXPOSE 8080

#ENV SPRING_PROFILES_ACTIVE=docker

CMD ["java", "-jar", "/opt/app/ui-0.0.1.jar"]