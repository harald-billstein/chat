 WORKDIR ./
 COPY src/ /src/
 RUN ls -la /src/*
-RUN javac -cp src/server/ src/server/Main.java
-CMD java -cp ./src/server/ Main
+RUN mkdir classes
+RUN javac -d classes/ src/server/*.java
+RUN jar cfm server.jar src/server/manifest.txt -C classes server
+RUN java -jar server.jar
