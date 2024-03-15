/*
 * Creado el 05/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.consulta.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import pe.cosapi.domain.Operacion;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface Prestamo extends Operacion, Serializable{
	/**
	 * @return Devuelve calendarioPrestamo.
	 */
	public abstract List getCalendarioPrestamo();

	/**
	 * @return Devuelve cliente.
	 */
	public abstract String getCliente();

	/**
	 * @return Devuelve deudaActual.
	 */
	public abstract String getDeudaActual();

	/**
	 * @return Devuelve direccion.
	 */
	public abstract String getDireccion();

	/**
	 * @return Devuelve dOI.
	 */
	public abstract String getDOI();

	/**
	 * @return Devuelve id.
	 */
	public abstract String getId();

	/**
	 * @return Devuelve interesActual.
	 */
	public abstract String getInteresActual();

	/**
	 * @return Devuelve oficina.
	 */
	public abstract String getOficina();

	/**
	 * @return Devuelve saldoActual.
	 */
	public abstract String getSaldoActual();

	/**
	 * @return Devuelve garante.
	 */
	public abstract String getGarante();

	/**
	 * @return Devuelve pagare.
	 */
	public abstract String getPagare();
	
	public String getMoneda();
	
	public Timestamp getFechaPrestamo();
	
	public String getCuenta();
	
	public abstract String getMontoPrestamo() ;
	
	public abstract String getTasa();
	
	public abstract String getTcea();
	
	public abstract String getSaldoNeto();
	
	public abstract String getSaldoDeuda();
	
	public abstract String getSegDesgravamen();
	
	public abstract String getMontoCuotaProtegida() ;
	
	public abstract String getMontoCuota();
	
	public abstract String getNroPrestamo();
	
	public abstract String getFechaVenPrestamo();
	
	public abstract String getFechaVenCuota();
	
	public abstract String getDiaPago();
	
	public abstract String getMontoMinimo();
	
	public abstract String getCuentaFormateada();
	
	public abstract String getNumPrestamo();
	
	
}