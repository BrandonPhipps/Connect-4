
package lab7out;


import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class HomeScreenPanel extends JPanel{

	private JTextArea recordArea;
	private JTextArea top5Area;
	private String username;
	private JTextArea topTenArea;
	public JTextArea getRecordArea()
	{
		return recordArea;
	}

	public void setRecordArea(JTextArea recordArea)
	{
		this.recordArea = recordArea;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}

	public HomeScreenPanel(HomeScreenControl hc) {

		//setLayout(null);
		topTenArea = new JTextArea(10,50);
		hc.setTextArea(topTenArea);
		JLabel topTenLabel = new JLabel("Top Ten", JLabel.CENTER);
		JPanel topTenPanel = new JPanel(new BorderLayout());
		JPanel topTenLabelBuffer = new JPanel();
		topTenLabelBuffer.add(topTenLabel);
		topTenPanel.add(topTenLabelBuffer, BorderLayout.NORTH);
		topTenArea.setEditable(false);
		JScrollPane topTenScrollPane = new JScrollPane(topTenArea);
		ArrayList<String> topTen = new ArrayList<String>();
		topTen.addAll(hc.getTopTen());
		for(int i = 0; i < topTen.size()-1; i++)
		{
			topTenArea.setText(topTenArea.getText() + topTen.get(i));
		}
		topTenPanel.add(topTenScrollPane, BorderLayout.SOUTH);
	    
		
	    // Create the login button.
		JButton joinButton = new JButton("Join a Game");
		joinButton.addActionListener(hc);
		hc.setJoinGame(joinButton);
	    JPanel ButtonBuffer = new JPanel(new BorderLayout());
	    ButtonBuffer.add(joinButton, BorderLayout.NORTH);
	    
	    // Create the create account button.
	    JButton logoutButton = new JButton("Logout");
	    logoutButton.addActionListener(hc);
	    hc.setLogOut(logoutButton);
	    ButtonBuffer.add(logoutButton, BorderLayout.SOUTH);
	    

	    // Arrange the components in a grid.
	    JPanel grid = new JPanel(new BorderLayout());
	    grid.add(ButtonBuffer, BorderLayout.NORTH);
	    grid.add(topTenPanel, BorderLayout.SOUTH);
	    this.add(grid);
	    //************************************
	    //************************************
	
	}


}
