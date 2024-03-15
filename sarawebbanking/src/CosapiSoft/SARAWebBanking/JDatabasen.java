package CosapiSoft.SARAWebBanking;

/**
 * Internal utility class for database viewer bean.
 * Clase para obtener una conexión a la Base de Datos
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
public class JDatabasen {
	private String driver = "";
	private String url = "";
	private String user = "";
	private String password = "";
	private Connection connection = null;
	private String error = "";
public JDatabasen() {
	this.error = "";
}
/**
 * Constructor : Asigna propiedades para una conexión de Base de Datos
 */
public JDatabasen(String driver,String database,String user, String password) {
	this.error = "";
	this.setDriver(driver);
	this.setDatabase(database);
	this.setUser(user);
	this.setPassword(password);
}
/**
 * Constructor : Asigna una conexión de Base de Datos
 */
public JDatabasen(Connection connection) {
	this.connection = connection;
	this.error = "";
}
/**
 * Limpia el error
 */
public void clearError(){
	this.error = "";
}
/**
 * Cierra la conexión a la Base de Datos
 */
public void close() throws SQLException {
	this.clearError();
	try {
		if (connection != null) {
			connection.close();
			connection = null;
		}
	}
	catch (SQLException e) {
		connection = null;
		this.errorMethod("close()");
		this.error += "\n" + e.getMessage();
		throw new SQLException(this.getError());
	}
}
/**
 * Realiza el commit de las transacciones en la Base de Datos
 */
public void commit() throws SQLException{
	this.clearError();
	try {
		getConnection().commit();
	} catch (SQLException e) {
		this.errorMethod("commit()");
		this.error += "\n" + e.getMessage();
		throw( new SQLException(this.getError()));
	}
}
private void errorMethod(String metodo) {
	this.error += "\nMétodo : " + metodo + " --- Clase : "+this.getClass().getName();
}
/**
 * Devuelve un valor booleano si está habilitado el commit automático
 */
public synchronized boolean getAutoCommit() throws SQLException{
	this.clearError();
	try {
		return getConnection().getAutoCommit();
	} catch (SQLException e) {
		this.errorMethod("getAutoCommit()");
		this.error += "\n" + e.getMessage();
		throw new SQLException(this.getError());
	}
}
/**
 * Devuelve la conexión
 */
public Connection getConnection() throws SQLException {
	this.clearError();
	/*try {
		if (connection == null)
			open();
	} catch (SQLException e) {
		this.errorMethod("getConnection()");
		this.error += "\n" + e.getMessage();
		throw (new SQLException(this.getError()));
	}*/
	try {
	java.util.Properties parms = new java.util.Properties();
	//parms.setProperty(Context.INITIAL_CONTEXT_FACTORY,"com.ibm.websphere.naming.WsnInitialContextFactory");

	//javax.naming.Context ctx = new javax.naming.InitialContext(parms);
	javax.naming.Context ctx = new javax.naming.InitialContext(parms);
	
	//TODO
	//javax.sql.DataSource ds =(javax.sql.DataSource)ctx.lookup("jdbc/dbsara10");
	//YADDIF A01
	javax.sql.DataSource ds =(javax.sql.DataSource)ctx.lookup("jdbc/dbJT");
	//javax.sql.DataSource ds =(javax.sql.DataSource)ctx.lookup("java:comp/env/jdbc/dbJT");
	
	connection = ds.getConnection();

	return connection;
	} catch (Exception e) {
		this.errorMethod("getConnection()");
		this.error += "\n" + e.getMessage();
		throw (new SQLException(this.getError()));
	}
}
/**
 * Devuelve la Base de Datos
 */
public String getDatabase(){
	return url;
}
/**
 * Devuelve el driver de la Base de Datos
 */
public String getDriver(){
	return driver;
}
/**
 * Devuelve el error obtenido durante la conexión
 */
public String getError(){
	return this.error;
}
/**
 * Devuelve la Meta Data
 */
public DatabaseMetaData getMetaData() throws SQLException{
	this.clearError();
	try {
		return getConnection().getMetaData();
	} catch (SQLException e) {
		this.errorMethod("getMetaData()");
		this.error += "\n" + e.getMessage();
		throw new SQLException(this.getError());
	}
}
/**
 * Devuelve el Password
 */
public String getPassword(){
	return password;
}
/**
 * Devuelve el Usuario
 */
public String getUser(){
	return user;
}
/**
 * Asigna una conexión a la Base de datos
 */
private void open() throws SQLException{
	this.clearError(); 
	try {
		Class.forName(driver);
	} catch (Exception ex) {
		this.errorMethod("open()");
		this.error += "\nError al cargar driver --- driver = " + getDriver();
		this.error += "\n" + ex.getMessage();
		connection = null;
		throw new SQLException(this.getError());
	}
	java.util.Properties info = new java.util.Properties();
	info.put("user", getUser());
	info.put("password", getPassword());
	try {
		Connection con = DriverManager.getConnection(getDatabase(), info);
		connection = con;
		return;
	} catch (SQLException ex) {
		this.errorMethod("open()");
		this.error += "\nPrimer Intento";
		this.error += "\nError al conectarse a la Base de Datos";
		this.error += "\nDriver = " + getDriver();
		this.error += "\nUrl = " + getDatabase();
		this.error += "\nUser = " + getUser();
		this.error += "\nPassword = "+ getPassword();
		this.error += "\n" + ex.getMessage();
		connection = null;
	}
	try {
		Thread.sleep(1000);
		Connection con = DriverManager.getConnection(url, info);
		connection = con;
		return;
	} catch (SQLException sx) {
		this.errorMethod("open()");
		this.error += "\nSegundo Intento";
		this.error += "\nError al conectarse a la Base de Datos";
		this.error += "\nDriver = " + getDriver();
		this.error += "\nUrl = "+getDatabase();
		this.error += "\nUser = "+getUser();
		this.error += "\nPassword = "+ getPassword();
		this.error += "\n" + sx.getMessage();
		connection = null;
		throw new SQLException(this.getError());
	} catch (Exception ex) {
		System.err.println("Exception : " + ex.getMessage());
		ex.printStackTrace();
		connection = null;
	}
}
/**
 * Realiza el rollback en la Base de Datos
 */
public void rollback() throws SQLException{
	try {
		getConnection().rollback();
	} catch (SQLException e) {
		this.errorMethod("rollback()");
		this.error += "\n" + e.getMessage();
		throw new SQLException(this.getError());
	}
}
/**
 * Asigna el commit automático
 */
public void setAutoCommit(boolean commit) throws SQLException{
	this.clearError();
	try {
		getConnection().setAutoCommit(commit);
	} catch (SQLException e) {
		this.errorMethod("setAutoCommit(" + commit + ")");
		this.error += "\n" + e.getMessage();
		throw new SQLException(this.getError());
	}
}
/**
 * Asigna nueva conexión a la Base de Datos
 */
public void setConnection(Connection connection) throws SQLException {
	this.clearError();
	if (connection == null) {
		this.errorMethod("setConnection(" + connection + ")");
		throw (new SQLException(this.getError()));
	}
	this.connection = connection;
}
/**
 * Asigna la Base de Datos
 */
public void setDatabase(String url) {
	this.url = url;
}
/**
 * Asigna Driver de la Base de Datos
 */
public void setDriver(String driver) {
	this.driver = driver;
}
/**
 * Asigna Password de la Base de Datos
 */
public void setPassword(String password) {
	this.password = password;
}
/**
 * Asigna Usuario de la Base de Datos
 */
public void setUser(String user) {
	this.user = user;
}
public String toString() {
	return "[Driver : " + driver + ", Url : " + url + ", User : " + user + ", Password : " + password + "]";
}
}