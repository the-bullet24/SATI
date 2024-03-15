package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class ClasesServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public ClasesServlet() {
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
		Clases bean;
		if (req.getSession(false).getAttribute("tcladat") != null) {
			bean = ((Clases) req.getSession(false).getAttribute("tcladat"));
		}
		else {
			bean = new Clases();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder"));
			req.getSession(false).setAttribute("tcladat", bean);
		}

		//
		String value = (req.getParameter("BtnCla") == null) ? "Otro" : req.getParameter("BtnCla").trim();
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("MAESTRO DE CLASES");
		if (value.equalsIgnoreCase("Otro")) {
			/**
			bean.getLogin().setCodact("32");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
			*/
				bean.getLogin().setCodact("204");
				bean.getLogin().setAccion("Consulta");
				if (!bean.getLogin().hasAccesAccion()) {
					//					res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
					callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
					return;
				}
			//}
			bean.consult();
		}
		if (value.equalsIgnoreCase("Agregar")) {
			bean.getLogin().setCodact("204");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCodcla(req.getParameter("TxtCodcla"));
			bean.setTxtcla(req.getParameter("TxtTxtcla"));
			bean.setSesnam(req.getParameter("TxtSesnam"));
			if (!validate(bean)) {
				bean.insert();
				if (bean.getError().equals("")) {
					bean.setCodcla("");
					bean.setTxtcla("");
					bean.setSesnam("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Clases.jsp");
			callPage(req, res, JspServlet.CLASES_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Modificar")) {
			bean.getLogin().setCodact("204");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCodcla(req.getParameter("TxtCodcla"));
			bean.setTxtcla(req.getParameter("TxtTxtcla"));
			bean.setSesnam(req.getParameter("TxtSesnam"));
			if (!validate(bean)) {
				bean.update();
				if (bean.getError().equals("")) {
					bean.setCodcla("");
					bean.setTxtcla("");
					bean.setSesnam("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Clases.jsp");
			callPage(req, res, JspServlet.CLASES_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Eliminar")) {
			bean.getLogin().setCodact("204");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.delete(req);
			bean.setCodcla("");
			bean.setTxtcla("");
			bean.setSesnam("");
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Clases.jsp");
			callPage(req, res, JspServlet.CLASES_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setCodcla(req.getParameter("TxtCodcla"));
			if (bean.getCodcla().equals(""))
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_CLASE));
			else {
				bean.search();
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Clases.jsp");
			callPage(req, res, JspServlet.CLASES_JSP);
			return;
		}
		if (value.equals("Asig. de Metodos")) {
			bean.setCodcla(req.getParameter("TxtCodcla"));
			if (bean.getCodcla().equals("")) {
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_CLASE));
			}
			else {
				if (!bean.search()) {
					String str = Mensajes.getMessage(Mensajes.CODIGO_DE_CLASE_NO_EXISTE);
					int pos = str.indexOf('&');
					bean.setError(str.substring(0, pos) + bean.getCodcla() + str.substring(pos + 1));
				}
				else {
					res.sendRedirect(JspServlet.METODOS_SERVLET + "?BtnMet=Otro&TxtCodcla=" + bean.getCodcla());
					return;
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Clases.jsp");
			callPage(req, res, JspServlet.CLASES_JSP);
			return;
		}
		if (value.equals("Asig. de Propiedades")) {
			bean.setCodcla(req.getParameter("TxtCodcla"));
			if (bean.getCodcla().equals("")) {
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_CLASE));
			}
			else {
				if (!bean.search()) {
					String str = Mensajes.getMessage(Mensajes.CODIGO_DE_CLASE_NO_EXISTE);
					int pos = str.indexOf('&');
					bean.setError(str.substring(0, pos) + bean.getCodcla() + str.substring(pos + 1));
				}
				else {
					res.sendRedirect(JspServlet.PROPERTIES_SERVLET + "?BtnPro=Otro&TxtCodcla=" + bean.getCodcla());
					return;
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Clases.jsp");
			callPage(req, res, JspServlet.CLASES_JSP);
			return;
		}
		bean.setError("");
		bean.loadGrid();
		bean.setCodcla("");
		bean.setTxtcla("");
		//		res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Clases.jsp");
		callPage(req, res, JspServlet.CLASES_JSP);
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
		Clases bean = (Clases) obj;
		bean.setError("");
		if (bean.getCodcla().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_CLASE));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getCodcla())) {
			bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_CLASE_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (bean.getTxtcla().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_DESCRIPCION_CLASE));
			return true;
		}
		if (bean.getSesnam().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_CLASE_SESION));
			return true;
		}
		return false;
	}
	catch (Exception e) {
		throw new Exception("validate() -> ClasesServlet " + e.getMessage());
	}
}
}