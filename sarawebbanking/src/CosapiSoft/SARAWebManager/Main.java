/*
 * Creado el 20/04/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package CosapiSoft.SARAWebManager;
import javax.mail.*;
/*import javax.mail.internet.*;
import java.util.*;*/
import java.util.Properties; 
import java.util.Date; 
import javax.mail.Session; 
import javax.mail.Message; 
import javax.mail.Transport; 
import javax.mail.internet.MimeMultipart; 
import javax.mail.internet.MimeMessage; 
import javax.mail.internet.MimeBodyPart; 
import javax.mail.internet.InternetAddress;

import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.ObjectUtil;

public class Main {
		private String  d_email1 	= "BNenlinea@bn.com.pe";
		private String  d_email 	= "BNMultiredVirtual";
		private String  d_password 	= "BNMultiredVirtual";
		private String  d_host 		= "10.7.132.10";
		public String  m_subject 	= "Banco de la Nación - Duplicado de Constancia de Pago";
		/**
		 * @return Devuelve cc_m_to.
		 */
		public String getCc_m_to() {
			return cc_m_to;
		}
		/**
		 * @param cc_m_to El cc_m_to a establecer.
		 */
		public void setCc_m_to(String cc_m_to) {
			this.cc_m_to = cc_m_to;
		}
		/**
		 * @return Devuelve m_text.
		 */
		public String getM_text() {
			return m_text;
		}
		/**
		 * @param m_text El m_text a establecer.
		 */
		public void setM_text(String m_text) {
			this.m_text = m_text;
		}
		/**
		 * @return Devuelve m_to.
		 */
		public String getM_to() {
			return m_to;
		}
		/**
		 * @param m_to El m_to a establecer.
		 */
		public void setM_to(String m_to) {
			this.m_to = m_to;
		}
		private String  m_text = "";
		private String  m_to = "";
		private Object text=null;
		private String  cc_m_to = "";

		
		public void Enviarcorreo(){
			Properties props = new Properties();
			props.put("mail.smtp.user", d_email1);
			props.put("mail.smtp.host", d_host);
			Session session = Session.getDefaultInstance(props, null); 
			//props.put("mail.smtp.auth", "true");
			

			try
			{
				//Authenticator auth = new SMTPAuthenticator();
				//Session session = Session.getInstance(props, auth);
				//session.setDebug(true);
				MimeMultipart multipart = new MimeMultipart(); 
				MimeMessage msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(d_email1)); 
				msg.setRecipients(Message.RecipientType.TO, getM_to()); 
				msg.setRecipients(Message.RecipientType.CC, getCc_m_to()); 
				//msg.setSubject(m_subject);
				msg.setSubject(ConstanteSesion.MAIL_SUBJECT);
			    msg.setSentDate(new Date());
			    
			    MimeBodyPart mbp = new MimeBodyPart(); 
			    mbp.setContent(ObjectUtil.replaceIlegalCharacterToSendMail(getM_text().toString()), "text/html"); 
			    multipart.addBodyPart(mbp); 
			    msg.setContent(multipart);
			    /*
				msg.setText(m_text);
				msg.setSubject(m_subject);
				msg.setContent(getM_text(),"text/html");
				msg.setFrom(new InternetAddress(d_email));            
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(getM_to()));
				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(getCc_m_to()));*/
				Transport.send(msg);
			}
			catch (Exception mex)
			{
				mex.printStackTrace();
			} 
		}

		public static void main(String[] args)
		{
			Main blah = new Main();
		}

		private class SMTPAuthenticator extends javax.mail.Authenticator
		{
			public PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(d_email, d_password);
			}
		}
		/**
		 * @return Devuelve text.
		 */
		public Object getText() {
			return text;
		}
		/**
		 * @param text El text a establecer.........s.
		 */
		public void setText(Object text) {
			this.text = text;
		}
		/**
		 * @return Devuelve m_subject.
		 */
		public String getM_subject() {
			return m_subject;
		}
		/**
		 * @param m_subject El m_subject a establecer.
		 */
		public void setM_subject(String m_subject) {
			this.m_subject = m_subject;
		}
}
