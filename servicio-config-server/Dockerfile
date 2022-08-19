FROM eclipse-temurin:17.0.4_8-jre-alpine
VOLUME /tmp
EXPOSE 8888
ADD ./target/config-server.jar config-server.jar
ENTRYPOINT ["java","-jar","/config-server.jar"]