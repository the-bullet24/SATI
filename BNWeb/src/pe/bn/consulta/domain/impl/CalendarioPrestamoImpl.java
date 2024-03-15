/*
 * Creado el 05/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.consulta.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import pe.bn.consulta.domain.CalendarioPrestamo;
import pe.cosapi.common.ObjectUtil;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class CalendarioPrestamoImpl implements CalendarioPrestamo, Serializable {
	private String id;
	private Timestamp fecha;
	private String concepto;
	private BigDecimal amortizacion;
	private BigDecimal cuota;
	private BigDecimal saldo;
	private BigDecimal seguro;
	private int nroDias;
	private BigDecimal interes;
	private String estado;
	
	
	public BigDecimal getSeguro() {
		return seguro;
	}
	public void setSeguro(BigDecimal seguro) {
		this.seguro = seguro;
	}
	/**
	 * @return Devuelve amortizacion.
	 */
	public String getAmortizacion() {
		return ObjectUtil.formatearMonto(amortizacion);
	}
	/**
	 * @param amortizacion El amortizacion a establecer.
	 */
	public void setAmortizacion(BigDecimal amortizacion) {
		this.amortizacion = amortizacion;
	}
	/**
	 * @return Devuelve concepto.
	 */
	public String getConcepto() {
		return concepto;
	}
	/**
	 * @param concepto El concepto a establecer.
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	/**
	 * @return Devuelve fecha.
	 */
	public Timestamp getFecha() {
		return fecha;
	}
	/**
	 * @param fecha El fecha a establecer.
	 */
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return Devuelve interes.
	 */
	public String getInteres() {
		return ObjectUtil.formatearMonto(interes);
	}
	/**
	 * @param interes El interes a establecer.
	 */
	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}
	/**
	 * @return Devuelve nroDias.
	 */
	public int getNroDias() {
		return nroDias;
	}
	/**
	 * @param nroDias El nroDias a establecer.
	 */
	public void setNroDias(int nroDias) {
		this.nroDias = nroDias;
	}
	/**
	 * @return Devuelve saldo.
	 */
	public String getSaldo() {
		return ObjectUtil.formatearMonto(saldo);
	}
	/**
	 * @param saldo El saldo a establecer.
	 */
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	/**
	 * @return Devuelve estado.
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado El estado a establecer.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return Devuelve cuota.
	 */
	public String getCuota() {
		return ObjectUtil.formatearMonto(cuota);
	}
	/**
	 * @param cuota El cuota a establecer.
	 */
	public void setCuota(BigDecimal cuota) {
		this.cuota = cuota;
	}
	/**
	 * @return Devuelve id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id El id a establecer.
	 */
	public void setId(String id) {
		this.id = id;
	}
}
