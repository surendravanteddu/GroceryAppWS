package com.groceryApp.entity;

import java.util.ArrayList;
import java.util.List;

public class Orders {
	
	private int orderId;
    private String customerId;
    private String grocerId;
    private String driverId;
    private String storeMemeberId;
    private int itemCount;
    private int cost;
    private String status;
    private String  dropOffId;
    private String date;
    private List<Item> list = new ArrayList<>();
	
	public Orders(){
		
	}
	
    public Orders(int orderId, String customerId, String grocerId, String driverId, String storeMemeberId,
			int itemCount, int cost, String status, String dropOffId, String date, List<Item> list) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.grocerId = grocerId;
		this.driverId = driverId;
		this.storeMemeberId = storeMemeberId;
		this.itemCount = itemCount;
		this.cost = cost;
		this.status = status;
		this.dropOffId = dropOffId;
		this.date = date;
		this.list = list;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getGrocerId() {
		return grocerId;
	}
	public void setGrocerId(String grocerId) {
		this.grocerId = grocerId;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getStoreMemeberId() {
		return storeMemeberId;
	}
	public void setStoreMemeberId(String storeMemeberId) {
		this.storeMemeberId = storeMemeberId;
	}
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDropOffId() {
		return dropOffId;
	}
	public void setDropOffId(String dropOffId) {
		this.dropOffId = dropOffId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<Item> getList() {
		return list;
	}
	public void setList(List<Item> list) {
		this.list = list;
	}
    
    

}
