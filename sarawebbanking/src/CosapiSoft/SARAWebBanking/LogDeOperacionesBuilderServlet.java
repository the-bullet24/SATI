package CosapiSoft.SARAWebBanking;

/**
 * This type was created in VisualAge.
 */
public class LogDeOperacionesBuilderServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public LogDeOperacionesBuilderServlet() {
	super();
}
public void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {
	doPost(req,res);
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException {
	try {
		if (!isLoggedIn(req)) {
			try {
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));         
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		String value = req.getParameter("BtnLog");
		String modulo = "builder";
		//
		LogDeOperaciones bean;
		if (req.getSession(false).getAttribute("logBuilder") != null) {
			bean = ((LogDeOperaciones) req.getSession(false).getAttribute("logBuilder"));
		}
		else {
			bean = new LogDeOperaciones();
			req.getSession(false).setAttribute("logBuilder", bean);
		}
		bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute(modulo));
		bean.setModulo(modulo);
		bean.setError("");
		//
		if (value.equals("Otro")) {
			//			bean.clearGrid();
			bean.getLogin().setError("");
			bean.getLogin().setNamePantalla("LOG DE OPERACIONES");
			System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
			bean.getLogin().setCodact("206");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect(JspServlet.ERROR_BUILDER_JSP);
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
		}
		//
		if (value.equals("Cargar Tabla")) {
			//			res.getWriter().println("Log de Operaciones - modulo = " + modulo + ", Btn = " + value);
			bean.setGrid(null);
			bean.setCodusr(req.getParameter("CmbCodusr"));
			bean.setTxtope(req.getParameter("CmbTxtope"));
			//
			bean.setHorpro1(req.getParameter("Horpro1Hour") + req.getParameter("Horpro1Minute") + req.getParameter("Horpro1Second"));
			bean.setHorpro2(req.getParameter("Horpro2Hour") + req.getParameter("Horpro2Minute") + req.getParameter("Horpro2Second"));
			bean.setDatpro1(req.getParameter("Datpro1Year") + req.getParameter("Datpro1Month") + req.getParameter("Datpro1Day"));
			bean.setDatpro2(req.getParameter("Datpro2Year") + req.getParameter("Datpro2Month") + req.getParameter("Datpro2Day"));
			if (!validate(bean)) {
				bean.loadGrid();
			}
			//			res.sendRedirect(JspServlet.LOG_DE_OPERACIONES_BUILDER_JSP);
			callPage(req, res, JspServlet.LOG_DE_OPERACIONES_BUILDER_JSP);
			return;
		}
		if (value.equals("Ver Detalle")) {
			if (bean.goTo(req.getParameter("ChkLog")))
				res.sendRedirect(JspServlet.DETALLE_LOG_DE_OPERACIONES_BUILDER_SERVLET + "?BtnLog=Otro&Modulo=" + modulo);
			else {
				//				res.sendRedirect(JspServlet.LOG_DE_OPERACIONES_BUILDER_JSP);
				callPage(req, res, JspServlet.LOG_DE_OPERACIONES_BUILDER_JSP);
			}
			return;
		}
		if (value.equals("Buscar")) {
			if (bean.goTo(req.getParameter("ChkLog")))
				res.sendRedirect(JspServlet.DETALLE_LOG_DE_OPERACIONES_BUILDER_SERVLET + "?BtnLog=Otro&Modulo=" + modulo);
			else {
				//				res.sendRedirect(JspServlet.LOG_DE_OPERACIONES_BUILDER_JSP);
				callPage(req, res, JspServlet.LOG_DE_OPERACIONES_BUILDER_JSP);
			}
			return;
		}
		//		bean.loadComboUsr();
		bean.setError("");
		//		res.sendRedirect(JspServlet.LOG_DE_OPERACIONES_BUILDER_JSP);
		callPage(req, res, JspServlet.LOG_DE_OPERACIONES_BUILDER_JSP);
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
public boolean validate(Object obj) throws Exception {
	try {
		LogDeOperaciones bean = (LogDeOperaciones) obj;
		// System.out.println(bean.getDatpro1());
		// System.out.println(bean.getDatpro2());
		// System.out.println(bean.getHorpro1());
		// System.out.println(bean.getHorpro2());
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
			/*if (bean.getHorpro1().length() < 8) {
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_OCHO_CARACTERES_HORA) + " (Desde)");
				return true;
			}*/
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
			/*if (bean.getHorpro2().length() < 8) {
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_OCHO_CARACTERES_HORA) + " (Hasta)");
				return true;
			}*/
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