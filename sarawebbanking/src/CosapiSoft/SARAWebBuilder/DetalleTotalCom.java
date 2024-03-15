package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class DetalleTotalCom {
	//
	private CosapiSoft.SARAWebBanking.InicioSesion login;
	//
	public java.util.Vector grid = null;
	private String status = "0";




	private String error = "";



/**
 * AyudaDeCampo constructor comment.
 */
public DetalleTotalCom() {
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
			"Insert into ttotcomdet values ('" + getCodtotcom() + "', " + getNumsecmax() + ", '" + getCodtot() + "', '" + getSigope() + "')";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", "", "", ""};
			String aft[] = {getCodtotcom(), getNumsecmax(), getCodtot(), getSigope()};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Agregar", "ttotcomdet", Table.getColumnsTtotcomdet(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_MENSAJE_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getNumsec() + str.substring(pos + 1);
		}
	} catch (Exception e) {
		throw new Exception("\nAgregar - Datos de Mensajes \n" + e.getMessage());
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
		throw new Exception("\nBuscar Datos de Mensajes \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
private boolean buscar(JQuery db) throws Exception {
	try {
		String a=getNumsec();
		if (a.equals("")) a=getNumsecmax();
		String query = //
		"Select codtot, sigope, numsec " + //
		"From ttotcomdet " + //
		"Where codtotcom = '" + getCodtotcom() + "' " + //
		"And numsec = " + a;
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		codtot = db.stringValue(1);
		numsec = db.stringValue(3);
		sigope = db.stringValue(2);
	} catch (Exception e) {
		throw new Exception("\nBuscar - Detalle de Totales \n " + e.getMessage());
	}
	return true;
}
public void consultar() throws Exception {
	try {
		String bef[] = {"", "", "", ""};
		String aft[] = {"", "", "", ""};
		LogOpe.saveLogManager(login.getNameProducto(), login.getUsuario(), "Consultar", "ttotcomdet", Table.getColumnsTtotcomdet(), bef, aft);
	} catch (Exception e) {
		throw new Exception(" \n Consultar - Datos de Mensajes \n" + e.getMessage());
	}
}
public void eliminar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		if (buscar(db)) {
			String query = //
			"Delete from ttotcomdet " + //
			"Where codtotcom = '" + getCodtotcom() + "' " + //
			"And numsec = '" + getNumsec() + "' ";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {getCodtotcom(), getNumsec(), getCodtot(), getSigope()};
			String aft[] = {"", "", "", ""};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Eliminar", "ttotcomdet", Table.getColumnsTtotcomdet(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_MENSAJE_NO_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getNumsec() + str.substring(pos + 1);
		}
	} catch (Exception e) {
		throw new Exception("\nEliminar - Datos de Mensajes \n" + e.getMessage());
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
			numsec = vector.nextElement().toString();
			String sw = req.getParameter(numsec).toUpperCase();
			if (sw.equals("ON")) {
				if (buscar(db)) {
					String query = //
					"Delete from ttotcomdet " + //
					"Where codtotcom = '" + getCodtotcom() + "' " + //
					"And numsec = " + getNumsec() ;
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {getCodtotcom(), getNumsec(), getCodtot(), getSigope()};
					String aft[] = {"", "", "", ""};
					LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Eliminar", "ttotcomdet", Table.getColumnsTtotcomdet(), bef, aft);
				}
			}
		}
		loadGrid(db);
	} catch (Exception e) {
		throw new Exception("\nEliminar Items de Ayuda \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
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
public CosapiSoft.SARAWebBanking.InicioSesion getLogin() {
	return login;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getStatus() {
	return status;
}

public void loadGrid() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		loadGrid(db);
	} catch (Exception e) {
		throw new Exception("\nLoadGrid - Datos de Mensajes \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
private void loadGrid(JQuery db) throws Exception {
	grid = new java.util.Vector(10);
	try {
		String max="0";
		String query = //
		"Select codtotcom, numsec, codtot, sigope " + //
		"From ttotcomdet " + //
		"Where codtotcom = '" + getCodtotcom() + "'" + //
		"Order by codtotcom, numsec";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			status = "0";
			java.util.Vector row = new java.util.Vector(5);
			row.addElement(status);
			row.addElement(db.stringValue(1));
			max=db.stringValue(2);
			row.addElement(max);
			row.addElement(db.stringValue(3));
			row.addElement(db.stringValue(4));
			grid.addElement(row);
		}
		setNumsecmax(new Integer(Integer.valueOf(max).intValue()+1).toString());
	} catch (Exception e) {
		throw new Exception("\nLoadGrid - Detalle de Totales \n" + e.getMessage());
	}
}
public void modificar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		String antCodtot = getCodtot();
		String antSigope = getSigope();
		if (buscar(db)) {
			String query = //
			"Update ttotcomdet set " + //
			"codtot = '" + antCodtot + "', " + //
			"sigope = '" + antSigope + "' " + //
			"Where codtotcom = '" + getCodtotcom() + "' " + //
			"And numsec = '" + getNumsec() + "' ";
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getCodtotcom(), getNumsec(), getCodtot(), getSigope()};
			String aft[] = {getCodtotcom(), getNumsec(), antCodtot, antSigope};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Modificar", "ttotcomdet", Table.getColumnsTtotcomdet(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_MENSAJE_NO_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getNumsec() + str.substring(pos + 1);
		}
	} catch (Exception e) {
		throw new Exception("\nModificar - Datos de Mensajes \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public void next(int i) {
	status = ((java.util.Vector) grid.elementAt(i)).elementAt(0).toString();
	codtotcom = ((java.util.Vector) grid.elementAt(i)).elementAt(1).toString();
	num_sec = ((java.util.Vector) grid.elementAt(i)).elementAt(2).toString();
	cod_tot = ((java.util.Vector) grid.elementAt(i)).elementAt(3).toString();
	sig_ope = ((java.util.Vector) grid.elementAt(i)).elementAt(4).toString();
}
public boolean nuevoCodigo(String cod) {
	if (grid == null || grid.size() == 0)
		return false;
	next(0);
	return (!getCodtotcom().equals(cod));
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
public void setLogin(CosapiSoft.SARAWebBanking.InicioSesion newValue) {
	this.login = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setStatus(String newValue) {
	this.status = newValue;
}
	//
	private String cod_tot = "";	private String codtot = "";	private String codtotcom = "";	private String num_sec = "";	private String numsec = "";	private java.lang.String numsecmax;	private String sig_ope = "";	private String sigope = "";	private java.util.Vector totales;/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_tot() {
	if (cod_tot == null)
		cod_tot = "";
	return cod_tot.trim();
}/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */ 
public String getCodtot() {
	if (codtot == null)
		codtot = "";
	codtot = codtot.trim();
	if (!codtot.equals(""))
		codtot = CString.completeToBeginWithNChars(codtot, 2, '0');
	return codtot.trim();
}/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */ 
public String getCodtotcom() {
	if (codtotcom == null)
		codtotcom = "";
	return codtotcom.trim();
}/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */ 
public java.util.Vector getGrid() {
	return grid;
}/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */ 
public String getNum_sec() {
	return num_sec;
}/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */ 
public String getNumsec() {
	return numsec;
}/**
 * Insert the method's description here.
 * Creation date: (10/17/02 4:25:54 PM)
 * @return java.lang.String
 */ 
public java.lang.String getNumsecmax() {
	return numsecmax;
}/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */ 
public String getSig_ope() {
	return sig_ope;
}/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */ 
public String getSigope() {
	CString str = new CString(sigope);
	str.arrange();
	return str.toString();
}/**
 * Insert the method's description here.
 * Creation date: (10/17/02 5:29:19 PM)
 * @return java.util.Vector
 */ 
public java.util.Vector getTotales() {
	//totales=null;
	if (totales==null)
	{
		try{
			JDatabase jd = new JDatabase();
			JQuery db = new JQuery(jd.getConnection());
			totales=new java.util.Vector();
			String query = //
			"Select codtot, nomtot " + //
			"From ttotdat " + //
			"Order by codtot";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector row = new java.util.Vector(3);
				row.addElement(db.stringValue(1).trim());
				row.addElement(db.stringValue(2).trim());
				totales.addElement(row);
			}
		}
		catch(java.sql.SQLException e){
		}		
	}
	return totales;
}public String getUrlClasses() {
	return JspServlet.TOTALCOMDET_SERVLET + "?BtnDet=Buscar&TxtCodtotcom=" + getCodtotcom() + "&TxtNumsec=";
}/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */ 
public void setCod_tot(String newValue) {
	this.cod_tot = newValue;
}/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */ 
public void setCodtot(String newValue) {
	this.codtot = newValue;
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
public void setNum_sec(String newValue) {
	this.num_sec = newValue;
}/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */ 
public void setNumsec(String newValue) {
	this.numsec = newValue;
}/**
 * Insert the method's description here.
 * Creation date: (10/17/02 4:25:54 PM)
 * @param newNumsecmax java.lang.String
 */ 
public void setNumsecmax(java.lang.String newNumsecmax) {
	numsecmax = newNumsecmax;
}/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */ 
public void setSig_ope(String newValue) {
	this.sig_ope = newValue;
}/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */ 
public void setSigope(String newValue) {
	this.sigope = newValue;
}/**
 * Insert the method's description here.
 * Creation date: (10/17/02 5:29:19 PM)
 * @param newTotales java.util.Vector
 */ 
public void setTotales(java.util.Vector newTotales) {
	totales = newTotales;
}}