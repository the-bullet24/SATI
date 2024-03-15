/*
 * Creado el 14/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.telegiro.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Vector;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.telegiro.action.TelegiroAction;
import pe.bn.telegiro.domain.Telegiro;
import pe.cosapi.common.Constante;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Agencia;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.PagoImpl;
import pe.cosapi.domain.impl.PagoImplNueva;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TelegiroImpl extends PagoImplNueva implements Telegiro, Serializable {
	private Cuenta cuenta;
	private Afiliacion afiliacion;
	private Agencia agencia;
	private String nroGiro;
	private String claveGiro;
	
	
	public String getClaveGiro() {
		return claveGiro;
	}

	public void setClaveGiro(String claveGiro) {
		this.claveGiro = claveGiro;
	}

	/**
	 * 
	 */
	public TelegiroImpl() {
		
	}

	public TelegiroImpl(Transaction t,Cuenta cuenta, Afiliacion afiliacion, BigDecimal importe, Agencia agencia, Usuario usuario) throws Exception{
		
		
		this.afiliacion = afiliacion;
		this.cuenta = cuenta;

		this.agencia 	= agencia;

		
		
		Vector resultado = procesar(t);
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				setImporte(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString()));
			
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				//
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				setComision(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString()));
			
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
				setItf(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString()));
				this.setTotal((new BigDecimal(getComision())).add((importe).add(new BigDecimal(getItf()))));
			}	
			

		}
		
		
		
		
		
		//generarLog(t,usuario,null);
		if(getArrayRuleException()!=null) 
			throw getArrayRuleException();
		
	}
	
	/**
	 * @return Devuelve afiliacion.
	 */
	public Afiliacion getAfiliacion() {
		return afiliacion;
	}
	/**
	 * @return Devuelve cuenta.
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}

	/**
	 * @param afiliacion El afiliacion a establecer.
	 */
	public void setAfiliacion(Afiliacion afiliacion) {
		this.afiliacion = afiliacion;
	}
	/**
	 * @param cuenta El cuenta a establecer.
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	/**
	 * @param importe El importe a establecer.
	 */
	/**
	 * @return Devuelve comision.
	 */
	/**
	 * @return Devuelve itf.
	 */
	
	public void pagar(Transaction t, Usuario usuario) throws Exception{
	
		Vector resultado = procesar(t);
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				setNroOperacion(element.elementAt(6).toString());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				setNroGiro(element.elementAt(6).toString());
			}	
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				setImporte(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString()));
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				setComision(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString()));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
				setItf(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString()));
				this.setTotal((new BigDecimal(getComision())).add(ObjectUtil.deFormatearMonto(getImporte())).add(new BigDecimal(getItf())));
						
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
				setClaveGiro(element.elementAt(6).toString().substring(10,15));
			
			}
		}
		
		t.setObjeto(this);
		//@DPS generarLog(t,usuario,Constante.REFRENDO_TELEGIRO);
		//@DPS if(getArrayRuleException()!=null) 
		//@DPS	throw getArrayRuleException();
	}
	/**
	 * @return Devuelve agencia.
	 */
	public Agencia getAgencia() {
		return agencia;
	}
	/**
	 * @param agencia El agencia a establecer.
	 */
	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	/**
	 * @return Devuelve nroGiro.
	 */
	public String getNroGiro() {
		return nroGiro;
	}
	/**
	 * @param nroGiro El nroGiro a establecer.
	 */
	public void setNroGiro(String nroGiro) {
		this.nroGiro = nroGiro;
	}
}
