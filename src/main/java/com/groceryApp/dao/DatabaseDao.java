package com.groceryApp.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseDao {

	public Connection getConnection(){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://hostname:3306/groceryDb", "username", "password");
			return con;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	public void close(Connection con){
		try{
			con.close();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}
