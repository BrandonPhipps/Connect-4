package lab7out;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.JPanel;

public class GameControl implements ActionListener{

	private JPanel container;
	private ChatClient user;
	private JLabel[][] slots; //[columns][rows]
	private int index[];
	private JLabel info;
	private Color color;
	private boolean turn;
	
	public GameControl(JPanel container, ChatClient user, Color blackOrRed) 
	{
		this.container = container;
		this.user = user;
		index = new int[7];
		for(int i = 0; i<index.length;i++)
		{
			index[i] = 5;
		}
		color = blackOrRed;
		turn = false; ////////////////////////////////////////////////
		/////////////////////////////This is the initial status. It can be change
		//True: Player's turn
		//False: other player's turn
		//////////////////////////////////////////////////////////
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
		else if(command.equals("0")||command.equals("1")||command.equals("2")||command.equals("3")||command.equals("4")
				||command.equals("5")||command.equals("6"))
		{
			if(turn)
				{
				Color temp =	slots[0][0].getBackground();
			int num = Integer.parseInt(command);
			if(index[num] >= 0)
			{
				slots[num][index[num]].setBackground(color);
				slots[num][index[num]].setOpaque(true);
				index[num]--;
				if(winCheck())
				{
					info.setText("You Are Win");
				}
				else
				{
					///////////Look Here;
					//switchTurn(); //This Function is to switch the Turn
					////////////////////////////////////////////
					////////////////////////////////////////////
					GameData data = new GameData("",1,"",num);
					try {
						user.sendToServer(data);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					info.setText("Other's turn");
				}
			}
			else
				info.setText("This Column is Full. Choose Another one.");
				}
		}
	}
	
	public boolean winCheck()
	{
		int HEIGHT = 6;
	    int WIDTH = 7;
	    Color EMPTY_SLOT = Color.white;
	    //Color player = color;

		for (int r = 0; r < WIDTH; r++) { // iterate rows, bottom to top
	        for (int c = 0; c < HEIGHT; c++) { // iterate columns, left to right
	           Color player = slots[r][c].getBackground();
	            if (player == EMPTY_SLOT)
	                continue; // don't check empty slots

	            if (c + 3 < HEIGHT &&
	                player == slots[r][c+1].getBackground() && // look right
	                player == slots[r][c+2].getBackground() &&
	                player == slots[r][c+3].getBackground())
	            	return true;
	          
	            if (r + 3 < WIDTH) {
	                if (player == slots[r+1][c].getBackground() && // look up
	                    player == slots[r+2][c].getBackground() &&
	                    player == slots[r+3][c].getBackground())
	                    return true;
	                if (c + 3 < HEIGHT &&
	                    player == slots[r+1][c+1].getBackground() && // look up & right
	                    player == slots[r+2][c+2].getBackground() &&
	                    player == slots[r+3][c+3].getBackground())
	                    return true;
	                if (c - 3 >= 0 &&
	                    player == slots[r+1][c-1].getBackground() && // look up & left
	                    player == slots[r+2][c-2].getBackground() &&
	                    player == slots[r+3][c-3].getBackground())
	                    return true;
	            }
	        }
	    }
		return false;
	}
	
	public void setLabel(JLabel info)
	{
		this.info = info;
	}
	
	public void setChecker(int column)
	{
		slots[column][index[column]].setBackground(color);
		index[column]++;
	}
	public void setSlot(JLabel[][] slots)
	{
		this.slots = slots;
	}
	
	public void switchTurn()
	{
		if(turn)
			turn =false;
		else
			turn =true;
	}
	
	public void disableUsersColumnsSubmit()
	{
		//
	}
}

