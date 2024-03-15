package CosapiSoft.SARAWebManager;

import CosapiSoft.SARAWebBanking.InicioSesion;
import CosapiSoft.SARAWebBanking.JavaServlet;
import CosapiSoft.SARAWebBanking.JspServlet;
import CosapiSoft.SARAWebBanking.Mensajes;


/**
 * This type was created in VisualAge.
 */
public class AfiliacionesClienteServlet extends JavaServlet {
	
/**
 * AyudaDeCampoServlet constructor comment.
 */
public AfiliacionesClienteServlet() {
	super();
}
public void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {
	doPost(req,res);
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException {
	InicioSesion login = new InicioSesion();
	login.setNameProducto("SARA Web Manager");
	String modulo="manager";
	try {
		if (req.getSession(false) == null) {
			//			res.sendRedirect(JspServlet.SARA_WEB_BANKING_HTML);
			try {
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				setAttribute(req, "login", login);
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		String value = req.getParameter("BtnAfiCli");
		AfiliacionesCliente bean;
		if (req.getSession(false).getAttribute("taficli") != null) {
			bean = ((AfiliacionesCliente) req.getSession(false).getAttribute("taficli"));
		}
		else {
			bean = new AfiliacionesCliente();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("taficli", bean);
		}
		
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("AFILIACIONES POR CLIENTES");
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		if (value.equals("Ver")) {
			bean.getLogin().setCodact("333");
			bean.getLogin().setAccion("Consulta");
			
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			
			bean.setNumprdsrc(req.getParameter("TxtTarjeta"));
			bean.AfiporCliente();
			callPage(req, res, JspServlet.AFILIACION_CLIENTES_JSP);
			return;
		}
		if (value.equals("Inicio")) {
			bean.setAfi(null);
			callPage(req, res, JspServlet.AFILIACION_CLIENTES_JSP);
			return;
		}
		
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
 * This method was created in VisualAge.
 * @return boolean
 * @param obj java.lang.Object
 */
public boolean validate(Object obj) throws Exception {
	try {
		return false;
	}
	catch (Exception e) {
		throw new Exception("validate() -> LogDeOperacionesServlet - " + e.getMessage());
	}
}
}

