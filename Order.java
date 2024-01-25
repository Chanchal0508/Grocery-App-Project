package com.grocery.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Order {

	private String orderId;
	private LocalDate orderDate;
	private String dropLocation;
	private LocalDateTime expectedDelivery;
	private String custId;
	private Double billingAmount;
	private String status;
	
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Order(String orderId, LocalDate orderDate, String dropLocation, LocalDateTime expectedDelivery,
			String custId, Double billingAmount, String status) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.dropLocation = dropLocation;
		this.expectedDelivery = expectedDelivery;
		this.custId = custId;
		this.billingAmount = billingAmount;
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", dropLocation=" + dropLocation
				+ ", expectedDelivery=" + expectedDelivery + ", custId=" + custId + ", billingAmount="
				+ billingAmount + ", status=" + status + "]";
	}
	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public String getDropLocation() {
		return dropLocation;
	}
	public void setDropLocation(String dropLocation) {
		this.dropLocation = dropLocation;
	}
	public LocalDateTime getExpectedDelivery() {
		return expectedDelivery;
	}
	public void setExpectedDelivery(LocalDateTime expectedDelivery) {
		this.expectedDelivery = expectedDelivery;
	}
	public String getcustId() {
		return custId;
	}
	public void setcustId(String custId) {
		this.custId = custId;
	}
	public Double getBillingAmount() {
		return billingAmount;
	}
	public void setBillingAmount(Double billingAmount) {
		this.billingAmount = billingAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	
	}
}

