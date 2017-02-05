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
import com.groceryApp.dao.VacationListDao;
import com.groceryApp.entity.AppUser;
import com.groceryApp.entity.Crew;
import com.groceryApp.entity.Item;
import com.groceryApp.entity.VacationList;


@Path("/vacationList")
public class VacationListResource extends FilterService{

@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String addToVacationList(VacationList vacationlist) throws SQLException{
	return	new VacationListDao().addToVacationList(vacationlist)?"success":"failed";	
}

@DELETE
@Path("/{crewId}")
@Produces(MediaType.TEXT_PLAIN)
public String deleteItem(@PathParam("crewId") String id){
	VacationList v = new VacationList();
	v.setCrewId(id);
	System.out.println(id+"****************");
	return new VacationListDao().deleteFromVacationList(v)?"deleted" : "failed";
}

@GET
@Produces(MediaType.APPLICATION_JSON)
public List<Crew> getIt() {
    return new VacationListDao().getVacationList();
}

}
