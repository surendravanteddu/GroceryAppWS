package com.groceryApp.webservices;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.groceryApp.dao.AppUserDao;
import com.groceryApp.entity.AppUser;



@Path("/AppUser")
public class AppUserResource extends FilterService{
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addAppUser(AppUser user) {
		System.out.println(user.getDob()+"***************************************");
	return	new AppUserDao().signUp(user)?"success":"failed";
	}
	
	@GET
	@Path("customer/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppUser getCustomersList(@PathParam("username") String username){
		try{
			return new AppUserDao().getCustomer(username);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	@GET
	@Path("customerList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AppUser> getCustomersList(){
		try{
			return new AppUserDao().getCustomersList();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	@GET
	@Path("grocerList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AppUser> getGrocerList(){
		try{
			return new AppUserDao().getGrocerList();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String login(AppUser appuser){
		return new AppUserDao().logIn(appuser)? "success" : "failed";
	}
	
	 @GET
	 @Produces(MediaType.TEXT_PLAIN)
	 public String getIt() {
	        return "Got it!";
	 }
	 
	    @PUT 
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateProfile(AppUser user){
			return new AppUserDao().updateProfile(user)? "success":"failed";
		}
	    
	    @PUT 
		@Path("/changePassword")
	    @Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String changePassword(AppUser user){
			return new AppUserDao().changePassword(user)? "success":"failed";
		}
	    
	    
	    
	 
	
}
 