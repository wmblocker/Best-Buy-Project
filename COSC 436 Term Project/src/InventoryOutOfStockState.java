
public class InventoryOutOfStockState extends InventoryState {
	
	// Two Constructors
	
	public InventoryOutOfStockState(Product product){
		super(product);
	}
					
	public InventoryOutOfStockState(InventoryState source){
		super(source);
	}
	
	// Inherited Methods
	
	@Override
	public void addToCart(Product product, int userID){
				
		System.out.println("The " + getContext().getItemName() + " you requested is out of stock.");
		System.out.println("It has not been added to your shopping cart.");
		
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
