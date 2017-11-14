package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import server.Message;

public class Client {
  private Socket clientSocket;
  private ObjectOutputStream outPutStream;
  private ObjectInputStream inPutStream;
  private Message message;
  private String userName;
  private String ip;
  private int port;

  public void connect(String ip, int port) {
    this.ip = ip;
    this.port = port;

    boolean connectionSuccess = false;

    while (!connectionSuccess) {
      try {
        System.out.println("Client connecting...");
        clientSocket = new Socket(ip, port);
        outPutStream = new ObjectOutputStream(clientSocket.getOutputStream());
        inPutStream = new ObjectInputStream(clientSocket.getInputStream());
        listenToIncommingMessages();
        connectionSuccess = true;

      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
    System.out.println("Client connected!");
  }

  public void listenToIncommingMessages() {

    Thread thread = new Thread() {
      boolean listen = true;

      public void run() {


        while (listen) {
          try {
            message = (Message) inPutStream.readObject();

            if (!message.isRecived()) {
              System.out.println("Failed to send message!");
            }

            if (!message.getUser().equals(userName)) {
              System.out.println(message.getUser() + ": " + message.getMessage());
            }

          } catch (ClassNotFoundException | IOException e1) {
            listen = false;
            System.out.println("lost connection to server!");
          }
        }
      }
    };
    thread.start();
  }

  public void sendMessage(String user, String message) {

    Message messageToSend = new Message();
    messageToSend.setUser(user);
    messageToSend.setMessage(message);
    messageToSend.setRecived(false);

    try {
      outPutStream.writeObject(messageToSend);

    } catch (IOException e) {
      System.out.println("Connection to server lost!");
      connect(ip, port);
    }
  }

  public void setUserName(String userName) {
    this.userName = userName;

  }

  public void close() {
    try {
      clientSocket.close();
      outPutStream.close();
      inPutStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}
