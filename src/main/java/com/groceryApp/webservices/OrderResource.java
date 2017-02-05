package com.groceryApp.webservices;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.groceryApp.dao.OrdersDao;
import com.groceryApp.entity.Item;
import com.groceryApp.entity.Orders;

@Path("/orders")
public class OrderResource extends FilterService{

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String placeOrder(Orders orders){
		try{	
			return new OrdersDao().placeOrder(orders) ? "success":"failed"; 
		}catch(Exception  ex){
			System.out.println(ex.getMessage());
			return "failed";
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Orders> getAllOrders(){
		return new OrdersDao().getAllOrders();
    }
	
	
	
	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Orders> getOrders(@PathParam("name") String customerId){
		return new OrdersDao().getOrders(customerId);
	}
	
	
	@GET
	@Path("/items/{orderId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Item> getOrderItems(@PathParam("orderId") int orderId){
		return new OrdersDao().getOrderItem(orderId);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String assignCrew(Orders orders){
		return new OrdersDao().assignCrew(orders)?"success":"failed";
	}
}
