package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
  private clientManagerInterface clientManagerInterface;
  private Socket socket;
  private ObjectOutputStream outPutStream;
  private ObjectInputStream inPutStream;
  private Message message;
  private String clientName;

  public void sendMessage(Message message) {
     message.setRecived(true);
     try {
      outPutStream.writeObject(message);
      System.out.println("message sent back!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void startIncommigMessageListener() {
    System.out.println("Client startIncommigMessageListener");
    
    Thread thread = new Thread() {
      public void run() {
        System.out.println("Client listening");
        try {
          message = (Message) inPutStream.readObject();
          clientName = message.getUser();
          System.out.println(message.getUser() + ": " + message.getMessage());
          clientManagerInterface.messageDistribution(message);
        } catch (IOException | ClassNotFoundException e) {
          e.printStackTrace();
        }
      }
    };
    thread.start();
  }
  
  public String getClientName() {
    return clientName;
  }

  public Client(Socket socket) {
    System.out.println("creating client...");
    this.socket = socket;
    init();
  }

  private void init() {
    System.out.println("Client init objects");
    try {
      inPutStream = new ObjectInputStream(socket.getInputStream());
      outPutStream = new ObjectOutputStream(socket.getOutputStream());
      System.out.println("Server streams created!");
      

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  // try {
  //
  // outPutStream = new ObjectOutputStream(socket.getOutputStream());
  // inPutStream = new ObjectInputStream(socket.getInputStream());
  // Message message = (Message) inPutStream.readObject();
  //
  //
  // System.out.println(message.getUser() + ": " + message.getMessage());
  // message.setRecived(true);
  // outPutStream.writeObject(message);
  //
  // } catch (Exception e) {
  // System.out.println("Error server " + e.getMessage());
  // }
  // }

  public void setObserver(clientManagerInterface clientManagerInterface) {
    System.out.println("Client setObserver");
    System.out.println("Observer " + clientManagerInterface.toString());
    this.clientManagerInterface = clientManagerInterface;
  }
}
