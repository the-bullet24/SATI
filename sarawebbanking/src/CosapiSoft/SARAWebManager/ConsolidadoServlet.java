package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class ConsolidadoServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public ConsolidadoServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	System.out.println("ConsolidadoServlet.doPost...");
    InicioSesion login = new InicioSesion();
	login.setNameProducto("SARA Web Manager");
	try {
		if (req.getSession(false) == null) {
		    System.out.println("req.getSession(false) == null");
			//			res.sendRedirect(JspServlet.SARA_WEB_BANKING_HTML);
			try {
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				setAttribute(req, "login", login);
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
			    System.out.println("ServletException="+ se.getMessage());
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		String value = req.getParameter("BtnCon");
		value = (value == null) ? "Otro" : value.trim();
		Consolidado bean;
		System.out.println("value="+ value);
		if (req.getSession(false).getAttribute("tcondat") != null) {
		    System.out.println("tcondat!=null");
			bean = ((Consolidado) req.getSession(false).getAttribute("tcondat"));
		}
		else {
		    System.out.println("tcondat=null");
			bean = new Consolidado();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("tcondat", bean);
		}
		System.out.println("getLogin...");
		//
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("AFILIACIONES CONSOLIDADAS");
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		if (value.equalsIgnoreCase("Mostrar")) {
			bean.getLogin().setCodact("331");
			bean.getLogin().setAccion("Consulta");
			System.out.println("hasAccesAccion...");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			System.out.println("mostrar...");
			bean.mostrar();
			System.out.println("callpage...");
			callPage(req, res, JspServlet.CONSOLIDADO_JSP);
			return;
		}
	}
	catch (Exception e) {
	    System.out.println("Exception="+e.getMessage());
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

