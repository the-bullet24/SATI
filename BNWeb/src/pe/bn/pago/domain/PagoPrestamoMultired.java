/*
 * Creado el 09/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.pago.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Pago;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface PagoPrestamoMultired extends Pago, Serializable{
	/**
	 * @return Devuelve cliente.
	 */
	public abstract String getCliente();

	/**
	 * @return Devuelve cuenta.
	 */
	public abstract Cuenta getCuenta();

	/**
	 * @return Devuelve deuda.
	 */
	public abstract String getDeuda();

	public abstract String getNroCuota();
	/**
	 * @return Devuelve fechaVencimiento.
	 */
	public abstract Timestamp getFechaVencimiento();

	/**
	 * @return Devuelve nroPrestamo.
	 */
	public abstract String getNroPrestamo();
	
	public abstract String getNroDesembolso();
	
	public abstract BigDecimal getIntereses();
	public abstract BigDecimal getNroDiasInteres();
	public abstract BigDecimal getNroDiasInteresCompensatorio();
	public abstract BigDecimal getNroDiasInteresMoratorio();
	public abstract BigDecimal getInteCompesatorios();
	public abstract BigDecimal getInteMoratorios();

}