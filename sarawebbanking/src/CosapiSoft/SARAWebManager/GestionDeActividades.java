package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class GestionDeActividades {
	//
	private InicioSesion login;
	//
	public java.util.Vector grid = null;
	private String status = "0";
	private String codact = "";
	private String txtact = "";
	private String error = "";
	//
	private String cod_act = "";
	private String txt_act = "";
/**
 * AyudaDeCampo constructor comment.
 */
public GestionDeActividades() {
	super();
	grid = new java.util.Vector(1);
}
public void agregar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		if (!buscar(db)) {
			String query = //
			"Insert into "+Constante.ESQUEMA1+".TACTDAT values ('" + getCodact() + "', '" + getTxtact() + "')";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", ""};
			String aft[] = {getCodact(), getTxtact()};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Agregar", Constante.ESQUEMA1+".TACTDAT", Table.getColumnsTactdat(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_ACTIVIDAD_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getCodact() + str.substring(pos + 1);
		}
	} catch (Exception e) {
		throw new Exception("\nAgregar - Gestión de Actividades \n" + e.getMessage());
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
		throw new Exception("\nBuscar - Gestión de Actividades \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
private boolean buscar(JQuery db) throws Exception {
	try {
		String query = //
		"Select codact, txtact " + //
		"From "+Constante.ESQUEMA1+".TACTDAT " + //
		"Where codact = '" + getCodact() + "'";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		codact = db.stringValue(1);
		txtact = db.stringValue(2);
		return true;
	} catch (Exception e) {
		throw new Exception("\nBuscar - Gestión de Actividades \n" + e.getMessage());
	}
}
public void consultar() throws Exception {
	try {
		String bef[] = {"", ""};
		String aft[] = {"", ""};
		LogOpe.saveLogManager(login.getNameProducto(), login.getUsuario(), "Consultar", Constante.ESQUEMA1+".TACTDAT", Table.getColumnsTactdat(), bef, aft);
	} catch (Exception e) {
		throw new Exception(" \n Consultar - Gestión de Actividades \n" + e.getMessage());
	}
}
public void eliminar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		if (buscar(db)) {
			String query = //
			"Delete from "+Constante.ESQUEMA1+".TACTPRF Where codact = '" + getCodact() + "'";
			db.setQuery(query);
			db.executeUpdate();
			query = //
			"Delete from "+Constante.ESQUEMA1+".TACTDAT Where codact = '" + getCodact() + "'";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {getCodact(), getTxtact()};
			String aft[] = {"", ""};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Eliminar", Constante.ESQUEMA1+".TACTDAT", Table.getColumnsTactdat(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_ACTIVIDAD_NO_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getCodact() + str.substring(pos + 1);
		}
	} catch (Exception e) {
		throw new Exception("\nEliminar - Gestión de Actividades \n" + e.getMessage());
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
		while (vector.hasMoreElements() && vector != null) {
			codact = vector.nextElement().toString();
			String sw = req.getParameter(codact).toUpperCase();
			if (sw.equals("ON")) {
				if (buscar(db)) {
					String query = //
					"Delete from "+Constante.ESQUEMA1+".TACTPRF Where codact = '" + codact + "'";
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from "+Constante.ESQUEMA1+".TACTDAT Where codact = '" + codact + "'";
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {codact, getTxtact()};
					String aft[] = {"", ""};
					LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Eliminar", Constante.ESQUEMA1+".TACTDAT", Table.getColumnsTactdat(), bef, aft);
				}
			}
		}
		loadGrid(db);
	} catch (Exception e) {
		throw new Exception("\nEliminar - Gestión de Actividades \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_act() {
	if (cod_act == null)
		cod_act = "";
	return cod_act.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodact() {
	try {
		if (codact == null || codact.equals(""))
			codact = "0";
		codact = codact.trim();
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(codact)) {
			codact = "0";
		}
		int cod = new Integer(codact).intValue();
		if (cod <= 0) {
			JDatabase jd = new JDatabase();
			JQuery db = new JQuery(jd.getConnection());
			try {
				String query = //
				"Select codact " + //
				"From "+Constante.ESQUEMA1+".TACTDAT " + //
				"Order by codact ";
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					codact = db.stringValue(1).trim();
				}
				cod = new Integer(codact).intValue();
				codact = (cod >= 99) ? "99" : "" + (cod + 1);
			} catch (Exception ex) {
				// System.out.println(getClass().getName() + " - getCodact() --> " + ex.getMessage());
				return "-1";
			} finally {
				db.close();
				jd.close();
			}
		}
		if (!codact.equals(""))
			codact = CString.completeToBeginWithNChars(codact, 2, '0');
		return codact.trim();
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
public String getTxt_act() {
	return txt_act;
}
/**
 * This method was created in VisualAge.
 * @return String
 */
public String getTxtact() {
	CString str = new CString(txtact);
	str.arrange();
	return str.toString();
}
public String getUrlGestionDeActividades() {
	return JspServlet.GESTION_DE_ACTIVIDADES_SERVLET + "?BtnAct=Buscar&TxtCodact=";
}
public void loadGrid() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		loadGrid(db);
	} catch (Exception e) {
		throw new Exception("\nLoadGrid - Gestió de Actividades \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
private void loadGrid(JQuery db) throws Exception {
	grid = new java.util.Vector(10);
	try {
		String query = //
		"Select codact, txtact " + //
		"From "+Constante.ESQUEMA1+".TACTDAT " + //
		"Order by codact";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			status = "0";
			java.util.Vector row = new java.util.Vector(3);
			row.addElement(status);
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			grid.addElement(row);
		}
	} catch (Exception e) {
		throw new Exception("\nLoadGrid - Gestió de Actividades \n" + e.getMessage());
	}
}
public void modificar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		String antTxtact = txtact;
		if (buscar(db)) {
			String query = //
			"Update "+Constante.ESQUEMA1+".TACTDAT set txtact = '" + antTxtact + "' Where codact = '" + getCodact() + "' ";
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getCodact(), getTxtact()};
			String aft[] = {getCodact(), antTxtact};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Modificar", Constante.ESQUEMA1+".TACTDAT", Table.getColumnsTactdat(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_ACTIVIDAD_NO_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getCodact() + str.substring(pos + 1);
		}
	} catch (Exception e) {
		throw new Exception("\nModificar - Gestión de Actividades \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public void next(int i) {
	status = ((java.util.Vector) grid.elementAt(i)).elementAt(0).toString();
	cod_act = ((java.util.Vector) grid.elementAt(i)).elementAt(1).toString();
	txt_act = ((java.util.Vector) grid.elementAt(i)).elementAt(2).toString();
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_act(String newValue) {
	this.cod_act = newValue;
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
public void setError(String newValue) {
	this.error = newValue;
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
public void setTxt_act(String newValue) {
	this.txt_act = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setTxtact(String newValue) {
	this.txtact = newValue;
}
}