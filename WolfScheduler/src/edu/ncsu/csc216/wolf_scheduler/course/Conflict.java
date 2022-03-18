/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * 
 * Conflict interface checks to see if two activities have conflicting times.
 * 
 * @author msabrams
 *
 */
public interface Conflict {

	/**
	 * Checks to see if two activities have conflicting times
	 * 
	 * @param possibleConflictingActivity is the activity which is checked for conflicting times.
	 * 
	 * @throws ConflictException if there is a conflicting time between the two activities.
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
	
}
