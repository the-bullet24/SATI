/*
 * Creado el 27/04/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import pe.cosapi.common.DAOAbstract;
import pe.cosapi.dao.ItfDAO;
import pe.cosapi.dao.SabmDAO;
import pe.cosapi.domain.GeneracionLog;
import pe.cosapi.domain.GeneracionLogResultado;
import pe.cosapi.domain.Itf;
import pe.cosapi.domain.impl.GeneracionLogImpl;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class SabmIbatis extends DAOAbstract implements SabmDAO {

	
	@Override
	public GeneracionLog getGeneracionLog(GeneracionLog generacionLog)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
				//(GeneracionLog)getSqlMapClientTemplate().queryForObject("sqlMapSABM.getGeneracionLog",generacionLog);	
		
	}

	@Override
	public GeneracionLogResultado insertGeneracionLog(GeneracionLogImpl generacionLogImpl)
			throws Exception {
		// TODO Auto-generated method stub
		
		return null;
				//(GeneracionLogResultado) getSqlMapClientTemplate().insert("sqlMapSABM.insertGeneraLog",generacionLogImpl);
		
	}

	@Override
	public int insertLog(GeneracionLogImpl generacionLogImpl) throws Exception {
		// TODO Auto-generated method stub
		
		int valor=0;
		
		try {		
				//MGL
				Connection conn = this.getConnection();
		
				// Prepare a call to the stored procedure
				String storedProcedureCall = "{call BMPKG_GENERACION_CLAVE_SEIS.BMSP_REGISTRAR_LOG_GENERACION(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		        CallableStatement callableStatement = conn.prepareCall(storedProcedureCall);
		
		        
		        // Set input parameters
	            callableStatement.setDate(1, (new java.sql.Date(generacionLogImpl.getP_F32_FECHA_CRONO_OP().getTime())));
	            callableStatement.setString(2, generacionLogImpl.getP_F32_CANAL());
	            callableStatement.setString(3, generacionLogImpl.getP_F32_IP_DIRECCION());
	            callableStatement.setString(4, generacionLogImpl.getP_F32_MAC());
	            callableStatement.setString(5, generacionLogImpl.getP_F32_NUM_TRANS());
	            callableStatement.setLong(6, generacionLogImpl.getP_F32_CIC_CLTE());
	            callableStatement.setString(7, generacionLogImpl.getP_F32_TIPO_DOCUMENTO());
	            callableStatement.setString(8, generacionLogImpl.getP_F32_NUM_DOCUMENTO());
	            callableStatement.setString(9, generacionLogImpl.getP_F32_TIPO_TARJETA());
	            callableStatement.setString(10, generacionLogImpl.getP_F32_NUM_TARJETA());
	            callableStatement.setString(11, generacionLogImpl.getP_F32_ESTADO());
	            callableStatement.setString(12, generacionLogImpl.getP_F32_FLAG_ERROR());
	            callableStatement.setString(13, generacionLogImpl.getP_F32_COD_APP());
	            callableStatement.setString(14, generacionLogImpl.getP_F32_COD_RET());
	            callableStatement.setString(15, generacionLogImpl.getP_F32_TOKEN());
	            callableStatement.setString(16, generacionLogImpl.getP_F32_EMAIL_OUT());
	            callableStatement.setString(17, generacionLogImpl.getP_F32_BENEF_OUT());
	            callableStatement.setString(18, generacionLogImpl.getP_F32_TIPO_TOKEN());

	            // Register the OUT parameter
	            callableStatement.registerOutParameter(19, java.sql.Types.NUMERIC);
	            callableStatement.registerOutParameter(20, java.sql.Types.VARCHAR);
	            callableStatement.registerOutParameter(21, java.sql.Types.VARCHAR);

	            // Execute the stored procedure
	            callableStatement.execute();

	            // Retrieve the result from the OUT parameter
	            int result1 = callableStatement.getInt(19);
	            String result2 = callableStatement.getString(20);
	            String result3 = callableStatement.getString(21);
	            
	            valor=result1;

	            // Process the result
	            System.out.println("Output Parameter result1: " + result1);
	            System.out.println("Output Parameter result2: " + result2);
	            System.out.println("Output Parameter result3: " + result3);

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
		return valor; 
         
         
	}

	@Override
	public void updateLog(GeneracionLogImpl generacionLogImpl) throws Exception {
		// TODO Auto-generated method stub
		try {		
			
			Connection conn = this.getConnection();
	
			// Prepare a call to the stored procedure
			String storedProcedureCall = "{call BMPKG_GENERACION_CLAVE_SEIS.BMSP_ACTUALIZAR_LOG_GENERACION(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
	        CallableStatement callableStatement = conn.prepareCall(storedProcedureCall);
	
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        // Set input parameters
	        callableStatement.setLong(1, generacionLogImpl.getP_F32_NUMLOG());
	        callableStatement.setString(2, sdf.format((new java.sql.Date(generacionLogImpl.getP_F32_FECHA_CRONO_OP().getTime()))));
            callableStatement.setString(3, generacionLogImpl.getP_F32_FLAG_ERROR());
            callableStatement.setString(4, generacionLogImpl.getP_F32_COD_APP());
            callableStatement.setString(5, generacionLogImpl.getP_F32_COD_RET()); 
            callableStatement.setString(6, generacionLogImpl.getP_F32_BENEF_OUT());            
            callableStatement.setString(7, generacionLogImpl.getP_F32_FECHA_LOGICA());
            callableStatement.setString(8, generacionLogImpl.getP_F32_CONSTANCIA_SATI());

            // Register the OUT parameter

            callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(10, java.sql.Types.VARCHAR);

            // Execute the stored procedure
            callableStatement.execute();

            // Retrieve the result from the OUT parameter
           
            String result2 = callableStatement.getString(9);
            String result3 = callableStatement.getString(10);

            // Process the result
          
            System.out.println("Output Parameter result2: " + result2);
            System.out.println("Output Parameter result3: " + result3);

        } catch (SQLException e) {
            e.printStackTrace();
        } 
	}
	
	
	

}
