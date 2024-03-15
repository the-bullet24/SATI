package pe.bn.notificaciones.facade;

import java.io.Serializable;

public interface TransaccionFacade extends Serializable {
	public String getDescripcionCorta(String cod) throws Exception;
}
