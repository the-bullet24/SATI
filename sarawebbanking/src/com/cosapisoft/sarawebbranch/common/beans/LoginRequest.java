/*
 * Created on 12/03/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.cosapisoft.sarawebbranch.common.beans;

import java.io.Serializable;

/**
 * @author cosapi_sac_04
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LoginRequest implements Serializable{

	/**
	 * 
	 */
	private String codUsuario = "";
	private String clave = "";
	private String codModulo = "";
	private String codAgencia = "";
	
	public LoginRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the clave.
	 */
	public String getClave() {
		return clave;
	}
	/**
	 * @param clave The clave to set.
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	/**
	 * @return Returns the codAgencia.
	 */
	public String getCodAgencia() {
		return codAgencia;
	}
	/**
	 * @param codAgencia The codAgencia to set.
	 */
	public void setCodAgencia(String codAgencia) {
		this.codAgencia = codAgencia;
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
}
