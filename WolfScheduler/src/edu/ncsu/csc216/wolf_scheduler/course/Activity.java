package edu.ncsu.csc216.wolf_scheduler.course;



/**
 * Activity is an abstract class used to create an activity object which can be displayed on the WolfScheduler.
 * Holds fields such as title, meetingDays, and start/end time. Methods include getter and setters
 * for fields, abstract duplicate method for checking if two activities are equal, abstract
 * get short and long display array which formats an activity as a list of strings. Activity 
 * is a parent class for Course and Event.
 * 
 * @author msabrams
 * 
 */
public abstract class Activity implements Conflict {

	/** Maximum hours in a day */
	static final int UPPER_HOUR = 24;
	/** Maximum minutes in a hour */
	static final int UPPER_MINUTES = 60;
	/** Number to divide military time/MOD by in order to retrieve minutes/hour */
	static final int MILITARY_DIVIDER = 100;
	/** Military Minimum PM is the minimum number which determines if the military time is PM or AM */
	static final int MILITARY_PM_MINIMUM = 12;
	/** Upper single digit minute categorizes the minute to see if additional formatting is required */
	static final int UPPER_SINGLE_DIGIT_MINUTE = 10;
	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
	
	
	
	
	/**
	 * Generates a hashCode for Activity using all fields.
	 * 
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * 
	 * @param obj the Object to compare
	 * 
	 * @return true if the objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Creates an Activity object with the given title, meetingDays, startTime, and endTime.
	 * 
	 * @param title is the title of the activity
	 * @param meetingDays is the meetingDays of the activity
	 * @param startTime is the startTime of the activity
	 * @param endTime is the endTime of the activity
	 * 
	 * @throws IllegalArgumentException if title is null or empty, meetingDays is null or empty, or
	 * start/end time has invalid times for a standard 24 hour day.
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Returns the Course's title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title. If the title is null or empty an 
	 * IllegalArgumentException is thrown.
	 * 
	 * @param title is the title to set.
	 * 
	 * @throws IllegalArgumentException if title parameter is invalid.
	 */
	public void setTitle(String title) {
	
		// Throw an exception if title is an empty string
		// Throw an exception if title is null
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
	
		this.title = title;
	}

	/**
	 * Returns the Course's meeting days
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Course's start time
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course's end time
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the Activities's meeting days and time. If meetingDays is null or empty, or
	 * start/end time has out of range times for a standard 24 hour day.
	 * 
	 * @param meetingDays is the meetingDays for the activity to set
	 * @param startTime is the star time for the activity to set
	 * @param endTime is the end time for the activity to set
	 * 
	 * @throws IllegalArgumentException if meetingDays, startTime, or endTime parameters are/is invalid.
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		
		// Throw an exception if endTime is less than startTime
		if (endTime < startTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		int startTimeHour = militaryTimeToHours(startTime);
		int startTimeMinute = militaryTimeToMinutes(startTime);

		int endTimeHour = militaryTimeToHours(endTime);
		int endTimeMinute = militaryTimeToMinutes(endTime);

		// Throw an exception if startTimeHour is invalid
		if (startTimeHour < 0 || startTimeHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		// Throw an exception if startTimeMinute is invalid
		if (startTimeMinute < 0 || startTimeMinute >= UPPER_MINUTES) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		// Throw an exception if endTimeHour is invalid
		if (endTimeHour < 0 || endTimeHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		// Throw an exception if startTimeMinute is invalid
		if (endTimeMinute < 0 || endTimeMinute >= UPPER_MINUTES) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;


	}

	/**
	 * Retrieves the hour from military time and converts it to standard time.
	 * 
	 * @param time is the time in military time
	 * 
	 * @return the hour from military time
	 * 
	 */
	public int militaryTimeToHours(int time) {
	
		return time / MILITARY_DIVIDER;
	
	}

	/**
	 * Retrieves the minutes from military time and converts it to standard time.
	 * 
	 * @param time is the time in military time
	 * 
	 * @return the minutes from military time
	 */
	public int militaryTimeToMinutes(int time) {
		return time % MILITARY_DIVIDER;
	}

	/**
	 * Parses military time into readable string.
	 * Example meetingString: MW 10:00AM-12:00AM
	 * 
	 * @return the meeting time in string format
	 */
	public String getMeetingString() {
	
		// Return Arranged if meeting days is arranged
		if ("A".equals(this.meetingDays)) {
			return "Arranged";
		}
	
		String startTimeOfDayType = "AM";
		String endTimeOfDayType = "AM";
	
		int startTimeHour = militaryTimeToHours(this.startTime);
		int startTimeMinute = militaryTimeToMinutes(this.startTime);
	
		int endTimeHour = militaryTimeToHours(this.endTime);
		int endTimeMinute = militaryTimeToMinutes(this.endTime);
	
		String startTimeHourString;
		String endTimeHourString;
	
		String startTimeMinuteString;
		String endTimeMinuteString;
	
		if(startTimeHour >= MILITARY_PM_MINIMUM) {
			startTimeOfDayType = "PM";
		}
		
		if(endTimeHour >= MILITARY_PM_MINIMUM) {
			endTimeOfDayType = "PM";
		}
		
		if (startTimeHour > MILITARY_PM_MINIMUM) {
			
			startTimeHour -= MILITARY_PM_MINIMUM;
			
			startTimeOfDayType = "PM";
			
		} 
		else if (startTimeHour == 0) {
			startTimeHour = MILITARY_PM_MINIMUM;
			
			startTimeOfDayType = "PM";
			
		}
	
		if (endTimeHour > MILITARY_PM_MINIMUM) {
				
			endTimeHour -= MILITARY_PM_MINIMUM;
			
			endTimeOfDayType = "PM";
			
		} else if (startTimeHour == 0) {
			startTimeHour = MILITARY_PM_MINIMUM;
			
			endTimeOfDayType = "PM";
			
		}
	
		startTimeHourString = Integer.toString(startTimeHour);
		endTimeHourString = Integer.toString(endTimeHour);
	
		startTimeMinuteString = Integer.toString(startTimeMinute);
		endTimeMinuteString = Integer.toString(endTimeMinute);
	
		if (startTimeMinute < UPPER_SINGLE_DIGIT_MINUTE) {
			startTimeMinuteString = "0" + startTimeMinute;
		}
	
		if (endTimeMinute < UPPER_SINGLE_DIGIT_MINUTE) {
			endTimeMinuteString = "0" + endTimeMinute;
		}
	
		String meetingDaysString = "";
		meetingDaysString += this.meetingDays;
		meetingDaysString += " ";
		meetingDaysString += startTimeHourString;
		meetingDaysString += ":";
		meetingDaysString += startTimeMinuteString;
		meetingDaysString += startTimeOfDayType;
		meetingDaysString += "-";
		meetingDaysString += endTimeHourString;
		meetingDaysString += ":";
		meetingDaysString += endTimeMinuteString;
		meetingDaysString += endTimeOfDayType;
	
		return meetingDaysString;
	}
	
	/**
	 * Checks to see if the given activity is a duplicate
	 * 
	 * @param activity is the activity which is checked for duplication
	 * 
	 * @return true if the activity is a duplicate
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	/**
	 * Returns a short description of each activity in a String array.
	 * 
	 * @return a String array giving a short description of an activity.
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * Returns a long description of an activity in a String array.
	 * 
	 * @return a String array giving a long description of an activity.
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Checks to see if there is a time conflict between two Activities. Throws a ConflictException
	 * if start and end time boundaries on any same day conflict, or if the two times overlapping.
	 * 
	 * @param possibleConflictingActivity is the activity which is checked for time conflicts
	 * 
	 * @throws ConflictException if there is a time conflict between the two activities.
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		
		//If the meeting days contain any of the same days, check for time conflict
		if(containsSameDays(this, possibleConflictingActivity)) {
			
			//Grabs the meeting strings of both activities
			int possibleConflictingStartTime = possibleConflictingActivity.startTime;
			int possibleConflictingEndTime = possibleConflictingActivity.endTime;
			
			
			//Checks for conflicting times
			
			if(this.startTime <= possibleConflictingStartTime && this.endTime >= possibleConflictingStartTime) {
				throw new ConflictException();
			}
			else if(this.startTime >= possibleConflictingStartTime && this.startTime <= possibleConflictingEndTime) {
				throw new ConflictException();
			}

		}
		
	}
	
	/**
	 * Checks to see if there are any same days in two given activities.
	 * 
	 * @param a1 is the first activity to be compared against a2
	 * @param a2 is the second activity to be compared against a1
	 * 
	 * @return true if two activities have any day in common, and false if they don't
	 */
	private boolean containsSameDays(Activity a1, Activity a2) {
		
		String meetingStringOne = a1.getMeetingString().split(" ")[0];
		String meetingStringTwo = a2.getMeetingString().split(" ")[0];
		
		if(meetingStringOne.indexOf('M') != -1 && meetingStringTwo.indexOf('M') != -1) {
			return true;
		}
		
		if(meetingStringOne.indexOf('T') != -1 && meetingStringTwo.indexOf('T') != -1) {
			return true;
		}
		
		if(meetingStringOne.indexOf('W') != -1 && meetingStringTwo.indexOf('W') != -1) {
			return true;
		}
		
		if(meetingStringOne.indexOf('H') != -1 && meetingStringTwo.indexOf('H') != -1) {
			return true;
		}
		
		if(meetingStringOne.indexOf('F') != -1 && meetingStringTwo.indexOf('F') != -1) {
			return true;
		}
		
		if(meetingStringOne.indexOf('S') != -1 && meetingStringTwo.indexOf('S') != -1) {
			return true;
		}
		
		return meetingStringOne.indexOf('U') != -1 && meetingStringTwo.indexOf('U') != -1;
		
	}

}