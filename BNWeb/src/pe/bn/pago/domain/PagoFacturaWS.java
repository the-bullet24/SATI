/*
 * Creado el 12/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.pago.domain;

import java.util.List;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.impl.FacturaImpl;

/**
 * @author cosapi_ati02
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface PagoFacturaWS {
	
	public abstract String getHora();
	
	public abstract void setHora(String hora);
	
	public abstract void setFecha(String fecha);
	
	public abstract String getFecha();
	
	/**
	 * @return Devuelve cuenta.
	 */
	public abstract Cuenta getCuenta();

	/**
	 * @param cuenta El cuenta a establecer.
	 */
	public abstract void setCuenta(Cuenta cuenta);

	/**
	 * @return Devuelve nroReferencia.
	 */
	public abstract String getNroReferencia();

	/**
	 * @param nroReferencia El nroReferencia a establecer.
	 */
	public abstract void setNroReferencia(String nroReferencia);
	
	/**
	 * @return Devuelve codEntidad.
	 */
	public abstract String getCodEntidad();
	
	/**
	 * @param codEntidad El codEntidad a establecer.
	 */
	public abstract void setCodEntidad(String codEntidad);

	/**
	 * @param nomEntidad El nomEntidad a establecer.
	 */
	public abstract void setNomEntidad(String nomEntidad);
	
	/**
	 * @return Devuelve nomEntidad.
	 */
	public abstract String getNomEntidad();

		
	/**
	 * @return Devuelve nroChequeo.
	 */
	public abstract String getNroChequeo();
	
	/**
	 * @param nroChequeo El nroChequeo a establecer.
	 */
	public abstract void setNroChequeo(String nroChequeo);
	
	/**
	 * @return Devuelve codServicio.
	 */
	public abstract String getCodServicio();
	
	/**
	 * @param codServicio El codServicio a establecer.
	 */
	public abstract void setCodServicio(String codServicio);
	
	/**
	 * 
	 * @return Devuelve facturas.
	 */
	public List getFacturas();
	
	/**
	 * @param facturas El facturas a establecer.
	 */
	public void setFacturas(List facturas);
	
	public FacturaImpl getFactura();
	/**
	 * @param factura El factura a establecer.
	 */
	public void setFactura(FacturaImpl factura);
	
	public void setAfiliacion(Afiliacion afiliacion);
	

}