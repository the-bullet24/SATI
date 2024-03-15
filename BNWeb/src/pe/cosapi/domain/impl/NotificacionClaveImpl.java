
package pe.cosapi.domain.impl;

import java.io.Serializable;
import pe.cosapi.domain.NotificacionClave;


public class NotificacionClaveImpl implements NotificacionClave, Serializable{
	
	private String estadoExpira;
	private String diasVencimiento;
	private String signoError;
	private String codigoError;
	
	
	public String getEstadoExpira() {
		return estadoExpira;
	}
	public void setEstadoExpira(String estadoExpira) {
		this.estadoExpira = estadoExpira;
	}
	public String getDiasVencimiento() {
		return diasVencimiento;
	}
	public void setDiasVencimiento(String diasVencimiento) {
		this.diasVencimiento = diasVencimiento;
	}	
	
	public String getSignoError() {
		return signoError;
	}
	public void setSignoError(String signoError) {
		this.signoError = signoError;
	}
	public String getCodigoError() {
		return codigoError;
	}
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}
	
	
	
	@Override
	public String toString() {
		return "NotificacionClaveImpl [estadoExpira=" + estadoExpira
				+ ", diasVencimiento=" + diasVencimiento
				+ ", getEstadoExpira()=" + getEstadoExpira()
				+ ", getDiasVencimiento()=" + getDiasVencimiento()
				+ ", getSignoError()=" + getSignoError()
				+ ", getCodigoError()=" + getCodigoError()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}		
	
	
	
	
	

	

}
