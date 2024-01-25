package com.grocery.ui;

import com.grocery.service.LoginServiceImpl;

public class LoginUI {

	public String login(String username, String password) {
		
		
        LoginServiceImpl limpl=new LoginServiceImpl();
		
		
		if(limpl.customerLogin(username, password)) {
			return "customer";
		}
		else if(limpl.adminLogin(username, password)) {
			return "admin";  
		}
		return null;
	
	}

}
