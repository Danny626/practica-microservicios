FROM eclipse-temurin:17.0.4_8-jre-alpine
VOLUME /tmp
EXPOSE 8090
ADD ./target/gateway-server.jar gateway-server.jar
ENTRYPOINT ["java","-jar","/gateway-server.jar"]