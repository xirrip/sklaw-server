#   FROM openjdk:11.0.1-jdk
   FROM openjdk:11-jre
   VOLUME /tmp

   COPY build/libs/*.war app.war

   EXPOSE 8080

   CMD ["java","-jar","/app.war"]
   #ENTRYPOINT ["java","-jar","/app.war"]

