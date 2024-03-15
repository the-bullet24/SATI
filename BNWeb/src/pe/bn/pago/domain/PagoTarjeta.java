/*
 * Creado el 13/04/2007
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
public interface PagoTarjeta extends Operacion, Serializable{
	
	public abstract Afiliacion getAfiliacion();

	public abstract Cuenta getCuenta();

	public abstract String getMoneda();
	
	public abstract String getMoneda_();
	
	public abstract void  setMoneda_(String moneda_);
	
	public abstract String getTipoPlaza();
	
	public abstract String getNumTransferencia();
	
	public abstract BigDecimal getComisionIB();
		
	public abstract BigDecimal getComisionIBConvertido();
	
	public abstract String getFlagRefrendo();
	
	/*MGL*/
	public abstract String getCuentaPropia();
	
}