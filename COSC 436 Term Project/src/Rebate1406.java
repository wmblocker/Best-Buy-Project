import java.util.ArrayList;

public class Rebate1406 implements AddOn, Rebate {
	
	User currentUser = User.getInstance();
	ShoppingCart cart = ShoppingCart.getInstance(currentUser.getIdNumber());
    
    @Override
    public boolean applies(ArrayList<Product> items) {
        return cart.containsItem("1406");
    }
    
    @Override
    public String getLines() {
        return "\nMail-in Rebate for Item #1406\n" + "Name:\n" + "Address:\n" +
                "Mail to: Best Buy Rebates, P.O. Box 1400, Orlando, FL";
    }
    
}