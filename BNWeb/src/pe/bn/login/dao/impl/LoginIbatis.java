package pe.bn.login.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;
import pe.bn.login.dao.LoginDAO;
import pe.bn.login.domain.Menu;
import pe.bn.login.domain.impl.ImagenTarjetaImpl;
import pe.bn.login.domain.impl.IngresoTarjetaImpl;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.Constante;
import pe.cosapi.common.DAOAbstract;



public class LoginIbatis extends DAOAbstract implements LoginDAO {
	private static LoggerSati log3 = LoggerSati.getInstance(LoginIbatis.class.getName());
	
	/* (non-Javadoc)
	 * @see pe.bn.login.dao.impl.LoginDAO#getMenuByCode(java.lang.String)
	 */
	public Menu[] getMenuByCode(String codigoPersona,String codigoTarjeta) throws Exception{
	    Map input = new HashMap();
	    //if(Constante.VER_LOG) System.out.println("codigoTarjeta:"+codigoTarjeta);
	    input.put("codigoPersona", codigoPersona);
		input.put("codigoTarjeta", codigoTarjeta);
		List lstMenu = getSqlMapClientTemplate().queryForList("sqlMapLogin.getMenuByCode",input);
		return (Menu[])lstMenu.toArray(new Menu[0]);
	}
	
	public Menu[] getMenuByCode1(String codigoPersona,String codigoTarjeta) throws Exception{
		
	    Map input = new HashMap();
	   
	    //if(Constante.VER_LOG) System.out.println("codigoTarjeta:"+codigoTarjeta);
	    input.put("codigoPersona", codigoPersona);
		input.put("codigoTarjeta", codigoTarjeta);
		List lstMenu = getSqlMapClientTemplate().queryForList("sqlMapLogin.getMenuByCode1",input);
		return (Menu[])lstMenu.toArray(new Menu[0]);
	}
	
	public Menu[] getMenuByCodeOtros(String codigoPersona,String codigoTarjeta) throws Exception{
	    Map input = new HashMap();
	    //if(Constante.VER_LOG) System.out.println("codigoTarjeta:"+codigoTarjeta);
	    input.put("codigoPersona", codigoPersona);
		input.put("codigoTarjeta", codigoTarjeta);
		List lstMenu = getSqlMapClientTemplate().queryForList("sqlMapLogin.getMenuByCodeOtros",input);
		return (Menu[])lstMenu.toArray(new Menu[0]);
	}
	
	public Menu[] getSubMenuByCodeOtros(String codigoPersona,String codigoPadre) throws Exception{
		Map input = new HashMap();
		input.put("codePerson", codigoPersona);
		input.put("codeFather", codigoPadre);
		List lstMenu = getSqlMapClientTemplate().queryForList("sqlMapLogin.getSubMenuByCodeOtros",input);
		return (Menu[])lstMenu.toArray(new Menu[0]);
	}
		
	
	/* (non-Javadoc)
	 * @see pe.bn.login.dao.impl.LoginDAO#getSubMenuByCode(java.lang.String)
	 */
	public Menu[] getSubMenuByCode(String codigoPersona,String codigoPadre) throws Exception{
		Map input = new HashMap();
		input.put("codePerson", codigoPersona);
		input.put("codeFather", codigoPadre);
		List lstMenu = getSqlMapClientTemplate().queryForList("sqlMapLogin.getSubMenuByCode",input);
		return (Menu[])lstMenu.toArray(new Menu[0]);
	}
	
	public Menu[] getSubMenuByCode1(String codigoPersona,String codigoPadre, String ip) throws Exception{
		
		String inter = Parametro.getString(ConstanteParametros.BN_PARAM_PARAMETRO_SATI_INTEROPERABILIDAD);
		String submenu1 = Parametro.getString(ConstanteParametros.BN_PARAM_PARAMETRO_SATI_SUBMENU1);
		 
		Map input = new HashMap();
		input.put("codePerson", codigoPersona);
		input.put("codeFather", codigoPadre);
		List menuCode1= new ArrayList();
		ResourceBundle rb = ResourceBundle.getBundle("parametro");		
		String ips = rb.getString("bn.ip.usuarios");
		List lstMenu = getSqlMapClientTemplate().queryForList("sqlMapLogin.getSubMenuByCode1",input);
		
		if(ips.equals("all") || ips.indexOf(ip) >= 0 ){
			
			for (int i = 0; i < lstMenu.size(); i++) {
				
//				if(inter.equals("1")){
//					Menu menu = (Menu)lstMenu.get(i);
//				//0141
//					System.out.println("ConstanteParametros.BN_PARAM_PARAMETRO_SATI_SUBMENU1:::"+submenu1);
//					
//					if((((submenu1).equals(menu.getCodeOption().trim())))						
//					){
//						
//					}else{
//						menuCode1.add(menu);
//					}
//				}else{				
					Menu menu = (Menu)lstMenu.get(i);
					menuCode1.add(menu);					
//				}
					
				
			}
			
		}else{
			
			for (int i = 0; i < lstMenu.size(); i++) {
				
				Menu menu = (Menu)lstMenu.get(i);
				
				if(!menu.getCodeOption().equals("0001")&& !menu.getCodeOption().equals("0011")){
					menuCode1.add(menu);
				}
			}
		}	

		
		//return (Menu[])lstMenu.toArray(new Menu[0]);
		return (Menu[])menuCode1.toArray(new Menu[0]);
	}
	
	public Menu[] getSubMenuByCode2(String codigoPersona,String codigoPadre, String codigoHijo) throws Exception{
		String inter = Parametro.getString(ConstanteParametros.BN_PARAM_PARAMETRO_SATI_INTEROPERABILIDAD);
		String subsubmenu1 = Parametro.getString(ConstanteParametros.BN_PARAM_PARAMETRO_SATI_SUBSUBMENU1);
		String subsubmenu2 = Parametro.getString(ConstanteParametros.BN_PARAM_PARAMETRO_SATI_SUBSUBMENU2);
				
		List menuCode1= new ArrayList();
		
		Map input = new HashMap();
		input.put("codePerson", codigoPersona);
		input.put("codeFather", codigoPadre);
		input.put("codeHijo", codigoHijo);
		List lstMenu = getSqlMapClientTemplate().queryForList("sqlMapLogin.getSubMenuByCode2",input);
		for (int i = 0; i < lstMenu.size(); i++) {
//			if(inter.equals("1")){
//				Menu menu = (Menu)lstMenu.get(i);
//			//"0185 - 0187"
//				System.out.println("ConstanteParametros.BN_PARAM_PARAMETRO_SATI_SUBSUBMENU1:::"+subsubmenu1);
//				System.out.println("ConstanteParametros.BN_PARAM_PARAMETRO_SATI_SUBSUBMENU2:::"+subsubmenu2);
//				
//				if(
//						(((subsubmenu1).equals(menu.getCodeOption().trim())))
//						
//						||
//					   (((subsubmenu2).equals(menu.getCodeOption().trim())))
//						
//						){
//					
//				}else{
//					menuCode1.add(menu);
//				}
//			}else{				
				Menu menu = (Menu)lstMenu.get(i);
				menuCode1.add(menu);					
//			}
		}
			
		
		return (Menu[])menuCode1.toArray(new Menu[0]);
	}
	
	/* (non-Javadoc)
	 * @see pe.bn.login.dao.impl.LoginDAO#getValidaImagen(java.lang.String)
	 */
	public List getValidaImagen(String numeroTMV) throws Exception {
		
		
		List lstResult = new ArrayList();
		
		Context ctxTempLog;
		DataSource dsTempLog;
		Connection cnTempLog = null;
		PreparedStatement psTempLogSequence;
		ResultSet rs;
		 
		try {
			     
			ctxTempLog = new InitialContext();
			dsTempLog = (DataSource)ctxTempLog.lookup("jdbc/dbSWB");
			cnTempLog = dsTempLog.getConnection();
			 
			String query="SELECT  "+
				"	NumTar, "+
				"	NomImg, "+ 
				"	FecIng, "+
				"	UltFec, "+ 
				"	NumInt, "+
				"	DiaInt, "+
				"	NumSec, "+
				"	CodCat "+
				"	From PV_SWB.TIMGTMV "+
				"	Where NumSec = (Select Max(NumSec) From PV_SWB.TIMGTMV Where NumTar = ? ) ";
			
			//System.out.println("query:"+query);
			
			int pos =0;
			psTempLogSequence = cnTempLog.prepareStatement("		" + query);
			psTempLogSequence.setString(++pos, numeroTMV.trim());
			rs = psTempLogSequence.executeQuery();
			
			boolean seguir = rs.next();
			
			if (seguir){
				ImagenTarjetaImpl nuevaLista = new ImagenTarjetaImpl();
				nuevaLista.setNumTarImg(rs.getString(1));
				nuevaLista.setNomValorImg(rs.getString(2));
				nuevaLista.setFechaIng(rs.getString(3));
				nuevaLista.setUltFecha(rs.getString(4));
				nuevaLista.setNumIntento(rs.getString(5));
				nuevaLista.setNumIntDia(rs.getString(6));
				nuevaLista.setNumSec(rs.getString(7));
				nuevaLista.setCodCat(rs.getString(8));
							
				
				lstResult.add(0, nuevaLista);
				rs.close();
				cnTempLog.close();
			} else {
				rs.close();
				cnTempLog.close();
				return lstResult = null;
			}
			
		}catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			System.out.println("SATI: getValidaImagen.ArrayRuleException: "+e.getMessage());
			if(Constante.VER_LOG)System.out.println("SATI: Exception 1 ValidaImagen...");
			throw e;
		}
		
		finally{
			try{
				cnTempLog.close();
			}catch(Exception e){
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				
				if(Constante.VER_LOG)System.out.println("SATI: Exception 3 getValidaImagen");
				if(Constante.VER_LOG)System.out.println(""+e);
				return lstResult = null;
			}
		}

	
		return lstResult;
	}
	
	public void getAfiliaImagen(String numeroTMV, String cImgNom, String cNumSec, String cNumCat, String ip, String estado) throws Exception {
		
		Context ctxTempLog;
		DataSource dsTempLog;
		Connection cnTempLog = null;
		PreparedStatement psTempLogSequence;
		ResultSet rs;
		 
		try {
			     
			ctxTempLog = new InitialContext();
			dsTempLog = (DataSource)ctxTempLog.lookup("jdbc/dbSWB");
			cnTempLog = dsTempLog.getConnection();
			 
			String query="Insert Into PV_SWB.TIMGTMV(  "+
				"	NumSec, "+
				"	NumTar, "+
				"	NomImg, "+ 
				"	CodCat, "+
				"	UltFec, "+
				"	Ipcrea, "+
				"	Stdoimg, "+
				"	FecIng) "+
				"	Values( "+
				"	?, " +
				"	?, " +
				"	?, " +
				"	?, " +
				"	sysdate,	"+
				"	?, " +
				"	?, " +
				"	sysdate) ";
			
			psTempLogSequence = cnTempLog.prepareStatement("		" + query);
			int pos =0;
			psTempLogSequence.setString(++pos, cNumSec.trim());
			psTempLogSequence.setString(++pos, numeroTMV.trim());
			psTempLogSequence.setString(++pos, cImgNom.trim());
			psTempLogSequence.setString(++pos, cNumCat.trim() );
			//psTempLogSequence.setString(++pos, "sysdate");
			psTempLogSequence.setString(++pos, ip.trim());
			psTempLogSequence.setString(++pos,  estado.trim());
			//psTempLogSequence.setString(++pos,  "sysdate");
			rs = psTempLogSequence.executeQuery();
			
			boolean seguir = rs.next();
			
			rs.close();
			cnTempLog.close();
			
		}catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			cnTempLog.close();
			
			if(Constante.VER_LOG)System.out.println("SATI: Exception 1 getAfiliaImagen...");
			throw e;
		}
		
		finally{
			try{
				cnTempLog.close();
			}catch(Exception e){
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				
				if(Constante.VER_LOG)System.out.println("SATI: Exception 3 getValidaImagen");
				if(Constante.VER_LOG)System.out.println(""+e);
			}
		}
	}
	
	public void getIntentosImagen(String numeroSec, int numIntDia, String ip) throws Exception {
		

	}
	
	public void getUltimoIngreso(String numeroSec,String ip) throws Exception {
		
		Context ctxTempLog;
		DataSource dsTempLog;
		Connection cnTempLog = null;
		PreparedStatement psTempLogSequence;
		ResultSet rs;
		
		try {
			     
			ctxTempLog = new InitialContext();
			dsTempLog = (DataSource)ctxTempLog.lookup("jdbc/dbSWB");
			cnTempLog = dsTempLog.getConnection();
						
			String query="Update PV_SWB.TIMGTMV Set "+
				"	UltFec = SysDate, "+
				"	NumInt = 0, "+
				"	DiaInt = 0, "+
				"	ipult =	 ?"+
				"	Where "+
				"	NumSec = ? ";
			
			
			psTempLogSequence = cnTempLog.prepareStatement("		" + query);
			int pos =0;
			psTempLogSequence.setString(++pos, ip.trim());			
			psTempLogSequence.setString(++pos, numeroSec.trim());			
			rs = psTempLogSequence.executeQuery();
			
			//boolean seguir = rs.next();
			
			rs.close();
			cnTempLog.close();
			
		}catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			cnTempLog.close();
			
			if(Constante.VER_LOG)System.out.println("SATI: Exception 1 getUltimoIngreso...");
			throw e;
		}
		
		finally{
			try{
				cnTempLog.close();
			}catch(Exception e){
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				
				if(Constante.VER_LOG)System.out.println("SATI: Exception 3 getUltimoIngreso");
				if(Constante.VER_LOG)System.out.println(""+e);
			}
		}
	}
	

// Verifica si existe el registro -- Agregado 08/04/2013
	
public List getValidaExiste(String numeroTMV) throws Exception {
		
	
		List lstResult = new ArrayList();
	
		
		Context ctxTempLog;
		DataSource dsTempLog;
		Connection cnTempLog = null;
		PreparedStatement psTempLogSequence;
		ResultSet rs;
		 
		try {
			     
			ctxTempLog = new InitialContext();
			dsTempLog = (DataSource)ctxTempLog.lookup("jdbc/dbSWB");
			cnTempLog = dsTempLog.getConnection();
			 
			String query="	Select numsec, "+
						" numtar, "+
						" fecing, "+
						" fecant, "+
						" to_char(fecant,'dd/MM/yyyy') as fecha, "+
						" to_char(fecant,'hh:mi:ss AM') as hora, "+
						" iping, "+
						" ipant "+
						" from PV_SWB.TFECTMV  "+
						" where numtar = ?";
			
			
		
			
			psTempLogSequence = cnTempLog.prepareStatement("		" + query);
			int pos =0;
			psTempLogSequence.setString(++pos,numeroTMV.trim());
			rs = psTempLogSequence.executeQuery();
			
			boolean seguir = rs.next();
			
			if (seguir){
				IngresoTarjetaImpl lista = new IngresoTarjetaImpl();
				lista.setNumSec(rs.getString(1));
				lista.setNumTar(rs.getString(2));
				lista.setFechaIng(rs.getString(3));
				lista.setFechaAnt(rs.getDate(4));
				lista.setDiaAnt(rs.getString(5));
				lista.setHoraAnt(rs.getString(6));
				lista.setIpIng(rs.getString(7));
				lista.setIpAnt(rs.getString(8));
							
				lstResult.add(0, lista);
				rs.close();
				cnTempLog.close();
			} else {
				rs.close();
				cnTempLog.close();
				return lstResult;
			}
			
		}catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			throw e;
		}
		
		finally{
			try{
				cnTempLog.close();
			}catch(Exception e){
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				
				return lstResult = null;
			}
		}
		
		return lstResult;
	}

//Si es la primera vez que se ingresa se inserta el registro con la fecha actual -- Agregado 08/04/2013

 public void getFechaIngreso(String numeroTMV, String cNumSec, String ip) throws Exception {
	
	Context ctxTempLog;
	DataSource dsTempLog;
	Connection cnTempLog = null;
	PreparedStatement psTempLogSequence;
	ResultSet rs;
	 
	
	try {
		     
		ctxTempLog = new InitialContext();
		dsTempLog = (DataSource)ctxTempLog.lookup("jdbc/dbSWB");
		cnTempLog = dsTempLog.getConnection();
		 
		String query=	"Insert into PV_SWB.TFECTMV(     "+
						"NumSec,   "+
						"NumTar,   "+
						"IpIng,	"+
						"fecAnt) "+
						"Values( "+
						"?, " +
						"?, " +
						"?, " +
						"sysdate)" ;
				
	
		psTempLogSequence = cnTempLog.prepareStatement("		" + query);
		int pos =0;
		psTempLogSequence.setString(++pos,cNumSec.trim());		
		psTempLogSequence.setString(++pos,numeroTMV.trim() );
		psTempLogSequence.setString(++pos, ip.trim());
		rs = psTempLogSequence.executeQuery();
		
		boolean seguir = rs.next();
		
		rs.close();
		cnTempLog.close();
		
	}catch(ArrayRuleException e){
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		e.printStackTrace();		
		cnTempLog.close();
		throw e;
	}
	
	finally{
		try{
			cnTempLog.close();
		}catch(Exception e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			
		}
	}
}
 
// Actualiza el registro, mueve la fecha a la anterior y graba la fecha de ingreso  -- Agregado 08/04/2013
 
 
	public void getUltimoIngresoFecha(String numTarjeta,String ip) throws Exception {
		
		Context ctxTempLog;
		DataSource dsTempLog;
		Connection cnTempLog = null;
		PreparedStatement psTempLogSequence;
		ResultSet rs;
		
		try {
			     
			ctxTempLog = new InitialContext();
			dsTempLog = (DataSource)ctxTempLog.lookup("jdbc/dbSWB");
			cnTempLog = dsTempLog.getConnection();
						
			String query="update PV_SWB.TFECTMV "+
						"  set fecant = fecing, "+
						"  ipAnt = iping, "+
						"  fecing = sysdate, "+
						"  iping  = ? "+
						"  where numtar  = ? ";
				
			
			psTempLogSequence = cnTempLog.prepareStatement("		" + query);
			int pos =0;
			psTempLogSequence.setString(++pos,ip.trim());					
			psTempLogSequence.setString(++pos,numTarjeta.trim());
			rs = psTempLogSequence.executeQuery();
			
			//boolean seguir = rs.next();
			
			rs.close();
			cnTempLog.close();
			
		}catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			cnTempLog.close();
			throw e;
		}
		
		finally{
			try{
				cnTempLog.close();
			}catch(Exception e){
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			
			}
		}
	}
	
	
	public String getNextSec() throws Exception {
		
		Context ctxTempLog;
		DataSource dsTempLog;
		Connection cnTempLog = null;
		PreparedStatement psTempLogSequence;
		ResultSet rs;
		String cNextVal = "";
		 
		try {
			     
			ctxTempLog = new InitialContext();
			dsTempLog = (DataSource)ctxTempLog.lookup("jdbc/dbSWB");
			cnTempLog = dsTempLog.getConnection();
			 
			String query="Select PV_SWB.SEQNUMIMG.NEXTVAL As NEXTVAL FROM DUAL";
			
			psTempLogSequence = cnTempLog.prepareStatement("		" + query);
						
			rs = psTempLogSequence.executeQuery();
			
			boolean seguir = rs.next();
			
			if (seguir){
				cNextVal = rs.getString(1);
				return cNextVal;
			}

			rs.close();
			cnTempLog.close();
			
		}catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			cnTempLog.close();
			
			if(Constante.VER_LOG)System.out.println("SATI: Exception 1 getNextSec...");
			throw e;
			
		}
		
		finally{
			try{
				cnTempLog.close();
			}catch(Exception e){
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			
				if(Constante.VER_LOG)System.out.println("SATI: Exception 3 getNextSec");
				if(Constante.VER_LOG)System.out.println(""+e);
				return cNextVal;
			}
		}
		return cNextVal;
	}
	
	public String getNextSecFech() throws Exception {
		
		Context ctxTempLog;
		DataSource dsTempLog;
		Connection cnTempLog = null;
		PreparedStatement psTempLogSequence;
		ResultSet rs;
		String cNextVal = "";
		 
		try {
			     
			ctxTempLog = new InitialContext();
			dsTempLog = (DataSource)ctxTempLog.lookup("jdbc/dbSWB");
			cnTempLog = dsTempLog.getConnection();
			 
			String query="Select PV_SWB.SEQFECING.NEXTVAL As NEXTVAL FROM DUAL";
			
			psTempLogSequence = cnTempLog.prepareStatement("		" + query);
						
			rs = psTempLogSequence.executeQuery();
			
			boolean seguir = rs.next();
			
			if (seguir){
				cNextVal = rs.getString(1);
				return cNextVal;
			}

			rs.close();
			cnTempLog.close();
			
		}catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			cnTempLog.close();
			
			if(Constante.VER_LOG)System.out.println("SATI: Exception 1 getNextSec...");
			throw e;
			
		}
		
		finally{
			try{
				cnTempLog.close();
			}catch(Exception e){
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			
				if(Constante.VER_LOG)System.out.println("SATI: Exception 3 getNextSec");
				if(Constante.VER_LOG)System.out.println(""+e);
				return cNextVal;
			}
		}
		return cNextVal;
	}
	public void getCambioEstado(String numeroSec, String estado) throws Exception {
		
				
	
	}
	
}
