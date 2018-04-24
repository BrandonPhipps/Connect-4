package lab7out;

import java.awt.*;
import javax.swing.*;

public class InitialPanel extends JPanel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton [] button = new JButton[2];
	public JButton getButtonAt(int i)
	 {
		 return button[i];
	 }
	
  // Constructor for the initial panel.
  public InitialPanel(InitialControl ic)
  { 
	
	
    // Create the controller.
    //InitialControl controller = new InitialControl(container);
    
    // Create the information label.
    JLabel label = new JLabel("Account Information", JLabel.CENTER);
    
    // Create the login button.
   button[0] = new JButton("Login");
    button[0].addActionListener(ic);
    JPanel loginButtonBuffer = new JPanel();
    loginButtonBuffer.add(button[0]);
    
    // Create the create account button.
   button[1] = new JButton("Create");
    button[1].addActionListener(ic);
    JPanel createButtonBuffer = new JPanel();
    createButtonBuffer.add(button[1]);

    // Arrange the components in a grid.
    JPanel grid = new JPanel(new GridLayout(3, 1, 5, 5));
    grid.add(label);
    grid.add(loginButtonBuffer);
    grid.add(createButtonBuffer);
    this.add(grid);
  }
  
  
}
