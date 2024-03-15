/*
 * Created on 06/02/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.cosapisoft.sarawebbranch.server;

import java.io.Serializable;

/**
 * @author David
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GeneralParameters implements Serializable{

	/**
	 * 
	 */
	
	private String keyDES_A = ""; 
	private String keyDES_B = "";
	private String keyMAC = "";
	private String keyPinPad = "";
	private String tipoEncripcion = "";
	
	public GeneralParameters() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Devuelve keyDES_A.
	 */
	public String getKeyDES_A() {
		return keyDES_A;
	}
	/**
	 * @param keyDES_A El keyDES_A a establecer.
	 */
	public void setKeyDES_A(String keyDES_A) {
		this.keyDES_A = keyDES_A;
	}
	/**
	 * @return Devuelve keyDES_B.
	 */
	public String getKeyDES_B() {
		return keyDES_B;
	}
	/**
	 * @param keyDES_B El keyDES_B a establecer.
	 */
	public void setKeyDES_B(String keyDES_B) {
		this.keyDES_B = keyDES_B;
	}
	/**
	 * @return Devuelve keyMAC.
	 */
	public String getKeyMAC() {
		return keyMAC;
	}
	/**
	 * @param keyMAC El keyMAC a establecer.
	 */
	public void setKeyMAC(String keyMAC) {
		this.keyMAC = keyMAC;
	}
	/**
	 * @return Devuelve keyPinPad.
	 */
	public String getKeyPinPad() {
		return keyPinPad;
	}
	/**
	 * @param keyPinPad El keyPinPad a establecer.
	 */
	public void setKeyPinPad(String keyPinPad) {
		this.keyPinPad = keyPinPad;
	}
	/**
	 * @return Devuelve tipoEncripcion.
	 */
	public String getTipoEncripcion() {
		return tipoEncripcion;
	}
	/**
	 * @param tipoEncripcion El tipoEncripcion a establecer.
	 */
	public void setTipoEncripcion(String tipoEncripcion) {
		this.tipoEncripcion = tipoEncripcion;
	}
}
