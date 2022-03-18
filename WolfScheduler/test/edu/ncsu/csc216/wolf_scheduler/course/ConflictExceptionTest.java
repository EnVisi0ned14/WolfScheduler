/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the ConflictException class.
 * 
 * @author msabrams
 *
 */
class ConflictExceptionTest {

	/**
	 * Test method for the default ConflictException constructor.
	 */
	@Test
	void testConflictException() {
		
		ConflictException ce = new ConflictException();
		
		assertEquals("Schedule conflict.", ce.getMessage(), "Expected 'Schedule conflict.'");
		
	}

	/**
	 * Tests custom message for the ConflictException constructor.
	 */
	@Test
	public void testConflictExceptionString() {
	    ConflictException ce = new ConflictException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}

}
