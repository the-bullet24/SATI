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

import pe.cosapi.domain.Movimiento;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class MovimientoImpl implements Movimiento, Serializable {
	private String secuencia;
	private String abono;
	private String cargo;
	private Timestamp fecha;
	private String fechaFormat;
	private String concepto;
	private String desConcepto;
	private BigDecimal importe;
	private String monto;
	private String signo;
	private String ruc;
	private String nroCheque;
	private String oficina;
	private String moneda;
	
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
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
	 * @return Devuelve importe.
	 */
	public BigDecimal getImporte() {
		return importe;
	}
	/**
	 * @param importe El importe a establecer.
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
    public String getMonto() {
        return monto;
    }
    public void setMonto(String monto) {
        this.monto = monto;
    }
	/**
	 * @return Devuelve desConcepto.
	 */
	public String getDesConcepto() {
		return desConcepto;
	}
	/**
	 * @param desConcepto El desConcepto a establecer.
	 */
	public void setDesConcepto(String desConcepto) {
		this.desConcepto = desConcepto;
	}
	/**
	 * @return Devuelve signo.
	 */
	public String getSigno() {
		return signo;
	}
	/**
	 * @param signo El signo a establecer.
	 */
	public void setSigno(String signo) {
		this.signo = signo;
	}
	/**
	 * @return Devuelve ruc.
	 */
	public String getRuc() {
		return ruc;
	}
	/**
	 * @param ruc El ruc a establecer.
	 */
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	/**
	 * @return Devuelve nroCheque.
	 */
	public String getNroCheque() {
		return nroCheque;
	}
	/**
	 * @param nroCheque El nroCheque a establecer.
	 */
	public void setNroCheque(String nroCheque) {
		this.nroCheque = nroCheque;
	}
	/**
	 * @return Devuelve oficina.
	 */
	public String getOficina() {
		return oficina;
	}
	/**
	 * @param oficina El oficina a establecer.
	 */
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	/**
	 * @return Devuelve fechaFormat.
	 */
	public String getFechaFormat() {
		return fechaFormat;
	}
	/**
	 * @param fechaFormat El fechaFormat a establecer.
	 */
	public void setFechaFormat(String fechaFormat) {
		this.fechaFormat = fechaFormat;
	}
	/**
	 * @return Devuelve secuencia.
	 */
	public String getSecuencia() {
		return secuencia;
	}
	/**
	 * @param secuencia El secuencia a establecer.
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}
	/**
	 * @return Devuelve abono.
	 */
	public String getAbono() {
		return abono;
	}
	/**
	 * @param abono El abono a establecer.
	 */
	public void setAbono(String abono) {
		this.abono = abono;
	}
	/**
	 * @return Devuelve cargo.
	 */
	public String getCargo() {
		return cargo;
	}
	/**
	 * @param cargo El cargo a establecer.
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
}
