import java.util.ArrayList;

public class Inventory { // Singleton
	
	ArrayList<Product> products = new ArrayList<Product>();
	public static Inventory instance = null;
	
	public static Inventory getInstance(){
		if(instance == null){
			instance = new Inventory();
		}
		return instance;
	}
	
	private Inventory(){
		addItemsToInventory();
	}
	
	// Populate items in our inventory
	public void addItemsToInventory(){
        Product dell = new Product("1406", "Dell computer", 600.00, 300, "desktops"); 
        Product apple = new Product("1602", "Apple laptop", 1000.00, 432, "laptops");
        Product samsung = new Product("1524", "Samsung tablet", 300.00, 250, "tablets");
        Product appleDesktop = new Product("1234", "Apple desktop", 1200.00, 50, "desktops"); 
        Product asus = new Product("1999", "Asus", 650.00, 163, "laptops");
        Product kindle = new Product("1188", "Kindle Fire", 45.00, 499, "tablets");
        products.add(dell);
        products.add(apple);
        products.add(samsung);
        products.add(appleDesktop);
        products.add(asus);
        products.add(kindle);
	}
	
	public ArrayList<Product> getProducts(){
		return products;
	}
	
	// Access product based on its #. Used by commands to retrieve product info.
	public Product getProduct(String productNum){
		boolean exists = false;
		int index = 0;
		
		for(int i=0; i<products.size(); i++){
			if(products.get(i).getItemCode().equals(productNum)){
				index = i;
				exists = true;
			}
		}
		
		if(exists){
			return products.get(index);
		}
		else{
			return null;
		}
		
	}
	
	// Product iterators
	
	/**
	 * Gets the all products iterator.
	 *
	 * @return the all products iterator
	 */
	public Iterator getAllProductsIterator() {
		return AllProductsIterator.getInstance();
	}
	
	/**
	 * Gets the products by category.
	 *
	 * @return the products by category
	 */
	public Iterator getProductsByCategory()
	{
		return ProductsByCategoryIterator.getInstance();
	}
	
	/**
	 * Gets the products by price.
	 *
	 * @return the products by price
	 */
	public Iterator getProductsByPrice()
	{
		return ProductsByPriceIterator.getInstance();
	}
	
	/**
	 * Gets the prices iterator.
	 *
	 * @param category the category
	 * @return the prices iterator
	 */
	public Iterator getPricesIterator(ArrayList<Product> category) {
		return PricesByCategoryIterator.getInstance(category);
	}
	
}
