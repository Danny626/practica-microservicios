FROM eclipse-temurin:17.0.4_8-jre-alpine
VOLUME /tmp
EXPOSE 9100
ADD ./target/servicio-oauth.jar servicio-oauth.jar
ENTRYPOINT ["java","-jar","/servicio-oauth.jar"]