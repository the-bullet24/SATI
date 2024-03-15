package CosapiSoft.SARAWebBanking;

/**
 * This type was created in VisualAge.
 */
public class InicioDeDia1Servlet extends JavaServlet {
/**
 * InicioSesionServlet constructor comment.
 */
public InicioDeDia1Servlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	setCache(res, true);
	InicioSesion login = new InicioSesion();
	login.setNameProducto("Inicio de Dia");
	try {
		login.loadParameters();
		req.getSession(true).setAttribute("ini", login);
		//
		CosapiSoft.SARAWebManager.InformacionDeAgencias tbrainf = new CosapiSoft.SARAWebManager.InformacionDeAgencias();
		JDatabase jd = new JDatabase();
		JQuery db = new JQuery(jd.getConnection());
		try {
			boolean sw = tbrainf.existeCodbraInInf(db);
			if (!sw) {
				login.setError(Mensajes.getMessage(Mensajes.DEBE_CREAR_LA_SUCURSAL_VIRTUAL));
				//			res.sendRedirect("../../SARAWebBanking/InicioDeDia/InicioDeDia1.jsp");
				callPage(req, res, JspServlet.INICIO_DE_DIA1_JSP);
				return;
			}
		}
		catch (java.sql.SQLException sqle) {
			throw sqle;
		}
		finally {
			db.close();
			jd.close();
		}
		Mensajes.loadMessages();
		InicioDeDia idia;
		if (req.getSession(false).getAttribute("idia") == null) {
			idia = new InicioDeDia();
			idia.setLogin(login);
			idia.loadSucursalVirtual();
			idia.setIdSesion(req.getSession(false).getId());
			req.getSession(false).setAttribute("idia", idia);
		}
		else {
			idia = (InicioDeDia) req.getSession(false).getAttribute("idia");
			idia.clearGrid();
		}
		//
		String value = (req.getParameter("BtnIDia") == null) ? "Otro" : req.getParameter("BtnIDia");
		if (value.equals("Aceptar")) {
			login.setUsuario(req.getParameter("TxtUsuario"));
			login.setClave(req.getParameter("TxtClave"));
			if (login.isValidoLogin()) {
				if (login.getUsuario().equals(login.getClave())) {
					login.setError(Mensajes.getMessage(Mensajes.CAMBIO_DE_CLAVE));
					res.sendRedirect(JspServlet.MODIFICAR_CLAVE_SERVLET + "?TxtCodusr=" + login.getUsuario() + "&BtnClave=Otro");
					return;
				}
				else {
					login.setCodact("40");
					if (login.hasAccesIDia()) {
						res.sendRedirect(JspServlet.INICIO_DE_DIA2_SERVLET + "?BtnIDia=Otro");
						return;
					}
					else {
						String str = Mensajes.getMessage(Mensajes.NO_TIENE_PERMISOS_PARA_EJECUTAR_INICIO_DE_DIA);
						int pos = str.indexOf('&');
						login.setError(str.substring(0, pos) + login.getNombre() + str.substring(pos + 1));
					}
				}
			}
			//			res.sendRedirect("../../SARAWebBanking/InicioDeDia/InicioDeDia1.jsp");
			callPage(req, res, JspServlet.INICIO_DE_DIA1_JSP);
			return;
		}
		if (value.equals("Regresar")) {
			res.sendRedirect(JspServlet.SARA_WEB_BANKING_HTML);
			return;
		}
		login.setError("");
		//		res.getWriter().println("InicioDeDia1 Servlet -> paso 1 - value = "+value);
		//		res.sendRedirect("../../SARAWebBanking/InicioDeDia/InicioDeDia1.jsp");
		callPage(req, res, JspServlet.INICIO_DE_DIA1_JSP);
	}
	catch (Exception e) {
		try {
			e.printStackTrace();
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
	return false;
}
}