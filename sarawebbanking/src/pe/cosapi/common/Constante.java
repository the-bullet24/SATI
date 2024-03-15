/*
 * Creado el 02/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.common;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class Constante {
	
	public static final String GLOBAL_MESSAGE 							= "message";
	
	public static final String TARJETA_VACIA 							= "01";
	public static final String TARJETA_GLOBAL_DEBITO 					= "02";
	public static final String TARJETA_MULTIRED 						= "03";
	
	public static final String NOMBRE_PERSONA_NATURAL					= "Persona Natural";
	public static final String NOMBRE_PERSONA_JURIDICA					= "Persona Jurídica";
	
	public static final String CUENTA_AHORROS 							= "CA";

	public static final String COD_MSK_DEBITO 							= "TGD";
	/**ONLY WEB SERVICES ***/
	public static final String IP_SRV_WEB_SERVICES 						= "localhost"; 
	//public static final String IP_SRV_WEB_SERVICES 					="10.1.8.28";
	
	public static final String URI_WEB_SERVICES 						= "urn:host1:sara";
	public static final String AFI_TELEGIRO 							= "TELN";
	public static final String AFI_OTRO_BANCO 							= "PTAR";
	public static final String AFI_TRA_INTER_BANCO 						= "TRAI";
	
	/*************************************************************************************************/
	
	public static final String ENCRIPTACION_DES							= "D";
	public static final String ENCRIPTACION_TRIPLE_DES					= "T";
		
	/************* CONSTANTS FOR SEND HOST -- HEADER NO MODIFICAR SIN AUTORIZACION :@     ********/
	public static final String COD_LINPUT 								= "1084";
	
	public static final String CTRANS_DESP 								= "DI00";
	public static final String COD_CAUSAL 								= "WEB";
	public static final String CLOG_TRSQ								= "0";
	public static final String COD_USUARIO 								= "0000";
	public static final String COD_SUPERVISOR 							= "0000";
	public static final String DELIM_NUM								= "¬";
	public static final String DELIM_ALF								= "@";
	public static final String CLOG_EXTOR								= "0000000";
	public static final String FILLER									= "                  ";
	
	public static final String IDEN_ALFANUMERIC							= "A";
	
	public static final String SEP_NUMERIC								= "¬";
	public static final String SEP_ALPHANUMERIC							= "@";
	
	public static final String FILLER_BODY								= "!";
	public static final String SPACER_BODY								= "   ";
	
	
	/************* END CONSTANTS FOR SEND HOST -- HEADER NO MODIFICAR SIN AUTORIZACION :@ ********/
	
	/************* BEGIN CONSTANTS FOR WEB SERVICE 6 DIGITOS ********/
	
	public static final String COD_RESP								= "0";
	
	/************* END CONSTANTS FOR WEB SERVICE 6 DIGITOS ********/
	
	
	/************* BEGIN CONSTANTS FOR ENCRIPTION 4 DIGITS  ********/
	public static final String KEY4_DIGITS								= new StringBuffer().append((char)43).append((char)42).append((char)43).append((char)36).toString();					
	/************* END   CONSTANTS FOR ENCRIPTION 4 DIGITS  ********/
	
	/************MONEDAS*****************/
	public static final String MONEDA_SOL 								= "PEI";
	public static final String MONEDA_DOLAR 							= "USD";
	
	public static final String NOMBRE_MONEDA_SOL 						= "Soles";
	public static final String NOMBRE_MONEDA_DOLAR 						= "Dolares";
	
	public static final String SIMBOLO_MONEDA_SOL 						= "S/";
	public static final String SIMBOLO_MONEDA_DOLAR 					= "$";
	
	/**SOLAMENTE PARA LAS IMAGENES DEL JSP HEADER*/
	public static final String COD_PERSON_NAT 							= "01";
	public static final String COD_PERSON_JUR 							= "02";
	public static final String COD_BRANCH 								= "0001";
	
	/*************CONSTANTES MENU AND HEATHER .gif***********/
	public static final String IMG_LOGO_BN 									= "logobn.gif";
	
	public static final String IMG_BANNER_PN 								= "BannerPN.gif";
	public static final String IMG_BANNER_PJ 								= "BannerPJ.gif";
	
	
	public static final String IMG_CLOSE_SESSION_PN 						= "imgpn_cerrar-sesion.gif";
	public static final String IMG_CLOSE_SESSION_PJ 						= "imgpj_cerrar-sesion.gif";
	
	/**TIPO DE PRODUCTOS**/
	public static final String COD_CUENTA_AHORROS_MN	= "04";
	public static final String COD_CUENTA_AHORROS_ME	= "08";
	public static final String COD_CUENTA_CORRIENTE_MN	= "00";
	public static final String COD_CUENTA_CORRIENTE_ME	= "06";
	public static final String COD_CUENTA_PRESTAMOS		= "44";
	public static final String COD_CUENTA_CTS_MN		= "54";
	public static final String COD_CUENTA_CTS_ME		= "64";
	
	public static final String NOMBRE_CUENTA_AHORROS	= "Cta. Ahorros";
	public static final String NOMBRE_CUENTA_CORRIENTE	= "Cta. Corriente";
	public static final String NOMBRE_CUENTA_PRESTAMOS	= "Cta. Préstamos";
	public static final String NOMBRE_CUENTA_CTS		= "Cta. CTS";
	
	/**CODIGO DE TEXTOS DE AYUDA**/		
	public static final String COD_HLP_DET_DOCU		= "00004";
	public static final String COD_HLP_DET_SERV		= "00412";
	public static final String COD_HLP_DET_LOCAL	= "00411";
	public static final String COD_HLP_DET_MES		= "00410";
	public static final String COD_HLP_DEPARTAMENTO	= "00413";
	public static final String COD_HLP_AGENCIA		= "00414";
	public static final String COD_HLP_AFILIACION	= "00415";
	public static final String COD_HLP_DET_ENTIDAD	= "00416";
	public static final String COD_HLP_DET_TRIBUTO	= "00417";
	public static final String COD_HLP_BANCO		= "00420";
	public static final String COD_HLP_TIP_TARJETA	= "00435";
	public static final String COD_HLP_PGO_TELEFONO	= "00436";
	
	
	public static final String SERVICIOS		 		= "SERV";
	public static final String TELEGIRO_NACIONAL 		= "TELN";
	public static final String TELEGIRO_INTERNACIONAL 	= "TELI";
	public static final String TB_MISMO_BANCO 			= "TRAM";
	public static final String TB_INTERBANCO 			= "TRAI";
	public static final String PAGO_TARJETA 			= "PTAR";
	public static final String PAGO_TASAS			    = "PTAS";
	
	public static final String PAGO_TEL 			    = "PAGT";

	public static final String SELECCIONE				= "Seleccionar..."; 
	
	public static final String COD_INT_BLANK 		  = "0";
	
	public static final String TRANSFERENCIA_BANCARIA = "/transferenciaBancaria";
	public static final String TRANSFERENCIA_INTERBANCARIA = "/transferenciaInterbancaria";

	public static final String TRANSFERENCIA_BANCARIA_TITULO = "TRANSFERENCIA MISMO BANCO";
	public static final String TRANSFERENCIA_INTERBANCARIA_TITULO = "TRANSFERENCIA INTERBANCARIA";
	
	public static final String PAGO_TELEFONO = "/pagoTelefono";

	public static final String PAGO_TELEFONO_TITULO = "PAGO DE SERVICIO DE TELEFONIA";
	
	/************************************* USO EXCLUSIVO DE LOS LOGS *************************************************/
	public static final String REFRENDO_GENERACION_CLAVE	= "refrendoGeneracionClave";
	public static final String REFRENDO_CAMBIO_CLAVE		= "refrendoCambioClave";
	public static final String REFRENDO_BLOQUE_TARJETA		= "refrendoBloqueoTarjeta";
	
	public static final String REFRENDO_AFILIACION_SERVICIO	= "refrendoAfiliacionServicio";
	public static final String REFRENDO_AFILIACION_TRANSFERENCIA_BANCARIA	= "refrendoAfiliacionTransferenciaBancaria";
	public static final String REFRENDO_AFILIACION_TRANSFERENCIA_INTERBANCARIA	= "refrendoAfiliacionTransferenciaInterbancaria";
	public static final String REFRENDO_AFILIACION_TRANSFERENCIA_TARJETA	= "refrendoAfiliacionTarjeta";
	public static final String REFRENDO_AFILIACION_TELEGIRO	= "refrendoAfiliacionTelegiro";
	public static final String REFRENDO_DESAFILIACION	= "refrendoDesafiliacion";

	public static final String REFRENDO_SALDO_PRESTAMO		= "refrendoSaldoPrestamo";
	public static final String REFRENDO_SALDO_AHORROS	= "refrendoSaldoAhorros";
	public static final String REFRENDO_SALDO_CTACTE	= "refrendoSaldoCtaCte";
	public static final String REFRENDO_ULTIMOS_MOVIMIENTOS_AHORROS	= "refrendoUltimosMovimientosAhorros";
	public static final String REFRENDO_ULTIMOS_MOVIMIENTOS_CTS	= "refrendoUltimosMovimientosCTS";
	
	public static final String REFRENDO_CCI	= "refrendoCCIMN";
	public static final String REFRENDO_PAGO_MULTIRED	= "refrendoPagoMultired";
	public static final String REFRENDO_CALENDARIO_PAGO	= "refrendoCalendarioPago";
	public static final String REFRENDO_TELEGIRO	= "refrendoTelegiro";
	public static final String REFRENDO_TRANSFERENCIA_BANCARIA	= "refrendoTransferenciaBancaria";
	public static final String REFRENDO_TRANSFERENCIA_INTERBANCARIA	= "refrendoTransferenciaInterbancaria";
	public static final String REFRENDO_PAGO_TARJETA	= "refrendoPagoTarjeta";
	public static final String REFRENDO_PAGO_TASAS	= "refrendoPagoTasas";
	
	public static final String FORMATO_CUENTA  =  "xx-xxx-xxxxxx";
	public static final String FORMATO_TARJETA =  "xxxx-xxxx-xxxx-xxxx";
	
	/****************************************************** ESQUEMAS ***************************************************************/
	public static final String ESQUEMA1  =  "PV_SWB";
	public static final String ESQUEMA2 =  "BN_SWB_LOG";
	
	
	/****************************************************** CANALES ***************************************************************/
	public static final String CANAL_WEB 	= "0";
	public static final String CANAL_MOVIL 	= "1";
	
	/****************************************************** inicio const web service .net ***************************************************************/
	public static final String WCOPERATION_VALID_KEY6 			= "310";
	public static final String WCOPERATION_GENERATION_KEY6 	= "300";
	public static final String WCOPERATION_CHANGE_KEY6 		= "320";
	
	/****************************************************** end const web service .net ***************************************************************/
	/***CONSTANTES DE SALIDA DE SARAWEB****/
	public static final String OUTPUT_NUMBER_1	="700";
	public static final String OUTPUT_NUMBER_2	="701";
	public static final String OUTPUT_NUMBER_3	="702";
	public static final String OUTPUT_NUMBER_4	="703";
	public static final String OUTPUT_NUMBER_5	="704";
	public static final String OUTPUT_NUMBER_6	="705";
	public static final String OUTPUT_NUMBER_7	="706";
	public static final String OUTPUT_NUMBER_8	="707";
	public static final String OUTPUT_NUMBER_9	="708";
	public static final String OUTPUT_NUMBER_10	="709";
	public static final String OUTPUT_NUMBER_11	="710";
	public static final String OUTPUT_NUMBER_12	="711";
	public static final String OUTPUT_NUMBER_13	="712";
	public static final String OUTPUT_NUMBER_14	="713";
	public static final String OUTPUT_NUMBER_15	="714";
	public static final String OUTPUT_NUMBER_16	="715";
	public static final String OUTPUT_NUMBER_17	="716";
	public static final String OUTPUT_NUMBER_18	="717";
	public static final String OUTPUT_NUMBER_19	="718";
	public static final String OUTPUT_NUMBER_20	="719";
	public static final String OUTPUT_NUMBER_21	="720";
	public static final String OUTPUT_NUMBER_22	="721";
	public static final String OUTPUT_NUMBER_23	="722";
	public static final String OUTPUT_NUMBER_24	="723";
	public static final String OUTPUT_NUMBER_25	="724";
	public static final String OUTPUT_NUMBER_26	="725";
	public static final String OUTPUT_NUMBER_27	="726";
	public static final String OUTPUT_NUMBER_28	="727";
	public static final String OUTPUT_NUMBER_29	="728";
	public static final String OUTPUT_NUMBER_30	="729";
	public static final String OUTPUT_NUMBER_31	="730";
	public static final String OUTPUT_NUMBER_32	="731";
	public static final String OUTPUT_NUMBER_33	="732";
	public static final String OUTPUT_NUMBER_34	="733";
	public static final String OUTPUT_NUMBER_35	="734";
	public static final String OUTPUT_NUMBER_36	="735";
	public static final String OUTPUT_NUMBER_37	="736";
	public static final String OUTPUT_NUMBER_38	="737";
	public static final String OUTPUT_NUMBER_39	="738";
	public static final String OUTPUT_NUMBER_40	="739";
	public static final String OUTPUT_NUMBER_41	="740";
	public static final String OUTPUT_NUMBER_42	="741";
	public static final String OUTPUT_NUMBER_43	="742";
	public static final String OUTPUT_NUMBER_44	="743";
	public static final String OUTPUT_NUMBER_45	="744";
	public static final String OUTPUT_NUMBER_46	="745";
	public static final String OUTPUT_NUMBER_47	="746";
	public static final String OUTPUT_NUMBER_48	="747";
	public static final String OUTPUT_NUMBER_49	="748";
	public static final String OUTPUT_NUMBER_50	="749";
	public static final String OUTPUT_STRING_1	="800";
	public static final String OUTPUT_STRING_2	="801";
	public static final String OUTPUT_STRING_3	="802";
	public static final String OUTPUT_STRING_4	="803";
	public static final String OUTPUT_STRING_5	="804";
	public static final String OUTPUT_STRING_6	="805";
	public static final String OUTPUT_STRING_7	="806";
	public static final String OUTPUT_STRING_8	="807";
	public static final String OUTPUT_STRING_9	="808";
	public static final String OUTPUT_STRING_10	="809";
	public static final String OUTPUT_STRING_11	="810";
	public static final String OUTPUT_STRING_12	="811";
	public static final String OUTPUT_STRING_13	="812";
	public static final String OUTPUT_STRING_14	="813";
	public static final String OUTPUT_STRING_15	="814";
	public static final String OUTPUT_STRING_16	="815";
	public static final String OUTPUT_STRING_17	="816";
	public static final String OUTPUT_STRING_18	="817";
	public static final String OUTPUT_STRING_19	="818";
	public static final String OUTPUT_STRING_20	="819";
	public static final String OUTPUT_STRING_21	="820";
	public static final String OUTPUT_STRING_22	="821";
	public static final String OUTPUT_STRING_23	="822";
	public static final String OUTPUT_STRING_24	="823";
	public static final String OUTPUT_STRING_25	="824";
	public static final String OUTPUT_STRING_26	="825";
	public static final String OUTPUT_STRING_27	="826";
	public static final String OUTPUT_STRING_28	="827";
	public static final String OUTPUT_STRING_29	="828";
	public static final String OUTPUT_STRING_30	="829";
	public static final String OUTPUT_STRING_31	="830";
	public static final String OUTPUT_STRING_32	="831";
	public static final String OUTPUT_STRING_33	="832";
	public static final String OUTPUT_STRING_34	="833";
	public static final String OUTPUT_STRING_35	="834";
	public static final String OUTPUT_STRING_36	="835";
	public static final String OUTPUT_STRING_37	="836";
	public static final String OUTPUT_STRING_38	="837";
	public static final String OUTPUT_STRING_39	="838";
	public static final String OUTPUT_STRING_40	="839";
	public static final String OUTPUT_STRING_41	="840";
	public static final String OUTPUT_STRING_42	="841";
	public static final String OUTPUT_STRING_43	="842";
	public static final String OUTPUT_STRING_44	="843";
	public static final String OUTPUT_STRING_45	="844";
	public static final String OUTPUT_STRING_46	="845";
	public static final String OUTPUT_STRING_47	="846";
	public static final String OUTPUT_STRING_48	="847";
	public static final String OUTPUT_STRING_49	="848";
	public static final String OUTPUT_STRING_50	="849";
	
	public static String CONST_MODULO="13";
	public static String INICIO_101="";
	public static String INICIO_102="";
	
	public static String SESSION_CADUCA="1";
	
	public static final String BN_SWB_LOG="bn_swb_log";
	public static String FLG_TABLE=null;
	public static final String ESTADO_CANCELADA="8";
	public static boolean VER_LOG 						= false;
	public static final String CONSTANTE_OS 			= "1"; //1-DESARROLLO(WINDOWS) --- 2-CERTIFICACION(LINUX) ---3-PRODUCCION(LINUX)
}
