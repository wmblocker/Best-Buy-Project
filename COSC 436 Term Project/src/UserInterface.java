import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
	
    public static void main(String[] args) {  
    	
    	User currentUser = User.getInstance();
    	Inventory inventory = Inventory.getInstance();
    	Scanner input = new Scanner(System.in);
		Invoker invoker = new Invoker(new Aggregator());
		SystemInterface.setInvoker(invoker);
    	int option = 0;
    	ArrayList<String> products = new ArrayList<String>();
    	String loop = "n";
		
    	System.out.println("Hello! " + currentUser.getName() + ", Welcome to Best Buy");	
    	
    	do {
    		
			System.out.println("Please select an option");
			System.out.println();
			System.out.println("1. View all items available");
			System.out.println("2. View items in a particular category");
			System.out.println("3. Enter a credit card");
			System.out.println("4. Add an item to your shopping cart");
			System.out.println("5. Display the items currently in shopping cart");
			System.out.println("6. Delete an item from your shopping cart");
			System.out.println("7. Purchase items from shopping cart");
			
			option = input.nextInt();
			
			switch(option){
			
			case 1: //View all items available
				
				products = SystemInterface.getAllItems();
				
				for(int i = 0; i < products.size(); i++ ){
					System.out.println(products.get(i));
				}
				
				break;
				
			case 2: //View items in a particular category
				products = SystemInterface.getProductsByCategory();
				
				for(int i = 0; i < products.size(); i++ ){
					System.out.println(products.get(i));
				}
				
				break;
				
			case 3: //Enter a credit card
				SystemInterface.enterCreditCard();
				break;
			
			case 4: //Add an item to your shopping cart
				System.out.print("Please enter the item 4-digit item number: ");
				String itemNum = input.next();
				
				System.out.print("Please enter the quantity you would like to purchase: ");
				int purchaseQuantity = input.nextInt();
				
				SystemInterface.addItemToCart(itemNum, purchaseQuantity);
				break;
			
			case 5: //Display the items currently in shopping cart
				SystemInterface.displayCart();
				break;
				
			case 6: //Delete an item from your shopping cart
				SystemInterface.displayCart();
				System.out.println("Please enter the corresponding line number (e.g. 0):");
				int index;
				index = input.nextInt();
				
				SystemInterface.deleteItemFromCart(index);
				break;
			
			case 7: //Purchase items from shopping cart
				System.out.println("Are you sure you want to checkout?");
				System.out.print("Enter Y or N: ");
				String ans = input.next();
				
				SystemInterface.purchaseItems();
				break;
				
			default: System.out.println("Command not recognized."); break;
			}
			
			System.out.println("Would you like to return to the main menu? Enter Y or N:");
			loop = input.next();
			
		} while (loop.equals("y") || loop.equals("Y"));
    	
    	System.out.println("Thank you for shopping at Best Buy!");
    	input.close();
    	
    }
 
}