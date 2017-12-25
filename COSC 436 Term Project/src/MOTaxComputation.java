import java.util.ArrayList;

public class MOTaxComputation extends TaxComputation {
	
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
            double tax = total * .04225;
            return tax;
        }
    }
    
    @Override
    public boolean taxHoliday(ReceiptDate date){
        // MO has tax holiday on computers during 1st weekend in Aug.
        return date.isWeekend() && date.isAugust() && date.isFirstWeek();
    }
    
}
