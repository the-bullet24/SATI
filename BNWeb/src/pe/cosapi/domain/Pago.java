/*
 * Creado el 13/04/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
package pe.cosapi.domain;

import java.io.Serializable;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
public interface Pago extends Operacion, Serializable{
	/**
	 * @return Devuelve comision.
	 */
	public abstract String getComision();

	/**
	 * @return Devuelve importe.
	 */
	public abstract String getImporte();

	/**
	 * @return Devuelve itf.
	 */
	public abstract String getItf();

	/**
	 * @return Devuelve total.
	 */
	public abstract String getTotal();
	
	public String getImporteAlCambio();
}