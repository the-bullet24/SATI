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
public class GruposPerfiles implements Serializable {

	private String codModulo;
	private String codGrupo;			    	
	private String codPerfil;
	/**
	 * @return Returns the codGrupo.
	 */
	public String getCodGrupo() {
		return codGrupo;
	}
	/**
	 * @param codGrupo The codGrupo to set.
	 */
	public void setCodGrupo(String codGrupo) {
		this.codGrupo = codGrupo;
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
	
}
