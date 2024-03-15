package CosapiSoft.SARAWebBuilder;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import CosapiSoft.SARAWebBanking.InicioSesion;
import CosapiSoft.SARAWebBanking.JDatabase;
import CosapiSoft.SARAWebBanking.JDatabasen;
import CosapiSoft.SARAWebBanking.JQuery;
import CosapiSoft.SARAWebBanking.Mensajes;
import pe.cosapi.common.*;
import pe.cosapi.wzapata.test.TestBD;

public class ExportTables {
	private InicioSesion login;
	
	public String error="";

public boolean exportar(String nomTabla, String esquema, String opcion, String ruta) throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	nomTabla= nomTabla.toLowerCase();
	try {
		error = "";
		boolean sw = exportar(db, nomTabla,esquema,opcion, ruta);
		return sw;
	} catch (Exception e) {
		throw new Exception("\nExportar - Tablas del aplicativo \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
private boolean exportar(JQuery db, String nomTabla, String esquema, String opcion, String ruta) throws Exception {
	try {
		SimpleDateFormat formatter=null;
		java.util.ResourceBundle resource=null;
    	resource = java.util.ResourceBundle.getBundle("CosapiSoft.SARAWebBuilder."+ esquema);
    	String campos=resource.getString(nomTabla) + " ";
    	String[] arrayCampos=ObjectUtil.getArrayStrings(campos.trim(),",");
		String query = //
		"Select "+campos + 
		"From " + esquema.trim() + "." + nomTabla.trim();
		db.setQuery(query);
		db.executeQuery();
		String insert="";
		String queryInsert="";
		while (db.getResultSet().next()) {
			insert=	"Insert into "+ esquema+ "." + nomTabla + "("+ campos.trim()+") values(" ;
			for(int i=0; i<arrayCampos.length;i++){
				if(i==arrayCampos.length-1){
					if(arrayCampos[i].trim().equalsIgnoreCase("horini") || arrayCampos[i].trim().equalsIgnoreCase("horfin")){
						formatter=  new SimpleDateFormat("dd/MM/yy HH:mm:ss");
						insert= insert+"to_date('"+formatter.format(db.getResultSet().getTimestamp(arrayCampos[i].trim()))+"','dd/mm/rrrr hh24:mi:ss')); \n";
					}else{
						if(db.getResultSet().getString(arrayCampos[i].trim())==null){
							insert= insert+"''); \n";
						}else
							insert= insert+"'"+db.getResultSet().getString(arrayCampos[i].trim())+"'); \n";							
					}
				}else{
					if(arrayCampos[i].trim().equalsIgnoreCase("horini") || arrayCampos[i].trim().equalsIgnoreCase("horfin")){
						formatter=  new SimpleDateFormat("dd/MM/yy HH:mm:ss");
						insert= insert+"to_date('"+formatter.format(db.getResultSet().getTimestamp(arrayCampos[i].trim()))+"','dd/mm/rrrr hh24:mi:ss'),";
					}else{
						if(db.getResultSet().getString(arrayCampos[i].trim())==null){
							insert= insert+"'',";
						}else
							insert= insert+"'"+db.getResultSet().getString(arrayCampos[i].trim())+"',";
					}
					
				}
					
			}
			queryInsert = queryInsert + insert;
		}
		TestBD.printToExportFile(queryInsert.getBytes(),nomTabla+".sql",ruta);
		return true;
	} 
	catch (Exception e) {
		e.printStackTrace();
		throw new Exception("\nBuscar - Log de Operaciones \n" + e.getMessage());
	}
}



	/**
	 * @return Devuelve error.
	 */
	public String getError() {
		return error;
	}
	/**
	 * @param error El error a establecer.
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return Devuelve login.
	 */
	public InicioSesion getLogin() {
		return login;
	}
	/**
	 * @param login El login a establecer.
	 */
	public void setLogin(InicioSesion login) {
		this.login = login;
	}
}
