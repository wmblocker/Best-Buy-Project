import java.util.ArrayList;
import java.util.ListIterator;

/**
 * The Class PricesByCategoryIterator.
 */
public class PricesByCategoryIterator implements Iterator {

	/** The prices by category iterator. */
	private static PricesByCategoryIterator pricesByCategoryIterator = null;

	/** The iterator. */
	private ListIterator<Product> iterator = null;

	/** The products. */
	public ArrayList<Product> products = null;

	/**
	 * Instantiates a new prices by category iterator.
	 *
	 * @param category
	 *            the category
	 */
	private PricesByCategoryIterator(ArrayList<Product> category) {
		iterator = category.listIterator();
	}

	/**
	 * Gets the single instance of PricesByCategoryIterator.
	 *
	 * @param category
	 *            the category
	 * @return single instance of PricesByCategoryIterator
	 */
	public static PricesByCategoryIterator getInstance(ArrayList<Product> category) {
		if (pricesByCategoryIterator == null) {
			pricesByCategoryIterator = new PricesByCategoryIterator(category);
		}

		return pricesByCategoryIterator;
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
	 * Gets the prices by category.
	 *
	 * @return the prices by category
	 */
	public ArrayList<Product> getPricesByCategory() {

		while (hasNext()) {
			products.add(next());

		}

		return products;
	}
	public ArrayList<String> getProducts(){
		ArrayList<String> products = new ArrayList<String>();
		return products;
	}

}
