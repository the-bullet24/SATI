package CosapiSoft.SARAWebBanking;

/**
 * This type was created in VisualAge.
 */
public class InicioSesion {
	//
	//private static com.cosapisoft.global.SystemLog sl = null;
	// Módulos
	public java.util.Vector modulos = null;
	private String codmod = "";
	// Perfiles
	public java.util.Vector perfiles = null;
	private String codprf = "";
	// Acciones
	public java.util.Vector acciones = null;
	private String codact = "";
	// Base de datos
	public static String ipDB = "";
	public static String ipWEB = "";
	public static String dbPort = "8888";
	public static String dbName = "";
	public static String userid = "";
	public static String password = "";
	public static final String driver = "COM.ibm.db2.jdbc.net.DB2Driver";
	public static String url = null;
	//
	private String nameProducto = "";
	private String usuario = "";
	private String clave = "";
	private String nombre = "";
	private String error = "";
	//
	private String namePantalla = "";
	private String accion = "";
	
	public static String codAgencia = "9999";//Agencia Desarrollo
/**
 * InicioSesion constructor comment.
 */
public InicioSesion() {
	super();
	modulos = new java.util.Vector(1);
	perfiles = new java.util.Vector(1);
	acciones = new java.util.Vector(1);
	/**
	 *if (sl == null) {
	*	try {
	*		sl = new com.cosapisoft.global.SystemLog("SystemLog.txt");
	*	}
	*	catch (Exception any) {
	*		// use of system default error out stream
	*		System.out.println("Test SystemLog failed creating log file\n" + any);
	*	}
	*} 
	 */
	
}
protected void finalize() throws Throwable {
	//com.cosapisoft.util.SaveOutput.stop();
	super.finalize();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getAccion() {
	if (accion == null)
		accion = "";
	return accion;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getClave() {
	if (clave == null)
		clave = "";
	return clave.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodact() {
	if (codact == null)
		codact = "";
	return codact.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodmod() {
	if (codmod == null)
		codmod = "";
	return codmod.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodprf() {
	if (codprf == null)
		codprf = "";
	return codprf.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getError() {
	if (error == null)
		error = "";
	return error;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNamePantalla() {
	if (namePantalla == null)
		namePantalla = "";
	return namePantalla;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNameProducto() {
	if (nameProducto == null)
		nameProducto = "";
	return nameProducto;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNombre() {
	if (nombre == null)
		nombre = "";
	return nombre;
}
public String getUrl() {
	return JspServlet.INICIO_SESION_SERVLET + "?Modulo=" + getNameProducto().replace(' ', '_') + "&BtnLogin=Otro";
}
public String getUrlInicioSesion() {
	return JspServlet.INICIO_SESION_SERVLET + "?TxtCodmod=" + getCodmod() + "&Modulo=" + getNameProducto().replace(' ', '_') + "&BtnLogin=Otro";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getUsuario() {
	if (usuario == null)
		usuario = "";
	return usuario.trim();
}
public boolean hasAccesAccion() throws Exception {
	error = "";
	if (acciones == null || acciones.size() == 0) {
		error = "Usted " + getNombre() + " con código " + getUsuario() + " " + Mensajes.getMessage(Mensajes.NO_TIENE_PERMISOS_ASIGNADOS) + " " + getNameProducto();
		return false;
	}
	try {
		int rows = acciones.size();
		for (int i = 0; i < rows; i++) {
			String cod_act = acciones.elementAt(i).toString();
			if (getCodact().equals(cod_act))
				return true;
		}
		error = "Usted " + getNombre() + " con código " + getUsuario() + " " + Mensajes.getMessage(Mensajes.NO_TIENE_PERMISO_PARA_EJECUTAR_LA_ACCION) + " " + getAccion();
		return false;
	} catch (Exception e) {
		throw new Exception("InicioSesion -> hasAccesAccion() - " + e.getMessage());
	}
}
public boolean hasAccesIDia() throws Exception {
	JDatabase jd = new JDatabase(driver, url, userid, password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		String query = //
		"Select a.codact " + //
		"From tactprf a, tusrmod b, tusrprf c " + //
		"Where b.codusr = '" + getUsuario() + "' " + //
		"And a.codact = '" + getCodact() + "' " + //
		"And b.codusr = c.codusr " + //
		"And b.codmod = c.codmod " + //
		"and c.codprf = a.codprf";
		db.setQuery(query);
		db.executeQuery();
		boolean sw = db.getResultSet().next();
		return sw;
	} catch (Exception e) {
		throw new Exception("InicioSesion -> hasAccesIDia() - " + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public boolean hasAccesModulo() throws Exception {
	error = "";
	if (modulos == null || modulos.size() == 0) {
		error = "Usuario " + getUsuario() + " " + Mensajes.getMessage(Mensajes.NO_TIENE_PRODUCTOS_ASIGNADOS);
		return false;
	}
	try {
		int rows = modulos.size();
		for (int i = 0; i < rows; i++) {
			java.util.Vector row = (java.util.Vector) modulos.elementAt(i);
			if (getCodmod().equals(row.elementAt(0).toString()))
				return true;
		}
		error = "Usuario " + getUsuario() + " " + Mensajes.getMessage(Mensajes.NO_TIENE_ACCESO_AL_MODULO) + " (" + getNameProducto() + ")";
		return false;
	} catch (Exception e) {
		throw new Exception("InicioSesion -> hasAccesModulo() - " + e.getMessage());
	}
}
public boolean isValidoLogin() throws Exception {
	JDatabase jd = new JDatabase(driver, url, userid, password);
	JQuery db = new JQuery(jd.getConnection());
	boolean sw = true;
	try {
		error = "";
		if (getUsuario().equals("")) {
			error = Mensajes.getMessage(Mensajes.INGRESAR_USUARIO);
			return false;
		}
		if (getUsuario().length() < 7) {
			error = Mensajes.getMessage(Mensajes.USUARIO_SIETE_CARACTERES);
			return false;
		}
		if (getClave().equals("")) {
			error = Mensajes.getMessage(Mensajes.INGRESAR_CLAVE);
			return false;
		}
		if (getClave().length() < 6) {
			error = Mensajes.getMessage(Mensajes.CLAVE_SEIS_CARACTERES);
			return false;
		}
		//JT, se autenticará con manager
		/*String query = //
		"Select codusr, txtnam, txtpas " + //
		"From tusrdat " + //
		"Where codusr = '" + getUsuario() + "'";
		db.setQuery(query);
		db.executeQuery();
		sw = db.getResultSet().next();
		if (!sw) {
			error = Mensajes.getMessage(Mensajes.USUARIO_ERRADO);
			return false;
		} else {
			String codusr = db.stringValue(1).trim();
			String txtnam = db.stringValue(2);
			String txtpas = Encrypt.unencrypt(db.stringValue(3).trim(), "PLER4&/!1BA97c&-|", "HQ89S");
			if (!getClave().equals(txtpas)) {
				error = Mensajes.getMessage(Mensajes.CLAVE_ERRADO);
				return false;
			} else {
				setUsuario(codusr);
				setClave(txtpas);
				setNombre(txtnam);
			}
		}*/
		return sw;
	} catch (Exception e) {
		throw new Exception("isValidoLogin() -> InicioSesion - \n " + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public boolean isValidoPerfil() throws Exception {
	JDatabase jd = new JDatabase(driver, url, userid, password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		String query = //
		"Select codmod, codusr, codprf " + //
		"From tusrprf " + //
		"Where codusr = '" + getUsuario() + "'" + //
		"And codmod = '" + getCodmod() + "' " + //
		"And codprf = '" + getCodprf() + "' ";
		db.setQuery(query);
		db.executeQuery();
		boolean sw = db.getResultSet().next();
		if (!sw) {
			error = "Usuario " + getUsuario() + " " + Mensajes.getMessage(Mensajes.NO_TIENE_PERFIL_PARA_ACCESAR) + " " + getNameProducto();
		}
		return sw;
	} catch (Exception e) {
		throw new Exception("InicioSesion -> isValidoPerfil");
	} finally {
		db.close();
		jd.close();
	}
}
public void loadAcciones() throws Exception {
	acciones.add("01");//-----------InformacionDeAgenciasServlet (SOLO MANAGER)
	acciones.add("02");//-----------InformacionDeAgenciasServlet (SOLO MANAGER)
	acciones.add("03");//-----------AdministracionDeAgenciasServlet (SOLO MANAGER)
	acciones.add("04");//-----------AdministracionDeAgenciasServlet (SOLO MANAGER)
	acciones.add("05");//MensajesDeComunicacionServlet: Consulta
	acciones.add("06");//MensajesDeComunicacionServlet: Inserción, Modificación y Eliminación 
	acciones.add("07");//-----------EntidadesFinancierasServlet (SOLO MANAGER)
	acciones.add("08");//-----------EntidadesFinancierasServlet (SOLO MANAGER)
	acciones.add("09");//AyudaDeCampoServlet, LimitesServlet, ProductosAhorrosConfServlet, ProductosAhorrosServlet, ProductosCredConfServlet, ProductosCredServlet, ProductosCteConfServlet, ProductosCteServlet: Consulta
	acciones.add("10");//AyudaDeCampoServlet, ItemsDeAyudaServlet, LimitesDetalleServlet, LimitesServlet, ProductosAhorrosConfServlet, ProductosAhorrosServlet, ProductosCredConfServlet, ProductosCredServlet, ProductosCteConfServlet, ProductosCteServlet: Inserción, Modificación y Eliminación
	acciones.add("11");//MaestroDeTablasServlet: Consulta
	acciones.add("12");//MaestroDeTablasServlet: Inserción, Modificacion, Eliminación
	acciones.add("13");//GrupoDeMensajesServlet: Consulta
	acciones.add("14");//DetalleTotalComServlet, DatosDeMensajesServlet, GrupoDeMensajesServlet, GruposTrxPerfilesServlet: Inserción, Modificacion, Eliminación
	acciones.add("15");//MaestroDeMonedasServlet: Consulta
	acciones.add("16");//MaestroDeMonedasServlet: Inserción, Modificación y Eliminación 
	acciones.add("17");//-----------CanalesDeAtencionServlet (SOLO MANAGER)
	acciones.add("18");//-----------CanalesDeAtencionServlet (SOLO MANAGER)
	acciones.add("19");//-----------GestionDePerfilesServlet (SOLO MANAGER)
	acciones.add("20");//-----------ActividadesDePerfilesServlet,GestionDePerfilesServlet (SOLO MANAGER)
	acciones.add("21");//-----------GestionDeActividadesServlet (SOLO MANAGER)
	acciones.add("22");//-----------GestionDeActividadesServlet (SOLO MANAGER)
	acciones.add("23");//-----------GestionDeUsuariosServlet (SOLO MANAGER)
	acciones.add("24");//-----------GestionDeUsuariosServlet, PerfilesDeUsuariosServlet (SOLO MANAGER)
	acciones.add("25");//LogDeOperacionesBuilderServlet, LogDeOperacionesManagerServlet: Acceso al Log de Operaciones Builder
	acciones.add("26");//GrupoDeTransaccionesServlet: Consulta de Grupo de Transacciones
	acciones.add("27");//GrupoDeTransaccionesServlet: Inserción, Modificación y Eliminación de Grupo de Transacciones
	acciones.add("28");//TransaccionesServlet, EmpresaServlet, FacultadCuentaServlet, NominaAutorizacionServlet, NominaDetalleServlet, NominaServlet, NotificacionServlet, UsuariosEmpresaServlet: Consulta
	acciones.add("29");//DatosDeTransaccionesServlet, JournalTransaccionesServlet, TransaccionesServlet, EmpresaServlet, FacultadCuentaServlet, NominaAutorizacionServlet, NominaDetalleServlet, NominaServlet, NotificacionServlet, UsuariosEmpresaServlet: Inserción, Modificacion, Eliminación
	acciones.add("30");//FaseDicc0Servlet, FaseTrx0Servlet: consulta y acceso
	acciones.add("31");//FaseDicc0Servlet, FaseTrx0Servlet: consulta y acceso
	acciones.add("32");//CajaServlet, ClasesServlet, TotalComServlet, TotalServlet: Consulta del Maestro de Clases
	acciones.add("33");//CajaServlet, ClasesServlet, MetodosServlet, PropertiesServlet, TotalComServlet, TotalServlet: Inserción, Modificación y Eliminación del Maestro de Clases
	acciones.add("34");//DiccionarioServlet: Consulta de Diccionarios de Datos
	acciones.add("35");//DiccionarioServlet: Inserción, Modificación y Eliminación de Diccionarios de Datos
	acciones.add("36");
	acciones.add("37");//EsquemaDiccServlet, EsquemaServlet, FaseDiccServlet, FaseServlet: Acesso, esta comentado...
	acciones.add("38");//-----------BloqueoDeOficinaServlet (SOLO MANAGER)
	acciones.add("39");//-----------BloqueoDeOficinaServlet (SOLO MANAGER)
	acciones.add("40");//-----------InicioDeDia1Servlet (SOLO MANAGER) 
	acciones.add("41");
	acciones.add("42");
	acciones.add("43");//-----------PerfilesTellerServlet (SOLO MANAGER)
	acciones.add("44");//-----------AddRestTrxsServlet, PerfilesTellerServlet (SOLO MANAGER)
	acciones.add("45");//-----------BeanTprgdatServlet (SOLO MANAGER)
	acciones.add("46");//-----------BeanTprgdatServlet (SOLO MANAGER)
	acciones.add("47");//-----------BeanTlindatServlet (SOLO MANAGER)
	acciones.add("48");//-----------BeanTlindatServlet (SOLO MANAGER)
	acciones.add("49");
	acciones.add("50");//-----------BeanTpatdatServlet (SOLO MANAGER)
	acciones.add("51");//-----------BeanTnoddatServlet (SOLO MANAGER)
	acciones.add("52");//-----------BeanTnoddatServlet (SOLO MANAGER)
	acciones.add("53");//-----------BeanTnodpatServlet (SOLO MANAGER)
	acciones.add("54");//-----------BeanTusrnodServlet (SOLO MANAGER)
	acciones.add("55");
	acciones.add("56");
	acciones.add("57");//-----------BeanTtasdatServlet (SOLO MANAGER)
	acciones.add("58");//-----------BeanTtasdatServlet (SOLO MANAGER)
	acciones.add("59");
	acciones.add("60");//-----------BeanTnodtasServlet, BeanTtasnodServlet (SOLO MANAGER)
	acciones.add("61");
	acciones.add("62");
	acciones.add("63");//Distribucion: acceso
	acciones.add("64");//Receipt: acceso
	/*
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		acciones = new java.util.Vector(50);
		String query = //
		"Select distinct(codact) " + //
		"From tactprf " + //
		"Where codprf IN (" + //
		"Select codprf " + //
		"From tusrprf " + //
		"where codusr = '" + getUsuario() + "' " + //
		"And codmod = '" + getCodmod() + "')";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			acciones.addElement(db.stringValue(1));
		}
	} catch (Exception e) {
		throw new Exception("InicioSesion -> loadAcciones() - " + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}*/
}
public void loadModulos() throws Exception {
	java.util.Vector row = new java.util.Vector(2);
	row.addElement("12");
	row.addElement("SARA Web Builder");
	modulos.addElement(row);
	row = new java.util.Vector(2);
	row.addElement("13");
	row.addElement("SARA Web Manager");
	modulos.addElement(row);
	/*
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		modulos = new java.util.Vector(5);
		perfiles = new java.util.Vector(5);
		String query = //
		"Select codmod, txtmod " + //
		"From tmoddat " + //
		"Where codmod IN (Select codmod from tusrmod where codusr = '" + getUsuario() + "' )";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector(2);
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			modulos.addElement(row);
		}
	} catch (Exception e) {
		throw new Exception("InicioSesion -> loadModulos() - " + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
	*/
}
/**
   * Tries to load a count value from a text file. If there are problems,
   * it initializes the count at 0.
   */
public static void loadParameters() throws Exception {
/*	if (InicioSesion.url == null || InicioSesion.dbName.equals("") || InicioSesion.ipDB.equals("")) {
		try {
			//java.io.File file = new java.io.File("..\\");
			//java.io.File file = new java.io.File("/usr/bin", "ConfigDb2sara10.txt");
			//java.io.BufferedReader in = new java.io.BufferedReader(new java.io.FileReader(file));
			java.io.BufferedReader in = new java.io.BufferedReader(new java.io.FileReader("c:\\winnt\\system32\\ConfigDb2sara10.txt"));
			String s = in.readLine();
			while (s != null) {
				System.out.println(s);
				if (s.indexOf('=') > 0) {
					CString str = new CString(s.trim());
					String var = str.substringHeadUntil(' ');
					str.deleteAll('=');
					str.set(str.toString().trim());
					String value = str.substringHeadUntil(' ');
					if (var.equalsIgnoreCase("DB_NAME")) {
						InicioSesion.dbName = value;
					}
					if (var.equalsIgnoreCase("DB_USR")) {
						InicioSesion.userid = value;
					}
					if (var.equalsIgnoreCase("DB_PWD")) {
						InicioSesion.password = value;
					}
					if (var.equalsIgnoreCase("DB_PORT")) {
						InicioSesion.dbPort = value;
					}
					if (var.equalsIgnoreCase("IP_DB")) {
						InicioSesion.ipDB = value;
					}
					if (var.equalsIgnoreCase("IP_WEB")) {
						InicioSesion.ipWEB = value;
					}
				}
				s = in.readLine();
			}
			if (InicioSesion.dbName.equals(""))
				throw new Exception("loadParameters() -> En el archivo de configuraciones 'ConfigDb2sara10.txt' falta declarar el nombre de la Base de Datos:\nDB_NAME = DBSARA10");
			if (InicioSesion.dbPort.equals(""))
				throw new Exception("loadParameters() -> En el archivo de configuraciones 'ConfigDb2sara10.txt' falta declarar el Nro de Puerto de la Base de Datos:\nDB_PORT = 8888");
			if (InicioSesion.ipWEB.equals(""))
				throw new Exception("loadParameters() -> En el archivo de configuraciones 'ConfigDb2sara10.txt' falta declarar el IP del Servidor Web:\nIP_WEB = 10.37.54.43");
			InicioSesion.ipDB = (InicioSesion.ipDB.equals("")) ? InicioSesion.ipWEB : InicioSesion.ipDB;
			if (InicioSesion.ipDB.equals(""))
				throw new Exception("loadParameters() -> En el archivo de configuraciones 'ConfigDb2sara10.txt' falta declarar el IP de la Base de Datos:\nIP_DB = 10.37.54.137");
			InicioSesion.url = "jdbc:db2://" + InicioSesion.ipDB + ":" + InicioSesion.dbPort + "/" + InicioSesion.dbName; // "jdbc:db2://90.4.3.1:8888/SHB20"; //90.4.3.1
			in.close();
			System.out.println("URL : " + InicioSesion.url);
		}
		catch (Exception e) {
			InicioSesion.url = null;
			e.printStackTrace();
			throw new Exception("\n... loadParameters() -> " + e.getMessage());
		}
	}*/
}
public void loadPerfiles() throws Exception {
	JDatabase jd = new JDatabase(driver, url, userid, password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		perfiles = new java.util.Vector(5);
		String query = //
		"Select codmod, codprf " + //
		"From tusrprf " + //
		"Where codmod IN (Select codmod from tusrmod where codusr = '" + getUsuario() + "' )";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector(2);
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			perfiles.addElement(row);
		}
	} catch (Exception e) {
		throw new Exception("InicioSesion -> loadPerfiles() - " + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setAccion(String newValue) {
	this.accion = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setClave(String newValue) {
	this.clave = newValue;
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
public void setCodmod(String newValue) {
	this.codmod = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodprf(String newValue) {
	this.codprf = newValue;
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
public void setNamePantalla(String newValue) {
	this.namePantalla = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setNameProducto(String newValue) {
	if (newValue == null)
		newValue = "";
	this.nameProducto = newValue;
	if (getNameProducto().toUpperCase().equals("SARA WEB MANAGER")) {
		codmod = "13";
	} else {
		if (getNameProducto().toUpperCase().equals("SARA WEB BUILDER")) {
			codmod = "12";
		} else {
			codmod = "11";
		}
	}
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setNombre(String newValue) {
	this.nombre = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setUsuario(String newValue) {
	this.usuario = newValue;
}

}