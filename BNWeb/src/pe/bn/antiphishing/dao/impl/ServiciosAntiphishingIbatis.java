package pe.bn.antiphishing.dao.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;
import pe.bn.antiphishing.dao.ServiciosAntiphishingDAO;
import pe.cosapi.common.DAOAbstract;

public class ServiciosAntiphishingIbatis extends DAOAbstract implements ServiciosAntiphishingDAO{

	public String getValidaIP(String ip) throws Exception {
		Context ctxTempLog;
		DataSource dsTempLog;
		Connection cnTempLog = null;
		PreparedStatement psTempLogSequence;
		CallableStatement callableStatement = null;
	
		String desFlag 	= "";
		String flag		= "";
		
		try {
			ctxTempLog = new InitialContext();
			dsTempLog = (DataSource)ctxTempLog.lookup("jdbc/dbSWB");
			cnTempLog = dsTempLog.getConnection();
			
			
			String sql = "{call PV_SWB.BNPKG_SATI_CONSULTA_IP.BNSP_CONSULTA_IP(?,?,?)}";
    	    callableStatement = cnTempLog.prepareCall(sql);       
    	  
    	    callableStatement.setString(1, ip);
    	    callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
    	    callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
    	    
   
    	    callableStatement.execute();
    	   
    	    flag = callableStatement.getString(2);
    	    desFlag = callableStatement.getString(3);
			
            
        } catch (Exception e) {
            e.printStackTrace();
        	if (callableStatement != null) {
        		callableStatement.close();
        		callableStatement = null;
			}
			;
			
			if (cnTempLog != null) {
				cnTempLog.close();
				cnTempLog = null;
			}
        } finally {
			try {
				if (callableStatement != null) {
					callableStatement.close();
					callableStatement = null;
				}
				;
				
				if (cnTempLog != null) {
					cnTempLog.close();
					cnTempLog = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
		return flag;
	}
	
	public String getOpcionAntiphishing(String opcion) throws Exception{
		
		Context ctxTempLog;
		DataSource dsTempLog;
		Connection cnTempLog = null;
		PreparedStatement psTempLogSequence;
		CallableStatement callableStatement = null;
	
	
		String estado		= "";
		
		try {
			ctxTempLog = new InitialContext();
			dsTempLog = (DataSource)ctxTempLog.lookup("jdbc/dbSWB");
			cnTempLog = dsTempLog.getConnection();
			
			
			String sql = "{call PV_SWB.BNPKG_SATI_CONSULTA_IP.BNSP_CONSULTA_OPCION(?,?)}";
    	    callableStatement = cnTempLog.prepareCall(sql);       
    	  
    	    callableStatement.setString(1, opcion);
    	    callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
   
    	    callableStatement.execute();
    	   
    	    estado = callableStatement.getString(2);
    	    
			
            
        } catch (Exception e) {
            e.printStackTrace();
        	if (callableStatement != null) {
        		callableStatement.close();
        		callableStatement = null;
			}
			;
			
			if (cnTempLog != null) {
				cnTempLog.close();
				cnTempLog = null;
			}
        } finally {
			try {
				if (callableStatement != null) {
					callableStatement.close();
					callableStatement = null;
				}
				;
				
				if (cnTempLog != null) {
					cnTempLog.close();
					cnTempLog = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
		return estado;
	}

}
