/*
 * Creado el 29/09/2007 WALTER ZAPATA RAMOS
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
package pe.cosapi.dao;
import java.util.List;
/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
public interface DescripcionDAO {
	public abstract List getDescripcionesMov() throws Exception;
	
	public abstract List getDescripcionMov(String codMov) throws Exception;
	
}