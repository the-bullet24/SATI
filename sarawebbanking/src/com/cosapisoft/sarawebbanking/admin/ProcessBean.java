/*
 * Created on 28/04/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.cosapisoft.sarawebbanking.admin;

import java.util.ArrayList;

/**
 * @author David
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProcessBean {
	private String processID;
	private String titleProcess;
	private String message;
	private ArrayList subProcessList;
	public static String PROC_ACTUALIZACION_SISTEMA = "Actualización del sistema con versiones nuevas existentes";
	public static String PROC_ACTUALIZACION_FECHA = "Actualización de Fecha de Proceso desde el Computador Central";
	public static String PROC_ACTUALIZACION_AGENCIA = "Actualización de la Agencia virtual desde el Computador Central";
	public static String PROC_PASE_DIARIO = "Pase de Movimientos Diarios al Diario Histórico";
	public static String PROC_DEPURACION_HISTORICO = "Depuración del Diario Histórico";
	public static String PROC_BLOQUEO_AGENCIA = "Bloqueo de Agencia";
	public static String PROC_DESBLOQUEO_AGENCIA = "Desbloqueo de Agencia";
	public static String PROC_GENERACION_ARCHIVO_RPT_ESTADO = "Generación de Archivo de Reporte de Estado";
	
	/**
	 * 
	 */
	public ProcessBean() {
		super();
		processID = "";
		titleProcess = "";
		subProcessList = new ArrayList();
	}
	
	public String getProcessID() {
		return processID;
	}
	public void setProcessID(String processID) {
		this.processID = processID;
	}
	public ArrayList getSubProcessList() {
		return subProcessList;
	}
	public void setSubProcessList(ArrayList subProcessList) {
		this.subProcessList = subProcessList;
	}
	public String getTitleProcess() {
		return titleProcess;
	}
	public void setTitleProcess(String titleProcess) {
		this.titleProcess = titleProcess;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
