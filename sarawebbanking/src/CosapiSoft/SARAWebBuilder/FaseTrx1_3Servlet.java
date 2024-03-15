package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class FaseTrx1_3Servlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public FaseTrx1_3Servlet() {
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
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.CREACION_DE_FASES));
		if (value.equalsIgnoreCase("Regresar")) {
			String back = Transacciones.pop();
			back = (back.equals("")) ? JspServlet.FASE_TRX1_2_JSP : back;
			callPage(req, res, back);
			return;
		}
		if (value.equalsIgnoreCase("Siguiente")) {
			bean.setCodalt(req.getParameter("TxtCodalt").trim());
			if (validate(bean)) {
				callPage(req, res, JspServlet.FASE_TRX1_3_JSP);
				return;
			}
			Transacciones.push(JspServlet.FASE_TRX1_3_JSP);
			res.sendRedirect(JspServlet.FASE_TRX2_SERVLET + "?BtnEsq=Otro");
			return;
		}
		callPage(req, res, JspServlet.FASE_TRX1_3_JSP);
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
	Fase1 bean = (Fase1) obj;
	try {
		bean.setError("");
		if (bean.getCodalt().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_COD_ALTERNATIVA));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getCodalt())) {
			bean.setError(Mensajes.getMessage(Mensajes.COD_ALTERNATIVA_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (bean.getAlt().equals("0") && !bean.getCodalt().equals("00")) {
			bean.setError("El Código de la alternativa debe ser '00'");
			return true;
		}
		if (bean.getAlt().equals("1") && bean.getCodalt().equals("00")) {
			bean.setError(Mensajes.getMessage(Mensajes.COD_ALTERNATIVA_DEBE_MAYOR_A_CERO));
			return true;
		}
		return false;
	}
	catch (Exception e) {
		throw new Exception("\n... validate() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}