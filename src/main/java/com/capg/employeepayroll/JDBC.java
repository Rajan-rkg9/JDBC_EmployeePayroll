package com.capg.employeepayroll;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {
	static String url = "jdbc:mysql://localhost:3306/payroll_service?allowPublicKeyRetrieval=true&useSSL=false";
	static String userName = "root";
	static String password = "RajanRKG@0909";
	private static Connection con = null;
	
	/**
	 * UC1
	 */
	public static Connection getConnection()
	{
		try {
			//Driver Loading
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			//Making the connection to Database
			con = DriverManager.getConnection(url,userName,password); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
