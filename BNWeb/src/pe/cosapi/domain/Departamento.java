/*
 * Creado el 19/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface Departamento extends Serializable {
	/**
	 * @return Devuelve codigo.
	 */
	
	public abstract void setCodigo(String codigo);
	
	public abstract String getCodigo();

	/**
	 * @return Devuelve descripcion.
	 */
	public abstract void setDescripcion(String descripcion);
	
	public abstract String getDescripcion();
	
	public List getAgencias();
	
	public void setAgencias(List agencias);

}