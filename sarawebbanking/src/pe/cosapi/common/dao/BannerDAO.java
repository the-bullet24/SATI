/*
 * Created on 03/01/2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pe.cosapi.common.dao;

import pe.cosapi.common.domain.impl.BannerImpl;

/**
 * @author cosapi_ati08
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface BannerDAO {
	 public abstract BannerImpl getBanner(String tipoPersona) throws Exception;
	 public abstract void updateBanner(BannerImpl Banner) throws Exception;


}
