package com.groceryApp.entity;

public class Item {

	private int itemId;
	private int cost;
	private String itemName;
	private int quantity;

	
	public Item(int itemId, String itemName, int cost, int quantity) {
		super();
		this.itemId = itemId;
		this.cost = cost;
		this.itemName = itemName;
		this.quantity = quantity;
	}
	
	public Item(){
		
	}
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
