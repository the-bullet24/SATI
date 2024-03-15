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
public class PagosServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public PagosServlet() {
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
		Pagos bean;
		if (req.getSession(false).getAttribute("hjoutra") != null) {
			bean = ((Pagos) req.getSession(false).getAttribute("hjoutra"));
		}
		else {
			bean = new Pagos();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("hjoutra", bean);
		}
		
		
		if (value.equals("Otro")) {
			bean.tiposdoc.clear();
			bean.getLogin().setError("");
			bean.getLogin().setNamePantalla("LOG DE OPERACIONES");
			System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
			bean.getLogin().setCodact("310");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			// System.out.println("termino OTRO");
		}
		if (value.equals("Agua")) {
			
			bean.getLogin().setError("");
			bean.setBand("Agua");
			bean.getLogin().setNamePantalla("SERVICIO DE AGUA");
			System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
			bean.getLogin().setCodact("310");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
		}
		if (value.equals("Refrendo")) {
			bean.getLogin().setError("");
			bean.setNumlog(req.getParameter("numLog"));
			bean.setHora(req.getParameter("hora"));
			bean.Refrendo();
			callPage(req, res, "/sarawebbanking/SARAWebManager/ReporteEstadistico/Refrendo.jsp");
			return;
			
		}
		if (value.equals("Telefonia")) {
			bean.getLogin().setError("");
			bean.setGrid(null);
			bean.setBand("Telefonia");
			bean.getLogin().setNamePantalla("SERVICIO DE TELEFONIA");
			System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
			bean.getLogin().setCodact("341");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
		}
		
		if (value.equals("Pago de Tasas")) {
			bean.getLogin().setError("");
			bean.setBand("Pago de Tasas");
			bean.getLogin().setNamePantalla("PAGO DE TASAS");
			System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
			bean.getLogin().setCodact("311");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
		}
		if (value.equals("Emision de Giros")) {
			bean.getLogin().setError("");
			bean.setBand("Emision de Giros");
			bean.getLogin().setNamePantalla("EMISION DE GIROS");
			System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
			bean.getLogin().setCodact("328");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
		}
		if (value.equals("Transferencia Mismo Banco")) {
			bean.getLogin().setError("");
			bean.setBand("Transferencia Mismo Banco");
			bean.getLogin().setNamePantalla("TRANSFERENCIA MISMO BANCO");
			System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
			bean.getLogin().setCodact("329");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
		}
		if (value.equals("Transferencia Interbancaria")) {
			bean.getLogin().setError("");
			bean.setBand("Transferencia Interbancaria");
			bean.getLogin().setNamePantalla("TRANSFERENCIA INTERBANCARIA");
			System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
			bean.getLogin().setCodact("330");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
		}
		if (value.equals("Prestamo Multired")) {
			bean.getLogin().setError("");
			bean.setBand("Prestamo Multired");
			bean.getLogin().setNamePantalla("PRESTAMO MULTIRED");
			System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
			bean.getLogin().setCodact("326");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
		}
		
		if (value.equals("Pago Tarjeta")) {
			bean.getLogin().setError("");
			bean.setBand("Pago Tarjeta");
			bean.getLogin().setNamePantalla("PAGO DE TARJETA");
			System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
			bean.getLogin().setCodact("327");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
		}
		
		if (value.equals("Cargar Tabla")) {
			System.out.println("entró aki a cargar tabla");
			bean.setError("");
			bean.setGrid(null);
			bean.setCoddoc("");
			bean.setNumdoc("");
			bean.setDatpro1("");
			bean.setDatpro2("");
			bean.setNumref("");
			bean.setDatpro("");
			bean.setNrocta("");
			bean.setNumprdtar("");
			bean.setNumprdsrc("");
			bean.setNumlog("");
			bean.setTotalcargodolares("");
			bean.setTotalcargosoles("");
			bean.setTotalpagodolares("");
			bean.setTotalpagosoles("");
			bean.setNroregistroscargodol("");
			bean.setNroregistroscargosol("");
			String servicio=req.getParameter("servicio");
			if (req.getParameter("TxtCodtri") == null || req.getParameter("TxtCodtri").trim().length()==0)
				bean.setNrotxn(req.getParameter("transaccion"));
			else
				bean.setNrotxn(req.getParameter("TxtCodtri"));
			if(!this.validate(bean)){
				bean.setBand(req.getParameter("constancia"));
			bean.setCoddoc(req.getParameter("CboTypDoc"));
			bean.setNumdoc(req.getParameter("TxtNroDoc"));
			bean.setDatpro1(req.getParameter("TxtDatpro1"));
			bean.setDatpro2(req.getParameter("TxtDatpro2"));
			bean.setNumref(req.getParameter("TxtNroRef"));
			
			bean.setDatpro(req.getParameter("TxtDate"));
			if(bean.getBand().equals("Telefonia") || bean.getBand().equals("Agua") || bean.getBand().equals("Pago de Tasas") || bean.getBand().equals("Emision de Giros") ||  bean.getBand().equals("Agua")){
			    bean.setNrocta(req.getParameter("TxtNroCta"));
			}
			
			else if(bean.getBand().equals("Transferencia Interbancaria") || bean.getBand().equals("Transferencia Mismo Banco")){
				bean.setNrocta(req.getParameter("TxtNroCta"));
				if(bean.getBand().equals("Transferencia Interbancaria")){
				    bean.setNumprdtar(req.getParameter("TxtNroRef"));
				    bean.setNumref("");
				}
			}
			else if(bean.getBand().equals("Pago Tarjeta")){
				System.out.println("entró a pago d atrjeta");
				bean.setNumprdtar(req.getParameter("TxtNroCta"));
				bean.setNrocta(req.getParameter("TxtNroRef"));
				bean.setNumref("");
			}
			bean.setNumope(req.getParameter("TxtNroOpe"));
			bean.loadTotales();
			bean.loadGrid();
			// System.out.println("servicio--------"+servicio);
			callPage(req, res, "/sarawebbanking/SARAWebManager/ReporteEstadistico/PagoServicios.jsp?servicio="+servicio);
			return;
			}
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
			callPage(req, res, JspServlet.REPORTE_PAGOS_JSP);
			return;
		}
		bean.setError("");
		bean.setGrid(null);
		bean.setCoddoc("");
		bean.setNumdoc("");
		bean.setDatpro1("");
		bean.setDatpro2("");
		bean.setNumref("");
		bean.setDatpro("");
		bean.setNrocta("");
		bean.setNumlog("");
		bean.setTotalcargodolares("");
		bean.setTotalcargosoles("");
		bean.setTotalpagodolares("");
		bean.setTotalpagosoles("");
		
		bean.setNroregistroscargodol("");
		bean.setNroregistroscargosol("");
		//bean.setSuministro(req.getParameter("TxtNroOpe"));
		bean.setNumprdsrc("");
		bean.setNumprdtar("");
		callPage(req, res, "/sarawebbanking/SARAWebManager/ReporteEstadistico/PagoServicios.jsp?servicio="+value);
	}
	catch (Exception e) {
		try {
			e.printStackTrace();
			setAttribute(req, "error", new Exception(Mensajes.ERROR_GENERAL));
			callPage(req, res, JspServlet.ERROR_GENERAL_JSP);
		}
		catch (javax.servlet.ServletException se) {
			se.printStackTrace();
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
		Pagos bean = (Pagos) obj;
		bean.setError(null);
		if(!bean.getNumprdsrc().equals("")){
			if(!CFormat.isNumeric(bean.getNumprdsrc())){
				bean.setError(Mensajes.getMessage(Mensajes.NRO_CTA_INVALIDO));
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
