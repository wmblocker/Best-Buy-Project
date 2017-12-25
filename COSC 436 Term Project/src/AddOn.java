import java.util.ArrayList;

// The type of added info to a BasicReceipt (e.g. greetings, rebates, coupons)
public interface AddOn {
    public boolean applies(ArrayList<Product> items);
    public String getLines();
}
