package lab7out;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class HomeScreenControl implements ActionListener
{
	private JPanel container;
	private ChatClient user;
	private HomeScreenData homeScreenData;
	private JTextArea textArea;
	private JButton joinGame;
	private JButton logOut;

	// Constructor for the HomeScreen controller.
	public HomeScreenControl(JPanel container, ChatClient user)
	{
		this.container = container;
		this.user = user;
		ArrayList<String> blank = new ArrayList<String>();
		blank.add("[");
		homeScreenData = new HomeScreenData(" ", 0, 0, 0, blank);
	}
	public ArrayList<String> getTopTen() {
		return homeScreenData.getTopTen();
	}
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	public void setJoinGame(JButton joinGame) {
		this.joinGame = joinGame;
	}
	public void setLogOut(JButton logOut) {
		this.logOut = logOut;
	}
	public JButton getJoinGame() {
		return joinGame;
	}
	public JButton getLogOut() {
		return logOut;
	}
	
	public void setTopTen(ArrayList<String> topTen)
	{
		
		homeScreenData.setTopTen(topTen);
		String temp = new String("Username\tWins\tLosses\tDraws\n----------------------------------------------------------------------------\n");
		for(String list: topTen) {
			temp += list.replaceAll(" ", "\t") + "\n";
		}
		textArea.setText(temp);
	}
	public HomeScreenData getHomeScreenData() {
		return homeScreenData;
	}
	public void setHomeScreenData(HomeScreenData hsd) {
		homeScreenData.setUsername(hsd.getUsername());
		homeScreenData.setWin(hsd.getWin());
		homeScreenData.setLose(hsd.getLose());
		homeScreenData.setDraw(hsd.getDraw());
		homeScreenData.setTopTen(hsd.getTopTen());
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
			Object startGame;
			startGame = "Start Game";
			
			try
			{
				user.sendToServer(startGame);
				//System.out.println("True or False, User is still connected <" + user.isConnected() + ">\n");
			}
			catch (IOException e)
			{
				e.getStackTrace();
				displayError("Error connecting to server.");
			}
		}
		else if(command.equals("Logout"))
		{
			//InitialPanel InitialPanel = (InitialPanel)container.getComponent(0);
			String result = "Logout";
			try
			{
				user.sendToServer(result);
				CardLayout cardLayout = (CardLayout)container.getLayout();
				cardLayout.show(container, "1");
			}
			catch (IOException e)
			{
				e.printStackTrace();
				displayError("Error connecting to server.");
			}
			
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
