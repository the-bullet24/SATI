package pe.cosapi.common;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.nativejdbc.WebSphereNativeJdbcExtractor;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class DAOAbstract extends SqlMapClientDaoSupport{
	
	public Connection getConnection(){
		return DataSourceUtils.getConnection(this.getDataSource());
	}

	public Connection getNativeConnection() throws SQLException	{
		//Conexion Nativa para WAS
		WebSphereNativeJdbcExtractor IBMJDBCExtractor = new WebSphereNativeJdbcExtractor();
		return IBMJDBCExtractor.getNativeConnection(this.getConnection());
	}
}
