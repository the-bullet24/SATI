package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class ItemsDeAyuda extends AyudaDeCampo {
	private String numseq = "";
	private String txthlpdat = "";
	private String codhlpdat = "";
	//	
	private String num_seq = "";
	private String txt_hlpdat = "";
	private String cod_hlpdat = "";
/**
 * AyudaDeCampo constructor comment.
 */
public ItemsDeAyuda() {
	super();
}
public void consult() throws java.sql.SQLException {
	try {
		String bef[] = {"", "", "", "", ""};
		String aft[] = {"", "", "", "", ""};
		LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", Constante.ESQUEMA1+".THLPDET", Table.getColumnsThlpdet(), bef, aft);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n...  consult() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public void delete(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		if (search(db)) {
			String query = //
			"Delete from "+Constante.ESQUEMA1+".THLPDET Where codhlp = '" + getCodhlp() + "' " + //
			"And numseq = " + new Integer(getNumseq()).intValue() + " ";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {getCodhlp(), getNumseq(), getTxthlp(), getTxthlpdat(), getCodhlpdat(),getCodOpc()};
			String aft[] = {"", "", "", "", "", ""};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".THLPDET", Table.getColumnsThlpdet(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.NRO_SECUENCIA_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getNumseq() + str.substring(pos + 1));
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
			numseq = vector.nextElement().toString();
			String sw = req.getParameter(numseq).toUpperCase();
			if (sw.equals("ON")) {
				if (search(db)) {
					String query = //
					"Delete from "+Constante.ESQUEMA1+".THLPDET Where codhlp = '" + getCodhlp() + "' " + //
					"And numseq = " + new Integer(numseq).intValue() + " ";
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {getCodhlp(), numseq, getTxthlp(), getTxthlpdat(), getCodhlpdat()};
					String aft[] = {"", "", "", "", ""};
					LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".THLPDET", Table.getColumnsThlpdet(), bef, aft);
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
public String getNum_seq() {
	if (num_seq == null || num_seq.equals(""))
		num_seq = "0";
	return num_seq.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNumseq() {
	try {
		if (numseq == null || numseq.equals(""))
			numseq = "0";
		numseq = numseq.trim();
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(numseq)) {
			numseq = "0";
		}
		long cod = new Integer(numseq).intValue();
		if (cod <= 0) {
			InicioSesion.loadParameters();
			JDatabase jd = new JDatabase();
			JQuery db = new JQuery(jd.getConnection());
			try {
				String query = //
				"Select numseq " + //
				"From "+Constante.ESQUEMA1+".THLPDET " + //
				"Where codhlp = '" + getCodhlp() + "' " + //
				"Order by numseq ";
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					numseq = db.stringValue(1).trim();
				}
				cod = new Integer(numseq).intValue();
				numseq = (cod >= 99999) ? "99999" : "" + (cod + 1);
			}
			catch (Exception ex) {
				// System.out.println("\n... " + getClass().getName() + " - getnumseq() --> " + ex.getMessage());
				return "-1";
			}
			finally {
				db.close();
				jd.close();
			}
		}
		return numseq.trim();
	}
	catch (Exception e) {
		return "-1";
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxt_hlpdat() {
	return txt_hlpdat;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxthlpdat() {
	return txthlpdat;
}

public String getCod_hlpdat() {
	return cod_hlpdat;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodhlpdat() {
	return codhlpdat;
}
public String getUrl() {
	return JspServlet.ELEMENTOS_DE_AYUDA_SERVLET + "?BtnHlp=Buscar&TxtCodhlp=" + getCodhlp() + "&TxtNumseq=" + getNum_seq();
}
public void insert(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		if (!search(db)) {
			String query = //
			"Insert into "+Constante.ESQUEMA1+".THLPDET (codhlp, numseq, txthlp, txthlpdat, codhlpdat) values ('" + getCodhlp() + "', " + new Integer(getNumseq()).intValue() + ", '" + getTxthlp() + "', '" + getTxthlpdat() + "', '" + getCodhlpdat() + "')";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", "", "", "", ""};
			String aft[] = {getCodhlp(), getNumseq(), getTxthlp(), getTxthlpdat(), getCodhlpdat()};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".THLPDET", Table.getColumnsThlpdet(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.NRO_SECUENCIA_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getNumseq() + str.substring(pos + 1));
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
			"Select numseq, txthlp, txthlpdat, codhlpdat " + //
			"From "+Constante.ESQUEMA1+".THLPDET " + //
			"Where codhlp = '" + getCodhlp() + "' " + //
			"Order by codhlp, numseq";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector row = new java.util.Vector();
				row.addElement(getStatus());
				row.addElement(db.stringValue(1));
				row.addElement(db.stringValue(2));
				row.addElement(db.stringValue(3));
				row.addElement(db.stringValue(4));
				getGrid().addElement(row);
			}
		}
		catch (Exception e) {
			throw new java.sql.SQLException("\n... loadGrid() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
}
public void next(int i) {
	setStatus(((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString());
	num_seq = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
	setTxt_hlp(((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString());
	txt_hlpdat = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
	cod_hlpdat = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
}
public boolean search(JQuery db) throws java.sql.SQLException {
	try {
		String query = //
		"Select txthlp, txthlpdat, codhlpdat " + //
		"From "+Constante.ESQUEMA1+".THLPDET " + //
		"Where codhlp = '" + getCodhlp() + "' " + //
		"And numseq = " + new Integer(getNumseq()).intValue() + " ";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		setTxthlp(db.stringValue(1));
		txthlpdat = db.stringValue(2);
		codhlpdat = db.stringValue(3);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... search() - " + getClass().getName() + " -> " + e.getMessage());
	}
	return true;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setNum_seq(String newValue) {
	this.num_seq = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setNumseq(String newValue) {
	this.numseq = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxt_hlpdat(String newValue) {
	this.txt_hlpdat = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxthlpdat(String newValue) {
	this.txthlpdat = newValue;
}

public void setCod_hlpdat(String newValue) {
	this.cod_hlpdat = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodhlpdat(String newValue) {
	this.codhlpdat = newValue;
}
public void update(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		String antTxthlp = getTxthlp();
		String antTxthlpdat = getTxthlpdat();
		String antCodhlpdat = getCodhlpdat();
		if (search(db)) {
			String query = //
			"Update "+Constante.ESQUEMA1+".THLPDET set " + //
			"txthlp = '" + antTxthlp + "', " + //
			"txthlpdat = '" + antTxthlpdat + "', " + //
			"codhlpdat = '" + antCodhlpdat + "' " + //
			"Where codhlp = '" + getCodhlp() + "' " + //
			"And numseq = " + new Integer(getNumseq()).intValue() + " ";
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getCodhlp(), getNumseq(), getTxthlp(), getTxthlpdat(), getCodhlpdat()};
			String aft[] = {getCodhlp(), getNumseq(), antTxthlp, antTxthlpdat, antCodhlpdat};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Modificar",Constante.ESQUEMA1+".THLPDET", Table.getColumnsThlpdet(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.NRO_SECUENCIA_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getNumseq() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... update() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}