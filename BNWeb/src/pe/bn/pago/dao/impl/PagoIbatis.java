/*
 * Creado el 19/06/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.pago.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.pago.dao.PagoDAO;
import pe.bn.pago.domain.PagoTasas;
import pe.bn.pago.domain.impl.PagoTasasImpl;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.ComboUtil;
import pe.cosapi.common.ConnectionPool;
import pe.cosapi.common.Constante;
import pe.cosapi.common.DAOAbstract;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Limit;
import pe.cosapi.domain.impl.LimitImpl;

/**
 * @author cosapi_ati02
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class PagoIbatis extends DAOAbstract implements PagoDAO {
	private static LoggerSati log3 = LoggerSati.getInstance(PagoIbatis.class.getName());

	ConnectionPool pool= null;
	Connection conn = null;
	public BigDecimal getImporteTasa(PagoTasasImpl pago) throws Exception{
		
		
	    try {
			
			
				if(pool ==null)
					pool= new ConnectionPool();	
			    conn = pool.getConnection();
						
			PreparedStatement pstmt = conn.prepareStatement("		" +
					"SELECT" +
					"		A.F02_CTRIBUTO tributo," +
					"		A.F02_SIMPO_VALOR importe," +
					"		A.F02_ATRIBUTO dettributo," +
					"		A.F02_CADM_TRIB," +
					"		B.F04_AADM_TRIB" +
					"		FROM" +
					"		NACION.BNREF02 A," +
					"			NACION.BNREF04 B" +
					"		WHERE" +
					"			A.F02_CADM_TRIB = B.F04_CADM_TRIB" +
					"		AND A.F02_CTRIBUTO = " + String.valueOf(Integer.parseInt(pago.getTributo().getCodigo()))+				
					"		ORDER BY" +
					"		A.F02_CTRIBUTO desc , A.F02_ATRIBUTO desc");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				return new BigDecimal(rs.getString(2));
			}
			/*** comentar todo el try para certificacion**/
			
			//try{
				rs.close();
				pstmt.close();
				//conn.close();
			//}catch (Exception e) {
				
			//}

			/*** fin de comentar el try **/
		return null;
		} catch (NumberFormatException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} finally {
            try {
                //System.out.println("***********"+pool.ConnectionsInfo());
				if (pool != null) {	pool.free(conn);	}
			} catch (Exception e) {
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				
			}
			
        }
		
	}
	
	public List getListaImportesTasa(String codTributo, Cuenta cuenta) throws Exception{
		
	    List lstResult = new ArrayList();
	    
	    try {
	    	
	    //Limite segun cuenta AHMN para no mostrar tasas cuyos montos superan el limite establecido
	  	  Limit limit = new LimitImpl();
	  	  	
		    limit.setTipoCuenta(Constante.COD_CUENTA_AHORROS_MN);
		    LimitImpl lim = getLimiteByLimt(limit);
		   
		    String montoMaximo = ""+Integer.parseInt(lim.getImporteMaximo().toString());
	        PreparedStatement pstmt = null;
			//2 certificacion, 3 produccion
			//if (Constante.CONSTANTE_OS.equals("2") || Constante.CONSTANTE_OS.equals("3")){
				//System.out.println("ENTRO AL DATACOM DEL CONNECTIONPOOL");
				if(pool ==null)
					pool= new ConnectionPool();	
			    conn = pool.getConnection();
			ComboUtil combo = new ComboUtil();

			if (codTributo.equals("03670")){
			    pstmt = conn.prepareStatement("		" +
			            "SELECT NACION.BNREF02.F02_CTRIBUTO, NACION.BNREF02.F02_ATRIBUTO, " +
			            "NACION.BNREF02.F02_SVALOR AS IMPORTE, NACION.BNREF02.F02_SIMP_VALOR1, " +
			            "NACION.BNREF02.F02_SIMP_VALOR2 " +
			            "From NACION.BNREF02 Where F02_CTRIBUTO = " + String.valueOf(Integer.parseInt(codTributo))); 
			}else if (codTributo.equals("09970")){
			    pstmt = conn.prepareStatement("		" +
			            "SELECT NACION.BNREF02.F02_CTRIBUTO, NACION.BNREF02.F02_ATRIBUTO, " +
			            "NACION.BNREF02.F02_SVALOR AS IMPORTE, NACION.BNREF02.F02_SIMP_VALOR1, " +
			            "NACION.BNREF02.F02_SIMP_VALOR2 " + 
			            "From NACION.BNREF02 Where F02_CTRIBUTO = " + String.valueOf(Integer.parseInt(codTributo)));
			    
			}else if (codTributo.equals("08991")){
			    pstmt = conn.prepareStatement("		" +
			            "SELECT NACION.BNREF02.F02_CTRIBUTO, NACION.BNREF02.F02_ATRIBUTO, " +
			            "NACION.BNREF02.F02_SVALOR AS IMPORTE, NACION.BNREF02.F02_SIMP_VALOR1, " +
			            "NACION.BNREF02.F02_SIMP_VALOR2 " + 
			            "From NACION.BNREF02 Where F02_CTRIBUTO = " + String.valueOf(Integer.parseInt(codTributo)));
			}else if (codTributo.equals("09996")){
			    pstmt = conn.prepareStatement("		" +
			    		"SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			}else if (codTributo.equals("01857")){
			    pstmt = conn.prepareStatement("		" +
			    		"SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			}else if (codTributo.equals("01806")){
			    pstmt = conn.prepareStatement("		" +
			    		"SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			}else if (codTributo.equals("01792")){
			    pstmt = conn.prepareStatement("		" +
			    		"SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			}else if (codTributo.equals("01873")){
			    pstmt = conn.prepareStatement("		" +
			    		"SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			}else if (codTributo.equals("01814")){
			    pstmt = conn.prepareStatement("		" +
			    		"SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			}else if (codTributo.equals("07900")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			}else if (codTributo.equals("01775")){
			    pstmt = conn.prepareStatement("		" +		
			            "SELECT NACION.BNREF02.F02_CTRIBUTO, NACION.BNREF02.F02_ATRIBUTO, " +
			            "NACION.BNREF02.F02_SVALOR AS IMPORTE, NACION.BNREF02.F02_SIMP_VALOR1, " + 
			            "NACION.BNREF02.F02_SIMP_VALOR2 " + 
			            "From NACION.BNREF02 Where F02_CTRIBUTO = " + String.valueOf(Integer.parseInt(codTributo)));
			}else if (codTributo.equals("01800")){
			    pstmt = conn.prepareStatement("		" +
			            "SELECT NACION.BNREF02.F02_CTRIBUTO, NACION.BNREF02.F02_ATRIBUTO, " +
			            "NACION.BNREF02.F02_SVALOR AS IMPORTE, NACION.BNREF02.F02_SIMP_VALOR1, " +
			            "NACION.BNREF02.F02_SIMP_VALOR2 " + 
			            "From NACION.BNREF02 Where F02_CTRIBUTO = " + String.valueOf(Integer.parseInt(codTributo)));
			}else if (codTributo.equals("00044")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE FROM  NACION.BNSUF13 " +
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			}else if (codTributo.equals("09040")){
			    pstmt = conn.prepareStatement("		" +	
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE FROM  NACION.BNSUF13 " +  
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			}
			else if (codTributo.equals("07307")){
		    pstmt = conn.prepareStatement("		" +			    
		            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
		            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
		            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
		            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
		    }
			else if (codTributo.equals("07439")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			    }
			else if (codTributo.equals("08214")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			    }

			else if (codTributo.equals("00116")){
			    pstmt = conn.prepareStatement("		" +
			            "SELECT NACION.BNREF02.F02_CTRIBUTO, NACION.BNREF02.F02_ATRIBUTO, " +
			            "NACION.BNREF02.F02_SVALOR AS IMPORTE, NACION.BNREF02.F02_SIMP_VALOR1, " +
			            "NACION.BNREF02.F02_SIMP_VALOR2 " + 
			            "From NACION.BNREF02 Where F02_CTRIBUTO = " + String.valueOf(Integer.parseInt(codTributo)));
			    }

			else if (codTributo.equals("07609")){
		    pstmt = conn.prepareStatement("		" +			    
		            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
		            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
		            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
		            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
		 }
			else if (codTributo.equals("07625")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			 }
			else if (codTributo.equals("07684")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			 }
			else if (codTributo.equals("07463")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			 }
			else if (codTributo.equals("07471")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			 }
			else if (codTributo.equals("07668")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			 }
			else if (codTributo.equals("07447")){
			    pstmt = conn.prepareStatement("		" +
			            "SELECT NACION.BNREF02.F02_CTRIBUTO, NACION.BNREF02.F02_ATRIBUTO, " +
			            "NACION.BNREF02.F02_SVALOR AS IMPORTE, NACION.BNREF02.F02_SIMP_VALOR1, " +
			            "NACION.BNREF02.F02_SIMP_VALOR2 " + 
			            "From NACION.BNREF02 Where F02_CTRIBUTO = " + String.valueOf(Integer.parseInt(codTributo)));
			    }
			else if (codTributo.equals("02119")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			 }
			
			else if (codTributo.equals("00523")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			 }
			else if (codTributo.equals("00524")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			 }
			else if (codTributo.equals("00730")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			 }
			
			
			else if (codTributo.equals("00729")){
				
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			 }
			else if (codTributo.equals("02143")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			 }
			
			else if (codTributo.equals("00728")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			 }
			
			else if (codTributo.equals("06610")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			 }

			else if (codTributo.equals("06831")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			 }
			
			else if (codTributo.equals("08222")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%' and NACION.BNSUF13.F13_SMONTO <= "+ montoMaximo);
			 }
			
		
			
			else if (codTributo.equals("07935")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%' and NACION.BNSUF13.F13_SMONTO <= "+ montoMaximo);
			
			
		    }else if (codTributo.equals("02844")){
		    pstmt = conn.prepareStatement("		" +		
		            "SELECT NACION.BNREF02.F02_CTRIBUTO, NACION.BNREF02.F02_ATRIBUTO, " +
		            "NACION.BNREF02.F02_SVALOR AS IMPORTE, NACION.BNREF02.F02_SIMP_VALOR1, " + 
		            "NACION.BNREF02.F02_SIMP_VALOR2 " + 
		            "From NACION.BNREF02 Where F02_CTRIBUTO = " + String.valueOf(Integer.parseInt(codTributo)));
		   
	    	}else if (codTributo.equals("02879")){
		    pstmt = conn.prepareStatement("		" +		
		            "SELECT NACION.BNREF02.F02_CTRIBUTO, NACION.BNREF02.F02_ATRIBUTO, " +
		            "NACION.BNREF02.F02_SVALOR AS IMPORTE, NACION.BNREF02.F02_SIMP_VALOR1, " + 
		            "NACION.BNREF02.F02_SIMP_VALOR2 " + 
		            "From NACION.BNREF02 Where F02_CTRIBUTO = " + String.valueOf(Integer.parseInt(codTributo)));
		    
	    	}else if (codTributo.equals("08095")){
		    pstmt = conn.prepareStatement("		" +		
		        		    		
				    "SELECT NACION.BNREF02.F02_CTRIBUTO, NACION.BNREF02.F02_ATRIBUTO,"+
				    "NACION.BNREF02.F02_SVALOR AS IMPORTE "+
				    "From NACION.BNREF02 Where F02_CTRIBUTO =  "+ String.valueOf(Integer.parseInt(codTributo))+
				    "UNION  "+
				    "SELECT NACION.BNREF02.F02_CTRIBUTO, NACION.BNREF02.F02_ATRIBUTO, "+
				    "NACION.BNREF02.F02_SIMP_VALOR1  AS IMPORTE  "+
				    "From NACION.BNREF02 Where F02_CTRIBUTO = "+ String.valueOf(Integer.parseInt(codTributo))+
				    "UNION "+
				    "SELECT NACION.BNREF02.F02_CTRIBUTO, NACION.BNREF02.F02_ATRIBUTO,"+
				    "NACION.BNREF02.F02_SIMP_VALOR2  AS IMPORTE "+
				    "From NACION.BNREF02 Where F02_CTRIBUTO = "+ String.valueOf(Integer.parseInt(codTributo)));
		    
		    
			
    		}else if (codTributo.equals("08494")){
		    pstmt = conn.prepareStatement("		" +		
		        		    		
		    		 "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			
		    
		    
//    		}else if (codTributo.equals("05533")){
//		    pstmt = conn.prepareStatement("		" +		
//		        		    		
//		    		 "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
//			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
//			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
//			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
//			
//		    
//    		}else if (codTributo.equals("03964")){
//    		    pstmt = conn.prepareStatement("		" +		
//    		        		    		
//    		    		 "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
//    			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
//    			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
//    			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
//    			
//    		
//			}else if (codTributo.equals("05541")){
//		    pstmt = conn.prepareStatement("		" +		
//		        		    		
//		    		 "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
//			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
//			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
//			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
//					    
//			}else if (codTributo.equals("05991")){
//			    pstmt = conn.prepareStatement("		" +
//			            "SELECT NACION.BNREF02.F02_CTRIBUTO, NACION.BNREF02.F02_ATRIBUTO, " +
//			            "NACION.BNREF02.F02_SVALOR AS IMPORTE, NACION.BNREF02.F02_SIMP_VALOR1, " +
//			            "NACION.BNREF02.F02_SIMP_VALOR2 " + 
//			            "From NACION.BNREF02 Where F02_CTRIBUTO = " + String.valueOf(Integer.parseInt(codTributo)));
//			   
//			
//			}else if (codTributo.equals("06122")){
//		    pstmt = conn.prepareStatement("		" +		
//		        		    		
//		    		 "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
//			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
//			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
//			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
//			
//			}else if (codTributo.equals("06386")){
//			    pstmt = conn.prepareStatement("		" +
//			            "SELECT NACION.BNREF02.F02_CTRIBUTO, NACION.BNREF02.F02_ATRIBUTO, " +
//			            "NACION.BNREF02.F02_SVALOR AS IMPORTE, NACION.BNREF02.F02_SIMP_VALOR1, " +
//			            "NACION.BNREF02.F02_SIMP_VALOR2 " + 
//			            "From NACION.BNREF02 Where F02_CTRIBUTO = " + String.valueOf(Integer.parseInt(codTributo)));
			   
			
					   
			}else if (codTributo.equals("07374")){
		    pstmt = conn.prepareStatement("		" +
		    		 "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
		
		  }else if (codTributo.equals("07927")){
			    pstmt = conn.prepareStatement("		" +
			    		 "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
				            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
				            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
				            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			
			
		  }else if (codTributo.equals("07943")){
			    pstmt = conn.prepareStatement("		" +
			    		 "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
				            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
				            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
				            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			
			
		  }else if (codTributo.equals("07951")){
			    pstmt = conn.prepareStatement("		" +
			    		 "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
				            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
				            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
				            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%' and NACION.BNSUF13.F13_SMONTO <= "+ montoMaximo);
			
			
	    }else if (codTributo.equals("07978")){
		    pstmt = conn.prepareStatement("		" +
		    		 "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%' and NACION.BNSUF13.F13_SMONTO <= "+ montoMaximo);
		
		
	    }else if (codTributo.equals("08168")){
		    pstmt = conn.prepareStatement("		" +
		    		 "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%' and NACION.BNSUF13.F13_SMONTO <= "+ montoMaximo);
		
		
	    }else if (codTributo.equals("07234")){
		    pstmt = conn.prepareStatement("		" +
		    		 "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
		
		}else if (codTributo.equals("01810")){
		    pstmt = conn.prepareStatement("		" +		
		            "SELECT NACION.BNREF02.F02_CTRIBUTO, NACION.BNREF02.F02_ATRIBUTO, " +
		            "NACION.BNREF02.F02_SVALOR AS IMPORTE, NACION.BNREF02.F02_SIMP_VALOR1, " + 
		            "NACION.BNREF02.F02_SIMP_VALOR2 " + 
		            "From NACION.BNREF02 Where F02_CTRIBUTO = " + String.valueOf(Integer.parseInt(codTributo)));
		   
		  }else if (codTributo.equals("06662")){
			    pstmt = conn.prepareStatement("		" +
			    		 "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
				            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
				            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
				            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			
		  }else if (codTributo.equals("00521")){
				    pstmt = conn.prepareStatement("		" +			    
				            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
				            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
				            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
				            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
				 			
			
		  }else if (codTributo.equals("00522")){
		    pstmt = conn.prepareStatement("		" +			    
		            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
		            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
		            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
		            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
		 			
	
		  }else if (codTributo.equals("00525")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
			 			
		
		  }else if (codTributo.equals("00530")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
		    
		 
		  }else if (codTributo.equals("06666")){
		    pstmt = conn.prepareStatement("		" +		
		            "SELECT NACION.BNREF02.F02_CTRIBUTO, NACION.BNREF02.F02_ATRIBUTO, " +
		            "NACION.BNREF02.F02_SVALOR AS IMPORTE, NACION.BNREF02.F02_SIMP_VALOR1, " + 
		            "NACION.BNREF02.F02_SIMP_VALOR2 " + 
		            "From NACION.BNREF02 Where F02_CTRIBUTO = " + String.valueOf(Integer.parseInt(codTributo)));
		   
		  
			
	      }else if (codTributo.equals("01601")){
	    	   pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO AS ELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
		 
	      }else if (codTributo.equals("01602")){
	    	   pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO AS ELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
		  
			
	     }else if (codTributo.equals("00529")){
	    	   pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO AS ELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            "BNSUF13.F13_CELEMENTO LIKE '" + codTributo + "%'");
		  }

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){   
			    combo = new ComboUtil();
			    
				combo.setCodigo(rs.getString("IMPORTE"));
				combo.setDescripcion(rs.getString("IMPORTE"));
				
				lstResult.add(combo);
			}
			rs.close();
			pstmt.close();	
			conn.close();
			/*** comentar todo el try para certificacion**/
			
			//try{
				//rs.close();
				//pstmt.close();
				//conn.close();
			//}catch (Exception e) {
				
			//}

			/*** fin de comentar el try **/
				return lstResult;
		} catch (NumberFormatException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			conn.close();
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			conn.close();
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			conn.close();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} finally {
            try {
                //System.out.println("***********"+pool.ConnectionsInfo());
				if (pool != null) {	pool.free(conn);	}
			} catch (Exception e) {
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			}
			
        }
		
	}
	
	public List getListaDistritos(String codTributo) throws Exception{
		
	    List lstResult = new ArrayList();
	    try {
			
			//2 certificacion, 3 produccion
			//if (Constante.CONSTANTE_OS.equals("2") || Constante.CONSTANTE_OS.equals("3")){
				//System.out.println("ENTRO AL DATACOM DEL CONNECTIONPOOL");
				if(pool ==null)
					pool= new ConnectionPool();	
			    conn = pool.getConnection();
			/*}
			else if (Constante.CONSTANTE_OS.equals("1")){
			    conn = this.getDatacomConnection();
			}*/
			
			PreparedStatement pstmt = conn.prepareStatement("		" +
					"SELECT" +
					"		A.F02_CTRIBUTO tributo," +
					"		A.F02_SIMPO_VALOR importe," +
					"		A.F02_ATRIBUTO dettributo," +
					"		A.F02_CADM_TRIB," +
					"		B.F04_AADM_TRIB" +
					"		FROM" +
					"		NACION.BNREF02 A," +
					"			NACION.BNREF04 B" +
					"		WHERE" +
					"			A.F02_CADM_TRIB = B.F04_CADM_TRIB" +
					"		AND A.F02_CTRIBUTO = " + String.valueOf(Integer.parseInt(codTributo))+				
					"		ORDER BY" +
					"		A.F02_CTRIBUTO desc , A.F02_ATRIBUTO desc");
			
			ResultSet rs = pstmt.executeQuery();

			ComboUtil combo = new ComboUtil();
			combo.setCodigo("");
			combo.setDescripcion(Constante.SELECCIONE);	
			
			lstResult.add(0, combo);	
			combo = new ComboUtil();
			combo.setCodigo("01");
			combo.setDescripcion("DIST.JUD.DE AMAZONAS");				
			lstResult.add(combo);
			combo.setCodigo("02");
			combo.setDescripcion("DIST.JUD.DE APURIMAC");				
			lstResult.add(combo);
			combo.setCodigo("03");
			combo.setDescripcion("DIST.JUD.DE AREQUIPA");				
			lstResult.add(combo);
			
			while(rs.next()){
				combo = new ComboUtil();
				combo.setCodigo(rs.getString(1));
				combo.setDescripcion(rs.getString(2));		
				lstResult.add(0, combo);				    
			}
			
			/*** comentar todo el try para certificacion**/
			
			//try{
				rs.close();
				pstmt.close();
				conn.close();
				//conn.close();
			//}catch (Exception e) {
				
			//}

			/*** fin de comentar el try **/
				return lstResult;
		} catch (NumberFormatException e) {
			conn.close();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			conn.close();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			conn.close();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} finally {
            try {
                //System.out.println("***********"+pool.ConnectionsInfo());
				if (pool != null) {	pool.free(conn);	}
			} catch (Exception e) {
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			}
			
        }
		
	}
	

	public List getListaDistritosJudiciales() throws Exception{
	    List lstResult = new ArrayList();
	    try {
			
			//2 certificacion, 3 produccion
			//if (Constante.CONSTANTE_OS.equals("2") || Constante.CONSTANTE_OS.equals("3")){
				//System.out.println("ENTRO AL DATACOM DEL CONNECTIONPOOL");
				if(pool ==null)
					pool= new ConnectionPool();	
			    conn = pool.getConnection();
			/*}
			else if (Constante.CONSTANTE_OS.equals("1")){
			    conn = this.getDatacomConnection();
			}*/
			
			PreparedStatement pstmt = conn.prepareStatement("		" +
					"SELECT" +
					"		NACION.BNREF29.F29_CDEPENDENCIA," +
					"		NACION.BNREF29.F29_CUBIGEO, NACION.BNREF29.F29_ADEP_JUDICIAL" +
					"	FROM" +
					"		NACION.BNREF29");
			
			ResultSet rs = pstmt.executeQuery();

			ComboUtil combo = new ComboUtil();
			combo.setCodigo("");
			combo.setDescripcion(Constante.SELECCIONE);	
					
			while(rs.next()){
			    if (rs.getString(3)!=null){
				    combo = new ComboUtil();
					combo.setCodigo(rs.getString(1));
					combo.setDescripcion(rs.getString(3));
					
				    lstResult.add(combo); 
			    }
			}
			
			/*** comentar todo el try para certificacion**/
			
			//try{
				rs.close();
				pstmt.close();
				conn.close();
				//conn.close();
			//}catch (Exception e) {
				
			//}

			/*** fin de comentar el try **/
				return lstResult;
		} catch (NumberFormatException e) {
			conn.close();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			conn.close();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			conn.close();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} finally {
            try {
                //System.out.println("***********"+pool.ConnectionsInfo());
				if (pool != null) {	pool.free(conn);	}
			} catch (Exception e) {	
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			}
			
        }

	}

public List getListaImportesTasaDetalle(String codTributo,String detalle) throws Exception{
		
	    List lstResult = new ArrayList();
	    
	    try {
	        PreparedStatement pstmt = null;
			//2 certificacion, 3 produccion
			//if (Constante.CONSTANTE_OS.equals("2") || Constante.CONSTANTE_OS.equals("3")){
				//System.out.println("ENTRO AL DATACOM DEL CONNECTIONPOOL");
				if(pool ==null)
					pool= new ConnectionPool();	
			    conn = pool.getConnection();
			ComboUtil combo = new ComboUtil();
				
			if (codTributo.equals("02119")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            " BNSUF13.F13_CELEMENTO ='" + detalle + "'");
			 }
			
			else if (codTributo.equals("00523")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            " BNSUF13.F13_CELEMENTO ='" + detalle + "'");
			 }
			else if (codTributo.equals("00730")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            " BNSUF13.F13_CELEMENTO ='" + detalle + "'");
			 }
			
			else if (codTributo.equals("00729")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            " BNSUF13.F13_CELEMENTO ='" + detalle + "'");
			 }
			else if (codTributo.equals("00524")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            " BNSUF13.F13_CELEMENTO ='" + detalle + "'");
			 }
			
			else if (codTributo.equals("00522")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            " BNSUF13.F13_CELEMENTO ='" + detalle + "'");
			 }
			
			else if (codTributo.equals("02143")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            " BNSUF13.F13_CELEMENTO ='" + detalle + "'");
			 }
			
			else if (codTributo.equals("00728")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            " BNSUF13.F13_CELEMENTO ='" + detalle + "'");
			 }
			
			else if (codTributo.equals("06610")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            " BNSUF13.F13_CELEMENTO ='" + detalle + "'");
			 }

			else if (codTributo.equals("06831")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            " BNSUF13.F13_CELEMENTO ='" + detalle + "'");
			 }
			
			else if (codTributo.equals("01601")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            " BNSUF13.F13_CELEMENTO ='" + detalle + "'");
			 }
			else if (codTributo.equals("01602")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            " BNSUF13.F13_CELEMENTO ='" + detalle + "'");
			 }
			else if (codTributo.equals("00529")){
			    pstmt = conn.prepareStatement("		" +			    
			            "SELECT NACION.BNSUF13.F13_CTABLA, NACION.BNSUF13.F13_CELEMENTO, " +
			            "NACION.BNSUF13.F13_SMONTO AS IMPORTE  FROM  NACION.BNSUF13 " + 
			            "WHERE NACION.BNSUF13.F13_CTABLA= 'TRIBUT'  AND  " +
			            " BNSUF13.F13_CELEMENTO ='" + detalle + "'");
			 }
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){   
			    combo = new ComboUtil();
				combo.setCodigo(rs.getString("IMPORTE"));
				combo.setDescripcion(rs.getString("IMPORTE"));
				lstResult.add(combo);
			}
			rs.close();
			pstmt.close();	
			conn.close();

			/*** comentar todo el try para certificacion**/
			
			//try{
				//rs.close();
				//pstmt.close();
				//conn.close();
			//}catch (Exception e) {
				
			//}

			/*** fin de comentar el try **/
				return lstResult;
		} catch (NumberFormatException e) {
			conn.close();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			conn.close();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			conn.close();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} finally {
            try {
                //System.out.println("***********"+pool.ConnectionsInfo());
				if (pool != null) {	pool.free(conn);	}
			} catch (Exception e) {
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			}
			
        }
		
	}
	
	public List getListaDependencias(String distrito) throws Exception{
		
	    List lstResult = new ArrayList();
	    try {
			
			//2 certificacion, 3 produccion
			//if (Constante.CONSTANTE_OS.equals("2") || Constante.CONSTANTE_OS.equals("3")){
				//System.out.println("ENTRO AL DATACOM DEL CONNECTIONPOOL");
				if(pool ==null)
					pool= new ConnectionPool();	
			    conn = pool.getConnection();
			/*}
			else if (Constante.CONSTANTE_OS.equals("1")){
			    conn = this.getDatacomConnection();
			}*/
			
			PreparedStatement pstmt = conn.prepareStatement("		" +
					"SELECT" +
					"		NACION.BNREF29.F29_CDEPENDENCIA," +
					"		NACION.BNREF29.F29_CUBIGEO, NACION.BNREF29.F29_ADEP_JUDICIAL" +
					"	FROM" +
					"		NACION.BNREF29");
			
			ResultSet rs = pstmt.executeQuery();

			ComboUtil combo = new ComboUtil();
			combo.setCodigo("");
			combo.setDescripcion(Constante.SELECCIONE);	
					
			while(rs.next()){
			    if (rs.getString(3)!=null && rs.getString(3).endsWith(distrito)){
					combo = new ComboUtil();
					combo.setCodigo(rs.getString(1));
					combo.setDescripcion(rs.getString(3));
					
					lstResult.add(combo);
		        
			    }
			}
			
			/*** comentar todo el try para certificacion**/
			
			//try{
				rs.close();
				pstmt.close();
				conn.close();
				//conn.close();
			//}catch (Exception e) {
				
			//}

			/*** fin de comentar el try **/
				return lstResult;
		} catch (NumberFormatException e) {
			conn.close();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			conn.close();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			conn.close();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} finally {
            try {
                //System.out.println("***********"+pool.ConnectionsInfo());
				if (pool != null) {	pool.free(conn);	}
			} catch (Exception e) {	
				
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			}
			
        }
		
	}
	public LimitImpl getLimiteByLimt(Limit limit) throws Exception {
	    LimitImpl limit_ = new LimitImpl(limit);
	    limit_.cargar();
	    //if(Constante.VER_LOG) System.out.println("getTipoCuenta():"+limit_.getTipoCuenta());
	    //if(Constante.VER_LOG) System.out.println("getImporteMaximo():"+limit_.getImporteMaximo());
	    return limit_;
	}
	
public List getListaImporteTasaEducativa(String codTributo) throws Exception{
		
	    List lstResult = new ArrayList();
	    
	    try {
	    	 
	        PreparedStatement pstmt = null;
	
			if(pool ==null)
					pool= new ConnectionPool();	
			    conn = pool.getConnection();
			ComboUtil combo = new ComboUtil();

		
		    pstmt = conn.prepareStatement("		" +		
		            "SELECT NACION.BNREF02.F02_CTRIBUTO, NACION.BNREF02.F02_ATRIBUTO  AS DETALLE, " +
		            "NACION.BNREF02.F02_SVALOR AS IMPORTE, NACION.BNREF02.F02_SIMP_VALOR1, " + 
		            "NACION.BNREF02.F02_SIMP_VALOR2 " + 
		            "From NACION.BNREF02 Where F02_CTRIBUTO = " + String.valueOf(Integer.parseInt(codTributo)) + " AND F02_IVIGENCIA='0'"	);
		

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){   
			    combo = new ComboUtil();
				combo.setCodigo(rs.getString("IMPORTE"));
				combo.setDescripcion(rs.getString("DETALLE"));
				lstResult.add(combo);
			}
			rs.close();
			pstmt.close();	
			conn.close();

		return lstResult;
		} catch (NumberFormatException e) {
			conn.close();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			conn.close();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			conn.close();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} finally {
            try {
                //System.out.println("***********"+pool.ConnectionsInfo());
				if (pool != null) {	pool.free(conn);	}
			} catch (Exception e) {
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			}
			
        }
		
	}
}
