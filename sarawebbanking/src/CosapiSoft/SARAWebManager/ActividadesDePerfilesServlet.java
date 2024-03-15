package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class ActividadesDePerfilesServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public ActividadesDePerfilesServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	try {
		if (!isLoggedIn(req)) {
			try {
				req.getSession(false).setAttribute("error", new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				//req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		// System.out.println("llego hasta el dopost de actviidades");
		GestionDePerfiles tprfdat = (GestionDePerfiles) (req.getSession(false).getAttribute("tprfdat"));
		String value = req.getParameter("BtnPrf");
		value = (value == null) ? "Otro" : value.trim();
		//
		if (value.equalsIgnoreCase("Otro")) {
			tprfdat.consultActividad();
			tprfdat.loadGridChild();
		}
		tprfdat.getLogin().setError("");
		tprfdat.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.ASIGNACION_DE_ACTIVIDADES));
		if (value.equalsIgnoreCase("Aceptar")) {
			//tprfdat.getLogin().setCodact("20");
			tprfdat.getLogin().setAccion("Modificación");
			/**
			if (!tprfdat.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			*/
			tprfdat.saveActividad(req);
			callPage(req, res, JspServlet.ACTIVIDADES_DE_PERFILES_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Regresar")) {
			callPage(req, res, JspServlet.GESTION_DE_PERFILES_JSP);
			return;
		}
		tprfdat.setError("");
		callPage(req, res, JspServlet.ACTIVIDADES_DE_PERFILES_JSP);
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
	return false;
}
}