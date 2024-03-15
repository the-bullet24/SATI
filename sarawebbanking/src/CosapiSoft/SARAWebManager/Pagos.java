package CosapiSoft.SARAWebManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Clob;
import java.util.Iterator;
import java.util.Vector;

import java.math.BigDecimal;

import pe.cosapi.common.Constante;
import pe.cosapi.common.ObjectUtil;

import CosapiSoft.SARAWebBanking.JDatabase;
import CosapiSoft.SARAWebBanking.JDatabasen;
import CosapiSoft.SARAWebBanking.JspServlet;
import CosapiSoft.SARAWebBanking.JQuery;
import CosapiSoft.SARAWebBanking.InicioSesion;
import CosapiSoft.SARAWebBanking.Mensajes;
import CosapiSoft.SARAWebBanking.CString;

public class Pagos {
	
	private InicioSesion login;
	public java.util.Vector grid = null;
	public java.util.Vector transacciones = null;
	public java.util.Vector tiposdoc = null;
	public java.util.Vector tipdoc = null;
	public java.util.Vector detalle = null;
	public String txnhst[]=null;
	public java.util.Vector monedas = null;
	private String modulo="manager";
	private String numlog = "";
	private String flgerror = "";
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
	private String suministro = "";
	private String numope = "";
	private String hora = "";
	private String fecha = "";
	private String amotra = "";
	private String codcur = "";
	private String txtcur = "";
	private String numref = "";
	private String namtxn = "";
	private String nrotxn = "";
	private String txttd = "";
	private String band="";
	private String tributo="";
	private String nomben="";
	private Object refrendo = null;
	private String refrendito = "";
	//
	private String codent = "";
	private String msghst = "";
	private String idetrapro = "";
	private String idetracom = "";
	private String error = "";
	private String amotxn = "";
	private String nrocta = "";
	private String baltra = "";
	private String msgerror = "";

	private String nroregistros = "";

	
	private String nroregistrospagosol = "";
	private String nroregistrospagodol = "";
	private String nroregistroscargosol = "";
	private String nroregistroscargodol = "";
	
	private String totalcargosoles = "";
	private String totalcargodolares = "";
	private String totalpagosoles = "";
	private String totalpagodolares = "";
	private String variable = "";
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
		
public Pagos() {
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
		String query = //
		"Select numlog,coddoc,datpro,horpro,numdoc,typper,cipadr,codtra,codtrahst,tipprdsrc,numprdsrc,tipprdsrc,numprdtar,amotra,codcur,numref,codent,msghst,idetrapro,idetracom,amotxn,baltra,msgerror,nomben,flgerror " + //
		"From "+Constante.ESQUEMA2+".HJOUTRA " + //
		"Where nroref = '" + getCodtra().toUpperCase() + "' " + //
		"And typper = '" + getTypper().toUpperCase() + "' " + //
		"And datpro = '" + getDatpro().toUpperCase() + "' " + //
		"And (clobcons is not null or msghst is not null) " + //
		"Order by datpro, horpro, typper";
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
		idetrapro = db.stringValue(19);
		idetracom = db.stringValue(20);
		amotxn = db.stringValue(21);
		baltra = db.stringValue(22);
		msgerror = db.stringValue(23);
		nomben = db.stringValue(24);
		flgerror = db.stringValue(25);
		return true;
	} 
	catch (Exception e) {
		e.printStackTrace();
		throw new Exception("\nBuscar - Log de Operaciones \n" + e.getMessage());
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
	return str.toString().trim();
}

public String getDatpro2() {
	CString str = new CString(datpro2);
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
	/*if (getDatpro1().toUpperCase().equals("DDMMYYYY") && getDatpro2().toUpperCase().equals("DDMMYYYY"))
		return "";
	if (getDatpro1().toUpperCase().equals("00000000") && getDatpro2().toUpperCase().equals("00000000"))
		return "";
	if (getDatpro1().equals("") && getDatpro2().equals(""))
		return "";*/
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

private String getQueryFech() {
	String str = "";
	String str1 = "";
	if (getDatpro().equals(""))
		return "";
	str = "And to_char(datpro,'dd/mm/rrrr') = '" + getDatpro() + "' ";
	System.out.println("entró aki 2");
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
	if (getNumref().equals(""))
		return "";
	String query = "And numref = '" + getNumref().trim() + "' ";
	return query;
}

private String getQueryNroOpe() {
	if (getNumope().toUpperCase().equals(""))
		return "";
	String query = "And numope = '" + getNumope().trim() + "' ";
	return query;
}

private String getQueryDoc() {
	if (getNumdoc().equals(""))
		return "";
	String query = "And numdoc = '" + getNumdoc().trim() + "' ";
	return query;
}

private String getQueryNroCtaDestino() {
	if (getNumprdtar().equals(""))
		return "";
	String query = "And numprdtar = '" + getNumprdtar().trim() + "' ";

	return query;
}

private String getQueryNroCta() {
	if (getNrocta().equals(""))
		return "";
	String query = "And nrocta = '" + getNrocta().trim() + "' ";

	return query;
}

private String getQueryTxn() {
	String query3="codtrahst in ("+getNrotxn()+") ";
	String query1="codtrahst in ("+getNrotxn();
	String query2="";
	String query=""; 
	if (txnhst == null)
		return query3;
	for (int p = 0; p < txnhst.length; ++p)
    {
      String testParam = txnhst[p];
      if(p+1==txnhst.length)
      query2 = query2 + ", '" + testParam.substring(0,4).toString() + "') ";
      else
      	query2 = query2 + ", '" + testParam.substring(0,4).toString() + "' ";
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
	} catch (Exception e) {
		e.printStackTrace();
		throw new Exception("\nLoadComboUsr() - Log de Operaciones \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}

public void loadTotales() throws Exception {
	if(!getBand().equals("Pago de Tasas") && !getBand().equals("Transferencia Mismo Banco") && !getBand().equals("Transferencia Interbancaria") && !getBand().equals("Pago Tarjeta") && !getBand().equals("Telefonia")  && !getBand().equals("Agua")){
		JDatabasen jd = new JDatabasen(login.driver, login.url, login.userid, login.password);
		JQuery db = new JQuery(jd.getConnection());
		try {
			String query1=//
			"select count(*),sum(amotxn),sum(amotra) " + //
			"From "+Constante.ESQUEMA2+".HJOUTRA " + //
			"Where flgerror='0' and codcur='840' and " + 											//
			"(clobcons is not null or msghst is not null) and " + //
			getQueryTxn() + //
			getQueryFecha() + //
			getQueryNroOpe()+ //
			getQueryNroCta() + //
			getQueryNroCtaDestino() + //
			getQueryFech() + //
			getQueryNroRef() + //
			getQueryDoc() + //
			"Order by numlog asc, codtra, datpro desc, horpro desc";
			db.setQuery(query1);
			System.out.println("DOLARES-->"+query1);
			db.executeQuery();
				if (db.getResultSet().next()) {
					setNroregistroscargodol(db.stringValue(1));
					if(!db.stringValue(2).equals("") && db.stringValue(2)!=null){
						BigDecimal tot2= new BigDecimal(db.stringValue(2)).setScale(2);
						setTotalpagodolares(ObjectUtil.formatearMonto(tot2));
					}else
						setTotalpagodolares("0.00");
						
					if(!db.stringValue(3).equals("") && db.stringValue(3)!=null){
							BigDecimal tot2= new BigDecimal(db.stringValue(3)).setScale(2);
							setTotalcargodolares(ObjectUtil.formatearMonto(tot2));
					}else
						setTotalcargodolares("0.00");
				}
			query1=//
			"select count(*),sum(amotxn),sum(amotra) " + //
			"From "+Constante.ESQUEMA2+".HJOUTRA " + //
			"Where flgerror='0' and codcur='604' and " + 											//
			"(clobcons is not null or msghst is not null) and " + //
			getQueryTxn() + //
			getQueryFecha() + //
			getQueryNroOpe()+ //
			getQueryNroCta() + //
			getQueryNroCtaDestino() + //
			getQueryFech() + //
			getQueryNroRef() + //
			getQueryDoc() + //
			"Order by numlog asc, codtra, datpro desc, horpro desc";
			db.setQuery(query1);
			System.out.println("SOLES-->"+query1);
			db.executeQuery();
				if (db.getResultSet().next()) {
					
					setNroregistroscargosol(db.stringValue(1));
					if(!db.stringValue(2).equals("") && db.stringValue(2)!=null){
						BigDecimal tot1= new BigDecimal(db.stringValue(2)).setScale(2);
						setTotalpagosoles(ObjectUtil.formatearMonto(tot1));
					}
					else
					setTotalpagosoles("0.00");
					
					if(!db.stringValue(3).equals("") && db.stringValue(3)!=null){
						BigDecimal tot1= new BigDecimal(db.stringValue(3)).setScale(2);
						setTotalcargosoles(ObjectUtil.formatearMonto(tot1));
					}
					else
					setTotalcargosoles("0.00");
				}
			if(getBand().equals("Prestamo Multired")){
				
					System.out.println("Prestamo Multired");
				query1=//
					"Select count(*), sum(baltra) " + //
					"From "+Constante.ESQUEMA2+".HJOUTRA " + //
					"Where flgerror='0' and " + 											//
					"(clobcons is not null or msghst is not null) and " + //
					getQueryTxn() + //
					getQueryFecha() + //
					getQueryNroOpe()+ //
					getQueryNroCta() + //
					getQueryNroCtaDestino() + //
					getQueryFech() + //
					getQueryNroRef() + //
					getQueryDoc() + //
					"Order by numlog asc, codtra, datpro desc, horpro desc";
					db.setQuery(query1);
					System.out.println("SOLES-->"+query1);
					db.executeQuery();
						if (db.getResultSet().next()) {
							
							setNroregistrospagosol(db.stringValue(1));
							if(!db.stringValue(2).equals("") && db.stringValue(2)!=null){
								BigDecimal tot1= new BigDecimal(db.stringValue(2)).setScale(2);
								setTotalpagosoles(ObjectUtil.formatearMonto(tot1));
								
							}else
								setTotalpagosoles("0.00");
							
						}
				
				
			}
			if(getBand().equals("Emision de Giros")){
				
					System.out.println("EMISION DE GIROS PARA EJECUTAR IMPORTE GIRADO SOLES");
				query1=//
					"Select count(*), sum(baltra) " + //
					"From "+Constante.ESQUEMA2+".HJOUTRA " + //
					"Where flgerror='0' and codcur='604' and " + 											//
					"(clobcons is not null or msghst is not null) and " + //
					getQueryTxn() + //
					getQueryFecha() + //
					getQueryNroOpe()+ //
					getQueryNroCta() + //
					getQueryNroCtaDestino() + //
					getQueryFech() + //
					getQueryNroRef() + //
					getQueryDoc() + //
					"Order by numlog asc, codtra, datpro desc, horpro desc";
					db.setQuery(query1);
					System.out.println("SOLES-->"+query1);
					db.executeQuery();
						if (db.getResultSet().next()) {
							
							setNroregistrospagosol(db.stringValue(1));
							if(!db.stringValue(2).equals("") && db.stringValue(2)!=null){
								BigDecimal tot1= new BigDecimal(db.stringValue(2)).setScale(2);
								setTotalpagosoles(ObjectUtil.formatearMonto(tot1));
								
							}else
								setTotalpagosoles("0.00");
							
						}
					System.out.println("EMISION DE GIROS PARA EJECUTAR IMPORTE GIRADO DOLARES");
					query1=//
						"Select count(*), sum(baltra) " + //
						"From "+Constante.ESQUEMA2+".HJOUTRA " + //
						"Where flgerror='0' and codcur='840' and " + 											//
						"(clobcons is not null or msghst is not null) and " + //
						getQueryTxn() + //
						getQueryFecha() + //
						getQueryNroOpe()+ //
						getQueryNroCta() + //
						getQueryNroCtaDestino() + //
						getQueryFech() + //
						getQueryNroRef() + //
						getQueryDoc() + //
						"Order by numlog asc, codtra, datpro desc, horpro desc";
						db.setQuery(query1);
						System.out.println("DOLARES-->"+query1);
						db.executeQuery();
							if (db.getResultSet().next()) {
								
								setNroregistrospagodol(db.stringValue(1));
								if(!db.stringValue(2).equals("") && db.stringValue(2)!=null){
									BigDecimal tot1= new BigDecimal(db.stringValue(2)).setScale(2);
									setTotalpagodolares(ObjectUtil.formatearMonto(tot1));
									
								}else
									setTotalpagodolares("0.00");
								
							}			
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("\nLoadComboUsr() - Log de Operaciones \n" + e.getMessage());
		} finally {
			db.close();
			jd.close();
		}
	}
}

public void loadTotales2(Vector v_contenido) throws Exception {
	System.out.println("LOAD TOTALES 2");
	BigDecimal totalCargoSoles= new BigDecimal("0.00"),
	totalCargoDolares= new BigDecimal("0.00"),
	totalPagoSoles= new BigDecimal("0.00"),
	totalPagoDolares= new BigDecimal("0.00");
	String TextoMoneda="";
	int nroRegCargoSoles=0, nroRegCargoDolares=0,nroRegPagoSoles=0,nroRegPagoDolares=0;
	try {
	
		if(getBand().equals("Transferencia Mismo Banco")|| getBand().equals("Transferencia Interbancaria") || getBand().equals("Pago Tarjeta") || getBand().equals("Telefonia")  || getBand().equals("Agua") || getBand().equals("Pago de Tasas")){
			System.out.println("Proceso=" + getBand());
			
				for(int i=0;i<v_contenido.size();i++){
					Vector content= (Vector)v_contenido.elementAt(i);//content[x]=[amotra,codcur,amotxn,baltra,nrocta]
					String amotra2=((String)content.elementAt(0).toString().trim()).equals("0.00")?"":(String)content.elementAt(0).toString().trim(), 
						   codMon =(String)content.elementAt(1).toString().trim(), 
						   amotxn2=((String)content.elementAt(2).toString().trim()).equals("0.00")?"":(String)content.elementAt(2).toString().trim(),
						   baltra2=((String)content.elementAt(3).toString().trim()).equals("0.00")?"":(String)content.elementAt(3).toString().trim(), 
						   nrocta2=(String)content.elementAt(4).toString().trim();
					/*log.info("amotra2: " + amotra2);
					log.info("codMon: " + codMon);
					log.info("amotxn2: " + amotxn2);
					log.info("baltra2: " + baltra2);
					log.info("nrocta2: " + nrocta2);*/
					
					TextoMoneda="";
											
					if((codMon.equals("604") && amotxn2.equals(baltra2)) || (codMon.equals("840") && amotxn2.equals(baltra2)) || amotxn2==null || amotxn2.equals("")){
						TextoMoneda = 		getTxtmoneda(codMon);
						//log.info("Totales2 Moneda seleccionada condicion 1: " + TextoMoneda);
					}
					else if(codMon.equals("604") && !amotxn2.equals(baltra2) && amotxn2!=null && !amotxn2.equals("")){
						TextoMoneda = 		getTxtmoneda("840");
						//log.info("Totales2 Moneda seleccionada condicion 2: " + TextoMoneda);
					}
					else if(codMon.equals("840") && !amotxn2.equals(baltra2) && amotxn2!=null && !amotxn2.equals("")){
						TextoMoneda = 		getTxtmoneda("604");
						//log.info("Totales2 Moneda seleccionada condicion 3: " + TextoMoneda);
					}
					String x= "";
					if(TextoMoneda==null ||TextoMoneda.equals("null") || TextoMoneda.equals(null)){;
					}else{	
						if(TextoMoneda.equals("Soles")){//codcur
							//log.debug("Entro a Moneda SOLES");
							nroRegPagoSoles++;
							totalPagoSoles=totalPagoSoles.add(new BigDecimal(""+baltra2).setScale(2));
						}else if(TextoMoneda.equals("Dólares")){
							//log.debug("Entro a Moneda DOLARES");
							nroRegPagoDolares++;
							totalPagoDolares=totalPagoDolares.add(new BigDecimal(""+baltra2).setScale(2));
						}
						
						String prefijo=content.get(4).toString().trim().substring(0,2);
						if(prefijo.equals("04") || prefijo.equals("00") || prefijo.equals("54")){//Soles
							nroRegCargoSoles++;
							totalCargoSoles=totalCargoSoles.add(new BigDecimal(""+amotra2).setScale(2));
						}else{
							nroRegCargoDolares++;
							totalCargoDolares=totalCargoDolares.add(new BigDecimal(""+amotra2).setScale(2));
						}	
					
					}
				}
				if(TextoMoneda==null ||TextoMoneda.equals("null") || TextoMoneda.equals(null)){;
				}else{
					setNroregistrospagosol(""+nroRegPagoSoles);
					setTotalpagosoles(""+ObjectUtil.formatearMonto(totalPagoSoles));
					
					setNroregistrospagodol(""+nroRegPagoDolares);
					setTotalpagodolares(""+ObjectUtil.formatearMonto(totalPagoDolares));
					
					setNroregistroscargosol(""+nroRegCargoSoles);
					setTotalcargosoles(""+ObjectUtil.formatearMonto(totalCargoSoles));
					
					setNroregistroscargodol(""+nroRegCargoDolares);
					setTotalcargodolares(""+ObjectUtil.formatearMonto(totalCargoDolares));
				}
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new Exception("Error en cargar totales 2 : " + e.getMessage());
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
	Vector v_contenido,total= new Vector();//v_contenido=[amotra,codcur,amotxn,baltra,nrocta]
	try {
		String query = //
		"Select numlog," +						//01
		"		coddoc," +						//02
		"		to_char(datpro,'dd/mm/rrrr')," +//03
		"		horpro," +						//04
		"		numdoc," +						//05
		"		typper," +						//06
		"		cipadr," +						//07
		"		codtra," +						//08
		"		codtrahst," +					//09
		"		tipprdsrc," +					//10
		"		numprdsrc," +					//11
		"		tipprdtar," +					//12
		"		numprdtar," +					//13
		"		amotra," +						//14 SUMA
		"		codcur," +						//15 Moneda
		"		numref," +						//16
		"		codent," +						//17
		"		msghst," +						//18
		"		idetracom," +					//19
		"		amotxn," +						//20 SUMA
		"		baltra," +						//21 SUMA
		"		msgerror," +					//22
		"		clobcons," +					//23
		"		nomben," +						//24
		"		nrocta, " +						//25 Cuenta
		"		numope," +						//26
		"		flgerror " +					//27
		"From "+Constante.ESQUEMA2+".HJOUTRA " + 
		"Where flgerror='0' and " + 
		"(clobcons is not null or msghst is not null) and " + //
		getQueryTxn() + //
		getQueryFecha() + //
		getQueryNroOpe()+ //
		getQueryNroCta() + //
		getQueryNroCtaDestino() + //
		getQueryFech() + //
		getQueryNroRef() + //
		getQueryDoc() + //
		"Order by numlog asc, codtra, datpro desc, horpro desc";
		db.setQuery(query);
		db.executeQuery();
		System.out.println("query-------"+query);
		// System.out.println("ENTRO A EJECUTAR PRUEBA LOADGRID()");
		int i =0;
		while (db.getResultSet().next()) {
			
			i++;
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
			row.addElement(db.stringValue(14));	//
			row.addElement(db.stringValue(15));	//
			row.addElement(db.stringValue(16));
			row.addElement(db.stringValue(17));
			row.addElement(db.stringValue(18));
			row.addElement(db.stringValue(19));
			row.addElement(db.stringValue(20));	//
			row.addElement(db.stringValue(21));	//
			row.addElement(db.stringValue(22));
			row.addElement(db.getObjectValue(23));
			row.addElement(db.stringValue(24));
			row.addElement(db.stringValue(25));	//
			row.addElement(db.stringValue(26));
			row.addElement(db.stringValue(27));
			/**	----- Para los totales ------ **/
			v_contenido= new Vector();
			v_contenido.add(0,""+new BigDecimal(db.stringValue(14).trim().equals("")?"0.00":db.stringValue(14).trim()).setScale(2));//amotra
			v_contenido.add(1,db.stringValue(15));//codcur
			v_contenido.add(2,""+new BigDecimal(db.stringValue(20).trim().equals("")?"0.00":db.stringValue(20).trim()).setScale(2));//amotxn
			v_contenido.add(3,""+new BigDecimal(db.stringValue(21).trim().equals("")?"0.00":db.stringValue(21).trim()).setScale(2));//baltra
			v_contenido.add(4,db.stringValue(25));//nrocta
			total.addElement(v_contenido);
			/** -----------	Fin ---------- **/
			getGrid().addElement(row);
		}
		System.out.println("Numero de registros a procesar= " + total.size());
		loadTotales2(total);
				
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
public void next(int i) throws Exception {
	numlog = 		((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString();
	coddoc = 		((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
	txttd = 		getTxttipodoc(coddoc);
	datpro = 		((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
	horpro = 		((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
	numdoc = 		((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
	typper = 		((java.util.Vector) getGrid().elementAt(i)).elementAt(5).toString();
	cipadr = 		((java.util.Vector) getGrid().elementAt(i)).elementAt(6).toString();
	codtra = 		((java.util.Vector) getGrid().elementAt(i)).elementAt(7).toString();
	codtrahst = 	((java.util.Vector) getGrid().elementAt(i)).elementAt(8).toString();
	tipprdsrc = 	((java.util.Vector) getGrid().elementAt(i)).elementAt(9).toString();
	numprdsrc = 	((java.util.Vector) getGrid().elementAt(i)).elementAt(10).toString();
	tipprdtar = 	((java.util.Vector) getGrid().elementAt(i)).elementAt(11).toString();
	numprdtar = 	((java.util.Vector) getGrid().elementAt(i)).elementAt(12).toString();
	amotra = 	(	(java.util.Vector) getGrid().elementAt(i)).elementAt(13).toString();
	if(amotra != null && !amotra.equals("")){
		BigDecimal mont= new BigDecimal(amotra).setScale(2);
		amotra = mont.toString();
	}
	codcur = 		((java.util.Vector) getGrid().elementAt(i)).elementAt(14).toString();
	numref = 		((java.util.Vector) getGrid().elementAt(i)).elementAt(15).toString();
	codent = 		((java.util.Vector) getGrid().elementAt(i)).elementAt(16).toString();
	msghst = 		((java.util.Vector) getGrid().elementAt(i)).elementAt(17).toString();
	idetracom = 	((java.util.Vector) getGrid().elementAt(i)).elementAt(18).toString();
	amotxn =	 	((java.util.Vector) getGrid().elementAt(i)).elementAt(19).toString();
	if(amotxn != null && !amotxn.equals("")){
		BigDecimal mont= new BigDecimal(amotxn).setScale(2);
		amotxn = mont.toString();
	}
	baltra =		((java.util.Vector) getGrid().elementAt(i)).elementAt(20).toString();
	if(baltra != null && !baltra.equals("")){
		BigDecimal mont= new BigDecimal(baltra).setScale(2);
		baltra = mont.toString();
	}
	msgerror = 		((java.util.Vector) getGrid().elementAt(i)).elementAt(21).toString();
	
	java.util.Vector v = (java.util.Vector) getGrid().elementAt(i);
	refrendo=((Object) v.elementAt(22));
	//log.info("refrendo="+refrendo);
	refrendito = clobToString(refrendo);
	
	if(idetracom.equals("0")) idetracom="Procesada";
	if(idetracom.equals("1")) idetracom="Pendiente";
	if(idetracom.equals("2")) idetracom="Cancelado";
	if(idetracom.equals("3")) idetracom="Error";
	if(idetracom.equals("4")) idetracom="Vencida";
	nomben = ((java.util.Vector) getGrid().elementAt(i)).elementAt(23).toString();
	nrocta = ((java.util.Vector) getGrid().elementAt(i)).elementAt(24).toString();
	numope = ((java.util.Vector) getGrid().elementAt(i)).elementAt(25).toString();
	flgerror = ((java.util.Vector) getGrid().elementAt(i)).elementAt(26).toString();
	if(flgerror.equals("0"))
		flgerror="P";
	else if(flgerror.equals("1"))
		flgerror="NP";
	else
		flgerror="";
	
	if((codcur.equals("604") && amotxn.equals(baltra)) || (codcur.equals("840") && amotxn.equals(baltra)) || amotxn==null || amotxn.equals("")){
		txtcur = 		getTxtmoneda(codcur);
		//log.info("Moneda seleccionada condicion 1: " + txtcur);
	}
	else if(codcur.equals("604") && !amotxn.equals(baltra) && amotxn!=null && !amotxn.equals("")){
		txtcur = 		getTxtmoneda("840");
		//log.info("Moneda seleccionada condicion 2: " + txtcur);
	}
	else if(codcur.equals("840") && !amotxn.equals(baltra) && amotxn!=null && !amotxn.equals("")){
		txtcur = 		getTxtmoneda("604");
		//log.info("Moneda seleccionada condicion 3: " + txtcur);
	}
	/*log.info("cod moneda: "+ codcur);
	log.info("amotra2: " + amotra);
	log.info("amotxn2: " + amotxn);
	log.info("baltra2: " + baltra);
	log.info("nrocta2: " + nrocta);*/
}
public String BuscarNroServicio(String nrorefe) throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	nomben="";
	try {
		String query = //
		"Select nomben " + //
		"From "+Constante.ESQUEMA1+".TAFIDAT " + //
		"Where numser ='" + nrorefe + "'";
		//System.out.println("beneficiario:------"+query);
		//log.debug("beneficiario:------"+query);
		db.setQuery(query);
		db.executeQuery();
		
		while (db.getResultSet().next()) {
			nomben=db.stringValue(1);
			System.out.println("beneficiario:------"+nomben);
		}
	}
	catch (Exception e) {
		e.printStackTrace();
		throw new Exception("\nLoadComboUsr() - Log de Operaciones \n" + e.getMessage());
	} finally {
		
		db.close();
		jd.close();
	}
	return nomben;
}
public void BuscarRefrendito(String nrorefe) throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		String query = //
		"Select nomben " + //
		"From "+Constante.ESQUEMA1+".TAFIDAT " + //
		"Where numser ='" + nrorefe + "'";
		//System.out.println("beneficiario:------"+query);
		//log.debug("beneficiario:------"+query);
		db.setQuery(query);
		db.executeQuery();
		
		while (db.getResultSet().next()) {
			nomben=db.stringValue(1);
		}
	}
	catch (Exception e) {
		e.printStackTrace();
		throw new Exception("\nLoadComboUsr() - Log de Operaciones \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}

public void Refrendo() throws Exception {
	JDatabasen jd = new JDatabasen(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		String query = //
		"Select clobcons " + //
		"From "+Constante.ESQUEMA2+".HJOUTRA " + //
		"Where numlog ='" + getNumlog() + "'" +
		" And horpro = '" + getHora() + "'"; 
	//	log.debug("numlog="+getNumlog()+"|horpro="+getHora());
	//	log.debug("query="+query);
		db.setQuery(query);
		db.executeQuery();
		
		if(db.getResultSet().next()) {
			refrendo=((Object) db.getObjectValue(1));
			refrendito = clobToString(refrendo);
		}
	}
	catch (Exception e) {
		System.out.println(""+e);
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
	 * @return Devuelve band.
	 */
	public String getBand() {
		return band;
	}
	/**
	 * @param band El band a establecer.
	 */
	public void setBand(String band) {
		this.band = band;
	}
	/**
	 * @return Devuelve tributo.
	 */
	public String getTributo() {
		return tributo;
	}
	/**
	 * @param tributo El tributo a establecer.
	 */
	public void setTributo(String tributo) {
		this.tributo = tributo;
	}
	/**
	 * @return Devuelve numser.
	 */
	public String getNomben() {
		return nomben;
	}
	/**
	 * @param numser El numser a establecer.
	 */
	public void setNomben(String nomben) {
		this.nomben = nomben;
	}
	/**
	 * @return Devuelve refrendo.
	 */
	public Object getRefrendo() {
		return refrendo;
	}
	/**
	 * @param refrendo El refrendo a establecer.
	 */
	public void setRefrendo(Object refrendo) {
		this.refrendo = refrendo;
	}
	
	
	/**
	 * @return Devuelve refrendito.
	 */
	public String getRefrendito() {
		return refrendito;
	}
	/**
	 * @param refrendito El refrendito a establecer.
	 */
	public void setRefrendito(String refrendito) {
		this.refrendito = refrendito;
	}
	/**
	 * @return Devuelve fecha.
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha El fecha a establecer.
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return Devuelve hora.
	 */
	public String getHora() {
		return hora;
	}
	/**
	 * @param hora El hora a establecer.
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}
	/**
	 * @return Devuelve suministro.
	 */
	public String getSuministro() {
		return suministro;
	}
	/**
	 * @param suministro El suministro a establecer.
	 */
	public void setSuministro(String suministro) {
		this.suministro = suministro;
	}
	/**
	 * @return Returns the nrocta.
	 */
	public String getNrocta() {
		return nrocta;
	}
	/**
	 * @param nrocta The nrocta to set.
	 */
	public void setNrocta(String nrocta) {
		this.nrocta = nrocta;
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
	 * @return Returns the totalcargodolares.
	 */
	public String getTotalcargodolares() {
		return totalcargodolares;
	}
	/**
	 * @param totalcargodolares The totalcargodolares to set.
	 */
	public void setTotalcargodolares(String totalcargodolares) {
		this.totalcargodolares = totalcargodolares;
	}
	/**
	 * @return Returns the totalcargosoles.
	 */
	public String getTotalcargosoles() {
		return totalcargosoles;
	}
	/**
	 * @param totalcargosoles The totalcargosoles to set.
	 */
	public void setTotalcargosoles(String totalcargosoles) {
		this.totalcargosoles = totalcargosoles;
	}
	/**
	 * @return Returns the totalpagodolares.
	 */
	public String getTotalpagodolares() {
		return totalpagodolares;
	}
	/**
	 * @param totalpagodolares The totalpagodolares to set.
	 */
	public void setTotalpagodolares(String totalpagodolares) {
		this.totalpagodolares = totalpagodolares;
	}
	/**
	 * @return Returns the totalpagosoles.
	 */
	public String getTotalpagosoles() {
		return totalpagosoles;
	}
	/**
	 * @param totalpagosoles The totalpagosoles to set.
	 */
	public void setTotalpagosoles(String totalpagosoles) {
		this.totalpagosoles = totalpagosoles;
	}
	/**
	 * @return Returns the variable.
	 */
	public String getVariable() {
		return variable;
	}
	/**
	 * @param variable The variable to set.
	 */
	public void setVariable(String variable) {
		this.variable = variable;
	}
	/**
	 * @return Devuelve nroregistroscargodol.
	 */
	public String getNroregistroscargodol() {
		return nroregistroscargodol;
	}
	/**
	 * @param nroregistroscargodol El nroregistroscargodol a establecer.
	 */
	public void setNroregistroscargodol(String nroregistroscargodol) {
		this.nroregistroscargodol = nroregistroscargodol;
	}
	/**
	 * @return Devuelve nroregistroscargosol.
	 */
	public String getNroregistroscargosol() {
		return nroregistroscargosol;
	}
	/**
	 *
	 * @param nroregistroscargosol El nroregistroscargosol a establecer.
	 */
	public void setNroregistroscargosol(String nroregistroscargosol) {
		this.nroregistroscargosol = nroregistroscargosol;
	}
	/**
	 * @return Devuelve nroregistrospagodol.
	 */
	public String getNroregistrospagodol() {
		return nroregistrospagodol;
	}
	/**
	 * @param nroregistrospagodol El nroregistrospagodol a establecer.
	 */
	public void setNroregistrospagodol(String nroregistrospagodol) {
		this.nroregistrospagodol = nroregistrospagodol;
	}
	/**
	 * @return Devuelve nroregistrospagosol.
	 */
	public String getNroregistrospagosol() {
		return nroregistrospagosol;
	}
	/**
	 * @param nroregistrospagosol El nroregistrospagosol a establecer.
	 */
	public void setNroregistrospagosol(String nroregistrospagosol) {
		this.nroregistrospagosol = nroregistrospagosol;
	}
}


