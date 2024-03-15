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
public interface MovDescripcion extends Serializable {
	/**
	 * @return Devuelve codigo.
	 */
	public abstract String getCodigo();

	/**
	 * @return Devuelve descripcion.
	 */
	public abstract String getDescripcion();
	
	public abstract void setCodigo(String codigo);

	/**
	 * @return Devuelve descripcion.
	 */
	public abstract void setDescripcion(String descripcion);

	
}