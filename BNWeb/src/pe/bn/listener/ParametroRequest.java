package pe.bn.listener;

import java.io.Serializable;

public class ParametroRequest implements Serializable {
	
	private String sistema;
	private String cuenta;
	private String idUsuario;
	private byte[] clave;
	
	public String getSistema() {
		return sistema;
	}
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public byte[] getClave() {
		return clave;
	}
	public void setClave(byte[] clave) {
		this.clave = clave;
	}
	
	
}
