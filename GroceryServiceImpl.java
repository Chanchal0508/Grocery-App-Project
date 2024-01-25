package com.grocery.service;

import java.util.ArrayList;
import java.util.List;
import com.grocery.entity.Grocery;
import com.grocery.utility.DBConnection;
import java.sql.*;

public class GroceryServiceImpl implements GroceryService {
	
	Connection con=null;
    String sql=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
	

	@Override
	public boolean addGrocery(Grocery g) {
		
		try {
			con=DBConnection.makeConnection();
			sql="insert into grocery_6370 values (?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, g.getGroceryId());
			ps.setString(2, g.getGroceryName());
			ps.setString(3, g.getType());
			ps.setDouble(4, g.getPrice());
			ps.setInt(5, g.getQuantityInStock());
			
			int i=ps.executeUpdate();
			if(i>0)
				return true;
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();
				
			}catch(SQLException e) {
				
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean updateGrocery(Grocery g) {
		con=DBConnection.makeConnection();
		sql="update grocery_6370 set groceryName=?, type=?, price=?, quantityInStock=? where groceryId=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, g.getGroceryName());
			ps.setString(2, g.getType());
			ps.setDouble(3, g.getPrice());
			ps.setInt(4, g.getQuantityInStock());
			ps.setString(5, g.getGroceryId());
			
			int i=ps.executeUpdate();
			if(i>0)
				return true;
			}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();
			}catch(SQLException e) {
				
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean deleteGrocery(String groceryId) {
		con=DBConnection.makeConnection();
		sql="delete from grocery_6370 where groceryId=?";
		try{
			ps=con.prepareStatement(sql);
		    ps.setString(1, groceryId);
			
			int i= ps.executeUpdate();
			if(i>0) {
				return true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			}
		finally {
			try {
				ps.close();
				con.close();
			}catch(SQLException e) {
				
				e.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public Grocery getGroceryById(String groceryId) {
		con=DBConnection.makeConnection();
		sql="Select * from grocery_6370 where groceryId=?";
		
		try {
			
			ps=con.prepareStatement(sql);
			ps.setString(1, groceryId);
			
			rs=ps.executeQuery();
			
			if(rs.next()) {
				
				Grocery g=new Grocery();
				g.setGroceryId(rs.getString("groceryId"));
				g.setGroceryName(rs.getString("groceryName"));
				g.setType(rs.getString("type"));
				g.setPrice(rs.getDouble("price"));
				g.setQuantityInStock(rs.getInt("quantityInStock"));
				
				return g;  
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();
			}catch(SQLException e) {
				
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<Grocery> showAllGrocery() {
		try {
			con=DBConnection.makeConnection();
			sql="select * from grocery_6370";
			
			ps=con.prepareStatement(sql);
			
			rs=ps.executeQuery();
			List<Grocery> glist=new ArrayList<Grocery>();
			
			while(rs.next()) {
				
				Grocery g=new Grocery();
				g.setGroceryId(rs.getString("groceryId"));
				g.setGroceryName(rs.getString("groceryName"));
				g.setType(rs.getString("type"));
				g.setPrice(rs.getDouble("price"));
				g.setQuantityInStock(rs.getInt("quantityInStock"));
				
				glist.add(g);
			}
			return glist;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Grocery> getGroceryByType(String type) {
		con=DBConnection.makeConnection();
		sql="Select * from grocery_6370 where type=?";
		
		try {
			
			ps=con.prepareStatement(sql);
			ps.setString(1, type);
			
			rs=ps.executeQuery();
			
			if(rs.next()) {
				
				Grocery g=new Grocery();
				g.setGroceryId(rs.getString("groceryId"));
				g.setGroceryName(rs.getString("groceryName"));
				g.setType(rs.getString("type"));
				g.setPrice(rs.getDouble("price"));
				g.setQuantityInStock(rs.getInt("quantityInStock"));
				
				return  (List<Grocery>) g;  
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();
			}catch(SQLException e) {
				
				e.printStackTrace();
			}
		}
		return null;
	}

}
