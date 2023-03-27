package com.profilebook.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public RegisterServlet() {
		System.out.println("in nor arg contructor of regsiter servlet");
	}


	ValidationFile vf=new ValidationFile();
	int max=0;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{   
		

		LocalDateTime dateTime=LocalDateTime.now();

		System.out.println("in do post of register servlet");
		PrintWriter out = response.getWriter();
		String userFirstName=request.getParameter("userFirstName"); 

		System.out.println(userFirstName);

		String userPass=request.getParameter("userPass");  
		String repeatUserPass=request.getParameter("repeatUserPass");  
		
			 if(!vf.validatePassword(userPass, repeatUserPass))
			{
				out.write("<html><body><b>Confirm PASSWORD DO NOT MATCH, register again!!"
						+ "</b></body></html>");
				out.write("<a href=\"Login.html\">Login </a>|  ");
				out.write("<a href=\"logout.html\">Logout </a>|  ");
				out.write("<a href=\"Register.html\">Register </a>  ");

			}
			else{



				//	pw.write("<a href=\"Login.html\">Login </a>|  ");
				//	pw.write("<a href=\"Logout.html\">Logout </a> ");
				//	pw.write("<a href=\"Register.html\">Register </a>  ");

				// Initialize the database
				
				try {
				Connection con = JdbcConn.initializeDatabase();				 
				PreparedStatement st = con.prepareStatement("insert into marchMid values(?,?,?,?,?,?,?,?,?,?)");	

				st.setString(1, request.getParameter("userFirstName"));
				st.setString(2, request.getParameter("userLastName"));

				st.setString(3, request.getParameter("mobileNumber"));
				st.setString(4, request.getParameter("userEmail"));
				st.setString(5, request.getParameter("Address"));
				
				try {
				st.setString(6, request.getParameter("userName"));
				}catch(Throwable t)
				   {
					System.out.println("in try block of useName"+t.getMessage());
					out.write("<html><body><b>USer name already exists sir, register again with a different user name!!"
							+ "</b></body></html>");
					out.write("<a href=\"Register.html\">Register </a>  ");	
			       	}

				st.setString(7, request.getParameter("userPass"));	
				st.setInt(8, 0);
				st.setString(9,"Active");

				st.setString(10, dateTime.toString());
				st.executeUpdate();
				st.close();
				con.close();

				String userFirstName1=request.getParameter("userFirstName");  


				HttpSession session=request.getSession();  
				session.setAttribute("uname",userFirstName1);  

				//   String html="<html><body><h1> You are Welcome  in ProfileBook ! "+ userFirstName1+ " ji </h1> </body></html>";
				//pw.write(html);
				// Get a writer pointer 
				// to display the successful result
				out.write("<html><body><b>Successfully Inserted"
						+ "</b></body></html>");
				out.write("<a href=\"Logout.html\">Logout </a> ");
			

			   } catch(Exception t)
				{
				   System.out.println("after validation of password to register there is an error while entering records in the table");
				   out.write("<a href=\"Register.html\">Register </a>  ");	
				   out.write("<a href=\"Register.html\">Register </a>  ");
				}

            
		       }
		
		
	}
}






		
	

