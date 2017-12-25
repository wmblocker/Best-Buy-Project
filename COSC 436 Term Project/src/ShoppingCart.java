
import java.text.DecimalFormat;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class ShoppingCart.
 */
public class ShoppingCart // Singleton
{
	
	/** The shopping cart. */
	private static ShoppingCart shoppingCart  = null;
	
	/** The user ID. */
	public int userID;
	
	/** The items. */
	public ArrayList<Product> items;
        
    private int index = 0;
    private static double totalCost = 0;
	
	/**
	 * Instantiates a new shopping cart.
	 *
	 * @param id the id
	 */
	private ShoppingCart(int id)
	{
		this.userID = id;
		this.items = new ArrayList();
	}
	
	/**
	 * Gets the single instance of ShoppingCart.
	 *
	 * @param id the id
	 * @return single instance of ShoppingCart
	 */
	public static ShoppingCart getInstance(int id)
	{
		if(shoppingCart == null)
		{
			shoppingCart = new ShoppingCart(id);
		}
		
		return shoppingCart;
	}
        
	public void addItem(Product item){
    	
    	// Update inventory.
    	int purchaseQuantity = item.getQuantityToPurchase();
    	int amountInStock = item.getInventoryAmount();
    	int newNumInStock = amountInStock - purchaseQuantity;
    	item.setInventoryAmount(newNumInStock);
    	
    	// Add item cost to running total
        for (int i = 0; i < purchaseQuantity; i++){
        	double price = item.getItemPrice(); 
            totalCost = totalCost + price;
        }
        
        // Flag to determine whether or not to update existing record in cart.
        boolean notInCart = true; 
        
        // If item is already in cart, just update the total quantity to purchase. 
        // E.g. User adds 2 of item & later adds additional 3 of same item. Update total to 5.
        String name = item.getItemName();
        for (int i = 0; i < items.size(); i++){
        	if (items.get(i).getItemName().equals(name)){
        		int quantity = items.get(i).getQuantityInCart();
        		quantity = quantity + purchaseQuantity;
        		item.setQuantityInCart(quantity);
        		notInCart = false;
        	}
        }
        
        // If item is NOT already in cart, add it to the ArrayList.
        if (notInCart == true){
        	items.add(index, item); 
            index++;
            item.setQuantityInCart(purchaseQuantity);
        }
        
        System.out.println("The item " + item.getItemName() + " was successfully added to your shopping cart.");
        
    }
        
    public void removeItem(int i) {
    	if (items.isEmpty()){
    		System.out.println("Your shopping cart is empty.");
    	}
    	else {
    		String itemName = items.get(i).getItemName();
            items.remove(i);
            System.out.println(itemName + " successfully deleted.");
    	}
   }
        
     public double getTotalCost(){
        return totalCost;
    }
    
    public boolean containsItem(String itemCode){
        for (int i = 0; i < index; i++) {
            Product item = items.get(i);
            String code = item.getItemCode();
            if (code.equals(itemCode)) {
                return true;
            }
        }
        return false; 
    }
    
    public void printItems() {
    	
    	if (items.isEmpty()){
    		System.out.println("Your shopping cart is empty.");
    	}
    	else {
    		System.out.println("Your Shopping Cart Items: ");

            DecimalFormat df = new DecimalFormat("#.00");

            for (int i = 0; i < items.size(); i++) {
                System.out.println("Line " + (i) +"   " + items.get(i).getQuantityInCart() + " " +
              		items.get(i).getItemName() + ", " +
                        "$" + df.format(items.get(i).getItemPrice()) + " each");
            }
                
            System.out.println("Total Cost: $" + shoppingCart.getTotalCost());
    		
    	}
            
    }
    
    public void clearCart(){
    	items.clear();
    }
    
    // To checkout, we call the Receipt Factory Class to print the receipt.
    public void checkout(){
    	ReceiptFactory receipt = new ReceiptFactory();
    	Receipt decoratedReceipt = receipt.getReceipt(items);
    	decoratedReceipt.prtReceipt();
    	
    	shoppingCart.clearCart(); // Delete all items in cart after successful checkout
    }
   
        
}
