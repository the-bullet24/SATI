/*
 * Creado el 16/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.common;

import java.util.Vector;

/**
 * @author cosapi_ati03
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class AlternativaConsulta {
	
	public String alternativaQuery(String dicc1, java.util.Vector data, Vector cuentas )  {
		Vector opcion = (Vector)cuentas.elementAt(0);
		return (String)opcion.elementAt(0);
	}
}
