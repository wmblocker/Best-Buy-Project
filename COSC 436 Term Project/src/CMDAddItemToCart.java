
public class CMDAddItemToCart implements Command {
	
	private Aggregator agg;
	String productNum;
	Product product;
	int amount = 0;
	User currentUser = User.getInstance();
	
	public CMDAddItemToCart(Aggregator agg, String productNum, int amount){
		this.agg = agg;
		this.productNum = productNum;
		this.amount = amount;
	}
	
	@Override
	public String execute(){
		
		try {
			this.product = agg.getProduct(productNum);
			this.product.setQuantityToPurchase(amount);
			agg.getProduct(productNum).addToCart(product, currentUser.getIdNumber());
			return product.toString();
		}
		catch (Exception e) {
			System.out.println("Item not found. Please try again.");
			return null;
		}
		
	}

}
