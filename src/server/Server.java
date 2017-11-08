package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.naming.InitialContext;
import javax.swing.text.StyledEditorKit.BoldAction;

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
    System.out.println("Server started listening...");
    boolean listen = false;
    try {
      serverSocket = new ServerSocket(port);
      listen = true;
    } catch (IOException e) {
      System.out.println("Error 101, in Server: " + e.getMessage());
      e.printStackTrace();
    }

    while (listen) {
      try {
        System.out.println("Server listening!");
        clientSocket = serverSocket.accept();
        System.out.println("Client Connected!");

        Thread thread = new Thread() {
          public void run() {
            System.out.println("new thread created");
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

}


