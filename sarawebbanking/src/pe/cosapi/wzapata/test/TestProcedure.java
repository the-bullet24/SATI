package pe.cosapi.wzapata.test;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import pe.cosapi.common.Constante;


public class TestProcedure {

	public static void main(String[] args) throws ParseException {
		Connection conn = null;
		 String password = "";
		 String url = "";
		 String esquema="pv_swb";
		 String nomTabla="APP_USER";
		 Calendar cal = Calendar.getInstance();
		 
		 SimpleDateFormat formatter;
		 formatter = new SimpleDateFormat("yyyyMMdd");
		 CallableStatement proc=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:oracle:thin:@10.7.12.34:1521:orades";
		 	password = "db_swb";
		 	conn = DriverManager.getConnection(url, "db_swb", password);
			proc = conn.prepareCall("{call disableConstraints(?, ?) }");
		    proc.setString(1, esquema);
		    proc.setString(2, nomTabla);
		    proc.execute();		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally
		   {
		      try
		      {
		         proc.close();
		      }
		      catch (SQLException e) {}
		      //conn.close();
		   }
	}
}

