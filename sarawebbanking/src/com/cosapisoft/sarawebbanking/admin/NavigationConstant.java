/*
 * Created on 27/04/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.cosapisoft.sarawebbanking.admin;

/**
 * @author David
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NavigationConstant {
	//Constantes que tienen que ser iguales en clases y jsps
	public static String ID_MENU_INICIO_DIA = "mnuInicioDia";
	public static String ID_MENU_CONFIGURACION = "mnuConfiguracion";
	public static String ID_MENU_SERVIDOR_BACKUP = "mnuServidorBackup";

	public static String ID_INICIO_DIA = "cmdInicioDia";
	public static String ID_RE_INICIO_DIA = "cmdReInicioDia";
	public static String ID_FIN_DIA = "cmdFinDia";
	
	public static String ID_ACEPTAR = "cmdAceptar";
	public static String ID_CANCELAR = "cmdCancelar";
	public static String ID_CAMBIAR = "cmdCambiar";
	//Fin
	
	public static String PAG_INICIO = "/sarawebbanking/admin/Inicio.html";
	public static String PAG_INICIO_SESION = "/sarawebbanking/admin/InicioDeSesion.jsp";
	// public static String PAG_INICIO_DIA = "/sarawebbanking/admin/InicioDeDia.jsp";
	public static String PAG_INICIO_DIA = "/sarawebbanking/servlet/Admin.html";
	public static String PAG_CONFIGURACION = "/sarawebbanking/admin/ConfiguracionAgencia.jsp";
	public static String PAG_SERVIDOR_BACKUP = "/sarawebbanking/admin/InicioServidorBackup.jsp";
	
	public static String PAG_EJECUCION = "/sarawebbanking/admin/EjecucionDeProceso.jsp";
	public static String PAG_ERROR = "/sarawebbanking/admin/ErrorGeneral.jsp";
	public static String PAG_EXITO = "/sarawebbanking/admin/Exito.jsp";
	public static String PAG_ACCESO = "/sarawebbanking/admin/AccesoDenegado.jsp";


	
	
	
	/**
	 * 
	 */
	public NavigationConstant() {
		super();
		// TODO Auto-generated constructor stub
	}

}
