package CosapiSoft.SARAWebBanking;

import pe.cosapi.common.Constante;

/**
 * This type was created in VisualAge.
 */
public class DetalleLogBuilderServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public DetalleLogBuilderServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException {
	InicioSesion login = new InicioSesion();
	login.setNameProducto("SARA Web Builder");
	try {
	    if (Constante.SESSION_CADUCA.equals("2")) {
			try {
				//req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));         
				req.getSession(false).setAttribute("error", new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				setAttribute(req, "login", login);
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
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
		String value = req.getParameter("BtnLog");
		LogDeOperaciones bean = ((LogDeOperaciones) req.getSession(false).getAttribute("logBuilder"));
		if (bean == null) {
			res.getWriter().println("LogDeOperaciones no se encuentra en Sesión");
			return;
		}
		bean.loadDetalle();
		//
		if (value.equals("Regresar")) {
			//			res.sendRedirect("../../SARAWebBanking/SARAWebBanking/LogDeOperaciones/LogDeOperacionesBuilder.jsp");
			callPage(req, res, JspServlet.LOG_DE_OPERACIONES_BUILDER_JSP);
			return;
		}
		//		res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/LogDeOperaciones/DetalleLogBuilder.jsp");
		callPage(req, res, JspServlet.DETALLE_LOG_DE_OPERACIONES_BUILDER_JSP);
	}
	catch (Exception e) {
		try {
			setAttribute(req, "error", new Exception(Mensajes.ERROR_GENERAL));
			setAttribute(req, "login", login);
			callPage(req, res, JspServlet.ERROR_JSP);
		}
		catch (javax.servlet.ServletException se) {
			res.getWriter().println(se.getMessage());
		}
	}
}
public boolean validate(Object obj) throws Exception {
	return false;
}
}