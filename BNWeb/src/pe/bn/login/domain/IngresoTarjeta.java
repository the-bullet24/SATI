/*
 * Created on 06/10/2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pe.bn.login.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author 2424012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IngresoTarjeta extends Serializable {
	
	public abstract Date  getFechaAnt();

	public abstract void setFechaAnt(Date fechaAnt);
	
	public abstract String getFechaIng();

	public abstract void setFechaIng(String fechaIng);
	
	public abstract String getIpAnt();
 
	public abstract void setIpAnt(String ipAnt);
	
	public abstract String getIpIng();
	 
	public abstract void setIpIng(String ipIng);
	
	public abstract String getNumSec();
	 
	public abstract void setNumSec(String numSec);
	
	public abstract  String getNumTar();
	
	public abstract void setNumTar(String numTar) ;
	
	public abstract  String getDiaAnt();
	
	public abstract void setDiaAnt(String diaAnt) ;
	
	public abstract  String getHoraAnt();
	
	public abstract void setHoraAnt(String horaAnt) ;
	
	
	
}
