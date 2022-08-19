FROM eclipse-temurin:17.0.4_8-jre-alpine
VOLUME /tmp
ADD ./target/servicio-productos.jar servicio-productos.jar
ENTRYPOINT ["java","-jar","/servicio-productos.jar"]