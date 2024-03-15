/*
 * Creado el 02/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.login.action.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import pe.cosapi.common.Constante;


/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class LoginForm extends ActionForm{
	
	private String txtCaptcha;
	private String cboTipoPersona;
	//private String cboTipoJuridica;
	private String cboTipoTarjeta;
	private String txtNumeroTarjeta;
	private String txtPassword;
	private String txtDNI;
	private String txtCvv2;
	private String captcha;
	
	//Afilicion Clave Internet
	
	private String cboTipoDoc;
	private String txtNumDoc;
	private String txtDia;
	private String cmbMes;
	private String txtAnio;
	private String txtEmail;
	
	
	
	public String getTxtEmail() {
		return txtEmail;
	}
	public void setTxtEmail(String txtEmail) {
		this.txtEmail = txtEmail;
	}
	public String getCboTipoDoc() {
		return cboTipoDoc;
	}
	public void setCboTipoDoc(String cboTipoDoc) {
		this.cboTipoDoc = cboTipoDoc;
	}
	public String getCmbMes() {
		return cmbMes;
	}
	public void setCmbMes(String cmbMes) {
		this.cmbMes = cmbMes;
	}
	public String getTxtAnio() {
		return txtAnio;
	}
	public void setTxtAnio(String txtAnio) {
		this.txtAnio = txtAnio;
	}
	public String getTxtDia() {
		return txtDia;
	}
	public void setTxtDia(String txtDia) {
		this.txtDia = txtDia;
	}
	public String getTxtNumDoc() {
		return txtNumDoc;
	}
	public void setTxtNumDoc(String txtNumDoc) {
		this.txtNumDoc = txtNumDoc;
	}
	public String getCaptcha() {
	return captcha;
	}
	public void setCaptcha(String captcha) {
	this.captcha = captcha;
	}
	public LoginForm() {
	super();
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request){
		cboTipoPersona = Constante.COD_PERSON_NAT;
		cboTipoTarjeta = "";
		txtNumeroTarjeta="";
		txtPassword="";
		txtDNI="";
		txtCvv2="";
		txtPassword = "";
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		return null;
		/*
		ActionErrors errors = new ActionErrors();		
		//visualizar(request);
		
		String validar = request.getParameter("validar");
		if(validar!=null&&validar.equals("false"))
			return errors;	
							
		if(cboTipoPersona.equals(Constante.PERSONA_NATURAL)){
			if(txtNumeroTarjeta==null||txtNumeroTarjeta.equals("")){
				errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_CAMPO_OBLIGATORIO,"número de tarjeta"));
				return errors;
			}			
			if(txtNumeroTarjeta.length()!=4){
				errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_LONGITUD_REQUERIDA,"número de tarjeta","4"));
				return errors;
			}
		}
		else{
			if(cboTipoJuridica.equals(Constante.PERSONA_JURIDICA_UNICA_OFERTA_BANCARIA)){
				if(txtNumeroTarjeta==null||txtNumeroTarjeta.equals("")){
					errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_CAMPO_OBLIGATORIO,"número de tarjeta"));
					return errors;
				}				
				/*if(txtNumeroTarjeta.length()!=4){
					errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_LONGITUD_REQUERIDA,"número de tarjeta","4"));
					return errors;
				}	*/			
			/*}
			else{
				if(txtNumeroTarjeta==null||txtNumeroTarjeta.equals("")){
					errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_CAMPO_OBLIGATORIO,"DNI"));
					return errors;
				}				
				if(txtNumeroTarjeta.length()!=6){					
					errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_LONGITUD_REQUERIDA,"DNI","6"));
					return errors;
				}								
			}
		}		
		if(txtPassword==null){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_CAMPO_OBLIGATORIO,"clave"));
			return errors;
		}/*
		else if(txtPassword.length()<4){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_LONGITUD_REQUERIDA,"clave","4"));
			return errors;
		}*//*
		return errors;*/
	}
	/*
	 * 

	public void visualizar(HttpServletRequest request){		
		if(this.getCboTipoPersona().equals(Constante.PERSONA_NATURAL)){
			request.setAttribute("tarjeta","existe");
			request.setAttribute("numero","existe");			
		}
		else{
			request.setAttribute("juridico","existe");
			if(this.getCboTipoJuridica().equals(Constante.PERSONA_JURIDICA_UNICA_OFERTA_BANCARIA)){				
				request.setAttribute("tarjeta","existe");
				request.setAttribute("numero","existe");				
			}
			else{				
				request.setAttribute("DNI","existe");				
			}
		}		
	}
	
	*/
	
	/**
	 * @return Devuelve cboTipoPersona.
	 */
	public String getCboTipoPersona() {
		return cboTipoPersona;
	}
	/**
	 * @param cboTipoPersona El cboTipoPersona a establecer.
	 */
	public void setCboTipoPersona(String cboTipoPersona) {
		this.cboTipoPersona = cboTipoPersona;
	}
	/*
	/**
	 * @return Devuelve cboTipoTarjeta.
	 */
	public String getCboTipoTarjeta() {
		return cboTipoTarjeta;
	}
	/**
	 * @param cboTipoTarjeta El cboTipoTarjeta a establecer.
	 */
	public void setCboTipoTarjeta(String cboTipoTarjeta) {
		this.cboTipoTarjeta = cboTipoTarjeta;
	}
	/**
	 * @return Devuelve txtNumeroTarjeta.
	 */
	public String getTxtNumeroTarjeta() {
		return txtNumeroTarjeta;
	}
	/**
	 * @param txtNumeroTarjeta El txtNumeroTarjeta a establecer.
	 */
	public void setTxtNumeroTarjeta(String txtNumeroTarjeta) {
		this.txtNumeroTarjeta = txtNumeroTarjeta;
	}
	/**
	 * @return Devuelve txtPassword.
	 */
	public String getTxtPassword() {
		return txtPassword;
	}
	/**
	 * @param txtPassword El txtPassword a establecer.
	 */
	public void setTxtPassword(String txtPassword) {
		this.txtPassword = txtPassword;
	}
	/**
	 * @return Devuelve txtDNI.
	 */
	public String getTxtDNI() {
		return txtDNI;
	}
	/**
	 * @param txtDNI El txtDNI a establecer.
	 */
	public void setTxtDNI(String txtDNI) {
		this.txtDNI = txtDNI;
	}
	/**
	 * @return Devuelve txtCvv2.
	 */
	public String getTxtCvv2() {
		return txtCvv2;
	}
	/**
	 * @param txtCvv2 El txtCvv2 a establecer.
	 */
	public void setTxtCvv2(String txtCvv2) {
		this.txtCvv2 = txtCvv2;
	}
	public String getTxtCaptcha() {
		return txtCaptcha;
	}
	public void setTxtCaptcha(String txtCaptcha) {
		this.txtCaptcha = txtCaptcha;
	}
}