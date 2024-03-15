package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;


public class OficinaServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public OficinaServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
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
		String value = req.getParameter("BtnOfi");
		value = (value == null) ? "Otro" : value.trim();
		Oficina bean;
		if (req.getSession(false).getAttribute("tofidat") != null) {
			bean = (Oficina) (req.getSession(false).getAttribute("tofidat"));
		}
		else {
			bean = new Oficina();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("tofidat", bean);
		}

		//

		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("AGENCIAS");
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		if (value.equalsIgnoreCase("Otro")) {
			/**
			*bean.getLogin().setCodact("94");
			*bean.getLogin().setAccion("Consulta");
			*if (!bean.getLogin().hasAccesAccion()) {
			*/
				bean.getLogin().setCodact("324");
				bean.getLogin().setAccion("Consulta");
				if (!bean.getLogin().hasAccesAccion()) {
					callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
					return;
				}
			/**}
			 * 
			 */
			bean.consult();
		}
		if (value.equalsIgnoreCase("Agregar")) {
			bean.getLogin().setCodact("324");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCubigeo(req.getParameter("txtCubigeo"));
			bean.setCoddep4(req.getParameter("txtCoddep4"));
			bean.setCoficina(req.getParameter("txtCoficina"));
			bean.setAoficina(req.getParameter("txtAoficina"));
			bean.setFlgmoneda(req.getParameter("cboMoneda"));
			
			if (!validate(bean)) {
				bean.setGrid(null);
				bean.insert();
				bean.loadGrid();
				if (bean.getError().equalsIgnoreCase("")) {
					bean.setCubigeo("");
					bean.setCoddep4("");
					bean.setCoficina("");
					bean.setAoficina("");
					bean.setFlgmoneda("");
				}
			}
			bean.loadGrid();
			callPage(req, res, JspServlet.OFICINA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Modificar")) {
			bean.getLogin().setCodact("324");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCubigeo(req.getParameter("txtCubigeo"));
			bean.setCoddep4(req.getParameter("txtCoddep4"));
			bean.setCoficina(req.getParameter("txtCoficina"));
			bean.setAoficina(req.getParameter("txtAoficina"));
			bean.setFlgmoneda(req.getParameter("cboMoneda"));
			// System.out.println("limite diario inferior.."+bean.getLimdayinf());
			if (!validate(bean)) {
				bean.setGrid(null);
				bean.update();
				bean.loadGrid();
				if (bean.getError().equalsIgnoreCase("")) {
					bean.setCubigeo("");
					bean.setCoddep4("");
					bean.setCoficina("");
					bean.setAoficina("");
					bean.setFlgmoneda("");
				}
			}
			
			callPage(req, res, JspServlet.OFICINA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Eliminar")) {
			bean.getLogin().setCodact("324");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setGrid(null);
			bean.delete(req);
			
			bean.setCubigeo("");
			bean.setCoddep4("");
			bean.setCoficina("");
			bean.setAoficina("");
			bean.setFlgmoneda("");
			bean.loadGrid();
			callPage(req, res, JspServlet.OFICINA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setCoddep4(req.getParameter("txtCoddep4"));
			bean.setGrid(null);
			
			bean.search();
			bean.loadGrid();
			callPage(req, res, JspServlet.OFICINA_JSP);
			return;
		}
		
		
		bean.setError("");
		bean.setGrid(null);
		bean.setCubigeo("");
		bean.setCoddep4("");
		bean.setCoficina("");
		bean.setAoficina("");
		bean.setFlgmoneda("");
		bean.setCod_ubigeo("");
		bean.setCod_dep4("");
		bean.setF01_coficina("");
		bean.setF01_aoficina("");
		bean.setFlg_moneda("");
		bean.loadGrid();
		callPage(req, res, JspServlet.OFICINA_JSP);
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
		Oficina bean = (Oficina) obj;
		
		if (bean.getCoddep4().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.OFIDAT_CODDEP4_BLANCO));
			return true;
		}
		if (bean.getCubigeo().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.OFIDAT_CUBIGEO_BLANCO));
			return true;
		}
		if (bean.getAoficina().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.OFIDAT_AOFICINA_BLANCO));
			return true;
		}
		if (bean.getCoficina().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.OFIDAT_COFICINA_BLANCO));
			return true;
		}
		
		return false;
	}
	catch (Exception e) {
		throw new Exception("validate() -> LogDeOperacionesServlet - " + e.getMessage());
	}
	
}
}