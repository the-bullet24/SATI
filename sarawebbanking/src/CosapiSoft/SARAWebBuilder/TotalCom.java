package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class TotalCom extends GenericTable {






/**
 * AyudaDeCampo constructor comment.
 */
public TotalCom() {
	super();
}
public void consult() throws java.sql.SQLException {
	try {
		String bef[] = {"", ""};
		String aft[] = {"", ""};
		LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", "ttotcom", Table.getColumnsTtotcom(), bef, aft);
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... consult() - Totales \n" + e.getMessage());
	}
}
public void delete(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		if (search(db)) {
			String query = //
			"Delete from ttotdat Where codtot = '" + getcodtotcom() + "'";
			db.setQuery(query);
			db.executeUpdate();
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {codtotcom, getNomtotcom()};
			String aft[] = {"", ""};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", "ttotdat", Table.getColumnsTtotdat(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_TOTALCOM_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getcodtotcom() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - Totales compuestos \n" + e.getMessage());
	}
}
public void delete(JQuery db, javax.servlet.http.HttpServletRequest req) throws java.sql.SQLException, javax.servlet.ServletException {
	try {
		setError("");
		java.util.Enumeration vector = req.getParameterNames();
		while (vector.hasMoreElements() && vector != null) {
			codtotcom = vector.nextElement().toString();
			String sw = req.getParameter(codtotcom).toUpperCase();
			if (sw.equals("ON")) {
				if (search(db)) {
					setGrid(null);
					String query = //
					"Delete from ttotcom Where codtotcom = '" + getcodtotcom() + "'";
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {codtotcom, getNomtotcom()};
					String aft[] = {"", ""};
					LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", "ttotcom", Table.getColumnsTtotcom(), bef, aft);
				}
			}
		}
		loadGrid(db);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - Totales Compuestos \n" + e.getMessage());
	}
}


/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNameTable() {
	return "tcladat";
}




/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getUrl() {
	return getUrlClases();
}
public String getUrlClases() {
	return JspServlet.TOTALCOM_SERVLET + "?BtnTot=Buscar&TxtCodtotcom=";
}
public void insert(JQuery db) throws java.sql.SQLException {
	try {
		if (!search(db)) {
			String query = //
			"Insert into ttotcom (codtotcom, nomtotcom) values ('" + getcodtotcom() + "', '" + getNomtotcom() + "')";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", ""};
			String aft[] = {getcodtotcom(), getNomtotcom()};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", "ttotcom", Table.getColumnsTtotcom(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_TOTALCOM_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getcodtotcom() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... insert() - Totales Compuestos \n" + e.getMessage());
	}
}
public void loadGrid(JQuery db) throws java.sql.SQLException {
	try {
		setIndex(0);
		setStatus("0");
		if (getGrid().size() == 0) {
			String query = //
			"Select codtotcom, nomtotcom " + //
			"From ttotcom " + //
			"Order by codtotcom";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector row = new java.util.Vector(3);
				row.addElement(getStatus());
				row.addElement(db.stringValue(1).trim());
				row.addElement(db.stringValue(2).trim());
				getGrid().addElement(row);
			}
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n...loadGrid() - Totales Compuestos \n" + e.getMessage());
	}
}
public void loadGridChild(JQuery db) throws java.sql.SQLException {
}
public void next(int i) {
	setStatus(((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString().trim());
	cod_tot_com = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString().trim();
	nom_tot_com = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString().trim();
}
public void nextChild(int i) {
}
public boolean search(JQuery db) throws java.sql.SQLException {
	try {
		String query = //
		"Select nomtotcom " + //
		"From ttotcom " + //
		"Where codtotcom = '" + getcodtotcom() + "'";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		nomtotcom = db.stringValue(1);
		return true;
	}
	catch (java.sql.SQLException e) {
		throw new java.sql.SQLException("\n... search() - totales compuestos \n" + e.getMessage());
	}
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
		String antNomtotcom = nomtotcom;
		if (search(db)) {
			String query = //
			"Update ttotcom set nomtotcom = '" + antNomtotcom + "' Where codtotcom = '" + getcodtotcom() + "' ";
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getcodtotcom(), getNomtotcom()};
			String aft[] = {getcodtotcom(), antNomtotcom};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", "ttotcom", Table.getColumnsTtotcom(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_TOTALCOM_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getcodtotcom() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... update() - Clases \n" + e.getMessage());
	}
}
	//
	private String cod_tot_com = "";	private String codtotcom = "";	private String nom_tot_com = "";	private String nomtotcom = "";/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_tot_com() {
	if (cod_tot_com == null)
		cod_tot_com = "";
	return cod_tot_com.trim();
}/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */ 
public String getcodtotcom() {
	try {
		if (codtotcom == null || codtotcom.equals(""))
			codtotcom = "0";
		codtotcom = codtotcom.trim();
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(codtotcom)) {
			codtotcom = "0";
		}
		int cod = new Integer(codtotcom).intValue();
		if (cod <= 0) {
			InicioSesion.loadParameters();
			JDatabase jd = new JDatabase();
			JQuery db = new JQuery(jd.getConnection());
			try {
				String query = //
				"Select codtotcom " + //
				"From ttotcom " + //
				"Order by codtotcom ";
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					codtotcom = db.stringValue(1).trim();
				}
				cod = new Integer(codtotcom).intValue();
				codtotcom = (cod >= 99999) ? "99999" : "" + (cod + 1);
			}
			catch (Exception ex) {
				// System.out.println(getClass().getName() + " - getcodtotcom() --> " + ex.getMessage());
				return "-1";
			}
			finally {
				db.close();
				jd.close();
			}
		}
		if (!codtotcom.equals(""))
			codtotcom = CString.completeToBeginWithNChars(codtotcom, 5, '0');
		return codtotcom.trim();
	}
	catch (Exception e) {
		return "-1";
	}
}/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */ 
public String getNom_tot_com() {
	return nom_tot_com;
}/**
 * This method was created in VisualAge.
 * @return String
 */ 
public String getNomtotcom() {
	if (nomtotcom	 == null)
		nomtotcom = "";
	CString str = new CString(nomtotcom);
	str.arrange();
	return str.toString();
}public static String searchCaja(JQuery db, String codcaj) throws Exception {
	try {
		String query = //
		"Select nomcaj " + //
		"From tcajdat " + //
		"Where codcaj = '" + codcaj + "'";
		db.setQuery(query);
		db.executeQuery();
		boolean sw = db.getResultSet().next();
		String txtcaj = "---";
		if (sw) {
			txtcaj = db.stringValue(1).trim();
			if (txtcaj.equals("")) {
				txtcaj = "---";
			}
		}
		return txtcaj;
	}
	catch (Exception e) {
		throw new Exception("\n... searchClase(" + codcaj + ") - Cajas \n" + e.getMessage());
	}
}public static String searchCaja(String codcaj) throws Exception {
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		String query = //
		"Select nomcaj " + //
		"From tcajdat " + //
		"Where codcaj = '" + codcaj + "'";
		db.setQuery(query);
		db.executeQuery();
		boolean sw = db.getResultSet().next();
		String txtcaj = "---";
		if (sw) {
			txtcaj = db.stringValue(1);
			if (txtcaj.equals("")) {
				txtcaj = "---";
			}
		}
		return txtcaj;
	} catch (Exception e) {
		throw new Exception("\n... searchClase(" + codcaj + ") - Cajas \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */ 
public void setCod_tot_com(String newValue) {
	this.cod_tot_com = newValue;
}/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */ 
public void setCodtotcom(String newValue) {
	this.codtotcom = newValue;
}/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */ 
public void setNom_tot_com(String newValue) {
	this.nom_tot_com = newValue;
}/**
 * This method was created in VisualAge.
 * @param newValue int
 */ 
public void setNomtotcom(String newValue) {
	this.nomtotcom = newValue;
}}