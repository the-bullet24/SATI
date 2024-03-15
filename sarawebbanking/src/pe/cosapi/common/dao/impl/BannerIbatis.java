/*
 * Fecha 19/06/2007
 * Creado por Wilder A. Hernandez Nuñez
 */
package pe.cosapi.common.dao.impl;




import pe.cosapi.common.DAOAbstract;
import pe.cosapi.common.dao.BannerDAO;
import pe.cosapi.common.domain.impl.BannerImpl;

public class BannerIbatis extends DAOAbstract implements BannerDAO{
   

    public void updateBanner(BannerImpl banner) throws Exception{
        getSqlMapClientTemplate().update("sqlMapBanner.updateBanner",banner);
    }
    public BannerImpl getBanner(String tipoPersona) throws Exception{
		return (BannerImpl)getSqlMapClientTemplate().queryForObject("sqlMapBanner.getBannerById",tipoPersona);
	}
}

