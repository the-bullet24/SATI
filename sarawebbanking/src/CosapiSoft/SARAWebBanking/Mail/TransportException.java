package CosapiSoft.SARAWebBanking.Mail;

/**
 *  TransportException.java
 *
 *  Version 1.2
 *
 *  Diciembre 23, 1999
 *
 *  Copyright (C) 1999  CosapiSoft S:A
 *
 */

/**
 *  TransportException class
 *
 *  Public Methods:
 *    public TransportException()
 *    TransportException( String msg )
 *
 */

import java.io.*;
public class TransportException extends Exception {
/**
 *  Default constructor.
 */
public TransportException() {
	super();
}
/**
 *  TransportException( String msg )
 *  You should do something with the "msg" parameter! It provides useful information
 *  about each error.
 */
public TransportException(String msg) {
	super(msg);
}
}