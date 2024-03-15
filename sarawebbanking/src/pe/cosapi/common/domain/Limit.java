/*
 * Fecha 19/03/2007
 * Creado por Wilder A. Hernandez Nuñez
 */
package pe.cosapi.common.domain;

import java.math.BigDecimal;


public interface Limit {
    public abstract BigDecimal getImporteMaximo();
    public abstract void setImporteMaximo(BigDecimal importeMaximo);
    public abstract String getTipoCuenta();
    public abstract void setTipoCuenta(String tipoCuenta);
    
    public abstract String getNombreTipoCuenta();
}
