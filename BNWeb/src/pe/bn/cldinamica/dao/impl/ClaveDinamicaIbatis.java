package pe.bn.cldinamica.dao.impl;


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

import pe.bn.cldinamica.dao.ClaveDinamicaDAO;
import pe.bn.cldinamica.domain.ParametrosCldi;
import pe.bn.tcredito.dao.TarjetaCreditoDAO;
import pe.bn.tcredito.domain.Formato;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ObjectUtil;

import pe.cosapi.common.DAOAbstract;



public class ClaveDinamicaIbatis extends DAOAbstract implements ClaveDinamicaDAO  {
	
	private static LoggerSati log3 = LoggerSati.getInstance(ClaveDinamicaIbatis.class.getName());
	
		
	

		 public List obtenerListaBinesMedioAutenticacion( String estado) throws Exception {
			  	
			 	
			 	List lstResult = new ArrayList();
				Context ctxTempLog;
				DataSource dsTempLog;
				Connection cnTempLog = null;
				PreparedStatement psTempLogSequence;
				ResultSet rs;
				
				try {
					     
					
					ctxTempLog = new InitialContext();
					dsTempLog = (DataSource)ctxTempLog.lookup("jdbc/cldioracle");
					cnTempLog = dsTempLog.getConnection();
					 
				
					String query= 	"  select " +
										"bin_ma, " +
										"nombre, " +
										"estado " +
									" from bn_cldi_act.tp_bin_medio_autenticacion "+
									"where estado = ? ";

					
					psTempLogSequence = cnTempLog.prepareStatement(query);
					psTempLogSequence.setString(1,estado);
					
					rs = psTempLogSequence.executeQuery();
					
				
					while (rs.next()){   
						ParametrosCldi parametros = new ParametrosCldi();
						
						parametros.setCodBinMA(rs.getString(1));
						parametros.setNombreBinMA(rs.getString(2));
						
						lstResult.add(parametros);
								
					}
						
										
					if(rs!=null) { rs.close();rs=null;}
			        if (psTempLogSequence !=null) {psTempLogSequence.close();psTempLogSequence=null;}
			        if (cnTempLog !=null) {cnTempLog.close();cnTempLog=null;}
			        
			    	
			   
					
				}catch(Exception e){
					e.printStackTrace();
				 
				}
				return lstResult;
					
	}

		 public List verificarExistenciaMedioAutenticacion( String binVirtual, String codigoMA) throws Exception {
			  	
			 	
			 	List lstResult = new ArrayList();
				Context ctxTempLog;
				DataSource dsTempLog;
				Connection cnTempLog = null;
				PreparedStatement psTempLogSequence;
				ResultSet rs;
				
				try {
					     
					
					ctxTempLog = new InitialContext();
					dsTempLog = (DataSource)ctxTempLog.lookup("jdbc/cldioracle");
					cnTempLog = dsTempLog.getConnection();
					 
				
					String query= 	" select " +
										"cod_medio, " +
										"bin_ma, " +
										"estado "+
									" from bn_cldi_act.tp_medio_autenticacion "+
									" where cod_medio= ? and bin_ma = ? ";

					
					psTempLogSequence = cnTempLog.prepareStatement(query);
					psTempLogSequence.setString(1,codigoMA);
					psTempLogSequence.setString(2,binVirtual);
					
					rs = psTempLogSequence.executeQuery();
					
				
					while (rs.next()){   
						ParametrosCldi parametros = new ParametrosCldi();
						
						parametros.setCodMedioAutenticacion(rs.getString(1));
						parametros.setCodBinMA(rs.getString(2));
						parametros.setEstadoMedioAutenticacion(rs.getString(3));
						lstResult.add(parametros);
								
					}
						
										
					if(rs!=null) { rs.close();rs=null;}
			        if (psTempLogSequence !=null) {psTempLogSequence.close();psTempLogSequence=null;}
			        if (cnTempLog !=null) {cnTempLog.close();cnTempLog=null;}
			        
			    	
			   
					
				}catch(Exception e){
					e.printStackTrace();
				 
				}
				return lstResult;
					
	}
		 
		 public List obtenerListaElementoSeguridadRelacionados( String binVirtual, String codigoMA) throws Exception {
			  	
			 	
			 	List lstResult = new ArrayList();
				Context ctxTempLog;
				DataSource dsTempLog;
				Connection cnTempLog = null;
				PreparedStatement psTempLogSequence;
				ResultSet rs;
				
				try {
					     
					
					ctxTempLog = new InitialContext();
					dsTempLog = (DataSource)ctxTempLog.lookup("jdbc/cldioracle");
					cnTempLog = dsTempLog.getConnection();
					 
				
					String query= 	"select " +
										"cod_medio, " +
										"id_primario_elemento_seg, " +
										"id_secundario_elemento_seg, " +
										"tipo_elemento_seguridad, " +
										"estado "+
									"from bn_cldi_act.tr_med_aut_ele_seg "+
									"where bin_ma = ? and cod_medio= ? ";

					
					psTempLogSequence = cnTempLog.prepareStatement(query);
					psTempLogSequence.setString(1,binVirtual);
					psTempLogSequence.setString(2,codigoMA);
					
					
					rs = psTempLogSequence.executeQuery();
					
				
					while (rs.next()){   
						ParametrosCldi parametros = new ParametrosCldi();
						
						parametros.setCodMedioAutenticacion(rs.getString(1));
						parametros.setIdPrimarioES(rs.getString(2));
						parametros.setIdSecundarioES(rs.getString(3));
						parametros.setTipoES(rs.getString(4));
						parametros.setEstadoES_MA(rs.getString(5));
		
						lstResult.add(parametros);
								
					}
													
					if(rs!=null) { rs.close();rs=null;}
			        if (psTempLogSequence !=null) {psTempLogSequence.close();psTempLogSequence=null;}
			        if (cnTempLog !=null) {cnTempLog.close();cnTempLog=null;}
			     
					
				}catch(Exception e){
					e.printStackTrace();
				 
				}
				return lstResult;
					
	}
}
