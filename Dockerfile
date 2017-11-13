FROM openjdk:8-jdk
WORKDIR ./
ADD ./ ./
RUN javac -d build -cp src/ src/server/*.java
CMD java -cp build server.Main
EXPOSE 8081
