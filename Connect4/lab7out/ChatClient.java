package lab7out;

import ocsf.client.AbstractClient;
import java.util.*;

public class ChatClient extends AbstractClient
{
  // Private data fields for storing the GUI controllers.
  private LoginControl loginControl;
  private CreateAccountControl createAccountControl;
  private GameControl gameControl;
  private HomeScreenControl homeScreenControl;

  // Setters for the GUI controllers.
  public void setLoginControl(LoginControl loginControl)
  {
    this.loginControl = loginControl;
  }
  public void setCreateAccountControl(CreateAccountControl createAccountControl)
  {
    this.createAccountControl = createAccountControl;
  }
  public void setGameControl(GameControl gameControl) {
	  this.gameControl = gameControl;
  }

  // Constructor for initializing the client with default settings.
  public ChatClient()
  {
    super("localhost", 8300);
  }
  
  // Method that handles messages from the server.
  public void handleMessageFromServer(Object arg0)
  {
    // If we received a String, figure out what this event is.
    if (arg0 instanceof String)
    {
      // Get the text of the message.
      String message = (String)arg0;
      loginControl.loginSuccess();
      // If we successfully logged in, tell the login controller.
      if (message.equals("LoginSuccessful"))
      {
        this.loginControl.loginSuccess();
      }
      
      // If we successfully created an account, tell the create account controller.
      else if (message.equals("CreateAccountSuccessful"))
      {
        this.createAccountControl.createAccountSuccess();
      }
      else if (message.equals("Logout")) {
    	  
      }
    }
    else if(arg0 instanceof ArrayList) {
    	this.homeScreenControl.setTopTen((ArrayList<String>)arg0);
    }
    
    // If we received an Error, figure out where to display it.
    else if (arg0 instanceof Error)
    {
      // Get the Error object.
      Error error = (Error)arg0;
      
      // Display login errors using the login controller.
      if (error.getType().equals("Login"))
      {
        loginControl.displayError(error.getMessage());
      }
      
      // Display account creation errors using the create account controller.
      else if (error.getType().equals("CreateAccount"))
      {
        createAccountControl.displayError(error.getMessage());
      }
    }
  }  
}