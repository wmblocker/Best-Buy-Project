import java.util.ArrayList;

public class Invoker {
	private Command command;
	public Aggregator agg;
	
	public Invoker(Aggregator agg){
		this.agg = agg;
	}
	
	public void setCommand(Command command){
		this.command = command;
	}
	
	public ArrayList<String> getAllItems(){
		command = new CMDGetAllItems(agg);
		return (ArrayList<String>) command.execute();
	}
	
	public ArrayList<String> getProductsByCategory(){
		command = new CMDGetProductsByCategory(agg);
		return (ArrayList<String>) command.execute();
	}
	
	public String enterCreditCard(){
		command = new CMDEnterCreditCard(agg);
		return (String) command.execute();
	}
	
	public String addItemToCart(String productNum, int amount){
		command = new CMDAddItemToCart(agg, productNum, amount);
		return (String) command.execute();
	}
	
	public String displayCart(){
		command = new CMDDisplayShoppingCart(agg);
		return (String) command.execute();
	}
	
	public String deleteItemFromCart(int indexValue){
		command = new CMDDeleteItemFromCart(agg, indexValue);
		return (String) command.execute();
	}
	
	public String purchaseItems(){
		command = new CMDPurchaseItems(agg);
		return (String) command.execute();
	}
	
}