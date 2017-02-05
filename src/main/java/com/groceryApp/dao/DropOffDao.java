package com.groceryApp.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.groceryApp.entity.Crew;
import com.groceryApp.entity.DropOff;
import com.groceryApp.entity.Item;
import com.groceryApp.entity.Orders;

public class DropOffDao {
	DatabaseDao dao=null;
	
	public DropOffDao(){
		dao = new DatabaseDao();
	}
	
	public boolean addDropOff(DropOff dropoff) throws SQLException{
		Connection con=null;
		try{
			con=dao.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into dropoff (address) values (?)");
			ps.setString(1,dropoff.getAddress());
			return ps.executeUpdate() == 1 ? true : false;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}finally{
			con.close();
		}
	}
	
	public boolean deleteDropOff(DropOff dropoff){
		Connection con = null;
		try{
			con=dao.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from dropoff where id=?");
			ps.setInt(1,dropoff.getId());
			return ps.executeUpdate() == 1 ? true : false;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	public List<DropOff> viewDropOffList(){
		List<DropOff> list = new  ArrayList<>();
		Connection con = null;
		try{
			con=dao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from dropoff");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				DropOff d = new DropOff(rs.getInt(1),rs.getString(2));
				list.add(d);
			}
			return list;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	public int getDropOffId(String dropoff){
		Connection con = null;
		try{
			con=dao.getConnection();
			PreparedStatement ps=con.prepareStatement("select id from dropoff where address='"+dropoff+"'");
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return 0;
		}
	}
	
	
	public static void main(String[] args) {
		Date dt = new Date();
	    CrewDao c = new CrewDao();
	    for(Crew x : c.getCrewList()){
	    	System.out.println(x.getFirstname());
	    }
		
	}
}
