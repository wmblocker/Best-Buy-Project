import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TimeZone;

public class CreditCard {
	
	private int userID;
	private String creditCardNumber = null;
	private int expirationMonth;
	private int expirationYear;
	
	// States can include approved, expired, invalid, or no payment on file.
	private PaymentState objState;
	
	// Two Constructors
	
	public CreditCard(){
		objState = PaymentState.setInitialState(this);
	}
	
	public CreditCard(int userID, String creditCardNumber, int expirationMonth, int expirationYear){
		this.userID = userID;
		this.creditCardNumber = creditCardNumber;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		
		objState = PaymentState.setInitialState(this);
		getPaymentState().transitionState();
	}
	
	// Setters
	public void setPaymentState(PaymentState objState){
		this.objState = objState;
	}
	public void setUserID(int userID){
		this.userID = userID;
	}
	public void setCreditCardNumber(String creditCardNumber){
		this.creditCardNumber = creditCardNumber;
	}
	public void setCreditCardExpiration(int expirationMonth, int expirationYear){
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
	}
	
	// Getters
	public PaymentState getPaymentState(){
		return this.objState;
	}
	public int getUserId(){
		return this.userID;
	}
	public String getCreditCardNumber(){
		return this.creditCardNumber;
	}
	public String getExpiration(){
		String month = null;
		switch(this.expirationMonth){
			case 1: month = "Jan"; break;
			case 2: month = "Feb"; break;
			case 3: month = "March"; break;
			case 4: month = "April"; break;
			case 5: month = "May"; break;
			case 6: month = "June"; break;
			case 7: month = "July"; break;
			case 8: month = "Aug"; break;
			case 9: month = "Sept"; break;
			case 10: month = "Oct"; break;
			case 11: month = "Nov"; break;
			case 12: month = "Dec"; break;
			default: month = "Invalid month"; break;
		}
		return month + " " + this.expirationYear;
	}
	
	// To String
	public String toString() {
		boolean noPayment = this.noPaymentOnFile();
		if(noPayment){
			return "No credit card on file.";
		}
		
        return creditCardNumber + " " + this.getExpiration();
    }
	
    // askForPayment() method depends on object state.
    public void askForPayment(){
    	getPaymentState().transitionState();
 		getPaymentState().askForPayment();
 	}
	
	public void addNewPayment(){
			
		Scanner scanner = new Scanner(System.in);
	
		System.out.print("Please enter your credit card number (16 digits): ");
		this.creditCardNumber = scanner.next();
				
		System.out.println("1-Jan\n" + "2-Feb\n" + "3-Mar\n" + "4-Apr\n" + "5-May\n" + "6-Jun\n" +
				"7-July\n" + "8-Aug\n" + "9-Sept\n" + "10-Oct\n" + "11-Nov\n" + "12-Dec\n");
		System.out.print("Please choose the card's expiration month (i.e. 1 for Jan): ");
		
		this.expirationMonth = scanner.nextInt();
		
		System.out.print("Please enter the card's expiration year in this format (yyyy): ");
		this.expirationYear = scanner.nextInt();
		
		System.out.print("Please verify your user ID: ");
		this.userID = scanner.nextInt();
		
		askForPayment(); // Check state again to make sure that new card is valid
			
	}
	
	//If user enters an invalid credit card number.
	public void reenterCardNum(){
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Please reenter your credit card number: ");
		String newCardNum = scanner.next();
		
		this.creditCardNumber = newCardNum;
		
		askForPayment(); // Check state again to make sure that new card num is valid
		
	}
	
	// Four boolean methods to assist states in implementation of transitionState() method.
	
	// #1 Check if no payment. Returns true if NO payment is on file
	public boolean noPaymentOnFile(){
		if (this.creditCardNumber == null){
			return true;
		}
		return false;
	}
	
	
	// #2 Check expiration. Return true if credit card is expired
	public boolean trueifExpired(){
		
    	Calendar todaysDate = Calendar.getInstance(TimeZone.getDefault());
    	
    	// Convert credit card expiration data to type Calendar for comparison
    	Calendar creditCardExpiration = Calendar.getInstance();
    	creditCardExpiration.set(Calendar.YEAR, this.expirationYear);
    	int month = this.expirationMonth - 1; // Jan starts at 0
    	creditCardExpiration.set(Calendar.MONTH, month);
    	creditCardExpiration.set(Calendar.DAY_OF_MONTH, 1);
    	
    	//If difference is positive, that means the card is expired.
		int difference = todaysDate.compareTo(creditCardExpiration);
		
		if(difference > 0){
			return true;
		}
		return false;
	}
	
	
	// #3 Check card validity. Return true if card is invalid.
	public boolean trueIfInvalid(){
	
		int numDigits = this.creditCardNumber.toCharArray().length;
		
	    // Valid credit card should be 16 digits
	    if(numDigits == 16){
	    	return false;
	    }
	    
	    return true;
	    
	}
	
	// #4 Check card approval. Return true if card is approved.
	public boolean trueifApproved(){
		
		if(noPaymentOnFile()){
			return false;
		}
		if(trueifExpired()){
			return false;
		}
		if((trueIfInvalid())){
			return false;
		}
		
		return true;
		
	}

}
