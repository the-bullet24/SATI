/*
 * Creado por Wilder A. Hernandez Nuñez
 */
package pe.cosapi.common.domain.impl;

import java.math.BigDecimal;
import com.ibm.etools.webservice.wscommonext.Timestamp;
import pe.cosapi.common.Constante;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.domain.Banner;
import pe.cosapi.common.domain.Limit;


public class BannerImpl implements Banner{
	/**
	 * @return Returns the log.
	 */
    /**
	public Logger getLog() {
		return log;
	}
	*/
	/**
	 * @param log The log to set.
	 */
    /**
	public void setLog(Logger log) {
		this.log = log;
	}
	*/
	/**
	 * @return Returns the fechaFin.
	 */
	public Timestamp getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin The fechaFin to set.
	 */
	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return Returns the fechaInicio.
	 */
	public Timestamp getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio The fechaInicio to set.
	 */
	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return Returns the nombreArchivo.
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	/**
	 * @param nombreArchivo The nombreArchivo to set.
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	/**
	 * @return Returns the tipoPersona.
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}
	/**
	 * @param tipoPersona The tipoPersona to set.
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
    private Timestamp fechaInicio;
    private Timestamp fechaFin;
    private String nombreArchivo;
    private String tipoPersona;
    
    
    
    public BannerImpl (){
        
    }
    
    public BannerImpl (Banner banner){
        this.fechaInicio 	= banner.getFechaInicio();
        this.fechaFin 	= banner.getFechaFin();
        this.nombreArchivo  = banner.getNombreArchivo();
        this.tipoPersona  = banner.getTipoPersona();
    }
    
    
    
    public void modificarBanner()throws Exception{
        DAOFactory.getBannerDAO().updateBanner(this);
    }
    
    
    
    public BannerImpl getBanner(String tipoPersona) throws Exception{
        return DAOFactory.getBannerDAO().getBanner(tipoPersona);
    }
    
 
    
    
    
    
   
    
}

