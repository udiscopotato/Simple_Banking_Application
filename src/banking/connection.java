package banking;

import java.sql.Connection;
import java.sql.DriverManager;


// This is the Main connection class that will be the single point of contact with our Database and our 
// program will talk to the database through this class
public class connection {
	static Connection conn; // this will be our Global connection object 
	
	// This getConnection() method will establish the link with Database and return the Connection object
	static Connection getConnection() {
		try {
			String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/BANK";
			String user = "root";
			String pass = "bapun123";
			
			
			Class.forName(JDBC_Driver);
			conn = DriverManager.getConnection(url, user, pass);
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return conn;
	}
	
}
