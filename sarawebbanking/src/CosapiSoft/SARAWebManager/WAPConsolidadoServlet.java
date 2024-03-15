package CosapiSoft.SARAWebManager;

import CosapiSoft.SARAWebBanking.InicioSesion;
import CosapiSoft.SARAWebBanking.JavaServlet;
import CosapiSoft.SARAWebBanking.JspServlet;
import CosapiSoft.SARAWebBanking.Mensajes;


/**
 * This type was created in VisualAge.
 */
public class WAPConsolidadoServlet extends JavaServlet {
	
/**
 * AyudaDeCampoServlet constructor comment.
 */
public WAPConsolidadoServlet() {
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
		value = (value == null) ? "Otro" : value.trim();
		WAPConsolidado bean;
		if (req.getSession(false).getAttribute("wapcon") != null) {
			bean = ((WAPConsolidado) req.getSession(false).getAttribute("wapcon"));
		}
		else {
			bean = new WAPConsolidado();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("wapcon", bean);
		}
		
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("WAP CONSOLIDADO");
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		
		if (value.equals("Inicio")) {
			bean.getLogin().setCodact("340");
			bean.getLogin().setAccion("Consulta");
			ConsolidadoWAP cw= new ConsolidadoWAP();
			cw.setTotFaClaro1("0");
			cw.setTotFaClaro3("0");
			cw.setTotFaMovis2("0");
			cw.setTotFaMovis4("0");
			cw.setTotOkClaro1("0");
			cw.setTotOkClaro3("0");
			cw.setTotOkMovis2("0");
			cw.setTotOkMovis4("0");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.getLogin().setError("");
			callPage(req, res, JspServlet.WAP_CONSOLIDADO_JSP);
			return;
		}
		
		if (value.equals("Buscar")){
			bean.getLogin().setCodact("340");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setGrid(null);
			ConsolidadoWAP cw= new ConsolidadoWAP();
			cw.setTotFaClaro1("0");
			cw.setTotFaClaro3("0");
			cw.setTotFaMovis2("0");
			cw.setTotFaMovis4("0");
			cw.setTotOkClaro1("0");
			cw.setTotOkClaro3("0");
			cw.setTotOkMovis2("0");
			cw.setTotOkMovis4("0");
			bean.setDatpro1(req.getParameter("TxtDatpro1"));
			bean.setDatpro2(req.getParameter("TxtDatpro2"));
			
			if(!validate(bean)){
			bean.mostrar();
			}
			callPage(req, res, JspServlet.WAP_CONSOLIDADO_JSP);
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
		WAPConsolidado bean = (WAPConsolidado) obj;
		if(bean.getDatpro2().equals("") || bean.getDatpro1().equals("")){
			bean.setError(Mensajes.getMessage(Mensajes.FECHA_NO_NULO));
			return true;
		}
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
		java.util.Date fecha1 = sdf.parse(bean.getDatpro1());
		java.util.Date fecha2 = sdf.parse(bean.getDatpro2());
		if( fecha2.before(fecha1)){
			bean.setError(Mensajes.getMessage(Mensajes.FECHA_MAYOR));
			return true;
			}

		return false;
	}
	catch (Exception e) {
		throw new Exception("validate() -> LogDeOperacionesServlet - " + e.getMessage());
	}
}
}

