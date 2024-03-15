package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;

public class PerfilesDeUsuariosServlet extends JavaServlet {
	
/**
 * AyudaDeCampoServlet constructor comment.
 */
public PerfilesDeUsuariosServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	try {
		System.out.println("entro al servlet");
		
		if (!isLoggedIn(req)) {
			try {
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));   ;      
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		
		GestionDeUsuarios tusrdat = (GestionDeUsuarios) req.getSession(false).getAttribute("tusrdat");
		String value = req.getParameter("BtnPrf");
		value = (value == null) ? "Otro" : value.trim();
		PerfilesDeUsuarios bean;
		System.out.println("entro al servlet value:"+value);
		if (req.getSession(false).getAttribute("tusrprf") != null) {
			bean = ((PerfilesDeUsuarios) req.getSession(false).getAttribute("tusrprf"));
			bean.setGrid(null);
		}
		else {
			bean = new PerfilesDeUsuarios();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("tusrprf", bean);
			bean.setModulos(null);
			bean.setCodmod("");
		}
		bean.setCodusr(tusrdat.getCodusr());
		bean.setCodbra(tusrdat.getCodbra());
		bean.setCodrea(tusrdat.getCodrea());
		if (value.equalsIgnoreCase("Regresar")) {
			callPage(req, res, JspServlet.GESTION_DE_USUARIOS_JSP);
			return;
		}
		//
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.ASIGNACION_DE_PERFILES));
		if (value.equalsIgnoreCase("Aceptar")) {
			bean.getLogin().setCodact("24");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setCodmod(req.getParameter("CmbCodmod"));
			bean.aceptar(req);
			bean.setCodprf("");
			bean.setTxtprf("");
			callPage(req, res, JspServlet.PERFILES_DE_USUARIOS_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Grabar")) {
			bean.getLogin().setCodact("24");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setCodmod(req.getParameter("CmbCodmod"));
			bean.aceptar(req);
			bean.setCodprf("");
			bean.setTxtprf("");
			callPage(req, res, JspServlet.PERFILES_DE_USUARIOS_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Cargar Perfiles")) {
			System.out.println("ingreso akangha --------");
			bean.setCodmod(req.getParameter("CmbCodmod"));
			bean.setGrid(null);
			bean.loadGrid();
			callPage(req, res, JspServlet.PERFILES_DE_USUARIOS_JSP);
			return;
		}
		bean.setError("");
		bean.setCodmod("");
		bean.consult();
		bean.setModulos(null);
		bean.setCodprf("");
		bean.setTxtprf("");
		callPage(req, res, JspServlet.PERFILES_DE_USUARIOS_JSP);
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
/**
 * validate method comment.
 */
public boolean validate(Object obj) throws Exception {
	
	return false;
}
}