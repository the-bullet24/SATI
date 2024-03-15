/*
 * Fecha 19/06/2007
 * Creado por Wilder A. Hernandez Nuñez
 */
package pe.cosapi.common.facade.impl;

import pe.cosapi.common.domain.Limit;
import pe.cosapi.common.domain.impl.LimitImpl;
import pe.cosapi.common.facade.CommonFacade;

public class CommonFacadeImpl implements CommonFacade{
	
    public LimitImpl getLimit(String tipoCuenta) throws Exception{
        System.out.println("tipoCuenta:"+tipoCuenta);
        LimitImpl limit_ = new LimitImpl();
        return limit_.getLimit(tipoCuenta);
    }
    
    public Limit[] getArrayLimit() throws Exception{
        Limit[] arreglo = null;
        LimitImpl limit_ = new LimitImpl();
        arreglo = limit_.getArrayLimit();
        if (arreglo.length == 0)
            return null;
        
        return arreglo;
    }
    
    public String insertarLimite(Limit limit) throws Exception{
        LimitImpl limit_ = new LimitImpl(limit);
        String message = limit_.insertar();
        return message;
    }
    
    public void eliminarLimite(Limit limit) throws Exception{
        LimitImpl limit_ = new LimitImpl(limit);
        limit_.eliminar();
    }
    
    public void modificarLimite(Limit limit) throws Exception{
        LimitImpl limit_ = new LimitImpl(limit);
        limit_.modificar();
    }
    
}
