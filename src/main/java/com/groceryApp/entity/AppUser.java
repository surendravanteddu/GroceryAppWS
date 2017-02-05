package com.groceryApp.entity;

import  java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class AppUser {
	public AppUser(String username, String firstname, String lastname, String password, String email,
			String phone, String address, String dob, String role) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.role = role;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.dob = dob;
	}
	public AppUser(){
		
	}
	private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String role;
    private String email;
    private String phone;
    private String address;
    private String dob;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
