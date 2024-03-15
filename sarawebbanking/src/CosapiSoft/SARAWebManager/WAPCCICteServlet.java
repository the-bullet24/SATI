package CosapiSoft.SARAWebManager;

import CosapiSoft.SARAWebBanking.CFormat;
import CosapiSoft.SARAWebBanking.CString;
import CosapiSoft.SARAWebBanking.InicioSesion;
import CosapiSoft.SARAWebBanking.JavaServlet;
import CosapiSoft.SARAWebBanking.JspServlet;
import CosapiSoft.SARAWebBanking.LogDeOperaciones;
import CosapiSoft.SARAWebBanking.Mensajes;


/**
 * This type was created in VisualAge.
 */
public class WAPCCICteServlet extends JavaServlet {
	
/**
 * AyudaDeCampoServlet constructor comment.
 */
public WAPCCICteServlet() {
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
		String value = req.getParameter("BtnEst");
		
		WAPCCICte bean;
		if (req.getSession(false).getAttribute("ccicte") != null) {
			bean = ((WAPCCICte) req.getSession(false).getAttribute("ccicte"));
		}
		else {
			bean = new WAPCCICte();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("ccicte", bean);
		}
		
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("CCI CUENTA CORRIENTE");
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		
		if (value.equals("Inicio")) {
			bean.getLogin().setCodact("338");
		bean.getLogin().setAccion("Consulta");
			
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			
			bean.setGrid(null);
			bean.getLogin().setError("");
			callPage(req, res, JspServlet.WAP_CCI_CORRIENTE_JSP);
			return;
		}
		
		if (value.equals("Cargar Tabla")) {
		bean.getLogin().setCodact("338");
		bean.getLogin().setAccion("Consulta");
			
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setDatpro1("");
			bean.setDatpro2("");
			bean.setNumcta("");
			bean.setTyptar("");
			bean.setEstado("");
			bean.setGrid(null);
			bean.setNumdoc(req.getParameter("TxtNroDoc"));
			// System.out.println("nro de doc ...."+req.getParameter("TxtNroDoc"));
			bean.setDatpro1(req.getParameter("TxtDatpro1"));
			bean.setDatpro2(req.getParameter("TxtDatpro2"));
			bean.setNumcta(req.getParameter("TxtNroCta"));
			bean.setTyptar(req.getParameter("TipoTar"));
			bean.setEstado(req.getParameter("CboEstado"));
			bean.loadGrid();
			callPage(req, res, JspServlet.WAP_CCI_CORRIENTE_JSP);
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


