package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import java.sql.PreparedStatement;

import pe.cosapi.common.Constante;

import CosapiSoft.SARAWebBanking.*;
public class DatosDeMensajes {
	//
	private CosapiSoft.SARAWebBanking.InicioSesion login;
	//
	public java.util.Vector grid = null;
	private String status = "0";
	private String codgrp = "";
	private String codmsg = "";
	private String idemsg = "";
	private String desmsg = "";
	private String error = "";
	//
	private String des_msg = "";
	private String cod_msg = "";
	private String ide_msg = "";
/**
 * AyudaDeCampo constructor comment.
 */
public DatosDeMensajes() {
	super();
	grid = new java.util.Vector(1);
}
public void agregar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	int rowsAffected=0;
	try {
		error = "";
		if (!buscar(db)) {
			PreparedStatement preparedStatement= jd.getConnection().prepareStatement("INSERT INTO "+Constante.ESQUEMA1+".tmsgdat (codgrp, codmsg, desmsg, " + 
                  " idemsg) VALUES (?, ?, ?, ?)");
			preparedStatement.setString (1, getCodgrp());
	        preparedStatement.setString (2, getCodmsg());
	        preparedStatement.setString(3, getDesmsg());
	        preparedStatement.setString (4, getIdemsg());
	        rowsAffected = preparedStatement.executeUpdate ();
            preparedStatement.close ();
            // System.out.println();
            preparedStatement.toString();

/*
			String query = //
			"Insert into "+Constante.ESQUEMA1+".TMSGDAT values ('" + getCodgrp() + "', '" + getCodmsg() + "', '" + getDesmsg() + "', '" + getIdemsg() + "')";
			System.out.println("query............."+query);
			db.setQuery(query);
			db.executeUpdate();*/
	        
			String bef[] = {"", "", "", ""};
			String aft[] = {getCodgrp(), getCodmsg(), getIdemsg(), getDesmsg()};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Agregar", Constante.ESQUEMA1+".TMSGDAT", Table.getColumnsTmsgdat(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_MENSAJE_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getCodmsg() + str.substring(pos + 1);
		}
	} catch (Exception e) {
		e.printStackTrace();
		throw new Exception("\nAgregar - Datos de Mensajes \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public boolean buscar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		boolean sw = buscar(db);
		return sw;
	} catch (Exception e) {
		throw new Exception("\nBuscar Datos de Mensajes \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
private boolean buscar(JQuery db) throws Exception {
	try {
		String query = //
		"Select idemsg, desmsg " + //
		"From "+Constante.ESQUEMA1+".TMSGDAT " + //
		"Where codgrp = '" + getCodgrp() + "' " + //
		"And codmsg = '" + getCodmsg() + "' ";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		idemsg = db.stringValue(1);
		desmsg = db.stringValue(2);
	} catch (Exception e) {
		throw new Exception("\nBuscar - Datos de Mensajes \n " + e.getMessage());
	}
	return true;
}
public void consultar() throws Exception {
	try {
		String bef[] = {"", "", "", ""};
		String aft[] = {"", "", "", ""};
		LogOpe.saveLogManager(login.getNameProducto(), login.getUsuario(), "Consultar", Constante.ESQUEMA1+".TMSGDAT", Table.getColumnsTmsgdat(), bef, aft);
	} catch (Exception e) {
		throw new Exception(" \n Consultar - Datos de Mensajes \n" + e.getMessage());
	}
}
public void eliminar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		if (buscar(db)) {
			String query = //
			"Delete from "+Constante.ESQUEMA1+".TMSGDAT " + //
			"Where codgrp = '" + getCodgrp() + "' " + //
			"And codmsg = '" + getCodmsg() + "' ";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {getCodgrp(), getCodmsg(), getIdemsg(), getDesmsg()};
			String aft[] = {"", "", "", ""};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Eliminar", Constante.ESQUEMA1+".TMSGDAT", Table.getColumnsTmsgdat(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_MENSAJE_NO_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getCodmsg() + str.substring(pos + 1);
		}
	} catch (Exception e) {
		throw new Exception("\nEliminar - Datos de Mensajes \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public void eliminar(javax.servlet.http.HttpServletRequest req) throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		java.util.Enumeration vector = req.getParameterNames();
		while (vector.hasMoreElements() && vector != null) {
			codmsg = vector.nextElement().toString();
			String sw = req.getParameter(codmsg).toUpperCase();
			if (sw.equals("ON")) {
				if (buscar(db)) {
					String query = //
					"Delete from "+Constante.ESQUEMA1+".TMSGDAT " + //
					"Where codgrp = '" + getCodgrp() + "' " + //
					"And codmsg = '" + getCodmsg() + "' ";
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {getCodgrp(), getCodmsg(), getIdemsg(), getDesmsg()};
					String aft[] = {"", "", "", ""};
					LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Eliminar", Constante.ESQUEMA1+".TMSGDAT", Table.getColumnsTmsgdat(), bef, aft);
				}
			}
		}
		loadGrid(db);
	} catch (Exception e) {
		throw new Exception("\nEliminar Items de Ayuda \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_msg() {
	if (cod_msg == null)
		cod_msg = "";
	return cod_msg.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodgrp() {
	if (codgrp == null)
		codgrp = "";
	codgrp = codgrp.trim();
	if (!codgrp.equals(""))
		codgrp = CString.completeToBeginWithNChars(codgrp, 2, '0');
	return codgrp.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodmsg() {
	try {
		if (codmsg == null || codmsg.equals(""))
			codmsg = "0";
		codmsg = codmsg.trim();
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(codmsg)) {
			codmsg = "0";
		}
		int cod = new Integer(codmsg).intValue();
		if (cod <= 0) {
			JDatabase jd = new JDatabase();
			JQuery db = new JQuery(jd.getConnection());
			try {
				String query = //
				"Select codmsg " + //
				"From "+Constante.ESQUEMA1+".TMSGDAT " + //
				"Where codgrp = '" + getCodgrp() + "' " + //
				"Order by codmsg";
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					codmsg = db.stringValue(1).trim();
				}
				cod = new Integer(codmsg).intValue();
				codmsg = (cod >= 99999) ? "99999" : "" + (cod + 1);
			} catch (Exception ex) {
				// System.out.println(getClass().getName() + " - getCodmsg() --> " + ex.getMessage());
				return "-1";
			} finally {
				db.close();
				jd.close();
			}
		}
		if (!codmsg.equals(""))
			codmsg = CString.completeToBeginWithNChars(codmsg, 5, '0');
		return codmsg.trim();
	} catch (Exception e) {
		return "-1";
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getDes_msg() {
	return des_msg;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getDesmsg() {
	CString str = new CString(desmsg);
	str.arrange();
	return str.toString();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getError() {
	return error;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getIde_msg() {
	return ide_msg;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getIdemsg() {
	if (idemsg == null)
		idemsg = "";
	return idemsg.trim();
}
/**
 * This method was created in VisualAge.
 * @return CosapiSoft.SARAWebBanking.InicioSesion
 */
public CosapiSoft.SARAWebBanking.InicioSesion getLogin() {
	return login;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getStatus() {
	return status;
}
public String getUrlDatosDeMensajes() {
	return JspServlet.MENSAJES_DEL_APLICATIVO_SERVLET + "?BtnDatos=Buscar&TxtCodgrp=" + getCodgrp() + "&TxtCodmsg=";
}
public void loadGrid() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		loadGrid(db);
	} catch (Exception e) {
		throw new Exception("\nLoadGrid - Datos de Mensajes \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
private void loadGrid(JQuery db) throws Exception {
	grid = new java.util.Vector(10);
	try {
		String query = //
		"Select codgrp, codmsg, idemsg, desmsg " + //
		"From "+Constante.ESQUEMA1+".TMSGDAT " + //
		"Where codgrp = '" + getCodgrp() + "'" + //
		"Order by codgrp, codmsg, idemsg";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			status = "0";
			java.util.Vector row = new java.util.Vector(5);
			row.addElement(status);
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			row.addElement(db.stringValue(3));
			row.addElement(db.stringValue(4));
			grid.addElement(row);
		}
	} catch (Exception e) {
		throw new Exception("\nLoadGrid - Datos de Mensajes \n" + e.getMessage());
	}
}
public void modificar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		String antDesmsg = getDesmsg();
		String antIdemsg = getIdemsg();
		if (buscar(db)) {
			String query = //
			"Update "+Constante.ESQUEMA1+".TMSGDAT set " + //
			"idemsg = '" + antIdemsg + "', " + //
			"desmsg = '" + antDesmsg + "' " + //
			"Where codgrp = '" + getCodgrp() + "' " + //
			"And codmsg = '" + getCodmsg() + "' ";
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getCodgrp(), getCodmsg(), getIdemsg(), getDesmsg()};
			String aft[] = {getCodgrp(), getCodmsg(), antIdemsg, antDesmsg};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Modificar", Constante.ESQUEMA1+".TMSGDAT", Table.getColumnsTmsgdat(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_MENSAJE_NO_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getCodmsg() + str.substring(pos + 1);
		}
	} catch (Exception e) {
		throw new Exception("\nModificar - Datos de Mensajes \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public void next(int i) {
	status = ((java.util.Vector) grid.elementAt(i)).elementAt(0).toString();
	codgrp = ((java.util.Vector) grid.elementAt(i)).elementAt(1).toString();
	cod_msg = ((java.util.Vector) grid.elementAt(i)).elementAt(2).toString();
	ide_msg = ((java.util.Vector) grid.elementAt(i)).elementAt(3).toString();
	des_msg = ((java.util.Vector) grid.elementAt(i)).elementAt(4).toString();
}
public boolean nuevoCodigo(String cod) {
	if (grid == null || grid.size() == 0)
		return false;
	next(0);
	return (!getCodgrp().equals(cod));
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_msg(String newValue) {
	this.cod_msg = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodgrp(String newValue) {
	this.codgrp = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodmsg(String newValue) {
	this.codmsg = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setDes_msg(String newValue) {
	this.des_msg = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setDesmsg(String newValue) {
	this.desmsg = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setError(String newValue) {
	this.error = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setIde_msg(String newValue) {
	this.ide_msg = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setIdemsg(String newValue) {
	this.idemsg = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue CosapiSoft.SARAWebBanking.InicioSesion
 */
public void setLogin(CosapiSoft.SARAWebBanking.InicioSesion newValue) {
	this.login = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setStatus(String newValue) {
	this.status = newValue;
}
}