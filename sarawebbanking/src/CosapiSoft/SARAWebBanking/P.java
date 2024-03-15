package CosapiSoft.SARAWebBanking;
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
public class P extends HttpServlet {
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
			parms.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");

			javax.naming.Context ctx = new javax.naming.InitialContext(parms);

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
				stb.append("@");

			}
			res = stb.toString();
		} catch (Exception e) {
			// System.out.println(e.getMessage());
		} finally {
		}
		return res;
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

	public void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("text1");
		String a="Ñ";
		String b=new String (a.getBytes(),"ISO8859_1");
		// System.out.println(b);
		//System.out.println("-"+(char)63);
		//System.out.println((int)a.charAt(0));
		//System.out.println(param);
		//System.out.println(new String(param.getBytes(),"ISO-8859-15"));

/*		if (param.equals("executeInsUpd"))
			resp.getWriter().print(executeInsUpd(req.getParameter("query")));
		if (param.equals("insertLog"))
			resp.getWriter().print(insertLog(req.getParameter("query1"), req.getParameter("query2")));
		if (param.equals("executeQuery"))
			resp.getWriter().print(executeQuery(req.getParameter("query")));
			*/
	}
}