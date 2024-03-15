package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class MaestroDeMonedas {
	//
	private InicioSesion login;
	//
	private java.util.Vector grid = null;
	private String status = "0";
	private String error = "";
	private String codcur = "";
	private String txtcurlon = "";
	private String txtcursht = "";
	private String codcurbcr = "";
	private String codcurswi = "";
	private String codcursym = "";
	private String amoarbpur = "";
	private String amoarbsal = "";
	private String flgcurdec = "";
	//
	private String cod_cur = "";
	private String txt_curlon = "";
	private String txt_cursht = "";
	private String cod_curbcr = "";
	private String cod_curswi = "";
	private String cod_cursym = "";
	private String amo_arbpur = "";
	private String amo_arbsal = "";
	private String flg_curdec = "";
/**
 * AyudaDeCampo constructor comment.
 */
public MaestroDeMonedas() {
	super();
	grid = new java.util.Vector(1);
}
public void agregar() throws Exception {
	try {
		error = "";
		JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
		JQuery db = new JQuery(jd.getConnection());
		if (!buscar(db)) {
			String query = //
			"Insert into tcurdat values ('" + getCodcur() + "', '" + getTxtcurlon() + "', '" + getTxtcursht() + "', '" + getCodcurbcr() + "', '" + getCodcurswi() + "', '" + getCodcursym() + "',  " + new Double(getAmoarbpur()).doubleValue() + ",  " + new Double(getAmoarbsal()).doubleValue() + ", '" + getFlgcurdec() + "')";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", "", "", "", "", "", "", "", ""};
			String aft[] = {getCodcur(), getTxtcurlon(), getTxtcursht(), getCodcurbcr(), getCodcurswi(), getCodcursym(), getAmoarbpur(), getAmoarbsal(), getFlgcurdec()};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Agregar", "tcurdat", Table.getColumnsTcurdat(), bef, aft);
			loadGrid(db);
		} else {
			error = "Error : Código de Moneda " + getCodcur() + " existe, ingrese otro Código";
		}
		db.close();
		jd.close();
	} catch (Exception e) {
		throw new Exception("\nAgregar - Maestro de Monedas  \n" + e.getMessage());
	}
}
public boolean buscar() throws Exception {
	try {
		error = "";
		JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
		JQuery db = new JQuery(jd.getConnection());
		boolean sw = buscar(db);
		db.close();
		jd.close();
		return sw;
	} catch (Exception e) {
		throw new Exception("\nBuscar - Maestro de Tablas \n" + e.getMessage());
	}
}
private boolean buscar(JQuery db) throws Exception {
	try {
		String query = //
		"Select codcur, txtcurlon, txtcursht, codcurbcr, codcurswi, codcursym, amoarbpur, amoarbsal, flgcurdec " + //
		"From tcurdat " + //
		"Where codcur = '" + getCodcur() + "'";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		codcur = db.stringValue(1);
		txtcurlon = db.stringValue(2);
		txtcursht = db.stringValue(3);
		codcurbcr = db.stringValue(4);
		codcurswi = db.stringValue(5);
		codcursym = db.stringValue(6);
		amoarbpur = db.stringValue(7);
		amoarbsal = db.stringValue(8);
		flgcurdec = db.stringValue(9);
		return true;
	} catch (Exception e) {
		throw new Exception("\nBuscar - Maestro de Monedas \n" + e.getMessage());
	}
}
public void consultar() throws Exception {
	try {
		String bef[] = {"", "", "", "", "", "", "", "", ""};
		String aft[] = {"", "", "", "", "", "", "", "", ""};
		LogOpe.saveLogManager(login.getNameProducto(), login.getUsuario(), "Consultar", "tcurdat", Table.getColumnsTcurdat(), bef, aft);
	} catch (Exception e) {
		throw new Exception(" \n Consultar - Maestro de Monedas \n" + e.getMessage());
	}
}
public void eliminar() throws Exception {
	try {
		error = "";
		JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
		JQuery db = new JQuery(jd.getConnection());
		if (buscar(db)) {
			String query = //
			"Delete from tcurdat Where codcur = '" + getCodcur() + "'";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {getCodcur(), getTxtcurlon(), getTxtcursht(), getCodcurbcr(), getCodcurswi(), getCodcursym(), getAmoarbpur(), getAmoarbsal(), getFlgcurdec()};
			String aft[] = {"", "", "", "", "", "", "", "", ""};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Eliminar", "tcurdat", Table.getColumnsTcurdat(), bef, aft);
			loadGrid(db);
		} else {
			error = "Error : Código de Moneda " + getCodcur() + " no existe, ingrese otro Código";
		}
		db.close();
		jd.close();
	} catch (Exception e) {
		throw new Exception("\nEliminar - Maestro de Monedas \n" + e.getMessage());
	}
}
public void eliminar(javax.servlet.http.HttpServletRequest req) throws Exception {
	try {
		error = "";
		JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
		JQuery db = new JQuery(jd.getConnection());
		java.util.Enumeration vector = req.getParameterNames();
		while (vector.hasMoreElements()) {
			codcur = vector.nextElement().toString();
			String sw = req.getParameter(getCodcur()).toUpperCase();
			if (sw.equals("ON")) {
				if (buscar(db)) {
					String query = //
					"Delete from tcurdat Where codcur = '" + getCodcur() + "'";
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {getCodcur(), getTxtcurlon(), getTxtcursht(), getCodcurbcr(), getCodcurswi(), getCodcursym(), getAmoarbpur(), getAmoarbsal(), getFlgcurdec()};
					String aft[] = {"", "", "", "", "", "", "", "", ""};
					LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Eliminar", "tcurdat", Table.getColumnsTcurdat(), bef, aft);
				}
			}
		}
		loadGrid(db);
		db.close();
		jd.close();
	} catch (Exception e) {
		throw new Exception("\nEliminar - Maestro de Monedas \n" + e.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getAmo_arbpur() {
	return amo_arbpur;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getAmo_arbsal() {
	return amo_arbsal;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getAmoarbpur() {
	CString str = new CString(amoarbpur);
	str.arrange();
	return str.toString().trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getAmoarbsal() {
	CString str = new CString(amoarbsal);
	str.arrange();
	return str.toString().trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_cur() {
	if (cod_cur == null)
		cod_cur = "";
	return cod_cur.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_curbcr() {
	return cod_curbcr;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_curswi() {
	return cod_curswi;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_cursym() {
	return cod_cursym;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodcur() {
	if (codcur == null)
		codcur = "";
	codcur = codcur.trim();
	if (!codcur.equals(""))
		codcur = CString.completeToBeginWithNChars(codcur, 3, '0');
	return codcur.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodcurbcr() {
	CString str = new CString(codcurbcr);
	str.arrange();
	return str.toString().trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodcurswi() {
	CString str = new CString(codcurswi);
	str.arrange();
	return str.toString().trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodcursym() {
	CString str = new CString(codcursym);
	str.arrange();
	return str.toString().trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getError() {
	return error;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getFlg_curdec() {
	return flg_curdec;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getFlgcurdec() {
	CString str = new CString(flgcurdec);
	str.arrange();
	return str.toString().trim();
}
/**
 * This method was created in VisualAge.
 * @return CosapiSoft.SARAWebBanking.InicioSesion
 */
public InicioSesion getLogin() {
	return login;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getStatus() {
	return status;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxt_curlon() {
	return txt_curlon;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxt_cursht() {
	return txt_cursht;
}
/**
 * This method was created in VisualAge.
 * @return String
 */
public String getTxtcurlon() {
	CString str = new CString(txtcurlon);
	str.arrange();
	return str.toString();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxtcursht() {
	CString str = new CString(txtcursht);
	str.arrange();
	return str.toString();
}
public String getUrlMaestroDeMonedas() {
	return "/sarawebbanking/servlet/MaestroDeMonedasServlet?BtnCur=Buscar&TxtCodcur=";
}
public void loadGrid() throws Exception {
	try {
		JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
		JQuery db = new JQuery(jd.getConnection());
		loadGrid(db);
		db.close();
		jd.close();
	} catch (Exception e) {
		throw new Exception("\nLoadGrid - Maestro de Tablas \n" + e.getMessage());
	}
}
private void loadGrid(JQuery db) throws Exception {
	grid = new java.util.Vector(10);
	try {
		String query = //
		"Select codcur, txtcurlon, txtcursht, codcurbcr, codcurswi, codcursym, amoarbpur, amoarbsal, flgcurdec " + //
		"From tcurdat " + //
		"Order by codcur";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			status = "0";
			java.util.Vector row = new java.util.Vector(3);
			row.addElement(status);
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			row.addElement(db.stringValue(3));
			row.addElement(db.stringValue(4));
			row.addElement(db.stringValue(5));
			row.addElement(db.stringValue(6));
			row.addElement(db.stringValue(7));
			row.addElement(db.stringValue(8));
			row.addElement(db.stringValue(9));
			grid.addElement(row);
		}
	} catch (Exception e) {
		throw new Exception("\nLoadGrid - Maestro de Tablas \n" + e.getMessage());
	}
}
public void modificar() throws Exception {
	try {
		error = "";
		JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
		JQuery db = new JQuery(jd.getConnection());
		String antTxtcurlon = txtcurlon;
		String antTxtcursht = txtcursht;
		String antCodcurbcr = codcurbcr;
		String antCodcurswi = codcurswi;
		String antCodcursym = codcursym;
		String antAmoarbpur = amoarbpur;
		String antAmoarbsal = amoarbsal;
		String antFlgcurdec = flgcurdec;
		if (buscar(db)) {
			String query = //
			"Update tcurdat set " + //
			"txtcurlon = '" + antTxtcurlon + "', " + //
			"txtcursht = '" + antTxtcursht + "', " + //
			"codcurbcr = '" + antCodcurbcr + "', " + //
			"codcurswi = '" + antCodcurswi + "', " + //
			"codcursym = '" + antCodcursym + "', " + //
			"amoarbpur = " + new Double(antAmoarbpur).doubleValue() + ", " + //
			"amoarbsal = " + new Double(antAmoarbsal).doubleValue() + ", " + //
			"flgcurdec = '" + antFlgcurdec + "' " + //
			"Where codcur = '" + getCodcur() + "' ";
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getCodcur(), getTxtcurlon(), getTxtcursht(), getCodcurbcr(), getCodcurswi(), getCodcursym(), getAmoarbpur(), getAmoarbsal(), getFlgcurdec()};
			String aft[] = {getCodcur(), antTxtcurlon, antTxtcursht, antCodcurbcr, antCodcurswi, antCodcursym, antAmoarbpur, antAmoarbsal, antFlgcurdec};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Modificar", "tcurdat", Table.getColumnsTcurdat(), bef, aft);
			loadGrid(db);
		} else {
			error = "Error : Código de Moneda " + getCodcur() + " no existe, ingrese otro Código";
		}
		db.close();
		jd.close();
	} catch (Exception e) {
		throw new Exception("\nModificar - Maestro de Monedas \n" + e.getMessage());
	}
}
public void next(int i) {
	status = ((java.util.Vector) grid.elementAt(i)).elementAt(0).toString();
	cod_cur = ((java.util.Vector) grid.elementAt(i)).elementAt(1).toString();
	txt_curlon = ((java.util.Vector) grid.elementAt(i)).elementAt(2).toString();
	txt_cursht = ((java.util.Vector) grid.elementAt(i)).elementAt(3).toString();
	cod_curbcr = ((java.util.Vector) grid.elementAt(i)).elementAt(4).toString();
	cod_curswi = ((java.util.Vector) grid.elementAt(i)).elementAt(5).toString();
	cod_cursym = ((java.util.Vector) grid.elementAt(i)).elementAt(6).toString();
	amo_arbpur = ((java.util.Vector) grid.elementAt(i)).elementAt(7).toString();
	amo_arbsal = ((java.util.Vector) grid.elementAt(i)).elementAt(8).toString();
	flg_curdec = ((java.util.Vector) grid.elementAt(i)).elementAt(9).toString();
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setAmo_arbpur(String newValue) {
	this.amo_arbpur = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setAmo_arbsal(String newValue) {
	this.amo_arbsal = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setAmoarbpur(String newValue) {
	this.amoarbpur = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setAmoarbsal(String newValue) {
	this.amoarbsal = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_cur(String newValue) {
	this.cod_cur = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_curbcr(String newValue) {
	this.cod_curbcr = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_curswi(String newValue) {
	this.cod_curswi = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_cursym(String newValue) {
	this.cod_cursym = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodcur(String newValue) {
	this.codcur = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodcurbcr(String newValue) {
	this.codcurbcr = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodcurswi(String newValue) {
	this.codcurswi = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodcursym(String newValue) {
	this.codcursym = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setError(String newValue) {
	this.error = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setFlg_curdec(String newValue) {
	this.flg_curdec = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setFlgcurdec(String newValue) {
	this.flgcurdec = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue CosapiSoft.SARAWebBanking.InicioSesion
 */
public void setLogin(InicioSesion newValue) {
	this.login = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setStatus(String newValue) {
	this.status = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxt_curlon(String newValue) {
	this.txt_curlon = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxt_cursht(String newValue) {
	this.txt_cursht = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setTxtcurlon(String newValue) {
	this.txtcurlon = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxtcursht(String newValue) {
	this.txtcursht = newValue;
}
	/**
	 * Returns the grid.
	 * @return java.util.Vector
	 */
	public java.util.Vector getGrid() {
		return grid;
	}

	/**
	 * Sets the grid.
	 * @param grid The grid to set
	 */
	public void setGrid(java.util.Vector grid) {
		this.grid = grid;
	}

}