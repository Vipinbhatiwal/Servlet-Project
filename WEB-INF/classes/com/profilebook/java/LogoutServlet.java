package com.profilebook.java;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LogoutServlet() {
        // TODO Auto-generated constructor stub
    	System.out.println("logout");
    }

	protected void doPost(HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter pw= response.getWriter();
		pw.write(" <html><body><b> you have successfully logged out"
	            + "</b></br></br></br></body></html></br>");
		pw.write("<a href=\"Login.html\">Login again   </a> |  ");
		pw.write("<a href=\"Register.html\">  Register </a> | ");
		
	}

}
