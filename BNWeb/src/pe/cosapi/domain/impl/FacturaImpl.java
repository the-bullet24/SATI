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

import pe.cosapi.domain.Factura;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ObjectUtil;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class FacturaImpl implements Factura, Serializable,Cloneable {
	private Timestamp fecha;
	private String secuencia;
	private String numRecibo; 
	private String cuota;
	private String codClient;
	private BigDecimal importe;
	private BigDecimal impCuota;
	private BigDecimal impMinimo;
	private BigDecimal interes;
	private BigDecimal interesCom;
	private BigDecimal comision;
	private BigDecimal mora;
	private BigDecimal gasto;
	private String moneda;
	private String codMoneda;
	private String nombreMoneda;
	private String simboloMoneda;
	private String glosa;
	private String secuenciaMenor;
	private String concepto;
	private String nroReferencia;
	
	
	/**
	 * @return Returns the glosa.
	 */
	public String getGlosa() {
		return glosa;
	}
	/**
	 * @param glosa The glosa to set.
	 */
	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}
	
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
	 * @return Returns the codClient.
	 */
	public String getCodClient() {
		return codClient;
	}
	/**
	 * @param codClient The codClient to set.
	 */
	public void setCodClient(String codClient) {
		this.codClient = codClient;
	}
	/**
	 * @return Returns the codMoneda.
	 */
	public String getCodMoneda() {
		return codMoneda;
	}
	/**
	 * @param codMoneda The codMoneda to set.
	 */
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	/**
	 * @return Returns the comision.
	 */
	public String getComision() {
		return ObjectUtil.formatearMonto(comision);
	}
	/**
	 * @param comision The comision to set.
	 */
	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}
	/**
	 * @return Returns the cuota.
	 */
	public String getCuota() {
		return cuota;
	}
	/**
	 * @param cuota The cuota to set.
	 */
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}
	/**
	 * @return Returns the fecha.
	 */
	public Timestamp getFecha() {
		return fecha;
	}
	/**
	 * @param fecha The fecha to set.
	 */
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return Returns the gasto.
	 */
	public String getGasto() {
		return ObjectUtil.formatearMonto(gasto);
	}
	/**
	 * @param gasto The gasto to set.
	 */
	public void setGasto(BigDecimal gasto) {
		this.gasto = gasto;
	}
	/**
	 * @return Returns the impCuota.
	 */
	public String getImpCuota() {
		return ObjectUtil.formatearMonto(impCuota);
	}
	/**
	 * @param impCuota The impCuota to set.
	 */
	public void setImpCuota(BigDecimal impCuota) {
		this.impCuota = impCuota;
	}
	/**
	 * @return Returns the impMinimo.
	 */
	public String getImpMinimo() {
		return ObjectUtil.formatearMonto(impMinimo);
	}
	/**
	 * @param impMinimo The impMinimo to set.
	 */
	public void setImpMinimo(BigDecimal impMinimo) {
		this.impMinimo = impMinimo;
	}
	/**
	 * @return Devuelve importe.
	 */
	public String getImporte() {
		return ObjectUtil.formatearMonto(importe);
	}
	/**
	 * @param importe The importe to set.
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	/**
	 * @return Returns the interes.
	 */
	public String getInteres() {
		return ObjectUtil.formatearMonto(interes);
	}
	/**
	 * @param interes The interes to set.
	 */
	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}
	/**
	 * @return Returns the interes.
	 */
	public String getInteresCom() {
		return ObjectUtil.formatearMonto(interesCom);
	}
	/**
	 * @param interes The interes to set.
	 */
	public void setInteresCom(BigDecimal interesCom) {
		this.interesCom = interesCom;
	}
	/**
	 * @return Returns the moneda.
	 */
	public String getMoneda() {
		return moneda;
	}
	/**
	 * @param moneda The moneda to set.
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	/**
	 * @return Returns the mora.
	 */
	public String getMora() {
		return ObjectUtil.formatearMonto(mora);
	}
	/**
	 * @param mora The mora to set.
	 */
	public void setMora(BigDecimal mora) {
		this.mora = mora;
	}
	/**
	 * @return Returns the nroReferencia.
	 */
	public String getNumRecibo() {
		return numRecibo;
	}
	/**
	 * @param nroReferencia The nroReferencia to set.
	 */
	public void setNumRecibo(String numRecibo) {
		this.numRecibo = numRecibo;
	}
	/**
	 * @return Returns the secuencia.
	 */
	public String getSecuencia() {
		return secuencia;
	}
	/**
	 * @param secuencia The secuencia to set.
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}
	
	
	
	public String getNroReferencia() {
		return nroReferencia;
	}
	public void setNroReferencia(String nroReferencia) {
		this.nroReferencia = nroReferencia;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getSecuenciaMenor() {
		return secuenciaMenor;
	}
	public void setSecuenciaMenor(String secuenciaMenor) {
		this.secuenciaMenor = secuenciaMenor;
	}
	/* (sin Javadoc)
	 * @see java.lang.Object#clone()
	 */
	protected Object clone() throws CloneNotSupportedException {		
		return super.clone();
	}
}
