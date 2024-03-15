/*
 * Creado el 05/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.consulta.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface CalendarioPrestamo extends Serializable{
	/**
	 * @return Devuelve amortizacion.
	 */
	public String getAmortizacion();

	/**
	 * @return Devuelve concepto.
	 */
	public String getConcepto();

	/**
	 * @return Devuelve fecha.
	 */
	public Timestamp getFecha();

	/**
	 * @return Devuelve interes.
	 */
	public String getInteres();

	/**
	 * @return Devuelve nroDias.
	 */
	public int getNroDias();

	/**
	 * @return Devuelve saldo.
	 */
	public String getSaldo();
	
	public String getEstado();
	
	public String getCuota();
	
	public String getId();
	
}