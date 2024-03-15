package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import java.util.Vector;

import CosapiSoft.SARAWebBanking.*;
public class Caja extends GenericTable {






/**
 * AyudaDeCampo constructor comment.
 */
public Caja() {
	super();
}
public void consult() throws java.sql.SQLException {
	try {
		String bef[] = {"", "", "", "", "", "", ""};
		String aft[] = {"", "", "", "", "", "", ""};
		LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", "tcajdat", Table.getColumnsTcajdat(), bef, aft);
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... consult() - Cajas \n" + e.getMessage());
	}
}
public void delete(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		if (search(db)) {
			String query = //
			"Delete from tcajdat Where codcaj = '" + getCodcaj() + "'";
			db.setQuery(query);
			db.executeUpdate();
			query = //
			"Delete from tcajdet Where codcaj = '" + getCodcaj() + "'";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {codcaj, getNomcaj(), getCodcur(),getCodusr(),getCodbra(),getCodlim(), getFlgblk()};
			String aft[] = {"", "", "", "", "", "", ""};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", "tcajdat", Table.getColumnsTcajdat(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_CAJA_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodcaj() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - Clases \n" + e.getMessage());
	}
}
public void delete(JQuery db, javax.servlet.http.HttpServletRequest req) throws java.sql.SQLException, javax.servlet.ServletException {
	try {
		setError("");
		java.util.Enumeration vector = req.getParameterNames();
		while (vector.hasMoreElements() && vector != null) {
			codcaj = vector.nextElement().toString();
			String sw = req.getParameter(codcaj).toUpperCase();
			if (sw.equals("ON")) {
				if (search(db)) {
					setGrid(null);
					String query = //
					"Delete from tcajdat Where codcaj = '" + getCodcaj() + "'";
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from tcajdet Where codcaj = '" + getCodcaj() + "'";
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {codcaj, getNomcaj(), getCodcur(),getCodusr(),getCodbra(),getCodlim(), getFlgblk()};
					String aft[] = {"", "", "", "", "", "", ""};
					LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", "tcajdat", Table.getColumnsTcajdat(), bef, aft);
				}
			}
		}
		loadGrid(db);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - Clases \n" + e.getMessage());
	}
}


/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNameTable() {
	return "tcladat";
}




/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getUrl() {
	return getUrlClases();
}
public String getUrlClases() {
	return JspServlet.CAJAS_SERVLET + "?BtnCaj=Buscar&TxtCodcaj=";
}
public void insert(JQuery db) throws java.sql.SQLException {
	try {
		if (!search(db)) {
			String query = //
			"Insert into tcajdat (codcaj, nomcaj, codcur, codusr, codbra, codlim, flgblk) values ('" + getCodcaj() + "', '" + getNomcaj() + "', '" + getCodcur() + "', '" + getCodusr() + "', '" + getCodbra() + "', '" + getCodlim() + "','"+getFlgblk()+"')";
			db.setQuery(query);
			db.executeUpdate();
			query = //
			"Insert into tcajdet (codcaj, codcur, codusr, codbra, amocaj, amoent, amosal) values ('" + getCodcaj() + "', '" + getCodcur() + "', '" + getCodusr() + "', '" + getCodbra() + "', 0,0,0)";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", "", "", "", "", "", ""};
			String aft[] = {getCodcaj(), getNomcaj(), getCodcur(), getCodusr(),getCodbra(), getCodlim(), getFlgblk()};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", "tcajdat", Table.getColumnsTcajdat(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_CAJA_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodcaj() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - Cajas \n" + e.getMessage());
	}
}
public void loadGrid(JQuery db) throws java.sql.SQLException {
	try {
		setIndex(0);
		setStatus("0");
		if (getGrid().size() == 0) {
			String query = //
			"Select a.codcaj, a.nomcaj, a.codcur, a.codusr, a.codbra, a.codlim, a.flgblk, b.txtlim, c.txtcurlon, d.txtnam, e.txtbra " + //
			"From tcajdat a left join tlimdat b on a.codlim=b.codlim, tcurdat c, tusrdat d, tbradat e " + //
			"where a.codcur=c.codcur and a.codusr=d.codusr and a.codbra=e.codbra " + //
			"Order by codcaj";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector row = new java.util.Vector(3);
				row.addElement(getStatus());
				row.addElement(db.stringValue(1).trim());
				row.addElement(db.stringValue(2).trim());
				row.addElement(db.stringValue(3).trim()+"-"+db.stringValue(9).trim());
				row.addElement(db.stringValue(4).trim()+"-"+db.stringValue(10).trim());
				row.addElement(db.stringValue(5).trim()+"-"+db.stringValue(11).trim());
				row.addElement(db.stringValue(6).trim());
				row.addElement(db.stringValue(7).trim());
				row.addElement(db.stringValue(8).trim());
				getGrid().addElement(row);
			}
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n...loadGrid() - Cajas \n" + e.getMessage());
	}
}
public void loadGridChild(JQuery db) throws java.sql.SQLException {
}
public void next(int i) {
	setStatus(((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString().trim());
	cod_caj = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString().trim();
	nom_caj = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString().trim();
	cod_cur = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString().trim();
	cod_usr = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString().trim();
	cod_bra = ((java.util.Vector) getGrid().elementAt(i)).elementAt(5).toString().trim();
	cod_lim = ((java.util.Vector) getGrid().elementAt(i)).elementAt(6).toString().trim();
	flg_blk = ((java.util.Vector) getGrid().elementAt(i)).elementAt(7).toString().trim();
	txt_lim = ((java.util.Vector) getGrid().elementAt(i)).elementAt(8).toString().trim();
}
public void nextChild(int i) {
}
public boolean search(JQuery db) throws java.sql.SQLException {
	try {
		String query = //
		"Select nomcaj, codcur, codusr, codbra, codlim, flgblk " + //
		"From tcajdat " + //
		"Where codcaj = '" + getCodcaj() + "'";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		nomcaj = db.stringValue(1);
		codcur = db.stringValue(2);
		codusr = db.stringValue(3);
		codbra = db.stringValue(4);
		codlim = db.stringValue(5);
		flgblk = db.stringValue(6);
		return true;
	}
	catch (java.sql.SQLException e) {
		throw new java.sql.SQLException("\n... search() - Cajas \n" + e.getMessage());
	}
}








/**
 * This method was created in VisualAge.
 * @param db CosapiSoft.SARAWebBanking.JQuery
 * @exception java.sql.SQLException The exception description.
 */
public void sort(JQuery db) throws java.sql.SQLException {
}
public void update(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		String antNomcaj = nomcaj;
		String antCodcur = codcur;
		String antCodusr = codusr;
		String antCodbra = codbra;
		String antCodlim = codlim;
		String antFlgblk = flgblk;
		if (search(db)) {
			String query = //
			"Update tcajdat set nomcaj = '" + antNomcaj + "', codcur = '" + antCodcur + "', codusr = '" + antCodusr + "', codbra = '" + antCodbra + "', codlim = '" + antCodlim + "', flgblk='"+antFlgblk+"' Where codcaj = '" + getCodcaj() + "' ";
			db.setQuery(query);
			int rows = db.executeUpdate();
			query = //
			"Update tcajdet set codcur = '" + antCodcur + "', codusr = '" + antCodusr + "', codbra = '" + antCodbra + "' Where codcaj = '" + getCodcaj() + "' ";
			db.setQuery(query);
			//int rows = db.executeUpdate();
			String bef[] = {getCodcaj(), getNomcaj(), getCodcur(), getCodusr(), getCodbra(), getCodlim(),getFlgblk()};
			String aft[] = {getCodcaj(), antNomcaj, antCodcur, antCodusr, antCodbra, antCodlim, antFlgblk};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", "tcajdat", Table.getColumnsTcajdat(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_CAJA_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodcaj() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... update() - Clases \n" + e.getMessage());
	}
}
	private String txtlim = "";	//
	private String txt_lim = "";	//
	private String cod_bra = "";	//
	private String cod_lim = "";	//
	private String flg_blk = "";	//
	private String codlim = "";	//
	private String flgblk = "";	//
	private Vector limites = null;	//
	private String cod_caj = "";	private String cod_cur = "";	private String cod_usr = "";	private String codbra = "";	private String codcaj = "";	private String codcur = "";	private String codusr = "";	private String lim_max = "";	private String limmax = "";	private java.util.Vector monedas;	private String nom_caj = "";	private String nomcaj = "";	private java.util.Vector oficinas;	private java.util.Vector usuarios;/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_bra() {
	if (cod_bra == null)
		cod_bra = "";
	return cod_bra.trim();
}/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */ 
public String getCod_caj() {
	if (cod_caj == null)
		cod_caj = "";
	return cod_caj.trim();
}/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */ 
public String getCod_cur() {
	return cod_cur;
}/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */ 
public String getCod_usr() {
	if (cod_usr == null)
		cod_usr = "";
	return cod_usr.trim();
}/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */ 
public String getCodbra() {
	return codbra;
}/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */ 
public String getCodcaj() {
	try {
		if (codcaj == null || codcaj.equals(""))
			codcaj = "0";
		codcaj = codcaj.trim();
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(codcaj)) {
			codcaj = "0";
		}
		int cod = new Integer(codcaj).intValue();
		if (cod <= 0) {
			InicioSesion.loadParameters();
			JDatabase jd = new JDatabase();
			JQuery db = new JQuery(jd.getConnection());
			try {
				String query = //
				"Select codcaj " + //
				"From tcajdat " + //
				"Order by codcaj ";
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					codcaj = db.stringValue(1).trim();
				}
				cod = new Integer(codcaj).intValue();
				codcaj = (cod >= 999) ? "999" : "" + (cod + 1);
			}
			catch (Exception ex) {
				// System.out.println(getClass().getName() + " - getcodcaj() --> " + ex.getMessage());
				return "-1";
			}
			finally {
				db.close();
				jd.close();
			}
		}
		if (!codcaj.equals(""))
			codcaj = CString.completeToBeginWithNChars(codcaj, 3, '0');
		return codcaj.trim();
	}
	catch (Exception e) {
		return "-1";
	}
}/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */ 
public String getCodcur() {
	return codcur;
}/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */ 
public String getCodusr() {
	return codusr;
}/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */ 
public String getLim_max() {
	if (lim_max == null)
		lim_max = "";
	return lim_max.trim();
}/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */ 
public String getLimmax() {
	return limmax;
}/**
 * Insert the method's description here.
 * Creation date: (10/16/02 5:14:36 PM)
 * @return java.util.Vector
 */ 
public java.util.Vector getMonedas() {
	if (monedas==null){
		try{
			JDatabase jd = new JDatabase();
			JQuery db = new JQuery(jd.getConnection());
			monedas=new java.util.Vector();
			String query = //
			"Select codcur, txtcurlon " + //
			"From tcurdat " + //
			"Order by codcur";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector row = new java.util.Vector(3);
				row.addElement(db.stringValue(1).trim());
				row.addElement(db.stringValue(2).trim());
				monedas.addElement(row);
			}
		}
		catch(java.sql.SQLException e){
		}		
	
		
	}
	return monedas;
}/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */ 
public String getNom_caj() {
	return nom_caj;
}/**
 * This method was created in VisualAge.
 * @return String
 */ 
public String getNomcaj() {
	if (nomcaj	 == null)
		nomcaj = "";
	CString str = new CString(nomcaj);
	str.arrange();
	return str.toString();
}/**
 * Insert the method's description here.
 * Creation date: (10/16/02 5:14:09 PM)
 * @return java.util.Vector
 */ 
public java.util.Vector getOficinas() {
	if (oficinas==null){
		try{
			JDatabase jd = new JDatabase();
			JQuery db = new JQuery(jd.getConnection());
			oficinas=new java.util.Vector();
			String query = //
			"Select codbra, txtbra " + //
			"From tbradat " + //
			"Order by codbra";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector row = new java.util.Vector(3);
				row.addElement(db.stringValue(1).trim());
				row.addElement(db.stringValue(2).trim());
				oficinas.addElement(row);
			}
		}
		catch(java.sql.SQLException e){
		}		
	
		
	}
	return oficinas;
}/**
 * Insert the method's description here.
 * Creation date: (10/16/02 5:15:02 PM)
 * @return java.util.Vector
 */ 
public java.util.Vector getUsuarios() {
	if (usuarios==null){
		try{
			JDatabase jd = new JDatabase();
			JQuery db = new JQuery(jd.getConnection());
			usuarios=new java.util.Vector();
			String query = //
			"Select codusr, txtnam, codbra " + //
			"From tusrdat " + //
			"Order by codusr";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector row = new java.util.Vector(3);
				row.addElement(db.stringValue(1).trim());
				row.addElement(db.stringValue(2).trim());
				row.addElement(db.stringValue(3).trim());
				usuarios.addElement(row);
			}
		}
		catch(java.sql.SQLException e){
		}		
	
		
	}
	return usuarios;
}public static String searchCaja(JQuery db, String codcaj) throws Exception {
	try {
		String query = //
		"Select nomcaj " + //
		"From tcajdat " + //
		"Where codcaj = '" + codcaj + "'";
		db.setQuery(query);
		db.executeQuery();
		boolean sw = db.getResultSet().next();
		String txtcaj = "---";
		if (sw) {
			txtcaj = db.stringValue(1).trim();
			if (txtcaj.equals("")) {
				txtcaj = "---";
			}
		}
		return txtcaj;
	}
	catch (Exception e) {
		throw new Exception("\n... searchClase(" + codcaj + ") - Cajas \n" + e.getMessage());
	}
}public static String searchCaja(String codcaj) throws Exception {
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		String query = //
		"Select nomcaj " + //
		"From tcajdat " + //
		"Where codcaj = '" + codcaj + "'";
		db.setQuery(query);
		db.executeQuery();
		boolean sw = db.getResultSet().next();
		String txtcaj = "---";
		if (sw) {
			txtcaj = db.stringValue(1);
			if (txtcaj.equals("")) {
				txtcaj = "---";
			}
		}
		return txtcaj;
	} catch (Exception e) {
		throw new Exception("\n... searchClase(" + codcaj + ") - Cajas \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */ 
public void setCod_bra(String newValue) {
	this.cod_bra = newValue;
}/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */ 
public void setCod_caj(String newValue) {
	this.cod_caj = newValue;
}/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */ 
public void setCod_cur(String newValue) {
	this.cod_cur = newValue;
}/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */ 
public void setCod_usr(String newValue) {
	this.cod_usr = newValue;
}/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */ 
public void setCodbra(String newValue) {
	this.codbra = newValue;
}/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */ 
public void setCodcaj(String newValue) {
	this.codcaj = newValue;
}/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */ 
public void setCodcur(String newValue) {
	this.codcur = newValue;
}/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */ 
public void setCodusr(String newValue) {
	this.codusr = newValue;
}/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */ 
public void setLim_max(String newValue) {
	this.lim_max = newValue;
}/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */ 
public void setLimmax(String newValue) {
	this.limmax = newValue;
}/**
 * Insert the method's description here.
 * Creation date: (10/16/02 5:14:36 PM)
 * @param newMonedas java.util.Vector
 */ 
public void setMonedas(java.util.Vector newMonedas) {
	monedas = newMonedas;
}/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */ 
public void setNom_caj(String newValue) {
	this.nom_caj = newValue;
}/**
 * This method was created in VisualAge.
 * @param newValue int
 */ 
public void setNomcaj(String newValue) {
	this.nomcaj = newValue;
}/**
 * Insert the method's description here.
 * Creation date: (10/16/02 5:14:09 PM)
 * @param newOficinas java.util.Vector
 */ 
public void setOficinas(java.util.Vector newOficinas) {
	oficinas = newOficinas;
}/**
 * Insert the method's description here.
 * Creation date: (10/16/02 5:15:02 PM)
 * @param newUsuarios java.util.Vector
 */ 
public void setUsuarios(java.util.Vector newUsuarios) {
	usuarios = newUsuarios;
	/**
	 * Returns the cod_lim.
	 * @return String
	 */

}


	/**
	 * Returns the cod_lim.
	 * @return String
	 */
	public String getCod_lim() {
		return cod_lim;
	}

	/**
	 * Returns the codlim.
	 * @return String
	 */
	public String getCodlim() {
		return codlim;
	}

	/**
	 * Returns the flg_blk.
	 * @return String
	 */
	public String getFlg_blk() {
		return flg_blk;
	}

	/**
	 * Returns the flgblk.
	 * @return String
	 */
	public String getFlgblk() {
		return flgblk;
	}

	/**
	 * Returns the limites.
	 * @return Vector
	 */
	public Vector getLimites() {

			setLimites(new Vector());
			JDatabase jd = null;
			JQuery db = null;
			try {
				jd = new JDatabase();
				db = new JQuery(jd.getConnection());
				String query = "Select codlim, txtlim From tlimdat order by codlim"; //
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					java.util.Vector row = new java.util.Vector(2);
					row.addElement(db.stringValue(1));
					row.addElement(db.stringValue(2));
					limites.addElement(row);
				}
			} catch (Exception e) {
				// System.out.println(e.getMessage());
			} finally {
				try {
					db.close();
					jd.close();
				} catch (Exception e1) {
				}
			}
		return limites;
	}


	/**
	 * Sets the cod_lim.
	 * @param cod_lim The cod_lim to set
	 */
	public void setCod_lim(String cod_lim) {
		this.cod_lim = cod_lim;
	}

	/**
	 * Sets the codlim.
	 * @param codlim The codlim to set
	 */
	public void setCodlim(String codlim) {
		this.codlim = codlim;
	}

	/**
	 * Sets the flg_blk.
	 * @param flg_blk The flg_blk to set
	 */
	public void setFlg_blk(String flg_blk) {
		this.flg_blk = flg_blk;
	}

	/**
	 * Sets the flgblk.
	 * @param flgblk The flgblk to set
	 */
	public void setFlgblk(String flgblk) {
		this.flgblk = flgblk;
	}

	/**
	 * Sets the limites.
	 * @param limites The limites to set
	 */
	public void setLimites(Vector limites) {
		this.limites = limites;
	}

	/**
	 * Returns the txt_lim.
	 * @return String
	 */
	public String getTxt_lim() {
		return txt_lim;
	}

	/**
	 * Returns the txtlim.
	 * @return String
	 */
	public String getTxtlim() {
		return txtlim;
	}

	/**
	 * Sets the txt_lim.
	 * @param txt_lim The txt_lim to set
	 */
	public void setTxt_lim(String txt_lim) {
		this.txt_lim = txt_lim;
	}

	/**
	 * Sets the txtlim.
	 * @param txtlim The txtlim to set
	 */
	public void setTxtlim(String txtlim) {
		this.txtlim = txtlim;
	}

}