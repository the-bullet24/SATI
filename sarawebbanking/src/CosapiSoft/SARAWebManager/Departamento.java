
package CosapiSoft.SARAWebManager;


import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;


public class Departamento extends GenericTable {
	private InicioSesion login;
	private String cubigeo = "";
	private String cdepartamento = "";
	private String departamento = "";
	private String cprovincia = "";
	private String provincia = "";
	private String cdistrito = "";
	private String distrito = "";
	private String zonal = "";
	private String ubigeo = "";
	private String cregion = "";
	private String error = "";
	private String f02_cubigeo = "";
	private String f02_cdepartamento = "";
	private String f02_departamento = "";
	private String f02_cprovincia = "";
	private String f02_provincia = "";
	private String f02_cdistrito = "";
	private String f02_distrito = "";
	private String f02_zonal = "";
	private String f02_ubigeo = "";
	private String f02_cregion = "";
	
	public Departamento() {
		super();
	}
	
	public void consult() throws java.sql.SQLException {
		try {
			String bef[] = { "", "", "", "", "", "", "", "", "", ""};
			String aft[] = { "", "", "", "", "", "", "", "", "", ""};
			LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", "tdepdat", Table.getColumnsTdepdat(), bef, aft);
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... consult() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	public void delete(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
			//if (search(db)) {
				String query = //
					"Delete from "+Constante.ESQUEMA1+".TDEPDAT Where f02_cubigeo = '" + getCubigeo()+ "'";
				// System.out.println("delete limite-----------"+query);
				db.setQuery(query);
				db.executeUpdate();
				String bef[] = { getCubigeo(), getCdepartamento(), getDepartamento(), getCprovincia(), getProvincia(), getCdistrito(), getDistrito(), getZonal(), getUbigeo(), getCregion()};
				String aft[] = { "", "", "", "", "", "", "", "", "", ""};
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TDEPDAT", Table.getColumnsTdepdat(), bef, aft);
				setGrid(null);
				loadGrid(db);
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
				cubigeo = vector.nextElement().toString();
				sw = req.getParameter(cubigeo).toUpperCase();
				  if (sw.equals("ON")) {
						String query = //
							 "Delete from "+Constante.ESQUEMA1+".TDEPDAT Where f02_cubigeo = '" + getCubigeo()+ "'";
						db.setQuery(query);
						db.executeUpdate();
						
						String bef[] = { getCubigeo(), getCdepartamento(), getDepartamento(), getCprovincia(), getProvincia(), getCdistrito(), getDistrito(), getZonal(), getUbigeo(), getCregion()};
						String aft[] = { "", "", "", "", "", "", "", "", "", ""};
						LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TDEPDAT", Table.getColumnsTdepdat(), bef, aft);
						setGrid(null);
				  }
			    
			}
			loadGrid(db);
		} catch (Exception e) {
			e.printStackTrace();
			throw new java.sql.SQLException("\n... delete() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	public void insert(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
			if (!search(db)) {
				String query = //
					"insert into tdepdat(f02_cubigeo,f02_cdepartamento,f02_departamento,f02_cprovincia, f02_provincia, f02_cdistrito, f02_distrito, f02_zonal, f02_ubigeo_bnsif50, f02_cregion) values('"+getCubigeo()+"','"+getCdepartamento()+"','"+getDepartamento()+"','"+getCprovincia()+"','"+getProvincia()+"','"+getCdistrito()+"','"+getDistrito()+"','"+getZonal()+"','"+getUbigeo()+"','"+getCregion()+"')";
				db.setQuery(query);
				db.executeUpdate();
				String aft[] = { "", "", "", "", "", "", "", "", "", ""};
				String bef[] = { getCubigeo(), getCdepartamento(), getDepartamento(), getCprovincia(), getProvincia(), getCdistrito(), getDistrito(), getZonal(), getUbigeo(), getCregion()};
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TDEPDAT", Table.getColumnsTdepdat(), bef, aft);
				setGrid(null);
				loadGrid(db);
			} else {
				error = Mensajes.getMessage(Mensajes.DEPDAT_REPETIR);
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
		"Select f02_cubigeo,f02_cdepartamento,f02_departamento,f02_cprovincia, f02_provincia, f02_cdistrito, f02_distrito, f02_zonal, f02_ubigeo_bnsif50, f02_cregion " + //
		"From "+Constante.ESQUEMA1+".TDEPDAT " + //
	    "Order by f02_departamento";
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
					row.addElement(db.stringValue(6));
					row.addElement(db.stringValue(7));
					row.addElement(db.stringValue(8));
					row.addElement(db.stringValue(9));
					row.addElement(db.stringValue(10));
					
					getGrid().addElement(row);
				}
			} catch (Exception e) {
				throw new java.sql.SQLException("\n... loadGrid() - " + getClass().getName() + " -> " + e.getMessage());
			}
		}
	}
	public void next(int i) {
		setStatus(((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString());
		f02_cubigeo= ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
		f02_cdepartamento = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
		f02_departamento = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
		f02_cprovincia = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
		f02_provincia = ((java.util.Vector) getGrid().elementAt(i)).elementAt(5).toString();
		f02_cdistrito = ((java.util.Vector) getGrid().elementAt(i)).elementAt(6).toString();
		f02_distrito = ((java.util.Vector) getGrid().elementAt(i)).elementAt(7).toString();
		f02_zonal = ((java.util.Vector) getGrid().elementAt(i)).elementAt(8).toString(); 
		f02_ubigeo = ((java.util.Vector) getGrid().elementAt(i)).elementAt(9).toString();
		f02_cregion = ((java.util.Vector) getGrid().elementAt(i)).elementAt(10).toString();
	}
	
	
	
	public boolean search(JQuery db) throws java.sql.SQLException {
		try {
			String query = //
				"Select f02_cubigeo,f02_cdepartamento,f02_departamento,f02_cprovincia, f02_provincia, f02_cdistrito, f02_distrito, f02_zonal, f02_ubigeo_bnsif50, f02_cregion " + //
		    "From "+Constante.ESQUEMA1+".TDEPDAT "+
		    "Where f02_cubigeo = '" + getCubigeo() + "'";
			db.setQuery(query);
			db.executeQuery();
			if (!db.getResultSet().next()) {
				return false;
			}
			
			cubigeo= db.stringValue(1);
			cdepartamento = db.stringValue(2);
			departamento = db.stringValue(3);
			cprovincia = db.stringValue(4);
			provincia = db.stringValue(5);
			cdistrito = db.stringValue(6);
			distrito = db.stringValue(7);
			zonal = db.stringValue(8); 
			ubigeo = db.stringValue(9);
			cregion = db.stringValue(10);
			
			return true;
		} 
		catch (Exception e) {
			throw new java.sql.SQLException("\n... search() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	
	public void update(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
			
			String antCdepartamento=getCdepartamento();
			String antDepartamento=getDepartamento();
			String antCprovincia=getCprovincia();
			String antProvincia=getProvincia();
			String antCdistrito=getCdistrito();
			String antDistrito=getDistrito();
			String antZonal=getZonal();
			String antUbigeo=getUbigeo();
			String antCregion=getCregion();
			
			if (search(db)) {
				String query = //
	            "Update "+Constante.ESQUEMA1+".TDEPDAT set f02_cdepartamento = '"+antCdepartamento+"', f02_departamento='"+antDepartamento+"', f02_cprovincia='"+antCprovincia+"', f02_provincia='"+antProvincia+"', f02_cdistrito='"+antCdistrito+"', f02_distrito='"+antDistrito+"', f02_zonal='"+antZonal+"', f02_ubigeo_bnsif50='"+antUbigeo+"', f02_cregion='"+antCregion+"' Where f02_cubigeo = '" + getCubigeo() + "' ";
				db.setQuery(query);
				int rows = db.executeUpdate();
				String bef[] = { getCubigeo(), getCdepartamento(), getDepartamento(), getCprovincia(), getProvincia(), getCdistrito(), getDistrito(), getZonal(), getUbigeo(), getCregion()};
				String aft[] = { getCubigeo(), antCdepartamento, antDepartamento, antCprovincia, antProvincia, antCdistrito, antDistrito, antZonal, antUbigeo, antCregion};
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Modificar", Constante.ESQUEMA1+".TDEPDAT", Table.getColumnsTdepdat(), bef, aft);
				setGrid(null);
				loadGrid(db);
			}
			else{
				error = Mensajes.getMessage(Mensajes.DEPDAT_MODIFICAR);
			}
		
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... update() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	
	
	public void sort(JQuery db) throws java.sql.SQLException {
	}
	public void loadGridChild(JQuery db) throws java.sql.SQLException {
	}
	public String getNameTable() {
		return Constante.ESQUEMA1+".TDEPDAT";
	}
	public String getUrl() {
		return JspServlet.DEPARTAMENTO_SERVLET+ "?BtnDep=Buscar&txtCubigeo=" + getF02_cubigeo();
	}
	
	/**
	 * @return Returns the cdepartamento.
	 */
	public String getCdepartamento() {
		return cdepartamento;
	}
	/**
	 * @param cdepartamento The cdepartamento to set.
	 */
	public void setCdepartamento(String cdepartamento) {
		this.cdepartamento = cdepartamento;
	}
	/**
	 * @return Returns the cdistrito.
	 */
	public String getCdistrito() {
		return cdistrito;
	}
	/**
	 * @param cdistrito The cdistrito to set.
	 */
	public void setCdistrito(String cdistrito) {
		this.cdistrito = cdistrito;
	}
	/**
	 * @return Returns the cprovincia.
	 */
	public String getCprovincia() {
		return cprovincia;
	}
	/**
	 * @param cprovincia The cprovincia to set.
	 */
	public void setCprovincia(String cprovincia) {
		this.cprovincia = cprovincia;
	}
	/**
	 * @return Returns the cregion.
	 */
	public String getCregion() {
		return cregion;
	}
	/**
	 * @param cregion The cregion to set.
	 */
	public void setCregion(String cregion) {
		this.cregion = cregion;
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
	 * @return Returns the departamento.
	 */
	public String getDepartamento() {
		return departamento;
	}
	/**
	 * @param departamento The departamento to set.
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	/**
	 * @return Returns the distrito.
	 */
	public String getDistrito() {
		return distrito;
	}
	/**
	 * @param distrito The distrito to set.
	 */
	public void setDistrito(String distrito) {
		this.distrito = distrito;
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
	 * @return Returns the f02_cdepartamento.
	 */
	public String getF02_cdepartamento() {
		return f02_cdepartamento;
	}
	/**
	 * @param f02_cdepartamento The f02_cdepartamento to set.
	 */
	public void setF02_cdepartamento(String f02_cdepartamento) {
		this.f02_cdepartamento = f02_cdepartamento;
	}
	/**
	 * @return Returns the f02_cdistrito.
	 */
	public String getF02_cdistrito() {
		return f02_cdistrito;
	}
	/**
	 * @param f02_cdistrito The f02_cdistrito to set.
	 */
	public void setF02_cdistrito(String f02_cdistrito) {
		this.f02_cdistrito = f02_cdistrito;
	}
	/**
	 * @return Returns the f02_cprovincia.
	 */
	public String getF02_cprovincia() {
		return f02_cprovincia;
	}
	/**
	 * @param f02_cprovincia The f02_cprovincia to set.
	 */
	public void setF02_cprovincia(String f02_cprovincia) {
		this.f02_cprovincia = f02_cprovincia;
	}
	/**
	 * @return Returns the f02_cregion.
	 */
	public String getF02_cregion() {
		return f02_cregion;
	}
	/**
	 * @param f02_cregion The f02_cregion to set.
	 */
	public void setF02_cregion(String f02_cregion) {
		this.f02_cregion = f02_cregion;
	}
	/**
	 * @return Returns the f02_cubigeo.
	 */
	public String getF02_cubigeo() {
		return f02_cubigeo;
	}
	/**
	 * @param f02_cubigeo The f02_cubigeo to set.
	 */
	public void setF02_cubigeo(String f02_cubigeo) {
		this.f02_cubigeo = f02_cubigeo;
	}
	/**
	 * @return Returns the f02_departamento.
	 */
	public String getF02_departamento() {
		return f02_departamento;
	}
	/**
	 * @param f02_departamento The f02_departamento to set.
	 */
	public void setF02_departamento(String f02_departamento) {
		this.f02_departamento = f02_departamento;
	}
	/**
	 * @return Returns the f02_distrito.
	 */
	public String getF02_distrito() {
		return f02_distrito;
	}
	/**
	 * @param f02_distrito The f02_distrito to set.
	 */
	public void setF02_distrito(String f02_distrito) {
		this.f02_distrito = f02_distrito;
	}
	/**
	 * @return Returns the f02_provincia.
	 */
	public String getF02_provincia() {
		return f02_provincia;
	}
	/**
	 * @param f02_provincia The f02_provincia to set.
	 */
	public void setF02_provincia(String f02_provincia) {
		this.f02_provincia = f02_provincia;
	}
	/**
	 * @return Returns the f02_ubigeo.
	 */
	public String getF02_ubigeo() {
		return f02_ubigeo;
	}
	/**
	 * @param f02_ubigeo The f02_ubigeo to set.
	 */
	public void setF02_ubigeo(String f02_ubigeo) {
		this.f02_ubigeo = f02_ubigeo;
	}
	/**
	 * @return Returns the f02_zonal.
	 */
	public String getF02_zonal() {
		return f02_zonal;
	}
	/**
	 * @param f02_zonal The f02_zonal to set.
	 */
	public void setF02_zonal(String f02_zonal) {
		this.f02_zonal = f02_zonal;
	}
	/**
	 * @return Returns the provincia.
	 */
	public String getProvincia() {
		return provincia;
	}
	/**
	 * @param provincia The provincia to set.
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	/**
	 * @return Returns the ubigeo.
	 */
	public String getUbigeo() {
		return ubigeo;
	}
	/**
	 * @param ubigeo The ubigeo to set.
	 */
	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}
	/**
	 * @return Returns the zonal.
	 */
	public String getZonal() {
		return zonal;
	}
	/**
	 * @param zonal The zonal to set.
	 */
	public void setZonal(String zonal) {
		this.zonal = zonal;
	}

	
	public void nextChild(int index) {
		
	}
	
}
