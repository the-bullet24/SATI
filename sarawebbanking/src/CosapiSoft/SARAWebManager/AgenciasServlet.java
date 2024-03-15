package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;

public class AgenciasServlet extends JavaServlet {
	/**
 * AyudaDeCampoServlet constructor comment.
 */
public AgenciasServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	InicioSesion login = new InicioSesion();
	login.setNameProducto("SARA Web Manager");
	try {
		if (req.getSession(false) == null) {
			//			res.sendRedirect(JspServlet.SARA_WEB_BANKING_HTML);
			try {
				System.out.println("ingreso 1.-");
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				setAttribute(req, "login", login);
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				System.out.println(""+se);
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		System.out.println("antes de empezar.");
		String value = req.getParameter("BtnAge");
		System.out.println("value:"+value);
		value = (value == null) ? "Otro" : value.trim();
		Agencias bean;
		if (req.getSession(false).getAttribute("tagedat") != null) {
			bean = ((Agencias) req.getSession(false).getAttribute("tagedat"));
		}
		else {
			bean = new Agencias();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("tagedat", bean);
		}

		//
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("AFILIACIONES POR AGENCIAS");
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		if (value.equalsIgnoreCase("Otro")) {
			bean.setGrid(null);
			/**
			*bean.getLogin().setCodact("03");
			*bean.getLogin().setAccion("Consulta");
			*if (!bean.getLogin().hasAccesAccion()) {
			*/	
				bean.getLogin().setCodact("332");
				bean.getLogin().setAccion("Consulta");
				if (!bean.getLogin().hasAccesAccion()) {
					//					res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
					callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
					return;
				}
			//}
			
			callPage(req, res, JspServlet.AFILIACION_AGENCIAS_JSP);
			return;
			
		}
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setGrid(null);
			bean.getLogin().setCodact("03");
			bean.getLogin().setAccion("Consulta");
			bean.setFecha(req.getParameter("TxtFecha"));
			// System.out.println("fecha--------"+req.getParameter("TxtFecha"));
			bean.buscar();
			callPage(req, res, JspServlet.AFILIACION_AGENCIAS_JSP);
			return;
			
		}
		
	}
	catch (Exception e) {
		try {
			setAttribute(req, "error", new Exception(Mensajes.ERROR_GENERAL));
			setAttribute(req, "login", login);
			callPage(req, res, JspServlet.ERROR_JSP);
		}
		catch (javax.servlet.ServletException se) {
			res.getWriter().println(se.getMessage());
		}
	}
}	
	public boolean validate(Object obj) throws Exception {
		try {
			return false;
		}
		catch (Exception e) {
			throw new Exception("\n... validate - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
}


