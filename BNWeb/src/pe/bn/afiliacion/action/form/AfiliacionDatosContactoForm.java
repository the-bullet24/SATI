package pe.bn.afiliacion.action.form;

import pe.com.bn.sati.common.LoggerSati;

public class AfiliacionDatosContactoForm extends AfiliacionGenericaForm{
	private static LoggerSati log3 = LoggerSati.getInstance(AfiliacionGenericaForm.class.getName());
	
	private String txtNumeroPersonal= "";
	private String cmbOperador="";
	
	

	public String getCmbOperador() {
		return cmbOperador;
	}

	public void setCmbOperador(String cmbOperador) {
		this.cmbOperador = cmbOperador;
	}

	public String getTxtNumeroPersonal() {
		return txtNumeroPersonal;
	}

	public void setTxtNumeroPersonal(String txtNumeroPersonal) {
		this.txtNumeroPersonal = txtNumeroPersonal;
	}
	
	
}
