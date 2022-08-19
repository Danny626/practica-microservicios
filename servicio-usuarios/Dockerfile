FROM eclipse-temurin:17.0.4_8-jre-alpine
VOLUME /tmp
ADD ./target/servicio-usuarios.jar servicio-usuarios.jar
ENTRYPOINT ["java","-jar","/servicio-usuarios.jar"]