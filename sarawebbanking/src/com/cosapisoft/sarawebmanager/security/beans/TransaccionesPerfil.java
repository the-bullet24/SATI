/*
 * Created on 14/03/2007
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
public class TransaccionesPerfil implements Serializable {

	private String codPerfil = "";
	private String codModulo = "";
	private String codTransaccion = "";
	private int typTransaccion;	
	
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
	 * @return Returns the codTransaccion.
	 */
	public String getCodTransaccion() {
		return codTransaccion;
	}
	/**
	 * @param codTransaccion The codTransaccion to set.
	 */
	public void setCodTransaccion(String codTransaccion) {
		this.codTransaccion = codTransaccion;
	}	
	/**
	 * @return Returns the typTransaccion.
	 */
	public int getTypTransaccion() {
		return typTransaccion;
	}
	/**
	 * @param typTransaccion The typTransaccion to set.
	 */
	public void setTypTransaccion(int typTransaccion) {
		this.typTransaccion = typTransaccion;
	}
	public TransaccionesPerfil(){
		super();
	}
}
