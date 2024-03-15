package pe.cosapi.common;

import pe.cosapi.common.dao.BannerDAO;
import pe.cosapi.common.dao.CommonDAO;

public class DAOFactory extends ObjectFactory{
	
	public static CommonDAO getCommonDAO(){
		return (CommonDAO) getObject("commonDAO");
	}
	public static BannerDAO getBannerDAO(){
		return (BannerDAO) getObject("bannerDAO");
	}
	
}