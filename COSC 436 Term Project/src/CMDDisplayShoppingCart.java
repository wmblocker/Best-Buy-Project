import java.util.ArrayList;

public class CMDDisplayShoppingCart implements Command {
	
	private Aggregator agg;
	
	public CMDDisplayShoppingCart(Aggregator agg){
		this.agg = agg;
	}
	
	@Override
	public String execute(){
		agg.getShoppingCart().printItems();
		return " ";
	}
	
}
