package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class ColorTipoPersona extends GenericTable {
	//
	private String typper = "";
	private String Colbck = "";
	private String Coltit = "";
	private String Colsubtit = "";
	private String Colrow1 = "";
	private String Colrow2 = "";
	private String typ_per = "";
	private String Col_bck = "";
	private String Col_tit = "";
	private String Col_sub_tit = "";
	private String Col_row1 = "";
	private String Col_row2 = "";
	
	private String typper1 = "";
	private String Colbck1 = "";
	private String Coltit1 = "";
	private String Colsubtit1 = "";
	private String Colrow11 = "";
	private String Colrow21 = "";
	private String typ_per1 = "";
	private String Col_bck1 = "";
	private String Col_tit1 = "";
	private String Col_sub_tit1 = "";
	private String Col_row11 = "";
	private String Col_row21 = "";
	
public ColorTipoPersona() {
	super();
}
public void consult() throws java.sql.SQLException {
	try {
		String bef[] = {"", "","", "", "", ""};
		String aft[] = {"", "","", "", "", ""};
		LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", Constante.ESQUEMA1+".TCOLDAT", Table.getColumnsTcoldat(), bef, aft);
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
	return Constante.ESQUEMA1+".TCOLDAT";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCol_bck() {
	if (Col_bck == null || Col_bck.equals(""))
		Col_bck = "---";
	CString str = new CString(Col_bck);
	str.arrange();
	return str.toString();
}

public String getColbck() {
	CString str = new CString(Colbck);
	str.arrange();
	return str.toString();
}

public String getCol_bck1() {
	if (Col_bck1 == null || Col_bck1.equals(""))
		Col_bck1 = "---";
	CString str = new CString(Col_bck1);
	str.arrange();
	return str.toString();
}

public String getColbck1() {
	CString str = new CString(Colbck1);
	str.arrange();
	return str.toString();
}

public String getCol_tit() {
	if (Col_tit == null || Col_tit.equals(""))
		Col_tit = "---";
	CString str = new CString(Col_tit);
	str.arrange();
	return str.toString();
}

public String getColtit() {
	CString str = new CString(Coltit);
	str.arrange();
	return str.toString();
}
public String getCol_tit1() {
	if (Col_tit1 == null || Col_tit1.equals(""))
		Col_tit1 = "---";
	CString str = new CString(Col_tit1);
	str.arrange();
	return str.toString();
}

public String getColtit1() {
	CString str = new CString(Coltit1);
	str.arrange();
	return str.toString();
}
public String getCol_sub_tit() {
	if (Col_sub_tit == null || Col_sub_tit.equals(""))
		Col_sub_tit = "---";
	CString str = new CString(Col_sub_tit);
	str.arrange();
	return str.toString();
}

public String getColsubtit() {
	CString str = new CString(Colsubtit);
	str.arrange();
	return str.toString();
}
public String getCol_sub_tit1() {
	if (Col_sub_tit1 == null || Col_sub_tit1.equals(""))
		Col_sub_tit1 = "---";
	CString str = new CString(Col_sub_tit1);
	str.arrange();
	return str.toString();
}

public String getColsubtit1() {
	CString str = new CString(Colsubtit1);
	str.arrange();
	return str.toString();
}

public String getCol_row1() {
	if (Col_row1 == null || Col_row1.equals(""))
		Col_row1 = "---";
	CString str = new CString(Col_row1);
	str.arrange();
	return str.toString();
}

public String getColrow1() {
	CString str = new CString(Colrow1);
	str.arrange();
	return str.toString();
}
public String getCol_row11() {
	if (Col_row11 == null || Col_row11.equals(""))
		Col_row11 = "---";
	CString str = new CString(Col_row11);
	str.arrange();
	return str.toString();
}

public String getColrow11() {
	CString str = new CString(Colrow11);
	str.arrange();
	return str.toString();
}
public String getCol_row2() {
	if (Col_row2 == null || Col_row2.equals(""))
		Col_row2 = "---";
	CString str = new CString(Col_row2);
	str.arrange();
	return str.toString();
}

public String getColrow2() {
	CString str = new CString(Colrow2);
	str.arrange();
	return str.toString();
}
public String getCol_row21() {
	if (Col_row21 == null || Col_row21.equals(""))
		Col_row21 = "---";
	CString str = new CString(Col_row21);
	str.arrange();
	return str.toString();
}

public String getColrow21() {
	CString str = new CString(Colrow21);
	str.arrange();
	return str.toString();
}
public String getUrl() {
	return getUrlBannerPromocional();
}
public String getUrlBannerPromocional() {
	return JspServlet.COLOR_TIPO_PERSONA_SERVLET + "?BtnCol=Buscar&TxtTypper=";
}
public void insert(JQuery db) throws java.sql.SQLException {
	try {
		String antTypper = typper;
		String antColbck = Colbck;
		String antColtit = Coltit;
		String antColsubtit = Colsubtit;
		String antColrow1 = Colrow1;
		String antColrow2 = Colrow2;
		String antTypper1 = typper1;
		String antColbck1 = Colbck1;
		String antColtit1 = Coltit1;
		String antColsubtit1 = Colsubtit1;
		String antColrow11 = Colrow11;
		String antColrow21 = Colrow21;
		setError("");
		//if (!search(db)) {
			String query = //
			"Insert into "+Constante.ESQUEMA1+".TCOLDAT (typper,colbck,coltit,colsubtit,colrow1,colrow2) values ('" + antTypper + "', '" + antColbck + "','" + antColtit + "','" + antColsubtit + "', '" + antColrow1 + "', '" + antColrow2 + "')";
			// System.out.println("QUERY-----"+query);
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", "","", "", "", ""};
			String aft[] = {getTypper(), getColbck(), getColtit(), getColsubtit(), getColrow1(), getColrow2()};
			LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TCOLDAT", Table.getColumnsTcoldat(), bef, aft);
			setGrid(null);
			query = //
			"Insert into "+Constante.ESQUEMA1+".TCOLDAT (typper,colbck,coltit,colsubtit,colrow1,colrow2) values ('" + antTypper1 + "', '" + antColbck1 + "','" + antColtit1 + "','" + antColsubtit1 + "', '" + antColrow11 + "', '" + antColrow21 + "')";
			// System.out.println("QUERY-----"+query);
				db.setQuery(query);
				db.executeUpdate();
				String bef1[] = {"", "","", "", "", ""};
				String aft1[] = {getTypper1(), getColbck1(), getColtit1(), getColsubtit1(), getColrow11(), getColrow21()};
				LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TCOLDAT", Table.getColumnsTcoldat(), bef1, aft1);
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
			"Select typper,colbck,coltit,colsubtit,colrow1,colrow2 " + //
			"From "+Constante.ESQUEMA1+".TCOLDAT ";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector row = new java.util.Vector(6);
				row.addElement(getStatus());
				row.addElement(db.stringValue(1));
				row.addElement(db.stringValue(2));
				row.addElement(db.stringValue(3));
				row.addElement(db.stringValue(4));
				row.addElement(db.stringValue(5));
				row.addElement(db.stringValue(6));
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
	Col_bck = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
	Col_tit = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
	Col_sub_tit = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
	Col_row1 = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
	Col_row2 = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
}
public void nextChild(int i) {
}
public boolean search(JQuery db) throws java.sql.SQLException {
	try {
		java.text.SimpleDateFormat sd = new java.text.SimpleDateFormat();
		java.util.Vector lv_vec=new java.util.Vector();
		String query = //
		"Select typper,colbck,coltit,colsubtit,colrow1,colrow2 " + //
		"From "+Constante.ESQUEMA1+".TCOLDAT "+
		"Where typper = '01'";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
		return false;
		typper = db.stringValue(1);
		Colbck = db.stringValue(2);
		Coltit = db.stringValue(3);
		Colsubtit = db.stringValue(4);
		Colrow1 = db.stringValue(5);
		Colrow2 = db.stringValue(6);
		//mostrar(db);
		query = //
		"Select typper,colbck,coltit,colsubtit,colrow1,colrow2 " + //
		"From "+Constante.ESQUEMA1+".TCOLDAT "+
		"Where typper = '02'";
		db.setQuery(query);
		db.executeQuery();
			if (db.getResultSet().next()){
			typper1 = db.stringValue(1);
			Colbck1 = db.stringValue(2);
			Coltit1 = db.stringValue(3);
			Colsubtit1 = db.stringValue(4);
			Colrow11 = db.stringValue(5);
			Colrow21 = db.stringValue(6);
			}
		return true;
	}
	catch (Exception e) {
		e.printStackTrace();
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
public void setCol_bck(String newValue) {
	this.Col_bck = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setColbck(String newValue) {
	this.Colbck = newValue;
}

public void setCol_bck1(String newValue) {
	this.Col_bck1 = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setColbck1(String newValue) {
	this.Colbck1 = newValue;
}

public void setCol_tit(String newValue) {
	this.Col_tit = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setColtit(String newValue) {
	this.Coltit = newValue;
}
public void setCol_tit1(String newValue) {
	this.Col_tit1 = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setColtit1(String newValue) {
	this.Coltit1 = newValue;
}
public void setCol_row1(String newValue) {
	this.Col_row1 = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setColrow1(String newValue) {
	this.Colrow1 = newValue;
}
public void setCol_row11(String newValue) {
	this.Col_row11 = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setColrow21(String newValue) {
	this.Colrow21 = newValue;
}
public void setCol_row2(String newValue) {
	this.Col_row2 = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setColrow2(String newValue) {
	this.Colrow2 = newValue;
}
public void setCol_row21(String newValue) {
	this.Col_row21 = newValue;
}
public void setCol_sub_tit(String newValue) {
	this.Col_sub_tit = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setColsubtit(String newValue) {
	this.Colsubtit = newValue;
}
public void setCol_sub_tit1(String newValue) {
	this.Col_sub_tit1 = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setColsubtit1(String newValue) {
	this.Colsubtit1 = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setColrow11(String newValue) {
	this.Colrow11 = newValue;
}
public void sort(JQuery db) throws java.sql.SQLException {
}
public void update(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		String antTypper = typper;
		String antColbck = Colbck;
		String antColtit = Coltit;
		String antColsubtit = Colsubtit;
		String antColrow1 = Colrow1;
		String antColrow2 = Colrow2;
		String antTypper1 = typper1;
		String antColbck1 = Colbck1;
		String antColtit1 = Coltit1;
		String antColsubtit1 = Colsubtit1;
		String antColrow11 = Colrow11;
		String antColrow21 = Colrow21;
		//if (search(db)) {
			String query = //
			"Update "+Constante.ESQUEMA1+".TCOLDAT set colbck = '" + antColbck + "' , coltit = '" + antColtit + "' ,colsubtit = '" + antColsubtit + "' , colrow1 = '" + antColrow1 + "', colrow2 = '" + antColrow2 + "'  Where typper = '" + antTypper + "' ";
			
			// System.out.println("QUERY-----"+query);
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getTypper(), getColbck(),getColtit(),getColrow1(),getColrow2()};
			String aft[] = {getTypper(), antColbck, antColtit, antColsubtit,antColrow1, antColrow2};
			LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "update", Constante.ESQUEMA1+".TCOLDAT", Table.getColumnsTcoldat(), bef, aft);
			//setGrid(null);
			rows=0;
			query = //
			"Update "+Constante.ESQUEMA1+".TCOLDAT set colbck = '" + antColbck1 + "' , coltit = '" + antColtit1 + "' ,colsubtit = '" + antColsubtit1 + "' , colrow1 = '" + antColrow11 + "', colrow2 = '" + antColrow21 + "'  Where typper = '" + antTypper1 + "' ";
			// System.out.println("QUERY-----"+query);
				db.setQuery(query);
				rows = db.executeUpdate();
				String bef1[] = {getTypper1(), getColbck1(),getColtit1(),getColsubtit1(),getColrow11(),getColrow21()};
				String aft1[] = {getTypper1(), antColbck1, antColtit1,antColsubtit1, antColrow11, antColrow21};
				LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "update", Constante.ESQUEMA1+".TCOLDAT", Table.getColumnsTcoldat(), bef1, aft1);
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
