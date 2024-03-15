
package pe.cosapi.domain.impl;

import java.io.Serializable;
import pe.cosapi.domain.Notificacion;


public class NotificacionImpl implements Notificacion, Serializable{
	
	private String estadoNotificacion;
	private String montoNotificacion;
	private String tipoMonedaNotificacion;
	private String medioNotificacion;


	public String getEstadoNotificacion() {
		return estadoNotificacion;
	}
	public void setEstadoNotificacion(String estadoNotificacion) {
		this.estadoNotificacion = estadoNotificacion;
	}
	public String getMedioNotificacion() {
		return medioNotificacion;
	}
	public void setMedioNotificacion(String medioNotificacion) {
		this.medioNotificacion = medioNotificacion;
	}
	public String getMontoNotificacion() {
		return montoNotificacion;
	}
	public void setMontoNotificacion(String montoNotificacion) {
		this.montoNotificacion = montoNotificacion;
	}
	public String getTipoMonedaNotificacion() {
		return tipoMonedaNotificacion;
	}
	public void setTipoMonedaNotificacion(String tipoMonedaNotificacion) {
		this.tipoMonedaNotificacion = tipoMonedaNotificacion;
	}
	public String celular() {
		// TODO Apéndice de método generado automáticamente
		return null;
	}
	public String correo() {
		// TODO Apéndice de método generado automáticamente
		return null;
	}
	public String operador() {
		// TODO Apéndice de método generado automáticamente
		return null;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NotificacionImpl [estadoNotificacion=");
		builder.append(estadoNotificacion);
		builder.append(", montoNotificacion=");
		builder.append(montoNotificacion);
		builder.append(", tipoMonedaNotificacion=");
		builder.append(tipoMonedaNotificacion);
		builder.append(", medioNotificacion=");
		builder.append(medioNotificacion);
		builder.append("]");
		return builder.toString();
	}
	
	

	

}
