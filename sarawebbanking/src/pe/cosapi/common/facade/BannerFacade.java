/*
 * Fecha 19/06/2007
 * Creado por Vilia Rios Vidal
 */
package pe.cosapi.common.facade;

import pe.cosapi.common.domain.Banner;
import pe.cosapi.common.domain.impl.BannerImpl;


public interface BannerFacade {
    public abstract BannerImpl getBanner(String tipoPersona) throws Exception;
    public abstract void modificarBanner(Banner banner) throws Exception;
    
}