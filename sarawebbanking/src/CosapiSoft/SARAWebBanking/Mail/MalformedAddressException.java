package CosapiSoft.SARAWebBanking.Mail;

/**
 *  MalformedAddressException.java
 *
 *  Version 1.2
 *
 *  September 27, 1999
 *
 */

/**
 *  MalformedAddressException class
 *
 *  Public Methods:
 *    MalformedAddressException()
 *    MalformedAddressException( String msg )
 *
 *  Changes in ver 1.2:
 *    Improved the documentation for the MalformedAddressException(String msg) method.
 */
public class MalformedAddressException extends Exception {
/**
 *  Default constructor.
 */
public MalformedAddressException() {
	super();
}
/**
 *  Do something with the "error" parameter! It provides useful information
 *  about each error.
 *
 *  For instance, the "msg" parameter can contain information about the
 *  malformed email -- I improved the error message strings in the Address
 *  class (see setPersonal() and setAddress()). Looking at those error strings
 *  should help you determine which email address or name is throwing an error.
 */
public MalformedAddressException(String msg) {
	super(msg);
}
}