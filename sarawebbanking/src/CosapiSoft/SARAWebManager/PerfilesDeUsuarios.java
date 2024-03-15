package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class PerfilesDeUsuarios extends GestionDeUsuarios {
	public final static String CODMOD_BUILDER = "01";
	public final static String CODMOD_MANAGER = "02";
	public final static String CODMOD_TELLER = "03";
	public final static String CODMOD_SIGN = "04";
	public final static String CODMOD_SALES = "05";
	//
	private String codprf = "";
	private String txtprf = "";
/**
 * AyudaDeCampo constructor comment.
 */
public PerfilesDeUsuarios() {
	super();
}
public void aceptar(javax.servlet.http.HttpServletRequest req) throws Exception {
	if (getCodmod().equals(""))
		return;
	JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		setError("");
		String query = //
		"Delete from ";
		if (getCodmod().equals(CODMOD_BUILDER) || getCodmod().equals(CODMOD_MANAGER))
			query += ""+Constante.ESQUEMA1+".TUSRPRF ";
		if (getCodmod().equals(CODMOD_TELLER))
			query += ""+Constante.ESQUEMA1+".TBRAUSR ";
		if (getCodmod().equals(CODMOD_SIGN))
			query += ""+Constante.ESQUEMA1+".TUSRSGN ";
		query += "Where codusr = '" + getCodusr() + "' " + //
		"And codmod = '" + getCodmod() + "' ";
		db.setQuery(query);
		db.executeUpdate();
		java.util.Enumeration vector = req.getParameterNames();
		while (vector.hasMoreElements()) {
			codprf = vector.nextElement().toString();
			String sw = req.getParameter(getCodprf()).toUpperCase();
			if (sw.equals("ON")) {
				query = "Insert into ";
				if (getCodmod().equals(CODMOD_BUILDER) || getCodmod().equals(CODMOD_MANAGER))
					query += ""+Constante.ESQUEMA1+".TUSRPRF ";
				if (getCodmod().equals(CODMOD_TELLER))
					query += ""+Constante.ESQUEMA1+".TBRAUSR ";
				if (getCodmod().equals(CODMOD_SIGN))
					query += ""+Constante.ESQUEMA1+".TUSRSGN ";
				query += " (codmod, codusr, codprf) values('" + getCodmod() + "', '" + getCodusr() + "', '" + getCodprf() + "') ";
				db.setQuery(query);
				db.executeUpdate();
				String bef[] = {" ", " ", " "};
				String aft[] = {getCodmod(), getCodusr(), getCodprf()};
				if (getCodmod().equals(CODMOD_BUILDER) || getCodmod().equals(CODMOD_MANAGER))
					LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", Constante.ESQUEMA1+".TUSRPRF", Table.getColumnsTusrprf(), bef, aft);
				if (getCodmod().equals(CODMOD_TELLER))
					LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", Constante.ESQUEMA1+".TBRAUSR", Table.getColumnsTbrausr(), bef, aft);
				if (getCodmod().equals(CODMOD_SIGN))
					LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", Constante.ESQUEMA1+".TUSRSGN", Table.getColumnsTusrsgn(), bef, aft);
				//
			}
		}
		/*LogDeUsuariosBranch log = new LogDeUsuariosBranch();
		log.setLogin(getLogin());
		if (getCodmod().equals(CODMOD_TELLER)) {
			log.setCodspv(getLogin().getUsuario());
			log.setCodusr(getCodusr());
			log.setDatbeg(CString.toDate("yyyy/MM/dd"));
			log.setTimbeg(CString.toTime());
			log.setCodrea(getCodrea());
			log.setCodbra(getCodbra());
			log.update(db);
		}*/
		loadGrid(db);
	}
	catch (Exception e) {
		throw new Exception("\n...aceptar() - " + getClass().getName() + " - > " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
public void consult() throws java.sql.SQLException {
	try {
		if (getCodmod().equals(""))
			return;
		String bef[] = {"", "", ""};
		String aft[] = {"", "", ""};
		if (getCodmod().equals(CODMOD_BUILDER) || getCodmod().equals(CODMOD_MANAGER))
			LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", Constante.ESQUEMA1+".TUSRPRF", Table.getColumnsTusrprf(), bef, aft);
		if (getCodmod().equals(CODMOD_TELLER))
			LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", Constante.ESQUEMA1+".TBRAUSR", Table.getColumnsTbrausr(), bef, aft);
		if (getCodmod().equals(CODMOD_SIGN))
			LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", Constante.ESQUEMA1+".TUSRSGN", Table.getColumnsTusrsgn(), bef, aft);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... consult - " + getClass().getName() + " -> " + e.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodprf() {
	if (codprf == null)
		codprf = "";
	return codprf.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxtprf() {
	CString str = new CString(txtprf);
	str.arrange();
	return str.toString();
}
public void loadGrid(JQuery db) throws java.sql.SQLException {
	setGrid(null);
	if (getCodmod().equals(""))
		return;
	try {
		String query = //
		"Select codprf, txtprf ";
		if (getCodmod().equals(CODMOD_BUILDER) || getCodmod().equals(CODMOD_MANAGER))
			query += "From "+Constante.ESQUEMA1+".TPRFDAT ";
		if (getCodmod().equals(CODMOD_TELLER))
			query += "From "+Constante.ESQUEMA1+".TPRFBRA ";
		if (getCodmod().equals(CODMOD_SIGN))
			query += "From "+Constante.ESQUEMA1+".TPRFSGN ";
		query += "Where codprf IN ( " + //
		"Select codprf ";
		if (getCodmod().equals(CODMOD_BUILDER) || getCodmod().equals(CODMOD_MANAGER))
			query += "From "+Constante.ESQUEMA1+".TUSRPRF ";
		if (getCodmod().equals(CODMOD_TELLER))
			query += "From "+Constante.ESQUEMA1+".TBRAUSR ";
		if (getCodmod().equals(CODMOD_SIGN))
			query += "From "+Constante.ESQUEMA1+".TUSRSGN ";
		query += "Where codusr = '" + getCodusr() + "' " + //
		"And codmod = '" + getCodmod() + "' )" + //
		"Order by codprf";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			setStatus("1");
			java.util.Vector row = new java.util.Vector(3);
			row.addElement(getStatus());
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			getGrid().addElement(row);
		}
		query = //
		"Select codprf, txtprf ";
		if (getCodmod().equals(CODMOD_BUILDER) || getCodmod().equals(CODMOD_MANAGER))
			query += "From "+Constante.ESQUEMA1+".TPRFDAT ";
		if (getCodmod().equals(CODMOD_TELLER))
			query += "From "+Constante.ESQUEMA1+".TPRFBRA ";
		if (getCodmod().equals(CODMOD_SIGN))
			query += "From "+Constante.ESQUEMA1+".TPRFSGN ";
		query += "Where codprf NOT IN ( " + //
		"Select codprf ";
		if (getCodmod().equals(CODMOD_BUILDER) || getCodmod().equals(CODMOD_MANAGER))
			query += "From "+Constante.ESQUEMA1+".TUSRPRF ";
		if (getCodmod().equals(CODMOD_TELLER))
			query += "From "+Constante.ESQUEMA1+".TBRAUSR ";
		if (getCodmod().equals(CODMOD_SIGN))
			query += "From "+Constante.ESQUEMA1+".TUSRSGN ";
		query += "Where codusr = '" + getCodusr() + "' " + //
		"And codmod = '" + getCodmod() + "' )" + //
		"Order by codprf";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			setStatus("0");
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
public void loadModulos() throws java.sql.SQLException {
	if (getModulos().size() == 0) {
		JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
		JQuery db = new JQuery(jd.getConnection());
		try {
			String query = //
			"Select codmod, txtmod " + //
			"From "+Constante.ESQUEMA1+".TMODDAT " + //
			"Where codmod IN " + //
			"(Select codmod " + //
			"from "+Constante.ESQUEMA1+".TUSRMOD " + //
			"where codusr = '" + getCodusr() + "' )";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector row = new java.util.Vector(2);
				row.addElement(db.stringValue(1));
				row.addElement(db.stringValue(2));
				getModulos().addElement(row);
			}
		}
		catch (Exception e) {
			throw new java.sql.SQLException("\n.... loadModulos() - " + getClass().getName() + " -> " + e.getMessage());
		}
		finally {
			db.close();
			jd.close();
		}
	}
}
public void next(int i) {
	setStatus(((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString());
	codprf = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
	txtprf = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
}
public void nextModulo(int i) {
	setCod_mod(((java.util.Vector) getModulos().elementAt(i)).elementAt(0).toString());
	setTxt_mod(((java.util.Vector) getModulos().elementAt(i)).elementAt(1).toString());
}
public boolean search(JQuery db) throws java.sql.SQLException {
	try {
		String query = //
		"Select codusr, codmod " + //
		"From "+Constante.ESQUEMA1+".TUSRMOD " + //
		"Where codusr = '" + getCodusr() + "' " + //
		"And codmod = '" + getCodmod() + "' ";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
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
public void setCodprf(String newValue) {
	this.codprf = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxtprf(String newValue) {
	this.txtprf = newValue;
}
}