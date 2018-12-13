   FROM openjdk:11.0.1-jdk
   VOLUME /tmp
   ARG JAR_FILE=build/libs/springdemo-0.0.1-SNAPSHOT.jar
   COPY ${JAR_FILE} app.jar

   EXPOSE 8080

   ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
