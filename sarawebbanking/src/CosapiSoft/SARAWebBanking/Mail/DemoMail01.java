package CosapiSoft.SARAWebBanking.Mail;

/**
 *  DemoMail01.java
 **/


/**
 *  DemoMail01
 *
 *  This example shows how to send a basic email.
 *
 **/

public class DemoMail01 {
public DemoMail01() {
	// Create Message and Transport objects
	Message msg = new Message();
	//	Transport tr = new Transport( "yourSmtpHost.net", 25 );
	Transport tr = new Transport("smtp.mail.yahoo.com", 25);

	// Set the FROM address
	//	msg.setFrom( new Address( "Your Name", "your@address.com" ) );
	msg.setFrom(new Address("Alexander Bonilla", "a_bonilla_d@yahoo.com"));

	// Set the TO recipient
	msg.setRecipient(RecipientType.TO, new Address("Your Friend", "a_bonilla@hotmail.com"));

	// Set the SUBJECT
	msg.setSubject("This is the subject!");

	// Set the message TEXT
	msg.setText("This is the text of the email.");

	// Send the email. Must catch TransportException Exception.
	try {
		// System.out.println("Sending Mail");
		tr.send(msg);
		// System.out.println("Mail send!! ");
	} catch (TransportException te) {
		// System.out.println("TransportException: " + te);
	}
}
public static void main(String[] args) {
	DemoMail01 em = new DemoMail01();
}
}