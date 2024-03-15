/*
 * Created on 03/08/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pe.bn.consulta.reportes;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.ArrayRuleException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;


import pe.cosapi.common.Constante;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONObject;
import org.springframework.util.FileCopyUtils;



public class PedidoFicha implements Serializable {
	private static LoggerSati log3 = LoggerSati.getInstance(PedidoFicha.class.getName());

	public static int imprimeRepMovimiento(HttpServletResponse response, InputStream reportStream, HashMap parameters) throws Exception{
		 
	    Constante.FLAG_CACHE = "1";

		 int cantRegistros = 1;
		 Context ctxTempLog;
		 DataSource dsTempLog;
		 Connection cnTempLog = null;
		 PreparedStatement psTempLogSequence;
		 ResultSet rs;
		 PreparedStatement psTempLogQuery;
		 
		 try {
		     
		     ctxTempLog = new InitialContext();
//		     System.out.println("imprimeRepMovimiento [INICIO]");
		     dsTempLog = (DataSource)ctxTempLog.lookup("jdbc/dbSERV");
		     cnTempLog = dsTempLog.getConnection();
		     
//		     System.out.println("imprimeRepMovimiento: url="+cnTempLog.getMetaData().getURL());
		     
		     //cnTempLog.setAutoCommit(true);
		     // Buscar el valor siguiente para utilizar como ID
		     /**
		     psTempLogSequence = cnTempLog.prepareStatement("		" +"" +
		     		"Select * FROM bn_pservicio.BNDETR_MOVIMIENTO A Where A.Ncuenta = '1924271455' AND A.Tregistro = 5");
		     		*/
		     //00101087093
		     String query="SELECT A.SALDULTESTEMITIDO, A.FUTLESTEMITIDO, B.FPROCESO,	B.NCUENTA, B.CODCLIENTE, "+
				"		B.NOMBRE1CLIENTE,	  B.NOMBRE2CLIENTE,	B.NOMBRECUENTA1, B.NOMBRECUENTA2, B.CODGRUPO "+
				"	FROM bn_pservicio.BNDETR_MOVIMIENTO A INNER JOIN bn_pservicio.BNDETR_CABECERA B ON (A.FPROCESO = B.FPROCESO And A.NCUENTA = B.NCUENTA) "+ 
				"	INNER JOIN bn_pservicio.BNDETR_RESUMEN C ON (C.NCUENTA = B.NCUENTA AND C.FPROCESO = B.FPROCESO) "+
				"	WHERE SUBSTR(A.FPROCESO,0,6) = '" + parameters.get("nPeriodo")+"'"+
				"		AND B.NCUENTA = '"+ parameters.get("numCta")+ "'"+
				"		AND A.Tregistro = 5 "+
				"	ORDER BY A.nsecuencia ";

		     psTempLogSequence = cnTempLog.prepareStatement("		" + query);
						
		     double tDemora=0;
		     long tDesde=System.currentTimeMillis();     
		     rs = psTempLogSequence.executeQuery();
		     tDemora=(System.currentTimeMillis() - tDesde)/1000;
//		     System.out.println("imprimeRepMovimiento: verificacion en "+tDemora+"s.");
		     // Query de insercion a la DB
		     //cnTempLog.close();
		     tDesde=System.currentTimeMillis();
			/**
			 * Código para poder saber si existen registros o no y
			 * Obtener el saldo inicial con la que inicia el consulta.
			 *  
			 */
		    
			boolean seguir = rs.next();

//			System.out.println("imprimeRepMovimiento: existen registros="+seguir);
			if (seguir){
				//rs.next();
				
				ServletOutputStream servletOutputStream = null;
			    //response.setContentType("application/pdf");
			    
				BigDecimal nSaldoInicial = new BigDecimal(rs.getString(1));
				String cFechaSaldo = new String(rs.getString(2));
				String codCliente = new String(rs.getString(5));
				String grupo = new String(rs.getString(10));
							
				parameters.put("pSaldoInicial",nSaldoInicial);
				
				parameters.put("cFechaInicial", cFechaSaldo);
				if(codCliente !=null ){
					if(!codCliente.trim().equals(Constante.CONST_CODIGO_VACIO_CTA_CTE)){
				
						parameters.put("codCliente",codCliente+"-"+grupo);
						parameters.put("etCliente",Constante.CONST_ETIQUETA_ESTADO_CTA_CTE);
					}else{
						parameters.put("codCliente","");
						parameters.put("etCliente","");
					}
				}else{
					parameters.put("codCliente","");
					parameters.put("etCliente","");
				}

				tDemora=(System.currentTimeMillis() - tDesde)/1000;
//			    System.out.println("imprimeRepMovimiento: executeQuery->runReportToPdf en "+tDemora+"s.");
			    tDesde=System.currentTimeMillis();
				byte[] fichero = JasperRunManager.runReportToPdf (reportStream, parameters, cnTempLog);
				tDemora=(System.currentTimeMillis() - tDesde)/1000;
//			    System.out.println("imprimeRepMovimiento: runReportToPdf en "+tDemora+"s.");
			    tDesde=System.currentTimeMillis();
							
				response.setContentType("application/pdf");
				
				response.setHeader("Content-disposition", "inline; filename=Rpt_Pservicio.pdf");
				//response.setHeader("Content-Disposition", "attachment;filename=\"Estado_Cuenta"+parameters.get("nPeriodo")+".pdf" + "\"");
				response.setContentLength(fichero.length);

				servletOutputStream = response.getOutputStream();
			
				cantRegistros = 1;
			
				cnTempLog.close();
				servletOutputStream.write(fichero, 0, fichero.length);
			 	servletOutputStream.flush();
			 	servletOutputStream.close();
				tDemora=(System.currentTimeMillis() - tDesde)/1000;
//			    System.out.println("imprimeRepMovimiento:runReportToPdf->close en : "+tDemora+"s.");

			} else {
				cantRegistros = 0;
				cnTempLog.close();
				//conn.close();
				
			}
			
		 }catch(ArrayRuleException e){
			 log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		    System.out.println("imprimeRepMovimiento.ArrayRuleException: "+e.getMessage());
		 	if(Constante.VER_LOG)System.out.println("Exception 1 PedidoFicha.....");
			throw e;
		 }
//		 }catch(Exception e1){
//			    System.out.println("imprimeRepMovimiento.Exception="+e1.getMessage());
//			 	if(Constante.VER_LOG)System.out.println("Exception 1 PedidoFicha.....");
//				throw e1;
//			 }
		 finally{
		 	try{
		 		//conn.close();
		 		cnTempLog.close();
		 	}catch(Exception e){
		 		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		 	   System.out.println("imprimeRepMovimiento.close="+e.getMessage());
		 		if(Constante.VER_LOG)System.out.println("Exception 3 PedidoFicha");
		 		if(Constante.VER_LOG)System.out.println(""+e);
		 		return cantRegistros;
		 	}
		 	System.out.println("imprimeRepMovimiento [FIN]");
		}
		 return cantRegistros;
	}
	
	public static void obtenerEstadoCuentaCorriente(HttpServletResponse response, HashMap parameters) throws Exception{
		
		 try {
			 
			 ServletOutputStream servletOutputStream = null;
			 
			 int indfechaCorte= Integer.parseInt(Parametro.getString(ConstanteParametros.BN_PARAM_SATI_IND_FECHA_ESTADO_CTA_CTE));
			 String urlService="";
			 int fechaConsulta=Integer.parseInt(parameters.get("nPeriodo").toString());
			 
			 if(fechaConsulta > indfechaCorte){
				 urlService=Parametro.getString(ConstanteParametros.BN_PARAM_WS_SATI_ESTADO_CTA_CTE_NUEVO).concat("?PrmNroCuenta=").concat(""+parameters.get("numCta")).concat("&PrmFecInicial=").concat(""+parameters.get("nPeriodo"));
			 }else{
				 urlService=Parametro.getString(ConstanteParametros.BN_PARAM_WS_SATI_ESTADO_CTA_CTE_ANTIGUO).concat("?PrmNroCuenta=").concat(""+parameters.get("numCta")).concat("&PrmFecInicial=").concat(""+parameters.get("nPeriodo"));
			 }
				
			
			 String flagLog = Parametro.getString(ConstanteParametros.BN_PARAM_FLAG_VER_LOG);
			 boolean isActiveLog=Boolean.parseBoolean(flagLog); 
			 //String strURL= Parametro.getString(ConstanteParametros.BN_PARAM_WS_SATI_ESTADO_CTA_CTE_NUEVO).concat("?PrmNroCuenta=").concat(""+parameters.get("numCta")).concat("&PrmFecInicial=").concat(""+parameters.get("nPeriodo"));
			 //String strURL="http://10.7.12.69/Aplicaciones/Banco/Gerencia_Operaciones/ServiceMVCEstadosCtaCte/CtaCteEstado/GetMOVICTASCTES?PrmNroCuenta=00105001711&PrmFecInicial=20200831";
			 if(isActiveLog)System.out.println("URL-EECC:"+urlService);
			 
			 URL url = new URL(urlService);
	         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	         conn.setRequestMethod("GET");
	         //conn.setRequestProperty("Content-Type", "application/pdf");
	         
	         conn.setConnectTimeout(5000);
	         conn.setDoOutput(true);
	          
	            if (conn.getResponseCode() != 200) {
	            	
	                throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
	            } 
	           
	            InputStream in = conn.getInputStream();
	           	            
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            
				byte[] array = new byte[1024]; // buffer temporal de lectura.
				int n = in.read(array);
				while (n > 0) {
					
					baos.write(array,0,n);
					n = in.read(array);
				}

				
				byte[] fichero = baos.toByteArray();
			
				conn.disconnect();

				
							
				
				//response.setContentType("application/xxx"); //poner el tipo correspondiente
				response.setDateHeader("Expires", 0);
				response.setHeader("CacheControl", "Private");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "No-cache");
				response.setHeader("Pragma", "store");
				response.setHeader("Cache-Control", "store");
				//response.setHeader("Content-disposition", "\"nombre_de_tu_fichero.pdf\"");
				response.setHeader("Content-Disposition", "inline;filename=\"estado_cuenta.pdf\"");
				response.setContentType("application/pdf; name=\"estado_cuenta.pdf\"");
				//response.setHeader("Content-Disposition", "attachment;filename=\"Estado_Cuenta"+parameters.get("nPeriodo")+".pdf" + "\"");
				response.setContentLength(fichero.length);

				servletOutputStream = response.getOutputStream();
			
				response.getOutputStream().write(fichero);
			 	servletOutputStream.flush();
			 	servletOutputStream.close();
				

			
		 }catch(ArrayRuleException e){
			 log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		    System.out.println("imprimeRepMovimiento.ArrayRuleException: "+e.getMessage());
		 	if(Constante.VER_LOG)System.out.println("Exception 1 PedidoFicha.....");
			throw e;
		 }
//		 }catch(Exception e1){
//			    System.out.println("imprimeRepMovimiento.Exception="+e1.getMessage());
//			 	if(Constante.VER_LOG)System.out.println("Exception 1 PedidoFicha.....");
//				throw e1;
//			 }

		
	}
}