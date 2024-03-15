/*
 * Creado el 09/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.pago.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Vector;

import pe.bn.pago.domain.PagoPrestamoMultired;
import pe.cosapi.common.Constante;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;

import pe.cosapi.domain.impl.PagoImpl;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class PagoPrestamoMultiredImpl extends PagoImpl implements PagoPrestamoMultired, Serializable {
	private Cuenta cuenta;
	private String nroPrestamo;
	private String cliente;
	private String nroCuota;
	
	private String nroDesembolso;
	
	private BigDecimal intereses;
	private BigDecimal inteMoratorios;
	private BigDecimal inteCompesatorios;
	private BigDecimal nroDiasInteres;
	private BigDecimal nroDiasInteresMoratorio;
	private BigDecimal nroDiasInteresCompensatorio;

	private Timestamp fechaVencimiento;
	private BigDecimal deuda;	
	private BigDecimal amortizacion;
	
	private boolean esPagoTotal;
	
	private BigDecimal indicadorCuotaRetenida;

	
	/**
	 * @return Devuelve cliente.
	 */
	public String getCliente() {
		return cliente;
	}
	/**
	 * @param cliente El cliente a establecer.
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	/**
	 * @return Devuelve cuenta.
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}
	/**
	 * @param cuenta El cuenta a establecer.
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	/**
	 * @return Devuelve deuda.
	 */
	public String getDeuda() {
		return ObjectUtil.formatearMonto(deuda);
	}
	/**
	 * @param deuda El deuda a establecer.
	 */
	public void setDeuda(BigDecimal deuda) {
		this.deuda = deuda;
	}
	/**
	 * @return Devuelve fechaVencimiento.
	 */
	public Timestamp getFechaVencimiento() {
		return fechaVencimiento;
	}
	/**
	 * @param fechaVencimiento El fechaVencimiento a establecer.
	 */
	public void setFechaVencimiento(Timestamp fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	/**
	 * @return Returns the nroDesembolso.
	 */
	public String getNroDesembolso() {
		return nroDesembolso;
	}
	/**
	 * @param nroDesembolso The nroDesembolso to set.
	 */
	public void setNroDesembolso(String nroDesembolso) {
		this.nroDesembolso = nroDesembolso;
	}

	/**
	 * @return Returns the inteCompesatorios.
	 */
	public BigDecimal getInteCompesatorios() {
		return inteCompesatorios;
	}
	/**
	 * @param inteCompesatorios The inteCompesatorios to set.
	 */
	public void setInteCompesatorios(BigDecimal inteCompesatorios) {
		this.inteCompesatorios = inteCompesatorios;
	}
	/**
	 * @return Returns the inteMoratorios.
	 */
	public BigDecimal getInteMoratorios() {
		return inteMoratorios;
	}
	/**
	 * @param inteMoratorios The inteMoratorios to set.
	 */
	public void setInteMoratorios(BigDecimal inteMoratorios) {
		this.inteMoratorios = inteMoratorios;
	}
	/**
	 * @return Returns the intereses.
	 */
	public BigDecimal getIntereses() {
		return intereses;
	}
	/**
	 * @param intereses The intereses to set.
	 */
	public void setIntereses(BigDecimal intereses) {
		this.intereses = intereses;
	}
	/**
	 * @return Returns the nroDiasInteres.
	 */
	public BigDecimal getNroDiasInteres() {
		return nroDiasInteres;
	}
	/**
	 * @param nroDiasInteres The nroDiasInteres to set.
	 */
	public void setNroDiasInteres(BigDecimal nroDiasInteres) {
		this.nroDiasInteres = nroDiasInteres;
		
	}
	/**
	 * @return Returns the nroDiasInteresCompensatorio.
	 */
	public BigDecimal getNroDiasInteresCompensatorio() {
		return nroDiasInteresCompensatorio;
	}
	/**
	 * @param nroDiasInteresCompensatorio The nroDiasInteresCompensatorio to set.
	 */
	public void setNroDiasInteresCompensatorio(
			BigDecimal nroDiasInteresCompensatorio) {
		this.nroDiasInteresCompensatorio = nroDiasInteresCompensatorio;
	}
	/**
	 * @return Returns the nroDiasInteresMoratorio.
	 */
	public BigDecimal getNroDiasInteresMoratorio() {
		return nroDiasInteresMoratorio;
	}
	/**
	 * @param nroDiasInteresMoratorio The nroDiasInteresMoratorio to set.
	 */
	public void setNroDiasInteresMoratorio(BigDecimal nroDiasInteresMoratorio) {
		this.nroDiasInteresMoratorio = nroDiasInteresMoratorio;
	}

	public String getNroPrestamo() {
		return nroPrestamo;
	}
	/**
	 * @param nroPrestamo El nroPrestamo a establecer.
	 */
	public void setNroPrestamo(String nroPrestamo) {
		this.nroPrestamo = nroPrestamo;
	}

	public void getPrestamoMultired(Transaction t, Cuenta cuenta, Usuario usuario) throws Exception{
		boolean sw = true;
		Vector mascara = procesar(t);
		this.cuenta = cuenta;
		
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_1)){
				String result = elemento.elementAt(6).toString();
				result = result.substring(result.length()-2);
				String cuentaForm = "";
				cuentaForm = ObjectUtil.formatearCuenta(this.getCuenta().getNumeroProducto(),Constante.FORMATO_CUENTA);
				String desembolso = result;
				result = cuentaForm+"-"+result;
				this.setNroPrestamo(result);
				this.setNroDesembolso(desembolso);
				//this.setNroPrestamo(elemento.elementAt(6).toString());
			}
			if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_20)){
				this.setCliente(elemento.elementAt(6).toString());
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_2)){
				this.setNroCuota(String.valueOf(Integer.parseInt(elemento.elementAt(6).toString())));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_21)){
				this.setFechaVencimiento(ObjectUtil.tramaToTimestamp(elemento.elementAt(6).toString().trim(),"dd-MM-yyyy"));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_7)){
				this.setImporte(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString()));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_19)){
				this.setDeuda(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString()));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_25)){
				this.setIndicadorCuotaRetenida(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString(), 0));
			}

			
			//else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_22)){
			//	this.setNroOperacion(elemento.elementAt(6).toString());
			//}
			//else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_23)){
				//this.setNroSecuencia(elemento.elementAt(6).toString());
			//}
		}
		generarLog(t,usuario,null);
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
	}
	
	public void pagarPrestamoMultired(Transaction t, Usuario usuario) throws Exception{
		
	 	String importeSinFormato = ObjectUtil.replaceChar(this.getImporte(),',',"");
		importeSinFormato = ObjectUtil.replaceChar(importeSinFormato,'.',"");
		BigDecimal importeX = ObjectUtil.tramaToBigDecimal(importeSinFormato);
		//FacadeFactory.getGeneralFacade().validarLimiteImporteHost(usuario,this.getCuenta(),importeX);
		
		Vector resultado= procesar(t);

		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_1)){
				this.setNroOperacion(elemento.elementAt(6).toString());
			}			
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_2)){
				this.setDeuda(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString()));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_3)){
				this.setItf(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString()));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_4)){
				this.setIntereses(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString()));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_5)){
				this.setInteMoratorios(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString()));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_6)){
				this.setInteCompesatorios(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString()));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_7)){
				this.setNroDiasInteres(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString(), 0));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_9)){
				this.setNroDiasInteresMoratorio(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString(),0));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_8)){
				this.setNroDiasInteresCompensatorio(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString(), 0));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_10)){
				this.setNroTransaccion(elemento.elementAt(6).toString());
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_11)){
				this.setAmortizacion(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString()));
			}
	
		}
		
		if (this.getNroCuota().equals("99")){
			this.setNroCuota("Total");
		}
		
		t.setObjeto(this);
		generarLog(t,usuario,Constante.REFRENDO_PAGO_MULTIRED);
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
		//this.setNroOperacion("00000000000");
	}
	/**
	 * @return Devuelve nroCuota.
	 */
	public String getNroCuota() {
		return nroCuota;
	}
	/**
	 * @param nroCuota El nroCuota a establecer.
	 */
	public void setNroCuota(String nroCuota) {
		this.nroCuota = nroCuota;
	}
	/**
	 * @return Devuelve esPagoTotal.
	 */
	public boolean getEsPagoTotal() {
		return esPagoTotal;
	}
	/**
	 * @param esPagoTotal El esPagoTotal a establecer.
	 */
	public void setEsPagoTotal(boolean esPagoTotal) {
		this.esPagoTotal = esPagoTotal;
	}
	/**
	 * @return Returns the indicadorCuotaRetenida.
	 */
	public BigDecimal getIndicadorCuotaRetenida() {
		return indicadorCuotaRetenida;
	}
	/**
	 * @param indicadorCuotaRetenida The indicadorCuotaRetenida to set.
	 */
	public void setIndicadorCuotaRetenida(BigDecimal indicadorCuotaRetenida) {
		this.indicadorCuotaRetenida = indicadorCuotaRetenida;
	}
	
	

	/**
	 * @return Returns the amortizacion.
	 */
	public BigDecimal getAmortizacion() {
		return amortizacion;
	}
	/**
	 * @param amortizacion The amortizacion to set.
	 */
	public void setAmortizacion(BigDecimal amortizacion) {
		this.amortizacion = amortizacion;
	}
}
