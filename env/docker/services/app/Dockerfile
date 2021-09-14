
FROM openjdk:8
ADD calcul.wsdl calcul.wsdl
ADD demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8091
CMD ["java","-jar","app.jar"]