/*
 * Created on 15/03/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.cosapisoft.sarawebmanager.security.beans;

import java.io.Serializable;

/**
 * @author cosapi_sac_01
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Actividad implements Serializable {

	private String codModulo;
	private String codActividad;	
	private String nomActividad;
	
	/**
	 * @return Returns the nomActividad.
	 */
	public String getNomActividad() {
		return nomActividad;
	}
	/**
	 * @param nomActividad The nomActividad to set.
	 */
	public void setNomActividad(String nomActividad) {
		this.nomActividad = nomActividad;
	}
	/**
	 * @return Returns the codActividad.
	 */
	public String getCodActividad() {
		return codActividad;
	}
	/**
	 * @param codActividad The codActividad to set.
	 */
	public void setCodActividad(String codActividad) {
		this.codActividad = codActividad;
	}
	/**
	 * @return Returns the codModulo.
	 */
	public String getCodModulo() {
		return codModulo;
	}
	/**
	 * @param codModulo The codModulo to set.
	 */
	public void setCodModulo(String codModulo) {
		this.codModulo = codModulo;
	}
}
