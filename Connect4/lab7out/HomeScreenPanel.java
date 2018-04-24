
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

	public JTextArea getTop5Area()
	{
		return top5Area;
	}

	public void setTop5Area(JTextArea top5Area)
	{
		this.top5Area = top5Area;
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
	    JPanel ButtonBuffer = new JPanel(new BorderLayout());
	    ButtonBuffer.add(joinButton, BorderLayout.NORTH);
	    
	    // Create the create account button.
	    JButton logoutButton = new JButton("Logout");
	    logoutButton.addActionListener(hc);
	    ButtonBuffer.add(logoutButton, BorderLayout.SOUTH);
	    

	    // Arrange the components in a grid.
	    JPanel grid = new JPanel(new BorderLayout());
	    grid.add(ButtonBuffer, BorderLayout.NORTH);
	    grid.add(topTenPanel, BorderLayout.SOUTH);
	    this.add(grid);
	    //************************************
	    //************************************
	    

		/*JLabel name = new JLabel();
		name.setBounds(50, 50, 50, 50);
		name.setText(username);
		add(name);

		JLabel recordLabel = new JLabel("Your Personal Record");
		recordLabel.setBounds(302, 11, 125, 14);
		add(recordLabel);

		JLabel top5Label = new JLabel("Top 10 Players");
		top5Label.setBounds(320, 107, 120, 14);
		add(top5Label);

		JButton joinButton = new JButton("Join a Game");
		joinButton.setBounds(99, 77, 120, 23);
		joinButton.addActionListener(hc);
		add(joinButton);

		JButton logoutButton = new JButton("Logout");
		logoutButton.setBounds(115, 136, 89, 23);
		logoutButton.addActionListener(hc);
		add(logoutButton);

		recordArea = new JTextArea();
		recordArea.setBounds(293, 39, 123, 43);
		add(recordArea);

		JScrollPane scrollPaneRecord = new JScrollPane(recordArea);
		scrollPaneRecord.setBounds(293, 39, 123, 43);
		add(scrollPaneRecord);

		top5Area = new JTextArea();
		top5Area.setBounds(293, 132, 125, 134);
		add(top5Area);

		JScrollPane scrollPaneTop5 = new JScrollPane(top5Area);
		scrollPaneTop5.setBounds(293, 132, 125, 134);
		add(scrollPaneTop5);*/
	}


}
