FROM openjdk:8-alpine

ARG JAVA_PARAM="-Xms256M -Xmx1024M -XX:+UseG1GC"
ENV JAVA_PARAM=$JAVA_PARAM

WORKDIR /opt/app

COPY src/main/resources/ /opt/app

COPY /target/*.jar /opt/app/app.jar

EXPOSE 8091

CMD ["sh", "-c", "java ${JAVA_PARAM} -Dserver.port=8091 -Dfile.encoding=UTF-8 -jar /opt/app/app.jar"]