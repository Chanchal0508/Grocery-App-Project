package com.grocery.service;

import java.util.List;

import com.grocery.entity.Order;


public interface OrderService {

	public Order placeOrder(Order o);
	public boolean cancelOrder(String orderId);
	
	public List<Order> showMyOrderHistory(String customerId);
	public Order showOrderById(String orderId);
}
