/*
 * Created on 10/01/2007
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
public class Limite implements Serializable {

	/**
	 * 
	 */
	
	private String codPerfil = "";
	private String codModulo = "";
	private String codMoneda = "";
	private double montoMinimo;
	private double montoMaximo;
	
	public Limite() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the codMoneda.
	 */
	public String getCodMoneda() {
		return codMoneda;
	}
	/**
	 * @param codMoneda The codMoneda to set.
	 */
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	/**
	 * @return Returns the montoMaximo.
	 */
	public double getMontoMaximo() {
		return montoMaximo;
	}
	/**
	 * @param montoMaximo The montoMaximo to set.
	 */
	public void setMontoMaximo(double montoMaximo) {
		this.montoMaximo = montoMaximo;
	}
	/**
	 * @return Returns the montoMinimo.
	 */
	public double getMontoMinimo() {
		return montoMinimo;
	}
	/**
	 * @param montoMinimo The montoMinimo to set.
	 */
	public void setMontoMinimo(double montoMinimo) {
		this.montoMinimo = montoMinimo;
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
