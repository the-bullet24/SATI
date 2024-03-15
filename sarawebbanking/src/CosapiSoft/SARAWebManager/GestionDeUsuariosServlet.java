package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class GestionDeUsuariosServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public GestionDeUsuariosServlet() {
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
		String value = req.getParameter("BtnUsr");
		value = (value == null) ? "Otro" : value.trim();
		GestionDeUsuarios bean;
		if (req.getSession(false).getAttribute("tusrdat") != null) {
			bean = (GestionDeUsuarios) (req.getSession(false).getAttribute("tusrdat"));
		}
		else {
			bean = new GestionDeUsuarios();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			bean.loadOficinas();
			req.getSession(false).setAttribute("tusrdat", bean);
		}
		String usr = req.getParameter("TxtCodusr");
		usr = (usr == null) ? "" : usr.trim();
		bean.setCodusr(usr);
		//
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.GESTION_DE_USUARIOS));
		if (value.equals("Otro")) {
			/**
			bean.getLogin().setCodact("23");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
			*/
				bean.getLogin().setCodact("315");
				bean.getLogin().setAccion("Consulta");
				if (!bean.getLogin().hasAccesAccion()) {
					callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
					return;
				}
			//}
			bean.consult();
		}
		if (value.equalsIgnoreCase("Agregar")) {
			bean.getLogin().setCodact("315");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCodusr(usr);
			bean.setTxtnam(req.getParameter("TxtTxtnam"));
			bean.setUsrmail(req.getParameter("TxtUsrmail"));
			bean.setTxtusu(req.getParameter("TxtTxtusu"));
			if (!validate(bean)) {
				bean.setListMod(req.getParameterValues("TxtCodmod"));
				bean.insert();
				if (bean.getError().equals("")) {
					bean.setCodusr("");
					bean.setTxtnam("");
					bean.setCodbra("");
					bean.setUsrmail("");
					bean.setCodlim("");
					bean.setTxtusu("");
					bean.setTxtsec("");
					bean.setSta_mod("");
				}
			}
			callPage(req, res, JspServlet.GESTION_DE_USUARIOS_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Modificar")) {
			bean.getLogin().setCodact("315");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCodusr(usr);
			bean.setTxtnam(req.getParameter("TxtTxtnam"));
			bean.setUsrmail(req.getParameter("TxtUsrmail"));
			bean.setTxtusu(req.getParameter("TxtTxtusu"));
			if (!validate(bean)) {
				bean.setListMod(req.getParameterValues("TxtCodmod"));
				bean.update();
				if (bean.getError().equals("")) {
					bean.setCodusr("");
					bean.setTxtnam("");
					bean.setCodbra("");
					bean.setUsrmail("");
					bean.setCodlim("");
					bean.setSta_mod("");
					bean.setTxtusu("");
					bean.setTxtsec("");
				}
			}
			callPage(req, res, JspServlet.GESTION_DE_USUARIOS_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Eliminar")) {
			bean.getLogin().setCodact("315");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.delete(req);
			bean.setCodusr("");
			bean.setTxtnam("");
			bean.setCodbra("");
			bean.setUsrmail("");
			bean.setCodlim("");
			bean.setTxtusu("");
			bean.setTxtsec("");
			bean.setSta_mod("");
			callPage(req, res, JspServlet.GESTION_DE_USUARIOS_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setCodusr(usr);
			bean.search();
			callPage(req, res, JspServlet.GESTION_DE_USUARIOS_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Asig. de Perfiles")) {
			bean.setCodusr(usr);
			if (bean.search()) {
				res.sendRedirect(JspServlet.PERFILES_DE_USUARIOS_SERVLET + "?TxtCodusr=" + bean.getCodusr() + "&BtnPrf=Otro");
			}
			else {
				String str = Mensajes.getMessage(Mensajes.CODIGO_DE_USUARIO_NO_EXISTE);
				int pos = str.indexOf('&');
				bean.setError(str.substring(0, pos) + bean.getCodusr() + str.substring(pos + 1));
				callPage(req, res, JspServlet.GESTION_DE_USUARIOS_JSP);
			}
			return;
		}
		if (value.equalsIgnoreCase("Ver Log (Branch)")) {
			bean.setCodusr(usr);
			if (bean.search()) {
				if (bean.hasAssignedBranch()) {
					res.sendRedirect(JspServlet.LOG_DE_USUARIOS_BRANCH_SERVLET + "?TxtCodusr=" + bean.getCodusr() + "&BtnUsrLog=Otro");
				}
				else {
					bean.setError("Usuario NO tiene asignado el Módulo SARA Web Branch");
					callPage(req, res, JspServlet.GESTION_DE_USUARIOS_JSP);
				}
			}
			else {
				String str = Mensajes.getMessage(Mensajes.CODIGO_DE_USUARIO_NO_EXISTE);
				int pos = str.indexOf('&');
				bean.setError(str.substring(0, pos) + bean.getCodusr() + str.substring(pos + 1));
				callPage(req, res, JspServlet.GESTION_DE_USUARIOS_JSP);
			}
			return;
		}
		bean.setError("");
		bean.loadGrid();
		bean.setCodusr("");
		bean.setTxtnam("");
		bean.setCodbra("");
		bean.setUsrmail("");
		bean.setTxtusu("");
		bean.setTxtsec("");
		bean.setSta_mod("");
		callPage(req, res, JspServlet.GESTION_DE_USUARIOS_JSP);
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
		GestionDeUsuarios bean = (GestionDeUsuarios) obj;
		bean.setError("");
		if (bean.getCodusr().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.INGRESAR_USUARIO));
			return true;
		}
		if (bean.getCodusr().length() < 4) {
			bean.setError(Mensajes.getMessage(Mensajes.USUARIO_CUATRO_CARACTERES));
			return true;
		}
		if (bean.getTxtnam().equals("")) {
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