package pe.bn.antiphishing.facade.impl;

import pe.bn.antiphishing.facade.ServiciosAntiphishingFacade;
import pe.cosapi.common.DAOFactory;

public class ServiciosAntiphishingFacadeImpl  implements ServiciosAntiphishingFacade{

	public String getValidaIP(String ip) throws Exception {
		
		return DAOFactory.getServiciosAntiphishingDAO().getValidaIP(ip);
	}
	
	public String getOpcionAntiphishing(String opcion) throws Exception{
		
		return DAOFactory.getServiciosAntiphishingDAO().getOpcionAntiphishing(opcion);
	}

}
