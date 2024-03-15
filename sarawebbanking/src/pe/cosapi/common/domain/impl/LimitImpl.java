/*
 * Fecha 19/03/2007
 * Creado por Wilder A. Hernandez Nuñez
 */
package pe.cosapi.common.domain.impl;

import java.math.BigDecimal;
import pe.cosapi.common.Constante;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.domain.Limit;


public class LimitImpl implements Limit{
    
    private String 		tipoCuenta;
    private BigDecimal  importeMaximo;
    
    
    public LimitImpl (){
        
    }
    
    public LimitImpl (Limit limit){
        this.tipoCuenta 	= limit.getTipoCuenta();
        this.importeMaximo 	= limit.getImporteMaximo();
    }
    
    public void eliminar()throws Exception{
        DAOFactory.getCommonDAO().delete(this);
    }
    
    public String insertar()throws Exception{
        if (existLimit(this.tipoCuenta)){
            return "Existe Un Registro en La BD";
        }
        DAOFactory.getCommonDAO().insert(this);
        return null;
    }
    
    public void modificar()throws Exception{
        DAOFactory.getCommonDAO().update(this);
    }
    
    public boolean existLimit(String tipoCuenta) throws Exception{
        
        boolean flag = false;
        
        if (DAOFactory.getCommonDAO().getLimit(tipoCuenta)!= null)
            flag = true;
        else 
            flag = false;
        
        return flag;
    }
    
    public LimitImpl getLimit(String tipoCuenta) throws Exception{
        return DAOFactory.getCommonDAO().getLimit(tipoCuenta);
    }
    
    public Limit[] getArrayLimit() throws Exception{
        return DAOFactory.getCommonDAO().getArrayLimit();
    }
    
    
    public String getNombreTipoCuenta(){
        String nombre = null;
        if (this.tipoCuenta.equals(Constante.COD_CUENTA_AHORROS_MN)){
            nombre =  "Ahorro MN";
        }else if (this.tipoCuenta.equals(Constante.COD_CUENTA_AHORROS_ME)){
            nombre =  "Ahorro ME";
        }else if (this.tipoCuenta.equals(Constante.COD_CUENTA_CORRIENTE_MN)){
            nombre =  "Corriente MN";
        }else if (this.tipoCuenta.equals(Constante.COD_CUENTA_CORRIENTE_ME)){
            nombre =  "Corriente ME";
        }else if (this.tipoCuenta.equals(Constante.COD_CUENTA_CTS_MN)){
            nombre =  "CTS MN";
        }else if (this.tipoCuenta.equals(Constante.COD_CUENTA_CTS_ME)){
            nombre =  "CTS ME";
        }
        
      return nombre;  
    }
    
    public BigDecimal getImporteMaximo() {
        return importeMaximo;
    }
    public void setImporteMaximo(BigDecimal importeMaximo) {
        this.importeMaximo = importeMaximo;
    }
    public String getTipoCuenta() {
        return tipoCuenta;
    }
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
    
}
