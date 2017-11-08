package server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientManager implements clientManagerInterface {

  private List<Client> clientList;
  
  public ClientManager() {
    init();
  }

  private void init() {
    clientList = Collections.synchronizedList(new ArrayList<Client>());
  }

  public void processClient(Socket socket) {
    System.out.println("processClient");
    
    Client client = new Client(socket);
    client.setObserver(this);
    client.startIncommigMessageListener();
    clientList.add(client);  
  }

  @Override
  public void messageDistribution(Message message) {
    
    for (Client client: clientList) {
      System.out.println(client.getClientName() + " gets message from " + message.getUser() + " message: " + message.getMessage() );
      client.sendMessage(message);
    }

    
  }
}


