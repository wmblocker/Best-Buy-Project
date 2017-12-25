import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

public class PaymentNotOnFileState extends PaymentState {
	
	// Two Constructors
	
	public PaymentNotOnFileState(CreditCard creditCard){
		super(creditCard);
	}
	
	public PaymentNotOnFileState(PaymentState source){
		super(source);
	}
	
	// Inherited Methods
	
	@Override
	public void askForPayment(){
		
		System.out.println("We do not have a credit card on file for you.");
		
		System.out.println("Would you like to enter your credit card now?");
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
		
		// #1 Check if card is approved.
		boolean isApproved = getContext().trueifApproved();
		if (isApproved){
			getContext().setPaymentState(new PaymentApprovedState(this));
		}
		
		// #2 Check if card is expired.
		boolean isExpired = getContext().trueifExpired();
		if (isExpired){
			getContext().setPaymentState(new PaymentExpiredState(this));
		}
		
		// #3 Check if card is invalid (but only if there is a card on file to check).
		boolean doesntExist = getContext().noPaymentOnFile();
		if (doesntExist){
			getContext().setPaymentState(new PaymentNotOnFileState(this));
		}
		else {
			boolean isInvalid = getContext().trueIfInvalid();
			if (isInvalid){
				getContext().setPaymentState(new PaymentInvalidState(this));
			}
		}
		
		return getContext().getPaymentState();	
		
	}
	
}
