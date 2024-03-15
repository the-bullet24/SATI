/*
 * Creado el 27/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.tarjeta.domain;

import java.io.Serializable;

import pe.cosapi.domain.Operacion;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface Tarjeta extends Operacion, Serializable{
	/**
	 * @return Devuelve asociado.
	 */
	public abstract boolean isAsociado();

	/**
	 * @return Devuelve numero.
	 */
	public abstract String getNumero();

	/**
	 * @return Devuelve tipo.
	 */
	public abstract String getTipo();
	
	public abstract String getClave();
	
	public abstract void setAsociado(boolean asociado);
	
	public abstract void setNumero(String numero);
	
	public abstract void setTipo(String tipo);
	
	public abstract void setClave(String clave);
	
	public abstract String getFecha();
    
	public abstract void setFecha(String fecha);
    
	public abstract String getHora();
    
	public abstract void setHora(String hora);
	public abstract void setTarjetaOculta(String tarjetaOculta);
	public abstract String getTarjetaOculta();
	
	
	public abstract String getFechaLogica();    
	public abstract void setFechaLogica(String fechaLogica);
	
	public abstract String getFechaCronologica();    
	public abstract void setFechaCronologica(String fechaCronologica);
	
	public abstract String getHoraCronologica();    
	public abstract void setHoraCronologica(String horaCronologica);
	
	
}