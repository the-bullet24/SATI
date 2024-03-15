package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class UsuariosEmpresaServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public UsuariosEmpresaServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	try {
	    if (Constante.SESSION_CADUCA.equals("2")) {
			try {
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));         
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
		UsuariosEmpresa bean;
		if (req.getSession(false).getAttribute("tusrorg") != null) {
			bean = ((UsuariosEmpresa) req.getSession(false).getAttribute("tusrorg"));
		}
		else {
			bean = new UsuariosEmpresa();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("tusrorg", bean);
		}
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.TRANSACCIONES));
		if (value.equalsIgnoreCase("Otro")) {
			bean.getLogin().setCodact("28");
			bean.getLogin().setAccion("Consulta");
			bean.setCodorg(req.getParameter("TxtCodigo"));
			if (!bean.getLogin().hasAccesAccion()) {
				bean.getLogin().setCodact("29");
				if (!bean.getLogin().hasAccesAccion()) {
					//					res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
					callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
					return;
				}
			}
			bean.consultar();
		}

		//
		if (value.equalsIgnoreCase("Agregar")) {
			bean.getLogin().setCodact("29");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.setId(req.getParameter("TxtId"));
			bean.setNombre(req.getParameter("TxtNombre"));
			bean.setApaterno(req.getParameter("TxtApaterno"));
			bean.setAmaterno(req.getParameter("TxtAmaterno"));
			bean.setPass(req.getParameter("TxtPass"));
			bean.setEmail(req.getParameter("TxtEmail"));
			if (req.getParameter("ChkSupervisor")==null) bean.setSupervisor("0");
				else bean.setSupervisor("1");
			if (!validate(bean)) {
				bean.agregar();
				if (bean.getError().equals("")) {
					bean.setId("");
					bean.setNombre("");
					bean.setApaterno("");
					bean.setAmaterno("");
					bean.setPass("");
					bean.setSupervisor("");
					bean.setEmail("");
				}
			}
			callPage(req, res, JspServlet.USUARIOS_EMPRESA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Modificar")) {
			bean.getLogin().setCodact("29");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.setId(req.getParameter("TxtId"));
			bean.setNombre(req.getParameter("TxtNombre"));
			bean.setApaterno(req.getParameter("TxtApaterno"));
			bean.setAmaterno(req.getParameter("TxtAmaterno"));
			bean.setPass(req.getParameter("TxtPass"));
			bean.setEmail(req.getParameter("TxtEmail"));
			if (req.getParameter("ChkSupervisor")==null) bean.setSupervisor("0");
				else bean.setSupervisor("1");
			if (!validate(bean)) {
				bean.modificar();
				if (bean.getError().equals("")) {
					bean.setId("");
					bean.setNombre("");
					bean.setApaterno("");
					bean.setAmaterno("");
					bean.setPass("");
					bean.setSupervisor("");
					bean.setEmail("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Transacciones.jsp");
			callPage(req, res, JspServlet.USUARIOS_EMPRESA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Eliminar")) {
			bean.getLogin().setCodact("29");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.eliminar(req);
			bean.setId("");
			bean.setNombre("");
			bean.setApaterno("");
			bean.setAmaterno("");
			bean.setPass("");
			bean.setSupervisor("");
			bean.setEmail("");
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Transacciones.jsp");
			callPage(req, res, JspServlet.USUARIOS_EMPRESA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setId(req.getParameter("TxtId"));
			if (bean.getId().equals(""))
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_USUARIO));
			else {
				bean.buscar();
			}
			callPage(req, res, JspServlet.USUARIOS_EMPRESA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Facultades")) {
			bean.setId(req.getParameter("TxtId"));
			// System.out.println(bean.getId());
				if (bean.buscar()) {
					res.sendRedirect(JspServlet.FACULTAD_CUENTA_SERVLET + "?TxtCodusr=" + bean.getId() + "&TxtCodorg="+bean.getCodorg()+"&BtnTrx=Otro");
				}
				else {
					String str = Mensajes.getMessage(Mensajes.CODIGO_DE_EMPRESA_NO_EXISTE);
					int pos = str.indexOf('&');
					bean.setError(str.substring(0, pos) + bean.getId() + str.substring(pos + 1));
					callPage(req, res, JspServlet.USUARIOS_EMPRESA_JSP);
				}
				return;
		}
		if (value.equalsIgnoreCase("Regresar")) {
			callPage(req, res, JspServlet.EMPRESA_JSP);
			return;
		}
		bean.setError("");
		bean.loadGrid();
		bean.setId("");
		bean.setNombre("");
		bean.setApaterno("");
		bean.setAmaterno("");
		bean.setPass("");
		bean.setSupervisor("");
		bean.setEmail("");
		callPage(req, res, JspServlet.USUARIOS_EMPRESA_JSP);
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
	UsuariosEmpresa bean = (UsuariosEmpresa) obj;
	try {
		bean.setError("");
		if (bean.getId().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_USUARIO));
			return true;
		}
		if (bean.getNombre().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_USUARIO));
			return true;
		}
		if (bean.getApaterno().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_USUARIO));
			return true;
		}
		if (bean.getAmaterno().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_USUARIO));
			return true;
		}
		if (bean.getPass().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_USUARIO));
			return true;
		}
		return false;
	}
	catch (Exception e) {
		throw new Exception("\n... validate() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}