package lab7out;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class LoginControl implements ActionListener
{
  // Private data fields for the container and chat client.
  private JPanel container;
  private ChatClient client;
  private JTextField usernameField;
  private JTextField passwordField;
  private JButton submitButton;
  private JButton cancelButton;
  
  
  
  public String getUsername()
  {
    return usernameField.getText();
  }
  
  // Getter for the text in the password field.
  public String getPassword()
  {
    return new String(passwordField.getText());
  }
  
  public void setUsername(String u) {
	  usernameField.setText(u);
  }
  
  public void setPassword(String p) {
	  passwordField.setText(p);
  }
  
  public void setUserTextField(JTextField usernameField) {
	  this.usernameField = usernameField;
  }
  
public void setPassTextField(JTextField passwordField) {
	  this.passwordField = passwordField;
  }
  
  public JTextField getUserTextField() {
	  return usernameField;
  }
  public JTextField getPassTextField() {
	  return passwordField;
  }
  
  public void setSubmitButton(JButton submitButton) {
	  
  }
  
  public void setCancelButton(JButton cancelButton) {
	  
  }
  
  public JButton getSubmitButton() {
	return submitButton;  
  }
  
 public JButton getCancelButton() {
	  return cancelButton;
  }
  
  
  // Constructor for the login controller.
  public LoginControl(JPanel container, ChatClient client)
  {
    this.container = container;
    this.client = client;
  }
  
  // Handle button clicks.
  public void actionPerformed(ActionEvent ae)
  {
    // Get the name of the button clicked.
    String command = ae.getActionCommand();

    // The Cancel button takes the user back to the initial panel.
    if (command == "Cancel")
    {
      CardLayout cardLayout = (CardLayout)container.getLayout();
      cardLayout.show(container, "1");
    }

    // The Submit button submits the login information to the server.
    else if (command == "Submit")
    {
      // Get the username and password the user entered.
      LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
      LoginData data = new LoginData(loginPanel.getUsername(), loginPanel.getPassword());
      
      // Check the validity of the information locally first.
      if (data.getUsername().equals("") || data.getPassword().equals(""))
      {
        displayError("You must enter a username and password.");
        return;
      }

      // Submit the login information to the server.
      try
      {
        client.sendToServer(data);
      }
      catch (IOException e)
      {
    	e.printStackTrace();
        displayError("Error connecting to the server.");
      }
    }
  }

  // After the login is successful, set the User object and display the contacts screen.
  public void loginSuccess()
  {
    LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
    CardLayout cardLayout = (CardLayout)container.getLayout();
    cardLayout.show(container, "4");
  }

  // Method that displays a message in the error label.
  public void displayError(String error)
  {
    LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
    loginPanel.setError(error);
  }
}
