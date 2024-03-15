package CosapiSoft.SARAWebBanking.Mail;

/**
 *  ExampleApp.java
 *
 *  CS Email Package, Version 1.2
 *
 *  September 27, 1999
 *
 *  Copyright (C) 1999  Bill Lynch, CoolServlets.com
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Library General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *  http://www.coolservlets.com/LGPL.html
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Library General Public License for more details.
 *
 *  You should have received a copy of the GNU Library General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

import java.io.*;

/**
 *  This is a fully functional demo of the Email Package. To run this
 *  demo, you need to do a few things:
 *
 *  (1) Put the email classes in the right place.
 *
 *      yourCurrentDirectory/ ExampleApp.java
 *                          / com /
 *                                / coolservlets /
 *                                               / email /
 *                                                       / <email classes>
 *      where <email classes> are the files included in the distribution.
 *
 *  (2) Find out what SMTP port you have access to. Once you do that,
 *      be sure to note the SMTP host name (ie, smtp.coolservlets.com) and
 *      port number (ie, 25). (Note, that's not our real SMTP server so
 *      don't even try it... :) ).
 *
 *  (3) Change the private variables in the ExampleApp class. Without this
 *      change, this demo will compile but you won't send anything! :)
 *
 */
public class ExampleApp {
	private String YOUR_NAME = "Operador 1";
	/* ie, "Joe Bloe"                           */
	private String YOUR_EMAIL = "operad1@interdesa";
	/* ie, "jbloe@joesDomain.com"               */
	private String RECIPIENT_NAME = "Operador 2";
	/* ie, "Jane Doe"                           */
	private String RECIPIENT_EMAIL = "operad2@interdesa";
	/* ie, "jdoe@anotherDomain.com"             */
	private String EMAIL_SUBJECT = "Ejecución de Inicio de Día";
	/* ie, "Hi Jane!"                           */
	private String EMAIL_TEXT = "Esto es una prueba para la ejecución del Inicio de Dia.....";
	/* ie, "Hope all is well... later! -- Joe"  */
	private String SMTP_HOST = "interdesa";
	/* Joe's smtp server: smtp.joesDomain.com   */
	private int SMTP_PORT = 25;
	/* Default is 25                            */

public ExampleApp() {

	// Create the message and transport objects:
	Message msg = new Message();
	Transport tr = new Transport(SMTP_HOST, SMTP_PORT);

	// Set the FROM, TO, SUBJECT and text of the email message
	msg.setFrom(new Address(YOUR_NAME, YOUR_EMAIL));
	msg.setRecipient(RecipientType.TO, new Address(RECIPIENT_NAME, RECIPIENT_EMAIL));
	msg.setSubject(EMAIL_SUBJECT);
	msg.setText(EMAIL_TEXT);

	// Send it! Catch the TransportException.
	try {
		// System.out.println("Message sending...");
		tr.send(msg);
		// System.out.println("Message was send!");
	} catch (TransportException te) {
		// System.out.println("Transport Exception: " + te);
	}
}
public static void main(String args[]) {
	ExampleApp ea = new ExampleApp();
}
}