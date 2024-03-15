package pe.bn.antiphishing.dao;

public interface ServiciosAntiphishingDAO {
	public String getValidaIP(String ip) throws Exception;
	public String getOpcionAntiphishing(String opcion) throws Exception;
}
