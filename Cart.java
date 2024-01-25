package com.grocery.entity;

public class Cart {

	 private String cartId;
     private String groceryId;
     private String custId;
     private Integer quantity;//the number of grocery item added to cart
     private Double subTotal;//price * quantity
     private Double price;//grocery price 
     private Grocery g;
     
     
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cart(String cartId, String groceryId, String custId, Integer quantity, Double subTotal, Double price) {
		super();
		this.cartId = cartId;
		this.groceryId = groceryId;
		this.custId = custId;
		this.quantity = quantity;
		this.subTotal = subTotal;
		this.price = price;
		
	}
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", groceryId=" + groceryId + ", custId=" + custId + ", quantity="
				+ quantity + ", subTotal=" + subTotal + ", price=" + price + ", g=" + g + "]";
	}
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public String getGroceryId() {
		return groceryId;
	}
	public void setGroceryId(String groceryId) {
		this.groceryId = groceryId;
	}
	public String getcustId() {
		return custId;
	}
	public void setcustId(String custId) {
		this.custId = custId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Grocery getG() {
		return g;
	}
	public void setG(Grocery g) {
		this.g = g;
	}

	
}
