package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class Limites extends GenericTable {
	private InicioSesion login;
	public java.util.Vector monedas = null;
	private String typper = "";
	private String trahst = "";
	private String limopeinf = "";
	private String limopesup = "";
	private String limdayinf = "";
	private String limdaysup = "";
	private String codcur = "";
	private String typ_per = "";
	private String cod_cur = "";
	private String tra_hst = "";
	private String lim_ope_inf = "";
	private String lim_ope_sup = "";
	private String lim_day_inf = "";
	private String lim_day_sup = "";
	private String txtcur = "";
	private String txt_cur = "";
	private String codtp="";
	private String cod_tp="";
	/**
	 * AyudaDeCampo constructor comment.
	 */
	public Limites() {
		super();
	}
	public void consult() throws java.sql.SQLException {
		try {
			String bef[] = { "", "", "", "", "", "", "" };
			String aft[] = { "", "", "", "", "", "", "" };
			LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", "tlimdat", Table.getColumnsTlimdat(), bef, aft);
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... consult() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	public void delete(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
			//if (search(db)) {
				String query = //
	            "Delete from "+Constante.ESQUEMA1+".TLIMDAT Where typper = '" + getTypper() + "' and trahst = '" + getTrahst() + "' and codcur= '" + getCodcur() + "'";
				// System.out.println("delete limite-----------"+query);
				db.setQuery(query);
				db.executeUpdate();
				String bef[] = { getTypper(), getTrahst(), getLimopeinf(), getLimopesup(), getLimdayinf(), getLimdaysup(), getCodcur()};
				String aft[] = { "", "", "", "", "", "", ""  };
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TLIMDAT", Table.getColumnsTlimdat(), bef, aft);
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
						typper=var1.substring(5,7);
						codcur=var1.substring(8,11);
						// System.out.println("transa.."+trahst);
						// System.out.println("tipo pers.."+typper);
						// System.out.println("moneda.."+codcur);
						String query = //
						"Delete from "+Constante.ESQUEMA1+".TLIMDAT Where typper = '" + getTypper() + "' and trahst = '" + getTrahst() + "' and codcur= '" + getCodcur() + "'";
						// System.out.println("delete limite1-----------"+query);
						db.setQuery(query);
						db.executeUpdate();
							String bef[] = {getTypper(), getTrahst(), getLimopeinf(), getLimopesup(), getLimdayinf(), getLimdaysup(), getCodcur()};
						String aft[] = {"", "", "", "", "", "", ""};
						LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TLIMDAT", Table.getColumnsTlimdat(), bef, aft);
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
		return Constante.ESQUEMA1+".TLIMDAT";
	}
	public String getUrl() {
		return JspServlet.LIMITES_SERVLET + "?BtnLim=Buscar&TxtTransaccion=" + getTra_hst()+"&cboPersona=" + getCod_tp()+"&cboMoneda=" + getCod_cur();
	}
	public void insert(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
			if (!search(db)) {
					String query = //
	            "Insert into "+Constante.ESQUEMA1+".TLIMDAT (typper,trahst,limopeinf,limopesup,limdayinf,limdaysup,codcur) values ('" + getTypper() + "','" + getTrahst() + "', " + getLimopeinf() + ", " + getLimopesup() + ", " + getLimdayinf() + ", " + getLimdaysup() + ", '" + getCodcur() + "')";
				db.setQuery(query);
				db.executeUpdate();
				String bef[] = { "", "", "", "", "", "", "" };
				String aft[] = { getTypper(), getTrahst(), getLimopeinf(), getLimopesup(), getLimdayinf(), getLimdaysup(),getCodcur()};
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TLIMDAT", Table.getColumnsTlimdat(), bef, aft);
				setGrid(null);
				loadGrid(db);
			} else {
				String str = Mensajes.getMessage(Mensajes.CODIGO_DE_AYUDA_EXISTE);
				int pos = str.indexOf('&');
				setError(str.substring(0, pos) + getTypper() + str.substring(pos + 1));
			}
		} 
		catch (Exception e) {
			throw new java.sql.SQLException("\n... insert() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	public void nextMon(int i) {
		txtcur =((java.util.Vector) monedas.elementAt(i)).elementAt(0).toString() + " - " + ((java.util.Vector) monedas.elementAt(i)).elementAt(1).toString();
	}
	public void setNamMon(String newValue) {
		this.txtcur = newValue;
	}
	public void loadComboMonedas() throws Exception {
		JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
		JQuery db = new JQuery(jd.getConnection());
		try {
			
			monedas = new java.util.Vector();
			String query = //
			"Select codcur,txtcurlon " + //
			"From "+Constante.ESQUEMA1+".TCURDAT " + //
			"Order by codcur";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector row = new java.util.Vector();
				row.addElement(db.stringValue(1));
				row.addElement(db.stringValue(2));
				monedas.addElement(row);
			}
			
		} catch (Exception e) {
			throw new Exception("\nLoadComboMonedas() - Log de Operaciones \n" + e.getMessage());
		} finally {
			db.close();
			jd.close();
		}
	}
	public void loadGrid(JQuery db) throws java.sql.SQLException {
		setIndex(0);
		setStatus("0");
		if (getGrid().size() == 0) {
			try {
					String query = //
		"Select typper,trahst,limopeinf,limopesup,limdayinf,limdaysup,l.codcur,c.txtcurlon " + //
		"From "+Constante.ESQUEMA1+".TLIMDAT l, "+Constante.ESQUEMA1+".tcurdat c " + //
		"Where l.codcur=c.codcur " + //
	    "Order by trahst";
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
		cod_tp = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
		tra_hst = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
		lim_ope_inf = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
		lim_ope_sup = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
		lim_day_inf = ((java.util.Vector) getGrid().elementAt(i)).elementAt(5).toString();
		lim_day_sup = ((java.util.Vector) getGrid().elementAt(i)).elementAt(6).toString();
		cod_cur = ((java.util.Vector) getGrid().elementAt(i)).elementAt(7).toString();
		txt_cur = ((java.util.Vector) getGrid().elementAt(i)).elementAt(8).toString();
		
		if (cod_tp.equals("02"))
			typ_per = "Jurídica";
		else
			typ_per = "Natural";
	}
	public void nextChild(int i) {
	}
	public boolean search(JQuery db) throws java.sql.SQLException {
		try {
			String query = //
			"Select typper,trahst,limopeinf,limopesup,limdayinf,limdaysup,l.codcur,c.txtcurlon " + //
		    "From "+Constante.ESQUEMA1+".TLIMDAT l,"+Constante.ESQUEMA1+".tcurdat c " + //
	        "Where l.codcur=c.codcur and typper = '" + getTypper() + "' and trahst = '" + getTrahst() + "' and l.codcur = '" + getCodcur() + "'";
			db.setQuery(query);
			db.executeQuery();
			if (!db.getResultSet().next()) {
				return false;
			}
			cod_tp = db.stringValue(1);
			trahst = db.stringValue(2);
			limopeinf = db.stringValue(3);
			limopesup = db.stringValue(4);
			limdayinf = db.stringValue(5);
			limdaysup = db.stringValue(6);
			codcur = db.stringValue(7);
			txtcur = db.stringValue(8);
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
			String antTypper = getTypper();
			String antTrahst = getTrahst();
			String antLimopeinf=getLimopeinf();
			String antLimopesup=getLimopesup();
			String antLimdayinf=getLimdayinf();
			String antLimdaysup=getLimdaysup();
			String antCodcur=getCodcur();
			
			if (search(db)) {
				String query = //
	            "Update "+Constante.ESQUEMA1+".TLIMDAT set limopeinf = " + antLimopeinf + ", limopesup=" + antLimopesup + ", limdayinf=" + antLimdayinf + ", limdaysup=" + antLimdaysup + ", codcur='" + antCodcur + "' Where typper = '" + getTypper() + "' and trahst = '" + getTrahst() + "' and codcur = '" + getCodcur() + "' ";
				// System.out.println("query update --------------"+query);
				db.setQuery(query);
				int rows = db.executeUpdate();
				String bef[] = { getTypper(), getTrahst(), getLimopeinf(), getLimopesup(), getLimdayinf(), getLimdaysup(),getCodcur()};
				String aft[] = { getTypper(), getTrahst(), antLimopeinf,antLimopesup,antLimdayinf,antLimdaysup,getCodcur() };
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Modificar", Constante.ESQUEMA1+".TLIMDAT", Table.getColumnsTlimdat(), bef, aft);
				setGrid(null);
				loadGrid(db);
			} else {
				String str = Mensajes.getMessage(Mensajes.CODIGO_DE_AYUDA_NO_EXISTE);
				int pos = str.indexOf('&');
				setError(str.substring(0, pos) + getTypper() + str.substring(pos + 1));
			}
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... update() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}

	public String getTypper() {
		return typper;
	}
	public String getTxtcur() {
		return txtcur;
	}
	public String getTrahst() {
		return trahst;
	}
	public String getLimopeinf() {
		return limopeinf;
	}
	public String getLimopesup() {
		return limopesup;
	}
	public String getLimdayinf() {
		return limdayinf;
	}
	public String getLimdaysup() {
		return limdaysup;
	}
	public String getCodcur() {
		return codcur;
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
					String query = "Select codlim From tlimdat Order by codlim";
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
	public String getNamMon() {
		return txtcur.toUpperCase();
	}
	public String getTyp_per() {
		return typ_per;
	}
	public String getTxt_cur() {
		return txt_cur;
	}
	public String getTra_hst() {
		return tra_hst;
	}
	public String getLim_ope_inf() {
		return lim_ope_inf;
	}
	public String getLim_ope_sup() {
		return lim_ope_sup;
	}
	public String getLim_day_inf() {
		return lim_day_inf;
	}
	public String getLim_day_sup() {
		return lim_day_sup;
	}
	public String getCod_cur() {
		return cod_cur;
	}
	public void setTyp_per(String typ_per) {
		this.typ_per = typ_per;
	}
	public void setTypper(String typper) {
		this.typper = typper;
	}
	public void setTra_hst(String tra_hst) {
		this.tra_hst = tra_hst;
	}
	public void setTrahst(String trahst) {
		this.trahst = trahst;
	}
	public void setLim_ope_inf(String lim_ope_inf) {
		this.lim_ope_inf = lim_ope_inf;
	}
	public void setLimopeinf(String limopeinf) {
		this.limopeinf = limopeinf;
	}
	public void setTxt_cur(String txt_cur) {
		this.txt_cur = txt_cur;
	}
	public void setTxtcur(String txtcur) {
		this.txtcur = txtcur;
	}
	public void setLim_ope_sup(String lim_ope_sup) {
		this.lim_ope_sup = lim_ope_sup;
	}
	public void setLimopesup(String limopesup) {
		this.limopesup = limopesup;
	}
	public void setLim_day_inf(String lim_day_inf) {
		this.lim_day_inf = lim_day_inf;
	}
	public void setLimdayinf(String limdayinf) {
		this.limdayinf = limdayinf;
	}
	public void setLim_day_sup(String lim_day_sup) {
		this.lim_day_sup = lim_day_sup;
	}
	public void setLimdaysup(String limdaysup) {
		this.limdaysup = limdaysup;
	}
	public void setCod_cur(String cod_cur) {
		this.cod_cur = cod_cur;
	}
	public void setCodcur(String codcur) {
		this.codcur = codcur;
	}

	/**
	 * @return Devuelve cod_tp.
	 */
	public String getCod_tp() {
		return cod_tp;
	}
	/**
	 * @param cod_tp El cod_tp a establecer.
	 */
	public void setCod_tp(String cod_tp) {
		this.cod_tp = cod_tp;
	}
	/**
	 * @return Devuelve codtp.
	 */
	public String getCodtp() {
		return codtp;
	}
	/**
	 * @param codtp El codtp a establecer.
	 */
	public void setCodtp(String codtp) {
		this.codtp = codtp;
	}
}