FROM openjdk:8-jdk
WORKDIR /src/server/
RUN javac -cp ./src/server Main.java
