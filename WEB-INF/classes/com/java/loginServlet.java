package com.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;

public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PrintWriter pw = null;

	public loginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doGet(request, response);
	}
	// getting status using user name if locked check time of last login
	//if Active let him validate password, if password correct login 
	//if password wrong badcount++ till 3 times

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{   
		System.out.println("inside doGet() method of LoginServlet");
		pw=response.getWriter();
		try {//LocalDateTime dateTime1 = LocalDateTime.now();

			Timestamp ts  = new Timestamp(System.currentTimeMillis());
		Connection con=null;	
		
		ResultSet rs = null;
		con = JdbcConn.initializeDatabase();
		String username1=request.getParameter("userName");  
		String userpass=request.getParameter("userPass");
		String sql="select * from march16 where username='"+username1+"'";
		PreparedStatement st2 = con.prepareStatement(sql);
		rs=st2.executeQuery();
		System.out.println("select * from march16 in 53 line");
		
		String a=rs.toString();
        System.out.println(a);
        
		while (rs.next()) 
		{
		System.out.println("got the content");

			String stat = rs.getString("status");
	         int count = rs.getInt("badcount");
	         String timestamp=rs.getString("timestamp");
			System.out.println(stat);

			if(!stat.equals("Active"))//CHECK
			{  
				
				
				System.out.println(ts);
			
			
			
			
					String t= timestamp;
					
						
			
				 System.out.println(t);
				 final Timestamp t1 = Timestamp.valueOf(t);
				 System.out.println(t1);
				 long milliseconds = ts.getTime() - t1.getTime();
				 System.out.println(milliseconds);
				 int hours = (int) milliseconds / (3600000);
				 System.out.println(hours);
				

				
				
				if(hours>12)
				{
					System.out.println("time=13 condition");
					PreparedStatement st = con.prepareStatement("UPDATE march16\n"
							+ "SET status= 'Active', \n"
							+ " timestamp='"+ts+"' "
							+ "WHERE username='"+username1+"'");
					st.executeUpdate();	


					if(Validate.validatePassword(userpass))
					{
						response.setContentType("text/html"); 
						HttpSession session=request.getSession(); 
					 
						session.setAttribute("uname",username1);  

						pw.print("<html><body><h1>Password  matches<h1></body></html>");
						pw.write("<a href='Welcome_Servlet'>Welcome</a>"); 
						pw.write("<a href='Logout_Servlet'>Logout</a>"); 


					}else //if status Active and count<3
					{
						pw.print("<html><body><h1>Password do not  match<h1></body></html>");
						PreparedStatement st1 = con.prepareStatement("UPDATE march16\n"
								+ "SET  \n"
								+ " timestamp='"+ts+"' ,\n"
								+ " badcount='"+count+"'\n "
								+ "WHERE username='"+username1+"'");
						st1.executeUpdate();	
						count++;
						if(count>=3)
						{PreparedStatement st11 = con.prepareStatement("UPDATE march16\n"
								+ "SET status= locked \n"
								+ "WHERE username='"+username1+"'");
						st11.executeUpdate();

						}
						else
						{
							pw.print(" <a href='Login.html'>Login </a>");
						}

					}
				}else{
					System.out.println("come later madi , after"+ milliseconds+"");
				
					
					pw.print("<html><body><h1>your account is locked due toi multiple  bad login attempts ,come later madi , after"+ milliseconds+"<h1></body></html>");
				    }
			    }
				else// if Active	
				{
					System.out.println("if Active  status true passed to else block");
					if(Validate.validatePassword(userpass))
					{
						System.out.println("if Active  status true passed to else block");
						
						PreparedStatement st = con.prepareStatement("UPDATE march16\n"
								+ "SET  \n"
								+ " timestamp='"+ts+" '"
								+ "WHERE username='"+username1+"'");
						st.executeUpdate();	
						
						response.setContentType("text/html"); 
						HttpSession session=request.getSession(); 
						 
						session.setAttribute("uname",username1);  

						pw.print("<html><body><h1>Password  matches<h1></body></html>");
						pw.write("<a href='Welcome_Servlet'>Welcome</a>|");
						pw.write("<a href='Logout_Servlet'>Logout</a>"); 
						


					 }else 
					{
						pw.print("<html><body><h1>Password do not  match<h1></body></html>");
						count++;
						PreparedStatement st = con.prepareStatement("UPDATE march16\n"
								+ "SET  \n"
								+ " timestamp='"+ts+"', \n"
								+ " badcount='"+count+"'\n "
								+ "WHERE username='"+username1+"'");
						st.executeUpdate();	
						if(count==3)
						{
							PreparedStatement st1 = con.prepareStatement("UPDATE march16\n"
								+ "SET status= 'Locked' \n"
								+ "WHERE username='"+username1+"'");
						    st1.executeUpdate();

						}
						else
						{
							 pw.print(" <a href='Login.html'>Login </a>|");
							 pw.print( "<a href= 'Register.html'> Homepage </a>"); 
						}

					}
					
				}
			   
		}
		con.close();
		
		
		
		}
		catch (SQLException e) 
		{	
			e.printStackTrace();
		}
		catch(Throwable t)
		{
			t.printStackTrace();
		}
		finally{
			System.out.println("in the end of lioginservlet");
		
		}
	}

}

	
