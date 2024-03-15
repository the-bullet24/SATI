/*
 * Creado el 22/06/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain.impl;

import java.util.List;

import pe.cosapi.domain.Tributo;

/**
 * @author cosapi_ati02
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TributoImpl implements Tributo {
	private String codigo;
	private String descripcion;
	private List detalles;

	public TributoImpl(String codigo,String descripcion){
		this.codigo = codigo;
		this.descripcion = descripcion.replaceAll("&#40;", "(").replaceAll("&#41;", ")");		
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
	/**
	 * @return Devuelve detalles.
	 */
	public List getDetalles() {
		return detalles;
	}
	/**
	 * @param detalles El detalles a establecer.
	 */
	public void setDetalles(List detalles) {
		this.detalles = detalles;
	}
}
