package CosapiSoft.SARAWebBanking;

import pe.cosapi.common.Constante;

/**
 * This type was created in VisualAge.
 */
public class LogDeOperaciones {
	//
	private InicioSesion login;
	//
	public java.util.Vector grid = null;
	public java.util.Vector usuarios = null;
	public java.util.Vector detalle = null;
	//
	private String txtmod = "";
	private String codusr = "";
	private String datpro = "";
	private String horpro = "";
	private String txtope = "";
	private String txttab = "";
	private String aftrcd = "";
	private String bfrrcd = "";
	//
	private String usr_nam = "";
	//
	private String horpro1 = "";
	private String horpro2 = "";
	private String datpro1 = "";
	private String datpro2 = "";
	//
	private String nameColumn = "";
	private String campoBef = "";
	private String campoAft = "";
	private String error = "";
	private String modulo = "";
/**
 * LogDeOperaciones constructor comment.
 */
public LogDeOperaciones() {
	super();
	grid = new java.util.Vector();
	usuarios = new java.util.Vector();
	detalle = new java.util.Vector();
}
public boolean buscar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		boolean sw = buscar(db);
		return sw;
	} catch (Exception e) {
		throw new Exception("\nBuscar - Log de Operaciones \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
private boolean buscar(JQuery db) throws Exception {
	try {
		String query = //
		"Select txtmod, codusr, datpro, horpro, txtope, txttab, aftrcd, bfrrcd " + //
		"From "+Constante.ESQUEMA1+".TLOGOPE " + //
		"Where txtmod = '" + getTxtmod().toUpperCase() + "' " + //
		"And codusr = '" + getCodusr().toUpperCase() + "' " + //
		"And datpro = '" + getDatpro().toUpperCase() + "' " + //
		"And horpro = '" + getHorpro().toUpperCase() + "' " + //
		"And txtope = '" + getTxtope().toUpperCase() + "' " + //
		"And txttab = '" + getTxttab().toUpperCase() + "' " + //
		"Order by datpro, horpro, txtope, txttab";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next()) {
			error = Mensajes.getMessage(Mensajes.NO_EXISTE_REGISTROS);
			return false;
		}
		txtmod = db.stringValue(1);
		codusr = db.stringValue(2);
		datpro = db.stringValue(3);
		horpro = db.stringValue(4);
		txtope = db.stringValue(5);
		txttab = db.stringValue(6);
		aftrcd = db.stringValue(7);
		bfrrcd = db.stringValue(8);
		return true;
	} catch (Exception e) {
		throw new Exception("\nBuscar - Log de Operaciones \n" + e.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 */
public void clearGrid() {
	grid = new java.util.Vector(1);
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getAftrcd() {
	return aftrcd;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getBfrrcd() {
	return bfrrcd;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCampoAft() {
	return campoAft;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCampoBef() {
	return campoBef;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodusr() {
	return codusr;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getDatpro() {
	return datpro.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getDatpro1() {
	CString str = new CString(datpro1);
	str.deleteAll('/');
	return str.toString().trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getDatpro2() {
	CString str = new CString(datpro2);
	str.deleteAll('/');
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
 * @return java.util.Vector
 */
public java.util.Vector getGrid() {
	if (grid == null)
		grid = new java.util.Vector(100,100);
	return grid;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getHorpro() {
	return horpro.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getHorpro1() {
	return horpro1.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getHorpro2() {
	return horpro2.trim();
}
/**
 * This method was created in VisualAge.
 */
public InicioSesion getLogin() {
	return this.login;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getModulo() {
	return modulo;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNameColumn() {
	return nameColumn.toUpperCase();
}
private String getQueryFecha() {
	if (getDatpro1().toUpperCase().equals("YYYYMMDD") && getDatpro2().toUpperCase().equals("YYYYMMDD"))
		return "";
	if (getDatpro1().toUpperCase().equals("00000000") && getDatpro2().toUpperCase().equals("00000000"))
		return "";
	if (getDatpro1().equals("") && getDatpro2().equals(""))
		return "";
	String str1 = "";
	if (!getDatpro1().equals(""))
		str1 = "datpro >= '" + getDatpro1() + "' ";
	String str2 = "";
	if (!getDatpro2().equals(""))
		str2 = "datpro <= '" + getDatpro2() + "' ";
	String str = "";
	if (!str1.equals("") && !str2.equals(""))
		str = "And (" + str1 + " and " + str2 + ") ";
	else {
		if (!str1.equals("")) {
			str = "And " + str1;
		} else {
			str = "And " + str2;
		}
	}
	return str;
}
private String getQueryHora() {
	if (getHorpro1().equals("00:00:00") && getHorpro2().equals("00:00:00"))
		return "";
	if (getHorpro1().equals("") && getHorpro2().equals(""))
		return "";
	String str1 = "";
	if (!getHorpro1().equals(""))
		str1 = "horpro >= '" + getHorpro1() + "' ";
	String str2 = "";
	if (!getHorpro2().equals(""))
		str2 = "horpro <= '" + getHorpro2() + "' ";
	String str = "";
	if (!str1.equals("") && !str2.equals(""))
		str = "And (" + str1 + " and " + str2 + ") ";
	else {
		if (!str1.equals("")) {
			str = "And " + str1;
		} else {
			str = "And " + str2;
		}
	}
	return str;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
private String getQueryOpe() {
	if (getTxtope().toUpperCase().equals("TODOS"))
		return "";
	String query = "And txtope = '" + getTxtope().toUpperCase() + "' ";
	return query;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
private String getQueryUsr() {
	if (codusr.toUpperCase().equals("TODOS"))
		return "";
	CString str = new CString(getCodusr());
	String query = "And codusr = '" + str.firstNChars(4).toString() + "' ";
	return query;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxtmod() {
	return txtmod.toUpperCase();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxtope() {
	return txtope.toUpperCase();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxttab() {
	return txttab.toUpperCase();
}
public String getUrlLogDeOperaciones() {
	if (modulo.toUpperCase().equals("MANAGER"))
		return JspServlet.LOG_DE_OPERACIONES_MANAGER_SERVLET + "?BtnLog=Buscar&Modulo=" + getModulo() + "&ChkLog=";
	else
		return JspServlet.LOG_DE_OPERACIONES_BUILDER_SERVLET + "?BtnLog=Buscar&Modulo=" + getModulo() + "&ChkLog=";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getUsr_nam() {
	return usr_nam.toUpperCase();
}
public boolean goTo(String pos) throws Exception {
	try {
		if (getGrid() == null || getGrid().size() == 0) {
			error = Mensajes.getMessage(Mensajes.LOG_DE_OPERACIONES_VACIA);
			return false;
		}
		if (pos == null || pos.equals("")) {
			error = Mensajes.getMessage(Mensajes.NUMERO_DE_SECUENCIA_NO_ENCONTRADO);
			return false;
		}
		int index = new Integer(pos.trim()).intValue();
		if (index < 0) {
			error = Mensajes.getMessage(Mensajes.DEBE_SELECCIONAR_NUMERO_DE_SECUENCIA);
			return false;
		}
		if (index >= getGrid().size()) {
			String msg = Mensajes.getMessage(Mensajes.NUMERO_DE_SECUENCIA_MAYOR_NRO_DE_REGISTROS);
			int p = msg.indexOf('&');
			error = msg.substring(0, p) + index + msg.substring(p + 1);
			return false;
		}
		next(index);
		return true;
	} catch (Exception e) {
		throw new Exception(" \n Log de Operaciones -> goTo(" + pos + ")  \n" + e.getMessage());
	}
}
public void loadComboUsr() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		usuarios = new java.util.Vector(20, 20);
		String query = //
		"Select codusr, txtnam " + //
		"From "+Constante.ESQUEMA1+".TUSRDAT " + //
		"Where codusr IN (Select codusr from tusrmod where codmod = '" + login.getCodmod() + "') " + //
		"Order by codusr";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector(3);
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			usuarios.addElement(row);
		}
	} catch (Exception e) {
		throw new Exception("\nLoadComboUsr() - Log de Operaciones \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public void loadDetalle() throws Exception {
	try {
		detalle = new java.util.Vector(10);
		String columns[] = LogOpe.getColumns(getAftrcd(), getBfrrcd());
		String aft[] = LogOpe.getDatos(getAftrcd());
		String bef[] = LogOpe.getDatos(getBfrrcd());
		for (int i = 0;(i < columns.length && i < aft.length && i < bef.length); i++) {
			java.util.Vector row = new java.util.Vector(3);
			row.addElement(columns[i]);
			row.addElement(bef[i]);
			row.addElement(aft[i]);
			detalle.addElement(row);
		}
	} catch (Exception e) {
		throw new Exception("\nloadDetalle() - Log de Operaciones \n" + e.getMessage());
	}
}
public void loadGrid() throws Exception {
	if (grid == null || grid.size() == 0) {
		JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
		JQuery db = new JQuery(jd.getConnection());
		try {
			loadGrid(db);
		}
		catch (Exception e) {
			throw new Exception("\nLoadGrid - Log de Operaciones \n" + e.getMessage());
		}
		finally {
			db.close();
			jd.close();
		}
	}
}
private void loadGrid(JQuery db) throws Exception {
	try {
		String query = //
		"Select txtmod, codusr, datpro, horpro, txtope, txttab, aftrcd, bfrrcd " + //
		"From "+Constante.ESQUEMA1+".TLOGOPE " + //
		"Where txtmod = '" + login.getNameProducto().toUpperCase() + "' " + //
		getQueryUsr() + //
		getQueryFecha() + //
		getQueryHora() + //
		getQueryOpe() + //
		"Order by codusr, datpro desc, horpro desc, txtope, txttab";
		// System.out.println(query);
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector(3);
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			row.addElement(db.stringValue(3));
			row.addElement(db.stringValue(4));
			row.addElement(db.stringValue(5));
			row.addElement(db.stringValue(6));
			row.addElement(db.stringValue(7));
			row.addElement(db.stringValue(8));
			getGrid().addElement(row);
		}
	} catch (Exception e) {
		throw new Exception("\nLoadGrid - Log de Operaciones \n" + e.getMessage());
	}
}
public void next(int i) {
	txtmod = ((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString();
	codusr = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
	datpro = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
	horpro = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
	txtope = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
	txttab = ((java.util.Vector) getGrid().elementAt(i)).elementAt(5).toString();
	aftrcd = ((java.util.Vector) getGrid().elementAt(i)).elementAt(6).toString();
	bfrrcd = ((java.util.Vector) getGrid().elementAt(i)).elementAt(7).toString();
}
public void nextDetalle(int i) {
	nameColumn = ((java.util.Vector) detalle.elementAt(i)).elementAt(0).toString();
	campoBef = ((java.util.Vector) detalle.elementAt(i)).elementAt(1).toString();
	campoAft = ((java.util.Vector) detalle.elementAt(i)).elementAt(2).toString();
	campoBef = (campoBef.equals("")) ? "Sin Dato" : campoBef;
	campoAft = (campoAft.equals("")) ? "Sin Dato" : campoAft;
}
public void nextUsr(int i) {
	usr_nam = ((java.util.Vector) usuarios.elementAt(i)).elementAt(0).toString() + " - " + ((java.util.Vector) usuarios.elementAt(i)).elementAt(1).toString();
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setAftrcd(String newValue) {
	this.aftrcd = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setBfrrcd(String newValue) {
	this.bfrrcd = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCampoAft(String newValue) {
	this.campoAft = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCampoBef(String newValue) {
	this.campoBef = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodusr(String newValue) {
	if (newValue == null)
		newValue = "";
	this.codusr = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setDatpro(String newValue) {
	this.datpro = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setDatpro1(String newValue) {
	this.datpro1 = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setDatpro2(String newValue) {
	this.datpro2 = newValue;
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
 * @param newValue java.util.Vector
 */
public void setGrid(java.util.Vector newValue) {
	this.grid = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setHorpro(String newValue) {
	this.horpro = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setHorpro1(String newValue) {
	this.horpro1 = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setHorpro2(String newValue) {
	this.horpro2 = newValue;
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
public void setModulo(String newValue) {
	this.modulo = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setNameColumn(String newValue) {
	this.nameColumn = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxtmod(String newValue) {
	this.txtmod = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxtope(String newValue) {
	this.txtope = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxttab(String newValue) {
	this.txttab = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setUsr_nam(String newValue) {
	this.usr_nam = newValue;
}
}