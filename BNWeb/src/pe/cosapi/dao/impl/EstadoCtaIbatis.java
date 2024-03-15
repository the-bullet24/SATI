/*
 * Creado el 27/04/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.dao.impl;

import pe.cosapi.common.DAOAbstract;
import pe.cosapi.dao.EstadoCtaDAO;
import pe.cosapi.domain.EstadoCta;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class EstadoCtaIbatis extends DAOAbstract implements EstadoCtaDAO {

	/* (sin Javadoc)
	 * @see pe.cosapi.dao.ItfDAO#getItfAnual(pe.cosapi.domain.Itf)
	 */
	public EstadoCta getEstadoConsulta(EstadoCta estadoCta) throws Exception {
		return (EstadoCta)getSqlMapClientTemplate().queryForObject("sqlMapESTADOCTA.getEstadoConsulta",estadoCta);	
	}

}
