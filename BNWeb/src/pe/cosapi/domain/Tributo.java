/*
 * Creado el 22/06/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain;

import java.util.List;

/**
 * @author cosapi_ati02
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface Tributo {
	/**
	 * @return Devuelve codigo.
	 */
	public abstract String getCodigo();

	/**
	 * @param codigo El codigo a establecer.
	 */
	public abstract void setCodigo(String codigo);

	/**
	 * @return Devuelve descripcion.
	 */
	public abstract String getDescripcion();

	/**
	 * @param descripcion El descripcion a establecer.
	 */
	public abstract void setDescripcion(String descripcion);
		
	public abstract List getDetalles();
	/**
	 * @param detalles El detalles a establecer.
	 */
	public abstract void setDetalles(List detalles);
	
}