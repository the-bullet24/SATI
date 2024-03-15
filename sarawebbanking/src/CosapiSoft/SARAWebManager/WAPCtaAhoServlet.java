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
public class WAPCtaAhoServlet extends JavaServlet {
	
/**
 * AyudaDeCampoServlet constructor comment.
 */
public WAPCtaAhoServlet() {
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
		System.out.println("antes value="+value);
		
		value = (value == null) ? "Otro" : value.trim();
		System.out.println("despues value="+value);
		WAPCtaAho bean;
		if (req.getSession(false).getAttribute("ctaaho") != null) {
			bean = ((WAPCtaAho) req.getSession(false).getAttribute("ctaaho"));
		}
		else {
			bean = new WAPCtaAho();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("ctaaho", bean);
		}
		
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("WAP SALDO CUENTA AHORRO");
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		
		if (value.equals("Inicio")) {
			bean.getLogin().setCodact("312");
			bean.getLogin().setAccion("Consulta");
			System.out.println("Ingreso....");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setGrid(null);
			bean.getLogin().setError("");
			callPage(req, res, JspServlet.WAP_CTA_AHORROS_JSP);
			return;
		}
		
		if (value.equals("Cargar Tabla")) {
			bean.getLogin().setCodact("312");
			bean.getLogin().setAccion("Consulta");
			bean.setGrid(null);
			bean.setDatpro1("");
			bean.setDatpro2("");
			bean.setNrotar("");
			bean.setTyptar("");
			bean.setEstado("");
			bean.setDatpro1(req.getParameter("TxtDatpro1"));
			bean.setDatpro2(req.getParameter("TxtDatpro2"));
			bean.setNrotar(req.getParameter("TxtNroCta"));
			bean.setTyptar(req.getParameter("TipoTar"));
			bean.setEstado(req.getParameter("CboEstado"));
			// System.out.println("tipo de tarjeta-------"+req.getParameter("TipoTar").toString());
			System.out.println("tipo de tarjeta----"+req.getParameter("TipoTar").toString());
			System.out.println("fecha 1----"+req.getParameter("TxtDatpro1").toString());
			System.out.println("fecha 2----"+req.getParameter("TxtDatpro2").toString());
			System.out.println("nro tarjeta----"+req.getParameter("TxtNroCta").toString());
			bean.loadGrid();
			callPage(req, res, JspServlet.WAP_CTA_AHORROS_JSP);
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
	WAPCtaAho bean = (WAPCtaAho) obj;
	try {
		if (bean.getDatpro1().equals("") || bean.getDatpro2().equals("") ) {
			bean.setError(Mensajes.getMessage(Mensajes.LIMITE_NULO));
			return true;
		}
		if (bean.getTyptar().equals("") ) {
			bean.setError(Mensajes.getMessage(Mensajes.LIMITE_NULO));
			return true;
		}
		return false;
	}
	catch (Exception e) {
		throw new Exception("validate() -> LogDeOperacionesServlet - " + e.getMessage());
	}
}
}

