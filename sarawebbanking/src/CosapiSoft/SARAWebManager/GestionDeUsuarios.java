package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import java.util.Vector;

import pe.cosapi.common.Constante;

import CosapiSoft.SARAWebBanking.*;
public class GestionDeUsuarios extends GenericTable {
	//
	private java.util.Vector oficinas = null;
	private java.util.Vector modulos = null;
	private java.util.Vector modulosb = null;
	private java.util.Vector modUsr = null;
	private String codusr = "";
	private String codlim = "";
	private String txtnam = "";
	private String txtlim = "";
	private String txtpas = "";
	private String oldclave = "";
	private String newclave = "";
	private String codmod = "";
	private String usrmail = "";
	private String codbra = "";
	private String datbeg = "";
	private String timbeg = "";
	private String codrea = "";
	private String txtusu = "";
	private String txtsec = "";
	private String txtCodrea = "";
	private java.lang.String[] listMod = null;
	//
	private String cod_usr = "";
	private String txt_sec = "";
	private String txt_usu = "";
	private String cod_lim = "";
	private String txt_nam = "";
	private String txt_lim = "";
	private String usr_mail = "";
	private String cod_bra = "";
	private String txt_bra = "";
	private String cod_bra_nam = "";
	private String cod_mod = "";
	private String txt_mod = "";
	private String cod_mod_nam;
	private String sta_mod = "";
	private String dat_beg = "";
	private String tim_beg = "";
	private boolean assignedBranch = false;
	
	private Vector limites = null;
	
	/**
	 * AyudaDeCampo constructor comment.
	 */
	public GestionDeUsuarios() {
		super();
		oficinas = new java.util.Vector(1);
		modulos = new java.util.Vector(1);
	}
	public void consult() throws java.sql.SQLException {
		try {
			String bef[] = { "", "", "", "", "", "", "", "", "", "" };
			String aft[] = { "", "", "", "", "", "", "", "", "", "" };
			LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", Constante.ESQUEMA1+".TUSRDAT", Table.getColumnsTusrdat(), bef, aft);
		} 
		catch (Exception e) {
			throw new java.sql.SQLException(" \n... consult() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	public void delete(JQuery db) throws java.sql.SQLException {}
/*		try {
			setError("");
			if (search(db)) {
					String query = "Delete from tusrprf Where codusr = '" + getCodusr() + "'";
				db.setQuery(query);
				db.executeUpdate();
					query = "Delete from tusrwks Where codusr = '" + getCodusr() + "'";
				db.setQuery(query);
				db.executeUpdate();
					query = "Delete from tbrausr Where codusr = '" + getCodusr() + "'";
				db.setQuery(query);
				db.executeUpdate();
					query = "Delete from tusrsgn Where codusr = '" + getCodusr() + "'";
				db.setQuery(query);
				db.executeUpdate();
					query = "Delete from tusrmod Where codusr = '" + getCodusr() + "'";
				db.setQuery(query);
				db.executeUpdate();
					query = "Delete from tusrdat Where codusr = '" + getCodusr() + "'";
				db.setQuery(query);
				db.executeUpdate();
				String bef[] = { getCodusr(), getTxtnam(), "******", getCodbra(), getUsrmail(), getDatbeg(), getTimbeg(), getCodlim(), getTxtusu(), getTxtsec()};
				String aft[] = { "", "", "", "", "", "", "", "", "", "" };
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", "tusrdat", Table.getColumnsTusrdat(), bef, aft);
				setGrid(null);
				loadGrid(db);
				setCodusr("");
				loadModulos(db);
			} else {
				String str = Mensajes.getMessage(Mensajes.CODIGO_DE_USUARIO_NO_EXISTE);
				int pos = str.indexOf('&');
				setError(str.substring(0, pos) + getCodusr() + str.substring(pos + 1));
			}
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... delete() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}*/
	public void delete(JQuery db, javax.servlet.http.HttpServletRequest req) throws java.sql.SQLException, javax.servlet.ServletException {
		try {
			setError("");
			java.util.Enumeration vector = req.getParameterNames();
			while (vector.hasMoreElements() && vector != null) {
				codusr = vector.nextElement().toString();
				if (req.getParameter(codusr) != null) {
					String sw = req.getParameter(codusr).toUpperCase();
					if (sw.equals("ON")) {
						if (search(db)) {
								String query = "Delete from "+Constante.ESQUEMA1+".TUSRPRF Where codusr = '" + getCodusr() + "'";
							db.setQuery(query);
							db.executeUpdate();
								/*query = "Delete from tusrwks Where codusr = '" + getCodusr() + "'";
							db.setQuery(query);
							db.executeUpdate();*/
								query = "Delete from "+Constante.ESQUEMA1+".tbrausr Where codusr = '" + getCodusr() + "'";
							db.setQuery(query);
							db.executeUpdate();
							/*	query = "Delete from tusrsgn Where codusr = '" + getCodusr() + "'";
							db.setQuery(query);
							db.executeUpdate();*/
								query = "Delete from "+Constante.ESQUEMA1+".TUSRMOD Where codusr = '" + getCodusr() + "'";
							db.setQuery(query);
							db.executeUpdate();
								query = "Delete from "+Constante.ESQUEMA1+".TUSRDAT Where codusr = '" + getCodusr() + "'";
							db.setQuery(query);
							db.executeUpdate();
					/*query = "Delete from tcajdet Where codusr = '" + getCodusr() + "'";
				db.setQuery(query);
				db.executeUpdate();
					query = "Delete from tcajdat Where codusr = '" + getCodusr() + "'";
				db.setQuery(query);
				db.executeUpdate();
					query = "Delete from ttotdet Where codusr = '" + getCodusr() + "'";
				db.setQuery(query);*/
				db.executeUpdate();
							String bef[] = { getCodusr(), getTxtnam(), "******", getCodbra(), getUsrmail(), getDatbeg(), getTimbeg(), getCodlim(), getTxtusu(), getTxtsec()};
							String aft[] = { "", "", "", "", "", "", "", "", "", "" };
							LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TUSRDAT", Table.getColumnsTusrdat(), bef, aft);
							setGrid(null);
						}
					}
				}
			}
			loadGrid(db);
			setCodusr("");
			loadModulos(db);
		} catch (Exception e) {
			throw new java.sql.SQLException("\n...delete() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getCod_bra() {
		if (CString.isEmptyOrNull(cod_bra))
			cod_bra = "0000";
		return cod_bra;
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getCod_bra_nam() {
		return cod_bra_nam;
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getCod_mod() {
		return cod_mod;
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getCod_mod_nam() {
		return cod_mod_nam;
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getCod_usr() {
		if (cod_usr == null)
			cod_usr = "";
		return cod_usr.trim();
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getCodbra() {
		if (CString.isEmptyOrNull(codbra))
			codbra = "0000";
		return codbra;
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getCodmod() {
		if (codmod == null)
			codmod = "01";
		return codmod.trim();
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getCodrea() {
		if (CString.isEmptyOrNull(codrea))
			codrea = "0";
		return codrea;
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getCodusr() {
		if (codusr == null)
			codusr = "";
		return codusr.trim();
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getDat_beg() {
		return dat_beg.trim();
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getDatbeg() {
		return datbeg.trim();
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String[]
	 */
	public java.lang.String[] getListMod() {
		return listMod;
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.util.Vector
	 */
	protected java.util.Vector getModulos() {
		if (modulos == null)
			modulos = new java.util.Vector(10, 10);
		return modulos;
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.util.Vector
	 */
	public java.util.Vector getModUsr() {
		return modUsr;
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getNameTable() {
		return ""+Constante.ESQUEMA1+".TUSRDAT";
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getNewclave() {
		CString str = new CString(newclave);
		str.arrange();
		return str.toString();
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getOldclave() {
		CString str = new CString(oldclave);
		str.arrange();
		return str.toString();
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getSta_mod() {
		return sta_mod;
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getTim_beg() {
		return tim_beg.trim();
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getTimbeg() {
		return timbeg.trim();
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getTxt_bra() {
		return txt_bra;
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getTxt_mod() {
		return txt_mod;
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getTxt_nam() {
		return txt_nam;
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getTxtCodrea() {
		try {
			int intCodrea = Integer.valueOf(getCodrea().trim()).intValue();
			switch (intCodrea) {
				case 0 :
					txtCodrea = "Creación";
					break;
				case 1 :
					txtCodrea = "Activación";
					break;
				case 2 :
					txtCodrea = "Traslado";
					break;
				case 3 :
					txtCodrea = "Vacaciones";
					break;
				case 4 :
					txtCodrea = "Licencia";
					break;
				case 5 :
					txtCodrea = "Renuncia";
					break;
				default :
					txtCodrea = "Ninguno";
			}
			return txtCodrea;
		} catch (Exception e) {
			return "Null";
		}
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getTxtnam() {
		CString str = new CString(txtnam);
		str.arrange();
		return str.toString();
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getTxtpas() {
		if (txtpas == null)
			txtpas = "";
		return txtpas.trim();
	}
	public String getUrl() {
		return getUrlGestionDeUsuarios();
	}
	public String getUrlGestionDeUsuarios() {
		return JspServlet.GESTION_DE_USUARIOS_SERVLET + "?BtnUsr=Buscar&TxtCodusr=";
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getUsr_mail() {
		return usr_mail;
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getUsrmail() {
		return usrmail;
	}
	/**
	 * This method was created in VisualAge.
	 * @return boolean
	 */
	public boolean hasAssignedBranch() {
		return assignedBranch;
	}
	public void insert(JQuery db) throws java.sql.SQLException {
		try {
			Vector module = new java.util.Vector(10, 10);
			setError("");
			boolean sw = search(db);
			if (!sw) {
				txtpas = Encrypt.encrypt(getCodusr() + "01", "PLER4&/!1BA97c&-|", "HQ89S");
				String date = CString.deleteAll(CString.toDate("yyyy/MM/dd"), "/");
				String time = CString.toTime();
					String query = //
	            "Insert into "+Constante.ESQUEMA1+".TUSRDAT (codusr, txtnam, txtpas, codbra, usrmail, datbeg, timbeg,codrea, codlim, txtusu, txtsec) values ('" + getCodusr() + "', '" + getTxtnam() + "', '" + getTxtpas() + "', '0001', '" + getUsrmail() + "', '" + date + "', '" + time + "', '','','"+getTxtusu()+"','')";
				db.setQuery(query);
				db.executeUpdate();
				
				query = "select codcur from "+Constante.ESQUEMA1+".tcurdat order by codcur";
				db.setQuery(query);
				db.executeQuery();
				Vector cur = new Vector();
				while (db.getResultSet().next()) {
					Vector tmp = new Vector();
					tmp.addElement(db.getResultSet().getString(1));
					cur.addElement(tmp);
				}

				/*query = "select codtot from ttotdat order by codtot";
				db.setQuery(query);
				db.executeQuery();
				Vector tot = new Vector();
				while (db.getResultSet().next()) {
					Vector tmp = new Vector();
					tmp.addElement(db.getResultSet().getString(1));
					tot.addElement(tmp);
				}


				for (int i = 0; i < tot.size(); i++) {
					String codtot = ((Vector) tot.elementAt(i)).elementAt(0).toString();
					for (int j = 0; j < cur.size(); j++) {
						String codcur = ((Vector) cur.elementAt(j)).elementAt(0).toString();
						query = "Insert into ttotdet (codtot, codusr, codbra, mtotot, mtocan, numope, codcur, mtotot2) values ('" + codtot + "', '" + getCodusr() + "', '" + getCodbra() + "', 0,0,0,'" + codcur + "',0)";
						db.setQuery(query);
						db.executeUpdate();
					}
				}
				
*/				
				String bef[] = { "", "", "", "", "", "", "", "", "", "" };
				String aft[] = { getCodusr(), getTxtnam(), "******", getCodbra(), getUsrmail(), date, time, getCodlim(), getTxtusu(), getTxtsec() };
				LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TUSRDAT", Table.getColumnsTusrdat(), bef, aft);
				//
				/*if (listMod == null || listMod.length == 0) {
					listMod = new String[modulos.size()];
					for (int i = 0; i < modulos.size(); i++) {
						nextModulo(i);
						listMod[i] = cod_mod.trim();
					}
				}*/
				boolean isBranch = false;
				query = //
					"Select codmod, txtmod " + //
					"From "+Constante.ESQUEMA1+".tmoddat ";
						db.setQuery(query);
						db.executeQuery();
						while (db.getResultSet().next()) {
							java.util.Vector mod = new java.util.Vector(3);
							mod.addElement(db.stringValue(1));
							mod.addElement(db.stringValue(2));
							mod.addElement("1");
							module.addElement(mod);
							
						}
						
				for (int i = 0; i < module.size(); i++) {
					// System.out.println("cod de modulo:   "+((java.util.Vector) module.elementAt(i)).elementAt(0).toString());
					// System.out.println("nombre de modulo:   "+((java.util.Vector) module.elementAt(i)).elementAt(0).toString());
					query = //
					"Insert into "+Constante.ESQUEMA1+".TUSRMOD (codmod, codusr) values ('" + ((java.util.Vector) module.elementAt(i)).elementAt(0).toString() + "', '" + getCodusr() + "')";
					db.setQuery(query);
					db.executeUpdate();
					//if (listMod[i].equalsIgnoreCase(PerfilesDeUsuarios.CODMOD_TELLER))
						//isBranch = true;
					String bef1[] = { "", "" };
					String aft1[] = { getCodusr(), ((java.util.Vector) module.elementAt(i)).elementAt(0).toString() };
					LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TUSRMOD", Table.getColumnsTusrmod(), bef1, aft1);
				}
				//
				/*if (isBranch) {
					LogDeUsuariosBranch log = new LogDeUsuariosBranch();
					log.setLogin(getLogin());
					log.setCodusr(getCodusr());
					log.setCodbra(getCodbra());
					log.setCodspv(getLogin().getUsuario());
					log.setDatbeg(CString.toDate("yyyy/MM/dd"));
					log.setTimbeg(time);
					log.setCodrea("0");
					log.setTxtprf("Ninguno");
					log.setTxtaddtrx("Ninguno");
					log.setTxtrestrx("Ninguno");
					log.insert(db);
				}*/
				//
				setGrid(null);
				loadGrid(db);
				setCodusr("");
				loadModulos(db);
			} else {
				String str = Mensajes.getMessage(Mensajes.CODIGO_DE_USUARIO_EXISTE);
				int pos = str.indexOf('&');
				setError(str.substring(0, pos) + getCodusr() + str.substring(pos + 1));
			}
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... insert() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	public void loadGrid(JQuery db) throws java.sql.SQLException {
		setIndex(0);
		if (getGrid().size() == 0) {
			try {
					String query = //
		"Select a.codusr, a.txtnam, a.codbra, a.usrmail, a.datbeg, a.timbeg, b.codmod, c.txtmod, " + //
		"a.codrea, a.txtusu, a.txtsec " + //
		"From "+Constante.ESQUEMA1+".TUSRDAT a,"+Constante.ESQUEMA1+".TUSRMOD b, "+Constante.ESQUEMA1+".tmoddat c " + //
		"Where a.codusr = b.codusr " + //
		"And b.codmod = c.codmod " + //
	"Order by a.codbra, a.codusr, b.codmod ";
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					java.util.Vector row = new java.util.Vector(12);
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
					row.addElement(db.stringValue(11));
					getGrid().addElement(row);
				}
			} catch (Exception e) {
				throw new java.sql.SQLException("\n... loadGrid() - " + getClass().getName() + " -> " + e.getMessage());
			}
		}
	}
	public void loadGridChild(JQuery db) throws java.sql.SQLException {
	}


	
	private void loadModulos(JQuery db) throws java.sql.SQLException {
		try {
			modulos = new java.util.Vector(10, 10);
			assignedBranch = false;
				String query = //
		"Select codmod, txtmod " + //
		"From "+Constante.ESQUEMA1+".tmoddat " + //
		"Where codmod IN (Select codmod from "+Constante.ESQUEMA1+".TUSRMOD where codusr = '" + getCodusr() + "') " + //
	"Order by codmod ";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector reg = new java.util.Vector(3);
				reg.addElement(db.stringValue(1));
				reg.addElement(db.stringValue(2));
				reg.addElement("1");
				modulos.addElement(reg);
				if (reg.elementAt(0).toString().equals(PerfilesDeUsuarios.CODMOD_TELLER))
					assignedBranch = true;
			}
				query = //
		"Select codmod, txtmod " + //
		"From "+Constante.ESQUEMA1+".tmoddat " + //
		"Where codmod NOT IN (Select codmod from "+Constante.ESQUEMA1+".TUSRMOD where codusr = '" + getCodusr() + "') " + //
	"Order by codmod ";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector reg = new java.util.Vector(3);
				reg.addElement(db.stringValue(1));
				reg.addElement(db.stringValue(2));
				reg.addElement("0");
				modulos.addElement(reg);
			}
			//System.out.println("Modulos : " + modulos);
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... loadModulos() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	public void cargarModulos() throws java.sql.SQLException {
		JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
		JQuery db = new JQuery(jd.getConnection());
		try {
			modulosb = new java.util.Vector(10, 10);
			assignedBranch = false;
				String query = //
		"Select codmod, txtmod " + //
		"From "+Constante.ESQUEMA1+".tmoddat ";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector reg1 = new java.util.Vector(3);
				reg1.addElement(db.stringValue(1));
				reg1.addElement(db.stringValue(2));
				reg1.addElement("1");
				modulosb.addElement(reg1);
				//if (reg1.elementAt(0).toString().equals(PerfilesDeUsuarios.CODMOD_TELLER))
					//assignedBranch = true;
			}
			//System.out.println("Modulos : " + modulos);
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... loadModulos() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	/**
	 * This method was created in VisualAge.
	 * @exception java.sql.SQLException The exception description.
	 */
	public void loadOficinas() throws java.sql.SQLException {
		JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
		JQuery db = new JQuery(jd.getConnection());
		try {
			/*oficinas = new java.util.Vector(10, 10);
				String query = //
		"Select codbra, txtbra " + //
		"From tbradat " + //
	"Order by codbra ";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector reg = new java.util.Vector(2);
				reg.addElement(db.stringValue(1));
				reg.addElement(db.stringValue(2));
				oficinas.addElement(reg);
			}*/
			//System.out.println("Oficinas : " + oficinas);
			loadModulos(db);
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... loadOficinas() - " + getClass().getName() + " -> " + e.getMessage());
		} finally {
			db.close();
			jd.close();
		}
	}
	public void next(int i) {
		
		setStatus(((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString());
		cod_usr = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
		txt_nam = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
		usr_mail = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
		usr_mail = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
		dat_beg = ((java.util.Vector) getGrid().elementAt(i)).elementAt(5).toString();
		tim_beg = ((java.util.Vector) getGrid().elementAt(i)).elementAt(6).toString();
		cod_mod = ((java.util.Vector) getGrid().elementAt(i)).elementAt(7).toString();
		txt_mod = ((java.util.Vector) getGrid().elementAt(i)).elementAt(8).toString();
		codrea = ((java.util.Vector) getGrid().elementAt(i)).elementAt(9).toString();
		txt_usu = ((java.util.Vector) getGrid().elementAt(i)).elementAt(10).toString();
		txt_sec = ((java.util.Vector) getGrid().elementAt(i)).elementAt(11).toString();
		CFormat format = new CFormat();
		format.setFormat("0000/00/00");
		format.setText(dat_beg);
		dat_beg = format.changeFormat().trim();
		dat_beg = (dat_beg.equals("")) ? CString.toDate("yyyy/MM/dd") : dat_beg;
		//
		format.setFormat("00:00:00");
		format.setText(tim_beg);
		tim_beg = format.changeFormat().trim();
		tim_beg = (tim_beg.equals("")) ? CString.toTime() : tim_beg;
		//
	}
	public void nextChild(int i) {
	}
	/**
	 * This method was created in VisualAge.
	 * @param i int
	 */
	public void nextModulo(int i) {
		cod_mod = ((java.util.Vector) modulos.elementAt(i)).elementAt(0).toString();
		txt_mod = ((java.util.Vector) modulos.elementAt(i)).elementAt(1).toString();
		sta_mod = ((java.util.Vector) modulos.elementAt(i)).elementAt(2).toString();
		cod_mod_nam = cod_mod + " - " + txt_mod;
	}
	public void sigModulo(int i) {
		cod_mod = ((java.util.Vector) modulosb.elementAt(i)).elementAt(0).toString();
		txt_mod = ((java.util.Vector) modulosb.elementAt(i)).elementAt(1).toString();
		sta_mod = ((java.util.Vector) modulosb.elementAt(i)).elementAt(2).toString();
		cod_mod_nam = cod_mod + " - " + txt_mod;
	}
	/**
	 * This method was created in VisualAge.
	 * @param i int
	 */
	public void nextOficina(int i) {
		cod_bra = ((java.util.Vector) oficinas.elementAt(i)).elementAt(0).toString();
		txt_bra = ((java.util.Vector) oficinas.elementAt(i)).elementAt(1).toString();
		cod_bra_nam = cod_bra + " - " + txt_bra;
	}
	public boolean search(JQuery db) throws java.sql.SQLException {
		try {
				String query = //
	"Select codusr, txtnam, txtpas, codbra, usrmail, datbeg, timbeg, codrea, codlim, txtusu, txtsec " +
				//
		"From "+Constante.ESQUEMA1+".TUSRDAT " + //
	"Where codusr = '" + getCodusr() + "'";
			db.setQuery(query);
			db.executeQuery();
			if (!db.getResultSet().next()) {
				return false;
			}
			codusr = db.stringValue(1);
			txtnam = db.stringValue(2);
			txtpas = Encrypt.unencrypt(db.stringValue(3), "PLER4&/!1BA97c&-|", "HQ89S");
			oldclave = txtpas;
			codbra = db.stringValue(4);
			usrmail = db.stringValue(5);
			datbeg = db.stringValue(6);
			timbeg = db.stringValue(7);
			codrea = db.stringValue(8);
			codlim = db.stringValue(9);
			txtusu = db.stringValue(10);
			txtsec = db.stringValue(11);
			loadModulos(db);
			return true;
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... search() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setCod_bra(String newValue) {
		this.cod_bra = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setCod_bra_nam(String newValue) {
		this.cod_bra_nam = newValue;
	}
	
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setCod_mod(String newValue) {
		this.cod_mod = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setCod_mod_nam(String newValue) {
		this.cod_mod_nam = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setCod_usr(String newValue) {
		this.cod_usr = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setCodbra(String newValue) {
		this.codbra = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setCodmod(String newValue) {
		if (newValue == null)
			newValue = "";
		this.codmod = newValue.trim();
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setCodrea(String newValue) {
		this.codrea = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setCodusr(String newValue) {
		this.codusr = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setDat_beg(String newValue) {
		this.dat_beg = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setDatbeg(String newValue) {
		this.datbeg = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String[]
	 */
	public void setListMod(java.lang.String[] newValue) {
		this.listMod = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.util.Vector
	 */
	protected void setModulos(java.util.Vector newValue) {
		this.modulos = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.util.Vector
	 */
	public void setModUsr(java.util.Vector newValue) {
		this.modUsr = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setNewclave(String newValue) {
		this.newclave = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setOldclave(String newValue) {
		this.oldclave = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setSta_mod(String newValue) {
		this.sta_mod = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setTim_beg(String newValue) {
		this.tim_beg = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setTimbeg(String newValue) {
		this.timbeg = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setTxt_bra(String newValue) {
		this.txt_bra = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setTxt_mod(String newValue) {
		this.txt_mod = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setTxt_nam(String newValue) {
		this.txt_nam = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setTxtCodrea(String newValue) {
		this.txtCodrea = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setTxtnam(String newValue) {
		this.txtnam = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setTxtpas(String newValue) {
		this.txtpas = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setUsr_mail(String newValue) {
		this.usr_mail = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setUsrmail(String newValue) {
		this.usrmail = newValue;
	}
	public void sort(JQuery db) throws java.sql.SQLException {
	}
	public void update(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
			String antNombre = getTxtnam();
			String antClave = getTxtpas();
			String antCodbra = getCodbra();
			String antUsrmail = getUsrmail();
			String antCodlim = getCodlim();
			String antTxtusu = getTxtusu();
			String antTxtsec = getTxtsec();
			if (search(db)) {
					String query = //
		"Update "+Constante.ESQUEMA1+".TUSRDAT set " + //
		"txtnam = '" + antNombre + "', " + //
		"codbra = '" + antCodbra + "', " + //
		"usrmail = '" + antUsrmail + "', " + //
		"codlim = '" + antCodlim + "', " + //
		"txtusu = '" + antTxtusu + "', " + //
		"txtsec = '" + antTxtsec + "' " + //
	"Where codusr = '" + getCodusr() + "' ";
				db.setQuery(query);
				int rows = db.executeUpdate();
				
				query="update ttotdet set codbra='"+antCodbra+"' where codusr='"+getCodusr()+"'";
				db.setQuery(query);
				rows = db.executeUpdate();

				query="update tcajdet set codbra='"+antCodbra+"' where codusr='"+getCodusr()+"'";
				db.setQuery(query);
				rows = db.executeUpdate();

				String bef[] = { getCodusr(), getTxtnam(), "******", getCodbra(), getUsrmail(), getDatbeg(), getTimbeg(), getCodlim(), getTxtusu(), getTxtsec()};
				String aft[] = { getCodusr(), antNombre, "******", antCodbra, antUsrmail, getDatbeg(), getTimbeg(), antCodlim, antTxtusu, antTxtusu};
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Modificar", Constante.ESQUEMA1+".TUSRDAT", Table.getColumnsTusrdat(), bef, aft);
				//
				if (listMod == null)
					return;
				if (listMod.length == 0)
					return;
					query = //
	"Select codmod from "+Constante.ESQUEMA1+".TUSRMOD where codusr = '" + getCodusr() + "' order by codmod";
				db.setQuery(query);
				db.executeQuery();
				java.util.Vector vector = new java.util.Vector(5);
				while (db.getResultSet().next()) {
					vector.addElement(db.stringValue(1));
				}
				boolean sw = false;
				for (int i = 0; i < vector.size(); i++) {
					sw = false;
					for (int j = 0; j < listMod.length; j++) {
						if (vector.elementAt(i).toString().trim().equals(listMod[j].trim())) {
							sw = true;
							break;
						}
					}
					if (!sw) {
							query = //
		"Delete from "+Constante.ESQUEMA1+".TUSRPRF Where codusr = '" + getCodusr() + "' " + //
	"And codmod = '" + vector.elementAt(i).toString().trim() + "'";
						db.setQuery(query);
						db.executeUpdate();
							query = //
		"Delete from "+Constante.ESQUEMA1+".tbrausr Where codusr = '" + getCodusr() + "' " + //
	"And codmod = '" + vector.elementAt(i).toString().trim() + "'";
						db.setQuery(query);
						db.executeUpdate();
							query = //
		/*"Delete from tusrsgn Where codusr = '" + getCodusr() + "' " + //
	"And codmod = '" + vector.elementAt(i).toString().trim() + "'";
						db.setQuery(query);
						db.executeUpdate();
							query = //*/
		"Delete from "+Constante.ESQUEMA1+".TUSRMOD Where codusr = '" + getCodusr() + "' " + //
	"And codmod = '" + vector.elementAt(i).toString().trim() + "'";
						db.setQuery(query);
						db.executeUpdate();
						String bef1[] = { getCodusr(), vector.elementAt(i).toString().trim()};
						String aft1[] = { "", "" };
						LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Modificar", Constante.ESQUEMA1+".TUSRMOD", Table.getColumnsTusrmod(), bef1, aft1);
					}
				}
				for (int i = 0; i < listMod.length; i++) {
					sw = false;
					for (int j = 0; j < vector.size(); j++) {
						if (listMod[i].trim().equals(vector.elementAt(j).toString().trim())) {
							sw = true;
							break;
						}
					}
					if (!sw) {
							query = //
	"Insert into "+Constante.ESQUEMA1+".TUSRMOD (codmod, codusr) values ('" + listMod[i] + "', '" + getCodusr() + "')";
						db.setQuery(query);
						db.executeUpdate();
						//
						String bef1[] = { "", "" };
						String aft1[] = { getCodusr(), listMod[i] };
						LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Modificar", Constante.ESQUEMA1+".TUSRMOD", Table.getColumnsTusrmod(), bef1, aft1);
						/*if (listMod[i].equalsIgnoreCase(PerfilesDeUsuarios.CODMOD_TELLER)) {
							LogDeUsuariosBranch log = new LogDeUsuariosBranch();
							log.setLogin(getLogin());
							log.setCodspv(getLogin().getUsuario());
							log.setCodusr(getCodusr());
							log.setDatbeg(CString.toDate("yyyy/MM/dd"));
							log.setTimbeg(CString.toTime());
							log.setCodrea(getCodrea());
							log.setCodbra(antCodbra);
							log.update(db);
						}*/
					}
				}
				//
				setGrid(null);
				loadGrid(db);
				setCodusr("");
				loadModulos(db);
			} else {
				String str = Mensajes.getMessage(Mensajes.CODIGO_DE_USUARIO_NO_EXISTE);
				int pos = str.indexOf('&');
				setError(str.substring(0, pos) + getCodusr() + str.substring(pos + 1));
			}
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... update() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	public void updateClave() throws java.sql.SQLException {
		JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
		JQuery db = new JQuery(jd.getConnection());
		try {
			setError("");
			String antClave = getOldclave();
			if (search(db)) {
				if (!antClave.equals(getOldclave())) {
					setError(Mensajes.getMessage(Mensajes.CLAVE_ERRADO));
				} else {
					txtpas = Encrypt.encrypt(getNewclave(), "PLER4&/!1BA97c&-|", "HQ89S");
						String query = //
		"Update "+Constante.ESQUEMA1+".TUSRDAT set " + //
		"txtpas = '" + getTxtpas() + "' " + //
	"Where codusr = '" + getCodusr() + "' ";
					db.setQuery(query);
					int rows = db.executeUpdate();
					setError(Mensajes.getMessage(Mensajes.CLAVE_MODIFICADA));
				}
			} else {
				String str = Mensajes.getMessage(Mensajes.CODIGO_DE_USUARIO_NO_EXISTE);
				int pos = str.indexOf('&');
				setError(str.substring(0, pos) + getCodusr() + str.substring(pos + 1));
			}
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... updateClave() - " + getClass().getName() + " -> " + e.getMessage());
		} finally {
			db.close();
			jd.close();
		}
	}
	/**
	 * Returns the codlim.
	 * @return String
	 */
	public String getCodlim() {
		return codlim;
	}

	/**
	 * Returns the limites.
	 * @return Vector
	 */
	public Vector getLimites() {

			setLimites(new Vector());
			JDatabase jd = null;
			JQuery db = null;
			try {
				jd = new JDatabase();
				db = new JQuery(jd.getConnection());
				String query = "Select codlim, txtlim From "+Constante.ESQUEMA1+".TLIMDAT order by codlim"; //
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					java.util.Vector row = new java.util.Vector(2);
					row.addElement(db.stringValue(1));
					row.addElement(db.stringValue(2));
					limites.addElement(row);
				}
			} catch (Exception e) {
				// System.out.println(e.getMessage());
			} finally {
				try {
					db.close();
					jd.close();
				} catch (Exception e1) {
				}
			}
		return limites;
	}

	/**
	 * Returns the oficinas.
	 * @return java.util.Vector
	 */
	public java.util.Vector getOficinas() {
		return oficinas;
	}

	/**
	 * Returns the txt_lim.
	 * @return String
	 */
	public String getTxt_lim() {
		return txt_lim;
	}

	/**
	 * Returns the txtlim.
	 * @return String
	 */
	public String getTxtlim() {
		return txtlim;
	}

	/**
	 * Sets the codlim.
	 * @param codlim The codlim to set
	 */
	public void setCodlim(String codlim) {
		this.codlim = codlim;
	}

	/**
	 * Sets the limites.
	 * @param limites The limites to set
	 */
	public void setLimites(Vector limites) {
		this.limites = limites;
	}

	/**
	 * Sets the oficinas.
	 * @param oficinas The oficinas to set
	 */
	public void setOficinas(java.util.Vector oficinas) {
		this.oficinas = oficinas;
	}

	/**
	 * Sets the txt_lim.
	 * @param txt_lim The txt_lim to set
	 */
	public void setTxt_lim(String txt_lim) {
		this.txt_lim = txt_lim;
	}

	/**
	 * Sets the txtlim.
	 * @param txtlim The txtlim to set
	 */
	public void setTxtlim(String txtlim) {
		this.txtlim = txtlim;
	}

	/**
	 * Returns the cod_lim.
	 * @return String
	 */
	public String getCod_lim() {
		return cod_lim;
	}

	/**
	 * Sets the cod_lim.
	 * @param cod_lim The cod_lim to set
	 */
	public void setCod_lim(String cod_lim) {
		this.cod_lim = cod_lim;
	}

	/**
	 * Returns the txt_usu.
	 * @return String
	 */
	public String getTxt_usu() {
		return txt_usu;
	}

	/**
	 * Returns the txtusu.
	 * @return String
	 */
	public String getTxtusu() {
		return txtusu;
	}

	/**
	 * Sets the txt_usu.
	 * @param txt_usu The txt_usu to set
	 */
	public void setTxt_usu(String txt_usu) {
		this.txt_usu = txt_usu;
	}

	/**
	 * Sets the txtusu.
	 * @param txtusu The txtusu to set
	 */
	public void setTxtusu(String txtusu) {
		this.txtusu = txtusu;
	}

	/**
	 * Returns the txt_sec.
	 * @return String
	 */
	public String getTxt_sec() {
		return txt_sec;
	}

	/**
	 * Returns the txtsec.
	 * @return String
	 */
	public String getTxtsec() {
		return txtsec;
	}

	/**
	 * Sets the txt_sec.
	 * @param txt_sec The txt_sec to set
	 */
	public void setTxt_sec(String txt_sec) {
		this.txt_sec = txt_sec;
	}

	/**
	 * Sets the txtsec.
	 * @param txtsec The txtsec to set
	 */
	public void setTxtsec(String txtsec) {
		this.txtsec = txtsec;
	}

}