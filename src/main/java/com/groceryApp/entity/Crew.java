package com.groceryApp.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Crew {
	
	public Crew(String crewId, String role, String firstname, String lastname, String email, String phone,
			String address, String dob) {
		super();
		this.crewId = crewId;
		this.role = role;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.dob = dob;
	}
	
	public Crew(){
		
	}
	private String crewId;
	private String role;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String address;
    private String dob;
	public String getCrewId() {
		return crewId;
	}
	public void setCrewId(String crewId) {
		this.crewId = crewId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
}
