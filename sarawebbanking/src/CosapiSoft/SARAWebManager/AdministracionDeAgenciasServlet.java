package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;

import pe.cosapi.common.Constante;
public class AdministracionDeAgenciasServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public AdministracionDeAgenciasServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException {
	// System.out.println("entro al do post");
	InicioSesion login = new InicioSesion();
	login.setNameProducto("SARA Web Manager");
	System.out.println("entro a agencias");
	try {
	    if (Constante.SESSION_CADUCA.equals("2")) {
			//			res.sendRedirect(JspServlet.SARA_WEB_BANKING_HTML);
			try {
				//req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				req.getSession(false).setAttribute("error", new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				setAttribute(req, "login", login);
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
		String value = req.getParameter("BtnAge");
		value = (value == null) ? "Otro" : value.trim();
		AdministracionDeAgencias bean;
		if (req.getSession(false).getAttribute("tbrainf") != null) {
			bean = ((AdministracionDeAgencias) req.getSession(false).getAttribute("tbrainf"));
		}
		else {
			bean = new AdministracionDeAgencias();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("tbrainf", bean);
		}
		/*bean = new AdministracionDeAgencias();
		bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getValue("manager"));
		setAttribute(req,"tbradat", bean);*/
		//		}
		bean.getLogin().setError("");
		//bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.ADMINISTRACION_DE_OFICINAS));
		if (value.equals("Otro")) {
			bean.getLogin().setCodact("03");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				bean.getLogin().setCodact("04");
				if (!bean.getLogin().hasAccesAccion()) {
					//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
					callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
					return;
				}
			}
			bean.consult();
			// System.out.println("salio del consult");
		}
		if (value.equals("Agregar")) {
			bean.getLogin().setCodact("04");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			//bean.loadGrid();
			bean.setStatus("0");
			bean.setCodbra(req.getParameter("TxtCodbra"));
			bean.setNumsum(req.getParameter("TxtNumsum"));
			bean.setDayprgtra(req.getParameter("TxtDayprgtra"));
			bean.setBandef(req.getParameter("TxtBandef"));
			bean.setHouini(req.getParameter("TxtHouini"));
			bean.setHoufin(req.getParameter("TxtHoufin"));
			bean.setBanpna(req.getParameter("TxtBanpna"));
			bean.setBanpju(req.getParameter("TxtBanpju"));
			if (req.getParameter("chkFlgblkbra")==null)
				bean.setFlgblkbra("0");
			else
				bean.setFlgblkbra("1");
			if (!validate(bean)) {
				bean.insert();
				if (bean.getError().equals("")) {
					bean.setCodbra("");
					bean.setNumsum("");
					bean.setDayprgtra("");
					bean.setFlgblkbra("");
					bean.setBandef("");
					bean.setHouini("");
					bean.setHoufin("");
					bean.setBanpna("");
					bean.setBanpju("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/AdministracionDeAgencias/AdministracionDeAgencias.jsp");
			callPage(req, res, JspServlet.ADMINISTRACION_DE_AGENCIAS_JSP);
			return;
		}
		if (value.equals("Modificar")) {
			bean.getLogin().setCodact("04");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			//bean.loadGrid();
			bean.setStatus("0");
			bean.setCodbra(req.getParameter("TxtCodbra"));
			bean.setNumsum(req.getParameter("TxtNumsum"));
			bean.setDayprgtra(req.getParameter("TxtDayprgtra"));
			bean.setBandef(req.getParameter("TxtBandef"));
			bean.setHouini(req.getParameter("TxtHouini"));
			bean.setHoufin(req.getParameter("TxtHoufin"));
			bean.setBanpna(req.getParameter("TxtBanpna"));
			bean.setBanpju(req.getParameter("TxtBanpju"));
			if (req.getParameter("chkFlgblkbra")==null)
				bean.setFlgblkbra("0");
			else
				bean.setFlgblkbra("1");
			// System.out.println("antes de validar...........");
			if (!validate(bean)) {
				bean.update();
				if (bean.getError().equals("")) {
					bean.setCodbra("");
					bean.setNumsum("");
					bean.setDayprgtra("");
					bean.setFlgblkbra("");
					bean.setBandef("");
					bean.setHouini("");
					bean.setHoufin("");
					bean.setBanpna("");
					bean.setBanpju("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/AdministracionDeAgencias/AdministracionDeAgencias.jsp");
			callPage(req, res, JspServlet.ADMINISTRACION_DE_AGENCIAS_JSP);
			return;
		}
		if (value.equals("Eliminar")) {
			bean.getLogin().setCodact("04");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.delete(req);
			bean.setCodbra("");
			bean.setNumsum("");
			bean.setDayprgtra("");
			bean.setFlgblkbra("");
			bean.setBandef("");
			bean.setHouini("");
			bean.setHoufin("");
			bean.setBanpna("");
			bean.setBanpju("");
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/AdministracionDeAgencias/AdministracionDeAgencias.jsp");
			callPage(req, res, JspServlet.ADMINISTRACION_DE_AGENCIAS_JSP);
			return;
		}
		if (value.equals("Buscar")) {
			//bean.loadGrid();
			bean.setCodbra(req.getParameter("TxtCodbra"));
			bean.search();

			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/AdministracionDeAgencias/AdministracionDeAgencias.jsp");
			callPage(req, res, JspServlet.ADMINISTRACION_DE_AGENCIAS_JSP);
			return;
		}
		bean.setError("");
		bean.loadGrid();
		bean.setCodbra("");
		bean.setNumsum("");
		bean.setDayprgtra("");
		bean.setFlgblkbra("");
		bean.setBandef("");
		bean.setHouini("");
		bean.setHoufin("");
		bean.setBanpna("");
		bean.setBanpju("");
		//		res.sendRedirect("../../../SARAWebBanking/SARAWebManager/AdministracionDeAgencias/AdministracionDeAgencias.jsp");
		callPage(req, res, JspServlet.ADMINISTRACION_DE_AGENCIAS_JSP);
	}
	catch (Exception e) {
		try {
			e.printStackTrace();
			setAttribute(req, "error", new Exception(Mensajes.ERROR_GENERAL));
			setAttribute(req, "login", login);
			callPage(req, res, JspServlet.ERROR_JSP);
		}
		catch (javax.servlet.ServletException se) {
			se.printStackTrace();
			res.getWriter().println(se.getMessage());
		}
	}
}
public boolean validate(Object obj) throws Exception {
	try {
		AdministracionDeAgencias bean = (AdministracionDeAgencias) obj;
		bean.setError("");
		System.out.println("entro al valid");
		if (bean.getCodbra().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_EL_CODIGO_DE_OFICINA));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getCodbra())) {
			bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_OFICINA_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CFormat.isTime(bean.getHouini())) {
			bean.setError(Mensajes.getMessage(Mensajes.HORAINI_INGRESADA_INCORRECTA));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CFormat.isTime(bean.getHoufin())) {
			bean.setError(Mensajes.getMessage(Mensajes.HORAFIN_INGRESADA_INCORRECTA));
			return true;
		}
		System.out.println("antes de comprar horas");
		if (!CosapiSoft.SARAWebBanking.CFormat.isTimemayor(bean.getHouini(),bean.getHoufin())) {
			bean.setError(Mensajes.getMessage(Mensajes.HORAFIN_INGRESADA_INCORRECTA));
			return true;
		}
		return false;
	}
	catch (Exception e) {
		e.printStackTrace();
		throw new Exception("\n... validate() - " + getClass().getName() + " -> " + e.getMessage());
	}
	
}
}