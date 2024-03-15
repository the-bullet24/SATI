/*
 * Creado el 09/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import pe.cosapi.domain.Afiliaciones;
import pe.cosapi.domain.Recibo;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ObjectUtil;

/**
 * @author 
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class AfiliacionesAliasImpl implements  Serializable,Cloneable {
	
	
	private String alias;
	private String producto;
	private String moneda;
	private String numCuenta;
	private String servicio;
	private String aliasServicio;
	private List resultado;
	private String fecha;
	private String hora;
	private String cuentaFormateada;
	private String secuencia;
	private String numReferencia;
	
	
	
	public String getNumReferencia() {
		return numReferencia;
	}
	public void setNumReferencia(String numReferencia) {
		this.numReferencia = numReferencia;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getNumCuenta() {
		return numCuenta;
	}
	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getAliasServicio() {
		return aliasServicio;
	}
	public void setAliasServicio(String aliasServicio) {
		this.aliasServicio = aliasServicio;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public List getResultado() {
		return resultado;
	}
	public void setResultado(List resultado) {
		this.resultado = resultado;
	}
	public String getCuentaFormateada() {
		return cuentaFormateada;
	}
	public void setCuentaFormateada(String cuentaFormateada) {
		this.cuentaFormateada = cuentaFormateada;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

}
