package com.java;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class logoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public logoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter pw = response.getWriter();
		
		
		HttpSession session = request.getSession(false);
		String name =(String)session.getAttribute("uname");
		session.invalidate();
		pw.print("<html><body><h1>you have Sucessfully logged out"+name+"<h1></body></html>"); 
		
		 pw.print( "<a href= 'Login.html'> Login </a>|"); 
		 pw.print( "<a href= 'Register.html'>    Homepage </a>"); 
		}
		
	}


