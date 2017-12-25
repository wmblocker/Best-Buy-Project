
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BasicReceipt implements Receipt {
    private String storeInfo;       //store number, store address, phone number
    private String stateAbbrev;     // AL, DE, GA, MD, or MO
    User currentUser = User.getInstance();
    private ArrayList<Product> items;
    private Date date;
    private TaxComputation tc;
    ShoppingCart cart = ShoppingCart.getInstance(currentUser.getIdNumber());
    
    public BasicReceipt(ArrayList<Product> items, String storeInfo){
        this.items = items;
        this.storeInfo = storeInfo;
    }
    
    public void setTaxComputation(TaxComputation tc) {
        this.tc = tc;
    }
    
    public void setDate(Date date){
        this.date = date;
    }
    
    @Override
    public void prtReceipt(){
        double totalSales = cart.getTotalCost();
        
        // Convert Date object to Calendar object 
        Calendar cal = ReceiptDate.toCalendar(date);
        // Pass cal to ReceiptDate constructor. Need ReceiptDate obj for computeTax.
        ReceiptDate receiptDate = new ReceiptDate(cal);
        
        double tax = tc.computeTax(items, receiptDate);
        double amountDue = totalSales + tax;
        
        DecimalFormat df = new DecimalFormat("#.00");
        
        System.out.println("\n" + storeInfo);
        System.out.println("Today's Date & Time: " + date + "\n");
        cart.printItems(); System.out.println();
        System.out.println("Total: $" + df.format(totalSales));
        System.out.println("Tax: $" + df.format(tax));
        System.out.println("Balance Due: $" + df.format(amountDue));
    }
    
}
