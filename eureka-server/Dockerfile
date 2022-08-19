FROM eclipse-temurin:17.0.4_8-jre-alpine
VOLUME /tmp
EXPOSE 8761
ADD ./target/eureka-server.jar eureka-server.jar
ENTRYPOINT ["java","-jar","/eureka-server.jar"]