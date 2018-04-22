package lab7out;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class ContactsControl {

	 // Private data fields for the container and chat client.
	  private JPanel container;
	  private ChatClient client;
	  
	// Constructor for the create account controller.
	  public ContactsControl(JPanel container, ChatClient client)
	  {
	    this.container = container;
	    this.client = client;
	  }
	
	
	  public void actionPerformed(ActionEvent ae)
	  {
	    // Get the name of the button clicked.
	    String command = ae.getActionCommand();
	    // The Delete Contact button takes the user back to the initial panel.
	    if (command == "Delete Contact")
	    {
	      CardLayout cardLayout = (CardLayout)container.getLayout();
	      cardLayout.show(container, "1");
	    }
	    // The Add Contact button takes the user back to the initial panel.
	    else if (command == "Add Contact")
	    {
	      CardLayout cardLayout = (CardLayout)container.getLayout();
	      cardLayout.show(container, "1");
	    }
	    // The Submit button creates a new account.
	    else if (command == "Logout")
	    {
	      
//	      try
//	      {
//	    	client.sendToServer(data);
//	      }
//	      catch (IOException e)
//	      {
//	        e.printStackTrace();
//	    	displayError("Error connecting to the server.");
//	      }
	    }
	  }
	
	  
	  public void createAccountSuccess()
	  {
	    ContactsPanel contactsPanel = (ContactsPanel)container.getComponent(2);
	    ClientGUI clientGUI = (ClientGUI)SwingUtilities.getWindowAncestor(contactsPanel);
	    CardLayout cardLayout = (CardLayout)container.getLayout();
	    cardLayout.show(container, "2");
	  }
	  
	  
	
	// Method that displays a message in the error label.
	  public void displayError(String error)
	  {
	    CreateAccountPanel createAccountPanel = (CreateAccountPanel)container.getComponent(2);
	    createAccountPanel.setError(error);
	  }
	
	
	
}
