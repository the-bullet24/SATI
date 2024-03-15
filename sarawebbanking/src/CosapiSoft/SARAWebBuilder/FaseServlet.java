package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class FaseServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public FaseServlet() {
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
		//
		/*bean.getLogin().setCodact("37");
		bean.getLogin().setAccion("");
		if (!bean.getLogin().hasAccesAccion()) {
		callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
		return;
		}*/
		//
		String value = req.getParameter("BtnEsq");
		value = (value == null) ? "Otro" : value.trim();
		bean.setError("");
		bean.setGridArgs(null);
		if (value.equalsIgnoreCase("Otro")) {
			bean.consult();
			bean.setGrid(null);
			bean.loadGrid();
		}
		if (value.equalsIgnoreCase("Esquema")) {
			bean.setCodtra(req.getParameter("TxtCodtra"));
			bean.setTxttra(req.getParameter("TxtTxttra").replace('_', ' '));
			bean.consult();
			bean.setFunction("ADD_FASE");
			Transacciones.push(JspServlet.FASE_TRX0_JSP);
			if (!bean.hasTrxEsquema(bean.getCodtra())) {
				res.sendRedirect(JspServlet.FASE_TRX1_1_SERVLET + "?BtnEsq=Otro");
				return;
			}
			bean.setGrid(null);
			bean.loadGrid();
		}
		if (value.equalsIgnoreCase("Regresar")) {
			String back = Transacciones.pop();
			back = (back.equals("")) ? JspServlet.FASE_TRX1_1_JSP : back;
			callPage(req, res, back);
			return;
		}
		if (value.equalsIgnoreCase("Agregar Fase")) {
			bean.setCodfas(bean.nextCodfas());
			bean.setFunction("ADD_FASE");
			Transacciones.push(JspServlet.FASE_JSP);
			callPage(req, res, JspServlet.FASE_TRX1_2_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Insertar Fase")) {
			bean.setCodfas("");
			bean.setFunction("INSERT_FASE");
			Transacciones.push(JspServlet.FASE_JSP);
			callPage(req, res, JspServlet.FASE_TRX1_1_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Agregar Alternativa")) {
			bean.setCodfas(req.getParameter("TxtCodfas"));
			bean.setAlt(req.getParameter("TxtAlt"));
			bean.setFunction("ADD_ALTERNATIVA");
			if (bean.getAlt().equals("1")) {
				bean.setCodalt(bean.nextCodalt(bean.getCodfas()));
				Transacciones.push(JspServlet.FASE_JSP);
				callPage(req, res, JspServlet.FASE_TRX2_JSP);
				return;
			}
			else {
				bean.setError("No se puede Agregar Alternativa(s) porque la Fase " + bean.getCodfas() + " no es Alternativa");
			}
		}
		if (value.equalsIgnoreCase("Insertar Alternativa")) {
			bean.setCodfas(req.getParameter("TxtCodfas"));
			bean.setAlt(req.getParameter("TxtAlt"));
			bean.setFunction("INSERT_ALTERNATIVA");
			if (bean.getAlt().equals("1")) {
				bean.setCodalt("");
				Transacciones.push(JspServlet.FASE_JSP);
				callPage(req, res, JspServlet.FASE_TRX1_3_JSP);
				return;
			}
			else {
				bean.setError("No se puede Insertar Alternativa(s) porque la Fase " + bean.getCodfas() + " no es Alternativa");
			}
		}
		if (value.equalsIgnoreCase("Eliminar")) {
			bean.delete(req);
		}
		if (value.equalsIgnoreCase("Fase")) {
			bean.setCodfas(req.getParameter("TxtCodfas"));
			bean.setAlt(req.getParameter("TxtAlt"));
		}
		callPage(req, res, JspServlet.FASE_JSP);
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
	return false;
}
}