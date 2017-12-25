import java.util.*;
// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
public class User { // Singleton
	
	private static User instance = null;
	
	/** The name. */
	private String name;
	
	/** The prime. */
	private boolean prime; // If true, user has prime membership.
	
	/** The id number. */
	private int idNumber;
	
	/** The id counter. */
	private static int idCounter = 100;
	
	/**
	 * Instantiates a new user.
	 *
	 * @param name the name
	 * @param prime the prime
	 */
	private User(String name, boolean prime){
		this.name = name;
		this.prime = prime;
		this.idNumber = idCounter++;
	}
	
	public static User getInstance(){
		if(instance == null){
			instance = new User("COSC 436", true);
		}
		return instance;
	}
	
	// Getters & Setters

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Checks if is prime.
	 *
	 * @return true, if is prime
	 */
	public boolean isPrime() {
		return prime;
	}

	/**
	 * Sets the prime.
	 *
	 * @param prime the new prime
	 */
	public void setPrime(boolean prime) {
		this.prime = prime;
	}

	/**
	 * Gets the id number.
	 *
	 * @return the id number
	 */
	public int getIdNumber() {
		return idNumber;
	}

	// Prime Membership - Upgrading & Downgrading
	
	/**
	 * Upgrade to prime.
	 *
	 * @return true, if successful
	 */
	public boolean upgradeToPrime() {
		this.prime = true;
		return prime;
	}
	
	/**
	 * Downgrade to non-prime.
	 *
	 * @return true, if successful
	 */
	public boolean downgradeToNonPrime() {
		this.prime = false;
		return prime;
	}

}
