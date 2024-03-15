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
import pe.cosapi.domain.impl.ReciboImpl;

/**
 * @author cosapi_ati02
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface PagoSedapal {
	
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
	 * @return Devuelve nroSuministro.
	 */
	public abstract String getNroSuministro();

	/**
	 * @param nroSuministro El nroSuministro a establecer.
	 */
	public abstract void setNroSuministro(String nroSuministro);
	
	/**
	 * @return Devuelve codEntidad.
	 */
	public abstract String getCodEntidad();

	/**
	 * @param codEntidad El codEntidad a establecer.
	 */
	public abstract void setCodEntidad(String codEntidad);
	
	/**
	 * @return Devuelve codServicio.
	 */
	public abstract String getCodServicio();

	/**
	 * @param codServicio El codServicio a establecer.
	 */
	public abstract void setCodServicio(String codServicio);
		
	/**
	 * @return Devuelve nroChequeo.
	 */
	public abstract String getNroChequeo();
	
	/**
	 * @param nroChequeo El nroChequeo a establecer.
	 */
	public abstract void setNroChequeo(String nroChequeo);
	
	/**
	 * 
	 * @return Devuelve recibos.
	 */
	public List getRecibos();
	
	/**
	 * @param recibos El recibos a establecer.
	 */
	public void setRecibos(List recibos);
	
	public ReciboImpl getRecibo();
	/**
	 * @param recibo El recibo a establecer.
	 */
	public void setRecibo(ReciboImpl recibo);
	
	public void setAfiliacion(Afiliacion afiliacion);
	
	public String getCliente();
	

}