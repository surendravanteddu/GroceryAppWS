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
import com.groceryApp.entity.Crew;
import com.groceryApp.entity.Item;

public class ItemDao {

	DatabaseDao dao = null;
	
	public ItemDao(){
		dao = new DatabaseDao();
	}

	public boolean addItem(Item item){
		Connection con = null;
		try{
			con=dao.getConnection();
			
			PreparedStatement ps = con.prepareStatement("select max(itemId) as id from item");
			 ResultSet rs = ps.executeQuery();
			 rs.next();
			 int id = rs.getInt("id");
			 ps = con.prepareStatement("select count(*) as cou from item where itemName='"+item.getItemName()+"'");
			  rs = ps.executeQuery();
			 rs.next();
			 int count = rs.getInt("cou");
			 if(count > 0){
				 return false;
			 }
			 
			ps = con.prepareStatement("insert into item values (?,?,?,?)");
			ps.setInt(1,id+1);
			ps.setString(2,item.getItemName());
			ps.setInt(3,item.getCost());
			ps.setInt(4,item.getQuantity());
			return ps.executeUpdate() == 1 ? true : false;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	public boolean deleteItem(Item item){
		Connection con = null;
		try{
			con=dao.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from item where itemId=?");
			ps.setInt(1,item.getItemId());
			return ps.executeUpdate() == 1 ? true : false;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	public boolean updateItem(Item item){
		Connection con = null;
		try{
			con=dao.getConnection();
			PreparedStatement ps = con.prepareStatement("update item set itemName=?, cost=?, quantity=? where itemId=?");
			ps.setString(1,item.getItemName());
			ps.setInt(2,item.getCost());
			ps.setInt(3,item.getQuantity());
			ps.setInt(4,item.getItemId());
			return ps.executeUpdate() == 1 ? true : false;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	public List<Item> viewItemList(){
		List<Item> list = new  ArrayList<>();
		Connection con = null;
		try{
			con=dao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from item");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Item c = new Item(rs.getInt(1),rs.getString(2),rs.getInt(3),
						rs.getInt(4));
				list.add(c);
			}
			return list;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	public List<Item> searchItemList(String itemName){
		List<Item> list = new  ArrayList<>();
		Connection con = null;
		try{
			con=dao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from item where itemName like '"+itemName+"%'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Item c = new Item(rs.getInt(1),rs.getString(2),rs.getInt(3),
						rs.getInt(4));
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
		Item a = new Item(101,
				"Cabbage",
				3,
				11); 
		if(new ItemDao().updateItem(a)){
			System.out.println("deleted");
		}else{
			System.out.println("no");
		}
		
	}
	
	
}
