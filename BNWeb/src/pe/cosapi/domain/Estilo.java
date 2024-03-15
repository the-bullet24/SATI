/*
 * Creado el 07/03/2007
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
public interface Estilo extends Serializable {
	/**
	 * @return Devuelve background.
	 */
	public abstract String getBackground();

	/**
	 * @return Devuelve id.
	 */
	public abstract String getId();

	/**
	 * @return Devuelve row_1.
	 */
	public abstract String getRow_1();

	/**
	 * @return Devuelve row_2.
	 */
	public abstract String getRow_2();

	/**
	 * @return Devuelve subtitle.
	 */
	public abstract String getSubtitle();

	/**
	 * @return Devuelve title.
	 */
	public abstract String getTitle();
}