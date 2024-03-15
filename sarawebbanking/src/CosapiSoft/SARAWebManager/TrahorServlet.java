package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;


public class TrahorServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public TrahorServlet() {
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
		String value = req.getParameter("BtnHor");
		value = (value == null) ? "Otro" : value.trim();
		Trahor bean;
		if (req.getSession(false).getAttribute("ttrahor") != null) {
			bean = (Trahor) (req.getSession(false).getAttribute("ttrahor"));
		}
		else {
			bean = new Trahor();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("ttrahor", bean);
		}

		//

		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("HORARIO POR TRANSACCION");
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		if (value.equalsIgnoreCase("Otro")) {
			/**
			*bean.getLogin().setCodact("94");
			*bean.getLogin().setAccion("Consulta");
			*if (!bean.getLogin().hasAccesAccion()) {
			*/
				bean.getLogin().setCodact("322");
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
			bean.getLogin().setCodact("322");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setTrahst(req.getParameter("cboTransaccion").toUpperCase());
			bean.setHorini(req.getParameter("TxtHorini"));
			bean.setHorfin(req.getParameter("TxtHorfin"));
			bean.setDia(req.getParameter("cboDia"));
			
			if (!validate(bean)) {
				bean.setGrid(null);
				bean.insert();
				bean.loadGrid();
				if (bean.getError().equalsIgnoreCase("")) {
					bean.setTrahst("");
					bean.setHorini("");
					bean.setHorfin("");
					bean.setDia("");
				}
			}
			bean.loadGrid();
			callPage(req, res, JspServlet.HORTRA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Modificar")) {
			bean.getLogin().setCodact("322");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setTrahst(req.getParameter("cboTransaccion").toUpperCase());
			bean.setHorini(req.getParameter("TxtHorini"));
			bean.setHorfin(req.getParameter("TxtHorfin"));
			bean.setDia(req.getParameter("cboDia"));
			// System.out.println("limite diario inferior.."+bean.getLimdayinf());
			if (!validate(bean)) {
				bean.setGrid(null);
				bean.update();
				bean.loadGrid();
				if (bean.getError().equalsIgnoreCase("")) {
					bean.setTrahst("");
					bean.setHorini("");
					bean.setHorfin("");
					bean.setDia("");
				}
			}
			
			callPage(req, res, JspServlet.HORTRA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Eliminar")) {
			bean.getLogin().setCodact("322");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setGrid(null);
			bean.delete(req);
			
			bean.setTrahst("");
			bean.setHorini("");
			bean.setHorfin("");
			bean.setDia("");
			bean.loadGrid();
			callPage(req, res, JspServlet.HORTRA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			// System.out.println("tipo de persona"+(req.getParameter("cboPersona")).substring(0,2));
			bean.setTrahst(req.getParameter("cboTransaccion"));
			bean.setDia(req.getParameter("cboDia"));
			bean.setGrid(null);
			
			bean.search();
			bean.loadGrid();
			callPage(req, res, JspServlet.HORTRA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Ver Horarios")) {
			// System.out.println("tipo de persona"+(req.getParameter("cboPersona")).substring(0,2));
			bean.setTrahst(req.getParameter("cboTransaccion"));
			bean.setGrid(null);	
			bean.loadGrid();
			callPage(req, res, JspServlet.HORTRA_JSP);
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
		bean.setTrahst("");
		bean.setHorini("");
		bean.setHorfin("");
		bean.setDia("");
		bean.setTra_hst("");
		bean.setHor_ini("");
		bean.setHor_fin("");
		bean.setLim_dia("");
		bean.setNom_dia("");
		callPage(req, res, JspServlet.HORTRA_JSP);
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
		Trahor bean = (Trahor) obj;
		
		if (bean.getTrahst().equals("TODOS")) {
			bean.setError(Mensajes.getMessage(Mensajes.HORDIA_TXN_BLANCO));
			return true;
		}
		if (bean.getHorini().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.HORDIA_HORINI_BLANCO));
			return true;
		}
		if (bean.getHorfin().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.HORDIA_HORFIN_BLANCO));
			return true;
		}
		
		
		if (!bean.getHorini().equals("")) {
			/*if (bean.getHorpro1().length() < 8) {
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_OCHO_CARACTERES_HORA) + " (Desde)");
				return true;
			}*/
			if(bean.getHorini().length()<8){
				bean.setError(Mensajes.getMessage(Mensajes.HORA_INICIO_ERROR));
				return true;
			}
			
			CFormat f = new CFormat("00:00:00", bean.getHorini());
			if (f.isValidoText()) {
				bean.setHorini(f.changeFormat());
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
		else{
			if (!bean.getHorfin().equals("")) {
				bean.setError(Mensajes.getMessage(Mensajes.HORA_ERROR));
				return true;
			}
		}
		if (!bean.getHorfin().equals("")) {
			/*if (bean.getHorpro2().length() < 8) {
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_OCHO_CARACTERES_HORA) + " (Hasta)");
				return true;
			}*/
			if(bean.getHorfin().length()<8){
				bean.setError(Mensajes.getMessage(Mensajes.HORA_FIN_ERROR));
				return true;
			}
			CFormat f = new CFormat("00:00:00", bean.getHorfin());
			if (f.isValidoText()) {
				bean.setHorfin(f.changeFormat());
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
		if (!bean.getHorini().equals("") && !bean.getHorfin().equals("")) {
			if (bean.getHorini().compareTo(bean.getHorfin()) > 0) {
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