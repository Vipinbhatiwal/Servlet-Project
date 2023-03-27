package com.profilebook.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class Welcomepage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Welcomepage() {
        System.out.println("in welcomepage constructor");
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in doget of welcome");
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("in do post of welcome");
		PrintWriter pw = response.getWriter();
            PreparedStatement p = null;
            ResultSet rs = null;
            Connection con = null;
            String UserNameEntered= request.getParameter("userName");
            System.out.println("data retrieved from table"+UserNameEntered);
            try {
				con = JdbcConn.initializeDatabase();
				
				
    			System.out.println("con");
    		
          String sql = "select * from marchMid where UserName = '"+UserNameEntered+"'";
                
                System.out.println(sql);  
                
					p = con.prepareStatement(sql);
				
                
					rs = p.executeQuery();
			 
                System.out.println("query executed");
               
                
               
                   
					while(rs.next()) {
					    String Firstname = rs.getString("FirstName"); // from data table
					    String Lastname = rs.getString("LastName");
					    String mobileNumber = rs.getString("mobileNumber");
					    String Email= rs.getString("Email");
					    String Address= rs.getString("Address");
					    String Username = rs.getString("UserName");
					    String Password= rs.getString("UserPassword");
					    System.out.println("in while loop of login servlet");
					   

  // give address of the profile page where you can update your profile except
 
  pw.print("<html><body><h1>Welcome to your profile you are successfully logged in , your details:<h1></body></html>");
     pw.print("<html><body><h1>your name is   :   "+Firstname+"<h1></body></html> ");
     pw.print("<html><body><h1>your Email is  :   "+Email+"<h1></body></html>");
  pw.print("<html><body><h1>your address is:   "+Address+"<h1></body></html>");
     pw.print("<html><body><h1>your user name is:   "+Username+"<h1></body></html>") ; 
     // pw.write("<a href=\"Login.html\">Login </a>|  ");
  pw.write("<a href=\"Logout.html\">Logout </a>|  ");
  //pw.write("<a href=\"Register.html\">Register </a> ");
     System.out.println("in if condition of login servlet");
     
     HttpSession session=request.getSession();  
     session.setAttribute("uname",Username);  
     con.close();		   }
				} 
              catch(SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();}
                   
            catch(ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
           

            
            	
         
	}
}      
	     
