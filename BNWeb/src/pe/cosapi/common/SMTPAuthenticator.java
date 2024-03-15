/*
 * Creado el 08/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.common;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class SMTPAuthenticator extends Authenticator {
	private String user;
	private String password;
	
	public SMTPAuthenticator(String user, String password){
		this.user = user;
		this.password = password;
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
	}
}
