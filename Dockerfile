FROM openjdk:11
EXPOSE 8080
ADD target/spring-boot-rnd.jar spring-boot-rnd.jar
ENTRYPOINT ["java","-jar","/spring-boot-rnd.jar"]