
package pe.bn.afiliacion.action.form;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import pe.cosapi.common.Constante;


public  class AfiliacionGenericaForm extends ActionForm{
	
	protected String cmbTipoDocIdent;
	protected String txtNumDoc;
	protected String txtDia;
	protected String cmbMes;
	protected String txtAnio;
	protected String rdnSexo;
	protected String txtMail;
	protected String txtNumClave;
	
	
	protected String cmbTipoTelefono;		       
	protected String txtNumeroTelefono;		    	
	protected String cmbEmpresa;					
	protected String cmbServicio;				
	protected String cmbCodigoUsuarioSumi;		
	protected String txtMaximo;	
	protected String rdnMontoMaximoDebito;
	
	
	protected String txtCodUserSuministro;
	
	public String getCmbMes() {
		return cmbMes;
	}
	public void setCmbMes(String cmbMes) {
		this.cmbMes = cmbMes;
	}
	public String getCmbTipoDocIdent() {
		return cmbTipoDocIdent;
	}
	public void setCmbTipoDocIdent(String cmbTipoDocIdent) {
		this.cmbTipoDocIdent = cmbTipoDocIdent;
	}
	
	public String getRdnSexo() {
		return rdnSexo;
	}
	public void setRdnSexo(String rdnSexo) {
		this.rdnSexo = rdnSexo;
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
	public String getTxtMail() {
		return txtMail;
	}
	public void setTxtMail(String txtMail) {
		this.txtMail = txtMail;
	}
	public String getTxtNumDoc() {
		return txtNumDoc;
	}
	public void setTxtNumDoc(String txtNumDoc) {
		this.txtNumDoc = txtNumDoc;
	}
	
	public String getTxtCodUserSuministro() {
		return txtCodUserSuministro;
	}
	public void setTxtCodUserSuministro(String txtCodUserSuministro) {
		this.txtCodUserSuministro = txtCodUserSuministro;
	}


	public String getCmbCodigoUsuarioSumi() {
		return cmbCodigoUsuarioSumi;
	}
	public void setCmbCodigoUsuarioSumi(String cmbCodigoUsuarioSumi) {
		this.cmbCodigoUsuarioSumi = cmbCodigoUsuarioSumi;
	}
	public String getCmbEmpresa() {
		return cmbEmpresa;
	}
	public void setCmbEmpresa(String cmbEmpresa) {
		this.cmbEmpresa = cmbEmpresa;
	}
	public String getCmbServicio() {
		return cmbServicio;
	}
	public void setCmbServicio(String cmbServicio) {
		this.cmbServicio = cmbServicio;
	}
	public String getCmbTipoTelefono() {
		return cmbTipoTelefono;
	}
	public void setCmbTipoTelefono(String cmbTipoTelefono) {
		this.cmbTipoTelefono = cmbTipoTelefono;
	}
	public String getTxtMaximo() {
		return txtMaximo;
	}
	public void setTxtMaximo(String txtMaximo) {
		this.txtMaximo = txtMaximo;
	}
	public String getTxtNumeroTelefono() {
		return txtNumeroTelefono;
	}
	public void setTxtNumeroTelefono(String txtNumeroTelefono) {
		this.txtNumeroTelefono = txtNumeroTelefono;
	}

	public String getRdnMontoMaximoDebito() {
		return rdnMontoMaximoDebito;
	}
	public void setRdnMontoMaximoDebito(String rdnMontoMaximoDebito) {
		this.rdnMontoMaximoDebito = rdnMontoMaximoDebito;
	}
	
	public  ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		super.validate(mapping, request);
		
		ActionErrors errors = new ActionErrors();
		
		return errors;
	}
	
	
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);
		
		this.cmbTipoDocIdent 	= "";
		this.txtNumDoc 			= "";
		this.txtDia 			= "";
		this.cmbMes 			= "";
		this.txtAnio 			= "";
		this.rdnSexo 			= "";
		this.txtMail 			= "";
		this.txtNumClave		= "";
		
		this.cmbTipoTelefono    	= "";		       
		this.txtNumeroTelefono		= "";	    	
		this.cmbEmpresa				= "";		
		this.cmbServicio			= "";		
		this.cmbCodigoUsuarioSumi	= "";	
		this.txtMaximo				= "";
		this.rdnMontoMaximoDebito   = "";
		
		this.txtCodUserSuministro 			= "";
	
	}
	public String getTxtNumClave() {
		return txtNumClave;
	}
	public void setTxtNumClave(String txtNumClave) {
		this.txtNumClave = txtNumClave;
	}
	

}
	


