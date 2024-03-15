/*
 * Created on 23/01/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.cosapisoft.sarawebbranch.common.beans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author David
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PerfilesUsuario implements Serializable{

	/**
	 * 
	 */
	public PerfilesUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String codUsuario = "";
	private ArrayList perfiles = new ArrayList();
	/**
	 * @return Returns the codUsuario.
	 */
	public String getCodUsuario() {
		return codUsuario;
	}
	/**
	 * @param codUsuario The codUsuario to set.
	 */
	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}
	/**
	 * @return Returns the perfiles.
	 */
	public ArrayList getPerfiles() {
		return perfiles;
	}
	/**
	 * @param perfiles The perfiles to set.
	 */
	public void setPerfiles(ArrayList perfiles) {
		this.perfiles = perfiles;
	}
}
