/*
 * Creado el 03/05/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
package pe.cosapi.domain;

import java.math.BigDecimal;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
public interface LimitePrestamo {
	/**
	 * @return Devuelve leyenda.
	 */
	public abstract String getLeyenda();

	/**
	 * @return Devuelve limite.
	 */
	public abstract BigDecimal getLimite();
}