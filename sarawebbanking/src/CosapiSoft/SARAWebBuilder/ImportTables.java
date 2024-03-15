package CosapiSoft.SARAWebBuilder;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.cosapisoft.sarawebbanking.admin.EjecucionDeProcesoServlet;

import CosapiSoft.SARAWebBanking.InicioSesion;
import CosapiSoft.SARAWebBanking.JDatabase;
import CosapiSoft.SARAWebBanking.JDatabasen;
import CosapiSoft.SARAWebBanking.JQuery;
import CosapiSoft.SARAWebBanking.Mensajes;
import pe.cosapi.common.*;
import pe.cosapi.wzapata.test.TestBD;
import pe.cosapi.wzapata.test.TestReadFile;

public class ImportTables {
	public InicioSesion login;
	JDatabase jdc = null;
	public String error="";
	Connection conexion=null;
	//JQuery db;
	

public boolean importar(String nomTabla, String esquema, String opcion, String ruta) throws Exception {
	JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	nomTabla= nomTabla.toLowerCase();
	
		error = "";
		boolean sw = importar(db, nomTabla,esquema,opcion, ruta);
		try {
			db.close();
			jd.close();
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return sw;
}
private boolean importar(JQuery db, String nomTabla, String esquema, String opcion, String ruta) throws Exception {

		this.setConexion(db.getConnection());
		db.getConnection().setAutoCommit(false);
		java.util.ResourceBundle resource=null;
		SimpleDateFormat formatter=null;
    	String campos="";
		String[] arrayCampos={};
		String query;
		try {
			resource = java.util.ResourceBundle.getBundle("CosapiSoft.SARAWebBuilder."+ esquema);
			campos = resource.getString(nomTabla) + " ";
			arrayCampos = ObjectUtil.getArrayStrings(campos.trim(),",");
			query = "Select "+campos + 
			"From " + esquema.trim() + "." + nomTabla.trim();
			db.setQuery(query);
			db.executeQuery();
		} catch (Exception e2) {
			System.out.println("Error al leer el archivo properties ó al ejecutar el select general a la tabla");
			db.getConnection().rollback();
			return false;
		}
		String insert="";
		String queryInsert="";
		try {
			while (db.getResultSet().next()) {
				insert=	"Insert into "+ esquema+ "." + nomTabla + "("+ campos.trim()+") values('" ;
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
				queryInsert = queryInsert + "\n" + insert;
			}
		} catch (Exception e3) {
			System.out.println("Error al generar el query del backup");
			db.getConnection().rollback();
			return false;
		}
		
		/**************************************EJECUTA BACKUP DE TABLA**********************************************/
		try {
			TestBD.printToImportFile(queryInsert.getBytes(),nomTabla.toLowerCase()+"_backup.sql",ruta);
			System.out.println("Imprime archivo backup .." +ruta+"/"+ nomTabla.toLowerCase()+"_backup.sql");
		} catch (Exception e4) {
			System.out.println("Error al generar el archivo de backup de la tabla "+ nomTabla);
			return false;
		}

	    /**************************************BOrra la tabla**********************************************/
		String sentencia="";
		try {
			sentencia=TestReadFile.ReadToImportFile(nomTabla.toLowerCase(), ruta);
		} catch (Exception e5) {
			
			System.out.println("Error al leer el archivo a restaurar " + nomTabla+ ".sql o no se encontró la ruta " + ruta);
			e5.printStackTrace();
			return false;
		}
	    try {
			query = "delete from " +esquema+"."+ nomTabla;
			PreparedStatement pstmt = db.getConnection().prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("DELETE GENERAL A LA TABLA " + nomTabla);
		} catch (Exception e1) {
			System.out.println("Error al borrar la tabla " + nomTabla);
			db.getConnection().rollback();
			return false;
		}
	    /**************************************Lee archivo de datos************************************************/
		
		sentencia="";
		try {
			sentencia=TestReadFile.ReadToImportFile(nomTabla.toLowerCase(), ruta);
			sentencia =	"BEGIN " +sentencia+" END;";
		} catch (Exception e5) {
			
			System.out.println("Error al leer el archivo a restaurar " + nomTabla+ ".sql o no se encontró la ruta " + ruta);
			e5.printStackTrace();
			return false;
		}
		
		
		try {
			db.setQuery(sentencia);
			db.executeQuery();
		} catch (RuntimeException e6) {
			System.out.println("Error al realizar la inserción de registros a la tabla " + nomTabla);
			e6.printStackTrace();
			return false;
		}
		db.getConnection().commit();
		System.out.println("Importacion de datos finalizada a la tabla " + nomTabla);
		return true;
}

	public  boolean deshabilitaConstraints(String esquema, String nomTabla) throws Exception{
		JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
		JQuery db = new JQuery(jd.getConnection());
		this.setConexion(db.getConnection());
		try {
			CallableStatement proc=null;
		 	proc = db.getConnection().prepareCall("{call disableConstraints(?, ?) }");
		    proc.setString(1, esquema);
		    proc.setString(2, nomTabla);
		    proc.execute();
		    db.getConnection().commit();
		    System.out.println("DESHABILITA CONSTRAINTS");
		    return true;
		} catch (Exception e) {
			System.out.println("\nExportar - Tablas del aplicativo \n" + e.getMessage());
			return false;
		} finally {
			db.close();
			jd.close();
		}
	}
	
	public  boolean habilitaConstraints(String esquema, String nomTabla) throws Exception{
	    JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
		JQuery db = new JQuery(jd.getConnection());
		this.setConexion(db.getConnection());
		try {
			CallableStatement proc=null;
			proc = db.getConnection().prepareCall("{call enableConstraints(?, ?) }");
		    proc.setString(1, esquema);
		    proc.setString(2, nomTabla);
		    proc.execute();
		    db.getConnection().commit();
		    System.out.println("HABILITA CONSTRAINTS");
		    return true;
		} catch (Exception e) {
			System.out.println("\nExportar - Tablas del aplicativo \n" + e.getMessage());
			return false;
		} finally {
			db.close();
			jd.close();
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
	/**
	 * @return Devuelve db.
	 */
	
	public void closeConnection()  throws Exception {
		jdc.getConnection().close();
	
	}
	/**
	 * @return Devuelve conexion.
	 */
	public Connection getConexion() {
		return conexion;
	}
	/**
	 * @param conexion El conexion a establecer.
	 */
	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
}
