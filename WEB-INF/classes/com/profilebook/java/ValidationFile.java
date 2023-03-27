package com.profilebook.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidationFile {
	
	
	 
	 PreparedStatement p = null;
     ResultSet rs = null;
     Connection con = null;
     String userFirstname;
	
	
	protected boolean validatePassword(String password,String repassword)
	{
		if(password.equals(repassword))
		{
			return true;
		}
		return false;
	}
	
	
	
}
