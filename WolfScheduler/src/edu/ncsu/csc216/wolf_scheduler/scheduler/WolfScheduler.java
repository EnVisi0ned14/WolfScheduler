/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;


/**
 * WolfScheduler Class used for scheduling courses, adding events to schedule,
 * getting courses from catalog, exporting schedules, removing courses from schedule,
 * setting the schedule title, getting the schedule title, presenting activities in short or full descriptions,
 * and adding events to schedule.
 * 
 * 
 * @author msabrams
 *
 */
public class WolfScheduler {
	
	/** Number of columns for a 2D Array of Course objects */
	static final int COLUMNS_FOR_ACTIVITY_OBJECTS = 4;
	
	/** Number of columns for a 2D Array of full activity objects */
	static final int COLUMNS_FOR_FULL_ACTIVITY_OBJECTS = 7;
	
	/** Index for a Course's 2D array containing it's name */
	static final int INDEX_FOR_COURSE_NAME = 0;
	
	/** Index for a Course's 2D array containing it's section */
	static final int INDEX_FOR_COURSE_SECTION = 1;
	
	/** Index for a Course's 2D array containing it's title */
	static final int INDEX_FOR_COURSE_TITLE = 2;
	
	/** Index for a Course's 2D array containing it's credits */
	static final int INDEX_FOR_COURSE_CREDIT = 3;
	
	/** Index for a Course's 2D array containing it's instructorId */
	static final int INDEX_FOR_COURSE_INSTRUCTORID = 4;
	
	/** Index for a Course's 2D array containing it's meeting days */
	static final int INDEX_FOR_COURSE_MEETINGDAYS = 5;
	
	/** Available courses for registration */
	ArrayList<Course> catalog;
	
	/** Courses currently added for on a person's schedule */
	ArrayList<Activity> schedule;
	
	/** Schedule title */
	String title;
	
	/**
	 * Creates a WolfSchedule with a given fileName containing Course objects
	 * 
	 * @param fileName file containing Course objects
	 * 
	 * @throws IllegalArgumentException if file can not be found.
	 */
	public WolfScheduler(String fileName) {
		
		this.schedule = new ArrayList<Activity>();
		
		this.title = "My Schedule";
		
		try {
			this.catalog = CourseRecordIO.readCourseRecords(fileName);
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}
	
	/**
	 * Returns the WolfScheduler's catalog in a 2D String array, 
	 * example: [[CSC, 116, Introduction to Java, MW 10:00AM-12:00AM], [CSC, 226, Discrete Mathematics, TH 5:00PM-7:00PM]]
	 * 
	 * @return the catalog
	 */
	public String[][] getCourseCatalog() {
		
		int courseSize = this.catalog.size();
		
		
		String[][] catalogList = new String[courseSize][COLUMNS_FOR_ACTIVITY_OBJECTS];
		
		if(courseSize == 0) {
			return new String[0][0];
		}
		
        for (int i = 0; i < courseSize; i++) {
            Course c = catalog.get(i);
            catalogList[i] = c.getShortDisplayArray();
        }
		
		return catalogList;
	}

	/**
	 * Returns the WolfScheduler's scheduled activities in a 2D String Array
	 * example: [[CSC, 116, Introduction to Java, MW 10:00AM-12:00AM], ["", "", Lunch, W 5:00PM-7:00PM]]
	 * 
	 * @return the scheduled activities
	 */	
	public String[][] getScheduledActivities() {
		
		int scheduleSize = this.schedule.size();
		
		String[][] scheduleList = new String[scheduleSize][COLUMNS_FOR_ACTIVITY_OBJECTS];
		
		if(scheduleSize == 0) {
			return new String[0][0];
		}
		
		for(int i = 0; i < scheduleSize; i++) {
			Activity a = schedule.get(i);
			scheduleList[i] = a.getShortDisplayArray();
		}
		
		return scheduleList;
	}

	/**
	 * Returns the WolfScheduler's scheduled courses with all information.
	 * Example Output: [[CSC, 116, Introduction to Java, 3, msabrams, MW 10:00AM-12:00AM, ""], 
	 *                  ["", "", Lunch, "", "", W 5:00PM-7:00PM, Lunch with the boys, ""]]
	 * @return the full scheduled activities with all information
	 */
	public String[][] getFullScheduledActivities() {
		
		int scheduleSize = this.schedule.size();
		
		String[][] fullSchedule = new String[scheduleSize][COLUMNS_FOR_FULL_ACTIVITY_OBJECTS];
		
		if(scheduleSize == 0) {
			return new String[0][0];
		}
		
		for(int i = 0; i < scheduleSize; i++) {
			
			Activity a = schedule.get(i);
			
			fullSchedule[i] = a.getLongDisplayArray();
			
		}
		
		return fullSchedule;
	}

	/**
	 * Exports WolfScheduler's schedule to an output file
	 * 
	 * @param fileName the output file name
	 * 
	 * @throws IllegalArgumentException if file can not be saved to
	 */
	public void exportSchedule(String fileName) {
		
		try {
			ActivityRecordIO.writeActivityRecords(fileName, this.schedule);
		}
		catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
		
	}

	/**
	 * Returns a course with a given name and section. Returns
	 * null if no course was found.
	 * 
	 * @param name is the name to query for a course
	 * @param section is the section to query for a course
	 * 
	 * @return course the searched for course, and null if no courses were found
	 */
	public Course getCourseFromCatalog(String name, String section) {
		
		//Current size of the catalog 
		int catalogSize = this.catalog.size();
		
		
		//Search for target course and return if found
		for(int i = 0; i < catalogSize; i++) {
			
			String currentCourseName = this.catalog.get(i).getName();
			String currentCourseSection = this.catalog.get(i).getSection();
			
			if(name.equals(currentCourseName) && section.equals(currentCourseSection)) {
				return this.catalog.get(i);
			}

		}

		// No targeted course was found and returns null
		return null;
	}

	/**
	 * Adds a course to the schedule from catalog
	 * 
	 * @param name is the name of the course to add
	 * @param section is the section of the course to add
	 * 
	 * @return true if the course exists and is added to schedule or
	 * false if the course is not in schedule and can not be added.
	 * 
	 * @throws IllegalArgumentException if a user is already registered to a course they try to add or if
	 * there is a time conflict with the course to add and an activity in schedule.
	 */
	public boolean addCourseToSchedule(String name, String section) {
		
		// Current size of catalog
		int catalogSize = this.catalog.size();
		
		//Search to see if target course exists in catalog
		for(int i = 0; i < catalogSize; i++) {
			
			
			Course currentCourse = this.catalog.get(i);
			
			String currentCourseName = currentCourse.getName();
			String currentCourseSection = currentCourse.getSection();
			
			if(name.equals(currentCourseName) && section.equals(currentCourseSection)) {
				
				
				//Course was found and check to see if course is already in schedule
				
				if(isCourseInSchedule(currentCourse)) {
					throw new IllegalArgumentException("You are already enrolled in " + name);
				}
				
				//Checks for conflicting times
				for (Activity a: schedule) {
					
					try {
						a.checkConflict(currentCourse);
					} catch (ConflictException e) {
						throw new IllegalArgumentException("The course cannot be added due to a conflict.");
						
					}
					
				}

				
				// Course has not been added to schedule and should return true
				
				this.schedule.add(currentCourse);
				
				return true;
			}
			
			
		}
		
		// Course was not able to be found and returns false
		return false;
	}

	
	/**
	  * Checks to see if course is already in schedule
	  * 
	  * @param course is the course to be checked if in schedule
	  * 
	  * @return true if course is in schedule and false if not
	  */
	private boolean isCourseInSchedule(Course course) {
		
		for(int i = 0; i < this.schedule.size(); i++) {
			
			Activity currentScheduleCourse = this.schedule.get(i);
			
			if(currentScheduleCourse.isDuplicate(course)) {
				return true;
			}

		}
		
		return false;
	}

	/**
	 * Removes activity from schedule
	 * 
	 * @param idx is the index of the activity you want to remove from schedule	
	 * 
	 * @return true if course was able/was removed from schedule and
	 * false if course was not able to be removed from schedule.
	 * 
	 * @throws IndexOutOfBoundsException if the index parameter is out of bounds for the schedule ArrayList.
	 */
	public boolean removeActivityFromSchedule(int idx) {
		
		try {
			schedule.remove(idx);
			
			return true;
		}
		catch (IndexOutOfBoundsException e) {
			return false;
		}
		
		
	}
	
	/**
	 * Resets the schedule to an empty course list
	 */
	public void resetSchedule() {
		
		this.schedule = new ArrayList<Activity>();
		
	}
	
	/**
	 * Updates the WolfScheduler's title. If title is null throws an IllegalArgumentException.
	 * 
	 * @param title is the new title for the WolfScheduler
	 * 
	 * @throws IllegalArgumentException if title parameter is invalid.
	 * 
	 */
	public void setScheduleTitle(String title) {
		
		if(title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		
		this.title = title;
		
	}
	
	/**
	 * Returns the WolfScheduler's title
	 * 
	 * @return the WolfScheduler's title
	 */
	public String getScheduleTitle() {

		return this.title;
	}
	
	/**
	 * Adds an event to the schedule
	 * 
	 * @param eventTitle is the title of the event
	 * @param eventMeetingDays is the meeting days of the event
	 * @param eventStartTime is the start time of the event
	 * @param eventEndTime is the end time of the event
	 * @param eventDetails are the details of the event
	 * 
	 * @throws IllegalArgumentException if the event to add is already in the schedule or if there is a time conflict
	 * with the event to add with any activity already in schedule.
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime, String eventDetails) {
		
		Event newEvent = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);
		
		int scheduleSize = schedule.size();
		
		for(int i = 0; i < scheduleSize; i++) {
			
			Activity scheduledActivity = schedule.get(i);
			
			if(newEvent.isDuplicate(scheduledActivity)) {
				throw new IllegalArgumentException("You have already created an event called " + eventTitle);
			}
			
			try {
				scheduledActivity.checkConflict(newEvent);
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The event cannot be added due to a conflict.");
			}
		}
		
		schedule.add(newEvent);
		
	}
}
