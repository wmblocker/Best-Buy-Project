
public abstract class InventoryState {
	
	private Product context;
	
	// Two constructors.
	
	public InventoryState(Product product){
		setContext(product);
	}
	public InventoryState(InventoryState source){
		setContext(source.getContext());
	}
	
	// Getter and Setter for Context
	
	public Product getContext(){
		return context;
	}
	public void setContext(Product product){
		context = product;
	}
	
	// Initial Context state is inventory in-stock.
	public static InventoryState setInitialState(Product product){
		return new InventoryInStockState(product);
	}
	
	// Abstract method. Implemented in subclass.
	public InventoryState transitionState(){
		return null;
	}
	
	//Method where differing behavior is encapsulated. Depends on state.
	public void addToCart(Product product, int userID){
		transitionState();
		getContext().addToCart(product, userID);
		transitionState();
	}
	
}
