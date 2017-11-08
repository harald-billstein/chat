package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import server.Message;

public class Client {
  Socket clientSocket;
  ObjectOutputStream outPutStream;
  ObjectInputStream inPutStream;

  public void connect() {
    System.out.println("Client connecting...");
    try {
      clientSocket = new Socket("localhost", 8081);
      outPutStream = new ObjectOutputStream(clientSocket.getOutputStream());
      inPutStream = new ObjectInputStream(clientSocket.getInputStream());
      System.out.println("Client streams created!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void sendMessage(String user, String message) {
    System.out.println("Client sending....");

    Message messageToSend = new Message();
    messageToSend.setUser(user);
    messageToSend.setMessage(message);
    messageToSend.setRecived(false);

    try {
      outPutStream.writeObject(messageToSend);
      System.out.println("Message sent");
      Message messageFromServer = (Message) inPutStream.readObject();
      System.out.println("Server recived message? " + messageFromServer.isRecived());

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void close() throws IOException {
    clientSocket.close();
  }
}
