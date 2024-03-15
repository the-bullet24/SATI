package pe.cosapi.sarabank.bean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Vector;
import java.util.UUID;



//import com.cosapisoft.sarawebbanking.sarawebteller.beans.Login;
//import com.cosapisoft.sarawebbranch.awt.applets.forms.MaskCenter;
//import com.cosapisoft.sarawebbranch.awt.applets.forms.SARABranch;
//import com.cosapisoft.util.Time;
//import com.cosapisoft.util.string.CString;

import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;
import pe.bn.telegiro.action.TelegiroAction;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.TramaUtil;
import pe.cosapi.domain.MsgComunication;
import pe.cosapi.system.Crypto;
import pe.cosapi.system.HorarioTest;
import pe.cosapi.system.ParserXML;
import pe.cosapi.system.services.UtilWebServices;



public class Host {
	
	private static LoggerSati log3 = LoggerSati.getInstance(Host.class.getName());
	//public  Logger log = Logger.getLogger(Host.class);
	
	private String sTraceHeader = "";  
	private static String encoding = "CP037";
	
	public Host() 
	{	    
		super();
	}
	
	
	
	private String prepareHeaderAscii(String codTrx,String canal, java.util.Vector cuentas) throws Exception{
			//Time horaTerminal = new Time();
			//String sDatosCabecera = Constante.COD_LINPUT;
			String sDatosCabecera = "";
			
			sDatosCabecera = sDatosCabecera.concat(Constante.CTRANS_DESP);
			//Canal.
			sDatosCabecera = sDatosCabecera.concat(canal);
			//Codigo de transaccion.
			sDatosCabecera = sDatosCabecera.concat(codTrx);
			//Codigo de Usuario.
			//sDatosCabecera = sDatosCabecera.concat(sUsuario);
			sDatosCabecera = sDatosCabecera.concat(Constante.COD_USUARIO);
			//Codigo de Autorizador.
			//sDatosCabecera = sDatosCabecera.concat(sAutorizador);
			sDatosCabecera = sDatosCabecera.concat(Constante.COD_SUPERVISOR);
			//Hora de transaccion: Host espera numero de log???...
			sDatosCabecera = sDatosCabecera.concat(ObjectUtil.getHora());
			sDatosCabecera = sDatosCabecera.concat(ObjectUtil.getMinuto());
			sDatosCabecera = sDatosCabecera.concat(ObjectUtil.getSegundo());
			//CLOG-TRSQ.
			sDatosCabecera = sDatosCabecera.concat(Constante.CLOG_TRSQ);
			//Causal.
			sDatosCabecera = sDatosCabecera.concat(Constante.COD_CAUSAL);
			//Hora de Terminal.
			sDatosCabecera = sDatosCabecera.concat(ObjectUtil.getHora());
			sDatosCabecera = sDatosCabecera.concat(ObjectUtil.getMinuto());
			sDatosCabecera = sDatosCabecera.concat(ObjectUtil.getSegundo());
			//Fecha de Terminal.
			sDatosCabecera = sDatosCabecera.concat(String.valueOf(ObjectUtil.getYear()));
			sDatosCabecera = sDatosCabecera.concat(String.valueOf(ObjectUtil.getProcessMonth()));
			sDatosCabecera = sDatosCabecera.concat(ObjectUtil.getProcessDay());
			//Log de Extorno.!!!!!!!!!!!!!!!!!!CLOG_EXTOR
			sDatosCabecera = sDatosCabecera.concat(Constante.CLOG_EXTOR);
			//DELIM-NUM.
			//sDatosCabecera = sDatosCabecera.concat(Constante.DELIM_NUM);//DELIM-NUMERICO:¬
			sDatosCabecera = sDatosCabecera.concat(String.valueOf((char)126));//DELIM-NUMERICO:¬
			//DELIM-ALFANUM.
			//sDatosCabecera = sDatosCabecera.concat(Constante.DELIM_ALF);//DELIM-ALFANUMERICO:@
			sDatosCabecera = sDatosCabecera.concat(String.valueOf((char)64));
			//FILLER.
			sDatosCabecera = sDatosCabecera.concat(Constante.FILLER);
			
			//Para efectos de Trace.
			sTraceHeader = sDatosCabecera; 
			
			//Convierte cabecera a Ebcdic
			//sDatosCabecera = AsciiToEbcdic(sDatosCabecera);
			
			//return sDatosCabecera.getBytes();
			return sDatosCabecera;
	}

	public static String AsciiToEbcdic(String ebcdic){
		 byte[] cad = null;    
		 try {
		    cad = ebcdic.getBytes(encoding);  
		    return new String(cad);    
		 }catch(Exception e){
			 log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			 e.printStackTrace();
		 }
	  return new String(cad);
	}

	//trx es dic2 por el momento
	public void loadHostResponse(String trx, String trama ,java.util.Vector data, java.util.Vector cuentas, String codMsgCics) throws Exception{
				//if(Constante.VER_LOG) System.out.println("trama.substring(85, 87)="+trama.substring(85, 87));
				ConstanteSesion.HOST_ERROR = "0";
				ConstanteSesion.HOST_ERROR_MSG = "";
				//MODIFICAR ANTES DE PASARLO A CERTI "00"
							
				if (trama.substring(85, 87).equals("00")) {
					
					log3.debug(null, Constante.ERR_TOLD_HOST, "TXN:"+trx+"-RESPUESTA HOST OK");
				    /****************
				     * PARA SEGUIMIENTO DE CONSULTA DE MOVIMIENTO
				     * Fecha: 21/07/2008
				     */
				    ConstanteSesion.HOST_TRAMA_MOV = trama.trim();
				    /*******************************************/

					ConstanteSesion.HOST_ERROR = "0";
					ConstanteSesion.HOST_ERROR_MSG = "";
					String dato = "";
					//if(Constante.VER_LOG) System.out.println("trx="+trx);
					MsgComunication[] arrMsgComunication = FacadeFactory.getGeneralFacade().getMsgComunication(trx);
					
					for (int i = 0; i < arrMsgComunication.length; i++) {
						MsgComunication msgComunication = arrMsgComunication[i];
						if (msgComunication.getNumLonMsg().trim().equals("0")) {
							dato = trama.substring(Integer.valueOf(msgComunication.getNumBegPos().trim()).intValue() - 1);
						} else {
							dato = trama.substring(Integer.valueOf(msgComunication.getNumBegPos().trim()).intValue() - 1, Integer.valueOf(msgComunication.getNumBegPos().trim()).intValue() + Integer.valueOf(msgComunication.getNumLonMsg().trim()).intValue() - 1);
						}
					}
					
					for (int j = 0; j < arrMsgComunication.length; j++) {
						MsgComunication msgComunication = arrMsgComunication[j];
						
						if (msgComunication.getNumLonMsg().trim().equals("0")) {
						    int pos_a = Integer.valueOf(msgComunication.getNumBegPos().trim()).intValue()- 1;
							/*if (Constante.CONSTANTE_OS.equals("2")){
							    pos_a = pos_a + 10;
							}*/
							dato = trama.substring(pos_a);
						} else {
						    int pos_a = Integer.valueOf(msgComunication.getNumBegPos().trim()).intValue() - 1;
						    int pos_b = Integer.valueOf(msgComunication.getNumBegPos().trim()).intValue() + Integer.valueOf(msgComunication.getNumLonMsg().trim()).intValue() - 1;
						    /*if (Constante.CONSTANTE_OS.equals("2")){
						        pos_a = pos_a+10;
						        pos_b = pos_b+10;
							}*/
							dato = trama.substring(pos_a,pos_b);
						}
						int sw = 0;
						String coddic = msgComunication.getCodDic();
						
						for (int i = 0; i < data.size(); ++i) {
							if (((java.util.Vector) data.elementAt(i)).elementAt(0).equals(coddic)) {
								sw = 1;
							 	((java.util.Vector) data.elementAt(i)).setElementAt(dato, 6);
							 	//if(Constante.VER_LOG) System.out.println("dic:"+coddic+" encontrado seteando valor:"+dato);
							 	break;
							}
						}
						
						if (sw == 0) {
							java.util.Vector temp = new java.util.Vector(10);
							//if(Constante.VER_LOG) System.out.println("ingresando a la construccion del vector msgComunication.getCodDic()="+msgComunication.getCodDic());
							String dicc = msgComunication.getCodDic();
							temp.addElement(dicc);
							temp.addElement("");
							temp.addElement("");
							temp.addElement(msgComunication.getNumLonDic());
							temp.addElement(msgComunication.getTxtFor());
							temp.addElement("");
							temp.addElement(dato);
							temp.addElement("");
							temp.addElement("0");
							data.addElement(temp);
							//if(Constante.VER_LOG) System.out.println("end a la construccion del vector");
						}
					}
					
				}else if (trama.substring(85, 87).equals("10")){  
				    // MODIFICAR ANTES DE PASARLO A CERTI "10"
					//System.out.println("//------->VALOR DE CONSTANTE="+trama.substring(89,93)+"////");
					//System.out.println("//------->VALOR DE MENSAJE="+trama.substring(106, 146)+"<--////");
					String codapp = "9999";
					codapp=""+trama.substring(87,89);
					String codmsg=""+trama.substring(89,93);

					ConstanteSesion.HOST_ERROR_DD_3024 = false;
					
					// Creando el Ojeto APLICACION, para actualizar los mensajes de la BD
					// 14/05/2008
					BNAplicacion aplicacion =BNAplicacion.getInstance();
					
					/************************************************/

					Date dHoraAntes = new Date();
				    Date dHoraDespues = new Date();
				    Date dHoraActual = new Date();
				    
				    dHoraAntes.setHours(21);
				    dHoraAntes.setMinutes(0);
				    dHoraAntes.setSeconds(0);
				    
				    dHoraDespues.setHours(22);
				    dHoraDespues.setMinutes(00);
				    dHoraDespues.setSeconds(0);
				    
				    boolean fReturnHora = false;
				    boolean fExisteCodigoDD = false;
				    
				    fReturnHora = ObjectUtil.getRangoFecha(dHoraAntes, dHoraDespues, dHoraActual);
				    

				    String codErrorFinal = codapp;
				    //codErrorFinal.concat(codmsg);
				    codErrorFinal = codErrorFinal.concat(codmsg);
				    					

				    
				    if (codErrorFinal.trim().equals("DD3024")) {
				        fExisteCodigoDD = true;
				    }
				    

				    if ((fExisteCodigoDD) || (codMsgCics.equals("9") && fReturnHora) || (codMsgCics.equals("8") && fReturnHora)){
						//System.out.println("Entró a DD-3024");
						ConstanteSesion.HOST_ERROR_DD_3024 = true;
						try {
							
							Calendar cal = Calendar.getInstance();
							cal.add(Calendar.MINUTE,3);
							//Actualizo el horario en la base de datos
							DAOFactory.getGeneraDAO().setHorarioInicioDia(HorarioTest.getHora(cal)+":"+HorarioTest.getMinuto(cal)+":"+HorarioTest.getSegundo(cal));
							
							// Actualizando la Mensajistica del Aplicativo
							// 14/05/2008
							aplicacion.getMensajes();

						} catch (Exception e) {
							log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
							System.out.println("Error al actualizar el horario de Inicio de dia en la base de datos");
							e.printStackTrace();
						}
					}
					/************************************************/
					
					String mensaje=aplicacion.getMsjesHost(codapp,codmsg).elementAt(2).toString();
					ConstanteSesion.HOST_ERROR = "0";
					ConstanteSesion.HOST_ERROR_MSG = "";
					ConstanteSesion.HOST_ERROR = "1";
					ConstanteSesion.HOST_COD_APP=codapp;
					ConstanteSesion.HOST_COD_MSG=codmsg;
					ConstanteSesion.HOST_ERROR_MSG = "("+codapp+"-"+codmsg+") "+ trama.substring(106, 146);
					ConstanteSesion.HOST_ERROR_WAP = mensaje+" ("+codapp+"-"+codmsg+")";
				
					throw new ArrayRuleException(ConstanteError.GENERICO,mensaje+" ("+codapp+"-"+codmsg+")");
					//throw new ArrayRuleException(ConstanteError.GENERICO,mensaje+".");
				}
				else {
					//TODO FALTA CAPTURAR EL CODIGO DE LA TRAMA DE ENVIO
					String codeErrorTold = trama.substring(85, 87);
					
					//if(Constante.VER_LOG) System.out.println("Lanzando WebServicesException ---- codeErrorTold="+codeErrorTold);
					throw new ArrayRuleException(ConstanteError.GENERICO,"ERROR EN RESPUESTA:"+trama.substring(85, 87));
				}
	}

	//data es un Vector de Diccionarios.
	/**
	 * @param codTrx : codigo de transacion.
	 * @param 
	 */
	public String sendHost(String codTrx, String canal,String tramaDictionary ,java.util.Vector data, java.util.Vector cuentas) throws Exception{
		//System.out.println("LOGIN SEG Host- codTrx, longitud: " + codTrx);
		String longitudTrama="";
		if(codTrx.length()>4){
			longitudTrama= codTrx.substring(5,codTrx.length());
			codTrx= codTrx.substring(0,4);
		}else{
			longitudTrama= "3820";
			codTrx= codTrx;
		}
		
		//System.out.println("Host- codTrx:" + codTrx);
		//System.out.println("Host- longitud:" + longitudTrama);
        try
        {   
    	    ParserXML parser = new ParserXML();
            //String ruta 	 = BNAplicacion.getInstance().getRutaClasspath() + java.io.File.separator + "WEB-INF" + java.io.File.separator + "wsdl"+java.io.File.separator+"LogLevel.xml";
    	    
           // String ruta = BNAplicacion.getInstance().getRutaClasspath() + java.io.File.separator + "WEB-INF" + java.io.File.separator + "LogLevel.xml";                
            //System.out.println("ruta:"+ruta);
            
            //Map mapa = parser.leerDocDOM(ruta);
            
           // String level = (String)mapa.get("Level"); 
            
                //System.out.println("Level:"+level);
            
                /**
            if ("1".equals(level))
                log.setLevel(Level.INFO);
            else if ("2".equals(level))
                log.setLevel(Level.WARN);
            else if ("3".equals(level))
                log.setLevel(Level.DEBUG);
            else
                log.setLevel(Level.OFF);
                */
            
        } 
        catch (Exception e) 
        {
            System.out.println(e);
            log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
        }  
	      	//System.out.println("begin method send host");
			
			Vector vMaskDictionary = null;
			String header = prepareHeaderAscii(codTrx, canal,cuentas);
			//if(Constante.VER_LOG) System.out.println("header="+header);
			String cuerpo 	= "";
			int longvalor 	= 0;
			int longdicc 	= 0;
			BNAplicacion.getInstance().inicioDia();
			String[] arrDictionary = ObjectUtil.getArrayStrings(tramaDictionary,",");
			
			for (int j = 0; j < arrDictionary.length; j++) {
				String relleno = "";
				String codDic = arrDictionary[j];
				vMaskDictionary = ObjectUtil.getVectorbyDicc(codDic, data);
				//if(Constante.VER_LOG) System.out.println("senHost - ObjectUtil.getVectorbyDicc(codDic, data):" + ObjectUtil.getVectorbyDicc(codDic, data));
				try{
					longvalor = vMaskDictionary.elementAt(6).toString().length();	
				}
				catch (Exception e) {
					//log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
					longvalor = 0;
				}
				Vector diccionario_  = BNAplicacion.getInstance().getDiccionarioPorCodigo(codDic.trim());
				longdicc =(new Integer(diccionario_.elementAt(2).toString())).intValue();
				
				String dato = "";
				if(vMaskDictionary!=null&&vMaskDictionary.elementAt(6)!=null)
					dato = vMaskDictionary.elementAt(6).toString();
				//TODO PARA LO QUE ES NUMERICO QUITARLE LAS COMAS Y PUNTOS PARA LOS MONTOS EJEM 25.15 PASARLO A 2515
				if (longvalor <= longdicc) {
					if (diccionario_.elementAt(4).equals(Constante.IDEN_ALFANUMERIC)) {
						for (int n = 0; n < longdicc - longvalor; ++n) {							
								relleno = relleno + " ";
							/*if(dato.equals("")){
								relleno = " ";	
							}
							else{
								relleno = "";
							}*/
						}
						dato += relleno;
						//dato += Constante.SEP_ALPHANUMERIC;//TODO SEPARADOR ALFANUMERICO @
						dato += (char)64;//SEPARADOR ALFANUMERICO @
					} else {
						//
						for (int n = 0; n < longdicc - longvalor; ++n) {
							relleno = relleno + "0";
/*							if(dato.equals("")){
								relleno = "0";	
							}
							else{
								relleno = "";
							}
	*/						
						}
						dato = relleno + dato;
						//dato += Constante.SEP_NUMERIC;//TODO SEPARADOR NUMERICO ¬
						dato +=(char)126;//SEPARADOR NUMERICO ¬
					}
				} 
				else{
					dato = dato.substring(0,longdicc);
				}
				cuerpo = cuerpo + dato;
			}
			cuerpo += (char)33+Constante.SPACER_BODY;			
			
			String flagLog = Parametro.getString(ConstanteParametros.BN_PARAM_FLAG_VER_LOG);
					
			boolean isActiveLog=Boolean.parseBoolean(flagLog); 
			String uniqueID = UUID.randomUUID().toString();
			
			//TODO CAMBIO PARA CODIFICAR EL ASCII A DECIMAL EBCDIC
			/*******************************************************************************************/
			String strTemp = header+cuerpo;
			if(isActiveLog) System.out.println("Envio TRAMA["+uniqueID+"]:"+strTemp);
			
//			//TODO BEGIN ASIGNACION DE LONGITUD DE TRAMA DINAMICA
//				int tamTrama = strTemp.length();
//				header = ObjectUtil.procesarLongitud(tamTrama) + header; 
//				//if(Constante.VER_LOG) System.out.println("el valor del header="+header);
//				
//				//byte[] ebcdic1				= Crypto.AsciiToEbcdic(header);
//				//String codigoHex1			= Crypto.ToHexa(ebcdic1);				
//			//END ASIGNACION DE LONGITUD DE TRAMA DINAMICA
//							     
//				   //System.out.println("BNAplicacion.getInstance().getTipoEncriptacion():"+BNAplicacion.getInstance().getTipoEncriptacion());
//				
//			//TODO aqui es la nuez :P
//			if(Constante.ENCRIPTACION_DES.equals(BNAplicacion.getInstance().getTipoEncriptacion())){
//				
//				byte[] asciiEncriptado = null;
//				byte[] ebcdic				= Crypto.AsciiToEbcdic(cuerpo);
//				String codigoHex			= Crypto.ToHexa(ebcdic);
//				String codigoEncriptado		= Crypto.encriptaDES(codigoHex, BNAplicacion.getInstance().getClaveDESA());
//				//String codigoEncriptado		= Crypto.encriptaDES(codigoHex, BNAplicacion.getInstance().getClaveDESB());
//				
//				//printWithName(codigoEncriptado.getBytes(),"codigoEncriptado");
//				
//				asciiEncriptado				= Crypto.ToAscii2(codigoEncriptado);
//				StringBuffer sb = new StringBuffer("");
//				for (int i = 0; i < asciiEncriptado.length; i++) {
//					String numero = String.valueOf(ObjectUtil.byteToPositiveInt(asciiEncriptado[i]));
//					sb.append(ObjectUtil.setearCaracterLeft(numero,'0',3-numero.length()));
//				}
//				cuerpo = sb.toString();
//				
//			}
//			else if(Constante.ENCRIPTACION_TRIPLE_DES.equals(BNAplicacion.getInstance().getTipoEncriptacion())){
//				
//				byte[] asciiEncriptado = null;
//				byte[] ebcdic				= Crypto.AsciiToEbcdic(cuerpo);
//				String codigoHex			= Crypto.ToHexa(ebcdic);
//				String codigoEncriptado		= Crypto.encripta3DESede2Key(codigoHex, BNAplicacion.getInstance().getClaveDESA(), BNAplicacion.getInstance().getClaveDESB());
//				asciiEncriptado 			= Crypto.ToAscii2(codigoEncriptado);
//
//				StringBuffer sb = new StringBuffer("");
//				for (int i = 0; i < asciiEncriptado.length; i++) {
//					String numero = String.valueOf(ObjectUtil.byteToPositiveInt(asciiEncriptado[i]));
//					sb.append(ObjectUtil.setearCaracterLeft(numero,'0',3-numero.length()));
//				}
//				cuerpo = sb.toString();
//								
//			}
//			else{
//				cuerpo = TramaUtil.getStringDecimalEbcdic(cuerpo);
//			}
//			//FIN de aqui es la nuez :P
//			
//			//El header solo se pasa a EBCDIC
//			header =  TramaUtil.getStringDecimalEbcdic(header);
//			/*******************************************INICIO DE ANT WEB SERVICES************************************************/
//			String cadena = null;
//			
//			cadena = header + cuerpo;	
//			

			
			//if(Constante.VER_LOG) System.out.println("send to web services codificado Cadena:"+cadena);
			//String tramaCodificada = UtilWebServices.getInstance().callSendHost(Constante.URI_WEB_SERVICES,cadena);
			/*******************************************FIN DE ANT WEB SERVICES*********************/
			
			//System.out.println("Inicio UtilWebServices.getInstance().callNewSendHost()");	
			
			//Map mapResp = UtilWebServices.getInstance().callNewSendHost(codTrx+cadena);
			
			Map mapResp = UtilWebServices.getInstance().callNewSendHost(codTrx,strTemp);
			
			//System.out.println("Fin UtilWebServices.getInstance().callNewSendHost()");
			
			String codResp = "";
			String tramaCodificada  = (String)mapResp.get("tramaRespuesta");
			codResp 			= (String)mapResp.get("codResp");
			//byte[] byteCodificado 	= (byte[])mapResp.get("byteRespuesta");
			
			//printWithName(byteCodificado,"110");
//			/***************************************************************************************/
//			if(Constante.ENCRIPTACION_DES.equals(BNAplicacion.getInstance().getTipoEncriptacion()))
//			{
//			    //TODO PONIENDOLO			    
//			    
//			    String tramaCodificadaBK =  tramaCodificada;
//			    
//			    ByteArrayOutputStream bTemp = new ByteArrayOutputStream(byteCodificado.length-(1176*3));
//			    
//			    int longitud0 = (270+3826)*3;
//			    
//			    bTemp.write(byteCodificado,(1176*3),longitud0);			 
//			    
//		
//			    tramaCodificada	= TramaUtil.getProcessBytes(bTemp.toByteArray());
//			 
//				byte[] asciiEncriptado		= TramaUtil.getBytesTrama(tramaCodificada);
//				
//				int longitud = asciiEncriptado.length; 
//
//				ByteArrayOutputStream bClave = new ByteArrayOutputStream(longitud);
//
//				bClave.write(asciiEncriptado, 270, longitud-270);				
//				
//				//imprimir6(bClave.toByteArray());
//				
//				String tramaEbcdic =  Crypto.EbcdicToAscii(asciiEncriptado);
//		
//				ByteArrayOutputStream bDatos = new ByteArrayOutputStream();
//
//					bDatos.write(bClave.toByteArray(),0,Integer.parseInt(longitudTrama));
//					
//				byte[] asciiDesencriptado	= Crypto.desencriptaTramaDES(bDatos.toByteArray(), BNAplicacion.getInstance().getClaveDESA());
//				
//				String output = new String(Crypto.EbcdicToAscii(asciiDesencriptado));
//				//printWithName(output.getBytes(),"610");
//				//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//				int strn = tramaEbcdic.indexOf("STRN");
//				if (strn == -1)
//					strn = tramaEbcdic.indexOf("STRM");		
//
//				//System.out.println("strn:"+strn);
//				//System.out.println("tramaEbcdic.length():"+tramaEbcdic.length());
//				
//				
//				//printWithName(tramaEbcdic.getBytes(),"tramaEbcdic");
//				//if(Constante.VER_LOG) System.out.println("1. paso strn:"+strn);
//				//printWithName(tramaEbcdic.substring(1000,tramaEbcdic.length()).getBytes(),"tramaEbcdic.subs");
//				/**BEGIN IMPRIME LOG CUANDO NO VIENE UN STR NI STRM **/
//				if (strn == -1){
//					     //System.out.println("****************** COMENZANDO LA IMPRESION DE LA TRAMA DAÑADA *****************");
//					    //if(Constante.VER_LOG) System.out.println(tramaEbcdic.substring(1000,tramaEbcdic.length()));
//					     //System.out.println(tramaEbcdic);
//					     //System.out.println("****************** TERMINANDO LA IMPRESION DE LA TRAMA DAÑADA *****************");
//					}
//				/**END  IMPRIME LOG CUANDO NO VIENE UN STR NI STRM **/
//				//if(Constante.VER_LOG) System.out.println("1.2 paso strn:"+strn);
//				//printWithName(tramaEbcdic.substring(strn,tramaEbcdic.length()).getBytes(),"tramaEbcdic.substring");
//				//if(Constante.VER_LOG) System.out.println("2 paso strn:"+strn);
//				String temheader = tramaEbcdic.substring(strn,strn+270);
//				//if(Constante.VER_LOG) System.out.println("3 paso strn:"+strn);
//				tramaCodificada	= temheader + output;
//				// Comentado por Solicitud del BN 11/11/2008
//				//printWithName(temheader.getBytes(),"temheader");
//				
//				//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//				//tramaCodificada = tramaEbcdic.substring(0,1446) + output;//TramaUtil.decodificarDecimal2Ebcdic(asciiDesencriptado);
//				// Comentado por Solicitud del BN 11/11/2008
//				// printWithName(tramaEbcdic.substring(0,1446).getBytes(),"710");
//				// printWithName(output.getBytes(),"720");
//				// printWithName(tramaCodificada.getBytes(),"730");
//			}
//			else if(Constante.ENCRIPTACION_TRIPLE_DES.equals(BNAplicacion.getInstance().getTipoEncriptacion())){
//			    //TODO PONIENDOLO 
//			    tramaCodificada 			= TramaUtil.getProcessBytes(byteCodificado);
//			    
//				byte[] asciiEncriptado		= TramaUtil.getBytesTrama(tramaCodificada);
//				
//				byte[] asciiDesencriptado	= Crypto.desencriptaTrama3DES(asciiEncriptado, BNAplicacion.getInstance().getClaveDESA(), BNAplicacion.getInstance().getClaveDESB());
//				tramaCodificada				= TramaUtil.decodificarDecimal2Ebcdic(asciiDesencriptado);
//				
//				
//			}
//			else{
//				tramaCodificada   = TramaUtil.decodificarDecimal2Ebcdic(tramaCodificada);	
//				
//				
//			}
			/***************************************************************************************/
//			int intSTRN = tramaCodificada.indexOf("STRN");
			
			if(isActiveLog){ 
				System.out.println("Rpta TRAMA["+uniqueID+"]:"+tramaCodificada);
				log3.debug("Rpta TRAMA["+uniqueID+"]:", tramaCodificada, "1");
			}
//			tramaCodificada = tramaCodificada.substring(1176,tramaCodificada.length());
			//tramaCodificada = tramaCodificada.substring(intSTRN,tramaCodificada.length());//cambio del int STRN
			// Comentado por Solicitud del BN 11/11/2008
			if(Constante.VER_LOG){
			    String dateFormat="HHmmss.SSS";
			    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
			    //printWithName(tramaCodificada.getBytes(),codTrx + sdf.format(java.util.Calendar.getInstance().getTime()));
			}
			//System.out.println("TRAMA RESPUESTA DE HOST ARCHIVO ARCHIVO 810: " + new String(tramaCodificada.getBytes()));
			/*******************************************************************************************/
			ConstanteSesion.HOST_ERROR = "0";
			ConstanteSesion.HOST_ERROR_MSG = "";
			loadHostResponse(codTrx, tramaCodificada, data, cuentas, codResp);
			return tramaCodificada;
	}

	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 * @param dic java.lang.String
	 * @param data java.util.Vector
	 */
	public String setHostCode(String dic,java.util.Vector data,java.util.Vector cuentas) {
		return "M4";
	}
	
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 * @param dic java.lang.String
	 * @param data java.util.Vector
	 */
	public String swapHostFlag(String dic,java.util.Vector data,java.util.Vector cuentas) {
		return "";
	}

	public  String EbcdicToAscii(byte[] Ascii ){
		String cad = "";    
		try {
			return new String(Ascii, encoding);
		}catch(Exception e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			System.out.println(e);
		}
		return cad;
	}
	
	
	
	private void printWithName(byte[] trama,String nombre){
	    File f = null;
	    String ambiente = Parametro.getString(ConstanteParametros.BN_PARAM_PARAMETRO_SATI_PINBLOCK);
	    
	    if (ambiente.equals("1")){
		    //String archivo = "/home/logs/"+nombre+"__"+ObjectUtil.getCodigoTRX()+".txt";
	        String archivo = "/home/logs/"+nombre+".txt";
			f = new File(archivo);
		}
		else if (ambiente.equals("2") || Constante.CONSTANTE_OS.equals("3")){
		    String archivo = "/home/logs/"+nombre+".txt";
		    f = new File(archivo);
		}
		try {
			FileOutputStream st = new FileOutputStream(f);
			st.write(trama);
			st.close();
			f = null;
			st = null;
		} catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			System.out.println(e);
		}
	}
	
	
	private void printWithNameWin(byte[] trama,String nombre){
	    File f = null;
	    String ambiente = Parametro.getString(ConstanteParametros.BN_PARAM_PARAMETRO_SATI_PINBLOCK);
	    
	    if (ambiente.equals("1")){
	        String archivo = "c:\\logs\\"+nombre+"__"+ObjectUtil.getCodigoTRX()+".txt";
			f = new File(archivo);
		}
		else if (ambiente.equals("2") || Constante.CONSTANTE_OS.equals("3")){
		    String archivo = "/home/logs/"+nombre+".txt";
		    f = new File(archivo);
		}
		try {
			FileOutputStream st = new FileOutputStream(f);
			st.write(trama);
			st.close();
			f = null;
			st = null;
		} catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			System.out.println(e);
		}
	}
	
	private void printArrayByte(byte[] trama,String nombre){
	    File f = null;
	    String ambiente = Parametro.getString(ConstanteParametros.BN_PARAM_PARAMETRO_SATI_PINBLOCK);
	    
	    if (ambiente.equals("1")){
		    //String archivo = "/home/logs/"+nombre+"__"+ObjectUtil.getCodigoTRX()+".txt";
		    String archivo = "/opt/log/sati/"+nombre+".txt";
			f = new File(archivo);
		}
		else if (ambiente.equals("2") || Constante.CONSTANTE_OS.equals("3")){
		    String archivo = "/opt/log/sati/"+nombre+".txt";
		    f = new File(archivo);
		}
		String result = "";
		for (int i = 0; i < trama.length; i++) {
		    result += String.valueOf(trama[i]) + "\r\n";
        }
		
		try {
			FileOutputStream st = new FileOutputStream(f);
			st.write(result.getBytes());
			st.close();
			f = null;
			st = null;
		} catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			System.out.println(e);
		}
	}
		
}