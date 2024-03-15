package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class NominaDetalle {
	//
	private CosapiSoft.SARAWebBanking.InicioSesion login;
	//
	public java.util.Vector grid = null;





	public java.lang.String error="";



/**
 * AyudaDeCampo constructor comment.
 */
public NominaDetalle() {
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
			"Insert into tlstdet values ('"+getCodorg()+"','" + getCodlst() + "','" + getCodigo() + "', '" + getNombre() + "', '" + getApaterno() + "', '" + getAmaterno() + "', " + getMonto() + ",'"+getActivo()+"','"+getCuenta()+"')";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", "", "","","","","","",""};
			String aft[] = {getCodorg(),getCodlst(),getCodigo(), getNombre(), getApaterno(), getAmaterno(), getMonto(), getActivo(),getCuenta()};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Agregar", "tlstdet", Table.getColumnsTlstdet(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_USUARIO_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getCodigo() + str.substring(pos + 1);
		}
	} catch (Exception e) {
		throw new Exception("\nAgregar - Datos de Empresa \n" + e.getMessage());
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
		String query = //
		"Select codemp, txtnam, lstnam, famnam, amoemp, flgact, numacc  " + //
		"From tlstdet " + //
		"Where codorg = '" + getCodorg() + "' and codlst='"+getCodlst()+"' and codemp='"+getCodigo()+"'";//
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		codigo = db.stringValue(1);
		nombre = db.stringValue(2);
		apaterno = db.stringValue(3);
		amaterno = db.stringValue(4);
		monto = db.stringValue(5);
		activo = db.stringValue(6);
		cuenta = db.stringValue(7);
	} catch (Exception e) {
		throw new Exception("\nBuscar - Detalle de Nomina \n " + e.getMessage());
	}
	return true;
}
public void consultar() throws Exception {
	try {
		String bef[] = {"", "", "","","", "", "","",""};
		String aft[] = {"", "", "","","", "", "","",""};
		LogOpe.saveLogManager(login.getNameProducto(), login.getUsuario(), "Consultar", "tlstdet", Table.getColumnsTlstdet(), bef, aft);
	} catch (Exception e) {
		throw new Exception(" \n Consultar - Detalle de Nomina \n" + e.getMessage());
	}
}
public void eliminar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		if (buscar(db)) {
			String query = //
			"Delete from tlstdet " + //
			"Where codorg = '" + getCodorg() + "' and codlst='"+getCodlst()+"' and codemp='"+getCodigo()+"'"; //
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {getCodorg(),getCodlst(), getCodigo(), getNombre(), getApaterno(), getAmaterno(), getMonto(), getActivo(), getCuenta()};
			String aft[] = {"", "", "", "","", "", "", "",""};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Eliminar", "tlstdet", Table.getColumnsTlstdet(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_EMPLEADO_NO_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getCodigo() + str.substring(pos + 1);
		}
	} catch (Exception e) {
		throw new Exception("\nEliminar - Detalle Nomina \n" + e.getMessage());
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
			codigo = vector.nextElement().toString();
			String sw = req.getParameter(codigo).toUpperCase();
			if (sw.equals("ON")) {
				if (buscar(db)) {
					String query = //
					"Delete from tlstdet " + //
					"Where codorg = '" + getCodorg() + "' and codlst='"+getCodlst()+"' and codemp='"+getCodigo()+"'"; //
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {getCodorg(), getCodlst(), getCodigo(), getNombre(), getApaterno(), getAmaterno(), getMonto(),getActivo(),getCuenta()};
					String aft[] = {"", "", "", "","", "", "", "",""};
					LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Eliminar", "tlstdet", Table.getColumnsTlstdet(), bef, aft);
				}
			}
		}
		loadGrid(db);
	} catch (Exception e) {
		throw new Exception("\nEliminar Detalle Nomina \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}








/**
 * This method was created in VisualAge.
 * @return CosapiSoft.SARAWebBanking.InicioSesion
 */
public CosapiSoft.SARAWebBanking.InicioSesion getLogin() {
	return login;
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
		String query = //
		"Select codemp, txtnam, lstnam, famnam, amoemp, flgact, numacc " + //
		"From tlstdet where codorg='"+getCodorg()+"' and codlst='"+getCodlst()+"'"; //
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector();
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
		throw new Exception("\nLoadGrid - Detalle Nomina \n" + e.getMessage());
	}
}
public void modificar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		String nuevoNom=getNombre();
		String nuevoApa=getApaterno();
		String nuevoAma=getAmaterno();
		String nuevoMon=getMonto();
		String nuevoAct=getActivo();
		String nuevoCta=getCuenta();
		if (buscar(db)) {
			String query = //
			"Update tlstdet set " + //
			"txtnam = '" + nuevoNom + "', " + //
			"lstnam = '" + nuevoApa + "', " + //
			"famnam = '" + nuevoAma + "', " + //
			"amoemp = " + nuevoMon + ", " + //
			"flgact = '" + nuevoAct + "', " + //
			"numacc = '" + nuevoCta + "' " + //
			"Where codorg = '" + getCodorg() + "' and codlst='"+getCodlst()+"' and codemp='"+getCodigo()+"'"; //
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getCodorg(), getCodlst(), getCodigo(), getNombre(), getApaterno(), getAmaterno(), getMonto(), getActivo(), getCuenta()};
			String aft[] = {getCodorg(), getCodlst(), getCodigo(), nuevoNom, nuevoApa, nuevoAma, nuevoMon, nuevoAct,nuevoCta};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Modificar", "tlstdet", Table.getColumnsTlstdet(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_EMPLEADO_NO_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getCodigo() + str.substring(pos + 1);
		}
	} catch (Exception e) {
		throw new Exception("\nModificar - Detalle Nomina \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public void next(int i) {
	codigo = ((java.util.Vector) grid.elementAt(i)).elementAt(0).toString();
	nombre = ((java.util.Vector) grid.elementAt(i)).elementAt(1).toString();
	apaterno = ((java.util.Vector) grid.elementAt(i)).elementAt(2).toString();
	amaterno = ((java.util.Vector) grid.elementAt(i)).elementAt(3).toString();
	monto = ((java.util.Vector) grid.elementAt(i)).elementAt(4).toString();
	activo = ((java.util.Vector) grid.elementAt(i)).elementAt(5).toString();
	cuenta = ((java.util.Vector) grid.elementAt(i)).elementAt(6).toString();
}









/**
 * This method was created in VisualAge.
 * @param newValue CosapiSoft.SARAWebBanking.InicioSesion
 */
public void setLogin(CosapiSoft.SARAWebBanking.InicioSesion newValue) {
	this.login = newValue;
}

	public java.lang.String activo = "";	public java.lang.String amaterno = "";	public java.lang.String apaterno = "";	public java.lang.String codigo = "";	public java.lang.String codlst = "";	public java.lang.String codorg="";	public java.lang.String cuenta = "";	public java.lang.String monto = "";	public java.lang.String nombre="";/**
 * Insert the method's description here.
 * Creation date: (3/12/01 11:28:58 AM)
 * @return java.lang.String
 */
public java.lang.String getActivo() {
	return activo;
}/**
 * Insert the method's description here.
 * Creation date: (3/9/01 11:54:14 AM)
 * @return java.lang.String
 */ 
public java.lang.String getAmaterno() {
	return amaterno;
}/**
 * Insert the method's description here.
 * Creation date: (3/9/01 11:53:48 AM)
 * @return java.lang.String
 */ 
public java.lang.String getApaterno() {
	return apaterno;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 11:11:39 AM)
 * @return java.lang.String
 */ 
public java.lang.String getCodigo() {
	return codigo;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 11:12:48 AM)
 * @return java.lang.String
 */ 
public java.lang.String getCodlst() {
	return codlst;
}/**
 * Insert the method's description here.
 * Creation date: (3/9/01 9:56:22 AM)
 * @return java.lang.String
 */ 
public java.lang.String getCodorg() {
	return codorg;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 11:26:03 AM)
 * @return java.lang.String
 */ 
public java.lang.String getCuenta() {
	return cuenta;
}/**
 * Insert the method's description here.
 * Creation date: (3/8/01 2:28:03 PM)
 * @return java.lang.String
 */ 
public java.lang.String getError() {
	return error;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 11:12:03 AM)
 * @return java.lang.String
 */ 
public java.lang.String getMonto() {
	return monto;
}/**
 * Insert the method's description here.
 * Creation date: (3/8/01 2:17:42 PM)
 * @return java.lang.String
 */ 
public java.lang.String getNombre() {
	return nombre;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 11:28:58 AM)
 * @param newActivo java.lang.String
 */ 
public void setActivo(java.lang.String newActivo) {
	activo = newActivo;
}/**
 * Insert the method's description here.
 * Creation date: (3/9/01 11:54:14 AM)
 * @param newAmaterno java.lang.String
 */ 
public void setAmaterno(java.lang.String newAmaterno) {
	amaterno = newAmaterno;
}/**
 * Insert the method's description here.
 * Creation date: (3/9/01 11:53:48 AM)
 * @param newApaterno java.lang.String
 */ 
public void setApaterno(java.lang.String newApaterno) {
	apaterno = newApaterno;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 11:11:39 AM)
 * @param newCodigo java.lang.String
 */ 
public void setCodigo(java.lang.String newCodigo) {
	codigo = newCodigo;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 11:12:48 AM)
 * @param newCodlst java.lang.String
 */ 
public void setCodlst(java.lang.String newCodlst) {
	codlst = newCodlst;
}/**
 * Insert the method's description here.
 * Creation date: (3/9/01 9:56:22 AM)
 * @param newCodorg java.lang.String
 */ 
public void setCodorg(java.lang.String newCodorg) {
	codorg = newCodorg;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 11:26:03 AM)
 * @param newCuenta java.lang.String
 */ 
public void setCuenta(java.lang.String newCuenta) {
	cuenta = newCuenta;
}/**
 * Insert the method's description here.
 * Creation date: (3/8/01 2:28:03 PM)
 * @param newError java.lang.String
 */ 
public void setError(java.lang.String newError) {
	error = newError;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 11:12:03 AM)
 * @param newMonto java.lang.String
 */ 
public void setMonto(java.lang.String newMonto) {
	monto = newMonto;
}/**
 * Insert the method's description here.
 * Creation date: (3/8/01 2:17:42 PM)
 * @param newNombre java.lang.String
 */ 
public void setNombre(java.lang.String newNombre) {
	nombre = newNombre;
}}