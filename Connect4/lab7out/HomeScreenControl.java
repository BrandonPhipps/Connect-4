package lab7out;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

public class HomeScreenControl implements ActionListener
{
	private JPanel container;
	private ChatClient user;
	private ArrayList<String> topTen = new ArrayList<String>();
	private HomeScreenData homeScreenData;

	// Constructor for the HomeScreen controller.
	public HomeScreenControl(JPanel container, ChatClient user)
	{
		this.container = container;
		this.user = user;
		try
		{
			user.sendToServer(homeScreenData);
		}
		catch (IOException e)
		{
			displayError("Error connecting to server.");
		}
		
		
	}
	public ArrayList<String> getTopTen()
	{
		return topTen;
	}
	
	public void setTopTen(ArrayList<String> topTen)
	{
		for(String list: topTen) {
				this.topTen.add(list); 
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		// Get the name of the button clicked.
		String command = ae.getActionCommand();

		// The Login button takes the user to the login panel.
		if (command.equals("Join a Game"))
		{
			//GamePanel gamePanel = (GamePanel)container.getComponent(4);
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "5");
		}
		else if(command.equals("Logout"))
		{
			//InitialPanel InitialPanel = (InitialPanel)container.getComponent(0);
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "1");
		}
	}
	public void displayError(String error)
	{
		LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
		loginPanel.setError(error);
	}
}
