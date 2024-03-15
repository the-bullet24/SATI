package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class Diccionario {
	//
	private InicioSesion login;
	//
	public java.util.Vector grid = null;
	private String status = "0";
	private String error = "";
	private String coddic = "";
	private String txtdem = "";
	private String txtdes = "";
	private String numlon = "";
	private String idedic = "";
	private String txtfor = "";
	private String tipdat = "";
	//
	private String cod_dic = "";
	private String txt_des = "";
	private String txt_dem = "";
	private String num_lon = "";
	private String ide_dic = "";
	private String txt_for = "";
	private String tip_dat = "";
/**
 * AyudaDeCampo constructor comment.
 */
public Diccionario() {
	super();
	grid = new java.util.Vector(10);
}
public void agregar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		if (!buscar(db)) {
			String query = //
			"Insert into "+Constante.ESQUEMA1+".tdicdat values ('" + getCoddic() + "', '" + getTxtdes() + "', " + new Integer(getNumlon()).intValue() + ", '" + getIdedic() + "', '" + getTxtfor() + "', '" + getTipdat() + "', '" + getTxthlp() + "' )";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", "", "", "", "", "", ""};
			String aft[] = {getCoddic(), getTxtdes(), getNumlon(), getIdedic(), getTxtfor(), getTipdat(), getTxthlp()};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Agregar", Constante.ESQUEMA1+".TDICDAT", Table.getColumnsTdicdat(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_DICCIONARIO_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getCoddic() + str.substring(pos + 1);
		}
	} catch (Exception e) {
		throw new Exception(" \nAgregar - Diccionario \n" + e.getMessage());
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
		throw new Exception("\nBuscar - Diccionario \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
private boolean buscar(JQuery db) throws Exception {
	try {
		String query = //
		"Select coddic, txtdes, numlon, idedic, txtfor, tipdat, txthlp " + //
		"From "+Constante.ESQUEMA1+".TDICDAT " + //
		"Where coddic = '" + getCoddic() + "'";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		coddic = db.stringValue(1);
		txtdes = db.stringValue(2);
		numlon = db.stringValue(3);
		idedic = db.stringValue(4);
		txtfor = db.stringValue(5);
		tipdat = db.stringValue(6);
		txthlp = db.stringValue(7);
		
		return true;
	} catch (Exception e) {
		throw new Exception("\nBuscar - Diccionario \n" + e.getMessage());
	}
}
public void consultar() throws Exception {
	try {
		String bef[] = {"", "", "", "", "", "", ""};
		String aft[] = {"", "", "", "", "", "", ""};
		LogOpe.saveLogManager(login.getNameProducto(), login.getUsuario(), "Consultar", Constante.ESQUEMA1+".TDICDAT", Table.getColumnsTdicdat(), bef, aft);
	} catch (Exception e) {
		throw new Exception(" \n Consultar - Diccionario \n" + e.getMessage());
	}
}
public void eliminar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		if (buscar(db)) {
			String query = //
			"Delete from "+Constante.ESQUEMA1+".TJOUDIC Where coddic = '" + getCoddic() + "'";
			db.setQuery(query);
			db.executeUpdate();
			query = //
			"Delete from "+Constante.ESQUEMA1+".TCTRTRA Where coddic = '" + getCoddic() + "'";
			db.setQuery(query);
			db.executeUpdate();
			query = //
			"Delete from "+Constante.ESQUEMA1+".TCOMMSG Where coddic = '" + getCoddic() + "'";
			db.setQuery(query);
			db.executeUpdate();
			query = //
			"Delete from "+Constante.ESQUEMA1+".TDICDAT Where coddic = '" + getCoddic() + "'";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {getCoddic(), getTxtdes(), getNumlon(), getIdedic(), getTxtfor(), getTipdat(), getTxthlp()};
			String aft[] = {"", "", "", "", "", "", ""};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Eliminar",Constante.ESQUEMA1+".TDICDAT", Table.getColumnsTdicdat(), bef, aft);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_DICCIONARIO_NO_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getCoddic() + str.substring(pos + 1);
		}
	}
	catch (Exception e) {
		throw new Exception("\nEliminar - Diccionario \n" + e.getMessage());
	}
	finally {
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
		while (vector.hasMoreElements() && vector != null) {
			coddic = vector.nextElement().toString();
			String sw = req.getParameter(coddic).toUpperCase();
			if (sw.equals("ON")) {
				if (buscar(db)) {
					String query = //
					"Delete from "+Constante.ESQUEMA1+".TJOUDIC Where coddic = '" + coddic + "'";
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from "+Constante.ESQUEMA1+".TCTRTRA Where coddic = '" + coddic + "'";
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from "+Constante.ESQUEMA1+".TCOMMSG Where coddic = '" + coddic + "'";
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from "+Constante.ESQUEMA1+".TDICDAT Where coddic = '" + coddic + "'";
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {coddic, getTxtdes(), getNumlon(), getIdedic(), getTxtfor(), getTipdat(), getTxthlp()};
					String aft[] = {"", "", "", "", "", "", ""};
					LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Eliminar",Constante.ESQUEMA1+".TDICDAT", Table.getColumnsTdicdat(), bef, aft);
				}
			}
		}
		loadGrid(db);
	} catch (Exception e) {
		throw new Exception("\nEliminar - Diccionario \n" + e.getMessage());
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
	if (cod_dic == null)
		cod_dic = "";
	return cod_dic.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCoddic() {
	try {
		if (coddic == null || coddic.equals(""))
			coddic = "0";
		coddic = coddic.trim();
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(coddic)) {
			coddic = "0";
		}
		int cod = new Integer(coddic).intValue();
		if (cod <= 0) {
			JDatabase jd = new JDatabase();
			JQuery db = new JQuery(jd.getConnection());
			try {
				String query = //
				"Select coddic " + //
				"From "+Constante.ESQUEMA1+".TDICDAT " + //
				"Order by coddic ";
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					coddic = db.stringValue(1).trim();
				}
				cod = new Integer(coddic).intValue();
				coddic = (cod >= 999) ? "999" : "" + (cod + 1);
			} catch (Exception ex) {
				// System.out.println(getClass().getName() + " - getCoddic() --> " + ex.getMessage());
				return "-1";
			} finally {
				db.close();
				jd.close();
			}
		}
		if (!coddic.equals(""))
			coddic = CString.completeToBeginWithNChars(coddic, 3, '0');
		return coddic.trim();
	} catch (Exception e) {
		return "-1";
	}
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
public String getIde_dic() {
	return ide_dic;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getIdedic() {
	if (idedic == null || idedic.equals(""))
		idedic = "A";
	return idedic.trim();
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
public String getNum_lon() {
	return num_lon;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNumlon() {
	if (numlon == null)
		numlon = "";
	return numlon.trim();
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
public String getTip_dat() {
	return tip_dat;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTipdat() {
	if (tipdat == null || tipdat.equals(""))
		tipdat = "0";
	return tipdat.trim();
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
public String getTxt_for() {
	return txt_for;
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
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxtfor() {
	CString str = new CString(txtfor);
	str.arrange();
	return str.toString();
}
public String getUrlDiccionario() {
	return JspServlet.DICCIONARIO_SERVLET + "?BtnDic=Buscar&TxtCoddic=";
}
public void loadGrid() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		loadGrid(db);
	} catch (Exception e) {
		throw new Exception("\nLoadGrid - Diccionario \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
private void loadGrid(JQuery db) throws Exception {
	grid = new java.util.Vector(10);
	try {
		String query = //
		//"Select coddic, txtdes, numlon, idedic, txtfor, tipdat, txtfncval " + //
		"Select coddic, txtdes, numlon, idedic, txtfor, tipdat, txthlp " + //
		"From "+Constante.ESQUEMA1+".TDICDAT " + //
		"Order by coddic";
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
						
			grid.addElement(row);
		}
	} catch (Exception e) {
		throw new Exception("\nLoadGrid - Diccionario \n" + e.getMessage());
	}
}
public void modificar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		String antTxtdes = getTxtdes();
		String antNumlon = getNumlon();
		String antIdedic = getIdedic();
		String antTxtfor = getTxtfor();
		String antTipdat = getTipdat();
		String antTxthlp = getTxthlp();

		if (buscar(db)) {
			String query = //
			"Update "+Constante.ESQUEMA1+".TDICDAT set " + //
			"txtdes = '" + antTxtdes + "', " + //
			"numlon = " + new Integer(antNumlon).intValue() + ", " + //
			"idedic = '" + antIdedic + "', " + //
			"txtfor = '" + antTxtfor + "', " + //
			"tipdat = '" + antTipdat + "', " + //
			"txthlp = '" + antTxthlp + "' " + //
			"Where coddic = '" + getCoddic() + "' ";
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getCoddic(), getTxtdes(), getNumlon(), getIdedic(), getTxtfor(), getTipdat(), getTxthlp()};
			String aft[] = {getCoddic(), antTxtdes, antNumlon, antIdedic, antTxtfor, antTipdat, antTxthlp};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Modificar", Constante.ESQUEMA1+".TDICDAT", Table.getColumnsTdicdat(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_DICCIONARIO_NO_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getCoddic() + str.substring(pos + 1);
		}
	} catch (Exception e) {
		throw new Exception("\nModificar - Diccionario \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public void next(int i) {
	status = ((java.util.Vector) grid.elementAt(i)).elementAt(0).toString();
	cod_dic = ((java.util.Vector) grid.elementAt(i)).elementAt(1).toString();
	txt_des = ((java.util.Vector) grid.elementAt(i)).elementAt(2).toString();
	num_lon = ((java.util.Vector) grid.elementAt(i)).elementAt(3).toString();
	ide_dic = ((java.util.Vector) grid.elementAt(i)).elementAt(4).toString();
	txt_for = ((java.util.Vector) grid.elementAt(i)).elementAt(5).toString();
	tip_dat = ((java.util.Vector) grid.elementAt(i)).elementAt(6).toString();
	txt_hlp = ((java.util.Vector) grid.elementAt(i)).elementAt(7).toString();

}
public static boolean search(String coddic) throws Exception {
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		String query = //
		"Select coddic " + //
		"From "+Constante.ESQUEMA1+".TDICDAT " + //
		"Where coddic = '" + coddic + "'";
		db.setQuery(query);
		db.executeQuery();
		boolean sw = db.getResultSet().next();
		return sw;
	} catch (Exception e) {
		throw new Exception("\nSearch - Diccionario \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
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
public void setError(String newValue) {
	this.error = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setIde_dic(String newValue) {
	this.ide_dic = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setIdedic(String newValue) {
	this.idedic = newValue;
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
public void setNum_lon(String newValue) {
	this.num_lon = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setNumlon(String newValue) {
	this.numlon = newValue;
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
public void setTip_dat(String newValue) {
	this.tip_dat = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTipdat(String newValue) {
	this.tipdat = newValue;
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
public void setTxt_for(String newValue) {
	this.txt_for = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxtdes(String newValue) {
	this.txtdes = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxtfor(String newValue) {
	this.txtfor = newValue;
}
	private java.lang.String txt_fnc_val;	private java.lang.String txt_hlp;	private java.lang.String txtfncval;	private java.lang.String txthlp;/**
 * Insert the method's description here.
 * Creation date: (3/2/01 5:21:37 PM)
 * @return java.lang.String
 */
public java.lang.String getTxt_fnc_val() {
	return txt_fnc_val;
}/**
 * Insert the method's description here.
 * Creation date: (10/15/02 6:47:57 PM)
 * @return java.lang.String
 */ 
public java.lang.String getTxt_hlp() {
	return txt_hlp;
}/**
 * Insert the method's description here.
 * Creation date: (3/2/01 5:21:37 PM)
 * @return java.lang.String
 */ 
public java.lang.String getTxtfncval() {
	return txtfncval;
}/**
 * Insert the method's description here.
 * Creation date: (10/15/02 6:47:24 PM)
 * @return java.lang.String
 */ 
public java.lang.String getTxthlp() {
	return txthlp;
}/**
 * Insert the method's description here.
 * Creation date: (3/2/01 5:21:37 PM)
 * @param newTxtfncval java.lang.String
 */ 
public void setTxt_fnc_val(java.lang.String newTxtfncval) {
	txt_fnc_val = newTxtfncval;
}/**
 * Insert the method's description here.
 * Creation date: (10/15/02 6:47:57 PM)
 * @param newTxt_hlp java.lang.String
 */ 
public void setTxt_hlp(java.lang.String newTxt_hlp) {
	txt_hlp = newTxt_hlp;
}/**
 * Insert the method's description here.
 * Creation date: (3/2/01 5:21:37 PM)
 * @param newTxtfncval java.lang.String
 */ 
public void setTxtfncval(java.lang.String newTxtfncval) {
	txtfncval = newTxtfncval;
}/**
 * Insert the method's description here.
 * Creation date: (10/15/02 6:47:24 PM)
 * @param newTxthlp java.lang.String
 */ 
public void setTxthlp(java.lang.String newTxthlp) {
	txthlp = newTxthlp;
	/**
	 * Returns the grid.
	 * @return java.util.Vector
	 */

}
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

	/**
	 * Returns the txt_dem.
	 * @return String
	 */
	public String getTxt_dem() {
		return txt_dem;
	}

	/**
	 * Returns the txtdem.
	 * @return String
	 */
	public String getTxtdem() {
		return txtdem;
	}

	/**
	 * Sets the txt_dem.
	 * @param txt_dem The txt_dem to set
	 */
	public void setTxt_dem(String txt_dem) {
		this.txt_dem = txt_dem;
	}

	/**
	 * Sets the txtdem.
	 * @param txtdem The txtdem to set
	 */
	public void setTxtdem(String txtdem) {
		this.txtdem = txtdem;
	}

}