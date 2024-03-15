package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class Esquema1 extends Fase1 {
	private String numseqarg = "0";
	private String num_seq_arg = "0";
	private String ordsnd = "0";
	private String ord_snd = "0";
	/**
	 * Esquema1 constructor comment.
	 */
	public Esquema1() {
		super();
	}
	public void appendMetodo(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
			setNumseq(nextNumseq(db));
			insert(db);
		} catch (Exception e) {
			throw new java.sql.SQLException(" \n... appendMetodo() - " + getClass().getName() + " - > " + e.getMessage());
		}
	}
	public void consult() throws java.sql.SQLException {
		try {
			String bef[] = { "", "", "", "", "", "", ""};
			String aft[] = { "", "", "", "", "", "", ""};
			LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", Constante.ESQUEMA1+".TSCHTRA", Table.getColumnsTschtra(), bef, aft);
		} catch (Exception e) {
			throw new java.sql.SQLException(" \n... consult() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	public void delete(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
			// System.out.println("codtra---"+getCodtra());
			// System.out.println("codfas---"+getCodfas());
			// System.out.println("codalt---"+getCodalt());
			// System.out.println("numseq---"+getNumseq());
				String query = //
	"Delete from "+Constante.ESQUEMA1+".TMETARG Where codtra = '" + getCodtra() + "' and codfas = '" + getCodfas() + "' and codalt = '" + getCodalt() + "' and numseq = " + getNumseq();
			db.setQuery(query);
			db.executeUpdate();
				query = //
	"Delete from "+Constante.ESQUEMA1+".TSCHTRA Where codtra = '" + getCodtra() + "' and codfas = '" + getCodfas() + "' and codalt = '" + getCodalt() + "' and numseq = " + getNumseq();
			db.setQuery(query);
			int reg = db.executeUpdate();
			if (reg > 0) {
				String bef[] = { getCodtra(), getCodfas(), getCodalt(), getNumseq(), getCodcla(), getCodmet()};
				String aft[] = { "", "", "", "", "", "", "" };
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TSCHTRA", Table.getColumnsTschtra(), bef, aft);
			}
			numseqarg = "";
			setTxtarg("");
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... delete() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	public void delete(JQuery db, javax.servlet.http.HttpServletRequest req) throws java.sql.SQLException {
		try {
			setError("");
			// System.out.println("codtra---"+getCodtra());
			// System.out.println("codfas---"+getCodfas());
			// System.out.println("codalt---"+getCodalt());
			// System.out.println("numseq---"+getNumseq());
			java.util.Enumeration vector = req.getParameterNames();
			while (vector != null && vector.hasMoreElements()) {
				setNumseq(vector.nextElement().toString());
				if (getNumseq().length() <= 2) {
					String sw = req.getParameter(getNumseq()).toUpperCase();
					if (sw.equals("ON")) {
						delete(db);
						setGrid(null);
					}
				}
			}
			if (getGrid().size() == 0) {
				sort(db);
			}
			loadGrid(db);
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... delete() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	/**
	 * This method was created in VisualAge.
	 * @exception java.lang.Exception The exception description.
	 */
	public static void deleteClaseEsquema(JQuery db, String codcla) throws Exception {
		try {
			String query = "Select codtra, codfas, codalt, numseq from "+Constante.ESQUEMA1+".TSCHTRA where codcla = '" + codcla + "'";
			db.setQuery(query);
			db.executeQuery();
			java.util.Vector rows = new java.util.Vector(30, 30);
			while (db.getResultSet().next()) {
				java.util.Vector cols = new java.util.Vector(5);
				cols.addElement(db.stringValue(1));
				cols.addElement(db.stringValue(2));
				cols.addElement(db.stringValue(3));
				cols.addElement(db.stringValue(4));
				rows.addElement(cols);
			}
			for (int i = 0; i < rows.size(); i++) {
				String codtra = ((java.util.Vector) rows.elementAt(i)).elementAt(0).toString();
				String codfas = ((java.util.Vector) rows.elementAt(i)).elementAt(1).toString();
				String codalt = ((java.util.Vector) rows.elementAt(i)).elementAt(2).toString();
				String numseq = ((java.util.Vector) rows.elementAt(i)).elementAt(3).toString();
				deleteEsquema(db, codtra, codfas, codalt, numseq);
				sortEsquema(db, codtra, codfas, codalt);
			}
		} catch (Exception e) {
			throw new Exception(" \n... deleteClaseEsquema(" + db.toString() + ", " + codcla + ") -  Esquema -> " + e.getMessage());
		}
	}
	/**
	 * This method was created in VisualAge.
	 * @exception java.lang.Exception The exception description.
	 */
	public static void deleteClaseEsquema(JQuery db, String codcla, String codmet) throws Exception {
		try {
				String query = "Select codtra, codfas, codalt, numseq " + //
		"from "+Constante.ESQUEMA1+".TSCHTRA " + //
		"where codcla = '" + codcla + "' " + //
	"And codmet = '" + codmet + "' ";
			db.setQuery(query);
			db.executeQuery();
			java.util.Vector rows = new java.util.Vector(30, 30);
			while (db.getResultSet().next()) {
				java.util.Vector cols = new java.util.Vector(5);
				cols.addElement(db.stringValue(1));
				cols.addElement(db.stringValue(2));
				cols.addElement(db.stringValue(3));
				cols.addElement(db.stringValue(4));
				rows.addElement(cols);
			}
			for (int i = 0; i < rows.size(); i++) {
				String codtra = ((java.util.Vector) rows.elementAt(i)).elementAt(0).toString();
				String codfas = ((java.util.Vector) rows.elementAt(i)).elementAt(1).toString();
				String codalt = ((java.util.Vector) rows.elementAt(i)).elementAt(2).toString();
				String numseq = ((java.util.Vector) rows.elementAt(i)).elementAt(3).toString();
				deleteEsquema(db, codtra, codfas, codalt, numseq);
				sortEsquema(db, codtra, codfas, codalt);
			}
		} catch (Exception e) {
			throw new Exception(" \n... deleteClaseEsquema(" + db.toString() + ", " + codcla + ", " + codmet + ") - Esquema -> " + e.getMessage());
		}
	}
	public void deleteTmetarg() throws java.sql.SQLException {
		JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
		JQuery db = new JQuery(jd.getConnection());
		try {
			setError("");
			deleteTmetarg(db);
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... deleteTmetarg() - " + getClass().getName() + " -> " + e.getMessage());
		} finally {
			db.close();
			jd.close();
		}
	}
	public void deleteTmetarg(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
			// System.out.println("codtra---"+getCodtra());
			// System.out.println("codfas---"+getCodfas());
			// System.out.println("codalt---"+getCodalt());
			// System.out.println("numseq---"+getNumseq());
				String query = //
		"Delete from "+Constante.ESQUEMA1+".TMETARG " + //
		"Where codtra = '" + getCodtra() + "' " + //
		"And codfas = '" + getCodfas() + "' " + //
		"And codalt = '" + getCodalt() + "' " + //
		"And numseq = " + getNumseq() + " " + //
	"And numseqarg = " + getNumseqarg();
			db.setQuery(query);
			int reg = db.executeUpdate();
			if (reg > 0) {
				String bef[] = { getCodtra(), getCodfas(), getCodalt(),getNumseq(), getNumseqarg(), getTxtarg()};
				String aft[] = { "", "", "", "", "", ""};
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TMETARG", Table.getColumnsTmetarg(), bef, aft);
			}
			setNumseqarg("");
			setTxtarg("");
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... deleteTmetarg() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	public void deleteTmetarg(JQuery db, javax.servlet.http.HttpServletRequest req) throws java.sql.SQLException {
		try {
			setError("");
			java.util.Enumeration vector = req.getParameterNames();
			while (vector != null && vector.hasMoreElements()) {
				numseqarg = vector.nextElement().toString();
				String sw = req.getParameter(getNumseqarg()).toUpperCase();
				if (sw.equals("ON")) {
					deleteTmetarg(db);
					setGridArgs(null);
				}
			}
			if (getGridArgs().size() == 0) {
				sortArgsEsquema(db, getCodtra(), getCodfas(), getCodalt(), getNumseq());
			}
			loadGridTmetarg(db);
			setNumseqarg("");
			setTxtarg("");
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... deleteTmetarg() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	public void deleteTmetarg(javax.servlet.http.HttpServletRequest req) throws java.sql.SQLException {
		JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
		JQuery db = new JQuery(jd.getConnection());
		try {
			deleteTmetarg(db, req);
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... delete() - " + getClass().getName() + " -> " + e.getMessage());
		} finally {
			db.close();
			jd.close();
		}
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getNum_seq_arg() {
		return num_seq_arg;
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getNumseqarg() {
		return numseqarg;
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getOrd_snd() {
		if (CString.isEmptyOrNull(ord_snd))
			ord_snd = "00";
		return ord_snd.trim();
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getOrdsnd() {
		if (CString.isEmptyOrNull(ordsnd))
			ordsnd = "00";
		return CString.completeToBeginWithNChars(ordsnd.trim(), 2, '0');
	}
	public String getUrl() {
		return JspServlet.ESQUEMA_SERVLET + "?BtnEsq=Buscar&TxtCodtra=" + getCodtra()  + "&TxtCod_fas_alt=";
	}
	public String getUrlArgs() {
		return JspServlet.ESQUEMA_TRX4_SERVLET + "?BtnEsq=Argumentos&TxtCodcla=" + getCod_cla() + "&TxtCodmet=" + getCod_met() + "&TxtCodfas=" + getCodfas() + "&TxtCodalt=" + getCodalt() + "&TxtTxtcla=" + CString.toHtmlString(getTxt_cla()) + "&TxtTxtmet=" + CString.toHtmlString(getTxt_met()) + "&TxtNumseq=" + getNum_seq();
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getUrlCla() {
		return JspServlet.ESQUEMA_TRX3_SERVLET + "?BtnEsq=Clase&TxtCodcla=" + getCod_cla() + "&TxtTxtcla=" + CString.toHtmlString(getTxt_cla());
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getUrlMet() {
		//DPS D1
		//return JspServlet.ESQUEMA_TRX4_SERVLET + "?BtnEsq=Metodo&TxtCodmet=" + getCod_met() + "&TxtTxtmet=" + CString.toHtmlString(getTxt_met());
		//DPS A1
		return JspServlet.ESQUEMA_TRX4_SERVLET + "?BtnEsq=Metodo&TxtCodmet=" + getCod_met() + "&TxtTxtmet=" + CString.toHtmlString(getTxt_met()) + "&TxtDesmet=" + CString.toHtmlString(getDes_met());
	}
	public String getUrlTmetarg() {
		return JspServlet.ESQUEMA_TRX4_SERVLET + "?BtnEsq=Buscar&TxtNumseqarg=" + getNum_seq_arg();
	}
	public void insert() throws java.sql.SQLException {
		JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
		JQuery db = new JQuery(jd.getConnection());
		try {
			if (getFunction().equalsIgnoreCase("ADD_METODO")) {
				appendMetodo(db);
				return;
			}
			if (getFunction().equalsIgnoreCase("INSERT_METODO")) {
				insertMetodo(db);
				return;
			}
		} catch (Exception e) {
			throw new java.sql.SQLException(" \n... insert() - " + getClass().getName() + " - > " + e.getMessage());
		} finally {
			db.close();
			jd.close();
		}
	}
	public void insert(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
				String query = //
		"Insert into "+Constante.ESQUEMA1+".TSCHTRA (codtra, codfas, codalt, numseq, codcla, codmet, ordsnd) values (" + // 
		"'" + getCodtra() + "', '" + getCodfas() + "', '" + getCodalt() + "', " + //
		"" + getNumseq() + ", " + //
		"'" + getCodcla() + "', '" + getCodmet() + "', " + //
	"'" + getOrdsnd() + "')";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = { "", "", "", "", "", "", "", };
			String aft[] = { getCodtra(), getCodfas(), getCodalt(),  getNumseq(), getCodcla(), getCodmet(), getOrdsnd()};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TSCHTRA", Table.getColumnsTschtra(), bef, aft);
			setGrid(null);
			loadGrid(db);
		} catch (Exception e) {
			throw new java.sql.SQLException(" \n... insert(" + getCodtra() + ", " + getCodfas() + ", " + getCodalt() + ", " + getNumseq() + ", " + getCodcla() + ", " + getCodmet() + ") - " + getClass().getName() + " - > " + e.getMessage());
		}
	}
	public void insertMetodo(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
				String query = //
		"Select numseq, codcla, codmet, ordsnd " + //
		"From "+Constante.ESQUEMA1+".TSCHTRA " + //
		"Where codtra = '" + getCodtra() + "' " + //
		"And codfas = '" + getCodfas() + "' " + //
		"And codalt = '" + getCodalt() + "' " + //
	"Order by numseq";
			db.setQuery(query);
			db.executeQuery();
			java.util.Vector gridSeq = new java.util.Vector(20, 10);
			setNum_seq("0");
			while (db.getResultSet().next()) {
				java.util.Vector reg = new java.util.Vector(6);
				setNum_seq(db.stringValue(1));
				reg.addElement(getNum_seq());
				reg.addElement(db.stringValue(2));
				reg.addElement(db.stringValue(3));
				reg.addElement(db.stringValue(4));
				reg.addElement(db.stringValue(5));
				gridSeq.addElement(reg);
			}
			boolean cmp = Integer.valueOf(getNumseq().trim()).intValue() > Integer.valueOf(getNum_seq().trim()).intValue();
			if (!cmp) {
				for (int i = gridSeq.size() - 1; i >= 0; i--) {
					String seqBD = ((java.util.Vector) gridSeq.elementAt(i)).elementAt(0).toString();
					String seqNew = "" + (new Integer(seqBD).intValue() + 1);
					cmp = Integer.valueOf(seqBD).intValue() >= Integer.valueOf(getNumseq()).intValue();
					if (cmp) {
						String cod_cla = ((java.util.Vector) gridSeq.elementAt(i)).elementAt(1).toString();
						String cod_met = ((java.util.Vector) gridSeq.elementAt(i)).elementAt(2).toString();
						String ord_snd = ((java.util.Vector) gridSeq.elementAt(i)).elementAt(3).toString();
						java.util.Vector argsEsquema = getArgsEsquema(db, getCodtra(), getCodfas(), getCodalt(), seqBD);
						deleteEsquema(db, getCodtra(), getCodfas(), getCodalt(), seqBD);
						insertEsquema(db, getCodtra(), getCodfas(), getCodalt(), seqNew, cod_cla, cod_met, ord_snd, argsEsquema);
					} else {
						break;
					}
				}
			} else {
				setNumseq("" + (new Integer(getNum_seq()).intValue() + 1));
			}
			insert(db);
		} catch (Exception e) {
			throw new java.sql.SQLException(" \n... insertMetodo(" + getCodtra() + ", " + getCodfas() + ", " + getCodalt() + ", " + getNumseq() + ", " + getCodcla() + ", " + getCodmet() + ") - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	public void insertTmetarg() throws java.sql.SQLException {
		JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
		JQuery db = new JQuery(jd.getConnection());
		try {
			insertTmetarg(db);
		} catch (Exception e) {
			throw new java.sql.SQLException(" \n... insertTmetarg() - " + getClass().getName() + " - > " + e.getMessage());
		} finally {
			db.close();
			jd.close();
		}
	}
	public void insertTmetarg(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
				String query = //
		"Select count(numseqarg) from "+Constante.ESQUEMA1+".TMETARG " + //
		"Where codtra = '" + getCodtra() + "' " + //
		"And codfas = '" + getCodfas() + "' " + //
		"And codalt = '" + getCodalt() + "' " + //
	"And numseq = " + getNumseq() + " ";
			db.setQuery(query);
			db.executeQuery();
			if (db.getResultSet().next()) {
				setNumseqarg("" + (new Integer(db.stringValue(1)).intValue() + 1));
			} else {
				setNumseqarg("1");
			}
				query = //
		"Insert into "+Constante.ESQUEMA1+".TMETARG (codtra, codfas, codalt, numseq, numseqarg, txtarg) values (" + // 
		"'" + getCodtra() + "', '" + getCodfas() + "', '" + getCodalt() + "', " + // 
		"" + getNumseq().trim() + ", " + //
	"" + getNumseqarg() + ", '" + getTxtarg() + "')";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = { "", "", "", "", "", ""};
			String aft[] = { getCodtra(), getCodfas(), getCodalt(), getNumseq(), getNumseqarg(), getTxtarg()};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TMETARG", Table.getColumnsTmetarg(), bef, aft);
			setGridArgs(null);
			loadGridTmetarg(db);
		} catch (Exception e) {
			throw new java.sql.SQLException(" \n... insertTmetarg() - " + getClass().getName() + " - > " + e.getMessage());
		}
	}
	public void loadGrid(JQuery db) throws java.sql.SQLException {
		setIndex(0);
		setStatus("0");
		if (getGrid().size() == 0) {
			try {
					String query = //
		"Select a.numseq, a.codcla, a.codmet, b.txtcla, c.txtmet, a.ordsnd " + //
		"From "+Constante.ESQUEMA1+".TSCHTRA a, "+Constante.ESQUEMA1+".TCLADAT b, "+Constante.ESQUEMA1+".TMETDAT c " + //
		"Where a.codtra = '" + getCodtra() + "' " + //
		"And a.codfas = '" + getCodfas() + "' " + //
		"And a.codalt = '" + getCodalt() + "' " + //
		"And a.codcla = b.codcla and b.codcla = c.codcla " + //
		"And a.codmet = c.codmet " + //
	"Order by a.numseq";
				db.setQuery(query);
				db.executeQuery();
				JQuery db1 = new JQuery(db.getConnection());
				while (db.getResultSet().next()) {
					java.util.Vector row = new java.util.Vector(10);
					row.addElement(db.stringValue(1).trim());
					row.addElement(db.stringValue(2).trim());
					row.addElement(db.stringValue(3).trim());
					row.addElement(db.stringValue(4).trim());
					row.addElement(db.stringValue(5).trim());
					row.addElement(db.stringValue(6).trim());
					getGrid().addElement(row);
				}
			} catch (Exception e) {
				throw new java.sql.SQLException("\n... loadGrid() - " + getClass().getName() + " -> " + e.getMessage());
			}
		}
	}
	public void loadGridTmetarg() throws java.sql.SQLException {
		JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
		JQuery db = new JQuery(jd.getConnection());
		try {
			setGridArgs(null);
			loadGridTmetarg(db);
		} catch (Exception e) {
			throw new java.sql.SQLException(" \n... loadargsTmetarg()  - " + getClass().getName() + " - " + e.getMessage());
		} finally {
			db.close();
			jd.close();
		}
	}
	private void loadGridTmetarg(JQuery db) throws java.sql.SQLException {
		if (getGridArgs().size() == 0) {
			try {
					String query = //
		"Select numseqarg, txtarg " + //
		"From "+Constante.ESQUEMA1+".TMETARG " + //
		"Where codtra = '" + getCodtra() + "' " + //
		"And codfas = '" + getCodfas() + "' " + //
		"And codalt = '" + getCodalt() + "' " + //
		"And numseq = " + getNumseq() + " " + //
	"Order by numseqarg";
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					java.util.Vector row = new java.util.Vector(2);
					row.addElement(db.stringValue(1));
					row.addElement(db.stringValue(2));
					getGridArgs().addElement(row);
				}
			} catch (Exception e) {
				throw new java.sql.SQLException(" \n... loadargsTmetarg()  - " + getClass().getName() + " - " + e.getMessage());
			}
		}
	}
	public void next(int i) {
		setNum_seq(((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString());
		setCod_cla(((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString());
		setCod_met(((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString());
		setTxt_cla(((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString());
		setTxt_met(((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString());
		setOrd_snd(((java.util.Vector) getGrid().elementAt(i)).elementAt(5).toString());
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 * @exception java.sql.SQLException The exception description.
	 */
	public String nextNumseq() throws java.sql.SQLException {
		JDatabase jd = new JDatabase();
		JQuery db = new JQuery(jd.getConnection());
		try {
			return nextNumseq(db);
		} catch (Exception ex) {
			throw new java.sql.SQLException("\n... nextNumseq() - " + getClass().getName() + " -> " + ex.getMessage());
		} finally {
			db.close();
			jd.close();
		}
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 * @exception java.sql.SQLException The exception description.
	 */
	public String nextNumseq(JQuery db) throws java.sql.SQLException {
		try {
			String tmpNumseq = "0";
				String query = //
		"Select numseq " + //
		"From "+Constante.ESQUEMA1+".TSCHTRA " + //
		"Where codtra = '" + getCodtra() + "' " + //
		"And codfas = '" + getCodfas() + "' " + //
		"And codalt = '" + getCodalt() + "' " + //
	"Order by numseq DESC";
			db.setQuery(query);
			db.executeQuery();
			if (db.getResultSet().next()) {
				tmpNumseq = db.stringValue(1).trim();
			}
			int cod = new Integer(tmpNumseq).intValue();
			tmpNumseq = (cod >= 99) ? "99" : "" + (cod + 1);
			return tmpNumseq;
		} catch (Exception ex) {
			throw new java.sql.SQLException("\n... nextNumseq() - " + getClass().getName() + " -> " + ex.getMessage());
		}
	}
	public void nextTmetarg(int i) {
		setNum_seq_arg(((java.util.Vector) getGridArgs().elementAt(i)).elementAt(0).toString());
		setTxt_arg(((java.util.Vector) getGridArgs().elementAt(i)).elementAt(1).toString());
	}
	public boolean search(JQuery db) throws java.sql.SQLException {
		try {
				String query = //
		"Select codcla, codmet, ordsnd " + //
		"From "+Constante.ESQUEMA1+".TSCHTRA " + //
		"Where codtra = '" + getCodtra() + "' " + //
		"And codfas = '" + getCodfas() + "' " + //
		"And codalt = '" + getCodalt() + "' " + //
	"And numseq = " + getNumseq() + " ";
			db.setQuery(query);
			db.executeQuery();
			if (!db.getResultSet().next())
				return false;
			setCodcla(db.stringValue(1).trim());
			setCodmet(db.stringValue(2).trim());
			setOrdsnd(db.stringValue(3).trim());
			setTxtcla(Clases.searchClase(db, getCodcla()));
			setTxtmet(Metodos.searchMetodo(db, getCodcla(), getCodmet()));
			return true;
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... search() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	public boolean searchTmetarg() throws java.sql.SQLException {
		JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
		JQuery db = new JQuery(jd.getConnection());
		try {
			return searchTmetarg(db);
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... searchTmetarg() - " + getClass().getName() + " -> " + e.getMessage());
		} finally {
			db.close();
			jd.close();
		}
	}
	public boolean searchTmetarg(JQuery db) throws java.sql.SQLException {
		try {
				String query = //
		"Select numseqarg, txtarg " + //
		"From "+Constante.ESQUEMA1+".TMETARG " + //
		"Where codtra = '" + getCodtra() + "' " + //
		"And codfas = '" + getCodfas() + "' " + //
		"And codalt = '" + getCodalt() + "' " + //
		"And numseq = " + getNumseq() + " " + //
	"And numseqarg = " + getNumseqarg();
			db.setQuery(query);
			db.executeQuery();
			if (!db.getResultSet().next())
				return false;
			setNumseqarg(db.stringValue(1));
			setTxtarg(db.stringValue(2));
			return true;
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... searchTmetarg() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setNum_seq_arg(String newValue) {
		this.num_seq_arg = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setNumseqarg(String newValue) {
		this.numseqarg = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setOrd_snd(String newValue) {
		this.ord_snd = newValue;
	}
	/**
	 * This method was created in VisualAge.
	 * @param newValue java.lang.String
	 */
	public void setOrdsnd(String newValue) {
		this.ordsnd = newValue;
	}
	public void sort(JQuery db) throws java.sql.SQLException {
		try {
			sortEsquema(db, getCodtra(), getCodfas(), getCodalt());
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... sort() - " + getClass().getName() + " - " + e.getMessage());
		}
	}
	public void update(JQuery db) throws java.sql.SQLException {
	}
	public void update(JQuery db, javax.servlet.http.HttpServletRequest req) throws java.sql.SQLException {
		try {
			setError("");
			String numseq = nextNumseq(db);
			int maxNumseq = 0;
			try {
				maxNumseq = Integer.valueOf(numseq).intValue() + 1;
			} catch (Exception ex) {
				maxNumseq = 0;
			}
			for (int i = 1; i < maxNumseq; i++) {
				//DPS NO SE USA
				//String swOrdsnd = req.getParameter("Ordsnd" + i);
				//DPS A1
				String swOrdsnd = "00"; 
				try {
					String query = "Update "+Constante.ESQUEMA1+".TSCHTRA set ";
					
					if (CString.isEmptyOrNull(swOrdsnd))
						query += "ordsnd = '00' ";
					else
						query += "ordsnd = '" + CString.completeToBeginWithNChars(swOrdsnd, 2, '0') + "' ";
					query += "Where codtra = '" + getCodtra() + "' and codfas = '" + getCodfas() + "' and codalt = '" + getCodalt() + "' and numseq = " + i;
					db.setQuery(query);
					db.executeUpdate();
					setGrid(null);
				} catch (Exception e) {
					continue;
				}
			}
			loadGrid(db);
		} catch (Exception e) {
			throw new java.sql.SQLException("\n...update() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
	public void update(javax.servlet.http.HttpServletRequest req) throws java.sql.SQLException {
		JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
		JQuery db = new JQuery(jd.getConnection());
		try {
			update(db, req);
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... update() - " + getClass().getName() + " -> " + e.getMessage());
		} finally {
			db.close();
			jd.close();
		}
	}
	public void updateTmetarg() throws java.sql.SQLException {
		JDatabase jd = new JDatabase(getLogin().driver, getLogin().url, getLogin().userid, getLogin().password);
		JQuery db = new JQuery(jd.getConnection());
		try {
			updateTmetarg(db);
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... updateTmetarg() - " + getClass().getName() + " -> " + e.getMessage());
		} finally {
			db.close();
			jd.close();
		}
	}
	public void updateTmetarg(JQuery db) throws java.sql.SQLException {
		try {
			String antTxtarg = getTxtarg();
			if (searchTmetarg(db)) {
					String query = //
		"Update "+Constante.ESQUEMA1+".TMETARG set " + //
		"txtarg = '" + antTxtarg + "' " + //
		"Where codtra = '" + getCodtra() + "' " + //
		"And codfas = '" + getCodfas() + "' " + //
		"And codalt = '" + getCodalt() + "' " + //
		"And numseq = " + getNumseq() + " " + //
	"And numseqarg = " + getNumseqarg();
				db.setQuery(query);
				db.executeUpdate();
				String bef[] = { getCodtra(), getCodfas(), getCodalt(), getNumseq(), getNumseqarg(), getTxtarg()};
				String aft[] = { getCodtra(), getCodfas(), getCodalt(), getNumseq(), getNumseqarg(), antTxtarg };
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Modificar", Constante.ESQUEMA1+".TMETARG", Table.getColumnsTmetarg(), bef, aft);
				setGridArgs(null);
				loadGridTmetarg(db);
			}
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... updateTmetarg() - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
}