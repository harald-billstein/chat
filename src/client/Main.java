package client;

import java.util.Scanner;

public class Main {
  static String user;
  static String message;

  public static void main(String[] args) {
    Client client = new Client();
    client.connect();
//    Scanner scanner = new Scanner(System.in);
//    System.out.print("User: ");
//    String user = scanner.nextLine();
//    System.out.print("Message: ");
//    String message = scanner.nextLine();
    client.sendMessage("Harald", "message1");

    Client client2 = new Client();
    client2.connect();
    client2.sendMessage("Lisette", "message2");
  }

}
