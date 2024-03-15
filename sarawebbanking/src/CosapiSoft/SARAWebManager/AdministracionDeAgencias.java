package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */

import java.util.Vector;

import pe.cosapi.common.Constante;


import CosapiSoft.SARAWebBanking.*;
public class AdministracionDeAgencias extends GenericTable {
	//
	private String codbra = "";
	private String numsum = "";
	private String flgblkbra = "";
	private String dayprgtra = "";
	private String bandef = "";
	private String houini = "";
	private String houfin = "";
	private String banpna = "";
	private String banpju = "";
	
	//
	private String cod_bra = "";
	private String num_sum = "";
	private String flg_blk_bra = "";
	private String day_prg_tra = "";
	private String ban_def = "";
	private String hou_ini = "";
	private String hou_fin = "";
	private String ban_pna = "";
	private String ban_pju = "";

	
/**
 * AyudaDeCampo constructor comment.
 */
public AdministracionDeAgencias() {
	super();
}
public void consult() throws java.sql.SQLException {
	try {
		// System.out.println("entró al consult");
		String bef[] = {"", "", "", "", "", "", "", "", ""};
		String aft[] = {"", "", "", "", "", "", "", "", ""};
		LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", "Tbrainf", Table.getColumnsTbrainf(), bef, aft);
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... consult() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public void delete(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		if (search(db)) {
			String query = //
			"Delete from "+Constante.ESQUEMA1+".Tbrainf Where codbra = '" + getCodbra() + "'";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {getCodbra(), getNumsum(), getFlgblkbra(), getDayprgtra(), getBandef(), getHouini(), getHoufin(), getBanpna(), getBanpju()};
			String aft[] = {"", "", "", "", "", "", "", "", ""};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", "Tbrainf", Table.getColumnsTbrainf(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_OFICINA_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodbra() + str.substring(pos + 1));
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
			codbra = vector.nextElement().toString();
			String sw = req.getParameter(codbra).toUpperCase();
			if (sw.equals("ON")) {
				if (search(db)) {
					String query = //
					"Delete from "+Constante.ESQUEMA1+".Tbrainf Where codbra = '" + codbra + "'";
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from "+Constante.ESQUEMA1+".tusrwks Where codbra = '" + getCodbra() + "'";
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from "+Constante.ESQUEMA1+".tbrawks Where codbra = '" + getCodbra() + "'";
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from "+Constante.ESQUEMA1+".tbradet Where codbra = '" + codbra + "'";
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from "+Constante.ESQUEMA1+".Tbrainf Where codbra = '" + codbra + "'";
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {getCodbra(), getNumsum(), getFlgblkbra(), getDayprgtra(), getBandef(), getHouini(), getHoufin(), getBanpna(), getBanpju()};
					String aft[] = {"", "", "", "", "", "", "", "", ""};
					LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", "Tbrainf", Table.getColumnsTbrainf(), bef, aft);
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
public String getCod_bra() {
	if (cod_bra == null)
		cod_bra = "";
	return cod_bra.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodbra() {
	try {
		if (codbra == null || codbra.equals(""))
			codbra = "0";
		codbra = codbra.trim();
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(codbra)) {
			codbra = "0";
		}
		int cod = new Integer(codbra).intValue();
		if (cod <= 0) {
			JDatabase jd = new JDatabase();
			JQuery db = new JQuery(jd.getConnection());
			try {
				String query = //
				"Select codbra " + //
				"From "+Constante.ESQUEMA1+".Tbrainf " + //
				"Order by codbra ";
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					codbra = db.stringValue(1).trim();
				}
				cod = new Integer(codbra).intValue();
				codbra = (cod >= 9999) ? "9999" : "" + (cod + 1);
			} catch (Exception ex) {
				// System.out.println(getClass().getName() + " - getCodbra() --> " + ex.getMessage());
				return "-1";
			} finally {
				db.close();
				jd.close();
			}
		}
		if (!codbra.equals(""))
			codbra = CString.completeToBeginWithNChars(codbra, 4, '0');
		return codbra.trim();
	} catch (Exception e) {
		return "-1";
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNameTable() {
	return "tbrainf";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNum_sum() {
	return num_sum;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getFlg_blk_bra() {
	return flg_blk_bra;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getDay_prg_tra() {
	return day_prg_tra;
}
public String getBan_def() {
	return ban_def;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getHou_ini() {
	CString str = new CString(hou_ini);
	str.arrange();
	return str.toString();
}
public String getHou_fin() {
	CString str = new CString(hou_fin);
	str.arrange();
	return str.toString();
}
public String getBan_pna() {
	CString str = new CString(ban_pna);
	str.arrange();
	return str.toString();
}
public String getBan_pju() {
	CString str = new CString(ban_pju);
	str.arrange();
	return str.toString();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNumsum() {
	CString str = new CString(numsum);
	str.arrange();
	return str.toString();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getFlgblkbra() {
	CString str = new CString(flgblkbra);
	str.arrange();
	return str.toString();
}

public String getDayprgtra() {
	CString str = new CString(dayprgtra);
	str.arrange();
	return str.toString();
}
public String getBandef() {
	CString str = new CString(bandef);
	str.arrange();
	return str.toString();
}
public String getHouini() {
	CString str = new CString(houini);
	str.arrange();
	return str.toString();
}
public String getHoufin() {
	CString str = new CString(houfin);
	str.arrange();
	return str.toString();
}
public String getBanpna() {
	CString str = new CString(banpna);
	str.arrange();
	return str.toString();
}
public String getBanpju() {
	CString str = new CString(banpju);
	str.arrange();
	return str.toString();
}
public String getUrl() {
	return getUrlAdministracionDeAgencias();
}
public String getUrlAdministracionDeAgencias() {
	return JspServlet.ADMINISTRACION_DE_AGENCIAS_SERVLET + "?BtnAge=Buscar&TxtCodbra=";
}
public void insert(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		if (!search(db)) {
			String query = //
			"Insert into "+Constante.ESQUEMA1+".Tbrainf (codbra, numsum, flgblkbra, dayprgtra, bandef, houini, houfin, banpna, banpju ) values ('" + getCodbra() + "', '" + getNumsum() + "', '" + getFlgblkbra() + "', '" + getDayprgtra() + "', '"+getBandef()+"','"+getHouini()+"','"+getHoufin()+"','"+getBanpna()+"','"+getBanpju()+"' )";
			db.setQuery(query);
			db.executeUpdate();
			
			String bef[] = {"", "", "", "", "", "", "", "", ""};
			String aft[] = {getCodbra(), getNumsum(), getFlgblkbra(), getDayprgtra(), getBandef(), getHouini(), getHoufin(), getBanpna(), getBanpju()};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", "Tbrainf", Table.getColumnsTbrainf(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_OFICINA_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodbra() + str.substring(pos + 1));
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
			"select codbra, numsum, flgblkbra, dayprgtra, bandef, houini, houfin, banpna, banpju from "+Constante.ESQUEMA1+".tbrainf ";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector row = new java.util.Vector();
				row.addElement(getStatus());
				row.addElement(db.stringValue(1));
				row.addElement(db.stringValue(2));
				row.addElement(db.stringValue(3));
				row.addElement(db.stringValue(4));
				row.addElement(db.stringValue(5));
				row.addElement(db.stringValue(6));
				row.addElement(db.stringValue(7));
				row.addElement(db.stringValue(8));
				row.addElement(db.stringValue(9));
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
	cod_bra = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
	num_sum = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
	flg_blk_bra = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
	day_prg_tra = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
	ban_def = ((java.util.Vector) getGrid().elementAt(i)).elementAt(5).toString();
	hou_ini = ((java.util.Vector) getGrid().elementAt(i)).elementAt(6).toString();
	hou_fin = ((java.util.Vector) getGrid().elementAt(i)).elementAt(7).toString();
	ban_pna = ((java.util.Vector) getGrid().elementAt(i)).elementAt(8).toString();
	ban_pju = ((java.util.Vector) getGrid().elementAt(i)).elementAt(9).toString();
}
public void nextChild(int i) {
}
public boolean search(JQuery db) throws java.sql.SQLException {
	try {
		String query = //
		"select codbra, numsum, flgblkbra, dayprgtra, bandef, houini, houfin, banpna, banpju " + //
		"From "+Constante.ESQUEMA1+".Tbrainf " + //
		"Where codbra = '" + getCodbra() + "'";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		codbra = db.stringValue(1);
		numsum = db.stringValue(2);
		flgblkbra = db.stringValue(3);
		dayprgtra = db.stringValue(4);
		bandef = db.stringValue(5);
		houini = db.stringValue(6);
		houfin = db.stringValue(7);
		banpna = db.stringValue(8);
		banpju = db.stringValue(9);
		
		return true;
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... search() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public static boolean search(String codbra) throws Exception {
	//InicioSesion.loadParameters();
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		String query = //
		"Select codbra " + //
		"From "+Constante.ESQUEMA1+".Tbrainf " + //
		"Where codbra = '" + codbra + "'";
		db.setQuery(query);
		db.executeQuery();
		boolean sw = db.getResultSet().next();
		return sw;
	}
	catch (Exception e) {
		throw new Exception("\n... search(" + codbra + ") - Administración de Agencias \n" + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_bra(String newValue) {
	this.cod_bra = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodbra(String newValue) {
	this.codbra = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setNum_sum(String newValue) {
	this.num_sum = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setFlg_blk_bra(String newValue) {
	this.flg_blk_bra = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setDay_prg_tra(String newValue) {
	this.day_prg_tra = newValue;
}

public void setBan_def(String newValue) {
	this.ban_def = newValue;
}

public void setHou_ini(String newValue) {
	this.hou_ini = newValue;
}

public void setHou_fin(String newValue) {
	this.hou_fin = newValue;
}
public void setBan_pna(String newValue) {
	this.ban_pna = newValue;
}

public void setBan_pju(String newValue) {
	this.ban_pju = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setNumsum(String newValue) {
	this.numsum = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setFlgblkbra(String newValue) {
	this.flgblkbra = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setDayprgtra(String newValue) {
	this.dayprgtra = newValue;
}

public void setBandef(String newValue) {
	this.bandef = newValue;
}

public void setHouini(String newValue) {
	this.houini = newValue;
}

public void setHoufin(String newValue) {
	this.houfin = newValue;
}
public void setBanpna(String newValue) {
	this.banpna = newValue;
}

public void setBanpju(String newValue) {
	this.banpju = newValue;
}
public void sort(JQuery db) throws java.sql.SQLException {
}
public void update(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		String antNumsum = getNumsum();
		String antFlgblkbra = getFlgblkbra();
		String antDayprgtra = getDayprgtra();
		String antBandef = getBandef();
		String antHouini = getHouini();
		String antHoufin = getHoufin();
		String antBanpna = getBanpna();
		String antBanpju = getBanpju();
		if (search(db)) {
			String query = //
			"Update "+Constante.ESQUEMA1+".Tbrainf set " + //
			"numsum = '" + antNumsum + "', " + //
			"flgblkbra = '" + antFlgblkbra + "', " + //
			"dayprgtra = '" + antDayprgtra + "', " + //
			"bandef = '" + antBandef + "', " + //
			"houini = '" + antHouini + "', " + //
			"houfin = '" + antHoufin + "', " + //
			"banpna = '" + antBanpna + "' " + //
			"banpju = '" + antBanpju + "' " + //
			"Where codbra = '" + getCodbra() + "' ";
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getCodbra(), getNumsum(), getFlgblkbra(), getDayprgtra(), getBandef(), getHouini(), getHoufin(), getBanpna(), getBanpju()};
			String aft[] = {getCodbra(), antNumsum, antFlgblkbra, antDayprgtra, antBandef, antHouini, antHoufin, antBanpna, antBanpju};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Modificar", "Tbrainf", Table.getColumnsTbrainf(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_OFICINA_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodbra() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... update() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
	

}