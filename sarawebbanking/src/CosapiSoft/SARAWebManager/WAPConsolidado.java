package CosapiSoft.SARAWebManager;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import pe.cosapi.common.Constante;

import CosapiSoft.SARAWebBanking.GenericTable;

import CosapiSoft.SARAWebBanking.JDatabasen;

import CosapiSoft.SARAWebBanking.JQuery;
import CosapiSoft.SARAWebBanking.InicioSesion;


public class WAPConsolidado extends GenericTable{
	//
	private InicioSesion login;
	//
	public java.util.Vector grid = null;
	public Map totalMap = new HashMap();
	
	private String modulo="manager";
	/**
	 * @return Devuelve coddoc.
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
	
	public void setGrid(java.util.Vector grid) {
		this.grid = grid;
	}
	
	/**
	 * @return Devuelve datpro1.
	 */
	public String getDatpro1() {
		return datpro1;
	}
	/**
	 * @param datpro1 El datpro1 a establecer.
	 */
	public void setDatpro1(String datpro1) {
		this.datpro1 = datpro1;
	}
	/**
	 * @return Devuelve datpro2.
	 */
	public String getDatpro2() {
		return datpro2;
	}
	/**
	 * @param datpro2 El datpro2 a establecer.
	 */
	public void setDatpro2(String datpro2) {
		this.datpro2 = datpro2;
	}
	private String datpro1 = "";
	private String datpro2 = "";
	private String txnhst="";
	
	//
		private String nameColumn = "";
		
	
/**
 * LogDeOperaciones constructor comment.
 */
		
public WAPConsolidado() {
	super();
	grid = new java.util.Vector();
}
public boolean mostrar() throws Exception {
	JDatabasen jd = new JDatabasen(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		Map mapa1 = mostrar(db,"1100");
		Map mapa2 = mostrar(db,"0100");
		Map mapa3 = mostrar(db,"3035");
		Map mapa4 = mostrar(db,"3800");
		Map mapa5 = mostrar(db,"1125");
		Map mapa6 = mostrar(db,"0125");
		ConsolidadoWAP cWAP1 = new ConsolidadoWAP((Resultado[])mapa1.get("obj1"),(Resultado[])mapa1.get("obj2"),(Resultado[])mapa1.get("obj3"),(Resultado[])mapa1.get("obj4"));
		ConsolidadoWAP cWAP2 = new ConsolidadoWAP((Resultado[])mapa2.get("obj1"),(Resultado[])mapa2.get("obj2"),(Resultado[])mapa2.get("obj3"),(Resultado[])mapa2.get("obj4"));
		ConsolidadoWAP cWAP3 = new ConsolidadoWAP((Resultado[])mapa3.get("obj1"),(Resultado[])mapa3.get("obj2"),(Resultado[])mapa3.get("obj3"),(Resultado[])mapa3.get("obj4"));
		ConsolidadoWAP cWAP4 = new ConsolidadoWAP((Resultado[])mapa4.get("obj1"),(Resultado[])mapa4.get("obj2"),(Resultado[])mapa4.get("obj3"),(Resultado[])mapa4.get("obj4"));
		ConsolidadoWAP cWAP5 = new ConsolidadoWAP((Resultado[])mapa5.get("obj1"),(Resultado[])mapa5.get("obj2"),(Resultado[])mapa5.get("obj3"),(Resultado[])mapa5.get("obj4"));
		ConsolidadoWAP cWAP6 = new ConsolidadoWAP((Resultado[])mapa6.get("obj1"),(Resultado[])mapa6.get("obj2"),(Resultado[])mapa6.get("obj3"),(Resultado[])mapa6.get("obj4"));
		Map temp = new HashMap();
		
		temp.put("Ahorros",cWAP1);
		temp.put("CtaCte",cWAP2);
		temp.put("PreMul",cWAP3);
		temp.put("ConSal",cWAP4);
		temp.put("CciAho",cWAP5);
		temp.put("CciCte",cWAP6);
		this.totalMap = temp;
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		throw new Exception("\nBuscar - Log de Operaciones \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}

/**
 * @param db
 */
private Map mostrar(JQuery db,String codhst) throws Exception {
	Map mapa =  new HashMap();
	try {
		String query = //
			"select flgerror,count(*) "+//
			"from "+Constante.ESQUEMA2+".HJOUTRA "+//
			"where (substr(numprdsrc,0,4) in ('4214')  or length(numprdsrc)=8 or numprdsrc is null) and flgcom='3' and TO_DATE(DATPRO,'dd/mm/rrrr') >= TO_DATE('"+ getDatpro1()+ "', 'dd/mm/rrrr') and TO_DATE(DATPRO,'dd/mm/rrrr') <= TO_DATE('"+ getDatpro2()+ "', 'dd/mm/rrrr') and codtrahst='" + codhst + "' "+//
			"group by flgerror";
		//System.out.println(query);
		db.setQuery(query);
		db.executeQuery();
		List lst = new ArrayList();
		Resultado result 		= null;
		Resultado[] resArray 	= null;
		while(db.getResultSet().next()) {
			result = new Resultado();
			result.setValor1(db.stringValue(1));
			result.setValor2(db.stringValue(2));
			// System.out.println("valor seteado 1........"+db.stringValue(1));
			// System.out.println("valor seteado 2......."+db.stringValue(2));
			lst.add(result);
			resArray = (Resultado[])lst.toArray(new Resultado[0]); 
		}
		mapa.put("obj1",resArray);
		
		query = //
			"select flgerror,count(*) "+//
			"from "+Constante.ESQUEMA2+".HJOUTRA "+//
			"where (substr(numprdsrc,0,4) in ('4214') or length(numprdsrc)=8 or numprdsrc is null) and flgcom='2' and TO_DATE(DATPRO,'dd/mm/rrrr') >= TO_DATE('"+ getDatpro1()+ "', 'dd/mm/rrrr') and TO_DATE(DATPRO,'dd/mm/rrrr') <= TO_DATE('"+ getDatpro2()+ "', 'dd/mm/rrrr') and codtrahst='" + codhst + "' "+//
			"group by flgerror";
		// System.out.println(query);
		db.setQuery(query);
		db.executeQuery();
		List lst1 = new ArrayList();
		Resultado result1 		= null;
		Resultado[] resArray1 	= null;
		while(db.getResultSet().next()) {
			result1 = new Resultado();
			result1.setValor1(db.stringValue(1));
			result1.setValor2(db.stringValue(2));
			lst1.add(result1);
			resArray1 = (Resultado[])lst1.toArray(new Resultado[0]); 
		}
		mapa.put("obj2",resArray1);

		query = //
			"select flgerror,count(*) "+//
			"from "+Constante.ESQUEMA2+".HJOUTRA "+//
			"where substr(numprdsrc,0,4) in ('8018') and flgcom='3' and TO_DATE(DATPRO,'dd/mm/rrrr') >= TO_DATE('"+ getDatpro1()+ "', 'dd/mm/rrrr') and TO_DATE(DATPRO,'dd/mm/rrrr') <= TO_DATE('"+ getDatpro2()+ "', 'dd/mm/rrrr') and codtrahst='" + codhst + "' "+//
			"group by flgerror";
		//System.out.println(query);
		db.setQuery(query);
		db.executeQuery();
		List lst2 = new ArrayList();
		Resultado result2 		= null;
		Resultado[] resArray2 	= null;
		while(db.getResultSet().next()) {
			result2 = new Resultado();
			result2.setValor1(db.stringValue(1));
			result2.setValor2(db.stringValue(2));
			lst2.add(result2);
			resArray2 = (Resultado[])lst2.toArray(new Resultado[0]); 
		}
		mapa.put("obj3",resArray2);
		
		query = //
			"select flgerror,count(*) "+//
			"from "+Constante.ESQUEMA2+".HJOUTRA "+//
			"where  substr(numprdsrc,0,4) in ('8018') and flgcom='2' and TO_DATE(DATPRO,'dd/mm/rrrr') >= TO_DATE('"+ getDatpro1()+ "', 'dd/mm/rrrr') and TO_DATE(DATPRO,'dd/mm/rrrr') <= TO_DATE('"+ getDatpro2()+ "', 'dd/mm/rrrr') and codtrahst='" + codhst + "' "+//
			"group by flgerror";
		//System.out.println(query);
		db.setQuery(query);
		db.executeQuery();
		List lst3 = new ArrayList();
		Resultado result3 		= null;
		Resultado[] resArray3 	= null;
		while(db.getResultSet().next()) {
			result3 = new Resultado();
			result3.setValor1(db.stringValue(1));
			result3.setValor2(db.stringValue(2));
			lst3.add(result3);
			resArray3 = (Resultado[])lst3.toArray(new Resultado[0]); 
		}
		mapa.put("obj4",resArray3);
				
		return mapa;
		
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new Exception("\nLoadGrid - Ctas Ahorros \n" + e.getMessage());
	}
	
}


public void clearGrid() {
	grid = new java.util.Vector(1);
}

public java.util.Vector getGrid() {
	if (grid == null)
		grid = new java.util.Vector(100,100);
	return grid;
}


/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNameColumn() {
	return nameColumn.toUpperCase();
}


public void next() {
	for(int i=0;i<getGrid().size();i++){
	txnhst = ((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString();
			
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
/* (sin Javadoc)
 * @see CosapiSoft.SARAWebBanking.GenericTable#consult()
 */
public void consult() throws SQLException {
	// TODO Apéndice de método generado automáticamente
	
}
/* (sin Javadoc)
 * @see CosapiSoft.SARAWebBanking.GenericTable#delete(CosapiSoft.SARAWebBanking.JQuery)
 */
public void delete(JQuery db) throws SQLException {
	// TODO Apéndice de método generado automáticamente
	
}
/* (sin Javadoc)
 * @see CosapiSoft.SARAWebBanking.GenericTable#delete(CosapiSoft.SARAWebBanking.JQuery, javax.servlet.http.HttpServletRequest)
 */
public void delete(JQuery db, HttpServletRequest req) throws ServletException, SQLException {
	// TODO Apéndice de método generado automáticamente
	
}
/* (sin Javadoc)
 * @see CosapiSoft.SARAWebBanking.GenericTable#getNameTable()
 */
public String getNameTable() {
	// TODO Apéndice de método generado automáticamente
	return null;
}
/* (sin Javadoc)
 * @see CosapiSoft.SARAWebBanking.GenericTable#getUrl()
 */
public String getUrl() {
	// TODO Apéndice de método generado automáticamente
	return null;
}
/* (sin Javadoc)
 * @see CosapiSoft.SARAWebBanking.GenericTable#insert(CosapiSoft.SARAWebBanking.JQuery)
 */
public void insert(JQuery db) throws SQLException {
	// TODO Apéndice de método generado automáticamente
	
}
/* (sin Javadoc)
 * @see CosapiSoft.SARAWebBanking.GenericTable#loadGrid(CosapiSoft.SARAWebBanking.JQuery)
 */
public void loadGrid(JQuery db) throws SQLException, Exception {
	// TODO Apéndice de método generado automáticamente
	
}
/* (sin Javadoc)
 * @see CosapiSoft.SARAWebBanking.GenericTable#loadGridChild(CosapiSoft.SARAWebBanking.JQuery)
 */
public void loadGridChild(JQuery db) throws SQLException {
	// TODO Apéndice de método generado automáticamente
	
}
/* (sin Javadoc)
 * @see CosapiSoft.SARAWebBanking.GenericTable#next(int)
 */
public void next(int index) {
	// TODO Apéndice de método generado automáticamente
	
}
/* (sin Javadoc)
 * @see CosapiSoft.SARAWebBanking.GenericTable#nextChild(int)
 */
public void nextChild(int index) {
	// TODO Apéndice de método generado automáticamente
	
}
/* (sin Javadoc)
 * @see CosapiSoft.SARAWebBanking.GenericTable#search(CosapiSoft.SARAWebBanking.JQuery)
 */
public boolean search(JQuery db) throws SQLException {
	// TODO Apéndice de método generado automáticamente
	return false;
}
/* (sin Javadoc)
 * @see CosapiSoft.SARAWebBanking.GenericTable#sort(CosapiSoft.SARAWebBanking.JQuery)
 */
public void sort(JQuery db) throws SQLException {
	// TODO Apéndice de método generado automáticamente
	
}
/* (sin Javadoc)
 * @see CosapiSoft.SARAWebBanking.GenericTable#update(CosapiSoft.SARAWebBanking.JQuery)
 */
public void update(JQuery db) throws SQLException {
	// TODO Apéndice de método generado automáticamente
	
}

}



