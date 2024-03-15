package pe.bn.notificaciones.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import pe.bn.notificaciones.dao.TransaccionDAO;
import pe.bn.tcredito.domain.Formato;
import pe.cosapi.common.DAOAbstract;
import pe.cosapi.common.ObjectUtil;

public class TransaccionIbatis extends DAOAbstract implements TransaccionDAO{

	public String getDescripcionCorta(String cod) throws Exception {
		Context ctxTempLog;
		DataSource dsTempLog;
		Connection cnTempLog = null;
		PreparedStatement psTempLogSequence;
		ResultSet rs;
		String descripcion ="";
		try {
			     
			
			ctxTempLog = new InitialContext();
			dsTempLog = (DataSource)ctxTempLog.lookup("jdbc/dbJT");
			cnTempLog = dsTempLog.getConnection();
			 
		
			String query= 	"select descorta from bn_swb_log.swb_transacciones where tipotra = 'F' and GRUPO = ?";

			psTempLogSequence = cnTempLog.prepareStatement(query);
			psTempLogSequence.setString(1,cod);
			
			rs = psTempLogSequence.executeQuery();
			
		
			while (rs.next()){   
				descripcion = rs.getString(1);
			}
				
								
			if(rs!=null) { rs.close();rs=null;}
	        if (psTempLogSequence !=null) {psTempLogSequence.close();psTempLogSequence=null;}
	        if (cnTempLog !=null) {cnTempLog.close();cnTempLog=null;}
	        
	    	
	   
			
		}catch(Exception e){
			e.printStackTrace();
		 
		}
		return descripcion;
	}

}
