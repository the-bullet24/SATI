/*
 * Creado el 09/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface Factura extends Serializable {
	/**
	 * @return Devuelve secuencia.
	 */
	public abstract String getSecuencia();
	
	/**
	 * @return Devuelve numRecibo.
	 */
	public abstract String getNumRecibo();

	/**
	 * @return Devuelve fecha.
	 */
	public abstract Timestamp getFecha();
	
	/**
	 * @return Devuelve moneda.
	 */
	public abstract String getMoneda();

	/**
	 * @return Devuelve importe.
	 */
	public abstract String getImporte();
	
	/**
	 * @return Devuelve glosa.
	 */
	public abstract String getGlosa();
	
	/**
	 * @return Devuelve codMoneda.
	 */
	public abstract String getCodMoneda();
	
	
	/**
	 * @return Devuelve importe cuota.
	 */
	public abstract String getImpCuota();
	
	/**
	 * @return Devuelve importe mínimo.
	 */
	public abstract String getImpMinimo();
	
	/**
	 * @return Devuelve Interes.
	 */
	public abstract String getInteres();
	
	/**
	 * @return Devuelve Interes Compensatorio.
	 */
	public abstract String getInteresCom();
	
	/**
	 * @return Devuelve Comición.
	 */
	public abstract String getComision();
	
	/**
	 * @return Devuelve Mora.
	 */
	public abstract String getMora();
	
	/**
	 * @return Devuelve gasto.
	 */
	public abstract String getGasto();
	
}