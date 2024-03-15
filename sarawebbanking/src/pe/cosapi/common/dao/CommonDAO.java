/*
 * Fecha 19/06/2007
 * Creado por Wilder A. Hernandez Nuñez
 */
package pe.cosapi.common.dao;

import pe.cosapi.common.domain.impl.LimitImpl;

public interface CommonDAO {
    public abstract LimitImpl getLimit(String tipoCuenta) throws Exception;
    public abstract LimitImpl[] getArrayLimit() throws Exception;
    public abstract void insert(LimitImpl limite) throws Exception;
    public abstract void delete(LimitImpl limite) throws Exception;
    public abstract void update(LimitImpl limite) throws Exception;

}

