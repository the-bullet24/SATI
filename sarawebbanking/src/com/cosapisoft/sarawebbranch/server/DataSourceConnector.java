package com.cosapisoft.sarawebbranch.server;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceConnector {
	private static DataSourceConnector instance = null;
	private static DataSourceConnector instanceLOG = null;
	private Context context;
	private DataSource ds;
	
	private DataSourceConnector() throws NamingException{
		context = new InitialContext();
		//ds = (DataSource)context.lookup("java:comp/env/jdbc/dbsara10"); //WASCE
		//ds = (DataSource)context.lookup("jdbc/dbsara10"); //WAS
		ds = (DataSource)context.lookup("jdbc/dbSWB"); //WAS
		
	}
	
	private DataSourceConnector(String LOG) throws NamingException{
		context = new InitialContext();
		//ds = (DataSource)context.lookup("java:comp/env/jdbc/dbsara10"); //WASCE
		//ds = (DataSource)context.lookup("jdbc/dbsara10"); //WAS
		ds = (DataSource)context.lookup(LOG); //WAS
		
	}

	public static DataSourceConnector getInstance() throws NamingException{
		if (instance == null){
			instance = new DataSourceConnector();
		}
		return instance;
	}
	
	public static DataSourceConnector getInstance(String LOG) throws NamingException{
		if (instanceLOG == null){
			instanceLOG = new DataSourceConnector(LOG);
		}
		return instanceLOG;
	}

	public Connection getConnection() throws SQLException{
		Connection conn = ds.getConnection();
		return conn;
	}

	public void setJNDIName(String name){
		
	}
}
