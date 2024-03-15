package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class MsgHst extends GenericTable {
	private InicioSesion login;
	public java.util.Vector monedas = null;
	/**
	 * @return Returns the cod_aplicacion.
	 */
	public String getCod_aplicacion() {
		return cod_aplicacion;
	}
	/**
	 * @param cod_aplicacion The cod_aplicacion to set.
	 */
	public void setCod_aplicacion(String cod_aplicacion) {
		this.cod_aplicacion = cod_aplicacion;
	}
	/**
	 * @return Returns the cod_retorno.
	 */
	public String getCod_retorno() {
		return cod_retorno;
	}
	/**
	 * @param cod_retorno The cod_retorno to set.
	 */
	public void setCod_retorno(String cod_retorno) {
		this.cod_retorno = cod_retorno;
	}
	/**
	 * @return Returns the codaplicacion.
	 */
	public String getCodaplicacion() {
		return codaplicacion;
	}
	/**
	 * @param codaplicacion The codaplicacion to set.
	 */
	public void setCodaplicacion(String codaplicacion) {
		this.codaplicacion = codaplicacion;
	}
	/**
	 * @return Returns the codretorno.
	 */
	public String getCodretorno() {
		return codretorno;
	}
	/**
	 * @param codretorno The codretorno to set.
	 */
	public void setCodretorno(String codretorno) {
		this.codretorno = codretorno;
	}
	/**
	 * @return Returns the des_msgfront.
	 */
	public String getDes_msgfront() {
		return des_msgfront;
	}
	/**
	 * @param des_msgfront The des_msgfront to set.
	 */
	public void setDes_msgfront(String des_msgfront) {
		this.des_msgfront = des_msgfront;
	}
	/**
	 * @return Returns the des_msgtold.
	 */
	public String getDes_msgtold() {
		return des_msgtold;
	}
	/**
	 * @param des_msgtold The des_msgtold to set.
	 */
	public void setDes_msgtold(String des_msgtold) {
		this.des_msgtold = des_msgtold;
	}
	/**
	 * @return Returns the desmsgfront.
	 */
	public String getDesmsgfront() {
		return desmsgfront;
	}
	/**
	 * @param desmsgfront The desmsgfront to set.
	 */
	public void setDesmsgfront(String desmsgfront) {
		this.desmsgfront = desmsgfront;
	}
	/**
	 * @return Returns the desmsgtold.
	 */
	public String getDesmsgtold() {
		return desmsgtold;
	}
	/**
	 * @param desmsgtold The desmsgtold to set.
	 */
	public void setDesmsgtold(String desmsgtold) {
		this.desmsgtold = desmsgtold;
	}
	/**
	 * @return Returns the nom_desmsgfront.
	 */
	public String getNom_desmsgfront() {
		return nom_desmsgfront;
	}
	/**
	 * @param nom_desmsgfront The nom_desmsgfront to set.
	 */
	public void setNom_desmsgfront(String nom_desmsgfront) {
		this.nom_desmsgfront = nom_desmsgfront;
	}
	private String codaplicacion = "";
	private String codretorno = "";
	private String desmsgtold = "";
	private String desmsgfront = "";
	private String error = "";
	private String cod_aplicacion = "";
	private String cod_retorno = "";
	private String des_msgtold = "";
	private String des_msgfront = "";
	private String sta = "";
	private String nom_desmsgfront= "";
	
	/**
	 * AyudaDeCampo constructor comment.
	 */
	public MsgHst() {
		super();
	}
	public void consult() throws java.sql.SQLException {
		try {
			String bef[] = { "", "", "", ""};
			String aft[] = { "", "", "", ""};
			LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", "tmsghst", Table.getColumnsTmsghst(), bef, aft);
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... consult() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	public void delete(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
			//if (search(db)) {
				String query = //
	            "Delete from "+Constante.ESQUEMA1+".TMSGHST Where codretorno = '" + getCodretorno() + "' and codaplicacion = '" + getCodaplicacion() + "'";
				// System.out.println("delete limite-----------"+query);
				db.setQuery(query);
				db.executeUpdate();
				String bef[] = { getCodaplicacion(), getCodretorno(), getDesmsgtold(), getDesmsgfront()};
				String aft[] = { "", "", "", ""};
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TMSGHST", Table.getColumnsTmsghst(), bef, aft);
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
				String var1 = vector.nextElement().toString();
				// System.out.println("cadena completa......"+var1);
				if(var1.indexOf("-") > -1){
				  sw = req.getParameter(var1).toUpperCase();
				  if (sw.equals("ON")) {
						codaplicacion=var1.substring(0,2);
						codretorno=var1.substring(3,7);
						System.out.println("codaplicacion.."+codaplicacion);
						System.out.println("codretorno.."+codretorno);
						String query = //
							 "Delete from "+Constante.ESQUEMA1+".TMSGHST Where codretorno = '" + getCodretorno() + "' and codaplicacion = '" + getCodaplicacion() + "'";
						// System.out.println("delete limite1-----------"+query);
						db.setQuery(query);
						db.executeUpdate();
						String bef[] = { getCodaplicacion(), getCodretorno(), getDesmsgtold(), getDesmsgfront()};
						String aft[] = { "", "", "", ""};
						LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TMSGHST", Table.getColumnsTmsghst(), bef, aft);
						setGrid(null);
				  }
			    }
			}
			loadGrid(db);
		} catch (Exception e) {
			e.printStackTrace();
			throw new java.sql.SQLException("\n... delete() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	
	public String getNameTable() {
		return Constante.ESQUEMA1+".TMSGHST";
	}
	public String getUrl() {
		return JspServlet.MSGHST_SERVLET+ "?BtnHst=Buscar&txtCodaplicacion=" + getCod_aplicacion()+"&txtCodretorno=" + getCod_retorno();
	}
	public void insert(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
			if (!search(db)) {
				String query = //
					"insert into tmsghst(codaplicacion,desmsgfront,codretorno,desmsgtold) values('"+getCodaplicacion()+"','"+getDesmsgfront()+"','"+getCodretorno()+"','"+getDesmsgtold()+"')";
				db.setQuery(query);
				db.executeUpdate();
				String aft[] = { "", "", "", ""};
				String bef[] = { getCodaplicacion(), getCodretorno(), getDesmsgtold(), getDesmsgfront()};
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TMSGHST", Table.getColumnsTmsghst(), bef, aft);
				setGrid(null);
				loadGrid(db);
			} else {
				error = Mensajes.getMessage(Mensajes.MSGHST_REPETIR);
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
		"Select codaplicacion, desmsgfront, codretorno, desmsgtold " + //
		"From "+Constante.ESQUEMA1+".TMSGHST " + //
	    "Order by codaplicacion";
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					java.util.Vector row = new java.util.Vector();
					row.addElement(getStatus());
					row.addElement(db.stringValue(1));
					row.addElement(db.stringValue(2));
					row.addElement(db.stringValue(3));
					row.addElement(db.stringValue(4));
					
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
		cod_aplicacion= ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
		des_msgfront = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
		cod_retorno = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
		des_msgtold = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
		
	}
	public void nextChild(int i) {
	}
	public boolean search(JQuery db) throws java.sql.SQLException {
		try {
			String query = //
			"Select codaplicacion, desmsgfront, codretorno, desmsgtold " + //
		    "From "+Constante.ESQUEMA1+".TMSGHST "+
		    "Where codretorno = '" + getCodretorno() + "' and codaplicacion = '" + getCodaplicacion() + "'";
			db.setQuery(query);
			db.executeQuery();
			if (!db.getResultSet().next()) {
				return false;
			}
			
			codaplicacion = db.stringValue(1);
			desmsgfront = db.stringValue(2);
			codretorno = db.stringValue(3);
			desmsgtold = db.stringValue(4);
			
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
			
			String antCodaplicacion = getCodaplicacion();
			String antCodretorno=getCodretorno();
			String antDesmsgfront=getDesmsgfront();
			String antDesmsgtold=getDesmsgtold();
			
			if (search(db)) {
				String query = //
	            "Update "+Constante.ESQUEMA1+".TMSGHST set desmsgfront = '"+antDesmsgfront+"', desmsgtold='"+antDesmsgtold+"' Where codaplicacion = '" + getCodaplicacion() + "' and codretorno = '" + getCodretorno() + "' ";
				// System.out.println("query update --------------"+query);
				db.setQuery(query);
				int rows = db.executeUpdate();
				String bef[] = { getCodaplicacion(), getCodretorno(), getDesmsgtold(), getDesmsgfront()};
				String aft[] = { getCodaplicacion(), getCodretorno(), antDesmsgtold, antDesmsgfront};
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Modificar", Constante.ESQUEMA1+".TMSGHST", Table.getColumnsTmsghst(), bef, aft);
				setGrid(null);
				loadGrid(db);
			}
			else{
				error = Mensajes.getMessage(Mensajes.MSGHST_MODIFICAR);
			}
		
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... update() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}

	

	

	/**
	 * Returns the codlim.
	 * @return String
	 */
	/*public String getCodlim() {
		try {
			if (codlim == null || codlim.equals(""))
				codlim = "0";
			codlim = codlim.trim();
			if (!CosapiSoft.SARAWebBanking.CString.isInteger(codlim)) {
				codlim = "0";
			}
			long cod = new Integer(codlim).intValue();
			if (cod <= 0) {
				InicioSesion.loadParameters();
				JDatabase jd = new JDatabase();
				JQuery db = new JQuery(jd.getConnection());
				try {
					String query = "Select codlim From tmsghst Order by codlim";
					db.setQuery(query);
					db.executeQuery();
					while (db.getResultSet().next()) {
						codlim = db.stringValue(1).trim();
					}
					cod = new Integer(codlim).intValue();
					codlim = (cod >= 99999) ? "99999" : "" + (cod + 1);
				} catch (Exception ex) {
					System.out.println("\n... " + getClass().getName() + " - getCodlim() --> " + ex.getMessage());
					return "-1";
				} finally {
					db.close();
					jd.close();
				}
			}
			if (!codlim.equals(""))
				codlim = CString.completeToBeginWithNChars(codlim, 5, '0');
			return codlim.trim();
		} catch (Exception e) {
			return "-1";
		}
	}*/

	/**
	 * Returns the txt_lim.
	 * @return String
	 */

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
}