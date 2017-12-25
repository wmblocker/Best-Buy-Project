import java.util.ArrayList;

public class DETaxComputation extends TaxComputation {
    
    // DE doesn't have tax. Thus always a holiday.
    
    @Override
    public double computeTax(ArrayList<Product> items, ReceiptDate date) {
        return 0;
    }
    
    @Override
    public boolean taxHoliday(ReceiptDate date){
        return true;
    }
    
}
