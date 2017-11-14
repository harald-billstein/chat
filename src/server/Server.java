package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  private ServerSocket serverSocket;
  private Socket clientSocket;
  private ClientManager clientManager;
  private int port;


  public Server(int port) {
    this.port = port;
    Init();
  }

  private void Init() {
    clientManager = new ClientManager();
  }

  public void startListen() {
    boolean listen = false;

    try {
      serverSocket = new ServerSocket(port);
      listen = true;
    } catch (IOException e) {
      System.out.println("Error 101, in Server: " + e.getMessage());
      shutDownServer();
    }

    System.out.println("Server Started!");
    threadCounter();
    while (listen) {
      try {
        clientSocket = serverSocket.accept();
        System.out.println("Client Connected!");

        Thread thread = new Thread() {
          public void run() {
            clientManager.processClient(clientSocket);
          }
        };

        thread.start();

      } catch (IOException e) {
        System.out.println("Error 102, in Server: " + e.getMessage());
        closeClientSocket(clientSocket);
        e.printStackTrace();
      }
    }

  }

  public void closeClientSocket(Socket socket) {
    try {
      socket.close();
    } catch (IOException e) {
      System.out.println("Error 103, in Server: " + e.getMessage());
    }
  }

  public void shutDownServer() {
    System.exit(0);
  }

  private void threadCounter() {
    Thread threadCounter = new Thread() {
      public void run() {

        while (true) {
          System.out.println("Active threads: " + Thread.activeCount());
          try {
            Thread.sleep(10000);
          } catch (InterruptedException e) {
            System.out.println("Thread counter");
            e.printStackTrace();
          }
        }

      }
    };
    threadCounter.start();
  }

}


