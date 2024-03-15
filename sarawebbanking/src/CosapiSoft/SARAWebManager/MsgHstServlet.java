package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;


public class MsgHstServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public MsgHstServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	try {
	    System.out.println("Sesion de CADUCIDAD"+Constante.SESSION_CADUCA);
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
		String value = req.getParameter("BtnHst");
		value = (value == null) ? "Otro" : value.trim();
		MsgHst bean;
		if (req.getSession(false).getAttribute("tmsghst") != null) {
			bean = (MsgHst) (req.getSession(false).getAttribute("tmsghst"));
		}
		else {
			bean = new MsgHst();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("tmsghst", bean);
		}

		//

		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("MENSAJES DE HOST");
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		if (value.equalsIgnoreCase("Otro")) {
			/**
			*bean.getLogin().setCodact("94");
			*bean.getLogin().setAccion("Consulta");
			*if (!bean.getLogin().hasAccesAccion()) {
			*/
				bean.getLogin().setCodact("323");
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
			bean.getLogin().setCodact("323");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCodaplicacion(req.getParameter("txtCodaplicacion"));
			bean.setCodretorno(req.getParameter("txtCodretorno"));
			bean.setDesmsgtold(req.getParameter("txtDesmsgtold"));
			bean.setDesmsgfront(req.getParameter("txtDesmsgfront"));
			
			if (!validate(bean)) {
				bean.setGrid(null);
				bean.insert();
				bean.loadGrid();
				if (bean.getError().equalsIgnoreCase("")) {
					bean.setCodaplicacion("");
					bean.setCodretorno("");
					bean.setDesmsgtold("");
					bean.setDesmsgfront("");
				}
			}
			bean.loadGrid();
			callPage(req, res, JspServlet.MSGHST_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Modificar")) {
			bean.getLogin().setCodact("323");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCodaplicacion(req.getParameter("txtCodaplicacion"));
			bean.setCodretorno(req.getParameter("txtCodretorno"));
			bean.setDesmsgtold(req.getParameter("txtDesmsgtold"));
			bean.setDesmsgfront(req.getParameter("txtDesmsgfront"));
			// System.out.println("limite diario inferior.."+bean.getLimdayinf());
			if (!validate(bean)) {
				bean.setGrid(null);
				bean.update();
				bean.loadGrid();
				if (bean.getError().equalsIgnoreCase("")) {
					bean.setCodaplicacion("");
					bean.setCodretorno("");
					bean.setDesmsgtold("");
					bean.setDesmsgfront("");
				}
			}
			
			callPage(req, res, JspServlet.MSGHST_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Eliminar")) {
			bean.getLogin().setCodact("323");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setGrid(null);
			bean.delete(req);
			
			bean.setCodaplicacion("");
			bean.setCodretorno("");
			bean.setDesmsgtold("");
			bean.setDesmsgfront("");
			bean.loadGrid();
			callPage(req, res, JspServlet.MSGHST_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			// System.out.println("tipo de persona"+(req.getParameter("cboPersona")).substring(0,2));
			bean.setCodaplicacion(req.getParameter("txtCodaplicacion"));
			bean.setCodretorno(req.getParameter("txtCodretorno"));
			System.out.println("cod apli...."+req.getParameter("txtCodaplicacion"));
			System.out.println("cod ret...."+req.getParameter("txtCodretorno"));
			bean.setGrid(null);
			
			bean.search();
			bean.loadGrid();
			callPage(req, res, JspServlet.MSGHST_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Ver Horarios")) {
			// System.out.println("tipo de persona"+(req.getParameter("cboPersona")).substring(0,2));
			bean.setCodaplicacion(req.getParameter("txtCodaplicacion"));
			bean.setGrid(null);
			if (!validate(bean)) {
			bean.loadGrid();
			}
			callPage(req, res, JspServlet.MSGHST_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Detalle")) {
			/*bean.setTypper(req.getParameter("cboPersona"));
			bean.setTrahst(req.getParameter("TxtTransaccion"));
			if (!CString.isInteger(bean.getCodlim())) {
				bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_LIMITE_DEBE_CONTENER_NUMEROS));
			}
			else {
				if (bean.search()) {
					res.sendRedirect(JspServlet.LIMITES_DETALLE_SERVLET + "?TxtCodlim=" + bean.getCodlim() + "&BtnHlp=Otro");
				}
				else {
					String str = Mensajes.getMessage(Mensajes.CODIGO_DE_LIMITE_NO_EXISTE);
					int pos = str.indexOf('&');
					bean.setError(str.substring(0, pos) + bean.getCodlim() + str.substring(pos + 1));
					callPage(req, res, JspServlet.LIMITES_JSP);
				}
				return;
			}
			callPage(req, res, JspServlet.LIMITES_JSP);
			return;*/
		}
		bean.setError("");
		bean.setGrid(null);
		bean.setCodaplicacion("");
		bean.setCodretorno("");
		bean.setDesmsgtold("");
		bean.setDesmsgfront("");
		bean.setCod_aplicacion("");
		bean.setCod_retorno("");
		bean.setDes_msgtold("");
		bean.setDes_msgfront("");
		bean.loadGrid();
		callPage(req, res, JspServlet.MSGHST_JSP);
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
		MsgHst bean = (MsgHst) obj;
		
		if (bean.getCodaplicacion().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.MSGHST_APL_BLANCO));
			return true;
		}
		if (bean.getCodretorno().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.MSGHST_RETORNO_BLANCO));
			return true;
		}
		if (bean.getDesmsgtold().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.MSGHST_DESTOLD_BLANCO));
			return true;
		}
		if (bean.getDesmsgfront().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.MSGHST_DESHOST_BLANCO));
			return true;
		}
		
		return false;
	}
	catch (Exception e) {
		throw new Exception("validate() -> LogDeOperacionesServlet - " + e.getMessage());
	}
	
}
}