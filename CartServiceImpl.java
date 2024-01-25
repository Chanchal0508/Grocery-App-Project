package com.grocery.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.grocery.entity.Cart;
import com.grocery.entity.Grocery;
import com.grocery.utility.DBConnection;

public class CartServiceImpl implements CartService {
	 Connection con=null;
     String sql=null;
     PreparedStatement ps=null;
     ResultSet rs=null;

	@Override
	public boolean addToCart(Cart c) {
		con=DBConnection.makeConnection();
		sql="insert into cart_6370 values (?,?,?,?,?,?)";
		try {
			ps =con.prepareStatement(sql);
			ps.setString(1, c.getCartId());
			ps.setString(2, c.getGroceryId());
			ps.setString(3, c.getcustId());
			ps.setInt(4, c.getQuantity());
			ps.setDouble(6, c.getSubTotal());
			ps.setDouble(5, c.getPrice());
			
			int i=ps.executeUpdate();
			if(i>0)
				return true;
		}
		catch(Exception e) {
			
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
	public boolean updateQuantity(String cartId, Integer quantity) {
		try {
			con=DBConnection.makeConnection();
			sql="update cart_6370 set quantity=? where cartId=?";
			
			ps=con.prepareStatement(sql);
			ps.setInt(1, quantity);
			ps.setString(2, cartId);
			
			int i=ps.executeUpdate();
			if(i>0) {
				
				sql="update cart_6370 set subtotal=price*quantity where cartId=?";
				
				ps=con.prepareStatement(sql);
				ps.setString(1, cartId);
				
				i=ps.executeUpdate();
				if(i>0)
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
	public boolean clearMyCart(String custId) {
		try {
			con=DBConnection.makeConnection();
			sql="delete from cart_6370 where custId=?";
			
			ps=con.prepareStatement(sql);
			ps.setString(1, custId);
			
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
	public List<Cart> showMyCart(String custId) {
       try {
			
			con=DBConnection.makeConnection();
			sql="select * from cart_6370 where custId=?";
			
			ps=con.prepareStatement(sql);
			ps.setString(1, custId);
			
			rs=ps.executeQuery();
			List<Cart> clist=new ArrayList<Cart>();
			
			while(rs.next()) {
				
				Cart c=new Cart();
				c.setCartId(rs.getString("cartId"));
				c.setcustId(rs.getString("custId"));
				c.setGroceryId(rs.getString("groceryId"));
				c.setPrice(rs.getDouble("price"));
				c.setQuantity(rs.getInt("quantity"));
				c.setSubTotal(rs.getDouble("subTotal"));
				
				Grocery g=new GroceryServiceImpl().getGroceryById(rs.getString("groceryId"));
				c.setG(g);
				
				clist.add(c);
			}
			
			return clist;  
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
	public List<Cart> showAllCart() {
		try {
			con=DBConnection.makeConnection();
			sql="select * from cart_6370";
			
			ps=con.prepareStatement(sql);
			
			rs=ps.executeQuery();
            List<Cart> clist=new ArrayList<Cart>();
			
			while(rs.next()) {
				
				Cart c=new Cart();
				c.setCartId(rs.getString("cartId"));
				c.setcustId(rs.getString("custId"));
				c.setGroceryId(rs.getString("groceryId"));
				c.setPrice(rs.getDouble("price"));
				c.setQuantity(rs.getInt("quantity"));
				c.setSubTotal(rs.getDouble("subTotal"));
				
				Grocery g=new GroceryServiceImpl().getGroceryById(rs.getString("groceryId"));
				c.setG(g);
				
				clist.add(c);
			}
			
			return clist; 
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
	public Cart getCartById(String cartId) {
         try {
			
			con=DBConnection.makeConnection();
			sql="select * from cart_6370 where cartId=?";
			
			ps=con.prepareStatement(sql);
			ps.setString(1, cartId);
			
			rs=ps.executeQuery();
			
			
			if(rs.next()) {
				
				Cart c=new Cart();
				c.setCartId(rs.getString("cartId"));
				c.setcustId(rs.getString("custId"));
				c.setGroceryId(rs.getString("groceryId"));
				c.setPrice(rs.getDouble("price"));
				c.setQuantity(rs.getInt("quantity"));
				c.setSubTotal(rs.getDouble("subTotal"));
				
				Grocery g=new GroceryServiceImpl().getGroceryById(rs.getString("groceryId"));
				c.setG(g);
				
				return c;
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
	public boolean checkGroceryItem(String custId, String groceryId) {
		try {
			con=DBConnection.makeConnection();
			sql="select * from cart_6370 where custId=? && groceryId=?";
			
			ps=con.prepareStatement(sql);
			ps.setString(1, custId);
			ps.setString(2, groceryId);
			
			rs=ps.executeQuery();
			if(rs.next()) {
				
				int quantity=rs.getInt("quantity");
				quantity++;
				
				String cartId=rs.getString("cartId");
				
			    boolean flag=updateQuantity(cartId,quantity);
			   
			    return flag; 
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
	public boolean deleteGroceryItemFromCart(String cartId) {
		try {
			con=DBConnection.makeConnection();
			sql="delete from cart_6370 where cartId=?";
			
			ps=con.prepareStatement(sql);
			ps.setString(1, cartId);
			
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

}
