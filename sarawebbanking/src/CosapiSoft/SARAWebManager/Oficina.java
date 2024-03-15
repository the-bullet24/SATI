/*
 * Created on 15/11/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package CosapiSoft.SARAWebManager;

import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.GenericTable;
import CosapiSoft.SARAWebBanking.InicioSesion;
import CosapiSoft.SARAWebBanking.JQuery;
import CosapiSoft.SARAWebBanking.JspServlet;
import CosapiSoft.SARAWebBanking.LogOpe;
import CosapiSoft.SARAWebBanking.Mensajes;
import CosapiSoft.SARAWebBanking.Table;

/**
 * @author cosapi_ati08
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Oficina extends GenericTable {
	private InicioSesion login;
	private String cubigeo = "";
	private String coddep4 = "";
	private String aoficina = "";
	private String coficina = "";
	private String flgmoneda = "";
	private String error = "";
	private String cod_ubigeo = "";
	private String cod_dep4 = "";
	private String f01_aoficina = "";
	private String f01_coficina = "";
	private String flg_moneda = "";

	
	public Oficina() {
		super();
	}
	
	public void consult() throws java.sql.SQLException {
		try {
			String bef[] = { "", "", "", "", ""};
			String aft[] = { "", "", "", "", ""};
			LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", "tofidat", Table.getColumnsTofidat(), bef, aft);
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... consult() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	
	public void delete(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
			//if (search(db)) {
	
				String query = //
					"Delete from "+Constante.ESQUEMA1+".TAGEDAT Where COD_DEP4 = '" + getCoddep4() + "'";
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = { getCubigeo(), getCoddep4(), getAoficina(), getCoficina(), getFlgmoneda()};
					String aft[] = { "", "", "", "", ""};
					LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TAGEDAT", Table.getColumnsTofidat(), bef, aft);
					setGrid(null);
			/*} else {
				String str = Mensajes.getMessage(Mensajes.CODIGO_DE_AYUDA_NO_EXISTE);
				int pos = str.indexOf('&');
				setError(str.substring(0, pos) + getTypper() + str.substring(pos + 1));
			}*/
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... delete() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	public void delete(JQuery db, javax.servlet.http.HttpServletRequest req) throws java.sql.SQLException {
		try {
			setError("");
			String sw="";
			java.util.Enumeration vector = req.getParameterNames();
			while (vector.hasMoreElements() && vector != null) {
				coddep4 = vector.nextElement().toString();
				sw = req.getParameter(coddep4).toUpperCase();
				
				  if (sw.equals("ON")) {
						String query = //
						"Delete from "+Constante.ESQUEMA1+".TAGEDAT Where COD_DEP4 = '" + getCoddep4() + "'";
						db.setQuery(query);
						db.executeUpdate();
						String bef[] = { getCubigeo(), getCoddep4(), getAoficina(), getCoficina(), getFlgmoneda()};
						String aft[] = { "", "", "", "", ""};
						LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TAGEDAT", Table.getColumnsTofidat(), bef, aft);
						setGrid(null);
				  }
			    
			}
			loadGrid(db);
		} catch (Exception e) {
			e.printStackTrace();
			throw new java.sql.SQLException("\n... delete() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	public String getNameTable() {
		return Constante.ESQUEMA1+".TAGEDAT";
	}
	public String getUrl() {
		return JspServlet.OFICINA_SERVLET+ "?BtnOfi=Buscar&txtCoddep4=" + getCod_dep4();
	}
	public void insert(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
			if (!search(db)) {
				String query = //
					"insert into " + Constante.ESQUEMA1+ ".TAGEDAT(COD_UBIGEO,COD_DEP4,F01_AOFICINA,F01_COFICINA, FLG_MONEDA) values('"+getCubigeo()+"','"+getCoddep4()+"','"+getAoficina()+"','"+getCoficina()+"','"+getFlgmoneda()+"')";
				db.setQuery(query);
				db.executeUpdate();
				String aft[] = { "", "", "", "", ""};
				String bef[] = { getCubigeo(), getCoddep4(), getAoficina(), getCoficina(), getFlgmoneda()};
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TAGEDAT", Table.getColumnsTofidat(), bef, aft);
				setGrid(null);
				loadGrid(db);
			} else {
				error = Mensajes.getMessage(Mensajes.OFIDAT_REPETIR);
			}
		} 
		catch (Exception e) {
			throw new java.sql.SQLException("\n... insert() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	
	public void loadGrid(JQuery db) throws java.sql.SQLException {
		setIndex(0);
		setStatus("0");
		if (getGrid().size() == 0) {
			try {
					String query = //
		"Select COD_UBIGEO,COD_DEP4,F01_AOFICINA,F01_COFICINA, FLG_MONEDA " + //
		"From "+Constante.ESQUEMA1+".TAGEDAT " + //
	    "Order by F01_AOFICINA";
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					java.util.Vector row = new java.util.Vector();
					row.addElement(getStatus());
					row.addElement(db.stringValue(1));
					row.addElement(db.stringValue(2));
					row.addElement(db.stringValue(3));
					row.addElement(db.stringValue(4));
					row.addElement(db.stringValue(5));
					
					getGrid().addElement(row);
				}
			} catch (Exception e) {
				throw new java.sql.SQLException("\n... loadGrid() - " + getClass().getName() + " -> " + e.getMessage());
			}
		}
	}
	
	public void loadGridChild(JQuery db) throws java.sql.SQLException {
	}

	public void next(int i) {
		setStatus(((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString());
		cod_ubigeo= ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
		cod_dep4 = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
		f01_aoficina = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
		f01_coficina = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
		flg_moneda = ((java.util.Vector) getGrid().elementAt(i)).elementAt(5).toString();
		if(flg_moneda.equals("")){
			flg_moneda="PEI-USD";
		}
		
	}
	public void nextChild(int i) {
	}
	
	public boolean search(JQuery db) throws java.sql.SQLException {
		try {
			String query = //
			"Select COD_UBIGEO,COD_DEP4,F01_AOFICINA,F01_COFICINA, FLG_MONEDA " + //
		    "From "+Constante.ESQUEMA1+".TAGEDAT "+
		    "Where trim(COD_DEP4) = '" + getCoddep4() + "'";
			db.setQuery(query);
			db.executeQuery();
			if (!db.getResultSet().next()) {
				return false;
			}
			
			cubigeo = db.stringValue(1);
			coddep4 = db.stringValue(2);
			aoficina = db.stringValue(3);
			coficina = db.stringValue(4);
			flgmoneda = db.stringValue(5);
			
			return true;
		} 
		catch (Exception e) {
			throw new java.sql.SQLException("\n... search() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	
	public void sort(JQuery db) throws java.sql.SQLException {
	}
	public void update(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
			
			String antFlgmoneda = getFlgmoneda();
			String antCubigeo=getCubigeo();
			String antAoficina=getAoficina();
			String antCoficina=getCoficina();
			
			if (search(db)) {
				String query = //
	            "Update "+Constante.ESQUEMA1+".TAGEDAT set flg_moneda = '"+antFlgmoneda+"', f01_aoficina='"+antAoficina+"', f01_coficina='"+antCoficina+"', cod_ubigeo='"+antCubigeo+"' Where cod_dep4 = '" + getCoddep4() + "' ";
				
				db.setQuery(query);
				int rows = db.executeUpdate();
				String bef[] = { getCubigeo(), getCoddep4(), getAoficina(), getCoficina(), getFlgmoneda()};
				String aft[] = { antCubigeo, getCoddep4(), antAoficina, antCoficina,antFlgmoneda };
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Modificar", Constante.ESQUEMA1+".TAGEDAT", Table.getColumnsTofidat(), bef, aft);
				setGrid(null);
				loadGrid(db);
			}
			else{
				error = Mensajes.getMessage(Mensajes.OFIDAT_MODIFICAR);
			}
		
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... update() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	/**
	 * @return Returns the aoficina.
	 */
	public String getAoficina() {
		return aoficina;
	}
	/**
	 * @param aoficina The aoficina to set.
	 */
	public void setAoficina(String aoficina) {
		this.aoficina = aoficina;
	}
	/**
	 * @return Returns the cod_dep4.
	 */
	public String getCod_dep4() {
		return cod_dep4;
	}
	/**
	 * @param cod_dep4 The cod_dep4 to set.
	 */
	public void setCod_dep4(String cod_dep4) {
		this.cod_dep4 = cod_dep4;
	}
	/**
	 * @return Returns the cod_ubigeo.
	 */
	public String getCod_ubigeo() {
		return cod_ubigeo;
	}
	/**
	 * @param cod_ubigeo The cod_ubigeo to set.
	 */
	public void setCod_ubigeo(String cod_ubigeo) {
		this.cod_ubigeo = cod_ubigeo;
	}
	/**
	 * @return Returns the coddep4.
	 */
	public String getCoddep4() {
		return coddep4;
	}
	/**
	 * @param coddep4 The coddep4 to set.
	 */
	public void setCoddep4(String coddep4) {
		this.coddep4 = coddep4;
	}
	/**
	 * @return Returns the coficina.
	 */
	public String getCoficina() {
		return coficina;
	}
	/**
	 * @param coficina The coficina to set.
	 */
	public void setCoficina(String coficina) {
		this.coficina = coficina;
	}
	/**
	 * @return Returns the cubigeo.
	 */
	public String getCubigeo() {
		return cubigeo;
	}
	/**
	 * @param cubigeo The cubigeo to set.
	 */
	public void setCubigeo(String cubigeo) {
		this.cubigeo = cubigeo;
	}
	/**
	 * @return Returns the error.
	 */
	public String getError() {
		return error;
	}
	/**
	 * @param error The error to set.
	 */
	public void setError(String error) {
		this.error = error;
	}
	/**
	 * @return Returns the f01_aoficina.
	 */
	public String getF01_aoficina() {
		return f01_aoficina;
	}
	/**
	 * @param f01_aoficina The f01_aoficina to set.
	 */
	public void setF01_aoficina(String f01_aoficina) {
		this.f01_aoficina = f01_aoficina;
	}
	/**
	 * @return Returns the f01_coficina.
	 */
	public String getF01_coficina() {
		return f01_coficina;
	}
	/**
	 * @param f01_coficina The f01_coficina to set.
	 */
	public void setF01_coficina(String f01_coficina) {
		this.f01_coficina = f01_coficina;
	}
	/**
	 * @return Returns the flg_moneda.
	 */
	public String getFlg_moneda() {
		return flg_moneda;
	}
	/**
	 * @param flg_moneda The flg_moneda to set.
	 */
	public void setFlg_moneda(String flg_moneda) {
		this.flg_moneda = flg_moneda;
	}
	/**
	 * @return Returns the flgmoneda.
	 */
	public String getFlgmoneda() {
		return flgmoneda;
	}
	/**
	 * @param flgmoneda The flgmoneda to set.
	 */
	public void setFlgmoneda(String flgmoneda) {
		this.flgmoneda = flgmoneda;
	}
	/**
	 * @return Returns the login.
	 */
	public InicioSesion getLogin() {
		return login;
	}
	/**
	 * @param login The login to set.
	 */
	public void setLogin(InicioSesion login) {
		this.login = login;
	}
}
