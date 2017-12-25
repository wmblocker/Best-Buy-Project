import java.util.ArrayList;

public class MDTaxComputation extends TaxComputation {
	
	private User currentUser = User.getInstance();
	private ShoppingCart cart = ShoppingCart.getInstance(currentUser.getIdNumber());
	
    @Override
    public double computeTax(ArrayList<Product> items, ReceiptDate date) {
        boolean taxHoliday = taxHoliday(date);
        if (taxHoliday == true) {
            return 0;
        }
        else {
            double total = cart.getTotalCost();
            double tax = total * .06;
            return tax;
        }
    }
    
    @Override
    public boolean taxHoliday(ReceiptDate date){
        // Per Wikipedia, MD doesn't have tax holiday for electronics.
        return false;
    }
    
}
