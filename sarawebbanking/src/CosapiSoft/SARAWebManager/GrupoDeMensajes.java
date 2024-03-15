package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class GrupoDeMensajes extends GenericTable {
	//
	private String codgrp = "";
	private String txtdes = "";
	private String cod_grp = "";
	private String txt_des = "";
/**
 * AyudaDeCampo constructor comment.
 */
public GrupoDeMensajes() {
	super();
}
public void consult() throws java.sql.SQLException {
	try {
		String bef[] = {"", ""};
		String aft[] = {"", ""};
		LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", Constante.ESQUEMA1+".TGRPMSG", Table.getColumnsTgrpmsg(), bef, aft);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... consult() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public void delete(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		if (search(db)) {
			String query = //
			"Delete from "+Constante.ESQUEMA1+".TMSGDAT Where codgrp = '" + getCodgrp() + "'";
			db.setQuery(query);
			db.executeUpdate();
			query = //
			"Delete from "+Constante.ESQUEMA1+".TGRPMSG Where codgrp = '" + getCodgrp() + "'";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {getCodgrp(), getTxtdes()};
			String aft[] = {"", ""};
			LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TGRPMSG", Table.getColumnsTgrpmsg(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_GRUPO_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodgrp() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public void delete(JQuery db, javax.servlet.http.HttpServletRequest req) throws java.sql.SQLException, javax.servlet.ServletException {
	try {
		setError("");
		java.util.Enumeration vector = req.getParameterNames();
		while (vector.hasMoreElements() && vector != null) {
			codgrp = vector.nextElement().toString();
			String sw = req.getParameter(codgrp).toUpperCase();
			if (sw.equals("ON")) {
				if (search(db)) {
					String query = //
					"Delete from "+Constante.ESQUEMA1+".TMSGDAT Where codgrp = '" + codgrp + "'";
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from "+Constante.ESQUEMA1+".TGRPMSG Where codgrp = '" + codgrp + "'";
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {codgrp, getTxtdes()};
					String aft[] = {"", ""};
					LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TGRPMSG", Table.getColumnsTgrpmsg(), bef, aft);
					setGrid(null);
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
public String getCod_grp() {
	if (cod_grp == null)
		cod_grp = "";
	return cod_grp.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodgrp() {
	try {
		if (codgrp == null || codgrp.equals(""))
			codgrp = "0";
		codgrp = codgrp.trim();
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(codgrp)) {
			codgrp = "0";
		}
		int cod = new Integer(codgrp).intValue();
		if (cod <= 0) {
			JDatabase jd = new JDatabase();
			JQuery db = new JQuery(jd.getConnection());
			try {
				String query = //
				"Select codgrp " + //
				"From "+Constante.ESQUEMA1+".TGRPMSG " + //
				"Order by codgrp";
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					codgrp = db.stringValue(1).trim();
				}
				cod = new Integer(codgrp).intValue();
				codgrp = (cod >= 9999) ? "9999" : "" + (cod + 1);
			} catch (Exception ex) {
				// System.out.println(getClass().getName() + " - getCodgrp() --> " + ex.getMessage());
				return "-1";
			} finally {
				db.close();
				jd.close();
			}
		}
		if (!codgrp.equals(""))
			codgrp = CString.completeToBeginWithNChars(codgrp, 2, '0');
		return codgrp.trim();
	} catch (Exception e) {
		return "-1";
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNameTable() {
	return Constante.ESQUEMA1+".TGRPMSG";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxt_des() {
	if (txt_des == null || txt_des.equals(""))
		txt_des = "---";
	CString str = new CString(txt_des);
	str.arrange();
	return str.toString();
}
/**
 * This method was created in VisualAge.
 * @return String
 */
public String getTxtdes() {
	CString str = new CString(txtdes);
	str.arrange();
	return str.toString();
}
public String getUrl() {
	return getUrlGrupoDeMensajes();
}
public String getUrlGrupoDeMensajes() {
	return JspServlet.GRUPO_DE_MENSAJES_SERVLET + "?BtnGrp=Buscar&TxtCodgrp=";
}
public void insert(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		if (!search(db)) {
			String query = //
			"Insert into "+Constante.ESQUEMA1+".TGRPMSG (codgrp, txtdes) values ('" + getCodgrp() + "', '" + getTxtdes() + "')";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", ""};
			String aft[] = {getCodgrp(), getTxtdes()};
			LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TGRPMSG", Table.getColumnsTgrpmsg(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_GRUPO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodgrp() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... insert() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public void loadGrid(JQuery db) throws java.sql.SQLException {
	setIndex(0);
	if (getGrid().size() == 0) {
		try {
			String query = //
			"Select codgrp, txtdes " + //
			"From "+Constante.ESQUEMA1+".TGRPMSG " + //
			"Order by codgrp";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector row = new java.util.Vector(3);
				row.addElement(getStatus());
				row.addElement(db.stringValue(1));
				row.addElement(db.stringValue(2));
				getGrid().addElement(row);
			}
		}
		catch (Exception e) {
			throw new java.sql.SQLException("\n... loadGrid() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
}
public void loadGridChild(JQuery db) throws java.sql.SQLException {
}
public void next(int i) {
	setStatus(((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString());
	cod_grp = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
	txt_des = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
}
public void nextChild(int i) {
}
public boolean search(JQuery db) throws java.sql.SQLException {
	try {
		String query = //
		"Select txtdes " + //
		"From "+Constante.ESQUEMA1+".TGRPMSG " + //
		"Where codgrp = '" + getCodgrp() + "'";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next()) {
			//			error = "Código " + getCodgrp() + " no se a encontrado, ingrese otro Código de Grupo";
			return false;
		}
		txtdes = db.stringValue(1);
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
public void setCod_grp(String newValue) {
	this.cod_grp = newValue;
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
public void setTxt_des(String newValue) {
	this.txt_des = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setTxtdes(String newValue) {
	this.txtdes = newValue;
}
public void sort(JQuery db) throws java.sql.SQLException {
}
public void update(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		String antDescripcion = txtdes;
		if (search(db)) {
			String query = //
			"Update "+Constante.ESQUEMA1+".TGRPMSG set txtdes = '" + antDescripcion + "' Where codgrp = '" + getCodgrp() + "' ";
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getCodgrp(), getTxtdes()};
			String aft[] = {getCodgrp(), antDescripcion};
			LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TGRPMSG", Table.getColumnsTgrpmsg(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_GRUPO_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodgrp() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... update() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}