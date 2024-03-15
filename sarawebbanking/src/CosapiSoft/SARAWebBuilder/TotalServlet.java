package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class TotalServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public TotalServlet() {
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
		Total bean;
		if (req.getSession(false).getAttribute("ttotdat") != null) {
			bean = ((Total) req.getSession(false).getAttribute("ttotdat"));
		}
		else {
			bean = new Total();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder"));
			req.getSession(false).setAttribute("ttotdat", bean);
		}

		//
		String value = (req.getParameter("BtnTot") == null) ? "Otro" : req.getParameter("BtnTot").trim();
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.TOTAL));
		if (value.equalsIgnoreCase("Otro")) {
			bean.getLogin().setCodact("32");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				bean.getLogin().setCodact("33");
				if (!bean.getLogin().hasAccesAccion()) {
					//					res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
					callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
					return;
				}
			}
			bean.consult();
		}
		if (value.equalsIgnoreCase("Agregar")) {
			bean.getLogin().setCodact("33");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCodtot(req.getParameter("TxtCodtot"));
			bean.setNomtot(req.getParameter("TxtNomtot"));
			if (!validate(bean)) {
				bean.insert();
				if (bean.getError().equals("")) {
					bean.setCodtot("");
					bean.setNomtot("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Total.jsp");
			callPage(req, res, JspServlet.TOTAL_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Modificar")) {
			bean.getLogin().setCodact("33");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCodtot(req.getParameter("TxtCodtot"));
			bean.setNomtot(req.getParameter("TxtNomtot"));
			if (!validate(bean)) {
				bean.update();
				if (bean.getError().equals("")) {
					bean.setCodtot("");
					bean.setNomtot("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Total.jsp");
			callPage(req, res, JspServlet.TOTAL_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Eliminar")) {
			bean.getLogin().setCodact("33");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.delete(req);
			bean.setCodtot("");
			bean.setNomtot("");
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Total.jsp");
			callPage(req, res, JspServlet.TOTAL_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setCodtot(req.getParameter("TxtCodtot"));
			if (bean.getCodtot().equals(""))
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_TOTAL));
			else {
				bean.search();
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Total.jsp");
			callPage(req, res, JspServlet.TOTAL_JSP);
			return;
		}
		bean.setError("");
		bean.loadGrid();
		bean.setCodtot("");
		bean.setNomtot("");
		//		res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Total.jsp");
		callPage(req, res, JspServlet.TOTAL_JSP);
	}
	catch (Exception e) {
		try {
			e.printStackTrace();
			setAttribute(req, "error", new Exception(Mensajes.ERROR_GENERAL));
			callPage(req, res, JspServlet.ERROR_JSP);
		}
		catch (javax.servlet.ServletException se) {
			res.getWriter().println(se.getMessage());
		}
	}
}
public boolean validate(Object obj) throws Exception {
	try {
		Total bean = (Total) obj;
		bean.setError("");
		if (bean.getCodtot().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_TOTAL));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getCodtot())) {
			bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_TOTAL_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (bean.getNomtot().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_DE_TOTAL));
			return true;
		}
		return false;
	}
	catch (Exception e) {
		throw new Exception("validate() -> TotalServlet " + e.getMessage());
	}
}
}