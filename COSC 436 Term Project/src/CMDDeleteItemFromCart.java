
public class CMDDeleteItemFromCart implements Command {
	
	private Aggregator agg;
	int indexValue;
	User currentUser = User.getInstance();
	
	public CMDDeleteItemFromCart(Aggregator agg, int indexValue){
		this.agg = agg;
		this.indexValue = indexValue;
	}
	
	@Override
	public String execute(){
		
		try {
			agg.getShoppingCart().removeItem(indexValue);
			return " ";
		}
		catch (Exception e) {
			System.out.println("Input out of bounds. Please try again.");
			return null;
		}
		
	}

}