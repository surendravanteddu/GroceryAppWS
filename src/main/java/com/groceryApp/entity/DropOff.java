package com.groceryApp.entity;

public class DropOff {
	
	public DropOff(int id, String address) {
		super();
		this.id = id;
		this.address = address;
	}
	
	public DropOff(){

	}
	
	private int id;
	private String address;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
