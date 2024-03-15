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
public class ReporteEstadisticoServlet extends JavaServlet {
	
/**
 * AyudaDeCampoServlet constructor comment.
 */
public ReporteEstadisticoServlet() {
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
		ReporteEstadistico bean;
		if (req.getSession(false).getAttribute("hjoutra") != null) {
			bean = ((ReporteEstadistico) req.getSession(false).getAttribute("hjoutra"));
		}
		else {
			bean = new ReporteEstadistico();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("hjoutra", bean);
		}
		
		
		if (value.equals("Otro")) {
			// System.out.println("empieza OTRO");
			//bean.clearGrid();
			bean.getLogin().setError("");
			bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.LOG_DE_OPERACIONES));
			bean.getLogin().setCodact("25");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			// System.out.println("termino OTRO");
		}
		
		if (value.equals("Cargar Tabla")) {
			bean.setGrid(null);
			String[] selecciones = req.getParameterValues("CmbCodtra");
			bean.setTxnhst(selecciones);
			bean.setCoddoc(req.getParameter("CboTypDoc"));
			bean.setNumdoc(req.getParameter("TxtNroDoc"));
			bean.setDatpro1(req.getParameter("TxtDatpro1"));
			bean.setDatpro2(req.getParameter("TxtDatpro2"));
			bean.setNumref(req.getParameter("TxtNroRef"));
			bean.setDatpro(req.getParameter("TxtDate"));
			bean.setNumprdsrc(req.getParameter("TxtNroCta"));
			bean.loadGrid();
			callPage(req, res, JspServlet.REPORTE_ESTADISTICO_USO_JSP);
			return;
		}
		if (value.equals("Ver")) {
			bean.setGrid(null);
			bean.setNumprdsrc(req.getParameter("TxtTarjeta"));
			bean.AfiporCliente();
			callPage(req, res, JspServlet.AFILIACION_CLIENTES_JSP);
			return;
		}
		if (value.equals("3")) {
			bean.setGrid(null);
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
