/*
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.cosapisoft.sarawebbranch.common.beans;

import java.io.Serializable;
import java.util.Vector;

/**
 * @author Miguel
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBAccessResponse implements Serializable{
	/**
	 * 
	 */
	private Vector resultado;
	private String mensajeError;

	public DBAccessResponse() {
		super();
		resultado = new Vector();
		mensajeError = "";
		// TODO Auto-generated constructor stub
	}
	public String getMensajeError() {
		return mensajeError;
	}
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	public Vector getResultado() {
		return resultado;
	}
	public void setResultado(Vector resultado) {
		this.resultado = resultado;
	}
}