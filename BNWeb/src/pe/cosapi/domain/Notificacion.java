/*
 * Creado el 19/04/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
package pe.cosapi.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
public interface Notificacion {
	public abstract String getEstadoNotificacion();
	public abstract String getMontoNotificacion();
	public abstract String getTipoMonedaNotificacion();
	public abstract String getMedioNotificacion();
}