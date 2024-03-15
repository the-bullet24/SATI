package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class NominaDetalleServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public NominaDetalleServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	try {
		if (!isLoggedIn(req)) {
			try {
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));    ;     
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		String value = req.getParameter("BtnTrx");
		value = (value == null) ? "Otro" : value.trim();
		NominaDetalle bean;
		if (req.getSession(false).getAttribute("tlstdet") != null) {
			bean = ((NominaDetalle) req.getSession(false).getAttribute("tlstdet"));
		}
		else {
			bean = new NominaDetalle();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("tlstdet", bean);
		}
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.TRANSACCIONES));
		if (value.equalsIgnoreCase("Otro")) {
			bean.getLogin().setCodact("28");
			bean.getLogin().setAccion("Consulta");
			bean.setCodorg(req.getParameter("TxtCodorg"));
			bean.setCodlst(req.getParameter("TxtCodlst"));
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
			bean.setCodigo(req.getParameter("TxtCodigo"));
			bean.setNombre(req.getParameter("TxtNombre"));
			bean.setApaterno(req.getParameter("TxtApaterno"));
			bean.setAmaterno(req.getParameter("TxtAmaterno"));
			bean.setMonto(req.getParameter("TxtMonto"));
			bean.setCuenta(req.getParameter("TxtCuenta"));
			if (req.getParameter("ChkActivo")==null) bean.setActivo("0");
				else bean.setActivo("1");
			if (!validate(bean)) {
				bean.agregar();
				if (bean.getError().equals("")) {
					bean.setCodigo("");
					bean.setNombre("");
					bean.setApaterno("");
					bean.setAmaterno("");
					bean.setMonto("");
					bean.setActivo("");
					bean.setCuenta("");
				}
			}
			callPage(req, res, JspServlet.NOMINA_DETALLE_JSP);
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
			bean.setCodigo(req.getParameter("TxtCodigo"));
			bean.setNombre(req.getParameter("TxtNombre"));
			bean.setApaterno(req.getParameter("TxtApaterno"));
			bean.setAmaterno(req.getParameter("TxtAmaterno"));
			bean.setMonto(req.getParameter("TxtMonto"));
			bean.setCuenta(req.getParameter("TxtCuenta"));
			if (req.getParameter("ChkActivo")==null) bean.setActivo("0");
				else bean.setActivo("1");
			if (!validate(bean)) {
				bean.modificar();
				if (bean.getError().equals("")) {
					bean.setCodigo("");
					bean.setNombre("");
					bean.setApaterno("");
					bean.setAmaterno("");
					bean.setMonto("");
					bean.setActivo("");
					bean.setCuenta("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Transacciones.jsp");
			callPage(req, res, JspServlet.NOMINA_DETALLE_JSP);
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
			bean.setCodigo("");
			bean.setNombre("");
			bean.setApaterno("");
			bean.setAmaterno("");
			bean.setMonto("");
			bean.setActivo("");
			bean.setCuenta("");
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Transacciones.jsp");
			callPage(req, res, JspServlet.NOMINA_DETALLE_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setCodigo(req.getParameter("TxtCodigo"));
			if (bean.getCodigo().equals(""))
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_EMPLEADO));
			else {
				bean.buscar();
			}
			callPage(req, res, JspServlet.NOMINA_DETALLE_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Regresar")) {
			callPage(req, res, JspServlet.NOMINA_JSP);
			return;
		}
		bean.setError("");
		bean.loadGrid();
		bean.setCodigo("");
		bean.setNombre("");
		bean.setApaterno("");
		bean.setAmaterno("");
		bean.setMonto("");
		bean.setActivo("");
		bean.setCuenta("");
		callPage(req, res, JspServlet.NOMINA_DETALLE_JSP);
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
	NominaDetalle bean = (NominaDetalle) obj;
	try {
		bean.setError("");
		if (bean.getCodigo().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_EMPLEADO));
			return true;
		}
		if (bean.getNombre().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_DE_EMPLEADO));
			return true;
		}
		if (bean.getApaterno().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_APELLIDO_PATERNO_DE_EMPLEADO));
			return true;
		}
		if (bean.getAmaterno().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_APELLIDO_MATERNO_DE_EMPLEADO));
			return true;
		}
		if (bean.getMonto().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_MONTO_DE_EMPLEADO));
			return true;
		}
		if (bean.getCuenta().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CUENTA_DE_EMPLEADO));
			return true;
		}
		return false;
	}
	catch (Exception e) {
		throw new Exception("\n... validate() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}