import java.util.Scanner;

public class PaymentInvalidState extends PaymentState {
	
	// Two Constructors
	
	public PaymentInvalidState(CreditCard creditCard){
		super(creditCard);
	}
	
	public PaymentInvalidState(PaymentState source){
		super(source);
	}
	
	// Inherited Methods
	
	@Override
	public void askForPayment(){
		
		System.out.println("Your credit card is invalid.");
		System.out.println("Would you like to update your credit card number now?");
		System.out.print("Enter Y or N: ");
		
		Scanner scanner = new Scanner(System.in);
		String ans = scanner.next();
		
		if (ans.equals("Y") || ans.equals("y")){
			super.reenterCardNum();
		}
		else {
			System.out.println("Thank you. You will need to enter a valid credit card before checkout.");
		}
		
	}
	
	@Override
	public PaymentState transitionState(){
		
		// #1 Check if card is not on file.
		boolean doesntExist = getContext().noPaymentOnFile();
		if (doesntExist){
			getContext().setPaymentState(new PaymentNotOnFileState(this));
		}
		
		// #2 Check if card is approved.
		boolean isApproved = getContext().trueifApproved();
		if (isApproved){
			getContext().setPaymentState(new PaymentApprovedState(this));
		}
		
		// #3 Check if card is expired.
		boolean isExpired = getContext().trueifExpired();
		if (isExpired){
			getContext().setPaymentState(new PaymentExpiredState(this));
		}
		
		return getContext().getPaymentState();	
		
	}
	
}
