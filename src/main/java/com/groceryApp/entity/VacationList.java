package com.groceryApp.entity;

public class VacationList {
	public VacationList(String crewId) {
		super();
		this.crewId = crewId;
	}
	
	public VacationList(){
		
	}

	private String crewId;

	public String getCrewId() {
		return crewId;
	}

	public void setCrewId(String crewId) {
		this.crewId = crewId;
	}
}
