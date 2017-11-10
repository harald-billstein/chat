FROM openjdk:8-jdk
WORKDIR ./
COPY src/ /src/
RUN ls -la /src/*
RUN javac -cp src/server/ src/server/Main.java
CMD java -cp ./src/server/ Main
