import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class Client {

  // 10.27.75.198

  public static void main(String[] args) {
    String message = "testar";
    System.out.println("Client");
    ObjectOutputStream outPutStream;
    ObjectInputStream inPutStream;

    try {

      Socket clientSocket = new Socket("localhost", 8081);

      inPutStream = new ObjectInputStream(clientSocket.getInputStream());
      outPutStream = new ObjectOutputStream(clientSocket.getOutputStream());
  
      
      HashMap<String, String> hashMap = new HashMap<>();
      hashMap.put("Message", "testar");
      System.out.println("Sending....");
      outPutStream.writeObject(hashMap);
      
      try {
        HashMap<String, String> hashMapIn = (HashMap<String, String>) inPutStream.readObject();
        System.out.println(hashMapIn.get("Message"));
      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      

    } catch (IOException e) {
      System.out.println(" error " + e.getMessage());
      e.printStackTrace();
    }
  }

}
