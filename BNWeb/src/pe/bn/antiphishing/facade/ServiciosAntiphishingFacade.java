package pe.bn.antiphishing.facade;

import java.io.Serializable;

import pe.bn.notificaciones.model.ParametrosSIAT;

public interface ServiciosAntiphishingFacade extends Serializable {
	
	public String getValidaIP(String ip) throws Exception;
	
	public String getOpcionAntiphishing(String opcion) throws Exception;

}
