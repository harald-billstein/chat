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
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void startIncommigMessageListener() {

    Thread thread = new Thread() {
      public void run() {
        try {
          
          while(true) {
            message = (Message) inPutStream.readObject();
            clientName = message.getUser();
            clientManagerInterface.messageDistribution(message);            
          }
        } catch (IOException | ClassNotFoundException e) {
          removeClientFromSwarm();
        }
      }
    };
    thread.start();
  }

  private void removeClientFromSwarm() {
    clientManagerInterface.removeClientFromSwarm(this);
  }

  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public Client(Socket socket) {
    this.socket = socket;
    init();
  }

  private void init() {
    try {
      outPutStream = new ObjectOutputStream(socket.getOutputStream());
      inPutStream = new ObjectInputStream(socket.getInputStream());

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void setObserver(clientManagerInterface clientManagerInterface) {
    this.clientManagerInterface = clientManagerInterface;
  }
}
