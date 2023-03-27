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

public class Login_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	

	public Login_Servlet() {
		System.out.println("in no arg constr of login servlet");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("in dopost of login servlet");
		Table_Clean_queary.clean();

		PrintWriter pw = response.getWriter();

		String UserNameEntered = request.getParameter("userName");
		String passEntered = request.getParameter("userPass");
		PreparedStatement p = null;
		ResultSet rs = null;
		Connection con = null;
		System.out.println("data retrieved from table" + UserNameEntered);
		try {

			con = JdbcConn.initializeDatabase();
			System.out.println("con");
			
			String sql = "select * from marchMid where UserName = '" + UserNameEntered + "'";
			
			System.out.println(sql);
			p = con.prepareStatement(sql);
		
			rs = p.executeQuery();
			
			System.out.println("query executed");

			while (rs.next()) {
				String Firstname = rs.getString("FirstName"); // from data table
				String Lastname = rs.getString("LastName");
				String mobileNumber = rs.getString("mobileNumber");
				String Email = rs.getString("Email");
				String Address = rs.getString("Address");
				String Username = rs.getString("UserName");
				String Password = rs.getString("UserPassword");
			    int loginAtt = rs.getInt("bad_loginAttempt");
				System.out.println("in while loop of login servlet");

				if (Password.equals(passEntered)) {

					RequestDispatcher rd = request.getRequestDispatcher("Welcome");
					rd.forward(request, response);

					/*
					 * // give address of the profile page where you can update your profile except
					 * username , emailid System.out.println("right password"); pw.
					 * print("<html><body><h1>Welcome to your profile you are successfully logged in , your details:<h1></body></html>"
					 * );
					 * pw.print("<html><body><h1>your name is   :   "+Firstname+"<h1></body></html>"
					 * );
					 * pw.print("<html><body><h1>your Email is  :   "+Email+"<h1></body></html>");
					 * pw.print("<html><body><h1>your address is:   "+Address+"<h1></body></html>");
					 * pw.print("<html><body><h1>your address is:   "+Username+"<h1></body></html>")
					 * ; // pw.write("<a href=\"Login.html\">Login </a>|  ");
					 * pw.write("<a href=\"Logout.html\">Logout </a>|  ");
					 * //pw.write("<a href=\"Register.html\">Register </a> ");
					 * System.out.println("in if condition of login servlet");
					 */
				} else 
				{// update loginAttempt
					pw.print("<html><body>wrong password or user name</body></html>");
					System.out.println("wrong pass or username while login");
					loginAtt++;
					//update in table
					PreparedStatement st = con.prepareStatement("UPDATE marchMid SET bad_loginAttempt='"+loginAtt+"' WHERE UserName= '"+UserNameEntered+"'");
                    st.executeUpdate();
					if(loginAtt==3)
					{
						RequestDispatcher rd = request.getRequestDispatcher("halt");
						rd.forward(request, response);	
					}
					else
					{
						pw.write("<a href=\"Login.html\">Login again </a>");
					}

			
		
			
			
			

			System.out.println("outof the while loop");
           
		}
				
			}}

		// Catch block to handle exception
		catch (SQLException e) {

			// Print exception pop-up on screen
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
			
			
			finally {

			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(Throwable t)
			{
				t.printStackTrace();
			}

		}
		}}
	

