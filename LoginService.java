package com.grocery.service;

public interface LoginService {

	    public boolean customerLogin(String username, String password);

	    public boolean adminLogin(String username, String password);
	    
	    public boolean changeCustPassword(String username, String oldPassword, String newPassword);
	    public boolean changeAdminPassword(String username, String oldPassword, String newPassword);
			
}
