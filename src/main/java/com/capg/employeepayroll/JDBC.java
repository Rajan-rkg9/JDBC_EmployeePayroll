package com.capg.employeepayroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
	static String url = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
	static String userName = "root";
	static String password = "RajanRKG@0909";
	private static Connection con = null;
	
	public static Connection getConnection()
	{
		try {
			//Driver Loading
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			//Making the connection to Database
			con = DriverManager.getConnection(url,userName,password); 
			System.out.println("Connection Successful");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}