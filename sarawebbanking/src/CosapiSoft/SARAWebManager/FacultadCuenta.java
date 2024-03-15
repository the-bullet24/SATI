package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class FacultadCuenta {
	//
	private CosapiSoft.SARAWebBanking.InicioSesion login;
	//
	public java.util.Vector grid = null;





	public java.lang.String error="";



/**
 * AyudaDeCampo constructor comment.
 */
public FacultadCuenta() {
	super();
	grid = new java.util.Vector(1);
}
public void agregar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		String antFlgcns=getFlgcns();
		String antFlgtrf=getFlgtrf();
		String antLimtrf=getLimtrf();
		String antFlgpay=getFlgpay();
		String antLimpay=getLimpay();
		String antFlgmon=getFlgmon();
		String antStrtimmon=getStrtimmon();
		String antEndtimmon=getEndtimmon();
		String antFlgtue=getFlgtue();
		String antStrtimtue=getStrtimtue();
		String antEndtimtue=getEndtimtue();
		String antFlgwed=getFlgwed();
		String antStrtimwed=getStrtimwed();
		String antEndtimwed=getEndtimwed();
		String antFlgthu=getFlgthu();
		String antStrtimthu=getStrtimthu();
		String antEndtimthu=getEndtimthu();
		String antFlgfri=getFlgfri();
		String antStrtimfri=getStrtimfri();
		String antEndtimfri=getEndtimfri();
		String antFlgsat=getFlgsat();
		String antStrtimsat=getStrtimsat();
		String antEndtimsat=getEndtimsat();
		String antFlgsun=getFlgsun();
		String antStrtimsun=getStrtimsun();
		String antEndtimsun=getEndtimsun();
		if (!buscar(db)) {
			if (getLimpay().equals("")) setLimpay("0");
			if (getLimtrf().equals("")) setLimtrf("0");
			String query = //
			"Insert into tfacacc values ('"+getCodorg()+"','06','" + getCodigo() + "','" + getCuenta() + "', '" + getFlgcns() + "', '" + getFlgtrf() + "', " + getLimtrf() + ", '" + getFlgpay() + "',"+getLimpay()+",'"+getFlgmon()+"','"+getStrtimmon()+"','"+getEndtimmon()+"','"+getFlgtue()+"','"+getStrtimtue()+"','"+getEndtimtue()+"','"+getFlgwed()+"','"+getStrtimwed()+"','"+getEndtimwed()+"','"+getFlgthu()+"','"+getStrtimthu()+"','"+getEndtimthu()+"','"+getFlgfri()+"','"+getStrtimfri()+"','"+getEndtimfri()+"','"+getFlgsat()+"','"+getStrtimsat()+"','"+getEndtimsat()+"','"+getFlgsun()+"','"+getStrtimsun()+"','"+getEndtimsun()+"')";
			db.setQuery(query);
			db.executeUpdate();
			/*String bef[] = {"", "", "","","","","","","","","", "", "","","","","","","","","", "", "","","","","","","",""};
			String aft[] = {getCodorg(),"06",getCodigo(),getCuenta(),getFlgcns(), getFlgtrf(), getLimtrf(), getFlgpay(), getLimpay(), getFlgmon(),getStrtimmon(), getEndtimmon(), getFlgtue(), getStrtimtue(), getEndtimtue(), getFlgwed(), getStrtimwed(), getEndtimwed(), getFlgthu(), getStrtimthu(), getEndtimthu(), getFlgfri(), getStrtimfri(), getEndtimfri(), getFlgsat(), getStrtimsat(), getEndtimsat(), getFlgsun(), getStrtimsun(), getEndtimsun()};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Agregar", "tfacacc", Table.getColumnsTfacacc(), bef, aft);*/
			loadGrid(db);
		} else {
			String query = //
			"Update tfacacc set " + //
			"flgcns = '" + antFlgcns + "', " + //
			"flgtrf = '" + antFlgtrf + "', " + //
			"limtrf = " + antLimtrf + ", " + //
			"flgpay = '" + antFlgpay + "', " + //
			"limpay = " + antLimpay + ", " + //
			"flgmon = '" + antFlgmon + "', " + //
			"strtimmon = '" + antStrtimmon + "', " + //
			"endtimmon = '" + antEndtimmon + "', " + //
			"flgtue = '" + antFlgtue + "', " + //
			"strtimtue = '" + antStrtimtue + "', " + //
			"endtimtue = '" + antEndtimtue + "', " + //
			"flgwed = '" + antFlgwed + "', " + //
			"strtimwed = '" + antStrtimwed + "', " + //
			"endtimwed = '" + antEndtimwed + "', " + //
			"flgthu = '" + antFlgthu + "', " + //
			"strtimthu = '" + antStrtimthu + "', " + //
			"endtimthu = '" + antEndtimthu + "', " + //
			"flgfri = '" + antFlgfri + "', " + //
			"strtimfri = '" + antStrtimfri + "', " + //
			"endtimfri = '" + antEndtimfri + "', " + //
			"flgsat = '" + antFlgsat + "', " + //
			"strtimsat = '" + antStrtimsat + "', " + //
			"endtimsat = '" + antEndtimsat + "', " + //
			"flgsun = '" + antFlgsun + "', " + //
			"strtimsun = '" + antStrtimsun + "', " + //
			"endtimsun = '" + antEndtimsun + "' " + //
			"Where codorg = '" + getCodorg() + "' and codusr='"+getCodigo()+"' and numacc='"+getCuenta()+"'"; //
			db.setQuery(query);
			int rows = db.executeUpdate();
			//String bef[] = {getCodorg(), "06", getCodigo(), getCuenta(),getFlgcns(), getFlgtrf(), getLimtrf(), getFlgpay(), getLimpay(), getFlgmon(), getStrtimmon(), getEndtimmon(), getFlgtue(), getStrtimtue(), getEndtimtue(), getFlgwed(), getStrtimwed(), getEndtimwed(), getFlgthu(), getStrtimthu(), getEndtimthu(), getFlgfri(), getStrtimfri(), getEndtimfri(), getFlgsat(), getStrtimsat(), getEndtimsat(), getFlgsun(), getStrtimsun(), getEndtimsun()};
			//String aft[] = {getCodorg(), "06", getCodigo(), getCuenta(),antFlgcns, antFlgtrf, antLimtrf,antFlgpay, antLimpay,antFlgmon,antStrtimmon, antEndtimmon,antFlgtue, antStrtimtue, antEndtimtue, antFlgwed, antStrtimwed, antEndtimwed, antFlgthu, antStrtimthu, antEndtimthu, antFlgfri, antStrtimfri, antEndtimfri, antFlgsat, antStrtimsat, antEndtimsat, antFlgsun, antStrtimsun, antEndtimsun};
			//LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Agregar", "tfacacc", Table.getColumnsTfacacc(), bef, aft);
		}
	} catch (Exception e) {
		throw new Exception("\nAgregar - Facultad - Cuenta \n" + e.getMessage());
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
		"Select flgcns, flgtrf, limtrf, flgpay, limpay, flgmon, strtimmon, endtimmon, flgtue, strtimtue, endtimtue, flgwed, strtimwed, endtimwed, flgthu, strtimthu, endtimthu, flgfri, strtimfri, endtimfri, flgsat, strtimsat, endtimsat, flgsun, strtimsun, endtimsun  " + //
		"From tfacacc " + //
		"Where codorg = '" + getCodorg() + "' and codusr='"+getCodigo()+"' and numacc='"+getCuenta()+"'";//
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
		{
			flgcns = "";
			flgtrf = "";
			limtrf = "";
			flgpay = "";
			limpay = "";
			flgmon = "";
			strtimmon = "";
			endtimmon = "";
			flgtue = "";
			strtimtue = "";
			endtimtue = "";
			flgwed = "";
			strtimwed = "";
			endtimwed = "";
			flgthu = "";
			strtimthu = "";
			endtimthu = "";
			flgfri = "";
			strtimfri = "";
			endtimfri = "";
			flgsat = "";
			strtimsat = "";
			endtimsat = "";
			flgsun = "";
			strtimsun = "";
			endtimsun = "";
			return false;
		}
		flgcns = db.stringValue(1);
		flgtrf = db.stringValue(2);
		limtrf = db.stringValue(3);
		flgpay = db.stringValue(4);
		limpay = db.stringValue(5);
		flgmon = db.stringValue(6);
		strtimmon = db.stringValue(7);
		endtimmon = db.stringValue(8);
		flgtue = db.stringValue(9);
		strtimtue = db.stringValue(10);
		endtimtue = db.stringValue(11);
		flgwed = db.stringValue(12);
		strtimwed = db.stringValue(13);
		endtimwed = db.stringValue(14);
		flgthu = db.stringValue(15);
		strtimthu = db.stringValue(16);
		endtimthu = db.stringValue(17);
		flgfri = db.stringValue(18);
		strtimfri = db.stringValue(19);
		endtimfri = db.stringValue(20);
		flgsat = db.stringValue(21);
		strtimsat = db.stringValue(22);
		endtimsat = db.stringValue(23);
		flgsun = db.stringValue(24);
		strtimsun = db.stringValue(25);
		endtimsun = db.stringValue(26);
	} catch (Exception e) {
		throw new Exception("\nBuscar - Facultad - Cuenta \n " + e.getMessage());
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
		"Select taccdat.numacc, tcurdat.txtcurlon  " + //
		"From taccdat, tcurdat " + //
		"Where taccdat.codorg = '" + getCodorg() + "' and taccdat.codcur=tcurdat.codcur";//
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector();
			row.addElement(db.stringValue(1).trim());
			row.addElement(db.stringValue(2).trim());
			grid.addElement(row);
		}
	} catch (Exception e) {
		throw new Exception("\nLoadGrid - Facultad - Cuenta \n" + e.getMessage());
	}
}

public void next(int i) {
	cuenta = ((java.util.Vector) grid.elementAt(i)).elementAt(0).toString();
	moneda = ((java.util.Vector) grid.elementAt(i)).elementAt(1).toString();
}









/**
 * This method was created in VisualAge.
 * @param newValue CosapiSoft.SARAWebBanking.InicioSesion
 */
public void setLogin(CosapiSoft.SARAWebBanking.InicioSesion newValue) {
	this.login = newValue;
}

	public java.lang.String codigo = "";	public java.lang.String codorg="";	public java.lang.String cuenta = "";	public java.lang.String endtimfri = "";	public java.lang.String endtimmon = "";	public java.lang.String endtimsat = "";	public java.lang.String endtimsun = "";	public java.lang.String endtimthu = "";	public java.lang.String endtimtue = "";	public java.lang.String endtimwed = "";	public java.lang.String flgcns = "";	public java.lang.String flgfri = "";	public java.lang.String flgmon = "";	public java.lang.String flgpay = "";	public java.lang.String flgsat = "";	public java.lang.String flgsun = "";	public java.lang.String flgthu = "";	public java.lang.String flgtrf = "";	public java.lang.String flgtue = "";	public java.lang.String flgwed = "";	public java.lang.String limpay = "";	public java.lang.String limtrf = "";	public java.lang.String moneda = "";	public java.lang.String strtimfri = "";	public java.lang.String strtimmon = "";	public java.lang.String strtimsat = "";	public java.lang.String strtimsun = "";	public java.lang.String strtimthu = "";	public java.lang.String strtimtue="";	public java.lang.String strtimwed = "";/**
 * Insert the method's description here.
 * Creation date: (3/12/01 11:11:39 AM)
 * @return java.lang.String
 */
public java.lang.String getCodigo() {
	return codigo;
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
 * Creation date: (3/12/01 3:40:03 PM)
 * @return java.lang.String
 */ 
public java.lang.String getEndtimfri() {
	return endtimfri;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:26:37 PM)
 * @return java.lang.String
 */ 
public java.lang.String getEndtimmon() {
	return endtimmon;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:41:42 PM)
 * @return java.lang.String
 */ 
public java.lang.String getEndtimsat() {
	return endtimsat;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:43:38 PM)
 * @return java.lang.String
 */ 
public java.lang.String getEndtimsun() {
	return endtimsun;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:37:56 PM)
 * @return java.lang.String
 */ 
public java.lang.String getEndtimthu() {
	return endtimthu;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:31:01 PM)
 * @return java.lang.String
 */ 
public java.lang.String getEndtimtue() {
	return endtimtue;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:34:14 PM)
 * @return java.lang.String
 */ 
public java.lang.String getEndtimwed() {
	return endtimwed;
}/**
 * Insert the method's description here.
 * Creation date: (3/8/01 2:28:03 PM)
 * @return java.lang.String
 */ 
public java.lang.String getError() {
	return error;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:22:25 PM)
 * @return java.lang.String
 */ 
public java.lang.String getFlgcns() {
	return flgcns;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:38:40 PM)
 * @return java.lang.String
 */ 
public java.lang.String getFlgfri() {
	return flgfri;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:25:26 PM)
 * @return java.lang.String
 */ 
public java.lang.String getFlgmon() {
	return flgmon;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:24:12 PM)
 * @return java.lang.String
 */ 
public java.lang.String getFlgpay() {
	return flgpay;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:40:35 PM)
 * @return java.lang.String
 */ 
public java.lang.String getFlgsat() {
	return flgsat;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:42:18 PM)
 * @return java.lang.String
 */ 
public java.lang.String getFlgsun() {
	return flgsun;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:34:49 PM)
 * @return java.lang.String
 */ 
public java.lang.String getFlgthu() {
	return flgthu;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:22:56 PM)
 * @return java.lang.String
 */ 
public java.lang.String getFlgtrf() {
	return flgtrf;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:27:01 PM)
 * @return java.lang.String
 */ 
public java.lang.String getFlgtue() {
	return flgtue;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:31:37 PM)
 * @return java.lang.String
 */ 
public java.lang.String getFlgwed() {
	return flgwed;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:24:41 PM)
 * @return java.lang.String
 */ 
public java.lang.String getLimpay() {
	return limpay;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:23:24 PM)
 * @return java.lang.String
 */ 
public java.lang.String getLimtrf() {
	return limtrf;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:48:51 PM)
 * @return java.lang.String
 */ 
public java.lang.String getMoneda() {
	return moneda;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:39:22 PM)
 * @return java.lang.String
 */ 
public java.lang.String getStrtimfri() {
	return strtimfri;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:25:51 PM)
 * @return java.lang.String
 */ 
public java.lang.String getStrtimmon() {
	return strtimmon;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:41:11 PM)
 * @return java.lang.String
 */ 
public java.lang.String getStrtimsat() {
	return strtimsat;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:42:50 PM)
 * @return java.lang.String
 */ 
public java.lang.String getStrtimsun() {
	return strtimsun;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:35:50 PM)
 * @return java.lang.String
 */ 
public java.lang.String getStrtimthu() {
	return strtimthu;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:27:27 PM)
 * @return java.lang.String
 */ 
public java.lang.String getStrtimtue() {
	return strtimtue;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:33:39 PM)
 * @return java.lang.String
 */ 
public java.lang.String getStrtimwed() {
	return strtimwed;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 11:11:39 AM)
 * @param newCodigo java.lang.String
 */ 
public void setCodigo(java.lang.String newCodigo) {
	codigo = newCodigo;
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
 * Creation date: (3/12/01 3:40:03 PM)
 * @param newEndtimfri java.lang.String
 */ 
public void setEndtimfri(java.lang.String newEndtimfri) {
	endtimfri = newEndtimfri;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:26:37 PM)
 * @param newEndtimmon java.lang.String
 */ 
public void setEndtimmon(java.lang.String newEndtimmon) {
	endtimmon = newEndtimmon;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:41:42 PM)
 * @param newEndtimsat java.lang.String
 */ 
public void setEndtimsat(java.lang.String newEndtimsat) {
	endtimsat = newEndtimsat;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:43:38 PM)
 * @param newEndtimsun java.lang.String
 */ 
public void setEndtimsun(java.lang.String newEndtimsun) {
	endtimsun = newEndtimsun;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:37:56 PM)
 * @param newEndtimthu java.lang.String
 */ 
public void setEndtimthu(java.lang.String newEndtimthu) {
	endtimthu = newEndtimthu;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:31:01 PM)
 * @param newEndtimtue java.lang.String
 */ 
public void setEndtimtue(java.lang.String newEndtimtue) {
	endtimtue = newEndtimtue;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:34:14 PM)
 * @param newEndtimwed java.lang.String
 */ 
public void setEndtimwed(java.lang.String newEndtimwed) {
	endtimwed = newEndtimwed;
}/**
 * Insert the method's description here.
 * Creation date: (3/8/01 2:28:03 PM)
 * @param newError java.lang.String
 */ 
public void setError(java.lang.String newError) {
	error = newError;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:22:25 PM)
 * @param newFlgcns java.lang.String
 */ 
public void setFlgcns(java.lang.String newFlgcns) {
	flgcns = newFlgcns;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:38:40 PM)
 * @param newFlgfri java.lang.String
 */ 
public void setFlgfri(java.lang.String newFlgfri) {
	flgfri = newFlgfri;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:25:26 PM)
 * @param newFlgmon java.lang.String
 */ 
public void setFlgmon(java.lang.String newFlgmon) {
	flgmon = newFlgmon;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:24:12 PM)
 * @param newFlgpay java.lang.String
 */ 
public void setFlgpay(java.lang.String newFlgpay) {
	flgpay = newFlgpay;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:40:35 PM)
 * @param newFlgsat java.lang.String
 */ 
public void setFlgsat(java.lang.String newFlgsat) {
	flgsat = newFlgsat;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:42:18 PM)
 * @param newFlgsun java.lang.String
 */ 
public void setFlgsun(java.lang.String newFlgsun) {
	flgsun = newFlgsun;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:34:49 PM)
 * @param newFlgthu java.lang.String
 */ 
public void setFlgthu(java.lang.String newFlgthu) {
	flgthu = newFlgthu;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:22:56 PM)
 * @param newFlgtrf java.lang.String
 */ 
public void setFlgtrf(java.lang.String newFlgtrf) {
	flgtrf = newFlgtrf;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:27:01 PM)
 * @param newFlgtue java.lang.String
 */ 
public void setFlgtue(java.lang.String newFlgtue) {
	flgtue = newFlgtue;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:31:37 PM)
 * @param newFlgwed java.lang.String
 */ 
public void setFlgwed(java.lang.String newFlgwed) {
	flgwed = newFlgwed;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:24:41 PM)
 * @param newLimpay java.lang.String
 */ 
public void setLimpay(java.lang.String newLimpay) {
	limpay = newLimpay;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:23:24 PM)
 * @param newLimtrf java.lang.String
 */ 
public void setLimtrf(java.lang.String newLimtrf) {
	limtrf = newLimtrf;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:48:51 PM)
 * @param newMoneda java.lang.String
 */ 
public void setMoneda(java.lang.String newMoneda) {
	moneda = newMoneda;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:39:22 PM)
 * @param newStrtimfri java.lang.String
 */ 
public void setStrtimfri(java.lang.String newStrtimfri) {
	strtimfri = newStrtimfri;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:25:51 PM)
 * @param newStrtimmon java.lang.String
 */ 
public void setStrtimmon(java.lang.String newStrtimmon) {
	strtimmon = newStrtimmon;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:41:11 PM)
 * @param newStrtimsat java.lang.String
 */ 
public void setStrtimsat(java.lang.String newStrtimsat) {
	strtimsat = newStrtimsat;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:42:50 PM)
 * @param newStrtimsun java.lang.String
 */ 
public void setStrtimsun(java.lang.String newStrtimsun) {
	strtimsun = newStrtimsun;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:35:50 PM)
 * @param newStrtimthu java.lang.String
 */ 
public void setStrtimthu(java.lang.String newStrtimthu) {
	strtimthu = newStrtimthu;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:27:27 PM)
 * @param newStrtimtu java.lang.String
 */ 
public void setStrtimtue(java.lang.String newStrtimtu) {
	strtimtue = newStrtimtu;
}/**
 * Insert the method's description here.
 * Creation date: (3/12/01 3:33:39 PM)
 * @param newStrtimwed java.lang.String
 */ 
public void setStrtimwed(java.lang.String newStrtimwed) {
	strtimwed = newStrtimwed;
}}