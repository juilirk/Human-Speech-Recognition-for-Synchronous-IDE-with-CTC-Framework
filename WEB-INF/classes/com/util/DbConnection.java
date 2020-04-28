package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	public static Connection getConnection()

	{
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/speech", "root", "");
			return con;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception is " + e);

		}
		return con;
	}

}