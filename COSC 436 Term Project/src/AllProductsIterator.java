import java.util.ArrayList;
import java.util.ListIterator;

/**
	 * The Class AllProductsIterator.
	 */
	public class AllProductsIterator implements Iterator 
	{
		private Inventory inventory = Inventory.getInstance();
		
		/** The all products. */
		private static AllProductsIterator allProducts = null;
		
		/** The iterator. */
		private ListIterator<Product> iterator = null;

		/**
		 * Instantiates a new all products iterator.
		 */
		private AllProductsIterator() 
		{
			iterator = inventory.products.listIterator();
		}

		/**
		 * Gets the single instance of AllProductsIterator.
		 *
		 * @return single instance of AllProductsIterator
		 */
		public static AllProductsIterator getInstance() 
		{
			if (allProducts == null) {
				allProducts = new AllProductsIterator();
			}
			return allProducts;
		}

		/* (non-Javadoc)
		 * @see Iterator#hasNext()
		 */
		public boolean hasNext() 
		{
			boolean retVal = iterator.hasNext();
			return retVal;
		}

		/* (non-Javadoc)
		 * @see Iterator#next()
		 */
		public Product next() 
		{

			return iterator.next();
		}
		
		public ArrayList<String> getAllProducts(){
			ArrayList<String> products = new ArrayList<String>();
			while(hasNext()){
				products.add(iterator.next().toString());
			}
			return products;
		}
		public ArrayList<String> getProducts(){
			ArrayList<String> products = new ArrayList<String>();
			products = getAllProducts();
			return products;
		}
	}