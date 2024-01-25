package com.grocery.service;

import java.util.List;

import com.grocery.entity.Cart;

public interface CartService {

	public boolean addToCart(Cart c);
	public boolean updateQuantity(String cartId, Integer quantity);
	public boolean deleteGroceryItemFromCart(String cartId);
	public boolean clearMyCart(String customerId);
	
	public List<Cart> showMyCart(String custId);
	public List<Cart> showAllCart();
	public Cart getCartById(String cartId);
	public boolean checkGroceryItem(String custId, String groceryId);
}
