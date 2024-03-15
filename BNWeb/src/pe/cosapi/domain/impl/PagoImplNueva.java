/*
 * Fecha 15/07/2009
 * Creado por Wilder A. Hernandez Nuñez
 */
package pe.cosapi.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Pago;

/**
 * @author cosapi_ati05
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class PagoImplNueva extends OperacionImplNueva implements Pago, Serializable{
	private BigDecimal importe;
	private BigDecimal comision;
	private BigDecimal itf;
	private BigDecimal total;
	private BigDecimal importeAlCambio;
	private String flagRefrendo;
	
	
	public String getFlagRefrendo() {
		return flagRefrendo;
	}
	public void setFlagRefrendo(String flagRefrendo) {
		this.flagRefrendo = flagRefrendo;
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
}
