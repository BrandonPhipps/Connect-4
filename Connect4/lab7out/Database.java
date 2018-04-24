package lab7out;


import java.util.*;
import java.io.*;
import java.sql.*;


public class Database {
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private ResultSetMetaData rmd;
	
	
	    public Database()
	    {
		    
//		    //Read properties file
//		    Properties prop = new Properties();
//		    FileInputStream fis = null;
//			try {
				String fis = new String("lab7out/db.properties");
				this.setConnection(fis);
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		    try {
//				prop.load(fis);
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		    
//		    String url = prop.getProperty("url");
//		    String user = prop.getProperty("user");
//		    String pass = prop.getProperty("password");
//		    
//		    try
//		    {
//		      //Perform the Connection
//		      con = DriverManager.getConnection(url,user,pass);
//		    } catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	    }
	    
	    public ArrayList<String> query(String query)
	    {
	    	try {
				stmt=con.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				rs = stmt.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				rmd = rs.getMetaData();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	ArrayList<String> data = new ArrayList<String>();
	    	try {
				while(rs.next()) {
					String res = new String();
					for(int i = 1; i < rmd.getColumnCount()+1; i++)
						res += rs.getString(i) + " ";
					data.add(res);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	if (data.size() > 0) {
	    		return data;
	    	}
	    	else 
	    		return null;
	    }
	    
	    public boolean executeDML(String dml)
	    {
	    	try {
				stmt=con.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
	    		stmt.execute(dml);
					return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
	    }
	    
	    public boolean verifyAccount(String arg0, String arg1) {
		    String q = "Select aes_decrypt(password,'key') from players where username='" + arg0 + "';";
		    ArrayList<String> al = query(q);
		    if(((String)al.get(0)).trim().equals(((String)arg1).trim())) {
		    	return true;
		    }
		    else	
		    	return false;	
		    }
	  
	  public boolean createNewAccount(String arg0, String arg1) {
		   	String s = "Select * from players where username='" + arg0 + "';";
		   	ArrayList<String> al = query(s);
		   	if(al == null) {
		   		String c = "insert into players values('" + arg0 + "', aes_encrypt('" + arg1 + "', 'key'), 0, 0, 0)";
		   		boolean res = executeDML(c);
		   		return true;
		   	}
		   	else	
		    	return false;
		    }
	  public ArrayList<String> getTopTen(){
		  String query = "select username, wins, losses, draws from players order by wins desc;";
		   ArrayList<String> toReturn = query(query);
		   return toReturn;
	  }
	  public void setConnection(String fn) {
			 //Read properties file
		    Properties prop = new Properties();
		    FileInputStream fis = null;
			try {
				fis = new FileInputStream(fn);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    try {
				prop.load(fis);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    
		    String url = prop.getProperty("url");
		    String user = prop.getProperty("user");
		    String pass = prop.getProperty("password");
		    
		    try
		    {
		      //Perform the Connection
		      con = DriverManager.getConnection(url,user,pass);
		    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	  public Connection getConnection() {
			return con;
		}

}
	
	
	
	
	
	
	
	
	
	
	
