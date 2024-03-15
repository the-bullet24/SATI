package pe.bn.login.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import pe.bn.login.domain.Menu;
import pe.cosapi.common.DAOFactory;

public class FechaIngresoImpl implements Serializable {
	
	private String 		numSecuencia;
	private String 		numTarjeta;
	private String 		fechaIngreso;
	private String 		fechaAnterior;
	private String 		fecha;
	private String 		hora;
	private String 		ipIngreso;
	private String 		ipAnterior;
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getFechaAnterior() {
		return fechaAnterior;
	}
	public void setFechaAnterior(String fechaAnterior) {
		this.fechaAnterior = fechaAnterior;
	}
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getIpAnterior() {
		return ipAnterior;
	}
	public void setIpAnterior(String ipAnterior) {
		this.ipAnterior = ipAnterior;
	}
	public String getIpIngreso() {
		return ipIngreso;
	}
	public void setIpIngreso(String ipIngreso) {
		this.ipIngreso = ipIngreso;
	}
	public String getNumSecuencia() {
		return numSecuencia;
	}
	public void setNumSecuencia(String numSecuencia) {
		this.numSecuencia = numSecuencia;
	}
	public String getNumTarjeta() {
		return numTarjeta;
	}
	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

}
