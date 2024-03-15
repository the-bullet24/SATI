/*
 * Creado el 23/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.pago.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Operacion;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface PagoTelefono extends Operacion, Serializable{
	/**
	 * @return Devuelve afiliacion.
	 */
	public abstract Afiliacion getAfiliacion();

	/**
	 * @return Devuelve comision.
	 */
	public abstract BigDecimal getComision();

	/**
	 * @return Devuelve cuenta.
	 */
	public abstract Cuenta getCuenta();

	/**
	 * @return Devuelve importe.
	 */
	public abstract BigDecimal getImporte();

	/**
	 * @return Devuelve itf.
	 */
	public abstract BigDecimal getItf();

	/**
	 * @return Devuelve total.
	 */
	public abstract BigDecimal getTotal();
	
	
	public abstract String getCtaFormateada();
	
	
	public abstract String getRecibo();
	
	public abstract String getFecEmisionRecibo();
	
	public abstract String getMontoFormat();
	
	public abstract String getEmpresa();
	
	public abstract String getServicio();
	
	public abstract String getCodEntidad();
	
	public abstract String getCodServEnt();
	
	public abstract String getCodMonedaDes();
	
	public abstract String getImporteMinimo();
	
	public abstract String getGlosa();
	
	public abstract String getDocumento();
	
	/**
	 * @return Devuelve total.
	 */
	//public abstract BigDecimal getReciboTelefono();
}