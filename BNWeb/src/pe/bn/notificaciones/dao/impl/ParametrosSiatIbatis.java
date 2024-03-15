package pe.bn.notificaciones.dao.impl;

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

import pe.bn.notificaciones.dao.ParametrosSiatDAO;
import pe.bn.notificaciones.dao.TransaccionDAO;
import pe.bn.notificaciones.model.ParametrosSIAT;
import pe.cosapi.common.DAOAbstract;

public class ParametrosSiatIbatis extends DAOAbstract implements ParametrosSiatDAO{

	public ParametrosSIAT getParametrosSiat(String tarjeta) throws Exception {
		Context ctxTempLog;
		DataSource dsTempLog;
		Connection cnTempLog = null;
		PreparedStatement psTempLogSequence;
		ResultSet rs = null;
		int monto_max=0;
		ParametrosSIAT registro = null;
		try {
			ctxTempLog = new InitialContext();
			dsTempLog = (DataSource)ctxTempLog.lookup("jdbc/siatoracle");
			cnTempLog = dsTempLog.getConnection();
			
			CallableStatement callableStatement = null;
			String getDBUSERCursorSql = "{call BN_SIAT.BNPKG_SIAT_PARAMETROS_SATI.BNSP_OBTENER_PARAMETROS(?,?,?)}";
    	    callableStatement = cnTempLog.prepareCall(getDBUSERCursorSql);       

    	    callableStatement.setString(1, tarjeta);
    	    callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
    	    callableStatement.registerOutParameter(3, java.sql.Types.INTEGER);
    	    
   
    	    callableStatement.execute();
    	    rs = (ResultSet) callableStatement.getObject(2);
    	    monto_max = callableStatement.getInt(3);
    	    
    	    registro = new ParametrosSIAT();
    	    
			while (rs.next()){   
            	
            	registro.setNotioperacion(rs.getString(1));
            	registro.setTiponoti(rs.getInt(2));	     
            	registro.setEmail(rs.getString(3));
            	registro.setTelefono(rs.getString(4));
            	registro.setIdtelefono(rs.getString(5));
            	registro.setNtarjeta(rs.getString(6));            	
            }
		
			Integer temp = new Integer(monto_max); 
			if( temp != null ){ 
				registro.setImporte(new BigDecimal(monto_max));
			}
			
            rs.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        	if (rs != null) {
				rs.close();
				rs = null;
			}
			;
			
			if (cnTempLog != null) {
				cnTempLog.close();
				cnTempLog = null;
			}
        } finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
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
		return registro;
	}

}
