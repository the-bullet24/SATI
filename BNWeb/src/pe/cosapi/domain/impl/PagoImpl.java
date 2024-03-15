/*
 * Creado el 13/04/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Pago;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class PagoImpl extends OperacionImpl implements Pago, Serializable {
	private BigDecimal importe;
	private BigDecimal comision;
	private BigDecimal itf;
	private BigDecimal total;
	private BigDecimal importeAlCambio;
	private BigDecimal comp;
	private BigDecimal gasto;
	private BigDecimal mora;
	private BigDecimal importeTotal;
	private String nomCliente;
	
	
	
	public String getNomCliente() {
		return nomCliente;
	}
	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}
	/**
	 * @return Returns the comp.
	 */
	public BigDecimal getComp() {
		return comp;
	}
	/**
	 * @param comp The comp to set.
	 */
	public void setComp(BigDecimal comp) {
		this.comp = comp;
	}
	/**
	 * @return Returns the gasto.
	 */
	public BigDecimal getGasto() {
		return gasto;
	}
	/**
	 * @param gasto The gasto to set.
	 */
	public void setGasto(BigDecimal gasto) {
		this.gasto = gasto;
	}
	/**
	 * @return Returns the mora.
	 */
	public BigDecimal getMora() {
		return mora;
	}
	/**
	 * @param mora The mora to set.
	 */
	public void setMora(BigDecimal mora) {
		this.mora = mora;
	}
	/**
	 * @return Devuelve comision.
	 */
	public String getComision() {
		return ObjectUtil.formatearMonto(comision);
	}
	/**
	 * @return Devuelve importe.
	 */
	public String getImporte() {
		return ObjectUtil.formatearMonto(importe);
	}
	/**
	 * @return Devuelve itf.
	 */
	public String getItf() {
		return ObjectUtil.formatearMonto(itf);
	}
	/**
	 * @return Devuelve total.
	 */
	public String getTotal() {
		return ObjectUtil.formatearMonto(total);
	}
	/**
	 * @param comision El comision a establecer.
	 */
	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}
	/**
	 * @param importe El importe a establecer.
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	/**
	 * @param itf El itf a establecer.
	 */
	public void setItf(BigDecimal itf) {
		this.itf = itf;
	}
	/**
	 * @param total El total a establecer.
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	/**
	 * @return Devuelve importeAlCambio.
	 */
	public String getImporteAlCambio() {
		return ObjectUtil.formatearMonto(importeAlCambio);
	}
	/**
	 * @param importeAlCambio El importeAlCambio a establecer.
	 */
	public void setImporteAlCambio(BigDecimal importeAlCambio) {
		this.importeAlCambio = importeAlCambio;
	}
	/**
	 * @return Returns the importeTotal.
	 */
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}
	/**
	 * @param importeTotal The importeTotal to set.
	 */
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}
}
