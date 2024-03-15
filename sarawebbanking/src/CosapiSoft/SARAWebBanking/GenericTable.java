package CosapiSoft.SARAWebBanking;

import java.util.HashMap;
import java.util.Map;

/**
 * This type was created in VisualAge.
 */
public abstract class GenericTable {
	private java.util.Vector grid = null;
	private int index = 0;
	private int ideSort = -1;
	private InicioSesion login = null;
	private String error = "";
	private String status = "0";
	private java.util.Vector gridChild = null;
	private int indexChild = 0;
	private Map totalMap = new HashMap();
/**
 * This method was created in VisualAge.
 */
public GenericTable() {
	super();
}
/**
 * This method was created in VisualAge.
 * @exception java.sql.SQLException The exception description.
 */
public abstract void consult() throws java.sql.SQLException;
/**
 * This method was created in VisualAge.
 * @exception java.sql.SQLException The exception description.
 */
public void delete() throws java.sql.SQLException {
	setError("");
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		delete(db);
	}
	catch (java.sql.SQLException e) {
		throw new java.sql.SQLException("\n... " + getClass().getName() + " - delete() \n " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @param db CosapiSoft.SARAWebBanking.JQuery
 * @exception java.sql.SQLException The exception description.
 */
public abstract void delete(JQuery db) throws java.sql.SQLException;
/**
 * This method was created in VisualAge.
 * @param db CosapiSoft.SARAWebBanking.JQuery
 * @param req javax.servlet.http.HttpServletRequest
 * @exception javax.servlet.ServletException The exception description.
 * @exception java.sql.SQLException The exception description.
 */
public abstract void delete(JQuery db, javax.servlet.http.HttpServletRequest req) throws javax.servlet.ServletException, java.sql.SQLException;
/**
 * This method was created in VisualAge.
 * @param req javax.servlet.http.HttpServletRequest
 * @exception javax.servlet.ServletException The exception description.
 * @exception java.sql.SQLException The exception description.
 */
public void delete(javax.servlet.http.HttpServletRequest req) throws javax.servlet.ServletException, java.sql.SQLException {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		delete(db, req);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... " + getClass().getName() + " - delete(" + req.toString() + ") -> " + e.getMessage());
	}
	finally {
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
 * @return java.util.Vector
 */
public java.util.Vector getGrid() {
	if (grid == null) {
		grid = new java.util.Vector(100, 100);
	}
	return grid;
}
/**
 * This method was created in VisualAge.
 * @return java.util.Vector
 */
public java.util.Vector getGridChild() {
	if (gridChild == null) {
		gridChild = new java.util.Vector(50, 50);
	}
	return gridChild;
}
/**
 * This method was created in VisualAge.
 * @return int
 */
public int getIdeSort() {
	return ideSort;
}
/**
 * This method was created in VisualAge.
 * @return int
 */
protected int getIndex() {
	return index;
}
/**
 * This method was created in VisualAge.
 * @return int
 */
public int getIndexChild() {
	return indexChild;
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
public abstract String getNameTable();
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
public abstract String getUrl();
/**
 * This method was created in VisualAge.
 * @exception java.sql.SQLException The exception description.
 */
public void insert() throws java.sql.SQLException {
	setError("");
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		insert(db);
	}
	catch (java.sql.SQLException e) {
		throw new java.sql.SQLException("\n... " + getClass().getName() + " - insert() \n " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @param db CosapiSoft.SARAWebBanking.JQuery
 * @exception java.sql.SQLException The exception description.
 */
public abstract void insert(JQuery db) throws java.sql.SQLException;
/**
 * This method was created in VisualAge.
 * @exception java.sql.SQLException The exception description.
 * @throws Exception
 */
public void loadGrid() throws java.sql.SQLException, Exception {
	index = 0;
	if (getGrid().size() == 0) {
		JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
		JQuery db = new JQuery(jd.getConnection());
		try {
			error = "";
			loadGrid(db);
		}
		catch (java.sql.SQLException e) {
			throw new java.sql.SQLException("\n... " + getClass().getName() + " - loadGrid() \n " + e.getMessage());
		}
		finally {
			db.close();
			jd.close();
		}
	}
}
/**
 * This method was created in VisualAge.
 * @param db CosapiSoft.SARAWebBanking.JQuery
 * @exception java.sql.SQLException The exception description.
 * @throws Exception
 */
public abstract void loadGrid(JQuery db) throws java.sql.SQLException, Exception;
/**
 * This method was created in VisualAge.
 * @exception java.sql.SQLException The exception description.
 */
public void loadGridChild() throws java.sql.SQLException {
	indexChild = 0;
	if (getGridChild().size() == 0) {
		JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
		JQuery db = new JQuery(jd.getConnection());
		try {
			error = "";
			loadGridChild(db);
		}
		catch (java.sql.SQLException e) {
			throw new java.sql.SQLException("\n... " + getClass().getName() + " - loadGridChild() \n " + e.getMessage());
		}
		finally {
			db.close();
			jd.close();
		}
	}
}
/**
 * This method was created in VisualAge.
 * @param db CosapiSoft.SARAWebBanking.JQuery
 * @exception java.sql.SQLException The exception description.
 */
public abstract void loadGridChild(JQuery db) throws java.sql.SQLException;
/**
 * This method was created in VisualAge.
 */
public void next() {
	next(index);
	index++;
}
/**
 * This method was created in VisualAge.
 * @param index int
 */
public abstract void next(int index);
/**
 * This method was created in VisualAge.
 */
public void nextChild() {
	nextChild(indexChild);
	indexChild++;
}
/**
 * This method was created in VisualAge.
 * @param index int
 */
public abstract void nextChild(int index);
/**
 * This method was created in VisualAge.
 * @return boolean
 * @exception java.sql.SQLException The exception description.
 */
public boolean search() throws java.sql.SQLException {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		boolean sw = search(db);
		return sw;
	}
	catch (java.sql.SQLException e) {
		throw new java.sql.SQLException("\n... " + getClass().getName() + " - search() \n " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @return boolean
 * @param db CosapiSoft.SARAWebBanking.JQuery
 * @exception java.sql.SQLException The exception description.
 */
public abstract boolean search(JQuery db) throws java.sql.SQLException;
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setError(String newValue) {
	this.error = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.util.Vector
 */
public void setGrid(java.util.Vector newValue) {
	this.grid = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.util.Vector
 */
public void setGridChild(java.util.Vector newValue) {
	this.gridChild = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setIdeSort(int newValue) {
	this.ideSort = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
protected void setIndex(int newValue) {
	this.index = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setIndexChild(int newValue) {
	this.indexChild = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue CosapiSoft.SARAWebBanking.InicioSesion
 */
public void setLogin(InicioSesion newValue) throws Exception {
	try {
		if (newValue == null) {
			newValue = new InicioSesion();
		}
		InicioSesion.loadParameters();
		this.login = newValue;
	}
	catch (Exception e) {
		throw new Exception("\n... " + getClass().getName() + " - setLogin() -> " + e.getMessage());
	}
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
 * @exception java.sql.SQLException The exception description.
 */
public void sort() throws java.sql.SQLException {
	setError("");
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		sort(db);
	}
	catch (java.sql.SQLException e) {
		throw new java.sql.SQLException("\n... " + getClass().getName() + " - sort() \n " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @param db CosapiSoft.SARAWebBanking.JQuery
 * @exception java.sql.SQLException The exception description.
 */
public abstract void sort(JQuery db) throws java.sql.SQLException;
/**
 * This method was created in VisualAge.
 * @exception java.sql.SQLException The exception description.
 */
public void update() throws java.sql.SQLException {
	setError("");
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		update(db);
	}
	catch (java.sql.SQLException e) {
		throw new java.sql.SQLException("\n... " + getClass().getName() + " - update() \n " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @param db CosapiSoft.SARAWebBanking.JQuery
 * @exception java.sql.SQLException The exception description.
 */
public abstract void update(JQuery db) throws java.sql.SQLException;
}