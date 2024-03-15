package pe.cosapi.wzapata.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;

import java.sql.CallableStatement;
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

public class TestReadFile {
	public static SimpleDateFormat formatter=  new SimpleDateFormat("yyyyMMdd");
	public static Calendar cal = Calendar.getInstance();
	
	public static void main(String[] args) throws ParseException {
	Connection conn = null;
	String password = "";
	String url = "";
	String esquema="PV_SWB";
	String nomTabla="ttrahor";
		try {
			/***********************CONEXION A LA BD******************************/
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:oracle:thin:@10.7.10.36:1521:prod";
		 	password = "db_swb";
		 	conn = DriverManager.getConnection(url, "db_swb", password);
		 	conn.setAutoCommit(true);
		 	/*********************DESHABILITA CONSTRAINTS*************************/
		 	CallableStatement proc=null;
		 	proc = conn.prepareCall("{call disableConstraints(?, ?) }");
		    proc.setString(1, esquema);
		    proc.setString(2, nomTabla);
		    proc.execute();
		    System.out.println("DESHABILITA CONSTRAINTS");
		    /************************TRUNCATE A LA TABLA***************************/
		    String query = "truncate table " +esquema+"."+ nomTabla;
		    PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("TRUNCATE A LA TABLA");
		    /**************************EJECUTA INSERTS*******************************/
			query = ReadToImportFile(nomTabla, "C:/20080226");
			query =	"BEGIN " +query+" END;";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			System.out.println("EJECUTA INSERTS");
			/*********************HABILITA CONSTRAINTS*************************/
		 	proc=null;
		 	proc = conn.prepareCall("{call enableConstraints(?, ?) }");
		    proc.setString(1, esquema);
		    proc.setString(2, nomTabla);
		    proc.execute();
		    System.out.println("HABILITA CONSTRAINTS");
		    /*********************EXECUTE BAT*************************/
		    Runtime aplicacion = Runtime.getRuntime(); 
	        try{
	        	//aplicacion.exec("cmd.exe /K C:/walter/backup.bat");
	       // aplicacion.exec("cmd.exe /K start C:/walter/backup.bat");
	        	//aplicacion.exec("/bin/sh /home/sh/backup.sh"); 

	        System.out.println("EJECUTA BAT");
	        }
	        catch(Exception e)
			{System.out.println(e);
			}

				    
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String ReadToImportFile(String nombreTabla, String ruta){
		if(ruta.charAt(ruta.length()-1)=='/'){
	    	ruta=ruta.substring(0,ruta.length()-1);
	    }
	  FileInputStream fis=null;
	  byte b[]=null;
	  int i;
	  String s;
		  try {
		  	fis = new FileInputStream(ruta+"/"+nombreTabla+".sql");
		  } catch( FileNotFoundException e ) {
		  	
		  	System.out.println("No se encontro el archivo!!!..."+ e.getMessage());
		  }
		  try {
		  	b= new byte[fis.available()];
		  	i = fis.read( b );
		  } catch( IOException e ) {
		  }
	  s = new String(b);
			  return s;
	}
}
