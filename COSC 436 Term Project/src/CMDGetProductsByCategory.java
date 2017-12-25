import java.util.ArrayList;

public class CMDGetProductsByCategory implements Command {
	
	private Aggregator agg;
	private String category;
	
	public CMDGetProductsByCategory(Aggregator agg){
		this.agg = agg;
		this.category = category;
	}
	
	@Override
	public ArrayList<String> execute(){
		
		ArrayList<String> products = agg.getInventory().getProductsByCategory().getProducts();
		
		ArrayList<String> printProducts = new ArrayList<String>();
		for(int i = 0; i < products.size(); i++ ){
			printProducts.add(products.get(i).toString());
		}
		
		return printProducts;
		
	}

}
