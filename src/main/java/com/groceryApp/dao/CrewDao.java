package com.groceryApp.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.groceryApp.entity.Crew;
import com.groceryApp.entity.Item;

public class CrewDao {
	DatabaseDao dao=null;
	
	public CrewDao(){
		dao = new DatabaseDao();
	}
	
	public boolean addCrewMember(Crew crew) throws SQLException{
		Connection con=null;
		try{
			DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			Date dt = format.parse(crew.getDob());
			con=dao.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into crew values(?,?,?,?,?,?,?,?)");
			ps.setString(1,crew.getCrewId());
			ps.setString(2,crew.getRole());
			ps.setString(3,crew.getFirstname());
			ps.setString(4,crew.getLastname());
			ps.setString(5,crew.getEmail());
			ps.setString(6,crew.getPhone());
			ps.setString(7,crew.getAddress());
			ps.setDate(8, new java.sql.Date(dt.getTime()));
			return ps.executeUpdate() == 1 ? true : false;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}finally{
			con.close();
		}
	}
	
	public boolean deleteCrew(Crew crew){
		Connection con = null;
		try{
			con=dao.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from crew where crewId=?");
			ps.setString(1,crew.getCrewId());
			return ps.executeUpdate() == 1 ? true : false;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	public List<Crew> getCrewList(){
		List<Crew> list = new  ArrayList<>();
		Connection con = null;
		try{
			con=dao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from crew");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Crew c = new Crew(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
						rs.getDate(8).toString());
				list.add(c);
			}
			return list;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	public List<Crew> getAssignCrewList(){
		List<Crew> list = new  ArrayList<>();
		Connection con = null;
		try{
			con=dao.getConnection();
			PreparedStatement ps=con.prepareStatement("select c.firstname,c.role from crew c where c.crewId not in (select crewId from vacationlist) ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Crew c = new Crew();
				c.setFirstname(rs.getString(1));
				c.setRole(rs.getString(2));
				list.add(c);
			}
			return list;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
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
