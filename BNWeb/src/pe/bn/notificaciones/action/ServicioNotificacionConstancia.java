package pe.bn.notificaciones.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.servlet.ServletController;

import org.apache.commons.lang.StringUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.UtilAction;
import pe.cosapi.action.form.MailForm;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.Mail;
import pe.cosapi.common.MailFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Usuario;
import pe.com.bn.common.Funciones;

public class ServicioNotificacionConstancia {
	
	private static LoggerSati log3 = LoggerSati.getInstance(ServicioNotificacionConstancia.class.getName());
	

	
	@SuppressWarnings("unchecked")
	public static void enviarNotificacion(String idObjeto, String variableSesion, HttpServletRequest request) throws Exception {
		
		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		
		Afiliacion datos = new AfiliacionImpl();
		String nota="";
			
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		datos = FacadeFactory.getAfiliacionFacade().getConsultaDatosCliente(Constante.DAT_CLIENTES_CONSULTA, request.getRemoteAddr(), usuario, request);
		
		request.getSession().setAttribute("flagMigrar",datos.getFlagDejaMigrar());
        request.getSession().setAttribute("diasParaMigrar",datos.getDiasParaMigrar());
        
        String email= datos.getCorreoPersonal().trim()== ""?"" : datos.getCorreoPersonal();
		
		
		if(!email.equals("")){
			
		/*** RECUPERAR DETALLE DEL CORREO ***/
		Object objeto = request.getSession().getAttribute(variableSesion);
		HashMap map = new HashMap(UtilAction.cargarVar(request, objeto));
		
		Mail mail = MailFactory.getMailInstance(idObjeto);
		mail.setTo(email);
		mail.setCC("");
		nota=aplicacion.getMensajePorCodigo("CL01","00007").elementAt(2).toString();
		mail.setNota(nota);
		
		
		MailForm mailForm = new MailForm();
		mailForm.setMailDetalle(map);
		mailForm.setTo(email);
		mailForm.setCc("");
		
		try {
			request.getSession().setAttribute(ConstanteSesion.MAIL_CONSTANCIA, mailForm);
			
			mail.sendMailConstanciaNota(map, request);
			

		} catch (Exception e) {
			log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
			e.printStackTrace();
		}
		System.out.println("::::: FIN - NOTIFICACION GENERAL :::::");
	   }
	}
}
