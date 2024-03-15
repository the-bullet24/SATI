/*
 * Creado el 07/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.common;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;
import pe.bn.servlet.ServletController;
import pe.com.bn.common.Funciones;
import pe.com.bn.sati.common.LoggerSati;


/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class MailFactory extends ObjectFactory{
	
	private static LoggerSati log3 = LoggerSati.getInstance(ServletController.class.getName());
	
	public static Mail getMailInstance(String object) {
		Mail mail = (Mail) getObject(object);
		
		
//		if (Constante.BN_PARAM_SERVICE_CORREO == null){
//            Constante.BN_PARAM_SERVICE_CORREO = Funciones.getServiceAdress();
//            
//		}
		if (Constante.BN_PARAM_SERVICE_CORREO == null){
			log3.error(null, null, "NO SE ENCUENTRA EL VALOR DEL PARAMETRO  BN_PARAM_SERVICE_CORREO");
		}
		
		String[] vCadena=Parametro.getString(ConstanteParametros.BN_PARAM_SERVICE_CORREO).split(":");
						
		JavaMailSenderImpl ms = new JavaMailSenderImpl();
		ms.setHost(vCadena[0]);
		ms.setPort(Integer.parseInt(vCadena[1]));
		mail.setMailSender(ms);
		
		return mail;
	}
	
}
