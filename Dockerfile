FROM openjdk:8-jdk
WORKDIR ./
COPY src/ /src/
RUN ls -la /src/server/*
RUN javac -cp ./ src/server/Main.java
CMD java -cp ./src/server/ Main
