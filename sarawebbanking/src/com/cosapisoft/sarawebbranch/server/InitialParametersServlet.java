package com.cosapisoft.sarawebbranch.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cosapisoft.sarawebbranch.common.beans.InitialParametersInfo;

public class InitialParametersServlet extends HttpServlet implements Servlet, Serializable {
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public InitialParametersServlet() {
		super();
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		performTask(arg0, arg1);
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		performTask(arg0, arg1);
	}
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    try{
	    	InitialParametersInfo parametrosIniciales = new InitialParametersInfo();
	        String nombreEstacion = "";
	        //Obtengo objeto enviado por la estacion
			InputStream in = req.getInputStream();
		    ObjectInputStream ois = new ObjectInputStream(in);
	    	nombreEstacion = (String) ois.readObject();      
		    in.close();
		    

		    //loadAgenciaParameters(parametrosIniciales);

		    //Envío objeto esperado por la estación
		    res.setContentType("java-internal/" + InitialParametersInfo.class.getName());//Buscar otra forma!!!!
		    OutputStream out = res.getOutputStream();
		    ObjectOutputStream oos = new ObjectOutputStream(out);
		    oos.writeObject(parametrosIniciales);
		    oos.flush();
		    oos.close();
		    
	    }catch (ClassNotFoundException e){
	    	// System.out.println(e.getMessage());
	    }catch (Exception e){
	    	// System.out.println(e.getMessage());
	    }
	}

	

	
	public static void loadAgenciaParameters(InitialParametersInfo parametrosIniciales) throws SQLException{	
		Connection conn = null;	
		String sqlstmt = "";
		java.sql.Statement stm = null;
	
		try {
			// System.out.println("Carga de parametros de Agencia...");
			//PARAMETROS DESDE BD...
			sqlstmt = "SELECT A.CODBRA, A.TXTCONFIGTRXCLAVES, A.TYPENCRIPCION FROM PV_SWB.TBRAINF A";
			conn = DataSourceConnector.getInstance().getConnection();
			stm = conn.createStatement();
			// System.out.println("Consulta a la Base de Datos...");
	    	ResultSet result = stm.executeQuery(sqlstmt);
	    	// System.out.println("Consulta a la Base de Datos finalizada");
	    	if (result.next()){
	    		parametrosIniciales.setCodAgencia(result.getString("CODBRA"));
				parametrosIniciales.setConfiguracionTrxClaves(result.getString("TXTCONFIGTRXCLAVES"));
				parametrosIniciales.setTipoEncripcion(result.getString("TYPENCRIPCION"));
	    	}
	    	// System.out.println("Carga de parametros de Agencia finalizada");
		}catch (SQLException e){
			// System.out.println(e.getMessage());
		}catch (NamingException e){
			// System.out.println(e.getMessage());
		} finally{
	    	try {
	    		conn.close();
	    	}catch (SQLException e){
	    		// System.out.println(e.getMessage());
	    	}
		}
	}

}