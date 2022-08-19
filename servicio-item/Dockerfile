FROM eclipse-temurin:17.0.4_8-jre-alpine
VOLUME /tmp
EXPOSE 8002
ADD ./target/servicio-item.jar servicio-item.jar
ENTRYPOINT ["java","-jar","/servicio-item.jar"]