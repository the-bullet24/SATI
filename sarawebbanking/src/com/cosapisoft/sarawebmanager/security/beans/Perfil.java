/*
 * Created on 08/01/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.cosapisoft.sarawebmanager.security.beans;

import java.io.Serializable;

/**
 * @author cosapi_sac_04
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Perfil implements Serializable{

	/**
	 * 
	 */
	public Perfil() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String codPerfil = "";
	private String codModulo = "";
	private String nombrePerfil = "";
	
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
	/**
	 * @return Returns the codPerfil.
	 */
	public String getCodPerfil() {
		return codPerfil;
	}
	/**
	 * @param codPerfil The codPerfil to set.
	 */
	public void setCodPerfil(String codPerfil) {
		this.codPerfil = codPerfil;
	}
	/**
	 * @return Returns the nombrePerfil.
	 */
	public String getNombrePerfil() {
		return nombrePerfil;
	}
	/**
	 * @param nombrePerfil The nombrePerfil to set.
	 */
	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}
}
