package com.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConn {


      protected static Connection initializeDatabase()
		        throws SQLException, ClassNotFoundException
		    {
		        // Initialize all the information regarding
		        // Database Connection
		        String dbDriver = "com.mysql.cj.jdbc.Driver";
		        String dbURL = "jdbc:mysql:// localhost:3306/";
		        // Database name to access
		        String dbName = "profilebook";
		        String dbUsername = "root";
		        String dbPassword = "1234567890";
		  
		        Class.forName(dbDriver);
		        Connection con = DriverManager.getConnection(dbURL + dbName,
		                                                     dbUsername, 
		                                                     dbPassword);
		        return con;
	       }

}
