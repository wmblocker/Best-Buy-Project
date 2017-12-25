import java.util.ArrayList;

public class SystemInterface {
	
	private static Invoker invoker;
	
	public static void setInvoker(Invoker inv){
		invoker = inv;
	}
	
	public static ArrayList<String> getAllItems(){
		return invoker.getAllItems();
	}
	
	public static ArrayList<String> getProductsByCategory(){
		return invoker.getProductsByCategory();
	}
	
	public static String enterCreditCard(){
		return invoker.enterCreditCard();
	}
	
	public static String addItemToCart(String productNum, int amount){
		return invoker.addItemToCart(productNum, amount);
	}
	
	public static String displayCart(){
		return invoker.displayCart();
	}
	
	public static String deleteItemFromCart(int indexValue){
		return invoker.deleteItemFromCart(indexValue);
	}
	
	public static String purchaseItems(){
		return invoker.purchaseItems();
	}
	
}