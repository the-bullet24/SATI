package pe.com.bn.common;

import java.util.List;



public class Constante {

	  
	  
	public static String gBN_CONST_IMPRIMIR_TRAMA ="1";
	
	public static String gBN_CONST_SOAP_LONGS ="0000";
	public static String gBN_CONST_SOAP_CAPLIC ="WEBS";
	public static String gBN_CONST_SOAP_CTRANS ="WS20";
	
	

    
    public final static Long   CONST_COD_TIPO_OPERACION_CONSULTA_PAGO= new Long(100);   //Consultar pagos
    public final static Long   CONST_COD_TIPO_OPERACION_CONSULTA_PAGO_OK= new Long(101);  //realizo la consulta
    public final static Long   CONST_COD_TIPO_OPERACION_CONSULTA_PAGO_NE= new Long(102);  //No pudo realizar la consulta
    
    public final static Long   CONST_COD_TIPO_OPERACION_PAGO_OPEN_VIGENTE= new Long(0);
    public final static Long   CONST_COD_TIPO_OPERACION_PAGO_OPEN_EXTORNADO= new Long(1);

    public final static String  CONST_COD_TIPO_OPERACION_PAGO_HOST_REQ_PAGO= "0002";
    public final static String CONST_COD_TIPO_OPERACION_PAGO_HOST_REQ_EXTORNADO= "0008";
    
    public final static String CONST_IP_DEFAULT= "127.0.0.1";
    
    //public final static String CONST_INI_XML="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    
    public final static String CONST_INI_XML="";
    public final static String CONST_INI_XML_BN_RES_DATA_FILAS_0="<xmlBnResData><IBnRes></IBnRes></xmlBnResData>";
    
    public final static String  CONST_COD_TIPO_ABONO_SUNARP_ID_BN= "18";
    
    public final static String  CONST_COD_TIPO_PAGO_SUNARP_EFECTIVO= "E";
    public final static String  CONST_COD_TIPO_PAGO_SUNARP_CHEQUE= "C";
    
    public final static String  CONST_COD_TIPO_SERVICIO_SUNARP_DEFAUL= "201";

    public final static String  CONST_COD_TIPO_CHEQUE_SUNARP_CHEQ_GERENCIA= "G";
    public final static String  CONST_COD_TIPO_CHEQUE_SUNARP_CHE_CERTIFICADO= "C"; 
    
    public final static String  CONST_COD_RPTA_OPERACION_EXITOSA= "00000";
    public final static String  CONST_DES_RPTA_OPERACION_EXITOSA= "OPERACION EXITOSA";

    
    public final static String  CONST_COD_RPTA_PAGO_NO_ENCONTRADO= "00014";
    public final static String  CONST_DES_RPTA_PAGO_NO_ENCONTRADO= "PAGO NO ENCONTRADO";
    
        
    public final static String  CONST_COD_RPTA_ERROR_EN_EL_PARSEO= "10000";
    public final static String  CONST_DES_RPTA_ERROR_EN_EL_PARSEO= "ERROR EN ELPARSEO";
    
    public final static String  CONST_COD_RPTA_TRANSACCION_NO_EXISTE= "10001";
    public final static String  CONST_DES_RPTA_TRANSACCION_NO_EXISTE= "TRANSACCION NO EXISTE";

    public final static String  CONST_COD_RPTA_ERROR_INESPERADO= "10002";
    public final static String  CONST_DES_RPTA_ERROR_INESPERADO= "ERROR INESPERADO";
    
       
    public final static String gBN_CONST_COD_TRANSACCION_RECAUDACION="WS08";
    public final static String gBN_CONST_COD_RESULT_TRANSACCION_OK="00000";
    public final static String  CONST_DES_RPTA_FECHA_INCORRECTA= "FECHA INCORRECTA";
    
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
    
	public static final String ERR_LOGICA_NEGOCIO  			= "5000";
	public static final String ERR_CONECTION_DATACOM  		= "5001";
	public static final String ERR_ARCHIVO_NO_ENCONTRADO  	= "5002";
	public static final String ERR_CONECTION_ORACLE_SACH  	= "5003";
	public static final String ERR_WS_TRAMA_HOST  			= "5004";
	public static final String ERR_CONECTION_ORACLE_RENIEC  = "5005";
	public static final String ERR_CONECTION_FEDERACION  	= "5006";
	public static final String ERR_OUT_MEMORY  				= "5007";
	public static final String ERR_OUT_CONECTION_BDUC  		= "5008";
	public static final String ERR_SEND_CORREO  			= "5009";
	public static final String ERR_CONECTION_SBS  			= "5010";
	public static final String ERR_FUNCION		  			= "5011";
	public static final String ERR_CARGA_PARAMETROS	  		= "5012";
	public static final String ERR_SESION			  		= "5013";
	public static final String ERR_INTERRUPTED				= "5014";
	public static final String ERR_WS_PARAMETRO				= "5015";
	
	public static byte[]  BN_KEY_WS_GATEWAY = null;


}

