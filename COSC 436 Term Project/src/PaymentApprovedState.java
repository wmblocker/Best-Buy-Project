
public class PaymentApprovedState extends PaymentState {
	
	// Two Constructors
	
	public PaymentApprovedState(CreditCard creditCard){
		super(creditCard);
	}
			
	public PaymentApprovedState(PaymentState source){
		super(source);
	}
	
	// Inherited Methods
	
	@Override
	public void askForPayment(){
		System.out.println("Your credit card is approved. Thank you!");
	}
	
	@Override
	public PaymentState transitionState(){
		
		// #1 Check if card is not on file.
		boolean doesntExist = getContext().noPaymentOnFile();
		if (doesntExist){
			getContext().setPaymentState(new PaymentNotOnFileState(this));
		}
		
		// #2 Check if card is expired.
		boolean isExpired = getContext().trueifExpired();
		if (isExpired){
			getContext().setPaymentState(new PaymentExpiredState(this));
		}
		
		// #3 Check if card is invalid.
		boolean isInvalid = getContext().trueIfInvalid();
		if (isInvalid){
			getContext().setPaymentState(new PaymentInvalidState(this));
		}
		
		return getContext().getPaymentState();	
		
	}
	
}
