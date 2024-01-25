package com.grocery.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.grocery.entity.Customer;
import com.grocery.utility.DBConnection;

public class CustomerServiceImpl implements CustomerService {
	Connection con=null;
    String sql=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    
	@Override
	public boolean addCustomer(Customer c) {
		try {
			con=DBConnection.makeConnection();
			sql="insert into customer_6370 values (?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, c.getCustId());
			ps.setString(2, c.getCustName());
			ps.setString(3, c.getCustPassword());
			ps.setString(4, c.getCustEmail());
			ps.setString(5, c.getCustContact());
			ps.setString(6, c.getCustAddress());
			
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
	public boolean updateCustomer(Customer c) {
		con=DBConnection.makeConnection();
		sql="update customer_6370 set custName=?, custPassword=?, custEmail=?, custContact=?, custAddress=? where custId=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(6, c.getCustId());
			ps.setString(1, c.getCustName());
			ps.setString(2, c.getCustPassword());
			ps.setString(3, c.getCustEmail());
			ps.setString(4, c.getCustContact());
			ps.setString(5, c.getCustAddress());
			
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
	public Customer showCustomerById(String custId) {
		con=DBConnection.makeConnection();
		sql="Select * from customer_6370 where custId=?";
		
		try {
			
			ps=con.prepareStatement(sql);
			ps.setString(1, custId);
			
			rs=ps.executeQuery();
			
			if(rs.next()) {
				
				Customer c=new Customer();
				c.setCustId(rs.getString("custId"));
				c.setCustName(rs.getString("custName"));
				c.setCustPassword(rs.getString("custPassword"));
				c.setCustEmail(rs.getString("custEmail"));
				c.setCustContact(rs.getString("custContact"));
				c.setCustAddress(rs.getString("custAddress"));
				
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
	public Customer showCustomerByEmail(String custEmail) {
		// TODO Auto-generated method stub
		return null;
	}
}
