/*
 * Creado el 07/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.dao;

import java.util.List;

import pe.cosapi.common.DAOFactory;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface AgenciaDAO {
	public abstract List getAgenciasSoles() throws Exception;
	public abstract List getAgenciasDolares() throws Exception;
	public abstract List getDepartamentos() throws Exception;
	
	public abstract List getListDetalleDepartamento() throws Exception;
	public abstract List getListDetalleProvincia(String codAyuda) throws Exception;
	public abstract List getListDetalleDistrito(String codAyuda) throws Exception;
	
	
}