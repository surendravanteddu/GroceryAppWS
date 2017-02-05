package com.groceryApp.webservices;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.groceryApp.dao.AppUserDao;
import com.groceryApp.dao.CrewDao;
import com.groceryApp.dao.ItemDao;
import com.groceryApp.entity.AppUser;
import com.groceryApp.entity.Crew;
import com.groceryApp.entity.Item;


@Path("/item")
public class ItemResource extends FilterService{

@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String addItem(Item item) throws SQLException{
	return	new ItemDao().addItem(item)?"success":"failed";	
}

@GET
@Path("itemList")
@Produces(MediaType.APPLICATION_JSON)
public List<Item> viewItemList(){
	try{
		return new ItemDao().viewItemList();
	}catch(Exception ex){
		System.out.println(ex.getMessage());
		return null;
	}
}

@GET
@Path("/search/{itemName}")
@Produces(MediaType.APPLICATION_JSON)
public List<Item> searchItemList(@PathParam("itemName") String itemName){
	try{
		return new ItemDao().searchItemList(itemName);
	}catch(Exception ex){
		System.out.println(ex.getMessage());
		return null;
	}
}

@PUT
@Path("/{itemId}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateItem(@PathParam("itemId") int id,Item item){
	item.setItemId(id);
	return new ItemDao().updateItem(item)? "updated":"failed";
}

@DELETE
@Path("/{itemId}")
@Produces(MediaType.TEXT_PLAIN)
public String deleteItem(@PathParam("itemId") int id){
	Item i = new Item();
	i.setItemId(id);
	return new ItemDao().deleteItem(i)?"deleted" : "failed";
}

}
