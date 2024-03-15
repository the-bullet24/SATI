/*
 * Fecha 19/06/2007
 * Creado por Wilder A. Hernandez Nuñez
 */
package pe.cosapi.common.facade;

import pe.cosapi.common.domain.Limit;
import pe.cosapi.common.domain.impl.LimitImpl;


public interface CommonFacade {
    public abstract LimitImpl getLimit(String tipoCuenta) throws Exception;
    public abstract Limit[] getArrayLimit() throws Exception;
    
    public abstract String insertarLimite(Limit limit) throws Exception;
    public abstract void eliminarLimite(Limit limit) throws Exception;
    public abstract void modificarLimite(Limit limit) throws Exception;
    
}
