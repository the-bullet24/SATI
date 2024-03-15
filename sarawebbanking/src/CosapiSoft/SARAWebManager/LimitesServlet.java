package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;


public class LimitesServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public LimitesServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	try {
		if (!isLoggedIn(req)) {
			try {
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		String value = req.getParameter("BtnLim");
		value = (value == null) ? "Otro" : value.trim();
		Limites bean;
		if (req.getSession(false).getAttribute("tlimdat") != null) {
			bean = (Limites) (req.getSession(false).getAttribute("tlimdat"));
		}
		else {
			bean = new Limites();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("tlimdat", bean);
		}

		//

		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.LIMITES));
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		if (value.equalsIgnoreCase("Otro")) {
			/**
			*bean.getLogin().setCodact("94");
			*bean.getLogin().setAccion("Consulta");
			*if (!bean.getLogin().hasAccesAccion()) {
			*/
				bean.getLogin().setCodact("306");
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
			bean.getLogin().setCodact("306");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setTypper(req.getParameter("cboPersona"));
			bean.setTrahst(req.getParameter("TxtTransaccion").toUpperCase());
			bean.setLimopeinf(req.getParameter("TxtLimopeinf"));
			bean.setLimopesup(req.getParameter("TxtLimopesup"));
			bean.setLimdayinf(req.getParameter("TxtLimdayinf"));
			bean.setLimdaysup(req.getParameter("TxtLimdaysup"));
			bean.setCodcur(req.getParameter("cboMoneda"));
			
			if (!validate(bean)) {
				bean.insert();
				if (bean.getError().equalsIgnoreCase("")) {
					bean.setTypper("");
					bean.setTrahst("");
					bean.setLimopeinf("");
					bean.setLimopesup("");
					bean.setLimdayinf("");
					bean.setLimdaysup("");
					bean.setCodcur("");
				}
			}
			callPage(req, res, JspServlet.LIMITES_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Modificar")) {
			bean.getLogin().setCodact("306");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setTypper(req.getParameter("cboPersona"));
			bean.setTrahst(req.getParameter("TxtTransaccion"));
			bean.setLimopeinf(req.getParameter("TxtLimopeinf"));
			bean.setLimopesup(req.getParameter("TxtLimopesup"));
			bean.setLimdayinf(req.getParameter("TxtLimdayinf"));
			bean.setLimdaysup(req.getParameter("TxtLimdaysup"));
			bean.setCodcur(req.getParameter("cboMoneda"));
			// System.out.println("limite diario inferior.."+bean.getLimdayinf());
			if (!validate(bean)) {
				bean.update();
				if (bean.getError().equalsIgnoreCase("")) {
					bean.setTypper("");
					bean.setTrahst("");
					bean.setLimopeinf("");
					bean.setLimopesup("");
					bean.setLimdayinf("");
					bean.setLimdaysup("");
					bean.setCodcur("");
				}
			}
			callPage(req, res, JspServlet.LIMITES_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Eliminar")) {
			bean.getLogin().setCodact("306");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			
			bean.delete(req);
			
			bean.setTypper("");
			bean.setTrahst("");
			bean.setLimopeinf("");
			bean.setLimopesup("");
			bean.setLimdayinf("");
			bean.setLimdaysup("");
			bean.setCodcur("");
			callPage(req, res, JspServlet.LIMITES_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setTypper((req.getParameter("cboPersona")).substring(0,2));
			// System.out.println("tipo de persona"+(req.getParameter("cboPersona")).substring(0,2));
			bean.setTrahst(req.getParameter("TxtTransaccion"));
			bean.setCodcur(req.getParameter("cboMoneda"));
			
			bean.search();
			callPage(req, res, JspServlet.LIMITES_JSP);
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
		bean.loadGrid();
		bean.setTypper("");
		bean.setTrahst("");
		bean.setLimopeinf("");
		bean.setLimopesup("");
		bean.setLimdayinf("");
		bean.setLimdaysup("");
		bean.setCodcur("");
		callPage(req, res, JspServlet.LIMITES_JSP);
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
	Limites bean = (Limites) obj;
	try {
		bean.setError("");
		// System.out.println("entro al validate");
		if (bean.getLimdayinf().equals("") || bean.getLimdaysup().equals("") || bean.getLimopeinf().equals("") || bean.getLimopesup().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.LIMITE_NULO));
			return true;
		}
		if (!CFormat.validarImporte(bean.getLimdayinf()) || !CFormat.validarImporte(bean.getLimdaysup()) || !CFormat.validarImporte(bean.getLimopeinf()) || !CFormat.validarImporte(bean.getLimopesup())) {
			bean.setError(Mensajes.getMessage(Mensajes.LIMITE_INCORRECTO));
			return true;
		}
		if((Float.parseFloat(bean.getLimdayinf()) >= Float.parseFloat(bean.getLimdaysup()))){
			bean.setError(Mensajes.getMessage(Mensajes.LIMITE_INF_MAYOR_SUP));
			return true;
		}
		if((Float.parseFloat(bean.getLimopeinf()) >= Float.parseFloat(bean.getLimopesup()))){
			bean.setError(Mensajes.getMessage(Mensajes.LIMITE_INF_MAYOR_SUP));
			return true;
		}
		if((Float.parseFloat(bean.getLimdayinf()) <= Float.parseFloat(bean.getLimopeinf())) || (Float.parseFloat(bean.getLimdaysup()) <= Float.parseFloat(bean.getLimopesup()))){
			bean.setError(Mensajes.getMessage(Mensajes.LIMITE_OPE_MAYOR_DIA));
			return true;
		}
		if(bean.getTrahst().equals("")){
			bean.setError(Mensajes.getMessage(Mensajes.TRANSACCION_INVALIDA));
			return true;
		}
		if(!CFormat.ValidoAN(bean.getTrahst())){
			bean.setError(Mensajes.getMessage(Mensajes.TRANSACCION_INVALIDA));
			return true;
		}
			
		return false;
	}
	catch (Exception e) {
		e.printStackTrace();
		throw new Exception("\n... validate() - " + getClass().getName() + " -> " + e.getMessage());
	}
	
}
}