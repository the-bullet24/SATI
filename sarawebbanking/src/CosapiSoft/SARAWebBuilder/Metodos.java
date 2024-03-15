package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class Metodos extends Clases {
	private String codmet = "";
	private String txtmet = "";
	private String txtdes = "";
	//
	private String cod_met = "";
	private String txt_met = "";
	private String txt_des = "";
/**
 * AyudaDeCampo constructor comment.
 */
public Metodos() {
	super();
}
public void consult() throws java.sql.SQLException {
	try {
		String bef[] = {"", "", "", ""};
		String aft[] = {"", "", "", ""};
		LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", Constante.ESQUEMA1+".TMETDAT", Table.getColumnsTmetdat(), bef, aft);
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... consult() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public void delete(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		if (search(db)) {
			Esquema1.deleteClaseEsquema(db, getCodcla(), getCodmet());
			Fase1.deleteClaseFase(db, getCodcla(), getCodmet());
			String query = //
			"Delete from "+Constante.ESQUEMA1+".TMETDAT Where codcla = '" + getCodcla() + "' And codmet = '" + getCodmet() + "'";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {getCodcla(), getCodmet(), getTxtmet(), getTxtdes()};
			String aft[] = {"", "", "", ""};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TMETDAT", Table.getColumnsTmetdat(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_METODO_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodmet() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public void delete(JQuery db, javax.servlet.http.HttpServletRequest req) throws java.sql.SQLException {
	try {
		setError("");
		java.util.Enumeration vector = req.getParameterNames();
		while (vector.hasMoreElements() && vector != null) {
			codmet = vector.nextElement().toString();
			String sw = req.getParameter(codmet).toUpperCase();
			if (sw.equals("ON")) {
				if (search(db)) {
					setGrid(null);
					Esquema1.deleteClaseEsquema(db, getCodcla(), codmet);
					Fase1.deleteClaseFase(db, getCodcla(), codmet);
					String query = //
					"Delete from "+Constante.ESQUEMA1+".TMETDAT Where codcla = '" + getCodcla() + "' And codmet = '" + codmet + "'";
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {getCodcla(), codmet, getTxtmet(), getTxtdes()};
					String aft[] = {"", "", "", ""};
					LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", "tmetdat", Table.getColumnsTmetdat(), bef, aft);
					setError(Mensajes.getMessage(Mensajes.SE_HA_ELIMINADO_CLASES_Y_METODOS));
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
public String getCod_met() {
	if (cod_met == null)
		cod_met = "";
	return cod_met.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodmet() {
	try {
		if (codmet == null || codmet.equals(""))
			codmet = "0";
		codmet = codmet.trim();
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(codmet)) {
			codmet = "0";
		}
		int cod = new Integer(codmet).intValue();
		if (cod <= 0) {
			JDatabase jd = new JDatabase();
			JQuery db = new JQuery(jd.getConnection());
			String query = //
			"Select codmet " + //
			"From "+Constante.ESQUEMA1+".TMETDAT " + //
			"Where codcla = '" + getCodcla() + "' " + //
			"Order by codmet ";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				codmet = db.stringValue(1).trim();
			}
			cod = new Integer(codmet).intValue();
			codmet = (cod >= 999) ? "999" : "" + (cod + 1);
			db.close();
			jd.close();
		}
		if (!codmet.equals(""))
			codmet = CString.completeToBeginWithNChars(codmet, 3, '0');
		return codmet.trim();
	} catch (Exception e) {
		return "-1";
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxt_des() {
	return txt_des;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxt_met() {
	return txt_met;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxtdes() {
	CString str = new CString(txtdes);
	str.arrange();
	return str.toString();
}
/**
 * This method was created in VisualAge.
 * @return String
 */
public String getTxtmet() {
	CString str = new CString(txtmet);
	str.arrange();
	return str.toString();
}
public String getUrl() {
	return getUrlMetodos();
}
public String getUrlMetodos() {
	return JspServlet.METODOS_SERVLET + "?BtnMet=Buscar&TxtCodcla=" + getCodcla() + "&TxtCodmet=";
}
public void insert(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		if (!search(db)) {
			String query = //
			"Insert into "+Constante.ESQUEMA1+".TMETDAT (codcla, codmet, txtmet, txtdes) values ('" + getCodcla() + "', '" + getCodmet() + "', '" + getTxtmet() + "', '" + getTxtdes() + "')";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", "", "", ""};
			String aft[] = {getCodcla(), getCodmet(), getTxtmet(), getTxtdes()};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar",Constante.ESQUEMA1+".TMETDAT", Table.getColumnsTmetdat(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_METODO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodmet() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... insert() - Métodos \n" + e.getMessage());
	}
}
public void loadGrid(JQuery db) throws java.sql.SQLException {
	try {
		setIndex(0);
		if (getGrid().size() == 0) {
			String query = //
			"Select codmet, txtmet, txtdes " + //
			"From "+Constante.ESQUEMA1+".TMETDAT " + //
			"Where codcla = '" + getCodcla() + "' " + //
			"Order by codmet";
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
public void next(int i) {
	setStatus(((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString());
	cod_met = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
	txt_met = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
	txt_des = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
}
public boolean search(JQuery db) throws java.sql.SQLException {
	try {
		String query = //
		"Select txtmet, txtdes " + //
		"From "+Constante.ESQUEMA1+".TMETDAT " + //
		"Where codcla = '" + getCodcla() + "' " + //
		"And codmet = '" + getCodmet() + "'";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		txtmet = db.stringValue(1);
		txtdes = db.stringValue(2);
		return true;
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... search() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public static String searchMetodo(JQuery db, String codcla, String codmet) throws Exception {
	try {
		String query = //
		"Select txtmet " + //
		"From "+Constante.ESQUEMA1+".TMETDAT " + //
		"Where codcla = '" + codcla + "' " + //
		"And codmet = '" + codmet + "'";
		db.setQuery(query);
		db.executeQuery();
		boolean sw = db.getResultSet().next();
		String txtmet = "---";
		if (sw) {
			txtmet = db.stringValue(1);
			if (txtmet.equals("")) {
				txtmet = "---";
			}
		}
		return txtmet;
	} catch (Exception e) {
		throw new Exception("\n... searchMetodo(" + codcla + ", " + codmet + ") - Clases \n" + e.getMessage());
	}
}
public static String searchMetodo(String codcla, String codmet) throws Exception {
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		String query = //
		"Select txtmet " + //
		"From "+Constante.ESQUEMA1+".TMETDAT " + //
		"Where codcla = '" + codcla + "' " + //
		"And codmet = '" + codmet + "'";
		db.setQuery(query);
		db.executeQuery();
		boolean sw = db.getResultSet().next();
		String txtmet = "---";
		if (sw) {
			txtmet = db.stringValue(1);
			if (txtmet.equals("")) {
				txtmet = "---";
			}
		}
		return txtmet;
	} catch (Exception e) {
		throw new Exception("\n... searchMetodo(" + codcla + ", " + codmet + ") - Clases \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_met(String newValue) {
	this.cod_met = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodmet(String newValue) {
	this.codmet = newValue;
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
 * @param newValue java.lang.String
 */
public void setTxt_met(String newValue) {
	this.txt_met = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxtdes(String newValue) {
	this.txtdes = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setTxtmet(String newValue) {
	this.txtmet = newValue;
}
/**
 * This method was created in VisualAge.
 * @param db CosapiSoft.SARAWebBanking.JQuery
 * @exception java.sql.SQLException The exception description.
 */
public void sort(JQuery db) throws java.sql.SQLException {
}
public void update(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		String antTxtmet = getTxtmet();
		String antTxtdes = getTxtdes();
		if (search(db)) {
			String query = //
			"Update "+Constante.ESQUEMA1+".TMETDAT set " + //
			"txtmet = '" + antTxtmet + "', " + //
			"txtdes = '" + antTxtdes + "' " + //
			"Where codcla = '" + getCodcla() + "' " + //
			"And codmet = '" + getCodmet() + "' ";
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getCodcla(), getCodmet(), getTxtmet(), getTxtdes()};
			String aft[] = {getCodcla(), getCodmet(), antTxtmet, antTxtdes};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Modificar", Constante.ESQUEMA1+".TMETDAT", Table.getColumnsTmetdat(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_METODO_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodmet() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... update() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}