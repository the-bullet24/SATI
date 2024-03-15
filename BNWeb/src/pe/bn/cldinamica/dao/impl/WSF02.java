package pe.bn.cldinamica.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.bn.cldinamica.dao.afiliacionDAO;
import pe.bn.pago.dao.PagoDAO;
import pe.bn.pago.domain.impl.PagoTasasImpl;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.ComboUtil;
import pe.cosapi.common.ConnectionPool;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.DAOAbstract;
import pe.cosapi.domain.Usuario;
import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;

public class WSF02 extends DAOAbstract implements afiliacionDAO {
	
	ConnectionPool pool= null;
	Connection conn = null;

	
	public Integer insertAfiliacion(Afiliacion afiliacion, Usuario usuario, Integer idAfil, String ip, String claveValid) throws Exception{
		PreparedStatement stmt = null;
		Integer rpta =null;
		
	    try {


		} catch (Exception e) {
			e.printStackTrace();
			rpta = 1;
			throw e;
			
		} finally {
            try {
            
                //System.out.println("***********"+pool.ConnectionsInfo());
				if (pool != null) {	pool.free(conn);	}
			} catch (Exception e) {	}
			
        }
		
		return rpta;
	}
	public String mostrarIdBNWSF02() throws Exception{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String id="";	
				    
		try{	

					
		}catch(Exception e){
			e.printStackTrace();
			throw e;
			
	   }finally{
			
			if(rs!=null){rs.close();rs= null;};
			if(stmt !=null){stmt.close();stmt = null;};	
			if (pool != null) {	pool.free(conn);}
	  
	   }
	   return id;
  }

	public List consultarBNWSF02(String numTarjeta) throws SQLException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List lstResult = new ArrayList();
				    
		try{	
			
		}finally{
			
			if(rs!=null){rs.close();rs= null;};
			if(stmt !=null){stmt.close();stmt = null;};	
			if (pool != null) {	pool.free(conn);}
	  
	   }
	   return lstResult;
  }

}
