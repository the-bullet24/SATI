package CosapiSoft.SARAWebBanking.Mail;

/**
 *  RecipientType.java
 *
 *  Version 1.2
 *
 */

/**
 *  RecipientType class
 *
 *  This class is small and simple. It just holds recipient type values.
 *
 *  This class is primarily used like so:
 *
 *    if( type == RecipientType.TO )
 *      // do TO stuff
 *    else if( type == RecipientType.CC )
 *      // do CC stuff
 *
 *  Private Methods/Fields:
 *    static final int TO  = 1;
 *    static final int CC  = 2;
 *    static final int BCC = 3;
 *
 */

public class RecipientType {
	public static final int TO = 1;
	public static final int CC = 2;
	public static final int BCC = 3;

}