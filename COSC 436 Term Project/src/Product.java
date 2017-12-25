
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Product.
 */
public class Product {
	
    private String itemCode;        // e.g. 3010
    private String itemName; // e.g. Dell Laptop
    private double itemPrice;
    private String itemCategory;
    private int inventoryAmount;
    private int quantityAddingToCart;
    private int quantityInCart;
    
    // States can include in stock, out of stock, or partial stock.
 	private InventoryState objState;
 	
    public Product(String code, String name, double price, int amount, String category) {
        this.itemCode = code;
        this.itemName = name;
        this.itemPrice = price;
        this.inventoryAmount = amount;
        this.itemCategory = category;
        this.quantityAddingToCart = 0;
        this.quantityInCart = 0;
        objState = InventoryState.setInitialState(this);
    }

    // toString Method
    public String toString() {
        return itemCode + " " + itemName + " $" + itemPrice;
    }
    
    // Getters
    public InventoryState getInventoryState(){
		return this.objState;
	}
    public String getItemCode() {
        return this.itemCode;
    }
    public String getItemName() {
        return this.itemName;
    }
    public double getItemPrice() {
        return this.itemPrice;
    }
    public String getItemCategory(){
        return this.itemCategory;
    }
    public int getInventoryAmount() {
        return this.inventoryAmount;
    }
    public int getQuantityToPurchase() {
        return this.quantityAddingToCart;
    }
    public int getQuantityInCart(){
    	return this.quantityInCart;
    }
       
    // Setters
    public void setInventoryState(InventoryState objState){
		this.objState = objState;
	}
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }
    public void setInventoryAmount(int inventoryAmount) {
        this.inventoryAmount = inventoryAmount;
    }
    public void setQuantityToPurchase(int quantityToPurchase) {
        this.quantityAddingToCart = quantityToPurchase;
    }
    public void setQuantityInCart(int quantityInCart){
    	this.quantityInCart = quantityInCart;
    }
    
    // Method for State Pattern
    public void addToCart(Product product, int userID){
    	getInventoryState().transitionState();
    	getInventoryState().addToCart(product, userID);
    }

}
