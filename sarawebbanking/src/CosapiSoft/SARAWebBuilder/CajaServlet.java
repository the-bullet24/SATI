package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class CajaServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public CajaServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	try {
		if (!isLoggedIn(req)) {
			try {
				//req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));         
				req.getSession(false).setAttribute("error", new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		Caja bean;
		if (req.getSession(false).getAttribute("tcajdat") != null) {
			bean = ((Caja) req.getSession(false).getAttribute("tcajdat"));
		}
		else {
			bean = new Caja();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder"));
			req.getSession(false).setAttribute("tcajdat", bean);
		}

		//
		String value = (req.getParameter("BtnCaj") == null) ? "Otro" : req.getParameter("BtnCaj").trim();
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.CAJA));
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
			bean.setCodcaj(req.getParameter("TxtCodcaj"));
			bean.setNomcaj(req.getParameter("TxtNomcaj"));
			bean.setCodcur(req.getParameter("TxtCodcur"));
			if (req.getParameter("TxtCodusr")==null)
				bean.setCodusr("");
			else
				bean.setCodusr(req.getParameter("TxtCodusr"));
			bean.setCodbra(req.getParameter("TxtCodbra"));
			bean.setCodlim(req.getParameter("cboCodlim"));
			if (req.getParameter("chkFlgblk")==null)
				bean.setFlgblk("0");
			else
				bean.setFlgblk("1");
			if (!validate(bean)) {
				bean.insert();
				if (bean.getError().equals("")) {
					bean.setCodcaj("");
					bean.setNomcaj("");
					bean.setCodcur("");
					bean.setCodusr("");
					bean.setCodbra("");
					bean.setCodlim("");
					bean.setFlgblk("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Clases.jsp");
			callPage(req, res, JspServlet.CAJAS_JSP);
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
			bean.setCodcaj(req.getParameter("TxtCodcaj"));
			bean.setNomcaj(req.getParameter("TxtNomcaj"));
			bean.setCodcur(req.getParameter("TxtCodcur"));
			if (req.getParameter("TxtCodusr")==null)
				bean.setCodusr("");
			else
				bean.setCodusr(req.getParameter("TxtCodusr"));
			bean.setCodbra(req.getParameter("TxtCodbra"));
			bean.setCodlim(req.getParameter("cboCodlim"));
			if (req.getParameter("chkFlgblk")==null)
				bean.setFlgblk("0");
			else
				bean.setFlgblk("1");
			if (!validate(bean)) {
				bean.update();
				if (bean.getError().equals("")) {
					bean.setCodcaj("");
					bean.setNomcaj("");
					bean.setCodcur("");
					bean.setCodusr("");
					bean.setCodbra("");
					bean.setCodlim("");
					bean.setFlgblk("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Clases.jsp");
			callPage(req, res, JspServlet.CAJAS_JSP);
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
			bean.setCodcaj("");
			bean.setNomcaj("");
			bean.setCodcur("");
			bean.setCodusr("");
			bean.setCodbra("");
			bean.setCodlim("");
			bean.setFlgblk("");
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Clases.jsp");
			callPage(req, res, JspServlet.CAJAS_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setCodcaj(req.getParameter("TxtCodcaj"));
			if (bean.getCodcaj().equals(""))
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_CAJA));
			else {
				bean.search();
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Clases.jsp");
			callPage(req, res, JspServlet.CAJAS_JSP);
			return;
		}
		bean.setError("");
		bean.loadGrid();
		bean.setCodcaj("");
		bean.setNomcaj("");
		bean.setCodcur("");
		bean.setCodusr("");
		bean.setCodbra("");
		bean.setCodlim("");
		bean.setFlgblk("");
		//		res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Clases.jsp");
		callPage(req, res, JspServlet.CAJAS_JSP);
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
		Caja bean = (Caja) obj;
		bean.setError("");
		if (bean.getCodcaj().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_CLASE));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getCodcaj())) {
			bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_CAJA_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (bean.getNomcaj().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_DE_CAJA));
			return true;
		}
		if (bean.getCodcur().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_MONEDA_CAJA));
			return true;
		}
		if (bean.getCodusr().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_USUARIO_CAJA));
			return true;
		}
		if (bean.getCodbra().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_AGENCIA_CAJA));
			return true;
		}
		return false;
	}
	catch (Exception e) {
		throw new Exception("validate() -> ClasesServlet " + e.getMessage());
	}
}
}