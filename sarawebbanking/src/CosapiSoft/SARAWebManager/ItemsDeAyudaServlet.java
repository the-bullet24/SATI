package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class ItemsDeAyudaServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public ItemsDeAyudaServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException {
	try {
	    if (Constante.SESSION_CADUCA.equals("2")) {
			try {
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
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
		AyudaDeCampo thlpdat = (AyudaDeCampo) (req.getSession(false).getAttribute("thlpdat"));
		String value = req.getParameter("BtnHlp");
		value = (value == null) ? "Otro" : value.trim();
		ItemsDeAyuda bean;
		if (req.getSession(false).getAttribute("thlpdet") != null) {
			bean = (ItemsDeAyuda) (req.getSession(false).getAttribute("thlpdet"));
		}
		else {
			bean = new ItemsDeAyuda();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("thlpdet", bean);
		}
		bean.setCodhlp(thlpdat.getCodhlp());
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.ELEMENTOS_DE_AYUDA));
		if (value.equalsIgnoreCase("Agregar")) {
			//bean.getLogin().setCodact("10");
			bean.getLogin().setAccion("Inserción");
			/**
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			*/
			String str = req.getParameter("TxtNumseq");
			bean.setNumseq((str == null || str.equalsIgnoreCase("")) ? "0" : str.trim());
			bean.setTxthlp(req.getParameter("TxtTxthlp"));
			bean.setTxthlpdat(req.getParameter("TxtTxthlpdat"));
			if(req.getParameter("TxtCodhlpdat")==null || req.getParameter("TxtCodhlpdat").equals("-")){
				bean.setCodhlpdat("");
			}
			else{
				bean.setCodhlpdat(req.getParameter("TxtCodhlpdat"));
			}
			if (!validate(bean)) {
				bean.insert();
				if (bean.getError().equalsIgnoreCase("")) {
					bean.setNumseq("");
					bean.setTxthlp("");
					bean.setTxthlpdat("");
					bean.setCodhlpdat("");
				}
			}
			callPage(req, res, JspServlet.ELEMENTOS_DE_AYUDA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Modificar")) {
			//bean.getLogin().setCodact("10");
			bean.getLogin().setAccion("Modificación");
			/**
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			*/
			String str = req.getParameter("TxtNumseq");
			bean.setNumseq((str == null || str.equalsIgnoreCase("")) ? "0" : str);
			bean.setTxthlp(req.getParameter("TxtTxthlp"));
			bean.setTxthlpdat(req.getParameter("TxtTxthlpdat"));
			if(req.getParameter("TxtCodhlpdat")==null || req.getParameter("TxtCodhlpdat").equals("-")){
				bean.setCodhlpdat("");
			}
			else{
				bean.setCodhlpdat(req.getParameter("TxtCodhlpdat"));
			}
			if (!validate(bean)) {
				bean.update();
				if (bean.getError().equalsIgnoreCase("")) {
					bean.setNumseq("");
					bean.setTxthlp("");
					bean.setTxthlpdat("");
					bean.setCodhlpdat("");
				}
			}
			callPage(req, res, JspServlet.ELEMENTOS_DE_AYUDA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			String str = req.getParameter("TxtNumseq");
			bean.setNumseq((str == null || str.equalsIgnoreCase("")) ? "0" : str);
			bean.search();
			callPage(req, res, JspServlet.ELEMENTOS_DE_AYUDA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Eliminar")) {
			//bean.getLogin().setCodact("10");
			bean.getLogin().setAccion("Eliminación");
			/**
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			*/
			bean.delete(req);
			bean.setNumseq("");
			bean.setTxthlp("");
			bean.setTxthlpdat("");
			bean.setCodhlpdat("");
			callPage(req, res, JspServlet.ELEMENTOS_DE_AYUDA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Regresar")) {
			callPage(req, res, JspServlet.AYUDAS_DE_CAMPO_JSP);
			return;
		}
		bean.consult();
		bean.setGrid(null);
		bean.loadGrid();
		bean.setError("");
		bean.setNumseq("");
		bean.setTxthlp("");
		bean.setTxthlpdat("");
		bean.setCodhlpdat("");
		callPage(req, res, JspServlet.ELEMENTOS_DE_AYUDA_JSP);
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
	ItemsDeAyuda bean = (ItemsDeAyuda) obj;
	try {
		bean.setError("");
		if (bean.getCodhlp().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_AYUDA));
			return true;
		}
		if (!CString.isInteger(bean.getCodhlp())) {
			bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_AYUDA_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (bean.getNumseq().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NRO_SECUENCIA));
			return true;
		}
		if (!CString.isInteger(bean.getNumseq())) {
			bean.setError(Mensajes.getMessage(Mensajes.NRO_SECUENCIA_DEBE_CONTENER_NUMEROS));
			return true;
		}
		int seq = new Integer(bean.getNumseq()).intValue();
		bean.setNumseq("" + seq);
		if (seq < 0) {
			bean.setError(Mensajes.getMessage(Mensajes.NRO_SECUENCIA_DEBE_MAYOR_A_CERO));
			return true;
		}
		if (bean.getTxthlp().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_DESCRIPCION_AYUDA));
			return true;
		}
		if (bean.getTxthlpdat().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_VALOR_AYUDA));
			return true;
		}
		return false;
	}
	catch (Exception e) {
		throw new Exception("\n... validate() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}