/*
 * Creado el 24/10/2008
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.listener;

import java.util.HashMap;

/**
 * @author 
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ConstanteParametros {

	public static String BN_RESULT_PROCESO_OK = "0000";
	public static String BN_RESULT_SENDER_OK = "0001";
	public static String BN_RESULT_RECIBE_OK = "0002";
	public static String BN_RESULT_PROCESO_MASIVO = "0010";

	
	public static String BN_PARAM_WS_SATI_6201 				= "url.ws.sati6201.host";  
	public static String BN_PARAM_WS_SATI_PINBLOCK_V2	 	= "url.ws.pinblock.v2";  
	public static String BN_PARAM_WS_SATI_INTERFAZ_SERVICIOS= "url.ws.interfaz.servicios";
	public static String BN_PARAM_WS_SATI_SIMM				= "url.ws.simm";
	public static String BN_PARAM_WS_SATI_SIMS				= "url.ws.sims";
	public static String BN_PARAM_WS_SATI_NOTIFICACION		= "url.ws.notificacion";
	public static String BN_PARAM_WS_SATI_CLAVE_DINAMICA	= "url.ws.clave.dinamica";
	public static String BN_PARAM_WS_SATI_ESTADO_CTA_CTE_NUEVO		= "url.ws.estado.cta.cte.new";
	public static String BN_PARAM_WS_SATI_ESTADO_CTA_CTE_ANTIGUO	= "url.ws.estado.cta.cte.old";
	public static String BN_PARAM_SATI_IND_FECHA_ESTADO_CTA_CTE	= "param.fecha.corte.cta.cte";
	
	public static String BN_PARAM_CLAVE_SMS_CONSULTA_TOKEN	= "url.rest.service.consulta.token";
	public static String BN_PARAM_CLAVE_SMS_GENERA_CLAVE	= "url.rest.service.genera.token";
	public static String BN_PARAM_CLAVE_SMS_VALIDA_CLAVE	= "url.rest.service.valida.token";
	
	public static String BN_PARAM_CLAVE_SMS_GENERA_CLAVE_SMS_TOKEN	= "url.rest.service.genera.sms.act";
	public static String BN_PARAM_CLAVE_SMS_ACTIVA_CLAVE_SMS_TOKEN	= "url.rest.service.activ.clave.sms";
	public static String BN_PARAM_CLAVE_SMS_DESAFILIA_CLAVE_SMS_TOKEN	= "url.rest.service.desaf.clave.sms";
	
	public static String BN_PARAM_CLAVE_SMS_GENERA_MIGRACION	= "url.rest.service.gen.sms.mig.token";
	public static String BN_PARAM_CLAVE_SMS_ACTIVA_MIGRACION	= "url.rest.service.act.isms.mig.token";
	public static String BN_PARAM_CLAVE_SMS_VALIDA_MIGRACION	= "url.rest.service.val.sms.mig.token";
	
	public static String BN_PARAM_DATACOM_SATI_DRIVER		= "conexion.driver";  
	public static String BN_PARAM_DATACOM_SATI_DB			= "conexion.db";
	public static String BN_PARAM_DATACOM_SATI_URL			= "conexion.url";
	public static String BN_PARAM_DATACOM_SATI_PORT			= "conexion.port";
	public static String BN_PARAM_DATACOM_SATI_USER			= "conexion.user";
	public static String BN_PARAM_DATACOM_SATI_PASS			= "conexion.password";
	public static String BN_PARAM_DATACOM_SATI_SYSTEM		= "conexion.systemID";
	public static String BN_PARAM_DATACOM_SATI_APP			= "conexion.appID";
	public static String BN_PARAM_DATACOM_SATI_SERVER		= "conexion.servername";
	public static String BN_PARAM_DATACOM_SATI_POOL			= "true";
	public static String BN_PARAM_DATACOM_SATI_MAX_CON		= "DatacomMaxconnections";
	public static String BN_PARAM_DATACOM_SATI_WAIT_TIME	= "DatacomWaittime";
	public static String BN_PARAM_DATACOM_SATI_INI_CON		= "DatacomMaxconnections";
	
	public static String BN_PARAM_PARAMETRO_SATI_PINBLOCK	= "flag.operaciones.pinblock";
	

	public static String BN_PARAM_SERVICE_CORREO	= "url.servidorCorreo";
	public static String BN_PARAM_FLAG_VER_CAPTCHA  	= "flag.ver.capcha";
	public static String BN_PARAM_FLAG_VER_LOG  		= "flag.ver.log";
	
	public static String BN_PARAM_MS_LISTAS							= "url.rest.service.listas";
	public static String BN_PARAM_MS_CONSULTA_INVITACIONES			= "url.rest.service.consulta.invitacio";
	public static String BN_PARAM_MS_CONSULTA_CONFIG_TARJ			= "url.rest.service.cons.config.tarjet";
	public static String BN_PARAM_MS_REGISTRAR_CONFIG_TARJ			= "url.rest.service.reg.config.tarjet";
	public static String BN_PARAM_MS_INFOCARD						= "url.rest.service.inforcard";
	
	public static String BN_PARAM_WS_GATEWAY_HOST_SATI				= "url.gateway.host.sati";


	public static String BN_PARAM_WS_PRODUCTOS						= "url.rest.service.consultaProductos";
	public static String BN_PARAM_WS_SALDOS							= "url.rest.service.consultaSaldos";
	
	public static HashMap BN_PARAM_SISTEMA = null;
	public static HashMap BN_LIST_PARAMETRO = null;
	
	
	// Registro del LOG
	public static final String LOGGER_DEBUG_NIVEL_1 = "1";
	public static final String LOGGER_DEBUG_NIVEL_2 = "2";
	public static String BN_LOGGER_PRINT_ERROR = null;
	public static String BN_LOGGER_PRINT_DEBUG_NIVEL_1 = null;
	public static String BN_LOGGER_PRINT_DEBUG_NIVEL_2 = null;
	
	public static String BN_ERR_LOGGER_FILE = null;
	public static String BN_ERR_LOGGER_CONSOLE = null;
	public static String BN_ERR_LOGGER_PATH = null;
	
	public static String BN_PROC_LOGGER_FILE = null;
	public static String BN_PROC_LOGGER_CONSOLE = null;
	public static String BN_PROC_LOGGER_PATH = null;
	
	public static String BN_PARAM_PARAMETRO_SATI_INTEROPERABILIDAD	= "flag.ver.interoperabilidad";
	
	public static String BN_PARAM_PARAMETRO_SATI_SUBMENU1			= "submenu.codigo.ocultar1";
	public static String BN_PARAM_PARAMETRO_SATI_SUBSUBMENU2		= "subsubmenu.codigo.ocultar2";
	public static String BN_PARAM_PARAMETRO_SATI_SUBSUBMENU1		= "subsubmenu.codigo.ocultar1";


	public static String BN_PARAM_PARAMETRO_URL_GATE_NOVA	= "url.gate.nova";
	
	
}
