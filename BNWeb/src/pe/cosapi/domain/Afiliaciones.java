/*
 * Creado el 09/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface Afiliaciones extends Serializable {
	
	
	public abstract String getEmpresa();
		
	public abstract String getNumSuministro();

	public abstract String getServicio();
	
	public abstract String getFlagEstado();
	


	
}