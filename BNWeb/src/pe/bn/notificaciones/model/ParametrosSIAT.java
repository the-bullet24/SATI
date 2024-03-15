package pe.bn.notificaciones.model;

import java.math.BigDecimal;

public class ParametrosSIAT {
	private String notioperacion;
	private int tiponoti;
	private String email;
	private String telefono;
	private String idtelefono;
	private String ntarjeta;
	private BigDecimal importe;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdtelefono() {
		return idtelefono;
	}
	public void setIdtelefono(String idtelefono) {
		this.idtelefono = idtelefono;
	}
	public String getNotioperacion() {
		return notioperacion;
	}
	public void setNotioperacion(String notioperacion) {
		this.notioperacion = notioperacion;
	}
	public String getNtarjeta() {
		return ntarjeta;
	}
	public void setNtarjeta(String ntarjeta) {
		this.ntarjeta = ntarjeta;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getTiponoti() {
		return tiponoti;
	}
	public void setTiponoti(int tiponoti) {
		this.tiponoti = tiponoti;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
}
