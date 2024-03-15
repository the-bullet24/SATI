package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import java.text.NumberFormat;
import java.util.Vector;

import pe.cosapi.common.Constante;

import CosapiSoft.SARAWebBanking.*;
public class Transacciones extends GenericTable {
	//
	public java.util.Vector grupo = null;
	public java.util.Vector moneda = null;
	public java.util.Vector canal = null;
	//
	private String codgrp = "";
	private String codlim = "";
	private String codtra = "";
	private String houini = "";
	private String houfin = "";
	private String flgenb = "";
	private String txttra = "";
	private String txtlim = "";
	private String txtprcgde = "";
	private String codchn = "";
	private String txtchn = "";
	private String flgofl = "";
	private String flgofd = "";
	private String flgjou = "";
	private String flgrec = "";
	private String flgrev = "";
	private String codrev = "";
	private String codcur = "";
	private String limsup = "";
	private String liminf = "";
	private String timout1 = "";
	private String timout2 = "";
	private String codnxttra = "";
	//
	private String cod_grp_nam = "";
	private String cod_grp = "";
	private String cod_lim = "";
	private String grp_nam = "";
	private String txt_guia = "";
	private String txt_lim = "";
	private String hou_ini = "";
	private String hou_fin = "";
	private String flg_enb = "";
	private String cod_tra = "";
	private String txt_tra = "";
	private String cod_chn = "";
	private String txt_chn = "";
	private String flg_ofl = "";
	private String flg_ofd = "";
	private String flg_jou = "";
	private String flg_rec = "";
	private String tim_out1 = "";
	private String tim_out2 = "";
	private String flg_rev = "";
	private String cod_rev = "";
	private String cod_cur_nam = "";
	private String lim_sup = "";
	private String lim_inf = "";
	private String cod_tra_chn = "";
	private String cod_cur = "";
	private String cod_chn_nam = "";
	// Manejo de Pantallas
	private static java.util.Vector back = null;
	private String cod_nxt_tra = "";
	private Vector limites = null;
/**
 * AyudaDeCampo constructor comment.
 */
public Transacciones() {
	super();
	grupo = new java.util.Vector(1);
	moneda = new java.util.Vector(1);
	canal = new java.util.Vector(1);
}
public void consult() throws java.sql.SQLException {
	try {
		String bef[] = {"", "", "", "", "", "", "", "", "", ""};
		String aft[] = {"", "", "", "", "", "", "", "", "", ""};
		LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", Constante.ESQUEMA1+".TTRADAT", Table.getColumnsTtradat(), bef, aft);
	} catch (Exception e) {
		throw new java.sql.SQLException(" \n... consult() - Transacciones \n" + e.getMessage());
	}
}
public void delete(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		if (search(db)) {
			// System.out.println("YA LLEGÓ AQUI--------"+codtra);
			String query = //
			"Delete from "+Constante.ESQUEMA1+".TJOUDIC Where codtra = '" + codtra + "'";
			db.setQuery(query);
			db.executeUpdate();
			query = //
			
			"Delete from "+Constante.ESQUEMA1+".TCTRTRA Where codtra = '" + codtra + "'";
			db.setQuery(query);
			db.executeUpdate();
			query = //
			"Delete from "+Constante.ESQUEMA1+".TMETARGALT Where codtra = '" + codtra + "'";
			db.setQuery(query);
			db.executeUpdate();
			query = //
			"Delete from "+Constante.ESQUEMA1+".TMETARG Where codtra = '" + codtra + "'";
			db.setQuery(query);
			db.executeUpdate();
			query = //
			"Delete from "+Constante.ESQUEMA1+".TSCHTRA Where codtra = '" + codtra + "'";
			db.setQuery(query);
			db.executeUpdate();
			query = //
			"Delete from "+Constante.ESQUEMA1+".TTRAFAS Where codtra = '" + codtra + "'";
			db.setQuery(query);
			db.executeUpdate();
			query = //
			
			"Delete from "+Constante.ESQUEMA1+".TTRADAT Where codtra = '" + codtra + "'";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {getCodtra(), getCodgrp(), getTxttra(), getTxtprcgde(), getFlgjou(), getHouini(), getHoufin(), getFlgenb()};
			String aft[] = {"", "", "", "", "", "", "", "", "", ""};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TTRADAT", Table.getColumnsTtradat(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_TRX_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodtra() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public void delete(JQuery db, javax.servlet.http.HttpServletRequest req) throws java.sql.SQLException {
	try {
		setError("");
		java.util.Enumeration vector = req.getParameterNames();
		while (vector.hasMoreElements() && vector != null) {
			cod_tra_chn = vector.nextElement().toString();
			String sw = req.getParameter(cod_tra_chn).toUpperCase();
			if (sw.equals("ON")) {
				java.util.StringTokenizer str = new java.util.StringTokenizer(cod_tra_chn, "_");
				codtra = str.nextElement().toString();
				//codchn = str.nextElement().toString();
				if (search(db)) {
					// System.out.println("YA LLEGÓ AQUI--------"+codtra);
					String query = //
						"Delete from "+Constante.ESQUEMA1+".TJOUDIC Where codtra = '" + codtra + "'";
					db.setQuery(query);
					db.executeUpdate();
					query = //
					
					"Delete from "+Constante.ESQUEMA1+".TCTRTRA Where codtra = '" + codtra + "'";
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from "+Constante.ESQUEMA1+".TMETARGALT Where codtra = '" + codtra + "'";
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from "+Constante.ESQUEMA1+".TMETARG Where codtra = '" + codtra + "'";
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from "+Constante.ESQUEMA1+".TSCHTRA Where codtra = '" + codtra + "'";
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from "+Constante.ESQUEMA1+".TTRAFAS Where codtra = '" + codtra + "'";
					db.setQuery(query);
					db.executeUpdate();
					query = //
					
					"Delete from "+Constante.ESQUEMA1+".TTRADAT Where codtra = '" + codtra + "'";
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {getCodtra(), getCodgrp(), getTxttra(), getTxtprcgde(), getFlgjou(), getHouini(), getHoufin(), getFlgenb()};
					String aft[] = {"", "", "", "", "", "", "", "", "", ""};
					LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TTRADAT", Table.getColumnsTtradat(), bef, aft);
					setGrid(null);
				}
			}
		}
		loadGrid(db);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 * @return java.util.Vector
 */
public static java.util.Vector getBack() {
	if (back == null) {
		back = new java.util.Vector(10, 10);
	}
	return back;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_chn() {
	return cod_chn;
}
public String getFlg_enb() {
	if (CString.isEmptyOrNull(flg_enb))
		flg_enb = "0";
	return flg_enb.trim();
}
public String getFlgenb() {
	if (CString.isEmptyOrNull(flgenb))
		flgenb = "0";
	return flgenb.trim();
}
public String getHouini() {
	houini = (houini == null || houini.equals("")) ? "00:00" : houini;
	return houini;
}
public String getHoufin() {
	houfin = (houfin == null || houfin.equals("")) ? "00:00" : houfin;
	return houfin;
}
public String getHou_ini() {
	if (hou_ini == null || hou_ini.equals(""))
		hou_ini = "---";
	CString str = new CString(hou_ini);
	str.arrange();
	return str.toString();
}
public String getHou_fin() {
	if (hou_fin == null || hou_fin.equals(""))
		hou_fin = "---";
	CString str = new CString(hou_fin);
	str.arrange();
	return str.toString();
}
public void setHou_fin(String newValue) {
	this.hou_fin = newValue;
}

public void setFlgenb(String newValue) {
	this.flgenb = newValue;
}
public void setHou_ini(String hou_ini) {
	this.hou_ini = hou_ini;
}

public void setHouini(String houini) {
	this.houini = houini;
}
public void setHoufin(String houfin) {
	this.houfin = houfin;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_chn_nam() {
	return cod_chn_nam;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_cur() {
	return cod_cur;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_cur_nam() {
	return cod_cur_nam;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_grp() {
	if (cod_grp == null)
		cod_grp = "";
	return cod_grp.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_grp_nam() {
	CString str = new CString(cod_grp_nam);
	str.arrange();
	return str.toString();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_nxt_tra() {
	return cod_nxt_tra;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_rev() {
	if (cod_rev == null || cod_rev.equals(""))
		cod_rev = "---";
	return cod_rev.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_tra() {
	return cod_tra;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_tra_chn() {
	return cod_tra_chn;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodchn() {
	return codchn;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodcur() {
	return codcur;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodgrp() {
	if (codgrp == null)
		codgrp = "";
	return codgrp.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodnxttra() {
	codnxttra = (codnxttra == null) ? "" : codnxttra.trim();
	return codnxttra;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodrev() {
	return codrev;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodtra() {
	if (codtra == null)
		codtra = "";
	codtra = codtra.trim();
	if (!codtra.equals(""))
		codtra = CString.completeToBeginWithNChars(codtra, 4, '0');
	return codtra.trim().toUpperCase();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getFlg_jou() {
	if (CString.isEmptyOrNull(flg_jou))
		flg_jou = "0";
	return flg_jou.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getFlg_ofd() {
	if (CString.isEmptyOrNull(flg_ofd))
		flg_ofd = "0";
	return flg_ofd.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getFlg_ofl() {
	if (CString.isEmptyOrNull(flg_ofl))
		flg_ofl = "0";
	return flg_ofl.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getFlg_rec() {
	if (CString.isEmptyOrNull(flg_rec))
		flg_rec = "0";
	return flg_rec.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getFlg_rev() {
	if (CString.isEmptyOrNull(flg_rev))
		flg_rev = "0";
	return flg_rev.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getFlgjou() {
	if (CString.isEmptyOrNull(flgjou))
		flgjou = "0";
	return flgjou.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getFlgofd() {
	if (CString.isEmptyOrNull(flgofd))
		flgofd = "0";
	return flgofd.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getFlgofl() {
	if (CString.isEmptyOrNull(flgofl))
		flgofl = "0";
	return flgofl.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getFlgrec() {
	if (CString.isEmptyOrNull(flgrec))
		flgrec = "0";
	return flgrec.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getFlgrev() {
	if (CString.isEmptyOrNull(flgrev))
		flgrev = "0";
	return flgrev.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getGrp_nam() {
	CString str = new CString(grp_nam);
	str.arrange();
	return str.toString();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getLim_inf() {
	return lim_inf;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getLim_sup() {
	return lim_sup;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getLiminf() {
	liminf = (liminf == null || liminf.equals("")) ? "00:00" : liminf;
	return liminf;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getLimsup() {
	limsup = (limsup == null || limsup.equals("")) ? "00:00" : limsup;
	return limsup;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNameTable() {
	return "ttradat";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxt_chn() {
	return txt_chn;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxt_guia() {
	if (txt_guia == null || txt_guia.equals(""))
		txt_guia = "---";
	CString str = new CString(txt_guia);
	str.arrange();
	return str.toString();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxt_tra() {
	if (txt_tra == null || txt_tra.equals(""))
		txt_tra = "---";
	CString str = new CString(txt_tra);
	str.arrange();
	return str.toString();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxtchn() {
	return txtchn;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxtprcgde() {
	CString str = new CString(txtprcgde);
	str.arrange();
	return CString.compact(str.toString(), 250);
}
/**
 * This method was created in VisualAge.
 * @return String
 */
public String getTxttra() {
	CString str = new CString(txttra);
	str.arrange();
	return str.toString();
}
public String getUrl() {
	return getUrlTransacciones();
}
public String getUrlTransacciones() {
	return JspServlet.TRANSACCIONES_SERVLET + "?BtnTrx=Buscar&TxtCod_tra_chn=";
}
public void insert(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		if (!search(db) && !getCodgrp().equals("")) {
			String query = //
			"Insert into "+Constante.ESQUEMA1+".TTRADAT (codtra,codgrp, txttra, txtprcgde, flgjou, houini, houfin,flgenb,timout1,timout2) values (" + // 
			"'" + getCodtra().toUpperCase() + "', '" + getCodgrp() + "', '" + getTxttra() + "', " + // 
			"'" + getTxtprcgde() + "', '" + getFlgjou() + "', " + // 
			"'" + getHouini() + "', '" + getHouini() + "', " + //
			"'" + getFlgenb() +  "', '" + getTimout1() + "', '" + getTimout2() + "')";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", "", "", "", "", "", "", "", "", ""};
			String aft[] = {getCodtra(), getCodgrp(), getTxttra(), getTxtprcgde(), getFlgjou(), getHouini(),getHoufin(), getFlgenb(),getTimout1(),getTimout2()};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TTRADAT", Table.getColumnsTtradat(), bef, aft);
			setGrid(null);
			loadGrid(db);
		} else {
			if (getCodgrp().equals("")) {
				String str = Mensajes.getMessage(Mensajes.CODIGO_DE_GRUPO_TRX_NO_EXISTE);
				// System.out.println(str);
				int pos = str.indexOf('&');
				setError(str.substring(0, pos) + getCodgrp() + str.substring(pos + 1));
			} else {
				String str = Mensajes.getMessage(Mensajes.CODIGO_DE_TRX_EXISTE);
				// System.out.println(str);
				int pos = str.indexOf('&');
				setError(str.substring(0, pos) + getCodtra() + str.substring(pos + 1));
			}
		}
	} catch (Exception e) {
		throw new java.sql.SQLException(" \n...insert() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public void loadGrid(JQuery db) throws java.sql.SQLException {
	setIndex(0);
	setStatus("0");
	if (getGrid().size() == 0) {
		try {
			String query = //
			"Select a.codtra, a.codgrp, a.txttra, a.txtprcgde, " + //
			"a.flgjou, a.houini, a.houini, a.flgenb, a.timout1, a.timout2 " + //
			"From "+Constante.ESQUEMA1+".TTRADAT a " + //
			"Order by a.codtra";
			db.setQuery(query);
			db.executeQuery();
			NumberFormat nf=NumberFormat.getInstance();
			while (db.getResultSet().next()) {
				java.util.Vector row = new java.util.Vector();
				row.addElement(getStatus());
				String aux="";
				aux=db.stringValue(1);
				row.addElement(aux);
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
		}
		catch (Exception e) {
			throw new java.sql.SQLException("\n... loadGrid() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
}
/*public void loadGridCanal() throws Exception {
	JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
	JQuery db = new JQuery(jd.getConnection());
	canal = new java.util.Vector(20, 10);
	try {
		String query = //
		"Select codchn, txtchn " + //
		"From tchndat " + //
		"Order by codchn";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector(3);
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			canal.addElement(row);
		}
	}
	catch (Exception e) {
		throw new Exception("\n... loadGridCanal() - " + getClass().getName() + " -> " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}*/
public void loadGridChild(JQuery db) throws java.sql.SQLException {
}
public void loadGridGrupo() throws Exception {
	JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
	JQuery db = new JQuery(jd.getConnection());
	grupo = new java.util.Vector(20, 10);
	try {
		String query = //
		"Select codgrp, txtdes " + //
		"From "+Constante.ESQUEMA1+".TGRPTRA " + //
		"Order by codgrp";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector(3);
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			grupo.addElement(row);
		}
	}
	catch (Exception e) {
		throw new Exception("\n... loadGridGrupo() - " + getClass().getName() + " -> " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
public void loadGridMoneda() throws Exception {
	JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
	JQuery db = new JQuery(jd.getConnection());
	moneda = new java.util.Vector(20, 10);
	try {
		String query = //
		"Select codcur, txtcurlon " + //
		"From "+Constante.ESQUEMA1+".TCURDAT " + //
		"Order by codcur";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector(3);
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			moneda.addElement(row);
		}
	}
	catch (Exception e) {
		throw new Exception("\n... loadGridMoneda() - " + getClass().getName() + " -> " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
public void next(int i) {
	setStatus(((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString());
	cod_tra = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
	cod_grp = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
	txt_tra = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
	txt_guia = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
	flg_jou = ((java.util.Vector) getGrid().elementAt(i)).elementAt(5).toString();
	hou_ini = ((java.util.Vector) getGrid().elementAt(i)).elementAt(6).toString();
	hou_fin = ((java.util.Vector) getGrid().elementAt(i)).elementAt(7).toString();
	flg_enb = ((java.util.Vector) getGrid().elementAt(i)).elementAt(8).toString();
	tim_out1 = ((java.util.Vector) getGrid().elementAt(i)).elementAt(9).toString();
	tim_out2 = ((java.util.Vector) getGrid().elementAt(i)).elementAt(10).toString();
	txt_tra = CString.compact(txt_tra, 25);
	txt_guia = CString.compact(getTxt_guia(), 30);
	flg_jou = (flg_jou.equals("") || flg_jou.equals("0")) ? "NO" : "SI";
	flg_enb = (flg_enb.equals("") || flg_enb.equals("0")) ? "NO" : "SI";
	hou_ini = CString.compact(hou_ini, 25);
	hou_ini = CString.compact(hou_ini, 25);
	cod_tra_chn = cod_tra;
}
public void nextCanal(int i) {
	cod_chn = ((java.util.Vector) canal.elementAt(i)).elementAt(0).toString();
	txt_chn = ((java.util.Vector) canal.elementAt(i)).elementAt(1).toString();
	cod_chn_nam = cod_chn + " - " + txt_chn;
}
public void nextChild(int i) {
}
public void nextGrupo(int i) {
	cod_grp = ((java.util.Vector) grupo.elementAt(i)).elementAt(0).toString();
	grp_nam = ((java.util.Vector) grupo.elementAt(i)).elementAt(1).toString();
	cod_grp_nam = cod_grp + " - " + grp_nam;
}
public void nextMoneda(int i) {
	cod_cur = ((java.util.Vector) moneda.elementAt(i)).elementAt(0).toString();
	cod_cur_nam = cod_cur + " - " + ((java.util.Vector) moneda.elementAt(i)).elementAt(1).toString();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public static String pop() {
	if (getBack().size() != 0) {
		String back = new String(getBack().lastElement().toString().trim());
		getBack().removeElementAt(getBack().size() - 1);
		return back;
	}
	return "";
}
/**
 * This method was created in VisualAge.
 * @param element java.lang.String
 */
public static void push(String element) {
	getBack().addElement(element);
}
public boolean search(JQuery db) throws java.sql.SQLException {
	try {
		
		String query = //
		"Select a.codgrp, a.txttra, a.txtprcgde, b.txtdes, " + //
		"a.flgjou, a.houini, a.houfin,a.flgenb, a.timout1, a.timout2 " + //
		"From "+Constante.ESQUEMA1+".TTRADAT a, "+Constante.ESQUEMA1+".TGRPTRA b " + //
		"Where a.codtra = '" + getCodtra() + "' " + //
		"And a.codgrp = b.codgrp ";
		db.setQuery(query);
		db.executeQuery();
		// System.out.println("query search---"+query);
		if (!db.getResultSet().next())
			return false;
		NumberFormat nf=NumberFormat.getInstance();
		codgrp = db.stringValue(1);
		txttra = db.stringValue(2);
		txtprcgde = db.stringValue(3);
		cod_grp = codgrp;
		grp_nam = db.stringValue(4);
		flgjou = db.stringValue(5);
		houini = db.stringValue(6);
		houfin = db.stringValue(7);
		flgenb = db.stringValue(8);
		timout1 = db.stringValue(9);
		timout2 = db.stringValue(10);
		txt_tra = txttra;
		return true;
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... search() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 * @param newValue java.util.Vector
 */
public static void setBack(java.util.Vector newValue) {
	back = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_chn(String newValue) {
	this.cod_chn = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_chn_nam(String newValue) {
	this.cod_chn_nam = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_cur(String newValue) {
	this.cod_cur = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_cur_nam(String newValue) {
	this.cod_cur_nam = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_grp(String newValue) {
	this.cod_grp = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_grp_nam(String newValue) {
	this.cod_grp_nam = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_nxt_tra(String newValue) {
	this.cod_nxt_tra = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_rev(String newValue) {
	this.cod_rev = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_tra(String newValue) {
	this.cod_tra = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_tra_chn(String newValue) {
	this.cod_tra_chn = newValue;
	java.util.StringTokenizer str = new java.util.StringTokenizer(cod_tra_chn, "_");
	codtra = str.nextElement().toString().trim();
	//codchn = str.nextElement().toString().trim();
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodchn(String newValue) {
	this.codchn = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodcur(String newValue) {
	this.codcur = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodgrp(String newValue) {
	this.codgrp = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodnxttra(String newValue) {
	this.codnxttra = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodrev(String newValue) {
	this.codrev = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodtra(String newValue) {
	this.codtra = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setFlg_jou(String newValue) {
	this.flg_jou = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setFlg_ofd(String newValue) {
	this.flg_ofd = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setFlg_ofl(String newValue) {
	this.flg_ofl = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setFlg_rec(String newValue) {
	this.flg_rec = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setFlg_rev(String newValue) {
	this.flg_rev = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setFlgjou(String newValue) {
	this.flgjou = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setFlgofd(String newValue) {
	this.flgofd = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setFlgofl(String newValue) {
	this.flgofl = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setFlgrec(String newValue) {
	this.flgrec = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setFlgrev(String newValue) {
	this.flgrev = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setGrp_nam(String newValue) {
	this.grp_nam = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setLim_inf(String newValue) {
	this.lim_inf = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setLim_sup(String newValue) {
	this.lim_sup = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setLiminf(String newValue) {
	this.liminf = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setLimsup(String newValue) {
	this.limsup = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxt_chn(String newValue) {
	this.txt_chn = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxt_guia(String newValue) {
	this.txt_guia = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxt_tra(String newValue) {
	this.txt_tra = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxtchn(String newValue) {
	this.txtchn = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxtprcgde(String newValue) {
	this.txtprcgde = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setTxttra(String newValue) {
	this.txttra = newValue;
}
public void sort(JQuery db) throws java.sql.SQLException {
}
public void update(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		String antCodgrp = getCodgrp();
		String antTxttra = getTxttra();
		String antTxtprcgde = getTxtprcgde();
		String antFlgjou = getFlgjou();
		String antHouini = getHouini();
		String antHoufin = getHoufin();
		String antFlgenb = getFlgenb();
		String antTimout1 = getTimout1();
		String antTimout2 = getTimout2();
		if (search(db)) {
			String query = //
			"Update "+Constante.ESQUEMA1+".TTRADAT set " + //
			"codgrp = '" + antCodgrp + "', " + //
			"txttra = '" + antTxttra + "', " + //
			"txtprcgde = '" + antTxtprcgde + "', " + //
			"flgjou = '" + antFlgjou + "', " + //
			"houini = '" + antHouini + "', " + //
			"houfin = '" + antHoufin + "', " + //
			"flgenb = '" + antFlgenb + "', " + //
			"timout1 = '" + antTimout1 + "', " + //
			"timout2 = '" + antTimout2 + "' " + //
			"Where codtra = '" + getCodtra() + "' ";
			// System.out.println("update------"+query);
			db.setQuery(query);
			int rows = db.executeUpdate();
			setGrid(null);
			loadGrid(db);
			String bef[] = {getCodtra(), getCodgrp(), getTxttra(), getTxtprcgde(), getFlgjou(), getHouini(), getHoufin(), getFlgenb(), getTimout1(), getTimout2()};
			String aft[] = {getCodtra(), antCodgrp, antTxttra, antTxtprcgde, antFlgjou, antHouini, antHoufin, antFlgenb, antTimout1, antTimout2};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Modificar", Constante.ESQUEMA1+".TTRADAT", Table.getColumnsTtradat(), bef, aft);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_TRX_NO_EXISTE);
			// System.out.println(str);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodtra() + str.substring(pos + 1));
		}
	} catch (Exception e) {
		throw new java.sql.SQLException("\n... update() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
	private String funcval = "";/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getFuncval() {
	return funcval;
}/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */ 
public void setFuncval(String newValue) {
	this.funcval = newValue;
	/**
	 * Returns the cod_lim.
	 * @return String
	 */
}
	public String getCod_lim() {
		return cod_lim;
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
	 * Sets the cod_lim.
	 * @param cod_lim The cod_lim to set
	 */
	public void setCod_lim(String cod_lim) {
		this.cod_lim = cod_lim;
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
	 * Sets the limites.
	 * @param limites The limites to set
	 */
	public void setLimites(Vector limites) {
		this.limites = limites;
	}

	/**
	 * @return Devuelve tim_out1.
	 */
	public String getTim_out1() {
		tim_out1 = (tim_out1 == null || tim_out1.equals("")) ? "0" : tim_out1;
		return tim_out1;
	}
	/**
	 * @param tim_out1 El tim_out1 a establecer.
	 */
	public void setTim_out1(String tim_out1) {
		this.tim_out1 = tim_out1;
	}
	/**
	 * @return Devuelve tim_out2.
	 */
	public String getTim_out2() {
		tim_out2 = (tim_out2 == null || tim_out2.equals("")) ? "0" : tim_out2;
		return tim_out2;
	}
	/**
	 * @param tim_out2 El tim_out2 a establecer.
	 */
	public void setTim_out2(String tim_out2) {
		this.tim_out2 = tim_out2;
	}
	/**
	 * @return Devuelve timout1.
	 */
	public String getTimout1() {
		timout1 = (timout1 == null || timout1.equals("")) ? "0" : timout1;
		return timout1;
	}
	/**
	 * @param timout1 El timout1 a establecer.
	 */
	public void setTimout1(String timout1) {
		this.timout1 = timout1;
	}
	/**
	 * @return Devuelve timout2.
	 */
	public String getTimout2() {
		timout2 = (timout2 == null || timout2.equals("")) ? "0" : timout2;
		return timout2;
	}
	/**
	 * @param timout2 El timout2 a establecer.
	 */
	public void setTimout2(String timout2) {
		this.timout2 = timout2;
	}
}