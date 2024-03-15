package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class NominaAutorizacion {
	//
	private CosapiSoft.SARAWebBanking.InicioSesion login;






	public java.lang.String error="";



/**
 * AyudaDeCampo constructor comment.
 */
public NominaAutorizacion() {
	super();
}
public void agregar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	// System.out.println("ENTRO");
	try {
		error = "";
		String query = //
		"delete from tlstaut where codorg='" + getCodorg() + "' and codlst='" + getCodlst() + "'";
		db.setQuery(query);
		db.executeUpdate();
		// System.out.println("BORRO");

		for (int i = 0; i < gridIn.size(); ++i) {
			// System.out.println("INSERTO");
			query = //
			"insert into tlstaut values ('" + getCodorg() + "','" + getCodlst() + "'," + ((java.util.Vector) gridIn.elementAt(i)).elementAt(1) + ", '" + ((java.util.Vector) gridIn.elementAt(i)).elementAt(0) + "')";
			db.setQuery(query);
			db.executeUpdate();
		}
		/*String bef[] = {"", "", "", "", "", "", "", "", ""};
		String aft[] = {getCodorg(), getCodlst(), getCodigo(), getNombre(), getApaterno(), getAmaterno(), getMonto(), getActivo(), getCuenta()};
		LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Agregar", "tlstdet", Table.getColumnsTlstdet(), bef, aft);*/
		loadGrid(db);
	} catch (Exception e) {
		throw new Exception("\nAgregar - Datos de Empresa \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
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
	gridTodos = new java.util.Vector();
	gridIn = new java.util.Vector();
	gridOut = new java.util.Vector();
	try {
		String query = //
		"Select codusr, txtnam, lstnam, famnam " + //
		"From tusrorg where codorg='"+getCodorg()+"'"; //
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector();
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			row.addElement(db.stringValue(3));
			row.addElement(db.stringValue(4));
			gridTodos.addElement(row);
		}

		query = //
		"Select tlstaut.codusr,tlstaut.numseq, tusrorg.txtnam, tusrorg.lstnam, tusrorg.famnam " + //
		"From tlstaut, tusrorg where tlstaut.codorg='"+getCodorg()+"' and tlstaut.codlst='"+getCodlst()+"' and tlstaut.codusr=tusrorg.codusr order by tlstaut.numseq"; //
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector();
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			row.addElement(db.stringValue(3));
			row.addElement(db.stringValue(4));
			row.addElement(db.stringValue(5));
			gridIn.addElement(row);
		}


		for (int i=0;i<gridTodos.size();i++)
		{
			int sw=0;
			for (int j=0;j<gridIn.size();j++)
				if (((java.util.Vector)gridTodos.elementAt(i)).elementAt(0).toString().equals(((java.util.Vector)gridIn.elementAt(j)).elementAt(0).toString()))
					sw=1;
			if (sw==0)
				gridOut.addElement(((java.util.Vector)gridTodos.elementAt(i)));
		}
			
		// System.out.println("IN: "+gridIn);
		// System.out.println("OUT: "+gridOut);
		
		
	} catch (Exception e) {
		throw new Exception("\nLoadGrid - Detalle Nomina \n" + e.getMessage());
	}
}











/**
 * This method was created in VisualAge.
 * @param newValue CosapiSoft.SARAWebBanking.InicioSesion
 */
public void setLogin(CosapiSoft.SARAWebBanking.InicioSesion newValue) {
	this.login = newValue;
}

	public java.lang.String codlst = "";	public java.lang.String codorg="";	public java.util.Vector gridIn = new java.util.Vector();	public java.util.Vector gridOut = new java.util.Vector();	//
	public java.util.Vector gridTodos = new java.util.Vector();/**
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
 * Creation date: (3/8/01 2:28:03 PM)
 * @return java.lang.String
 */ 
public java.lang.String getError() {
	return error;
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
 * Creation date: (3/8/01 2:28:03 PM)
 * @param newError java.lang.String
 */ 
public void setError(java.lang.String newError) {
	error = newError;
}}