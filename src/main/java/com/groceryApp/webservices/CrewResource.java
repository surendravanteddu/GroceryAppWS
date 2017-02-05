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

import com.groceryApp.dao.CrewDao;
import com.groceryApp.entity.Crew;


@Path("/crew")
public class CrewResource extends FilterService{

@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String addCrew(Crew crew){
	try {
		return	new CrewDao().addCrewMember(crew)?"success":"failed";
	} catch (SQLException e) {
		e.printStackTrace();
		return "failed";
	}	
}

@DELETE
@Path("/{crewId}")
@Produces(MediaType.TEXT_PLAIN)
public String deleteCrew(@PathParam("crewId") String id){
	Crew c=new Crew();
	c.setCrewId(id);
	return new CrewDao().deleteCrew(c)?"deleted" : "failed";
}

@GET
@Path("/toAssign")
@Produces(MediaType.APPLICATION_JSON)
public List<Crew> getAssignCrewList() {
    return new CrewDao().getAssignCrewList();
}

@GET
@Produces(MediaType.APPLICATION_JSON)
public List<Crew> getIt() {
    return new CrewDao().getCrewList();
}





}
