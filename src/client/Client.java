package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import server.Message;

public class Client {
  Socket clientSocket;

  public void sendMessage(String user, String message) {
    System.out.println("Starting client...");
    ObjectOutputStream outPutStream;
    ObjectInputStream inPutStream;

    Message messageToSend = new Message();
    messageToSend.setUser(user);
    messageToSend.setMessage(message);
    messageToSend.setRecived(false);

    try {

      clientSocket = new Socket("localhost", 8081);

      inPutStream = new ObjectInputStream(clientSocket.getInputStream());
      outPutStream = new ObjectOutputStream(clientSocket.getOutputStream());



      System.out.println("Sending....");
      outPutStream.writeObject(messageToSend);

      try {
        Message messageFromServer = (Message) inPutStream.readObject();
        System.out.println("Message recived: " + messageFromServer.isRecived());
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }


    } catch (IOException e) {
      System.out.println(" error " + e.getMessage());
      e.printStackTrace();
    }
  }
  
  public void close() throws IOException {
    clientSocket.close();
  }
}
