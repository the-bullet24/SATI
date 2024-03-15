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
public class AfiliacionesServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public AfiliacionesServlet() {
	super();
}
public
void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {
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
		String value = req.getParameter("BtnAfi");
		value = (value == null) ? "Otro" : value.trim();
		Afiliaciones bean;
		if (req.getSession(false).getAttribute("tafidat") != null) {
			bean = ((Afiliaciones) req.getSession(false).getAttribute("tafidat"));
		}
		else {
			bean = new Afiliaciones();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("tafidat", bean);
		}
		
		bean.getLogin().setError("");		
		if (value.equals("Otro")) {
			// System.out.println("empieza OTRO");
			//bean.clearGrid();
			bean.getLogin().setNamePantalla("AFILIACIONES DE SERVICIO");
			bean.getLogin().setCodact("334");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			// System.out.println("termino OTRO");
		}
		
		if (value.equals("4")) {
		    bean.getLogin().setNamePantalla("AFILIACIONES DE SERVICIO");
		    System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		    
			bean.getLogin().setCodact("311");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			//bean.setGrid(null);
			bean.loadGrid();
			callPage(req, res, JspServlet.AFILIACION_SERVICIOS_JSP);
			return;
		}
		
		if (value.equals("3")) {
			callPage(req, res, JspServlet.AFILIACION_CLIENTES_JSP);
			return;
		}
		if (value.equals("2")) {
			bean.setNumtar(req.getParameter("TxtTarjeta"));
			bean.loadGrid();
			callPage(req, res, JspServlet.AFILIACION_CLIENTES_JSP);
			return;
		}
		/*if (value.equals("Ver Detalle")) {
			if (bean.goTo(req.getParameter("ChkLog")))
				res.sendRedirect(JspServlet.DETALLE_LOG_DE_OPERACIONES_MANAGER_SERVLET + "?BtnLog=Otro&Modulo=" + modulo);
			else {
				//				res.sendRedirect(JspServlet.LOG_DE_OPERACIONES_MANAGER_JSP);
				callPage(req, res, JspServlet.REPORTE_ESTADISTICO_JSP);
			}
			return;
		}*/
		if (value.equals("Buscar")) {
			callPage(req, res, JspServlet.REPORTE_ESTADISTICO_USO_JSP);
			return;
		}
		bean.setError("");
		callPage(req, res, JspServlet.REPORTE_ESTADISTICO_USO_JSP);
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
		LogDeOperaciones bean = (LogDeOperaciones) obj;
		bean.setError("");
		if (!bean.getDatpro1().equals("")) {
			if (bean.getDatpro1().length() < 8) {
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_OCHO_CARACTERES_FECHA) + " (Desde)");
				return true;
			}
			CFormat f = new CFormat("0000/00/00", bean.getDatpro1());
			if (f.isValidoText()) {
				bean.setDatpro1(f.changeFormat());
				if (!f.isValidoDate()) {
					CString str = new CString(bean.getDatpro1());
					str.deleteAll('/');
					bean.setDatpro1(str.toString());
					bean.setError(f.getError() + " " + Mensajes.getMessage(Mensajes.ERROR_EN_FECHA) + " (Desde)");
					return true;
				}
				CString str = new CString(bean.getDatpro1());
				str.deleteAll('/');
				bean.setDatpro1(str.toString());
			}
			else {
				bean.setError(f.getError() + " " + Mensajes.getMessage(Mensajes.ERROR_EN_FECHA) + " (Desde)");
				return true;
			}
		}
		if (!bean.getDatpro2().equals("")) {
			if (bean.getDatpro2().length() < 8) {
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_OCHO_CARACTERES_FECHA) + " (Hasta)");
				return true;
			}
			CFormat f = new CFormat("0000/00/00", bean.getDatpro2());
			if (f.isValidoText()) {
				bean.setDatpro2(f.changeFormat());
				if (!f.isValidoDate()) {
					CString str = new CString(bean.getDatpro2());
					str.deleteAll('/');
					bean.setDatpro2(str.toString());
					bean.setError(f.getError() + " " + Mensajes.getMessage(Mensajes.ERROR_EN_FECHA) + " (Hasta)");
					return true;
				}
				CString str = new CString(bean.getDatpro2());
				str.deleteAll('/');
				bean.setDatpro2(str.toString());
			}
			else {
				bean.setError(f.getError() + " " + Mensajes.getMessage(Mensajes.ERROR_EN_FECHA) + " (Hasta)");
				return true;
			}
		}
		if (!bean.getDatpro1().equals("") && !bean.getDatpro2().equals("")) {
			if (bean.getDatpro1().compareTo(bean.getDatpro2()) > 0) {
				bean.setError(Mensajes.getMessage(Mensajes.FECHA_DESDE_MENOR_A_FECHA_HASTA));
				return true;
			}
		}
		if (!bean.getHorpro1().equals("")) {
			CFormat f = new CFormat("00:00:00", bean.getHorpro1());
			if (f.isValidoText()) {
				bean.setHorpro1(f.changeFormat());
				if (!f.isValidoTime()) {
					bean.setError(f.getError() + " " + Mensajes.getMessage(Mensajes.ERROR_EN_HORA) + " (Desde)");
					return true;
				}
			}
			else {
				bean.setError(f.getError() + " " + Mensajes.getMessage(Mensajes.ERROR_EN_HORA) + " (Desde)");
				return true;
			}
		}
		if (!bean.getHorpro2().equals("")) {
			
			CFormat f = new CFormat("00:00:00", bean.getHorpro2());
			if (f.isValidoText()) {
				bean.setHorpro2(f.changeFormat());
				if (!f.isValidoTime()) {
					bean.setError(f.getError() + " " + Mensajes.getMessage(Mensajes.ERROR_EN_HORA) + " (Hasta)");
					return true;
				}
			}
			else {
				bean.setError(f.getError() + " " + Mensajes.getMessage(Mensajes.ERROR_EN_HORA) + " (Hasta)");
				return true;
			}
		}
		if (!bean.getHorpro1().equals("") && !bean.getHorpro2().equals("")) {
			if (bean.getHorpro1().compareTo(bean.getHorpro2()) > 0) {
				bean.setError(Mensajes.getMessage(Mensajes.HORA_DESDE_MENOR_A_HORA_HASTA));
				return true;
			}
		}
		return false;
	}
	catch (Exception e) {
		throw new Exception("validate() -> LogDeOperacionesServlet - " + e.getMessage());
	}
}
}

