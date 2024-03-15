package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class Trahor extends GenericTable {
	private InicioSesion login;
	public java.util.Vector monedas = null;
	private String trahst = "";
	private String horini = "";
	private String horfin = "";
	private String dia = "";
	private String error = "";
	private String tra_hst = "";
	private String hor_ini = "";
	private String hor_fin = "";
	private String lim_dia = "";
	private String sta = "";
	private String nom_dia= "";
	
	/**
	 * AyudaDeCampo constructor comment.
	 */
	public Trahor() {
		super();
	}
	public void consult() throws java.sql.SQLException {
		try {
			String bef[] = { "", "", "", ""};
			String aft[] = { "", "", "", ""};
			LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", "ttrahor", Table.getColumnsTtrahor(), bef, aft);
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... consult() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	public void delete(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
			//if (search(db)) {
				String query = //
	            "Delete from "+Constante.ESQUEMA1+".TTRAHOR Where coddia = '" + getDia() + "' and codtra = '" + getTrahst() + "'";
				// System.out.println("delete limite-----------"+query);
				db.setQuery(query);
				db.executeUpdate();
				String bef[] = { getTrahst(), getDia(), getHorini(), getHorfin()};
				String aft[] = { "", "", "", ""};
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TTRAHOR", Table.getColumnsTtrahor(), bef, aft);
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
						trahst=var1.substring(0,4);
						dia=var1.substring(5,6);
						//System.out.println("transa.."+trahst);
						//System.out.println("dia.."+dia);
						String query = //
							"Delete from "+Constante.ESQUEMA1+".TTRAHOR Where coddia = '" + getDia() + "' and codtra = '" + getTrahst() + "'";
						// System.out.println("delete limite1-----------"+query);
						db.setQuery(query);
						db.executeUpdate();
						String bef[] = { getTrahst(), getDia(), getHorini(), getHorfin()};
						String aft[] = { "", "", "", ""};
						LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TTRAHOR", Table.getColumnsTtrahor(), bef, aft);
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
		return Constante.ESQUEMA1+".TTRAHOR";
	}
	public String getUrl() {
		return JspServlet.HORTRA_SERVLET+ "?BtnHor=Buscar&cboTransaccion=" + getTra_hst()+"&cboDia=" + getLim_dia();
	}
	public void insert(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
			if (!search(db)) {
				String query = //
					"insert into ttrahor(codtra,coddia,horini,horfin) values('"+getTrahst()+"','"+getDia()+"',TO_DATE('01/01/80 "+getHorini()+"', 'DD/MM/YYYY hh24:mi:ss'),TO_DATE('01/01/80 "+getHorfin()+"', 'DD/MM/YYYY hh24:mi:ss'))";
				db.setQuery(query);
				db.executeUpdate();
				String aft[] = { "", "", "", ""};
				String bef[] = { getTrahst(), getDia(), getHorini(), getHorfin()};
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TTRAHOR", Table.getColumnsTtrahor(), bef, aft);
				setGrid(null);
				loadGrid(db);
			} else {
				error = Mensajes.getMessage(Mensajes.HORDIA_REPETIR);
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
		"Select codtra, coddia, TO_CHAR(horini,'hh24:mi:ss'), TO_CHAR(horfin,'hh24:mi:ss') " + //
		"From "+Constante.ESQUEMA1+".TTRAHOR " + //
		"Where codtra = '" + getTrahst() + "' " + //
	    "Order by coddia";
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					java.util.Vector row = new java.util.Vector();
					row.addElement(getStatus());
					row.addElement(db.stringValue(1));
					row.addElement(db.stringValue(2));
					row.addElement(db.stringValue(3));
					row.addElement(db.stringValue(4));
					if(db.stringValue(2).equals("1")) row.addElement("Domingo");
					if(db.stringValue(2).equals("2")) row.addElement("Lunes");
					if(db.stringValue(2).equals("3")) row.addElement("Martes");
					if(db.stringValue(2).equals("4")) row.addElement("Miercoles");
					if(db.stringValue(2).equals("5")) row.addElement("Jueves");
					if(db.stringValue(2).equals("6")) row.addElement("Viernes");
					if(db.stringValue(2).equals("7")) row.addElement("Sábado");
					
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
		tra_hst= ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
		lim_dia = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
		hor_ini = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
		hor_fin = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
		nom_dia = ((java.util.Vector) getGrid().elementAt(i)).elementAt(5).toString();
		
	}
	public void nextChild(int i) {
	}
	public boolean search(JQuery db) throws java.sql.SQLException {
		try {
			String query = //
			"Select codtra, coddia, TO_CHAR(horini,'hh24:mi:ss'), TO_CHAR(horfin,'hh24:mi:ss')" + //
		    "From "+Constante.ESQUEMA1+".TTRAHOR "+
		    "Where coddia = '" + getDia() + "' and codtra = '" + getTrahst() + "'";
			db.setQuery(query);
			db.executeQuery();
			if (!db.getResultSet().next()) {
				return false;
			}
			
			trahst = db.stringValue(1);
			dia = db.stringValue(2);
			horini = db.stringValue(3);
			horfin = db.stringValue(4);
			
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
			
			String antTrahst = getTrahst();
			String antHorfin=getHorfin();
			String antHorini=getHorini();
			String antDia=getDia();
			
			if (search(db)) {
				String query = //
	            "Update "+Constante.ESQUEMA1+".TTRAHOR set horini = TO_DATE('01/01/80 "+antHorini+"', 'DD/MM/YYYY hh24:mi:ss'), horfin=TO_DATE('01/01/80 "+antHorfin+"', 'DD/MM/YYYY hh24:mi:ss') Where codtra = '" + getTrahst() + "' and coddia = '" + getDia() + "' ";
				// System.out.println("query update --------------"+query);
				db.setQuery(query);
				int rows = db.executeUpdate();
				String bef[] = { getTrahst(), getDia(),getHorini(),  getHorfin()};
				String aft[] = { getTrahst(), getDia(), antHorini,antHorfin};
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Modificar", Constante.ESQUEMA1+".TTRAHOR", Table.getColumnsTtrahor(), bef, aft);
				setGrid(null);
				loadGrid(db);
			}
			else{
				error = Mensajes.getMessage(Mensajes.HORDIA_MODIFICAR);
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
					String query = "Select codlim From ttrahor Order by codlim";
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
	public String getTra_hst() {
		return tra_hst;
	}
	public String getHor_ini() {
		return hor_ini;
	}
	public void setTra_hst(String tra_hst) {
		this.tra_hst = tra_hst;
	}
	public void setTrahst(String trahst) {
		this.trahst = trahst;
	}
	public void setHor_ini(String hor_ini) {
		this.hor_ini = hor_ini;
	}
	
	public void setHorini(String horini) {
		this.horini = horini;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getTrahst() {
		return trahst;
	}
	
	public String getHorini() {
		return horini;
	}
	public String getDia() {
		return dia;
	}
	/**
	 * @return Returns the hor_fin.
	 */
	public String getHor_fin() {
		return hor_fin;
	}
	/**
	 * @param hor_fin The hor_fin to set.
	 */
	public void setHor_fin(String hor_fin) {
		this.hor_fin = hor_fin;
	}
	/**
	 * @return Returns the horfin.
	 */
	public String getHorfin() {
		return horfin;
	}
	/**
	 * @param horfin The horfin to set.
	 */
	public void setHorfin(String horfin) {
		this.horfin = horfin;
	}
	/**
	 * @return Returns the lim_dia.
	 */
	public String getLim_dia() {
		return lim_dia;
	}
	/**
	 * @param lim_dia The lim_dia to set.
	 */
	public void setLim_dia(String lim_dia) {
		this.lim_dia = lim_dia;
	}
	/**
	 * @return Returns the nomdia.
	 */
	public String getNom_dia() {
		return nom_dia;
	}
	/**
	 * @param nomdia The nomdia to set.
	 */
	public void setNom_dia(String nom_dia) {
		this.nom_dia = nom_dia;
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
}