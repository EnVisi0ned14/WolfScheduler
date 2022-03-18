/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * 
 * ConflictException is an exception which is thrown when two activities have conflicting times.
 * 
 * @author msabrams
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * The default constructor for the ConflictException class. 
	 */
	public ConflictException() {
		super("Schedule conflict.");
	}
	
	/**
	 * Constructor for the ConflictException class. Takes a message as a parameter 
	 * for the exception message.
	 * 
	 * @param message is the message for the ConflictException to output
	 */
	public ConflictException(String message) {
		super(message);
	}
	
}
