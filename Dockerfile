FROM amazoncorretto:17

WORKDIR application

COPY /build/libs/Genius-1.0-GENIUS-SNAPSHOT.jar ./

ENTRYPOINT ["java", "-jar", "Genius-1.0-GENIUS-SNAPSHOT.jar"]