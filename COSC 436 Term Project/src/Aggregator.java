
public class Aggregator {
	
	private Inventory inventory;
	private CreditCard card;
	private Product product;
	private User currentUser = User.getInstance();
	private ShoppingCart cart = ShoppingCart.getInstance(currentUser.getIdNumber());
	
	public Aggregator(){
		this.inventory = Inventory.getInstance();
		this.card = new CreditCard();
	}
	
	public Inventory getInventory(){
		return this.inventory;
	}
	
	public CreditCard getCreditCard(){
		return this.card;
	}
	
	public Product getProduct(String productNum){
		this.product = inventory.getProduct(productNum);
		return this.product;
	}
	
	public ShoppingCart getShoppingCart(){
		return this.cart;
	}
	
	
}
