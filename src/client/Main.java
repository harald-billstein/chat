package client;

import java.util.Scanner;

public class Main {
  static String user;
  static String message;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("User name: ");
    user = scanner.nextLine();
    System.out.print("Message: ");
    message = scanner.nextLine();
    
    Client client = new Client();
    client.sendMessage(user, message);
    
    System.out.print("User name: ");
    user = scanner.nextLine();
    System.out.print("Message: ");
    message = scanner.nextLine();
    
    Client client2 = new Client();
    client2.sendMessage(user, message);
    
    scanner.close();
    
    

  }

}
