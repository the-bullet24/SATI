
package pe.bn.afiliacion.dao;

import java.util.List;

import pe.bn.afiliacion.domain.impl.AfiliacionImpl;


public interface AfiliacionDAO {
    
	public abstract List getAfiliaciones(AfiliacionImpl afiliacionImpl) throws Exception;
	
	public abstract List getAfiliaExiste(AfiliacionImpl afiliacionImpl) throws Exception;
	
	public abstract List getAfiliaExisteDebito(AfiliacionImpl afiliacionImpl) throws Exception;
	
	public abstract void insertar(AfiliacionImpl afiliacionImpl) throws Exception;
	
	public abstract pe.bn.afiliacion.domain.impl.AfiliacionImpl getAfiliacion(AfiliacionImpl afiliacionImpl) throws Exception;

	public abstract void desafiliar(AfiliacionImpl impl)throws Exception;
	
	public abstract void desafiliarDebito(AfiliacionImpl impl)throws Exception;

	public abstract void afiliar(AfiliacionImpl impl)throws Exception;

	public abstract Long getMaximaSecuencia(String tipAfiliacion, String numTarjeta)throws Exception;
	
	public abstract List getAfiliacionByValues(String codigoAyuda,String tipoAfiliacion,String numTarjeta) throws Exception;

}