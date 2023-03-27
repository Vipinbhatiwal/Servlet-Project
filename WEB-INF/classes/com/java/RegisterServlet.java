package com.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Timestamp dateTime  = new Timestamp(System.currentTimeMillis());

	public RegisterServlet() {
		System.out.println("IN no arg Constr.of Reg serv.");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		System.out.println("IN doget() of Reg serv.");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		Connection con = null;

		try { // first try where data goes in table

			System.out.println("IN dopost() of Reg serv.");

			String userFirstName = request.getParameter("userFirstName");
			String userName = request.getParameter("userName");

			String userPassword = request.getParameter("password");
			String userConfirmPassword = request.getParameter("repassword");

			if (!userPassword.equals(userConfirmPassword)) {
				pw.write("<html><body><b>  password entries do not match </b></body></html>");

			} else {
				String password1 = userPassword;

				con = JdbcConn.initializeDatabase();

				PreparedStatement st = con.prepareStatement("insert into march16 values(?,?,?,?,?,?)");

				st.setString(1, request.getParameter("userFirstName"));
				st.setString(2, request.getParameter("userName"));

				st.setString(3, password1);
				st.setString(4, "Active");
				st.setInt(5, '0');
				st.setString(6, dateTime.toString());
				
				st.executeUpdate();
				pw.write("<html><body><b> entries sucessfully inserted in table </b></body></html>");
				System.out.println("entries sucessfully inserted in table");
				 pw.print( "<a href= 'Login.html'> Login </a>|"); 
				

			}
		} catch (Exception i) 
		{
			pw.write("<html><body><b>  Username entries not unique </b></body></html>");
			System.out.println("Exception in first try where data goes in table");
			i.printStackTrace();
		} finally 
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
	}//do post ends here

}
