import java.util.ArrayList;

public class CMDGetAllItems implements Command {
	
	private Aggregator agg;
	
	public CMDGetAllItems(Aggregator agg){
		this.agg = agg;
	}
	
	@Override
	public ArrayList<String> execute(){
		return agg.getInventory().getAllProductsIterator().getProducts();
	}
}