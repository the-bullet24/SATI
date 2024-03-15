/*
 * Creado el 02/04/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.nativejdbc.WebSphereNativeJdbcExtractor;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class DAOAbstract extends SqlMapClientDaoSupport{
	public Connection getConnection(){
		return DataSourceUtils.getConnection(this.getDataSource());
	}

	public Connection getNativeConnection() throws SQLException	{
		//Conexion Nativa para WAS
		WebSphereNativeJdbcExtractor IBMJDBCExtractor = new WebSphereNativeJdbcExtractor();
		return IBMJDBCExtractor.getNativeConnection(this.getConnection());
	}
	
	
	//TODO borrar en certificacion LINUX	
	public  Connection getDatacomConnection() throws Exception	{
		Connection conn = null;
		String cPss = "";
		String url  = "";
		//DESARROLLO
		if (Constante.CONSTANTE_OS.equals("1")){
			//System.out.println("DATACOM Desarrollo");
		    url = "jdbc:datacom://10.7.12.52:3709/ServerName=DCOMSVR,SystemID=BN01,ApplicationID=CCITCP";
		    cPss = "DBATI";
		} else if (Constante.CONSTANTE_OS.equals("2")){
			//System.out.println("DATACOM Certificacion");
		    url = "jdbc:datacom://10.7.106.28:3709/ServerName=DCOMSVR,SystemID=BN02,ApplicationID=CCITCP";
		    cPss = "DBSATI";
		} else if (Constante.CONSTANTE_OS.equals("3")){
		    url = "jdbc:datacom://10.7.12.52:3709/ServerName=DCOMSVR,SystemID=BN01,ApplicationID=CCITCP";
		    cPss = "DBSATI";
		}
		
		
		String driverName = "ca.datacom.jdbc.DatacomJdbcDriver";
		Class.forName(driverName);
		conn = DriverManager.getConnection(url, cPss, cPss);
		return conn;			
	}
}
