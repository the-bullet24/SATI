package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class EmpresaServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public EmpresaServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	try {
	    if (Constante.SESSION_CADUCA.equals("2")) {
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
		String value = req.getParameter("BtnTrx");
		value = (value == null) ? "Otro" : value.trim();
		Empresa bean;
		if (req.getSession(false).getAttribute("torgdat") != null) {
			bean = ((Empresa) req.getSession(false).getAttribute("torgdat"));
		}
		else {
			bean = new Empresa();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("torgdat", bean);
		}
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.TRANSACCIONES));
		if (value.equalsIgnoreCase("Otro")) {
			//bean.getLogin().setCodact("28");
			bean.getLogin().setAccion("Consulta");
			/**
			if (!bean.getLogin().hasAccesAccion()) {
				bean.getLogin().setCodact("29");
				if (!bean.getLogin().hasAccesAccion()) {
					//					res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
					callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
					return;
				}
			}
			*/
			bean.consultar();
		}

		//
		if (value.equalsIgnoreCase("Agregar")) {
			//bean.getLogin().setCodact("29");
			bean.getLogin().setAccion("Inserción");
			/**
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			*/
			bean.setCodigo(req.getParameter("TxtCodOrg"));
			bean.setNombre(req.getParameter("TxtTxtOrg"));
			bean.setFlgman(req.getParameter("ChkFlgofd"));
			if (!validate(bean)) {
				bean.agregar();
				if (bean.getError().equals("")) {
					bean.setCodigo("");
					bean.setNombre("");
					bean.setFlgman("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Transacciones.jsp");
			callPage(req, res, JspServlet.EMPRESA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Modificar")) {
			//bean.getLogin().setCodact("29");
			bean.getLogin().setAccion("Modificación");
			/**
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			*/
			bean.setCodigo(req.getParameter("TxtCodOrg"));
			bean.setNombre(req.getParameter("TxtTxtOrg"));
			bean.setFlgman(req.getParameter("ChkFlgofd"));
			if (!validate(bean)) {
				bean.modificar();
				if (bean.getError().equals("")) {
					bean.setCodigo("");
					bean.setNombre("");
					bean.setFlgman("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Transacciones.jsp");
			callPage(req, res, JspServlet.EMPRESA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Eliminar")) {
			//bean.getLogin().setCodact("29");
			bean.getLogin().setAccion("Eliminación");
			/**
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			*/
			bean.eliminar(req);
			bean.setCodigo("");
			bean.setNombre("");
			bean.setFlgman("");
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Transacciones.jsp");
			callPage(req, res, JspServlet.EMPRESA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setCodigo(req.getParameter("TxtCodOrg"));
			if (bean.getCodigo().equals(""))
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_EMPRESA));
			else {
				bean.buscar();
			}
			callPage(req, res, JspServlet.EMPRESA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Notificaciones")) {
			bean.setCodigo(req.getParameter("TxtCodOrg"));
			if (!CString.isInteger(bean.getCodigo())) {
				bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_EMPRESA_DEBE_CONTENER_NUMEROS));
			}
			else {
				if (bean.buscar()) {
					res.sendRedirect(JspServlet.NOTIFICACIONES_SERVLET + "?TxtCodigo=" + bean.getCodigo() + "&BtnTrx=Otro");
				}
				else {
					String str = Mensajes.getMessage(Mensajes.CODIGO_DE_EMPRESA_NO_EXISTE);
					int pos = str.indexOf('&');
					bean.setError(str.substring(0, pos) + bean.getCodigo() + str.substring(pos + 1));
					callPage(req, res, JspServlet.EMPRESA_JSP);
				}
				return;
			}
			callPage(req, res, JspServlet.EMPRESA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Usuarios")) {
			bean.setCodigo(req.getParameter("TxtCodOrg"));
			if (!CString.isInteger(bean.getCodigo())) {
				bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_EMPRESA_DEBE_CONTENER_NUMEROS));
			}
			else {
				if (bean.buscar()) {
					res.sendRedirect(JspServlet.USUARIOS_EMPRESA__SERVLET + "?TxtCodigo=" + bean.getCodigo() + "&BtnTrx=Otro");
				}
				else {
					String str = Mensajes.getMessage(Mensajes.CODIGO_DE_EMPRESA_NO_EXISTE);
					int pos = str.indexOf('&');
					bean.setError(str.substring(0, pos) + bean.getCodigo() + str.substring(pos + 1));
					callPage(req, res, JspServlet.EMPRESA_JSP);
				}
				return;
			}
			callPage(req, res, JspServlet.EMPRESA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Nóminas") || value.equalsIgnoreCase("Nominas")) {
			bean.setCodigo(req.getParameter("TxtCodOrg"));
			if (!CString.isInteger(bean.getCodigo())) {
				bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_EMPRESA_DEBE_CONTENER_NUMEROS));
			}
			else {
				if (bean.buscar()) {
					res.sendRedirect(JspServlet.NOMINA__SERVLET + "?TxtCodigo=" + bean.getCodigo() + "&BtnTrx=Otro");
				}
				else {
					String str = Mensajes.getMessage(Mensajes.CODIGO_DE_EMPRESA_NO_EXISTE);
					int pos = str.indexOf('&');
					bean.setError(str.substring(0, pos) + bean.getCodigo() + str.substring(pos + 1));
					callPage(req, res, JspServlet.EMPRESA_JSP);
				}
				return;
			}
			callPage(req, res, JspServlet.EMPRESA_JSP);
			return;
		}
		bean.setError("");
		bean.loadGrid();
		bean.setCodigo("");
		bean.setNombre("");
		bean.setFlgman("");
		callPage(req, res, JspServlet.EMPRESA_JSP);
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
	Empresa bean = (Empresa) obj;
	try {
		bean.setError("");
		if (bean.getCodigo().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_EMPRESA));
			return true;
		}
		if (bean.getNombre().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_DE_EMPRESA));
			return true;
		}
		return false;
	}
	catch (Exception e) {
		throw new Exception("\n... validate() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}