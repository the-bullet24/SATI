/*
 * Creado el 22/06/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain.impl;

import pe.cosapi.domain.DetalleTributo;
import pe.cosapi.domain.Tributo;

/**
 * @author cosapi_ati02
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class DetalleTributoImpl implements DetalleTributo {
	private String codigo;
	private String descripcion;
	private Tributo tributo;
	
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
	 * @return Devuelve tributo.
	 */
	public Tributo getTributo() {
		return tributo;
	}
	/**
	 * @param tributo El tributo a establecer.
	 */
	public void setTributo(Tributo tributo) {
		this.tributo = tributo;
	}
}
