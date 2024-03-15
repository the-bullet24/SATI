package pe.bn.tcredito.dao.impl;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.util.FileCopyUtils;


import com.ibm.ws.cluster.proxy.ClusterCallbackPackage.byteArrayHelper;

import pe.bn.tcredito.dao.TarjetaCreditoDAO;
import pe.bn.tcredito.domain.Formato;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ObjectUtil;

import pe.cosapi.common.DAOAbstract;



public class TarjetaCreditoIbatis extends DAOAbstract implements TarjetaCreditoDAO  {
	private static LoggerSati log3 = LoggerSati.getInstance(TarjetaCreditoIbatis.class.getName());
	
		
	

		 public List buscarEstadoCuenta( String numDocumento) throws Exception {
			  	
			 	
			 	List lstResult = new ArrayList();
				Context ctxTempLog;
				DataSource dsTempLog;
				Connection cnTempLog = null;
				PreparedStatement psTempLogSequence;
				ResultSet rs;
				byte[] buf = new byte[1024];
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				try {
					     
					
					ctxTempLog = new InitialContext();
					dsTempLog = (DataSource)ctxTempLog.lookup("jdbc/dbSCMC");
					cnTempLog = dsTempLog.getConnection();
					 
				
					String query= 	"  select * from "+ 
										"( select f50_num_documento,"+
										"f50_fecha_emision  "+
										"from bn_scmc.bnecf50_estadotarjcredito "+ 
										"where  f50_num_documento= ?  order by f50_fecha_emision desc) "+
										"where   rownum <= 3";

					
					psTempLogSequence = cnTempLog.prepareStatement(query);
					psTempLogSequence.setString(1,numDocumento);
					
					rs = psTempLogSequence.executeQuery();
					
				
					while (rs.next()){   
						Formato formato = new Formato();
						
						formato.setNumDocumento(rs.getString(1));
						formato.setPeriodo(rs.getString(2));
						formato.setFecha(ObjectUtil.getYYYYMMDDFormateada(rs.getString(2)));
						lstResult.add(formato);
								
					}
						
										
					if(rs!=null) { rs.close();rs=null;}
			        if (psTempLogSequence !=null) {psTempLogSequence.close();psTempLogSequence=null;}
			        if (cnTempLog !=null) {cnTempLog.close();cnTempLog=null;}
			        
			    	
			   
					
				}catch(Exception e){
					e.printStackTrace();
				 
				}
				return lstResult;
					
	}

		
 public void encontrarFormato2( final String numDocumento,final String periodo,final OutputStream contentStream) throws Exception {
				
	 			
	 			Context ctxTempLog;
				DataSource dsTempLog;
				Connection cnTempLog = null;
				PreparedStatement psTempLogSequence;
				ResultSet rs;
				byte[] buf = new byte[1024];
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				try {
					     
					ctxTempLog = new InitialContext();
					dsTempLog = (DataSource)ctxTempLog.lookup("jdbc/dbSCMC");
					cnTempLog = dsTempLog.getConnection();
					 
					//String query="SELECT CONTENIDO FROM PV_SWB.TECTARCRED WHERE NUMTAR = ? ";
					String query= "select " +
								  "f50_documento  " +
								  "from bn_scmc.bnecf50_estadotarjcredito " +
								  "where f50_fecha_emision=? " +
								  "and f50_num_documento= ? ";
								
					
					psTempLogSequence = cnTempLog.prepareStatement(query);
					psTempLogSequence.setString(1,periodo);
					psTempLogSequence.setString(2,numDocumento);
					
					rs = psTempLogSequence.executeQuery();
					
				
					if (rs.next()){
						
						Blob valor = rs.getBlob(1);
						
						if(valor !=null){
							InputStream insStream = valor.getBinaryStream();
							int n = 0;
							while((n= insStream.read(buf))>=0){
								baos.write(buf,0,n);
							}
							insStream.close();
							
						}						
				
//						Enviar el OuputStream
						byte[] bytes = baos.toByteArray();
						FileCopyUtils.copy(bytes,contentStream);						
					}
						
										
					if(rs!=null) { rs.close();rs=null;}
			        if (psTempLogSequence !=null) {psTempLogSequence.close();psTempLogSequence=null;}
			        if (cnTempLog !=null) {cnTempLog.close();cnTempLog=null;}
			        
			    	
					
				}catch(ArrayRuleException e){
					System.out.println("ERROR::::::::::::::::::::::::::::::::::::::::::::");
					e.printStackTrace();
					contentStream.close();
					
					throw new ArrayRuleException(ConstanteError.GENERICO,"Error en BD");
				 
				}
					
	}

 public void encontrarEstadoFtp( final String usuario,final String clave,final String servidor,final String remotePath, final OutputStream contentStream) throws Exception {
	
	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			URL url = new URL("ftp://" + usuario + ":" + clave + "@" + servidor + remotePath + ";type=i");
			URLConnection urlc = url.openConnection();
			InputStream is = urlc.getInputStream();
			
			byte[] buf = new byte[1024];
			
			int n = 0;
			while((n= is.read(buf))>=0){
				baos.write(buf,0,n);
			}
			is.close();

			//Enviar el OuputStream
			byte[] bytes = baos.toByteArray();
			FileCopyUtils.copy(bytes,contentStream);	
			contentStream.flush();
			contentStream.close();		
			} catch (Exception ex) {
				System.out.println("Error//////////////////////////////////");
				contentStream.flush();
				contentStream.close();	
				baos.reset(); 
				ex.printStackTrace();
				throw new ArrayRuleException(ConstanteError.GENERICO,"No se puede obtener el estado de cuenta");
						
			}
	
 	}
}
