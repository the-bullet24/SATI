package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class EsquemaTrx4Servlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public EsquemaTrx4Servlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
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
		Esquema1 bean = (Esquema1) req.getSession(false).getAttribute("tschtra");
		bean.setOrdsnd("0");
		String value = req.getParameter("BtnEsq");
		value = (value == null) ? "Otro" : value.trim();
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.ASIG_DE_ARGUMENTOS_ESQUEMA));
		if (value.equalsIgnoreCase("Regresar")) {
			String back = Transacciones.pop();
			back = (back.equals("")) ? JspServlet.ESQUEMA_TRX3_JSP : back;
			callPage(req, res, back);
			return;
		}
		if (value.equalsIgnoreCase("Metodo")) {
			bean.setCodmet(req.getParameter("TxtCodmet"));
			bean.setTxtmet(req.getParameter("TxtTxtmet").replace('_', ' '));
			//DPS A1
			bean.setDes_met(CString.toStringOfHtml(req.getParameter("TxtDesmet")));
			bean.insert();
		}
		if (value.equalsIgnoreCase("Agregar")) {
			bean.setTxtarg(req.getParameter("TxtTxtarg"));
			if (bean.getTxtarg().equals("")) {
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_VALOR_ARGUMENTO));
			}
			else {
				bean.insertTmetarg();
			}
			bean.setTxtarg("");
			bean.setNumseqarg("");
		}
		if (value.equalsIgnoreCase("Modificar")) {
			bean.setNumseqarg(req.getParameter("TxtNumseqarg"));
			bean.setTxtarg(req.getParameter("TxtTxtarg"));
			if (bean.getTxtarg().equals("")) {
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_VALOR_ARGUMENTO));
			}
			else {
				bean.updateTmetarg();
			}
			bean.setTxtarg("");
			bean.setNumseqarg("");
		}
		if (value.equalsIgnoreCase("Eliminar")) {
			bean.deleteTmetarg(req);
			bean.setTxtarg("");
			bean.setNumseqarg("");
		}
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setNumseqarg(req.getParameter("TxtNumseqarg"));
			bean.searchTmetarg();
		}
		if (value.equalsIgnoreCase("Esquema")) {
			bean.setTxtarg("");
			bean.setNumseqarg("");
			Transacciones.push(JspServlet.ESQUEMA_TRX4_JSP);
			res.sendRedirect(JspServlet.ESQUEMA_SERVLET + "?BtnEsq=Otro");
			return;
		}
		if (value.equalsIgnoreCase("Argumentos")) {
			Transacciones.push(JspServlet.ESQUEMA_JSP);
			bean.setCodfas(req.getParameter("TxtCodfas"));
			bean.setCodalt(req.getParameter("TxtCodalt"));
			bean.setCodcla(req.getParameter("TxtCodcla"));
			bean.setCodmet(req.getParameter("TxtCodmet"));
			bean.setTxtcla(req.getParameter("TxtTxtcla").replace('_', ' '));
			bean.setTxtmet(req.getParameter("TxtTxtmet").replace('_', ' '));
			//DPS A1
			bean.setDes_met(CString.toStringOfHtml(req.getParameter("TxtDesmet")));
			bean.setNumseq(req.getParameter("TxtNumseq"));
			bean.loadGridTmetarg();
		}
		callPage(req, res, JspServlet.ESQUEMA_TRX4_JSP);
	}
	catch (Exception e) {
		try {
			e.printStackTrace();
			setAttribute(req, "error", new Exception(Mensajes.ERROR_GENERAL));
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