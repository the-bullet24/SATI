/*
 * Created on 06/02/2007
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
public class InitialParametersInfo implements Serializable{

	/**
	 * 
	 */
	
	private String SecurityModuleIP = ""; //IP de SARAWebManager
	private String commPort = ""; //Puerto TCP/IP para Comunicaciones Estación - Servidor
	private String commServer = ""; //Dirección o nombre de Servidor TCP/IP para Comunicaciones Estación - Servidor
	private String keyDES_A = ""; 
	private String keyDES_B = "";
	private String keyMAC = "";
	private String keyPinPad = "";
	private String LUName = "";
	private String codAgencia = "";
	private String nameAgencia = "";
	private String fechaProceso = "";
	private String tipoEncripcion = ""; //0=Sin Encripción, D=DES, T=Triple DES
	private String configuracionTrxClaves = "";
	private String pathDistribucion = "";
	
	public static String COD_MODULO = "03"; //SARAWebBranch
	
	
	public InitialParametersInfo() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return Returns the nameAgencia.
	 */
	public String getNameAgencia() {
		return nameAgencia;
	}
	/**
	 * @param nameAgencia The nameAgencia to set.
	 */
	public void setNameAgencia(String nameAgencia) {
		this.nameAgencia = nameAgencia;
	}
	/**
	 * @return Returns the commPort.
	 */
	public String getCommPort() {
		return commPort;
	}
	/**
	 * @param commPort The commPort to set.
	 */
	public void setCommPort(String commPort) {
		this.commPort = commPort;
	}
	/**
	 * @param name The lUName to set.
	 */
	public void setLUName(String name) {
		LUName = name;
	}
	/**
	 * @return Returns the keyDES_A.
	 */
	public String getKeyDES_A() {
		return keyDES_A;
	}
	/**
	 * @param keyDES_A The keyDES_A to set.
	 */
	public void setKeyDES_A(String keyDES_A) {
		this.keyDES_A = keyDES_A;
	}
	/**
	 * @return Returns the keyDES_B.
	 */
	public String getKeyDES_B() {
		return keyDES_B;
	}
	/**
	 * @param keyDES_B The keyDES_B to set.
	 */
	public void setKeyDES_B(String keyDES_B) {
		this.keyDES_B = keyDES_B;
	}
	/**
	 * @return Returns the keyMAC.
	 */
	public String getKeyMAC() {
		return keyMAC;
	}
	/**
	 * @param keyMAC The keyMAC to set.
	 */
	public void setKeyMAC(String keyMAC) {
		this.keyMAC = keyMAC;
	}
	/**
	 * @return Returns the keyPinPad.
	 */
	public String getKeyPinPad() {
		return keyPinPad;
	}
	/**
	 * @param keyPinPad The keyPinPad to set.
	 */
	public void setKeyPinPad(String keyPinPad) {
		this.keyPinPad = keyPinPad;
	}
	/**
	 * @return Returns the lUName.
	 */
	public String getLUName() {
		return LUName;
	}
	/**
	 * @return Returns the securityModuleIP.
	 */
	public String getSecurityModuleIP() {
		return SecurityModuleIP;
	}
	/**
	 * @param securityModuleIP The securityModuleIP to set.
	 */
	public void setSecurityModuleIP(String securityModuleIP) {
		SecurityModuleIP = securityModuleIP;
	}
	public String getCommServer() {
		return commServer;
	}
	public void setCommServer(String commServer) {
		this.commServer = commServer;
	}
	public String getFechaProceso() {
		return fechaProceso;
	}
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public String getConfiguracionTrxClaves() {
		return configuracionTrxClaves;
	}

	public void setConfiguracionTrxClaves(String configuracionTrxClaves) {
		this.configuracionTrxClaves = configuracionTrxClaves;
	}

	public String getTipoEncripcion() {
		return tipoEncripcion;
	}

	public void setTipoEncripcion(String tipoEncripcion) {
		this.tipoEncripcion = tipoEncripcion;
	}

	public String getPathDistribucion() {
		return pathDistribucion;
	}

	public void setPathDistribucion(String pathDistribucion) {
		this.pathDistribucion = pathDistribucion;
	}
}
