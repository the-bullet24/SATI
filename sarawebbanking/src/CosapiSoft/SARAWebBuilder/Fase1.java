package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class Fase1 extends Transacciones {
	private String codfas = "";
	private String codalt = "";
	private String codcla = "";
	private String txtcla = "";
	private String codmet = "";
	private String txtmet = "";
	//
	private String cod_fas = "00";
	private String cod_alt = "";
	private String cod_cla = "";
	private String txt_cla = "";
	private String cod_met = "";
	private String txt_met = "";
	private String cod_fas_alt = "";
	//DPS A1
	private String des_met = "";
	//
	private String alt = "0";
	private String _alt = "0";
	//
	// Argumentos de la Alternativa de la Fase
	private String numseq = "0";
	private String txtarg = "";
	private String num_seq = "";
	private String txt_arg = "";
	private java.util.Vector gridArgs = null;
	// Variables auxiliares
	public java.util.Vector transacciones = null;
	public java.util.Vector clases = null;
	public java.util.Vector metodos = null;
	//
	private String function = "ADD_FASE";
/**
 * Fase constructor comment.
 */
public Fase1() {
	super();
	transacciones = new java.util.Vector(1);
	clases = new java.util.Vector(1);
	metodos = new java.util.Vector(1);
}
public void appendAlternativa(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		codalt = (getAlt().equals("0")) ? "00" : nextCodalt(db, getCodfas());
		codcla = (getAlt().equals("0")) ? "---" : getCodcla();
		codmet = (getAlt().equals("0")) ? "---" : getCodmet();
		insert(db);
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... appendAlternativa() - " + getClass().getName() + " - > " + e.getMessage());
	}
}
public void appendFase(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		codfas = nextCodfas(db);
		codalt = (getAlt().equals("0")) ? "00" : "01";
		codcla = (getAlt().equals("0")) ? "---" : getCodcla();
		codmet = (getAlt().equals("0")) ? "---" : getCodmet();
		insert(db);
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... appendFase() - " + getClass().getName() + " - > " + e.getMessage());
	}
}
public void consult() throws java.sql.SQLException {
	try {
		String bef[] = {"", "", "", "", ""};
		String aft[] = {"", "", "", "", ""};
		LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", Constante.ESQUEMA1+".TTRAFAS", Table.getColumnsTtrafas(), bef, aft);
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... consult() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public void delete(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		String query = //
		"Delete from "+Constante.ESQUEMA1+".TMETARGALT Where codtra = '" + getCodtra() + "' and codfas = '" + getCodfas() + "' and codalt = '" + getCodalt() + "'";
		db.setQuery(query);
		db.executeUpdate();
		query = //
		"Delete from "+Constante.ESQUEMA1+".TMETARG Where codtra = '" + getCodtra() + "' and codfas = '" + getCodfas() + "' and codalt = '" + getCodalt() + "'";
		db.setQuery(query);
		db.executeUpdate();
		query = //
		"Delete from "+Constante.ESQUEMA1+".TSCHTRA Where codtra = '" + getCodtra() + "' and codfas = '" + getCodfas() + "' and codalt = '" + getCodalt() + "'";
		db.setQuery(query);
		db.executeUpdate();
		query = //
		"Delete from "+Constante.ESQUEMA1+".TTRAFAS Where codtra = '" + getCodtra() + "' and codfas = '" + getCodfas() + "' and codalt = '" + getCodalt() + "'";
		db.setQuery(query);
		int reg = db.executeUpdate();
		if (reg > 0) {
			String bef[] = {getCodtra(), getCodfas(), getCodalt(), getCodcla(), getCodmet()};
			String aft[] = {"", "", "", "", ""};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TTRAFAS", Table.getColumnsTtrafas(), bef, aft);
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
		while (vector != null && vector.hasMoreElements()) {
			cod_fas_alt = vector.nextElement().toString();
			String sw = req.getParameter(cod_fas_alt).toUpperCase();
			if (sw.equals("ON")) {
				java.util.StringTokenizer str = new java.util.StringTokenizer(cod_fas_alt, "_");
				codfas = str.nextElement().toString();
				codalt = str.nextElement().toString();
				delete(db);
				setGrid(null);
			}
		}
		if (getGrid().size() == 0) {
			sort(db);
		}
		loadGrid(db);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public static void deleteArgsEsquema(JQuery db, String codtra, String codfas, String codalt, String numseq) throws java.sql.SQLException {
	try {
		String query = //
		"Delete from "+Constante.ESQUEMA1+".TMETARG Where codtra = '" + codtra + "' and codfas = '" + codfas + "' and codalt = '" + codalt + "' and numseq = " + numseq;
		db.setQuery(query);
		db.executeUpdate();
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... deleteArgsEsquema(" + codtra + ",  " + codfas + ", " + codalt + ", " + numseq + ") -> " + e.getMessage());
	}
}
public static void deleteArgsFase(JQuery db, String codtra, String codfas, String codalt) throws java.sql.SQLException {
	try {
		String query = //
		"Delete from "+Constante.ESQUEMA1+".TMETARGALT Where codtra = '" + codtra + "' and codfas = '" + codfas + "' and codalt = '" + codalt + "'";
		db.setQuery(query);
		db.executeUpdate();
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... deleteArgsEsquema(" + codtra + ", " + codfas + ", " + codalt + ") -> " + e.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 * @exception java.lang.Exception The exception description.
 */
public static void deleteClaseFase(JQuery db, String codcla) throws Exception {
	try {
		String query = "Select codtra, codfas, codalt from "+Constante.ESQUEMA1+".TTRAFAS where codclaalt = '" + codcla + "'";
		db.setQuery(query);
		db.executeQuery();
		java.util.Vector rows = new java.util.Vector(30, 30);
		while (db.getResultSet().next()) {
			java.util.Vector cols = new java.util.Vector(4);
			cols.addElement(db.stringValue(1));
			cols.addElement(db.stringValue(2));
			cols.addElement(db.stringValue(3));
			rows.addElement(cols);
		}
		for (int i = 0; i < rows.size(); i++) {
			String codtra = ((java.util.Vector) rows.elementAt(i)).elementAt(0).toString();
			String codfas = ((java.util.Vector) rows.elementAt(i)).elementAt(1).toString();
			String codalt = ((java.util.Vector) rows.elementAt(i)).elementAt(2).toString();
			deleteFase(db, codtra, codfas, codalt);
			sortFase(db, codtra);
		}
	}
	catch (Exception e) {
		throw new Exception(" \n... deleteClaseFase(" + db.toString() + ", " + codcla + ") - Fase -> " + e.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 * @exception java.lang.Exception The exception description.
 */
public static void deleteClaseFase(JQuery db, String codcla, String codmet) throws Exception {
	try {
		String query = "Select codtra, codfas, codalt " + //
		"from "+Constante.ESQUEMA1+".TTRAFAS " + //
		"where codclaalt = '" + codcla + "' " + //
		"And codmetalt = '" + codmet + "' ";
		db.setQuery(query);
		db.executeQuery();
		java.util.Vector rows = new java.util.Vector(30, 30);
		while (db.getResultSet().next()) {
			java.util.Vector cols = new java.util.Vector(4);
			cols.addElement(db.stringValue(1));
			cols.addElement(db.stringValue(2));
			cols.addElement(db.stringValue(3));
			rows.addElement(cols);
		}
		for (int i = 0; i < rows.size(); i++) {
			String codtra = ((java.util.Vector) rows.elementAt(i)).elementAt(0).toString();
			String codfas = ((java.util.Vector) rows.elementAt(i)).elementAt(1).toString();
			String codalt = ((java.util.Vector) rows.elementAt(i)).elementAt(2).toString();
			deleteFase(db, codtra, codfas, codalt);
			sortFase(db, codtra, codfas);
		}
	}
	catch (Exception e) {
		throw new Exception(" \n... deleteClaseFase(" + db.toString() + ", " + codcla + ", " + codmet + ") - Fase -> " + e.getMessage());
	}
}
public static void deleteEsquema(JQuery db, String codtra, String codfas) throws java.sql.SQLException {
	try {
		String query = //
		"Delete from "+Constante.ESQUEMA1+".TMETARG Where codtra = '" + codtra + "' and codfas = '" + codfas + "'";
		db.setQuery(query);
		db.executeUpdate();
		query = //
		"Delete from "+Constante.ESQUEMA1+".TSCHTRA Where codtra = '" + codtra + "' and codfas = '" + codfas + "'";
		db.setQuery(query);
		db.executeUpdate();
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... deleteEsquema(" + codtra + ", " + codfas + ") -> " + e.getMessage());
	}
}
public static void deleteEsquema(JQuery db, String codtra, String codfas, String codalt) throws java.sql.SQLException {
	try {
		String query = //
		"Delete from "+Constante.ESQUEMA1+".TMETARG Where codtra = '" + codtra + "' and codfas = '" + codfas + "' and codalt = '" + codalt + "'";
		db.setQuery(query);
		db.executeUpdate();
		query = //
		"Delete from "+Constante.ESQUEMA1+".TSCHTRA Where codtra = '" + codtra + "' and codfas = '" + codfas + "' and codalt = '" + codalt + "'";
		db.setQuery(query);
		db.executeUpdate();
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... deleteEsquema(" + codtra + ", " + codfas + ", " + codalt + ") -> " + e.getMessage());
	}
}
public static void deleteEsquema(JQuery db, String codtra, String codfas, String codalt, String numseq) throws java.sql.SQLException {
	try {
		String query = //
		"Delete from "+Constante.ESQUEMA1+".TMETARG Where codtra = '" + codtra + "' and codfas = '" + codfas + "' and codalt = '" + codalt + "' and numseq = " + numseq.trim() + "";
		db.setQuery(query);
		db.executeUpdate();
		query = //
		"Delete from "+Constante.ESQUEMA1+".TSCHTRA Where codtra = '" + codtra + "' and codfas = '" + codfas + "' and codalt = '" + codalt + "' and numseq = " + numseq.trim() + "";
		db.setQuery(query);
		db.executeUpdate();
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... deleteEsquema(" + codtra + "," + codfas + ", " + codalt + ", " + numseq + ") -> " + e.getMessage());
	}
}
public static void deleteFase(JQuery db, String codtra, String codfas) throws java.sql.SQLException {
	try {
		String query = //
		"Delete from "+Constante.ESQUEMA1+".TMETARGALT Where codtra = '" + codtra + "'and codfas = '" + codfas + "'";
		db.setQuery(query);
		db.executeUpdate();
		deleteEsquema(db, codtra, codfas);
		query = //
		"Delete from "+Constante.ESQUEMA1+".TTRAFAS Where codtra = '" + codtra + "' and codfas = '" + codfas + "'";
		db.setQuery(query);
		db.executeUpdate();
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... deleteFase(" + codtra + ", " + codfas + ") -> " + e.getMessage());
	}
}
public static void deleteFase(JQuery db, String codtra, String codfas, String codalt) throws java.sql.SQLException {
	try {
		String query = //
		"Delete from "+Constante.ESQUEMA1+".TMETARGALT Where codtra = '" + codtra + "' and codfas = '" + codfas + "' and codalt = '" + codalt + "'";
		db.setQuery(query);
		db.executeUpdate();
		deleteEsquema(db, codtra, codfas, codalt);
		query = //
		"Delete from "+Constante.ESQUEMA1+".TTRAFAS Where codtra = '" + codtra + "' and codfas = '" + codfas + "' and codalt = '" + codalt + "'";
		db.setQuery(query);
		db.executeUpdate();
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... deleteFase(" + codtra + ", " + codfas + ", " + codalt + ") -> " + e.getMessage());
	}
}
public void deleteTmetargalt() throws java.sql.SQLException {
	JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		deleteTmetargalt(db);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... deleteTmetargalt() - " + getClass().getName() + " -> " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
public void deleteTmetargalt(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		String query = //
		"Delete from "+Constante.ESQUEMA1+".TMETARGALT " + //
		"Where codtra = '" + getCodtra() + "' " + //
		"And codfas = '" + getCodfas() + "' " + //
		"And codalt = '" + getCodalt() + "' " + //
		"And numseqarg = " + getNumseq();
		db.setQuery(query);
		int reg = db.executeUpdate();
		if (reg > 0) {
			String bef[] = {getCodtra(), getCodfas(), getCodalt(), getNumseq(), getTxtarg()};
			String aft[] = {"", "", "", "", ""};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TMETARGALT", Table.getColumnsTmetargalt(), bef, aft);
		}
		numseq = "";
		txtarg = "";
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... deleteTmetargalt() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public void deleteTmetargalt(JQuery db, javax.servlet.http.HttpServletRequest req) throws java.sql.SQLException {
	try {
		setError("");
		java.util.Enumeration vector = req.getParameterNames();
		while (vector != null && vector.hasMoreElements()) {
			numseq = vector.nextElement().toString();
			String sw = req.getParameter(getNumseq()).toUpperCase();
			if (sw.equals("ON")) {
				deleteTmetargalt(db);
				setGridArgs(null);
			}
		}
		if (getGridArgs().size() == 0) {
			sortArgsFase(db, getCodtra(), getCodfas(), getCodalt());
		}
		loadGridTmetargalt(db);
		numseq = "";
		txtarg = "";
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... deleteTmetargalt() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public void deleteTmetargalt(javax.servlet.http.HttpServletRequest req) throws java.sql.SQLException {
	JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		deleteTmetargalt(db, req);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - " + getClass().getName() + " -> " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String get_alt() {
	return _alt;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getAlt() {
	if (alt == null || alt.equals(""))
		alt = "0";
	alt = alt.trim();
	if (alt.length() > 1)
		alt = "0";
	if (!alt.equals("0") && !alt.equals("1"))
		alt = "0";
	return alt;
}
public static java.util.Vector getArgsEsquema(JQuery db, String codtra, String codfas, String codalt, String numseq) throws java.sql.SQLException {
	try {
		String query = //
		"Select txtarg " + //
		"From "+Constante.ESQUEMA1+".TMETARG " + //
		"Where codtra = '" + codtra + "' " + //
		"And codfas = '" + codfas + "' " + //
		"And codalt = '" + codalt + "' " + //
		"And numseq = " + numseq + " " + //
		"Order by numseqarg";
		db.setQuery(query);
		db.executeQuery();
		java.util.Vector args = new java.util.Vector(10, 10);
		while (db.getResultSet().next()) {
			args.addElement(db.stringValue(1));
		}
		return args;
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... getArgsEsquema(" + db + ", " + codtra + ", " + codfas + ", " + codalt + ", " + numseq + ") - Fase -> " + e.getMessage());
	}
}
public static java.util.Vector getArgsFase(JQuery db, String codtra, String codfas, String codalt) throws java.sql.SQLException {
	try {
		String query = //
		"Select txtarg " + //
		"From "+Constante.ESQUEMA1+".TMETARGALT " + //
		"Where codtra = '" + codtra + "' " + //
		"And codfas = '" + codfas + "' " + //
		"And codalt = '" + codalt + "' " + //
		"Order by numseqarg";
		db.setQuery(query);
		db.executeQuery();
		java.util.Vector args = new java.util.Vector(10, 10);
		while (db.getResultSet().next()) {
			args.addElement(db.stringValue(1));
		}
		return args;
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... getArgsFase(" + db + ", " + codtra + ", " + codfas + ", " + codalt + ") - Fase -> " + e.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_alt() {
	return cod_alt;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_cla() {
	return cod_cla;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_fas() {
	return cod_fas;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_fas_alt() {
	return cod_fas_alt;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCod_met() {
	return cod_met;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodalt() {
	codalt = (codalt == null || codalt.equals("")) ? "00" : codalt;
	codalt = CString.completeToBeginWithNChars(codalt, 2, '0');
	if (codalt.equals("00")) {
		codalt = (getAlt().equals("0")) ? "00" : "01";
	}
	return codalt.trim();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodcla() {
	return codcla;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodfas() {
	try {
		if (codfas == null || codfas.equals(""))
			codfas = "0";
		codfas = codfas.trim();
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(codfas)) {
			codfas = "0";
		}
		int cod = new Integer(codfas).intValue();
		if (cod <= 0) {
			InicioSesion.loadParameters();
			codfas = nextCodfas();
		}
		if (!codfas.equals(""))
			codfas = CString.completeToBeginWithNChars(codfas, 2, '0');
		if (codfas.equals("999"))
			codfas = "";
		return codfas.trim();
	}
	catch (Exception e) {
		e.printStackTrace();
		return "-1";
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getCodmet() {
	return codmet;
}
public static java.util.Vector getEsquema(JQuery db, String codtra, String codfas, String codalt) throws java.sql.SQLException {
	try {
		String query = //
		"Select numseq, codcla, codmet, ordsnd " + //
		"From "+Constante.ESQUEMA1+".TSCHTRA " + //
		"Where codtra = '" + codtra + "' " + //
		"And codfas = '" + codfas + "' " + //
		"And codalt = '" + codalt + "' " + //
		"Order by numseq";
		db.setQuery(query);
		db.executeQuery();
		java.util.Vector args = new java.util.Vector(10, 10);
		JQuery db1 = new JQuery(db.getConnection());
		String numseq = "";
		while (db.getResultSet().next()) {
			java.util.Vector reg = new java.util.Vector(4);
			numseq = db.stringValue(1);
			reg.addElement(db.stringValue(2));
			reg.addElement(db.stringValue(3));
			reg.addElement(db.stringValue(4));
			reg.addElement(getArgsEsquema(db1, codtra, codfas, codalt, numseq));
			args.addElement(reg);
		}
		return args;
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... getEsquema(" + db + ", " + codtra + ", " + codfas + ", " + codalt + ") - Fase -> " + e.getMessage());
	}
}
public static java.util.Vector getFase(JQuery db, String codtra, String codfas) throws java.sql.SQLException {
	try {
		String query = //
		"Select codalt, codclaalt, codmetalt " + //
		"From "+Constante.ESQUEMA1+".TTRAFAS " + //
		"Where codtra = '" + codtra + "' " + //
		"And codfas = '" + codfas + "' " + //
		"Order by codalt";
		db.setQuery(query);
		db.executeQuery();
		java.util.Vector args = new java.util.Vector(10, 10);
		JQuery db1 = new JQuery(db.getConnection());
		String codalt = "";
		while (db.getResultSet().next()) {
			java.util.Vector reg = new java.util.Vector(4);
			codalt = db.stringValue(1);
			reg.addElement(codalt);
			reg.addElement(db.stringValue(2));
			reg.addElement(db.stringValue(3));
			reg.addElement(getArgsFase(db1, codtra, codfas, codalt));
			reg.addElement(getEsquema(db1, codtra, codfas, codalt));
			args.addElement(reg);
		}
		return args;
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... getFase(" + db + ", " + codtra + ", " + codfas + ") - Fase -> " + e.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getFunction() {
	return function;
}
/**
 * This method was created in VisualAge.
 * @return java.util.Vector
 */
public java.util.Vector getGridArgs() {
	if (gridArgs == null) {
		gridArgs = new java.util.Vector(10);
	}
	return gridArgs;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNameTable() {
	return ""+Constante.ESQUEMA1+".TTRAFAS";
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
public String getNumseq() {
	if (numseq == null || numseq.equals(""))
		numseq = "1";
	return numseq;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxt_arg() {
	return txt_arg;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxt_cla() {
	return CString.compactLeft(txt_cla, 30);
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxt_met() {
	return CString.compactLeft(txt_met, 30);
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxtarg() {
	return txtarg;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxtcla() {
	return CString.compactLeft(txtcla, 30);
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxtmet() {
	return CString.compactLeft(txtmet, 30);
}
public String getUrl() {
	return JspServlet.FASE_SERVLET + "?BtnEsq=Buscar&TxtCodtra=" + getCodtra() + "&TxtCod_fas_alt=";
}
public String getUrlArgs() {
	return JspServlet.FASE_TRX4_SERVLET + "?BtnEsq=Argumentos&TxtCodcla=" + getCod_cla() + "&TxtCodmet=" + getCod_met() + "&TxtCodfas=" + getCod_fas() + "&TxtCodalt=" + getCod_alt() + "&TxtCod_fas_alt=" + getCod_fas_alt() + "&TxtTxtcla=" + CString.toHtmlString(getTxt_cla()) + "&TxtTxtmet=" + CString.toHtmlString(getTxt_met());
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getUrlCla() {
	return JspServlet.FASE_TRX3_SERVLET + "?BtnEsq=Clase&TxtCodcla=" + getCod_cla() + "&TxtTxtcla=" + CString.toHtmlString(getTxt_cla());
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getUrlEsq() {
	return JspServlet.FASE_SERVLET + "?BtnEsq=Esquema&TxtCodtra=" + getCod_tra() + "&TxtTxttra=" + CString.toHtmlString(getTxt_tra());
}
public String getUrlEsquema() {
	return JspServlet.ESQUEMA_SERVLET + "?BtnEsq=Esquema&TxtCodfas=" + getCod_fas() + "&TxtCodalt=" + getCod_alt() + "&TxtAlt=" + get_alt();
}
public String getUrlFase() {
	return JspServlet.FASE_SERVLET + "?BtnEsq=Fase&TxtCodfas=" + getCod_fas() + "&TxtAlt=" + get_alt();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getUrlMet() {
	return JspServlet.FASE_TRX4_SERVLET + "?BtnEsq=Metodo&TxtCodmet=" + getCod_met() + "&TxtTxtmet=" + CString.toHtmlString(getTxt_met());
}
public String getUrlTmetargalt() {
	return JspServlet.FASE_TRX4_SERVLET + "?BtnEsq=Buscar&TxtNumseqarg=" + getNum_seq();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getUrlTrx() {
	return JspServlet.FASE_TRX1_1_SERVLET + "?BtnEsq=Transaccion&TxtCodtra=" + getCod_tra() + "&TxtTxttra=" + CString.toHtmlString(getTxt_tra());
}
/**
 * This method was created in VisualAge.
 * @exception java.lang.Exception The exception description.
 */
public static boolean hasTrxEsquema(String codtra) throws java.sql.SQLException {
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		String query = "Select codfas " + //
		"From "+Constante.ESQUEMA1+".TTRAFAS " + //
		"Where codtra = '" + codtra + "' ";
		db.setQuery(query);
		db.executeQuery();
		boolean sw = db.getResultSet().next();
		return sw;
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... hasTrxEsquema(" + codtra + ") - Fase -> " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
public void insert() throws java.sql.SQLException {
	JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		if (getFunction().equalsIgnoreCase("ADD_FASE")) {
			appendFase(db);
			return;
		}
		if (getFunction().equalsIgnoreCase("INSERT_FASE")) {
			insertFase(db);
			return;
		}
		if (getFunction().equalsIgnoreCase("ADD_ALTERNATIVA")) {
			appendAlternativa(db);
			return;
		}
		if (getFunction().equalsIgnoreCase("INSERT_ALTERNATIVA")) {
			insertAlternativa(db);
			return;
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... insert() - " + getClass().getName() + " - > " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
public void insert(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		String query = //
		"Insert into "+Constante.ESQUEMA1+".TTRAFAS (codtra, codfas, codalt, codclaalt, codmetalt) values (" + // 
		"'" + getCodtra() + "','" + getCodfas() + "', '" + getCodalt() + "', " + // 
		"'" + getCodcla() + "', '" + getCodmet() + "')";
		db.setQuery(query);
		db.executeUpdate();
		String bef[] = {"", "", "", "", ""};
		String aft[] = {getCodtra(), getCodfas(), getCodalt(), getCodcla(), getCodmet()};
		LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TTRAFAS", Table.getColumnsTtrafas(), bef, aft);
		setGrid(null);
		loadGrid(db);
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... insert(" + getCodtra() + ", " + getCodfas() + ", " + getCodalt() + ", " + getCodcla() + ", " + getCodmet() + ") - " + getClass().getName() + " - > " + e.getMessage());
	}
}
public void insertAlternativa(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		String query = //
		"Select codalt, codclaalt, codmetalt " + //
		"From "+Constante.ESQUEMA1+".TTRAFAS " + //
		"Where codtra = '" + getCodtra() + "' " + //
		"And codfas = '" + getCodfas() + "' " + //
		"Order by codalt";
		db.setQuery(query);
		db.executeQuery();
		java.util.Vector gridAlt = new java.util.Vector(20, 10);
		cod_alt = "00";
		while (db.getResultSet().next()) {
			java.util.Vector reg = new java.util.Vector(3);
			cod_alt = db.stringValue(1);
			reg.addElement(cod_alt);
			reg.addElement(db.stringValue(2));
			reg.addElement(db.stringValue(3));
			gridAlt.addElement(reg);
		}
		boolean cmp = Integer.valueOf(getCodalt().trim()).intValue() <= Integer.valueOf(cod_alt.trim()).intValue();
		if (cmp) {
			for (int i = gridAlt.size() - 1; i >= 0; i--) {
				String altBD = ((java.util.Vector) gridAlt.elementAt(i)).elementAt(0).toString();
				altBD = CString.completeToBeginWithNChars(altBD, 2, '0');
				String altNew = "" + (new Integer(altBD).intValue() + 1);
				altNew = CString.completeToBeginWithNChars(altNew, 2, '0');
				cmp = Integer.valueOf(altBD.trim()).intValue() >= Integer.valueOf(codalt.trim()).intValue();
				if (cmp) {
					String cod_cla = ((java.util.Vector) gridAlt.elementAt(i)).elementAt(1).toString();
					String cod_met = ((java.util.Vector) gridAlt.elementAt(i)).elementAt(2).toString();
					java.util.Vector argsFase = getArgsFase(db, getCodtra(), getCodfas(), altBD);
					java.util.Vector esquema = getEsquema(db, getCodtra(), getCodfas(), altBD);
					deleteFase(db, getCodtra(), getCodfas(), altBD);
					insertFase(db, getCodtra(), getCodfas(), altNew, cod_cla, cod_met, argsFase);
					insertEsquema(db, getCodtra(), getCodfas(), altNew, esquema);
				}
				else {
					break;
				}
			}
		}
		else {
			codalt = CString.completeToBeginWithNChars("" + (new Integer(cod_alt).intValue() + 1), 2, '0');
		}
		codalt = (getAlt().equals("0")) ? "00" : codalt;
		codcla = (getAlt().equals("0")) ? "---" : getCodcla();
		codmet = (getAlt().equals("0")) ? "---" : getCodmet();
		insert(db);
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... insertAlternativa(" + getCodtra() + ", " + getCodfas() + ", " + getCodalt() + ", " + getCodcla() + ", " + getCodmet() + ") - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public static void insertArgsEsquema(JQuery db, String codtra, String codfas, String codalt, String numseq, java.util.Vector args) throws java.sql.SQLException {
	try {
		String query = "";
		for (int i = 0; i < args.size(); i++) {
			query = //
			"Insert into "+Constante.ESQUEMA1+".TMETARG (codtra, codfas, codalt, numseq, numseqarg, txtarg) values (" + // 
			"'" + codtra + "', '" + codfas + "', '" + codalt + "', " + //
			"" + numseq.trim() + ", " + //
			"" + (i + 1) + ", '" + args.elementAt(i).toString().trim() + "')";
			db.setQuery(query);
			db.executeUpdate();
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... insertArgsEsquema(" + codtra + ", " + codfas + ", " + codalt + ", " + numseq + ", " + args.toString() + ") -> " + e.getMessage());
	}
}
public static void insertArgsFase(JQuery db, String codtra, String codfas, String codalt, java.util.Vector args) throws java.sql.SQLException {
	try {
		String query = "";
		for (int i = 0; i < args.size(); i++) {
			query = //
			"Insert into "+Constante.ESQUEMA1+".TMETARGALT (codtra, codfas, codalt, numseqarg, txtarg) values (" + // 
			"'" + codtra + "', '" + codfas + "', '" + codalt + "', " + // 
			"" + (i + 1) + ", '" + args.elementAt(i).toString().trim() + "')";
			db.setQuery(query);
			db.executeUpdate();
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... insertArgsFase(" + codtra + ", " + codfas + ", " + codalt + ", " + args.toString() + ") -> " + e.getMessage());
	}
}
public static void insertEsquema(JQuery db, String codtra, String codfas, String codalt, String numseq, String codcla, String codmet, String ordsnd) throws java.sql.SQLException {
	try {
		String query = //
		"Insert into "+Constante.ESQUEMA1+".TSCHTRA (codtra, codfas, codalt, numseq, codcla, codmet, ordsnd) values (" + // 
		"'" + codtra + "', '" + codfas + "', '" + codalt + "', " + //
		"" + numseq + ", " + //
		"'" + codcla + "', '" + codmet + "', " + //
		"'" + ordsnd + "')";
		db.setQuery(query);
		db.executeUpdate();
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n...insertEsquema(" + codtra + ", " + codfas + ", " + codalt + ", " + numseq + ", " + codcla + ", " + codmet + ", " + ordsnd + ") -> " + e.getMessage());
	}
}
public static void insertEsquema(JQuery db, String codtra, String codfas, String codalt, String numseq, String codcla, String codmet, String ordsnd, java.util.Vector args) throws java.sql.SQLException {
	try {
		insertEsquema(db, codtra, codfas, codalt, numseq, codcla, codmet, ordsnd);
		for (int i = 0; i < args.size(); i++) {
			String query = //
			"Insert into "+Constante.ESQUEMA1+".TMETARG (codtra, codfas, codalt, numseq, numseqarg, txtarg) values (" + // 
			"'" + codtra + "', '" + codfas + "', '" + codalt + "', " + //
			"" + numseq.trim() + ", " + //
			"" + (i + 1) + ", '" + args.elementAt(i).toString().trim() + "')";
			db.setQuery(query);
			db.executeUpdate();
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n...insertEsquema(" + codtra + ", " + codfas + ", " + codalt + ", " + numseq + ", " + codcla + ", " + codmet + ", " + ordsnd + ", " + args.toString() + ") -> " + e.getMessage());
	}
}
public static void insertEsquema(JQuery db, String codtra, String codfas, String codalt, java.util.Vector esq) throws java.sql.SQLException {
	try {
		for (int i = 0; i < esq.size(); i++) {
			String numseq = "" + i;
			String codcla = ((java.util.Vector) esq.elementAt(i)).elementAt(0).toString();
			String codmet = ((java.util.Vector) esq.elementAt(i)).elementAt(1).toString();
			String ordsnd = ((java.util.Vector) esq.elementAt(i)).elementAt(2).toString();
			java.util.Vector args = (java.util.Vector) ((java.util.Vector) esq.elementAt(i)).elementAt(5);
			insertEsquema(db, codtra, codfas, codalt, numseq, codcla, codmet, ordsnd, args);
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n...insertEsquema(" + codtra + ",  " + codfas + ", " + codalt + ", " + esq.toString() + ") -> " + e.getMessage());
	}
}
public void insertFase(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		String query = //
		"Select distinct codfas " + //
		"From "+Constante.ESQUEMA1+".TTRAFAS " + //
		"Where codtra = '" + getCodtra() + "' " + //
		"Order by codfas";
		db.setQuery(query);
		db.executeQuery();
		java.util.Vector gridFase = new java.util.Vector(20, 10);
		cod_fas = "00";
		while (db.getResultSet().next()) {
			cod_fas = db.stringValue(1);
			gridFase.addElement(cod_fas);
		}
		boolean cmp = Integer.valueOf(getCodfas().trim()).intValue() <= Integer.valueOf(cod_fas.trim()).intValue();
		if (cmp) {
			for (int i = gridFase.size() - 1; i >= 0; i--) {
				String faseBD = gridFase.elementAt(i).toString();
				faseBD = CString.completeToBeginWithNChars(faseBD, 2, '0');
				String faseNew = "" + (new Integer(faseBD).intValue() + 1);
				faseNew = CString.completeToBeginWithNChars(faseNew, 2, '0');
				cmp = Integer.valueOf(faseBD.trim()).intValue() >= Integer.valueOf(codfas.trim()).intValue();
				if (cmp) {
					java.util.Vector fase = getFase(db, getCodtra(), faseBD);
					deleteFase(db, getCodtra(), faseBD);
					for (int j = 0; j < fase.size(); j++) {
						String cod_alt = ((java.util.Vector) fase.elementAt(j)).elementAt(0).toString();
						String cod_cla = ((java.util.Vector) fase.elementAt(j)).elementAt(1).toString();
						String cod_met = ((java.util.Vector) fase.elementAt(j)).elementAt(2).toString();
						java.util.Vector argsFase = (java.util.Vector) ((java.util.Vector) fase.elementAt(j)).elementAt(3);
						java.util.Vector esquema = (java.util.Vector) ((java.util.Vector) fase.elementAt(j)).elementAt(4);
						insertFase(db, getCodtra(), faseNew, cod_alt, cod_cla, cod_met, argsFase);
						insertEsquema(db, getCodtra(), faseNew, cod_alt, esquema);
					}
				}
				else {
					break;
				}
			}
		}
		else {
			codfas = CString.completeToBeginWithNChars("" + (new Integer(cod_fas).intValue() + 1), 2, '0');
		}
		codalt = (getAlt().equals("0")) ? "00" : "01";
		codcla = (getAlt().equals("0")) ? "---" : getCodcla();
		codmet = (getAlt().equals("0")) ? "---" : getCodmet();
		insert(db);
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... insertFase(" + getCodtra() + ", " + getCodfas() + ", " + getCodalt() + ", " + getCodcla() + ", " + getCodmet() + ") - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public static void insertFase(JQuery db, String codtra, String codfas, String codalt, String codcla, String codmet) throws java.sql.SQLException {
	try {
		String query = //
		"Insert into "+Constante.ESQUEMA1+".TTRAFAS (codtra, codfas, codalt, codclaalt, codmetalt) values (" + // 
		"'" + codtra + "','" + codfas + "', '" + codalt + "', " + // 
		"'" + codcla + "', '" + codmet + "')";
		db.setQuery(query);
		db.executeUpdate();
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n...insertFase(" + codtra + ", " + codfas + ", " + codalt + ", " + codcla + ", " + codmet + ") -> " + e.getMessage());
	}
}
public static void insertFase(JQuery db, String codtra, String codfas, String codalt, String codcla, String codmet, java.util.Vector args) throws java.sql.SQLException {
	try {
		String query = //
		"Insert into "+Constante.ESQUEMA1+".TTRAFAS (codtra, codfas, codalt, codclaalt, codmetalt) values (" + // 
		"'" + codtra + "','" + codfas + "', '" + codalt + "', " + // 
		"'" + codcla + "', '" + codmet + "')";
		db.setQuery(query);
		db.executeUpdate();
		for (int i = 0; i < args.size(); i++) {
			query = //
			"Insert into "+Constante.ESQUEMA1+".TMETARGALT (codtra, codfas, codalt, numseqarg, txtarg) values (" + // 
			"'" + codtra + "','" + codfas + "', '" + codalt + "', " + // 
			"" + (i + 1) + ", '" + args.elementAt(i).toString().trim() + "')";
			db.setQuery(query);
			db.executeUpdate();
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n...insertFase(" + codtra + ", " + codfas + ", " + codalt + ", " + codcla + ", " + codmet + ", " + args.toString() + ") -> " + e.getMessage());
	}
}
public void insertTmetargalt() throws java.sql.SQLException {
	JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		insertTmetargalt(db);
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... insertTmetargalt() - " + getClass().getName() + " - > " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
public void insertTmetargalt(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		String query = //
		"Select count(numseqarg) from "+Constante.ESQUEMA1+".TMETARGALT " + //
		"Where codtra = '" + getCodtra() + "' " + //
		"And codfas = '" + getCodfas() + "' " + //
		"And codalt = '" + getCodalt() + "' ";
		db.setQuery(query);
		db.executeQuery();
		if (db.getResultSet().next()) {
			numseq = "" + (new Integer(db.stringValue(1)).intValue() + 1);
		}
		else {
			numseq = "1";
		}
		query = //
		"Insert into "+Constante.ESQUEMA1+".TMETARGALT (codtra, codfas, codalt, numseqarg, txtarg) values (" + // 
		"'" + getCodtra() + "','" + getCodfas() + "', '" + getCodalt() + "', " + // 
		"" + getNumseq().trim() + ", '" + getTxtarg() + "')";
		db.setQuery(query);
		db.executeUpdate();
		String bef[] = {"", "", "", "", ""};
		String aft[] = {getCodtra(), getCodfas(), getCodalt(), getNumseq(), getTxtarg()};
		LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TMETARGALT", Table.getColumnsTmetargalt(), bef, aft);
		setGridArgs(null);
		loadGridTmetargalt(db);
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... insertTmetargalt() - " + getClass().getName() + " - > " + e.getMessage());
	}
}
public void loadGrid(JQuery db) throws java.sql.SQLException {
	setIndex(0);
	setStatus("0");
	if (getGrid().size() == 0) {
		try {
			String query = //
			"Select codfas, codalt, codclaalt, codmetalt " + //
			"From "+Constante.ESQUEMA1+".TTRAFAS " + //
			"Where codtra = '" + getCodtra() + "' " + //
			"Order by codfas, codalt";
			db.setQuery(query);
			db.executeQuery();
			JQuery db1 = new JQuery(db.getConnection());
			while (db.getResultSet().next()) {
				java.util.Vector row = new java.util.Vector(6);
				row.addElement(db.stringValue(1).trim());
				row.addElement(db.stringValue(2).trim());
				String tmpCodcla = db.stringValue(3).trim();
				String tmpCodmet = db.stringValue(4).trim();
				tmpCodcla = (tmpCodcla.equals("")) ? "---" : tmpCodcla;
				tmpCodmet = (tmpCodmet.equals("")) ? "---" : tmpCodmet;
				row.addElement(tmpCodcla);
				if (tmpCodcla.equals("---"))
					row.addElement("---");
				else
					row.addElement(Clases.searchClase(db1, tmpCodcla));
				row.addElement(tmpCodmet);
				if (tmpCodmet.equals("---"))
					row.addElement("---");
				else
					row.addElement(Metodos.searchMetodo(db1, tmpCodcla, tmpCodmet));
				getGrid().addElement(row);
			}
		}
		catch (Exception e) {
			throw new java.sql.SQLException("\n... loadGrid() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
}
public void loadGridChild(JQuery db) throws java.sql.SQLException {
}
public void loadGridClases() throws Exception {
	JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
	JQuery db = new JQuery(jd.getConnection());
	clases = new java.util.Vector(20, 10);
	try {
		String query = //
		"Select codcla, txtcla " + //
		"From "+Constante.ESQUEMA1+".TCLADAT " + //
		"Order by codcla";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector(3);
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2).trim());
			clases.addElement(row);
		}
	}
	catch (Exception e) {
		throw new Exception("\n... loadGridClases() - " + getClass().getName() + " -> " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
public void loadGridMetodos() throws Exception {
	JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
	JQuery db = new JQuery(jd.getConnection());
	metodos = new java.util.Vector(20, 10);
	try {
		String query = //
		//DPS "Select codmet, txtmet " + //
		//DPS A1
		"Select codmet, txtmet, txtdes " + //			
		"From "+Constante.ESQUEMA1+".TMETDAT " + //
		"Where codcla = '" + getCodcla() + "' " + //
		"Order by codcla";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector(3);
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2).trim());
			//DPS A1
			row.addElement(db.stringValue(3).trim()); //Descripcion del metodo
			metodos.addElement(row);
		}
	}
	catch (Exception e) {
		throw new Exception("\n... loadGridMetodos() - " + getClass().getName() + " -> " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
public void loadGridTmetargalt() throws java.sql.SQLException {
	JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		setGridArgs(null);
		loadGridTmetargalt(db);
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... loadargsTmetargalt()  - " + getClass().getName() + " - " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
private void loadGridTmetargalt(JQuery db) throws java.sql.SQLException {
	if (getGridArgs().size() == 0) {
		try {
			String query = //
			"Select numseqarg, txtarg " + //
			"From "+Constante.ESQUEMA1+".TMETARGALT " + //
			"Where codtra = '" + getCodtra() + "' " + //
			"And codfas = '" + getCodfas() + "' " + //
			"And codalt = '" + getCodalt() + "' " + //
			"Order by numseqarg";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector row = new java.util.Vector(2);
				row.addElement(db.stringValue(1));
				row.addElement(db.stringValue(2));
				getGridArgs().addElement(row);
			}
		}
		catch (Exception e) {
			throw new java.sql.SQLException(" \n... loadargsTmetargalt()  - " + getClass().getName() + " - " + e.getMessage());
		}
	}
}
public void loadGridTransaccion() throws Exception {
	JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
	JQuery db = new JQuery(jd.getConnection());
	transacciones = new java.util.Vector(20, 10);
	try {
		String query = //
		"Select a.codtra, a.txttra " + //
		"From "+Constante.ESQUEMA1+".TTRADAT a "+
		"Order by a.codtra";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector(4);
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2).trim());
			transacciones.addElement(row);
		}
	}
	catch (Exception e) {
		throw new Exception("\n... loadGridTransaccion() - " + getClass().getName() + " -> " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
public void next(int i) {
	cod_fas = ((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString();
	cod_alt = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
	cod_cla = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
	cod_met = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
	cod_cla = (cod_cla == null || cod_cla.equals("")) ? "---" : ((getCod_alt().equals("00")) ? "---" : cod_cla);
	cod_met = (cod_met == null || cod_met.equals("")) ? "---" : ((getCod_alt().equals("00")) ? "---" : cod_met);
	txt_cla = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
	txt_met = ((java.util.Vector) getGrid().elementAt(i)).elementAt(5).toString();
	cod_fas_alt = cod_fas + "_" + cod_alt;
	_alt = (cod_alt.equals("00")) ? "0" : "1";
}
public void nextChild(int i) {
}
public void nextClases(int i) {
	if (clases == null || clases.size() == 0) {
		try {
			loadGridClases();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	setCod_cla(((java.util.Vector) clases.elementAt(i)).elementAt(0).toString());
	setTxt_cla(((java.util.Vector) clases.elementAt(i)).elementAt(1).toString());
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @exception java.sql.SQLException The exception description.
 */
public String nextCodalt(JQuery db, String tmpCodfas) throws java.sql.SQLException {
	try {
		String tmpCodalt = "0";
		String query = //
		"Select Distinct codalt " + //
		"From "+Constante.ESQUEMA1+".TTRAFAS " + //
		"Where codtra = '" + getCodtra() + "' " + //
		"And codfas = '" + tmpCodfas + "' " + //
		"Order by codalt ";
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			tmpCodalt = db.stringValue(1).trim();
		}
		int cod = new Integer(tmpCodalt).intValue();
		tmpCodalt = (cod >= 99) ? "99" : "" + (cod + 1);
		tmpCodalt = CString.completeToBeginWithNChars(tmpCodalt, 2, '0');
		return tmpCodalt;
	}
	catch (Exception ex) {
		throw new java.sql.SQLException("\n... nextCodalt(" + tmpCodfas + ") - " + getClass().getName() + " -> " + ex.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @exception java.sql.SQLException The exception description.
 */
public String nextCodalt(String tmpCodfas) throws java.sql.SQLException {
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		return nextCodalt(db, tmpCodfas);
	}
	catch (Exception ex) {
		throw new java.sql.SQLException("\n... nextCodalt(" + tmpCodfas + ") - " + getClass().getName() + " -> " + ex.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @exception java.sql.SQLException The exception description.
 */
public String nextCodfas() throws java.sql.SQLException {
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		return nextCodfas(db);
	}
	catch (Exception ex) {
		throw new java.sql.SQLException("\n... nextCodfas() - " + getClass().getName() + " -> " + ex.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @exception java.sql.SQLException The exception description.
 */
public String nextCodfas(JQuery db) throws java.sql.SQLException {
	try {
		String tmpCodfas = "0";
		String query = //
		"Select Distinct codfas " + //
		"From "+Constante.ESQUEMA1+".TTRAFAS " + //
		"Where codtra = '" + getCodtra() + "' " + //
		"Order by codfas DESC";
		db.setQuery(query);
		db.executeQuery();
		if (db.getResultSet().next()) {
			tmpCodfas = db.stringValue(1).trim();
		}
		int cod = new Integer(tmpCodfas).intValue();
		tmpCodfas = (cod >= 99) ? "99" : "" + (cod + 1);
		tmpCodfas = CString.completeToBeginWithNChars(tmpCodfas, 2, '0');
		return tmpCodfas;
	}
	catch (Exception ex) {
		throw new java.sql.SQLException("\n... nextCodfas() - " + getClass().getName() + " -> " + ex.getMessage());
	}
}
public void nextMetodos(int i) {
	if (metodos == null || metodos.size() == 0) {
		try {
			loadGridMetodos();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	setCod_met(((java.util.Vector) metodos.elementAt(i)).elementAt(0).toString());
	setTxt_met(((java.util.Vector) metodos.elementAt(i)).elementAt(1).toString());
	//DPS A1
	setDes_met(((java.util.Vector) metodos.elementAt(i)).elementAt(2).toString());
}
public void nextTmetargalt(int i) {
	num_seq = ((java.util.Vector) getGridArgs().elementAt(i)).elementAt(0).toString();
	txt_arg = ((java.util.Vector) getGridArgs().elementAt(i)).elementAt(1).toString();
}
public void nextTransaccion(int i) {
	setCod_tra(((java.util.Vector) transacciones.elementAt(i)).elementAt(0).toString());
	setTxt_tra(((java.util.Vector) transacciones.elementAt(i)).elementAt(1).toString());
}
public boolean search(JQuery db) throws java.sql.SQLException {
	try {
		String query = //
		"Select codclaalt, codmetalt " + //
		"From "+Constante.ESQUEMA1+".TTRAFAS " + //
		"Where codtra = '" + getCodtra() + "' " + //
		"And codfas = '" + getCodfas() + "' " + //
		"And codalt = '" + getCodalt() + "' ";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		codcla = db.stringValue(1).trim();
		codmet = db.stringValue(2).trim();
		codcla = (codcla == null || codcla.equals("") || codcla.equals("000")) ? "---" : codcla;
		codmet = (codmet == null || codmet.equals("") || codmet.equals("000")) ? "---" : codmet;
		txtcla = "---";
		txtmet = "---";
		if (!codcla.equals("---")) {
			txtcla = Clases.searchClase(db, codcla);
		}
		if (!codmet.equals("---") && !codcla.equals("---")) {
			txtmet = Metodos.searchMetodo(db, codcla, codmet);
		}
		return true;
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... search() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
public boolean searchTmetargalt() throws java.sql.SQLException {
	JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		return searchTmetargalt(db);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... searchTmetargalt() - " + getClass().getName() + " -> " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
public boolean searchTmetargalt(JQuery db) throws java.sql.SQLException {
	try {
		String query = //
		"Select numseqarg, txtarg " + //
		"From "+Constante.ESQUEMA1+".TMETARGALT " + //
		"Where codtra = '" + getCodtra() + "' " + //
		"And codfas = '" + getCodfas() + "' " + //
		"And codalt = '" + getCodalt() + "' " + //
		"And numseqarg = " + getNumseq();
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		numseq = db.stringValue(1);
		txtarg = db.stringValue(2);
		return true;
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... searchTmetargalt() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void set_alt(String newValue) {
	this._alt = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setAlt(String newValue) {
	this.alt = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_alt(String newValue) {
	this.cod_alt = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_cla(String newValue) {
	this.cod_cla = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_fas(String newValue) {
	this.cod_fas = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_fas_alt(String newValue) {
	this.cod_fas_alt = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCod_met(String newValue) {
	this.cod_met = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodalt(String newValue) {
	this.codalt = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodcla(String newValue) {
	this.codcla = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodfas(String newValue) {
	this.codfas = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setCodmet(String newValue) {
	this.codmet = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setFunction(String newValue) {
	this.function = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.util.Vector
 */
public void setGridArgs(java.util.Vector newValue) {
	this.gridArgs = newValue;
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
public void setNumseq(String newValue) {
	this.numseq = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxt_arg(String newValue) {
	this.txt_arg = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxt_cla(String newValue) {
	this.txt_cla = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxt_met(String newValue) {
	this.txt_met = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxtarg(String newValue) {
	this.txtarg = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxtcla(String newValue) {
	this.txtcla = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxtmet(String newValue) {
	this.txtmet = newValue;
}
public void sort(JQuery db) throws java.sql.SQLException {
	try {
		sortFase(db, getCodtra());
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... sort() - " + getClass().getName() + " - " + e.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 * @exception java.lang.Exception The exception description.
 */
public static void sortArgsEsquema(JQuery db, String codtra, String codfas, String codalt, String numseq) throws java.sql.SQLException {
	try {
		java.util.Vector args = getArgsEsquema(db, codtra, codfas, codalt, numseq);
		deleteArgsEsquema(db, codtra, codfas, codalt, numseq);
		insertArgsEsquema(db, codtra, codfas, codalt, numseq, args);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... sortArgsEsquema(" + codtra + ", " + codfas + ", " + codalt + ", " + numseq + ") - Fase -> " + e.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 * @exception java.lang.Exception The exception description.
 */
public static void sortArgsFase(JQuery db, String codtra, String codfas, String codalt) throws java.sql.SQLException {
	try {
		java.util.Vector args = getArgsFase(db, codtra, codfas, codalt);
		deleteArgsFase(db, codtra, codfas, codalt);
		insertArgsFase(db, codtra, codfas, codalt, args);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... sortArgsFase(" + codtra + ", " + codfas + ", " + codalt + ") - Fase -> " + e.getMessage());
	}
}
public static void sortEsquema(JQuery db, String codtra, String codfas, String codalt) throws java.sql.SQLException {
	try {
		String query = //
		"Select numseq, codcla, codmet, ordsnd " + //
		"From "+Constante.ESQUEMA1+".TSCHTRA " + //
		"Where codtra = '" + codtra + "' " + //
		"And codfas = '" + codfas + "' " + //
		"And codalt = '" + codalt + "' " + //
		"Order by numseq";
		db.setQuery(query);
		db.executeQuery();
		java.util.Vector gridSeq = new java.util.Vector(10, 10);
		while (db.getResultSet().next()) {
			java.util.Vector reg = new java.util.Vector(10, 10);
			reg.addElement(db.stringValue(1));
			reg.addElement(db.stringValue(2));
			reg.addElement(db.stringValue(3));
			reg.addElement(db.stringValue(4));
			gridSeq.addElement(reg);
		}
		for (int i = 0; i < gridSeq.size(); i++) {
			String seqBD = ((java.util.Vector) gridSeq.elementAt(i)).elementAt(0).toString();
			seqBD = CString.completeToBeginWithNChars(seqBD, 2, '0');
			String seqNew = "" + (i + 1);
			seqNew = CString.completeToBeginWithNChars(seqNew, 2, '0');
			if (!seqNew.equals(seqBD)) {
				java.util.Vector args = getArgsEsquema(db, codtra, codfas, codalt, seqBD);
				deleteEsquema(db, codtra, codfas, codalt, seqBD);
				String codcla = ((java.util.Vector) gridSeq.elementAt(i)).elementAt(1).toString();
				String codmet = ((java.util.Vector) gridSeq.elementAt(i)).elementAt(2).toString();
				String ordsnd = ((java.util.Vector) gridSeq.elementAt(i)).elementAt(3).toString();
				insertEsquema(db, codtra, codfas, codalt, seqNew, codcla, codmet, ordsnd, args);
			}
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... sortEsquema(" + codtra + ", " + codfas + ", " + codalt + ") - Fase -> " + e.getMessage());
	}
}
public static void sortFase(JQuery db, String codtra) throws java.sql.SQLException {
	try {
		String query = //
		"Select distinct codfas " + //
		"From "+Constante.ESQUEMA1+".TTRAFAS " + //
		"Where codtra = '" + codtra + "' " + //
		"Order by codfas";
		db.setQuery(query);
		db.executeQuery();
		java.util.Vector gridFase = new java.util.Vector(20, 10);
		while (db.getResultSet().next()) {
			gridFase.addElement(db.stringValue(1));
		}
		for (int i = 0; i < gridFase.size(); i++) {
			String faseBD = gridFase.elementAt(i).toString();
			faseBD = CString.completeToBeginWithNChars(faseBD, 2, '0');
			String faseNew = "" + (i + 1);
			faseNew = CString.completeToBeginWithNChars(faseNew, 2, '0');
			sortFase(db, codtra, faseBD);
			if (!faseNew.equals(faseBD)) {
				java.util.Vector fase = getFase(db, codtra, faseBD);
				deleteFase(db, codtra, faseBD);
				for (int j = 0; j < fase.size(); j++) {
					String codalt = ((java.util.Vector) fase.elementAt(j)).elementAt(0).toString();
					String codcla = ((java.util.Vector) fase.elementAt(j)).elementAt(1).toString();
					String codmet = ((java.util.Vector) fase.elementAt(j)).elementAt(2).toString();
					java.util.Vector argsFase = (java.util.Vector) ((java.util.Vector) fase.elementAt(j)).elementAt(3);
					java.util.Vector esquema = (java.util.Vector) ((java.util.Vector) fase.elementAt(j)).elementAt(4);
					insertFase(db, codtra, faseNew, codalt, codcla, codmet, argsFase);
					insertEsquema(db, codtra, faseNew, codalt, esquema);
				}
			}
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... sortFase(" + codtra + ") - Fase -> " + e.getMessage());
	}
}
public static void sortFase(JQuery db, String codtra, String codfas) throws java.sql.SQLException {
	try {
		String query = //
		"Select codalt, codclaalt, codmetalt " + //
		"From "+Constante.ESQUEMA1+".TTRAFAS " + //
		"Where codtra = '" + codtra + "' " + //
		"And codfas = '" + codfas + "' " + //
		"Order by codalt";
		db.setQuery(query);
		db.executeQuery();
		java.util.Vector gridAlt = new java.util.Vector(10, 10);
		String altBD = "";
		while (db.getResultSet().next()) {
			java.util.Vector reg = new java.util.Vector(10, 10);
			reg.addElement(db.stringValue(1));
			reg.addElement(db.stringValue(2));
			reg.addElement(db.stringValue(3));
			gridAlt.addElement(reg);
			altBD = reg.elementAt(0).toString();
		}
		if (!altBD.equals("00")) {
			for (int i = 0; i < gridAlt.size(); i++) {
				altBD = ((java.util.Vector) gridAlt.elementAt(i)).elementAt(0).toString();
				altBD = CString.completeToBeginWithNChars(altBD, 2, '0');
				String altNew = "" + (i + 1);
				altNew = CString.completeToBeginWithNChars(altNew, 2, '0');
				if (!altNew.equals(altBD)) {
					java.util.Vector argsFase = getArgsFase(db, codtra, codfas, altBD);
					java.util.Vector esquema = getEsquema(db, codtra, codfas, altBD);
					deleteFase(db, codtra, codfas, altBD);
					String codclaalt = ((java.util.Vector) gridAlt.elementAt(i)).elementAt(1).toString();
					String codmetalt = ((java.util.Vector) gridAlt.elementAt(i)).elementAt(2).toString();
					insertFase(db, codtra, codfas, altNew, codclaalt, codmetalt, argsFase);
					insertEsquema(db, codtra, codfas, altNew, esquema);
				}
			}
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... sortFase(" + codtra + ", " + codfas + ") - Fase -> " + e.getMessage());
	}
}
public void update(JQuery db) throws java.sql.SQLException {
}
public void updateTmetargalt() throws java.sql.SQLException {
	JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		updateTmetargalt(db);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... updateTmetargalt() - " + getClass().getName() + " -> " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
public void updateTmetargalt(JQuery db) throws java.sql.SQLException {
	try {
		String antTxtarg = getTxtarg();
		if (searchTmetargalt(db)) {
			String query = //
			"Update "+Constante.ESQUEMA1+".TMETARGALT set " + //
			"txtarg = '" + antTxtarg + "' " + //
			"Where codtra = '" + getCodtra() + "' " + //
			"And codfas = '" + getCodfas() + "' " + //
			"And codalt = '" + getCodalt() + "' " + //
			"And numseqarg = " + getNumseq();
			db.setQuery(query);
			db.executeUpdate();
		}
		String bef[] = {getCodtra(), getCodfas(), getCodalt(), getNumseq(), getTxtarg()};
		String aft[] = {getCodtra(), getCodfas(), getCodalt(), getNumseq(), antTxtarg};
		LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Modificar", Constante.ESQUEMA1+".TMETARGALT", Table.getColumnsTmetargalt(), bef, aft);
		setGridArgs(null);
		loadGridTmetargalt(db);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... updateTmetargalt() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
	public String getDes_met() {
		return des_met;
	}
	public void setDes_met(String des_met) {
		this.des_met = des_met;
	}
}