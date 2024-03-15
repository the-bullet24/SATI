/*
 * Creado el 09/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface Movimiento extends Serializable {
	/**
	 * @return Devuelve concepto.
	 */
	public abstract String getConcepto();

	/**
	 * @return Devuelve fecha.
	 */
	public abstract Timestamp getFecha();

	/**
	 * @return Devuelve importe.
	 */
	public abstract BigDecimal getImporte();
	
	/**
	 * @return Devuelve monto.
	 */
	public abstract String getMonto();
	
	public abstract String getSigno();
	
	public abstract String getDesConcepto();
	
	public abstract String getRuc();
	
	public abstract String getNroCheque();
	
	public abstract String getOficina();
	
	public abstract String getFechaFormat() ;
	
	public String getSecuencia();
	public String getAbono();
	public String getCargo();
	
	public abstract String getMoneda() ;
}
