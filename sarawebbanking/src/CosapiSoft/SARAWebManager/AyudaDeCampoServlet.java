package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class AyudaDeCampoServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public AyudaDeCampoServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	try {
	    System.out.println("Sesion de CADUCIDAD"+Constante.SESSION_CADUCA);
	    if (Constante.SESSION_CADUCA.equals("2")) {
			try {
				req.getSession(false).setAttribute("error", new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				//req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
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
		String value = req.getParameter("BtnHlp");
		value = (value == null) ? "Otro" : value.trim();
		AyudaDeCampo bean;
		
		/**
		if (req.getSession(false).getAttribute("thlpdat") != null) {
			bean = (AyudaDeCampo) (req.getSession(false).getAttribute("thlpdat"));
		}
		else {
		*/
			bean = new AyudaDeCampo();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("thlpdat", bean);
		//}

		//

		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("AYUDA DE CAMPO");
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		if (value.equalsIgnoreCase("Otro")) {
			// System.out.println("Otro");
			bean.getLogin().setCodact("302");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.consult();
		}
		if (value.equalsIgnoreCase("Agregar")) {
			// System.out.println("Agregar");
			bean.getLogin().setCodact("302");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCodhlp(req.getParameter("TxtCodhlp"));
			bean.setTxthlp(req.getParameter("TxtTxthlp"));
			bean.setCodfat(req.getParameter("TxtCodfat"));
			if (!validate(bean)) {
				bean.insert();
				if (bean.getError().equalsIgnoreCase("")) {
					bean.setCodhlp("");
					bean.setTxthlp("");
					bean.setCodfat("");
				}
			}
			callPage(req, res, JspServlet.AYUDAS_DE_CAMPO_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Modificar")) {
			// System.out.println("Modificar");
			bean.getLogin().setCodact("302");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCodhlp(req.getParameter("TxtCodhlp"));
			bean.setTxthlp(req.getParameter("TxtTxthlp"));
			bean.setCodfat(req.getParameter("TxtCodfat"));
			if (!validate(bean)) {
				bean.update();
				if (bean.getError().equalsIgnoreCase("")) {
					bean.setCodhlp("");
					bean.setTxthlp("");
					bean.setCodfat("");
				}
			}
			callPage(req, res, JspServlet.AYUDAS_DE_CAMPO_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Eliminar")) {
			// System.out.println("Eliminar");
			bean.getLogin().setCodact("302");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.delete(req);
			bean.setCodhlp("");
			bean.setTxthlp("");
			bean.setCodfat("");
			callPage(req, res, JspServlet.AYUDAS_DE_CAMPO_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setCodhlp(req.getParameter("TxtCodhlp"));
			bean.search();
			callPage(req, res, JspServlet.AYUDAS_DE_CAMPO_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Elem. de Ayuda")) {
			bean.setCodhlp(req.getParameter("TxtCodhlp"));
			if (!CString.isInteger(bean.getCodhlp())) {
				bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_AYUDA_DEBE_CONTENER_NUMEROS));
			}
			else {
				if (bean.search()) {
					res.sendRedirect(JspServlet.ELEMENTOS_DE_AYUDA_SERVLET + "?TxtCodhlp=" + bean.getCodhlp() + "&BtnHlp=Otro");
				}
				else {
					String str = Mensajes.getMessage(Mensajes.CODIGO_DE_AYUDA_NO_EXISTE);
					int pos = str.indexOf('&');
					bean.setError(str.substring(0, pos) + bean.getCodhlp() + str.substring(pos + 1));
					callPage(req, res, JspServlet.AYUDAS_DE_CAMPO_JSP);
				}
				return;
			}
			callPage(req, res, JspServlet.AYUDAS_DE_CAMPO_JSP);
			return;
		}
		bean.setError("");
		bean.loadGrid();
		bean.setCodhlp("");
		bean.setTxthlp("");
		callPage(req, res, JspServlet.AYUDAS_DE_CAMPO_JSP);
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
	AyudaDeCampo bean = (AyudaDeCampo) obj;
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
		if (bean.getTxthlp().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_DE_LA_AYUDA));
			return true;
		}
		if(!bean.getCodfat().trim().equals("") && !bean.getCodfat().trim().equals("-")){
			// System.out.println("no hay datos");
			if(!bean.searchFat()){
			bean.setError(Mensajes.getMessage(Mensajes.CODIGO_PADRE_NO_EXISTE));
			return true;
			}
		}
		if (bean.getCodhlp().trim().equals(bean.getCodfat().trim())) {
			bean.setError(Mensajes.getMessage(Mensajes.CODIGO_PADRE_INVALIDO));
			return true;
		}
		
		return false;
	}
	catch (Exception e) {
		throw new Exception("\n... validate() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}