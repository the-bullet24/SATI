package CosapiSoft.SARAWebBanking.Mail;

/**
 *  Transportable.java
 *
 *  Version 1.2
 *
 */

import java.io.PrintWriter;

/**
 *  Transportable Interface
 *
 *  I thought it would be a good idea to make an interface that would
 *  be used by the Transport class for a couple of reasons:
 *
 *    1) Other apps, servlets, etc, rely on this email package. When the package
 *       is updated, it should be that the users of the email package will just
 *       have to update their email classes without any other code recompiles
 *       elsewhere.
 *    2) I'm going to implement the Email Package using JavaMail some day so
 *       writing an interface makes switching between my implementation of
 *       sending emails or using JavaMail painless.
 */
public interface Transportable {
public String getSmtpHost();
public int getSmtpPort();
public void send(Message msg) throws TransportException;
public void setSmtpHost(String smtpHost);
public void setSmtpPort(int smtpPort);
}