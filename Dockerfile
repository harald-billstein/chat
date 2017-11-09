FROM openjdk:7
RUN javac src/server/Main.java
CMD ["java", "Main"]
