/*
 * Creado el 27/04/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
package pe.cosapi.dao.impl;

import pe.cosapi.common.DAOAbstract;
import pe.cosapi.dao.ItfDAO;
import pe.cosapi.domain.Itf;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
public class ItfIbatis extends DAOAbstract implements ItfDAO {

	/* (sin Javadoc)
	 * @see pe.cosapi.dao.ItfDAO#getItfAnual(pe.cosapi.domain.Itf)
	 */
	public Itf getItfAnual(Itf itf) throws Exception {
	
		return (Itf)getSqlMapClientTemplate().queryForObject("sqlMapITF.getITFAnual",itf);	
	}

}
