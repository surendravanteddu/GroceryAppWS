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

public class OrdersDao {
	DatabaseDao dao=null;
	
	public OrdersDao(){
		dao = new DatabaseDao();
	}
	
	public boolean placeOrder(Orders orders) throws SQLException{
		Connection con=null;
		try{
			CartDao cdao = new CartDao();
			DropOffDao ddao=  new DropOffDao();
			con=dao.getConnection();
			DateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
			java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

			List<Item> list =cdao.getCart(orders.getCustomerId()).getCart();
			int cost = 0;
			int itemcount = 0;
			
			for(Item item : list){
				cost += item.getCost()*item.getQuantity();
				itemcount++;
			}
			
			PreparedStatement ps=con.prepareStatement("insert into orders (customerid,grocerid,itemCount,cost,status,dropOffid,OrderDate) values(?,?,?,?,?,?,?)");
            ps.setString(1, orders.getCustomerId());
			ps.setString(2,orders.getGrocerId());
			ps.setInt(3,itemcount);
			ps.setInt(4,cost);
			ps.setString(5,"pending");
			ps.setInt(6,ddao.getDropOffId(orders.getDropOffId()));
			ps.setDate(7, sqlDate);
			ps.executeUpdate();
			
			ps = con.prepareStatement("select max(orderId) as orderId from orders");
			ResultSet rs = ps.executeQuery();
			rs.next();
			String  orderId = rs.getString(1);
			
			Statement st=con.createStatement();
			for(Item i : list){
			st.addBatch("insert into orderitem (orderId,itemId,cost,quantity,totalCost) values ('"+orderId+"','"+i.getItemId()+"','"+i.getCost()+"','"+i.getQuantity()+"','"+(i.getQuantity()*i.getCost())+"')");
			}
			int rows[]=st.executeBatch();
			int sum =0;
			for(int i:rows){
				sum += i;
			}
			if( list.size() == sum ){
				return cdao.emptyCart(orders.getCustomerId());
			}else{
				return false;
			}
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}finally{
			con.close();
		}
			
	}
	
	public List<Orders> getOrders(String customerId){
		try{
			List<Orders> list = new ArrayList<>();
		    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Connection con = dao.getConnection();
			PreparedStatement ps= con.prepareStatement("select o.orderId,o.itemCount,o.cost,o.status,d.address as dropoff, o.orderDate"
					+ " from orders o , dropoff d where o.customerid=? and d.id=o.dropOffid;");
			ps.setString(1, customerId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Orders o = new Orders();
				o.setOrderId(rs.getInt("orderId"));
				o.setItemCount(rs.getInt("itemCount"));
				o.setCost(rs.getInt("cost"));
				o.setStatus(rs.getString("status"));
				o.setDropOffId(rs.getString("dropoff"));
				Date date = rs.getDate("orderDate");
				o.setDate(df.format(date));
				list.add(o);
			}			
			return list;
			
		}catch(Exception ex){
			return null;
		}
	}
	
	public List<Orders> getAllOrders(){
		try{
			List<Orders> list = new ArrayList<>();
		    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Connection con = dao.getConnection();
			PreparedStatement ps= con.prepareStatement("select o.orderId,o.itemCount,o.cost,o.status,d.address as dropoff, o.orderDate"
					+ " from orders o , dropoff d where d.id=o.dropOffid;");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Orders o = new Orders();
				o.setOrderId(rs.getInt("orderId"));
				o.setItemCount(rs.getInt("itemCount"));
				o.setCost(rs.getInt("cost"));
				o.setStatus(rs.getString("status"));
				o.setDropOffId(rs.getString("dropoff"));
				Date date = rs.getDate("orderDate");
				o.setDate(df.format(date));
				list.add(o);
			}			
			return list;
			
		}catch(Exception ex){
			return null;
		}
	}
	
	public boolean assignCrew(Orders orders){
		try{
			Connection con = dao.getConnection();
			System.out.println(orders.getStoreMemeberId()+"__"+orders.getDriverId()+"__"+orders.getOrderId()+"**********");
			PreparedStatement ps = con.prepareStatement("update orders o,crew c,crew c1 set o.storeMemberId=c.crewId,o.driverId=c1.crewId,o.status='shipped' where o.orderId=? and c.firstname=? and c1.firstname=?");
			ps.setInt(1, orders.getOrderId());
			ps.setString(2, orders.getStoreMemeberId());
			ps.setString(3, orders.getDriverId());
			return (ps.executeUpdate() == 1 ) ? true : false ;
		}catch(Exception ex){
			return false;
		}
		
	}
	public List<Item> getOrderItem(int orderId){
		try{
			List<Item> list = new ArrayList<>();
			Connection con = dao.getConnection();
			PreparedStatement ps = con.prepareStatement("select i.itemName, o.quantity,o.cost from orderitem o, item i where i.itemId=o.itemId and o.orderId=?");
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
			Item i = new Item();
			i.setItemName(rs.getString("itemName"));
			i.setQuantity(rs.getInt("quantity"));
			i.setCost(rs.getInt("cost"));
			list.add(i);
			}
			return list;
		}catch(Exception ex){
			return null;
		}
	}
	
	public static void main(String[] args) {
		try{
		Orders o = new Orders();
		
		
	}
		catch(Exception ex){
			
		}
	}
}
