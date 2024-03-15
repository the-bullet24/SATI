package pe.cosapi.system.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;


import javax.ws.rs.core.HttpHeaders;
import javax.xml.rpc.holders.IntHolder;
import javax.xml.rpc.holders.StringHolder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import _11._106._7._10.WSCertiDatosCuentaSoapProxy;
import _11._106._7._10.WSCertiPINBLOCKSoapProxy;
import _211._50._1._10.WSPRDDatosCuentaSoapProxy;
import _211._50._1._10.WSPRDPINBLOCKSoapProxy;

import com.ibm.bn.InterfazServiciosSOAPImpl;
import com.ibm.bn.InterfazServicios_PortTypeProxy;

import _11._11._7._10.WSDatosCuentaSoapProxy;
import _11._11._7._10.WSPINBLOCKSoapProxy;

import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;
import pe.bn.service.bean.RequestGateway;
import pe.bn.service.bean.ResponseGateway;
import pe.bn.service.interfaz.GatewayInterfaceProxy;
import pe.bn.telegiro.action.TelegiroAction;
import pe.com.bn.bnws.ws.interfaz.GatewayInterfacePortProxy;
import pe.com.bn.common.Funciones;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.UtilAction;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.ObjectUtil;


public class UtilWebServices {
	private static LoggerSati log3 = LoggerSati.getInstance(UtilWebServices.class.getName());
	//protected Logger log = Logger.getLogger(UtilWebServices.class);
	private static UtilWebServices unico = null;
	
	public static UtilWebServices getInstance(){
		if(unico == null)	
			unico = new UtilWebServices();
		return unico;
	}
	
	/***************************** INICIO DEL NUEVO LLAMADO AL WEBSERVICE ***************************************************/
	
	public Map callNewSendHost(String codtxn,String trama) throws Exception,ArrayRuleException{
	   //if(Constante.VER_LOG) System.out.println("************************* Begin New CallSendHost ***********************");
	   String codRptaWS="";
	   String mensajeRptaWS="";
	   String flagErrorHost = "";
       Map output = new HashMap();
	   String tramaResponse="";
	   String codTxn="";

	   try {

		   	    System.out.println("codtxn>>>>>>>"+codtxn); 
		   	    System.out.println("trama>>>>>>>"+trama); 
		   	    
//		   	    log3.debug("codtxn:::", codtxn, "1");
//		   	    log3.debug("trama:::", trama, "1");
		   	    
		   	    
		   	    /*MGL*/
		   	    if(codtxn.equals("1509") || codtxn.equals("1480") || codtxn.equals("8509") || codtxn.equals("8480") ||
		   	    		codtxn.equals("1504") || codtxn.equals("1481") || codtxn.equals("8504") || codtxn.equals("8481")
		   	    		){
		   	    	
		   	    	GatewayInterfacePortProxy proxyNew = new GatewayInterfacePortProxy();
		   	    	
		   	    	String urlGateNova = Parametro.getString(ConstanteParametros.BN_PARAM_PARAMETRO_URL_GATE_NOVA);	
		   	    	
		   	    	System.out.println("urlGateNova:"+urlGateNova);
		   	    	
		   	    	proxyNew._getDescriptor().setEndpoint(urlGateNova);
		   	    	
		   	    	byte[]keyprueba = null;
		   	    	pe.com.bn.bnws.ws.bean.RequestGateway rqGateway = new pe.com.bn.bnws.ws.bean.RequestGateway();
		   	    	pe.com.bn.bnws.ws.bean.ResponseGateway rpGateway = new pe.com.bn.bnws.ws.bean.ResponseGateway();
		   	    	rqGateway.setDatos(trama);
		   	    	rqGateway.setFiller("");
		   	    	rqGateway.setLongitud("");
		   	    	rqGateway.setTransid("WS99");
		   	    	rpGateway=proxyNew.enviarTramaConsulta(keyprueba, rqGateway);
		   	    	System.out.println("rpGateway.getMensaje():"+rpGateway.getMensaje());
		   	    	System.out.println("rpGateway.getDatos():"+rpGateway.getDatos());
		   	    	
		   	       codRptaWS=rpGateway.getMsgno();
				   mensajeRptaWS=rpGateway.getMensaje();
				   
//				   log3.debug("codRptaWS :::", codRptaWS, "1");
//			   	   log3.debug("mensajeRptaWS :::", mensajeRptaWS, "1");
				   
				   if(rpGateway.getMsgno()!= null && rpGateway.getMsgno().equals("0000")){//Si la WS respondio OK
				    	if(rpGateway.getDatos()!= null && !rpGateway.getDatos().equals("")){
				    		
				    		  tramaResponse = rpGateway.getDatos();
				    		  flagErrorHost = tramaResponse.substring(85, 87);
				    		  System.out.println("flagErrorHost:"+flagErrorHost);
				    		  codTxn=tramaResponse.substring(22, 26);
				    		  String desTrama ="";
				    		  			    		 
				    		 	if(flagErrorHost.trim().equals("10")){
				    		 		 desTrama = tramaResponse.substring(106,157);
				    		 		log3.error(null, Constante.ERR_TOLD_HOST, "TXN:"+codTxn+"-"+desTrama);
				    		 	  	 							    	
						    	}else if(flagErrorHost.trim().equals("")) {
						    		tramaResponse =  rpGateway.getDatos().substring(1055);
						    		 desTrama = tramaResponse.substring(41,81);
						    		
						    		ConstanteSesion.HOST_ERROR="1";
							   		ConstanteSesion.HOST_ERROR_MSG=getMensage(5);
							   		String mensaje = getMensage(5);
							   		log3.error(null, Constante.ERR_TOLD_HOST, "TXN:"+codTxn+"-"+desTrama);
							   		throw new ArrayRuleException(ConstanteError.GENERICO,mensaje);

						    	}
				    		
				    	}
				    }
				   else{
					   if(rpGateway.getMsgno()!= null && rpGateway.getMsgno().equals("8998") ){
						   
						   ConstanteSesion.HOST_ERROR="1";
				   		   ConstanteSesion.HOST_ERROR_MSG=getMensage(7);
				   		   String mensaje = getMensage(7);
				   		   log3.error(null, Constante.ERR_WS_TRAMA_HOST, mensaje);
				   		   throw new ArrayRuleException(ConstanteError.GENERICO,mensaje);
				   		   
					   } if(rpGateway.getMsgno()!= null && rpGateway.getMsgno().equals("8997") ){
						   ConstanteSesion.HOST_ERROR="1";
				   		   ConstanteSesion.HOST_ERROR_MSG=getMensage(6);
				   		   String mensaje = getMensage(6);
				   		   log3.error(null, Constante.ERR_WS_TRAMA_HOST, mensaje);
				   		   throw new ArrayRuleException(ConstanteError.GENERICO,mensaje); 
						   
					   }
					   
					   else{
					    ConstanteSesion.HOST_ERROR="1";
			   			ConstanteSesion.HOST_ERROR_MSG=getMensage(9);
			   		    String mensaje = getMensage(9);
			   		    //if(Constante.VER_LOG) System.out.println("Mensaje error:"+mensaje);
			   		    log3.error(null, Constante.ERR_WS_TRAMA_HOST, mensaje);
			   		    throw new ArrayRuleException(ConstanteError.GENERICO,mensaje); 
					   }
				   }
				   
		   	    } 
		   	    
		   	    else{
		  
		       GatewayInterfaceProxy proxy = new GatewayInterfaceProxy();
						   
			   RequestGateway request = new RequestGateway();
			    
			   request.setDatos("9999"+trama);
			   request.setFiller("00000000000000000");
			   request.setLongitud("9999");
			   request.setTransid("WS99");
			    
			   ResponseGateway respuesta = new ResponseGateway();
			   respuesta = proxy.enviarTramaConsulta( pe.com.bn.common.Constante.BN_KEY_WS_GATEWAY,request);
		   	   
			   codRptaWS=respuesta.getMsgno();
			   mensajeRptaWS=respuesta.getMensaje();
			   
			   log3.debug("codRptaWS:::", codRptaWS, "1");
		   	   log3.debug("mensajeRptaWS:::", mensajeRptaWS, "1");
			   
			   //System.out.println("respuesta.getMsgno():"+respuesta.getMsgno());
			    
			   if(respuesta.getMsgno()!= null && respuesta.getMsgno().equals("0000")){//Si la WS respondio OK
			    	if(respuesta.getDatos()!= null && !respuesta.getDatos().equals("")){
			    		
			    		  tramaResponse = respuesta.getDatos().substring(1176);
			    		  
			    		  System.out.println("tramaResponse:"+tramaResponse);
			    		  
			    		  flagErrorHost = tramaResponse.substring(85, 87);
			    		  codTxn=tramaResponse.substring(22, 26);
			    		  String desTrama ="";
			    		  			    		 
			    		 	if(flagErrorHost.trim().equals("10")){
			    		 		 desTrama = tramaResponse.substring(106,157);
			    		 		log3.error(null, Constante.ERR_TOLD_HOST, "TXN:"+codTxn+"-"+desTrama);
			    		 	  	 							    	
					    	}else if(flagErrorHost.trim().equals("")) {
					    		tramaResponse =  respuesta.getDatos().substring(1055);
					    		 desTrama = tramaResponse.substring(41,81);
					    		
					    		ConstanteSesion.HOST_ERROR="1";
						   		ConstanteSesion.HOST_ERROR_MSG=getMensage(5);
						   		String mensaje = getMensage(5);
						   		log3.error(null, Constante.ERR_TOLD_HOST, "TXN:"+codTxn+"-"+desTrama);
						   		throw new ArrayRuleException(ConstanteError.GENERICO,mensaje);

					    	}
			    		
			    	}
			    }
			   else{
				   if(respuesta.getMsgno()!= null && respuesta.getMsgno().equals("8998") ){
					   
					   ConstanteSesion.HOST_ERROR="1";
			   		   ConstanteSesion.HOST_ERROR_MSG=getMensage(7);
			   		   String mensaje = getMensage(7);
			   		   log3.error(null, Constante.ERR_WS_TRAMA_HOST, mensaje);
			   		   throw new ArrayRuleException(ConstanteError.GENERICO,mensaje);
			   		   
				   } if(respuesta.getMsgno()!= null && respuesta.getMsgno().equals("8997") ){
					   ConstanteSesion.HOST_ERROR="1";
			   		   ConstanteSesion.HOST_ERROR_MSG=getMensage(6);
			   		   String mensaje = getMensage(6);
			   		   log3.error(null, Constante.ERR_WS_TRAMA_HOST, mensaje);
			   		   throw new ArrayRuleException(ConstanteError.GENERICO,mensaje); 
					   
				   }
				   
				   else{
				    ConstanteSesion.HOST_ERROR="1";
		   			ConstanteSesion.HOST_ERROR_MSG=getMensage(9);
		   		    String mensaje = getMensage(9);
		   		    //if(Constante.VER_LOG) System.out.println("Mensaje error:"+mensaje);
		   		    log3.error(null, Constante.ERR_WS_TRAMA_HOST, mensaje);
		   		    throw new ArrayRuleException(ConstanteError.GENERICO,mensaje); 
				   }
			   }
		   	 }
	   		output.put("tramaRespuesta",tramaResponse);
	   		output.put("codResp","0");

	   }
		catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			
			output.put("tramaRespuesta","");
	   		output.put("codResp","");
			if(e instanceof RemoteException){
			    System.out.println("ERROR: trouble with Web Service RemoteException");
			    System.out.println(e);
				throw new ArrayRuleException(ConstanteError.GENERICO,"ERROR DEL HOST: Error en la llamada del Procedimiento Remoto.."); 
			}else if (e instanceof SocketTimeoutException){
			    System.out.println("ERROR: trouble with Web Service TimeoutException");
			    System.out.println(""+e);
				throw new ArrayRuleException(ConstanteError.GENERICO,"ERROR DEL HOST: Error de Socket Timeout Exception"); 
			}else if (e instanceof ArrayRuleException){
			    System.out.println("ERROR: trouble with Web Service ArrayRuleException");
			    throw e;
			}
			System.out.println("ERROR: "+ e);
			return output;
	  }
		return output;
	}
	
	public String[] callWebServiceValidatorCardAhorro(String nroDocumento,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception{
		String wcOperation = Constante.WCOPERATION_VALID_KEY6;
		return callPinWebServices(wcOperation,null,nroDocumento,0,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
	}
	
	public String[] callWebServiceGenerationKey6DigitsAhorro(String nroDocumento,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception{
		String wcOperation = Constante.WCOPERATION_GENERATION_KEY6;
		return callPinWebServices(wcOperation,null,nroDocumento,0,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
	}
	
	public String[] callWebServiceGenerationOlvidoKey6DigitsAhorro(String nroDocumento,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception{
		String wcOperation = Constante.WCOPERATION_GENERATION_OLVIDO_KEY6;
		return callPinWebServices(wcOperation,null,nroDocumento,0,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
	}
	
	public String[] callWebServiceDesafiliaKey6DigitsAhorro(String nroDocumento,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception{
		String wcOperation = Constante.WCOPERATION_DESAFILIA_KEY6;
		return callPinWebServices(wcOperation,null,nroDocumento,0,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
	}
	
	public String[] callWebServiceChangeKey6DigitsAhorro(String nroDocumento,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception{
		String wcOperation = Constante.WCOPERATION_CHANGE_KEY6;
		return callPinWebServices(wcOperation,null,nroDocumento,0,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
	}
	
	public String[] callWebServiceValidatorCardCorriente(String wTipDoc,String nroDocumento,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception{
		String wcOperation = Constante.WCOPERATION_VALID_KEY6;
		return callPinWebServices(wcOperation,wTipDoc,nroDocumento,1,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
	}
	
	public String[] callWebServiceGenerationKey6DigitsCorriente(String wTipDoc,String nroDocumento,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception{
		String wcOperation = Constante.WCOPERATION_GENERATION_KEY6;
		return callPinWebServices(wcOperation,wTipDoc,nroDocumento,1,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
	}
	
	public String[] callWebServiceChangeKey6DigitsCorriente(String wTipDoc,String nroDocumento,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception{
		String wcOperation = Constante.WCOPERATION_CHANGE_KEY6;
		return callPinWebServices(wcOperation,wTipDoc,nroDocumento,1,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
	}
	
	/***
	 * 
	 * @param wcOperation
	 * @param wTipDoc
	 * @param nroDocumento
	 * @param opt
	 * @param wPINEncript
	 * @param wPINBLOCKNew
	 * @param wPINBLOCKConf
	 * @return
	 * @throws Exception
	 */
	private String[] callPinWebServices(String wcOperation,String wTipDoc,String nroDocumento,int opt,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception{
		String respuesta = "";
		
		/*** TODO SIRVE PARA DESARROLLO **/
		   WSPINBLOCKSoapProxy proxy = new WSPINBLOCKSoapProxy();
		 
		/*** TODO SIRVE PARA CERTIFICACION**/
		   WSCertiPINBLOCKSoapProxy proxy_ = new WSCertiPINBLOCKSoapProxy();
		   
		/*** TODO SIRVE PARA PRODUCCION**/
		   WSPRDPINBLOCKSoapProxy _proxy = new WSPRDPINBLOCKSoapProxy();
		   
		   String ambiente = Parametro.getString(ConstanteParametros.BN_PARAM_PARAMETRO_SATI_PINBLOCK);
		   
		String[] rst = null;
		try {
			if (opt==0){
				//if(Constante.VER_LOG) System.out.println("wcOperation:"+wcOperation+"|wTipDoc="+wTipDoc+"|nroDocumento="+nroDocumento+"|wPINEncript="+wPINEncript+"|wPINBLOCKNew="+wPINBLOCKNew+"|wPINBLOCKConf="+wPINBLOCKConf);
				
				if (ambiente.equals("1")){
					 try {
				
						
						 respuesta = proxy.operacionesPINBLOCKAhorros(wcOperation,nroDocumento,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				     
				    
				 }
				 else if (ambiente.equals("2")){
				
					 respuesta = proxy_.operacionesPINBLOCKAhorros(wcOperation,nroDocumento,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
					
					 
				 }else if (ambiente.equals("3")){
				     //TODO FALTA IMPLEMENTAR AKI
				     respuesta = _proxy.operacionesPINBLOCKAhorros(wcOperation,nroDocumento,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
				 }
			}
			else if (opt==1){
				//if(Constante.VER_LOG) System.out.println("wcOperation:"+wcOperation+"|wTipDoc="+wTipDoc+"|nroDocumento="+nroDocumento+"|wPINEncript="+wPINEncript+"|wPINBLOCKNew="+wPINBLOCKNew+"|wPINBLOCKConf="+wPINBLOCKConf);
				
				if (ambiente.equals("1")){
				    respuesta = proxy_.operacionesPINBLOCKCtasCtes(wcOperation,wTipDoc,nroDocumento,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
				}
				else if (ambiente.equals("2")){
				    respuesta = proxy_.operacionesPINBLOCKCtasCtes(wcOperation,wTipDoc,nroDocumento,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
				}else if (ambiente.equals("3")){
				    respuesta = _proxy.operacionesPINBLOCKCtasCtes(wcOperation,wTipDoc,nroDocumento,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
				    //TODO FALTA IMPLEMENTAR AKI
			    }
			}
			//if(Constante.VER_LOG) System.out.println("Web Services respuesta="+respuesta);
			if (!ObjectUtil.isStringBlank(respuesta)){
				rst = ObjectUtil.getArrayStrings(respuesta,"|");
			}else{
			    throw new ArrayRuleException(ConstanteError.GENERICO,"Su transacción no pudo ser procesada, intente nuevamente");
			}
		} catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.printStackTrace();
			if (e instanceof ArrayRuleException){
			    System.out.println("lanzando la ArrayRuleException");
		        throw e;
		    }
			
			if (e instanceof RemoteException){
			    System.out.println("trouble with Web Service RemoteException");
			    System.out.println(e);
				throw new ArrayRuleException(ConstanteError.GENERICO,"Error en la llamada del Procedimiento Remoto.."); 
			}
			
		}
		return rst;
	}
	/********************************************************************************/
	
	/***************************** INICIO WEBSERVICE DATOS CUENTA AHORROS ***************************************************/
//	public String[] callOperacionesDatosCuenta(String numeroCuenta)throws Exception{
//		String[] arr 	= null;
//		String result 	= "";
//		String ambiente = Parametro.getString(ConstanteParametros.BN_PARAM_PARAMETRO_SATI_PINBLOCK);
//		
//		
//		
//		try {
//		    /*** TODO SIRVE PARA DESARROLLO **/
//		    if (ambiente.equals("1")){    
//		        WSDatosCuentaSoapProxy proxy = new WSDatosCuentaSoapProxy();
//		        result = proxy.operacionesDatosCuenta(numeroCuenta);
//		    }
//			/*** TODO SIRVE PARA CERTIFICACION **/
//		    else if (ambiente.equals("2")){
//		        WSCertiDatosCuentaSoapProxy proxy_ = new WSCertiDatosCuentaSoapProxy();
//		        result = proxy_.operacionesDatosCuenta(numeroCuenta);
//		    }
//		    /*** TODO SIRVE PARA PRODUCCION **/
//		    else if (ambiente.equals("3")){
//		        //TODO FALTA IMPLEMENTAR AKI
//		        WSPRDDatosCuentaSoapProxy _proxy = new WSPRDDatosCuentaSoapProxy();
//		        result = _proxy.operacionesDatosCuenta(numeroCuenta);
//		    }
//			//String result = proxy_.operacionesDatosCuenta(numeroCuenta);
//		   String flagLog = Parametro.getString(ConstanteParametros.BN_PARAM_FLAG_VER_LOG);
//		    		
//		   boolean isActiveLog=Boolean.parseBoolean(flagLog); 
//		   
//		   if(isActiveLog)System.out.println("callOperacionesDatosCuenta:"+result);
//		    
//			if (!ObjectUtil.isStringBlank(result)){
//				//if(Constante.VER_LOG) System.out.println("CONSTANTE_OS:"+Constante.CONSTANTE_OS);if(Constante.VER_LOG) System.out.println("result:"+result);
//				arr = ObjectUtil.getArrayStrings(result,"|");
//				if (!Constante.COD_RESP.equals(arr[0])){
//					throw new ArrayRuleException(ConstanteError.GENERICO,arr[1]);
//				}
//			}else{
//			    System.out.println("Error: resultado nulo del Webservice callOperacionesDatosCuenta.");
//				throw new ArrayRuleException(ConstanteError.GENERICO,"Su transacción no pudo ser procesada. Intente nuevamente"); 
//			}
//		}catch (Exception e) {
//			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
//		    if (e instanceof ArrayRuleException){
//		        System.out.println("lanzando la ArrayRuleException");
//		        throw e;
//		    }
//		    
//			if (e instanceof RemoteException){
//			    System.out.println("trouble with Web Service RemoteException");
//			    System.out.println(e);
//				throw new ArrayRuleException(ConstanteError.GENERICO,"Error en la llamada del Procedimiento Remoto.."); 
//			}
//		}	
//		return arr;
//	}
//	
	/***************************** FIN WEBSERVICE DATOS CUENTA AHORROS ***************************************************/
	/*
	    0  – transacción correcta
		8  – error de host, la interfaz del mainframe no recibió respuesta del TOLD.
		9  – error interno de la interfaz CICS-SOAP.
		10 – error de comunicación, el servidor de aplicaciones no recibió respuesta del host.
		11 – error de host, se excedió el tiempo de espera de la petición al servicio web del host.
	 */
	
	private String getMensage(int intHolder){
	    String mensaje  = "";
	    
	    System.out.println("intHolder"+intHolder);
	    if (intHolder == 5){
	    	mensaje="SISTEMA TOLD NO ACTIVO";	        //mensaje = "Sistema TOLD NO ACTIVO";
	    }
	    
	    else if (intHolder == 6){
	    	mensaje="EN ESTOS MOMENTOS NO PODEMOS ATENDER SU SOLICITUD, INTENTELO MAS TARDE (GH-8997)";	        //mensaje = "Error en la validación de la llave del Gateway";
	    }
	    
	    else if(intHolder == 7){
	    	mensaje="EN ESTOS MOMENTOS NO PODEMOS ATENDER SU SOLICITUD, INTENTELO MAS TARDE (GH-8998)";	        //mensaje = "Error en la validación de la llave del Gateway";
	    	
	    }
	    
	    else if (intHolder == 8){
	    	mensaje="EN ESTOS MOMENTOS NO PODEMOS ATENDER SU SOLICITUD, INTENTELO MAS TARDE (WS-8)";	        //mensaje = "Error de HOST, la Interfaz del Mainframe no recibió respuesta del TOLD.";
	    	System.out.println("Error de comunicación con CICS-SOA (WS-8)");
	    }
	    else if (intHolder == 9)
	    	mensaje="EN ESTOS MOMENTOS NO PODEMOS ATENDER SU SOLICITUD, INTENTELO MAS TARDE (WS-9)";	        //mensaje = "Error Interno de la Interfaz CICS-SOAP.";
	    else if (intHolder == 10){
	    	mensaje="EN ESTOS MOMENTOS NO PODEMOS ATENDER SU SOLICITUD, INTENTELO MAS TARDE (WS-10)";	        //mensaje = "Error de Comunicación, el Servidor de Aplicaciones no Recibió Respuesta del HOST.";
	    	System.out.println("Error de comunicación CICS-SOA, no Recibió Respuesta del HOST (WS-10)");
	    }
	    else if (intHolder == 11){
	    	mensaje="EN ESTOS MOMENTOS NO PODEMOS ATENDER SU SOLICITUD, INTENTELO MAS TARDE (WS-11)";	        //mensaje = "Error de HOST, se Excedió el Tiempo de Espera de la Petición al Servicio Web del HOST.";
	    	System.out.println("Error de comunicación CICS-SOA, se Excedió el Tiempo de Espera de la Petición al Servicio Web del HOST (WS-11)");
	    }
	    else
	      throw new ArrayRuleException(ConstanteError.GENERICO,"ERROR GENERICO en la llamada al host");   
	    
	    return mensaje; 
	}
	private String getMensageError(int intHolder){
	    String mensaje  = "";
	    if (intHolder == 5)
	    	mensaje="SISTEMA TOLD NO ACTIVO";
	    else if (intHolder == 6)
	    	mensaje="EN ESTOS MOMENTOS NO PODEMOS ATENDER SU SOLICITUD, INTENTELO MAS TARDE (GH-8997)";
	    else if (intHolder == 7)
	    	mensaje="EN ESTOS MOMENTOS NO PODEMOS ATENDER SU SOLICITUD, INTENTELO MAS TARDE (GH-8998)";
	    else if (intHolder == 8)
	    	mensaje="Error de HOST, la Interfaz del Mainframe no recibió respuesta del TOLD. (WS-8)";	        //mensaje = "Error de HOST, la Interfaz del Mainframe no recibió respuesta del TOLD.";
	    else if (intHolder == 9)
	    	mensaje="Error Interno de la Interfaz CICS-SOAP. (WS-9)";	        								//mensaje = "Error Interno de la Interfaz CICS-SOAP.";
	    else if (intHolder == 10)
	    	mensaje="Error de Comunicación, el Servidor de Aplicaciones no Recibió Respuesta del HOST. (WS-10)";//mensaje = "Error de Comunicación, el Servidor de Aplicaciones no Recibió Respuesta del HOST.";
	    else if (intHolder == 11)
	    	mensaje="Error de HOST, se Excedió el Tiempo de Espera de la Petición al Servicio Web del HOST. (WS-11)";	        //mensaje = "Error de HOST, se Excedió el Tiempo de Espera de la Petición al Servicio Web del HOST.";
	    else
	      throw new ArrayRuleException(ConstanteError.GENERICO,"ERROR GENERICO en la llamada al host");   
	    
	    return mensaje; 
	}
}
