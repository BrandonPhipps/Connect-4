package lab7out;

import static org.junit.Assert.*;

import java.awt.CardLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.*;
public class InitialControlTest {

private ClientGUI cg;
private InitialControl hsc;
private JPanel container;
private InitialPanel pan;
private CreateAccountPanel cap;
private LoginPanel lp;
private CreateAccountControl cac;
private LoginControl lgc;
//CardLayout cardLayout = new CardLayout();

@Before
public void setUp() throws Exception
{
cg = new ClientGUI(null);
container = new JPanel(new CardLayout());
ChatClient client = new ChatClient();
hsc = new InitialControl(container,client);
pan = new InitialPanel(hsc);
cac = new CreateAccountControl(container, client);
lgc = new LoginControl(container, client);
cap = new CreateAccountPanel(cac);
lp = new LoginPanel(lgc);
container.add(pan, "1");
container.add(lp, "2");
container.add(cap, "3");
}

@Test
public void testActionPerformed()
{

JButton button;
Random rand = new Random();
int rnum = rand.nextInt(2);
String com;
System.out.print(rnum);


//button = pan.getButtonAt(rnum);

//com = button.getActionCommand();

//button.doClick(1000);


//	button = cap.getButtonAt(rnum);
//	button.doClick(1000);

//	try {
//	TimeUnit.SECONDS.sleep(2);
//	} catch (InterruptedException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//	} 
//	


}

}
 


