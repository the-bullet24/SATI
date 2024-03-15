/*
 * Creado el 07/06/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain;

import pe.cosapi.domain.impl.DepartamentoImpl;


/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface Agencia {
	/**
	 * @return Devuelve codigo.
	 */
	public abstract String getCodigo();

	/**
	 * @param codigo El codigo a establecer.
	 */
	public abstract void setCodigo(String codigo);

	/**
	 * @return Devuelve departamento.
	 */
	public abstract DepartamentoImpl getDepartamento();

	/**
	 * @param departamento El departamento a establecer.
	 */
	public abstract void setDepartamento(DepartamentoImpl departamento);

	/**
	 * @return Devuelve descripcion.
	 */
	public abstract String getDescripcion();

	/**
	 * @param descripcion El descripcion a establecer.
	 */
	public abstract void setDescripcion(String descripcion);
}