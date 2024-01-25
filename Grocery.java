package com.grocery.entity;

public class Grocery {

	private String groceryId;
	private String groceryName;
	private String type;//veg , non-veg , milk product  , other groceries .
	private Double price;
	private Integer quantityInStock;
	
	
	public Grocery() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Grocery(String groceryId, String groceryName, String type, Double price, Integer quantityInStock) {
		super();
		this.groceryId = groceryId;
		this.groceryName = groceryName;
		this.type = type;
		this.price = price;
		this.quantityInStock = quantityInStock;
	}
	
	
	@Override
	public String toString() {
		return "Grocery:- \ngroceryId=" + groceryId + ", \ngroceryName=" + groceryName + ", \ntype=" + type + ", \nprice="
				+ price + ", \nquantityInStock=" + quantityInStock + "\n";
	}
	
	
	public String getGroceryId() {
		return groceryId;
	}
	public void setGroceryId(String groceryId) {
		this.groceryId = groceryId;
	}
	public String getGroceryName() {
		return groceryName;
	}
	public void setGroceryName(String groceryName) {
		this.groceryName = groceryName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantityInStock() {
		return quantityInStock;
	}
	public void setQuantityInStock(Integer quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
}