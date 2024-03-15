package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class Clases extends GenericTable {
	private String codcla = "";
	private String txtcla = "";
	private String sesnam = "";
	//
	private String cod_cla = "";
	private String txt_cla = "";
	private String ses_nam = "";
/**
 * AyudaDeCampo constructor comment.
 */
public Clases() {
	super();
}
public void consult() throws java.sql.SQLException {
	try {
		String bef[] = {"", "", ""};
		String aft[] = {"", "", ""};
		LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", Constante.ESQUEMA1+".TCLADAT", Table.getColumnsTcladat(), bef, aft);
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... consult() - Clases \n" + e.getMessage());
	}
}
public void delete(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		if (search(db)) {
			Esquema1.deleteClaseEsquema(db, getCodcla());
			Fase1.deleteClaseFase(db, getCodcla());
			String query = //
			"Delete from "+Constante.ESQUEMA1+".TMETDAT Where codcla = '" + getCodcla() + "'";
			db.setQuery(query);
			db.executeUpdate();
			query = //
			/*"Delete from tprodat Where codcla = '" + getCodcla() + "'";
			db.setQuery(query);
			db.executeUpdate();
			query = *///
			"Delete from "+Constante.ESQUEMA1+".TCLADAT Where codcla = '" + getCodcla() + "'";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {codcla, getTxtcla(), getSesnam()};
			String aft[] = {"", "", ""};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TCLADAT", Table.getColumnsTcladat(), bef, aft);
			setError(Mensajes.getMessage(Mensajes.SE_HA_ELIMINADO_CLASES_Y_METODOS));
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_CLASE_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodcla() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - Clases \n" + e.getMessage());
	}
}
public void delete(JQuery db, javax.servlet.http.HttpServletRequest req) throws java.sql.SQLException, javax.servlet.ServletException {
	try {
		setError("");
		java.util.Enumeration vector = req.getParameterNames();
		while (vector.hasMoreElements() && vector != null) {
			codcla = vector.nextElement().toString();
			String sw = req.getParameter(codcla).toUpperCase();
			if (sw.equals("ON")) {
				if (search(db)) {
					setGrid(null);
					Esquema1.deleteClaseEsquema(db, getCodcla());
					Fase1.deleteClaseFase(db, getCodcla());
					String query = //
					"Delete from "+Constante.ESQUEMA1+".TMETDAT Where codcla = '" + getCodcla() + "'";
					db.setQuery(query);
					db.executeUpdate();
					query = //
					/*"Delete from tprodat Where codcla = '" + getCodcla() + "'";
					db.setQuery(query);
					db.executeUpdate();
					query = //*/
					"Delete from "+Constante.ESQUEMA1+".TCLADAT Where codcla = '" + getCodcla() + "'";
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {codcla, getTxtcla(), getSesnam()};
					String aft[] = {"", "", ""};
					LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TCLADAT", Table.getColumnsTcladat(), bef, aft);
					setError(Mensajes.getMessage(Mensajes.SE_HA_ELIMINADO_CLASES_Y_METODOS));
				}
			}
		}
		loadGrid(db);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - Clases \n" + e.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_cla() {
	if (cod_cla == null)
		cod_cla = "";
	return cod_cla.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodcla() {
	try {
		if (codcla == null || codcla.equals(""))
			codcla = "0";
		codcla = codcla.trim();
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(codcla)) {
			codcla = "0";
		}
		int cod = new Integer(codcla).intValue();
		if (cod <= 0) {
			InicioSesion.loadParameters();
			JDatabase jd = new JDatabase();
			JQuery db = new JQuery(jd.getConnection());
			try {
				String query = //
				"Select codcla " + //
				"From "+Constante.ESQUEMA1+".TCLADAT " + //
				"Order by codcla ";
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					codcla = db.stringValue(1).trim();
				}
				cod = new Integer(codcla).intValue();
				codcla = (cod >= 999) ? "999" : "" + (cod + 1);
			}
			catch (Exception ex) {
				// System.out.println(getClass().getName() + " - getCodcla() --> " + ex.getMessage());
				return "-1";
			}
			finally {
				db.close();
				jd.close();
			}
		}
		if (!codcla.equals(""))
			codcla = CString.completeToBeginWithNChars(codcla, 3, '0');
		return codcla.trim();
	}
	catch (Exception e) {
		return "-1";
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNameTable() {
	return Constante.ESQUEMA1+".TCLADAT";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getSes_nam() {
	return ses_nam;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getSesnam() {
	return sesnam;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxt_cla() {
	return txt_cla;
}
/**
 * This method was created in VisualAge.
 * @return String
 */
public String getTxtcla() {
	if (txtcla == null)
		txtcla = "";
	CString str = new CString(txtcla);
	str.arrange();
	return str.toString();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getUrl() {
	return getUrlClases();
}
public String getUrlClases() {
	return JspServlet.CLASES_SERVLET + "?BtnCla=Buscar&TxtCodcla=";
}
public void insert(JQuery db) throws java.sql.SQLException {
	try {
		if (!search(db)) {
			String query = //
			"Insert into "+Constante.ESQUEMA1+".TCLADAT (codcla, txtcla, sesnam) values ('" + getCodcla() + "', '" + getTxtcla() + "', '" + getSesnam() + "')";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", "", ""};
			String aft[] = {getCodcla(), getTxtcla(), getSesnam()};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TCLADAT", Table.getColumnsTcladat(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_CLASE_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodcla() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - Clases \n" + e.getMessage());
	}
}
public void loadGrid(JQuery db) throws java.sql.SQLException {
	try {
		setIndex(0);
		setStatus("0");
		if (getGrid().size() == 0) {
			String query = //
			"Select codcla, txtcla, sesnam " + //
			"From "+Constante.ESQUEMA1+".TCLADAT " + //
			"Order by codcla";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector row = new java.util.Vector(3);
				row.addElement(getStatus());
				row.addElement(db.stringValue(1).trim());
				row.addElement(db.stringValue(2).trim());
				row.addElement(db.stringValue(3).trim());
				getGrid().addElement(row);
			}
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n...loadGrid() - Clases \n" + e.getMessage());
	}
}
public void loadGridChild(JQuery db) throws java.sql.SQLException {
}
public void next(int i) {
	setStatus(((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString().trim());
	cod_cla = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString().trim();
	txt_cla = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString().trim();
	ses_nam = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString().trim();
}
public void nextChild(int i) {
}
public boolean search(JQuery db) throws java.sql.SQLException {
	try {
		String query = //
		"Select txtcla, sesnam " + //
		"From "+Constante.ESQUEMA1+".TCLADAT " + //
		"Where codcla = '" + getCodcla() + "'";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		txtcla = db.stringValue(1);
		sesnam = db.stringValue(2);
		return true;
	}
	catch (java.sql.SQLException e) {
		throw new java.sql.SQLException("\n... search() - Clases \n" + e.getMessage());
	}
}
public static String searchClase(JQuery db, String codcla) throws Exception {
	try {
		String query = //
		"Select txtcla " + //
		"From "+Constante.ESQUEMA1+".TCLADAT " + //
		"Where codcla = '" + codcla + "'";
		db.setQuery(query);
		db.executeQuery();
		boolean sw = db.getResultSet().next();
		String txtcla = "---";
		if (sw) {
			txtcla = db.stringValue(1).trim();
			if (txtcla.equals("")) {
				txtcla = "---";
			}
		}
		return txtcla;
	}
	catch (Exception e) {
		throw new Exception("\n... searchClase(" + codcla + ") - Clases \n" + e.getMessage());
	}
}
public static String searchClase(String codcla) throws Exception {
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		String query = //
		"Select txtcla " + //
		"From "+Constante.ESQUEMA1+".TCLADAT " + //
		"Where codcla = '" + codcla + "'";
		db.setQuery(query);
		db.executeQuery();
		boolean sw = db.getResultSet().next();
		String txtcla = "---";
		if (sw) {
			txtcla = db.stringValue(1);
			if (txtcla.equals("")) {
				txtcla = "---";
			}
		}
		return txtcla;
	} catch (Exception e) {
		throw new Exception("\n... searchClase(" + codcla + ") - Clases \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_cla(String newValue) {
	this.cod_cla = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodcla(String newValue) {
	this.codcla = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setSes_nam(String newValue) {
	this.ses_nam = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setSesnam(String newValue) {
	this.sesnam = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxt_cla(String newValue) {
	this.txt_cla = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setTxtcla(String newValue) {
	this.txtcla = newValue;
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
		String antDescripcion = txtcla;
		String antSesnam = sesnam;
		if (search(db)) {
			String query = //
			"Update "+Constante.ESQUEMA1+".TCLADAT set txtcla = '" + antDescripcion + "', sesnam = '" + antSesnam + "' Where codcla = '" + getCodcla() + "' ";
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getCodcla(), getTxtcla(), getSesnam()};
			String aft[] = {getCodcla(), antDescripcion, antSesnam};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Modificar", Constante.ESQUEMA1+".TCLADAT", Table.getColumnsTcladat(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_CLASE_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodcla() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... update() - Clases \n" + e.getMessage());
	}
}
}