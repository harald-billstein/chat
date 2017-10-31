import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {

  public static void main(String[] args) {
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    ObjectOutputStream outPutStream;
    ObjectInputStream inPutStream;

    int portNumber = 8081;
    System.out.println("Server");
    try {
      serverSocket = new ServerSocket(portNumber);
      clientSocket = serverSocket.accept();
      
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    
    
    //while (true) {

      try {
        
        outPutStream = new ObjectOutputStream(clientSocket.getOutputStream());
        inPutStream = new ObjectInputStream(clientSocket.getInputStream());


        


        HashMap<String, String> hashMap = (HashMap<String, String>)inPutStream.readObject();
        System.out.println(hashMap.get("Message"));
        hashMap.put("Message", "changed");
        
        outPutStream.writeObject(hashMap);

      }


      catch (Exception e) {
        System.out.println("Error server " + e.getMessage());

      }
    }

  }




