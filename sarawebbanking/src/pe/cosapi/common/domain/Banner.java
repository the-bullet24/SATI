/*
 * Fecha 19/03/2007
 * Creado por Wilder A. Hernandez Nuñez
 */
package pe.cosapi.common.domain;

import java.math.BigDecimal;

import com.ibm.etools.webservice.wscommonext.Timestamp;

import CosapiSoft.SARAWebBanking.Date;


public interface Banner {
    public abstract Timestamp getFechaInicio();
    public abstract void setFechaInicio(Timestamp fechaInicio);
    public abstract Timestamp getFechaFin();
    public abstract void setFechaFin(Timestamp fechaFin);
    public abstract String getNombreArchivo();
    public abstract void setNombreArchivo(String nombreArchivo);
    public abstract String getTipoPersona();
    public abstract void setTipoPersona(String tipoPersona);
    
   
}
