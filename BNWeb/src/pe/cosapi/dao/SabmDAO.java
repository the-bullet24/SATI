
package pe.cosapi.dao;

import pe.cosapi.domain.GeneracionLog;
import pe.cosapi.domain.GeneracionLogResultado;
import pe.cosapi.domain.Journal;
import pe.cosapi.domain.impl.GeneracionLogImpl;


public interface SabmDAO {

	public GeneracionLog getGeneracionLog(GeneracionLog generacionLog) throws Exception;
	
	public GeneracionLogResultado insertGeneracionLog(GeneracionLogImpl generacionLogImpl) throws Exception;
	
	public int insertLog(GeneracionLogImpl generacionLogImpl) throws Exception;

	public void updateLog(GeneracionLogImpl generacionLogImpl) throws Exception;
}
