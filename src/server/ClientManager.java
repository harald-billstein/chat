package server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
    
    Client client = new Client(socket);
    clientList.contains(client);
    client.setObserver(this);
    client.startIncommigMessageListener();
    client.setClientName("client:" + (clientList.size() + 1));
    clientList.add(client);
    System.out.println("Clients connected: " + clientList.size());
  }

  @Override
  public void messageDistribution(Message message) {
    System.out.println(new Date().toString() + " Message from: " + message.getUser());
    for (Client client : clientList) {
      client.sendMessage(message);
    }
  }

  @Override
  public void removeClientFromSwarm(Client client) {
    client.close();
    clientList.remove(client);
    System.out.println("User: " + client.getClientName() + " disconnected!");
    System.out.println("Clients connected: " + clientList.size());
  }

}


