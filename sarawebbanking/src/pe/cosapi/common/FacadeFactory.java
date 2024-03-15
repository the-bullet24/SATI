package pe.cosapi.common;

import pe.cosapi.common.facade.CommonFacade;
import pe.cosapi.common.facade.BannerFacade;

public class FacadeFactory extends ObjectFactory {

	public static CommonFacade getCommonFacade(){
		return (CommonFacade)getObject("commonFacade");
	}
	public static BannerFacade getBannerFacade(){
		return (BannerFacade)getObject("bannerFacade");
	}

}