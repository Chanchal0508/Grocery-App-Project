package com.grocery.ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.grocery.entity.Cart;
import com.grocery.entity.Customer;
import com.grocery.entity.Order;
import com.grocery.service.CustomerServiceImpl;
import com.grocery.service.OrderServiceImpl;



public class OrderUI {

	private String orderId;
	private LocalDate orderDate;
	private String dropLocation;
	private LocalDateTime expectedDelivery;
	private String custId;
	private Double billingAmount;
	private String status;
	boolean flag;
	String ans;
	
	Order o=null;
	OrderServiceImpl oimpl= new OrderServiceImpl();
	List<Order> olist=null;
	
	
	public void orderMenu(String custId, List<Cart> clist) {
		
	   Customer cust=new CustomerServiceImpl().showCustomerById(custId);
	   System.out.println("Welcome to the Order page "+cust.getCustName()+" !!!");
	   
	   int option;
	   Scanner sc=new Scanner(System.in);
	   
	   Double grandTotal=0.0;
		
	   if(clist!=null && clist.isEmpty()!=true) {
		for(Cart cart: clist)
				{
				System.out.println("Cart id: "+cart.getCartId());
				System.out.println("Item added: "+cart.getG().getGroceryName());
				System.out.println("Price of single item: "+cart.getPrice());
				System.out.println("Quantity in cart: "+cart.getQuantity());
				System.out.println("Subtotal: "+cart.getSubTotal());
				
				grandTotal+=cart.getSubTotal();
		        System.out.println("___________________");
				}
				
		System.out.println("Grand Total= "+grandTotal);
	   }
	   else
	   {
		   System.out.println("Your cart is empty and so you will not be able to place order.");
		   System.out.println("Do you want to continue with the order menu??");
		   
		   ans=sc.nextLine();
		   
		   if(ans.equals("no"))
			   return;
		   
		   else if(ans.equals("yes"))
			   System.out.println("Thankyou for input. Please do not place order.\nYou can check other options");
		   
		   else
		   {
			   System.out.println("Please enter yes or no only.....Returning to main menu");
			   return;
		   }
	   }
	   while(true) {
		   
		   System.out.println("\n\nEnter 1-----> Place Order");
		   System.out.println("Enter 2-----> Cancel my Order");
		   System.out.println("Enter 3-----> Show my order history");
		   System.out.println("Enter 4-----> Show order by id");
		   System.out.println("Enter 5-----> Go back to main menu");
		   
		   option=sc.nextInt();
		   sc.nextLine();
		   
		   switch(option) {
		   case 1:
			    int min=100;
				int max=400;
				int b=(int)(Math.random()*(max-min+1)+min);

				orderId="OR"+b;
				
				orderDate=LocalDate.now();
				
				System.out.println("Do you want to deliver at the below given address??");
				System.out.println(cust.getCustAddress());
				System.out.println("\nAnswer in yes or no");
				
				ans=sc.nextLine();
				if(ans.equalsIgnoreCase("yes")) {
					
					dropLocation=cust.getCustAddress();
				}
				else if(ans.equalsIgnoreCase("no")) {
					System.out.println("Enter your drop location:- ");
					dropLocation=sc.nextLine();
				}
				else
					System.out.println("Please answer in yes or no only!!Going back to main menu");
				
				expectedDelivery=LocalDateTime.now().plusHours(1);
				
				billingAmount=grandTotal;
				
				status="Processing...";
				
				o=new Order(orderId , orderDate , dropLocation , expectedDelivery , custId , billingAmount , status);
				
				Order order=oimpl.placeOrder(o);
				if(order!=null) {
					System.out.println("Your order has been placed. Details given below...");
					System.out.println("Order number: "+order.getOrderId());
					System.out.println("Drop Location: "+order.getDropLocation());
					
					DateTimeFormatter pattern=DateTimeFormatter.ofPattern("dd-LLL-yyyy HH:mm");
					LocalDateTime delivery=order.getExpectedDelivery();
					
					System.out.println("Delivered by: "+delivery.format(pattern));
					
					System.out.println("Billing amount: "+order.getBillingAmount());
					System.out.println("Status: "+o.getStatus());
					
					return;
				}
				
				else
					System.out.println("Error while placing order");
			   break;
			   
		   case 2:
			   System.out.println("Enter the order id to be cancelled: ");
			   orderId=sc.nextLine();
			   
			   flag=oimpl.cancelOrder(orderId);
			   
			   if(flag)
				   System.out.println("Your order has been cancelled!!!");
			   else
				   System.out.println("Error while cancelling your order. Try again later...");
			   break;
			   
		   case 3:
			   
			   olist=oimpl.showMyOrderHistory(custId);
			   if(olist!=null && olist.isEmpty()!=true) {
				   
				   for(Order o1:olist) {
					   
					    
						System.out.println("Order number: "+o1.getOrderId());
						System.out.println("Drop Location: "+o1.getDropLocation());
						
						DateTimeFormatter pattern=DateTimeFormatter.ofPattern("dd-LLL-yyyy HH:mm");
						LocalDateTime delivery=o1.getExpectedDelivery();
						
						System.out.println("Delivered by: "+delivery.format(pattern));
						
						System.out.println("Billing amount: "+o1.getBillingAmount());
						System.out.println("Status: "+o1.getStatus());
						
						System.out.println("_______________________________\n");
				   }
			   }
			   break;
			   
		   case 4:
			   System.out.println("Enter the order id: ");
			   orderId=sc.nextLine();
			   
			   o=oimpl.showOrderById(orderId);
			   if(o!=null) {
				    System.out.println("Order number: "+o.getOrderId());
					System.out.println("Drop Location: "+o.getDropLocation());
					
					DateTimeFormatter pattern=DateTimeFormatter.ofPattern("dd-LLL-yyyy HH:mm");
					LocalDateTime delivery=o.getExpectedDelivery();
					
					System.out.println("Delivered by: "+delivery.format(pattern));
					
					System.out.println("Billing amount: "+o.getBillingAmount());
					System.out.println("Status: "+o.getStatus());
					
					System.out.println("_______________________________\n");
			   }
			   else
				   System.out.println("No order found with this id.");
			   break;
			   
		   case 5:
			   System.out.println("\nGoing back to main menu....");
			   return;
			   
			   
			   default:System.out.println("Please enter the number as given in menu...");
		   }
	   }
	}

}
