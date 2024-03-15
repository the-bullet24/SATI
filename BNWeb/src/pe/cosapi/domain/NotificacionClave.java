/*
 * Creado el 19/04/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 */
public interface NotificacionClave {
	public abstract String getEstadoExpira();	
	public abstract String getDiasVencimiento();
	
	public abstract String getSignoError();	
	public abstract String getCodigoError();	
	
	
}