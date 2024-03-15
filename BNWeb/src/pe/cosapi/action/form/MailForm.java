/*
 * Creado el 12/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.action.form;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class MailForm extends ActionForm {
	
	private String to;
	private String cc;
	private Map mailDetalle;
	
	/**
	 * @return Devuelve cc.
	 */
	public String getCc() {
		return cc;
	}
	/**
	 * @param cc El cc a establecer.
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}
	/**
	 * @return Devuelve to.
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to El to a establecer.
	 */
	public void setTo(String to) {
		this.to = to;
	}
	
	/* (sin Javadoc)
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
		return super.validate(arg0, arg1);
	}
    public Map getMailDetalle() {
        return mailDetalle;
    }
    public void setMailDetalle(Map mailDetalle) {
        this.mailDetalle = mailDetalle;
    }
}
