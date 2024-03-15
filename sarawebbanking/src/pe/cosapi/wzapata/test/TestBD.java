package pe.cosapi.wzapata.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import pe.cosapi.common.Constante;
import pe.cosapi.common.ObjectUtil;

public class TestBD {
	public static SimpleDateFormat formatter=  new SimpleDateFormat("yyyyMMdd");
	public static Calendar cal = Calendar.getInstance();
	public static void main(String[] args) throws ParseException {
		Connection conn = null;
		 String password = "";
		 String url = "";
		 String esquema="pv_swb";
		 String nomTabla="tctrtra";
		 
		 
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:oracle:thin:@10.7.10.36:1521:prod";
		 	password = "db_swb";
		 	conn = DriverManager.getConnection(url, "db_swb", password);
			
			/*Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:oracle:thin:@10.7.12.34:1521:orades";
		 	password = "db_swb";
		 	
		 	 conn = DriverManager.getConnection(url, "db_swb", password);
			*/
		 	 
		 	java.util.ResourceBundle resource=null;
	    	resource = java.util.ResourceBundle.getBundle("CosapiSoft.SARAWebBuilder."+ esquema);
	    	String campos=resource.getString(nomTabla) + " ";
	    	String[] arrayCampos=ObjectUtil.getArrayStrings(campos,",");
			String query = //
			"Select "+campos + 
			"From " + esquema.trim() + "." + nomTabla.trim();
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			String insert="";
			String queryInsert="";
			while (rs.next()) {
				insert=	"Insert into "+ esquema+ "." + nomTabla + "("+ campos.trim()+") values(" ;
				for(int i=0; i<arrayCampos.length;i++){
					if(i==arrayCampos.length-1){
						if(arrayCampos[i].trim().equalsIgnoreCase("horini") || arrayCampos[i].trim().equalsIgnoreCase("horfin")){
							formatter=  new SimpleDateFormat("dd/MM/yy HH:mm:ss");
							insert= insert+"to_date('"+formatter.format(rs.getTimestamp(arrayCampos[i].trim()))+"','dd/mm/rrrr hh24:mi:ss')); \n";
						}else{
							if(rs.getString(arrayCampos[i].trim())==null){
								insert= insert+"''); \n";
							}else
								insert= insert+"'"+rs.getString(arrayCampos[i].trim())+"'); \n";							
						}
					}else{
						if(arrayCampos[i].trim().equalsIgnoreCase("horini") || arrayCampos[i].trim().equalsIgnoreCase("horfin")){
							formatter=  new SimpleDateFormat("dd/MM/yy HH:mm:ss");
							insert= insert+"to_date('"+formatter.format(rs.getTimestamp(arrayCampos[i].trim()))+"','dd/mm/rrrr hh24:mi:ss'),";
						}else{
							if(rs.getString(arrayCampos[i].trim())==null){
								insert= insert+"'',";
							}else
								insert= insert+"'"+rs.getString(arrayCampos[i].trim())+"',";
						}
						
					}
				}
				queryInsert = queryInsert + insert;
			}
				
				printToExportFile(queryInsert.getBytes(),nomTabla+".sql","c:/walter");
	
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printToExportFile(byte[] trama,String nombre, String ruta){
	    File f = null;
	    formatter=  new SimpleDateFormat("yyyyMMdd");
	    if(ruta.charAt(ruta.length()-1)=='/'){
	    	ruta=ruta.substring(0,ruta.length()-1);
	    }
	    
		if (Constante.CONSTANTE_OS.equals("2") || Constante.CONSTANTE_OS.equals("3")){
			cal = Calendar.getInstance();
			f=new File(ruta +"/" +formatter.format(cal.getTime()) ); 
		    f.mkdir(); 
		    String archivo = ruta+"/"+formatter.format(cal.getTime())+"/"+nombre;
		    f = new File(archivo);
		    

		}else{
			cal = Calendar.getInstance();
	    	f=new File(ruta +"/" +formatter.format(cal.getTime()) ); 
		    f.mkdir(); 
	        String archivo = ruta+"/"+ formatter.format(cal.getTime())+"/"+nombre;
			f = new File(archivo);
			
		}
		
		try {
			FileOutputStream st = new FileOutputStream(f);
			st.write(trama);
			st.close();
			f = null;
			st = null;
		} catch (Exception e) {
			
		}
	}
	public static void printToImportFile(byte[] trama,String nombre, String ruta){
	    File f = null;
	    
	    if(ruta.charAt(ruta.length()-1)=='/'){
	    	ruta=ruta.substring(0,ruta.length()-1);
	    }
	    
		if (Constante.CONSTANTE_OS.equals("2") || Constante.CONSTANTE_OS.equals("3")|| Constante.CONSTANTE_OS.equals("1")){
			 
		    String archivo = ruta+"/"+nombre;
		    f = new File(archivo);
		    

		}
		
		try {
			FileOutputStream st = new FileOutputStream(f);
			st.write(trama);
			st.close();
			f = null;
			st = null;
		} catch (Exception e) {
			
		}
	}
	
}

