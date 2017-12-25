import java.util.Calendar;
import java.util.Scanner;

public abstract class PaymentState {
	
	private CreditCard context;
	
	// Two constructors
	
	public PaymentState(CreditCard creditCard){
		setContext(creditCard);
	}
	public PaymentState(PaymentState source){
		setContext(source.getContext());
	}
	
	// Getter and Setter for Context
	
	public CreditCard getContext(){
		return this.context;
	}
	
	public void setContext(CreditCard creditCard){
		this.context = creditCard;
	}
	
	// Initial Context state is no payment on file.
	public static PaymentState setInitialState(CreditCard creditCard){
		return new PaymentNotOnFileState(creditCard);
	}
	
	// Abstract method. Implemented in subclass.
	public PaymentState transitionState(){
		return null; 
	}
	
	//Method where varying behavior (based on state) is encapsulated. 
	public void askForPayment(){
		transitionState();
		getContext().askForPayment();
		transitionState();
	}
	
	public void addNewPayment(){
		getContext().addNewPayment();
		transitionState();
	}
	
	public void reenterCardNum(){
		getContext().reenterCardNum();
		transitionState();
	}

}
