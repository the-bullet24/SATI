/*
 * Creado el 08/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.cosapi.common.ComboUtil;
import pe.cosapi.common.Constante;
import pe.cosapi.common.DAOAbstract;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.dao.DescripcionDAO;
import pe.cosapi.dao.GeneralDAO;
import pe.cosapi.domain.Banner;
import pe.cosapi.domain.Branch;
import pe.cosapi.domain.MasterTransaction;
import pe.cosapi.domain.MsgComunication;
import pe.cosapi.domain.impl.LimitImpl;
import pe.cosapi.domain.impl.MasterLimitsImpl;


/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class DescripcionIbatis extends DAOAbstract implements DescripcionDAO{

	public List getDescripcionesMov() throws Exception {
		return getSqlMapClientTemplate().queryForList("sqlMapDescripcion.getDescripcionesMov");
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.dao.DescripcionDAO#getDescripcionMov(java.lang.String)
	 */
	public List getDescripcionMov(String codMov) throws Exception {
		List lstDetalleAyuda = getSqlMapClientTemplate().queryForList("sqlMapDescripcion.getDescripcionMov",codMov);
		return lstDetalleAyuda;
	}
	
}
