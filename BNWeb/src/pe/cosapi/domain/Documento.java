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
public interface Documento {
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
	
	public String getNumero();
	
	/**
	 * @param numero El numero a establecer.
	 */
	public void setNumero(String numero);
		
}