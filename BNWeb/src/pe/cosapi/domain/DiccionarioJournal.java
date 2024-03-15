/*
 * Creado el 03/03/2007
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
public interface DiccionarioJournal extends Serializable {
	/**
	 * @return Devuelve campoJournal.
	 */
	public abstract String getCampoJournal();

	/**
	 * @return Devuelve codigoDiccionario.
	 */
	public abstract String getCodigoDiccionario();

	/**
	 * @return Devuelve idTransaccion.
	 */
	public abstract String getIdTransaccion();
}