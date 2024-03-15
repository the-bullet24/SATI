/*
 * Created on 06/10/2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pe.bn.login.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 2424012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ImagenTarjeta extends Serializable {
	
	public abstract String getNumTarImg();

	public abstract void setNumTarImg(String numTarImg);
	
	public abstract String getNomValorImg();

	public abstract void setNomValorImg(String nomValorImg);
	
	public abstract String getFechaIng();
 
	public abstract void setFechaIng(String fechaIng);
	
	public abstract String getUltFecha();
	 
	public abstract void setUltFecha(String ultFecha);
	
	public abstract String getNumIntento();
	 
	public abstract void setNumIntento(String numIntento);
	
	public abstract void setNumIntDia(String numIntDia);
	
	public abstract void setNumSec(String numSec);
	
	public abstract String getCodCat();

	public abstract void setCodCat(String codCat);
	
}
