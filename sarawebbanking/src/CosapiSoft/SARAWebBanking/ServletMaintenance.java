package CosapiSoft.SARAWebBanking;

/**
 * ServletMaintenance.java
 * Matt Tucker
 * CoolServlets.com
 * June 10, 1999
 * Version 1.0.2
 *
 *    Copyright (C) 1999  Matt Tucker
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Library General Public
 *    License as published by the Free Software Foundation; either
 *    version 2 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Library General Public License for more details.
 *
 *    You should have received a copy of the GNU Library General Public
 *    License along with this library; if not, write to the
 *    Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 *    Boston, MA  02111-1307, USA.
 */

/**
 * A thread that helps a servlet perform time based actions. This system
 * is useful for doing things such as saving the servlet's state or performing
 * other periodic tasks.
 * <p>
 * The user of this class specifies a number of minutes and hours to wait
 * before invoking the doMaintenance() function of the passed in
 * MaintainedServlet object.
 * <p>
 * For example, a servlet should "implement ServletMaintenace" and then have
 * a doMainenance() method that contains actions that should be performed
 * periodically. Then, the servlet should define a ServletMaintenance object
 * as something like:
 * <p><pre>
 * ServletMaintenance timer = new ServletMaintenance(this,30,ServletMaintenance.MINUTES);
 * </pre>
 * <p>
 * This starts up a timer that will call doMaintenance() every thirty minutes.
 * When the thread needs to be stopped, the shutDown() method should be called.
 *
 * @author Matt Tucker
 * @version 1.0.2
 */
public class ServletMaintenance extends Thread {
  /**
   * Constant representing seconds.
   */
  public static final int SECONDS = 0;
  /**
   * Constant representing minutes.
   */
  public static final int MINUTES = 1;
  /**
   * Constant representing hours.
   */
  public static final int HOURS = 2;
  /**
   * Constant representing days.
   */
  public static final int DAYS = 3;

  private MaintainedServlet servletReference;
  private boolean done = false;
  private int secondsToWait;
  private int minutesToWait;
  private int hoursToWait;
  private int daysToWait;
  //A series of time constants to make the source more readable.
  //They are measured in milleseconds.
  private final long SECOND = 1000;
  private final long MINUTE = SECOND*60;
  private final long HOUR = MINUTE*60;
  private final long DAY = HOUR*24;
/**
   * Class users must pass in a servlet that implements the MaintainedServlet
   * interface as well as a length of time that this thread
   * will wait during each interval. Note, after calling the constructor, the
   * thread will automatically start running.
   * <p>
   * The third argument to the constructor should be one of the following:
   * <ul><li>ServletMaintenance.SECONDS
   *     <li>ServletMaintenance.MINUTES
   *     <li>ServletMaintenance.HOURS
   *     <li>ServletMaintenance.DAYS
   * </ul>
   *
   * @param reference  some object that implements MaintainedServlet
   * @param lengthTime  a length of time to wait
   * @param timeType  the time unit that should be used
   */
public ServletMaintenance(MaintainedServlet reference, int lengthTime, int timeType) {
	servletReference = reference;
	secondsToWait = 0;
	minutesToWait = 0;
	hoursToWait = 0;
	daysToWait = 0;
	if (timeType == ServletMaintenance.SECONDS)
		secondsToWait = lengthTime;
	else
		if (timeType == ServletMaintenance.MINUTES)
			minutesToWait = lengthTime;
		else
			if (timeType == ServletMaintenance.HOURS)
				hoursToWait = lengthTime;
			else
				if (timeType == ServletMaintenance.DAYS)
					daysToWait = lengthTime;

				//Start this thread
	start();
}
/**
   * Returns the number of days the thread waits before executing.
   */
public int getDays() {
	return daysToWait;
}
/**
   * Returns the number of hours the thread waits before executing.
   */
public int getHours() {
	return hoursToWait;
}
/**
   * Returns the number of minutes the thread waits before executing.
   */
public int getMinutes() {
	return minutesToWait;
}
/**
   * Returns the number of seconds the thread waits before executing.
   */
public int getSeconds() {
	return secondsToWait;
}
/**
   * Calls the doMaintenance() method of the MaintainedServlet object and then
   * sleeps for the desired interval. This process will continue until the
   * shutDown() method is called. This method will be automatically started
   * when the object is constructed. You should not call the run method or
   * the start method in your code.
   */
public void run() {
	//While the user has not called the shutDown() method.
	while (!done) {
		try {
			//Wait for the specified amount of time before continuing.
			long timeToWait = secondsToWait * SECOND;
			timeToWait += minutesToWait * MINUTE;
			timeToWait += hoursToWait * HOUR;
			timeToWait += daysToWait * DAY;
			sleep(timeToWait);
			//Tell the servlet to perform the maintenance function
			if (!done)
				servletReference.doMaintenance();
		} catch (Exception e) {
			//we'll ignore errors...
		}
	}
}
/**
   * Sets the number of hours the thread waits before executing. The change
   * will not take effect until the next time the thread loops through a
   * wait cycle.
   *
   * @param minutes the number of hours to wait.
   */
public void setDays(int days) {
	daysToWait = days;
}
/**
   * Sets the number of hours the thread waits before executing. The change
   * will not take effect until the next time the thread loops through a
   * wait cycle.
   *
   * @param minutes the number of hours to wait.
   */
public void setHours(int hours) {
	hoursToWait = hours;
}
/**
   * Sets the number of minutes the thread waits before executing. The change
   * will not take effect until the next time the thread loops through a
   * wait cycle.
   *
   * @param minutes the number of minutes to wait.
   */
public void setMinutes(int minutes) {
	minutesToWait = minutes;
}
/**
   * Sets the number of seconds the thread waits before executing. The change
   * will not take effect until the next time the thread loops through a
   * wait cycle.
   *
   * @param minutes the number of hours to wait.
   */
public void setSeconds(int seconds) {
	secondsToWait = seconds;
}
/**
   * This should be called when the thread needs to be shut-down.
   */
public void shutDown() {
	done = true;
}
}