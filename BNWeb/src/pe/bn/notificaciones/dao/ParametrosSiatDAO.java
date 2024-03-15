package pe.bn.notificaciones.dao;

import pe.bn.notificaciones.model.ParametrosSIAT;

public interface ParametrosSiatDAO {
	public ParametrosSIAT getParametrosSiat(String tarjeta) throws Exception;
}
