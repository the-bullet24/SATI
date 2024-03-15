package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class Empresa {
	//
	private CosapiSoft.SARAWebBanking.InicioSesion login;
	//
	public java.util.Vector grid = null;





	public java.lang.String error="";



/**
 * AyudaDeCampo constructor comment.
 */
public Empresa() {
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
			"Insert into torgdat values ('" + getCodigo() + "', '" + getNombre() + "', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '" + getFlgman() + "')";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", "", ""};
			String aft[] = {getCodigo(), getNombre(), getFlgman()};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Agregar", "torgdat", Table.getColumnsTorgdat(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_MENSAJE_EXISTE);
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
		"Select codorg, txtemp, flgman  " + //
		"From torgdat " + //
		"Where codorg = '" + getCodigo() + "' ";//
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		codigo = db.stringValue(1);
		nombre = db.stringValue(2);
		flgman = db.stringValue(3);
	} catch (Exception e) {
		throw new Exception("\nBuscar - Datos de Empresa \n " + e.getMessage());
	}
	return true;
}
public void consultar() throws Exception {
	try {
		String bef[] = {"", "", ""};
		String aft[] = {"", "", ""};
		LogOpe.saveLogManager(login.getNameProducto(), login.getUsuario(), "Consultar", "torgdat", Table.getColumnsTorgdat(), bef, aft);
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
			"Delete from torgdat " + //
			"Where codorg = '" + getCodigo() + "' "; //
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {getCodigo(), getNombre(), getFlgman()};
			String aft[] = {"", "", ""};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Eliminar", "torgdat", Table.getColumnsTorgdat(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_MENSAJE_NO_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getCodigo() + str.substring(pos + 1);
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
			codigo = vector.nextElement().toString();
			String sw = req.getParameter(codigo).toUpperCase();
			if (sw.equals("ON")) {
				if (buscar(db)) {
					String query = //
					"Delete from torgdat " + //
					"Where codorg = '" + getCodigo() + "' "; //
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from torglst " + //
					"Where codorg = '" + getCodigo() + "' "; //
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from tlstdet " + //
					"Where codorg = '" + getCodigo() + "' "; //
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from tlstaut " + //
					"Where codorg = '" + getCodigo() + "' "; //
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from torgnot " + //
					"Where codorg = '" + getCodigo() + "' "; //
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from tusrorg " + //
					"Where codorg = '" + getCodigo() + "' "; //
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from tfacacc " + //
					"Where codorg = '" + getCodigo() + "' "; //
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {getCodigo(), getNombre(), getFlgman()};
					String aft[] = {"", "", ""};
					LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Eliminar", "torgdat", Table.getColumnsTorgdat(), bef, aft);
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
		"Select codorg, txtemp, flgman " + //
		"From torgdat "; //
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector();
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			row.addElement(db.stringValue(3));
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
		String nuevoMan=getFlgman();
		if (buscar(db)) {
			String query = //
			"Update torgdat set " + //
			"txtemp = '" + nuevoNom + "', " + //
			"flgman = '" + nuevoMan + "' " + //
			"Where codorg = '" + getCodigo() + "' "; //
			// System.out.println(query);
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getCodigo(), getNombre(), getFlgman()};
			String aft[] = {getCodigo(), nuevoNom, nuevoMan};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Modificar", "torgdat", Table.getColumnsTorgdat(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_MENSAJE_NO_EXISTE);
			int pos = str.indexOf('&');
			error = str.substring(0, pos) + getCodigo() + str.substring(pos + 1);
		}
	} catch (Exception e) {
		throw new Exception("\nModificar - Datos de Empresa \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public void next(int i) {
	codigo = ((java.util.Vector) grid.elementAt(i)).elementAt(0).toString();
	nombre = ((java.util.Vector) grid.elementAt(i)).elementAt(1).toString();
	flgman = ((java.util.Vector) grid.elementAt(i)).elementAt(2).toString();
}









/**
 * This method was created in VisualAge.
 * @param newValue CosapiSoft.SARAWebBanking.InicioSesion
 */
public void setLogin(CosapiSoft.SARAWebBanking.InicioSesion newValue) {
	this.login = newValue;
}

	public java.lang.String codigo="";	public java.lang.String flgman="";	public java.lang.String nombre="";/**
 * Insert the method's description here.
 * Creation date: (3/8/01 2:18:24 PM)
 * @return java.lang.String
 */
public java.lang.String getCodigo() {
	return codigo;
}/**
 * Insert the method's description here.
 * Creation date: (3/8/01 2:28:03 PM)
 * @return java.lang.String
 */ 
public java.lang.String getError() {
	return error;
}/**
 * Insert the method's description here.
 * Creation date: (3/8/01 2:19:03 PM)
 * @return java.lang.String
 */ 
public java.lang.String getFlgman() {
	return flgman;
}/**
 * Insert the method's description here.
 * Creation date: (3/8/01 2:17:42 PM)
 * @return java.lang.String
 */ 
public java.lang.String getNombre() {
	return nombre;
}/**
 * Insert the method's description here.
 * Creation date: (3/8/01 2:18:24 PM)
 * @param newCodigo java.lang.String
 */ 
public void setCodigo(java.lang.String newCodigo) {
	codigo = newCodigo;
}/**
 * Insert the method's description here.
 * Creation date: (3/8/01 2:28:03 PM)
 * @param newError java.lang.String
 */ 
public void setError(java.lang.String newError) {
	error = newError;
}/**
 * Insert the method's description here.
 * Creation date: (3/8/01 2:19:03 PM)
 * @param newFlgman java.lang.String
 */ 
public void setFlgman(java.lang.String newFlgman) {
	flgman = newFlgman;
}/**
 * Insert the method's description here.
 * Creation date: (3/8/01 2:17:42 PM)
 * @param newNombre java.lang.String
 */ 
public void setNombre(java.lang.String newNombre) {
	nombre = newNombre;
}}