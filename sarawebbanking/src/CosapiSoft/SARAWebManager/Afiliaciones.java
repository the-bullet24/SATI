package CosapiSoft.SARAWebManager;


import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class Afiliaciones extends GenericTable {
	private String tipafi = "";
	private String numtar = "";
	private String numsec = "";
	private String typdoc = "";
	private String numdoc = "";
	private String fecnac = "";
	private String sexper = "";
	private String email = "";
	private String coddoc = "";
	private String codser = "";
	private String numser = "";
	private String desafil = "";
	private String ctapro = "";
	private String nomben = "";
	private String cliafi = "";
	private String clidesafi = "";
	private String serdesafi = "";
	private String serafi = "";


	public String getCliafi() {
		return cliafi;
	}
	/**
	 * @param cliafi El cliafi a establecer.
	 */
	public void setCliafi(String cliafi) {
		this.cliafi = cliafi;
	}
	/**
	 * @return Devuelve clidesafi.
	 */
	public String getClidesafi() {
		return clidesafi;
	}
	/**
	 * @param clidesafi El clidesafi a establecer.
	 */
	public void setClidesafi(String clidesafi) {
		this.clidesafi = clidesafi;
	}
	/**
	 * @return Devuelve serdesafi.
	 */
	public String getSerdesafi() {
		return serdesafi;
	}
	/**
	 * @param serdesafi El serdesafi a establecer.
	 */
	public void setSerdesafi(String serdesafi) {
		this.serdesafi = serdesafi;
	}
	

public Afiliaciones() {
	super();
}
public void consult() throws java.sql.SQLException {
	try {
		String bef[] = {"", "","", "","", "","", "","", "","", "","", ""};
		String aft[] = {"", "","", "","", "","", "","", "","", "","", ""};
		LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", "tafidat", Table.getColumnsTafidat(), bef, aft);
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
public void delete(JQuery db, javax.servlet.http.HttpServletRequest req) throws java.sql.SQLException {
	try {
		
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - " + getClass().getName() + " -> " + e.getMessage());
	}
}

/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNameTable() {
	return "tafidat";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */

public String getUrl() {
	return JspServlet.TRANSACCIONES_SERVLET + "?BtnAfi=Buscar&TxtCodhlp=" + getTipafi();
}
public void insert(JQuery db) throws java.sql.SQLException {
	try {
		
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... insert() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public void loadGrid(JQuery db) throws java.sql.SQLException {
	setIndex(0);
	setStatus("0");
	//if (getGrid().size() == 0) {
		try {
			String query = //
			"Select count(distinct(numdoc)) " + //
			"From "+Constante.ESQUEMA1+".tafidat " + //
			"where flgafi='D' ";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				setClidesafi(db.stringValue(1));
			}
			
			query = //
			"Select count(distinct(numdoc)) " + //
			"From "+Constante.ESQUEMA1+".TAFIDAT " + //
			"where flgafi='A' ";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				setCliafi(db.stringValue(1));
			}
			
			query = //
				"Select count(distinct(tipafi)) " + //
				"From "+Constante.ESQUEMA1+".TAFIDAT t " + //
				"where flgafi='D' ";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				setSerdesafi(db.stringValue(1));
			}
			
			query = //
			"Select count(distinct(tipafi)) " + //
			"From "+Constante.ESQUEMA1+".TAFIDAT t " + //
			"where flgafi='A' ";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				setSerafi(db.stringValue(1));
			}
			
		}
		catch (Exception e) {
			throw new java.sql.SQLException("\n... loadGrid() - " + getClass().getName() + " -> " + e.getMessage());
		}
	//}
}
public void loadGridChild(JQuery db) throws java.sql.SQLException {
}
public void next(int i) {
	setStatus(((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString());
	tipafi = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
	numtar = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
	numsec = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
	typdoc = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
	numdoc = ((java.util.Vector) getGrid().elementAt(i)).elementAt(5).toString();
	fecnac = ((java.util.Vector) getGrid().elementAt(i)).elementAt(6).toString();
	sexper = ((java.util.Vector) getGrid().elementAt(i)).elementAt(7).toString();
	email = ((java.util.Vector) getGrid().elementAt(i)).elementAt(8).toString();
	coddoc = ((java.util.Vector) getGrid().elementAt(i)).elementAt(9).toString();
	codser = ((java.util.Vector) getGrid().elementAt(i)).elementAt(10).toString();
	numser = ((java.util.Vector) getGrid().elementAt(i)).elementAt(11).toString();
	desafil = ((java.util.Vector) getGrid().elementAt(i)).elementAt(12).toString();
	ctapro = ((java.util.Vector) getGrid().elementAt(i)).elementAt(13).toString();
	nomben = ((java.util.Vector) getGrid().elementAt(i)).elementAt(14).toString();
}
public void nextChild(int i) {
}
public boolean search(JQuery db) throws java.sql.SQLException {
	try {
		return true;
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... search() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */

public void sort(JQuery db) throws java.sql.SQLException {
}
public void update(JQuery db) throws java.sql.SQLException {
	try {
	
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... update() - " + getClass().getName() + " -> " + e.getMessage());
	}
}

	public String getCoddoc() {
		return coddoc;
	}


	public void setCoddoc(String coddoc) {
		this.coddoc = coddoc;
	}
	
	
	public String getCodser() {
		return codser;
	}


	public void setCodser(String codser) {
		this.codser = codser;
	}
	/**
	 * @return Devuelve ctapro.
	 */
	public String getCtapro() {
		return ctapro;
	}
	/**
	 * @param ctapro El ctapro a establecer.
	 */
	public void setCtapro(String ctapro) {
		this.ctapro = ctapro;
	}
	/**
	 * @return Devuelve desafil.
	 */
	public String getDesafil() {
		return desafil;
	}
	/**
	 * @param desafil El desafil a establecer.
	 */
	public void setDesafil(String desafil) {
		this.desafil = desafil;
	}
	/**
	 * @return Devuelve email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email El email a establecer.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return Devuelve fecnac.
	 */
	public String getFecnac() {
		return fecnac;
	}
	/**
	 * @param fecnac El fecnac a establecer.
	 */
	public void setFecnac(String fecnac) {
		this.fecnac = fecnac;
	}
	/**
	 * @return Devuelve nomben.
	 */
	public String getNomben() {
		return nomben;
	}
	/**
	 * @param nomben El nomben a establecer.
	 */
	public void setNomben(String nomben) {
		this.nomben = nomben;
	}
	/**
	 * @return Devuelve numdoc.
	 */
	public String getNumdoc() {
		return numdoc;
	}
	/**
	 * @param numdoc El numdoc a establecer.
	 */
	public void setNumdoc(String numdoc) {
		this.numdoc = numdoc;
	}
	/**
	 * @return Devuelve numsec.
	 */
	public String getNumsec() {
		return numsec;
	}
	/**
	 * @param numsec El numsec a establecer.
	 */
	public void setNumsec(String numsec) {
		this.numsec = numsec;
	}
	/**
	 * @return Devuelve numser.
	 */
	public String getNumser() {
		return numser;
	}
	/**
	 * @param numser El numser a establecer.
	 */
	public void setNumser(String numser) {
		this.numser = numser;
	}
	/**
	 * @return Devuelve numtar.
	 */
	public String getNumtar() {
		return numtar;
	}
	/**
	 * @param numtar El numtar a establecer.
	 */
	public void setNumtar(String numtar) {
		this.numtar = numtar;
	}
	/**
	 * @return Devuelve sexper.
	 */
	public String getSexper() {
		return sexper;
	}
	/**
	 * @param sexper El sexper a establecer.
	 */
	public void setSexper(String sexper) {
		this.sexper = sexper;
	}
	/**
	 * @return Devuelve tipafi.
	 */
	public String getTipafi() {
		return tipafi;
	}
	/**
	 * @param tipafi El tipafi a establecer.
	 */
	public void setTipafi(String tipafi) {
		this.tipafi = tipafi;
	}
	/**
	 * @return Devuelve typdoc.
	 */
	public String getTypdoc() {
		return typdoc;
	}
	/**
	 * @param typdoc El typdoc a establecer.
	 */
	public void setTypdoc(String typdoc) {
		this.typdoc = typdoc;
	}
	/**
	 * @return Devuelve serafi.
	 */
	public String getSerafi() {
		return serafi;
	}
	/**
	 * @param serafi El serafi a establecer.
	 */
	public void setSerafi(String serafi) {
		this.serafi = serafi;
	}
}