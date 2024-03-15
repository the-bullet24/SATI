package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class FaseTrx3Servlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public FaseTrx3Servlet() {
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
		Fase1 bean = (Fase1) req.getSession(false).getAttribute("ttrafas");
		String value = req.getParameter("BtnEsq");
		value = (value == null) ? "Otro" : value.trim();
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.ASIG_DE_METODOS_FASE));
		if (value.equalsIgnoreCase("Clase")) {
			bean.setCodcla(req.getParameter("TxtCodcla"));
			bean.setTxtcla(req.getParameter("TxtTxtcla").replace('_', ' '));
			bean.loadGridMetodos();
		}
		if (value.equalsIgnoreCase("Regresar")) {
			Transacciones.pop();
			String back = Transacciones.pop();
			back = (back.equals("")) ? JspServlet.FASE_TRX2_JSP : back;
			callPage(req, res, back);
			return;
		}
		Transacciones.push(JspServlet.FASE_TRX3_JSP);
		callPage(req, res, JspServlet.FASE_TRX3_JSP);
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