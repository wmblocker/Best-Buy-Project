
public class CMDPurchaseItems implements Command {
	
private Aggregator agg;
	
	public CMDPurchaseItems(Aggregator agg){
		this.agg = agg;
	}
	
	//If credit card has not been approved yet, do not continue with checkout.
	@Override
	public Object execute() {
		agg.getCreditCard().askForPayment();
		if (agg.getCreditCard().trueifApproved()){
			agg.getShoppingCart().checkout();
		}
		return null;
	}

}
