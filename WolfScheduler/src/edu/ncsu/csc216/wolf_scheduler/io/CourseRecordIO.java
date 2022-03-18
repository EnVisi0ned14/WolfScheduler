package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * CourseRecordIO can read course records from text files.
 * 
 * @author msabrams
 */
public class CourseRecordIO {

		
    /**
     * Reads course records from a file and generates a list of valid Courses. Any invalid
     * Courses are ignored. If the file to read cannot be found or the permissions are incorrect
     * a FileNotFoundException is thrown.
     * 
     * @param fileName file to read Course records from
     * 
     * @return a list of valid Courses
     * 
     * @throws FileNotFoundException if the file cannot be found or read
     * 
     * @throws IllegalArgumentException if a line in the file is invalid b/c 
     * a object course could not be created
     */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		
	    Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
	    
	    ArrayList<Course> courses = new ArrayList<Course>(); //Create an empty array of Course objects
	    while (fileReader.hasNextLine()) { //While we have more lines in the file
	        try { //Attempt to do the following
	            //Read the line, process it in readCourse, and get the object
	            //If trying to construct a Course in readCourse() results in an exception, flow of control will transfer to the catch block, below
	            Course course = readCourse(fileReader.nextLine()); 

	            //Create a flag to see if the newly created Course is a duplicate of something already in the list  
	            boolean duplicate = false;
	            //Look at all the courses in our list
	            for (int i = 0; i < courses.size(); i++) {
	                //Get the course at index i
	                Course current = courses.get(i);
	                //Check if the name and section are the same
	                if (course.getName().equals(current.getName()) &&
	                        course.getSection().equals(current.getSection())) {
	                    //It's a duplicate!
	                    duplicate = true;
	                    break; //We can break out of the loop, no need to continue searching
	                }
	            }
	            //If the course is NOT a duplicate
	            if (!duplicate) {
	                courses.add(course); //Add to the ArrayList!
	            } //Otherwise ignore
	        } catch (IllegalArgumentException e) {
	            //The line is invalid b/c we couldn't create a course, skip it!
	        }
	    }
	    //Close the Scanner b/c we're responsible with our file handles
	    fileReader.close();
	    //Return the ArrayList with all the courses we read!
	    return courses;
	}
	
	/**
	 * Parses a line within a file to return a course object
	 * 
	 * @param line is the line containing the course object
	 * 
	 * @return a course object from the given line
	 * 
	 * @throws IllegalArgumentException if an unexpected item is found within the course line.
	 */
    private static Course readCourse(String line) {
    	
		Scanner lineReader = new Scanner(line);
		
		//Change the delimiter to a comma
		lineReader.useDelimiter(",");
		
		try {
			
			String name = lineReader.next();
			String title = lineReader.next();
			String section = lineReader.next();
			int creditHours = lineReader.nextInt();
			String instructorId = lineReader.next();
			String meetingDays = lineReader.next();
			
			//If class is arranged
			if("A".equals(meetingDays)) {
				
				
				
				//If line has more tokens
				if(lineReader.hasNext()) {
					lineReader.close();
					throw new IllegalArgumentException();
				}
				//else if lines don't have more tokens
				else {
					lineReader.close();
					return new Course(name, title, section, creditHours, instructorId, meetingDays);
				}
			}
			//else
			else {
				int startTime = lineReader.nextInt();
				int endTime = lineReader.nextInt();
				
				
				
				//If line has more tokens
				if(lineReader.hasNext()) {
					lineReader.close();
					throw new IllegalArgumentException();
				}
				
				lineReader.close();
				return new Course(name, title, section, creditHours, instructorId, meetingDays, startTime, endTime);
				
			}
			
			
		}
		catch (Exception e) {
			lineReader.close();
			throw new IllegalArgumentException();
		}
		
	}

	

}
