package com.groceryApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.groceryApp.entity.AppUser;

public class AppUserDao {

	DatabaseDao dao = null;
	
	public AppUserDao(){
		dao = new DatabaseDao();
	}

	//Login
    public boolean logIn(AppUser user){
     Connection con=null;
     try{
     con=dao.getConnection();
     PreparedStatement ps=con.prepareStatement("select * from user where username=? and password=? and role=?");
     ps.setString(1,user.getUsername());
     ps.setString(2,user.getPassword());
     ps.setString(3, user.getRole());
     ResultSet rs = ps.executeQuery();
     return rs.next();
     }
    catch(Exception e){
    System.out.println(e.getMessage());
    return false;
    }
   }
    
	//SignUp
	public boolean signUp(AppUser user){
		Connection con = null;
		try{
			DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			Date dt = format.parse(user.getDob());
			con = dao.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into user values (?,?,?,?,?,?,?,?,?)");
			ps.setString(1,user.getUsername());
			ps.setString(2,user.getFirstname());
			ps.setString(3,user.getLastname());
			ps.setString(4,user.getPassword());
			ps.setString(5,user.getEmail());
			ps.setString(6,user.getPhone());
			ps.setString(7,user.getAddress());
			ps.setDate(8, new java.sql.Date(dt.getTime()));
			ps.setString(9,user.getRole());
			return ps.executeUpdate() == 1 ? true : false;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	//get customer list
	public List<AppUser> getCustomersList(){
		List<AppUser> list = new  ArrayList<>();
		Connection con = null;
		try{
			con=dao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from user where role='customer'");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				AppUser c = new AppUser(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
						rs.getDate(8).toString(),rs.getString(9));
				list.add(c);
			}
			return list;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}
	}

	//getCustomer
	public AppUser getCustomer(String username){
		List<AppUser> list = new  ArrayList<>();
		Connection con = null;
		try{
			con=dao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from user where  username='"+username+"'");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				AppUser c = new AppUser(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
						rs.getDate(8).toString(),rs.getString(9));
				return c;
			}
			return null;
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}
	}	
	
	public boolean updateProfile(AppUser user){
		Connection con=null;
		try{
			con=dao.getConnection();
			DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			Date dt = format.parse(user.getDob());
			PreparedStatement ps=con.prepareStatement("update user set firstname=?,lastname=?,email=?,phone=?,address=?,dob=? where username=?");
			ps.setString(1, user.getFirstname());
			ps.setString(2, user.getLastname());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPhone());
			ps.setString(5, user.getAddress());
			ps.setDate(6, new java.sql.Date(dt.getTime()));
			ps.setString(7, user.getUsername());
			return ps.executeUpdate() == 1 ? true :false;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean changePassword(AppUser appuser){
		try{
		     Connection con = dao.getConnection();
		     String data[] = appuser.getUsername().split(":");
		     PreparedStatement ps = con.prepareStatement("update user set password=? where password=? and username=?");
		     ps.setString(1, data[1]);
		     ps.setString(2, appuser.getPassword());
		     ps.setString(3, data[0]);
		     return ps.executeUpdate()==1? true:false;
			
		}catch(Exception ex){
			return false;
		}
	}
	
	public List<AppUser> getGrocerList(){
		List<AppUser> list = new  ArrayList<>();
		Connection con = null;
		try{
			con=dao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from user where role='grocer'");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				AppUser c = new AppUser(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
						rs.getDate(8).toString(),rs.getString(9));
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
		/*AppUser a = new AppUser("1001357754",
				"surendra",
				"vanteddu",
				"password",
				 "customer",
				"surendranaidu04@gmail.com",
				"9090909090",
				"arlington",
				 "08/24/1993");
		if(new AppUserDao().signUp(a)){
			System.out.println("added");
		}else{
			System.out.println("no");
		}*/
		
	}
	
  	
	
}
