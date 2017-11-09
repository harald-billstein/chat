package client;

import java.util.Scanner;

public class Main {
  static String user;
  static String message;

  public static void main(String[] args) {
    boolean runChat = true;
    Scanner scanner = new Scanner(System.in);
    System.out.print("User: ");
    user = scanner.nextLine();
    
    Client client = new Client();
    client.setUserName(user);
    client.connect();

    System.out.print("==>");
    while (runChat) {
      message = scanner.nextLine();
      if (message.equals("$$EXIT")) {
        runChat = false;
      } else {
        client.sendMessage(user, message);        
      }
    }
    scanner.close();
    System.out.println("Chat closed!");
    System.exit(0); 
  }
}
