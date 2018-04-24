package lab7out;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {
	String[] users = {"jsmith@uca.edu","msmith@uca.edu","tjones@yahoo.com","jjones@yahoo.com"};
	String[] passwords = {"hello123 ","pass123 ","123456 ","hello1234 "};
	Database db;
	int rando;
	
	@Before
	public void setUp() throws Exception 
	{
	  db = new Database(); 
	  rando = (int) ((17*Math.random())%users.length); 
	}

	
	@Test
	public void testSetConnection() {
		db.setConnection("lab7out/db.properties");
		Connection conney = db.getConnection();
		assertNotNull("check setConnection", conney);
	}
	
	@Test
	public void testQuery() {
		db.setConnection("lab7out/db.properties");
		String query =  "select aes_decrypt(password, 'key') from users where username='" + users[rando] + "';";
		ArrayList<String> res = db.query(query);
		System.out.println("Random Integer:" + rando + "\nUser:" + users[rando] + "\nPass:" + passwords[rando] + "\nResults:" + res.get(0) + "\n");
		assertEquals("failure",passwords[rando], res.get(0));
	}
	
	@Test
	public void testExecuteDML() {
		db.setConnection("lab7out/db.properties");
		Connection conn = db.getConnection();
		String c = "insert into users values('jj@gmail.com', aes_encrypt('zvAFqr', 'key'));";
   		boolean res = db.executeDML(c);
   		assert(res);
	}

	
	
	
	
	
	
	

}

