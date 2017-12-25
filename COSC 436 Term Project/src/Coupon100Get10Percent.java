import java.util.ArrayList;

public class Coupon100Get10Percent implements AddOn, Coupon {
	
	User currentUser = User.getInstance();
	ShoppingCart cart = ShoppingCart.getInstance(currentUser.getIdNumber());
    
    @Override
    public boolean applies(ArrayList<Product> items) {
        if (cart.getTotalCost() >= 100) {
            return true;
        }
        return false; 
    }
    
    @Override
    public String getLines() {
        return "Congratulations! You get 10% off your next purchase.\n" + 
                "Simply bring this coupon with you on your next visit.\n";
    }
    
}
