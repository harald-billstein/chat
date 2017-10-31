package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SocketManager {

  private ObjectOutputStream outPutStream;
  private ObjectInputStream inPutStream;
  private List clientSocketList;
  
  public SocketManager() {
    init();
  }


  private void init() {
    clientSocketList = Collections.synchronizedList(new ArrayList<Socket>());
  }


  public void test(Socket clientSocket) {
    try {

      outPutStream = new ObjectOutputStream(clientSocket.getOutputStream());
      inPutStream = new ObjectInputStream(clientSocket.getInputStream());

      Message message = (Message) inPutStream.readObject();
      System.out.println(message.getUser() + ": " + message.getMessage());
      message.setRecived(true);


      outPutStream.writeObject(message);

    }


    catch (Exception e) {
      System.out.println("Error server " + e.getMessage());

    }
  }
}


