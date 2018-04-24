package lab7out;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.sql.*;
import java.util.ArrayList;


public class DatabaseTest {

	String[] users = {"jsmith@uca.edu","msmith@uca.edu","tjones@yahoo.com","jjones@yahoo.com"};
	String[] passwords = {"hello123","pass123","123456","hello1234"};
	
	private Database db; 
	private int rando;
	
	@Before 
	public void setUp() throws Exception 
	{
	  db = new Database();
	  //db.setConnection("lab7out/db.properties");
	  rando = ((int)Math.random()*users.length); 
	}

	
	
	
	@Test
	 public void testQuery() throws SQLException
	 {
		
		// db.getConnection();
		 String username = users[rando]; 
		 String expected = passwords[rando];
		 String query1 = "select aes_decrypt(password,'key') as decrypted_password from users where username = '"+ username +"';";
		
		 ArrayList<String> result = new ArrayList<String>();
		 
		 result = db.query(query1);
		
		 //System.out.println(result+ "333");
		 String pass = null;
		
		 pass = result.toString();
		 pass = pass.substring(1,pass.length()-2);
		  	   
	 
		//compare expected with actual using assertEquals
		assertEquals("Check Query ", expected, pass);
	 }
	 
	
	@Test
	 public void testExecuteDML() throws SQLException
	 {
		
		//db.getConnection();
		String dml;
		dml = "insert into users values('jess', aes_encrypt('password', 'key'))";
		
		boolean a =  db.executeDML(dml);
		 
		assertTrue("Check Execute Statement", a);
		 		
	 }


}
