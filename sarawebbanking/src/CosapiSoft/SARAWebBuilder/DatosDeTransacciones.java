package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class DatosDeTransacciones {
	//
	private InicioSesion login;
	//
	private java.util.Vector grid = null;
	private String status = "0";
	private String error = "";
	private String codtra = "";
	private String coddic = "";
	private String txtctr = "";
	private String ordsnd = "";
	private String txtdes = "";
	private String flgnul = "";
	private String rutval = "";
	private String tra_dic = "";
	//
	private String dic_nam = "";
	private String cod_dic = "";
	private String txt_ctr = "";
	private String ord_snd = "";
	private String txt_des = "";
	private String flg_nul = "";
	private String rut_val = "";
/**
 * AyudaDeCampo constructor comment.
 */
public DatosDeTransacciones() {
	super();
	grid = new java.util.Vector(10);
}
public void agregar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		setTra_dic("");
		if (!buscar(db)) {
			String query = //	
			"Insert into "+Constante.ESQUEMA1+".TCTRTRA values ('" + getCodtra() + "', '" + getCoddic() + "', '" + getTxtctr() + "', '" + getOrdsnd() + "', '" + getTxtdes() + "', '" + getFlgnul() + "', '" + getRutval() + "')";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", "", "", "", "", "", ""};
			String aft[] = {getCodtra(), getCoddic(), getTxtctr(), getOrdsnd(), getTxtdes(), getFlgnul(), getRutval()};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Agregar", Constante.ESQUEMA1+".TCTRTRA", Table.getColumnsTctrtra(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_DICCIONARIO_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getCoddic() + str.substring(pos + 1);
		}
	} catch (Exception e) {
		throw new Exception("\nAgregar - Datos de Transacciones \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public boolean buscar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		boolean sw = buscar(db);
		return sw;
	} catch (Exception e) {
		throw new Exception("\nBuscar - Datos de Transacciones \n " + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
private boolean buscar(JQuery db) throws Exception {
	try {
		if (getTra_dic() != null && !getTra_dic().equals("")) {
			java.util.StringTokenizer str = new java.util.StringTokenizer(getTra_dic(), "_");
			codtra = str.nextElement().toString();
			coddic = str.nextElement().toString();
		}
		String query = //
		"Select a.txtctr, a.ordsnd, a.txtdes, a.flgnul, a.rutval, b.txtdes " + //
		"From "+Constante.ESQUEMA1+".TCTRTRA a, "+Constante.ESQUEMA1+".tdicdat b " + //
		"Where a.codtra = '" + getCodtra() + "' " + //
		"And a.coddic = '" + getCoddic() + "' " + //
		"And a.coddic = b.coddic";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		txtctr = db.stringValue(1);
		ordsnd = db.stringValue(2);
		txtdes = db.stringValue(3);
		flgnul = db.stringValue(4);
		rutval = db.stringValue(5);
		dic_nam = db.stringValue(6);
		return true;
	} catch (Exception e) {
		throw new Exception("\nBuscar - Datos de Transacciones \n" + e.getMessage());
	}
}
public void consultar() throws Exception {
	try {
		String bef[] = {"", "", "", "", "", "", ""};
		String aft[] = {"", "", "", "", "", "", ""};
		LogOpe.saveLogManager(login.getNameProducto(), login.getUsuario(), "Consultar", Constante.ESQUEMA1+".TCTRTRA", Table.getColumnsTctrtra(), bef, aft);
	} catch (Exception e) {
		throw new Exception(" \n Consultar - Datos de Transacciones \n" + e.getMessage());
	}
}
public void eliminar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		if (buscar(db)) {
			String query = //
			"Delete from "+Constante.ESQUEMA1+".TCTRTRA " + //
			"Where codtra = '" + getCodtra() + "' " + //
			"And coddic = '" + getCoddic() + "'";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {getCodtra(), getCoddic(), getTxtctr(), getOrdsnd(), getTxtdes(), getFlgnul(), getRutval()};
			String aft[] = {"", "", "", "", "", "", ""};
			LogOpe.saveLogManager(login.getNameProducto(), login.getUsuario(), "Eliminar", Constante.ESQUEMA1+".TCTRTRA", Table.getColumnsTctrtra(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_DICCIONARIO_NO_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getCoddic() + str.substring(pos + 1);
		}
	} catch (Exception e) {
		throw new Exception("\nEliminar - Datos de Transacciones \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public void eliminar(javax.servlet.http.HttpServletRequest req) throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		java.util.Enumeration vector = req.getParameterNames();
		while (vector.hasMoreElements()) {
			tra_dic = vector.nextElement().toString();
			String sw = req.getParameter(tra_dic).toUpperCase();
			if (sw.equals("ON")) {
				java.util.StringTokenizer str = new java.util.StringTokenizer(tra_dic, "_");
				codtra = str.nextElement().toString();
				coddic = str.nextElement().toString();
				setTra_dic("");
				if (buscar(db)) {
					String query = //
					"Delete from "+Constante.ESQUEMA1+".TCTRTRA " + //
					"Where codtra = '" + getCodtra() + "' " + //
					"And coddic = '" + getCoddic() + "'";
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {getCodtra(), getCoddic(), getTxtctr(), getOrdsnd(), getTxtdes(), getFlgnul(), getRutval()};
					String aft[] = {"", "", "", "", "", "", ""};
					LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Eliminar", Constante.ESQUEMA1+".TCTRTRA", Table.getColumnsTctrtra(), bef, aft);
				}
			}
		}
		loadGrid(db);
	} catch (Exception e) {
		throw new Exception("\nEliminar - Datos de Transacciones \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_dic() {
	return cod_dic;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCoddic() {
	if (coddic == null)
		coddic = "";
	coddic = coddic.trim();
	if (!coddic.equals(""))
		coddic = CString.completeToBeginWithNChars(coddic, 3, '0');
	return coddic.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodtra() {
	if (codtra == null)
		codtra = "";
	codtra = codtra.trim();
	if (!codtra.equals(""))
		codtra = CString.completeToBeginWithNChars(codtra, 4, '0');
	return codtra.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getDic_nam() {
	CString str = new CString(dic_nam);
	str.arrange();
	return str.toString();
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
public String getFlg_nul() {
	return flg_nul;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getFlgnul() {
	if (flgnul == null || flgnul.equals(""))
		flgnul = "0";
	return flgnul.trim();
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
public String getOrd_snd() {
	return ord_snd;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getOrdsnd() {
	if (ordsnd == null)
		ordsnd = "";
	if (!ordsnd.equals(""))
		ordsnd = CString.completeToBeginWithNChars(ordsnd, 2, '0');
	return ordsnd.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getRut_val() {
	return rut_val;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getRutval() {
	if (rutval == null)
		rutval = "";
	CString str = new CString(rutval);
	str.arrange();
	return str.toString();
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
public String getTra_dic() {
	if (tra_dic == null)
		return "";
	return tra_dic;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxt_ctr() {
	return txt_ctr;
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
public String getTxtctr() {
	CString str = new CString(txtctr);
	str.arrange();
	return str.toString();
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
public String getUrlDatosDeTransacciones() {
	return JspServlet.DATOS_TRANSACCIONES_SERVLET + "?BtnTrx=Buscar&TxtTra_dic=";
}
public void loadGrid() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		loadGrid(db);
	} catch (Exception e) {
		throw new Exception("\nLoadGrid - Datos de Transacciones \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
private void loadGrid(JQuery db) throws Exception {
	grid = new java.util.Vector(10);
	try {
		String query = //
		"Select codtra, coddic, txtctr, ordsnd, txtdes, flgnul, rutval " + //
		"From "+Constante.ESQUEMA1+".TCTRTRA " + //
		"Where codtra = '" + getCodtra() + "' " + //
		"order by coddic, ordsnd, txtctr";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector(7);
			row.addElement("0");
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			row.addElement(db.stringValue(3));
			row.addElement(db.stringValue(4));
			row.addElement(db.stringValue(5));
			row.addElement(db.stringValue(6));
			row.addElement(db.stringValue(7));
			grid.addElement(row);
		}
	} catch (Exception e) {
		throw new Exception("\nLoadGrid - Datos de Transacciones \n" + e.getMessage());
	}
}
public void modificar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		String antTxtctr = getTxtctr();
		String antOrdsnd = getOrdsnd();
		String antTxtdes = getTxtdes();
		String antFlgnul = getFlgnul();
		String antRutval = getRutval();
		setTra_dic("");
		if (buscar(db)) {
			String query = //
			"Update "+Constante.ESQUEMA1+".TCTRTRA set " + //
			"txtctr = '" + antTxtctr + "', " + //
			"ordsnd = '" + antOrdsnd + "', " + //
			"txtdes = '" + antTxtdes + "', " + //
			"flgnul = '" + antFlgnul + "', " + //
			"rutval = '" + antRutval + "' " + //
			"Where codtra = '" + getCodtra() + "' " + //
			"And coddic = '" + getCoddic() + "' ";
			db.setQuery(query);
			int rows = db.executeUpdate();
			loadGrid(db);
			String bef[] = {getCodtra(), getCoddic(), getTxtctr(), getOrdsnd(), getTxtdes(), getFlgnul(), getRutval()};
			String aft[] = {getCodtra(), getCoddic(), antTxtctr, antOrdsnd, antTxtdes, antFlgnul, antRutval};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Modificar", Constante.ESQUEMA1+".TCTRTRA", Table.getColumnsTctrtra(), bef, aft);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_DICCIONARIO_NO_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getCoddic() + str.substring(pos + 1);
		}
	} catch (Exception e) {
		throw new Exception("\nModificar - Datos de Transacciones \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public void next(int i) {
	status = ((java.util.Vector) grid.elementAt(i)).elementAt(0).toString();
	codtra = ((java.util.Vector) grid.elementAt(i)).elementAt(1).toString();
	cod_dic = ((java.util.Vector) grid.elementAt(i)).elementAt(2).toString();
	txt_ctr = ((java.util.Vector) grid.elementAt(i)).elementAt(3).toString();
	ord_snd = ((java.util.Vector) grid.elementAt(i)).elementAt(4).toString();
	txt_des = ((java.util.Vector) grid.elementAt(i)).elementAt(5).toString();
	flg_nul = ((java.util.Vector) grid.elementAt(i)).elementAt(6).toString();
	rut_val = ((java.util.Vector) grid.elementAt(i)).elementAt(7).toString();
	tra_dic = getCodtra() + "_" + getCod_dic();
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_dic(String newValue) {
	this.cod_dic = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCoddic(String newValue) {
	this.coddic = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodtra(String newValue) {
	this.codtra = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setDic_nam(String newValue) {
	this.dic_nam = newValue;
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
public void setFlg_nul(String newValue) {
	this.flg_nul = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setFlgnul(String newValue) {
	this.flgnul = newValue;
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
public void setOrd_snd(String newValue) {
	this.ord_snd = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setOrdsnd(String newValue) {
	this.ordsnd = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setRut_val(String newValue) {
	this.rut_val = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setRutval(String newValue) {
	this.rutval = newValue;
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
public void setTra_dic(String newValue) {
	this.tra_dic = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxt_ctr(String newValue) {
	this.txt_ctr = newValue;
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
public void setTxtctr(String newValue) {
	this.txtctr = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxtdes(String newValue) {
	this.txtdes = newValue;
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