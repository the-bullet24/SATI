/*
 * Creado el 20/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain;

import java.io.Serializable;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface Operacion extends Serializable {
	/**
	 * @return Devuelve fecha.
	 */
	public abstract String getFecha();

	/**
	 * @return Devuelve hora.
	 */
	public abstract String getHora();

	/**
	 * @return Devuelve nroOperacion.
	 */
	public abstract String getNroOperacion();
	
	/*
	 * @return Devuelve canal.
	 */
	public abstract String getCanal();
	
	public abstract String getTipoToken();
	
	
}