FROM openjdk:8-jdk
WORKDIR /src/server/
RUN javac /src/server/Main.java
CMD ["java", "Main"]
