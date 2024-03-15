/*
 * Creado el 07/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.dao.impl;

import java.util.List;

import pe.cosapi.common.DAOAbstract;
import pe.cosapi.dao.AgenciaDAO;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class AgenciaIbatis extends DAOAbstract implements AgenciaDAO {

	public List getAgenciasSoles()  throws Exception{
		return getSqlMapClientTemplate().queryForList("sqlMapAgencia.getAgenciasSoles");
	}
	public List getAgenciasDolares()  throws Exception{
		return getSqlMapClientTemplate().queryForList("sqlMapAgencia.getAgenciasDolares");
	}
	
	public List getDepartamentos()  throws Exception{
		return getSqlMapClientTemplate().queryForList("sqlMapAgencia.getDepartamentos");
	}
	
	public List getListDetalleDepartamento()  throws Exception{
		return getSqlMapClientTemplate().queryForList("sqlMapAgencia.getListDetalleDepartamento");
	}
	
	public  List getListDetalleProvincia(String codAyuda) throws Exception{
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapAgencia.getListDetalleProvincia",codAyuda);
		return lstDetalleAyuda;
	}
	public  List getListDetalleDistrito(String codAyuda) throws Exception{
		
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapAgencia.getListDetalleDistrito",codAyuda);
		
		return lstDetalleAyuda;
	}

}