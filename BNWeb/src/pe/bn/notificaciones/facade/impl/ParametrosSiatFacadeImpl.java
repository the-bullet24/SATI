package pe.bn.notificaciones.facade.impl;

import pe.bn.notificaciones.facade.ParametrosSiatFacade;
import pe.bn.notificaciones.model.ParametrosSIAT;
import pe.cosapi.common.DAOFactory;

public class ParametrosSiatFacadeImpl  implements ParametrosSiatFacade{

	public ParametrosSIAT getParametrosSiat(String tarjeta) throws Exception {
		return DAOFactory.getParametrosSiatDAO().getParametrosSiat(tarjeta);
	}

}
