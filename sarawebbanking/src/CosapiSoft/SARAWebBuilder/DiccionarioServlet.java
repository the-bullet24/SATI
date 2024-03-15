package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class DiccionarioServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public DiccionarioServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	InicioSesion login = new InicioSesion();
	login.setNameProducto("SARA Web Builder");
	try {
		if (!isLoggedIn(req)) {
			try {
				//req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));         
				req.getSession(false).setAttribute("error", new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				setAttribute(req, "login", login);
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		String value = req.getParameter("BtnDic");
		Diccionario bean;
		if (req.getSession(false).getAttribute("tdicdat") != null) {
			// System.out.println("***************1**************");
			bean = ((Diccionario) req.getSession(false).getAttribute("tdicdat"));
		}
		else {
			// System.out.println("***************2**************");
			bean = new Diccionario();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder"));
			req.getSession(false).setAttribute("tdicdat", bean);
		}
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("DICCIONARIO DE DATOS");
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		if (value.equals("Otro")) {
			/**
			bean.getLogin().setCodact("34");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
			*/
				bean.getLogin().setCodact("205");
				bean.getLogin().setAccion("Consulta");
				if (!bean.getLogin().hasAccesAccion()) {
					//					res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
					callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
					return;
				}
			//}
			bean.consultar();
		}
		//
		if (value.equals("Agregar")) {
			bean.getLogin().setCodact("205");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCoddic(req.getParameter("TxtCoddic"));
			bean.setTxtdes(req.getParameter("TxtTxtdes"));
			bean.setNumlon(req.getParameter("TxtNumlon"));
			bean.setIdedic(req.getParameter("TxtIdedic"));
			bean.setTxtfor(req.getParameter("TxtTxtfor"));
			bean.setTipdat(req.getParameter("TxtTipdat"));
			bean.setTxtdem(req.getParameter("TxtTxtdem"));
			bean.setTxtfncval(req.getParameter("TxtFncVal"));
			bean.setTxthlp(req.getParameter("TxtHlp"));
			if (!validate(bean)) {
				bean.agregar();
				if (bean.getError().equals("")) {
					bean.setCoddic("");
					bean.setTxtdes("");
					bean.setNumlon("");
					bean.setIdedic("");
					bean.setTxtfor("");
					bean.setTipdat("");
					bean.setTxtfncval("");
					bean.setTxthlp("");
					bean.setTxtdem("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Diccionario/Diccionario.jsp");
			callPage(req, res, JspServlet.DICCIONARIO_JSP);
			return;
		}
		if (value.equals("Modificar")) {
			bean.getLogin().setCodact("205");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCoddic(req.getParameter("TxtCoddic"));
			bean.setTxtdes(req.getParameter("TxtTxtdes"));
			bean.setNumlon(req.getParameter("TxtNumlon"));
			bean.setIdedic(req.getParameter("TxtIdedic"));
			bean.setTxtfor(req.getParameter("TxtTxtfor"));
			bean.setTipdat(req.getParameter("TxtTipdat"));
			bean.setTxtdem(req.getParameter("TxtTxtdem"));
			bean.setTxtfncval(req.getParameter("TxtFncVal"));
			bean.setTxthlp(req.getParameter("TxtHlp"));
			if (!validate(bean)) {
				bean.modificar();
				if (bean.getError().equals("")) {
					bean.setCoddic("");
					bean.setTxtdes("");
					bean.setNumlon("");
					bean.setIdedic("");
					bean.setTxtfor("");
					bean.setTipdat("");
					bean.setTxtfncval("");
					bean.setTxthlp("");
					bean.setTxtdem("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Diccionario/Diccionario.jsp");
			callPage(req, res, JspServlet.DICCIONARIO_JSP);
			return;
		}
		if (value.equals("Eliminar")) {
			bean.getLogin().setCodact("205");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.eliminar(req);
			bean.setCoddic("");
			bean.setTxtdes("");
			bean.setNumlon("");
			bean.setIdedic("");
			bean.setTxtfor("");
			bean.setTipdat("");
			bean.setTxtfncval("");
			bean.setTxthlp("");
			bean.setTxtdem("");
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Diccionario/Diccionario.jsp");
			callPage(req, res, JspServlet.DICCIONARIO_JSP);
			return;
		}
		if (value.equals("Buscar")) {
			bean.setCoddic(req.getParameter("TxtCoddic"));
			bean.buscar();
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Diccionario/Diccionario.jsp");
			callPage(req, res, JspServlet.DICCIONARIO_JSP);
			return;
		}
		bean.setError("");
		bean.loadGrid();
		bean.setCoddic("");
		bean.setTxtdes("");
		bean.setNumlon("");
		bean.setIdedic("");
		bean.setTxtfor("");
		bean.setTipdat("");
		bean.setTxtfncval("");
		bean.setTxthlp("");
		bean.setTxtdem("");
		//		res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Diccionario/Diccionario.jsp");
		callPage(req, res, JspServlet.DICCIONARIO_JSP);
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
	Diccionario bean = (Diccionario) obj;
	try {
		bean.setError("");
		if (bean.getCoddic().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_COD_DICCIONARIO));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getCoddic())) {
			bean.setError(Mensajes.getMessage(Mensajes.COD_DICCIONARIO_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (bean.getNumlon().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_LONG_DATO));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getNumlon())) {
			bean.setError(Mensajes.getMessage(Mensajes.LONG_DATO_DEBE_CONTENER_NUMEROS));
			return true;
		}
		int seq = new Integer(bean.getNumlon()).intValue();
		bean.setNumlon("" + seq);
		if (seq < 0) {
			bean.setError(Mensajes.getMessage(Mensajes.LONG_DATO_DEBE_MAYOR_A_CERO));
			return true;
		}
		if (bean.getTxtdes().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_DESCRIPCION_DICCIONARIO));
			return true;
		}
		if (bean.getTxtfor().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_FORMATO_DATO));
			return true;
		}
		return false;
	}
	catch (Exception e) {
		throw new Exception("validate() -> DiccionarioServlet " + e.getMessage());
	}
}
}