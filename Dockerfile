FROM openjdk:8-jdk
WORKDIR /src/server/
RUN javac -d ./ -sourcepath src src/server/Main.java
