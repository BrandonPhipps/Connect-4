package lab7out;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class GameControl implements ActionListener{

  private JPanel container;
  private ChatClient user;
  
	public GameControl(JPanel container, ChatClient user) 
	{
		this.container = container;
		this.user = user;
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
    // Get the name of the button clicked.
    String command = ae.getActionCommand();
    
    // The Login button takes the user to the login panel.
    if (command.equals("Replay"))
    {

    }
    else if(command.equals("Exit"))
    		{
    	HomeScreenPanel homeScreenPanel = (HomeScreenPanel)container.getComponent(3);
      CardLayout cardLayout = (CardLayout)container.getLayout();
      cardLayout.show(container, "4");
    		}
  }
}

