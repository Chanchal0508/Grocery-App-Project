package com.grocery.service;

import java.util.List;
import com.grocery.entity.Grocery;

public interface GroceryService {

	public boolean addGrocery(Grocery g);
	public boolean updateGrocery(Grocery g);
	public boolean deleteGrocery(String groceryId);
	
	public Grocery getGroceryById(String groceryId);
	public List<Grocery> showAllGrocery();
	public List<Grocery> getGroceryByType(String type);

}
