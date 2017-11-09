package server;

public interface clientManagerInterface {
  
  void messageDistribution(Message message);

  void removeClientFromSwarm(Client client);


}
