package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class Receipt extends CosapiSoft.SARAWebBanking.JavaServlet {
/**
 * Transfer constructor comment.
 */
public Receipt() {
	super();
}
public void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) {
	try {
		if (!isLoggedIn(req)) {
			try {
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));         
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		CosapiSoft.SARAWebBanking.InicioSesion login = (CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder");
		ReceiptBean rp = new ReceiptBean();
		rp.setReceiptVector(loadData());
		// System.out.println(rp.getReceiptVector());
		rp.setDicvector(new java.util.Vector());
		try {
			JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
			JQuery db = new JQuery(jd.getConnection());
			String query1 = "select coddic, txtdes from "+Constante.ESQUEMA1+".TDICDAT order by coddic";
			db.setQuery(query1);
			db.executeQuery();
			//cur = java.sql.DriverManager.getConnection(login.url, login.userid, login.password);
			//query = cur.createStatement();
			//result = query.executeQuery(query1);
			while (db.getResultSet().next()) {
				java.util.Vector tmp = new java.util.Vector();
				tmp.addElement(db.stringValue(1));
				tmp.addElement(db.stringValue(2));
				rp.getDicvector().addElement(tmp);
			}
			db.close();
			jd.close();
		}
		catch (Exception e3) {
			// System.out.println(e3.getMessage());
		}
		req.getSession(false).setAttribute("rp", rp);
		ErrorMasc error = new ErrorMasc();
		error.setErrorMessage("");
		req.getSession(false).setAttribute("error", error);
		res.sendRedirect("/sarawebbanking/SARAWebBuilder/receipt/receipt.jsp");
	}
	catch (Exception e) {
		e.printStackTrace();
		// System.out.println(e.getMessage());
	}
}
/**
 * This method was created in VisualAge.
 * @return java.util.Vector
 * @param tx java.lang.String
 */
public java.util.Vector loadData() {
	java.util.Vector temp = new java.util.Vector();
	try {
		JDatabase jd = new JDatabase();
		JQuery jq = new JQuery(jd.getConnection());
		String query1 = "select codrec, recdes, recdat from "+Constante.ESQUEMA1+".TRECDAT";
		jq.setQuery(query1);
		jq.executeQuery();
		while (jq.getResultSet().next()) {
			java.util.Vector temp1 = new java.util.Vector();
			temp1.addElement(jq.stringValue(1));
			temp1.addElement(jq.stringValue(2));
			temp1.addElement(jq.stringValue(3));
			temp.addElement(temp1);
		}
		jq.close();
		jd.close();
	}
	catch (Exception e) {
		// System.out.println("ERROR en cargar: " + e.getMessage());
	}
	return temp;
}
public boolean validate(Object obj) throws Exception {
	return false;
}
}