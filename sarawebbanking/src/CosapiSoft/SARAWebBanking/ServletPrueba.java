package CosapiSoft.SARAWebBanking;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.cosapi.common.Constante;

/**
 * @version 	1.0
 * @author
 */
public class ServletPrueba extends HttpServlet {
	/**
	* Devuelve la conexión
	*/
	public Connection getConnection() throws SQLException {
		/*try {
			if (connection == null)
				open();
		} catch (SQLException e) {
			this.errorMethod("getConnection()");
			this.error += "\n" + e.getMessage();
			throw (new SQLException(this.getError()));
		}*/
		try {
			java.util.Properties parms = new java.util.Properties();
			//parms.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");

			//javax.naming.Context ctx = new javax.naming.InitialContext(parms);
			javax.naming.Context ctx = new javax.naming.InitialContext();

			javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup("jdbc/dbsara10");

			return ds.getConnection();

		} catch (Exception e) {
			throw (new SQLException(e.getMessage()));
		}
	}

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		performTask(req, resp);
	}

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		performTask(req, resp);

	}
	public String executeQuery(String query) {
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		String res = null;
		try 
		{
			c = getConnection();
			s = c.createStatement();
			r = s.executeQuery(query.replace('¦',' '));
			ResultSetMetaData rm = r.getMetaData();
			String tmp = "";
			StringBuffer stb = new StringBuffer("");
			while (r.next()) 
			{
				for (int i = 1; i <= rm.getColumnCount(); i++) 
				{
					tmp = r.getString(i);
					if (tmp == null || tmp.equals(""))
					{
						stb.append(" ^");
					}
					else
					{
						stb.append(tmp + "^");
					}

				}
				stb.append("@");

			}
			res = stb.toString();
		} 
		catch (Exception e) 
		{
			// System.out.println(e.getMessage());
		} 
		finally 
		{
			try
			{
			c.close();
			}
			catch(Exception ew){}
			
		}
		return res;
	}
	public String executeQuery2(String query) {
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		String res = null;
		try {
			c = getConnection();
			s = c.createStatement();
			r = s.executeQuery(query.replace('¦',' '));
			ResultSetMetaData rm = r.getMetaData();
			String tmp = "";
			StringBuffer stb = new StringBuffer("");
			while (r.next()) {
				for (int i = 1; i <= rm.getColumnCount(); i++) {
					tmp = r.getString(i);
					if (tmp == null || tmp.equals(""))
						stb.append(" ^");
					else
						stb.append(tmp + "^");

				}
				stb.append("|");

			}
			res = stb.toString();
		} catch (Exception e) {
			// System.out.println(e.getMessage());
		} finally {
			try{
			c.close();
			}
			catch(Exception ew){}
		}
		return res;
	}
	public byte[] image(String query) {
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		String res = null;
		//byte tmp[] = null;
		byte[] tmp = null;
		try {
			c = getConnection();
			s = c.createStatement();
			r = s.executeQuery(query.replace('¦',' '));
			ResultSetMetaData rm = r.getMetaData();
			StringBuffer stb = new StringBuffer("");
			r.next();
			tmp = r.getBytes(1);
			/*if (tmp == null || tmp.equals(""))
			stb.append(" ^");
			else
			stb.append(tmp + "^");*/


			res = new String(tmp);
			// System.out.println("******************");
			// System.out.println(res);
			// System.out.println(res.length());
			// System.out.println("******************");
		} catch (Exception e) {
			// System.out.println(e.getMessage());
		} finally {
		}
		return tmp;
	}
	public String executeInsUpd(String query) {
		JDatabase jd = null;
		JQuery db = null;
		String res = "";
		try {
			jd = new JDatabase();
			db = new JQuery(jd.getConnection());
			db.setQuery(query.replace('¥', ' '));
			db.executeUpdate();
			return "";
		} catch (Exception e) {
			// System.out.println(e.getMessage());
			return e.getMessage();
		} finally {
			try {
				db.close();
				jd.close();
			} catch (Exception e1) {
			}
		}
	}

	public String insertLog(String query1, String query2) {
		Connection c = null;
		ResultSet r = null;
		String numlog = "";
		String res = "";
		String query = "";
		CString cs = new CString();
		try {
			c = getConnection();
			c.setTransactionIsolation(c.TRANSACTION_SERIALIZABLE);
			Statement s = c.createStatement();
			r = s.executeQuery("select count(numlog) from "+Constante.ESQUEMA2+".tjoutra");
			if (r.next())
				numlog = r.getString(1);
			numlog = Long.toString(Long.valueOf(numlog).longValue() + 1);
			if (numlog.length() == 1)
				numlog = "0000" + numlog;
			if (numlog.length() == 2)
				numlog = "000" + numlog;
			if (numlog.length() == 3)
				numlog = "00" + numlog;
			if (numlog.length() == 4)
				numlog = "0" + numlog;

			query = query1.replace('¦', ' ');
			// System.out.println(cs.replaceString(query, "<numlog>", numlog));
			s.executeUpdate(cs.replaceString(query, "<numlog>", numlog));
			query = query2.replace('¦', ' ');
			// System.out.println(cs.replaceString(query, "<numlog>", numlog));
			s.executeUpdate(cs.replaceString(query, "<numlog>", numlog));
			c.commit();
			return numlog;
		} catch (Exception e) {
			try {
				c.rollback();
			} catch (Exception e1) {
				// System.out.println(e1.getMessage());
				return e1.getMessage();
			}
			// System.out.println(e.getMessage());
			return e.getMessage();
		} finally {
			try {
				c.close();
				r.close();
			} catch (Exception e1) {
			}
		}
	}
	//@01 El log será por usuario
	//public String numlog() {
	public String numlog(String codUser){
		
		String numlog="";
		String res = "";
		Connection c = null;
		ResultSet r = null;
		try {
			c = getConnection();
			c.setTransactionIsolation(c.TRANSACTION_SERIALIZABLE);
			Statement s = c.createStatement();
			//@01 D1
			//r = s.executeQuery("select count(numlog)+1 from tjoutra");
			//@01 A1
			r = s.executeQuery("select coalesce(char(int(max(numlog))+1),'1') from "+Constante.ESQUEMA2+".tjoutra where codusr='" + codUser+"'");
			//Transaction.
			if (r.next())
				//@01 D1 numlog = r.getString(1);
				//@01 A1
				numlog = r.getString(1).trim();
			numlog = Long.toString(Long.valueOf(numlog).longValue());
			/*@01 D...
			if (numlog.length() == 1)
				numlog = "00" + numlog;
			if (numlog.length() == 2)
				numlog = "0" + numlog;
			*/
			//@01 A1
			numlog = "00000" + numlog;
			numlog = numlog.substring(numlog.length()- 5);
			
			//@01 D1
			//s.executeUpdate("insert into tjoutra (numlog) values ('"+numlog+"')");
			//@01 A1
			s.executeUpdate("insert into "+Constante.ESQUEMA2+".tjoutra (numlog,codusr,idetrapro) values ('"+numlog+"', '"+codUser+"','"+Constante.ESTADO_CANCELADA +"')");
			// System.out.println("xxx3");
			c.commit();
			return numlog;
		} catch (Exception e) {
			try {
				c.rollback();
			} catch (Exception e1) {
				// System.out.println(e1.getMessage());
				return e1.getMessage();
			}
			// System.out.println(e.getMessage());
			return e.getMessage();
		} finally {
			try {
				c.close();
				r.close();
			} catch (Exception e1) {
			}
		}
	}
	public String insertLog2(String numlog,String query1, String query2) {
		String res = "";
		String query = "";
		Connection c = null;
		CString cs = new CString();
		try {
			c = getConnection();
			c.setTransactionIsolation(c.TRANSACTION_SERIALIZABLE);
			Statement s = c.createStatement();
			/*r = s.executeQuery("select count(numlog)+1 from tjoutra");
			if (r.next())
				numlog = r.getString(1);
			numlog = Long.toString(Long.valueOf(numlog).longValue() + 1);
			if (numlog.length() == 1)
				numlog = "0000" + numlog;
			if (numlog.length() == 2)
				numlog = "000" + numlog;
			if (numlog.length() == 3)
				numlog = "00" + numlog;
			if (numlog.length() == 4)
				numlog = "0" + numlog;
			*/
			query = "delete from "+Constante.ESQUEMA2+".tjoutra where numlog='"+numlog+"'";
			//System.out.println(cs.replaceString(query, "<numlog>", numlog));
			s.executeUpdate(query);
			query = query1.replace('¦', ' ');
			//System.out.println(cs.replaceString(query, "<numlog>", numlog));
			s.executeUpdate(query);
			query = query2.replace('¦', ' ');
			//System.out.println(cs.replaceString(query, "<numlog>", numlog));
			s.executeUpdate(query);
			c.commit();
			return numlog;
		} catch (Exception e) {
			try {
				c.rollback();
			} catch (Exception e1) {
				// System.out.println(e1.getMessage());
				return e1.getMessage();
			}
			// System.out.println(e.getMessage());
			return e.getMessage();
		} finally {
			try {
				c.close();
				//r.close();
			} catch (Exception e1) {
			}
		}
	}
	
	public void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//@01 El numero de log será por usuario
		String param = req.getParameter("param");
		if (param.equals("executeInsUpd"))
			resp.getWriter().print(executeInsUpd(req.getParameter("query")));
		if (param.equals("insertLog2"))
			resp.getWriter().print(insertLog2(req.getParameter("numlog"),req.getParameter("query1"), req.getParameter("query2")));
		if (param.equals("numlog"))
			//@01 D1
			//resp.getWriter().print(numlog());
			//@01 A1
			resp.getWriter().print(numlog(req.getParameter("usr")));
		if (param.equals("executeQuery"))
			resp.getWriter().print(executeQuery(req.getParameter("query")));
		if (param.equals("executeQuery2"))
			resp.getWriter().print(executeQuery2(req.getParameter("query")));
		if (param.equals("image"))
		{
			byte[] t=image(req.getParameter("query"));
			resp.setContentType("image/jpeg");
			resp.getOutputStream().write(t);			
		}
		if (param.equals("executeSingleSQLStatement")) //Ejecuta 2 querys dentro de transaccion de BD
			resp.getWriter().print(executeSingleSQLStatement(req.getParameter("query1")));
		
		if (param.equals("executeDoubleSQLStatement")) //Ejecuta 2 querys dentro de transaccion de BD
			resp.getWriter().print(executeDoubleSQLStatement(req.getParameter("query1"), req.getParameter("query2")));
	}

	public String executeSingleSQLStatement(String query1) {
		String res = "";
		String query = "";
		Connection c = null;
		CString cs = new CString();
		try {
			c = getConnection();
			//c.setTransactionIsolation(c.TRANSACTION_SERIALIZABLE);
			Statement s = c.createStatement();
			query = query1.replace('¦', ' ');
			s.executeUpdate(query);
			return "";
		} catch (Exception e) {
			// System.out.println(e.getMessage());
			return e.getMessage();
		} finally {
			try {
				c.close();
			} catch (Exception e1) {
			}
		}
	}

	
	public String executeDoubleSQLStatement(String query1, String query2) {
		String res = "";
		String query = "";
		Connection c = null;
		CString cs = new CString();
		try {
			c = getConnection();
			c.setTransactionIsolation(c.TRANSACTION_SERIALIZABLE);
			Statement s = c.createStatement();
			query = query1.replace('¦', ' ');
			s.executeUpdate(query);
			query = query2.replace('¦', ' ');
			s.executeUpdate(query);
			c.commit();
			return "";
		} catch (Exception e) {
			try {
				c.rollback();
			} catch (Exception e1) {
				// System.out.println(e1.getMessage());
				return e1.getMessage();
			}
			// System.out.println(e.getMessage());
			return e.getMessage();
		} finally {
			try {
				c.close();
			} catch (Exception e1) {
			}
		}
	}

	
	
}