package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class FaseTrx1_1Servlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public FaseTrx1_1Servlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
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
		Fase1 bean = (Fase1) req.getSession(false).getAttribute("ttrafas");
		String value = req.getParameter("BtnEsq");
		value = (value == null) ? "Otro" : value.trim();
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.CREACION_DE_FASES));
		if (value.equalsIgnoreCase("Transaccion")) {
			bean.setCodtra(req.getParameter("TxtCodtra"));
			bean.setTxttra(CString.toStringOfHtml(req.getParameter("TxtTxttra").replace('_', ' ')));
			if (bean.hasTrxEsquema(bean.getCodtra())) {
				res.sendRedirect(JspServlet.FASE_SERVLET + "?BtnEsq=Otro");
				return;
			}
		}
		if (value.equalsIgnoreCase("Regresar")) {
			String back = Transacciones.pop();
			back = (back.equals("")) ? JspServlet.FASE_TRX0_JSP : back;
			callPage(req, res, back);
			return;
		}
		if (value.equalsIgnoreCase("Siguiente")) {
			bean.setCodfas(req.getParameter("TxtCodfas").trim());
			if (validate(bean)) {
				callPage(req, res, JspServlet.FASE_TRX1_1_JSP);
				return;
			}
			Transacciones.push(JspServlet.FASE_TRX1_1_JSP);
			res.sendRedirect(JspServlet.FASE_TRX1_2_SERVLET + "?BtnEsq=Otro");
			return;
		}
		callPage(req, res, JspServlet.FASE_TRX1_1_JSP);
	}
	catch (Exception e) {
		try {
			e.printStackTrace();
			setAttribute(req, "error", new Exception(Mensajes.ERROR_GENERAL));
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
		if (bean.getCodfas().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_FASE));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getCodfas())) {
			bean.setError(Mensajes.getMessage(Mensajes.COD_FASE_DEBE_CONTENER_NUMEROS));
			return true;
		}
		int codfas = new Integer(bean.getCodfas()).intValue();
		if (codfas <= 0) {
			bean.setError(Mensajes.getMessage(Mensajes.COD_FASE_DEBE_MAYOR_A_CERO));
			return true;
		}
		return false;
	}
	catch (Exception e) {
		throw new Exception("\n... validate() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}