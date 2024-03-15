/*
 * Created on 23/01/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.cosapisoft.sarawebbranch.common.beans;

import java.io.Serializable;

/**
 * @author Miguel
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBQuery implements Serializable{
	/**
	 * 
	 */

	public static int TIPO_SELECT = 1;
	public static int TIPO_UPDATE = 2;
	public static int TIPO_INSERT = 3;
	public static int TIPO_DELETE = 4;

	public DBQuery() {
		super();
		// TODO Auto-generated constructor stub
	}
	private int tipoQuery = 0;
	private String sQuery = "";
	/**
	 * @return Returns the codUsuario.
	 */
	public int getTipoQuery() {
		return tipoQuery;
	}
	/**
	 * @param codUsuario The codUsuario to set.
	 */
	public void setTipoQuery(int tipoQuery) {
		this.tipoQuery = tipoQuery;
	}
	/**
	 * @return Returns the perfiles.
	 */
	public String getQuery() {
		return sQuery;
	}
	/**
	 * @param perfiles The perfiles to set.
	 */
	public void setQuery(String Query) {
		this.sQuery = Query;
	}
}
