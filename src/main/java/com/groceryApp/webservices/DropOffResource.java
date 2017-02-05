package com.groceryApp.webservices;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.groceryApp.dao.DropOffDao;
import com.groceryApp.entity.DropOff;


@Path("/dropOff")
public class DropOffResource extends FilterService{

@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String addDropOff(DropOff dropoff) throws SQLException{
	return	new DropOffDao().addDropOff(dropoff)?"success":"failed";	
}

@GET
@Produces(MediaType.APPLICATION_JSON)
public List<DropOff> viewDropOffList(){
	try{
		return new DropOffDao().viewDropOffList();
	}catch(Exception ex){
		System.out.println(ex.getMessage());
		return null;
	}
}

@DELETE
@Path("/{id}")
@Produces(MediaType.TEXT_PLAIN)
public String deleteDropOff(@PathParam("id") int id){
	DropOff d = new DropOff();
	d.setId(id);
	return new DropOffDao().deleteDropOff(d)?"deleted" : "failed";
}

}
