package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class EsquemaServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public EsquemaServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	InicioSesion login = new InicioSesion();
	login.setNameProducto("SARA Web Builder");
	try {
		if (!isLoggedIn(req)) {
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
		Fase1 ttrafas = (Fase1) req.getSession(false).getAttribute("ttrafas");
		Esquema1 bean = null;
		if (req.getSession(false).getAttribute("tschtra") != null) {
			bean = (Esquema1) req.getSession(false).getAttribute("tschtra");
		}
		else {
			bean = new Esquema1();
			bean.setLogin((InicioSesion) req.getSession(false).getAttribute("builder"));
			req.getSession(false).setAttribute("tschtra", bean);
		}
		bean.setCodtra(ttrafas.getCodtra());
		bean.setTxttra(ttrafas.getTxttra());
		bean.setGridArgs(null);
		//
		/*bean.getLogin().setCodact("37");
		bean.getLogin().setAccion("");
		if (!bean.getLogin().hasAccesAccion()) {
		callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
		return;
		}*/
		//
		String value = req.getParameter("BtnEsq");
		value = (value == null) ? "Otro" : value.trim();
		bean.setError("");
		if (value.equalsIgnoreCase("Esquema")) {
			bean.setCodfas(req.getParameter("TxtCodfas"));
			bean.setCodalt(req.getParameter("TxtCodalt"));
			bean.setAlt(req.getParameter("TxtAlt"));
			bean.consult();
			bean.setGrid(null);
			bean.loadGrid();
		}
		if (value.equalsIgnoreCase("Otro")) {
			bean.loadGrid();
		}
		if (value.equalsIgnoreCase("Regresar")) {
			String back = Transacciones.pop();
			back = (back.equals("")) ? JspServlet.FASE_JSP : back;
			callPage(req, res, back);
			return;
		}
		if (value.equalsIgnoreCase("Fase")) {
			Transacciones.push(JspServlet.ESQUEMA_JSP);
			callPage(req, res, JspServlet.FASE_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Agregar Método")) {
			bean.setNumseq(bean.nextNumseq());
			bean.setFunction("ADD_METODO");
			Transacciones.push(JspServlet.ESQUEMA_JSP);
			// System.out.println("AAAAAAAAAAAAAAAAAAA:"+value);
			// System.out.println("BBBBBBBBBBBBBBBBBBB:"+JspServlet.ESQUEMA_JSP);
			// System.out.println("BBBBBBBBBBBBBBBBBBB:"+JspServlet.ESQUEMA_TRX2_JSP);
			callPage(req, res, "/sarawebbanking/SARAWebBuilder/Esquema/Esquema2.jsp");
			return;
			//JspServlet.ESQUEMA_TRX2_JSP
		}
		if (value.equalsIgnoreCase("Insertar Método")) {
			bean.setNumseq("");
			bean.setFunction("INSERT_METODO");
			Transacciones.push(JspServlet.ESQUEMA_JSP);
			callPage(req, res, JspServlet.ESQUEMA_TRX1_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Eliminar")) {
			bean.delete(req);
			Transacciones.push(JspServlet.FASE_TRX0_JSP);
		}
		if (value.equalsIgnoreCase("Modificar")) {
			bean.update(req);
		}
		callPage(req, res, JspServlet.ESQUEMA_JSP);
	}
	catch (Exception e) {
		try {
			e.printStackTrace();
			setAttribute(req, "error", new Exception(Mensajes.ERROR_GENERAL));
			setAttribute(req, "login", login);
			callPage(req, res, JspServlet.ERROR_JSP);
		}
		catch (Exception se) {
			res.getWriter().println(se.getMessage());
		}
	}
}
public boolean validate(Object obj) throws Exception {
	return false;
}
}