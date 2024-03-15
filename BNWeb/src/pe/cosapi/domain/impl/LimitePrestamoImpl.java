/*
 * Creado el 03/05/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain.impl;

import java.math.BigDecimal;
import java.util.Vector;

import pe.cosapi.domain.LimitePrestamo;
import pe.cosapi.domain.Usuario;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class LimitePrestamoImpl extends OperacionImpl implements LimitePrestamo{

	private String leyenda;
	private BigDecimal limite;
	
	
	/**
	 * 
	 */
	public LimitePrestamoImpl(String transaccion, Vector datos, Vector cuentas, Usuario usuario) {
		this.leyenda	= "María de los Ángeles";
		this.limite		= new BigDecimal("3500.25");
	}
	
	/**
	 * @return Devuelve leyenda.
	 */
	public String getLeyenda() {
		return leyenda;
	}
	/**
	 * @param leyenda El leyenda a establecer.
	 */
	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}
	/**
	 * @return Devuelve limite.
	 */
	public BigDecimal getLimite() {
		return limite;
	}
	/**
	 * @param limite El limite a establecer.
	 */
	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}
}
