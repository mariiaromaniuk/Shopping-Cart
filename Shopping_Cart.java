package weekfour;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class ShoppingCart {
	private static double total = 0;

	public static void main(String[] args) {
		LinkedHashMap<String, Item> cart = new LinkedHashMap<String, Item>(16, .75f, true);
		Scanner scn = new Scanner(System.in);

		//While the user is still inputting
		while(true) {
			String name;
			Double price;
			Integer quantity;
			//Get item name
			
			System.out.print("What item are you buying? ");
			name = scn.next();
			
			//exit case
			if(name.equals("quit"))
				break;
			
			//If item is not found in the cart,price need not be updated.
			if(!cart.containsKey(name)) { 
				System.out.print("What is the price of the item? ");
				price = scn.nextDouble();
			}else {
				price = cart.get(name).getPrice();
			}
			
			//Get Item quantity
			System.out.print("What is the quantity of the item? ");
			quantity = scn.nextInt();
			//Create new Item object
			Item newItem = new Item(name, price, quantity);
			//Add item to cart
			add(cart, newItem);
	
			System.out.println("\nUpdated list:");
			printCart(cart);
		}
		System.out.println("\n-----Final cart amount-----");
		printCart(cart);
		
		scn.close();
	}
	//If item is already found in the cart it only increases the quantity. Total
	//price is incremented regardless depending on the item price and quantity.
	private static void add(LinkedHashMap<String, Item> cart, Item newItem) {
		String name = newItem.getName();
		if(cart.containsKey(name)) {
			total += newItem.getPrice()*newItem.getQuantity();
			cart.get(name).updateQuantity(newItem.getQuantity());
		}else {
			cart.put(newItem.getName(), newItem);
			total += newItem.getQuantity()*newItem.getPrice();
		}
	}
	//Prints cart
	private static void printCart(LinkedHashMap<String, Item> cart) {
		cart.forEach((key, value) ->{
			System.out.println("\nItem name: "+ key);
			value.printItem();
		});
		System.out.printf("%nTotal: $%S%n%n", String.format("%.2f", total));
	}
}

class Item{
	private String itemName;
	private Double itemPrice;
	private Integer itemQuantity;

	Item(String name, Double price, Integer quantity){
		itemName = name;
		itemPrice = price;
		itemQuantity = quantity;
	}
	
	void updateQuantity(Integer q) {
		itemQuantity += q;
	}
	
	String getName() {
		return itemName;
	}

	Double getPrice() {
		return itemPrice;
	}

	Integer getQuantity() {
		return itemQuantity;
	}

	void printItem() {
		String result = String.format("%.2f", itemPrice);
		System.out.printf("Item quantity: %d%nItem price: $%S%n", itemQuantity, result);
	}
}

