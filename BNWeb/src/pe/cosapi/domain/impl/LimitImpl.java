/*
 * Fecha 20/06/2007
 * Creado por Wilder A. Hernandez Nuñez
 */
package pe.cosapi.domain.impl;

import java.math.BigDecimal;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.domain.Limit;


public class LimitImpl implements Limit{
    
    private String 		tipoCuenta;
    private BigDecimal  importeMaximo;
    
    
    public LimitImpl (){
        
    }
    
    public LimitImpl (Limit limit){
        this.tipoCuenta 	= limit.getTipoCuenta();
        this.importeMaximo 	= limit.getImporteMaximo();
    }
    
    public void cargar() throws Exception{
        LimitImpl lim =  DAOFactory.getGeneraDAO().getLimit(this);
        this.tipoCuenta 	= lim.tipoCuenta;  
        this.importeMaximo  = lim.importeMaximo;
    }
    
    public LimitImpl getLimit(LimitImpl limit) throws Exception{
        return DAOFactory.getGeneraDAO().getLimit(limit);
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
