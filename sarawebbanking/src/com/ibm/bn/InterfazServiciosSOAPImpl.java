/**
 * InterfazServiciosSOAPImpl.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf150632.18 v81506105401
 */

package com.ibm.bn;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import files.target.SATI620PortTypeProxy;

//Clase en WS1 
public class InterfazServiciosSOAPImpl // implements com.ibm.bn.InterfazServicios_PortType
{
    
    public void enviarTramaConsulta(java.lang.String codTrans, java.lang.String tramaConsulta, javax.xml.rpc.holders.StringHolder tramaRespuesta, javax.xml.rpc.holders.IntHolder codRes) throws java.rmi.RemoteException {
    	
        //       Trace
    	// Se inicializan las variables para el proceso de tracing el cual sera habilitado unicamente mediante
    	// la modificacion y recompilacion del programa fuente.        
    	//System.out.println("Inicia InterfazServicios.enviarTramaConsulta()");
        //System.out.println("CODIGO TRANSACCION: "+codTrans);
    	// Cambiar por TRUE para realizar el trace.
    	boolean bTraceFlag = false;
    	// Cambiar por el limite de caracteres deseado por trama
    	int iCharLimit = 20;
    	
    	String sTraceInputTrama = "";
    	String sTraceOutputTrama = "";
    	BufferedWriter fTraceLog;
    	String sLogBuffer;
    	
    	if (bTraceFlag == true){
    		sTraceInputTrama = tramaConsulta;
    		sTraceOutputTrama = "";
    	}
    	// Trace
    	
    	// DB Log 
    	// Se realiza la grabacion del log en la DB utilizando el data source con nombre jdbc/dbJT 
    	// configurado previamente en el WAS. 
    	Context ctxTempLog;
    	DataSource dsTempLog;
    	Connection cnTempLog;
    	PreparedStatement psTempLogSequence;
    	ResultSet rs;
    	PreparedStatement psTempLogQuery;
    	int iIDLog = 0;
    	
    	try{
//    		ctxTempLog = new InitialContext();
//    		dsTempLog = (DataSource)ctxTempLog.lookup("jdbc/dbJT");
//    		cnTempLog = dsTempLog.getConnection();
//    		cnTempLog.setAutoCommit(true);
//    		// Buscar el valor siguiente para utilizar como ID
//    		psTempLogSequence = cnTempLog.prepareStatement("SELECT BN_SWB_LOG.SEQLOGSRV.NEXTVAL as NEXTVAL FROM DUAL");
//    		rs = psTempLogSequence.executeQuery();
//    		if (rs.next()){
//    			iIDLog = rs.getInt("NEXTVAL");
//    		}
//    		// Query de insercion a la DB
//    		psTempLogQuery = cnTempLog.prepareStatement("INSERT INTO BN_SWB_LOG.TLOGSRV (CODLOG, CODTRAN, FECTRAN) VALUES(" + iIDLog + ", '" + codTrans + "', SYSDATE)");
//    		psTempLogQuery.execute();
//    		cnTempLog.close();
    	}
    	catch (Exception ex){
    	    ex.printStackTrace();
    	}
    	finally
    	{
    		// Limpieza inmediata de objetos en memoria.
    		ctxTempLog = null;
    		dsTempLog = null;
    		cnTempLog = null;
    		rs = null;
    	}
    	// DB Log
    	
    	// Transaccion
    	// Se realiza la llamada al metodo del servicio web CICS-SOAP utilizando los tipos de datos
    	// generados automaticamente por el WSDL2Java, los cuales incluyen metodos de acceso para
    	// la lectura y asignacion de los datos de output e input respectivamente.
    	String sTramaRespuesta = "";

    	int iTempCodRes = 0;
    	com.BCDDTP00I.www.TCOMMAREA tempTramaConsulta = new com.BCDDTP00I.www.TCOMMAREA();
    	com.BCDDTP00O.www.TCOMMAREA tempTramaRespuesta = new com.BCDDTP00O.www.TCOMMAREA();
    	files.target.SATI620PortTypeProxy tempServicio;
    	int var=5;
    	
    	String codTrx = codTrans.substring(0,4);
     		
    	for (int nn=0;nn<var;nn++)
		{
    		//Inicializacion del objeto servicio
    		tempServicio = new SATI620PortTypeProxy();
    		tempTramaConsulta.setTrama(tramaConsulta);
    		//System.out.println("InterfazServicios BNWEB:Antes de Llamar al metodo de SATI620Operation");
	    	//Llamada al metodo del servicio
    	long t_ini=System.currentTimeMillis();
    	double t_fin=0;
    	try{
    	    //System.out.println("tempTramaConsulta"+tempTramaConsulta);
	    	tempTramaRespuesta = tempServicio.SATI620Operation(tempTramaConsulta);
	    	t_fin=(System.currentTimeMillis() - t_ini)/(double)1000;
	    	System.out.println("SATI - Trx: " + codTrx +" Tiempo Host Ok: " + t_fin);
	    	//System.out.println("InterfazServicios BNWEB:Despues de Llamar al metodo de SATI620Operation");
    		iTempCodRes = tempTramaRespuesta.getCodres();
    	}
    	catch (Exception exDelHost){
	    	t_fin=(System.currentTimeMillis() - t_ini)/(double)1000;
    	    System.out.println("SATI - Trx: " + codTrx +" Tiempo Host Error: " + t_fin);
    	    //System.out.println("InterfazServicios BNWEB:Lansando Error debido a un problema con host");
    		//Se verifica la naturaleza de la excepcion. El unico caso particular es de host no encontrado
    		//que tiene el codigo de error 10 asignado.
    		if (exDelHost.toString().startsWith("java.net.NoRouteToHostException")){
    			sTramaRespuesta = "";
    			iTempCodRes = 10;
    		}
    		//Error de timeout
    		else if (exDelHost.toString().startsWith("java.net.SocketTimeoutException")){
    			sTramaRespuesta = "";
    			iTempCodRes = 11;
    		}
    		//Cualquier otro caso representa un error en el mismo host, lo que significa que no es un
    		//error de comunicacion, y se le asigna el codigo de error 9.
    		else{
    			//System.out.println("CodTrans::"+codTrans + exDelHost);
	    		sTramaRespuesta = "";
	    		iTempCodRes = 9;
    		}
    	}
    	finally{
    		// Limpieza inmediata de objetos en memoria.
    		tempServicio = null;
    	}
    	
    	if (iTempCodRes==0){
    	    System.out.println("Envios="+(nn+1));
		    nn=var-1;
    	}else{
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                
                e.printStackTrace();
            }
            if (nn==var-1){
                System.out.println("Envios (Max)="+(nn+1));
            }
    	}
		}
    	
    	// Se verifica la existencia de errores controlados desde el host. De existir, dicho codigo
		// es enviado hacia el sistema cliente y la trama de respuesta es enviada vacia.
    	if (iTempCodRes==0){
			sTramaRespuesta = tempTramaRespuesta.getTrama();
		}
    	
    	tramaRespuesta.value = sTramaRespuesta;
    	codRes.value = iTempCodRes;
    	// Transaccion
    	
    	// Trace
    	// Con los datos resultantes de la transaccion se realiza la grabacion del archivo
    	// de trace, el cual puede ubicarse en la carpeta del perfil del WAS.
    	if (bTraceFlag == true){
    		try{
    			sTraceOutputTrama = sTramaRespuesta;
    			
    			// En sLogBuffer se arma el texto a guardar en el archivo de trace.
    			sLogBuffer = "Iniciando trace ";
    			sLogBuffer+= (new java.util.Date()).toString();
    			sLogBuffer+= "...\r\n";
    			sLogBuffer+= "Trama de consulta (limitada a "+iCharLimit+" caracteres): \r\n";
    			if (sTraceInputTrama.length()<=iCharLimit){
    				sLogBuffer+= sTraceInputTrama + "\r\n";
    			}
    			else{
    				//sLogBuffer+= sTraceInputTrama.substring(0, iCharLimit) + "\r\n";
    			    sLogBuffer+= sTraceInputTrama + "\r\n";
    			}
    			
    			sLogBuffer+= "Trama de respuesta (limitada a " + iCharLimit + " caracteres): \r\n";
    			if (sTraceOutputTrama.length()<=iCharLimit){
    				sLogBuffer+=sTraceOutputTrama + "\r\n";
    			}
    			else{
    				sLogBuffer+= sTraceOutputTrama.substring(0, iCharLimit) + "\r\n";
    			}
    			sLogBuffer+= "Fin del trace.";
    			
    			fTraceLog = new BufferedWriter(new FileWriter("/home/logs/TraceLog" + (new java.util.Date()).toString().replaceAll(":", ".") + ".txt"));
    			//fTraceLog = new BufferedWriter(new FileWriter("/home/logs/TraceLog" + pe.cosapi.common.ObjectUtil.getCodigoTRX() + ".txt"));
    			
    			//fTraceLog.write(sLogBuffer);
    			fTraceLog.write(sTramaRespuesta);
    			fTraceLog.close();
    		}
    		catch (Exception ex2){
    			System.out.println(ex2.toString());
    		}
    		finally{
    			// Limpieza inmediata de objetos en memoria.
    			sTraceInputTrama = null;
    			sTraceOutputTrama = null;
    			sLogBuffer = null;
    			fTraceLog = null;
    		}
    	}
    	// Trace
    }

}
