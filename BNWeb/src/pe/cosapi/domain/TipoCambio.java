/*
 * Creado el 19/04/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface TipoCambio extends Operacion, Serializable{
	/**
	 * @return Devuelve compra.
	 */
	public abstract BigDecimal getCompra();

	/**
	 * @return Devuelve impuesto.
	 */
	public abstract BigDecimal getImpuesto();

	/**
	 * @return Devuelve pasaporte.
	 */
	public abstract BigDecimal getPasaporte();

	/**
	 * @return Devuelve venta.
	 */
	public abstract BigDecimal getVenta();
	
	/**
	 * @return Devuelve compra.
	 */
	public abstract BigDecimal getCompraMuestra();
	
	/**
	 * @return Devuelve venta.
	 */
	public abstract BigDecimal getVentaMuestra();
}