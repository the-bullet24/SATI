package pe.bn.notificaciones.facade.impl;

import pe.bn.notificaciones.facade.TransaccionFacade;
import pe.cosapi.common.DAOFactory;

public class TransaccionFacadeImpl implements TransaccionFacade{

	public String getDescripcionCorta(String cod) throws Exception {
		return DAOFactory.getTransaccionDAO().getDescripcionCorta(cod);
	}

}
