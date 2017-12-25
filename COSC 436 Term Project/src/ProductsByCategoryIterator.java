import java.util.*;

/**
 * The Class ProductsByCategoryIterator.
 */
public class ProductsByCategoryIterator implements Iterator {

	private Inventory inventory = Inventory.getInstance();;
	
	/** The products by category. */
	private static ProductsByCategoryIterator productsByCategory = null;

	/** The iterator. */
	private ListIterator<Product> iterator = null;

	/** The products. */
	private ArrayList<Product> products = null;

	/**
	 * Instantiates a new products by category iterator.
	 */
	private ProductsByCategoryIterator() {
		iterator = inventory.products.listIterator();
	}

	/**
	 * Gets the single instance of ProductsByCategoryIterator.
	 *
	 * @return single instance of ProductsByCategoryIterator
	 */
	public static ProductsByCategoryIterator getInstance() {
		if (productsByCategory == null) {
			productsByCategory = new ProductsByCategoryIterator();
		}
		return productsByCategory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Iterator#hasNext()
	 */
	public boolean hasNext() {
		boolean retVal = iterator.hasNext();
		return retVal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Iterator#next()
	 */
	public Product next() {

		return iterator.next();
	}

	/**
	 * Gets the products by category.
	 *
	 * @param category
	 *            the category
	 * @return the products by category
	 */
	public ArrayList<String> getProductsByCategory(String category) {
		ArrayList<String> productsByCategory = new ArrayList<String>();
		Product current = null;
		while (hasNext()) {
			current = iterator.next();
			if (current.getItemCategory().equals(category)) {
				productsByCategory.add(current.toString());
			}
		}

		return productsByCategory;
	}
	public ArrayList<String> getProducts(){
    	Scanner input = new Scanner(System.in);
		System.out.println("Please choose the category that you would like to browse: ");
		System.out.println("1. Desktops");
		System.out.println("2. Laptops");
		System.out.println("3. Tablets");
		
		int number = input.nextInt();
		String category = null;
		
		if (number == 1){
			category = "desktops";
		}
		else if (number == 2){
			category = "laptops";
		}
		else if (number == 3){
			category = "tablets";
		}
		else {
			System.out.println("Input is invalid. Please try again.");
		}
		
		System.out.println("The category you chose was " + number + ". " + category);
		
		return getProductsByCategory(category);
	}
}