package CosapiSoft.SARAWebBanking;

/**
 * This type was created in VisualAge.
 */
public class Time {
	public final static long ONE_SECOND = 1000;
	public final static long ONE_MINUTE = 60 * ONE_SECOND;
	public final static long ONE_HOUR = 60 * ONE_MINUTE;
	//
	private int hours = 0;
	private int minutes = 0;
	private int seconds = 0;
	private int milliseconds = 0;
/**
 * Time constructor comment.
 */
public Time() {
	java.util.Calendar cal = java.util.Calendar.getInstance();
	cal.setTime(new java.util.Date());
	setHours(cal.get(java.util.Calendar.HOUR_OF_DAY));
	setMinutes(cal.get(java.util.Calendar.MINUTE));
	setSeconds(cal.get(java.util.Calendar.SECOND));
	setMilliseconds(cal.get(java.util.Calendar.MILLISECOND));
}
/**
 * Construct a Time Object
 *
 * @param hour 0 to 23
 * @param minute 0 to 59
 * @param second 0 to 59
 */
public Time(int hour, int minute, int second) throws IllegalArgumentException {
	this(hour, minute, second, 0);
}
/**
 * Construct a Time Object
 *
 * @param hour 0 to 23
 * @param minute 0 to 59
 * @param second 0 to 59
 * @param millisecond 0 to ....
 */
public Time(int hour, int minute, int second, int millisecond) throws IllegalArgumentException {
	setHours(hour);
	setMinutes(minute);
	setSeconds(second);
	setMilliseconds(millisecond);
}
/**
 * @param time milliseconds since January 1, 1970, 00:00:00 GMT
 */
public Time(long time) {
	setTime(time);
}
/**
 * This method was created in VisualAge.
 * @param time hh:mm:ss
 */
public Time(String time) throws IllegalArgumentException {
	Time t = Time.valueOf(time);
	setTime(t.getTime());
}
/**
 * This method was created in VisualAge.
 * @return int
 * @param t1 com.cosapisoft.sarawebbanking.util.Time
 * @param t2 com.cosapisoft.sarawebbanking.util.Time
 */
public static long diffTime(Time t1, Time t2) {
	/*	long time = t1.getTime() - t2.getTime();
	if (time < 0)
	time = -time;
	return new Time(time);
	*/
	return t1.getTime() - t2.getTime();
}
/**
 * Compares two objects for equality. Returns a boolean that indicates
 * whether this object is equivalent to the specified object. This method
 * is used when an object is stored in a hashtable.
 * @param obj the Object to compare with
 * @return true if these Objects are equal; false otherwise.
 * @see java.util.Hashtable
 */
public boolean equals(Object obj) {
	if (obj instanceof Time) {
		Time t = (Time) obj;
		return (getHours() == t.getHours() && getMinutes() == t.getMinutes() && getSeconds() == t.getSeconds());
	}
	else {
		return super.equals(obj);
	}
}
/**
 * This method was created in VisualAge.
 * @return int
 */
public int getHours() {
	return hours;
}
/**
 * This method was created in VisualAge.
 * @return int
 */
public int getMilliseconds() {
	return milliseconds;
}
/**
 * This method was created in VisualAge.
 * @return int
 */
public int getMinutes() {
	return minutes;
}
/**
 * This method was created in VisualAge.
 * @return int
 */
public int getSeconds() {
	return seconds;
}
/**
 * This method was created in VisualAge.
 * @return long
 */
public long getTime() {
	return getHours() * ONE_HOUR + getMinutes() * ONE_MINUTE + getSeconds() * ONE_SECOND + getMilliseconds();
	
}
/**
 * Generates a hash code for the receiver.
 * This method is supported primarily for
 * hash tables, such as those provided in java.util.
 * @return an integer hash code for the receiver
 * @see java.util.Hashtable
 */
public int hashCode() {
	// Insert code to generate a hash code for the receiver here.
	// This implementation forwards the message to super.  You may replace or supplement this.
	// NOTE: if two objects are equal (equals(Object) returns true) they must have the same hash code
	return super.hashCode();
}
/**
 * Starts the application.
 * @param args an array of command-line arguments
 */
public static void main(java.lang.String[] args) {
	Time t1 = new Time("00:00:59:0");
	// System.out.println("T1 = " + t1.toString());
	Time t2 = new Time("00:00:05:130");
	// System.out.println("T2 = " + t2.toString());
	long t3 = Time.diffTime(t1, t2);
	// System.out.println("Diff (T1 - T2) = " + t3);
	// System.out.println("Sum (T1 + T2) = " + Time.sumTime(t1, t2));
	// System.out.println("Time 1 = " + new Time());
	// System.out.println("Time 2 = " + new Time());
}
/**
 * This method was created in VisualAge.
 * @param hour int
 */
public void setHours(int hour) throws IllegalArgumentException {
	if (hour < 0 || hour > 23)
		throw new IllegalArgumentException("\nIllegalArgumentException : (hour < 0 || hour > 23) -> hour : " + hour);
	this.hours = hour;
}
/**
 * This method was created in VisualAge.
 * @param millisecond int
 */
public void setMilliseconds(int millisecond) throws IllegalArgumentException {
	if (millisecond < 0 || millisecond > 1000)
		throw new IllegalArgumentException("\nIllegalArgumentException : (millisecond < 0 || millisecond > 1000) -> millisecond = " + millisecond);
	this.milliseconds = millisecond;
}
/**
 * This method was created in VisualAge.
 * @param minute int
 */
public void setMinutes(int minute) throws IllegalArgumentException {
	if (minute < 0 || minute > 59)
		throw new IllegalArgumentException("\nIllegalArgumentException : (minute < 0 || minute > 59) -> minute : " + minute);
	this.minutes = minute;
}
/**
 * This method was created in VisualAge.
 * @param second int
 */
public void setSeconds(int second) throws IllegalArgumentException {
	if (second < 0 || second > 59)
		throw new IllegalArgumentException("\nIllegalArgumentException : (second < 0 || second > 59) -> second : " + second);
	this.seconds = second;
}
/**
 *
 */
public void setTime(long time) throws IllegalArgumentException {
	if (time < 0)
		throw new IllegalArgumentException("\nIllegalArgumentException : (time < 0) -> " + time);
	int hour = Integer.parseInt("" + (time / ONE_HOUR));
	setMinutes(Integer.parseInt("" + ((time - hour * ONE_HOUR) / ONE_MINUTE)));
	setSeconds(Integer.parseInt("" + ((time - hour * ONE_HOUR - getMinutes() * ONE_MINUTE) / ONE_SECOND)));
	setMilliseconds(Integer.parseInt("" + (time - hour * ONE_HOUR - getMinutes() * ONE_MINUTE - getSeconds() * ONE_SECOND)));
	while (hour > 23)
		hour -= 24;
	setHours(hour);
}
/**
 * This method was created in VisualAge.
 * @return int
 * @param t1 com.cosapisoft.sarawebbanking.util.Time
 * @param t2 com.cosapisoft.sarawebbanking.util.Time
 */
public static long sumTime(Time t1, Time t2) {
	long time = t1.getTime() + t2.getTime();
	return (time);
}
/**
 * @return a String in hh:mm:ss format
 */
public String toString() {
	int hour = getHours();
	int minute = getMinutes();
	int second = getSeconds();
	String hourString;
	String minuteString;
	String secondString;
	if (hour < 10) {
		hourString = "0" + hour;
	}
	else {
		hourString = Integer.toString(hour);
	}
	if (minute < 10) {
		minuteString = "0" + minute;
	}
	else {
		minuteString = Integer.toString(minute);
	}
	if (second < 10) {
		secondString = "0" + second;
	}
	else {
		secondString = Integer.toString(second);
	}
	return (hourString + ":" + minuteString + ":" + secondString + ":" + milliseconds);
}
/**
 * @param s time in format "hh:mm:ss"
 * @return corresponding Time
 */
public static Time valueOf(String time) throws java.lang.IllegalArgumentException {
	if (time == null)
		throw new java.lang.IllegalArgumentException("\nIllegalArgumentException : null time");
	if (time.length() < 1)
		throw new java.lang.IllegalArgumentException("\nIllegalArgumentException : (time.length() < 1) - > time : " + time);
	try {
		Time t = new Time();
		if (time.length() == 8) {
			java.util.StringTokenizer token = new java.util.StringTokenizer(time, ":");
			if (token.hasMoreElements())
				t.setHours(Integer.parseInt(token.nextElement().toString()));
			if (token.hasMoreElements())
				t.setMinutes(Integer.parseInt(token.nextElement().toString()));
			if (token.hasMoreElements())
				t.setSeconds(Integer.parseInt(token.nextElement().toString()));
			if (token.hasMoreElements())
				t.setMilliseconds(Integer.parseInt(token.nextElement().toString()));
		}
		else {
			if (time.length() == 6) {
				t.setHours(Integer.parseInt(time.substring(0, 2)));
				t.setMinutes(Integer.parseInt(time.substring(2, 4)));
				t.setSeconds(Integer.parseInt(time.substring(4)));
			}
		}
		return t;
	}
	catch (java.lang.NumberFormatException e) {
		throw new java.lang.IllegalArgumentException("\nIllegalArgumentException : time(" + time + ") NO contiene caracteres numéricos -> " + e.getMessage());
	}
}
}