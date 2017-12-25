
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ReceiptFactory {
    
    private final String best_buy_name = "BEST BUY";
    private String store_num;
    private String street_addr;
    private String phone;
    private String storeInfo;
    private String state_code;
    
    private TaxComputation[] taxComputationsObjs; // tax computation objs (one per state)
    private AddOn[] addOns; // secondary header, rebate, and coupon add-ons
    private TaxComputation appropriateTaxCompObj;
    
    public ReceiptFactory() { // constructor
        
        // Populate array of StateComputation objects
        taxComputationsObjs = new TaxComputation[5];
        taxComputationsObjs[0] = new ALTaxComputation();
        taxComputationsObjs[1] = new DETaxComputation();
        taxComputationsObjs[2] = new GATaxComputation();
        taxComputationsObjs[3] = new MDTaxComputation();
        taxComputationsObjs[4] = new MOTaxComputation();
        
        // Populate array of AddOn objects
        addOns = new AddOn[3];
        addOns[0] = new HolidayGreeting();
        addOns[1] = new Rebate1406();
        addOns[2] = new Coupon100Get10Percent();

        // Read config file to assign store information
        String fileName = "c:/Configuration.txt";
        
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            store_num = bufferedReader.readLine();
            street_addr = bufferedReader.readLine();
            phone = bufferedReader.readLine();
            state_code = bufferedReader.readLine();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open Configuration File.");
        }
        catch(IOException ex) {
            System.out.println("Error reading Configuration File.");
        } 
        
        // Store appropriate StateComputation object to be used on all receipts.
        switch (state_code) {
            case "AL": appropriateTaxCompObj = taxComputationsObjs[0]; break;
            case "DE": appropriateTaxCompObj = taxComputationsObjs[1]; break;
            case "GE": appropriateTaxCompObj = taxComputationsObjs[2]; break;
            case "MD": appropriateTaxCompObj = taxComputationsObjs[3]; break;
            case "MO": appropriateTaxCompObj = taxComputationsObjs[4]; break;
        }
        
    }
    
    public Receipt getReceipt(ArrayList<Product> items){
        
        storeInfo = "Store Number: " + store_num + "\n" + 
                "Address: " + street_addr + "\n" + 
                "Phone Number: " + phone + "\n";
        
        //Create the basic Receipt
        BasicReceipt basicReceipt = new BasicReceipt(items, storeInfo);
        
        // Set the current date of the BasicReceipt.
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        basicReceipt.setDate(date);
        
        // Create ReceiptDate object for later tax computation purposes.
        ReceiptDate receiptDate = new ReceiptDate(calendar);
                
        // Attach StateComputation obj to BasicReceipt.
        basicReceipt.setTaxComputation(appropriateTaxCompObj);
        
        Receipt decoratedReceipt = basicReceipt;
        
        boolean applies = false;

        for(int i = 0; i <= 2; i++) {
            applies = addOns[i].applies(items);
            if (applies) { // Determine AddOn type
                
                if (addOns[i] instanceof SecondaryHeading){ // Create PreDecorator
                    PreDecorator preDecorator = new PreDecorator(addOns[i], decoratedReceipt);
                    decoratedReceipt = preDecorator;
                }
                else if (addOns[i] instanceof Rebate) { // Create PostDecorator
                    PostDecorator postDecorator = new PostDecorator(addOns[i], decoratedReceipt);
                    decoratedReceipt = postDecorator;
                }
                else if (addOns[i] instanceof Coupon){ // Create PostDecorator
                    PostDecorator postDecorator = new PostDecorator(addOns[i], decoratedReceipt);
                    decoratedReceipt = postDecorator;
                }
            }
        }
       
        // Return decorated BasicReceipt object as type Receipt.
        return decoratedReceipt;
    }

}
