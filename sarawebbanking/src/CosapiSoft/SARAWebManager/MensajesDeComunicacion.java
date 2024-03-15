package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class MensajesDeComunicacion {
	//
	private CosapiSoft.SARAWebBanking.InicioSesion login;
	//
	private java.util.Vector grid = null;
	private String status = "0";
	private String error = "";
	private String codmsgcom = "";
	private String numseq = "";
	private String coddic = "";
	private String txtmsgcom = "";
	private String numbegpos = "";
	private String numlon = "";
	//
	private String msg_seq = "";
	private String cod_msgcom = "";
	private String num_seq = "";
	private String cod_dic = "";
	private String txt_msgcom = "";
	private String num_begpos = "";
	private String num_lon = "";
/**
 * AyudaDeCampo constructor comment.
 */
public MensajesDeComunicacion() {
	super();
	grid = new java.util.Vector(1);
}
public void agregar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		if (!buscar(db)) {
			if(buscarDic()){
				String query = //
				"Insert into "+Constante.ESQUEMA1+".TCOMMSG values ('" + getCodmsgcom() + "', " + new Integer(getNumseq()).intValue() + ", '" + getCoddic() + "', '" + getTxtmsgcom() + "', '" + getNumbegpos() + "', '" + getNumlon() + "')";
				db.setQuery(query);
				db.executeUpdate();
				// System.out.println("wuery...."+query);
				String bef[] = {"", "", "", "", "", ""};
				String aft[] = {getCodmsgcom(), getNumseq(), getCoddic(), getTxtmsgcom(), getNumbegpos(), getNumlon()};
				LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Agregar", Constante.ESQUEMA1+".TCOMMSG", Table.getColumnsTcommsg(), bef, aft);
				loadGrid(db);
			}
			else{
				error = Mensajes.getMessage(Mensajes.COD_DICCIONARIO_NO_EXISTE);
				//int pos = str.indexOf('&');
				//int pos1 = pos + str.indexOf('&', pos + 1);
				//error = str.substring(0, pos) + getCodmsgcom() + str.substring(pos + 1, pos1) + getNumseq() + str.substring(pos1 + 1);
			}
		}
		else {
			String error = Mensajes.getMessage(Mensajes.CODIGO_DE_COMUNICACION_EXISTE);
			//int pos = str.indexOf('&');
			//int pos1 = pos + str.indexOf('&', pos + 1);
			//error = str.substring(0, pos) + getCodmsgcom() + str.substring(pos + 1, pos1) + getNumseq() + str.substring(pos1 + 1);
		}
	} catch (Exception e) {
		e.printStackTrace();
		throw new Exception("\nAgregar - Mensajes de Comunicación \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public boolean buscar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		if (getMsg_seq() != null || !getMsg_seq().equals("")) {
			java.util.StringTokenizer str = new java.util.StringTokenizer(msg_seq, "_");
			codmsgcom = str.nextElement().toString();
			numseq = str.nextElement().toString();
		}
		boolean sw = buscar(db);
		return sw;
	} catch (Exception e) {
		throw new Exception("\nBuscar - Mensajes de Comunicación \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
private boolean buscar(JQuery db) throws Exception {
	try {
		String query = //
		"Select coddic, txtmsgcom, numbegpos, numlon " + //
		"From "+Constante.ESQUEMA1+".TCOMMSG " + //
		"Where codmsgcom = '" + getCodmsgcom() + "' " + //
		"And numseq = " + new Integer(getNumseq()).intValue() + " ";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		coddic = db.stringValue(1);
		txtmsgcom = db.stringValue(2);
		numbegpos = db.stringValue(3);
		numlon = db.stringValue(4);
	} catch (Exception e) {
		throw new Exception("\nBuscar - Mensajes de Comunicación \n" + e.getMessage());
	}
	return true;
}
private boolean buscarDic() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		String query = //
		"Select txtdes " + //
		"From "+Constante.ESQUEMA1+".TDICDAT " + //
		"Where coddic = '" + getCoddic() + "' ";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		return true;
	} catch (Exception e) {
		throw new Exception("\nBuscar - Mensajes de Comunicación \n" + e.getMessage());
	}
	
}
public void consultar() throws Exception {
	try {
		String bef[] = {"", "", "", "", "", ""};
		String aft[] = {"", "", "", "", "", ""};
		LogOpe.saveLogManager(login.getNameProducto(), login.getUsuario(), "Consultar", Constante.ESQUEMA1+".TCOMMSG", Table.getColumnsTcommsg(), bef, aft);
	} catch (Exception e) {
		throw new Exception(" \n Consultar - Mensajes de Comunicación \n" + e.getMessage());
	}
}
public void eliminar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		if (buscar(db)) {
			String query = //
			"Delete from "+Constante.ESQUEMA1+".TCOMMSG Where codmsgcom = '" + getCodmsgcom() + "' " + //
			"And numseq = " + new Integer(getNumseq()).intValue() + " ";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {getCodmsgcom(), getNumseq(), getCoddic(), getTxtmsgcom(), getNumbegpos(), getNumlon()};
			String aft[] = {"", "", "", "", "", ""};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Eliminar", Constante.ESQUEMA1+".TCOMMSG", Table.getColumnsTcommsg(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_COMUNICACION_NO_EXISTE);
			int pos = str.indexOf('&');
			int pos1 = pos + str.indexOf('&', pos + 1);
			error = str.substring(0, pos) + getCodmsgcom() + str.substring(pos + 1, pos1) + getNumseq() + str.substring(pos1 + 1);
		}
	} catch (Exception e) {
		throw new Exception("\nEliminar - Mensajes de Comunicación \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public void eliminar(javax.servlet.http.HttpServletRequest req) throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		java.util.Enumeration vector = req.getParameterNames();
		while (vector.hasMoreElements() && vector != null) {
			msg_seq = vector.nextElement().toString();
			String sw = req.getParameter(msg_seq).toUpperCase();
			if (sw.equals("ON")) {
				java.util.StringTokenizer str = new java.util.StringTokenizer(msg_seq, "_");
				codmsgcom = str.nextElement().toString();
				numseq = str.nextElement().toString();
				if (buscar(db)) {
					String query = //
					"Delete from "+Constante.ESQUEMA1+".TCOMMSG Where codmsgcom = '" + getCodmsgcom() + "' " + //
					"And numseq = " + new Integer(getNumseq()).intValue() + " ";
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {getCodmsgcom(), getNumseq(), getCoddic(), getTxtmsgcom(), getNumbegpos(), getNumlon()};
					String aft[] = {"", "", "", "", "", ""};
					LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Eliminar", Constante.ESQUEMA1+".TCOMMSG", Table.getColumnsTcommsg(), bef, aft);
				}
			}
		}
		loadGrid(db);
	} catch (Exception e) {
		throw new Exception("\nEliminar - Mensajes de Comunicación \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_dic() {
	return cod_dic;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_msgcom() {
	if (cod_msgcom == null)
		cod_msgcom = "";
	return cod_msgcom.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCoddic() {
	if (coddic == null)
		coddic = "";
	coddic = coddic.trim();
	if (!coddic.equals(""))
		coddic = CString.completeToBeginWithNChars(coddic, 3, '0');
	return coddic.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodmsgcom() {
	try {
		if (codmsgcom == null || codmsgcom.equals(""))
			codmsgcom = "0";
		codmsgcom = codmsgcom.trim();
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(codmsgcom)) {
			codmsgcom = "0";
		}
		int cod = new Integer(codmsgcom).intValue();
		if (cod <= 0) {
			JDatabase jd = new JDatabase();
			JQuery db = new JQuery(jd.getConnection());
			try {
				String query = //
				"Select codmsgcom " + //
				"From "+Constante.ESQUEMA1+".TCOMMSG " + //
				"Order by codmsgcom ";
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					codmsgcom = db.stringValue(1).trim();
				}
				cod = new Integer(codmsgcom).intValue();
				codmsgcom = (cod >= 9999) ? "9999" : "" + ((cod == 0) ? 1 : cod);
			} catch (Exception ex) {
				// System.out.println(getClass().getName() + " - getCodmsgcom() -> " + ex.getMessage());
				return "-1";
			} finally {
				db.close();
				jd.close();
			}
		}
		if (!codmsgcom.equals(""))
			codmsgcom = CString.completeToBeginWithNChars(codmsgcom, 4, '0');
		return codmsgcom.trim();
	} catch (Exception e) {
		return "-1";
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getError() {
	return error;
}
/**
 * This method was created in VisualAge.
 * @return CosapiSoft.SARAWebBanking.InicioSesion
 */
public CosapiSoft.SARAWebBanking.InicioSesion getLogin() {
	return login;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getMsg_seq() {
	if (msg_seq == null)
		return "";
	return msg_seq;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNum_begpos() {
	return num_begpos;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNum_lon() {
	return num_lon;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNum_seq() {
	return num_seq;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNumbegpos() {
	CString str = new CString(numbegpos);
	str.arrange();
	return str.toString().trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNumlon() {
	CString str = new CString(numlon);
	str.arrange();
	return str.toString().trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNumseq() {
	String query = "";
	try {
		if (numseq == null || numseq == "")
			numseq = "0";
		if (CString.isInteger(numseq))
			numseq = "" + CString.toInteger(numseq);
		if (numseq.equals("0")) {
			JDatabase jd = new JDatabase();
			JQuery db = new JQuery(jd.getConnection());
			try {
				query = //
				"Select max(numseq) " + //
				"From "+Constante.ESQUEMA1+".TCOMMSG " + //
				"Where codmsgcom = '" + getCodmsgcom() + "' ";
				db.setQuery(query);
				db.executeQuery();
				numseq = (!db.getResultSet().next()) ? "1" : "" + (db.intValue(1) + 1);
			} catch (Exception ex) {
				// System.out.println(getClass().getName() + " - getNumseq() --> " + ex.getMessage());
				return "-1";
			} finally {
				db.close();
				jd.close();
			}
		}
		return numseq.trim();
	} catch (Exception e) {
		// System.out.println("getNumseq() - Query = " + query + " -> " + e.getMessage());
		return "-1";
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getStatus() {
	return status;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxt_msgcom() {
	return txt_msgcom;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxtmsgcom() {
	CString str = new CString(txtmsgcom);
	str.arrange();
	return str.toString();
}
public String getUrlMensajesDeComunicacion() {
	return JspServlet.MENSAJES_DE_COMUNICACION_SERVLET + "?BtnCom=Buscar&TxtMsg_seq=";
}
public void loadGrid() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		loadGrid(db);
	} catch (Exception e) {
		throw new Exception("\nLoadGrid - Mensajes de Comunicación \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
private void loadGrid(JQuery db) throws Exception {
	grid = new java.util.Vector(10);
	try {
		String query = //
		"Select codmsgcom, numseq, coddic, txtmsgcom, numbegpos, numlon " + //
		"From "+Constante.ESQUEMA1+".TCOMMSG " + //
		"Order by codmsgcom, numseq";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			status = "0";
			java.util.Vector row = new java.util.Vector(5);
			row.addElement(status);
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			row.addElement(db.stringValue(3));
			row.addElement(db.stringValue(4));
			row.addElement(db.stringValue(5));
			row.addElement(db.stringValue(6));
			grid.addElement(row);
		}
	} catch (Exception e) {
		throw new Exception("\nLoadGrid - Mensajes de Comunicación \n" + e.getMessage());
	}
}
public void modificar() throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		String antCoddic = getCoddic();
		String antTxtmsgcom = getTxtmsgcom();
		String antNunbegpos = getNumbegpos();
		String antNumlon = getNumlon();
		if (buscar(db)) {
			String query = //
			"Update "+Constante.ESQUEMA1+".TCOMMSG set " + //
			"coddic = '" + antCoddic + "', " + //
			"txtmsgcom = '" + antTxtmsgcom + "', " + //
			"numbegpos = '" + antNunbegpos + "', " + //
			"numlon = '" + antNumlon + "' " + //
			"Where codmsgcom = '" + getCodmsgcom() + "' " + //
			"And numseq = " + new Integer(getNumseq()).intValue() + " ";
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getCodmsgcom(), getNumseq(), getCoddic(), getTxtmsgcom(), getNumbegpos(), getNumlon()};
			String aft[] = {getCodmsgcom(), getNumseq(), antCoddic, antTxtmsgcom, antNunbegpos, antNumlon};
			LogOpe.saveLogManager(db, login.getNameProducto(), login.getUsuario(), "Modificar", Constante.ESQUEMA1+".TCOMMSG", Table.getColumnsTcommsg(), bef, aft);
			loadGrid(db);
		} else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_COMUNICACION_NO_EXISTE);
			int pos = str.indexOf('&');
			int pos1 = pos + str.indexOf('&', pos + 1);
			error = str.substring(0, pos) + getCodmsgcom() + str.substring(pos + 1, pos1) + getNumseq() + str.substring(pos1 + 1);
		}
	} catch (Exception e) {
		throw new Exception("\nModificar - Mensajes de Comunicación \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
public void next(int i) {
	status = ((java.util.Vector) grid.elementAt(i)).elementAt(0).toString();
	cod_msgcom = ((java.util.Vector) grid.elementAt(i)).elementAt(1).toString();
	num_seq = ((java.util.Vector) grid.elementAt(i)).elementAt(2).toString();
	cod_dic = ((java.util.Vector) grid.elementAt(i)).elementAt(3).toString();
	txt_msgcom = ((java.util.Vector) grid.elementAt(i)).elementAt(4).toString();
	num_begpos = ((java.util.Vector) grid.elementAt(i)).elementAt(5).toString();
	num_lon = ((java.util.Vector) grid.elementAt(i)).elementAt(6).toString();
	msg_seq = cod_msgcom + "_" + num_seq;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_dic(String newValue) {
	this.cod_dic = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_msgcom(String newValue) {
	this.cod_msgcom = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCoddic(String newValue) {
	this.coddic = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodmsgcom(String newValue) {
	this.codmsgcom = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setError(String newValue) {
	this.error = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue CosapiSoft.SARAWebBanking.InicioSesion
 */
public void setLogin(CosapiSoft.SARAWebBanking.InicioSesion newValue) {
	this.login = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setMsg_seq(String newValue) {
	this.msg_seq = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setNum_begpos(String newValue) {
	this.num_begpos = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setNum_lon(String newValue) {
	this.num_lon = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setNum_seq(String newValue) {
	this.num_seq = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setNumbegpos(String newValue) {
	this.numbegpos = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setNumlon(String newValue) {
	this.numlon = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setNumseq(String newValue) {
	this.numseq = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setStatus(String newValue) {
	this.status = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxt_msgcom(String newValue) {
	this.txt_msgcom = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxtmsgcom(String newValue) {
	this.txtmsgcom = newValue;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public java.util.Vector getGrid() {
	return grid;
}}