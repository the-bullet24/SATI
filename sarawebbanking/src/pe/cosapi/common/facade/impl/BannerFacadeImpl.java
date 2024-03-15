/*
 * Fecha 19/06/2007
 * Creado por Wilder A. Hernandez Nuñez
 */
package pe.cosapi.common.facade.impl;

import pe.cosapi.common.domain.Banner;
import pe.cosapi.common.domain.impl.BannerImpl;
import pe.cosapi.common.facade.BannerFacade;

public class BannerFacadeImpl implements BannerFacade{
	
    public BannerImpl getBanner(String tipoPersona) throws Exception{
        System.out.println("tipoCuenta:"+tipoPersona);
        BannerImpl banner_ = new BannerImpl();
        return banner_.getBanner(tipoPersona);
    }
    
    
    
    public void modificarBanner(Banner banner) throws Exception{
        BannerImpl banner_ = new BannerImpl(banner);
        banner_.modificarBanner();
    }
    
}
