package CosapiSoft.SARAWebBanking;

import pe.cosapi.common.Constante;


/**
 * This type was created in VisualAge.
 */

public class DiarioHistoricoServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public DiarioHistoricoServlet() {
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
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));
				setAttribute(req, "login", login);
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		String value = req.getParameter("BtnDia");
		//System.out.println("value........"+value);
		value = (value == null) ? "Otro" : value.trim();
		DiarioElectronico bean;
		
		bean = new DiarioElectronico();
		bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
		req.getSession(false).setAttribute("tjoutra", bean);
		bean.getLogin().setError("");
		bean.loadComboTransacciones();
		
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("DIARIO HISTORICO");
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		
		if (value.equals("Otro")) {
			if(req.getParameter("flgTable")!=null)
				Constante.FLG_TABLE=req.getParameter("flgTable");
			else
				Constante.FLG_TABLE=null;
				System.out.println("Constante.FLG_TABLE:"+ Constante.FLG_TABLE);
				System.out.println("req.getParameter(flgTable):"+ req.getParameter("flgTable"));
				
			bean.getLogin().setCodact("317");
			bean.getLogin().setAccion("Inserción");
			// System.out.println("empieza OTRO");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			//bean.clearGrid();

			bean.getLogin().setCodact("317");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			// System.out.println("termino OTRO");
		}
		//
		if (value.equals("Cargar Tabla")) {
			bean.getLogin().setCodact("317");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			//			res.getWriter().println("Log de Operaciones - modulo = " + modulo + ", Btn = " + value);
			bean.setGrid(null);
			bean.setCodtra(req.getParameter("CmbCodtra"));
			bean.setTypper(req.getParameter("CmbTypper"));
			bean.setDatpro1(req.getParameter("TxtDatpro1"));
			bean.setDatpro2(req.getParameter("TxtDatpro2"));
			bean.setHorpro1(req.getParameter("TxtHorpro1"));
			bean.setHorpro2(req.getParameter("TxtHorpro2"));
			
			// System.out.println("antes del load grid");
			if (!validate(bean)) {
				bean.loadGrid();
			}
			//			res.sendRedirect(JspServlet.LOG_DE_OPERACIONES_MANAGER_JSP);
			if(Constante.FLG_TABLE==null){
				callPage(req, res, JspServlet.DIARIO_ELECTRONICO_JSP);	
			}else{
				callPage(req, res, JspServlet.DIARIO_HISTORICO_JSP);
			}
			
			return;
		}
		if (value.equals("Refrendo")) {
			bean.setNumlog(req.getParameter("secuencial"));
			//System.out.println("secuencial..........."+req.getParameter("secuencial"));
			bean.Refrendo();
			callPage(req, res, JspServlet.REFRENDO_JSP);
			return;
		}
		if (value.equals("Buscar")) {
			//if (bean.goTo(req.getParameter("ChkLog")))
				res.sendRedirect(JspServlet.DETALLE_LOG_DE_OPERACIONES_MANAGER_SERVLET + "?BtnLog=Otro&Modulo=" + modulo);
			//else {
				//				res.sendRedirect(JspServlet.LOG_DE_OPERACIONES_MANAGER_JSP);
				if(Constante.FLG_TABLE==null){
					callPage(req, res, JspServlet.DIARIO_ELECTRONICO_JSP);	
				}else{
					callPage(req, res, JspServlet.DIARIO_HISTORICO_JSP);
				}
				
			//}
			return;
		}
		//		bean.loadComboUsr();
		bean.setError("");
		//		res.sendRedirect(JspServlet.LOG_DE_OPERACIONES_MANAGER_JSP);
		if(Constante.FLG_TABLE==null){
			callPage(req, res, JspServlet.DIARIO_ELECTRONICO_JSP);	
		}else{
			callPage(req, res, JspServlet.DIARIO_HISTORICO_JSP);
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
		DiarioElectronico bean = (DiarioElectronico) obj;
		System.out.println("bean.getDatpro1(): " + bean.getDatpro1());
		System.out.println("bean.getDatpro2(): " + bean.getDatpro2());
		/*if (!bean.getDatpro1().equals("") && !bean.getDatpro2().equals("")) {
			if (bean.getDatpro1().compareTo(bean.getDatpro2()) > 0) {
				bean.setError(Mensajes.getMessage(Mensajes.FECHA_DESDE_MENOR_A_FECHA_HASTA));
				return true;
			}
		}*/
		if (bean.getDatpro1().equals("") && !bean.getDatpro2().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.FECHA_ERROR));
			return true;
		}
		
		
		
		
		if (!bean.getHorpro1().equals("")) {
			/*if (bean.getHorpro1().length() < 8) {
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_OCHO_CARACTERES_HORA) + " (Desde)");
				return true;
			}*/
			if(bean.getHorpro1().length()<8){
				bean.setError(Mensajes.getMessage(Mensajes.HORA_INICIO_ERROR));
				return true;
			}
			
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
		else{
			if (!bean.getHorpro2().equals("")) {
				bean.setError(Mensajes.getMessage(Mensajes.HORA_ERROR));
				return true;
			}
		}
		if (!bean.getHorpro2().equals("")) {
			/*if (bean.getHorpro2().length() < 8) {
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_OCHO_CARACTERES_HORA) + " (Hasta)");
				return true;
			}*/
			if(bean.getHorpro2().length()<8){
				bean.setError(Mensajes.getMessage(Mensajes.HORA_FIN_ERROR));
				return true;
			}
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
