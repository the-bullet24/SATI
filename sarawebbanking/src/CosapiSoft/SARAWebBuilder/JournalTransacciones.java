package CosapiSoft.SARAWebBuilder;

import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
/**
 * This type was created in VisualAge.
 */
public class JournalTransacciones extends GenericTable {
	//
	public java.util.Vector diccionarios = null;
	//
	private String codtra = "";
	private String txttra = "";
	private String codchn = "";
	private String txtchn = "";
	private String codgrp = "";
	private String txtgrp = "";
	private String coddic = "";
	private String idefld = "";
	//
	private String cod_dic = "";
	private String ide_fld = "";
	private String txt_dic = "";
	private String cod_dic_nam = "";
/**
 * JournalTransacciones constructor comment.
 */
public JournalTransacciones() {
	super();
	diccionarios = new java.util.Vector(1);
}
/**
 * consult method comment.
 */
public void consult() throws java.sql.SQLException {
	try {
		String bef[] = {"", "", ""};
		String aft[] = {"", "", ""};
		LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", "tjoudic", Table.getColumnsTjoudic(), bef, aft);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... consult() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
/**
 * delete method comment.
 */
public void delete(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		if (search(db)) {
			String query = //
			"Delete from "+Constante.ESQUEMA1+".TJOUDIC Where codtra = '" + getCodtra() + "' And coddic = '" + getCoddic() + "'";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {getCodtra(), getCoddic(), getIdefld()};
			String aft[] = {"", "", ""};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TJOUDIC", Table.getColumnsTjoudic(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			/*String str = Mensajes.getMessage(Mensajes.CODIGO_DE_METODO_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodmet() + str.substring(pos + 1));*/
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
/**
 * delete method comment.
 */
public void delete(JQuery db, javax.servlet.http.HttpServletRequest req) throws javax.servlet.ServletException, java.sql.SQLException {
	try {
		setError("");
		java.util.Enumeration vector = req.getParameterNames();
		while (vector.hasMoreElements() && vector != null) {
			coddic = vector.nextElement().toString();
			String sw = req.getParameter(coddic).toUpperCase();
			if (sw.equals("ON")) {
				if (search(db)) {
					setGrid(null);
					String query = //
					"Delete from "+Constante.ESQUEMA1+".TJOUDIC Where codtra = '" + getCodtra() + "' And coddic = '" + getCoddic() + "'";
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {getCodtra(), getCoddic(), getIdefld()};
					String aft[] = {"", "", ""};
					LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TJOUDIC", Table.getColumnsTjoudic(), bef, aft);
				}
			}
		}
		loadGrid(db);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_dic() {
	return cod_dic;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_dic_nam() {
	return cod_dic_nam;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodchn() {
	return codchn;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCoddic() {
	return coddic;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodgrp() {
	return codgrp;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodtra() {
	return codtra;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getIde_fld() {
	return ide_fld;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getIdefld() {
	return idefld;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNameTable() {
	return "tjoutra";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxt_dic() {
	return txt_dic;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxtchn() {
	return txtchn;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxtgrp() {
	return txtgrp;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxttra() {
	return txttra;
}
/**
 * getUrl method comment.
 */
public String getUrl() {
	return JspServlet.JOURNAL_TRANSACCIONES_SERVLET + "?BtnTrx=Buscar&TxtCoddic=" + getCod_dic();
}
/**
 * insert method comment.
 */
public void insert(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		if (!search(db)) {
			String query = //
			"Insert into "+Constante.ESQUEMA1+".TJOUDIC (codtra, coddic, idefld) values ('" + getCodtra() + "', '" + getCoddic() + "', '" + getIdefld() + "')";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", "", ""};
			String aft[] = {getCodtra(), getCoddic(), getIdefld()};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TJOUDIC", Table.getColumnsTjoudic(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			/*String str = Mensajes.getMessage(Mensajes.CODIGO_DE_METODO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodmet() + str.substring(pos + 1));*/
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... insert() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 * @exception java.sql.SQLException The exception description.
 */
public void loadDiccionarios() throws java.sql.SQLException {
	JDatabase jd = null;
	JQuery db = null;
	try {
		diccionarios = new java.util.Vector(100, 100);
		jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
		db = new JQuery(jd.getConnection());
		String query = //
		"Select a.coddic, b.txtdes " + //
		"From "+Constante.ESQUEMA1+".tctrtra a, "+Constante.ESQUEMA1+".tdicdat b " + //
		"Where a.coddic = b.coddic " + //
		"And a.codtra = '" + getCodtra() + "' " + //
		"Order by a.coddic";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector(3);
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			diccionarios.addElement(row);
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... loadDiccionarios() - " + getClass().getName() + " -> " + e.getMessage());
	}
	finally {
		if (db != null)
			db.close();
		if (jd != null)
			jd.close();
	}
}
/**
 * loadGrid method comment.
 */
public void loadGrid(JQuery db) throws java.sql.SQLException {
	try {
		setIndex(0);
		if (getGrid().size() == 0) {
			String query = //
			"Select a.coddic, b.txtdes, a.idefld " + //
			"From "+Constante.ESQUEMA1+".TJOUDIC a, "+Constante.ESQUEMA1+".tdicdat b " + //
			"Where a.codtra = '" + getCodtra() + "' " + //
			"And a.coddic = b.coddic " + //
			"Order by a.coddic";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector row = new java.util.Vector(4);
				row.addElement(getStatus());
				row.addElement(db.stringValue(1));
				row.addElement(db.stringValue(2));
				row.addElement(db.stringValue(3));
				getGrid().addElement(row);
			}
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... loadGrid() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
/**
 * loadGrid method comment.
 */
public void loadGridChild(JQuery db) throws java.sql.SQLException {
}
/**
 * next method comment.
 */
public void next(int index) {
	setStatus(((java.util.Vector) getGrid().elementAt(index)).elementAt(0).toString());
	cod_dic = ((java.util.Vector) getGrid().elementAt(index)).elementAt(1).toString();
	txt_dic = ((java.util.Vector) getGrid().elementAt(index)).elementAt(2).toString();
	ide_fld = ((java.util.Vector) getGrid().elementAt(index)).elementAt(3).toString();
	cod_dic_nam = cod_dic + " - " + txt_dic;
}
/**
 * next method comment.
 */
public void nextChild(int index) {
}
/**
 * This method was created in VisualAge.
 * @param i int
 */
public void nextDiccionario(int i) {
	cod_dic = ((java.util.Vector) diccionarios.elementAt(i)).elementAt(0).toString();
	txt_dic = ((java.util.Vector) diccionarios.elementAt(i)).elementAt(1).toString();
	cod_dic_nam = cod_dic + " - " + txt_dic;
}
/**
 * search method comment.
 */
public boolean search(JQuery db) throws java.sql.SQLException {
	try {
		String query = //
		"Select idefld " + //
		"From "+Constante.ESQUEMA1+".TJOUDIC " + //
		"Where codtra = '" + getCodtra() + "' " + //
		"And coddic = '" + getCoddic() + "' ";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		idefld = db.stringValue(1);
		return true;
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... search() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_dic(String newValue) {
	this.cod_dic = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_dic_nam(String newValue) {
	this.cod_dic_nam = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodchn(String newValue) {
	this.codchn = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCoddic(String newValue) {
	this.coddic = newValue;
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
public void setCodtra(String newValue) {
	this.codtra = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setIde_fld(String newValue) {
	this.ide_fld = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setIdefld(String newValue) {
	this.idefld = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxt_dic(String newValue) {
	this.txt_dic = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxtchn(String newValue) {
	this.txtchn = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxtgrp(String newValue) {
	this.txtgrp = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxttra(String newValue) {
	this.txttra = newValue;
}
/**
 * sort method comment.
 */
public void sort(JQuery db) throws java.sql.SQLException {
}
/**
 * update method comment.
 */
public void update(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		String antIdefld = getIdefld();
		if (search(db)) {
			String query = //
			"Update "+Constante.ESQUEMA1+".TJOUDIC set " + //
			"idefld = '" + antIdefld + "' " + //
			"Where codtra = '" + getCodtra() + "' " + //
			"And coddic = '" + getCoddic() + "' ";
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getCodtra(), getCoddic(), getIdefld()};
			String aft[] = {getCodtra(), getCoddic(), antIdefld};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Modificar",Constante.ESQUEMA1+".TJOUDIC", Table.getColumnsTjoudic(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			/*String str = Mensajes.getMessage(Mensajes.CODIGO_DE_chnODO_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodchn() + str.substring(pos + 1));*/
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... update() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}