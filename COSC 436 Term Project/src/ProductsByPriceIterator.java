import java.util.ArrayList;
import java.util.ListIterator;

/**
 * The Class ProductsByPriceIterator.
 */
public class ProductsByPriceIterator implements Iterator {

	private Inventory inventory = Inventory.getInstance();
	
	/** The products by price iterator. */
	private static ProductsByPriceIterator productsByPriceIterator = null;

	/** The iterator. */
	private ListIterator<Product> iterator = null;

	/** The products. */
	public ArrayList<Product> products = null;

	/**
	 * Instantiates a new products by price iterator.
	 */
	private ProductsByPriceIterator() {
		iterator = inventory.products.listIterator();
	}

	/**
	 * Gets the single instance of ProductsByPriceIterator.
	 *
	 * @return single instance of ProductsByPriceIterator
	 */
	public static ProductsByPriceIterator getInstance() {
		if (productsByPriceIterator == null) {
			productsByPriceIterator = new ProductsByPriceIterator();
		}

		return productsByPriceIterator;
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
	 * Gets the products by price category.
	 *
	 * @param price
	 *            the price
	 * @return the products by price category
	 */
	public ArrayList<Product> getProductsByPriceCategory(double price) {
		Product current = null;
		while (hasNext()) {
			current = next();
			if (current.getItemPrice() == price)
				products.add(current);

		}

		return products;
	}
	
	public ArrayList<String> getProducts(){
		ArrayList<String> products = new ArrayList<String>();
		return products;
	}

}