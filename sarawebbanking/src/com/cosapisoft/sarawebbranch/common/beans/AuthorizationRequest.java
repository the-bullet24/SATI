/*
 * Created on 04/04/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.cosapisoft.sarawebbranch.common.beans;

import java.io.Serializable;

/**
 * @author David
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AuthorizationRequest implements Serializable{
	/**
	 * 
	 */
	private String codUsuario = "";
	private String clave = "";
	private String codModulo = "";
	private String codAgencia = "";
	private String codPerfil = "";
	private String codMoneda = "";
	private double importe = 0;
	
	
	public AuthorizationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getCodAgencia() {
		return codAgencia;
	}
	public void setCodAgencia(String codAgencia) {
		this.codAgencia = codAgencia;
	}
	public String getCodModulo() {
		return codModulo;
	}
	public void setCodModulo(String codModulo) {
		this.codModulo = codModulo;
	}
	public String getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	public String getCodPerfil() {
		return codPerfil;
	}
	public void setCodPerfil(String codPerfil) {
		this.codPerfil = codPerfil;
	}
	public String getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
}
