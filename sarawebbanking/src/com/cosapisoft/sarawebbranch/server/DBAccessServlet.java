package com.cosapisoft.sarawebbranch.server;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cosapisoft.sarawebbranch.common.beans.DBAccessRequest;
import com.cosapisoft.sarawebbranch.common.beans.DBAccessResponse;
import com.cosapisoft.sarawebbranch.common.beans.DBQuery;
import com.cosapisoft.sarawebbranch.common.beans.SecurityBranch;
import com.cosapisoft.sarawebmanager.security.beans.*;

/**
 * @version 	1.0
 * @author
 */
public class DBAccessServlet extends HttpServlet {
	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		performTask(req, resp);
	}

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		performTask(req, resp);

	}
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		requireDBAccess(req, res);
	}
	
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
	
	private void requireDBAccess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try{
			Connection conn = null;		
			InputStream in = req.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(in);
			DBAccessRequest autoReq = new DBAccessRequest();
			PreparedStatement pstmt = null;	     
			autoReq = (DBAccessRequest) ois.readObject();      
		    in.close();	    	
		    		    
		    conn = DataSourceConnector.getInstance().getConnection();
//		    conn.setAutoCommit(false); 

		    ArrayList Querys = autoReq.getQuerys();
		    
//		    int listQuerys = 0;	    	
//		    listQuerys  = Querys.size();	    	
//		    if(listQuerys!=0){
//			    for(int i=0; i<=listQuerys-1; i++){		
//			    	DBQuery Querys = (DBQuery) Querys.get(i);
//
//			    	pstmt = conn.prepareStatement(Querys.getQuery());			    	
//			    	ResultSet result = pstmt.executeQuery();
//			    	if (result.next()){
//			    		
//			    	}
//			    }
//		    }
		    
			java.util.Iterator it = Querys.iterator();
			DBAccessResponse Respuesta = new DBAccessResponse(); 
			while (it.hasNext()){
				DBQuery Query = (DBQuery) it.next();
				pstmt = conn.prepareStatement(Query.getQuery());
				
				if (Query.getTipoQuery() == DBQuery.TIPO_SELECT){
					ResultSet result = pstmt.executeQuery();				
					ResultSetMetaData rm = result.getMetaData();
					String tmp = "";
					Vector vTabla = new Vector();
					while (result.next()) {
						Vector vFila = new Vector();
						for (int i = 1; i <= rm.getColumnCount(); i++) {
							tmp = result.getString(i);
							vFila.add(tmp);
						}
						vTabla.add(vFila);
					}
					Respuesta.setResultado(vTabla);
				}
				if (Query.getTipoQuery() == DBQuery.TIPO_UPDATE){
					int rows = pstmt.executeUpdate();
				}
				if (Query.getTipoQuery() == DBQuery.TIPO_INSERT){
					int rows = pstmt.executeUpdate();
				}
				if (Query.getTipoQuery() == DBQuery.TIPO_DELETE){
					int rows = pstmt.executeUpdate();
				}
			}
			
    	    res.setContentType("java-internal/" + DBAccessResponse.class.getName());
    	    OutputStream out = res.getOutputStream();	    	
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(Respuesta);
			oos.flush();
			oos.close(); 			
		}catch(Exception e){
			// System.out.println(getClass().getName() + "." + "requireDBAccess: "+ e.getMessage());
		}
	}
}
	