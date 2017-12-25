
public class InventoryInStockState extends InventoryState {
	
	User currentUser = User.getInstance();
	
	// Two Constructors
	
	public InventoryInStockState(Product product){
		super(product);
	}
				
	public InventoryInStockState(InventoryState source){
		super(source);
	}
	
	// Inherited Methods
	
	@Override
	public void addToCart(Product product, int userID){
		
		ShoppingCart cart = ShoppingCart.getInstance(currentUser.getIdNumber());
		cart.addItem(product);
			
		System.out.println("The " + getContext().getItemName() + " you requested is in stock.");
		
	}
		
	@Override
	public InventoryState transitionState(){
		
		int inventoryAmount = getContext().getInventoryAmount();
		int inventoryRequested = getContext().getQuantityToPurchase();
		
		// #1 Check if product is now out of stock.
		if (inventoryAmount == 0){
			getContext().setInventoryState(new InventoryOutOfStockState(this));
		}
		
		// #2 Check if product only has partial stock.
		if (inventoryAmount > 0 && inventoryAmount < inventoryRequested){
			getContext().setInventoryState(new InventoryPartialStockState(this));
		}
			
		return getContext().getInventoryState();	
			
	}

}
