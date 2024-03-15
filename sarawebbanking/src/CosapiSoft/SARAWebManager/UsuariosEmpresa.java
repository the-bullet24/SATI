package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class UsuariosEmpresa {
	//
	private CosapiSoft.SARAWebBanking.InicioSesion login;
	//
	public java.util.Vector grid = null;





	public java.lang.String error="";



/**
 * AyudaDeCampo constructor comment.
 */
public UsuariosEmpresa() {
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
			"Insert into tusrorg values ('"+getCodorg()+"','06','" + getId() + "', '" + getNombre() + "', '" + getApaterno() + "', '" + getAmaterno() + "', '" + getSupervisor() + "','"+getPass()+"','"+getEmail()+"')";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", "", "","","","","","",""};
			String aft[] = {getCodorg(),"06",getId(), getNombre(), getApaterno(), getAmaterno(), getSupervisor(), getPass(),getEmail()};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Agregar", "tusrorg", Table.getColumnsTusrorg(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_USUARIO_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getId() + str.substring(pos + 1);
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
		"Select codusr, txtnam, lstnam, famnam, flgsup, txtpas, txteml  " + //
		"From tusrorg " + //
		"Where codorg = '" + getCodorg() + "' and codusr='"+getId()+"'";//
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		id = db.stringValue(1);
		nombre = db.stringValue(2);
		apaterno = db.stringValue(3);
		amaterno = db.stringValue(4);
		supervisor = db.stringValue(5);
		pass = db.stringValue(6);
		email = db.stringValue(7);
	} catch (Exception e) {
		throw new Exception("\nBuscar - Datos de Empresa \n " + e.getMessage());
	}
	return true;
}
public void consultar() throws Exception {
	try {
		String bef[] = {"", "", "","","", "", "","",""};
		String aft[] = {"", "", "","","", "", "","",""};
		LogOpe.saveLogManager(login.getNameProducto(), login.getUsuario(), "Consultar", "tusrorg", Table.getColumnsTusrorg(), bef, aft);
	} catch (Exception e) {
		throw new Exception(" \n Consultar - Datos de Empresa \n" + e.getMessage());
	}
}
public void eliminar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		if (buscar(db)) {
			String query = //
			"Delete from tusrorg " + //
			"Where codorg = '" + getCodorg() + "' and codusr='"+getId()+"'"; //
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {getCodorg(),"06", getId(), getNombre(), getApaterno(), getAmaterno(), getSupervisor(), getPass()};
			String aft[] = {"", "", "", "","", "", "", ""};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Eliminar", "torgnot", Table.getColumnsTorgnot(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_USUARIO_NO_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getId() + str.substring(pos + 1);
		}
	} catch (Exception e) {
		throw new Exception("\nEliminar - Datos de Empresa \n" + e.getMessage());
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
			id = vector.nextElement().toString();
			String sw = req.getParameter(id).toUpperCase();
			if (sw.equals("ON")) {
				if (buscar(db)) {
					String query = //
					"Delete from tusrorg " + //
					"Where codorg = '" + getCodorg() + "' and codusr='"+getId()+"'"; //
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from tfacacc " + //
					"Where codorg = '" + getCodorg() + "' and codusr='"+getId()+"'"; //
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {getCodorg(), "06", getId(), getNombre(), getApaterno(), getAmaterno(), getSupervisor(),getPass(),getEmail()};
					String aft[] = {"", "", "", "","", "", "", "",""};
					LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Eliminar", "tusrorg", Table.getColumnsTusrorg(), bef, aft);
				}
			}
		}
		loadGrid(db);
	} catch (Exception e) {
		throw new Exception("\nEliminar Datos de Empresa \n" + e.getMessage());
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
		"Select codusr, txtnam, lstnam, famnam, flgsup, txtpas, txteml " + //
		"From tusrorg where codorg='"+getCodorg()+"' "; //
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
		throw new Exception("\nLoadGrid - Datos de Empresa \n" + e.getMessage());
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
		String nuevoSup=getSupervisor();
		String nuevoPas=getPass();
		String nuevoEml=getEmail();
		if (buscar(db)) {
			String query = //
			"Update tusrorg set " + //
			"txtnam = '" + nuevoNom + "', " + //
			"lstnam = '" + nuevoApa + "', " + //
			"famnam = '" + nuevoAma + "', " + //
			"flgsup = '" + nuevoSup + "', " + //
			"txtpas = '" + nuevoPas + "', " + //
			"txteml = '" + nuevoEml + "' " + //
			"Where codorg = '" + getCodorg() + "' and codusr='"+getId()+"'"; //
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getCodorg(), "06", getId(), getNombre(), getApaterno(), getAmaterno(), getSupervisor(), getPass(), getEmail()};
			String aft[] = {getCodorg(), "06", getId(), nuevoNom, nuevoApa, nuevoAma, nuevoSup, nuevoPas,nuevoEml};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Modificar", "tusrorg", Table.getColumnsTusrorg(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_USUARIO_NO_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getId() + str.substring(pos + 1);
		}
	} catch (Exception e) {
		throw new Exception("\nModificar - Datos de Empresa \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public void next(int i) {
	id = ((java.util.Vector) grid.elementAt(i)).elementAt(0).toString();
	nombre = ((java.util.Vector) grid.elementAt(i)).elementAt(1).toString();
	apaterno = ((java.util.Vector) grid.elementAt(i)).elementAt(2).toString();
	amaterno = ((java.util.Vector) grid.elementAt(i)).elementAt(3).toString();
	supervisor = ((java.util.Vector) grid.elementAt(i)).elementAt(4).toString();
	pass = ((java.util.Vector) grid.elementAt(i)).elementAt(5).toString();
	email = ((java.util.Vector) grid.elementAt(i)).elementAt(6).toString();
}









/**
 * This method was created in VisualAge.
 * @param newValue CosapiSoft.SARAWebBanking.InicioSesion
 */
public void setLogin(CosapiSoft.SARAWebBanking.InicioSesion newValue) {
	this.login = newValue;
}

	public java.lang.String amaterno = "";	public java.lang.String apaterno = "";	public java.lang.String codorg="";	public java.lang.String email="";	public java.lang.String id = "";	public java.lang.String nombre="";	public java.lang.String pass = "";	public java.lang.String supervisor = "";/**
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
 * Creation date: (3/9/01 9:56:22 AM)
 * @return java.lang.String
 */ 
public java.lang.String getCodorg() {
	return codorg;
}/**
 * Insert the method's description here.
 * Creation date: (3/9/01 3:48:47 PM)
 * @return java.lang.String
 */ 
public java.lang.String getEmail() {
	return email;
}/**
 * Insert the method's description here.
 * Creation date: (3/8/01 2:28:03 PM)
 * @return java.lang.String
 */ 
public java.lang.String getError() {
	return error;
}/**
 * Insert the method's description here.
 * Creation date: (3/9/01 11:53:22 AM)
 * @return java.lang.String
 */ 
public java.lang.String getId() {
	return id;
}/**
 * Insert the method's description here.
 * Creation date: (3/8/01 2:17:42 PM)
 * @return java.lang.String
 */ 
public java.lang.String getNombre() {
	return nombre;
}/**
 * Insert the method's description here.
 * Creation date: (3/9/01 12:02:11 PM)
 * @return java.lang.String
 */ 
public java.lang.String getPass() {
	return pass;
}/**
 * Insert the method's description here.
 * Creation date: (3/9/01 11:57:02 AM)
 * @return java.lang.String
 */ 
public java.lang.String getSupervisor() {
	return supervisor;
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
 * Creation date: (3/9/01 9:56:22 AM)
 * @param newCodorg java.lang.String
 */ 
public void setCodorg(java.lang.String newCodorg) {
	codorg = newCodorg;
}/**
 * Insert the method's description here.
 * Creation date: (3/9/01 3:48:47 PM)
 * @param newEmail java.lang.String
 */ 
public void setEmail(java.lang.String newEmail) {
	email = newEmail;
}/**
 * Insert the method's description here.
 * Creation date: (3/8/01 2:28:03 PM)
 * @param newError java.lang.String
 */ 
public void setError(java.lang.String newError) {
	error = newError;
}/**
 * Insert the method's description here.
 * Creation date: (3/9/01 11:53:22 AM)
 * @param newId java.lang.String
 */ 
public void setId(java.lang.String newId) {
	id = newId;
}/**
 * Insert the method's description here.
 * Creation date: (3/8/01 2:17:42 PM)
 * @param newNombre java.lang.String
 */ 
public void setNombre(java.lang.String newNombre) {
	nombre = newNombre;
}/**
 * Insert the method's description here.
 * Creation date: (3/9/01 12:02:11 PM)
 * @param newPass java.lang.String
 */ 
public void setPass(java.lang.String newPass) {
	pass = newPass;
}/**
 * Insert the method's description here.
 * Creation date: (3/9/01 11:57:02 AM)
 * @param newSupervisor java.lang.String
 */ 
public void setSupervisor(java.lang.String newSupervisor) {
	supervisor = newSupervisor;
}}