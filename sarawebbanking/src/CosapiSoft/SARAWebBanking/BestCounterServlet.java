package CosapiSoft.SARAWebBanking;

/**
 * BestCounter.java
 * Matt Tucker
 * CoolServlets.com
 *
 *    Copyright (C) 1999  Matt Tucker
 *
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import com.coolservlets.util.*;

/**
 * The best counter of the three. It loads and saves the count from a file
 * but uses more efficient time-based resource management.
 */
public class BestCounterServlet extends HttpServlet implements MaintainedServlet {
	private int count;
	private ServletMaintenance maintenanceThread;
/**
   * Called when the servlet is shutting down. It stops the maintenance
   * thread and saves the count to a file.
   */
public void destroy() {
	maintenanceThread.shutDown();
	saveCount();
}
/**
   * A get request will simply be passed to the post method.
   */
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
}
/**
   * Called by the maintenance thread periodically. It saves the count
   * to a file.
   */
public void doMaintenance() {
	try {
		saveCount();
	} catch (Exception e) {
		// System.out.println(e.getMessage());
	}
}
/**
   * Prints the current count to the servlet client.
   */
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	out.println(getCurrentCount());
	out.close();
}
/**
   * Increments the count value and returns the result.
   */
private synchronized int getCurrentCount() {
	count++;
	return count;
}
/**
   * Servlet startup. Load the count from the file and startup the
   * maintenance thread.
   */
public void init(ServletConfig config) throws ServletException {
	super.init(config);
	loadCount();
	//Creates a thread that will call the doMaintenance method of
	//this servlet every ten minutes.
	maintenanceThread = new ServletMaintenance(this, 10, ServletMaintenance.SECONDS);
}
/**
   * Tries to load a count value from a text file. If there are problems,
   * it initializes the count at 0.
   */
private void loadCount() {
	try {
		BufferedReader in = new BufferedReader(new FileReader("countData2"));
		count = Integer.parseInt(in.readLine());
		in.close();
	} catch (Exception e) {
		//If there was some error, we'll simply start the count at 0
		count = 0;
	}
}
/**
   * Puts the current count in a text file.
   */
private void saveCount() {
	try {
		PrintWriter out = new PrintWriter(new FileWriter("countData2"));
		out.println(count);
		out.close();
	} catch (Exception e) {
		//do nothing
	}
}
}