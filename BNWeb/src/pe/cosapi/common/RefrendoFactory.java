/*
 * Creado el 02/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
package pe.cosapi.common;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
public class RefrendoFactory extends ObjectFactory {

	public static Refrendo getRefrendoInstance(String idRefrendo) {
		return (Refrendo) getObject(idRefrendo);
	}
	
}
