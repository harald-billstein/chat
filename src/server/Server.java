package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  private ServerSocket serverSocket;
  private Socket clientSocket;
  private ClientManager socketManager;
  private int port;


  public Server(int port) {
    this.port = port;
    Init();
  }

  private void Init() {
    socketManager = new ClientManager();
  }

  public void startListen() {
    boolean listen = false;
    
    try {
      serverSocket = new ServerSocket(port);
      listen = true;
    } catch (IOException e) {
      System.out.println("Error 101, in Server: " + e.getMessage());
      e.printStackTrace();
    }

    System.out.println("Server Started!");
    //threadCounter();
    while (listen) {
      try {
        clientSocket = serverSocket.accept();
        System.out.println("Client Connected!");

        Thread thread = new Thread() {
          public void run() {
            socketManager.processClient(clientSocket);
          }
        };

        thread.start();

      } catch (IOException e) {
        System.out.println("Error 102, in Server: " + e.getMessage());
        e.printStackTrace();
      }
    }

  }

  private void threadCounter() {
    Thread threadCounter = new Thread() {
      public void run() {

        while (true) {
          System.out.println("Active threads: " + Thread.activeCount());
          try {
            Thread.sleep(20000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }

      }
    };
    threadCounter.start();
  }

}


