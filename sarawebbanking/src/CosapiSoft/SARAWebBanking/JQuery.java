package CosapiSoft.SARAWebBanking;

/**
 * Internal utility class for database viewer bean.
 * Clase que se utiliza para ejecutar consultas, modificaciones e inserciones
 * la Base de Datos
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JQuery {
	
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultset = null;
	private String query = "";
	private String error = "";
public JQuery(){
	this.error = "";
}
/**
 * Constructor : Asigna una conexión de Base de Datos
 */
public JQuery(Connection connection) {
	this.error = "";
	this.connection = connection;
}
/**
 * Limpia el error de las consultas a la Base de Datos
 */
public void clearError(){
	this.error = "";
}
/**
 * Cierra el cursor (resultados de una Consulta)
 */
public void close() throws SQLException {
	try {
		if (getResultSet() != null)
			getResultSet().close();
		if (getStatement() != null)
			getStatement().close();
	} catch (SQLException e) {
		this.errorMethod("close()");
		this.error += e.getMessage();
		throw new SQLException(this.getError());
	}
}
public double doubleValue(int nameField) throws SQLException {
	this.clearError();
	String field = "";
	try {
		field = getResultSet().getString(nameField);
		if (field == null)
			return 0;
		field = field.trim();
		if (field.equals(""))
			return 0;
		return new Double(field).doubleValue();
	} catch (java.sql.SQLException e) {
		this.errorMethod("doubleValue(" + nameField + ")");
		this.error += "\nPara el query : " + this.getQuery();
		this.error += "\n" + e.getMessage();
		throw new SQLException(this.getError());
	}
}
public double doubleValue(String nameField) throws SQLException {
	this.clearError();
	String field = "";
	try {
		field = getResultSet().getString(nameField);
		if (field == null)
			return 0;
		field = field.trim();
		if (field.equals(""))
			return 0;
		return new Double(field).doubleValue();
	} catch (java.sql.SQLException e) {
		this.errorMethod("doubleValue(" + nameField + ")");
		this.error += "\nPara el query : " + this.getQuery();
		this.error += "\n" + e.getMessage();
		throw new SQLException(this.getError());
	}
}
private void errorMethod(String metodo) {
	this.error += "\nMétodo : " + metodo + " --- Clase : "+this.getClass().getName();
}
/**
 * Ejecuta una consulta
 */
public void executeQuery() throws SQLException {
	try {
		this.statement = getConnection().createStatement();
		this.resultset = statement.executeQuery(query);
		return;
	} catch (Exception e) {
		this.clearError();
		this.errorMethod("executeQuery()");
		this.error += "\nConexión :" + this.getConnection();
		this.error += "\nQuery : \n" + this.getQuery();
		this.error += "\nStatement : " + this.getStatement();
		this.error += "\nResulset : " + this.getResultSet();
		this.error += "\n" + e.getMessage();
		throw new SQLException(this.getError());
	}
}
/**
 * Ejecuta update, insert, create, etc 
 */
public int executeUpdate() throws SQLException {
	try {
		this.statement = getConnection().createStatement();
		int nreg = this.statement.executeUpdate(getQuery());
		return nreg;
	} catch (Exception e) {
		this.clearError();
		this.errorMethod("executeUpdate()");
		this.error += "\nPara la conexión : " + getConnection();
		this.error += "\nPara el query : \n" + getQuery();
		this.error += "\nStatement : " + this.getStatement();
		this.error += "\n" + e.getMessage();
		throw new SQLException(this.error);
	}
}
/**
 * Devuelve la conexión a la Base de Datos
 */
public Connection getConnection() {
	return connection;
}
/**
 * Devuelve el error obtenido en alguna consulta a la Base de Datos
 */
public String getError(){
	return this.error;
}
public String getQuery() {
	return query;
}
public ResultSet getResultSet(){
	return resultset;
}
public Statement getStatement(){
	return statement;
}
public int intValue(int nameField) throws SQLException {
	this.clearError();
	String field = "";
	try {
		field = getResultSet().getString(nameField);
		if (field == null)
			return 0;
		field = field.trim();
		if (field.equals(""))
			return 0;
		return new Integer(field.trim()).intValue();
	} catch (java.sql.SQLException e) {
		this.errorMethod("intValue(" + nameField + ")");
		this.error += "\nPara el query : " + query;
		this.error += "\n" + e.getMessage();
		throw new SQLException(this.getError());
	}
}
public int intValue(String nameField) throws SQLException {
	this.clearError();
	String field = "";
	try {
		field = getResultSet().getString(nameField);
		if (field == null)
			return 0;
		field = field.trim();
		if (field.equals(""))
			return 0;
		return new Integer(field.trim()).intValue();
	} catch (java.sql.SQLException e) {
		this.errorMethod("intValue(" + nameField + ")");
		this.error += "\nPara el query : " + query;
		this.error += "\n" + e.getMessage();
		throw new SQLException(this.getError());
	}
}
public void setAutoCommit(boolean commit) throws SQLException {
	this.clearError();
	try {
		getConnection().setAutoCommit(commit);
	} catch (SQLException e) {
		this.errorMethod("setAutoCommit(" + commit + ")");
		this.error += "\nConexión = " + this.getConnection();
		this.error += "\n" + e.getMessage();
		throw new SQLException(this.getError());
	}
}
public void setConnection(Connection con) throws SQLException {
	this.clearError();
	if (con == null) {
		this.errorMethod("setConnection(" + con + ")");
		throw new SQLException();
	}
	this.connection = con;
}
public void setQuery(String query) {
	this.query = query;
}
public void setResultSet(ResultSet resultset) {
	this.resultset = resultset;
}

public String stringValue(int nameField) throws SQLException {
	this.clearError();
	String field = "";
	try {
		field = getResultSet().getString(nameField);
		if (field == null)
			return "";
		return field;
	} catch (java.sql.SQLException e) {
		this.errorMethod("stringValue(" + nameField + ")");
		this.error += "\nPara el query : " + this.getQuery();
		this.error += "\n" + e.getMessage();
		throw new SQLException(this.getError());
	}
}
public String stringValue(String nameField) throws SQLException {
	this.clearError();
	String field = "";
	try {
		field = getResultSet().getString(nameField);
		if (field == null)
			return "";
		return field;
	} catch (java.sql.SQLException e) {
		this.errorMethod("stringValue(" + nameField + ")");
		this.error += "\nPara el query : " + this.getQuery();
		this.error += "\n" + e.getMessage();
		throw new SQLException(this.getError());
	}
}

public byte[] arrByteValue(String nameField) throws SQLException {
	this.clearError();
	byte[] b = null;
	try {
		b = getResultSet().getBytes(nameField);
		if (b == null)
			return null;
		return b;
	} catch (java.sql.SQLException e) {
		System.out.println("entro 1.- SQLException...");
		this.errorMethod("arrByteValue(" + nameField + ")");
		this.error += "\nPara el query : " + this.getQuery();
		this.error += "\n" + e.getMessage();
		System.out.println(""+e);
		throw new SQLException(this.getError());
	}
}

public byte[] arrByteValue(int nameField) throws SQLException {
	this.clearError();
	byte[] b = null;
	try {
		b = getResultSet().getBytes(nameField);
		if (b == null)
			return null;
		return b;
	} catch (java.sql.SQLException e) {
		System.out.println("entro 2.- SQLException...");
		this.errorMethod("arrByteValue(" + nameField + ")");
		this.error += "\nPara el query : " + this.getQuery();
		this.error += "\n" + e.getMessage();
		System.out.println(""+e);
		throw new SQLException(this.getError());
	}
}

public Object getObjectValue(int nameField) throws SQLException {
	this.clearError();
	Object obj = null;
	try {
		obj = getResultSet().getObject(nameField);
		if (obj == null)
			return null;
		return obj;
	} catch (java.sql.SQLException e) {
		System.out.println("entro 3.- SQLException...");
		this.errorMethod("getObjectValue(" + nameField + ")");
		this.error += "\nPara el query : " + this.getQuery();
		this.error += "\n" + e.getMessage();
		System.out.println(""+e);
		throw new SQLException(this.getError());
	}
}

public String toString() {
	return super.toString() + connection.toString();
}
}