package pe.bn.notificaciones.facade;

import java.io.Serializable;

import pe.bn.notificaciones.model.ParametrosSIAT;

public interface ParametrosSiatFacade extends Serializable {
	public ParametrosSIAT getParametrosSiat(String tarjeta) throws Exception;

}
