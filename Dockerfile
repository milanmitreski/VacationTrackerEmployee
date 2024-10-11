FROM openjdk:21-oracle
COPY build/libs/vacationemployee-0.0.1-SNAPSHOT.jar vacationemployee.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "vacationemployee.jar"]