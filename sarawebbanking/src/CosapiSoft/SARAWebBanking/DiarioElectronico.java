package CosapiSoft.SARAWebBanking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.sql.Clob;
import java.util.Iterator;

import com.ibm.math.BigDecimal;

import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteSesion;

import CosapiSoft.SARAWebManager.Pagos;

/**
 * This type was created in VisualAge.
 */
public class DiarioElectronico implements Serializable {
	//
	private InicioSesion login;
	//
	public java.util.Vector grid = null;
	public java.util.Vector transacciones = null;
	public java.util.Vector detalle = null;
	
	public java.util.Vector monedas = null;
	//
	private String modulo="manager";
	private String numlogabrev = "";
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
	private Object refrendo=null;
	private String refrendito = "";
	//
	private String codent = "";
	private String msghst = "";
	private String idetrapro = "";
	private String idetracom = "";
	private String error = "";
	private String amotxn = "";
	private String baltra = "";
	private String msgerror = "";
	private String nroCuenta = "";
	private String flgerror = "";
	private String numope = "";
	
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
/**
 * LogDeOperaciones constructor comment.
 */
		
public DiarioElectronico() {
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
private boolean buscar(JQuery db) throws Exception {
	try {
		String query = "";
		if(Constante.FLG_TABLE==null){
			query = //
				"Select numlog,coddoc,datpro,horpro,numdoc,typper,cipadr,codtra,codtrahst,tipprdsrc,numprdsrc,tipprdtar,numprdtar,amotra,codcur,numref,codent,msghst,idetracom,amotxn,baltra,msgerror,clobcons,nrocta,numope,flgerror " + //
				"From "+Constante.ESQUEMA2+".TJOUTRA " + //
				"Where codtra = '" + getCodtra().toUpperCase() + "' " + //
				"And typper = '" + getTypper().toUpperCase() + "' " + //
				"And datpro = '" + getDatpro().toUpperCase() + "' " + //
				"And (clobcons is not null or msghst is not null) " + //
				"Order by datpro, horpro, typper";	
		}else{
			query = //
				"Select numlog,coddoc,datpro,horpro,numdoc,typper,cipadr,codtra,codtrahst,tipprdsrc,numprdsrc,tipprdtar,numprdtar,amotra,codcur,numref,codent,msghst,idetracom,amotxn,baltra,msgerror,clobcons,nrocta,numope,flgerror " + //
				"From "+Constante.ESQUEMA2+".HJOUTRA " + //
				"Where codtra = '" + getCodtra().toUpperCase() + "' " + //
				"And typper = '" + getTypper().toUpperCase() + "' " + //
				"And datpro = '" + getDatpro().toUpperCase() + "' " + //
				"And (clobcons is not null or msghst is not null) " + //
				"Order by datpro, horpro, typper";
		}
		
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next()) {
			error = Mensajes.getMessage(Mensajes.NO_EXISTE_REGISTROS);
			return false;
		}
		numlog = db.stringValue(1);
		coddoc = db.stringValue(2);
		datpro = db.stringValue(3);
		horpro = db.stringValue(4);
		numdoc = db.stringValue(5);
		typper = db.stringValue(6);
		cipadr = db.stringValue(7);
		codtra = db.stringValue(8);
		codtrahst = db.stringValue(9);
		tipprdsrc = db.stringValue(10);
		numprdsrc = db.stringValue(11);
		tipprdtar = db.stringValue(12);
		numprdtar = db.stringValue(13);
		amotra = db.stringValue(14);
		codcur = db.stringValue(15);
		numref = db.stringValue(16);
		codent = db.stringValue(17);
		msghst = db.stringValue(18);
		idetracom = db.stringValue(19);
		amotxn = db.stringValue(20);
		baltra = db.stringValue(21);
		msgerror = db.stringValue(22);
		refrendo = db.getObjectValue(23);
		nroCuenta = db.stringValue(24);
		numope = db.stringValue(25);
		flgerror = db.stringValue(26);
		refrendito= clobToString(refrendo);
		return true;
	} catch (Exception e) {
		throw new Exception("\nBuscar - Log de Operaciones \n" + e.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 */
public void clearGrid() {
	grid = new java.util.Vector(1);
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNumlog() {
	return numlog;
}
public String getTypper() {
	return typper;
}
public String getTxtcur() {
	return txtcur;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCoddoc() {
	return coddoc;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNumdoc() {
	return numdoc;
}

/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCipadr() {
	return cipadr;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodtra() {
	return codtra;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getDatpro() {
	return datpro.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getDatpro1() {
	return datpro1.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getDatpro2() {
	return datpro2.trim();
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
	if (grid == null)
		grid = new java.util.Vector(100,100);
	return grid;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getHorpro() {
	return horpro.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getHorpro1() {
	return horpro1.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getHorpro2() {
	return horpro2.trim();
}
/**
 * This method was created in VisualAge.
 */
public InicioSesion getLogin() {
	return this.login;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
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
private String getQueryFecha1() {
	if (getDatpro1().equals("") && getDatpro2().equals(""))
		return "";
	String str1 = "";
	if (!getDatpro1().equals(""))
		str1 = "datpro  = '" + getDatpro1() + "' ";
	String str2 = "";
	if (!getDatpro2().equals(""))
		str2 = "datpro = '" + getDatpro2() + "' ";
	String str = "";
	if (!str1.equals("") && !str2.equals(""))
		str = "And datpro  >= '" + getDatpro1() + "' and datpro <= '" + getDatpro2() + "' ";
	else {
		if (!str1.equals("")) {
			str = "And " + str1;
		} else {
			str = "And " + str2;
		}
	}
	return str;
	
	
	
}
private String getQueryFecha() {
	String str1 = "";
	if (!getDatpro1().equals(""))
		str1 = "TO_DATE(DATPRO,'dd/mm/rrrr') >= TO_DATE('"+ getDatpro1()+ "', 'dd/mm/rrrr') ";
	String str2 = "";
	if (!getDatpro2().equals(""))
		str2 = "TO_DATE(DATPRO,'dd/mm/rrrr') <= TO_DATE('"+ getDatpro2()+ "', 'dd/mm/rrrr') ";
	String str = "";
	if (!str1.equals("") && !str2.equals(""))
		str = "And (" + str1 + " and " + str2 + ") ";
	else {
		if (!str1.equals("")) {
			str = "And " + str1;
		} else if (!str2.equals("")) {
			str = "And " + str2;
			
		}
		else{
			return "";
		}
	}
	return str;
}
public void Refrendo() throws Exception {
	JDatabasen jd = new JDatabasen(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	
	ConstanteSesion.MAIL_SUBJECT = "Banco de la Nación - Duplicado de Constancia de Pago";
	
	String[] arregloCodigo = new String[18];
    arregloCodigo[0] = "1162";
    arregloCodigo[1] = "1125";
    arregloCodigo[2] = "8125";
    arregloCodigo[3] = "1100";
    arregloCodigo[4] = "8100";
    arregloCodigo[5] = "1923";
    arregloCodigo[6] = "8923";
    arregloCodigo[7] = "0125";
    arregloCodigo[8] = "6125";
    arregloCodigo[9] = "0100";
    arregloCodigo[10] = "6100";
    arregloCodigo[11] = "0124";
    arregloCodigo[12] = "6124";
    arregloCodigo[13] = "2510";
    arregloCodigo[14] = "2610";
    arregloCodigo[15] = "2523";
    arregloCodigo[16] = "2623";
    arregloCodigo[17] = "3035";
    
    for (int i = 0; i < arregloCodigo.length; i ++) {
        if  (getCodtrahst().equals(arregloCodigo[i])){
            ConstanteSesion.MAIL_SUBJECT = "Banco de la Nación - Duplicado de Constancia de Consulta";
        }
    }
	
	try {
		String query = "";
		if(Constante.FLG_TABLE==null){
			query = // 
				"Select clobcons " + //
				"From "+Constante.ESQUEMA2+".TJOUTRA " + //
				"Where numlog ='" + getNumlog() + "'";	
		}else{
			query = // 
				"Select clobcons " + //
				"From "+Constante.ESQUEMA2+".HJOUTRA " + //
				"Where numlog ='" + getNumlog() + "'";
		}
		System.out.println("query refrendo................="+query);
		db.setQuery(query);
		db.executeQuery();
		
		if(db.getResultSet().next()) {
			refrendo=((Object) db.getObjectValue(1));
		}
		refrendito= clobToString(refrendo);
	}
	catch (Exception e) {
		//log.error("",e);
		throw new Exception("\nLoadComboUsr() - Log de Operaciones \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public String clobToString(Object obj){
	if (obj==null) return null;
	BufferedReader bf=null;
	StringBuffer out = new StringBuffer();
	try {
		bf = new BufferedReader(new InputStreamReader( ( (Clob)
		obj).getAsciiStream()));

		String line;
		while ( (line = bf.readLine()) != null) {
		out.append(line);
		//out.append("\\n\\");
		}
		bf.close();
		}
		catch (Exception ex) {
		System.out.println(""+ex);
		}
		
	return out.toString();
}
private String getQueryHora() {
	if (getHorpro1().equals("") && getHorpro2().equals(""))
		return "";
	String str1 = "";
	if (!getHorpro1().equals(""))
		str1 = "horpro = '" + getHorpro1() + "' ";
	String str2 = "";
	if (!getHorpro2().equals(""))
		str2 = "horpro = '" + getHorpro2() + "' ";
	String str = "";
	if (!str1.equals("") && !str2.equals(""))
		str = "And horpro >= '" + getHorpro1() + "' and horpro <= '" + getHorpro2() + "' ";
	
	else {
		if (!str1.equals("")) {
			str = "And " + str1;
		} else {
			str = "And " + str2;
		}
	}
	 return str;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
private String getQueryTyp() {
	if (getTypper().toUpperCase().equals("TODOS"))
		return "";
	String query = "And typper = '" + getTypper().toUpperCase() + "' ";
	return query;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
private String getQueryTxn() {
	if (codtra.toUpperCase().equals("TODOS"))
		return "";
	CString str = new CString(getCodtra());
	String query = "And codtrahst = '" + str.firstNChars(4).toString() + "' ";
	return query;
}

/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */


public String getUrlLogDeOperaciones() {
	if (modulo.toUpperCase().equals("MANAGER"))
		return JspServlet.DIARIO_ELECTRONICO_SERVLET + "?BtnDia=Buscar&Modulo=Manager&ChkLog=";
	else
		return JspServlet.DIARIO_ELECTRONICO_SERVLET + "?BtnDia=Buscar&Modulo=Manager&ChkLog=";
}

public String getNamtxn() {
	return namtxn.toUpperCase();
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
		transacciones = new java.util.Vector();
		monedas = new java.util.Vector();
		String query = //
		"Select codtra, txttra " + //
		"From "+Constante.ESQUEMA1+".ttradat " + //
		"Order by txttra";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector();
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			transacciones.addElement(row);
		}
		query = //
			"Select txthlpdat,txthlp " + //
			"From "+Constante.ESQUEMA1+".THLPDET " + //
			"Where codhlp = '09041' " + //
			"Order by txthlpdat";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				Moneda nom = new Moneda(); 
				nom.setCode(db.stringValue(1));
				nom.setDescription(db.stringValue(2));
				monedas.addElement(nom);
			}
			//System.out.println("nro de filas monedas......"+monedas.size());
			//log.debug("nro de filas monedas......"+monedas.size());
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
			if(Constante.FLG_TABLE==null){
				System.out.println("Entro al LOADGRID");
				loadGrid(db);	
			}else{
				System.out.println("Entro al LOADGRIDHISTORICO");
				loadGridHistorico(db);
			}
			
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
	    
	    CString strCodTran = new CString(getCodtra());
	    String query = "";
	    
	    if (strCodTran.equals("0112")){
			query = //
				"Select numlog,coddoc,to_char(datpro,'dd/mm/rrrr'),horpro,numdoc,typper,cipadr,codtra,codtrahst,tipprdsrc,numprdsrc,tipprdtar,numprdtar,amotra,codcur,numref,codent,msghst,idetracom,amotxn,baltra,msgerror,clobcons,nrocta,numope,flgerror  " + //
				"From "+Constante.ESQUEMA2+".TJOUTRA " + //
				"Where codtra is not null " + //
				getQueryTxn() + //
				//getQueryFecha() + //
				getQueryHora() + //
				getQueryTyp() + //
				"Order by  datpro desc, horpro desc, numlog asc, codtra";
	    } else {
			query = //
				"Select numlog,coddoc,to_char(datpro,'dd/mm/rrrr'),horpro,numdoc,typper,cipadr,codtra,codtrahst,tipprdsrc,numprdsrc,tipprdtar,numprdtar,amotra,codcur,numref,codent,msghst,idetracom,amotxn,baltra,msgerror,clobcons,nrocta,numope,flgerror  " + //
				"From "+Constante.ESQUEMA2+".TJOUTRA " + //
				"Where codtra is not null " + //
				"And (clobcons is not null or msghst is not null) " + //
				getQueryTxn() + //
				//getQueryFecha() + //
				getQueryHora() + //
				getQueryTyp() + //
				"Order by  datpro desc, horpro desc, numlog asc, codtra";
	    }
	    
		//System.out.println(query);
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
			row.addElement(db.getObjectValue(23));
			row.addElement(db.stringValue(24));
			row.addElement(db.stringValue(25));
			row.addElement(db.stringValue(26));
			getGrid().addElement(row);
		}
	} catch (Exception e) {
		throw new Exception("\nLoadGrid - Diario Electrónico \n" + e.getMessage());
	}
}

private void loadGridHistorico(JQuery db) throws Exception {
	try {
	    CString strCodTran = new CString(getCodtra());
	    String query = "";
	    
	    if (strCodTran.equals("0112")){
	        query = //
	    		"Select numlog,coddoc,to_char(datpro,'dd/mm/rrrr'),horpro,numdoc,typper,cipadr,codtra,codtrahst,tipprdsrc,numprdsrc,tipprdtar,numprdtar,amotra,codcur,numref,codent,msghst,idetracom,amotxn,baltra,msgerror,clobcons,nrocta,numope,flgerror  " + //
	    		"From "+Constante.ESQUEMA2+".HJOUTRA " + //
	    		"Where codtra is not null " + //
	    		getQueryTxn() + //
	    		getQueryFecha() + //
	    		getQueryHora() + //
	    		getQueryTyp() + //
	    		"Order by  datpro desc, horpro desc, numlog asc, codtra";
	    } else {
	        query = //
	    		"Select numlog,coddoc,to_char(datpro,'dd/mm/rrrr'),horpro,numdoc,typper,cipadr,codtra,codtrahst,tipprdsrc,numprdsrc,tipprdtar,numprdtar,amotra,codcur,numref,codent,msghst,idetracom,amotxn,baltra,msgerror,clobcons,nrocta,numope,flgerror  " + //
	    		"From "+Constante.ESQUEMA2+".HJOUTRA " + //
	    		"Where codtra is not null " + //
	    		"And (clobcons is not null or msghst is not null) " + //
	    		getQueryTxn() + //
	    		getQueryFecha() + //
	    		getQueryHora() + //
	    		getQueryTyp() + //
	    		"Order by  datpro desc, horpro desc, numlog asc, codtra";
	    }
		
		System.out.println("Query Diario Historico: "+query);
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
			row.addElement(db.getObjectValue(23));
			row.addElement(db.stringValue(24));
			row.addElement(db.stringValue(25));
			row.addElement(db.stringValue(26));
			getGrid().addElement(row);
		}
	} catch (Exception e) {
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
public void next(int i) {
	numlog = ((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString();
	numlogabrev = numlog.substring(numlog.length()-10,numlog.length());
	coddoc = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
	datpro = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
	horpro = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
	numdoc = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
	typper = ((java.util.Vector) getGrid().elementAt(i)).elementAt(5).toString();
	cipadr = ((java.util.Vector) getGrid().elementAt(i)).elementAt(6).toString();
	codtra = ((java.util.Vector) getGrid().elementAt(i)).elementAt(7).toString();
	codtrahst = ((java.util.Vector) getGrid().elementAt(i)).elementAt(8).toString();
	tipprdsrc = ((java.util.Vector) getGrid().elementAt(i)).elementAt(9).toString();
	numprdsrc = ((java.util.Vector) getGrid().elementAt(i)).elementAt(10).toString();
	tipprdtar = ((java.util.Vector) getGrid().elementAt(i)).elementAt(11).toString();
	numprdtar = ((java.util.Vector) getGrid().elementAt(i)).elementAt(12).toString();
	amotra = ((java.util.Vector) getGrid().elementAt(i)).elementAt(13).toString();
	if(amotra != null && !amotra.equals("")){
		BigDecimal mont= new BigDecimal(amotra).setScale(2);
		amotra = mont.toString();
		}
	codcur = ((java.util.Vector) getGrid().elementAt(i)).elementAt(14).toString();
	if(codcur != null && !codcur.trim().equals("")){
		//log.debug("entro aki no es nulo");
		txtcur = getTxtmoneda(codcur);
	
	}
	else{
		//log.debug("entro aki es nulo");
		txtcur="";
	}
	numref = ((java.util.Vector) getGrid().elementAt(i)).elementAt(15).toString();
	codent = ((java.util.Vector) getGrid().elementAt(i)).elementAt(16).toString();
	msghst = ((java.util.Vector) getGrid().elementAt(i)).elementAt(17).toString();
	idetracom = ((java.util.Vector) getGrid().elementAt(i)).elementAt(18).toString();
	amotxn = ((java.util.Vector) getGrid().elementAt(i)).elementAt(19).toString();
	baltra = ((java.util.Vector) getGrid().elementAt(i)).elementAt(20).toString();
	msgerror = ((java.util.Vector) getGrid().elementAt(i)).elementAt(21).toString();
	
	java.util.Vector v = (java.util.Vector) getGrid().elementAt(i);
	refrendo = ((Object) v.elementAt(22));
	//log.info("refrendo="+refrendo);
	refrendito = clobToString(refrendo);
	//log.warn("refrendito:"+refrendito);
	
	nroCuenta = ((java.util.Vector) getGrid().elementAt(i)).elementAt(23).toString();
	if(idetracom.equals("0")) idetracom="Procesada";
	if(idetracom.equals("1")) idetracom="Pendiente";
	if(idetracom.equals("2")) idetracom="Cancelado";
	if(idetracom.equals("3")) idetracom="Error";
	if(idetracom.equals("4")) idetracom="Vencida";
	numope = ((java.util.Vector) getGrid().elementAt(i)).elementAt(24).toString();
	flgerror = ((java.util.Vector) getGrid().elementAt(i)).elementAt(25).toString();
	if(flgerror.equals("0"))
		flgerror="P";
	else if(flgerror.equals("1"))
		flgerror="NP";
	else
		flgerror="";
}
/*public void nextDetalle(int i) {
	nameColumn = ((java.util.Vector) detalle.elementAt(i)).elementAt(0).toString();
	campoBef = ((java.util.Vector) detalle.elementAt(i)).elementAt(1).toString();
	campoAft = ((java.util.Vector) detalle.elementAt(i)).elementAt(2).toString();
	campoBef = (campoBef.equals("")) ? "Sin Dato" : campoBef;
	campoAft = (campoAft.equals("")) ? "Sin Dato" : campoAft;
}*/
public void nextTxn(int i) {
	namtxn = ((java.util.Vector) transacciones.elementAt(i)).elementAt(0).toString() + " - " + ((java.util.Vector) transacciones.elementAt(i)).elementAt(1).toString();
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
public void setCodoc(String newValue) {
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
public void setTypper(String newValue) {
	this.typper = newValue;
}
	/**
	 * @return Returns the refrendo.
	 */
	public Object getRefrendo() {
		return refrendo;
	}
	/**
	 * @param refrendo The refrendo to set.
	 */
	public void setRefrendo(Object refrendo) {
		this.refrendo = refrendo;
	}
	/**
	 * @return Returns the refrendito.
	 */
	public String getRefrendito() {
		return refrendito;
	}
	/**
	 * @param refrendito The refrendito to set.
	 */
	public void setRefrendito(String refrendito) {
		this.refrendito = refrendito;
	}
	/**
	 * @return Returns the nroCuenta.
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}
	/**
	 * @param nroCuenta The nroCuenta to set.
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}
	/**
	 * @return Returns the flgerror.
	 */
	public String getFlgerror() {
		return flgerror;
	}
	/**
	 * @param flgerror The flgerror to set.
	 */
	public void setFlgerror(String flgerror) {
		this.flgerror = flgerror;
	}
	/**
	 * @return Returns the numope.
	 */
	public String getNumope() {
		return numope;
	}
	/**
	 * @param numope The numope to set.
	 */
	public void setNumope(String numope) {
		this.numope = numope;
	}
	/**
	 * @return Returns the numlogabrev.
	 */
	public String getNumlogabrev() {
		return numlogabrev;
	}
	/**
	 * @param numlogabrev The numlogabrev to set.
	 */
	public void setNumlogabrev(String numlogabrev) {
		this.numlogabrev = numlogabrev;
	}
}
