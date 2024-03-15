package pe.bn.notificaciones.form;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import pe.cosapi.common.Constante;

public class NotificacionSms {
	private String toperacion;
	private String canal;
	private String moneda;
	private String importe;
	private String foperacion;
	private String hoperacion;
	
	private String celular;
	private String operadora;
	
	public NotificacionSms(String toperacion, String moneda, String importe, String celular, String operadora){
		this.toperacion = toperacion;
		this.moneda = moneda;
		this.importe = importe;
		this.celular = celular;
		this.operadora = operadora;
		
	}

	public String getCanal() {
		this.canal=Constante.NOTI_CANAL;
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getFoperacion() {
		SimpleDateFormat formatter;
		Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
		formatter = new SimpleDateFormat("dd/MM/yyyy");			
		Date data = new Date(fechaActual.getTime());
		this.foperacion = formatter.format(data);
		return foperacion;
	}
	public void setFoperacion(String foperacion) {
		this.foperacion = foperacion;
	}
	public String getHoperacion() {
		SimpleDateFormat formatter;
		Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
		formatter = new SimpleDateFormat("HH:mm:ss");
		Date data = new Date(fechaActual.getTime());
		this.hoperacion = formatter.format(data);
		return hoperacion;
	}
	public void setHoperacion(String hoperacion) {
		this.hoperacion = hoperacion;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getToperacion() {
		return toperacion;
	}

	public void setToperacion(String toperacion) {
		this.toperacion = toperacion;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getOperadora() {
		if(this.operadora.equals(Constante.OPERADORA_CLARO)){
			setOperadora(Constante.CARRIER_CLARO);
		}else if(this.operadora.equals(Constante.OPERADORA_MOVISTAR)){
			setOperadora(Constante.CARRIER_MOVISTAR);
		}
		return operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}
	
	
}
