package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class BannerPromocional extends GenericTable {
	//
	private InicioSesion login;
	private String typper = "";
	private String datbeg = "";
	private String datend = "";
	private String filnam = "";
	private String typ_per = "";
	private String dat_beg = "";
	private String dat_end = "";
	private String fil_nam = "";
	private String typper1 = "";
	private String datbeg1 = "";
	private String datend1 = "";
	private String filnam1 = "";
	private String typ_per1 = "";
	private String dat_beg1 = "";
	private String dat_end1 = "";
	private String fil_nam1 = "";

	
public BannerPromocional() {
	super();
}
public void consult() throws java.sql.SQLException {
	try {
		String bef[] = {"", "","", ""};
		String aft[] = {"", "","", ""};
		LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", "tbandat", Table.getColumnsTbandat(), bef, aft);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... consult() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public void delete(JQuery db) throws java.sql.SQLException {
	try {

	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - " + getClass().getName() + " -> " + e.getMessage());
	}
}


public void delete(JQuery db, javax.servlet.http.HttpServletRequest req) throws java.sql.SQLException, javax.servlet.ServletException {
	try {
		
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - " + getClass().getName() + " -> " + e.getMessage());
	}
}


public String getTyp_per() {
	if (typ_per == null)
		typ_per = "";
	return typ_per.trim();
}

public String getTyp_per1() {
	if (typ_per1 == null)
		typ_per1 = "";
	return typ_per1.trim();
}



public String getTypper() {
	CString str = new CString(typper);
	str.arrange();
	return str.toString();
}


public String getTypper1() {
	CString str = new CString(typper1);
	str.arrange();
	return str.toString();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNameTable() {
	return Constante.ESQUEMA1+".TBANDAT";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getDat_beg() {
	if (dat_beg == null || dat_beg.equals(""))
		dat_beg = "---";
	CString str = new CString(dat_beg);
	str.arrange();
	return str.toString();
}

public String getDatbeg() {
	CString str = new CString(datbeg);
	str.arrange();
	return str.toString();
}

public String getDat_beg1() {
	if (dat_beg1 == null || dat_beg1.equals(""))
		dat_beg1 = "---";
	CString str = new CString(dat_beg1);
	str.arrange();
	return str.toString();
}

public String getDatbeg1() {
	CString str = new CString(datbeg1);
	str.arrange();
	return str.toString();
}

public String getDat_end() {
	if (dat_end == null || dat_end.equals(""))
		dat_end = "---";
	CString str = new CString(dat_end);
	str.arrange();
	return str.toString();
}

public String getDatend() {
	CString str = new CString(datend);
	str.arrange();
	return str.toString();
}
public String getDat_end1() {
	if (dat_end1 == null || dat_end1.equals(""))
		dat_end1 = "---";
	CString str = new CString(dat_end1);
	str.arrange();
	return str.toString();
}

public String getDatend1() {
	CString str = new CString(datend1);
	str.arrange();
	return str.toString();
}
public String getFil_nam() {
	if (fil_nam == null || fil_nam.equals(""))
		fil_nam = "---";
	CString str = new CString(fil_nam);
	str.arrange();
	return str.toString();
}

public String getFilnam() {
	CString str = new CString(filnam);
	str.arrange();
	return str.toString();
}
public String getFil_nam1() {
	if (fil_nam1 == null || fil_nam1.equals(""))
		fil_nam1 = "---";
	CString str = new CString(fil_nam1);
	str.arrange();
	return str.toString();
}

public String getFilnam1() {
	CString str = new CString(filnam1);
	str.arrange();
	return str.toString();
}
public String getUrl() {
	return getUrlBannerPromocional();
}
public String getUrlBannerPromocional() {
	return JspServlet.BANNER_PROMOCIONAL_SERVLET + "?BtnGrp=Buscar&TxtTypper=";
}
public void insert(JQuery db) throws java.sql.SQLException {
	try {
		String antTypper = typper;
		Date antDatbeg = new Date(datbeg);
		Date antDatend = new Date(datend);
		String antFilnam = filnam;
		String antTypper1 = typper1;
		String antDatbeg1 = datbeg1;
		String antDatend1 = datend1;
		String antFilnam1 = filnam1;
		setError("");
		//if (!search(db)) {
			String query = //
			"Insert into "+Constante.ESQUEMA1+".TBANDAT (typper,datbeg,datend,filnam) values ('" + antTypper + "', '" + antDatbeg + "','" + antDatend + "', '" + antFilnam + "')";
			// System.out.println("QUERY-----"+query);
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", "","", ""};
			String aft[] = {getTypper(), getDatbeg(), getDatend(), getFilnam()};
			LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TBANDAT", Table.getColumnsTbandat(), bef, aft);
			setGrid(null);
			query = //
			"Insert into "+Constante.ESQUEMA1+".TBANDAT (typper,datbeg,datend,filnam) values ('" + antTypper1 + "', '" + antDatbeg1 + "','" + antDatend1 + "', '" + antFilnam1 + "')";
			// System.out.println("QUERY-----"+query);
				db.setQuery(query);
				db.executeUpdate();
				String bef1[] = {"", "","", ""};
				String aft1[] = {getTypper1(), getDatbeg1(), getDatend1(), getFilnam1()};
				LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TBANDAT", Table.getColumnsTbandat(), bef1, aft1);
				setGrid(null);
			//loadGrid(db);
		/*}
		else {
			update(db);
		}*/
	
	}
	catch (Exception e) {
		e.printStackTrace();
		throw new java.sql.SQLException("\n... insert() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public void loadGrid(JQuery db) throws java.sql.SQLException {
	setIndex(0);
	if (getGrid().size() == 0) {
		try {
			String query = //
			"Select typper,datbeg,datend,filnam " + //
			"From "+Constante.ESQUEMA1+".TBANDAT ";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector row = new java.util.Vector(5);
				row.addElement(getStatus());
				row.addElement(db.stringValue(1));
				row.addElement(db.stringValue(2));
				row.addElement(db.stringValue(3));
				row.addElement(db.stringValue(4));

				getGrid().addElement(row);
			}
		}
		catch (Exception e) {
			throw new java.sql.SQLException("\n... loadGrid() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
}
public void loadGridChild(JQuery db) throws java.sql.SQLException {
}
public void next(int i) {
	setStatus(((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString());
	typ_per = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
	dat_beg = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
	dat_end = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
	fil_nam = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
}
public void nextChild(int i) {
}
public boolean search(JQuery db) throws java.sql.SQLException {
	try {
		
		java.text.SimpleDateFormat sd = new java.text.SimpleDateFormat();
		java.util.Vector lv_vec=new java.util.Vector();
		String query = //
		"Select typper,to_char(datbeg,'dd/mm/rrrr'),to_char(datend,'dd/mm/rrrr'),filnam " + //
		"From "+Constante.ESQUEMA1+".TBANDAT "+
		"Where typper = '01'";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
		return false;
		typper = db.stringValue(1);
		datbeg = db.stringValue(2);
		datend = db.stringValue(3);
		filnam = db.stringValue(4);
		//mostrar(db);
		query = //
		"Select typper,to_char(datbeg,'dd/mm/rrrr'),to_char(datend,'dd/mm/rrrr'),filnam " + //
		"From "+Constante.ESQUEMA1+".TBANDAT "+
		"Where typper = '02'";
		db.setQuery(query);
		db.executeQuery();
			if (db.getResultSet().next()){
			typper1 = db.stringValue(1);
			datbeg1 = db.stringValue(2);
			datend1 = db.stringValue(3);
			filnam1 = db.stringValue(4);
			}
		
		return true;
		
		
	}
	catch (Exception e) {
		e.printStackTrace();
		throw new java.sql.SQLException("\n... search() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public void mostrar(JQuery db) throws java.sql.SQLException{
	
	try {

		String query = //
			"Select typper,to_char(datbeg,'dd/mm/rrrr'),to_char(datend,'dd/mm/rrrr'),filnam " + //
			"From "+Constante.ESQUEMA1+".TBANDAT "+
		"Where typper = '02'";
			db.setQuery(query);
			db.executeQuery();
			typper1 = db.stringValue(1);
			datbeg1 = db.stringValue(2);
			datend1 = db.stringValue(3);
			filnam1 = db.stringValue(4);
			query = //
			"Select typper,to_char(datbeg,'dd/mm/rrrr'),to_char(datend,'dd/mm/rrrr'),filnam " + //
				"From "+Constante.ESQUEMA1+".TBANDAT "+
			"Where typper = '01'";
				db.setQuery(query);
				db.executeQuery();
				typper = db.stringValue(1);
				datbeg = db.stringValue(2);
				datend = db.stringValue(3);
				filnam = db.stringValue(4);
		
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... search() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTyp_per(String newValue) {
	this.typ_per = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTypper(String newValue) {
	this.typper = newValue;
}
public void setTyp_per1(String newValue) {
	this.typ_per1 = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTypper1(String newValue) {
	this.typper1 = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setDat_beg(String newValue) {
	this.dat_beg = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setDatbeg(String newValue) {
	this.datbeg = newValue;
}

public void setDat_beg1(String newValue) {
	this.dat_beg1 = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setDatbeg1(String newValue) {
	this.datbeg1 = newValue;
}

public void setDat_end(String newValue) {
	this.dat_end = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setDatend(String newValue) {
	this.datend = newValue;
}
public void setDat_end1(String newValue) {
	this.dat_end1 = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setDatend1(String newValue) {
	this.datend1 = newValue;
}
public void setFil_nam(String newValue) {
	this.fil_nam = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setFilnam(String newValue) {
	this.filnam = newValue;
}
public void setFil_nam1(String newValue) {
	this.fil_nam1 = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setFilnam1(String newValue) {
	this.filnam1 = newValue;
}
public void sort(JQuery db) throws java.sql.SQLException {
}
public void update(JQuery db) throws java.sql.SQLException {
	try {
		System.out.println("entro a update-----");
		setError("");
		String antTypper = getTypper();
		String antDatbeg = getDatbeg();
		String antDatend = getDatend();
		String antFilnam = getFilnam();
		String antTypper1 = typper1;
		String antDatbeg1 = datbeg1;
		String antDatend1 = datend1;
		String antFilnam1 = filnam1;
		//if (search(db)) {
			String query = //
			"Update "+Constante.ESQUEMA1+".TBANDAT set datbeg = to_date('"+antDatbeg+ "','dd/mm/yyyy'), datend = to_date('"+antDatend+ "','dd/mm/yyyy'), filnam = '" + antFilnam + "'  Where typper = '" + antTypper + "' ";
			
			 System.out.println("entro a update-----"+query);
			
			db.setQuery(query);
			db.executeUpdate();
			//String bef[] = {getTypper(), getDatbeg(),getDatend(),getFilnam()};
			//String aft[] = {getTypper(), antDatbeg, antDatend, antFilnam};
			//LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "update", Constante.ESQUEMA1+".TBANDAT", Table.getColumnsTbandat(), bef, aft);
			//setGrid(null);
			
			/*query = //
			"Update "+Constante.ESQUEMA1+".TBANDAT set datbeg = '" + antDatbeg1 + "' , datend = '" + antDatend1 + "' , filnam = '" + antFilnam1 + "'  Where typper = '" + antTypper1 + "' ";
			// System.out.println("QUERY-----"+query);
				db.setQuery(query);
				rows = db.executeUpdate();
				String bef1[] = {getTypper1(), getDatbeg1(),getDatend1(),getFilnam1()};
				String aft1[] = {getTypper1(), antDatbeg1, antDatend1, antFilnam1};
				LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "update", Constante.ESQUEMA1+".TBANDAT", Table.getColumnsTbandat(), bef1, aft1);*/
				setGrid(null);
			loadGrid(db);
		//}
		/*else {
			String str = Mensajes.getMessage(Mensajes.TIPO_DE_PERSONA_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getTypper() + str.substring(pos + 1));
		}*/
	}
	catch (Exception e) {
		e.printStackTrace();
		throw new java.sql.SQLException("\n... update() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}