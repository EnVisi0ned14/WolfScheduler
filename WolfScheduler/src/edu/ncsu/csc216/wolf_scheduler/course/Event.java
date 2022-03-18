/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Creates an event object which can be added onto the WolfScheduler's schedule.
 * Contains fields such as event details, title, meeting days, and start/end time.
 * Methods include getters and setters for fields, and overrides isDupliate and
 * get display array to accustom the Event class. Event is a subclass of Activity.
 * 
 * @author michaelabrams
 *
 */
public class Event extends Activity {

	/** Short display count is the number of items in a short display array */
	final static int SHORT_DISPLAY_COUNT = 4;
	
	/** Long display count is the number of items in a long display array */
	final static int LONG_DISPLAY_COUNT = 7;
	
	/** Short display title index is the index of the event's title in a short display array */
	final static int SHORT_DISPLAY_TITLE_INDEX = 2;
	
	/** Short display meeting string index is the index of the event's meeting string in a short display array */
	final static int SHORT_DISPLAY_MEETING_STRING_INDEX = 3;
	
	/** Long display title index is the index of the event's title in a long display array */
	final static int LONG_DISPLAY_TITLE_INDEX = 2;
	
	/** Long display meeting string index is the index of the event's meeting string in a long display array */
	final static int LONG_DISPLAY_MEETING_STRING_INDEX = 5;
	
	/** Long display event details index is the index of the event's event details in a long display array */
	final static int LONG_DISPLAY_EVENT_DETAILS_INDEX = 6;
	
	/** Event's event details */
	private String eventDetails;
	
	/**
	 * Creates an Event object given an event title, meeting days, start and end time, and event details.
	 * 
	 * @param title is the title for the Event.
	 * @param meetingDays are the meetingDays for the event.
	 * @param startTime is the start time for the event.
	 * @param endTime is the end time for the event.
	 * @param eventDetails is the event details for the event.
	 * 
	 * @throws IllegalArgumentException if eventDetails parameter is null, title is null or empty, 
	 * meetingDays is null or empty, or start/end time has invalid times for a standard 24 hour day.
	 */
    public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
    	
        super(title, meetingDays, startTime, endTime);
        
        setEventDetails(eventDetails);
    }
	
	/**
	 * Gets the Event's eventDetails
	 * 
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Sets the Event's eventDetails. If eventDetails is null,
	 * an IllegalArgumentException is thrown.
	 * 
	 * @param eventDetails is the eventDetails to set
	 * 
	 * @throws IllegalArgumentException if eventDetails parameter is invalid
	 */
	public void setEventDetails(String eventDetails) {
		
		if(eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		
		this.eventDetails = eventDetails;
	}

	/**
	 * Returns a short display array of an event object. Implemented as
	 * a required method of the abstract Activities class. Example Output: ["", "", Lunch, W 5:00PM-7:00PM]
	 * 
	 * 
	 * @return a short display array of an event object.
	 */
	@Override
	public String[] getShortDisplayArray() {
		
		String[] shortDisplayEvent = new String[SHORT_DISPLAY_COUNT];
		
		for(int i = 0; i < shortDisplayEvent.length; i++) {
			shortDisplayEvent[i] = "";
		}
		
		shortDisplayEvent[SHORT_DISPLAY_TITLE_INDEX] = getTitle();
		
		shortDisplayEvent[SHORT_DISPLAY_MEETING_STRING_INDEX] = getMeetingString(); 
		
		return shortDisplayEvent;
	}

	/**
	 * Returns a long display array of an event object. Implemented as
	 * a required method of the abstract Activities class.
	 * Example Output: ["", "", Lunch, "", "", W 5:00PM-7:00PM, Lunch with the boys, ""]
	 * 
	 * @return a long display array of an event object.
	 * 
	 */
	@Override
	public String[] getLongDisplayArray() {
		
		String[] longDisplayEvent = new String[LONG_DISPLAY_COUNT];
		
		for(int i = 0; i < longDisplayEvent.length; i++) {
			longDisplayEvent[i] = "";
		}
		
		longDisplayEvent[LONG_DISPLAY_TITLE_INDEX] = getTitle();
		longDisplayEvent[LONG_DISPLAY_MEETING_STRING_INDEX] = getMeetingString();
		longDisplayEvent[LONG_DISPLAY_EVENT_DETAILS_INDEX] = this.eventDetails;
		
		
		return longDisplayEvent;
	}
	
	/**
	 * Sets meeting days and time. Throws IllegalArgumentException if meeting days
	 * is null/empty, contains invalid characters or start/end time is out of bounds. 
	 * Overrides setMeetingDaysAndTime in Activity class to check specific invalid characters for Event objects.
	 * 
	 * @param meetingDays is the meetingDays to set for an event object
	 * @param startTime is the startTime to set for an event object
	 * @param endTime is the endTime to set for an event object
	 * 
	 * @throws IllegalArgumentException if meetingDays, startTime, or endTime are/is invalid.
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		
		if(meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if(meetingDaysHasInvalidCharacters(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		
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
		int saturdayCounter = 0;
		int sundayCounter = 0;
	
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
			} else if (currentCharacter == 'S') {
				saturdayCounter++;
			} else if (currentCharacter == 'U') {
				sundayCounter++;
			} else {
				// Contains an invalid character
				hasInvalidCharacters = true;
			}
		}
	
		if (mondayCounter > 1 || 
				tuesdayCounter > 1 || 
				wednesdayCounter > 1 || 
				thursdayCounter > 1 || 
				fridayCounter > 1 ||
				saturdayCounter > 1 ||
				sundayCounter > 1) {
	
			// Contains duplicate days
			hasInvalidCharacters = true;
	
		}
	
		return hasInvalidCharacters;
	
	}
	

	/**
	 * Creates a string representation of an event object using title, meeting days, and start/end time fields.
	 * 
	 * @return a string representation of an event object.
	 */
	@Override
	public String toString() {
		
		String s = "";
		s += getTitle();
		s += ",";
		s += getMeetingDays();
		s += ",";
		s += getStartTime();
		s += ",";
		s += getEndTime();
		s += ",";
		s += this.eventDetails;
		
		return s;
	}

	/**
	 * Checks to see if an activity is a duplicate of an event object.
	 * An event and activity are duplicate if they are both events and share the same title. 
	 * Implemented as a required method of the abstract Activities class.
	 * 
	 * @param activity is the activity checked for as a duplicate
	 * 
	 * @return true if the activity is a duplicate event, otherwise returns false
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		
		if(activity instanceof Event) {
			
			Event otherEvent = (Event)activity;
			
			if(getTitle().equals(otherEvent.getTitle())) {
				return true;
			}
			
			
		}
		
		return false;
	}
	
	

}
