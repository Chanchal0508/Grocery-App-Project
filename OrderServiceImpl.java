package com.grocery.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.grocery.entity.Order;
import com.grocery.utility.DBConnection;


public class OrderServiceImpl implements OrderService {

	Connection con=null;
    String sql=null;
    PreparedStatement ps=null;
    ResultSet rs=null;

	@Override
	public Order placeOrder(Order o) {
		
		try {
			con=DBConnection.makeConnection();
			sql="insert into Order_6370 values (?,?,?,?,?,?,?)";
			
			ps=con.prepareStatement(sql);
			ps.setString(1, o.getOrderId());
			/*
			 * Converting java LocalDate type to sql Date type
			 */
			ps.setDate(2, Date.valueOf(o.getOrderDate()));
			ps.setString(3, o.getDropLocation());
			/*
			 * Converting java LocalDateTime type to sql TimeStamp type to store date and time
			 */
			ps.setTimestamp(4, Timestamp.valueOf(o.getExpectedDelivery()));
			ps.setString(5, o.getcustId());
			ps.setDouble(6, o.getBillingAmount());
			ps.setString(7, o.getStatus());
			
			int i=ps.executeUpdate();
			if(i>0)
			{
				new CartServiceImpl().clearMyCart(o.getcustId());
				return o;
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
	public boolean cancelOrder(String orderId) {
		try {
			
			con=DBConnection.makeConnection();
			sql="update Order_6370 set status='Cancelled' where orderId=?";
			
			ps=con.prepareStatement(sql);
			ps.setString(1, orderId);
			
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
	public List<Order> showMyOrderHistory(String custId) {
		try {
			
			con=DBConnection.makeConnection();
			sql="select * from order_6370 where custId=?";
			
			ps=con.prepareStatement(sql);
			ps.setString(1, custId);
			
			rs=ps.executeQuery();
			List<Order> olist=new ArrayList<>();
			while(rs.next()) {
				Order o=new Order();
				
				o.setBillingAmount(rs.getDouble("billingAmount"));
				o.setcustId(rs.getString("custId"));
				o.setDropLocation(rs.getString("dropLocation"));
				o.setExpectedDelivery(rs.getTimestamp("expectedDelivery").toLocalDateTime());
				o.setOrderDate(rs.getDate("orderDate").toLocalDate());
				o.setOrderId(rs.getString("orderId"));
				o.setStatus(rs.getString("status"));
				
				olist.add(o);
				
			}
			
			return olist;
					
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
	public Order showOrderById(String orderId) {
		try {
			con=DBConnection.makeConnection();
			sql="select * from order_6370 where orderId=?";
			
			ps=con.prepareStatement(sql);
			ps.setString(1, orderId);
			
			rs=ps.executeQuery();
			if(rs.next()){
                Order o=new Order();
				
				o.setBillingAmount(rs.getDouble("billingAmount"));
				o.setcustId(rs.getString("custId"));
				o.setDropLocation(rs.getString("dropLocation"));
				o.setExpectedDelivery(rs.getTimestamp("expectedDelivery").toLocalDateTime());
				o.setOrderDate(rs.getDate("orderDate").toLocalDate());
				o.setOrderId(rs.getString("orderId"));
				o.setStatus(rs.getString("status"));
				
				return o;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				rs.close();
				con.close();
			}catch(SQLException e) {
				
				e.printStackTrace();
			}
		}
		return null;
	}
}
