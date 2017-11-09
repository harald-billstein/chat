package server;

import java.io.Serializable;

public class Message implements Serializable {

  private static final long serialVersionUID = -8301511212486984737L;

  private String user;
  private String Message;
  private boolean recived;


  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getMessage() {
    return Message;
  }

  public void setMessage(String message) {
    Message = message;
  }

  public boolean isRecived() {
    return recived;
  }

  public void setRecived(boolean recived) {
    this.recived = recived;
  }


}
