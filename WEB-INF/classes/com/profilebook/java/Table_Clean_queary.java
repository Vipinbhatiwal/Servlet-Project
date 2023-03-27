package com.profilebook.java;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Table_Clean_queary {

	public Table_Clean_queary() {
		// TODO Auto-generated constructor stub
	}
	


	public static void clean() {
		// TODO Auto-generated method stub
		   try {
				Connection con =JdbcConn.initializeDatabase();
				
				String sql= "UPDATE marchMid SET bad_loginAttempt= 0 where UserName='kkk'";
						
				 PreparedStatement st = con.prepareStatement(sql);
				 st.executeUpdate();  //******check here ALWAYS IF UPDATE OR EXECUTE
				   }catch(Throwable t)
				   {
					 System.out.println("exception occured check your database connection in table clean class");  
				   }
		
	}

}
