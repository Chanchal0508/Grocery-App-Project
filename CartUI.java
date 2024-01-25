package com.grocery.ui;

import java.util.List;
import java.util.Scanner;
import com.grocery.entity.Cart;
import com.grocery.entity.Customer;
import com.grocery.entity.Grocery;
import com.grocery.service.*;
import com.grocery.service.GroceryServiceImpl;


public class CartUI {

	public static void main(String[] args) {
		
		 String cartId;
	     String groceryId;
	     String custId;
	     Integer quantity;//the number of grocery item added to cart
	     Double subTotal;//price * quantity
	     Double price;//grocery price 
	     Grocery g;
	     int option;
	     Boolean flag;
	     String ans;
	     
	     Scanner sc=new Scanner(System.in);
	     Cart c=null;
	     CartService cimpl=new CartServiceImpl();
	     List<Cart> clist=null;
	     
	     
	       
		while(true) {
			System.out.println("\n\n***************WELCOME TO GROCERY APP*****************");
			System.out.print("\nEnter your username i.e your id: ");
			String username=sc.nextLine().trim();
			
			System.out.print("Enter password: ");
			String password=sc.nextLine();
			
			String login=new LoginUI().login(username, password);
			
			if(login==null)
				System.out.println("Username or password given is incorrect. Please login again");
			
			else if(login.equals("customer")) {
				/*
				 * fetching the customer object from the database by using the 
				 * showCustomerById method of CustomerServiceImpl.
				 */
				Customer cust=new CustomerServiceImpl().showCustomerById(username);
				
				System.out.println("Welcome to our website "+cust.getCustName());
				System.out.println("You have logged in as customer");
				boolean logout=false;
				
				while(logout==false) {
					System.out.println("\nEnter the number as given in options....");
					System.out.println("Enter 1----> Add to cart");
					System.out.println("Enter 2----> Show my cart");
					System.out.println("Enter 3----> Update quantity of grocery item");
					System.out.println("Enter 4----> Delete grocery item from cart");
					System.out.println("Enter 5----> Clear my cart");
					System.out.println("Enter 6----> See order menu");
					System.out.println("Enter 7----> Logout");
					
					option=sc.nextInt();
					sc.nextLine();
					
					switch(option) {
					case 1:
						
						int min=100;
						int max=400;
						int b=(int)(Math.random()*(max-min+1)+min);
						
						cartId="CA"+b;
						
						List<Grocery> glist=new GroceryServiceImpl().showAllGrocery();
						System.out.println("\nGrocery name and grocery id shown below...");
						for(Grocery g1:glist) {
							
							System.out.println(g1.getGroceryName()+" : "+g1.getGroceryId());
						}
						System.out.println("\nEnter the grocery id: ");
						groceryId=sc.nextLine();
						
						custId=username;
						
						flag=cimpl.checkGroceryItem(custId, groceryId);
						
						if(flag)
						   System.out.println("Item was already present in your cart."+" Quantity has been increased by 1");
						
						else {
						
						quantity=1;
						price=new GroceryServiceImpl().getGroceryById(groceryId).getPrice();
						
						subTotal=quantity*price;
						
						c=new Cart(cartId, groceryId, custId, quantity, subTotal, price);
						
						flag=cimpl.addToCart(c);
						if(flag)
							System.out.println("Grocery item added to cart successfully!!!");
						else
							System.out.println("Error while adding grocery item to cart!!!");
						}
						break;
						
					case 2:
						
						custId=username;
						clist=cimpl.showMyCart(custId);
						
						if(clist!=null && clist.isEmpty()!=true) {
							System.out.println("*********Your cart details**********");
							
							Double grandTotal=0.0;
							
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
							
							System.out.println("Do you want to go to order page? Answer in yes or no...");
							ans=sc.nextLine();
							
							if(ans.equalsIgnoreCase("yes")) {
								new OrderUI().orderMenu(custId, clist);
							}
							else if(ans.equalsIgnoreCase("no"))
								System.out.println("Thank you for your input. Continue browsing....");
							else
								System.out.println("Please answer in yes or no only!! Going back to main menu");
						}
						else
							System.out.println("Your cart is empty. Please add to cart first...");
						break;
						
					case 3:
						System.out.println("Enter the cart id: ");
						cartId=sc.nextLine();
						
						c=cimpl.getCartById(cartId);
						if(c!=null) {
							System.out.println("Cart id: "+c.getCartId());
							System.out.println("Item added: "+c.getG().getGroceryName());
							System.out.println("Price of single item: "+c.getPrice());
							System.out.println("Quantity in cart: "+c.getQuantity());
							System.out.println("Subtotal: "+c.getSubTotal());
							
							System.out.println("Enter new quantity: ");
							quantity=sc.nextInt();
							sc.nextLine();
							
							if(quantity>0) {
								
							flag=cimpl.updateQuantity(cartId, quantity);
							
							if(flag)
								System.out.println("Quantity updated successfully!!!");
							else
								System.out.println("Error while changing quantity!!!");
							}
							else
								System.out.println("Please give a number greater than 0 for quantity!!!");
						}
						break;
						
					case 4:
						System.out.print("Enter cartId of item to be deleted: ");
						cartId=sc.nextLine();
						
						System.out.println("Are you sure you want to delete this item?"+"\nAnswer in yes or no");
						ans=sc.nextLine();
						
						if(ans.equalsIgnoreCase("yes")) {
							
							flag=cimpl.deleteGroceryItemFromCart(cartId);
							if(flag)
								System.out.println("Item deleted from cart successfully");
							else
								System.out.println("Error while deleting item...");
						}
						else if(ans.equalsIgnoreCase("no"))
							System.out.println("Thank you for input. Please continue browsing....");
						else
							System.out.println("Answer in yes or no only. Going back to main menu...");
						break;
						
					case 5:
                        custId=username;
						
						System.out.println("Are you sure you want to clear your cart?"+"\nAnswer in yes or no");
						ans=sc.nextLine();
						
						if(ans.equalsIgnoreCase("yes")) {
							
							flag=cimpl.clearMyCart(custId);
							if(flag)
								System.out.println("Your cart is now empty!!!");
							else
								System.out.println("Error while clearing cart...");
						}
						else if(ans.equalsIgnoreCase("no"))
							System.out.println("Thank you for input. Please continue browsing....");
						else
							System.out.println("Answer in yes or no only. Going back to main menu...");
						break;
						
					case 6: 
						custId=username;
						clist=cimpl.showMyCart(custId);
						
						new OrderUI().orderMenu(custId, clist);
						
						break;
						
					case 7:
						logout=true;
						System.out.println("Thank you for using our services. Visit again soon!!!");
						break;
						
						default:System.out.println("Please give input as per the options given");
					}
				}
			}
			else if(login.equals("admin")) {
				
				System.out.println("You have logged in as admin");
				
                boolean logout=false;
				
				while(logout==false) {
				System.out.println("\nEnter the number as given in options....");
				System.out.println("Enter 1----> Show all cart items");
				System.out.println("Enter 2----> Show cart by id");
				System.out.println("Enter 3----> Logout");
				
				option=sc.nextInt();
				sc.nextLine();
				
				switch(option) {
				
				case 1:
					clist=cimpl.showAllCart();
					if(clist!=null && clist.isEmpty()!=true) {
						for(Cart cart: clist)
						{
						System.out.println("Cart id: "+cart.getCartId());
						System.out.println("Customer id: "+cart.getcustId());
						System.out.println("Item added: "+cart.getG().getGroceryName());
						System.out.println("Price of single item: "+cart.getPrice());
						System.out.println("Quantity in cart: "+cart.getQuantity());
						System.out.println("Subtotal: "+cart.getSubTotal());
						
						
				        System.out.println("________________________");
						}
						
					}
					else
						System.out.println("The cart is empty. No data available at this moment!!!");
					break;
					
				case 2:
					System.out.println("Enter the cart id: ");
					cartId=sc.nextLine();
					
					c=cimpl.getCartById(cartId);
					if(c!=null) {
						System.out.println("Cart id: "+c.getCartId());
						System.out.println("Customer id: "+c.getcustId());
						System.out.println("Item added: "+c.getG().getGroceryName());
						System.out.println("Price of single item: "+c.getPrice());
						System.out.println("Quantity in cart: "+c.getQuantity());
						System.out.println("Subtotal: "+c.getSubTotal());
						
					}
					else
						System.out.println("No cart with this id found");
					break;
					
				case 3:
					logout=true;
					System.out.println("Thank you for using our services. Visit again soon!!!");
					break;
					
					default:System.out.println("Please give input as per the options given");
					break;
				}
				}
			}
		}
}
}