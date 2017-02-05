package com.groceryApp.webservices;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.groceryApp.dao.CartDao;
import com.groceryApp.entity.Cart;
import com.groceryApp.entity.Item;


@Path("/cart")
public class CartResource extends FilterService{

@POST
@Path("/{customerId}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String addToCart(@PathParam("customerId")String customerId ,Item item) throws SQLException{
	return new CartDao().addToCart(customerId, item)?"success":"failed";
}

@GET
@Path("/{customerId}")
@Produces(MediaType.APPLICATION_JSON)
public Cart getCart(@PathParam("customerId") String customerId){
	return new CartDao().getCart(customerId);
}

@DELETE
@Path("/{customerId}/{itemId}")
@Produces(MediaType.TEXT_PLAIN)
public String removefromCart(@PathParam("customerId") String customerId,@PathParam("itemId") int itemId){
	return new CartDao().removefromCart(customerId, itemId)?"success":"failed";
}



}
