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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.groceryApp.entity.Cart;
import com.groceryApp.entity.Crew;
import com.groceryApp.entity.Item;
import com.groceryApp.entity.Orders;

public class CartDao {
	DatabaseDao dao=null;
	
	public CartDao(){
		dao = new DatabaseDao();
	}
	
	public boolean addToCart(String customerId,Item item) throws SQLException{
		Connection con=null;
		try{
			con=dao.getConnection();
			String sql= "";
			boolean flag = ifavailbleincart(customerId, item.getItemId()); 
			if(flag){
				sql = "update cart set quantity=quantity+1 "
						+ "where customerId='"+customerId+"' and itemId="+item.getItemId()+"";
			}else{
				sql = "insert into cart values(?,?,?,?)";
			}
			 
			PreparedStatement ps=con.prepareStatement(sql);
			
			if(!flag){
			ps.setString(1,customerId);
			ps.setInt(2,item.getItemId());
			ps.setInt(3,item.getCost());
			ps.setInt(4,item.getQuantity());
			}
			return ps.executeUpdate()==1? true:false;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}finally{
			con.close();
		}
	}
	
	public Cart getCart(String customerId){
		Connection con = null;
		try{
			con =  dao.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT item.itemName as itemname,cart.unitcost as unitcost,cart.quantity as quantity,"
					+ "cart.itemid as itemid FROM cart,item where cart.customerid='"+customerId+"' and cart.itemid=item.itemId;");
			ResultSet rs = ps.executeQuery();
			List<Item> items = new ArrayList<>();
			while(rs.next()){
				Item item = new Item();
				item.setItemId(rs.getInt("itemid"));
				item.setItemName(rs.getString("itemname"));
				item.setQuantity(rs.getInt("quantity"));
				item.setCost(rs.getInt("unitcost"));
			    items.add(item);
			}
			Cart cart =  new Cart();
			cart.setCart(items);
			return cart;
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	public boolean ifavailbleincart(String customerId,int itemId){
		Connection con = null;
		try{
			con = dao.getConnection();
	        PreparedStatement ps=con.prepareStatement("select count(*) as x from cart where customerId = ? and itemId=?");
		   ps.setString(1,customerId);
		   ps.setInt(2, itemId);
	        ResultSet rs = ps.executeQuery();
	        rs.next();
		    System.out.println(rs.getInt("x"));
			return  rs.getInt("x")==1 ? true:false ;
		
		
		}catch(Exception ex){
			return false;
		}
	}
   public boolean emptyCart(String username){
	   Connection con = null;
		try{
			con = dao.getConnection();
	        PreparedStatement ps=con.prepareStatement("delete from cart where customerid ='"+username+"'");
	        return  ps.executeUpdate()>0?true:false;
		}catch(Exception ex){
			return false;
		}
	}
   
	public boolean removefromCart(String customerId,int itemId){
		Connection con = null;
		try{
			con = dao.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from cart where customerId='"+customerId+"' and itemId="+itemId+"");
			return ps.executeUpdate()==1?true:false;
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}
	}
	public static void main(String[] args) {
		CartDao c=new CartDao();
		//System.out.println(c.ifavailbleincart("1",105));
		c.emptyCart("1210310266");
	}
}
