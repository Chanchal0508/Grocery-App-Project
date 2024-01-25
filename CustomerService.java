package com.grocery.service;

import com.grocery.entity.Customer;

public interface CustomerService {

	       public boolean addCustomer(Customer c);
	       public boolean updateCustomer(Customer c);
	       
	       public Customer showCustomerById(String custId);
	       public Customer showCustomerByEmail(String custEmail);
}
