/*
 * Fecha 19/06/2007
 * Creado por Wilder A. Hernandez Nuñez
 */
package pe.cosapi.common.dao.impl;


import java.util.List;

import pe.cosapi.common.DAOAbstract;
import pe.cosapi.common.dao.CommonDAO;
import pe.cosapi.common.domain.impl.BannerImpl;
import pe.cosapi.common.domain.impl.LimitImpl;

public class CommonIbatis extends DAOAbstract implements CommonDAO{
    
    public LimitImpl getLimit(String tipoCuenta) throws Exception{
		return (LimitImpl)getSqlMapClientTemplate().queryForObject("sqlMapCommon.getLimitById",tipoCuenta);
	}
    
    public LimitImpl[] getArrayLimit() throws Exception{
        List lstLimits = getSqlMapClientTemplate().queryForList("sqlMapCommon.getArrayLimit",null);
        return (LimitImpl[])lstLimits.toArray(new LimitImpl[lstLimits.size()]);
	}
    
    public void insert(LimitImpl limite) throws Exception{
        getSqlMapClientTemplate().insert("sqlMapCommon.insert",limite);
    }
    
    public void delete(LimitImpl limite) throws Exception{
        getSqlMapClientTemplate().delete("sqlMapCommon.delete",limite);
    }
    
    public void update(LimitImpl limite) throws Exception{
        getSqlMapClientTemplate().update("sqlMapCommon.update",limite);
    }

    public void updateBanner(BannerImpl banner) throws Exception{
        getSqlMapClientTemplate().update("sqlMapCommon.updateBanner",banner);
    }
    public BannerImpl getBanner(String tipoPersona) throws Exception{
		return (BannerImpl)getSqlMapClientTemplate().queryForObject("sqlMapCommon.getBannerById",tipoPersona);
	}
}
