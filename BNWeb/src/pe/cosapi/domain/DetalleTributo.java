/*
 * Creado el 22/06/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
package pe.cosapi.domain;

/**
 * @author cosapi_ati02
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
public interface DetalleTributo {
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

	public abstract Tributo getTributo();
	/**
	 * @param tributo El tributo a establecer.
	 */
	public abstract void setTributo(Tributo tributo);

}