package com.grocery.ui;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import com.grocery.entity.Grocery;
import com.grocery.service.GroceryServiceImpl;


public class GroceryUI {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		String groceryId;
		String groceryName;
	    String type;//veg , non-veg , milk product , other groceries .
		Double price;
		Integer quantityInStock;
		Boolean flag;
		
		Grocery g=null;
		GroceryServiceImpl gimpl=new GroceryServiceImpl();
		List<Grocery> glist=null;
		
		System.out.println("*********Grocery Page**********");
		while(true) {
			
			System.out.println("Enter the number as given in menu:- ");
			System.out.println("Enter 1---------> Add grocery item");
			System.out.println("Enter 2---------> Update grocery item");
			System.out.println("Enter 3---------> Delete grocery item");
			System.out.println("Enter 4---------> Show all grocery items");
			System.out.println("Enter 5---------> Search grocery item on the basis of type");
			
			Integer option=sc.nextInt();
			sc.nextLine();
			
			switch(option) {
			case 1:
				
				
				int min = 100;
				int max = 400;
				int b = (int)(Math.random()*(max-min+1)+min);
				
				
				groceryId="G"+b;
				
				System.out.print("Enter the grocery name: ");
				groceryName=sc.nextLine();
				
				System.out.println("Provide type of this grocery item: ");
				System.out.println("Enter v for veg , nv for non-veg , mp for milk product or other for other groceries: ");
				String t=sc.nextLine();
				
				if(t.equalsIgnoreCase("v")) {
					
					type="veg";
				}
				else if(t.equalsIgnoreCase("nv")) {
					type="non-veg";
				}
				else if(t.equalsIgnoreCase("mp")) {
					type="milk product";
				}
				else if(t.equalsIgnoreCase("other")) {
					type="other groceries";
				}
				else {
					System.out.println("Please enter valid data as per the type");
				    type="data unavailable";
			}
			
			System.out.print("Enter price: ");
			price=sc.nextDouble();
			sc.nextLine();
			
			System.out.print("Quatity in stock: ");
			quantityInStock=sc.nextInt();
			sc.nextLine();
			
			g=new Grocery(groceryId, groceryName, type, price, quantityInStock);
			
			flag=gimpl.addGrocery(g);
			if(flag)
				System.out.println("Grocery details added to database..");
			else
				System.out.println("Error while adding grocery details..");
			break;
				
			case 2:

				System.out.print("Enter the id of grocery you want to update: ");
				groceryId=sc.nextLine();
				
				g=gimpl.getGroceryById(groceryId);
				
				if(g!=null) {
					
					System.out.println(g);
					System.out.println("Are you sure you want to update this grocery item?"+"\nAnswer in yes or no");
					
					String ans=sc.next();
					sc.nextLine();
					
					if(ans.equalsIgnoreCase("yes")) {
						System.out.println("What do you want to update?");
						System.out.println("Enter a---> update name");
						System.out.println("Enter b---> update type");
						System.out.println("Enter c---> update price");
						System.out.println("Enter d---> update quantity");
						
						char choice2=sc.next().toLowerCase().charAt(0);
						sc.nextLine();
		
						
						
						switch(choice2) {
						case 'a':
							System.out.print("Enter name: ");
							groceryName=sc.nextLine();
							g.setGroceryName(groceryName);
							
							flag=gimpl.updateGrocery(g);
							if(flag)
								System.out.println("Grocery name updated successfully!!");
							else
								System.out.println("Error while updating grocery name!!");
							break;
							
						case 'b':
							System.out.println("Provide type of this grocery item: ");
							System.out.print("Enter v for veg , nv for non-veg , mp for milk product or other for other groceries: ");
							t=sc.nextLine();
							
							if(t.equalsIgnoreCase("v")) {
								
								type="veg";
							}
							else if(t.equalsIgnoreCase("nv")) {
								type="non-veg";
							}
							else if(t.equalsIgnoreCase("mp")) {
								type="milk product";
							}
							else if(t.equalsIgnoreCase("other")) {
								type="other groceries";
							}
							else {
								System.out.println("Please Enter v for veg , nv for non-veg , mp for milk product or other for other groceries as per type: ");
							    type="data unavailable";
						}
						g.setType(type);
						
						flag=gimpl.updateGrocery(g);
						if(flag)
							System.out.println("Grocery type updated successfully!!");
						else
							System.out.println("Error while updating grocery type!!");
							break;
							
						case 'c':
							System.out.print("Enter price: ");
							price=sc.nextDouble();
							sc.nextLine();
							
							g.setPrice(price);
                            flag=gimpl.updateGrocery(g);
							
							if(flag)
								System.out.println("Grocery price updated successfully!!");
							else
								System.out.println("Error while updating grocery price!!");

							break;
						
						case 'd':
							System.out.print("Quantity in stock: ");
							quantityInStock=sc.nextInt();
							sc.nextLine();
							
							g.setQuantityInStock(quantityInStock);
							flag=gimpl.updateGrocery(g);
								
								if(flag)
									System.out.println("Grocery quantity updated successfully!!");
								else
									System.out.println("Error while updating grocery quantity!!");

							break;
							
							default:System.out.println("Please enter characters as mentioned in update menu");
						}
					}
					else if(ans.equalsIgnoreCase("no")) {}
					else
						System.out.println("Please answer in yes or no only. Going back to main menu");
					
				}
				else
					System.out.println("No grocery item with this id found. Please check id given....");
				break;
				
			case 3:
				System.out.println("Enter the groceryId of the item you want to delete: ");
				groceryId=sc.nextLine();
				g=gimpl.getGroceryById(groceryId);
				
				if(g!=null) {
					System.out.println("***Grocery Details***");
					System.out.println(g);
					
					System.out.println("Are you sure you want to delete this grocery item?"+"\nAnswer in yes or no");
					
					
					String ans=sc.next();
					sc.nextLine();
					
					if(ans.equalsIgnoreCase("yes")) {
						flag=gimpl.deleteGrocery(groceryId);
						
						if(flag)
							System.out.println("Grocery deleted successfully");
						else
							System.out.println("Error while deleting grocery item");
					}
					else if(ans.equalsIgnoreCase("no")) 
						System.out.println("Thank you continue browsing...");
					else
						System.out.println("Please answer in yes or no only. Going back to main menu..");
				}
				else
					System.out.println("No grocery item with this id found!!!");
				break;
				
			case 4:
				glist=gimpl.showAllGrocery();
				if(glist!=null && glist.isEmpty()!=true) {
					
					Iterator<Grocery> it=glist.iterator();
					
					while(it.hasNext())
						System.out.println(it.next());
					    System.out.println("_________________________________");
				}
				else
					System.out.println("No data available for grocery details at this moment..");
				break;
				
			case 5:
				System.out.println("Provide type of this grocery item: ");
				System.out.print("Enter v for veg , nv for non-veg , mp for milk product or other for other groceries: ");
				t=sc.nextLine();
				
				if(t.equalsIgnoreCase("v")) {
					
					type="veg";
				}
				else if(t.equalsIgnoreCase("nv")) {
					type="non-veg";
				}
				else if(t.equalsIgnoreCase("mp")) {
					type="milk product";
				}
				else if(t.equalsIgnoreCase("other")) {
					type="other groceries";
				}
				else {
					System.out.println("Please Enter v for veg , nv for non-veg , mp for milk product or other for other groceries as per type: ");
				    type="data unavailable";
			}
				List<Grocery> glist1=null;
                 glist1=gimpl.getGroceryByType(type);
                 
                 if(glist1!=null) {
 					System.out.println("***Grocery Details***");
 					System.out.println(glist1);
                 }
                 else
                	 System.out.println("No data available for grocery details at this moment....");
                 
				break;
				
				default:System.out.println("Please enter only those numbers as mentioned in start");
			}
		
		}
}
}