package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import java.util.Vector;



import CosapiSoft.SARAWebBanking.*;
public class Total extends GenericTable {

	/**
	 * AyudaDeCampo constructor comment.
	 */
	public Total() {
		super();
	}
	public void consult() throws java.sql.SQLException {
		try {
			String bef[] = { "", "" };
			String aft[] = { "", "" };
			LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", "ttotdat", Table.getColumnsTtotdat(), bef, aft);
		} catch (Exception e) {
			throw new java.sql.SQLException(" \n... consult() - Totales \n" + e.getMessage());
		}
	}
	public void delete(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
			if (search(db)) {
					String query = //
	"Delete from ttotdat Where codtot = '" + getCodtot() + "'";
				db.setQuery(query);
				db.executeUpdate();
				db.setQuery(query);
				db.executeUpdate();
				String bef[] = { codtot, getNomtot()};
				String aft[] = { "", "" };
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", "ttotdat", Table.getColumnsTtotdat(), bef, aft);
				setGrid(null);
				loadGrid(db);
			} else {
				String str = Mensajes.getMessage(Mensajes.CODIGO_DE_TOTAL_NO_EXISTE);
				int pos = str.indexOf('&');
				setError(str.substring(0, pos) + getCodtot() + str.substring(pos + 1));
			}
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... delete() - Clases \n" + e.getMessage());
		}
	}
	public void delete(JQuery db, javax.servlet.http.HttpServletRequest req) throws java.sql.SQLException, javax.servlet.ServletException {
		try {
			setError("");
			java.util.Enumeration vector = req.getParameterNames();
			while (vector.hasMoreElements() && vector != null) {
				codtot = vector.nextElement().toString();
				String sw = req.getParameter(codtot).toUpperCase();
				if (sw.equals("ON")) {
					if (search(db)) {
						setGrid(null);
							String query = //
	"Delete from ttotdat Where codtot = '" + getCodtot() + "'";
						db.setQuery(query);
						db.executeUpdate();
						String bef[] = { codtot, getNomtot()};
						String aft[] = { "", "" };
						LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", "ttotdat", Table.getColumnsTtotdat(), bef, aft);
					}
				}
			}
			loadGrid(db);
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... delete() - Clases \n" + e.getMessage());
		}
	}

	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getNameTable() {
		return "tcladat";
	}

	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getUrl() {
		return getUrlClases();
	}
	public String getUrlClases() {
		return JspServlet.TOTAL_SERVLET + "?BtnTot=Buscar&TxtCodtot=";
	}
	public void insert(JQuery db) throws java.sql.SQLException {
		try {
			if (!search(db)) {
				String query = "select codusr, codbra from tusrdat order by codusr, codbra";
				db.setQuery(query);
				db.executeQuery();
				Vector usr = new Vector();
				while (db.getResultSet().next()) {
					Vector tmp = new Vector();
					tmp.addElement(db.getResultSet().getString(1));
					tmp.addElement(db.getResultSet().getString(2));
					usr.addElement(tmp);
				}

				query = "select codcur from tcurdat order by codcur";
				db.setQuery(query);
				db.executeQuery();
				Vector cur = new Vector();
				while (db.getResultSet().next()) {
					Vector tmp = new Vector();
					tmp.addElement(db.getResultSet().getString(1));
					cur.addElement(tmp);
				}

				query = "Insert into ttotdat (codtot, nomtot) values ('" + getCodtot() + "', '" + getNomtot() + "')";
				db.setQuery(query);
				db.executeUpdate();
				for (int i = 0; i < usr.size(); i++) {
					String codusr = ((Vector) usr.elementAt(i)).elementAt(0).toString();
					String codbra = ((Vector) usr.elementAt(i)).elementAt(1).toString();
					for (int j = 0; j < cur.size(); j++) {
						String codcur = ((Vector) cur.elementAt(j)).elementAt(0).toString();
						query = "Insert into ttotdet (codtot, codusr, codbra, mtotot, mtocan, numope, codcur, mtotot2) values ('" + getCodtot() + "', '" + codusr + "', '" + codbra + "', 0,0,0,'" + codcur + "',0)";
						db.setQuery(query);
						db.executeUpdate();
					}
				}

				String bef[] = { "", "" };
				String aft[] = { getCodtot(), getNomtot()};
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", "ttotdat", Table.getColumnsTtotdat(), bef, aft);
				setGrid(null);
				loadGrid(db);
			} else {
				String str = Mensajes.getMessage(Mensajes.CODIGO_DE_TOTAL_EXISTE);
				int pos = str.indexOf('&');
				setError(str.substring(0, pos) + getCodtot() + str.substring(pos + 1));
			}
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... delete() - Cajas \n" + e.getMessage());
		}
	}
	public void loadGrid(JQuery db) throws java.sql.SQLException {
		try {
			setIndex(0);
			setStatus("0");
			if (getGrid().size() == 0) {
					String query = //
		"Select codtot, nomtot " + //
		"From ttotdat " + //
	"Order by codtot";
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					java.util.Vector row = new java.util.Vector(3);
					row.addElement(getStatus());
					row.addElement(db.stringValue(1).trim());
					row.addElement(db.stringValue(2).trim());
					getGrid().addElement(row);
				}
			}
		} catch (Exception e) {
			throw new java.sql.SQLException("\n...loadGrid() - Totales \n" + e.getMessage());
		}
	}
	public void loadGridChild(JQuery db) throws java.sql.SQLException {
	}
	public void next(int i) {
		setStatus(((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString().trim());
		cod_tot = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString().trim();
		nom_tot = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString().trim();
	}
	public void nextChild(int i) {
	}
	public boolean search(JQuery db) throws java.sql.SQLException {
		try {
				String query = //
		"Select nomtot " + //
		"From ttotdat " + //
	"Where codtot = '" + getCodtot() + "'";
			db.setQuery(query);
			db.executeQuery();
			if (!db.getResultSet().next())
				return false;
			nomtot = db.stringValue(1);
			return true;
		} catch (java.sql.SQLException e) {
			throw new java.sql.SQLException("\n... search() - totas \n" + e.getMessage());
		}
	}

	/**
	 * This method was created in VisualAge.
	 * @param db CosapiSoft.SARAWebBanking.JQuery
	 * @exception java.sql.SQLException The exception description.
	 */
	public void sort(JQuery db) throws java.sql.SQLException {
	}
	public void update(JQuery db) throws java.sql.SQLException {
		try {
			setError("");
			String antNomtot = nomtot;
			if (search(db)) {
					String query = //
	"Update ttotdat set nomtot = '" + antNomtot + "' Where codtot = '" + getCodtot() + "' ";
				db.setQuery(query);
				int rows = db.executeUpdate();
				String bef[] = { getCodtot(), getNomtot()};
				String aft[] = { getCodtot(), antNomtot };
				LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", "ttotdat", Table.getColumnsTtotdat(), bef, aft);
				setGrid(null);
				loadGrid(db);
			} else {
				String str = Mensajes.getMessage(Mensajes.CODIGO_DE_TOTAL_NO_EXISTE);
				int pos = str.indexOf('&');
				setError(str.substring(0, pos) + getCodtot() + str.substring(pos + 1));
			}
		} catch (Exception e) {
			throw new java.sql.SQLException("\n... update() - Clases \n" + e.getMessage());
		}
	}
	//
	private String cod_tot = "";
	private String codtot = "";
	private String nom_tot = "";
	private String nomtot = ""; /**
			 * This method was created in VisualAge.
			 * @return java.lang.String
			 */
	public String getCod_tot() {
		if (cod_tot == null)
			cod_tot = "";
		return cod_tot.trim();
	} /**
			 * This method was created in VisualAge.
			 * @return java.lang.String
			 */
	public String getCodtot() {
		try {
			if (codtot == null || codtot.equals(""))
				codtot = "0";
			codtot = codtot.trim();
			if (!CosapiSoft.SARAWebBanking.CString.isInteger(codtot)) {
				codtot = "0";
			}
			int cod = new Integer(codtot).intValue();
			if (cod <= 0) {
				InicioSesion.loadParameters();
				JDatabase jd = new JDatabase();
				JQuery db = new JQuery(jd.getConnection());
				try {
						String query = //
		"Select codtot " + //
		"From ttotdat " + //
	"Order by codtot ";
					db.setQuery(query);
					db.executeQuery();
					while (db.getResultSet().next()) {
						codtot = db.stringValue(1).trim();
					}
					cod = new Integer(codtot).intValue();
					codtot = (cod >= 99999) ? "99999" : "" + (cod + 1);
				} catch (Exception ex) {
					// System.out.println(getClass().getName() + " - getcodtot() --> " + ex.getMessage());
					return "-1";
				} finally {
					db.close();
					jd.close();
				}
			}
			if (!codtot.equals(""))
				codtot = CString.completeToBeginWithNChars(codtot, 5, '0');
			return codtot.trim();
		} catch (Exception e) {
			return "-1";
		}
	} /**
			 * This method was created in VisualAge.
			 * @return java.lang.String
			 */
	public String getNom_tot() {
		return nom_tot;
	} /**
			 * This method was created in VisualAge.
			 * @return String
			 */
	public String getNomtot() {
		if (nomtot == null)
			nomtot = "";
		CString str = new CString(nomtot);
		str.arrange();
		return str.toString();
	}
	public static String searchCaja(JQuery db, String codcaj) throws Exception {
		try {
				String query = //
		"Select nomcaj " + //
		"From tcajdat " + //
	"Where codcaj = '" + codcaj + "'";
			db.setQuery(query);
			db.executeQuery();
			boolean sw = db.getResultSet().next();
			String txtcaj = "---";
			if (sw) {
				txtcaj = db.stringValue(1).trim();
				if (txtcaj.equals("")) {
					txtcaj = "---";
				}
			}
			return txtcaj;
		} catch (Exception e) {
			throw new Exception("\n... searchClase(" + codcaj + ") - Cajas \n" + e.getMessage());
		}
	}
	public static String searchCaja(String codcaj) throws Exception {
		JDatabase jd = new JDatabase();
		JQuery db = new JQuery(jd.getConnection());
		try {
				String query = //
		"Select nomcaj " + //
		"From tcajdat " + //
	"Where codcaj = '" + codcaj + "'";
			db.setQuery(query);
			db.executeQuery();
			boolean sw = db.getResultSet().next();
			String txtcaj = "---";
			if (sw) {
				txtcaj = db.stringValue(1);
				if (txtcaj.equals("")) {
					txtcaj = "---";
				}
			}
			return txtcaj;
		} catch (Exception e) {
			throw new Exception("\n... searchClase(" + codcaj + ") - Cajas \n" + e.getMessage());
		} finally {
			db.close();
			jd.close();
		}
	} /**
			 * This method was created in VisualAge.
			 * @param newValue java.lang.String
			 */
	public void setCod_tot(String newValue) {
		this.cod_tot = newValue;
	} /**
			 * This method was created in VisualAge.
			 * @param newValue java.lang.String
			 */
	public void setCodtot(String newValue) {
		this.codtot = newValue;
	} /**
			 * This method was created in VisualAge.
			 * @param newValue java.lang.String
			 */
	public void setNom_tot(String newValue) {
		this.nom_tot = newValue;
	} /**
			 * This method was created in VisualAge.
			 * @param newValue int
			 */
	public void setNomtot(String newValue) {
		this.nomtot = newValue;
	}
}