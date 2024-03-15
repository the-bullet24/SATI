package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class EsquemaTrx1Servlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public EsquemaTrx1Servlet() {
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
		String value = req.getParameter("BtnEsq");
		value = (value == null) ? "Otro" : value.trim();
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.ESQUEMA));
		if (value.equalsIgnoreCase("Regresar")) {
			String back = Transacciones.pop();
			back = (back.equals("")) ? JspServlet.ESQUEMA_JSP : back;
			callPage(req, res, back);
			return;
		}
		if (value.equalsIgnoreCase("Siguiente")) {
			bean.setNumseq(req.getParameter("TxtNumseq").trim());
			if (validate(bean)) {
				callPage(req, res, JspServlet.ESQUEMA_TRX1_JSP);
				return;
			}
			Transacciones.push(JspServlet.ESQUEMA_TRX1_JSP);
			res.sendRedirect(JspServlet.ESQUEMA_TRX2_SERVLET + "?BtnEsq=Otro");
			return;
		}
		callPage(req, res, JspServlet.ESQUEMA_TRX1_JSP);
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
	Esquema1 bean = (Esquema1) obj;
	try {
		bean.setError("");
		if (bean.getNumseq().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NRO_SECUENCIA));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getNumseq())) {
			bean.setError(Mensajes.getMessage(Mensajes.NRO_SECUENCIA_DEBE_CONTENER_NUMEROS));
			return true;
		}
		int tmp = new Integer(bean.getNumseq()).intValue();
		if (tmp < 1) {
			bean.setError(Mensajes.getMessage(Mensajes.NRO_SECUENCIA_DEBE_MAYOR_A_CERO));
			return true;
		}
		return false;
	}
	catch (Exception e) {
		throw new Exception("\n... validate() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}