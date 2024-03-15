package CosapiSoft.SARAWebBanking;

import pe.cosapi.common.Constante;

/**
 * This type was created in VisualAge.
 */
public class LogOpe {
/**
 * LogOpe constructor comment.
 */
public LogOpe() {
	super();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String[]
 */
public static java.lang.String[] getColumns(String aftrcd, String bfrrcd) {
	String cad = aftrcd;
	if (cad == null || cad.equals(""))
		cad = bfrrcd;
	CosapiSoft.SARAWebBanking.CString string = new CosapiSoft.SARAWebBanking.CString(cad);
	int ncols = string.countOccurences('¥') / 2;
	String[] columns = (ncols > 0) ? new String[ncols] : new String[1];
	for (int c = 0; c < ncols; c++) {
		string.deleteFirstChar();
		columns[c] = string.substringHeadUntil('¥').toUpperCase(); // Nombre de la Columna
		string.deleteFirstChar();
		if (c < ncols - 1)
			string.substringHeadUntil('¥'); // Valor de la Columna
	}
	return columns;
}
public static String[] getDatos(String datos) {
	if (datos == null || datos.equals(""))
		return new String[0];
	CosapiSoft.SARAWebBanking.CString string = new CosapiSoft.SARAWebBanking.CString(datos);
	int ncols = string.countOccurences('¥') / 2;
	String columns[] = (ncols > 0) ? new String[ncols] : new String[0];
	for (int c = 0; c < ncols; c++) {
		string.deleteFirstChar(); // Aqui se ecuentra
		string.substringHeadUntil('¥'); //       el
		string.deleteFirstChar(); //Nombre de la Columna
		if (c < ncols - 1)
			columns[c] = string.substringHeadUntil('¥'); // Valor de la Columna
	}
	if (ncols > 0)
		columns[ncols - 1] = string.toString(); // Valor de la Columna
	return columns;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public static String getDatpro() {
	CosapiSoft.SARAWebBanking.CString fecha = new CosapiSoft.SARAWebBanking.CString(CosapiSoft.SARAWebBanking.CString.toDate("yyyy/MM/dd"));
	fecha.deleteAll('/');
	return fecha.toString();
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public static String getHorpro() {
	return CosapiSoft.SARAWebBanking.CString.toTime();
}
/**
 * This method was created in VisualAge.
 * @param newValue java.util.Vector
 */
public static String getRecord(String[] columns, String[] datos) {
	if (columns == null || datos == null)
		return "";
	String record = "";
	for (int i = 0; i < datos.length; i++){
		
		record += "¥" + columns[i] + "¥" + datos[i];
	}
		
	return record;
}
/**
 * Perform the saveJournalManager method.
 */
public static void saveLogManager(JQuery db, String mod, String usr, String ope, String tabla, String[] columns, String[] bef, String[] aft) throws Exception {
	try {
		String query = //
		"Insert into "+Constante.ESQUEMA1+".TLOGOPE (datpro, txtmod, codusr, horpro, txtope, txttab, bfrrcd, aftrcd) values (" + //
		"'" + getDatpro() + "', " + //
		"'" + mod.toUpperCase() + "', " + //
		"'" + usr.toUpperCase() + "', " + //
		"'" + getHorpro() + "', " + //
		"'" + ope.toUpperCase() + "', " + //
		"'" + tabla.toUpperCase() + "', " + //
		"'" + getRecord(columns, bef) + "', " + //
		"'" + getRecord(columns, aft) + "' " + //
		")";
		// System.out.println("QUERY :"+query+"************************");
		db.setQuery(query);
		db.executeUpdate();
	}
	catch (Exception e) {
		e.printStackTrace();
		throw new Exception("... LogOpe -> saveLogManager(" + mod + ", " + usr + ", " + ope + ", " + tabla + ", " + columns.toString() + ", " + bef.toString() + ", " + aft.toString() + ") \n " + e.getMessage());
	}
}
/**
 * Perform the saveJournalManager method.
 */
public static void saveLogManager(String mod, String usr, String ope, String tabla, String[] columns, String[] bef, String[] aft) throws Exception {
	JDatabase jd = new JDatabase();
	JQuery db = new JQuery(jd.getConnection());
	try {
		saveLogManager(db, mod, usr, ope, tabla, columns, bef, aft);
	}
	catch (Exception e) {
		throw new Exception("... LogOpe -> saveLogManager(" + mod + ", " + usr + ", " + ope + ", " + tabla + ", " + columns.toString() + ", " + bef.toString() + ", " + aft.toString() + ") \n " + e.getMessage());
	}
	finally {
		db.close();
		jd.close();
	}
}
}