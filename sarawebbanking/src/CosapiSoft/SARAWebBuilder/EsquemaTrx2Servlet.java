package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class EsquemaTrx2Servlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public EsquemaTrx2Servlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	InicioSesion login = new InicioSesion();
	login.setNameProducto("SARA Web Builder");
	try {
		if (!isLoggedIn(req)) {
			try {
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));         
				setAttribute(req, "login", login);
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		Esquema1 bean = (Esquema1) req.getSession(false).getAttribute("tschtra");
		bean.loadGridClases();
		String value = req.getParameter("BtnEsq");
		value = (value == null) ? "Otro" : value.trim();
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.ASIG_DE_CLASES_ESQUEMA));
		if (value.equalsIgnoreCase("Regresar")) {
			Transacciones.pop();
			String back = Transacciones.pop();
			back = (back.equals("")) ? JspServlet.ESQUEMA_JSP : back;
			callPage(req, res, back);
			return;
		}
		Transacciones.push(JspServlet.ESQUEMA_TRX2_JSP);
		callPage(req, res, JspServlet.ESQUEMA_TRX2_JSP);
	}
	catch (Exception e) {
		try {
			e.printStackTrace();
			setAttribute(req, "error", new Exception(Mensajes.ERROR_GENERAL));
			setAttribute(req, "login", login);
			callPage(req, res, JspServlet.ERROR_JSP);
		}
		catch (Exception se) {
			res.getWriter().println(se.getMessage());
		}
	}
}
public boolean validate(Object obj) throws Exception {
	return false;
}
}