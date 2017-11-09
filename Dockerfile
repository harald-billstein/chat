FROM openjdk:8-jdk
WORKDIR /src/server/
RUN javac Main.java
CMD ["java", "Main"]
