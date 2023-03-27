package com.java;

import java.io.IOException;
import java.io.PrintWriter;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");  

		
		HttpSession session= request.getSession(false);  
		String name =(String)session.getAttribute("uname");
		pw.print("<html><body><h1>Welcome MR"+ name+" to PROJECT MARCH15 <h1></body></html>"); 
		
	   
	
		
		pw.print("<a href='Logout_Servlet'>logout</a>"); 
	     
		pw.close();
	     

	}

}
