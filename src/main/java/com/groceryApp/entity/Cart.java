package com.groceryApp.entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	
	private String customerId;
	private List<Item> cart = new ArrayList<>();
	
	public Cart() {
	
	}
	
	public Cart(String customerId, List<Item> cart) {
		super();
		this.customerId = customerId;
		this.cart = cart;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public List<Item> getCart() {
		return cart;
	}
	public void setCart(List<Item> cart) {
		this.cart = cart;
	}
}
