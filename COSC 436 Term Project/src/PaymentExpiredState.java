import java.util.Date;
import java.util.Scanner;

public class PaymentExpiredState extends PaymentState {
	
	// Two Constructors
	
	public PaymentExpiredState(CreditCard creditCard){
		super(creditCard);
	}
		
	public PaymentExpiredState(PaymentState source){
		super(source);
	}
	
	// Inherited Methods
	
	@Override
	public void askForPayment(){
		
		System.out.println("Your credit card has expired.");
		
		System.out.println("Would you like to add a new credit card now?");
		System.out.print("Enter Y or N: ");
		
		Scanner scanner = new Scanner(System.in);
		String ans = scanner.next();
		
		if (ans.equals("Y") || ans.equals("y")){
			super.addNewPayment();
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
			
		// #3 Check if card is invalid.
		boolean isInvalid = getContext().trueIfInvalid();
		if (isInvalid){
			getContext().setPaymentState(new PaymentInvalidState(this));
		}
			
		return getContext().getPaymentState();	
		
	}
	
}
