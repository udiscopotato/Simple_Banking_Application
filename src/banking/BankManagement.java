package banking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class BankManagement {
	private static final int NULL =0;
	static Connection conn = connection.getConnection();
    static String sql = "";
    
    
    
    public static boolean createAccount(String name,int amount , int pass) {
    	try {
    		if(name == "" || pass == NULL) {
    			System.out.println("All Fields are required");
    			return false;
    		}
    		amount=0;
    		Statement stmt = conn.createStatement();
    		sql = "insert into customer (cust_name,balance,pass_code) values ("+name+","+0+","+pass+")";
    		
    		if(stmt.executeUpdate(sql) == 1) {
    			System.out.println("Thank You \n"+name+"\nYour Account Created Successfully \nYou can login now");
    			return true;
    		}
    	}catch(SQLIntegrityConstraintViolationException e) {
    		System.out.println("This Username is already taken, try another one");
    	}catch(Exception e) {
    		System.out.println(e);
    	}
    	return false;
    }
    
    
    
    public static boolean login(String name,int pass) {
    	try {
    		if (name == "" || pass == NULL) {
                System.out.println("All Field Required!");
                return false;
            }
    		sql = "select * from customer where cust_name='"+ name + "' and pass_code=" + pass;
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ResultSet rs = ps.executeQuery();
    		
    		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
    		
    		if (rs.next()) {
    			int accNo = rs.getInt("acc_no");
    			System.out.print("Enter Choice:");
                int ch = Integer.parseInt(sc.readLine());
                if (ch == 2) {
                	 
                    BankManagement.viewBalance(accNo);
                }
    		}
    	}catch(SQLIntegrityConstraintViolationException e) {
    		System.out.println("Username Not Available, Please try again");
    	}catch(Exception e) {
    		System.out.println(e);
    	}
    	return false;
    }
    
    
    public static void viewBalance(int accNo) {
    	 try {
    		 
             // query
             sql = "select * from customer where acc_no="+ accNo;
             
             PreparedStatement st
                 = conn.prepareStatement(sql);
  
             ResultSet rs = st.executeQuery(sql);
             System.out.println(
                 "------------------------------------------------------------------------");
             System.out.printf("%12s %10s %10s\n",
                               "Account No", "Name",
                               "Balance");
  
             // Execution
  
             while (rs.next()) {
                 System.out.printf("%12d %10s %10d.00\n",
                                   rs.getInt("acc_no"),
                                   rs.getString("cust_name"),
                                   rs.getInt("balance"));
             }
             System.out.println(
                 "------------------------------------------------------------------------\n");
         }
         catch (Exception e) {
             e.printStackTrace();
         }
    }
    
}
