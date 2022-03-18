package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Creates a course object which is used for registration, scheduling, etc. on the WolfScheduler. 
 * Contains fields such as meeting days, start and end time, credit hours, course title, course name, 
 * course section, and instructor id. Methods include getters and setters for fields, and overrides isDupliate and
 * get display array to accustom the Course class. Course is a subclass of Activity.
 * 
 * @author msabrams
 *
 */
public class Course extends Activity {

	/** Minimum length for a name */
	static final int MIN_NAME_LENGTH = 5;

	/** Maximum length for a name */
	static final int MAX_NAME_LENGTH = 8;

	/** Minimum letter count for a valid name */
	static final int MIN_LETTER_COUNT = 1;

	/** Maximum letter count for a valid name */
	static final int MAX_LETTER_COUNT = 4;

	/** Digit count is the valid number of digits for a course's name */
	static final int DIGIT_COUNT = 3;

	/** Section's Length is the length for a section */
	static final int SECTION_LENGTH = 3;

	/** Minimum credits for a course */
	static final int MIN_CREDITS = 1;

	/** Maximum credits for a course */
	static final int MAX_CREDITS = 5;
	
	/** Short display count is the number of items in a short display array for a course object */
	static final int SHORT_DISPLAY_COUNT = 4;
	
	/** Long display count is the number of items in a long display array for a course object */
	static final int LONG_DISPLAY_COUNT = 7;

	/** Short display name index is the index for a course's name in a short display array */
	static final int SHORT_DISPLAY_NAME_INDEX = 0;
	
	/** Short display section index is the index for a course's section in a short display array */
	static final int SHORT_DISPLAY_SECTION_INDEX = 1;
	
	/** Short display title index is the index for a course's title in a short display array */
	static final int SHORT_DISPLAY_TITLE_INDEX = 2;
	
	/** Short display meeting string index is the index for a course's meeting string in a short display array */
	static final int SHORT_DISPLAY_MEETING_STRING_INDEX = 3;
	
	/** Long display name index is the index for a course's name in a long display array */
	static final int LONG_DISPLAY_NAME_INDEX = 0;
	
	/** Long display section index is the index for a course's section in a long display array */
	static final int LONG_DISPLAY_SECTION_INDEX = 1;
	
	/** Long display title index is the index for a course's title in a long display array */
	static final int LONG_DISPLAY_TITLE_INDEX = 2;
	
	/** Long display credits index is the index for a course's credits in a long display array */
	static final int LONG_DISPLAY_CREDITS_INDEX = 3;
	
	/** Long display instructor id index is the index for a course's instructor id in a long display array */
	static final int LONG_DISPLAY_INSTRUCTORID_INDEX = 4;
	
	/** Long display meeting string index is the index for a course's meeting string in a long display array */
	static final int LONG_DISPLAY_MEETING_STRING_INDEX = 5;
	
	/** Long display empty string index is the index for a course's empty string in a long display array */
	static final int LONG_DISPLAY_EMPTY_STRING_INDEX = 6;
	

	/** Course's name. */
	private String name;

	/** Course's section. */
	private String section;

	/** Course's credit hours */
	private int credits;

	/** Course's instructor id */
	private String instructorId;


	
	
	/**
	 * Generates a hashCode for Course using all fields.
	 * 
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + ","
				+ getStartTime() + "," + getEndTime();
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         is the name for the course
	 * @param title        is the title of the course
	 * @param section      is the section of the course
	 * @param credits      is the credits of the course
	 * @param instructorId is the instructor's id who teaches the course
	 * @param meetingDays  is the meeting days for the course
	 * 
	 * @throws IllegalArgumentException if name is null, has a length less than 5 or more than 8,
	 * does not contain a space between letter characters and number characters, has less than 1
	 * or more than 4 letter characters, and not exactly three trailing digit characters, section is 
	 * null/empty, the section does not have a length of three, or contains non-digit characters, 
	 * credits parameter is less than MIN_CREDITS or greater than MAX_CREDITS, instructor id is null or empty,
	 * meetingDays is null/empty, contains invalid characters, or title is null or empty.
	 * 
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Constructs a Course object with values for all fields.
	 * 
	 * @param name         is the name for the course
	 * @param title        is the title of the course
	 * @param section      is the section of the course
	 * @param credits      is the credits of the course
	 * @param instructorId is the instructor's id who teaches the course
	 * @param meetingDays  is the meeting days for the course
	 * @param startTime    is the time when the course starts
	 * @param endTime      is the time when the course ends
	 * 
	 * @throws IllegalArgumentException if name is null, has a length less than 5 or more than 8,
	 * does not contain a space between letter characters and number characters, has less than 1
	 * or more than 4 letter characters, and not exactly three trailing digit characters, section is 
	 * null/empty, the section does not have a length of three, or contains non-digit characters, 
	 * credits parameter is less than MIN_CREDITS or greater than MAX_CREDITS, instructor id is null or empty,
	 * meetingDays is null/empty, contains invalid characters, or start/end time has invalid times for a standard 24 hour day,
	 * or title is null or empty.
	 * 
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);

	}

	/**
	 * Returns the Course's name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name.  If the name is null, has a length less than 5 or more than 8,
	 * does not contain a space between letter characters and number characters, has less than 1
	 * or more than 4 letter characters, and not exactly three trailing digit characters, an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param name is the name to set
	 * 
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {
		// Throw exception if the name is null
		if (name == null)
			throw new IllegalArgumentException("Invalid course name.");

		// Throw exception if the name is an empty string
		// Throw exception if the name contains less than 5 character of greater than 8
		// character
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH)
			throw new IllegalArgumentException("Invalid course name.");

		// Check for pattern of L[LLL] NNN

		int numberOfLetters = 0;
		int numberOfDigits = 0;
		boolean foundSpace = false;

		for (int i = 0; i < name.length(); i++) {
			if (!foundSpace) {
				if (Character.isLetter(name.charAt(i))) {
					numberOfLetters++;
				} else if (" ".equals(String.valueOf(name.charAt(i)))) {
					foundSpace = true;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			} else if (foundSpace) {
				if (Character.isDigit(name.charAt(i))) {
					numberOfDigits++;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			}
		}

		// Check that the number of letters is correct
		if (numberOfLetters < MIN_LETTER_COUNT || numberOfLetters > MAX_LETTER_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		// Check that the number of digits is correct
		if (numberOfDigits != DIGIT_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		this.name = name;

	}

	/**
	 * Returns the Course's section
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section. If the section is null/empty,
	 * the section does not have a length of three, or contains non-digit characters 
	 * an IllegalArgumentException is thrown.
	 * 
	 * @param section is the section to set
	 * 
	 * @throws IllegalArgumentException if section parameter is invalid.
	 */
	public void setSection(String section) {

		// Throw an exception if section is null
		// Throw an exception if section does not have three characters
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}

		// Throw an exception if any of section's three characters are not digits
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}

		this.section = section;
	}

	/**
	 * Returns the Course's credit hours
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return this.credits;
	}

	/**
	 * Sets the Course's credit hours. If the credits parameter is less than MIN_CREDITS or 
	 * greater than MAX_CREDITS an IllegalArgumentException is thrown.
	 * 
	 * @param credits is the credits to set
	 * 
	 * @throws IllegalArgumentException if credits parameter is invalid.
	 */
	public void setCredits(int credits) {

		// Throw an exception if credits is out of bounds
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}

		this.credits = credits;
	}

	/**
	 * Returns the Course's instructor id
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructor id. If instructor id is null or empty an 
	 * IllegalArgumentException is thrown.
	 * 
	 * @param instructorId is the instructorId to set
	 * 
	 * @throws IllegalArgumentException if instructorId parameter is invalid.
	 */
	public void setInstructorId(String instructorId) {

		// Throw an exception if instructorId is null
		// Throw an exception if instructorId is an empty string
		if (instructorId == null || "".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}

		this.instructorId = instructorId;
	}

	/**
	 * Returns a short display array of a Course's name, section, title, and meeting string.
	 * Implemented as a required method for the abstract Activities class.
	 * 
	 * @return a short display array of a course object
	 */
	@Override
	public String[] getShortDisplayArray() {
		
		
		String[] shortDisplayCourse = new String[SHORT_DISPLAY_COUNT];
		
		shortDisplayCourse[SHORT_DISPLAY_NAME_INDEX] = this.name;
		
		shortDisplayCourse[SHORT_DISPLAY_SECTION_INDEX] = this.section;
		
		shortDisplayCourse[SHORT_DISPLAY_TITLE_INDEX] = getTitle();
		
		shortDisplayCourse[SHORT_DISPLAY_MEETING_STRING_INDEX] = getMeetingString();
		
		
		return shortDisplayCourse;
	}

	/**
	 * Returns a long display array of a Course's name, section, title, meeting string,
	 * credits, and instructor id. Implemented as a required method for the abstract 
	 * Activities class. Example: [CSC, 116, Introduction to Java, 3, msabrams, MW 10:00AM-12:00AM, ""]
	 * 
	 * @return a long display array of a course object
	 */
	@Override
	public String[] getLongDisplayArray() {
		
		String[] longDisplayCourse = new String[LONG_DISPLAY_COUNT];
		
		longDisplayCourse[LONG_DISPLAY_NAME_INDEX] = this.name;
		longDisplayCourse[LONG_DISPLAY_SECTION_INDEX] = this.section;
		longDisplayCourse[LONG_DISPLAY_TITLE_INDEX] = getTitle();
		longDisplayCourse[LONG_DISPLAY_CREDITS_INDEX] = Integer.toString(this.credits);
		longDisplayCourse[LONG_DISPLAY_INSTRUCTORID_INDEX] = this.instructorId;
		longDisplayCourse[LONG_DISPLAY_MEETING_STRING_INDEX] = getMeetingString();
		longDisplayCourse[LONG_DISPLAY_EMPTY_STRING_INDEX] = "";
		
		return longDisplayCourse;
	}
	
	/**
	 * Sets meeting days and time. Throws IllegalArgumentException if meeting days
	 * is null/empty, contains invalid characters, or start/end time is/are invalid. Overrides 
	 * setMeetingDaysAndTime in Activity class to check for specific invalid characters for Course objects.
	 * 
	 * @param meetingDays is the meetingDays which is set for a course
	 * @param startTime is the startTime which is set for a course
	 * @param endTime is the endTime which is set for a course
	 * 
	 * @throws IllegalArgumentException if meetingDays, startTime, endTime is/are invalid.
	 */
	@Override 
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		
		if(meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if("A".equals(meetingDays)) {
			
			// Throw an exception if startTime is not 0
			// Throw an exception if endTime is not 0
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
			
		}
		else {
			
			if(meetingDaysHasInvalidCharacters(meetingDays)) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
			
		}
	}
	
	/**
	 * Parses meeting days for invalid characters
	 * 
	 * @param meetingDays is the meetingDays which need to be checked
	 * 
	 * @return true if meetingDays has invalid characters and false if no invalid characters are found.
	 */
	public boolean meetingDaysHasInvalidCharacters(String meetingDays) {
	
		boolean hasInvalidCharacters = false;
	
		// Create counters for weekdays
		int mondayCounter = 0;
		int tuesdayCounter = 0;
		int wednesdayCounter = 0;
		int thursdayCounter = 0;
		int fridayCounter = 0;
	
		for (int i = 0; i < meetingDays.length(); i++) {
	
			char currentCharacter = meetingDays.charAt(i);
	
			if (currentCharacter == 'M') {
				mondayCounter++;
			} else if (currentCharacter == 'T') {
				tuesdayCounter++;
			} else if (currentCharacter == 'W') {
				wednesdayCounter++;
			} else if (currentCharacter == 'H') {
				thursdayCounter++;
			} else if (currentCharacter == 'F') {
				fridayCounter++;
			} else {
				// Contains an invalid character
				hasInvalidCharacters = true;
			}
		}
	
		if (mondayCounter > 1 || tuesdayCounter > 1 || wednesdayCounter > 1 || thursdayCounter > 1
				|| fridayCounter > 1) {
	
			// Contains duplicate days
			hasInvalidCharacters = true;
	
		}
	
		return hasInvalidCharacters;
	
	}
	
	/**
	 * Checks to see if two courses are duplicates. A course and an activity are
	 * duplicates if the activity is a course object and shares the same
	 * name. Implemented as a required method of the abstract Activities class.
	 * 
	 * @param activity is the activity which is checked as a duplicate
	 * 
	 * @return true if the course and activity are duplicates, and false otherwise.
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if(activity instanceof Course) {
			Course otherCourse = (Course)activity;
			
			if(name.equals(otherCourse.getName())) {
				return true;
			}
			
		}
		
		return false;
	}
}
