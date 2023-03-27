package com.profilebook.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Halt
 */
public class Halt_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con = null;
    public  Halt_servlet() {
      
    	System.out.println("in halt constr of haltservlet");
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in doget of haltservlet");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw= response.getWriter();
		System.out.println("in dopost of haltservlet");	
		try {
			con = JdbcConn.initializeDatabase();
		   } catch (ClassNotFoundException e1) {
                 e1.printStackTrace();
		     } catch (SQLException e1) {
		    	pw.print("<html><body>conection  not created error </body></html>");
			    e1.printStackTrace();
		      }
		pw.print("<html><body>conection created with table </body></html>");
		String UserNameEntered = request.getParameter("userName");
		
		PreparedStatement st=null;
		try {
			st = con.prepareStatement("UPDATE marchMid SET loginAttempt='"+0+"' WHERE UserName= '"+UserNameEntered+"'");
		     } catch (SQLException e1) {
		    	;
			    e1.printStackTrace();
		        }
           try {
			st.executeUpdate();
			
			System.out.println("Going for sleep for 5 secs");
			
			pw.print("Going for sleep for 5 secs");
	       
	        	
	        	for(int i=0;i<5;i++)
	        	{
	            Thread.sleep(2000);
	            pw.print(i);
	            }
	            
	        System.out.println("Resumed after 5 secs");
	        pw.print("resumed sleep after 5 secs");
	        pw.print("<a href=\"Login.html\">Login after halt</a>");
		       } catch (SQLException e1) {
		    	   pw.print("<html><body> UPDATE marchMid SET loginAttempt could not be done</body></html>");
		
			        e1.printStackTrace();
	             	}
                  catch (InterruptedException e) {
	             e.printStackTrace();
	            }
		
        
	}

}
