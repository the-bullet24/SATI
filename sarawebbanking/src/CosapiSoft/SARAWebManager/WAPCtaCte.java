package CosapiSoft.SARAWebManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.JDatabase;
import CosapiSoft.SARAWebBanking.JDatabasen;
import CosapiSoft.SARAWebBanking.JspServlet;
import CosapiSoft.SARAWebBanking.JQuery;
import CosapiSoft.SARAWebBanking.InicioSesion;
import CosapiSoft.SARAWebBanking.Mensajes;
import CosapiSoft.SARAWebBanking.CString;

public class WAPCtaCte {
	//
	private InicioSesion login;
	//
	public java.util.Vector grid = null;
	private String modulo="manager";
	/**
	 * @return Devuelve coddoc.
	 */
	public String getCoddoc() {
		return coddoc;
	}
	/**
	 * @param coddoc El coddoc a establecer.
	 */
	public void setCoddoc(String coddoc) {
		this.coddoc = coddoc;
	}
	/**
	 * @return Devuelve datpro.
	 */
	public String getDatpro() {
		return datpro;
	}
	/**
	 * @param datpro El datpro a establecer.
	 */
	public void setDatpro(String datpro) {
		this.datpro = datpro;
	}
	/**
	 * @return Devuelve empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}
	/**
	 * @param empresa El empresa a establecer.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	/**
	 * @return Devuelve estado.
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado El estado a establecer.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return Devuelve msgerror.
	 */
	public String getMsgerror() {
		return msgerror;
	}
	/**
	 * @param msgerror El msgerror a establecer.
	 */
	public void setMsgerror(String msgerror) {
		this.msgerror = msgerror;
	}
	/**
	 * @return Devuelve numcta.
	 */
	public String getNumcta() {
		return numcta;
	}
	/**
	 * @param numcta El numcta a establecer.
	 */
	public void setNumcta(String numcta) {
		this.numcta = numcta;
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
	 * @return Devuelve tipreg.
	 */
	public String getTipreg() {
		return tipreg;
	}
	/**
	 * @param tipreg El tipreg a establecer.
	 */
	public void setTipreg(String tipreg) {
		this.tipreg = tipreg;
	}
	/**
	 * @return Devuelve txnhst.
	 */
	public String getTxnhst() {
		return txnhst;
	}
	/**
	 * @param txnhst El txnhst a establecer.
	 */
	public void setTxnhst(String txnhst) {
		this.txnhst = txnhst;
	}
	/**
	 * @return Devuelve login.
	 */
	public InicioSesion getLogin() {
		return login;
	}
	/**
	 * @return Devuelve modulo.
	 */
	public String getModulo() {
		return modulo;
	}
	/**
	 * @param datpro1 El datpro1 a establecer.
	 */
	public void setDatpro1(String datpro1) {
		this.datpro1 = datpro1;
	}
	/**
	 * @param datpro2 El datpro2 a establecer.
	 */
	public void setDatpro2(String datpro2) {
		this.datpro2 = datpro2;
	}
	/**
	 * @param grid El grid a establecer.
	 */
	public void setGrid(java.util.Vector grid) {
		this.grid = grid;
	}
	private String coddoc = "";
	private String numdoc = "";
	private String numcta = "";
	private String tipreg = "";
	private String datpro1 = "";
	private String datpro2 = "";
	private String datpro="";
	private String txnhst="";
	private String estado="";
	private String empresa="";
	private String msgerror = "";
	private String typtar="";
	/**
	 * @return Devuelve typtar.
	 */
	public String getTyptar() {
		return typtar;
	}
	/**
	 * @param typtar El typtar a establecer.
	 */
	public void setTyptar(String typtar) {
		this.typtar = typtar;
	}
	
	//
		private String nameColumn = "";
		
	
/**
 * LogDeOperaciones constructor comment.
 */
		
public WAPCtaCte() {
	super();
	grid = new java.util.Vector();
}
public boolean buscar() throws Exception {
	JDatabasen jd = new JDatabasen(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		
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


public String getDatpro1() {
	CString str = new CString(datpro1);
	return str.toString().trim();
}

public String getDatpro2() {
	CString str = new CString(datpro2);
	return str.toString().trim();
}

private String getQueryEstado() {
	String query="";
	if (getEstado().equals("0") || getEstado().equals("1"))
		query = " And flgerror = '" + getEstado() + "' ";
	else
		query = " And flgerror in ('0','1') ";
	return query;
}

public java.util.Vector getGrid() {
	if (grid == null)
		grid = new java.util.Vector(100,100);
	return grid;
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
		str1 = "TO_DATE(DATPRO,'dd/mm/rrrr') >= TO_DATE('"+ getDatpro1()+ "', 'dd/mm/rrrr') ";
	String str2 = "";
	if (!getDatpro2().equals(""))
		str2 = "TO_DATE(DATPRO,'dd/mm/rrrr') <= TO_DATE('"+ getDatpro2()+ "', 'dd/mm/rrrr') ";
	String str = "";
	if (!str1.equals("") && !str2.equals(""))
		str = " And (" + str1 + " and " + str2 + ") ";
	else {
		if (!str1.equals("")) {
			str = " And " + str1;
		} else {
			str = " And " + str2;
		}
	}
	return str;
}

private String getQueryDoc() {
	if (getNumdoc().equals(""))
		return "";
	String query = "And numdoc = '" + getNumdoc() + "'";
	return query;
}

private String getQueryNroCta() {
	if (getNumcta().toUpperCase().equals(""))
		return "";
	String query = " And (nrocta = '" + getNumcta() + "') ";
	return query;
}

public String getUrlLogDeOperaciones() {
	if (modulo.toUpperCase().equals("MANAGER"))
		return JspServlet.DIARIO_ELECTRONICO_SERVLET + "?BtnDia=Buscar&Modulo=Manager&ChkLog=";
	else
		return JspServlet.DIARIO_ELECTRONICO_SERVLET + "?BtnDia=Buscar&Modulo=Manager&ChkLog=";
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
		String query = //
		"Select numdoc,coddoc,to_char(datpro,'DD/MM/YYYY HH24:MI:SS'),flgcom,nrocta,codtrahst,msghst " + //
		"From "+Constante.ESQUEMA2+".HJOUTRA " + //
		"Where codtrahst in ('0100') and flgcom in ('2','3') "+ //
		getQueryFecha() + //
		getQueryNroCta() + //
		getQueryDoc() + //
		getQueryEstado() + //
		"Order by datpro desc";
		System.out.println(query);
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
			getGrid().addElement(row);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new Exception("\nLoadGrid - Diario Electrónico \n" + e.getMessage());
	}
}

public void next(int i) {
	numdoc = ((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString();
	coddoc = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
	if(coddoc.equals("0001")) coddoc="DNI";
	datpro = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
	empresa = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
	if(empresa.equals("1")) empresa="ATI";
	else if(empresa.equals("2")) empresa="Telefónica";
	else if(empresa.equals("3")) empresa="Claro";
	numcta = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
	txnhst = ((java.util.Vector) getGrid().elementAt(i)).elementAt(5).toString();
	msgerror = ((java.util.Vector) getGrid().elementAt(i)).elementAt(6).toString();
	if(msgerror.equals("") || msgerror==null) msgerror="Satisfactorio";
	else  msgerror="Fallido";
	
}

private boolean buscar(JQuery db) throws Exception {
	try {
		return true;
	} 
	catch (Exception e) {
		e.printStackTrace();
		throw new Exception("\nBuscar - Log de Operaciones \n" + e.getMessage());
	}
}


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

}


