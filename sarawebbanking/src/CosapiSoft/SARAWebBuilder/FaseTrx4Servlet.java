package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class FaseTrx4Servlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public FaseTrx4Servlet() {
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
		Fase1 bean = (Fase1) req.getSession(false).getAttribute("ttrafas");
		String value = req.getParameter("BtnEsq");
		value = (value == null) ? "Otro" : value.trim();
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.ASIG_DE_ARGUMENTOS_FASE));
		if (value.equalsIgnoreCase("Regresar")) {
			String back = Transacciones.pop();
			back = (back.equals("")) ? JspServlet.FASE_TRX3_JSP : back;
			callPage(req, res, back);
			return;
		}
		if (value.equalsIgnoreCase("Metodo")) {
			bean.setCodmet(req.getParameter("TxtCodmet"));
			bean.setTxtmet(req.getParameter("TxtTxtmet").replace('_', ' '));
			bean.insert();
		}
		if (value.equalsIgnoreCase("Agregar")) {
			bean.setTxtarg(req.getParameter("TxtTxtarg"));
			if (bean.getTxtarg().equals("")) {
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_VALOR_ARGUMENTO));
			}
			else {
				bean.insertTmetargalt();
			}
			bean.setTxtarg("");
			bean.setNumseq("");
		}
		if (value.equalsIgnoreCase("Modificar")) {
			bean.setNumseq(req.getParameter("TxtNumseqarg"));
			bean.setTxtarg(req.getParameter("TxtTxtarg"));
			if (bean.getTxtarg().equals("")) {
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_VALOR_ARGUMENTO));
			}
			else {
				bean.updateTmetargalt();
			}
			bean.setTxtarg("");
			bean.setNumseq("");
		}
		if (value.equalsIgnoreCase("Eliminar")) {
			bean.deleteTmetargalt(req);
			bean.setTxtarg("");
			bean.setNumseq("");
		}
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setNumseq(req.getParameter("TxtNumseqarg"));
			bean.searchTmetargalt();
		}
		if (value.equalsIgnoreCase("Fase")) {
			Transacciones.push(JspServlet.FASE_TRX4_JSP);
			bean.setTxtarg("");
			bean.setNumseq("");
			res.sendRedirect(JspServlet.FASE_SERVLET + "?BtnEsq=Otro");
			return;
		}
		if (value.equalsIgnoreCase("Argumentos")) {
			Transacciones.push(JspServlet.FASE_JSP);
			bean.setCodfas(req.getParameter("TxtCodfas"));
			bean.setCodalt(req.getParameter("TxtCodalt"));
			bean.setCodcla(req.getParameter("TxtCodcla"));
			bean.setCodmet(req.getParameter("TxtCodmet"));
			bean.setTxtcla(req.getParameter("TxtTxtcla").replace('_', ' '));
			bean.setTxtmet(req.getParameter("TxtTxtmet").replace('_', ' '));
			bean.loadGridTmetargalt();
		}
		callPage(req, res, JspServlet.FASE_TRX4_JSP);
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