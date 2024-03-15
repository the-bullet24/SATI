package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class InformacionDeAgencias {
	//
	private InicioSesion login;
	//
	private String status = "0";
	private String error = "";
	private String codbra = "";
	private String numsum = "";
	private String flgblkbra = "";
	private String dayprgtra = "";
	private String bandef = "";
	private String houini = "";
	private String houfin = "";
	private String banpna = "";
	private String banpju = "";
	private String tipoEncripcion = "";
/**
 * AyudaDeCampo constructor comment.
 */
public InformacionDeAgencias() {
	super();
}
public void bloquearOficina() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		String query = "";
		String antFlgblkbra = getFlgblkbra();
		if (existeCodbraInInf(db)) { // Si existe algun registro en la tabla "+Constante.ESQUEMA1+".TBRAINF
			query = //
			"Update "+Constante.ESQUEMA1+".TBRAINF set " + //
			"flgblkbra = '" + antFlgblkbra + "' " + //
			"Where codbra = '" + getCodbra() + "' ";
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getCodbra(), getNumsum(), getFlgblkbra(), getDayprgtra(), getBandef(), getHouini(), getHoufin(), getBanpna(), getBanpju(), getTipoEncripcion()};
			String aft[] = {getCodbra(), getNumsum(), antFlgblkbra, getDayprgtra(), getBandef(), getHouini(), getHoufin(), getBanpna(), getBanpju(), getTipoEncripcion()};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Modificar", Constante.ESQUEMA1+".TBRAINF", Table.getColumnsTbrainf(), bef, aft);
			error = Mensajes.getMessage(Mensajes.ACTUALIZACION_EJECUTADA_CON_EXITO);
			loadGrid(db);
		} else {
			error = Mensajes.getMessage(Mensajes.DEBE_CREAR_LA_SUCURSAL_VIRTUAL);
		}
	} catch (Exception e) {
		throw new Exception("\nBloquearOficina - Información de Agencias \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
private boolean buscar(JQuery db) throws Exception {
	try {
		String query = //
		"select codbra, numsum, flgblkbra, dayprgtra, bandef, houini, houfin, banpna, banpju, TYPENCRIPCION " + //
		"From "+Constante.ESQUEMA1+".TBRAINF " + //
		"Where codbra = '" + getCodbra() + "'";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next()) {
			codbra = "";
			return false;
		}
		codbra = db.stringValue(1);
		numsum = db.stringValue(2);
		flgblkbra = db.stringValue(3);
		dayprgtra = db.stringValue(4);
		bandef = db.stringValue(5);
		houini = db.stringValue(6);
		houfin = db.stringValue(7);
		banpna = db.stringValue(8);
		banpju = db.stringValue(9);
		tipoEncripcion = db.stringValue(10);
		return true;
	} catch (Exception e) {
		throw new Exception("\nBuscar - Información de Agencias \n" + e.getMessage());
	}
}
private boolean buscar(JQuery db, String cod) throws Exception {
	try {
		String query = //
		"select codbra, numsum, flgblkbra, dayprgtra, bandef, houini, houfin, banpna, banpju, TYPENCRIPCION " + //
		"From "+Constante.ESQUEMA1+".TBRAINF " + //
		"Where codbra = '" + cod.trim() + "'";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next()) {
			return false;
		}
		codbra = db.stringValue(1);
		numsum = db.stringValue(2);
		flgblkbra = db.stringValue(3);
		dayprgtra = db.stringValue(4);
		bandef = db.stringValue(5);
		houini = db.stringValue(6);
		houfin = db.stringValue(7);
		banpna = db.stringValue(8);
		banpju = db.stringValue(9);
		tipoEncripcion = db.stringValue(10);
		return true;
	} catch (Exception e) {
		throw new Exception("\nBuscar - Información de Agencias \n" + e.getMessage());
	}
}
public void consultar() throws Exception {
	try {
		String bef[] = {"", "", "", "", "", "", "", "", "", ""};
		String aft[] = {"", "", "", "", "", "", "", "", "", ""};
		LogOpe.saveLogManager(login.getNameProducto(), login.getUsuario(), "Consultar", Constante.ESQUEMA1+".TBRAINF", Table.getColumnsTbrainf(), bef, aft);
	} catch (Exception e) {
		throw new Exception(" \n Consultar - Información de Agencias \n" + e.getMessage());
	}
}
private boolean existeCodbra(JQuery db, String cod) throws Exception {
	try {
		String query = //
		"Select codbra " + //
		"From tbradat " + //
		"Where codbra = '" + cod.trim() + "'";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next()) {
			return false;
		}
		return true;
	} catch (Exception e) {
		throw new Exception("\nexisteCodbra - Información de Agencias \n" + e.getMessage());
	}
}
public boolean existeCodbraInInf(JQuery db) throws Exception {
	try {
		String query = //
		"select codbra, numsum, flgblkbra, dayprgtra, bandef, houini, houfin, banpna, banpju, TYPENCRIPCION " + //
		"From "+Constante.ESQUEMA1+".TBRAINF ";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next()) {
			return false;
		}
		codbra = db.stringValue(1);
		numsum = db.stringValue(2);
		flgblkbra = db.stringValue(3);
		dayprgtra = db.stringValue(4);
		bandef = db.stringValue(5);
		houini = db.stringValue(6);
		houfin = db.stringValue(7);
		banpna = db.stringValue(8);
		banpju = db.stringValue(9);
		tipoEncripcion = db.stringValue(10);
		return true;
	} catch (Exception e) {
		throw new Exception("\nexisteCodbraInInf - Información de Agencias \n" + e.getMessage());
	}
}
public static boolean existeOficinaVirtual() throws Exception {
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		String query = //
		"Select codbra " + //
		"From "+Constante.ESQUEMA1+".TBRAINF ";
		db.setQuery(query);
		db.executeQuery();
		boolean sw = db.getResultSet().next();
		return sw;
	} catch (Exception e) {
		throw new Exception("\nexisteOficinaVirtual() - Información de Agencias \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodbra() {
	if (codbra == null)
		codbra = "";
	codbra = codbra.trim();
	if (!codbra.equals(""))
		codbra = CString.completeToBeginWithNChars(codbra, 4, '0');
	return codbra.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
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
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getDayprgtra() {
	CString str = new CString(dayprgtra);
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

public String getFlgblkbra() {
	CString str = new CString(flgblkbra);
	str.arrange();
	return str.toString();
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
public String getNumsum() {
	CString str = new CString(numsum);
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
public void loadGrid() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		loadGrid(db);
	} catch (Exception e) {
		throw new Exception("\nLoadGrid - Información de Agencias \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
private void loadGrid(JQuery db) throws Exception {
	try {
		String query = //
		"select codbra, numsum, flgblkbra, dayprgtra, bandef, houini, houfin, banpna, banpju, TYPENCRIPCION " + //
		"From "+Constante.ESQUEMA1+".TBRAINF " + //
		"Order by codbra";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			codbra = db.stringValue(1);
			numsum = db.stringValue(2);
			flgblkbra = db.stringValue(3);
			dayprgtra = db.stringValue(4);
			bandef = db.stringValue(5);
			houini = db.stringValue(6);
			houfin = db.stringValue(7);
			banpna = db.stringValue(8);
			banpju = db.stringValue(9);
			tipoEncripcion = db.stringValue(10);
		}
	} catch (Exception e) {
		throw new Exception("\nLoadGrid - Información de Agencias \n" + e.getMessage());
	}
}
public void loadInfBranch() throws Exception {
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		String query = //
		"select codbra, numsum, flgblkbra, dayprgtra, bandef, houini, houfin, banpna, banpju, TYPENCRIPCION " + //
		"From "+Constante.ESQUEMA1+".TBRAINF ";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next()) {
			return;
		}
		codbra = db.stringValue(1);
		numsum = db.stringValue(2);
		flgblkbra = db.stringValue(3);
		dayprgtra = db.stringValue(4);
		bandef = db.stringValue(5);
		houini = db.stringValue(6);
		houfin = db.stringValue(7);
		banpna = db.stringValue(8);
		banpju = db.stringValue(9);
		tipoEncripcion = db.stringValue(10);
	} catch (Exception e) {
		throw new Exception("\nloadInfBranch() - Información de Agencias \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public void modificar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		// System.out.println("entro al metodo modificar");
		error = "";
		String query = "";
		String antCodbra = getCodbra();
		String antNumsum = getNumsum();
		String antFlgblkbra = getFlgblkbra();
		String antDayprgtra = getDayprgtra();
		String antBandef = getBandef();
		String antHouini = getHouini();
		String antHoufin = getHoufin();
		String antBanpna = getBanpna();
		String antBanpju = getBanpju();
		String antTipoEncripcion  = getTipoEncripcion();
		
		// System.out.println("CODIGO--------"+antCodbra);
		if (existeCodbraInInf(db)) {
			// System.out.println("hay datos");
			if (buscar(db, antCodbra)) {
				// System.out.println("entro a modificar");// Si el codbra existe en la tabla "+Constante.ESQUEMA1+".TBRAINF 
				query = //
				"Update "+Constante.ESQUEMA1+".TBRAINF set " + //
				"numsum = '" + antNumsum + "', " + //
				"flgblkbra = '" + antFlgblkbra + "', " + //
				"dayprgtra = '" + antDayprgtra + "', " + //
				"bandef = '" + antBandef + "', " + //
				"houini = '" + antHouini + "', " + //
				"houfin = '" + antHoufin + "', " + //
				"banpna = '" + antBanpna + "', " + //
				"banpju = '" + antBanpju + "', " + //
				"TYPENCRIPCION = '" + antTipoEncripcion+"' " + 
				"Where codbra = '" + getCodbra() + "' ";
				db.setQuery(query);
				int rows = db.executeUpdate();
				String bef[] = {getCodbra(), getNumsum(), getFlgblkbra(), getDayprgtra(), getBandef(), getHouini(), getHoufin(), getBanpna(), getBanpju(), getTipoEncripcion()};
				String aft[] = {getCodbra(), antNumsum, antFlgblkbra, antDayprgtra, antBandef, antHouini, antHoufin, antBanpna, antBanpju, antTipoEncripcion};
				LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Modificar", Constante.ESQUEMA1+".TBRAINF", Table.getColumnsTbrainf(), bef, aft);
			} else {
				if (existeCodbra(db, antCodbra)) { // Si el codbra existe en la tbradat
					query = "Delete from "+Constante.ESQUEMA1+".TBRAINF";
					db.setQuery(query);
					db.executeUpdate();
					query = //
						"Insert into "+Constante.ESQUEMA1+".TBRAINF (codbra, numsum, flgblkbra, dayprgtra, bandef, houini, houfin, banpna, banpju,TYPENCRIPCION ) values ('0001', '" + getNumsum() + "', '" + getFlgblkbra() + "', '" + getDayprgtra() + "', '"+getBandef()+"','"+getHouini()+"','"+getHoufin()+"','"+getBanpna()+"','"+getBanpju()+"','"+getTipoEncripcion()+"')";
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {getCodbra(), getNumsum(), getFlgblkbra(), getDayprgtra(), getBandef(), getHouini(), getHoufin(), getBanpna(), getBanpju(), getTipoEncripcion()};
					String aft[] = {getCodbra(), antNumsum, antFlgblkbra, antDayprgtra, antBandef, antHouini, antHoufin, antBanpna, antBanpju, antTipoEncripcion};
					LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Modificar", Constante.ESQUEMA1+".TBRAINF", Table.getColumnsTbrainf(), bef, aft);
				} else {
					error = Mensajes.getMessage(Mensajes.CODIGO_DE_OFICINA_NO_EXISTE);
				}
			}
		} /*else {
			query = //
			"Insert into tbrainf values ('" + antCodbra + "', '" + antNumsum + "', '" + antFlgblkbra + "', '" + antFecpro + "', '" + antFecnxtday + "', " + new Integer(antDayprgtra).intValue() + ", " + new Integer(antDayprgope).intValue() + " )";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", "", "", "", "", "", "", "", ""};
			String aft[] = {antCodbra, antNumsum, antFecpro, antFecnxtday, antFlgblkbra, antDayprgtra, antDayprgope, antDayprgali, antDayprgmsg};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Agregar", "tbrainf", Table.getColumnsTbrainf(), bef, aft);
		}*/
		if (error.equals(""))
			error = Mensajes.getMessage(Mensajes.ACTUALIZACION_EJECUTADA_CON_EXITO);
		loadGrid(db);
	} catch (Exception e) {
		throw new Exception("\nModificar - Información de Agencias \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}

public void setCodbra(String newValue) {
	this.codbra = newValue;
}

public void setFlgblkbra(String newValue) {
	this.flgblkbra = newValue;
}

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

public void setError(String newValue) {
	this.error = newValue;
}

public void setLogin(InicioSesion newValue) {
	this.login = newValue;
}

public void setNumsum(String newValue) {
	this.numsum = newValue;
}

public void setStatus(String newValue) {
	this.status = newValue;
}
	/**
	 * @return Devuelve tipoEncripcion.
	 */
	public String getTipoEncripcion() {
		CString str = new CString(tipoEncripcion);
		str.arrange();
		return str.toString();
	}
	/**
	 * @param tipoEncripcion El tipoEncripcion a establecer.
	 */
	public void setTipoEncripcion(String newValue) {
		this.tipoEncripcion = newValue;
	}
}