/*
 * Creado el 22/06/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
package pe.cosapi.domain.impl;

import java.io.Serializable;

import pe.cosapi.domain.Entidad;

/**
 * @author cosapi_ati02
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
public class EntidadImpl implements Entidad, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8185377363506176261L;
	private String codigo;
	private String descripcion;
	
	public EntidadImpl(String codigo,String descripcion){
		this.codigo	= codigo;
		this.descripcion = descripcion;		
	}
	
	/**
	 * @return Devuelve codigo.
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo El codigo a establecer.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return Devuelve descripcion.
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion El descripcion a establecer.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
