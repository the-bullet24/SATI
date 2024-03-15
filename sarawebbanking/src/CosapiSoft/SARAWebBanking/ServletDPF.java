package CosapiSoft.SARAWebBanking;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.naming.Context;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 	1.0
 * @author
 */
public class ServletDPF extends HttpServlet {
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
public String add(String numacc, String branch, String typdoc, String numdoc, String numplz, String plz, String intr, String emi, String PInt, String adic, String cur, String amount, String date) throws Exception {
	Connection c=null;
	Statement s=null;
	try {
		c=getConnection();
		c.setTransactionIsolation(c.TRANSACTION_SERIALIZABLE);
		s=c.createStatement();
		String q = "insert into taccdat values ('" + numacc + "','" + numdoc + "','" + typdoc + "','" + branch + "','','" + cur + "','4','','2',0,0,0,0,0,0,'01','','" + date + "')";
		s.executeQuery(q);
		q = "insert into taccplz values ('" + numacc + "','" + plz + numplz + "','1'," + intr + ",'" + PInt + "','" + emi + "','" + adic + "'," + amount + ",0,0,'','','',0,'')";
		s.executeQuery(q);
		c.commit();
		return "";
	}
	catch (Exception e) {
		e.printStackTrace();
		//throw new Exception("\n... getDataOrganization(" + mascVector + ", " + global + ") -> " + e.getMessage());
		c.rollback();
		return e.getMessage();
	}
	finally{
		try{
		c.close();
		}
		catch(Exception e2){}
	}
}

	public void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url=req.getQueryString();
		if (url.indexOf('&')>0) url=url.substring(url.indexOf('&')+1);
		StringTokenizer st=new StringTokenizer(url,"&");
		Class paratypes[]=null;
		Object parametros[]=null;
		Vector v=new Vector(); 
		while (st.hasMoreElements())
		{
			StringTokenizer st1=new StringTokenizer(st.nextElement().toString(),"=");
			st1.nextElement();
			v.addElement(st1.nextElement());
			
		}
		paratypes=new Class[v.size()];
		parametros=new Object[v.size()];
		for (int i=0;i<v.size();i++){
			parametros[i]=v.elementAt(i).toString();
			paratypes[i]="".getClass();
		}
		String param = req.getParameter("param");
		try{
			String res=this.executeMethod(this.getClass().getName(),param,paratypes,parametros);
			resp.getWriter().print(res);			
		}
		catch(Exception e){
			// System.out.println(e.getMessage());
		}
		/*if (param.equals("insertLog"))
			resp.getWriter().print(add(req.getParameter("query1"), req.getParameter("query2")));*/
	}
	public static String executeMethod(String clas, String method, Class[] paratypes, Object[] parametros) throws Exception {
		try {
			Object retobj = "";
			// Actualizado por Alexander Bonilla D. 15-07-2000
			Class cls = null;
			if (cls == null) {
				cls = Class.forName(clas); // 1ra forma
			}
			// Fin de Actualización
			java.lang.reflect.Method meth = cls.getMethod(method, paratypes);
			retobj = meth.invoke(cls.newInstance(), parametros);
			return retobj.toString().trim();
		} catch (Exception e1) {
			throw new Exception("\n... executeMethod() -> " + e1.getMessage());
		}
	}
public String renovate(String date,String Acc, String NumPlz, String Plz, String Amo, java.util.Vector mascVector, java.util.Vector global) throws Exception {
	Connection c=null;
	Statement s=null;
	try {
		c=getConnection();
		c.setTransactionIsolation(c.TRANSACTION_SERIALIZABLE);
		s=c.createStatement();
		StringBuffer q = new StringBuffer("update taccplz set amoini=");
		q.append(Amo);
		q.append(", txtplz='");
		q.append(Plz);
		q.append(NumPlz);
		q.append("' where numacc='");
		q.append(Acc);
		q.append("'");
		s.executeUpdate(q.toString());
		q = new StringBuffer("update taccdat set datopn='");
		q.append(date);
		q.append("' where numacc='");
		q.append(Acc);
		q.append("'");
		s.executeUpdate(q.toString());
		c.commit();
		return "";
	}
	catch (Exception e) {
		c.rollback();
		return e.getMessage();
	}
	finally{
		try{
			c.close();
		}
		catch(Exception e3){
		}
	}
}

}