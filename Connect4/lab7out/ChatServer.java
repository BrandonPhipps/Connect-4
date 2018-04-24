package lab7out;

import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import java.util.*;
import java.util.HashMap;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class ChatServer extends AbstractServer
{
	// Data fields for this chat server.
	private JTextArea log;
	private JLabel status;
	private boolean running = false;
	private Database database;
	private HashMap<String, ConnectionToClient> Clients;
	private ArrayList<String> userids = new ArrayList<String>();
	private HashMap<String, String> IdsAndUsername = new HashMap<String, String>();
	private HashMap<String, String> playersInAMatch = new HashMap<String, String>();
	// Constructor for initializing the server with default settings.
	public ChatServer()
	{
		super(12345);
		this.setTimeout(500);
		Clients = new HashMap<String, ConnectionToClient>();
	}
	public ConnectionToClient getClients(String clientID) {

		return Clients.get(clientID);
	}
	public String getPlayersInAMatch(String opponentID)
	{
		return playersInAMatch.get(opponentID);
	}


	public String getUserids(int index) {
		return userids.get(index);
	}


	public String getIdsAndUsername(String clientID) {
		return IdsAndUsername.get(clientID);
	}

	void setDatabase(Database database) {
		this.database = database;
	}

	// Getter that returns whether the server is currently running.
	public boolean isRunning()
	{
		return running;
	}

	// Setters for the data fields corresponding to the GUI elements.
	public void setLog(JTextArea log)
	{
		this.log = log;
	}
	public void setStatus(JLabel status)
	{
		this.status = status;
	}

	// When the server starts, update the GUI.
	public void serverStarted()
	{
		running = true;
		status.setText("Listening");
		status.setForeground(Color.GREEN);
		log.append("Server started\n");
	}

	// When the server stops listening, update the GUI.
	public void serverStopped()
	{
		status.setText("Stopped");
		status.setForeground(Color.RED);
		log.append("Server stopped accepting new clients - press Listen to start accepting new clients\n");
	}

	// When the server closes completely, update the GUI.
	public void serverClosed()
	{
		running = false;
		status.setText("Close");
		status.setForeground(Color.RED);
		log.append("Server and all current clients are closed - press Listen to restart\n");
		Clients.clear();
		userids.clear();
	}

	// When a client connects or disconnects, display a message in the log.
	public void clientConnected(ConnectionToClient client)
	{
		log.append("Client " + client.getId() + " connected\n");
		Clients.put((""+client.getId()), client); 
		
	}

	// When a message is received from a client, handle it.
	public void handleMessageFromClient(Object arg0, ConnectionToClient arg1)
	{
		// If we received LoginData, verify the account information.
		if (arg0 instanceof LoginData)
		{
			// Check the username and password with the database.
			LoginData data = (LoginData)arg0;
			Object result;
			if (database.verifyAccount(data.getUsername(), data.getPassword()))
			{
				result = "LoginSuccessful";
				log.append("Client " + arg1.getId() + " successfully logged in as " + data.getUsername() + "\n");
				IdsAndUsername.put(""+arg1.getId(), "" +data.getUsername());
			}
			else
			{
				result = new Error("The username and password are incorrect.", "Login");
				log.append("Client " + arg1.getId() + " failed to log in\n");
			}

			// Send the result to the client.
			try
			{
				arg1.sendToClient(result);
			}
			catch (IOException e)
			{
				return;
			}
		}

		// If we received CreateAccountData, create a new account.
		else if (arg0 instanceof CreateAccountData)
		{
			// Try to create the account.
			CreateAccountData data = (CreateAccountData)arg0;
			Object result;
			if (database.createNewAccount(data.getUsername(), data.getPassword()))
			{
				result = "CreateAccountSuccessful";
				log.append("Client " + arg1.getId() + " created a new account called " + data.getUsername() + "\n");
			}
			else
			{
				result = new Error("The username is already in use.", "CreateAccount");
				log.append("Client " + arg1.getId() + " failed to create a new account\n");
			}

			// Send the result to the client.
			try
			{
				arg1.sendToClient(result);
			}
			catch (IOException e)
			{
				return;
			}
		}
		// If we received CreateAccountData, create a new account.
		else if (arg0 instanceof GameData)
		{	
			System.out.println("Server has got Game data");
			GameData data = (GameData)arg0;
			Object result;
			String userID = ""+arg1.getId();
			int indexOfUser = userids.indexOf(userID);
			String opponent;
			//result = "Switch Turn";
			
			if (indexOfUser%2 != 0)
			{
				opponent = userids.get(indexOfUser-1);				
			}
			else
			{
				opponent = userids.get(indexOfUser+1);	
			}
			
			
			
			try
			{
				/*ConnectionToClient ctc = Clients.get(opponent);
				System.out.println(
				ctc.getName() + "---"+
				ctc.getState());*/
				Clients.get(opponent).sendToClient(data);
				//System.out.println("True or False, User is still connected <" + user.isConnected() + ">\n");
			}
			catch (IOException e)
			{
				e.getStackTrace();
				return;
			}
			
			
		}
		// If we received String
		else if (arg0 instanceof String)
		{
			String message = (String)arg0;
			int getIndexPlayers;
			if (message.equals("Start Game"))
			{
				userids.add("" +arg1.getId());
				Object results;
				if (userids.size()%2 == 0)
				{
					results = "Start Game Player 1";
					getIndexPlayers = userids.indexOf("" +arg1.getId());
					playersInAMatch.put(userids.get(getIndexPlayers-1),userids.get(getIndexPlayers));
					try
					{
						Clients.get(userids.get(getIndexPlayers-1)).sendToClient(results);
					}
					catch (IOException e)
					{
						return;
					}
					results = "Player 2 Waiting";					
				}
				else
				{
					results = "Player 1 Waiting";					
					
				}
				try
				{
					arg1.sendToClient(results);
				}
				catch (IOException e)
				{
					return;
				}
				
			}
			else if(message.equals("PlayerWin"))
			{
				
				
				String userID = ""+arg1.getId();
				int indexOfUser = userids.indexOf(userID);
				String opponent;
				
				
				
				if (indexOfUser%2 != 0)
				{
					opponent = userids.get(indexOfUser-1);				
				}
				else
				{
					opponent = userids.get(indexOfUser+1);	
				}
				
				try
				{

					Clients.get(opponent).sendToClient("GameLose");
					userids.clear();
					playersInAMatch.clear();
		
				}
				catch (IOException e)
				{
					e.getStackTrace();
					return;
				}
				
				String winnerUpdate = "update players set wins = wins+1 where username = " + "'" + getIdsAndUsername(userID) + "'";
				String loserUpdate = "update players set losses  = losses+1 where username ="+ "'" + getIdsAndUsername(opponent) + "'";
				database.executeDML(winnerUpdate);
				database.executeDML(loserUpdate);
				
						
				
				
				
			}
			else if(message.equals("Draw"))
			{
				String userID = ""+arg1.getId();
				int indexOfUser = userids.indexOf(userID);
				String opponent;
				
				if (indexOfUser%2 != 0)
				{
					opponent = userids.get(indexOfUser-1);				
				}
				else
				{
					opponent = userids.get(indexOfUser+1);	
				}
				try
				{

					Clients.get(opponent).sendToClient("Draw");
					userids.clear();
					playersInAMatch.clear();
		
				}
				catch (IOException e)
				{
					e.getStackTrace();
					return;
				}
				String updateDrawPlayer = "update players set draws = draws+1 where username = " + "'" + getIdsAndUsername(userID) + "'";
				String updateDrawOpponent = "update players set draws = draws+1 where username = " + "'" + getIdsAndUsername(opponent) + "'";
				
				database.executeDML(updateDrawPlayer);
				database.executeDML(updateDrawOpponent);
				
			}
					
		}
	}

	// Method that handles listening exceptions by displaying exception information.
	public void listeningException(Throwable exception) 
	{
		running = false;
		status.setText("Exception occurred while listening");
		status.setForeground(Color.RED);
		log.append("Listening exception: " + exception.getMessage() + "\n");
		log.append("Press Listen to restart server\n");
	}
}
