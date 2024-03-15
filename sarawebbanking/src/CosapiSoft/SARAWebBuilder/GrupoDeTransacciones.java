package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class GrupoDeTransacciones extends GenericTable {
	private String codgrp = "";
	private String txtdes = "";
	//
	private String cod_grp = "";
	private String txt_des = "";
/**
 * AyudaDeCampo constructor comment.
 */
public GrupoDeTransacciones() {
	super();
}
public void consult() throws java.sql.SQLException {
	try {
		String bef[] = {"", ""};
		String aft[] = {"", ""};
		LogOpe.saveLogManager(getLogin().getNameProducto(), getLogin().getUsuario(), "Consultar", Constante.ESQUEMA1+".TGRPTRA", Table.getColumnsTgrptra(), bef, aft);
	}
	catch (Exception e) {
		throw new java.sql.SQLException(" \n... consult() - Grupo de Transacciones \n" + e.getMessage());
	}
}
public void delete(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		if (search(db)) {
			String query = //
			"Select codtra from "+Constante.ESQUEMA1+".TTRADAT Where codgrp = '" + getCodgrp() + "'";
			db.setQuery(query);
			db.executeQuery();
			java.util.Vector trx = new java.util.Vector(50, 50);
			
			//preguntar si hay una transacción asociada
			if(db.getResultSet().next()){
				trx.addElement(db.stringValue(1));
			for (int i = 0; i < trx.size(); i++) {
				String codtra = trx.elementAt(i).toString();
				query = //
				"Delete from "+Constante.ESQUEMA1+".TCTRTRA Where codtra = '" + codtra + "'";
				db.setQuery(query);
				db.executeUpdate();
				query = //
				"Delete from "+Constante.ESQUEMA1+".TCTRMETARGALT Where codtra = '" + codtra + "'";
				db.setQuery(query);
				db.executeUpdate();
				query = //
				"Delete from "+Constante.ESQUEMA1+".TCTRMETARG Where codtra = '" + codtra + "'";
				db.setQuery(query);
				db.executeUpdate();
				query = //
				"Delete from "+Constante.ESQUEMA1+".TCTRSCH Where codtra = '" + codtra + "'";
				db.setQuery(query);
				db.executeUpdate();
				query = //
				"Delete from "+Constante.ESQUEMA1+".TCTRFAS Where codtra = '" + codtra + "'";
				db.setQuery(query);
				db.executeUpdate();
				query = //
				"Delete from "+Constante.ESQUEMA1+".TPRFTAR Where codtra = '" + codtra + "'";
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
			}
			//la tabla tprfgrp no existe
			//query = //
			//"Delete from tprfgrp Where codgrp = '" + getCodgrp() + "'";
			//db.setQuery(query);
			//db.executeUpdate();
			query = //
			"Delete from "+Constante.ESQUEMA1+".TTRADAT Where codgrp = '" + getCodgrp() + "'";
			db.setQuery(query);
			db.executeUpdate();
			}
			query = //
			"Delete from "+Constante.ESQUEMA1+".TGRPTRA Where codgrp = '" + getCodgrp() + "'";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {getCodgrp(), getTxtdes()};
			String aft[] = {"", ""};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TGRPTRA", Table.getColumnsTgrptra(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_GRUPO_TRX_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodgrp() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - Grupo de Transacciones \n" + e.getMessage());
	}
}
public void delete(JQuery db, javax.servlet.http.HttpServletRequest req) throws java.sql.SQLException, javax.servlet.ServletException {
	try {
		setError("");
		java.util.Enumeration vector = req.getParameterNames();
		while (vector.hasMoreElements()) {
			codgrp = vector.nextElement().toString();
			String sw = req.getParameter(codgrp).toUpperCase();
			if (sw.equals("ON")) {
				if (search(db)) {
					String query = //
					"Select codtra from "+Constante.ESQUEMA1+".TTRADAT Where codgrp = '" + getCodgrp() + "'";
					db.setQuery(query);
					db.executeQuery();
					java.util.Vector trx = new java.util.Vector(50, 50);
					while (db.getResultSet().next()) {
						trx.addElement(db.stringValue(1));
					}
					for (int i = 0; i < trx.size(); i++) {
						String codtra = trx.elementAt(i).toString();
						query = //
						"Delete from "+Constante.ESQUEMA1+".TCTRTRA Where codtra = '" + codtra + "'";
						db.setQuery(query);
						db.executeUpdate();
						query = //
						"Delete from "+Constante.ESQUEMA1+".TCTRMETARGALT Where codtra = '" + codtra + "'";
						db.setQuery(query);
						db.executeUpdate();
						query = //
						"Delete from "+Constante.ESQUEMA1+".TCTRMETARG Where codtra = '" + codtra + "'";
						db.setQuery(query);
						db.executeUpdate();
						query = //
						"Delete from "+Constante.ESQUEMA1+".TCTRSCH Where codtra = '" + codtra + "'";
						db.setQuery(query);
						db.executeUpdate();
						query = //
						"Delete from "+Constante.ESQUEMA1+".TCTRFAS Where codtra = '" + codtra + "'";
						db.setQuery(query);
						db.executeUpdate();
						query = //
						"Delete from "+Constante.ESQUEMA1+".TPRFTAR Where codtra = '" + codtra + "'";
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
					}
//					la tabla tprfgrp no existe
					//query = //
					//"Delete from tprfgrp Where codgrp = '" + getCodgrp() + "'";
					//db.setQuery(query);
					//db.executeUpdate();
					query = //
					"Delete from "+Constante.ESQUEMA1+".TTRADAT Where codgrp = '" + getCodgrp() + "'";
					db.setQuery(query);
					db.executeUpdate();
					query = //
					"Delete from "+Constante.ESQUEMA1+".TGRPTRA Where codgrp = '" + getCodgrp() + "'";
					db.setQuery(query);
					db.executeUpdate();
					String bef[] = {getCodgrp(), getTxtdes()};
					String aft[] = {"", ""};
					LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Eliminar", Constante.ESQUEMA1+".TGRPTRA", Table.getColumnsTgrptra(), bef, aft);
					setGrid(null);
				}
			}
		}
		loadGrid(db);
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... delete() - Grupo de Transacciones \n" + e.getMessage());
	}
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
public String getCodgrp() {
	try {
		if (codgrp == null || codgrp.equals(""))
			codgrp = "0";
		codgrp = codgrp.trim();
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(codgrp)) {
			codgrp = "0";
		}
		int cod = new Integer(codgrp).intValue();
		if (cod <= 0) {
			JDatabase jd = new JDatabase();
			JQuery db = new JQuery(jd.getConnection());
			try {
				String query = //
				"Select codgrp " + //
				"From "+Constante.ESQUEMA1+".TGRPTRA " + //
				"Order by codgrp ";
				db.setQuery(query);
				db.executeQuery();
				while (db.getResultSet().next()) {
					codgrp = db.stringValue(1).trim();
				}
				cod = new Integer(codgrp).intValue();
				codgrp = (cod >= 999) ? "999" : "" + (cod + 1);
			} catch (Exception ex) {
				//System.out.println(getClass().getName() + " - getCodgrp() --> " + ex.getMessage());
				return "-1";
			} finally {
				db.close();
				jd.close();
			}
		}
		if (!codgrp.equals(""))
			codgrp = CString.completeToBeginWithNChars(codgrp, 3, '0');
		return codgrp.trim();
	} catch (Exception e) {
		return "-1";
	}
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getNameTable() {
	return Constante.ESQUEMA1+".tgrptra";
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getTxt_des() {
	return txt_des;
}
/**
 * This method was created in VisualAge.
 * @return String
 */
public String getTxtdes() {
	CString str = new CString(txtdes);
	str.arrange();
	return str.toString();
}
public String getUrl() {
	return getUrlGrupoDeTransacciones();
}
public String getUrlGrupoDeTransacciones() {
	return JspServlet.GRUPO_TRANSACCIONES_SERVLET + "?BtnGrp=Buscar&TxtCodgrp=";
}
public void insert(JQuery db) throws java.sql.SQLException {
	try {
		setError("");
		if (!search(db)) {
			String query = //
			"Insert into "+Constante.ESQUEMA1+".tgrptra values ('" + getCodgrp() + "', '" + getTxtdes() + "')";
			db.setQuery(query);
			db.executeUpdate();
			String bef[] = {"", ""};
			String aft[] = {getCodgrp(), getTxtdes()};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Agregar", Constante.ESQUEMA1+".TGRPTRA", Table.getColumnsTgrptra(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_GRUPO_TRX_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodgrp() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... insert() - Grupo de Transacciones \n" + e.getMessage());
	}
}
public void loadGrid(JQuery db) throws java.sql.SQLException {
	try {
		setIndex(0);
		setStatus("0");
		if (getGrid().size() == 0) {
			String query = //
			"Select codgrp, txtdes " + //
			"From "+Constante.ESQUEMA1+".TGRPTRA " + //
			"Order by codgrp";
			db.setQuery(query);
			db.executeQuery();
			while (db.getResultSet().next()) {
				java.util.Vector row = new java.util.Vector(3);
				row.addElement(getStatus());
				row.addElement(db.stringValue(1));
				row.addElement(db.stringValue(2));
				getGrid().addElement(row);
			}
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... loadGrid() - Grupo de Transacciones \n" + e.getMessage());
	}
}
public void loadGridChild(JQuery db) throws java.sql.SQLException {
}
public void next(int i) {
	setStatus(((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString());
	cod_grp = ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
	txt_des = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
}
public void nextChild(int i) {
}
public boolean search(JQuery db) throws java.sql.SQLException {
	try {
		String query = //
		"Select txtdes " + //
		"From "+Constante.ESQUEMA1+".TGRPTRA " + //
		"Where codgrp = '" + getCodgrp() + "'";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next())
			return false;
		txtdes = db.stringValue(1);
		return true;
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... search() - Grupo de Transacciones \n" + e.getMessage());
	}
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
public void setCodgrp(String newValue) {
	this.codgrp = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setTxt_des(String newValue) {
	this.txt_des = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue int
 */
public void setTxtdes(String newValue) {
	this.txtdes = newValue;
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
		String antDescripcion = txtdes;
		if (search(db)) {
			String query = //
			"Update "+Constante.ESQUEMA1+".TGRPTRA set txtdes = '" + antDescripcion + "' Where codgrp = '" + getCodgrp() + "' ";
			db.setQuery(query);
			int rows = db.executeUpdate();
			String bef[] = {getCodgrp(), getTxtdes()};
			String aft[] = {getCodgrp(), antDescripcion};
			LogOpe.saveLogManager(db, getLogin().getNameProducto(), getLogin().getUsuario(), "Modificar", Constante.ESQUEMA1+".TGRPTRA", Table.getColumnsTgrptra(), bef, aft);
			setGrid(null);
			loadGrid(db);
		}
		else {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_GRUPO_TRX_NO_EXISTE);
			int pos = str.indexOf('&');
			setError(str.substring(0, pos) + getCodgrp() + str.substring(pos + 1));
		}
	}
	catch (Exception e) {
		throw new java.sql.SQLException("\n... update() - Grupo de Transacciones \n" + e.getMessage());
	}
}
}