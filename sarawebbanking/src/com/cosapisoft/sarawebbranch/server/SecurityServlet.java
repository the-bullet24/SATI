package com.cosapisoft.sarawebbranch.server;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.cosapi.common.Constante;

import com.cosapisoft.sarawebbranch.common.beans.Crypto;
import com.cosapisoft.sarawebbranch.common.beans.LogginResult;
import com.cosapisoft.sarawebbranch.common.beans.LoginRequest;
import com.cosapisoft.sarawebbranch.common.beans.PerfilesUsuario;
import com.cosapisoft.sarawebbranch.common.beans.SecurityBranch;
import com.cosapisoft.sarawebmanager.security.beans.*;

/**
 * @version 	1.0
 * @author
 */
public class SecurityServlet extends HttpServlet {
	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	private String claveOffLine = "1A3C1F1D7532B10C";
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//performTask(req, resp);
	}

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//	performTask(req, resp);

	}
	/*
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

 		String param = req.getParameter("param");
		if (param.equals("loggin")){
			loggin(req, res);			
		}else if (param.equals("getsecuritybranch")){
			getSecurityBranch(req, res);
		}else if (param.equals("requireauthorization")){
			requireAuthorization(req, res);
		}else if (param.equals("requireauthorizationbylimits")){
			requireAuthorizationByLimits(req, res);
		}
	}*/

	//private void savePerfiles(PerfilesUsuario perfilesUsuario, Perfil[] perfil) throws IOException {
	private void savePerfiles(PerfilesUsuario perfilesUsuario) throws IOException, SQLException {
		Connection conn = null;		
	    String msgError = "";
	    String sqlstmt = "";
	    PreparedStatement pstmt = null;	     	    
	    try{	    	
		    conn = DataSourceConnector.getInstance().getConnection();
		    conn.setAutoCommit(false); 
		    //Grabar Perfiles		    
		    int listPerf = 0;	    	
		    listPerf  = perfilesUsuario.getPerfiles().size();
		    if(listPerf!=0){
			    for(int i=0; i<=listPerf-1; i++){
			    	Perfil perfil = (Perfil) perfilesUsuario.getPerfiles().get(i);
					sqlstmt = "select codperfil from utb_perfiles where codperfil = ? and codmodulo = ?";
				   	pstmt = conn.prepareStatement(sqlstmt);				   	
				   	pstmt.setString(1,perfil.getCodPerfil());
				   	pstmt.setString(2,perfil.getCodModulo());
				   	ResultSet result = pstmt.executeQuery();
				   	if (result.next()){ 
				   		sqlstmt = "update utb_perfiles set txtnombreperfil = ? where codperfil = ? and  codmodulo= ?";
				   		pstmt = conn.prepareStatement(sqlstmt);
				   		pstmt.setString(1,perfil.getNombrePerfil());
				    	pstmt.setString(2,perfil.getCodPerfil());
				    	pstmt.setString(3,perfil.getCodModulo());	    		
				   	}else{
				   		sqlstmt = "insert into utb_perfiles (codperfil, codmodulo, txtnombreperfil) values (?,?,?)";
				    	pstmt = conn.prepareStatement(sqlstmt);
				    	pstmt.setString(1,perfil.getCodPerfil());
				    	pstmt.setString(2,perfil.getCodModulo());
				    	pstmt.setString(3,perfil.getNombrePerfil());
				   	}
				   	pstmt.executeUpdate();
			    }		
				//Eliminar Perfiles de Cajero
				sqlstmt = "delete from utb_perfilescajero where codcajero = ?";
				pstmt = conn.prepareStatement(sqlstmt);
				pstmt.setString(1,perfilesUsuario.getCodUsuario());
				pstmt.executeUpdate();
				//Grabar Perfiles de Usuario	    
			    for(int i=0; i<=listPerf-1; i++){		
			    	Perfil perfil = (Perfil) perfilesUsuario.getPerfiles().get(i);
		    		sqlstmt = "insert into utb_perfilescajero (codcajero, codperfil, codmodulo) values (?,?,?)";
			    	pstmt = conn.prepareStatement(sqlstmt);			    	
			    	pstmt.setString(1,perfilesUsuario.getCodUsuario());
			    	pstmt.setString(2,perfil.getCodPerfil());
			    	pstmt.setString(3,perfil.getCodModulo());
			    	pstmt.executeUpdate();				    	
			    }	
		    }
		    conn.commit(); 
	    }catch(SQLException e){		
	    	conn.rollback();
	    	// System.out.println(e.getMessage());
	    }catch(NamingException e){		
	    	conn.rollback();
	    	// System.out.println(e.getMessage());	    	
	    }finally{
	    	try {	    			             
	    		conn.close();
	    	}catch (SQLException e){	    		
	    		// System.out.println(e.getMessage());
	    	}
	    }	
	}
	
	private void saveLimites(ArrayList ListaLimites) throws IOException, SQLException {
		Connection conn = null;		
	    String msgError = "";
	    String sqlstmt = "";
	    PreparedStatement pstmt = null;	       
	    try{   
		    conn = DataSourceConnector.getInstance().getConnection();
		    conn.setAutoCommit(false); 
		    //Grabar Límites
		    int listLimi = 0;	    	
		    listLimi  = ListaLimites.size();
		    if(listLimi!=0){
			    for(int i=0; i<=listLimi-1; i++){
			    	Limite limite = (Limite) ListaLimites.get(i);
			    	sqlstmt = "select codperfil from utb_limites where codperfil = ? and codmodulo = ? and codmoneda = ?";
			    	pstmt = conn.prepareStatement(sqlstmt);			    	
			    	pstmt.setString(1,limite.getCodPerfil());
			    	pstmt.setString(2,limite.getCodModulo());
			    	pstmt.setString(3,limite.getCodMoneda());
			    	ResultSet result = pstmt.executeQuery();
			    	if (result.next()){ //Si existe...
			    		sqlstmt = "update utb_limites set decmontomin = ?, decmontomax = ? where codperfil = ? and codmodulo = ? and codmoneda = ?";
			    		pstmt = conn.prepareStatement(sqlstmt);
			    		pstmt.setDouble(1,limite.getMontoMinimo());
				    	pstmt.setDouble(2,limite.getMontoMaximo());
				    	pstmt.setString(3,limite.getCodPerfil());
				    	pstmt.setString(4,limite.getCodModulo());
				    	pstmt.setString(5,limite.getCodMoneda());
				    	int rows = pstmt.executeUpdate();
			    	}else{ //Si no existe...
			    		sqlstmt = "insert into utb_limites (codperfil, codmodulo, codmoneda, decmontomin, decmontomax) values (?,?,?,?,?)";
				    	pstmt = conn.prepareStatement(sqlstmt);
				    	pstmt.setString(1,limite.getCodPerfil());
				    	pstmt.setString(2,limite.getCodModulo());
				    	pstmt.setString(3,limite.getCodMoneda());
				    	pstmt.setDouble(4,limite.getMontoMinimo());
				    	pstmt.setDouble(5,limite.getMontoMaximo());
				    	int rows = pstmt.executeUpdate();
			    	}
			    }	
		    }
		    conn.commit();
	    }catch(SQLException e){
	    	conn.rollback();
	    	// System.out.println(e.getMessage());
	    }catch(NamingException e){
	    	conn.rollback();
	    	// System.out.println(e.getMessage());
	    }finally{
	    	try {	    		 
	    		conn.close();
	    	}catch (SQLException e){
	    		// System.out.println(e.getMessage());
	    	}
	    }	    
	}
	
	private void saveGruposPerfiles(ArrayList listaperfiles, ArrayList grupoPerf) throws IOException, SQLException {
		Connection conn = null;		
	    String msgError = "";
	    String sqlstmt = "";
	    PreparedStatement pstmt = null;	   
	    try{   
		    conn = DataSourceConnector.getInstance().getConnection();
		    conn.setAutoCommit(false); 
		    int listPerf = 0;	    	
		    listPerf  = listaperfiles.size();
		    int listGrupPerf = 0;	    	
	    	listGrupPerf  = grupoPerf.size();	    	
		    if(listPerf!=0 && listGrupPerf!=0){
			    for(int i=0; i<=listPerf-1; i++){		
			    	Perfil perfil = (Perfil) listaperfiles.get(i);
			    	sqlstmt = "select codperfil from utb_gruposperfiles where codperfil = ? and codmodulo = ?";
			    	pstmt = conn.prepareStatement(sqlstmt);			    	
			    	pstmt.setString(1,perfil.getCodPerfil());
			    	pstmt.setString(2,perfil.getCodModulo());		    	
			    	ResultSet result = pstmt.executeQuery();
			    	if (result.next()){ //Si existe...		    		
			    		//Eliminar grupo de perfiles		    			
						sqlstmt = "delete from utb_gruposperfiles where codperfil = ? and codmodulo = ?";
						pstmt = conn.prepareStatement(sqlstmt);					
						pstmt.setString(1,perfil.getCodPerfil());
						pstmt.setString(2,perfil.getCodModulo());
						pstmt.executeUpdate();
			    	}			    	
			    	for(int j=0; j<=listGrupPerf-1; j++){
			    		GruposPerfiles gruposPerfiles = (GruposPerfiles) grupoPerf.get(j);					   	
				 		if((perfil.getCodPerfil().equals(gruposPerfiles.getCodPerfil())) &&  (perfil.getCodModulo().equals(gruposPerfiles.getCodModulo()))){
				 			sqlstmt = "insert into utb_gruposperfiles (codperfil, codmodulo, codgrupo) values (?,?,?)";
				 			pstmt = conn.prepareStatement(sqlstmt);			 				
				 			pstmt.setString(1,perfil.getCodPerfil());
				 			pstmt.setString(2,perfil.getCodModulo());
				 			pstmt.setString(3,gruposPerfiles.getCodGrupo());		 						    
				 			int rows = pstmt.executeUpdate();
				 		}
				 	}			    	
				}	  
		    }
		    conn.commit(); 
	    }catch(SQLException e){
	    	conn.rollback();
	    	// System.out.println(e.getMessage());
	    }catch(NamingException e){
	    	conn.rollback();
	    	// System.out.println(e.getMessage());
	    }finally{
	    	try {	    		
	    		conn.close();
	    	}catch (SQLException e){
	    		// System.out.println(e.getMessage());
	    	}
	    }	    
	}
	
	private void saveTransaccionesPerfil(ArrayList listaperfiles, ArrayList tipoPerf) throws IOException, SQLException {
		Connection conn = null;		
	    String msgError = "";
	    String sqlstmt = "";
	    PreparedStatement pstmt = null;	   
	    try{   
		    conn = DataSourceConnector.getInstance().getConnection();
		    conn.setAutoCommit(false); 
		    int listPerf = 0;	    	
		    listPerf  = listaperfiles.size();
		    int listTipoPerf = 0;	    	
	    	listTipoPerf  = tipoPerf.size();	    	
		    if(listPerf!=0 && listTipoPerf!=0){
			    for(int i=0; i<=listPerf-1; i++){		
			    	Perfil perfil = (Perfil) listaperfiles.get(i);
			    	sqlstmt = "select codperfil from utb_transaccionesperfil where codperfil = ? and codmodulo = ?";
			    	pstmt = conn.prepareStatement(sqlstmt);			    	
			    	pstmt.setString(1,perfil.getCodPerfil());
			    	pstmt.setString(2,perfil.getCodModulo());		    	
			    	ResultSet result = pstmt.executeQuery();
			    	if (result.next()){ //Si existe...		    		
			    		//Eliminar grupo de perfiles		    			
						sqlstmt = "delete from utb_transaccionesperfil where codperfil = ? and codmodulo = ?";
						pstmt = conn.prepareStatement(sqlstmt);					
						pstmt.setString(1,perfil.getCodPerfil());
						pstmt.setString(2,perfil.getCodModulo());
						pstmt.executeUpdate();
			    	}			    	
			    	for(int j=0; j<=listTipoPerf-1; j++){
			    		TransaccionesPerfil transaccionesPerfil = (TransaccionesPerfil) tipoPerf.get(j);					   	
				 		if((perfil.getCodPerfil().equals(transaccionesPerfil.getCodPerfil())) &&  (perfil.getCodModulo().equals(transaccionesPerfil.getCodModulo()))){
				 			sqlstmt = "insert into utb_transaccionesperfil (codperfil, codmodulo, codtransaccion, typtransaccion) values (?,?,?,?)";
				 			pstmt = conn.prepareStatement(sqlstmt);			 				
				 			pstmt.setString(1,perfil.getCodPerfil());
				 			pstmt.setString(2,perfil.getCodModulo());
				 			pstmt.setString(3,transaccionesPerfil.getCodTransaccion());	
				 			pstmt.setInt(4,transaccionesPerfil.getTypTransaccion());
				 			int rows = pstmt.executeUpdate();
				 		}
				 	}			    	
				}	  
		    }
		    conn.commit(); 
	    }catch(SQLException e){
	    	conn.rollback();
	    	// System.out.println(e.getMessage());
	    }catch(NamingException e){
	    	conn.rollback();
	    	// System.out.println(e.getMessage());
	    }finally{
	    	try {	    		
	    		conn.close();
	    	}catch (SQLException e){
	    		// System.out.println(e.getMessage());
	    	}
	    }	    
	}
		
	/*
	private void loggin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String messageResultTra = "";		
		try{
			System.out.println("loggin*********************************************");
			
			InputStream in = req.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(in);
			LoginRequest loginreq = new LoginRequest();
			System.out.println("readObject...");
	    	loginreq = (LoginRequest) ois.readObject();      
		    in.close();	    	
			String codUsuario = loginreq.getCodUsuario();
			String clave = loginreq.getClave();
	    	String codAgencia = loginreq.getCodAgencia();
	    	String codModulo = loginreq.getCodModulo();	    	
	    	
	    	LogginResult logginResult = new LogginResult();
	    	System.out.println("Instanciando Cliente SWM...");
	    	ClienteWS clienteWS = new ClienteWS();	       
	        ClientResultVO cliRVO = new ClientResultVO();
	        System.out.println("Invocando a SWM...");
	    	cliRVO = clienteWS.tellerLoggin(codModulo, codAgencia, codUsuario, clave);
	    	System.out.println("Leyendo de SWM...");
	    	LogginResultVO logRVO = (LogginResultVO) cliRVO.getObject();
	    	System.out.println("Fin de SWM");
	    	messageResultTra =cliRVO.getMessage();
	    	//valores 0: error, 1: exito (no lo estamos manejando)
	    	//String codResultTra = cliRVO.getCode();
	    	//mensaje de error
	    	if (cliRVO.getCode().equals("1")){
		        
			    logginResult.setState(logRVO.getState());
			    logginResult.setMessage(logRVO.getMessage());
			    logginResult.setNameUser(logRVO.getName());
			    logginResult.setFlagPasswordChange(logRVO.getFlagPasswordChange());	
			    
			    if (logginResult.getState().equals("1")){ //1: Login Exitoso      	
				   		
				  	//pasar datos del logginResult a los beans
				    //No se usarán los Flag de los objetos
				   	int listPerf = 0;			   				   	
					listPerf  = logRVO.getProfiles().size();
					for(int i=0; i<=listPerf-1; i++){	
						ProfileVO pVO = (ProfileVO) logRVO.getProfiles().get(i);
						Perfil per = new Perfil();
				    	per.setCodModulo(codModulo);
				    	per.setCodPerfil(pVO.getCode());			    	
				    	per.setNombrePerfil(pVO.getName());
				    	logginResult.getProfiles().add(per);
					}					 
				    
					int listLim = 0;	    	
					listLim  = logRVO.getLimits().size();				
				    for(int i=0; i<=listLim-1; i++){	
				    	LimitVO lVO = (LimitVO) logRVO.getLimits().get(i);	
				    	Limite limi = new Limite();
				    	limi.setCodModulo(codModulo);
				    	limi.setCodMoneda(lVO.getCurrencyCode());
				    	limi.setCodPerfil(lVO.getProfileCode());
				    	limi.setMontoMaximo(Double.parseDouble(lVO.getMaxAmount()));
				    	limi.setMontoMinimo(Double.parseDouble(lVO.getMinAmount()));
				    	logginResult.getLimits().add(limi);
				    }
				    
				    int listGrupP = 0;	    	
				    listGrupP  = logRVO.getGroupsProfiles().size();				    
				    for(int i=0; i<=listGrupP-1; i++){	    		
				    	 GroupProfileVO gVO = (GroupProfileVO) logRVO.getGroupsProfiles().get(i);
				    	 GruposPerfiles grupP = new GruposPerfiles();
				    	 grupP.setCodModulo(codModulo);
				    	 grupP.setCodGrupo(gVO.getCode());			    	
				    	 grupP.setCodPerfil(gVO.getProfileVO().getCode());	
				    	 logginResult.getGroupsProfiles().add(grupP);
				    }
				    
				    int listTranP = 0;	    	
				    listTranP  = logRVO.getTransactionsProfiles().size();	
				    for(int i=0; i<=listTranP-1; i++){	    		
				    	TransactionProfileVO tVO = (TransactionProfileVO) logRVO.getTransactionsProfiles().get(i);
				    	TransaccionesPerfil transP = new TransaccionesPerfil(); 
				    	transP.setCodModulo(codModulo);			    	
				    	transP.setCodPerfil(tVO.getProfileVO().getCode());
				    	transP.setCodTransaccion(tVO.getCode());
				    	transP.setTypTransaccion(Integer.parseInt(tVO.getType()));
				    	logginResult.getTransactionsProfiles().add(transP);			    	
				    }		  
				    
				    int listAct = 0;	    	
				    listAct  = logRVO.getActivities().size();			    
				    for(int i=0; i<=listAct-1; i++){			    
				    	ActivityVO aVO = (ActivityVO) logRVO.getActivities().get(i);
				    	Actividad act = new Actividad();
				    	act.setCodModulo(codModulo);
				    	act.setCodActividad(aVO.getCode());
				    	act.setNomActividad(aVO.getName());
				    	logginResult.getActivities().add(act);			    	
				    }			    
				    
				    
			    	//REGISTRAR EN BD
					PerfilesUsuario perfilesUsuario = new PerfilesUsuario();
					perfilesUsuario.setCodUsuario(codUsuario);
					perfilesUsuario.setPerfiles(logginResult.getProfiles());
					saveUsuario(loginreq, logginResult);
					savePerfiles(perfilesUsuario);				
					saveLimites(logginResult.getLimits());				
					saveGruposPerfiles(logginResult.getProfiles(), logginResult.getGroupsProfiles());
					saveTransaccionesPerfil(logginResult.getProfiles(), logginResult.getTransactionsProfiles());	
									
			    } 		  
	    	}else{
			    logginResult.setState("0");
			    logginResult.setMessage("Ha ocurrido un error inesperado");
			    System.out.println("cliRVO.Code: "+ cliRVO.getCode());
			    System.out.println("cliRVO.Message: "+ messageResultTra);
	    	}
		    res.setContentType("java-internal/" + LogginResult.class.getName());
			OutputStream out = res.getOutputStream();	    	
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(logginResult);
			oos.flush();
			oos.close();
	       			    	
		}catch (Exception e){
			System.out.println("com.cosapisoft.sarawebbranch.server.loggin: "+e.getMessage());
	    	System.out.println(messageResultTra);	    	
	    }			
	}
*/
	private void getSecurityBranch(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try{
			SecurityBranch securityBranch = new SecurityBranch();
			securityBranch.setPerfiles(loadPerfiles()); 
			securityBranch.setLimites(loadLimites());
		    res.setContentType("java-internal/" + SecurityBranch.class.getName());
			OutputStream out = res.getOutputStream();	    	
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(securityBranch);
			oos.flush();
			oos.close(); 			
		}catch(Exception e){
			// System.out.println(getClass().getName() + "." + "getSecurityBranch: "+ e.getMessage());
		}
	}
	
	private ArrayList loadPerfiles(){
		ArrayList perfiles = new ArrayList();
		Connection conn = null;		
	    String sqlstmt = "";
	    PreparedStatement pstmt = null;	   
	    try{   
		    conn = DataSourceConnector.getInstance().getConnection();
		    sqlstmt = "select codperfil, codmodulo, txtnombreperfil from utb_perfiles";
	    	pstmt = conn.prepareStatement(sqlstmt);			    	
	    	ResultSet result = pstmt.executeQuery();
	    	while (result.next()){
	    		Perfil perfil = new Perfil();
	    		perfil.setCodPerfil(result.getString("codperfil"));
	    		perfil.setCodModulo(result.getString("codmodulo"));
	    		perfil.setNombrePerfil(result.getString("txtnombreperfil"));
	    		perfiles.add(perfil);
	    	}
		}catch (SQLException e){
			// System.out.println(getClass().getName() + "." + "loadPerfiles: "+  e.getMessage());
		}catch (NamingException e){
			// System.out.println(getClass().getName() + "." + "loadPerfiles: "+  e.getMessage());
		} finally{
	    	try {
	    		conn.close();
	    	}catch (SQLException e){
	    		// System.out.println(e.getMessage());
	    	}
		}
		return perfiles;
 	}
	
	private ArrayList loadLimites(){
		ArrayList limites = new ArrayList();
		Connection conn = null;		
	    String sqlstmt = "";
	    PreparedStatement pstmt = null;	   
	    try{   
		    conn = DataSourceConnector.getInstance().getConnection();
		    sqlstmt = "select codperfil, codmodulo, codmoneda, decmontomin, decmontomax from utb_limites";
	    	pstmt = conn.prepareStatement(sqlstmt);			    	
	    	ResultSet result = pstmt.executeQuery();
	    	while (result.next()){
	    		Limite limite = new Limite();
	    		limite.setCodPerfil(result.getString("codperfil"));
	    		limite.setCodModulo(result.getString("codmodulo"));
	    		limite.setCodMoneda(result.getString("codmoneda"));
	    		limite.setMontoMinimo(result.getDouble("decmontomin"));
	    		limite.setMontoMaximo(result.getDouble("decmontomax"));
	    		limites.add(limite);
	    	}
		}catch (SQLException e){
			// System.out.println(getClass().getName() + "." + "loadLimites: "+  e.getMessage());
		}catch (NamingException e){
			// System.out.println(getClass().getName() + "." + "loadLimites: "+  e.getMessage());
		} finally{
	    	try {
	    		conn.close();
	    	}catch (SQLException e){
	    		// System.out.println(e.getMessage());
	    	}
		}
		return limites;
 	}
	/*
	private void requireAuthorization(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try{
			InputStream in = req.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(in);
			AuthorizationRequest autoReq = new AuthorizationRequest();
			autoReq = (AuthorizationRequest) ois.readObject();      
		    in.close();	    	
    	
	        ClienteWS clienteWS = new ClienteWS();	       
	        ClientResultVO cliRVO = new ClientResultVO();	        
	    		    	
	    	cliRVO = clienteWS.authorizationRequest(autoReq.getCodModulo(), autoReq.getCodAgencia(), autoReq.getCodUsuario(), autoReq.getClave(), autoReq.getCodPerfil());
	    	LogginResultVO resRVO = (LogginResultVO) cliRVO.getObject();
	    	
	    	if (cliRVO.getCode().equals("1")){
	    		AuthorizationResponse autoRes = new AuthorizationResponse();
	    		autoRes.setEstado(resRVO.getState());
	    		autoRes.setMensaje(resRVO.getMessage());
	    		
	    	    res.setContentType("java-internal/" + AuthorizationResponse.class.getName());
				OutputStream out = res.getOutputStream();	    	
				ObjectOutputStream oos = new ObjectOutputStream(out);
				oos.writeObject(autoRes);
				oos.flush();
				oos.close(); 			
	    	}else{
	    		System.out.println(getClass().getName() + "." + "requireAuthorization: "+ cliRVO.getCode()+", "+ cliRVO.getMessage());
	    	}
		}catch(Exception e){
			System.out.println(getClass().getName() + "." + "requireAuthorization: "+ e.getMessage());
		}
	}
	
	private void requireAuthorizationByLimits(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try{
			InputStream in = req.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(in);
			AuthorizationRequest autoReq = new AuthorizationRequest();
			autoReq = (AuthorizationRequest) ois.readObject();      
		    in.close();	    	
    	
	        ClienteWS clienteWS = new ClienteWS();	       
	        ClientResultVO cliRVO = new ClientResultVO();	        
	    		    	
	    	cliRVO = clienteWS.limitRequest(autoReq.getCodModulo(), autoReq.getCodAgencia(), autoReq.getCodUsuario(), autoReq.getClave(), autoReq.getCodMoneda(),String.valueOf(autoReq.getImporte()));
	    	LogginResultVO resRVO = (LogginResultVO) cliRVO.getObject();
	    	
	    	if (cliRVO.getCode().equals("1")){
	    		AuthorizationResponse autoRes = new AuthorizationResponse();
	    		autoRes.setEstado(resRVO.getState());
	    		autoRes.setMensaje(resRVO.getMessage());
	    		
	    	    res.setContentType("java-internal/" + AuthorizationResponse.class.getName());
				OutputStream out = res.getOutputStream();	    	
				ObjectOutputStream oos = new ObjectOutputStream(out);
				oos.writeObject(autoRes);
				oos.flush();
				oos.close(); 			
	    	}else{
	    		System.out.println(getClass().getName() + "." + "requireAuthorizationByLimits: "+ cliRVO.getCode()+", "+ cliRVO.getMessage());
	    	}
		}catch(Exception e){
			System.out.println(getClass().getName() + "." + "requireAuthorizationByLimits: "+ e.getMessage());
		}
	}
	*/
	private void saveUsuario(LoginRequest loginRequest, LogginResult loginResult) throws IOException, SQLException {
		Connection conn = null;		
	    String sqlstmt = "";
	    PreparedStatement pstmt = null;	   
	    try{
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    	Calendar c1 = Calendar.getInstance(); 
	    	String fechaLogin = sdf.format(c1.getTime());
	    	
		    conn = DataSourceConnector.getInstance().getConnection();
		    sqlstmt = "select codusr from "+Constante.ESQUEMA1+".tusrdat where codusr = ?";
		    pstmt = conn.prepareStatement(sqlstmt);			    	
		    pstmt.setString(1,loginRequest.getCodUsuario());
		    ResultSet result = pstmt.executeQuery();
		    if (result.next()){ //Si existe...		    		  			
				sqlstmt = "update "+Constante.ESQUEMA1+".tusrdat set txtnam = ?, txtpas = ?, datbeg = ?, txtwks = ? where codusr = ?";
				pstmt = conn.prepareStatement(sqlstmt);					
				pstmt.setString(1,loginResult.getNameUser());
				pstmt.setString(2,Crypto.encriptaDES(loginRequest.getClave(), claveOffLine));
				pstmt.setString(3,fechaLogin); //Fecha
				pstmt.setString(4,""); //Sin estación
				pstmt.setString(5,loginRequest.getCodUsuario()); 
				pstmt.executeUpdate();
		    }else{
				sqlstmt = "insert into "+Constante.ESQUEMA1+".tusrdat (codusr, txtnam, txtpas, datbeg, txtwks) values (?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sqlstmt);	
				pstmt.setString(1,loginRequest.getCodUsuario());
				pstmt.setString(2,loginResult.getNameUser());
				pstmt.setString(3,Crypto.encriptaDES(loginRequest.getClave(), claveOffLine));
				pstmt.setString(4,fechaLogin); //Fecha
				pstmt.setString(5,""); //Sin estación
				pstmt.executeUpdate();		    	
		    }
	    }catch(SQLException e){
	    	// System.out.println(e.getMessage());
	    }catch(NamingException e){
	    	// System.out.println(e.getMessage());
	    }finally{
	    	try {	    		
	    		conn.close();
	    	}catch (SQLException e){
	    		// System.out.println(e.getMessage());
	    	}
	    }	    
	}
	
	
}
	