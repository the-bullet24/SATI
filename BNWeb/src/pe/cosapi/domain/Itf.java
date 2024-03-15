/*
 * Creado el 26/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain;

import java.io.Serializable;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface Itf extends Operacion,  Serializable {
	/**
	 * @return Devuelve abonos.
	 */
	public abstract String getAbonos();

	/**
	 * @return Devuelve cargos.
	 */
	public abstract String getCargos();

	/**
	 * @return Devuelve cuenta.
	 */
	public abstract String getCuenta();

	/**
	 * @return Devuelve devoluciones.
	 */
	public abstract String getDevoluciones();

	/**
	 * @return Devuelve direccion.
	 */
	public abstract String getDireccion();

	/**
	 * @return Devuelve documento.
	 */
	public abstract String getDocumento();

	/**
	 * @return Devuelve fecha.
	 */
	public abstract String getFecha();

	/**
	 * @return Devuelve moneda.
	 */
	public abstract String getMoneda();

	/**
	 * @return Devuelve periodo.
	 */
	public abstract String getPeriodo();

	/**
	 * @return Devuelve razon.
	 */
	public abstract String getRazon();

	/**
	 * @return Devuelve servicio.
	 */
	public abstract String getServicio();

	/**
	 * @return Devuelve tdocumento.
	 */
	public abstract String getTdocumento();

	/**
	 * @return Devuelve tpersona.
	 */
	public abstract String getTpersona();
	
	public String getRetencion();
	
	public String getTotal();
	
	public String getNombre();
	
	public String getHora();
}