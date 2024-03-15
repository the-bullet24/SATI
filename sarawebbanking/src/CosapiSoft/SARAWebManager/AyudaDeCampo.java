package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class AyudaDeCampo extends GenericTable {
	private String codhlp = "";
	private String txthlp = "";
	private String codopc = "";
	private String codfat = "";
	//
	private String cod_hlp = "";
	private String txt_hlp = "";
	private String cod_opc = "";
	private String cod_fat = "";
/**
 * AyudaDeCampo constructor comment.
 */
public AyudaDeCampo() {
	super();
}
public void consult() throws java.sql.SQLException {
	try {
		String bef[] = {"", "", ""};
		String aft[] = {"", "", ""};
		LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", Constante.ESQUEMA1+".THLPDAT", Table.getColumnsThlpdat(), bef, aft);
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
			"Delete from "+Constante.ESQUEMA1+".THLPDET Where codhlp = '" + getCodhlp() + "'";
			db.setQuery(query);
			db.executeUpdate();
			query = //
			"Delete from "+Constante.ESQUEMA1+".THLPDAT Where codhlp = '" + getCodhlp() + "'";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", "", ""};
			String aft[] = {getCodhlp(), getTxthlp(),getCodfat()};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".THLPDAT", Table.getColumnsThlpdat(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_AYUDA_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodhlp() + str.substring(pos + 1));
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
			codhlp = vector.nextElement().toString();
			String sw = req.getParameter(codhlp).toUpperCase();
			if (sw.equals("ON")) {
				if (search(db)) {
					String query = //
					"Delete from "+Constante.ESQUEMA1+".THLPDET Where codhlp = '" + codhlp + "'";
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from "+Constante.ESQUEMA1+".THLPDAT Where codhlp = '" + codhlp + "'";
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {"", "", ""};
					String aft[] = {getCodhlp(), getTxthlp(),getCodfat()};
					LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".THLPDAT", Table.getColumnsThlpdat(), bef, aft);
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
public String getCod_hlp() {
	if (cod_hlp == null)
		cod_hlp = "";
	return cod_hlp.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodhlp() {
	try {
		if (codhlp == null || codhlp.equals(""))
			codhlp = "0";
		codhlp = codhlp.trim();
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(codhlp)) {
			codhlp = "0";
		}
		long cod = new Integer(codhlp).intValue();
		if (cod <= 0) {
			InicioSesion.loadParameters();
			JDatabase jd = new JDatabase();
			JQuery db = new JQuery(jd.getConnection());
			try {
				String query = //
				"Select codhlp " + //
				"From "+Constante.ESQUEMA1+".THLPDAT " + //
				"Order by codhlp ";
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					codhlp = db.stringValue(1).trim();
				}
				cod = new Integer(codhlp).intValue();
				codhlp = (cod >= 99999) ? "99999" : "" + (cod + 1);
			}
			catch (Exception ex) {
				// System.out.println("\n... " + getClass().getName() + " - getCodhlp() --> " + ex.getMessage());
				return "-1";
			}
			finally {
				db.close();
				jd.close();
			}
		}
		if (!codhlp.equals(""))
			codhlp = CString.completeToBeginWithNChars(codhlp, 5, '0');
		return codhlp.trim();
	}
	catch (Exception e) {
		return "-1";
	}
}

public String getCodOpc() {
	try {
		if (codopc == null || codopc.equals(""))
			codopc = "0";
		codopc = codopc.trim();
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(codopc)) {
			codhlp = "0";
		}
		long cod = new Integer(codopc).intValue();
		if (cod <= 0) {
			InicioSesion.loadParameters();
			JDatabase jd = new JDatabase();
			JQuery db = new JQuery(jd.getConnection());
			try {
				String query = //
				"Select codhlp " + //
				"From "+Constante.ESQUEMA1+".THLPDAT " + //
				"Order by codhlp ";
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					codhlp = db.stringValue(1).trim();
				}
				cod = new Integer(codhlp).intValue();
				codhlp = (cod >= 99999) ? "99999" : "" + (cod + 1);
			}
			catch (Exception ex) {
				// System.out.println("\n... " + getClass().getName() + " - getCodhlp() --> " + ex.getMessage());
				return "-1";
			}
			finally {
				db.close();
				jd.close();
			}
		}
		if (!codhlp.equals(""))
			codhlp = CString.completeToBeginWithNChars(codhlp, 5, '0');
		return codhlp.trim();
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
	return Constante.ESQUEMA1+".THLPDAT";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxt_hlp() {
	return txt_hlp;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxthlp() {
	return txthlp;
}
public String getUrl() {
	return JspServlet.AYUDAS_DE_CAMPO_SERVLET + "?BtnHlp=Buscar&TxtCodhlp=" + getCod_hlp();
}
public void insert(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		if (!search(db)) {
			String query = //
			"Insert into "+Constante.ESQUEMA1+".THLPDAT (codhlp, txthlp,codfathlp) values ('" + getCodhlp() + "', '" + getTxthlp() + "', '" + getCodfat() + "')";
			// System.out.println("query........"+query);
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", "", ""};
			String aft[] = {getCodhlp(), getTxthlp(),getCodfat()};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".THLPDAT", Table.getColumnsThlpdat(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_AYUDA_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodhlp() + str.substring(pos + 1));
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
			"Select codhlp, txthlp, codfathlp " + //
			"From "+Constante.ESQUEMA1+".THLPDAT " + //
			"Order by codhlp";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector row = new java.util.Vector();
				row.addElement(getStatus());
				row.addElement(db.stringValue(1));
				row.addElement(db.stringValue(2));
				row.addElement(db.stringValue(3));
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
	cod_hlp = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
	txt_hlp = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
	cod_fat = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
}
public void nextChild(int i) {
}
public boolean search(JQuery db) throws java.sql.SQLException {
	try {
		String query = //
		"Select codhlp, txthlp, codfathlp " + //
		"From "+Constante.ESQUEMA1+".THLPDAT " + //
		"Where codhlp = '" + getCodhlp() + "'";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next()) {
			return false;
		}
		codhlp = db.stringValue(1);
		txthlp = db.stringValue(2);
		codfat = db.stringValue(3);
		return true;
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... search() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public boolean searchFat() throws java.sql.SQLException {
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		String query = //
		"Select codhlp, txthlp, codfathlp " + //
		"From "+Constante.ESQUEMA1+".THLPDAT " + //
		"Where codhlp = '" + getCodfat() + "'";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next()) {
			return false;
		}
		
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
public void setCod_hlp(String newValue) {
	this.cod_hlp = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodhlp(String newValue) {
	this.codhlp = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxt_hlp(String newValue) {
	this.txt_hlp = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxthlp(String newValue) {
	this.txthlp = newValue;
}
public void sort(JQuery db) throws java.sql.SQLException {
}
public void update(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		String antNombre = getTxthlp();
		String antPadre = getCodfat();
		if (search(db)) {
			String query = //
			"Update "+Constante.ESQUEMA1+".THLPDAT set txthlp = '" + antNombre + "',codfathlp = '" + antPadre + "' Where codhlp = '" + getCodhlp() + "' ";
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getCodhlp(), getTxthlp(), getCodfat()};
			String aft[] = {getCodhlp(), antNombre, antNombre};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Modificar", Constante.ESQUEMA1+".THLPDAT", Table.getColumnsThlpdat(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_AYUDA_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodhlp() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... update() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
	/**
	 * @return Devuelve cod_fat.
	 */
	public String getCod_fat() {
		return cod_fat;
	}
	/**
	 * @param cod_fat El cod_fat a establecer.
	 */
	public void setCod_fat(String cod_fat) {
		this.cod_fat = cod_fat;
	}
	/**
	 * @return Devuelve codfat.
	 */
	public String getCodfat() {
		if(codfat==null || codfat.equals(""))
			return "-";
		return codfat;
	}
	/**
	 * @param codfat El codfat a establecer.
	 */
	public void setCodfat(String codfat) {
		this.codfat = codfat;
	}
}