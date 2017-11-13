FROM openjdk:8-jdk
WORKDIR ./
ADD ./ ./
RUN javac -cp src/ src/server/Main.java
CMD java -cp ./src Main
