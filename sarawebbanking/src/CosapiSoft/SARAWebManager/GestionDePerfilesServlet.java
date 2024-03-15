package CosapiSoft.SARAWebManager;

import CosapiSoft.SARAWebBanking.*;
public class GestionDePerfilesServlet extends JavaServlet {


public GestionDePerfilesServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	try {
		if (!isLoggedIn(req)) {
			try {
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		String value = req.getParameter("BtnPrf");
		value = (value == null) ? "Otro" : value.trim();
		GestionDePerfiles bean;
		GestionDeUsuarios user=new GestionDeUsuarios();
		if (req.getSession(false).getAttribute("tprfdat") != null) {
			bean = (GestionDePerfiles) (req.getSession(false).getAttribute("tprfdat"));
		}
		else {
			bean = new GestionDePerfiles();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("tprfdat", bean);
		}

		//
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.GESTION_DE_PERFILES));
		if (value.equals("Otro")) {
			/**
			bean.getLogin().setCodact("19");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
			*/
				bean.getLogin().setCodact("313");
				if (!bean.getLogin().hasAccesAccion()) {
					callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
					return;
				}
			//}
			bean.consult();
			user.cargarModulos();
		}
		if (value.equals("Agregar")) {
			bean.getLogin().setCodact("313");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCodprf(req.getParameter("TxtCodprf"));
			bean.setTxtprf(req.getParameter("TxtTxtprf"));
			
			if (!validate(bean)) {
				bean.insert();
				if (bean.getError().equals("")) {
					bean.setCodprf("");
					bean.setTxtprf("");
				}
			}
			callPage(req, res, JspServlet.GESTION_DE_PERFILES_JSP);
			return;
		}
		if (value.equals("Modificar")) {
			bean.getLogin().setCodact("313");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCodprf(req.getParameter("TxtCodprf"));
			bean.setTxtprf(req.getParameter("TxtTxtprf"));
			if (!validate(bean)) {
				bean.update();
				if (bean.getError().equals("")) {
					bean.setCodprf("");
					bean.setTxtprf("");
				}
			}
			callPage(req, res, JspServlet.GESTION_DE_PERFILES_JSP);
			return;
		}
		if (value.equals("Eliminar")) {
			bean.getLogin().setCodact("313");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.delete(req);
			bean.setCodprf("");
			bean.setTxtprf("");
			callPage(req, res, JspServlet.GESTION_DE_PERFILES_JSP);
			return;
		}
		if (value.equals("Buscar")) {
			bean.setCodprf(req.getParameter("TxtCodprf"));
			bean.search();
			callPage(req, res, JspServlet.GESTION_DE_PERFILES_JSP);
			return;
		}
		if (value.equals("Asig. de Activ.")) {
			bean.setCodprf(req.getParameter("TxtCodprf"));
			if (bean.search())
				res.sendRedirect(JspServlet.ACTIVIDADES_DE_PERFILES_SERVLET + "?TxtCodprf=" + bean.getCodprf() + "&BtnAct=Otro");
			else {
				callPage(req, res, JspServlet.GESTION_DE_PERFILES_JSP);
			}
			return;
		}
		bean.setError("");
		bean.loadGrid();
		bean.setCodprf("");
		bean.setTxtprf("");
		callPage(req, res, JspServlet.GESTION_DE_PERFILES_JSP);
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
		GestionDePerfiles bean = (GestionDePerfiles) obj;
		bean.setError("");
		if (bean.getCodprf().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_PERFIL));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getCodprf())) {
			bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_PERFIL_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (bean.getTxtprf().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_DESCRIPCION_PERFIL));
			return true;
		}
		return false;
	}
	catch (Exception e) {
		throw new Exception("\n... validate() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}