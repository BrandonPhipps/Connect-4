package lab7out;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel{
	
	
    public GamePanel(GameControl gc) {

        JFrame frame = new JFrame("connect four");
        int xsize = 7;
        int ysize= 6;
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setLayout(new GridLayout(xsize, ysize + 1));

        JLabel[][] slots = new JLabel[xsize][ysize];
        JButton[] buttons = new JButton[xsize];

        for (int i = 0; i < xsize; i++) {
            buttons[i] = new JButton("" + (i));
            buttons[i].setActionCommand("" + i);
            buttons[i].addActionListener(gc);

            panel.add(buttons[i]);
        }
        for (int column = 0; column < ysize; column++) {
            for (int row = 0; row < xsize; row++) {
                slots[row][column] = new JLabel();
                slots[row][column].setHorizontalAlignment(SwingConstants.CENTER);
                slots[row][column].setBorder(new LineBorder(Color.black));
                slots[row][column].setBackground(Color.white);
				slots[row][column].setOpaque(true);
                panel.add(slots[row][column]);
            }
        }
        JLabel info=new JLabel("");
        gc.setSlot(slots);
        gc.setLabel(info);
        JPanel buttonPanel = new JPanel();
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(gc);
        JButton replayButton = new JButton("Replay");
        replayButton.addActionListener(gc);    
        buttonPanel.add(exitButton);
        buttonPanel.add(replayButton);
        //JPanel viewPanel = new JPanel();
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.add(info);
        this.add(panel);
        this.add(buttonPanel);
//        panel.add(buttonPanel);
        //jframe stuff
//        frame.setContentPane(viewPanel);
//        //frame.add(panel);
//        frame.setSize(700, 600);
//        frame.setVisible(true);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}