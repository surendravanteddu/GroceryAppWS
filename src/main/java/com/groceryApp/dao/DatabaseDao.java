package com.groceryApp.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseDao {

	public Connection getConnection(){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://grocerapp.c92wi7qbbarw.us-west-2.rds.amazonaws.com:3306/groceryDb", "surendra", "surendra");
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
