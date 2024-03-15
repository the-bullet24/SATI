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
public class ClaveDinamicaForm extends ActionForm{
	
	private String txtApellidoPaterno;
	private String txtApellidoMaterno;
	private String txtNombre;
	private String cmbTipoDocBenef;
	private String txtNroDoc;
	private String cmbTipoDocIdent;
	private String txtNumDoc;
	private String txtDia;
	private String rdnSexo;
	private String txtMail;
	private String cmbMes;
	private String txtAnio;
	private String txtNumClave;
	private String rdnSexoBen;
	private String txtClaveAfiliacion;
	private String rdnMotPerdida;
	
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request){
		
		txtApellidoPaterno  = "";
		txtApellidoMaterno  = "";
		txtNombre 			= "";
		txtNroDoc 			= "";
		txtNumDoc 			= "";
		txtDia 				= "";
		rdnSexo 			= "";
		txtMail 			= "";
		txtAnio 			= "";
		cmbMes				="";
		cmbTipoDocBenef		="";
		txtNumClave		="";
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		return null;
	}
	
	
	/**
	 * @return Devuelve txtApellidoPaterno.
	 */
	public String getTxtApellidoPaterno() {
		return txtApellidoPaterno;
	}
	/**
	 * @param txtApellidoPaterno El txtApellidoPaterno a establecer.
	 */
	public void setTxtApellidoPaterno(String txtApellidoPaterno) {
		this.txtApellidoPaterno = txtApellidoPaterno;
	}
	
	
	/**
	 * @return Devuelve cmbTipoDocBenef.
	 */
	public String getCmbTipoDocBenef() {
		return cmbTipoDocBenef;
	}
	/**
	 * @param cmbTipoDocBenef El cmbTipoDocBenef a establecer.
	 */
	public void setCmbTipoDocBenef(String cmbTipoDocBenef) {
		this.cmbTipoDocBenef = cmbTipoDocBenef;
	}
	/**
	 * @return Devuelve txtApellidoMaterno.
	 */
	public String getTxtApellidoMaterno() {
		return txtApellidoMaterno;
	}
	/**
	 * @param txtApellidoMaterno El txtApellidoMaterno a establecer.
	 */
	public void setTxtApellidoMaterno(String txtApellidoMaterno) {
		this.txtApellidoMaterno = txtApellidoMaterno;
	}
	/**
	 * @return Devuelve txtNombre.
	 */
	public String getTxtNombre() {
		return txtNombre;
	}
	/**
	 * @param txtNombre El txtNombre a establecer.
	 */
	public void setTxtNombre(String txtNombre) {
		this.txtNombre = txtNombre;
	}
	/**
	 * @return Devuelve txtNroDoc.
	 */
	public String getTxtNroDoc() {
		return txtNroDoc;
	}
	/**
	 * @param txtNroDoc El txtNroDoc a establecer.
	 */
	public void setTxtNroDoc(String txtNroDoc) {
		this.txtNroDoc = txtNroDoc;
	}
	
	
	
	/**
	 * @return Devuelve cmbTipoDocIdent.
	 */
	public String getCmbTipoDocIdent() {
		return cmbTipoDocIdent;
	}
	/**
	 * @param cmbTipoDocIdent El cmbTipoDocIdent a establecer.
	 */
	public void setCmbTipoDocIdent(String cmbTipoDocIdent) {
		this.cmbTipoDocIdent = cmbTipoDocIdent;
	}
	/**
	 * @return Devuelve rdnSexo.
	 */
	public String getRdnSexo() {
		return rdnSexo;
	}
	/**
	 * @param rdnSexo El rdnSexo a establecer.
	 */
	public void setRdnSexo(String rdnSexo) {
		this.rdnSexo = rdnSexo;
	}
	/**
	 * @return Devuelve txtDia.
	 */
	public String getTxtDia() {
		return txtDia;
	}
	/**
	 * @param txtDia El txtDia a establecer.
	 */
	public void setTxtDia(String txtDia) {
		this.txtDia = txtDia;
	}
	/**
	 * @return Devuelve txtMail.
	 */
	public String getTxtMail() {
		return txtMail;
	}
	/**
	 * @param txtMail El txtMail a establecer.
	 */
	public void setTxtMail(String txtMail) {
		this.txtMail = txtMail;
	}
	/**
	 * @return Devuelve txtNumDoc.
	 */
	public String getTxtNumDoc() {
		return txtNumDoc;
	}
	/**
	 * @param txtNumDoc El txtNumDoc a establecer.
	 */
	public void setTxtNumDoc(String txtNumDoc) {
		this.txtNumDoc = txtNumDoc;
	}
	
	
	
	/**
	 * @return Devuelve cmbMes.
	 */
	public String getCmbMes() {
		return cmbMes;
	}
	/**
	 * @param cmbMes El cmbMes a establecer.
	 */
	public void setCmbMes(String cmbMes) {
		this.cmbMes = cmbMes;
	}
	
	
	/**
	 * @return Devuelve txtAnio.
	 */
	public String getTxtAnio() {
		return txtAnio;
	}
	/**
	 * @param txtAnio El txtAnio a establecer.
	 */
	public void setTxtAnio(String txtAnio) {
		this.txtAnio = txtAnio;
	}

	public String getTxtNumClave() {
		return txtNumClave;
	}

	public void setTxtNumClave(String txtNumClave) {
		this.txtNumClave = txtNumClave;
	}

	public String getRdnSexoBen() {
		return rdnSexoBen;
	}

	public void setRdnSexoBen(String rdnSexoBen) {
		this.rdnSexoBen = rdnSexoBen;
	}

	public String getTxtClaveAfiliacion() {
		return txtClaveAfiliacion;
	}

	public void setTxtClaveAfiliacion(String txtClaveAfiliacion) {
		this.txtClaveAfiliacion = txtClaveAfiliacion;
	}

	public String getRdnMotPerdida() {
		return rdnMotPerdida;
	}

	public void setRdnMotPerdida(String rdnMotPerdida) {
		this.rdnMotPerdida = rdnMotPerdida;
	}
	
}
