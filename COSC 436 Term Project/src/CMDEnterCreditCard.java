import java.util.ArrayList;

public class CMDEnterCreditCard implements Command {
	
	private Aggregator agg;
	
	public CMDEnterCreditCard(Aggregator agg){
		this.agg = agg;
	}
	
	@Override
	public String execute(){
		agg.getCreditCard().askForPayment();
		return agg.getCreditCard().toString();
	}

}
