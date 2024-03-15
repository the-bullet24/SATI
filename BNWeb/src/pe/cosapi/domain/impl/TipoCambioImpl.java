/*
 * Creado el 19/04/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de codigo - Plantillas de código
 */
package pe.cosapi.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Vector;

import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.TipoCambio;
import pe.cosapi.sarabank.bean.Transaction;
//import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TipoCambioImpl extends OperacionImpl implements TipoCambio,Serializable {
	private BigDecimal compra;
	private BigDecimal venta;
	private BigDecimal impuesto;
	private BigDecimal pasaporte;
	private BigDecimal compraMuestra;
	private BigDecimal ventaMuestra;

	
	public TipoCambioImpl(Vector datos, Vector cuentas) throws Exception{
		//Transaction t = new Transaction("TC01");
		//t.setValues(datos);
		//t.setCuentas(cuentas);
		//t.loadVectors();
		this.compra = new BigDecimal(3.125).setScale(3,BigDecimal.ROUND_UP);
		this.venta = new BigDecimal(3.126).setScale(3,BigDecimal.ROUND_UP);
		this.compraMuestra = new BigDecimal(3.125).setScale(3,BigDecimal.ROUND_UP);
		this.ventaMuestra = new BigDecimal(3.126).setScale(3,BigDecimal.ROUND_UP);
		this.impuesto = new BigDecimal(3.127).setScale(3,BigDecimal.ROUND_UP);
		this.pasaporte = new BigDecimal(3.128).setScale(3,BigDecimal.ROUND_UP);
	}
	
	/**
	 * 
	 */
	public TipoCambioImpl() {

		// TODO Apendice de constructor generado automaticamente
	}
	
	/**
	 * @return Devuelve compra.
	 */
	public BigDecimal getCompra() {
		return compra;
	}
	/**
	 * @return Devuelve impuesto.
	 */
	public BigDecimal getImpuesto() {
		return impuesto;
	}
	/**
	 * @return Devuelve pasaporte.
	 */
	public BigDecimal getPasaporte() {    
	
		return pasaporte;
	}
	/**
	 * @return Devuelve venta.
	 */
	public BigDecimal getVenta() {
		return venta;
	}
	/**
	 * @param compra El compra a establecer.
	 */
	public void setCompra(BigDecimal compra) {
		this.compra = compra;
	}
	/**
	 * @param impuesto El impuesto a establecer.
	 */
	public void setImpuesto(BigDecimal impuesto) {
		this.impuesto = impuesto;
	}
	/**
	 * @param pasaporte El pasaporte a establecer.
	 */
	public void setPasaporte(BigDecimal pasaporte) {
		this.pasaporte = pasaporte;
	}
	/**
	 * @param venta El venta a establecer.
	 */
	public void setVenta(BigDecimal venta) {
		this.venta = venta;
	}
	
	public void getTipoCambio(Transaction t) throws Exception{
		Vector resultado = procesar(t);
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				this.setCompra(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim(),7));
				this.setCompraMuestra(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim(),4));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				this.setVenta(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim(),7));
				this.setVentaMuestra(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim(),4));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				this.setImpuesto(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim(),7));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				this.setPasaporte(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim(),7));
			}			
		}
		t.setObjeto(this);
		UsuarioImpl usuario = new UsuarioImpl();
		usuario.setTipoIngreso(ConstanteSesion.CODIGO_COMPANIA);
		generarLog(t,usuario,null);
		
		if(getArrayRuleException()!=null) throw getArrayRuleException();
	}
	
	/**
	 * @return Devuelve compraMuestra.
	 */
	public BigDecimal getCompraMuestra() {
		return compraMuestra;
	}
	/**
	 * @param compraMuestra El compraMuestra a establecer.
	 */
	public void setCompraMuestra(BigDecimal compraMuestra) {
		this.compraMuestra = compraMuestra;
	}
	/**
	 * @return Devuelve ventaMuestra.
	 */
	public BigDecimal getVentaMuestra() {
		return ventaMuestra;
	}
	/**
	 * @param ventaMuestra El ventaMuestra a establecer.
	 */
	public void setVentaMuestra(BigDecimal ventaMuestra) {
		this.ventaMuestra = ventaMuestra;
	}
}
