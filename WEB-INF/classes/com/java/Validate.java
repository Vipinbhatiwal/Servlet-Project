package com.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Validate {

	public Validate() {
		// TODO Auto-generated constructor stub
	}

	public static boolean validatePassword(String password) throws SQLException {
		PreparedStatement p = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = JdbcConn.initializeDatabase();
			String sql = "select password from march16 where password = '" +password + "'";
			System.out.println(sql);
			p = con.prepareStatement(sql);
			rs = p.executeQuery();

			while (rs.next()) {
				String passwordTb = rs.getString("password");

				if (password.equals(passwordTb)) {
					return true;
				}
			}	
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return false;
	}

}
