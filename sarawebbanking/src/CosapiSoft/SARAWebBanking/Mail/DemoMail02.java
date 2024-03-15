package CosapiSoft.SARAWebBanking.Mail;

/**
 *  DemoMail02.java
 **/


/**
 *  DemoMail02
 *
 *  This example shows how to construct arrays of Address objects
 *  and then how to send them to the Message object.
 *
 **/

public class DemoMail02 {
public DemoMail02() {
	// Create Message and Transport objects
	Message msg = new Message();
	Transport tr = new Transport("yourSmtpHost.net", 25);

	// You can create arrays of addresses and send those to
	// the message object.
	Address[] toList = new Address[2];
	Address[] ccList = new Address[2];
	Address[] bccList = new Address[2];
	toList[0] = new Address("Franz Liszt", "fliszt@somewhere.com");
	toList[1] = new Address("Frederick Chopin", "fchopin@somewhere.com");
	ccList[0] = new Address("S Rachmaninov", "rach@somewhere.com");
	ccList[1] = new Address("Gustav Mahler", "gmahler@somewhere.com");
	bccList[0] = new Address("P Tchaikovsky", "tchaik@somewhere.com");
	bccList[1] = new Address("L Beethoven", "ludwig@somewhere.com");
	msg.setRecipients(RecipientType.TO, toList);
	msg.setRecipients(RecipientType.CC, ccList);
	msg.setRecipients(RecipientType.BCC, bccList);

	// Set the FROM address
	msg.setFrom(new Address("Ima Big Fan", "ima@address.com"));

	// Oops -- almost forgot two. You can add them the list of current recipients.
	// Calling "setRecipient(...)" here would override the current list of addresses.
	msg.addRecipient(RecipientType.CC, new Address("D Shoshtakovitch", "dsch@somewhere.com"));
	msg.addRecipient(RecipientType.BCC, new Address("Richard Wagner", "rwagner@somewhere.com"));

	// Set the SUBJECT
	msg.setSubject("You guys rule!");

	// Set the message TEXT
	msg.setText("You guys are the greatest! Later!");

	// Send the email. Must catch TransportException Exception.
	try {
		tr.send(msg);
	} catch (TransportException te) {
		// System.out.println("TransportException: " + te);
	}
}
public static void main(String[] args) {
	DemoMail02 em = new DemoMail02();
}
}