
package pe.bn.afiliacion.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.bn.afiliacion.dao.AfiliacionDAO;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.cosapi.common.DAOAbstract;


public class AfiliacionIbatis extends DAOAbstract implements AfiliacionDAO {
	
	public List getAfiliaciones(AfiliacionImpl afiliacionImpl) throws Exception{
		return getSqlMapClientTemplate().queryForList("sqlMapAfiliacion.getAfiliacion",afiliacionImpl);
	}
	
	public List getAfiliaExiste(AfiliacionImpl afiliacionImpl) throws Exception{
		return getSqlMapClientTemplate().queryForList("sqlMapAfiliacion.getAfiliaExiste",afiliacionImpl);
	}
	
	public List getAfiliaExisteDebito(AfiliacionImpl afiliacionImpl) throws Exception{
		return getSqlMapClientTemplate().queryForList("sqlMapAfiliacion.getAfiliaExisteDebito",afiliacionImpl);
	}
	
	public AfiliacionImpl getAfiliacion(AfiliacionImpl afiliacionImpl) throws Exception{
	
		return (AfiliacionImpl)getSqlMapClientTemplate().queryForObject("sqlMapAfiliacion.getAfiliacion",afiliacionImpl);
	}

	public void insertar(AfiliacionImpl afiliacionImpl) throws Exception{
		getSqlMapClientTemplate().insert("sqlMapAfiliacion.getAfiliacion",afiliacionImpl);
	}

	
	public void desafiliar(AfiliacionImpl impl) throws Exception {
		getSqlMapClientTemplate().delete("sqlMapAfiliacion.desafiliar",impl);
	}
	
	public void desafiliarDebito(AfiliacionImpl impl) throws Exception {
	
		
		getSqlMapClientTemplate().delete("sqlMapAfiliacion.desafiliarDebito",impl);
	}
	
	public void afiliar(AfiliacionImpl impl) throws Exception {
		
	
		
		getSqlMapClientTemplate().insert("sqlMapAfiliacion.insertar",impl);
	}

	
	public Long getMaximaSecuencia(String tipAfiliacion, String numTarjeta) throws Exception {

		Map input = new HashMap();
		input.put("tipAfiliacion",tipAfiliacion);
		input.put("numTarjeta",numTarjeta);
		return (Long)getSqlMapClientTemplate().queryForObject("sqlMapAfiliacion.getMaximaSecuencia",input);
	}
	

	public List getAfiliacionByValues(String codigoAyuda,String tipoAfiliacion,String numTarjeta) throws Exception{
		Map input = new HashMap();
		input.put("codigoAyuda",codigoAyuda);
		input.put("tipoAfiliacion",tipoAfiliacion);
		input.put("numTarjeta",numTarjeta);
		return getSqlMapClientTemplate().queryForList("sqlMapAfiliacion.getAfiliacionByValues",input);
	}

	
}
