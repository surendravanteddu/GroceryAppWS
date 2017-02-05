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
import com.groceryApp.entity.Item;
import com.groceryApp.entity.Orders;
import com.groceryApp.entity.VacationList;

public class VacationListDao {
	DatabaseDao dao=null;
	
	public VacationListDao(){
		dao = new DatabaseDao();
	}
	
	public boolean addToVacationList(VacationList vacationlist) throws SQLException{
		Connection con=null;
		try{
			con=dao.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into vacationlist values(?)");
			ps.setString(1,vacationlist.getCrewId());
			return ps.executeUpdate()==1? true:false;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}finally{
			con.close();
		}
	}
	
	public boolean deleteFromVacationList(VacationList vacationlist){
		Connection con = null;
		try{
			con=dao.getConnection();
			System.out.println(vacationlist.getCrewId()+"####");
			PreparedStatement ps = con.prepareStatement("delete from vacationlist where crewId=?");
			ps.setString(1,vacationlist.getCrewId());
			return ps.executeUpdate() == 1 ? true : false;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	public List<Crew> getVacationList(){
		List<Crew> list = new  ArrayList<>();
		Connection con = null;
		try{
			con=dao.getConnection();
			PreparedStatement ps=con.prepareStatement("select c.firstname, c.crewId from crew c,vacationlist v where c.crewId= v.crewId");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Crew v = new Crew();
				v.setCrewId(rs.getString(2));
				v.setFirstname(rs.getString(1));
				list.add(v);
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
