package CosapiSoft.SARAWebManager;


import java.util.Iterator;

import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.JDatabase;
import CosapiSoft.SARAWebBanking.JDatabasen;
import CosapiSoft.SARAWebBanking.JspServlet;
import CosapiSoft.SARAWebBanking.JQuery;
import CosapiSoft.SARAWebBanking.InicioSesion;
import CosapiSoft.SARAWebBanking.Mensajes;
import CosapiSoft.SARAWebBanking.CString;

public class ReporteEstadistico {
	//
	private InicioSesion login;
	//
	public java.util.Vector grid = null;
	public java.util.Vector afi = null;
	public java.util.Vector transacciones = null;
	public java.util.Vector tiposdoc = null;
	public java.util.Vector tipdoc = null;
	public java.util.Vector detalle = null;
	public String txnhst[]=null;
	
	public java.util.Vector monedas = null;
	//
	private String modulo="manager";
	private String desser = "";
	private String numlog = "";
	private String datpro = "";
	private String datpro1 = "";
	private String datpro2 = "";
	private String datbeg = "";
	private String datend = "";
	private String coddoc = "";
	private String typper = "";
	private String horpro = "";
	private String horpro1 = "";
	private String horpro2 = "";
	private String numdoc = "";
	private String cipadr = "";
	private String codtra = "";
	private String codtrahst = "";
	private String tipprdsrc = "";
	private String numprdsrc = "";
	private String tipprdtar = "";
	private String numprdtar = "";
	private String amotra = "";
	private String codcur = "";
	private String txtcur = "";
	private String numref = "";
	private String namtxn = "";
	private String nrotxn = "";
	private String txttd = "";
	//
	private String codent = "";
	private String msghst = "";
	private String idetrapro = "";
	private String idetracom = "";
	private String error = "";
	private String amotxn = "";
	private String baltra = "";
	private String msgerror = "";
	//
		private String nameColumn = "";
		
	private class Moneda{
		String code;
		String description;
		
		public String getCode() {
			return code;
		}
		
		public void setCode(String code) {
			this.code = code;
		}
		
		public String getDescription() {
			return description;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
	}
	private class TipoDoc{
		String code;
		String description;
		
		public String getCode() {
			return code;
		}
		
		public void setCode(String code) {
			this.code = code;
		}
		
		public String getDescription() {
			return description;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
	}
/**
 * LogDeOperaciones constructor comment.
 */
		
public ReporteEstadistico() {
	super();
	grid = new java.util.Vector();
	transacciones = new java.util.Vector();
	detalle = new java.util.Vector();
}
public boolean buscar() throws Exception {
	JDatabasen jd = new JDatabasen(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		boolean sw = buscar(db);
		return sw;
	} catch (Exception e) {
		throw new Exception("\nBuscar - Log de Operaciones \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}

public void clearGrid() {
	grid = new java.util.Vector(1);
}

public String getNumlog() {
	return numlog;
}

public String getTypper() {
	return typper;
}
public String getTxttd() {
	return txttd;
}
public String getTxtcur() {
	return txtcur;
}

public String getCoddoc() {
	return coddoc;
}

public String getNumdoc() {
	return numdoc;
}

public String getCipadr() {
	return cipadr;
}

public String getCodtra() {
	return codtra;
}

public String getDatpro() {
	return datpro.trim();
}

public String getDatpro1() {
	CString str = new CString(datpro1);
	str.deleteAll('/');
	return str.toString().trim();
}

public String getDatpro2() {
	CString str = new CString(datpro2);
	str.deleteAll('/');
	return str.toString().trim();
}

public String getError() {
	return error;
}

public java.util.Vector getGrid() {
	if (grid == null)
		grid = new java.util.Vector(100,100);
	return grid;
}

public String[] getTxnhst() {
	return txnhst;
}


public String getHorpro() {
	return horpro.trim();
}

public String getHorpro1() {
	return horpro1.trim();
}

public String getHorpro2() {
	return horpro2.trim();
}

public InicioSesion getLogin() {
	return this.login;
}

public String getCodtrahst() {
	return codtrahst;
}
public String getTipprdsrc() {
	return tipprdsrc;
}
public String getNumprdsrc() {
	return numprdsrc;
}
public String getTipprdtar() {
	return tipprdtar;
}
public String getNumprdtar() {
	return numprdtar;
}

public String getAmotra() {
	return amotra;
}

public String getCodcur() {
	return codcur;
}

public String getNumref() {
	return numref;
}

public String getCodent() {
	return codent;
}
public String getMsghst() {
	return msghst;
}
public String getIdetrapro() {
	return idetrapro;
}
public String getIdetracom() {
	return idetracom;
}
public String getAmotxn() {
	return amotxn;
}
public String getBaltra() {
	return baltra;
}

public String getMsgError() {
	return msgerror;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNameColumn() {
	return nameColumn.toUpperCase();
}

private String getQueryFecha() {
	if (getDatpro1().toUpperCase().equals("DDMMYYYY") && getDatpro2().toUpperCase().equals("DDMMYYYY"))
		return "";
	if (getDatpro1().toUpperCase().equals("00000000") && getDatpro2().toUpperCase().equals("00000000"))
		return "";
	if (getDatpro1().equals("") && getDatpro2().equals(""))
		return "";
	String str1 = "";
	if (!getDatpro1().equals(""))
		str1 = "datpro >= '" + getDatpro1() + "' ";
	String str2 = "";
	if (!getDatpro2().equals(""))
		str2 = "datpro <= '" + getDatpro2() + "' ";
	String str = "";
	if (!str1.equals("") && !str2.equals(""))
		str = "And (" + str1 + " and " + str2 + ") ";
	else {
		if (!str1.equals("")) {
			str = "And " + str1;
		} else {
			str = "And " + str2;
		}
	}
	return str;
}

private String getQueryFech() {
	if (getDatpro().toUpperCase().equals("DDMMYYYY"))
		return "";
	if (getDatpro().toUpperCase().equals("00000000"))
		return "";
	if (getDatpro().equals(""))
		return "";
	String str1 = "";
	if (!getDatpro().equals(""))
		str1 = "datpro = '" + getDatpro() + "' ";
	
	String str = "";
	if (!str1.equals(""))
		str = "And " + str1;
	return str;
}

private String getQueryHora() {
	if (getHorpro1().equals("00:00") && getHorpro2().equals("00:00"))
		return "";
	if (getHorpro1().equals("") && getHorpro2().equals(""))
		return "";
	String str1 = "";
	if (!getHorpro1().equals(""))
		str1 = "horpro >= '" + getHorpro1() + "' ";
	String str2 = "";
	if (!getHorpro2().equals(""))
		str2 = "horpro <= '" + getHorpro2() + "' ";
	String str = "";
	if (!str1.equals("") && !str2.equals(""))
		str = "And (" + str1 + " and " + str2 + ") ";
	else {
		if (!str1.equals("")) {
			str = "And " + str1;
		} else {
			str = "And " + str2;
		}
	}
	return str;
}

private String getQueryNroRef() {
	if (getNumref().toUpperCase().equals(""))
		return "";
	String query = "And numref = '" + getNumref() + "' ";
	return query;
}

private String getQueryDoc() {
	if (getNumdoc().equals(""))
		return "";
	String query = "And numdoc = '" + getNumdoc() + "' And coddoc = '" + getCoddoc() + "' ";
	return query;
}

private String getQueryNroCta() {
	if (getNumprdsrc().toUpperCase().equals(""))
		return "";
	String query = "And (numprdsrc = '" + getNumprdsrc() + "' or numprdtar = '" + getNumprdsrc() + "') ";
	return query;
}

private String getQueryTxn() {
	String query1="and codtrahst in (";
	String query2="";
	String query=""; 
	if (txnhst == null || txnhst[0].toString().equals("Todos"))
		return "";
	for (int p = 0; p < txnhst.length; ++p)
    {
      String testParam = txnhst[p];
      if(p+1==txnhst.length)
      query2 = query2 + " '" + testParam.substring(0,4).toString() + "') ";
      else
      	query2 = query2 + " '" + testParam.substring(0,4).toString() + "', ";
    }
	query=query1+query2;
	return query;
}

public String getUrlLogDeOperaciones() {
	if (modulo.toUpperCase().equals("MANAGER"))
		return JspServlet.DIARIO_ELECTRONICO_SERVLET + "?BtnDia=Buscar&Modulo=Manager&ChkLog=";
	else
		return JspServlet.DIARIO_ELECTRONICO_SERVLET + "?BtnDia=Buscar&Modulo=Manager&ChkLog=";
}

public String getNamtxn() {
	return namtxn.toUpperCase();
}

public String getNrotxn() {
	return nrotxn.toUpperCase();
}

public boolean goTo(String pos) throws Exception {
	try {
		if (getGrid() == null || getGrid().size() == 0) {
			error = Mensajes.getMessage(Mensajes.LOG_DE_OPERACIONES_VACIA);
			return false;
		}
		if (pos == null || pos.equals("")) {
			error = Mensajes.getMessage(Mensajes.NUMERO_DE_SECUENCIA_NO_ENCONTRADO);
			return false;
		}
		int index = new Integer(pos.trim()).intValue();
		if (index < 0) {
			error = Mensajes.getMessage(Mensajes.DEBE_SELECCIONAR_NUMERO_DE_SECUENCIA);
			return false;
		}
		if (index >= getGrid().size()) {
			//String msg = Mensajes.getMessage(Mensajes.NUMERO_DE_SECUENCIA_MAYOR_NRO_DE_REGISTROS);
			//int p = msg.indexOf('&');
			//error = msg.substring(0, p) + index + msg.substring(p + 1);
			return false;
		}
		next(index);
		return true;
	} catch (Exception e) {
		throw new Exception(" \n Log de Operaciones -> goTo(" + pos + ")  \n" + e.getMessage());
	}
}
public void loadComboTransacciones() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		tiposdoc = new java.util.Vector();
		monedas = new java.util.Vector();
		tipdoc = new java.util.Vector();
		String query = //
		"Select codhlp, numseq, txthlp, txthlpdat " + //
		"From "+Constante.ESQUEMA1+".THLPDET " + //
		"Where codhlp = '00004' " + //
		"Order by txthlpdat";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			TipoDoc td = new TipoDoc();
			java.util.Vector row = new java.util.Vector();
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			row.addElement(db.stringValue(3));
			row.addElement(db.stringValue(4));
			tiposdoc.addElement(row);
			td.setCode(db.stringValue(4));
			td.setDescription(db.stringValue(3));
			tipdoc.addElement(td);
			
		}
		query = //
			"Select codcur, txtcurlon " + //
			"From "+Constante.ESQUEMA1+".TCURDAT ";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				Moneda nom = new Moneda(); 
				nom.setCode(db.stringValue(1));
				nom.setDescription(db.stringValue(2));
				monedas.addElement(nom);
			}
	} catch (Exception e) {
		throw new Exception("\nLoadComboUsr() - Log de Operaciones \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public void AfiporCliente() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		afi = new java.util.Vector();
		String query = //
		"Select numtar, desafil, to_char(fecnac,'dd/mm/rrrr'), to_char(fecnac,'HH:mm') " + //
		"From "+Constante.ESQUEMA1+".TAFIDAT " + //
		"Where numtar = '" + getNumprdsrc().toUpperCase() + "' ";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector();
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			row.addElement(db.stringValue(3));
			row.addElement(db.stringValue(4));
			afi.addElement(row);
				
		}
		// System.out.println("size de afili-----"+afi.size());
	} catch (Exception e) {
		throw new Exception("\nLoadComboUsr() - Log de Operaciones \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public void loadDetalle() throws Exception {
	try {
		
	} catch (Exception e) {
		throw new Exception("\nloadDetalle() - Log de Operaciones \n" + e.getMessage());
	}
}
public void loadGrid() throws Exception {
	if (grid == null || grid.size() == 0) {
		JDatabasen jd = new JDatabasen(login.driver, login.url, login.userid, login.password);
		JQuery db = new JQuery(jd.getConnection());
		try {
			loadGrid(db);
		}
		catch (Exception e) {
			throw new Exception("\nLoadGrid - Diario Electrónico \n" + e.getMessage());
		}
		finally {
			db.close();
			jd.close();
		}
	}
}
private void loadGrid(JQuery db) throws Exception {
	try {
		getGrid().clear();
		// System.out.println("entro al load grid");
		String query = //
		"Select numlog,coddoc,to_char(datpro,'dd/mm/rrrr'),horpro,numdoc,typper,cipadr,codtra,codtrahst,tipprdsrc,numprdsrc,tipprdtar,numprdtar,amotra,codcur,numref,codent,msghst,idetracom,amotxn,baltra,msgerror " + //
		"From "+Constante.ESQUEMA2+".HJOUTRA " + //
		"Where codtra is not null " + //
		getQueryTxn() + //
		getQueryFecha() + //
		getQueryNroCta() + //
		getQueryFech() + //
		getQueryNroRef() + //
		getQueryDoc() + //
		"Order by numlog asc, codtra, datpro desc, horpro desc";
		// System.out.println(query);
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector();
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			row.addElement(db.stringValue(3));
			row.addElement(db.stringValue(4));
			row.addElement(db.stringValue(5));
			row.addElement(db.stringValue(6));
			row.addElement(db.stringValue(7));
			row.addElement(db.stringValue(8));
			row.addElement(db.stringValue(9));
			row.addElement(db.stringValue(10));
			row.addElement(db.stringValue(11));
			row.addElement(db.stringValue(12));
			row.addElement(db.stringValue(13));
			row.addElement(db.stringValue(14));
			row.addElement(db.stringValue(15));
			row.addElement(db.stringValue(16));
			row.addElement(db.stringValue(17));
			row.addElement(db.stringValue(18));
			row.addElement(db.stringValue(19));
			row.addElement(db.stringValue(20));
			row.addElement(db.stringValue(21));
			row.addElement(db.stringValue(22));
			getGrid().addElement(row);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new Exception("\nLoadGrid - Diario Electrónico \n" + e.getMessage());
	}
}
private String getTxtmoneda(String codCur){
	String txtCur1 = null;
	for (Iterator iter = monedas.iterator(); iter.hasNext();) {
		Moneda mon = (Moneda) iter.next();
		if(mon.getCode().equals(codCur)){
			return mon.getDescription();
		}
	}
	return txtCur1; 
}

private String getTxttipodoc(String codtip){
	String txttipo1 = null;
	for (Iterator iter = tipdoc.iterator(); iter.hasNext();) {
		TipoDoc td = (TipoDoc) iter.next();
		if(td.getCode().equals(codtip)){
			return td.getDescription();
		}
	}
	return txttipo1; 
}

public void next(int i) {
	numlog = ((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString();
	coddoc = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
	datpro = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
	horpro = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
}

public void nextafi(int i) {
	numprdsrc = ((java.util.Vector) getAfi().elementAt(i)).elementAt(0).toString();
	desser = ((java.util.Vector) getAfi().elementAt(i)).elementAt(1).toString();
	datpro = ((java.util.Vector) getAfi().elementAt(i)).elementAt(2).toString();
	horpro = ((java.util.Vector) getAfi().elementAt(i)).elementAt(3).toString();
}

private boolean buscar(JQuery db) throws Exception {
	try {
		String query = //
		"Select numprdsrc,codtrahst,to_char(datpro,'dd/mm/rrrr'),horpro " + //
		"From "+Constante.ESQUEMA2+".HJOUTRA " + //
		"Where numprdsrc = '" + getNumprdsrc().toUpperCase() + "' " + //
		"and codtrahst in ('9150','9100') ";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector();
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			row.addElement(db.stringValue(3));
			row.addElement(db.stringValue(4));
			getGrid().addElement(row);
		}
		
	
		return true;
	} 
	catch (Exception e) {
		e.printStackTrace();
		throw new Exception("\nBuscar - Log de Operaciones \n" + e.getMessage());
	}
}

public void txnclientes(int j) throws Exception {
	numprdsrc = ((java.util.Vector) getGrid().elementAt(j)).elementAt(0).toString();
	codtrahst = ((java.util.Vector) getGrid().elementAt(j)).elementAt(1).toString();
	datpro = ((java.util.Vector) getGrid().elementAt(j)).elementAt(2).toString();
	
	horpro = ((java.util.Vector) getGrid().elementAt(j)).elementAt(3).toString();
	if(codtrahst.equals("9150")) codtrahst="Generación de clave";
	if(codtrahst.equals("9100")) codtrahst="Afiliación";
	
}
/*public void nextDetalle(int i) {
	nameColumn = ((java.util.Vector) detalle.elementAt(i)).elementAt(0).toString();
	campoBef = ((java.util.Vector) detalle.elementAt(i)).elementAt(1).toString();
	campoAft = ((java.util.Vector) detalle.elementAt(i)).elementAt(2).toString();
	campoBef = (campoBef.equals("")) ? "Sin Dato" : campoBef;
	campoAft = (campoAft.equals("")) ? "Sin Dato" : campoAft;
}*/
public void nextTxn(int i) {
	namtxn = ((java.util.Vector) tiposdoc.elementAt(i)).elementAt(2).toString();
	nrotxn = ((java.util.Vector) tiposdoc.elementAt(i)).elementAt(3).toString();
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
/*public void setAftrcd(String newValue) {
	this.aftrcd = newValue;
}

public void setBfrrcd(String newValue) {
	this.bfrrcd = newValue;
}

public void setCampoAft(String newValue) {
	this.campoAft = newValue;
}

public void setCampoBef(String newValue) {
	this.campoBef = newValue;
}*/
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodtra(String newValue) {
	if (newValue == null)
		newValue = "";
	this.codtra = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setDatpro(String newValue) {
	this.datpro = newValue;
}

public void setTxttd(String newValue) {
	this.txttd = newValue;
}

public void setCodtrahst(String newValue) {
	if (newValue == null)
		newValue = "";
	this.codtrahst = newValue;
}

public void setTipprdsrc(String newValue) {
	this.tipprdsrc = newValue;
}

public void setNumprdsrc(String newValue) {
	this.numprdsrc = newValue;
}
public void setTipprdtar(String newValue) {
	this.tipprdtar = newValue;
}

public void setNumprdtar(String newValue) {
	this.numprdtar = newValue;
}
public void setAmotra(String newValue) {
	this.amotra = newValue;
}
public void setCodcur(String newValue) {
	this.codcur = newValue;
}
public void setNumref(String newValue) {
	this.numref = newValue;
}
public void setCodent(String newValue) {
	this.codcur = newValue;
}
public void setMsghst(String newValue) {
	this.msghst = newValue;
}
public void setIdetrapro(String newValue) {
	this.idetrapro = newValue;
}

public void setIdetracom(String newValue) {
	this.idetracom = newValue;
}
public void setAmotxn(String newValue) {
	this.amotxn = newValue;
}
public void setBaltra(String newValue) {
	this.baltra = newValue;
}
public void setMsgerror(String newValue) {
	this.msgerror = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setDatpro1(String newValue) {
	this.datpro1 = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setDatpro2(String newValue) {
	this.datpro2 = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setError(String newValue) {
	this.error = newValue;
}
public void setTxtcur(String newValue) {
	this.txtcur = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.util.Vector
 */
public void setGrid(java.util.Vector newValue) {
	this.grid = newValue;
}
public void setTxnhst(String[] newValue) {
	this.txnhst = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setHorpro(String newValue) {
	this.horpro = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setHorpro1(String newValue) {
	this.horpro1 = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setHorpro2(String newValue) {
	this.horpro2 = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue CosapiSoft.SARAWebBanking.InicioSesion
 */
public void setLogin(InicioSesion newValue) {
	this.login = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setModulo(String newValue) {
	this.modulo = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setNameColumn(String newValue) {
	this.nameColumn = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setNumdoc(String newValue) {
	this.numdoc = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCoddoc(String newValue) {
	this.coddoc = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setNumlog(String newValue) {
	this.numlog = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setNamtxn(String newValue) {
	this.namtxn = newValue;
}

public void setNrotxn(String newValue) {
	this.nrotxn = newValue;
}

public void setTypper(String newValue) {
	this.typper = newValue;
}
	/**
	 * @return Devuelve afi.
	 */
	public java.util.Vector getAfi() {
		return afi;
	}
	/**
	 * @param afi El afi a establecer.
	 */
	public void setAfi(java.util.Vector afi) {
		this.afi = afi;
	}
	/**
	 * @return Devuelve desser.
	 */
	public String getDesser() {
		return desser;
	}
	/**
	 * @param desser El desser a establecer.
	 */
	public void setDesser(String desser) {
		this.desser = desser;
	}
}

