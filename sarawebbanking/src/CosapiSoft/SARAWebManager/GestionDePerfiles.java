package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class GestionDePerfiles extends GenericTable {
	private String codprf = "";
	private String txtprf = "";
	//
	private String cod_prf = "";
	private String txt_prf = "";
	//
	private String codact = "";
	private String txtact = "";
/**
 * AyudaDeCampo constructor comment.
 */
public GestionDePerfiles() {
	super();
}
public void consult() throws java.sql.SQLException {
	try {
		String bef[] = {"", ""};
		String aft[] = {"", ""};
		LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", "tprfdat", Table.getColumnsTprfdat(), bef, aft);
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... Consult() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public void consultActividad() throws java.sql.SQLException {
	try {
		// System.out.println("llego a consult actividad");
		String bef[] = {"", ""};
		String aft[] = {"", ""};
		LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", Constante.ESQUEMA1+".TACTPRF", Table.getColumnsTactprf(), bef, aft);
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n...  consultActividad() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public void delete(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		if (search(db)) {
			String query = //
			"Delete from "+Constante.ESQUEMA1+".TACTPRF Where codprf = '" + getCodprf() + "'";
			db.setQuery(query);
			db.executeUpdate();
			query = //
			"Delete from "+Constante.ESQUEMA1+".tusrprf Where codprf = '" + getCodprf() + "'";
			db.setQuery(query);
			db.executeUpdate();
			query = //
			"Delete from "+Constante.ESQUEMA1+".tprfdat Where codprf = '" + getCodprf() + "'";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {getCodprf(), getTxtprf()};
			String aft[] = {"", ""};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", "tprfdat", Table.getColumnsTprfdat(), bef, aft);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_PERFIL_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodprf() + str.substring(pos + 1));
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
			codprf = vector.nextElement().toString();
			String sw = req.getParameter(codprf).toUpperCase();
			if (sw.equals("ON")) {
				delete(db);
				setGrid(null);
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
public String getCod_prf() {
	if (cod_prf == null)
		cod_prf = "";
	return cod_prf.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodact() {
	return codact;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodprf() {
	try {
		if (codprf == null || codprf.equals(""))
			codprf = "0";
		codprf = codprf.trim();
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(codprf)) {
			codprf = "0";
		}
		int cod = new Integer(codprf).intValue();
		if (cod <= 0) {
			codprf = nextCodprf();
		}
		if (!codprf.equals(""))
			codprf = CString.completeToBeginWithNChars(codprf, 2, '0');
		return codprf.trim();
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
	return "tprfdat";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxt_prf() {
	return txt_prf;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxtact() {
	return txtact;
}
/**
 * This method was created in VisualAge.
 * @return String
 */
public String getTxtprf() {
	CString str = new CString(txtprf);
	str.arrange();
	return str.toString();
}
public String getUrl() {
	return getUrlGestionDePerfiles();
}
public String getUrlActividadesDePerfiles() {
	return JspServlet.ACTIVIDADES_DE_PERFILES_SERVLET + "?BtnAct=Buscar&TxtCodprf=";
}
public String getUrlGestionDePerfiles() {
	return JspServlet.GESTION_DE_PERFILES_SERVLET + "?BtnPrf=Buscar&TxtCodprf=" + getCod_prf();
}
public void insert(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		if (!search(db)) {
			String query = //
			"Insert into "+Constante.ESQUEMA1+".tprfdat (codprf, txtprf) values ('" + getCodprf() + "', '" + getTxtprf() + "')";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", ""};
			String aft[] = {getCodprf(), getTxtprf()};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", "tprfdat", Table.getColumnsTprfdat(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_PERFIL_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodprf() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... insert() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public void loadGrid(JQuery db) throws java.sql.SQLException {
	setIndex(0);
	setStatus("0");
	if (getGrid().size() == 0) {
		try {
			String query = //
			"Select codprf, txtprf " + //
			"From "+Constante.ESQUEMA1+".tprfdat " + //
			"Order by codprf";
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
	setGridChild(null);
	if (getGridChild().size() == 0) {
		try {
			String query = //
			"Select codact, txtact " + //
			"From "+Constante.ESQUEMA2+".tactdat " + //
			"Where codact IN ( " + //
			"  Select codact " + //
			"  From "+Constante.ESQUEMA1+".TACTPRF " + //
			"  Where codprf = '" + getCodprf() + "') " + //
			"Order by codact";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				setStatus("1");
				java.util.Vector row = new java.util.Vector(3);
				row.addElement(getStatus());
				row.addElement(db.stringValue(1));
				row.addElement(db.stringValue(2));
				getGridChild().addElement(row);
			}
			query = //
			"Select codact, txtact " + //
			"From "+Constante.ESQUEMA2+".tactdat " + //
			"Where codact not IN ( " + //
			"  Select codact " + //
			"  From "+Constante.ESQUEMA1+".TACTPRF " + //
			"  Where codprf = '" + getCodprf() + "') " + //
			"Order by codact";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				setStatus("0");
				java.util.Vector row = new java.util.Vector(3);
				row.addElement(getStatus());
				row.addElement(db.stringValue(1));
				row.addElement(db.stringValue(2));
				getGridChild().addElement(row);
			}
		}
		catch (Exception e) {
			throw new java.sql.SQLException("\n...loadGridChild() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
}
public void next(int i) {
	setStatus(((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString());
	cod_prf = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
	txt_prf = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
}
public void nextActividad(int i) {
	setStatus(((java.util.Vector) getGridChild().elementAt(i)).elementAt(0).toString());
	codact = ((java.util.Vector) getGridChild().elementAt(i)).elementAt(1).toString();
	txtact = ((java.util.Vector) getGridChild().elementAt(i)).elementAt(2).toString();
}
public void nextChild(int i) {
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @exception java.sql.SQLException The exception description.
 */
public String nextCodprf() throws java.sql.SQLException {
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		String prf = "0";
		String query = //
		"Select codprf " + //
		"From "+Constante.ESQUEMA2+".tprfdat " + //
		"Order by codprf ";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			prf = db.stringValue(1).trim();
		}
		int cod = new Integer(prf).intValue();
		prf = (cod >= 99) ? "99" : "" + (cod + 1);
		return prf;
	}
	catch (Exception ex) {
		throw new java.sql.SQLException("\n... nextCodprf() - " + getClass().getName() + " -> " + ex.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
public void saveActividad(javax.servlet.http.HttpServletRequest req) throws java.sql.SQLException {
	JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		setError("");
		String query = //
		"Delete from "+Constante.ESQUEMA1+".TACTPRF " + //
		"Where codprf = '" + getCodprf() + "' ";
		db.setQuery(query);
		db.executeUpdate();
		java.util.Enumeration vector = req.getParameterNames();
		while (vector.hasMoreElements()) {
			codact = vector.nextElement().toString();
			String sw = req.getParameter(getCodact()).toUpperCase();
			if (sw.equals("ON")) {
				query = //
				"Insert into "+Constante.ESQUEMA1+".TACTPRF (codprf, codact) values ('" + getCodprf() + "', '" + getCodact() + "') ";
				db.setQuery(query);
				db.executeUpdate();
				String bef[] = {"", ""};
				String aft[] = {getCodprf(), getCodact()};
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TACTPRF", Table.getColumnsTactprf(), bef, aft);
			}
		}
		setGridChild(null);
		loadGridChild(db);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... saveActividad() - " + getClass().getName() + " -> " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
public boolean search(JQuery db) throws java.sql.SQLException {
	try {
		String query = //
		"Select codprf, txtprf " + //
		"From "+Constante.ESQUEMA1+".tprfdat " + //
		"Where codprf = '" + getCodprf() + "'";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next()) {
			return false;
		}
		codprf = db.stringValue(1);
		txtprf = db.stringValue(2);
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
public void setCod_prf(String newValue) {
	this.cod_prf = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodact(String newValue) {
	this.codact = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodprf(String newValue) {
	this.codprf = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxt_prf(String newValue) {
	this.txt_prf = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxtact(String newValue) {
	this.txtact = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue String
 */
public void setTxtprf(String newValue) {
	this.txtprf = newValue;
}
public void sort(JQuery db) throws java.sql.SQLException {
}
public void update(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		String antNombre = getTxtprf();
		if (search(db)) {
			String query = //
			"Update "+Constante.ESQUEMA1+".tprfdat set " + //
			"txtprf = '" + antNombre + "' " + //
			"Where codprf = '" + getCodprf() + "' ";
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getCodprf(), getTxtprf()};
			String aft[] = {getCodprf(), antNombre};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Modificar", "tprfdat", Table.getColumnsTprfdat(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_PERFIL_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodprf() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... update() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}