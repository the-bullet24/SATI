/*
 * Creado el 02/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.common;

import pe.com.bn.sati.domain.BnwsParametro.ParamUrl;

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
	public static final String COD_TRANSACCION_FINANCIERA 				= "FIN";
	public static final String COD_TRANSACCION_ADMINISTRATIVA 			= "ADMIN";
	
	//CONSTANTES PARA "TypeTrans" JSON
	public static final String TIP_OPER_GIRO = "1";
	public static final String TIP_OPER_TRANSFERENCIA = "2";
	public static final String TIP_OPER_RECARGA = "3";
	public static final String TIP_OPER_PAGO_TELEF_FIJA = "4";
	public static final String TIP_OPER_PAGO_TELEF_MOVIL = "5";
	public static final String TIP_OPER_PAGO_CABLE = "6";
	public static final String TIP_OPER_PAGO_LUZ = "7";
	public static final String TIP_OPER_PAGO_AGUA = "8";
	public static final String TIP_OPER_PAGO_PRESTAMO = "9";
	public static final String TIP_OPER_PAGO_TRANSF_IB = "10";//Transferencia IB Linea
	public static final String TIP_OPER_PAGO_PAGO_TARJETA = "11";//Pago Tarjeta otro banco Linea
	public static final String TIP_OPER_PAGO_TRANSF_IB_DIFERIDO = "12";
	public static final String TIP_OPER_PAGO_PAGO_TARJETA_DIFERIDO = "13";
	public static final String TIP_OPER_GENERA_CLAVE = "14";
	public static final String TIP_OPER_RESETEA_CLAVE = "15";
	public static final String TIP_OPER_ACTUALIZACION_DATOS = "16";
	public static final String TIP_OPER_RETIRO = "17";
	public static final String TIP_OPER_PAGO_EMPRESAS_BELLEZA = "18";
	public static final String TIP_OPER_BLOQUEO_TARJETA = "19";
	public static final String TIP_OPER_ADELANTO_SUELDO = "20";
	public static final String TIP_OPER_PAGO_UNIVERSIDADES = "21";
	public static final String TIP_OPER_PAGO_INST_PUBLICA = "22";
	public static final String TIP_OPER_PAGO_TARJETA_MISMO_TITULAR = "23";//Pago Tarjeta Mismo Titular
	public static final String TIP_OPER_PAGO_TARJETA_BN_TERCERO = "24";//Pago Tarjeta BN Terceros
	public static final String TIP_OPER_PAGO_CUPON_SERVICIOS = "30";
	public static final String TIP_OPER_ADMINISTRATIVA = "40";
	public static final String TIP_OPER_OPCIONES_SEGURIDAD_CAMBIO_SELLO = "46";
	public static final String TIP_OPER_ELECCION_MEDIO_ENVIO = "54";
	public static final String TIP_OPER_CONFIGURACION_TARJETAS = "55";
	public static final String TIP_OPER_CONFIGURACION_AF_CONTACTOS = "56";
	public static final String TIP_OPER_CAMBIO_CLAVE_INTERNET = "58";
	
	
	//public static final String TIP_OPER_ADMINISTRATIVAS = "21";
	
	public static final String PARAM_URL_GENERA_MIGRACION			 	= "bn.url.rest.service.genera.sms.migracion.token";
	public static final String PARAM_URL_VALIDA_MIGRACION			 	= "bn.url.rest.service.valida.sms.migracion.token";
	public static final String PARAM_URL_ACTIVA_MIGRACION			 	= "bn.url.rest.service.activa.sms.migracion.token";
	public static final String COD_MSK_DEBITO 							= "TGD";
	/**ONLY WEB SERVICES ***/
	public static final String IP_SRV_WEB_SERVICES 						= "localhost"; 
	//public static final String IP_SRV_WEB_SERVICES 						="10.1.8.28";
	
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
	public static final String SEP_LIST									= "-";
	
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
	public static final String MONEDA_SOL_ISO   						= "PEN";
	public static final String MONEDA_DOLAR 							= "USD";
	
	public static final String COD_MONEDA_SOL 							= "604";
	public static final String COD_MONEDA_DOL 							= "840";
	
	public static final String NOMBRE_MONEDA_SOL 						= "Soles";
	public static final String NOMBRE_MONEDA_DOLAR 						= "Dólares";
	public static final String NOMBRE_MONEDA_DOLAR_MAIL					= "D&oacute;lares";
	public static final String SIMBOLO_MONEDA_SOL 						= "S/";
	public static final String SIMBOLO_MONEDA_DOLAR 					= "US$";
	public static final String TOPE_MONTO_MAXIMO						= "10000";
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
	
	/**SOLAMENTE PARA LOGIN CON NUEVO TIPO DE DOCUMENTOS*/
	public static final String COD_DNI 										= "01";
	public static final String COD_C_EXTRANJERO 							= "04";
	public static final String COD_PASAPORTE 								= "05";
	
	public static final String NOMBRE_DNI 										= "DNI";
	public static final String NOMBRE_C_EXTRANJERO 								= "C. EXTRANJERIA";
	public static final String NOMBRE_PASAPORTE 								= "PASAPORTE";
	public static final String NOMBRE_RUC 										= "RUC";
	
	public static final String COD_RUC 											= "006";
	public static final String COD_DNI_ 										= "001";
	public static final String COD_CARNE_EXT 									= "003";
	
	public static final int TIPO_DOCUMENTO_DNI 									= 1;
	
	/**TIPO DE PRODUCTOS**/
	public static final String COD_CUENTA_AHORROS_MN	= "04";
	public static final String COD_CUENTA_AHORROS_ME	= "08";
	public static final String COD_CUENTA_CORRIENTE_MN	= "00";
	public static final String COD_CUENTA_CORRIENTE_ME	= "06";
	public static final String COD_CUENTA_PRESTAMOS		= "44";
	public static final String COD_CUENTA_CTS_MN		= "54";
	public static final String COD_CUENTA_CTS_ME		= "64";
	public static final String COD_TARJETA_CREDITO		= "01";
	public static final String COD_SERVICIO_PRESTAMO	= "88";
	public static final String COD_SERVICIO_TAR_CREDITO	= "90";
	
	public static final String COD_CUENTA_ESTADO_ACTIVA = "01";
	public static final String COD_CUENTA_SITUACION_NORMAL = "25";
	
	public static final String NOMBRE_CUENTA_AHORROS	= "Cta. Ahorros";
	public static final String NOMBRE_CUENTA_CORRIENTE	= "Cta. Corriente";
	public static final String NOMBRE_CUENTA_PRESTAMOS	= "Cta. Préstamos";
	public static final String NOMBRE_CUENTA_CTS		= "Cta. CTS";
	public static final String NOMBRE_TARJETA_CREDITO	= "Tarj. Crédito";
	
	public static final String NOMBRE_AHORROS				= "Ahorros";
	public static final String NOMBRE_CORRIENTE				= "Corriente";
	public static final String NOMBRE_PRESTAMOS				= "Préstamos";
	public static final String NOMBRE_CTS					= "CTS";
	public static final String NOMBRE_TARJETA				= "Tarjeta Crédito";
	
	public static final String NOMBRE_COMP_AHORROS	= "Cuenta de Ahorros";
	public static final String NOMBRE_COMP_CORRIENTE	= "Cuenta Corriente";
	
	public static final String CONST_ETIQUETA_ESTADO_CTA_CTE 	= "CODIGO DE CLIENTE  :";
	public static final String CONST_CODIGO_VACIO_CTA_CTE	 	= "000000";
	
	public static final String PAGO_TASAS_OSCE_CADENA	= " anuales < S/";
	public static final String PAGO_TASAS_OSCE_CADENA_HR	= "<HR>";
	public static final String PAGO_TASAS_OSCE_CADENA_HR2	= "<hr>";
	
	public static final String PAGO_TASAS_OSCE_REEMPLAZAR_CADENA	= " anuales &lt; S/";
	public static final String PAGO_TASAS_OSCE_REEMPLAZAR_CADENA_HR	= " ";
	
	public static final String PAGO_TASAS_COD_TRX_MINISTERIO_INT	= "00116";
	
	/**CODIGO DE TEXTOS DE AYUDA**/		
	public static final String COD_HLP_CORREO		= "00000";
	
	
	public static final String COD_HLP_DET_DOCU		= "00004";
	public static final String COD_HLP_DET_DOCU_GIRO= "00054";
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
	public static final String COD_HLP_PGO_MOVISTAR	= "00437";
	public static final String COD_HLP_RECARGA_CLARO= "00438";
	public static final String COD_HLP_PGO_CUPONES	= "09043";
	public static final String COD_HLP_PGO_FACTURAS	= "09044";
	public static final String COD_HLP_PGO_CLARO	= "00438";
	public static final String COD_HLP_PGO_CMAGICO	= "00439";
	public static final String COD_HLP_PGO_TERRA	= "00440";
	public static final String COD_HLP_PGO_SEDAPAL	= "00441";
	public static final String COD_HLP_DET_CIUDAD_MTC= "00450";
	public static final String COD_HLP_CIUDAD_TERRA	= "00451";
	public static final String COD_HLP_CIUDAD_SEDAPAL= "00452";
	public static final String COD_HLP_AFI_SEGUROS	= "00453";
	public static final String COD_HLP_DOCU_GIROS= "09042";
	public static final String COD_HLP_DET_ENTIDADES_PAGOS_SERVICIOS = "00500";
	public static final String COD_HLP_DET_ENTIDADES_PAGOS_UNI 		 = "00590";

	
	public static String TIPO_SERVICIO		= "00436";
	public static String CODIGO_COMPANIA	= "1";
	
	
	public static final String SERVICIOS		 		= "SERV";
	public static final String TELEGIRO_NACIONAL 		= "TELN";
	public static final String TELEGIRO_INTERNACIONAL 	= "TELI";
	public static final String TB_MISMO_BANCO 			= "TRAM";
	public static final String TB_INTERBANCO 			= "TRAI";
	public static final String TB_INTERBANCO_LINEA 		= "TRIL";
	public static final String PAGO_TARJETA 			= "PTAR";
	public static final String PAGO_TARJETA_LINEA		= "PTAL";
	public static final String PAGO_TARJETA_BN 			= "PTBN";
	public static final String PAGO_TASAS			    = "PTAS";
	
	public static final String PAGO_TEL 			    = "PAGT";
	public static final String PAGO_MOV 			    = "PMOV";
	public static final String PAGO_CUP 			    = "PCUP";
	public static final String PAGO_FAC 			    = "PFAC";
	public static final String PAGO_CMA 			    = "PCMA";
	public static final String PAGO_TER 			    = "PTER";
	public static final String PAGO_SED 			    = "PSED";
	public static final String RECARGA_MOV 			    = "RMOV";
	public static final String RECARGA_CLA 			    = "RCLA";
	public static final String RECARGA_BIT			    = "RBIT";
	public static final String RECARGA_ENT			    = "RENT";
	public static final String PAGO_TEL_CLARO 			= "PTCL";
	public static final String AFI_SEGURO				= "AS02";
	
	public static final String SERVICIO_CELULAR			= "C";
	public static final String SERVICIO_SEDAPAL			= "S";
	public static final String SERVICIO_CUPONES			= "CP";
	public static final String SELECCIONE				= "Seleccione..."; 
	public static final String MOVISTAR					= "1,MOVISTAR";
	public static final String CLARO					= "2,CLARO";
	public static final String ENTEL					= "3,ENTEL";
	public static final String BITEL					= "4,BITEL";
	
	
	public static final String DESCONOCIDO				= "Sin nombre"; 
	
	public static final String ADICIONAR				= "A"; 
	
	public static final String COD_INT_BLANK 		  	= "0";
	
	public static final String CLAVE_INTERNET_INCORRECTO = "10";
	
	public static final String CLAVE_INTERNET_INCORRECTO_DESCRIPCION = "CLAVE DE INTERNET INCORRECTA";
	
	public static final String TRANSFERENCIA_BANCARIA = "/transferenciaBancaria";
	public static final String TRANSFERENCIA_INTERBANCARIA = "/transferenciaInterbancaria";

	public static final String TRANSFERENCIA_BANCARIA_TITULO = "TRANSFERENCIAS MISMO BANCO";
	public static final String TRANSFERENCIA_INTERBANCARIA_TITULO = "TRANSFERENCIAS INTERBANCARIA";
	
	public static final String TRANSFERENCIA_INTERBANCARIA_TITULO_LINEA = "TRANSFERENCIAS INTERBANCARIA EN LINEA";
	
	public static final String PAGO_TELEFONO = "/pagoTelefono";
	
	public static final String CONST_CUENTA_PROPIA_SI 			= "S";
	public static final String CONST_CUENTA_PROPIA_NO 			= "N";
	
	public static final String CONST_TELEGIRO_MONTO_MININO		= "2000.00";
	
	public static final String CONST_RECARGAS_MONTO_MININO		= "2000.00";

	/************************************* USO EXCLUSIVO DE LOS LOGS *************************************************/
	public static final String REFRENDO_GENERACION_CLAVE	= "refrendoGeneracionClave";
	public static final String REFRENDO_CAMBIO_CLAVE		= "refrendoCambioClave";
	public static final String REFRENDO_BLOQUE_TARJETA		= "refrendoBloqueoTarjeta";
	
	public static final String REFRENDO_AFILIACION_SERVICIO	= "refrendoAfiliacionServicio";
	public static final String REFRENDO_AFILIACION_TRANSFERENCIA_BANCARIA	= "refrendoAfiliacionTransferenciaBancaria";
	public static final String REFRENDO_AFILIACION_TRANSFERENCIA_INTERBANCARIA	= "refrendoAfiliacionTransferenciaInterbancaria";
	public static final String REFRENDO_AFILIACION_TRANSFERENCIA_TARJETA	= "refrendoAfiliacionTarjeta";
	public static final String REFRENDO_AFILIACION_TELEGIRO	= "refrendoAfiliacionTelegiro";
	public static final String REFRENDO_AFILIACION_SEGURO	= "refrendoAfiliacionSeguro";
	public static final String REFRENDO_DESAFILIACION	= "refrendoDesafiliacion";
	public static final String REFRENDO_AFILIACION_DEBITO_AUTOMATICO	= "refrendoAfiliacionDebitoAutomatico";
	public static final String REFRENDO_DESAFILIACION_DEBITO_AUTOMATICO	= "refrendoDesAfiliacionDebitoAutomatico";
	public static final String REFRENDO_ANULACION_DEBITO_AUTOMATICO	= "refrendoAnulDebitoAutomatico";
	public static final String REFRENDO_MODIFICACION_DEBITO_AUTOMATICO	= "refrendoModAfiliacionDebitoAutomatico";
	public static final String REFRENDO_SALDO_PRESTAMO		= "refrendoSaldoPrestamo";
	public static final String REFRENDO_SALDO_AHORROS	= "refrendoSaldoAhorros";
	public static final String REFRENDO_SALDO_TARJETA_CREDITO	= "refrendoSaldoTarjetaCredito";
	public static final String REFRENDO_MOVIM_TARJETA_CREDITO	= "refrendoMovimientosTarjetaCredito";
	public static final String REFRENDO_SALDO_CTS	= "refrendoSaldoCTS";
	public static final String REFRENDO_SALDO_CTACTE	= "refrendoSaldoCtaCte";
	public static final String REFRENDO_ULTIMOS_MOVIMIENTOS_AHORROS	= "refrendoUltimosMovimientosAhorros";
	public static final String REFRENDO_ULTIMOS_MOVIMIENTOS_CORRIENTE	= "refrendoUltimosMovimientosCorriente";
	public static final String REFRENDO_ULTIMOS_MOVIMIENTOS_CTS	= "refrendoUltimosMovimientosCTS";
	public static final String REFRENDO_PAGO_TELEFONICA	= "refrendoPagoTelefonica";
	public static final String REFRENDO_PAGO_SEDAPAL	= "refrendoPagoSedapal";
	public static final String REFRENDO_RECARGA_TELEFONO	= "refrendoRecargaTelefono";
	public static final String REFRENDO_RECARGA_CLARO	= "refrendoRecargaClaro";
	public static final String REFRENDO_RECARGA_BITEL	= "refrendoRecargaOtros";
	public static final String REFRENDO_PAGO_UNIVERSIDAD_OTROS	= "refrendoPagoUniversidadOtros";
	public static final String REFRENDO_PAGO_UNIVERSIDAD_ALUMNO	= "refrendoPagoUniversidadAlumno";
	public static final String REFRENDO_PAGO_TASAS_EDUCATIVAS	= "refrendoPagoTasasEducativas";
	public static final String REFRENDO_CCI	= "refrendoCCIMN";
	public static final String REFRENDO_CTACTECCI = "refrendoCCICTACTE";
	public static final String REFRENDO_PAGO_MULTIRED	= "refrendoPagoMultired";
	public static final String REFRENDO_CALENDARIO_PAGO	= "refrendoCalendarioPago";
	public static final String REFRENDO_TELEGIRO_MN	= "refrendoTelegiroMN";
	public static final String REFRENDO_TELEGIRO_ME	= "refrendoTelegiroME";
	public static final String REFRENDO_TRANSFERENCIA_BANCARIA	= "refrendoTransferenciaBancaria";
	public static final String REFRENDO_TRANSFERENCIA_INTERBANCARIA	= "refrendoTransferenciaInterbancariaMTOT";
	public static final String REFRENDO_TRANSFERENCIA_INTERBANCARIA_LINEA	= "refrendoTransferenciaInterbancariaLinea";
	public static final String REFRENDO_TRANSFERENCIA_INTERBANCARIA_LINEA_RECHAZO	= "refrendoTransferenciaInterbancariaLineaRechazo";
	public static final String REFRENDO_PAGO_TARJETA	= "refrendoPagoTarjeta";
	public static final String REFRENDO_PAGO_TARJETA_CREDITO_EN_LINEA_OB	= "refrendoPagoTarjetaLineaOB";
	public static final String REFRENDO_PAGO_TARJETA_CREDITO_EN_LINEA_OB_RECHAZO	= "refrendoPagoTarjetaLineaOBRechazo";
	public static final String REFRENDO_PAGO_TASAS	= "refrendoPagoTasas";
	public static final String REFRENDO_PRESTAMO_RENOVACION	= "refrendoRenovacionPrestamo";
	public static final String REFRENDO_AFILIACION_BANCA_CEL_TRANS	= "refrendoAfiliacionBancaCelTrans";
	public static final String REFRENDO_AFILIACION_BANCA_CEL_TASA	= "refrendoAfiliacionBancaCelTasa";
	public static final String REFRENDO_AFILIACION_BANCA_CEL_RECA	= "refrendoAfiliacionBancaCelReca";
	public static final String REFRENDO_AFILIACION_BANCA_CEL_SERV	= "refrendoAfiliacionBancaCelServ";
	public static final String REFRENDO_DESAFILIACION_BANCA_CEL	= "refrendoDesafiliacionBancaCel";
	public static final String REFRENDO_ACTIVACION_CLAVE_SMS	= "refrendoActivacionClaveSms";
	public static final String REFRENDO_CONF_TARJETA	= "refrendoConfTarjetaMN";
	
	public static final String REFRENDO_VINCULACION_BANCA_CEL	= "refrendoVinculacionBancaCel";
	public static final String REFRENDO_DESVINCULACION_BANCA_CEL	= "refrendoDesvinculacionBancaCel";
	
	public static final String REFRENDO_ACTUALIZACION_DATOS_CLIENTE_ACEPTA	= "refrendoDatosClienteAcepta";
	public static final String REFRENDO_ACTUALIZACION_DATOS_CLIENTE_NO_ACEPTA	= "refrendoDatosClienteNoAcepta";
	
	public static final String REFRENDO_GENERACION_CLAVE_INTERNET	= "refrendoAfiliacionclaveInternet";
	
	public static final String REFRENDO_OLVIDO_CLAVE_INTERNET	= "refrendoOlvidoClaveInternet";
	
	public static final String REFRENDO_EXPIRACION_CLAVE_INTERNET	= "refrendoExpiraClaveInternet";
	
	
	public static final String REFRENDO_PAGO_TARJETA_CREDITO_BN 		= "refrendoPagoTarjetaCreditoBN";
	
	public static final String REFRENDO_PAGO_TARJETA_CREDITO_BN_OTROS 	= "refrendoPagoTarjetaCreditoOtrosBN";
	
	public static final String REFRENDO_SELECCION_MONTO	= "refrendoSeleccionMonto";
	
//	NCB
	public static final String REFRENDO_NCB_ACT_DOMICILIO	= "refrendoDatosClienteDireccion";
	
	public static final String REFRENDO_NCB_ALTA_DOMICILIO	= "refrendoAltaDatosClienteDireccion";
	
	public static final String REFRENDO_NCB_TELEFONO_ACT_TEL_FIJO	= "refrendoDatosClienteTelefonoFijo";
	
	public static final String REFRENDO_NCB_TELEFONO_ACT_TEL_LAB	= "refrendoDatosClienteTelefonoLaboral";
	
	public static final String REFRENDO_NCB_TELEFONO_ACT_TEL_MOV	= "refrendoDatosClienteTelefonoMovil";
	
	public static final String REFRENDO_NCB_TELEFONO_BAJA_TEL_FIJO	= "refrendoDatosClienteBajaTelefonoFijo";
	
	public static final String REFRENDO_NCB_TELEFONO_BAJA_TEL_LAB	= "refrendoDatosClienteBajaTelefonoLaboral";
	
	public static final String REFRENDO_NCB_TELEFONO_BAJA_TEL_MOV	= "refrendoDatosClienteBajaTelefonoMovil";
	
	public static final String REFRENDO_NCB_TELEFONO_ALTA_TEL_FIJO	= "refrendoDatosClienteAltaTelefonoFijo";
	
	public static final String REFRENDO_NCB_TELEFONO_ALTA_TEL_LAB	= "refrendoDatosClienteAltaTelefonoLaboral";
	
	public static final String REFRENDO_NCB_TELEFONO_ALTA_TEL_MOV	= "refrendoDatosClienteAltaTelefonoMovil";
	
	public static final String REFRENDO_NCB_EMAIL_ACT	= "refrendoDatosClienteCorreo";
	
	public static final String REFRENDO_NCB_EMAIL_ALTA	= "refrendoDatosClienteAltaCorreo";
	
	public static final String REFRENDO_NCB_EMAIL_BAJA	= "refrendoDatosClienteBajaCorreo";
	
	public static final String REFRENDO_NCB_INDICADOR_SI	= "refrendoLPDPCliente";

	public static final String REFRENDO_NCB_INDICADOR_NO	= "refrendoNoLPDPCliente";
		
	public static final String FORMATO_CUENTA  =  "xx-xxx-xxxxxx";
	public static final String FORMATO_TARJETA =  "xxxx-xxxx-xxxx-xxxx";
	
	/****************************************************** CANALES ***************************************************************/
	public static final String CANAL_WEB 	= "0";
	public static final String CANAL_MOVIL 	= "1";
	
	public static String HOST_ERROR 	= "0";
	public static boolean HOST_ERROR_DD_3024 	= false;
	public static String HOST_ERROR_MSG 	= "";
	public static String HOST_ERROR_WAP 	= "";
	public static String WAP_DNI_CTA_CTE 	= "";
	public static String WAP_CTA_CTE 	= "";
	public static String WAP_NUM_TAR 	= "";
	

	/****************************************************** inicio const web service .net ***************************************************************/
	public static final String WCOPERATION_VALID_KEY6 				= "310";
	public static final String WCOPERATION_GENERATION_KEY6 			= "300";
	public static final String WCOPERATION_CHANGE_KEY6 				= "320";
	public static final String WCOPERATION_DESAFILIA_KEY6 			= "330";
	public static final String WCOPERATION_GENERATION_OLVIDO_KEY6 	= "340";
	
	/****************************************************** end const web service .net ***************************************************************/
	/***CONSTANTES DE SALIDA DE SARAWEB****/
	
	public static final String OUTPUT_NUMBER_1			="700";
	public static final String OUTPUT_NUMBER_2			="701";
	public static final String OUTPUT_NUMBER_3			="702";
	public static final String OUTPUT_NUMBER_4			="703";
	public static final String OUTPUT_NUMBER_5			="704";
	public static final String OUTPUT_NUMBER_6			="705";
	public static final String OUTPUT_NUMBER_7			="706";
	public static final String OUTPUT_NUMBER_8			="707";
	public static final String OUTPUT_NUMBER_9			="708";
	public static final String OUTPUT_NUMBER_10			="709";
	public static final String OUTPUT_NUMBER_11			="710";
	public static final String OUTPUT_NUMBER_12			="711";
	public static final String OUTPUT_NUMBER_13			="712";
	public static final String OUTPUT_NUMBER_14			="713";
	public static final String OUTPUT_NUMBER_15			="714";
	public static final String OUTPUT_NUMBER_16			="715";
	public static final String OUTPUT_NUMBER_17			="716";
	public static final String OUTPUT_NUMBER_18			="717";
	public static final String OUTPUT_NUMBER_19			="718";
	public static final String OUTPUT_NUMBER_20			="719";
	public static final String OUTPUT_NUMBER_21			="720";
	public static final String OUTPUT_NUMBER_22			="721";
	public static final String OUTPUT_NUMBER_23			="722";
	public static final String OUTPUT_NUMBER_24			="723";
	public static final String OUTPUT_NUMBER_25			="724";
	public static final String OUTPUT_NUMBER_26			="725";
	public static final String OUTPUT_NUMBER_27			="726";
	public static final String OUTPUT_NUMBER_28			="727";
	public static final String OUTPUT_NUMBER_29			="728";
	public static final String OUTPUT_NUMBER_30			="729";
	public static final String OUTPUT_NUMBER_31			="730";
	public static final String OUTPUT_NUMBER_32			="731";
	public static final String OUTPUT_NUMBER_33			="732";
	public static final String OUTPUT_NUMBER_34			="733";
	public static final String OUTPUT_NUMBER_35			="734";
	public static final String OUTPUT_NUMBER_36			="735";
	public static final String OUTPUT_NUMBER_37			="736";
	public static final String OUTPUT_NUMBER_38			="737";
	public static final String OUTPUT_NUMBER_39			="738";
	public static final String OUTPUT_NUMBER_40			="739";
	public static final String OUTPUT_NUMBER_41			="740";
	public static final String OUTPUT_NUMBER_42			="741";
	public static final String OUTPUT_NUMBER_43			="742";
	public static final String OUTPUT_NUMBER_44			="743";
	public static final String OUTPUT_NUMBER_45			="744";
	public static final String OUTPUT_NUMBER_46			="745";
	public static final String OUTPUT_NUMBER_47			="746";
	public static final String OUTPUT_NUMBER_48			="747";
	public static final String OUTPUT_NUMBER_49			="748";
	public static final String OUTPUT_NUMBER_50			="749";
	public static final String OUTPUT_STRING_1			="800";
	public static final String OUTPUT_STRING_2			="801";
	public static final String OUTPUT_STRING_3			="802";
	public static final String OUTPUT_STRING_4			="803";
	public static final String OUTPUT_STRING_5			="804";
	public static final String OUTPUT_STRING_6			="805";
	public static final String OUTPUT_STRING_7			="806";
	public static final String OUTPUT_STRING_8			="807";
	public static final String OUTPUT_STRING_9			="808";
	public static final String OUTPUT_STRING_10			="809";
	public static final String OUTPUT_STRING_11			="810";
	public static final String OUTPUT_STRING_12			="811";
	public static final String OUTPUT_STRING_13			="812";
	public static final String OUTPUT_STRING_14			="813";
	public static final String OUTPUT_STRING_15			="814";
	public static final String OUTPUT_STRING_16			="815";
	public static final String OUTPUT_STRING_17			="816";
	public static final String OUTPUT_STRING_18			="817";
	public static final String OUTPUT_STRING_19			="818";
	public static final String OUTPUT_STRING_20			="819";
	public static final String OUTPUT_STRING_21			="820";
	public static final String OUTPUT_STRING_22			="821";
	public static final String OUTPUT_STRING_23			="822";
	public static final String OUTPUT_STRING_24			="823";
	public static final String OUTPUT_STRING_25			="824";
	public static final String OUTPUT_STRING_26			="825";
	public static final String OUTPUT_STRING_27			="826";
	public static final String OUTPUT_STRING_28			="827";
	public static final String OUTPUT_STRING_29			="828";
	public static final String OUTPUT_STRING_30			="829";
	public static final String OUTPUT_STRING_31			="830";
	public static final String OUTPUT_STRING_32			="831";
	public static final String OUTPUT_STRING_33			="832";
	public static final String OUTPUT_STRING_34			="833";
	public static final String OUTPUT_STRING_35			="834";
	public static final String OUTPUT_STRING_36			="835";
	public static final String OUTPUT_STRING_37			="836";
	public static final String OUTPUT_STRING_38			="837";
	public static final String OUTPUT_STRING_39			="838";
	public static final String OUTPUT_STRING_40			="839";
	public static final String OUTPUT_STRING_41			="840";
	public static final String OUTPUT_STRING_42			="841";
	public static final String OUTPUT_STRING_43			="842";
	public static final String OUTPUT_STRING_44			="843";
	public static final String OUTPUT_STRING_45			="844";
	public static final String OUTPUT_STRING_46			="845";
	public static final String OUTPUT_STRING_47			="846";
	public static final String OUTPUT_STRING_48			="847";
	public static final String OUTPUT_STRING_49			="848";
	public static final String OUTPUT_STRING_50			="849";
	
	public static final String OUTPUT_STRING_51			="1411";
	
	/*******************************CODIGOS DE DOCUMENTO*************************************/
	public static final String DNI 						= "001";
	public static final String CARNET_FF_PP 			= "002";
	public static final String CARNET_FF_AA 			= "003";
	public static final String CARNET_EXTRANJERIA 		= "004";
	public static final String LIBRETA_MILITAR 			= "005";
	public static final String RUC 						= "006";
	public static final String PASAPORTE 				= "007";
	public static final String PARTIDA_DE_NACIMIENTO 	= "008";
	public static final String REFRENDO_DESAFILIA_CLAVE	= "refrendoDesafiliaClave";
	public static final String BN_SWB_LOG 				= "BN_SWB_LOG";
	
	public static final String COD_HLP_DET_DOCU_OTRO = "00040";
	
	public static final String PAGO_FACT_ONLINE_CLARO_FACTURA 	= "2040";
	public static final String PAGO_FACT_ONLINE_CLARO_RECARGA	= "";
	public static final String PAGO_FACT_ONLINE_CLARO_3PLAY		= "3040";
	public static final String PAGO_FACT_ONLINE_INC				= "3080";
	public static final String PAGO_FACT_ONLINE_UNIQUE			= "2000";
	public static final String PAGO_FACT_ONLINE_ACAD_MAGISTRATURA= "1060";
	
	
	public static final String PAGO_TELEFONO_TITULO 	= "PAGO DE SERVICIOS: MOVISTAR FIJO";
	public static final String PAGO_MOVISTAR_TITULO 	= "PAGO DE SERVICIOS: MOVISTAR MÓVIL";
	public static final String PAGO_CMAGICO_TITULO  	= "PAGO DE SERVICIOS: MOVISTAR TV";
	public static final String PAGO_TERRA_TITULO  		= "PAGO DE SERVICIOS: TERRA";
	public static final String PAGO_SEDAPAL_TITULO  	= "PAGO DE SERVICIOS: SEDAPAL";
	public static final String PAGO_RECARGAS_MOVISTAR_TITULO  	= "PAGO DE SERVICIOS: RECARGA CELULAR";
	public static final String PAGO_RECARGAS_CLARO_TITULO  	= "PAGO DE SERVICIOS: RECARGA CELULAR CLARO";
	public static final String PAGO_RECARGAS_BITEL_TITULO  	= "PAGO DE SERVICIOS: RECARGA CELULAR BITEL";
	public static final String PAGO_RECARGAS_ENTEL_TITULO  	= "PAGO DE SERVICIOS: RECARGA CELULAR ENTEL";
	public static final String PAGO_CUPONES_TITULO  	= "PAGO DE SERVICIOS";
	public static final String PAGO_FACTURAS_TITULO  	= "PAGO DE SERVICIOS: FACTURAS";
	public static final String PAGO_UNIVERSIDADES_TITULO  	= "PAGO DE UNIVERSIDADES";
	public static final String DESAF_TELEFONO_TITULO 		= "DESAFILIACION DE PAGO DE SERVICIOS: MOVISTAR FIJA";
	public static final String DESAF_TELEFONO_TITULO_CLARO 	= "DESAFILIACION DE PAGO DE SERVICIOS: TELEFONIA FIJA";
	public static final String DESAF_MOVISTAR_TITULO 		= "DESAFILIACION DE PAGO DE SERVICIOS: MOVISTAR MÓVIL";
	public static final String DESAF_CMAGICO_TITULO  		= "DESAFILIACION DE PAGO DE SERVICIOS: MOVISTAR TV";
	public static final String DESAF_TERRA_TITULO  			= "DESAFILIACION DE PAGO DE SERVICIOS: TERRA";
	public static final String DESAF_SEDAPAL_TITULO  		= "DESAFILIACION DE PAGO DE SERVICIOS: SEDAPAL";
	public static final String DESAF_CUPONES_TITULO 		= "DESAFILIACION DE PAGO DE SERVICIOS: CUPONES";
	public static final String DESAF_FACTURAS_TITULO 		= "DESAFILIACION DE PAGO DE SERVICIOS: FACTURAS";
	public static final String DESAF_CLARO_TITULO 			= "DESAFILIACION DE PAGO DE SERVICIOS: RECARGAS CLARO";
	public static final String DESAF_BITEL_TITULO 			= "DESAFILIACION DE PAGO DE SERVICIOS: RECARGAS BITEL";
	public static final String DESAF_ENTEL_TITULO 			= "DESAFILIACION DE PAGO DE SERVICIOS: RECARGAS ENTEL";
	
	public static final String TIT_FONOFACIL          	   = "MOVISTAR PRE PAGO";
	public static final String TIT_TEL_FIJA          	   = "MOVISTAR FIJO";
	public static final String TIT_TERRA                = "TERRA";
	public static final String TIT_CABLE_MAGICO         = "MOVISTAR TV";
	public static final String TIT_TEL_CELULAR          = "MOVISTAR MOVIL: Recibo Celular";
	public static final String TIT_TEL_CELULAR_RECARGA  = "MOVISTAR MOVIL: Recarga Virtual";
	public static final String TIT_TEL_RECARGA_CLARO	= "CLARO PERU SAC: Recarga Virtual";
	public static final String MSG_TEL_CELULAR_RECARGA  = "**CONSULTAS/RECLAMOS AL 104 OPCIÓN 2 DE SU CELULAR**";
	
	public static final String TIT_MIGRA_PAGINA1 	= "MIGRA A LA CLAVE SMS Y OLVIDATE DEL TOKEN FISICO";
	public static final String TIT_MIGRA_PAGINA2	= "MIGRACION A CLAVE SMS";
	public static final String TIT_MIGRA_PAGINA3	= "ACTUALIZAR DATOS";
	public static final String TIT_MIGRA_PAGINA4	= "MIGRACION A CLAVE SMS";
	public static final String TIT_MIGRA_PAGINA5	= "CONFIRMACION DE MIGRACION A CLAVE SMS";
	public static final String TIT_MIGRA_PAGINA6	= "MIGRACION A CLAVE SMS";
	public static final String TIT_MIGRA_PAGINA7 	= "VERIFICA SI TU TOKEN ESTA POR VENCER";
	
	
	public static final String TIT_ACTIVACION_CLAVE_SMS	= "ACTIVACION DE CLAVE DINAMICA DIGITAL";
	
	public static String TIT_SERVICIO             		= "MOVISTAR";
	public static String FLAG_CACHE             		= "0";	
	public static String MSGBLOQUEOINFO            		= "";
	public static String MSGBLOQUEOATTE            		= "";
	public static boolean VER_LOG 						= true;
	public static final String CONSTANTE_OS 			= "1"; //1-DESARROLLO(WINDOWS) --- 2-CERTIFICACION(LINUX) ---3-PRODUCCION(LINUX)
	
	public static final String PAGO_SERVICIOS_GRP_ESTADO = "01";
	public static final String PAGO_SERVICIOS_GRP_TELEFONO = "02";
	public static final String PAGO_SERVICIOS_GRP_CELULAR = "03";
	public static final String PAGO_SERVICIOS_GRP_CABLE = "04";
	public static final String PAGO_SERVICIOS_GRP_AGUA = "05";
	public static final String PAGO_SERVICIOS_GRP_LUZ = "06";
	public static final String PAGO_SERVICIOS_GRP_BANCOSYFIN = "07";
	public static final String PAGO_SERVICIOS_GRP_SEGUROS = "08";
	public static final String PAGO_SERVICIOS_GRP_UNIVERSIDADES = "09";
	public static final String PAGO_SERVICIOS_GRP_COLEGIOS = "10";
	public static final String PAGO_SERVICIOS_GRP_EMPRESAS = "11";
	public static final String PAGO_SERVICIOS_GRP_INTERNET = "12";
	public static final String PAGO_SERVICIOS_GRP_EMP_PUB = "13";
	public static final String PAGO_SERVICIOS_GRP_TASAS_EDUCATIVAS = "15";

	public static final String PAGO_SERVICIOS_TIPO_TRX_ENT_TELF_FIJA_3PLAY = "3040";	
	public static final String PAGO_SERVICIOS_TIPO_TRX_TELEFONICA_FIJA = "01";
	public static final String PAGO_SERVICIOS_TIPO_TRX_TELEFONICA_MOVISTAR = "02";
	public static final String PAGO_SERVICIOS_TIPO_TRX_TELEFONICA_TERRA = "03";
	public static final String PAGO_SERVICIOS_TIPO_TRX_TELEFONICA_CABLE = "04";
	public static final String PAGO_SERVICIOS_TIPO_TRX_TELEFONICA_RECARGAS = "05";
	public static final String PAGO_SERVICIOS_TIPO_TRX_SEDAPAL = "06";
	public static final String PAGO_SERVICIOS_TIPO_TRX_CUPON_MN = "07";
	public static final String PAGO_SERVICIOS_TIPO_TRX_CUPON_ME = "08";
	public static final String PAGO_SERVICIOS_TIPO_TRX_FACTURA_MN = "09";
	public static final String PAGO_SERVICIOS_TIPO_TRX_FACTURA_ME = "10";
	public static final String PAGO_SERVICIOS_TIPO_TRX_CLARO_RECARGAS = "11";
	public static final String PAGO_SERVICIOS_TIPO_TRX_FACTURA_OL_MN = "12";
	public static final String PAGO_SERVICIOS_TIPO_TRX_FACTURA_OL_ME = "13";
	public static final String PAGO_SERVICIOS_TIPO_TRX_BITEL_RECARGAS = "14";
	public static final String PAGO_SERVICIOS_TIPO_TRX_TASAS_EDUCATIVAS = "15";
	public static final String PAGO_SERVICIOS_TIPO_TRX_ENTEL_RECARGAS = "16";
	public static final String REFRENDO_PAGO_CUPONERA	= "refrendoPagoCuponera";
	public static final String REFRENDO_PAGO_FACTURA	= "refrendoPagoFactura";
	public static final String REFRENDO_PAGO_FACTURA_WS	= "refrendoPagoFacturaWS";
	public static final String REFRENDO_PAGO_FACTURA_WS_CLARO	= "refrendoPagoFacturaWSClaro";
	public static final String REFRENDO_PAGO_FACTURA_PARCIAL	= "refrendoPagoFacturaWSP";
	public static final String REFRENDO_PAGO_FACTURA_MOD	= "refrendoPagoFacturaWSMod";
	
	public static final String ESTADO_SELLO_SEGURIDAD_PRIMERA_VEZ		 ="01";
	public static final String ESTADO_SELLO_SEGURIDAD_NUEVO_CAMBIO_SELLO ="02";
	public static final String ESTADO_SELLO_SEGURIDAD_NUEVO_NO_VIGENTE   ="03";
	
	public static final String MENSAJE_NO_HAY_LISTA						= "No se encuentra el suministro a desafiliar";

	/*******************************CLAVES DINAMICAS - CÓDIGOS*************************************/
	public static final String CODIGO_BIN_AUTENTICACIÓN_VIRTUAL 					= "1";
	public static final String CODIGO_TDC_BLOQUEADO				 					= "B";
	public static final String CODIGO_TDC_REGISTRADO			 					= "R";
	public static final String CODIGO_TDC_ACTIVADO			 						= "A";
	public static final String CODIGO_TDC_ELIMINADO			 						= "E";
	
	public static final String CODIGO_SI_PERMITE_OPERACIONES			 			= "1";
	public static final String CODIGO_NO_PERMITE_OPERACIONES			 			= "0";
	
	public static final String CODIGO_SI_PENDIENTE_ENROLAMIENTO			 			= "1";
	
	public static final String SI_PERMITE_OPERACIONES_ATRIBUTOS			 			= "S";
	public static final String NO_PERMITE_OPERACIONES_ATRIBUTOS			 			= "N";
	
	public static final String CODIGO_TIPO_ELEM_TDC			 						= "2";	
	public static final String CODIGO_TIPO_ELEM_TOKENS		 						= "5";
	public static final String CODIGO_TIPO_ELEM_TOKENS_MIGRACION					= "5";
	public static final String CODIGO_TIPO_ELEM_TOKENS_SMS		 					= "6";
	public static final String CODIGO_TIPO_ELEM_TOKENS_ACTIVACION					= "7";
	public static final String CODIGO_TIPO_ELEM_TOKENS_AFILIACION					= "0";
	public static final String CODIGO_TIPO_ELEM_TOKENS_AFILIADO_DESAFILIADO			= "99";
	public static final Integer CODIGO_RESULTADO_OK			 						= 0;
	public static final String CODIGO_OPERACION_OK			 						= "1";
	public static final String CODIGO_VALIDACION_OK			 						= "0000";
	
	public static final String CODIGO_ESTADO_TOKENS_FISICO							= "R";
	
	public static final String MENSAJE_EXISTENCIA_CLAVE6 									= "Si Usted no se encuentra afiliado a la clave de internet(6 Dígitos), acérquese a cualquiera de nuestras oficinas a nivel nacional para realizar su afiliación. Si ya se encuentra afiliado, recuerde generar la clave en la opción 'Clave Internet'.";
	/*******************************CLAVES DINAMICAS - MENSAJES*************************************/
	
	public static final String MENSAJE_ERROR_GENERAR_ID_AFILIACION		 					= "Error al generar el id de Afiliación";
	public static final String MENSAJE_ERROR_INSERTAR_SOLICITUD_AFILIACION		 			= "Error al guardar la solicitud de Afiliación";
	public static final String MENSAJE_ERROR_CARGAR_PARAMETROS_TDC		 					= "Error al cargar los parametros de la Tarjeta de Coordenadas o Token.";
	
	public static final String MENSAJE_EXISTENCIA_BIN_MEDIO_AUTENTICACIÓN 					= "No existe el medio de autenticación";
	
	public static final String MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN 						= "No se encuentra afiliado al servicio Clave Dinámica (Tarjeta de Coordenadas o Token Fisico). Acérquese a cualquiera de nuestras oficinas a nivel nacional para realizar su afiliación.";
	
	public static final String MENSAJE_ERROR_GENERAR_CLAVE_SMS 								= "Ocurrió un error al generar la Clave Dinámica Digital.";
	
	public static final String MENSAJE_NO_POSEE_CIC 										= "Usuario no posee un CIC asociado.";
	
	
	public static final String MENSAJE_ELEMENTO_SEGURIDAD_RELACIONADO 						= "No tiene una Tarjeta de Coordenadas o Token asignado";
	public static final String MENSAJE_CONSULTA_COORDENADAS_NO_DISPONIBLE 					= "Ha ocurrido un error al consultar la coordenada";
	public static final String MENSAJE_CONSULTA_DATOS_CLIENTE_ACTUALIZACION					= "Ha ocurrido un error al actualizar los datos del cliente";
	public static final String MENSAJE_ACTIVAR_TARJETA_COORDENADAS 							= "La Tarjeta de Coordenadas o Token aún no ha sido activado";
	public static final String MENSAJE_YA_ACTIVADA_TARJETA_COORDENADAS 						= "La tarjeta de coordenadas o Token ya se encuentra activa";
	public static final String MENSAJE_ERROR_ACTIVAR_TARJETA_COORDENADAS					= "Ocurrió un Error al activar la Clave Dinamica";
	public static final String MENSAJE_ERROR_VENCIO_TARJETA_COORDENADAS						= "Estimado cliente, el plazo para la activación de su dispositivo ha caducado. Comuníquese a nuestra mesa de consultas a los teléfonos 440-5305/442-4470 o línea gratuita 0-800-10-700 desde un teléfono fijo.";
	public static final String MENSAJE_NO_BLOQUEADO_TARJETA_COORDENADAS						= "No se pudo bloquear la Clave Dinámica";
	public static final String MENSAJE_ERROR_BLOQUEAR_TARJETA_COORDENADAS					= "Ocurrió un Error al bloquear la Clave Dinamica";
	public static final String MENSAJE_TARJETA_COORDENADAS_BLOQUEADA 						= "Su Token esta bloqueado por tal motivo no puede realizar esta operación";
	public static final String MENSAJE_TARJETA_COORDENADAS_ELIMINADA 						= "La Tarjeta de Coordenadas o Token se encuentra desvinculado";
	public static final String MENSAJE_YA_BLOQUEADA_TARJETA_COORDENADAS 					= "La tarjeta de Coordenadas o Token ya se encuentra bloqueado";
	public static final String MENSAJE_ERROR_CONSULTAR_TARJETA_COORDENADAS					= "Ocurrió un Error al consultar la Clave Dinámica";
	public static final String MENSAJE_ERROR_CONSULTAR_TOKENS								= "Ocurrió un Error al consultar el Token";
	public static final String MENSAJE_ERROR_VALIDAR_TARJETA_COORDENADAS					= "Ocurrió un Error al validar la Tarjeta de Coordenadas";
	public static final String MENSAJE_ERROR_VALIDAR_TOKEN									= "Ocurrió un Error al validar el Token";
	public static final String MENSAJE_BLOQUEAR_TARJETA_COORDENADAS_TEMP					= "Error al ingresar la coordenada por tercera vez,por su seguridad el acceso se bloqueará.Vuelva a intentarlo después de 24 horas.";
	public static final String MENSAJE_BLOQUEAR_TOKEN_TEMP									= "Error al ingresar la clave de 6 digitos del Token por tercera vez,por su seguridad el acceso se bloqueará.Vuelva a intentarlo despues de 24 horas.";
	public static final String MENSAJE_BLOQUEAR_TARJETA_COORDENADAS_PER						= "La Tarjeta de Coordenadas ha sido bloqueada por limite de intentos.Por su seguridad acerquese a la oficina mas cercana para tramitar nuevamente su Clave Dinámica";
	public static final String MENSAJE_BLOQUEAR_TOKEN_PER									= "El Token ha sido bloqueado por límite de intentos.Por su seguridad acerquese a la oficina mas cercana para tramitar nuevamente su Clave Dinámica.";
	public static final String MENSAJE_INHABILITADA_TARJETA_COORDENADAS						= "La Tarjeta de Coordenadas no esta habilitada.Comuníquese a nuestra mesa de consultas a los teléfonos 440-5305/442-4470 o línea gratuita 0-800-10-700 desde un teléfono fijo.";
	public static final String MENSAJE_INCORRECTO_CODIGO_TARJETA_COORDENADAS 				= "La coordenada ingresada es incorrecta.Ingresela nuevamente.";
	public static final String MENSAJE_INVALIDO_CODIGO_OPERACION			 				= "Ha ocurrido un Error al realizar su operación";
	public static final String MENSAJE_INVALIDO_RESULTADO_OPERACION			 				= "El tiempo de la operación caduco";
	public static final String MENSAJE_ERROR_CIFRADO										= "Ocurrió un Error en el cifrado";
	public static final String MENSAJE_TOKEN_CADUCADO						 				= "Estimado Cliente, el token a caducado.Acercarse a la oficina más cercana para tramitar su reposición";
	
	public static final String CODIGO_COMERCIO												= "0027";
	public static final Integer TIPO_TERMINAL												= 15;
	public static final String CODIGO_APLICACION_ORIGEN										= "SATI";
	public static final String USUARIO														= "0027";
	public static final String DIGITO_CHEQUEO_TOKENS							 			= "0";
	
	public static final String MENSAJE_INFO_CODIGO_SMS 					= "¿Nunca llego el codigo? Es probable que tengas problemas de conectividad de wifi y/o datos moviles o has cambiado de dispositivo telefonico. Si es asi, acercate a nuestras agencias para solicitar una nueva afiliacion.";
	public static final String MENSAJE_ERROR_ACTIVAR_CLAVE_SMS_0301 	= "El token generado a caducado";
	public static final String MENSAJE_ERROR_ACTIVAR_CLAVE_SMS_0302 	= "Los datos proporcionados son incorrectos";
	public static final String MENSAJE_ERROR_ACTIVAR_CLAVE_SMS_0303 	= "Ocurrió un problema al ingresar la Clave Dinámica Digital, por favor vuelve a intentarlo generando una nueva clave.";
	public static final String MENSAJE_ERROR_ACTIVAR_CLAVE_SMS_0304 	= "Ocurrió un problema al ingresar la Clave de 6 digitos.";
	public static final String MENSAJE_ERROR_ACTIVAR_CLAVE_SMS_0305 	= "Ocurrió un problema al realizar la migracion, no existe codigo de operacion";
	public static final String MENSAJE_ERROR_ACTIVAR_CLAVE_SMS_0306 	= "Ocurrió un problema al realizar la migracion";
	
	
	
	
	public static final String LOGGER_DEBUG_NIVEL_1 ="1";
	public static final String LOGGER_DEBUG_NIVEL_2 ="2";

	public static String BN_LOGGER_PRINT_ERROR =null;
	public static String BN_LOGGER_PRINT_DEBUG_NIVEL_1 =null;
	public static String BN_LOGGER_PRINT_DEBUG_NIVEL_2 =null;
	
	public static String BN_ERR_LOGGER_FILE=null;
	public static String BN_ERR_LOGGER_CONSOLE=null;
	public static String BN_ERR_LOGGER_PATH=null;
	
	public static String BN_PROC_LOGGER_FILE=null;
	public static String BN_PROC_LOGGER_CONSOLE=null;
	public static String BN_PROC_LOGGER_PATH=null;
	public static String gBN_CONST_IMPRIMIR_TRAMA ="";
	
	public static final String ERR_LOGICA_NEGOCIO  			= "5000";
	public static final String ERR_CONECTION_DATACOM  		= "5001";
	public static final String ERR_ARCHIVO_NO_ENCONTRADO  	= "5002";
	public static final String ERR_ENCRIPTAR			  	= "5003";
	public static final String ERR_WS_TRAMA_HOST  			= "5004";
	public static final String ERR_CONECTION_ORACLE_RENIEC  = "5005";
	public static final String ERR_CONECTION_FEDERACION  	= "5006";
	public static final String ERR_OUT_MEMORY  				= "5007";
	public static final String ERR_OUT_CONECTION_BDUC  		= "5008";
	public static final String ERR_SEND_CORREO  			= "5009";
	public static final String ERR_CONECTION_SBS  			= "5010";
	public static final String ERR_FUNCION		  			= "5011";
	public static final String ERR_CARGA_PARAMETROS_TDC		= "5012";
	public static final String ERR_SESION			  		= "5013";
	public static final String ERR_SOLICITAR_COORDENADA		= "5014";
	public static final String ERR_TOLD_HOST  			    = "8000";
	public static final String ERR_ERROR_VALIDAR_LOGIN		= "9000";
	public static final String ERR_ERROR_VALIDAR_TDC		= "5015";
	public static final String CODIGO_CREDISCOTIA			= "0095";
	public final static String gBN_CONST_PAGO_FACTURAS_CLARO= "2040";
	public final static String gBN_CONST_PAGO_FACTURAS_AMAG = "1060";
	public final static String gBN_CONST_PAGO_FACTURAS_UNIQUE= "2000";
	public final static String gBN_CONST_PAGO_FACTURAS_ENTEL= "2060";
	public final static String gBN_CONST_PAGO_FACTURAS_BITEL= "4071";
	public final static String gBN_CONST_PAGO_3PLAY_CLARO	= "3040";
	
	public static final String 	FLAG_MOSTRAR_TITULO_CLIENTE		= "0";
	public static final String 	FLAG_MOSTRAR_TITULO_USUARIO		= "1";
	
/*******************************DEBITO AUTOMATICO - EMPRESAS *************************************/
	
	public static final String DEB_AUTOMATICO_EMPRESA 		= "00510";       //01
	public static final String DEB_AUTOMATICO_SERVICIO 		= "00511";      //01
	
	public static final String DEB_AUTOMATICO_TIPO_TELEFONO = "00512";
	public static final String DEB_AUTOMATICO_CONF_VIA		= "00513";
	
	public static final String DEB_AUTOMATICO_DISCADO		= "00514";
	
	public static final String DEB_CONSTANTE_MONTO_MAXIMO 	= "0";
	public static final String DEB_CONSTANTE_SIN_TOPE 		= "T";
	public static final String DEB_CONSTANTE_MONTO	 		= "M";
	
	public static final String DEB_CONSTANTE_SIN_TOPE_HOST 		= "S";
	public static final String DEB_CONSTANTE_CON_TOPE_HOST	 		= "N";
	public static final String DEB_CONSTANTE_INDICADOR_NO	= "0";
	public static final String DEB_CONSTANTE_DES_CON_TOPE	= "Con Tope";
	public static final String DEB_CONSTANTE_DES_SIN_TOPE	= "Sin Tope";
	
	public static final String DEB_CONSTANTE_FLAG_AFIL		= "9";
	public static final String DEB_CONSTANTE_FLAG_PRE_AFIL	= "8";
	public static final String DEB_CONSTANTE_FLAG_EN_TRAM	= "6";
	public static final String DEB_CONSTANTE_DES_FLAG_AFIL		= "Afiliado";
	public static final String DEB_CONSTANTE_DES_FLAG_PRE_AFIL	= "Registrado";
	public static final String DEB_CONSTANTE_DES_EN_TRAM	= "En trámite";
	public static final String DEB_CONSTANTE_VIA_CONF_TEL	= "2";
	public static final String DEB_CONSTANTE_VIA_CONF_MAIL	= "1";
	
	//public static final String DESC_MIGRACION = "¡Migra ya!";
	public static final String DESC_MIGRACION = "";
	public static final String DESC_ACTIVACION = "¡Activa ya!";
	public static final String DESC_DESAFILIACION = "";
	public static final String DESC_AFILIACION = "";
	
	public static final String DESC_TOKEN_FISICO_BLANCO = "";
	public static final String DESC_TOKEN_FISICO_ACTIVACION = "¡Activa ya!";
	
	public static final String REDIRECT_BASE = "/claveSMS";
	public static final String REDIRECT_MIGRACION = "Migra.do";
	public static final String REDIRECT_ACTIVACION = "Activa.do";
	public static final String REDIRECT_DESAFILIACION = "Desafilia.do";
	public static final String REDIRECT_AFILIACION = "Afilia.do";
	
	
	//public static final String DEB_CONSTANTE_INDICADOR_NO	= "0";
	
	public static final String AFI_DEBITO_AUTOMATICO	    = "AD01";
	public static final String CONSULT_DEBITO_AUTOMATICO	= "AD02";
	public static final String DESAFI_DEBITO_AUTOMATICO		= "AD03";
	public static final String MOD_DEBITO_AUTOMATICO		= "AD04";
	
	public static final String CONSULT_CELULAR_BANCA_CELULAR= "BC00";
	public static final String CONSULT_ALIAS_BANCA_CELULAR	= "BC01";
	public static final String AFILIA_BANCA_CELULAR	    	= "BC02";
	public static final String DESAFILIA_BANCA_CELULAR	    = "BC03";
	public static final String VINCULA_BANCA_CELULAR	    = "BC04";
	
	//Nuevas Afiliaciones
	
	public static final String FREC_REGISTRO_TRANSAC	    = "F";
	public static final String NUEV_REGISTRO_TRANSAC	    = "N";
	public static final String CODIGO_LOCALIDAD_FREC	    = "00";
	public static final String CODIGO_SERVICIO_FREC	    	= "001";
	
	/*******************************BANCA CELULAR *************************************/
	
	public static final String BAN_CELULAR_TIPO_AFIL_TRANS	= "TRAN";       
	public static final String BAN_CELULAR_TIPO_AFIL_RECA	= "RECA";       
	public static final String BAN_CELULAR_TIPO_AFIL_SERV	= "SERV";       
	public static final String BAN_CELULAR_TIPO_AFIL_TASA	= "TASA";
	
	public static final String BAN_CELULAR_CONSULTA_MOV		= "MOVI";
	public static final String BAN_CELULAR_CONSULTA_FISE	= "FISE";
	public static final String BAN_CELULAR_CONSULTA_SALDO	= "SALD";
	
	public static final String BAN_CELULAR_SERV_AGUA		= "1";       
	public static final String BAN_CELULAR_SERV_CELULAR		= "3";       
	public static final String BAN_CELULAR_SERV_TELEFONIA	= "2";       
	public static final String BAN_CELULAR_SERV_TV			= "4";
	
	public static final String BAN_CELULAR_OPE_CLARO		= "1";
	public static final String BAN_CELULAR_OPE_MOVISTAR		= "2";
	
	public static final String BAN_CELULAR_RECARGA_CLARO		= "RECC";
	public static final String BAN_CELULAR_RECARGA_MOVISTAR		= "RECM";

	
	public static final String BAN_CELULAR_BD_TELEFONIA_FIJA_MOVISTAR		= "TF01";
	public static final String BAN_CELULAR_BD_RECIBO_MOVISTAR				= "CE02";
	public static final String BAN_CELULAR_BD_RECIBO_CLARO					= "CE01";
	public static final String BAN_CELULAR_BD_TV_MOVISTAR					= "TV01";
	public static final String BAN_CELULAR_BD_RECIBO_SEDAPAL				= "AG01";
	
	
	public static final String BAN_CELULAR_TELEFONIA_FIJA_MOVISTAR		= "FIJM";
	public static final String BAN_CELULAR_RECIBO_MOVISTAR				= "CELM";
	public static final String BAN_CELULAR_RECIBO_CLARO					= "CELC";
	public static final String BAN_CELULAR_TV_MOVISTAR					= "TVMO";
	public static final String BAN_CELULAR_RECIBO_SEDAPAL				= "SEDA";

	
	public static final String BAN_CELULAR_OPE_DES_CLARO	= "Claro";
	public static final String BAN_CELULAR_OPE_DES_MOVISTAR	= "Movistar";
	
	public static final String BAN_CELULAR_AFILIACIONES		= "00520";      
	public static final String BAN_CELULAR_OPERADOR			= "00552";       
	public static final String BAN_CELULAR_PAGO				= "00522";    
	public static final String BAN_CELULAR_PAGO_SERVICIO	= "01525";      
	public static final String BAN_CELULAR_TIPO_OPERACION	= "00526";     
	public static final String BAN_CELULAR_RECARGAS			= "00527";     
	public static final String BAN_CELULAR_SIN_DESCRIPCION	= "---";
	public static final String BAN_CELULAR_COD_CUENTA_AHORROS_MN	= "1"; 
	
	public static final String BAN_CELULAR_VINCULACION		= "1";
	public static final String BAN_CELULAR_DESVINCULACION	= "2";
	
	/*******************************DATOS DE CLIENTES *************************************/
	public static final String DAT_CLIENTES_CONSULTA				= "DC00";
	public static final String DAT_CLIENTES_ACTUALIZACION			= "DC01";
	public static final String DAT_CLIENTES_ACTUALIZACION_CEL		= "DC04";
	public static final String DAT_CLIENTES_TIPO_VIA		= "00550";     
	public static final String DAT_CLIENTES_MEDIO_ENVIO		= "00551";    
	public static final String DAT_CLIENTES_SIN_DESCRIPCION	= "---";
	public static final String DAT_CLIENTES_TIPO_OPERADOR	= "00552";
	public static final String DAT_CLIENTES_FLAG_PROTECCION_DATOS	= "00553";
	public static final int    DAT_CLIENTES_LONG_CELULAR	= 9;
	public static final String DAT_CLIENTES_OPERADOR_POR_DEFECTO	= "0";
	
	/*******************************MEDIO DE ENVIO*************************************/
	public static final String  MED_ENVIO_SOLICITUD_ENVIO		= "---";
	public static final String  MED_ENVIO_NINGUNO				= "01";
	public static final String  MED_ENVIO_MEDIO_ELECTRONICO		= "02";
	public static final String  MED_ENVIO_MEDIO_FISICO			= "03";
	public static final String  MED_ENVIO_MEDIO_ELECT_FISICO	= "04";
	
	public static final String  MED_ENVIO_DES_NINGUNO				= "Ninguno";
	public static final String  MED_ENVIO_DES_MEDIO_ELECTRONICO		= "Correo Electrónico";
	public static final String  MED_ENVIO_DES_MEDIO_FISICO			= "Medio Fisico";
	public static final String  MED_ENVIO_DES_MEDIO_ELECT_FISICO	= "Correo Electrónico y Medio Fisico";
	public static final String 	MED_ENVIO_CONSULTA					= "DC02";
	public static final String 	MED_ENVIO_GUARDAR_ELECCION			= "DC03";
	public static final String  MED_ENVIO_SIN_DESCRIPCION			= "---";
	public static final String  MED_ENVIO_COD_HLP_DET_DOC 			= "00045";
	
	/**********************************MULTIPAGOS ***************************************/
	
	public static final String  MULTIPAGOS_ENTIDADES 				= "00560";
	public static final String  MULTIPAGOS_ESTADO					= "00561";      
	public static final String  MULTIPAGOS_MONEDA 					= "00562";     
	public static final String 	MULTIPAGOS_CONSULTA					= "MP00";
	public static final String 	MULTIPAGOS_PAGO_TXN					= "MP01";
		
	/*******************************DEPOSITOS JUDICIALES *************************************/
	public static final String DEP_JUDICIALES_CONSULTA	    = "DJ00";
	
	
	public static final String DEP_JUDICIALES_IND_CONSULTA_CONSIG	    = "1";
	public static final String DEP_JUDICIALES_IND_CONSULTA_LIQUI	    = "2";
			
	/*********************************TARJETA DE CREDITO*************************************/
	public static final String 	TARJETA_CREDITO_CONSULTA_SALDO			= "TC00";
	public static final String 	TARJETA_CREDITO_CONSULTA_MOV			= "TC01";
	public static final String 	TARJETA_CREDITO_PAGO_TC					= "TC02";
	
	public static final String 	TARJETA_CREDITO_CONSULTA_SALDO_VALOR_TRAMA			= "2";
	public static final String 	TARJETA_CREDITO_CONSULTA_MOV_VALOR_TRAMA			= "3";
	public static final String 	TARJETA_CREDITO_PAGO_TC_VALOR_TRAMA					= "5";
	
	public static final String 	TARJETA_CREDITO_FORM_CONSULTA_SALDO			= "70";
	public static final String 	TARJETA_CREDITO_FORM_CONSULTA_MOV			= "80";
	public static final String 	TARJETA_CREDITO_FORM_PAGO_TC				= "100";
	public static final String 	TARJETA_CREDITO_CONST_MONTO_MININO			= "0.00";
	public static final String 	TARJETA_CREDITO_CONST_REDUC_PLAZO			= "21";
	public static final String 	TARJETA_CREDITO_CONST_REDUC_MONTO			= "22";
	/*****************************AFILIACION CLAVE DE INTERNET*******************************/
	public static final String 	AFILIACION_INTERNET_VAL_DAT_PERSONALES		= "LG07";
	public static final String 	AFILIACION_INTERNET_LOGIN					= "LG08";
	public static final String 	GENERACION_CLAVE_INTERNET					= "GE00";
	
	public static final String 	AFILIACION_INTERNET_NUEVO					= "GC01";
	public static final String 	AFILIACION_INTERNET_OLVIDO					= "GC02";
	
	public static final String 	AFILIACION_INTERNET_NO_AFILIADO				= "3";
	public static final String 	AFILIACION_INTERNET_AFILIADO				= "2";
	public static final String 	AFILIACION_INTERNET_YA_AFILIADO				= "Estimado Cliente, Usted ha generado anteriomente su Clave de Internet.";
	public static final String 	COD_HLP_DET_DOCU_AFILIACION_INTERNET		= "00041";
	
	public static final int 	AFILIACION_CODIGO_PASAPORTE					= 7;
	public static final int 	AFILIACION_CODIGO_CARNE_EXT					= 3;
		
	/*****************************TRANSFERENCIA INTERBANCARIA*******************************/
	public static final String 	TRANSF_INTERB_LINEA_CONST_CUENTA_PROPIA_SI			= "S";
	public static final String 	TRANSF_INTERB_LINEA_CONST_CUENTA_PROPIA_NO			= "N";
	public static final String 	TRANSF_INTERB_LINEA_CONST_CUENTA_PROPIA_SI_DES		= "Si";
	public static final String 	TRANSF_INTERB_LINEA_CONST_CUENTA_PROPIA_NO_DES		= "No";
	public static final String 	TRANSF_INTERB_LINEA_CONST_CUENTA_PROPIA_MISMO		= "M";
	public static final String 	TRANSF_INTERB_LINEA_CONST_CUENTA_PROPIA_OTRO		= "O";
	public static final String 	TRANSF_INTERB_LINEA_CONST_CUENTA_PROPIA_SI_HOST		= "1";
	public static final String 	TRANSF_INTERB_LINEA_CONST_CUENTA_PROPIA_NO_HOST		= "2";
	public static final String 	TRANSF_INTERB_LINEA_CONST_COD_REFRENDO 				= "000000000009999";
	
	public static final String 	TRANSF_INTERB_LINEA_COD_HLP_BANCO					= "00570";

	public static final String 	TRANSF_PAGO_TC_LINEA_COD_HLP_BANCO					= "00580";
	/********************************CLAVE DE INTERNET*************************************/
	
	public static final String 	CLAVE_INTERNET_CAMBIO			= "GC03";
	public static final String 	CLAVE_INTERNET_DESAFILIACION	= "GC04";
	public static final String  URL_AFILIACION_CLAVE_6			="https://bancaporinternet.bn.com.pe/BNWeb/Afiliacion"; //DESA: http://10.7.12.64:9080/BNWeb/Afiliacion || CERTI: https://10.7.106.122/BNWeb/Afiliacion || PROD: https://bancaporinternet.bn.com.pe/BNWeb/Afiliacion  
	public static final String  URL_OLVIDO_CLAVE_6				="https://bancaporinternet.bn.com.pe/BNWeb/Olvido"; //DESA: http://10.7.12.64:9080/BNWeb/Olvido || CERTI: https://10.7.106.122/BNWeb/Olvido || PROD: https://bancaporinternet.bn.com.pe/BNWeb/Olvido

	/********************************SELECCION DE MONTO*************************************/
	
	public static final String SEL_MONTO_CODIGO_BD 		= "02000"; 
	public static final String SEL_MONTO_VALOR_DEFECTO  = "2,000.00";
	
	/********************************ACTUALIZACION DE DATOS - NCB *******************************/
	
	public static final String 	ACTUALIZ_DATOS_NCB_SIN_CODIGO		= "00";
	public static final String 	ACTUALIZ_DATOS_NCB_TEL_FIJO			= "02";
	public static final String 	ACTUALIZ_DATOS_NCB_TEL_MOVIL		= "09";
	public static final String 	ACTUALIZ_DATOS_NCB_TEL_LABORAL		= "14";
	public static final String 	ACTUALIZ_DATOS_NCB_TEL_EXTRANJERO	= "16";
	public static final String 	ACTUALIZ_DATOS_NCB_TIPO_TELEFONO		= "00555";
	public static final String 	ACTUALIZ_DATOS_NCB_TIPO_EMAIL			= "00556";
	public static final String 	ACTUALIZ_DATOS_NCB_TIPO_OPERADOR		= "00557";
	public static final String  ACTUALIZ_DATOS_NCB_TIPO_VIA				= "00558";
	public static final String 	ACTUALIZ_DATOS_NCB_TIPO_DOMICILIO		= "00559";
	public static final String 	ACTUALIZ_DATOS_CODIGO_ENTIDAD			= "0018";
	public static final String 	ACTUALIZ_DATOS_CODIGO_PAIS				= "504";
	public static final String 	ACTUALIZ_DATOS_CODIGO_POSTAL			= "99999";
	public static final String 	ACTUALIZ_DATOS_CODIGO_IND_LOCALIDAD		= "S";
	public static final String 	ACTUALIZ_DATOS_CODIGO_IND_LOCALIDAD_NO	= "N";
	public static final String 	ACTUALIZ_DATOS_CODIGO_LOCALIDAD_NO_EXISTE		= "0000";
	public static final short 	ACTUALIZ_DATOS_NCB_RESULTADO_OK		= 1;
	public static final String  ACTUALIZ_DATOS_NCB_DESCRIPCION			= "---";
	public static final String  ACTUALIZ_DATOS_NCB_COD_ACT_DOMICILIO	= "NC01";
	public static final String  ACTUALIZ_DATOS_NCB_COD_ALTA_DOMICILIO	= "NC08";
	public static final String  ACTUALIZ_DATOS_NCB_COD_ALTA_TELF		= "NC02";
	public static final String  ACTUALIZ_DATOS_NCB_COD_BAJA_TELF		= "NC03";
	public static final String  ACTUALIZ_DATOS_NCB_COD_ACT_TELF			= "NC04";
	public static final String  ACTUALIZ_DATOS_NCB_COD_ALTA_EMAIL		= "NC05";
	public static final String  ACTUALIZ_DATOS_NCB_COD_BAJA_EMAIL		= "NC06";
	public static final String  ACTUALIZ_DATOS_NCB_COD_ACT_EMAIL		= "NC07";
	public static final String  ACTUALIZ_DATOS_NCB_COD_INDICADOR_LPDP	= "NC09";
	public static final String 	ACTUALIZ_DATOS_NCB_TIPO_DOCUMENTO		= "00549";
	public static final String 	ACTUALIZ_DATOS_NCB_DOMICILIO_EXTRANJERO	= "0";
	public static final String 	ACTUALIZ_DATOS_NCB_DOMICILIO_PERU		= "1";
	public static final String 	ACTUALIZ_DATOS_NCB_MARCA_LPDP_SI		= "S";
	public static final String 	ACTUALIZ_DATOS_NCB_MARCA_LPDP_NO		= "N";
	public static final String 	ACTUALIZ_DATOS_NCB_CODIGO_PAIS_PERU		= "504";
	public static final String 	ACTUALIZ_DATOS_NCB_TIPO_DOMICILIO_SI	= "S";
	public static final String 	ACTUALIZ_DATOS_NCB_TIPO_DOMICILIO_NO	= "N";
	public static final String 	ACTUALIZ_DATOS_NCB_TIPO_DOMICILIO_HABITUAL	= "Principal";
	public static final String 	ACTUALIZ_DATOS_NCB_TIPO_DOMICILIO_POSTAL	= "Correspondencia";
	public static final String 	ACTUALIZ_DATOS_NCB_TIPO_DOMICILIO_FISCAL	= "Fiscal";
	
	/*****************************SERVICIO NOTIFICACIONES**********************************/
	
	public static final String NOTIFICACIONES_SI="S";
	public static final String NOTIFICACIONES_NO="N";
	
	public static final int TIPO_NOTIFICACION_CORREO = 1;
	public static final int TIPO_NOTIFICACION_TELEFONO = 2;
	public static final int TIPO_NOTIFICACION_AMBOS = 3;
	
	
	public static final String TIPO_NOTIF_CORREO = "1";
	public static final String TIPO_NOTIF_TELEFONO = "2";
	public static final String TIPO_NOTIF_AMBOS = "3";
	
	
	public static final int COD_REQUERIMIENTO = 22;
	
	public static final String NOTI_TRANSFERENCIA_MB = "TRMB";
	public static final String NOTI_TRANSFERENCIA_IB = "TRIB";
	public static final String NOTI_TRANSFERENCIA_IB_LINEA = "TINL";
	public static final String NOTI_EMISION_GIROS = "EMGI";
	public static final String NOTI_PAGO_TC_OTRO_BANCO = "PTOB";
	public static final String NOTI_PAGO_TC_OTRO_BANCO_LINEA = "TCNL";
	public static final String NOTI_PAGO_TC_BN = "PTBN";
	public static final String NOTI_PAGO_SERVICIOS = "PGSV";
	public static final String NOTI_PAGO_TASAS = "PGTS";
	public static final String NOTI_MULTIPAGOS = "MULTIPAGOS";
	
	//MAIL
	public static final String NOTI_CANAL = "MULTIRED VIRTUAL";
	public static final String NOTI_ESTABLECIMIENTO = "-";
	public static final String NOTI_ASUNTO ="NOTIFICACION OPERACION";
	
	//SMS
	public static final String OPERADORA_MOVISTAR = "2";
	public static final String OPERADORA_CLARO = "1";
	public static final String CARRIER_MOVISTAR = "M";
	public static final String CARRIER_CLARO = "C";
	
//	AMPLIACION DE PRESTAMO
	public static final String PRESTAMO_CONST_CUOTA_DEFAULT 		= "48";
	public static final String PRESTAMO_CONST_FLAG_NO 				= "N";
	public static final String PRESTAMO_CONST_FLAG_NO_MV 			= "M";
	public static final String PRESTAMO_CONST_TIPO_OPERACION 		= "01";
	public static final String PRESTAMO_CONST_TIPO_PRESTAMO 		= "01";
	public static final String PRESTAMO_CONST_TIPO_ENVIO_VIRTUAL 	= "2";
	public static final String PRESTAMO_CONST_DES_PLAZO	 			= "	Cuotas";
	public static final String PRESTAMO_CONST_DES_DIA_PAGO	 		= "	de cada mes";
	public static final String PRESTAMO_CONST_CUOTAS		 		= "00801";    
	public static final String PRESTAMO_CONST_MONTO_DEFAULT		 	= "0";
	public static final String PRESTAMO_CONST_FORMATO		 		= "Formato";
	public static final String PRESTAMO_CONST_FORMATO1		 		= "Contrato de credito y hoja de resumen";
	public static final String PRESTAMO_CONST_FORMATO2		 		= "Solicitud de prestamo y carta de instrucciones";
	public static final String PRESTAMO_CONST_FORMATO3		 		= "Poliza seguro desgravamen y seguro cuota protegida";
	
//	UNIVERSIDADES	
	public static final String UNI_DETALLE_SITUACION		= "00591";
	public static final String UNI_DETALLE_CONCEPTO			= "00592";
	public static final String UNI_TIPO_DOCUMENTO			= "00593";
	public static final String UNI_SEDE_UNIVERSIDADES		= "00594";
	public static final String UNI_OPCION_PAGO_ALUMNO		= "A";
	public static final String UNI_OPCION_PAGO_OTROS		= "O";
	public static final String UNI_TIPO_PERSONA_ALUMNO		= "01";
	public static final String UNI_TIPO_PERSONA_OTROS		= "04";
	public static final String UNI_TRANSACION_CONSULTA_ALUMNO	= "PU00";
	public static final String UNI_TRANSACION_CONSULTA_TARIFARIO= "PU01";
	public static final String UNI_TRANSACION_PAGO_TASA			= "PU02";
	public static final String UNI_TRANSACION_PAGO_TASA_EDUCATIVA= "PT01";
	public static final String UNI_TRANSACION_FLAG_IMP_READONLY	= "0";
	//BITE y ENTEL
	
	public static final String COD_ENTIDAD_BITEL_RECARGAS		 		= "4072";
	public static final String COD_ENTIDAD_BITEL_FACTURAS		 		= "4071";
	
	public static final String COD_ENTIDAD_ENTEL_RECARGAS		 		= "2061";
	//ANTIPHISHING
	public static final String OPCION_ANTIPHISHING_VALIDA_IP			= "01";
	public static final String OPCION_ANTIPHISHING_ESTADO_ACTIVO		= "1";
	public static final String OPCION_ANTIPHISHING_ESTADO_INACTIVO		= "0";
	public static final int    FLAG_ANTIPHISHING_NO_VALIDA_IP			= 8;
	public static final int    FLAG_ANTIPHISHING_ERROR_VALIDA_IP		= 9;
	public static final int    FLAG_ANTIPHISHING_IP_EN_EL_RANGO			= 0;
	public static final String MENSAJE_VALIDA_IP_FUERA_PAIS				= "La operación se encuentra restringida. Para más información comuníquese a nuestra área de prevención a los teléfonos 519-2000 anexo 93120 o al correo prevenciondefraude@bn.com.pe";
	
    public static final String TC_BN_FLAG_DIAS_MIGRACION                        = "30001";
    
    public static final String URL_PRESTAMOS_GRUPO			= "30002";
    public static final String URL_CONDICIONES_PRESTAMOS	= "1";
    public static final String URL_CONTRATO_CREDITO			= "2";
    public static final String URL_POLIZA_SEGURO			= "3";
    public static final String URL_SOLICITUD				= "4";
    public static final String URL_TARIFARIO				= "5";
    public static final String URL_TASAS					= "6";
    
	//TARJETA DE CREDITO BN
	public static final String TC_BN_FLAG_THLPDET_COD						= "30000";
	public static final String TC_TIPO_SERVICIO_TITULAR				 		= "1";
	public static final String TC_TIPO_SERVICIO_TERCEROS				 	= "2";
	
	public static final String ID_TEMPLATE_CONFG_TARJETAS = "mailConfTarjetaMN";
	
	public static final String ID_TEMPLATE_TRANSFERENCIA_MISMO_BANCO = "mailTransferenciaBancaria";
	public static final String ID_TEMPLATE_TRANSFERENCIA_IB_LINEA = "mailTransferenciaInterbancariaLinea";
	public static final String ID_TEMPLATE_TRANSFERENCIA_IB_LINEA_RECHAZO = "mailTransferenciaInterbancariaLineaRechazo";
	public static final String ID_TEMPLATE_TRANSFERENCIA_IB_DIFERIDA = "mailTransferenciaInterbancariaMTOT";
	
	public static final String ID_TEMPLATE_TELEGIRO_MN = "mailTelegiroMN";
	public static final String ID_TEMPLATE_TELEGIRO_ME = "mailTelegiroME";
	
	public static final String ID_TEMPLATE_TARJETA_CREDITO_OB_LINEA = "mailPagoTarjetaLineaOB";
	public static final String ID_TEMPLATE_TARJETA_CREDITO_OB_LINEA_RECHAZO = "mailPagoTarjetaLineaOBRechazo";
	public static final String ID_TEMPLATE_TARJETA_CREDITO_OB_DIFERIDA = "mailPagoTarjeta";
	public static final String ID_TEMPLATE_TARJETA_CREDITO_BN_MISMO_TITULAR = "mailPagoTarjetaCreditoBN";
	public static final String ID_TEMPLATE_TARJETA_CREDITO_BN_TERCEROS = "mailPagoTarjetaCreditoOtrosBN";
	
	public static final String ID_TEMPLATE_SERVICIOS_TELEFONO = "mailPagoTelefonica";
	
	public static final String ID_TEMPLATE_SERVICIOS_FACTURAS_LINEA_MOD = "mailPagoFacturaWSMod";
	public static final String ID_TEMPLATE_SERVICIOS_FACTURAS_LINEA_PARCIAL = "mailPagoFacturaWSP";
	public static final String ID_TEMPLATE_SERVICIOS_FACTURAS_LINEA_CLARO = "mailPagoFacturaWSClaro";
	public static final String ID_TEMPLATE_SERVICIOS_FACTURAS_LINEA = "mailPagoFacturaWS";
	
	public static final String ID_TEMPLATE_SERVICIOS_CUPONES = "mailPagoCuponera";
	
	public static final String ID_TEMPLATE_SERVICIOS_RECARGAS_TELEFONO = "mailRecargaTelefono";
	public static final String ID_TEMPLATE_SERVICIOS_RECARGAS_CLARO = "mailRecargaClaro";
	public static final String ID_TEMPLATE_SERVICIOS_RECARGAS_OTROS = "mailRecargaOtros";
	
	public static final String ID_TEMPLATE_SERVICIOS_SEDAPAL = "mailPagoSedapal";
	
	public static final String ID_TEMPLATE_SERVICIOS_UNIVERSIDAD_ALUMNO = "mailPagoUniversidadAlumno";
	public static final String ID_TEMPLATE_SERVICIOS_UNIVERSIDAD_OTROS = "mailPagoUniversidadOtros";
	
	public static final String ID_TEMPLATE_SERVICIOS_TASAS_EDUCATIVAS = "mailPagoTasasEducativas";
	
	public static final String ID_TEMPLATE_SERVICIOS_FACTURAS_ESP = "mailPagoFacturaEsp";
	public static final String ID_TEMPLATE_SERVICIOS_FACTURAS = "mailPagoFactura";
	
	public static final String ID_TEMPLATE_SERVICIOS_TASAS = "mailPagoTasas";
	
	public static final String ID_TEMPLATE_PRESTAMOS = "mailRenovacionPrestamo";
	
	public static final String ID_TEMPLATE_AFILIACION_DEBITO_AUTO_AFI = "mailAfiliacionDebitoAutomatico";
	public static final String ID_TEMPLATE_AFILIACION_DEBITO_AUTO_DES = "mailDesAfiliacionDebitoAutomatico";
	public static final String ID_TEMPLATE_AFILIACION_DEBITO_AUTO_ANU = "mailAnulacionDebitoAutomatico";
	public static final String ID_TEMPLATE_AFILIACION_DEBITO_AUTO_MOD = "mailModificacionDebitoAutomatico";
	
	public static final String ID_TEMPLATE_AFILIACION_BANCA_VINCULACION = "mailVinculacionBancaCel";
	public static final String ID_TEMPLATE_AFILIACION_BANCA_DESVINCULACION = "mailDesvinculacionBancaCel";
	public static final String ID_TEMPLATE_AFILIACION_BANCA_AFILIACION_TRAN = "mailAfiliacionBancaCelTransferencia";
	public static final String ID_TEMPLATE_AFILIACION_BANCA_AFILIACION_RECA = "mailAfiliacionBancaCelReca";
	public static final String ID_TEMPLATE_AFILIACION_BANCA_AFILIACION_SERV = "mailAfiliacionBancaCelServ";
	public static final String ID_TEMPLATE_AFILIACION_BANCA_AFILIACION_TASA = "mailAfiliacionBancaCelTasa";
	public static final String ID_TEMPLATE_AFILIACION_BANCA_DESAFILIACION = "mailDesafiliacionBancaCel";
	public static final String ID_TEMPLATE_AFILIACION_BANCA_CONSULTA_AFIL = "mailConsultaAliasBancaCelular";
	
	public static final String ID_TEMPLATE_SEGURIDAD_CLAVE_INTERNET_CAMBIO = "mailCambioClave";
	public static final String ID_TEMPLATE_SEGURIDAD_CLAVE_INTERNET_DESAFILIACION = "mailDesafiliaClave";
	
	public static final String ID_TEMPLATE_SEGURIDAD_CLAVE_DINAMICA_ACTIVACION = "mailGeneracionClaveVal";
	public static final String ID_TEMPLATE_SEGURIDAD_CLAVE_DINAMICA_BLOQUEO = "mailBloqueoTarjetaTDC";
	
	public static final String ID_TEMPLATE_BLOQUEO_TARJETA = "mailBloqueoTarjeta";
	
	public static final String ID_TEMPLATE_ACTUALIZACION_DATOS_ACEPTA = "mailDatosClienteAcepta";
	public static final String ID_TEMPLATE_ACTUALIZACION_DATOS_NO_ACEPTA = "mailDatosClienteNoAcepta";
	
	public static final String ID_TEMPLATE_ESTADO_CUENTA_ENVIO = "mailEleccionMedioEnvio";
	public static final String ID_TEMPLATE_ESTADO_CUENTA_NO_ENVIO = "mailEleccionMedioNoEnvio";
	
	
	public static ParamUrl                    BN_PARAM_URL          = null;

	public static String CONST_PROCESO_OK = "00000";
	
	
	public static String BN_PARAM_SERVICE_CORREO        = null;
	
	public static final String COD_TXN_AFILIACION_CONTACTO	= "CO01";
	public static final String COD_TXN_DESAFILIACION_CONTACTO	= "CO02";
	public static final String COD_TXN_ACTUALIZACION_CONTACTO	= "CO03";
	
	public static final String REFRENDO_AFILIACION_CONTACTO	= "refrendoAfiliacionContacto";
	public static final String ID_TEMPLATE_AFILIACION_CONTACTO	= "mailAfiliacionContacto";
	
	public static final String REFRENDO_DESAFILIACION_CONTACTO	= "refrendoDesafiliacionContacto";
	public static final String ID_TEMPLATE_DESAFILIACION_CONTACTO	= "mailDesafiliacionContacto";
	
	public static final String REFRENDO_ACTUALIZACION_CONTACTO	= "refrendoActualizacionContacto";
	public static final String ID_TEMPLATE_ACTUALIZACION_CONTACTO	= "mailActualizacionContacto";
	
	public static final String REFRENDO_TRANSFERENCIA_CONTACTO	= "refrendoTransferenciaContacto";
	public static final String ID_TEMPLATE_TRANSFERENCIA_CONTACTO	= "mailTransferenciaContacto";
	
	public static final String REFRENDO_ACTUALIZACION_DATOS_CLIENTE_CEL	= "refrendoDatosClienteCel";
	public static final String ID_TEMPLATE_ACTUALIZACION_DATOS_CLIENTE_CEL	= "mailDatosClienteCel";
	
	/*MGL - INI - Interoperabilidad*/
	public static final String CONSTANTE_AFIL_CONTACTO	= "AF";
	public static final String CONSTANTE_SIN_AFIL_CONTACTO	= "SIN_AFIL";
	
	//Mensajes Generico de error de intermitencia en interoperabilidad
	public static final String MENSAJE_ERROR_INTERMITENCIA = "EN ESTOS MOMENTOS NO PODEMOS ATENDER SU SOLICITUD, INTENTELO MAS TARDE";
	public static final String MENSAJE_VALIDA_TRANSFERENCIA_CONTACTO	=	"No se encuentra afiliado a la Transferencia a Contactos";

	public static final String MENSAJE_NUMERO_NO_ASOCIADO	=	"El número no está asociado a ninguna cuenta de otro banco. Por favor, intenta con otro número de contacto.";

	/*MGL - FIN - Interoperabilidad*/
	
	public static final String CONSTANTE_EXPIRO	= "V";
	public static final String CONSTANTE_POR_VENCER	= "W";
	public static final String CONSTANTE_POR_VENCER_WEB	= "2";
	public static final String CONSTANTE_POR_SESION_OK	= "0";
	
	public static final String 	CAMBIO_CLAVE_INTERNET_NUEVO	= "GC05";
	public static final String 	CAMBIO_CLAVE_INTERNET_OLVIDO = "GC07";
	
	
	
	
}

