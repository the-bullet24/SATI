
package pe.bn.afiliacion.action.form;

import org.apache.struts.action.ActionForm;


public class ClaveSmsForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2890852028180212983L;
	private String cboOperador;
	private String desOperador;
	private String txtCelular;
	private String chkAceptar;
	private String txtClaveSms;
	private String mensajeHidden;

	
	public String getCboOperador() {
		return cboOperador;
	}


	public void setCboOperador(String cboOperador) {
		this.cboOperador = cboOperador;
	}
	
	public String getDesOperador() {
		return desOperador;
	}


	public void setDesOperador(String desOperador) {
		this.desOperador = desOperador;
	}


	public String getTxtCelular() {
		return txtCelular;
	}


	public void setTxtCelular(String txtCelular) {
		this.txtCelular = txtCelular;
	}


	public String getChkAceptar() {
		return chkAceptar;
	}


	public void setChkAceptar(String chkAceptar) {
		this.chkAceptar = chkAceptar;
	}


	public String getTxtClaveSms() {
		return txtClaveSms;
	}


	public void setTxtClaveSms(String txtClaveSms) {
		this.txtClaveSms = txtClaveSms;
	}


	public String getMensajeHidden() {
		return mensajeHidden;
	}


	public void setMensajeHidden(String mensajeHidden) {
		this.mensajeHidden = mensajeHidden;
	}
	
	
	

	
}
