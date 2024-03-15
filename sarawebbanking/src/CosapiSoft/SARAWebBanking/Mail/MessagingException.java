package CosapiSoft.SARAWebBanking.Mail;

/**
 *  MessagingException.java
 *
 **/

/**
 *  MessagingException class
 *
 *  Public Methods:
 *    MessagingException()
 *    MessagingException( String msg )
 *
 */

public class MessagingException extends Exception {
/**
 *  Default constructor.
 **/
public MessagingException() {
	super();
}
/**
 *  Do something with the "error" parameter! It provides useful information
 *  about each error.
 **/
public MessagingException(String msg) {
	super(msg);
}
}