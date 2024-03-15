package CosapiSoft.SARAWebBanking;

/**
 * 
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class Menu extends GenericTable {
	private InicioSesion login;
	public java.util.Vector monedas = null;
	/**
	 * @return Returns the grid1.
	 */
	public java.util.Vector getGrid1() {
		return grid1;
	}
	/**
	 * @param grid1 The grid1 to set.
	 */
	public void setGrid1(java.util.Vector grid1) {
		this.grid1 = grid1;
	}
	public java.util.Vector grid1 = null;
	private String codper = "";
	private String codtar = "";
	private String codopt = "";
	private String optmen = "";
	private String txtmen = "";
	private String codpad = "";
	private String ordmen = "";
	private String flgact = "";
	/**
	 * @return Returns the cod_opt.
	 */
	public String getCod_opt() {
		return cod_opt;
	}
	/**
	 * @param cod_opt The cod_opt to set.
	 */
	public void setCod_opt(String cod_opt) {
		this.cod_opt = cod_opt;
	}
	/**
	 * @return Returns the cod_pad.
	 */
	public String getCod_pad() {
		return cod_pad;
	}
	/**
	 * @param cod_pad The cod_pad to set.
	 */
	public void setCod_pad(String cod_pad) {
		this.cod_pad = cod_pad;
	}
	/**
	 * @return Returns the cod_per.
	 */
	public String getCod_per() {
		return cod_per;
	}
	/**
	 * @param cod_per The cod_per to set.
	 */
	public void setCod_per(String cod_per) {
		this.cod_per = cod_per;
	}
	/**
	 * @return Returns the cod_tar.
	 */
	public String getCod_tar() {
		return cod_tar;
	}
	/**
	 * @param cod_tar The cod_tar to set.
	 */
	public void setCod_tar(String cod_tar) {
		this.cod_tar = cod_tar;
	}
	/**
	 * @return Returns the flg_act.
	 */
	public String getFlg_act() {
		return flg_act;
	}
	/**
	 * @param flg_act The flg_act to set.
	 */
	public void setFlg_act(String flg_act) {
		this.flg_act = flg_act;
	}
	/**
	 * @return Returns the opt_men.
	 */
	public String getOpt_men() {
		return opt_men;
	}
	/**
	 * @param opt_men The opt_men to set.
	 */
	public void setOpt_men(String opt_men) {
		this.opt_men = opt_men;
	}
	/**
	 * @return Returns the ord_men.
	 */
	public String getOrd_men() {
		return ord_men;
	}
	/**
	 * @param ord_men The ord_men to set.
	 */
	public void setOrd_men(String ord_men) {
		this.ord_men = ord_men;
	}
	/**
	 * @return Returns the txt_men.
	 */
	public String getTxt_men() {
		return txt_men;
	}
	/**
	 * @param txt_men The txt_men to set.
	 */
	public void setTxt_men(String txt_men) {
		this.txt_men = txt_men;
	}
	private String cod_per = "";
	private String cod_tar = "";
	private String cod_opt = "";
	private String opt_men = "";
	private String txt_men = "";
	private String cod_pad = "";
	private String ord_men = "";
	private String flg_act = "";
	private String cod_per1 = "";
	private String cod_tar1 = "";
	private String cod_opt1 = "";
	private String opt_men1 = "";
	private String txt_men1 = "";
	private String cod_pad1 = "";
	private String ord_men1 = "";
	private String flg_act1 = "";
	
	/**
	 * AyudaDeCampo constructor comment.
	 */
	public Menu() {
		super();
	}
	public void consult() throws java.sql.SQLException {
		try {
			String bef[] = { "", "", "", "", "", "", "", "" };
			String aft[] = { "", "", "", "", "", "", "", "" };
			LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", "tmendat", Table.getColumnsTmendat(), bef, aft);
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... consult() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	
	
	public String getNameTable() {
		return Constante.ESQUEMA1+".TMENDAT";
	}
	public String getUrl() {
		return JspServlet.MENU_SERVLET + "?BtnMen=Buscar&cboPersona=" + getCodper()+"&cboTarjeta=" + getCodtar();
	}
	public void insert(JQuery db) throws java.sql.SQLException {
		try {
			
		} 
		catch (Exception e) {
			throw new java.sql.SQLException("\n... insert() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}


	public void loadGrid(JQuery db) throws java.sql.SQLException {
		
		
			try {
					String query = //
		"Select CODPER, CODOPT, OPTMEN, TXTMEN, CODPAD, ORDMEN, CODTAR, FLGACT " + //
		"From "+Constante.ESQUEMA1+".TMENDAT " + //
		"Where CODPER= '" +getCodper()+"' and codtar='" +getCodtar()+"' and codpad is null";
					//if(Constante.VER_LOG)
					//System.out.println("query MENU PADRE.............."+query);
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
					getGrid().addElement(row);
					JDatabase jd1 = new JDatabase(login.driver, login.url, login.userid, login.password);
					JQuery db1 = new JQuery(jd1.getConnection());
					query = //
							"Select CODPER, CODOPT, OPTMEN, TXTMEN, CODPAD, ORDMEN, CODTAR, FLGACT " + //
							"From "+Constante.ESQUEMA1+".TMENDAT " + //
							"Where CODPER= '" +getCodper()+"' and codtar='" +getCodtar()+"' and codpad='" +db.stringValue(2)+"'";
										//if(Constante.VER_LOG)
										//System.out.println("query MENU HIJO.............."+query);
									db1.setQuery(query);
									db1.executeQuery();
									//if(Constante.VER_LOG)
										//System.out.println("entro por aki");
										while (db1.getResultSet().next()) {
											java.util.Vector row1 = new java.util.Vector();
										row1.addElement(db1.stringValue(1));
										row1.addElement(db1.stringValue(2));
										row1.addElement(db1.stringValue(3));
										row1.addElement(db1.stringValue(4));
										row1.addElement(db1.stringValue(5));
										row1.addElement(db1.stringValue(6));
										row1.addElement(db1.stringValue(7));
										row1.addElement(db1.stringValue(8));
										getGrid().addElement(row1);
										}
									
									
													
								
					
					
					
				}
				//loadGrid1(db);
			} catch (Exception e) {
				throw new java.sql.SQLException("\n... loadGrid() - " + getClass().getName() + " -> " + e.getMessage());
			}
		
	}
	public void loadGrid1(JQuery db) throws java.sql.SQLException {
		
		
			try {
					String query = //
	"Select CODPER, CODOPT, OPTMEN, TXTMEN, CODPAD, ORDMEN, CODTAR, FLGACT " + //
		"From "+Constante.ESQUEMA1+".TMENDAT " + //
		"Where CODPER= '" +getCodper()+"' and codtar='" +getCodtar()+"' and codpad is not null";
					if(Constante.VER_LOG)
					//System.out.println("query.............."+query);
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
					
					getGrid1().addElement(row);
				}
			} catch (Exception e) {
				throw new java.sql.SQLException("\n... loadGrid() - " + getClass().getName() + " -> " + e.getMessage());
			}
		
	}
	public void loadGridChild(JQuery db) throws java.sql.SQLException {
	}
	public void next(int i) {
		
		cod_per = ((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString();
		cod_opt = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
		opt_men = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
		txt_men = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
		cod_pad = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
		ord_men = ((java.util.Vector) getGrid().elementAt(i)).elementAt(5).toString();
		cod_tar = ((java.util.Vector) getGrid().elementAt(i)).elementAt(6).toString();
		flg_act = ((java.util.Vector) getGrid().elementAt(i)).elementAt(7).toString();
	}
	public void next1(int i) {
		
		cod_per1 = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
		cod_opt1 = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
		opt_men1 = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
		txt_men1 = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
		cod_pad1 = ((java.util.Vector) getGrid().elementAt(i)).elementAt(5).toString();
		ord_men1 = ((java.util.Vector) getGrid().elementAt(i)).elementAt(6).toString();
		cod_tar1 = ((java.util.Vector) getGrid().elementAt(i)).elementAt(7).toString();
		flg_act1 = ((java.util.Vector) getGrid().elementAt(i)).elementAt(8).toString();
	}
	
	public void sort(JQuery db) throws java.sql.SQLException {
	}
	public void update(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
			String antcodper = getCodper();
			String antcodtar = getCodtar();
			String antFlgact=getFlgact();
			
			String query = //
	            "Update "+Constante.ESQUEMA1+".TMENDAT set flgact = " + antFlgact + "' Where codperper = '" + getCodper() + "' and codtar = '" + getCodtar() + "' ";
			//if(Constante.VER_LOG)
				//System.out.println("query update --------------"+query);
				db.setQuery(query);
				int rows = db.executeUpdate();
				String bef[] = { getCodper(), getCodopt(), getOptmen(), getTxtmen(), getCodpad(), getOrdmen(),getCodtar(),getFlgact()};
				String aft[] = { getCodper(), getOptmen(), getTxtmen(), getCodpad(), getOrdmen(),getCodtar(),antFlgact};
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Modificar", Constante.ESQUEMA1+".TMENDAT", Table.getColumnsTmendat(), bef, aft);
				setGrid(null);
				loadGrid(db);
			
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... update() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}

	
	public void delete(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
			//if(Constante.VER_LOG)
			//System.out.println("entro por aki 1ro");
			//if (search(db)) {
				String query = //
					"Update "+Constante.ESQUEMA1+".TMENDAT set flgact = '1' Where codper = '" + getCodper() + "' and (codopt = '" + getCodopt() + "' or codpad = '" + getCodopt() + "' ";
				// System.out.println("delete limite-----------"+query);
				db.setQuery(query);
				db.executeUpdate();
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
			String query = //
				"Update "+Constante.ESQUEMA1+".TMENDAT set flgact = '0' Where codper = '" + getCodper() + "' and codtar = '" + getCodtar() + "' ";
			
			db.setQuery(query);
			db.executeUpdate();
			java.util.Enumeration vector = req.getParameterNames();
			while (vector.hasMoreElements() && vector != null) {
				String var1 = vector.nextElement().toString();
				if(var1.indexOf("-") > -1){
				  sw = req.getParameter(var1).toUpperCase();
				  //if(Constante.VER_LOG)
				//  System.out.println("sw..."+sw);
				  if (sw.equals("ON")) {
						codper=var1.substring(0,2);
						codopt=var1.substring(3,7);
						//if(Constante.VER_LOG)
						//System.out.println("VAR1.."+var1);
						//if(Constante.VER_LOG)
						//System.out.println("transa.."+codper);
						//if(Constante.VER_LOG)
						//System.out.println("tipo pers.."+codopt);
						// System.out.println("moneda.."+codcur);
						query = //
							"Update "+Constante.ESQUEMA1+".TMENDAT set flgact = '1' Where codper = '" + getCodper() + "' and codopt = '" + getCodopt() + "'";
						
						db.setQuery(query);
						db.executeUpdate();
						//if(Constante.VER_LOG)
						//System.out.println("update........."+query);
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
	/**
	 * @return Returns the codopt.
	 */
	public String getCodopt() {
		return codopt;
	}
	/**
	 * @param codopt The codopt to set.
	 */
	public void setCodopt(String codopt) {
		this.codopt = codopt;
	}
	/**
	 * @return Returns the codpad.
	 */
	public String getCodpad() {
		return codpad;
	}
	/**
	 * @param codpad The codpad to set.
	 */
	public void setCodpad(String codpad) {
		this.codpad = codpad;
	}
	/**
	 * @return Returns the codper.
	 */
	public String getCodper() {
		return codper;
	}
	/**
	 * @param codper The codper to set.
	 */
	public void setCodper(String codper) {
		this.codper = codper;
	}
	/**
	 * @return Returns the codtar.
	 */
	public String getCodtar() {
		return codtar;
	}
	/**
	 * @param codtar The codtar to set.
	 */
	public void setCodtar(String codtar) {
		this.codtar = codtar;
	}
	/**
	 * @return Returns the flgact.
	 */
	public String getFlgact() {
		return flgact;
	}
	/**
	 * @param flgact The flgact to set.
	 */
	public void setFlgact(String flgact) {
		this.flgact = flgact;
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
	/**
	 * @return Returns the optmen.
	 */
	public String getOptmen() {
		return optmen;
	}
	/**
	 * @param optmen The optmen to set.
	 */
	public void setOptmen(String optmen) {
		this.optmen = optmen;
	}
	/**
	 * @return Returns the ordmen.
	 */
	public String getOrdmen() {
		return ordmen;
	}
	/**
	 * @param ordmen The ordmen to set.
	 */
	public void setOrdmen(String ordmen) {
		this.ordmen = ordmen;
	}
	/**
	 * @return Returns the txtmen.
	 */
	public String getTxtmen() {
		return txtmen;
	}
	/**
	 * @param txtmen The txtmen to set.
	 */
	public void setTxtmen(String txtmen) {
		this.txtmen = txtmen;
	}
	public boolean search(JQuery db) throws java.sql.SQLException {
		try {
			return true;
		} 
		catch (Exception e) {
			throw new java.sql.SQLException("\n... search() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	/* (non-Javadoc)
	 * @see CosapiSoft.SARAWebBanking.GenericTable#nextChild(int)
	 */
	public void nextChild(int index) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @return Returns the cod_opt1.
	 */
	public String getCod_opt1() {
		return cod_opt1;
	}
	/**
	 * @param cod_opt1 The cod_opt1 to set.
	 */
	public void setCod_opt1(String cod_opt1) {
		this.cod_opt1 = cod_opt1;
	}
	/**
	 * @return Returns the cod_pad1.
	 */
	public String getCod_pad1() {
		return cod_pad1;
	}
	/**
	 * @param cod_pad1 The cod_pad1 to set.
	 */
	public void setCod_pad1(String cod_pad1) {
		this.cod_pad1 = cod_pad1;
	}
	/**
	 * @return Returns the cod_per1.
	 */
	public String getCod_per1() {
		return cod_per1;
	}
	/**
	 * @param cod_per1 The cod_per1 to set.
	 */
	public void setCod_per1(String cod_per1) {
		this.cod_per1 = cod_per1;
	}
	/**
	 * @return Returns the cod_tar1.
	 */
	public String getCod_tar1() {
		return cod_tar1;
	}
	/**
	 * @param cod_tar1 The cod_tar1 to set.
	 */
	public void setCod_tar1(String cod_tar1) {
		this.cod_tar1 = cod_tar1;
	}
	/**
	 * @return Returns the flg_act1.
	 */
	public String getFlg_act1() {
		return flg_act1;
	}
	/**
	 * @param flg_act1 The flg_act1 to set.
	 */
	public void setFlg_act1(String flg_act1) {
		this.flg_act1 = flg_act1;
	}
	/**
	 * @return Returns the opt_men1.
	 */
	public String getOpt_men1() {
		return opt_men1;
	}
	/**
	 * @param opt_men1 The opt_men1 to set.
	 */
	public void setOpt_men1(String opt_men1) {
		this.opt_men1 = opt_men1;
	}
	/**
	 * @return Returns the ord_men1.
	 */
	public String getOrd_men1() {
		return ord_men1;
	}
	/**
	 * @param ord_men1 The ord_men1 to set.
	 */
	public void setOrd_men1(String ord_men1) {
		this.ord_men1 = ord_men1;
	}
	/**
	 * @return Returns the txt_men1.
	 */
	public String getTxt_men1() {
		return txt_men1;
	}
	/**
	 * @param txt_men1 The txt_men1 to set.
	 */
	public void setTxt_men1(String txt_men1) {
		this.txt_men1 = txt_men1;
	}
}