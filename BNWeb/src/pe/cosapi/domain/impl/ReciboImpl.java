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

import pe.cosapi.domain.Recibo;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ObjectUtil;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ReciboImpl implements Recibo, Serializable,Cloneable {
	private Timestamp fecha;
	private String concepto;
	private String secuencia;
	private String numRecibo;
	private String moneda;
	private String glosa;
	private String codMoneda;
	private String nombreMoneda;
	private String simboloMoneda;
	private BigDecimal importe;
	//Inicio 2019-M0400
	private BigDecimal mora;
	private BigDecimal importeTotal;
	//Fin 2019-M0400
	
	public String getNombreMoneda(){
		if(moneda == null)
			return null;			
		else if(moneda.equals(Constante.MONEDA_DOLAR))
			return Constante.NOMBRE_MONEDA_DOLAR;		
		else if (moneda.equals(Constante.MONEDA_SOL))
			return Constante.NOMBRE_MONEDA_SOL;
		return moneda;
	}
	
	public String getSimboloMoneda(){
		if(moneda == null)
			return null;			
		else if(moneda.equals(Constante.MONEDA_DOLAR))
			return Constante.SIMBOLO_MONEDA_DOLAR;		
		else if (moneda.equals(Constante.MONEDA_SOL))
			return Constante.SIMBOLO_MONEDA_SOL;
		return moneda;
	}
	
	/**
	 * @return Devuelve codMoneda.
	 */
	public String getCodMoneda() {
		return codMoneda;
	}
	/**
	 * @param codMoneda El codMoneda a establecer.
	 */
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
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
	 * @return Devuelve glosa.
	 */
	public String getGlosa() {
		return glosa;
	}
	/**
	 * @param glosa El glosa a establecer.
	 */
	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}
	/**
	 * @return Devuelve importe.
	 */
	public String getImporte() {
		return ObjectUtil.formatearMonto(importe);
	}
	/**
	 * @param importe El importe a establecer.
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	/**
	 * @return Devuelve moneda.
	 */
	public String getMoneda() {
		return moneda;
	}
	/**
	 * @param moneda El moneda a establecer.
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	/**
	 * @return Devuelve numRecibo.
	 */
	public String getNumRecibo() {
		return numRecibo;
	}
	/**
	 * @param numRecibo El numRecibo a establecer.
	 */
	public void setNumRecibo(String numRecibo) {
		this.numRecibo = numRecibo;
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
	//Inicio 2019-M0400
	public String getImporteTotal() {
		return ObjectUtil.formatearMonto(importeTotal);
	}

	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	public String getMora() {
		return ObjectUtil.formatearMonto(mora);
	}

	public void setMora(BigDecimal mora) {
		this.mora = mora;
	}
	//Fin 2019-M0400
	/* (sin Javadoc)
	 * @see java.lang.Object#clone()
	 */
	protected Object clone() throws CloneNotSupportedException {		
		return super.clone();
	}
}
