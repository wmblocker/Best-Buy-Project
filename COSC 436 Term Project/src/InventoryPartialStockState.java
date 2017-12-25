import java.util.Scanner;

public class InventoryPartialStockState extends InventoryState {
	
	User currentUser = User.getInstance();
	
	// Two Constructors
	
	public InventoryPartialStockState(Product product){
		super(product);
	}
						
	public InventoryPartialStockState(InventoryState source){
		super(source);
	}
	
	// Inherited Methods
	
	@Override
	public void addToCart(Product product, int userID){
		
		int amountInStock = getContext().getInventoryAmount();
					
		System.out.println("The " + getContext().getItemName() + " you requested is only partially stock.");
		System.out.println("There are only " + amountInStock + " units in stock.");
		System.out.println("Would you like to add these " + amountInStock + " units to your shopping cart?.");
		System.out.print("Enter Y or N: ");
		
		Scanner scanner = new Scanner(System.in);
		String ans = scanner.next();
		
		if (ans.equals("Y") || ans.equals("y")){
			
			// Update amount requested to match inventory
			product.setQuantityToPurchase(amountInStock);
			
			// Then add to shopping cart
			ShoppingCart cart = ShoppingCart.getInstance(currentUser.getIdNumber());
			cart.addItem(product);
		}
	}
				
	@Override
	public InventoryState transitionState(){
				
		int inventoryAmount = getContext().getInventoryAmount();
		int inventoryRequested = getContext().getQuantityToPurchase();
				
		// #1 Check if product is now in stock.
		if (inventoryAmount > inventoryRequested){
			getContext().setInventoryState(new InventoryInStockState(this));
		}
				
		// #2 Check if product only has partial stock.
		if (inventoryAmount > 0 && inventoryAmount < inventoryRequested){
			getContext().setInventoryState(new InventoryPartialStockState(this));
		}
					
		return getContext().getInventoryState();	
					
	}

}
