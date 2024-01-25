package com.grocery.ui;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.grocery.entity.Customer;
import com.grocery.service.CustomerServiceImpl;



public class CustomerUI {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		 String custId;
		 String custName;
         String custPassword;
		 String custEmail;
		 String custContact;         
		 String custAddress;
		 Boolean flag;
		
		Customer c=null;
		CustomerServiceImpl cimpl=new CustomerServiceImpl();
		List<Customer> clist=null;
		
		System.out.println("*********Customer Page**********");
		while(true) {
			
			System.out.println("Enter the number as given in list:- ");
			System.out.println("Enter 1---------> Add customer");
			System.out.println("Enter 2---------> Update customer");
			System.out.println("Enter 3---------> Show customer by Id");
			System.out.println("Enter 4---------> Show customer by email");
			
			Integer option=sc.nextInt();
			sc.nextLine();
			
			switch(option) {
			case 1:
				   
				
				int min = 100;
				int max = 400;
				int b = (int)(Math.random()*(max-min+1)+min);
				
				
				custId="C"+b;
				
	        System.out.print("Enter the customer name: ");
		    custName=sc.nextLine();
				
			System.out.print("Enter the customer password: ");
			custPassword=sc.nextLine();
				
			System.out.println("Enter the customer email:  ");
			custEmail=sc.nextLine();
			
			System.out.print("Enter the customer contact: ");
			custContact=sc.nextLine();
			
			
			System.out.print("Enter the customer address: ");
			custAddress=sc.nextLine();
			
			c=new Customer(custId, custName, custPassword, custEmail, custContact, custAddress);
			
			flag=cimpl.addCustomer(c);  
			if(flag)
				System.out.println("Customer details added to database..");
			else
				System.out.println("Error while adding customer details..");
			break;
				
			case 2:
				System.out.print("Enter the id of customer you want to update: ");
				custId=sc.nextLine();
				
				c=cimpl.showCustomerById(custId);
				
				if(c!=null) {
					
					System.out.println(c);
					System.out.println("Are you sure you want to update this customer details?"+"\nAnswer in yes or no");
					
					String ans=sc.next();
					sc.nextLine();
					
					if(ans.equalsIgnoreCase("yes")) {
						System.out.println("What do you want to update?");
						System.out.println("Enter a---> update name");
						System.out.println("Enter b---> update password");
						System.out.println("Enter c---> update email");
						System.out.println("Enter d---> update contact");
						System.out.println("Enter e---> update address");
						
						char choice2=sc.next().toLowerCase().charAt(0);
						sc.nextLine();
		
						
						
						switch(choice2) {
						case 'a':
							System.out.print("Enter customer name: ");
							custName=sc.nextLine();
							c.setCustName(custName);
							
							flag=cimpl.updateCustomer(c);
							if(flag)
								System.out.println("Customer name updated successfully!!");
							else
								System.out.println("Error while updating customer name!!");
							break;
							
						case 'b':
							System.out.print("Enter the customer password: ");
							custPassword=sc.nextLine();
						    c.setCustPassword(custPassword);
						
						    flag=cimpl.updateCustomer(c);
						    if(flag)
							    System.out.println("Customer password updated successfully!!");
						    else
							    System.out.println("Error while updating customer password!!");
							break;
							
						case 'c':
							System.out.println("Enter the customer email:  ");
							custEmail=sc.nextLine();
							c.setCustEmail(custEmail);
							
							flag=cimpl.updateCustomer(c);
							
							if(flag)
								System.out.print("Customer email updated successfully!!");
							else
								System.out.print("Error while updating customer email!!");

							break;
							
						case 'd':
							System.out.print("Enter the customer contact: ");
							custContact=sc.nextLine();
							
							
							c.setCustContact(custContact);
                           flag=cimpl.updateCustomer(c);
							
							if(flag)
								System.out.println("Customer contact updated successfully!!");
							else
								System.out.println("Error while updating customer contact!!");

							break;
						
						case 'e':
							System.out.print("Enter the customer address: ");
							custAddress=sc.nextLine();
							
							c.setCustAddress(custAddress);
							flag=cimpl.updateCustomer(c);
								
								if(flag)
									System.out.println("Customer address updated successfully!!");
								else
									System.out.println("Error while updating customer address!!");

							break;
							
							default:System.out.println("Please enter characters as mentioned in update list");
						}
					}
					else if(ans.equalsIgnoreCase("no")) {}
					else
						System.out.println("Please answer in yes or no only. Going back to main list");
					
				}
				else
					System.out.println("No customer with this id found. Please check id given....");
				break;
			
			case 3:
				break;
				
			case 4:
				break;
				
				default:System.out.println("Please enter only those numbers as mentioned in start");
			}
		}

	}

}
